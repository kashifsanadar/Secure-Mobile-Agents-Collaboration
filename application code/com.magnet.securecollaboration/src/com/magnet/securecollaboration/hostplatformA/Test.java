package com.magnet.securecollaboration.hostplatformA;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.magnet.securecollaboration.application.*;;


//-- Test class extends the SocketAction class because it has to
//-- establish the connection with remote host and send or receive data to
//-- or from the remote hosts--

public class Test extends SocketAction{
	
	//-- port number to connect with remote host--
	public static final int PORTNUM = 1234;
	
	public static void main (String args[])throws IOException{
		
	    //1.-----------------First take the input from user------------------
		
		//-- Create the master agent object. In the constructor of master agent
		//-- getUserInput is defined
		
		System.out.println("Getting input from the user:");
		
		MasterAgent masterAgent = new MasterAgent();
	   
	    //2.-----------------------Create Teams---------------------------------
		
		System.out.println("Demo of Mobile Agents team creation:");
		
		//-- Team consists of one team lead of type TeamLead, 2 team member of type TeamMember
		//-- and goal of type GoalDC (Goal Data Class)--
		
		masterAgent.createTeams();
		
		System.out.println("Teams created successfully:");
	   
	    //3.---------------------Assign goals-------------------------------------------
		
		for (int i =0; i< masterAgent.maTeams.length; i++)
		{
			//--- set the goal for individual team
			masterAgent.maTeams[i].goal.setFileName(masterAgent.goal.getFileName());
			masterAgent.maTeams[i].goal.setHostName(masterAgent.goal.getHostNames()[i]);	
			
			//---- display team goal
			System.out.println("Team " + i + " File Name: " + masterAgent.goal.getFileName());
			System.out.println("Team " + i + " Host Name: " + masterAgent.maTeams[i].goal.getHostName());
		}
		
		//--- Statically, set the goal for individual team member
		
		masterAgent.maTeams[0].teamMember[0].goal.setHosDrivetName("C");
		masterAgent.maTeams[0].teamMember[1].goal.setHosDrivetName("D");
		masterAgent.maTeams[1].teamMember[0].goal.setHosDrivetName("C");
		masterAgent.maTeams[1].teamMember[1].goal.setHosDrivetName("D");
		
		//---- display individual team member  goal
		
		for (int i =1; i< masterAgent.maTeams.length; i++) {
			for (int j =1; j< masterAgent.maTeams[i].teamMember.length; j++)
				System.out.println("Team " + i + "Team member "+ j + " Drive Name: " + 
						    masterAgent.maTeams[i].teamMember[j].goal.getHosDrivetName());
		}
	   
	    //4.-- Migrate the Agents, Send agents to remote hosts--
		//-- Be assure that connection with the required remote hosts is established.
		
		SocketAction sockAction = null; //-- create a socket action obj.
		for (int i =1; i< masterAgent.maTeams.length; i++) 
		{
			//-- establish the connection--
			//-- call to the constructor of SocketAction
			sockAction = new SocketAction (new Socket(masterAgent.maTeams[i].goal.getHostName(),
									  						   PORTNUM));
			//-- Now, send object to remote host.
			try {
					sockAction.sendObject(masterAgent.maTeams[i]);
		} 
		catch (IOException e) 
			{
			// TODO Auto-generated catch block
			System.out.println("Can't send the object on remote host");
			e.printStackTrace();
			}
		}
	
		System.out.println("Agents successfully migrated to remote host.");
				   
	    //5.-- Continuously listen for the feedback from mobile agents--
		
		//--Authentication Demonstration
		//Authentication auth = new Authentication();
		
		//auth.receiveChallenge(sockAction.receive());
		//System.out.println("Challange received" + sockAction.receive());
	    
	    //6.-- Display the end result to user.
		
		//-- finally close the connection-
		//sockAction.closeConnections();
	    
	}
}
