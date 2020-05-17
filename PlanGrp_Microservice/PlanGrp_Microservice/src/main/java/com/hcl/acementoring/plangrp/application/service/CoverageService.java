package com.hcl.acementoring.plangrp.application.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.acementoring.coverage.application.coverage.Coverage;
import com.hcl.acementoring.coverage.application.coverage.CoverageNotFoundException;


@Service
public class CoverageService {

	
	
@SuppressWarnings("unchecked")
public Coverage  getCoverage(Long  planGrpId) throws CoverageNotFoundException{
	final String uri = "http://localhost:8085/coverages/{planGrpId}";
	 Coverage coverage = null;
	 Map<String, String> param = new HashMap<String, String>();
	    param.put("planGrpId", planGrpId.toString());
       
	try {
		RestTemplate restTemplate = new RestTemplate();
		coverage = restTemplate.getForObject(uri, Coverage.class,param);
	}catch(Exception e) {
		System.out.println("e"+e);
		throw new CoverageNotFoundException(e.getMessage());
	}
	System.out.println("coverage "+coverage.toString());
	return coverage;
		
	}
	

	
}
