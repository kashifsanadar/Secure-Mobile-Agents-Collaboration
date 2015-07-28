package com.magnet.securecollaboration.application;

import java.io.Serializable;

/**
 * @Description: This class represents the team leader of mobile agents team.
 */
public class TeamLead extends Agent implements Serializable
{ 

	// private data members
	
	public GoalDC teamGoal; // goal for individual team.
	
	public TeamLead() {
		teamGoal = new GoalDC();
	}
	
	//-- continuously listen for the feedback from team members
	public void listenFeedback(){}
	//-- Broadcast the success message to each team lead and the master agent
	public void broadcastSuccessMsg(){}
	//-- Sends the search failure message to the master agent. 
	public void sendFailureMsg(){}
}
