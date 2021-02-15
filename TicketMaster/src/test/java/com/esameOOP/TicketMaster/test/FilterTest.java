package com.esameOOP.TicketMaster.test;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esameOOP.TicketMaster.Filters.Filters;
import com.esameOOP.TicketMaster.Service.ApiCall;
import com.esameOOP.TicketMaster.Stats.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
* @author Walter Nigito
* @author Filippo Polidori
*/


public class FilterTest {
	
	private Filters filter;
	private ApiCall call;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	
	
	
	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * test del metodo filter
	 */
	@Test
	public void filtersTest() throws Exception {
		String genere ="rock";
		ArrayList<Object> eventi;
		ObjectMapper mapper=new ObjectMapper();
		String paese="US";
		String jsonInString = call.cercaperpaese(paese);
		Map<String, Object> map =mapper.readValue(jsonInString, Map.class);
		Map<String, Object> map2 = (Map<String, Object>) map.get("_embedded");
		eventi = (ArrayList<Object>) map2.get("events");
		for (int i = 0; i < eventi.size(); i++) {
			Map<String, Object> map21 = (Map<String, Object>) eventi.get(i);
			ArrayList<Object> arr1 = (ArrayList<Object>) map21.get("classifications");

			for (int j = 0; j < arr1.size(); j++) {
				Map<String, Object> map3 = (Map<String, Object>) arr1.get(j);
				Map<String, Object> map4 = (Map<String, Object>) map3.get("genre");

				if (map4.get("name").equals(genere)) {
					System.out.println(map21.get("name"));
				}
			}
		}
		
	}
	}

