package com.magnet.securecollaboration.application;

import java.io.Serializable;


//-----------Goal Data Class provides the storage for Goal ontology----------

public class GoalDC implements Serializable
{
	
	//--- private data members related to the Goal ontology ---
	
	// data members related to the overall goal
	
	public String fileName;
	public String[] hostNames;
	
	// data members related to team goal
	
	public String hostName;
	
	// data members related to individual mobile agent goal
	
	public String hostDriveName;

    //--- constructor---
	
	public GoalDC() {}
	
	//---- Following are the get and set methods for the Goal ontology
	
    // ----------------- set methods--------------------------------
	
	/**
	 * @Description Set the file name to search
	 * @param String file name
	 * @return void
	 */
	public void setFileName (String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * @Description Set the list of host names to search the file
	 * @param String array of host names
	 * @return void
	 */
	public void setHostNames (String[] hostNames)
	{
		this.hostNames = hostNames;
	}
	
	/**
	 * @Description Set the single host name to search the file
	 * @param String host name
	 * @return void
	 */
	public void setHostName (String hostName)
	{
		this.hostName = hostName;
	}
	
	/**
	 * @Description Set the target host drive name on which search is performed 
	 * by individual agent
	 * @param String host drive name
	 * @return void
	 */
	public void setHosDrivetName (String hostDriveName)
	{
		this.hostDriveName = hostDriveName;
	}
	
    // ----------------- get methods--------------------------------
	
	/**
	 * @Description Get the file name to search
	 * @param void
	 * @return String file name
	 */
	public String getFileName ()
	{
		return this.fileName;
	}
	
	/**
	 * @Description Get the list of host names on which the search is performed.
	 * @param void
	 * @return String array containg the list of host names.
	 */
	public String[] getHostNames ()
	{
		return this.hostNames;
	}
	
	
	/**
	 * @Description Get the target host name on which search is performed
	 * @param void
	 * @return String the host name
	 */
	public String getHostName ()
	{
		return this.hostName;
	}
	
	/**
	 * @Description Get the required host name drive
	 * @param void
	 * @return String the host drive name
	 */
	public String getHosDrivetName ()
	{
		return this.hostDriveName;
	}
}// End class GoalDC
