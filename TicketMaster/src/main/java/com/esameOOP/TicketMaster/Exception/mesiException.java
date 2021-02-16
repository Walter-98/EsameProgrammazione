package com.esameOOP.TicketMaster.Exception;

import java.io.IOException;

public class mesiException extends IOException{
	//Eccezione in caso mese errato
		/*
		* @author Walter Nigito
		* @author Filippo Polidori
		*/
		private static final long serialVersionUID = 1L;
		
		public mesiException(){
			super();
		}
		public static String getException(){
		   String err ="Errore.Inserire numero corretto di mesi!";
		   return err;
		}
}
