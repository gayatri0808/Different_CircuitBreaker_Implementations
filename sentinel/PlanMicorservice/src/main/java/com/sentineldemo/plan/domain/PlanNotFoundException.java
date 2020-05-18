package com.sentineldemo.plan.domain;

public class PlanNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlanNotFoundException(String exception) {
		super(exception);
	}

}
