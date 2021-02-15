package com.esameOOP.TicketMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.esameOOP.TicketMaster.Filters.Filters;

		/*
		* @author Walter Nigito
		* @author Filippo Polidori
		*/
@SpringBootApplication
public class TicketMasterApplication {

	public static void main(String[] args) throws Exception {
		
		Filters f = new Filters("US");
		f.filtrapertempo(3);
		SpringApplication.run(TicketMasterApplication.class, args);
	}

}
