package com.validity.validity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.codec.language.Metaphone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class DuplicatesService {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidityApplication.class);
	private static final String COMMA_DELIMITER = ",";
	private List<Person> persons;
	private LinkedHashSet<Person> duplicates;
	private DuplicatesResponse response;
	
	public DuplicatesResponse getDuplicates(String[] args) {
		String name = (args.length>0 ? args[0]+".csv" : "normal.csv");
		persons = new ArrayList<>();
		response = new DuplicatesResponse();
		duplicates = new LinkedHashSet<>();
		readInputAndProcess(name);
		if(duplicates.size()>0) {
			List<Person> duplicateList = new ArrayList<>();
			duplicateList.addAll(duplicates);
			response.setDuplicates(duplicateList);
			System.out.println("Potential Duplicates: \n");
			for(Person person: duplicates) {
				if(person.isDuplicate()) {
					System.out.println(person.toString());
				}
			}
		}
		System.out.println("\n");
		System.out.println("Non Duplicates: \n");
		List<Person> nonDuplicates = new ArrayList<>();
		for(Person person: persons) {
			if(!person.isDuplicate()) {
				nonDuplicates.add(person);
				System.out.println(person.toString());
			}
		}
		response.setNonDuplicates(nonDuplicates);
		return response;
	}
		
	public void readInputAndProcess(String fileName){
		 BufferedReader br;
		 try{	
			Resource resource =  new ClassPathResource(fileName);
			br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			String line;
			boolean first = true;
		    while ((line = br.readLine()) != null) {
		    		if(first) {
		    			first = false;
		    			continue;
		    		}
		        String[] elements = line.split(COMMA_DELIMITER);
		        Person person = new Person();
		        int recordLength = elements.length;
		        int index = 0;
		        person.setId(elements[index]);
		        person.setFirstName((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setLastName((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setCompany((recordLength-1<=index) ? null : elements[index+=1]);
		        if(elements.length > 12) {
			        	if(!(recordLength-1<=index)) {
			        		person.setCompany(person.getCompany() + "," + elements[index+=1]);
			        	}
		        }
		        person.setEmail((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setAddress1((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setAddress2((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setZip((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setCity((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setStateLong((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setState((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setPhone((recordLength-1<=index) ? null : elements[index+=1]);
		        person.setDuplicate(false);
		        persons.add(person);
		    }
		 } 
		 catch(FileNotFoundException e) {
			 logger.error(e.toString());
		 }
		 catch(IOException e) {
			 logger.error(e.toString());
		 }
		 findDuplicates();	 
	}
	
	private void findDuplicates() {
		Metaphone metaphone = new Metaphone();
		for(int i=1; i<persons.size(); i++) {
			for(int j=2; j<persons.size(); j++) {
				if((i!=j) && (!persons.get(i).isDuplicate() || !persons.get(j).isDuplicate())) {
					if(metaphone.isMetaphoneEqual(persons.get(i).getFirstName(), persons.get(j).getFirstName())
							&& metaphone.isMetaphoneEqual(persons.get(i).getLastName(), persons.get(j).getLastName())
							&& metaphone.isMetaphoneEqual(persons.get(i).getEmail(), persons.get(j).getEmail())) {
						persons.get(i).setDuplicate(true);
						persons.get(j).setDuplicate(true);
						duplicates.add(persons.get(i));
						duplicates.add(persons.get(j));
					}
				}
			}
		}
	}
}
