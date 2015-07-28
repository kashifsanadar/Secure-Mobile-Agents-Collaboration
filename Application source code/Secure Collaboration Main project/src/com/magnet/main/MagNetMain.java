package com.magnet.main;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.setecs.OneSDK.ui.ApplicationWorkbenchAdvisor;

public class MagNetMain implements IApplication {
	
    // ------------------------------------------------------------------
	public Object start(IApplicationContext context)  throws Exception  {
		
		Display display = PlatformUI.createDisplay();
		
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}

			return IApplication.EXIT_OK;
		}
		finally {
			display.dispose();
		}
	}

	// -----------------------
	public void stop() {}
}
