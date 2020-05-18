package com.coverage.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;


import com.coverage.domain.Coverage;
@Component
public class CoverageUtil {
	static List<Coverage> coverageList;
	
	public CoverageUtil(){
		coverageList= new ArrayList<Coverage>( 
		 Arrays.asList(new Coverage(1L,"MEdical","Appollo"),
		   new Coverage(2L,"Travel","Bajaj"),
		    new Coverage(3L,"General","Appollo"))
		);
	}

 
 public static  List<Coverage> getCoverages(){
	 return coverageList;
 }
 public  Optional<Coverage> findCoverageById(Long id){
	  return coverageList.stream().filter(coverage-> id.equals(coverage.getId())).findFirst();
 }


	
}
