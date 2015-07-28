package com.magnet.securecollaboration.tests;

//----------------------------------------------------------------------------------
//SecPlatformDemo -- Test/demo of various Sec Platform functions 
//----------------------------------------------------------------------------------
//
//(1) Hash Functions
//
// Function 01 : compute hash on strings 
// Function 02 : verify hash on strings
// Function 03 : compute hash on binary data 
// Function 04 : verify hash on binary data
//
//
//(2) Symmetric Keys Functions
//
// Function 05 : generate default symmetric key (64 bits, DES-CBC) 
// Function 06 : generate customized symmetric key 
// Function 07 : encrypt with symmetric key 
// Function 08 : decrypt with symmetric key 
//
//
//(3) Asymmetric Keys Functions
//
// Function 09 : generate 1024 bits default pair of Asymmetric Keys (RSA algorithm) 
// Function 10 : extract RSA Public Key from a pair of Asymmetric Keys 
// Function 11 : extract RSA Private Key from a pair of Asymmetric Keys 
// Function 12 : extract components of the RSA Public Key  
// Function 13 : extract CRT components of the RSA Private Key 
// Function 14 : encrypt with RSA Public Key 
// Function 15 : decrypt with RSA Private Key 
// Function 16 : protect (encrypt) RSA Private Key with password 
// Function 17 : un-protect (decrypt) RSA Private Key with password
// Function 18 : generate 1024 bits pair of Asymmetric Keys (DSA algorithm)  
// Function 19 : encrypt with DSA Public Key 
// Function 20 : decrypt with DSA Private Key 
// Function 21 : protect DSA Private Key with password 
// Function 22 : un-protect (decrypt) DSA Private Key with password 
// Function 23 : serialization of the Symmetric Key  
// Function 24 : enveloping of the Symmetric Key  
// Function 25 : opening enveloped Symmetric Key  
// Function 26 : creation of the original Symmetric Key object using serialized Symmetric Key  
//
//
//(4) DistinguishedName Functions
//
// Function 27 : creation of the Distinguished Name 
// Function 28 : extract Distinguished Name parameters 
//
//
//(5) Certificates Functions
//
// Function 29 : create self-signed (root) Certificate
// Function 30 : create certificate request (PKCS#10) 
// Function 31 : create X.509 certificate out of the certificate request  
// Function 32 : receive certificate reply (PKCS#7)  
// Function 33 : request certificate from the Certificates DB 
// Function 34 : get the first certificate from the Certificates DB
// Function 35 : get next certificate from the Certificates DB      
// Function 36 : list all X.509 certificate attributes       
// Function 37 : export certificate into PKCS#12 format      
// Function 38 : import certificate from PKCS#12 format       
// Function 39 : export certificate into PEM format      
// Function 40 : import certificate from PEM format      
// Function 41 : export certificate into PKCS#7 (SignedData) format      
// Function 42 : import certificate from PKCS#7 (SignedData) format      
// Function 43 : export certificate into DER format      
// Function 44 : import certificate from DER format      
// Function 45 : verify certificate      
// Function 46 : save certificate in the Certificates DB      
// Function 47 : revoke certificate in the Certificates DB      
// Function 48 : list all certificates in the Certificates DB      
// Function 49 : extend the lifetime of the certificate       
// Function 50 : delete certificate       
//
//
//(6) Certificate Chain Functions
//
// Function 51 : create X.509 certificates chain 
// Function 52 : verify X.509 certificates chain 
//
//
//(7) PKCS7 Functions
//
// Function 53 : create PKCS#7 SignedData 
// Function 54 : add additional Signer to PKCS#7 SignedData 
// Function 55 : verify PKCS#7 SignedData 
// Function 56 : extract data from PKCS#7 SignedData 
// Function 57 : get all Signers of PKCS#7 SignedData 
// Function 58 : create PKCS#7 EnvelopedData 
// Function 59 : open PKCS#7 EnvelopedData 
// Function 60 : create PKCS#7 SignedAndEnvelopedData 
// Function 61 : create PKCS#7 EncryptedData 
// Function 62 : verify PKCS#7 EncryptedData 
//
//
//(8) SMIME Functions
//
// Function 63 : create SMIME SignedData 
// Function 64 : verify SMIME SignedData 
// Function 65 : create SMIME EnvelopedData 
// Function 66 : open SMIME EnvelopedData 
// Function 67 : get SMIME Message Type 
//
//
//(9) CRL Functions
//
// Function 68 : create CRL 
// Function 69 : read CRL 
// Function 70 : verify CRL  
// Function 71 : save CRL  
//
//----------------------------------------------------------------------------------

import java.io.DataInputStream;
import java.io.File;
import java.math.BigInteger;
import java.util.Date;

import setecs.OneSEC.platform.ASN1Encoding;
import setecs.OneSEC.platform.AsymmetricKeys;
import setecs.OneSEC.platform.Certificate;
import setecs.OneSEC.platform.DistinguishedName;
import setecs.OneSEC.platform.Hash;
import setecs.OneSEC.platform.PKCS7;
import setecs.OneSEC.platform.PrivateKey;
import setecs.OneSEC.platform.PublicKey;
import setecs.OneSEC.platform.SMIME;
import setecs.OneSEC.platform.Stx;
import setecs.OneSEC.platform.SymmetricKey;

public class SecPlatoformDemo {

	static int usage = 0;

	public static DistinguishedName subject1 = new DistinguishedName("US", // countryName
			"D.C.", // stateOrProvinceName
			"Washington", // locality
			"GWU", // organizationName
			"SEAS", // organizationalUnitName
			"Admin1", // commonName
			"", // emailAddress
			""); // URL (for Servers)

	public static DistinguishedName subject2 = new DistinguishedName("US", // countryName
			"D.C.", // stateOrProvinceName
			"Washington", // locality
			"GWU", // organizationName
			"SEAS", // organizationalUnitName
			"Admin2", // commonName
			"", // emailAddress
			""); // URL (for Servers)

	public static DistinguishedName subject3 = new DistinguishedName("US", // countryName
			"D.C.", // stateOrProvinceName
			"Washington", // locality
			"GWU", // organizationName
			"SEAS", // organizationalUnitName
			"Admin3", // commonName
			"", // emailAddress
			""); // URL (for Servers)

	public static DistinguishedName issuer = new DistinguishedName("US", // countryName
			"D.C.", // stateOrProvinceName
			"Washington", // locality
			"GWU", // organizationName
			"SEAS", // organizationalUnitName
			"OneNETServer35", // commonName
			"", // emailAddress
			"128.164.82.35"); // URL (for Servers
	public DistinguishedName[] recipients = {subject1, subject2};

	public static ASN1Encoding requested_ASN1Certificate = new ASN1Encoding();

	// static FileUtilities FU = new FileUtilities();

	public static String workingDir = "G:/workspace_kashif/Certificate_Rep";
	

	public SecPlatoformDemo() {
		System.out.println(" ");
		System.out
				.println(" --- [ Start of the Test/Demo ] ----------------------------------- ");
		System.out.println(" ");

		PKCS7Test("Kashif Sana Dar");
	}


	// -----------------------------------------------------------------------------------
	//   Functions
	// -----------------------------------------------------------------------------------
	public  void PKCS7Test(String aclMessage) {

		PKCS7 pkcs7 = new PKCS7();

		

		String password = "password";
		String envData = "";

		System.out.println(" ");
		System.out
				.println(" --- [ Demo of the PKCS#7 Function ] ----------------------------------- ");
		System.out.println(" ");

		// --- Function 60 : create PKCS#7 SignedAndEnvelopedData -----
		byte[] signedEnvelopedData = pkcs7.createSignedEnvelopedData(
				workingDir, aclMessage.getBytes(), issuer, password, recipients);

		if (signedEnvelopedData.length == 0)
			System.out.println("SignedAndEnvelopedData is NULL !");

		else {
			System.out.println("Creating signedAndEnvelopedData ... OK !");
			envData = new String(signedEnvelopedData);
			System.out.println(envData);
		}

		System.out.println(" ");

		// --- Function 59 : open PKCS#7 SignedAndEnvelopedData -----
		// --- Open EnvelopedData -----------------------------------
		System.out.println("Opening envelopedData ...");

		byte[] openSandEData0 = pkcs7.openSignedEnvelopedData(workingDir,
				signedEnvelopedData, recipients[0], password);
		System.out.println("subject 0 Opening SignedEnvelopedData ... OK !");
		System.out.println("Data in the envelope : \n");
		System.out.println(new String (openSandEData0)
				+ "\n");
		System.out.println(" ");
	}

}
