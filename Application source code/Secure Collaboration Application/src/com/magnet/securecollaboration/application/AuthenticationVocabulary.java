package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This class contains data elements needed for mutual authentication
 * between sender and receiver.
 */
public interface AuthenticationVocabulary {
	
	//-- The name of ontology
	public  static final String NAME = "Authentication";	
	
	//1.--Nonce value by the sender-- 
	public static final String S_NONCE = "Sender-Nonce";
	
	//2.--Nonce value by the receiver..
	public static final String R_NONCE = "Receiver-Nonce";


}
