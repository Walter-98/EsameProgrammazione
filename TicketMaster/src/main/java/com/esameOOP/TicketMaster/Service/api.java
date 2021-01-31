package com.esameOOP.TicketMaster.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class api {
	public static Map<String, Object> resultsActual = new HashMap<String, Object>();


public static Map<String, Object> actual(String url) {
		JSONParser parser = new JSONParser();

		try {
			URL weath = new URL(url);
			HttpsURLConnection yc = (HttpsURLConnection) weath.openConnection();
			yc.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

			String inputLine;
			String colonna = null;
			Object riga = null;
			while ((inputLine = in.readLine()) != null) {
				JSONObject statsA = (JSONObject) parser.parse(inputLine);
				HashMap<Object, Object> mp = new ObjectMapper().convertValue(statsA, HashMap.class);
				for(Entry<Object, Object> entry : mp.entrySet()) {
					 colonna=(String) entry.getKey();
					 riga=entry.getValue();	
					 resultsActual.put(colonna,riga);
				}
			}
			in.close();
		} catch (FileNotFoundException e11) {
					e11.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultsActual;
	}
}
