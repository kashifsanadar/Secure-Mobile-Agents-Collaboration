package com.magnet.securecollaboration.application;

public class Test {
	
	public static void main (String[] args)
	{

		//---- create a master static agent--------
		MasterAgent masterAgent = new MasterAgent();
		
		//---- create teams --------- 
		System.out.println("Demo of MA team creation:");
			
		masterAgent.createTeams();
		
		System.out.println("Teams created successfully:");
		
		// ------ Assign goal to individual teams and team members------
			
		for (int i =0; i< masterAgent.maTeams.length; i++)
		{
			//--- set the goal for individual team
			masterAgent.maTeams[i].goal.setFileName(masterAgent.goal.getFileName());
			masterAgent.maTeams[i].goal.setHostName(masterAgent.goal.getHostNames()[i]);	
			
			//---- display team goal
			System.out.println("Team " + i + " File Name: " + masterAgent.goal.getFileName());
			System.out.println("Team " + i + " Host Name: " + masterAgent.maTeams[i].goal.getHostName());

			//--- set the goal for individual team member
			masterAgent.maTeams[i].teamMember[i].goal.setHosDrivetName("A");
			
			//---- display individual team member  goal
			System.out.println("Team " + i + "Team member "+ i+ " Drive Name: " + 
							    masterAgent.maTeams[i].teamMember[i].goal.getHosDrivetName());
		}
	}
}