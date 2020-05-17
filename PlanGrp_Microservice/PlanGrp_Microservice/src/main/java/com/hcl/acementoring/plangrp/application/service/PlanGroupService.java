package com.hcl.acementoring.plangrp.application.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.acementoring.coverage.application.coverage.Coverage;
import com.hcl.acementoring.coverage.application.coverage.CoverageNotFoundException;
import com.hcl.acementoring.plan.application.plan.Plan;
import com.hcl.acementoring.plangrp.application.plangrp.PlanGroup;
import com.hcl.acementoring.plangrp.application.plangrp.PlanGroupNotFoundException;
import com.hcl.acementoring.plangrp.application.plangrp.PlanGroupUtil;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


@Service
public class PlanGroupService {


	@Autowired
	private PlanGroupUtil planGroupUtil;
	@Autowired
	private PlanService planService;
	@Autowired
	private CoverageService  coverageService;
	private List<Plan> plans;
	private List<PlanGroup> planGrps;
	
	@PostConstruct
    private void init(){
		planGrps=new ArrayList<>();	
		plans=new ArrayList<>();
		plans.add(new Plan());
		//planGrps.get(0).setPlans(plans);
		
		
    }	
	

public PlanGroup  getPlanGroup(Long  planGrpId){
		
	Plan plan;
	Optional<PlanGroup> planGrpOptional = null;
	PlanGroup planGrp = null;
	try {
		planGrpOptional=PlanGroupUtil.findPlanGroupById(planGrpId);
	if(planGrpOptional.isPresent()) {
		planGrp=planGrpOptional.get();
	}
	 plan=planService.getPlan(planGrp.getId());
	 planGrp.setPlans(Arrays.asList(plan));	
	
}catch(Exception e) {
		System.out.println("e"+e);
	}
	return planGrp;
		
	}
	


public List<PlanGroup>  getPlanGroups()	throws PlanGroupNotFoundException{
	
	List<PlanGroup> planGrps = null;

	
		planGrps=PlanGroupUtil.getPlanGroups();
		
	for(PlanGroup planGrp:planGrps) {
		List<Plan> plans=planService.getPlans();
	planGrp.setPlans(plans);
		
	}
	// this.sleep(3000);		
	return planGrps;
		
	}
	

public List<PlanGroup> getCoveragesForPlanGroups() throws CoverageNotFoundException {
	// TODO Auto-generated method stub
	List<PlanGroup> planGroups=getPlanGroups();
	for(PlanGroup planGrp:planGroups) {
		Coverage coverage=coverageService.getCoverage(planGrp.getId());
	planGrp.setCoverage(coverage);
	}
	return planGroups;
}
private void sleep(long time){
    try {
        Thread.sleep(time);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

public List<Plan> getFallbackPlans(Exception e) {
	System.out.println("in the Fallback() for the ratelimiter");
	System.out.println("*****"+e.getMessage());
    return this.plans;
}

}
