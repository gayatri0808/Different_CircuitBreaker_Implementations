package com.sentineldemo.plan.domain;



import org.springframework.stereotype.Component;

import com.sentineldemo.plan.domain.Plan;
@Component
public class Plan {
	private Long id;
	private String name;
	
	public Plan() {
		super();
	}

	public Plan(long id, String name) {
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
