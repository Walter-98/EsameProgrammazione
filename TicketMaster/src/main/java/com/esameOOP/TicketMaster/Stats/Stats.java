package com.esameOOP.TicketMaster.Stats;


import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;

import com.esameOOP.TicketMaster.Exception.genereException;
import com.esameOOP.TicketMaster.Exception.mesiException;
import com.esameOOP.TicketMaster.Exception.paeseException;
import com.esameOOP.TicketMaster.Service.ApiCall;
import com.fasterxml.*;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
* @author Walter Nigito
* @author Filippo Polidori
* Classe Statistiche.Sulla conoscenza del paese statistiche sul genere sul periodo e sulla parola chiave
*/
public class Stats {

	static List<Object> eventi;
	Vector <String> generi = new Vector();
	static String paese;

	
	
	// costruttore della classe, contiene tutti gli eventi di uno stato, memorizzati
	// in eventi

	public Stats(String paese) throws paeseException,Exception{
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
		}catch(Exception e) {
    		e.printStackTrace();
          	throw new paeseException();
            
		}
			
		}
		
		
	
	
	// mi indica quanti eventi ci sono
	public static int numeroeventipaese() throws Exception {
		return eventi.size();
	}

	
	
	// numero totale di eventi raggruppati per genere
	public static HashMap<String, Integer> divisionegeneri ()throws genereException{

		HashMap<String, Integer> generi = new HashMap<String, Integer>();
		try {
		for (int i = 0; i < eventi.size(); i++) {
			Map<String, Object> map2 = (Map<String, Object>) eventi.get(i);
			ArrayList<Object> arr1 = (ArrayList<Object>) map2.get("classifications");

			for (int j = 0; j < arr1.size(); j++) {
				Map<String, Object> map3 = (Map<String, Object>) arr1.get(j);
				Map<String, Object> map4 = (Map<String, Object>) map3.get("genre");
			
				if (generi.get(map4.get("name")) == null) {
					generi.put((String)map4.get("name"), 1);
				} else {
					generi.put((String)map4.get("name"), generi.get((String)map4.get("name"))+1);
				}				
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
		throw new genereException();
	}
		return generi;
	}

	
	
	
	// numero minimo/massimo/medio di eventi mensili
	public static ArrayList<String> mediaeventimensili() throws Exception {
		
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
		 try {
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
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new mesiException();
		 }
		 ArrayList<String>c = new ArrayList<String>();
		 c.add("numero massimo di eventi in un mese: " + max + " eventi");
		 c.add("numero minimo di eventi in un mese:  " + min + " eventi");
		 c.add("media eventi mensili:                " +media+ " eventi/mese");
		return c;
	}

}


