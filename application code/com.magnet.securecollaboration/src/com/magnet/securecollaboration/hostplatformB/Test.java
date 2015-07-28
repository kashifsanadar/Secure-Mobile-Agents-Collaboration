package com.magnet.securecollaboration.hostplatformB;
import com.magnet.securecollaboration.tests.PKCS_7;

//-- import application to test application classes
import com.magnet.securecollaboration.application.*;
import java.io.File;
import java.io.IOException;

public class Test extends SocketAction{
	
	public static void main (String args[]) throws IOException{
			
		 //-- First be in a running state--
		//-- start the server thread to listen for the client connection requests
		
		System.out.println("Remote Host Platform B is up and running...");
		new Server().start(); 
	
	}
}
