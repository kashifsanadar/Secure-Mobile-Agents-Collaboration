package com.magnet.securecollaboration.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Kashif Dar
 * @Description This class represents master agent which takes input (goal, remote host info)
 * from user and creates teams of mobile agents, assign them goal and send them
 * on different remote hosts. Team leads will give the feed back to master agent 
 * and finally master agent will report to user about the search results. 
 */
public class MasterAgent extends Agent {
	
	// member variables
	
	public MATeam[] maTeams; // teams of Mobile Agents
	public GoalDC goal; // overall goal
	
	// constructor
	/**
	 * @Description The default constructor, it calls the super class constructor to
	 * allot the static Agent ID, and then takes the goal as input from user 
	 */
	public  MasterAgent()
	{
		super (13); // set Agent ID
		
		// get user input and assign values to private members
		try {
			getUserInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description Get the required input from user
	 * @return void
	 */
	public void getUserInput() throws IOException
	{
		
		try {
		//--Create an InputStreamReader using the standard input stream--.
	    InputStreamReader isr = new InputStreamReader( System.in );

	    //--Create a BufferedReader using the InputStreamReader created--.
	    BufferedReader stdin = new BufferedReader( isr );

	    //--prompt the user for file name--
	    System.out.print( "Please enter the file name to search: " );

	    //--Use the BufferedReader to read a line of text from the user--.
	    this.goal = new GoalDC(); 
	    this.goal.setFileName(stdin.readLine());
	    
	    //--prompt the user for remote hosts--
	    
	    String[] remoteHosts = new String[2];
	    
	    for (int i =0; i< 2; i++) {
	    System.out.println( "Please enter the IP address for remote host: " + (i+1) );
	    remoteHosts[i]= stdin.readLine();
	    }
	    this.goal.setHostNames(remoteHosts);
	    
	    // --output it to the user--.
	    System.out.println( "File Name is = " + this.goal.getFileName());
	    
	    System.out.println( "IP Addresses for remote host are: ");
	    for (int i =0; i< 2; i++)
	    System.out.println( this.goal.getHostNames()[i]);
	    
	     }
		catch (IOException e)
		{
			System.out.println("Error occured");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description Creates the team of MAs.
	 * @param void
	 * @return void
	 */
	
	public  void createTeams()
	{
		//-- create two teams
		maTeams = new MATeam[2];
		
		// --just instantiate the individual team
		for (int i = 0; i < maTeams.length; i++)
			maTeams[i] = new MATeam();
	}
	
	/**
	 * @Description Report the end result to user.
	 * @param searchResult: The string containing the search result.
	 * @return void
	 */
	public void displayOutput(String searchResult){}
	
	/**
	 * @Description Continuously listen for the feedback from team members 
	 */
	
	public void listenFeedback(){}
}
		

