package com.esameOOP.TicketMaster.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.el.parser.ParseException;

public class APICall {
	private JSONArray ja = null;
	private JSONObject jo = null;
	
	public APICall() {
		this.jo = new JSONObject();
		this.ja = new JSONArray();
	}

	public JSONArray getArray() {
		return ja;
	}

	public void setArray(JSONArray ja) {
		this.ja = ja;
	}

	public JSONObject getObject() {
		return jo;
	}

	public void setObject(JSONObject jo) {
		this.jo = jo;
	}
	
	/**
	 * Inserisco un JSONObject nel mio JSONArray.
	 * @param jo JSONOnject
	 */
	public void insertObject(JSONObject jo) {
		this.ja.add(jo);
	}
	/**
	 * Metodo per scaricare un oggetto utilizzando API.
	 * Posso scegliere se scaricare un JSONObject oppure un JSONArray.
	 * 
	 * @param url URL da cui utilizzare la chiamata API.
	 * @param isObject Specifica se l'oggetto da salvare è un JSONObject oppure un JSONArray.
	 */
	public static JSONObject chiamataAPI(String url,boolean isObject) throws ParseException {
		 JSONArray jaja = new JSONArray();
		 JSONObject jojo = new JSONObject();
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String data = "";
			String line = "";
			try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
			   }
			} finally {
			   in.close();
			}
			
		
			if(isObject) {
				jojo = (JSONObject) JSONValue.parseWithException(data);	 //parse JSON Object
			} else {
				jaja = (JSONArray) JSONValue.parseWithException(data);	//parse JSON Array
				for(int i=0;i<jaja.size();i++) {
					jojo = (JSONObject) jaja.get(i);
					//System.out.println(i+") "+jo.get("title"));
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jojo;
	}
	
}
