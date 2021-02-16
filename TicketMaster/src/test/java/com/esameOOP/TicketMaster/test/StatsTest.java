package com.esameOOP.TicketMaster.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.*;

import com.esameOOP.TicketMaster.Exception.paeseException;
import com.esameOOP.TicketMaster.Service.ApiCall;
import com.esameOOP.TicketMaster.Stats.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
* @author Walter Nigito
* @author Filippo Polidori
*/

public class StatsTest {
	
	static List<Object> eventi;
	
	
	
	
	
	
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
	    Stats stats= new Stats(paese);
	    String DateInizio[] = {"2020-01-01T00:00:00Z","2020-02-01T00:00:00Z","2020-03-01T00:00:00Z","2020-04-01T00:00:00Z",
                "2020-05-01T00:00:00Z","2020-06-01T00:00:00Z","2020-07-01T00:00:00Z","2020-08-01T00:00:00Z",
                "2020-09-01T00:00:00Z","2020-10-01T00:00:00Z","2020-11-01T00:00:00Z","2020-12-01T00:00:00Z"};

	    String DateFine[] = {"2020-01-31T23:59:59Z","2020-02-28T23:59:59Z","2020-03-31T23:59:59Z","2020-04-30T23:59:59Z",
    		  "2020-05-31T23:59:59Z","2020-06-30T23:59:59Z","2020-07-31T23:59:59Z","2020-08-31T23:59:59Z",
    		  "2020-09-30T23:59:59Z","2020-10-3123:59:59Z","2020-11-30T59:59:59Z","2020-12-31T23:59:59Z"};
		int[] numeroeventi = new int[12];
		 int media = 0;
		 int min = 0;
		 int max = 0;
		 ArrayList<Object> eventi2 = null;
		 
		 for (int i =0;i<12;i++) {
		 
			 ApiCall obj2 = new ApiCall();
			 ObjectMapper mapper = new ObjectMapper();
			 String jsonInString = obj2.cercapertempo(DateInizio[i],DateFine[i], paese);
		
			 Map<String, Object> map = mapper.readValue(jsonInString, Map.class);

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
		 ArrayList<String>c = new ArrayList<String>();
		 c.add("numero massimo di eventi in un mese: " + max + " eventi");
		 c.add("numero minimo di eventi in un mese:  " + min + " eventi");
		 c.add("media eventi mensili:                " +media+ " eventi/mese");
			 assertEquals(c,Stats.mediaeventimensili());
			 
			
	}
	
}
