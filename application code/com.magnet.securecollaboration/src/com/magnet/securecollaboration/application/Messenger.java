package com.magnet.securecollaboration.application;

/**
 * 
 * @author Kashif Dar
 * @Description This class is used to represent a messenger agent that carries message
 * from one agent to another.
 *
 */
public class Messenger extends Agent{
	//-- Private data members
	private String pkcs7EnvMessage;
	
	/**
	 * @Description: Set the value of message to send.
	 * @param String: Message to send
	 * @return void
	 */
	public void setMessage(String msg){
		this.pkcs7EnvMessage = msg;
	}
	
	/**
	 * @Description: Used to retrieve the of message that Messenger agent carries.
	 * @param void
	 * @return String: the message
	 */
	
	public String getMessage(){
		return this.pkcs7EnvMessage;
	}

}
