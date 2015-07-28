package com.magnet.securecollaboration.tests;


import setecs.OneSEC.platform.ASN1Encoding;
import setecs.OneSEC.platform.DistinguishedName;
import setecs.OneSEC.platform.Hash;
import setecs.OneSEC.platform.PKCS7;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @Description This class contains the functions to envelopand deenvelop the ACL messages
 * into PKCS 7. 
 * @author Kashif
 *
 */
public class PKCS_7 extends PKCS7{
	
		//-- data members
	
		public  DistinguishedName subject1 = new DistinguishedName("US", // countryName
				"D.C.", // stateOrProvinceName
				"Washington", // locality
				"GWU", // organizationName
				"SEAS", // organizationalUnitName
				"Admin1", // commonName
				"", // emailAddress
				""); // URL (for Servers)
		public  DistinguishedName subject2 = new DistinguishedName("US", // countryName
				"D.C.", // stateOrProvinceName
				"Washington", // locality
				"GWU", // organizationName
				"SEAS", // organizationalUnitName
				"Admin2", // commonName
				"", // emailAddress
				""); // URL (for Servers)

		public  DistinguishedName issuer = new DistinguishedName("US", // countryName
				"D.C.", // stateOrProvinceName
				"Washington", // locality
				"GWU", // organizationName
				"SEAS", // organizationalUnitName
				"OneNETServer35", // commonName
				"", // emailAddress
				"128.164.82.35"); // URL (for Servers
		
		public  String workingDir = "D:/Certificate_Rep";
				
		public  DistinguishedName[] recipients = {subject1, subject2};
		
			
		/**
		 * @Description  This method envelops ACL message and creates a digital envelop that is secure
		 *  w.r.t message confidentiality, integrity, and origin integrity.
		 *  creates signed and enveloped data with encrypted data contents, encrypted digital signature 
		 *  and encrypted digested value
		 *  @param aclMessage The ACL message into plain text.
		 *  @return Returns enveloped base 64 encoded string. 
		 */	
		
		public  String envelopPKCS7(String aclMessage)
			{
			
			// --- Function 60 : create PKCS#7 SignedAndEnvelopedData -----
			
			byte[] data = aclMessage.getBytes();
			byte[] signedEnvelopedData = super.createSignedEnvelopedData(workingDir,
																		 data,
																		 issuer,
																		 "password",
																		 recipients);//-- only single recipient

			if (signedEnvelopedData.length == 0) {
				System.out.println("SignedAndEnvelopedData is NULL !");
				return null;
				}
			else {
				//-- Convert signedEnvelopedData into base 64 string. 
				BASE64Encoder bASE64Encoder = new BASE64Encoder();
				String encoded64EnvelopedData = bASE64Encoder.encode(signedEnvelopedData); 
				
				return  encoded64EnvelopedData;
			}
			}
			
		/**
		 * @Description  This method open the ACLmessage which is enveloped into PKCS 7
		 * receives base 64 encoded string
		 * @return Returns an ASCII string containing original data.  
		 */	
		public  String deEnvelopPKCS7(String signedEnvelopedData)
			{
			
			// --- Function 59 : open PKCS#7 SignedAndEnvelopedData -----
			// --- Open EnvelopedData -----------------------------------
			
			//-- convert base 64 string into byte array.
			BASE64Decoder bASE64Decoder = new BASE64Decoder();
			byte[] openSandEData = {0};
			
			try  {
					openSandEData = bASE64Decoder.decodeBuffer(signedEnvelopedData);
				 }
			
			catch (Exception exp){}
			
			openSandEData = super.openSignedEnvelopedData(workingDir,
																openSandEData,
																 recipients[0],
																 "password");
			
			return (new String (openSandEData));
			}
			
	}
