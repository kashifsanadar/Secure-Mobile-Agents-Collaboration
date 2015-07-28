package com.magnet.main.splashHandlers;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.splash.AbstractSplashHandler;

public class InteractiveSplashHandler extends AbstractSplashHandler {

	public InteractiveSplashHandler() {}
	
	// --- Init method for initial splash screen ------------------------------------
	public void init(final Shell splash) {
		System.out.println("init splash");
	}
}
