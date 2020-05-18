package com.plan.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.plan.domain.Plan;
import com.plan.domain.PlanNotFoundException;
import com.plan.util.PlanUtil;




@RestController
public class PlanResource {

	@Autowired
	private PlanUtil planUtil;

	
		
	//the URL is used to run the CIRCUIT BREAKER DEMO
	@GetMapping("/plans/{planId}")
	public Plan retrievePlansForPlanGrp(@PathVariable Long planId) {
		System.out.println(planId);
		Optional<Plan> plans=planUtil.findPlanById(planId);
		
		if((plans.get()==null)) {
			throw new PlanNotFoundException("plans are not available");
		}else
			return plans.get();
	}
	
	
	
	@GetMapping("/plans")
	public List<Plan> retrieveAllPlans() throws  PlanNotFoundException{
		List<Plan> plans=planUtil.findAll();
		
		if((plans==null)||(plans.isEmpty()||plans.size()==0)) {
			throw new PlanNotFoundException("plans are not available");
		}else
			return plans;
	}
	
}
