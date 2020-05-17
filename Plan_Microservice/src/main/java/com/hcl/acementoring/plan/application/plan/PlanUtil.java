package com.hcl.acementoring.plan.application.plan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.acementoring.plan.application.plan.Plan;

@Service
public class PlanUtil {
	static List<Plan> planList;
	
	public  PlanUtil(){
		planList= new ArrayList<Plan>( 
		 Arrays.asList(new Plan(1L,"plan1"),
		   new Plan(2L,"plan2")
		// , new Plan(3L,"plan3")
		 ));
	
	
	}

 
 public   List<Plan> findAll(){
return planList;
//	 return null;
 }
 public   Optional<Plan> findPlanById(Long id){
	  return planList.stream().filter(planGrp-> id.equals(planGrp.getId())).findFirst();
 }


	
}
