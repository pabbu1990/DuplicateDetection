package com.validity.validity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ValidityApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ValidityApplication.class, args);
		DuplicatesService service = applicationContext.getBean(DuplicatesService.class);		
		service.getDuplicates(args.length>0 ? args : new String[] {"normal"});
	}

}
