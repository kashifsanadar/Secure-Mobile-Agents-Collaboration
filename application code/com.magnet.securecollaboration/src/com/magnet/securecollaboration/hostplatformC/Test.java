package com.magnet.securecollaboration.hostplatformC;

//-- import application to test application classes
import com.magnet.securecollaboration.application.*;

import java.io.File;
import java.io.IOException;

public class Test {
	
	public static void main (String args[]) throws IOException{
			
		 //-- First be in a running state--
		//-- start the server thread to listen for the client connection requests
		
		System.out.println("Remote Host Platform C is up and running...");
		new Server().start(); 
	}
}
