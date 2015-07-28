package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This class is used to get the instance of AuthenticationOntology class with the
 * help of getInstance() method and contains the default Constructor, that is used to specify the 
 * data types for the elements involved in Authentication Ontology.  
 */
//-- imports--
import org.sage.core.acl.ontology.BasicOntology;
import org.sage.core.acl.ontology.Ontology;
import org.sage.core.acl.schema.AgentActionSchema;
import org.sage.core.acl.schema.PrimitiveSchema;

public class AuthenticationOntology extends Ontology implements AuthenticationVocabulary {
	
	  private static Ontology theInstance = new AuthenticationOntology();
	  
	 /**
	  * @Description Gets the instance of the AuthenticationOntology
	  * @return Instance of AuthenticationOntology
	  */
	  public static Ontology getInstance()
	  {
	    return theInstance;
	  }

	  /**
	   * @Description The default Constructor, Get the instance of Basic ontology
	   * and adds up the Authentication Ontology elements. 
	   */
	  public AuthenticationOntology()
	  {
	     super(NAME, BasicOntology.getInstance());
	     
	     try
	     {
	       AgentActionSchema cs = new AgentActionSchema(NAME);
	       cs.add(S_NONCE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
	       cs.add(R_NONCE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
	       
	       this.add(cs);
	     }
	     catch(Exception exp)
	     {
	       exp.printStackTrace();

	     }
	  }
}
