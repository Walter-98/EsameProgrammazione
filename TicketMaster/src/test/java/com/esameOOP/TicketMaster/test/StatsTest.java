package com.esameOOP.TicketMaster.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esameOOP.TicketMaster.Service.ApiCall;
import com.esameOOP.TicketMaster.Stats.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
* @author Walter Nigito
* @author Filippo Polidori
*/

public class StatsTest {

	private Stats stats;
	private double mediaValue, varValue;
	private ApiCall call;

	
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	
	
	
	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * test del metodo che calcola la min/max/media
	 */
	@Test
	public void statisticsTest() throws Exception {
		String paese="US";
		String DateInizio = "2020-01-01T00:00:00Z";
		String DateFine ="2020-12-31T23:59:59Z";
		String jsonInString = call.cercaperpaese(paese);
		 int[] numeroeventi = new int[12];
		 int media = 0;
		 int min = 0;
		 int max = 0;
		 ArrayList<Object> eventi2 = null;
		for (int i =0;i<12;i++) {
			 
			 ApiCall obj2 = new ApiCall();
			 ObjectMapper mapper = new ObjectMapper();
			 String jsonInString1 = obj2.cercapertempo(DateInizio,DateFine, paese);
		
			 Map<String, Object> map = mapper.readValue(jsonInString1, Map.class);

			 String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

			 Map<String, Object> map2 = (Map<String, Object>) map.get("_embedded");

			 
			 if (map2 == null) {
				 numeroeventi[i]=0;
			 }else {
				 eventi2 = (ArrayList<Object>) map2.get("events");
				 numeroeventi[i]=eventi2.size();
			 }
			 
			 if (numeroeventi[i]>max) {
				 max = numeroeventi[i];
			 }
			 
			 if(i==0) {
				 min=numeroeventi[0];
			 }else {
				 if(numeroeventi[i]<min) {
					 min=numeroeventi[i];
				 }
			 }
			 
			 media = media + numeroeventi[i];

		 }
		 media = media / 12;
	}
	
}
