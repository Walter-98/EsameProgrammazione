package com.esameOOP.TicketMaster.Exception;

import java.io.IOException;

public class paeseException extends IOException{
	
	//Eccezione in caso l'ID del paese è errato
	/*
	* @author Walter Nigito
	* @author Filippo Polidori
	*/
	private static final long serialVersionUID = 1L;
	
	public paeseException(){
		super();
	}
	public static String getException(){
	   String err ="Errore.Inserire correttamente l'ID del paese!";
	   return err;
	}
}
