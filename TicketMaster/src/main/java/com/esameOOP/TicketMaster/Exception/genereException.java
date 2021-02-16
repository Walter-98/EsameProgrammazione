package com.esameOOP.TicketMaster.Exception;

import java.io.IOException;

public class genereException extends IOException{
	//Eccezione in caso genere errato
			/*
			* @author Walter Nigito
			* @author Filippo Polidori
			*/
			private static final long serialVersionUID = 1L;
			
			public genereException(){
				super();
			}
			public static String getException(){
			   String err ="Inesistente.Inserire correttamente il genere!";
			   return err;
			}
}
