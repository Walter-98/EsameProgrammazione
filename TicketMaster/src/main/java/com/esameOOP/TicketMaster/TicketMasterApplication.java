package com.esameOOP.TicketMaster;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.esameOOP.TicketMaster.ApiCall.ApiCall;
import com.esameOOP.TicketMaster.filters.Filters;
import com.esameOOP.TicketMaster.stats.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TicketMasterApplication {

	public static void main(String[] args) throws Exception {
		

			
			Filters f = new Filters("US");

			f.filtrapertempo(3);
	}

}
