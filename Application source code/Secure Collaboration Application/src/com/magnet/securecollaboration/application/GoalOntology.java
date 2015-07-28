package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This class is used to get the instance of GoalOntology class with the
 * help of getInstance() method and contains the default Constructor, that is used to specify the 
 * data types for the elements involved in goal Ontology.  
 */
import org.sage.core.acl.ontology.BasicOntology;
import org.sage.core.acl.ontology.Ontology;
import org.sage.core.acl.schema.AgentActionSchema;
import org.sage.core.acl.schema.AggregateSchema;
import org.sage.core.acl.schema.ConceptSchema;
import org.sage.core.acl.schema.PredicateSchema;
import org.sage.core.acl.schema.PrimitiveSchema;

public class GoalOntology extends Ontology implements GoalVocabulary {
	
	private static Ontology theInstance = new GoalOntology();
	  
	/**
	  * @Description Gets the instance of the GoalOntology
	  * @return Instance of GoalOntology
	  */
	public static Ontology getInstance()
	  {
	    return theInstance;
	  }

	/**
	   * @Description The default Constructor, Get the instance of Basic ontology
	   * , defines and adds up the Goal Ontology elements. 
	   */ 
	public GoalOntology()
	  {
	     super(NAME, BasicOntology.getInstance());
	     try
	     {
	       
	    	//--- Set the ontology name------
	       AgentActionSchema cs = new AgentActionSchema(NAME);
	       
	       //--- parameters for overall Goal------
	       cs.add(FILE_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
	       
	       //--- parameter(s) for team Goal------
	       cs.add(HOST_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
	       
	       //--- parameter(s) for individual Goal------
	       cs.add(HOST_DRIVE_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));

	       this.add(cs);
	     }
	     catch(Exception exp)
	     {
	       exp.printStackTrace();

	     }
	  }
}
