package com.sentineldemo.plangroup.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sentineldemo.plan.domain.Plan;
import com.sentineldemo.plangroup.domain.PlanGroup;
import com.sentineldemo.plangroup.domain.PlanGroupNotFoundException;
import com.sentineldemo.plangroup.service.PlanGroupService;



@RestController
public class PlanGroupSentinelController {
	@Autowired
	private PlanGroupService planGroupService;
	private List<Plan> plans;
	private List<PlanGroup> planGrps;
	
	
	
	@GetMapping("/planGroups/plans")
//	@SentinelResource(value = "sentinelReource",fallback="getFallbackPlans")
	public List<PlanGroup> retrievePlanGroups() throws PlanGroupNotFoundException {
		
		List<PlanGroup> planGrps=planGroupService.getPlanGroups();
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
	/*public PlanGroup getFallbackPlans(Long  planGrpId,Exception e) {
		PlanGroup planGrp=new PlanGroup(99l,"Ratelimiter_backup_planGrp");	
	 plans=new ArrayList<>();
		plans.add(new Plan(9L,"backup_plan_ratelimiter"));
		planGrp.setPlans(plans);
		System.out.println("in the Fallback() for the ratelimiter");
		System.out.println("*****"+e.getMessage());
	  return planGrp;
	}
	*/
}
