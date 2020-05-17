package com.hcl.acementoring.plangrp.application.plangrp;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.hcl.acementoring.coverage.application.coverage.Coverage;
import com.hcl.acementoring.plan.application.plan.Plan;




@Component
public class PlanGroup {
	
	private Long id;
	private String name;
	private List<Plan> plans;
	private Coverage coverage;
	
	public Coverage getCoverage() {
		return coverage;
	}

	public void setCoverage(Coverage coverage) {
		this.coverage = coverage;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> list) {
		this.plans = list;
	}

	public PlanGroup() {
		super();
	}

	public PlanGroup(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
   
	
		
}
