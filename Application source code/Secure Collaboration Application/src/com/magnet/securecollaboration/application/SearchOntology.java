package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This class is used to get the instance of SearchOntology class with the
 * help of getInstance() method and contains the default Constructor, that is used to specify the 
 * data types for the elements involved in Search Ontology.  
 */

import org.sage.core.acl.ontology.BasicOntology;
import org.sage.core.acl.ontology.Ontology;
import org.sage.core.acl.schema.AgentActionSchema;
import org.sage.core.acl.schema.PrimitiveSchema;

public class SearchOntology extends Ontology implements SearchVocabulary {
	
		private static Ontology theInstance = new SearchOntology();
		  
		/**
		  * @Description Gets the instance of the SearchOntology
		  * @return Instance of SearchOntology
		  */  
		public static Ontology getInstance()
		  {
		    return theInstance;
		  }

		/**
		   * @Description The default Constructor, Get the instance of Basic ontology
		   * , defines and adds up the Search Ontology data elements. 
		   */  
		public SearchOntology()
		  {
		     super(NAME, BasicOntology.getInstance());
		     
		     try
		     {
		       AgentActionSchema cs = new AgentActionSchema(NAME);
		       
		       cs.add(SEARCH_RESULT, (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN));
		       
		       cs.add(REMOTE_HOST_ADDRESS, (PrimitiveSchema) getSchema(BasicOntology.STRING));
		       cs.add(FILE_CONTENTS, (PrimitiveSchema) getSchema(BasicOntology.STRING));
		       
		       cs.add(DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING));
		       
		       this.add(cs);
		     }
		     catch(Exception exp)
		     {
		       exp.printStackTrace();

		     }
		  }
	}
