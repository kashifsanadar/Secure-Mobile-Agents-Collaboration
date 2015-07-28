package com.magnet.securecollaboration.actions;

import com.magnet.ui.util.MagNetImageProvider;
import com.setecs.OneSDK.core.ui.Action;
import com.setecs.OneSDK.core.ui.IMessage;
import com.setecs.OneSDK.core.ui.MessageFactory;
import com.magnet.securecollaboration.application.Authentication;
import com.magnet.securecollaboration.tests.PKCS_7;
import com.magnet.securecollaboration.tests.SecPlatoformDemo;
import sun.misc.BASE64Encoder; 
import sun.misc.BASE64Decoder;

public class AboutAction extends Action  {
	
	public final static String ID = "com.magnet.securecollaboration.AboutAction";
	
	// --------------------------------------------------------
	public AboutAction(String text) {
		
		super(text);
		this.setImageDescriptor(MagNetImageProvider.Message_Info_16);
		this.setId(ID);
		
	}
	
	public void performAction() {		
	
//		//-- Auth Test
//		Authentication auth = new Authentication();
//		
//		String strEncChallenge = auth.generateChallenge();
//		System.out.println("Enc data is " + strEncChallenge);
//		
//		//auth.receiveChallenge(strEncChallenge);
//		
//		//System.out.println("opened data is " + auth.aclMessage.toString());
	
		try {
			MessageFactory.getInstance().showMessage(MagNetImageProvider.Information_16.createImage(), " Agent Secure Collaboration",
				"This plug-in has not been developed",
				MagNetImageProvider.Warning_48.createImage(), 
				new String[]{IMessage.OK});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		//ContentPaneEditor.showContentPane(new RegisterServer());	
	}

}
