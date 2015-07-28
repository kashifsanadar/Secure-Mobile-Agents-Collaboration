package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This class extends the ACLMEssageFactory class and is used to generate
 * different types of mesages related to the search operation e.g., 
 * search success and search failure etc.
 */
import org.sage.core.acl.aclcodec.ACLCodec;
import org.sage.core.acl.cf.CFAgentAction;
import org.sage.core.acl.cf.CFPrimitive;
import org.sage.core.acl.sl.codec.SLTokenizer;
import com.magnet.securecollaboration.tests.PKCS_7;

public class SearchResultACLMessage extends ACLMsgFactory{
	
	//-- private data members--
	public boolean searchResult; // represents search success or failure
	public String hostName;// remote host name on which search is being performed.
	private SearchDC searchDC;
	private PKCS_7 pkcs7;
	String envData; //-- contains the pkcs7 enveloped data
	String openedData;//-- contains de-enveloped data
		
	/** 
	 * @Description Constructor, create ACL message template and instantiate other data members
	 * 
	 */
	
	public SearchResultACLMessage() {
	
		super(); //-- create an ACL message with default parameter values. 
		//-- Instantiate PKCS_7 object
		pkcs7 = new PKCS_7();
		//-- initialize enveloped and de-enveloped data.
		envData = "";  openedData = "";
		//-- get an instance of SLTokenizer and set the ontology. 
		slt = SLTokenizer.getInstance();
		slt.setOntology(SearchOntology.getInstance());
				
		//-- set the ontology parameter for ACL msg
		aclMessage.setOntology(SearchOntology.NAME);
				
		//-- instantiate the searchDC
		searchDC = new SearchDC();
				
		//-- instantiate cfAgentAction with Search ontology
		cfAgentAction = new CFAgentAction(SearchOntology.NAME);
	
	}
	
	/**
	 * @Description Generate ACL Search successful message
	 * @param filContents: The found file contents
	 * @param remoteHostAddress: The target remote host address
	 * @return Return the PKCS7 enveloped ACL message  
	 */
	private String generateSuccessMsg(String fileContents, String remoteHostAddress){
		
		//-- Set the search result
		this.searchDC.setSearchResult(true);
		this.searchDC.setFileContents(fileContents);
		this.searchDC.setRemoteHostAddress(remoteHostAddress);
		
		//-- create the content parameter for ACL msg.
	    this.cfAgentAction.set(SearchOntology.SEARCH_RESULT, 
	    		CFPrimitive.getCFPrimitiveFor(searchDC.getSearchResult()));
	    this.cfAgentAction.set(SearchOntology.FILE_CONTENTS, 
	    		CFPrimitive.getCFPrimitiveFor(searchDC.getFileContents()));
	    this.cfAgentAction.set(SearchOntology.REMOTE_HOST_ADDRESS, 
	    		CFPrimitive.getCFPrimitiveFor(searchDC.getRemoteHostAddress()));
	    
	    //-- load ACL content parameter
	    aclMessage = super.loadACLContentParameter(aclMessage, slt, cfAgentAction);
	    
		//--Envelop ACL msg into PKCS 7
	    //envData = pkcs7.envelopPKCS7(aclMessage.toString());
		//--Send PKCS 7 enveloped data
	    return aclMessage.toString();
			
	}
	
	/**
	 * @Description Generate ACL search failure  message
	 * @param description: The search operation failure description
	 * @return Return PKCS7 enveloped ACL message  
	 */
	private String generateFailureMsg(String description){
		
		//-- Set the search result
		this.searchDC.setSearchResult(false);
		this.searchDC.setFailureDescription(description);
		
		//-- create the content parameter for ACL msg.
	    this.cfAgentAction.set(SearchOntology.SEARCH_RESULT, 
	    		CFPrimitive.getCFPrimitiveFor(searchDC.getSearchResult()));
	    this.cfAgentAction.set(SearchOntology.DESCRIPTION, 
	    		CFPrimitive.getCFPrimitiveFor(searchDC.getFailureDescription()));
	    //-- load ACL content parameter
	    aclMessage = super.loadACLContentParameter(aclMessage, slt, cfAgentAction);
	    //--Envelop ACL msg into PKCS 7
	    envData = pkcs7.envelopPKCS7(aclMessage.toString());
		//--Send PKCS 7 enveloped data
	    return envData;
	}
	
	/**
	 * @Description This method just opens the PKCS 7 enveloped success/failure message.
	 * @param pkcsEnvSearchResultMsg: The PKCS 7 enveloped ACL search result
	 * @return void  
	 */
	private void openSearchResultMessage(String pkcsEnvSearchResultMsg){
		//--Open PKCS 7 data
		openedData = pkcs7.deEnvelopPKCS7(pkcsEnvSearchResultMsg);
		//--Decode ACL msg
		this.decodeSearchResultMsg(openedData);		
		//--Pick up and set the value of search result variable.
		searchResult =  cfAgentAction.getBoolean(SearchOntology.SEARCH_RESULT);
	}
	
	/**
	 * @Description Decode any ACL message related to search result.
	 * @param aclMessage: ACL message to decode
	 * @return void
	 */
	private void decodeSearchResultMsg(String aclMessage) {
		// ----------------Decode the ACL message----------------------
		this.aclMessage = ACLCodec.decode(aclMessage);        
		// -----Decode the content parameter of ACL message ----------
	
		// --- Get an instance of SLTokenizer that will decode the content parameter
		//--- of the ACL message---
		    
		this.slt = SLTokenizer.getInstance();
		    
		  try {
			this.aclCFContent = slt.decode(this.aclMessage.getContent(),
												  SearchOntology.getInstance());
			} 
		  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		this.cfAgentAction = (CFAgentAction)aclCFContent.getObject();
	}

	//-- Some code to test--
	
	public static void main(String args[]){
			
		//-- Search Test Cases
		SearchResultACLMessage searchRes = new SearchResultACLMessage();
		
		//-- Success test
		String fileContents = "KashifDar";
		String remHost = "139.237.238.162";
		String str = searchRes.generateSuccessMsg(fileContents, remHost);
		System.out.println(str);
		
		//-- Failure test
		//str = searchRes.generateFailureMsg("Unknown reason");
		//System.out.println(str);
				
	}
	}
