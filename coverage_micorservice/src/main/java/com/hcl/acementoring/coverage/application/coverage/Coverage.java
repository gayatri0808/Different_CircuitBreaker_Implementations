package com.hcl.acementoring.coverage.application.coverage;



import org.springframework.stereotype.Component;

import com.hcl.acementoring.coverage.application.coverage.Coverage;




@Component
public class Coverage {
	private Long id;
	//Coverage is either medical or travel
	private String coverageName;
	//IssurerName is Bajaj Finance or  Apollo etc 
	private String IssurerName;
	

	@Override
	public String toString() {
		return "Coverage [id=" + id + ", coverageName=" + coverageName
				+ ", IssurerName=" + IssurerName + "]";
	}


	public Coverage(Long id, String coverageName, String issurerName) {
		super();
		this.id = id;
		this.coverageName = coverageName;
		IssurerName = issurerName;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCoverageName() {
		return coverageName;
	}


	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName;
	}


	public String getIssurerName() {
		return IssurerName;
	}


	public void setIssurerName(String issurerName) {
		IssurerName = issurerName;
	}


	public Coverage() {
		super();
	}

	

	
		
}
