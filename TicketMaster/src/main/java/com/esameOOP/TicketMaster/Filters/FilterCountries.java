package com.esameOOP.TicketMaster.Filters;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.esameOOP.TicketMaster.Service.APICall;
import com.sun.el.parser.ParseException;

public class FilterCountries {
	
	
	public static List<String> getCoutries(String countryCode) throws ParseException {
		List<String>value = new ArrayList<String>();
		//full code's Europe in this array
		String [] code= {"AT","AZ","BE","BG","HR","CY","CZ","DK","EE","FI","FR","DE","GB","GR","HU","IS","IE","IT","LU"
		,"MT","MC","NL","AN","ND","PL","PT","RO","RS","SK","SI","ES","SE","CH","UA"	};		
		for(int i=0;i<code.length;i++) {
			if(countryCode==code[i]) {
				value.add(countryCode);
			}
		else
			return null;
		}
	return value;
	}
}
