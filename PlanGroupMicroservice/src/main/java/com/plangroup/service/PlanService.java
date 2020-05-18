package com.plangroup.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.plan.domain.Plan;
import com.plan.domain.PlanNotFoundException;


@Service
public class PlanService {

	
	
@SuppressWarnings("unchecked")
public Plan  getPlan( Long  planId){
	final String uri = "http://localhost:9000/plans/{planId}";
	 Plan plan = null;
	 Map<String, String> param = new HashMap<String, String>();
	    param.put("planId", planId.toString());
	   
	try {
		RestTemplate restTemplate = new RestTemplate();
	     plan = restTemplate.getForObject(uri, Plan.class,param);
	}catch(Exception e) {
		System.out.println("e"+e);
	}
	return plan;
		
	}
	
@SuppressWarnings("unchecked")
public List<Plan>  getPlans() throws PlanNotFoundException{
	final String uri = "http://localhost:9000/plans";
	 List<Plan> plans = null;
	 
 	    RestTemplate restTemplate = new RestTemplate();
 	    try{
		plans = restTemplate.getForObject(uri, List.class);
 	    }catch(PlanNotFoundException e){
	if(plans.isEmpty()||plans.size()==0){
		throw new PlanNotFoundException(e.getMessage());
	}}
	return plans;
		
	}
	
	
}
