package com.magnet.securecollaboration.application;

/**
 * @author Kashif Dar
 * @Description This class is used to store the data for Search ontology. It contains
 * the set and get methods to set and receive the values of Search ontotology data items.
 */
public class SearchDC {
	
		//--- private data members related to Search Operation ---
						
			private boolean searchResult;
			
			//-- Search Success--
			private String fileContents;
			private String remoteHostAddress;
			
			//-- Search Failure--
			private String description;
			
		    //--- constructor---
			
			public SearchDC() {}
			
			//---- Following are the get and set methods for the Goal ontology
			
		    // ----------------- set methods--------------------------------
			
			/**
			 * @Description  Set the search result to true (success) or false (failure)
			 * @param Boolen: The serach result
			 * @return void 
			 */
			public void setSearchResult (boolean searchResult)
			{
				this.searchResult = searchResult;
			}
			
			/**
			 * @Description  Set the contents of the file that has been found
			 * @param String: The file contents
			 * @return void 
			 */
			public void setFileContents (String fileContents)
			{
				this.fileContents = fileContents;
			}
			
			/**
			 * @Description  Set the address of the remote host on which the file is found.
			 * @param String: Remote host address
			 * @return void 
			 */
			public void setRemoteHostAddress (String remoteHostAddress)
			{
				this.remoteHostAddress = remoteHostAddress;
			}
			
			/**
			 * @Description  Set  any description in case of the search operation failure
			 * @param String: failure description
			 * @return void 
			 */
			public void setFailureDescription (String description)
			{
				this.description = description;
			}
		    // ----------------- get methods--------------------------------
			
			/**
			 * @Description  Get the search result to true (success) or false (failure)
			 * @param void
			 * @return Boolean: Search result
			 */
			public boolean getSearchResult ()
			{
				return this.searchResult;
			}
			
			/**
			 * @Description  Get the contents of the file that is found during the search operation
			 * @param void
			 * @return String: file contents
			 */
			public String getFileContents ()
			{
				return this.fileContents;
			}
			
			/**
			 * @Description  Get the address of the remote host
			 * @param void
			 * @return String: remote host address 
			 */
			public String getRemoteHostAddress ()
			{
				return this.remoteHostAddress;
			}
			
			/**
			 * @Description  Get the failure description in case the search operation is failed
			 * @param void
			 * @return String: failure description
			 */
			public String getFailureDescription ()
			{
				return this.description;
			}
		}
