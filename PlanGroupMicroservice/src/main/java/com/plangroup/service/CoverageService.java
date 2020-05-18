package com.plangroup.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coverage.domain.Coverage;
import com.coverage.domain.CoverageNotFoundException;



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
	return coverage;
		
	}
	

	
}
