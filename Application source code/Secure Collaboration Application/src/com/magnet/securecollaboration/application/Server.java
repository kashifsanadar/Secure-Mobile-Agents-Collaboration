package com.magnet.securecollaboration.application;

//--Server.java--

//--Imports--
import java.net.*;
import java.io.*;

/**
 * @Description: The simple server class that establishes the connection with 
 * client and invokes the ServerConnectiontThread to fulfill the requests from client.  
 * @author Kashif
 *
 */
public class Server extends Thread {

	//-- Data members--  
	public static final int PORTNUM = 1234;
	private ServerSocket    port;
    private  Socket clientSocket;//-- take a client socket object--

	/**
	 * @Description The default constructor, calls the super class constructor.
	 * initialize the Server socket port.
	 * @throws IOException
	 */
    public Server() {
	    
		super("Server");
	    
		//--Try to grab the port--
	    try {
	      port = new ServerSocket(PORTNUM);
	    }
	    catch (IOException e) {
	      System.out.println("Couldn't access port " + PORTNUM + ": " + e);
	      System.exit(1);
	    }
	  }

	/**
	 * @Description Listen continuously to connect with the client, if client request
	 * is found, initialize the client socket object and runs a separate server thread
	 * for the client request. 
	 */  
    public void run() {
	    //-- check for the server port--
	    while (true) {
	      if (port == null) {
	        System.out.println("Sorry, the port disappeared.");
	        System.exit(1);
	      }
	      
	      try {
	        
	    	//-- wait until client request.
	    	clientSocket = port.accept();
	    	
	    	System.out.println("Successfully connected with the remote host:");
	        
	        //-- run a separate server thread for the client request
	    	//-- it will initialize input and output streams associated with the
	    	//-- specified ports by invoking the constructor of SocketAction class.
	    	
	    	new ServerConnectionThread(this, clientSocket).start();
	      }
	      catch (IOException e) {
	        System.out.println("Couldn't connect: " + e);
	        System.exit(1);
	      }
	    } // end while loop
	  }

	 //-- close the port--
	  public void finalize() {
	    if (port != null) {
	      try { 
	        port.close(); 
	      }
	      catch (IOException e) {
	        System.out.println("Error closing port: " + e);
	      }
	      port = null;
	    }
	  }
}
