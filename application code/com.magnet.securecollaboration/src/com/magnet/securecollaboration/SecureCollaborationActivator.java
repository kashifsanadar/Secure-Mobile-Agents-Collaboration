package com.magnet.securecollaboration;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.magnet.securecollaboration.application.Authentication;
import com.magnet.securecollaboration.tests.*;

import setecs.OneSEC.platform.PKCS7;

/**
 * The activator class controls the plug-in life cycle
 */
public class SecureCollaborationActivator extends AbstractUIPlugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "com.magnet.securecollaboration";

	// The shared instance
	private static SecureCollaborationActivator plugin;
	
	/**
	 * The constructor
	 */
	public SecureCollaborationActivator() {
		
/**
 * //-- Authentication Test
		Authentication auth = new Authentication();
		
		String strEncChallenge = auth.generateChallenge();
		
		System.out.println("Enc data is " + strEncChallenge);
		
		auth.receiveChallenge(strEncChallenge);
		
		System.out.println("opened data is " + auth.aclMessage.toString());	
 */	
		
		//-- PKCS 7 Test
		PKCS_7 pkcs7 = new PKCS_7();
		String input = "Dept. of DSV.";
		System.out.println("input:"+input);
		
		String data = pkcs7.envelopPKCS7(input);
		
		String str = pkcs7.deEnvelopPKCS7(data);
		System.out.println("Opened data is " + str);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static SecureCollaborationActivator getDefault() {
		return plugin;
	}

}
