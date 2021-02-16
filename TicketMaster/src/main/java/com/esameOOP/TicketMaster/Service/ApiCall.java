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

         }catch(Exception e) {
         	throw new paeseException();
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
           
        }catch(Exception e) {
        	throw new paeseException();
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
           
        }catch(Exception e) {
        	throw new paeseException();
        }
        return null;
    }
}