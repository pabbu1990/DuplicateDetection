package com.validity.validity;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DuplicatesResource {
	
	private DuplicatesService duplicatesService;
	
	@Inject
	public DuplicatesResource(DuplicatesService duplicatesService) {
		this.duplicatesService = duplicatesService;
	}
	
	@GetMapping("/duplicates")
	public DuplicatesResponse getDuplicateEntriesDefault(){
		String[] args = new String[] {"normal"};
		return duplicatesService.getDuplicates(args);
	}
	
	@GetMapping("/duplicates/{fileName}")
	public DuplicatesResponse getDuplicateEntries(@PathVariable("fileName") String fileName){
		String[] args = new String[] {fileName};
		return duplicatesService.getDuplicates(args);
	}
}
