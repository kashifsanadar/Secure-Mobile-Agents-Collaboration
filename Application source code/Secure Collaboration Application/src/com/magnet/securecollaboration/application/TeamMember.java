package com.magnet.securecollaboration.application;

import java.io.Serializable;
 
/**
 * @Description: This class represents the team member of mobile agents team.
 */
public class TeamMember extends Agent implements Serializable
{
	
	// private members
	
	public GoalDC goal; // individual goal of mobile agent.
	
	// -- constructor
	/**
	 * @Description The default constructor. Initialze the goal instance.
	 */
	public TeamMember(){
		
		//-- initialize the goal
		this.goal = new GoalDC();
		}
	
	//-- This method searches for a particular file and return true or false
	//-- depending on the search result. 
	public boolean searchFile(String fileName, String driveName){
		
		return true;
	}
	
	//-- Sends the search success message to the team lead.
	public void sendSuccessMsg(){}
	
	//-- Sends the search message message to the team lead.
	public void sendFailureMsg(){}
}
