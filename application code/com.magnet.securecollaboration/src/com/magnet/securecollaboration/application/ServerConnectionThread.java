package com.magnet.securecollaboration.application;


//-- import application to test application classes
import com.magnet.securecollaboration.application.*;

import java.io.IOException;
import java.net.Socket;
import sun.misc.BASE64Encoder;

/**
 * @Description: This class is used to fulfill the further requests from client
 * after establishing connection with the client.
 * @author Kashif Dar.
 *
 */
public class ServerConnectionThread extends SocketAction {

	private Server server = null;
	
	
	//-- implicit cons--
	public ServerConnectionThread(){}
	/**
	 * @Description Explicit constructor, calls the super class constructor to initialize the
	 * socket object.
	 * @param server: The server that invoked this thread
	 * @param sock: The Server socket with which client is connected
	 */
	public ServerConnectionThread(Server server, Socket sock) {
		super (sock);
		this.server = server;
	}
	
	/**
	 * @Description: Receive the team of mobile agents.
	 * @param void
	 * @return void
	 */
	public void run () {
		
		//--1. Receiving the remote object.--
		MATeam maTeam= new MATeam();//-- create the catching object
		
		//-- receive object from the remote host
		try {
			maTeam = (MATeam) super.receiveObject();
		} 
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//-- access the property of the object to ensure that it has restored
		System.out.println(maTeam.goal.getFileName());
		
		//--2. Perform the required task of searching the file--
		//--3. Send or receive the messages to or from other remote hosts--
		//---- including the home platform.
		
		//-- 3.1 Authentication Demo.
		
		Authentication auth = new Authentication();
		
		String str = auth.generateChallenge();
		System.out.println(str);
		super.send(str);
		
		
		
		System.out.println("Auth Demo is over....");
		
	    //--4. Report the end result to master agent and other remote platform--
		//---- if succeeded
		
		if (maTeam.teamMember[0].searchFile(maTeam.teamMember[0].goal.getFileName(),
											maTeam.teamMember[0].goal.getHosDrivetName())== true){
			//-- In case of true, file has been found
			//-- Now, send a special message to other remote hosts
		}
		else //-- file has not been found
		{
			//-- Send the failure message to the team lead
		}
		   
	    
	   
	     //--5. Destroy the agents.--
	}
}
