package com.esameOOP.TicketMaster.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import com.esameOOP.TicketMaster.ApiCall.ApiCall;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Filters {

	ArrayList<Object> eventi;
	String paese;

	// costruttore
	public Filters(String paese) throws Exception {
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

		} finally {
			obj.close();

		}
	}

	// ricerca eventi per genere
	public void filtrapergenere(String genere) {

		for (int i = 0; i < eventi.size(); i++) {
			Map<String, Object> map2 = (Map<String, Object>) eventi.get(i);
			ArrayList<Object> arr1 = (ArrayList<Object>) map2.get("classifications");
			for (int j = 0; j < arr1.size(); j++) {
				Map<String, Object> map3 = (Map<String, Object>) arr1.get(j);
				Map<String, Object> map4 = (Map<String, Object>) map3.get("genre");

				if (map4.get("name").equals(genere)) {
					System.out.println(map2.get("name"));
				}
			}
		}
	}

	public boolean checknome(String kw) {

		boolean check = false;

		for (int i = 0; i < eventi.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) eventi.get(i);

			if (((String) map.get("name")).contains(kw)) {
				check = true;

			}
		}
		return check;
	}

	public void filtraperkeyword(Vector<String> keywords) throws Exception {
		
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
				System.out.println(eventiperkeyword);

			}else {
				System.out.println("nessun evento trovato con il nome:" + keywords.get(i));
			}
		}

	}

}
