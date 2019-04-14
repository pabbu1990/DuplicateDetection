package com.validity.validity;

import java.util.List;

public class DuplicatesResponse {
	
	private List<Person> duplicates;
	
	private List<Person> nonDuplicates;

	public List<Person> getDuplicates() {
		return duplicates;
	}

	public void setDuplicates(List<Person> duplicates) {
		this.duplicates = duplicates;
	}

	public List<Person> getNonDuplicates() {
		return nonDuplicates;
	}

	public void setNonDuplicates(List<Person> nonDuplicates) {
		this.nonDuplicates = nonDuplicates;
	}
	

}
