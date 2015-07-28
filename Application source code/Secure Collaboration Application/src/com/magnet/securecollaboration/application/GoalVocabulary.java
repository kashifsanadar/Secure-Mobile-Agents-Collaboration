package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This interface contains data elements needed for goal ontology
 */
public interface GoalVocabulary {
	  
	  public static final String NAME = "Secure-Collaboration";
	
	// --parameters related to overall goal
	
	  public static final String FILE_NAME = "File-Name";// file name to search
	  public static final String[] HOST_NAMES = {"List-of-IPs-of-different-hosts"};
	  
	  // --parameters related to team goal, will extend overall goal
	  
	  public static final String HOST_NAME = "Host-name-to-perform-search";
	  
	  // --parameters related to individual team member goal, will extend team goal
	  
	  public static final String HOST_DRIVE_NAME = "Host-drive-name";
	  	  
	}
