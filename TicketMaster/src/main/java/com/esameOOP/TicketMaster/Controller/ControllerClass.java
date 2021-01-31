package com.esameOOP.TicketMaster.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esameOOP.TicketMaster.Filters.FilterCountries;
import com.esameOOP.TicketMaster.Service.APICall;
import com.esameOOP.TicketMaster.Service.api;
import com.sun.el.parser.ParseException;

@RestController
public class ControllerClass {
	
	
	
	private static String url="https://app.ticketmaster.com/discovery/v2";
	private static String urls= ".json?";
	private static String apikey="FllJXbNKxY3a3AuWj9SpWFE8tc0siktY";

	
	//Get a list of all events in the "countryCode"
	/*Get details for a specific event using the unique identifier for the event. This includes the venue and location,
	 *  the attraction(s), and the Ticketmaster Website URL for purchasing tickets for the event.
	 * Search for events 
	 * sourced by "Ticketmaster"
	 * in the "UK"
	 * with keyword "name of event" 
	 * 
	 * ID of the event*/
	@RequestMapping(value = "/all/events",method = RequestMethod.GET)
	public JSONObject callAllEvents(
			@RequestParam(name="keyword",defaultValue="") String keyword,
			@RequestParam(name="source",defaultValue="ticketmaster") String source,
			@RequestParam(name="countryCode",defaultValue="US") String countryCode) throws ParseException {
		String URL=(url+"/events"+urls+"&countryCode="+countryCode+"&apikey="+apikey);
		return APICall.chiamataAPI(URL,true);
		
		
	}

	/*Find attractions (artists, sports, packages, plays and so on) and filter your search by name, and much more.
	 * Search for events 
	 * sourced by "Ticketmaster"
	 * in the "UK"
	 * with keyword "name of event"
	 * */
	@RequestMapping(value = "/all/attractions",method = RequestMethod.GET)
	public JSONObject callAllAttractions(
			@RequestParam(name="keyword",defaultValue="") String keyword,
			@RequestParam(name="source",defaultValue="ticketmaster") String source,
			@RequestParam(name="countryCode",defaultValue="US") String countryCode) throws ParseException {
		String URL=(url+"/attractions"+urls+"keyword="+keyword+"&source="+source+"&countryCode="+countryCode+"&apikey="+apikey);
		return APICall.chiamataAPI(URL,true);
	}
	
	
	
	
	/*Find classifications and filter your search by name, and much more.
	 *  Classifications help define the nature of attractions and events.
	 *  Search for events 
	 * sourced by "Ticketmaster"
	 * in the "UK"
	 * with keyword "name of event"
	 * */
	@RequestMapping(value = "/all/classifications",method = RequestMethod.GET)
	public JSONObject callAllClassifications(
			@RequestParam(name="keyword",defaultValue="") String keyword,
			@RequestParam(name="source",defaultValue="ticketmaster") String source,
			@RequestParam(name="countryCode",defaultValue="US") String countryCode) throws ParseException {
		String URL=(url+"/attractions"+urls+"keyword="+keyword+"&source="+source+"&countryCode="+countryCode+"&apikey="+apikey);
		return APICall.chiamataAPI(URL,true);
	}
	 /*Find venues and filter your search by name, and much more. 
	 * Search for events 
	 * sourced by "Ticketmaster"
	 * in the "UK"
	 * with keyword "name of event"
	 * */
	@RequestMapping(value = "/all/venues",method = RequestMethod.GET)
	public JSONObject callAllVenues(
			@RequestParam(name="keyword",defaultValue="") String keyword,
			@RequestParam(name="source",defaultValue="ticketmaster") String source,
			@RequestParam(name="countryCode",defaultValue="US") String countryCode) throws ParseException {
		String URL=(url+"/attractions"+urls+"keyword="+keyword+"&source="+source+"&countryCode="+countryCode+"&apikey="+apikey);
		return APICall.chiamataAPI(URL,true);
	}
	
	
	@RequestMapping(value="/getcountries",method = RequestMethod.POST)
	public List<String> getCountries(@RequestBody  String countryCode) throws ParseException {
		List<String>value= new ArrayList<String>();
		value=FilterCountries.getCoutries(countryCode);
		return value;
	}
	
	
	@RequestMapping(value="/filtercountries",method = RequestMethod.GET)
	public JSONObject filterCountries(List<String> value) throws ParseException {
		JSONObject res = new JSONObject();
		for(int i=0; i<value.size();i++) {
			String URL=(url+"/events"+urls+"&countryCode="+value.get(i)+"&apikey="+apikey);	
			 res =APICall.chiamataAPI(URL,true); 
		}
		return res;
	}
}
