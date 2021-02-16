package com.esameOOP.TicketMaster.test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.*;

import com.esameOOP.TicketMaster.Filters.Filters;
import com.esameOOP.TicketMaster.Service.ApiCall;
import com.esameOOP.TicketMaster.Stats.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
* @author Walter Nigito
* @author Filippo Polidori
*/


public class FilterTest {
	
	
	
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
		int numeromesi=6;
		String paese="US";
		 Filters filter= new Filters(paese);
		 String DateInizio[] = { "2020-01-01T00:00:00Z", "2020-02-01T00:00:00Z", "2020-03-01T00:00:00Z",
					"2020-04-01T00:00:00Z", "2020-05-01T00:00:00Z", "2020-06-01T00:00:00Z", "2020-07-01T00:00:00Z",
					"2020-08-01T00:00:00Z", "2020-09-01T00:00:00Z", "2020-10-01T00:00:00Z", "2020-11-01T00:00:00Z",
					"2020-12-01T00:00:00Z" };

			String DateFine[] = { "2020-01-31T23:59:59Z", "2020-02-28T23:59:59Z", "2020-03-31T23:59:59Z",
					"2020-04-30T23:59:59Z", "2020-05-31T23:59:59Z", "2020-06-30T23:59:59Z", "2020-07-31T23:59:59Z",
					"2020-08-31T23:59:59Z", "2020-09-30T23:59:59Z", "2020-10-3123:59:59Z", "2020-11-30T59:59:59Z",
					"2020-12-31T23:59:59Z" };

			int counterInizio = 0;
			int counterFine = (numeromesi - 1);
			Vector<Integer> numeroeventi = new Vector();
			int media = 0;
			int min = 0;
			int max = 0;
			ArrayList<Object> eventi2 = null;

			for (;;) {

				ApiCall obj2 = new ApiCall();
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = obj2.cercapertempo(DateInizio[counterInizio], DateFine[counterFine], paese);

				Map<String, Object> map = mapper.readValue(jsonInString, Map.class);

				String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

				Map<String, Object> map2 = (Map<String, Object>) map.get("_embedded");

				if (map2 == null) {
					numeroeventi.add(0);
				} else {
					eventi2 = (ArrayList<Object>) map2.get("events");
					numeroeventi.add(eventi2.size());
				}

				counterInizio = counterInizio + numeromesi;
				counterFine = counterFine + numeromesi;

				if (counterFine > 11) {
					break;
				}
			}
			min = numeroeventi.get(0);
			max = numeroeventi.get(0);

			for (int j = 0; j < numeroeventi.size(); j++) {

				if (numeroeventi.get(j) > max) {
					max = numeroeventi.get(j);

					if (numeroeventi.get(j) < min) {
						min = numeroeventi.get(j);
					}
					media = media + numeroeventi.get(j);
				}
			}
			media = media / numeroeventi.size();
			ArrayList<String>c= new ArrayList<String>();
			c.add("numero massimo di eventi ogni " + numeromesi + " mesi: " + max + " eventi");
			c.add("numero minimo di eventi ogni " + numeromesi + " mesi: " + min + " eventi");
			c.add("numero medio di eventi ogni " + numeromesi + " mesi:" + media + " eventi / "+numeromesi+" mesi");
			assertEquals(c,Filters.filtrapertempo(numeromesi));
	}
}

