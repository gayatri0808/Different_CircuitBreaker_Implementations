package com.sentineldemo.plangroup.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sentineldemo.plangroup.domain.PlanGroup;

@Service
public class PlanGroupUtil {
	static List<PlanGroup> planGroupList;
	
	public PlanGroupUtil(){
		planGroupList= new ArrayList<PlanGroup>( 
		 Arrays.asList(new PlanGroup(1L,"planGroup1"),
		   new PlanGroup(2L,"planGroup1"),
		    new PlanGroup(3L,"planGroup1"))
		);
	}

 
 public static  List<PlanGroup> getPlanGroups(){
	 return planGroupList;
 }
 public static  Optional<PlanGroup> findPlanGroupById(Long id){
	  return planGroupList.stream().filter(planGrp-> id.equals(planGrp.getId())).findFirst();
 }

	
}
