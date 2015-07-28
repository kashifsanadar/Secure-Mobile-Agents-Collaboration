package com.magnet.securecollaboration.application;


/**
 * @author Kashif Dar
 * @Description This class is the top most general class of the agents community involved in our application.
 * It simply contains two constructors to initialize the agent ID field.
 */
public class Agent implements java.io.Serializable {
	
	public long agentID_;
	
	/**
	 * @author Kashif Dar
	 * @Description: Default constructor, simply allots the randomly generated 
	 * agent ID to newly generated agent. 
	 */
	public Agent() {
			this.agentID_ = System.currentTimeMillis();	
	}
	
	/**
	 * @author Kashif Dar
	 * @Description: Parameterized condtructor, simple allots the received 
	 * agent ID to newly generated agent. 
	 */
	public Agent(long agentID) {
		
		this.agentID_ = agentID;
	}

}
