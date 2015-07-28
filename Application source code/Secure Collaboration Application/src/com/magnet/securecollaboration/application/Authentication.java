package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description Authentication class extends ACLMsgFactory and contains data and methods that 
 * are used to create different ACL messages for mutual authentication between two agents. 
 * This class has authDC data item of type AuthenticationDC which is basically the storage object 
 * for authentication ontology data.
 */
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

import org.sage.core.acl.ACLMessage;
import org.sage.core.acl.ACLMessageInterface;
import org.sage.core.acl.ACLPerformatives;
import org.sage.core.acl.aclcodec.ACLCodec;
import org.sage.core.acl.cf.CFAgentAction;
import org.sage.core.acl.cf.CFContent;
import org.sage.core.acl.cf.CFPrimitive;
import org.sage.core.acl.sl.codec.SLTokenizer;
import org.sage.core.agent.AgentStates;
import org.sage.core.agent.id.AgentID;

import com.magnet.securecollaboration.tests.PKCS_7;

//-- This class contains methods which are used for sender and
//-- receiver authentication.

public class Authentication extends ACLMsgFactory {
	
	//-- private data members--
	public int sNonce;
	public int rNonce;
	private AuthenticationDC authDC;
	private PKCS_7 pkcs7; //-- used to envelop and de-envelop data.
	private String envData; //-- represent PKCS7 enveloped data.
	private String openedData; //-- represent PKCS7 opened data.
	
	/**
	 * @Description: constructor, create ACL message template and instantiate other 
	 * data members 
	 */
	public Authentication() {
		super(); //-- create an ACL message with default parameter values. 
		//-- instantiate pkcs 7 object
		pkcs7 = new PKCS_7();
		
		//-- get an instance of SLTokenizer and set the ontology. 
		slt = SLTokenizer.getInstance();
		slt.setOntology(AuthenticationOntology.getInstance());
		
		//-- set the ontology parameter for ACL msg
		aclMessage.setOntology(AuthenticationOntology.NAME);
		
		//-- instantiate the authDC
		authDC = new AuthenticationDC();
		
		//-- instantiate cfAgentAction with Auth ontology
		cfAgentAction = new CFAgentAction(AuthenticationOntology.NAME);
	}
	
	/**
	 * @Description Generates an ACL message as a challenge, this is enveloped into PKCS 7
	 * receives base 64 encoded string and finally returns its string form 
	 * @param void
	 * @return String The randomly generated challenge to the supplicant. 
	 * @throws PKCS 7 Unsatisfied link error exception
	 */
	public String generateChallenge() {
		
			//-- generate the nonce value--
			this.sNonce = (int) System.currentTimeMillis();
			this.authDC.setSNonce(this.sNonce);
			
			//-- create the content parameter for ACL msg.
            this.cfAgentAction.set(AuthenticationOntology.S_NONCE, 
            		CFPrimitive.getCFPrimitiveFor(authDC.getSNonce()));
            
            //-- Load the content parameter into ACL msg
            aclMessage = super.loadACLContentParameter(aclMessage, slt, cfAgentAction);
		 
		    //-- Now, envelop the ACL msg into PKCS 7.
            envData = pkcs7.envelopPKCS7(aclMessage.toString());
            
            //-- send PKCS 7 enveloped data
            return envData;
	}
	
	/**
	 * @Description  Receive the challenge and sets the value of sNonce
	 * pkcsEnvChallenge is base 64 encoded string
	 * @param pkcsEnvChallenge; The PKCS 7 enveloped challenge value
	 * @return void
	 */
	public void receiveChallenge(String pkcsEnvChallenge) {
		
		//--Receive PKCS 7 enveloped challenge and
		//--Open PKCS 7 data
		openedData = pkcs7.deEnvelopPKCS7(pkcsEnvChallenge);
		
		//--Check the confidentiality and integrity of the data received
		
		//--Decode ACL msg
		this.decodeAuthMsg(openedData);
		
		//--Pick up and sets the sender nonce value sent by authenticator
		 
		 this.sNonce =  this.cfAgentAction.getInteger(AuthenticationOntology.S_NONCE);
		 }

	/**
	 * @Description  Generates an ACL message as a response, this is enveloped into PKCS 7
	 * and finally returns its string form
	 * @param void
	 * @return String; The randomly generated response to the challenge.
	 */
	public String generateResponse() {
	
	//--generate the nonce value, challenge to authenticator
	
	this.rNonce = (int) System.currentTimeMillis();
	
	//-- sender nonce is
	this.sNonce = sNonce;
	
	//-- Generate an ACL msg containg both nonce values, received and newly generated.
	this.authDC.setRNonce(this.rNonce);
	this.authDC.setSNonce(this.sNonce);
	
	//-- create the content parameter for ACL msg.
    this.cfAgentAction.set(AuthenticationOntology.S_NONCE, 
    		CFPrimitive.getCFPrimitiveFor(authDC.getSNonce()));
    this.cfAgentAction.set(AuthenticationOntology.R_NONCE, 
    		CFPrimitive.getCFPrimitiveFor(authDC.getRNonce()));
    
    //-- Load the content parameter into ACL msg
    aclMessage = super.loadACLContentParameter(aclMessage, slt, cfAgentAction);
	
	//--Envelop ACL msg into PKCS 7
    envData = pkcs7.envelopPKCS7(aclMessage.toString());
	
    //-- Send the response
    return envData;
}

/**
 * @Description  Receive response from the supplicant.
 * @param pkcsEnvResponse, PKCS 7 enveloped response
 * @return void
 */	
public void receiveResponse(String pkcsEnvResponse) {
	
	//--Receive PKCS 7 enveloped response
	//--Open PKCS 7 data
	openedData = pkcs7.deEnvelopPKCS7(pkcsEnvResponse);
	//--Check the confidentiality and integrity of the data received
	
	//--Decode ACL msg
	this.decodeAuthMsg(openedData);
	
	
	//--Pick up nonce value sent by authenticator and verify the nonce sent by it. 
	 
	 if (this.sNonce !=  this.cfAgentAction.getInteger(AuthenticationOntology.S_NONCE)){
		 
		 System.out.println("Nonce value received doesnt match with the nonce sent..");
		 System.exit(1);
	 }
	
	//-- otherwise, set the nonce value received by the other party.
	this.rNonce = this.cfAgentAction.getInteger(AuthenticationOntology.R_NONCE);
}

/**
 * @Description Generate the acknowledgment to the response from supplicant
 * @param void 
 * @return PKCS 7 enveloped response acknowledgment
 */	
public String generateResponseAcknowledgement() {
	
	//-- Create an ACL msg containing the nonce value received by other party
	
	this.authDC.setRNonce(this.rNonce);
	this.authDC.setSNonce(0);	//-- will indicate that receiver has opened and properly 
	//--varified the nonce value and that the message has not been replayed.
	
	//-- create the content parameter for ACL msg.
    this.cfAgentAction.set(AuthenticationOntology.S_NONCE, 
    		CFPrimitive.getCFPrimitiveFor(authDC.getSNonce()));
    this.cfAgentAction.set(AuthenticationOntology.R_NONCE, 
    		CFPrimitive.getCFPrimitiveFor(authDC.getRNonce()));
    
    //-- load ACL content parameter
    aclMessage = super.loadACLContentParameter(aclMessage, slt, cfAgentAction);
    
	
	//--Envelop ACL msg into PKCS 7
    envData = pkcs7.envelopPKCS7(aclMessage.toString());
	//--Send PKCS 7 enveloped data
    return envData;
}

	
/**
 * @Description Receive the response ack sent by sender
 * @param pkcsEnvResponse, PKCS 7 enveloped response
 * @return True/False on the basis of authentication result
 */
public boolean receiveResponseAcknowledgement(String pkcsEnvResponse) {
	
	//-- receive PKCS 7 enveloped response acknowledgment
	//--open the PKCS 7 data
	openedData = pkcs7.deEnvelopPKCS7(pkcsEnvResponse);
	
	//-- Decode ACL Message
	this.decodeAuthMsg(openedData);
	
	//-- verify the nonce received
	//--Pick up nonce value sent by authenticator and verify the nonce sent by it. 
	 
	 if (this.rNonce !=  this.cfAgentAction.getInteger(AuthenticationOntology.R_NONCE)){
		 
		 System.out.println("Nonce value received doesnt match with the nonce sent..");
		 return false;
	 }
	 
	 return true;
}


/**
 * @Description Decode any ACL message related to mutual authentication.
 * @param aclMessage, Required ACL authentication message to decode.
 * @return void 
 */
private void decodeAuthMsg(String aclMessage) {
	
	 // ----------------Decode the ACL message----------------------
	 this.aclMessage = ACLCodec.decode(aclMessage);        
     // -----Decode the content parameter of ACL message ----------

    // ----Get an instance of SLTokenizer that will decode the content parameter of the ACL message---
    
    this.slt = SLTokenizer.getInstance();
    
    try {
		this.aclCFContent = slt.decode(this.aclMessage.getContent(),
										  AuthenticationOntology.getInstance());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	this.cfAgentAction = (CFAgentAction)aclCFContent.getObject();
}


//	public static void main (String args[]){
//		//-- Auth Test
//		Authentication auth = new Authentication();
//		
//		String strEncChallenge = auth.generateChallenge();
//		System.out.println("Enc data is " + strEncChallenge);
//	}
}
