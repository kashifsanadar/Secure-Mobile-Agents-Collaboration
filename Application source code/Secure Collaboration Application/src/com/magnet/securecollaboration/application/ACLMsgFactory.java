package com.magnet.securecollaboration.application;

/** 
 * @author Kashif Dar
 * @Description This class contains methods to create simple ACL message template, assign default
 * values, and create the ACL content parameter for any ACL message in our domain.
 */
//-- imports
import java.util.Date;

import org.sage.core.acl.ACLMessage;
import org.sage.core.acl.ACLMessageInterface;
import org.sage.core.acl.ACLPerformatives;
import org.sage.core.acl.cf.CFAgentAction;
import org.sage.core.acl.cf.CFContent;
import org.sage.core.acl.sl.codec.SLTokenizer;
import org.sage.core.agent.AgentStates;
import org.sage.core.agent.id.AgentID;

public class ACLMsgFactory {

	//-- store ACL msg	
	public ACLMessageInterface aclMessage = null;
	
	//-- SLTokenizer used to encode and decode ACL msg content parameter
	public SLTokenizer slt = null; 
	
	//-- used to create ACL content par
	public CFAgentAction cfAgentAction = null;  
	
	//-- ACL content in the form of string
	public String aclContent = null; 
	
	//-- ACL content in the form of CFContent
	public CFContent aclCFContent = null; 
	
	/**
	 * @author Kashif Dar
	 * @Description Default Constructor, Simply initialize the ACL message object with its default
	 * parameters values.
	 **/
	public ACLMsgFactory() {
		
		//-------------------create ACL Message--------------------
		
		// --- Create ACL Message Template with default header info--------- 
		this.aclMessage = createACLMsgWithDefaultParameters(); 
	}
	
	//-- create ACL msg template with default parameters values	
	/**
	 * @Description Cretaes ACL message template with default values.
	 * @param void
	 * @return ACLMessageInterface, An ACL message with default parameters values.
	 */
	private ACLMessageInterface createACLMsgWithDefaultParameters() {
		
		//-- create a template for ACL Message--
		
		ACLMessageInterface aclMessage = ACLMessage.createACLMessage(ACLPerformatives.REQUEST);

		// --- Load header data into ACL Message ----------------------
		
		aclMessage.addReceiver(AgentID.createAgentID("receiver@seclab.dsv.su.se", 
								     null, null, null,AgentStates.ACTIVE));
				        
	    aclMessage.setSender(AgentID.createAgentID("sender@seclab.dsv.su.se", 
	    							null, null, null, AgentStates.ACTIVE));
	   
	   aclMessage.setPerformative("Authentication");
	   aclMessage.setLanguage("fipa-sl0");
	   
       aclMessage.setEncoding("ISO-8859-1");
       aclMessage.setProtocol("request");
       aclMessage.setReplyWith(null);
       aclMessage.setInReplyTo(null);
       aclMessage.setConversationId("1abc");
       Date dt = new Date();
       aclMessage.setReplyBy(dt);
       aclMessage.addUserDefinedParameter("x", "y");
       
       return aclMessage;
	}
	
	/**
	 * @Description Loads the ACL content parameter.
	 * @param aclMessage: The ACL message for which content parameter is to be loaded
	 * @param slt: is used to encode the ACL contents
	 * @param cfAgentAction: contains the content parameter associated with particular ontology.
	 * @return The ACL message filled with content parameter.
	 *
	 */
	//-- load the current ACL content parameter
	public ACLMessageInterface loadACLContentParameter(ACLMessageInterface aclMessage,
														SLTokenizer slt,
														CFAgentAction cfAgentAction) {

	    //-- create content parameter for ACL msg
		try {
			aclContent = slt.encode(cfAgentAction);
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// --- Set the content parameter for ACL message------------
	        try {
	            	aclMessage.setContent(aclContent);
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    return aclMessage;    
	}
	
}
