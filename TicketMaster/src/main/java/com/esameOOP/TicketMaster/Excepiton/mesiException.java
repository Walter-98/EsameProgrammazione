package com.esameOOP.TicketMaster.Excepiton;

import java.io.IOException;

public class mesiException extends IOException{
	
	//Eccezione in caso del numero di mesi è errato
	/*
	* @author Walter Nigito
	* @author Filippo Polidori
	*/
	private static final long serialVersionUID = 1L;
	
	public mesiException(){
		super();
	}
	public String getMessaggio() {
		return "Errore.Inserire numero mesi minimo :2,massimo: 12";
	} 

}
