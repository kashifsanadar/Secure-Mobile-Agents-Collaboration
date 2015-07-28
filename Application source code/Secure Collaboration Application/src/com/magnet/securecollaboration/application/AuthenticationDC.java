package com.magnet.securecollaboration.application;


/**
 * @author Kashif Dar
 * @Description This class is used to store the data for authentication ontology. It contains
 * the set and get methods to set and receive the values of authentication ontotology data items.
 */
public class AuthenticationDC {
	
//--- private data members related to Authentication ---
	
	// data members related to the overall goal
	
	private int sNonce;
	private int rNonce;
	
    //--- constructor---
	
	public AuthenticationDC() {}
	
	//---- Following are the get and set methods for the Goal ontology
	
    // ----------------- set methods--------------------------------
	
	/**
	 * @Description Set the value of sender nonce
	 * @param sNonce: The sender nonce value
	 * @return void
	 */
	public void setSNonce (int sNonce)
	{
		this.sNonce = sNonce;
	}
	
	/**
	 * @Description Set the value of receiver nonce
	 * @param rNonce: The receiver nonce value
	 * @return void
	 */
	public void setRNonce (int rNonce)
	{
		this.rNonce = rNonce;
	}
    // ----------------- get methods--------------------------------
	
	/**
	 * @Description Get the value of sender nonce
	 * @param void
	 * @return integer: nonce value
	 */
	public int getSNonce ()
	{
		return this.sNonce;
	}
	
	/**
	 * @Description Get the value of receiver nonce
	 * @param rNonce: The receiver nonce value
	 * @return integer: nonce value
	 */
	public int getRNonce ()
	{
		return this.rNonce;
	}
}
