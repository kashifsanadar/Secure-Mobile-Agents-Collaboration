package com.magnet.securecollaboration.application;

import java.io.Serializable;

public class MATeam extends Agent
{
	
	public TeamLead teamLead;
	public TeamMember[] teamMember;
	public  GoalDC goal; 
	
	/**
	 * @Description The default constrictor that creates and instantiate the
	 * team leader. team members, and the goal instances.
	 */
	public MATeam() {
		
		// -- create and instantiate team leader
		this.teamLead = new TeamLead();
		
		// -- create and instantiate each team member
		this.teamMember = new TeamMember[2];
		for (int i = 0; i < teamMember.length; i++)
			teamMember[i] = new TeamMember();
		
		// -- create team goal
		this.goal = new GoalDC();
		
	}
}
