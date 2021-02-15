package com.esameOOP.TicketMaster.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esameOOP.TicketMaster.Filters.Filters;
import com.esameOOP.TicketMaster.Service.ApiCall;
import com.esameOOP.TicketMaster.Stats.Stats;
import com.sun.el.parser.ParseException;


/*
* @author Walter Nigito
* @author Filippo Polidori
*/

@RestController
public class ControllerClass {

	
	
	//Chiamata API  passando parola chiave epaese
	 @RequestMapping(value = "/keyword",method = RequestMethod.GET) public
	 String chiamataParolaChiave(  
	  @RequestParam(name="parola",defaultValue="") String parola,
	  @RequestParam(name="paese",defaultValue="US") String paese)throws Exception { 
	  return ApiCall.cercaPerParolaChiave(parola,paese);
	  }
	 
	 
	 //Chiamata API passando solo il paese
	 @RequestMapping(value = "/country",method = RequestMethod.GET) public
	 String chiamataPaese( 
	  @RequestParam(name="paese",defaultValue="US") String paese)throws Exception { 
	  return ApiCall.cercaperpaese(paese);
	  }
	 
	 
	 
	//Chiamata API passando solo una data iniziale e una finale yyyy/mm/ggT00:00Z
	 @RequestMapping(value = "/time",method = RequestMethod.GET) public
	 String chiamataPerTempo(  
			 @RequestParam(name="datainizio",defaultValue="") String datainizio,
			 @RequestParam(name="datafine",defaultValue="") String datafine,
			 @RequestParam(name="countryCode",defaultValue="US") String paese)throws Exception { 
	  return ApiCall.cercapertempo(datainizio,datafine,paese);
	  }
	 
	 
	 
	 //Filtraggio per genere
	 @RequestMapping(value = "/filterGenere",method = RequestMethod.GET) public
	 Map<String,Object> chiamataFiltri( 
			 @RequestParam(name="paese",defaultValue="US")String paese,
			 @RequestParam(name="genere",defaultValue="rock") String genere)throws Exception {
		 Filters filter= new Filters(paese);
		  return Filters.filtrapergenere(genere);
	 }
	 
	 //Filtraggio per parola chiave
	 @RequestMapping(value = "/filterParola",method = RequestMethod.GET) public
	 ArrayList<Object> chiamataFiltriKeyword( 
			 @RequestParam(name="paese",defaultValue="US")String paese,
			 @RequestParam(name="parola",defaultValue="rock") Vector<String> keyword)throws Exception {
		 Filters filter= new Filters(paese);
		  return Filters.filtraperkeyword(keyword);
	 }
	 
	 
	 
	 //Filtraggio per periodo temporale
	 @RequestMapping(value = "/filterTempo",method = RequestMethod.GET) public
	 ArrayList<String> chiamataFiltriTempo(
			 @RequestParam(name="paese",defaultValue="US")String paese,
			 @RequestParam(name="mesi",defaultValue="6") int numero)throws Exception {
		 Filters filter= new Filters(paese);
		  return Filters.filtrapertempo(numero);
	 }
	 
	 
	 
	 //Statistiche eventi per paese
	 @RequestMapping(value = "/statsEventi",method = RequestMethod.GET) public
	 int chiamataStatsEventi(@RequestParam(name="paese",defaultValue="US") String paese) throws Exception {
		 Stats stats= new Stats(paese);
	  return Stats.numeroeventipaese();
	 }
	 
	 
	 
	 
	 //Statistiche per generi
	 @RequestMapping(value = "/statsGeneri",method = RequestMethod.GET) public
	 HashMap<String,Integer> chiamataStatsGeneri(@RequestParam(name="paese",defaultValue="US") String paese)throws Exception {
		 Stats stats= new Stats(paese);
		 return Stats.divisionegeneri();
	 }
	 
	 
	 
	 
	 //Statistiche su min max e media
	 @RequestMapping(value = "/statsMedia",method = RequestMethod.GET) public
	 ArrayList<String> chiamataMedia(@RequestParam(name="paese",defaultValue="US") String paese)throws Exception {
		 Stats stats= new Stats(paese);
		 return Stats.mediaeventimensili();
	 }
}
