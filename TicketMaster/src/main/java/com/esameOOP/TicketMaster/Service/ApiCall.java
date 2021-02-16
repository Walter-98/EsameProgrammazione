package com.esameOOP.TicketMaster.Service;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.esameOOP.TicketMaster.Exception.paeseException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

/*
* @author Walter Nigito
* @author Filippo Polidori
* Classe chiamata API. Inserendo paese e/o parola chiave o periodo temporale ritorno degli eventi 
*/

public class ApiCall {

    private final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() throws IOException {
        httpClient.close();
    }
    
    
    
    // CHIAMATA API
    // ricerca per parola chiave e paese
    public static String cercaPerParolaChiave(String parola, String paese) throws Exception {
    	 HttpGet request = new HttpGet("https://app.ticketmaster.com/discovery/v2/events.json?keyword="+parola+"&countryCode="+paese+"&apikey=FllJXbNKxY3a3AuWj9SpWFE8tc0siktY");
    
    	 try (CloseableHttpResponse response = httpClient.execute(request)) {

             HttpEntity entity = response.getEntity();
 
             if (entity != null) {
                 return EntityUtils.toString(entity);
             }

         }
		return null;
    }

    
    
    
    // CHIAMATA API
    // mi restituisce tutti gli eventi di uno stato
    public static String cercaperpaese(String paese) throws Exception,paeseException  {
        HttpGet request = new HttpGet("https://app.ticketmaster.com/discovery/v2/events.json?countryCode="+paese+"&apikey=FllJXbNKxY3a3AuWj9SpWFE8tc0siktY");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
        	

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
           
        }
        return null;
    }
    
    
    
    
    // CHIAMATA API
    // ricerca per periodo di tempo
    public static String cercapertempo(String datainizio,String datafine, String paese) throws Exception {
        HttpGet request = new HttpGet("https://app.ticketmaster.com/discovery/v2/events.json?countryCode="+paese+"&startDateTime="+datainizio+"&endDateTime="+datafine+"&apikey=FllJXbNKxY3a3AuWj9SpWFE8tc0siktY");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
           
        }
        return null;
    }
   public static String getPaese(String paese) {
	   String p[]= {"US","AD","AI","AR","AU","AT","AZ","BS","BH","BB","BE","BM","BR","BG","CA","CL","CN","CO","CR","HR",
	   		        "CY","CZ","DK","DO","EC","EE","FO","FI","FR","GE","DE","GH","GI","GB","GR","HK","HU","IS","IN","IE",
	   		        "IL","IT","JM","JP","KR","LV","LB","LT","LU","MY","MT","MX","MC","ME","MA","NL","AN","NZ","ND","NO",
	   		        "PE","PL","PT","RO","RU","LC","SA","RS","SG","SK","SI","ZA","ES","SE","CH","TW","TH","TT","TR","UA",
	   		        "AE","UY","VE"};
	   for(int i=0;i<p.length; i++){
	   if( paese== p[i]) {
		   return paese;
	   }else
		   return paeseException.getException();
	   }
	return paeseException.getException();
   }

}