package com.coverage.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coverage.domain.Coverage;
import com.coverage.domain.CoverageNotFoundException;
import com.coverage.util.CoverageUtil;



@RestController
public class CoverageResource {

	@Autowired
	private CoverageUtil coverageUtil;


	//the URL is used to run the CIRCUIT BREAKER DEMO
	@GetMapping("/coverages/{planGrpId}")
	// @Bulkhead(name = "coverageService", fallbackMethod = "BulkHead_fallbackmethod", type = Bulkhead.Type.SEMAPHORE)
  public Coverage retrieveCoveragesForPlanGrp(@PathVariable("planGrpId") Long planGrpId) throws CoverageNotFoundException{
		System.out.println("1"+planGrpId);
		Optional<Coverage> coverages=coverageUtil.findCoverageById(planGrpId);
		if((coverages.get()==null)) {
			throw new CoverageNotFoundException("coverage are not available");
		}else
			return coverages.get();
	}
	
	/*public Coverage BulkHead_fallbackmethod(Long id,Exception e) {
		List<Coverage> coverages=new ArrayList<>();
		coverages.add(new Coverage(9999l,"backup_coverage", "Issuerer"));
		System.out.println("in the coverage  fallback method");
		return coverages.get(0);
	}
	
*/	

}
