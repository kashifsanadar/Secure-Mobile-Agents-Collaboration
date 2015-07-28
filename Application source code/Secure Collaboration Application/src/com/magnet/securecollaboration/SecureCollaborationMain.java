package com.magnet.securecollaboration;

import org.eclipse.jface.resource.ImageDescriptor;

import com.magnet.securecollaboration.actions.AboutAction;
import com.magnet.ui.util.MagNetImageProvider;
import com.setecs.OneSDK.core.IOneSDKMain;
import com.setecs.OneSDK.core.ui.Action;
import com.setecs.OneSDK.core.ui.ITreeNode;
import com.setecs.OneSDK.core.ui.swt.ToolbarMenuManager;
import com.setecs.OneSDK.ui.ImageProvider;
import com.setecs.OneSDK.ui.OneSDKMain;


public class SecureCollaborationMain  extends OneSDKMain {

	public static final String APPLICATION_ID = "SecureCollaboration";
	private static final String APPLICATION_NAME = "Secure Collaboration";
	
    private Action aboutAction_;
	public static final String M_AUDITLOG = "auditLog";

    
	// -----------------------------------------
	public String getConfigurationDirectory() {
		return getDataDirectory() + "Configuration/";
	}

	@Override
	public ImageDescriptor getIcon() {
		return MagNetImageProvider.Error24x24;
	}

	@Override
	public ImageDescriptor getAcceleratorImage() {
		return MagNetImageProvider.ErrorApplication;
	}

	// -----------------------------------------
	public String getDataDirectory() {
		
		return getWorkspaceLocation() + APPLICATION_ID + "/";
	}
	
	// -------------------------------------------------------------
	public ITreeNode<IOneSDKMain> getProductTreeNode() {
		return null;
	}
	
	// --------------------------------------------
	public ToolbarMenuManager[] getMenuContributions() {
		   

		ToolbarMenuManager[] menuContributions = new ToolbarMenuManager[1];
			
		try {
			
        // HELP menu actions
			menuContributions[0] = new ToolbarMenuManager("&Help", M_AUDITLOG);
			menuContributions[0].setImageDescriptor(ImageProvider.Help_24);
			menuContributions[0].add(aboutAction_);
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
			
		return menuContributions;
	}

	// --------------------------------------------
	public String getProductName() {
		return APPLICATION_NAME;
	}

	// --------------------------------------------
	public String getProductID() {
		return APPLICATION_ID;
	}
	
	// ------------------------------------------------------------------
	public void initialize() throws ProductInitializationException {
			
		aboutAction_ = new AboutAction("About Agent Collaboration");

	}

	// -----------------------------------------
	public void initializeWorkbench() {
		
	}
}
