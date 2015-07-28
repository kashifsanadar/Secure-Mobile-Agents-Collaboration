package com.magnet.main;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;

import com.magnet.securecollaboration.SecureCollaborationMain;
import com.magnet.ui.util.MagNetImageProvider;
import com.magnet.workstation.MagNetWorkstationMain;
import com.setecs.OneSDK.core.IOneSDKMain;
import com.setecs.OneSDK.core.login.ILoginDialog;
import com.setecs.OneSDK.core.login.ILoginProvider;
import com.setecs.OneSDK.core.ui.MessageFactory;
import com.setecs.OneSDK.core.ui.swt.DropDownButton;
import com.setecs.OneSDK.core.ui.swt.SSMessageFactory;
import com.setecs.OneSDK.ui.OneSDKUIEnvironment;
import com.setecs.OneSDK.ui.config.IStartupConfiguration;
import com.setecs.OneSDK.ui.config.ISystemWindowConfiguration;
import com.setecs.OneSDK.ui.menu.SystemMenu;
import com.setecs.OneSDK.ui.menu.SystemMenu.SwitchProductAction;

public class MagNetSystemConfiguration implements ILoginProvider, ISystemWindowConfiguration, IStartupConfiguration {
	// -------------------------------------------------
	// ILoginProvider
	
	@Override
	public ILoginDialog getLoginDialog() {
		// no login!
		return null;
	}

	// -------------------------------------------------
	// ISystemConfiguration
	
	@Override
	public void preWindowOpen(IWorkbenchWindowConfigurer configurer) {
		// --- Initialize SWT GUI components
		MessageFactory.initialize(new SSMessageFactory());
	}

	@Override
	public void postWindowOpen() {}
	
	@Override
	public SystemMenu createSystemMenu() {
		SystemMenu menu = new SystemMenu();
		
		List<IOneSDKMain> products = new LinkedList<IOneSDKMain>();

		try {
			// MagNet Workstation
			
			IOneSDKMain stationManager = OneSDKUIEnvironment.getInstance().getSetecsApplications().get(MagNetWorkstationMain.APPLICATION_ID);
			if (stationManager != null)
				products.add(stationManager);
			

			
			// Network Management
			

			// ---Secure Collaboration ---
			IOneSDKMain  cecureCollaboration = OneSDKUIEnvironment.getInstance().getSetecsApplications().get(SecureCollaborationMain.APPLICATION_ID);
			if (cecureCollaboration != null)
				products.add(cecureCollaboration);
			


		}
		catch(Exception e ) {
			e.printStackTrace();
		}

		for (IOneSDKMain product : products) {
			SwitchProductAction action = new SwitchProductAction(product);
			menu.addEntry(action);
		}
		return menu;
	}

	@Override
	public void configureSystemMenuButton(DropDownButton button) {
		button.setToolTipText("Switch MagNet Products");
		button.setImageDescriptor(MagNetImageProvider.MagNet);
	}

	@Override
	public String getInitialPerspectiveId() {
		return MainMagNetPerspective.ID;
	}
	
	@Override
	public WelcomeViewContent getWelcomeViewContent() {
		return null;
	}
}
