package com.hcl.acementoring.plangrp.application.plangrp;




import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.acementoring.coverage.application.coverage.CoverageNotFoundException;
import com.hcl.acementoring.plan.application.plan.Plan;
import com.hcl.acementoring.plangrp.application.service.PlanGroupService;



@RestController
public class PlanGroupController {
	@Autowired
	private PlanGroupService planGroupService;
	private List<Plan> plans;
	private List<PlanGroup> planGrps;
	
	@RateLimiter(name="planGroupService", fallbackMethod = "getFallbackPlans")
	@GetMapping("/planGroups/{planGrpId}")
	public PlanGroup retrievePlanGroup(@PathVariable Long  planGrpId) throws PlanGroupNotFoundException {
		
		PlanGroup planGrp=planGroupService.getPlanGroup(planGrpId);
		return planGrp;
	}
	/*@GetMapping("/plangroups/coverages/{planGrpId}")
	public PlanGroup retrieveCoveragesForPlanGroups(@PathVariable Long planGrpId) throws PlanGroupNotFoundException {
	PlanGroup planGrps=planGroupService.getCoveragesForPlanGroups(planGrpId);
		return planGrps;
	}*/
	
	@GetMapping("/planGroups/coverages")
	@Bulkhead(name = "coverageService", fallbackMethod = "BulkHead_fallbackmethod", type = Bulkhead.Type.SEMAPHORE)
	public List<PlanGroup> retrieveCoveragesForPlanGroups() throws CoverageNotFoundException {
		List<PlanGroup> planGrps=planGroupService.getCoveragesForPlanGroups();
		return planGrps;
	}
	
	
	@GetMapping("/planGroups/plans")
	@CircuitBreaker(name = "backendA",fallbackMethod="fallbackmethod")
	public List<PlanGroup> retrievePlanGroups() throws PlanGroupNotFoundException {
		
		List<PlanGroup> planGrps=planGroupService.getPlanGroups();
		return planGrps;
	}
	
	public List<PlanGroup> BulkHead_fallbackmethod(Exception e) {
		planGrps=new ArrayList<>();	
		plans=new ArrayList<>();
		plans.add(new Plan());
		System.out.println("in the Fallback() for the BulkHead_fallbackmethod");
		System.out.println("*****"+e.getMessage());
	  return planGrps;
	}
	public List<PlanGroup> fallbackmethod(Exception e) {
		planGrps=new ArrayList<>();	
		plans=new ArrayList<>();
		plans.add(new Plan(999L,"backup plan"));
		PlanGroup plnGrp=new PlanGroup(99L, "planGrP1");
		plnGrp.setPlans(plans);
		planGrps.add(plnGrp);
		System.out.println("in the Fallback() for the circuitBreaker");
		System.out.println("*****"+e.getMessage());
	  return planGrps;
	}
	public PlanGroup getFallbackPlans(Long  planGrpId,Exception e) {
		PlanGroup planGrp=new PlanGroup(99l,"Ratelimiter_backup_planGrp");	
	 plans=new ArrayList<>();
		plans.add(new Plan(9L,"backup_plan_ratelimiter"));
		planGrp.setPlans(plans);
		System.out.println("in the Fallback() for the ratelimiter");
		System.out.println("*****"+e.getMessage());
	  return planGrp;
	}
	
}
