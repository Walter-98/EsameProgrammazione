package com.esameOOP.TicketMaster.Filters;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONObject;


import com.esameOOP.TicketMaster.Exception.mesiException;
import com.esameOOP.TicketMaster.Exception.paeseException;
import com.esameOOP.TicketMaster.Service.ApiCall;
import com.esameOOP.TicketMaster.Stats.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
* @author Walter Nigito
* @author Filippo Polidori
* Classe filtri.Sulla conoscenza del paese filtraggio del genere,del periodo temporale e della parola chiave
*/

public class Filters {

	static ArrayList<Object> eventi;
	static String paese;

	
	
	
	// costruttore
	public Filters(String paese) throws paeseException,Exception {
		super();
		this.paese = paese;

		ApiCall obj = new ApiCall();
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;

		try {

			jsonInString = obj.cercaperpaese(paese);

			Map<String, Object> map = mapper.readValue(jsonInString, Map.class);

			String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

			Map<String, Object> map2 = (Map<String, Object>) map.get("_embedded");
			this.eventi = (ArrayList<Object>) map2.get("events");

		} catch(Exception e) {
    		e.printStackTrace();
    		throw new paeseException();
		}
	}

	
	
	
	
	// ricerca eventi per genere
	public static Map<String, Object> filtrapergenere(String genere) {
	
		for (int i = 0; i < eventi.size(); i++) {
			Map<String, Object> map2 = (Map<String, Object>) eventi.get(i);
			ArrayList<Object> arr1 = (ArrayList<Object>) map2.get("classifications");

			for (int j = 0; j < arr1.size(); j++) {
				Map<String, Object> map3 = (Map<String, Object>) arr1.get(j);
				Map<String, Object> map4 = (Map<String, Object>) map3.get("genre");

				if (map4.get("name").equals(genere)) {
					System.out.println(map2.get("name"));
					return map2;
				}
			}
		}
		return null;
		}		
	
	

	
	
	public static boolean checknome(String kw) {

		boolean check = false;

		for (int i = 0; i < eventi.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) eventi.get(i);

			if (((String) map.get("name")).contains(kw)) {
				check = true;

			}
		}
		return check;
	}

	
	
	//ricerca per parola chiave
	public static ArrayList<Object> filtraperkeyword(Vector<String> keywords) throws Exception {

		ArrayList<Object> eventiperkeyword;

		String jsonInString = null;

		for (int i = 0; i < keywords.size(); i++) {
			if (checknome(keywords.get(i))) {

				ApiCall obj = new ApiCall();
				ObjectMapper mapper = new ObjectMapper();
				jsonInString = obj.cercaPerParolaChiave(keywords.get(i), paese);
				Map<String, Object> map = mapper.readValue(jsonInString, Map.class);
				String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

				Map<String, Object> map2 = (Map<String, Object>) map.get("_embedded");
				eventiperkeyword = (ArrayList<Object>) map2.get("events");
				return eventiperkeyword;

			} else {
				ArrayList<Object>c = new ArrayList<Object>();
				c.add(("nessun evento trovato con il nome:" + keywords.get(i)));
				return c;
			}
		}
		return null;

	}
	
	
	
	//ricerca numero eventi per periodo di tempo
	public static ArrayList<String> filtrapertempo(int numeromesi) throws Exception,mesiException{

		/*
		 * numero mesi indica il tipo di filtraggio temporale 2 corrisponde a bimestrale
		 * 3 corrisponde a trimestrare 6 corrisponde a semestrale altri valori generano
		 * messaggi di errore
		 */

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
		try {
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
		}catch(Exception e) {
			e.printStackTrace();
    		throw new mesiException();
		}
		ArrayList<String>c= new ArrayList<String>();
		c.add("numero massimo di eventi ogni " + numeromesi + " mesi: " + max + " eventi");
		c.add("numero minimo di eventi ogni " + numeromesi + " mesi: " + min + " eventi");
		c.add("numero medio di eventi ogni " + numeromesi + " mesi:" + media + " eventi / "+numeromesi+" mesi");
		return c;
	}

}

