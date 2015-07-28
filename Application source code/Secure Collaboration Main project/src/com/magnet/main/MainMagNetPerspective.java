package com.magnet.main;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.magnet.ui.ExplorerResourceView;
import com.setecs.OneSDK.ui.menu.ProductActionView;
import com.setecs.OneSDK.ui.menu.SwitchProductView;

public class MainMagNetPerspective implements IPerspectiveFactory {

	public static final String ID = new String("com.botnet.main.MainMagNetPerspective");
	
	public void createInitialLayout(IPageLayout layout) {
	    defineActions(layout);
	    defineLayout(layout);
	}
	
	public void defineActions(IPageLayout layout) {
        layout.addShowViewShortcut(ExplorerResourceView.ID);
        layout.addShowViewShortcut(ProductActionView.ID);
        
	}
	
	public void defineLayout(IPageLayout layout) {
		
        String editorArea = layout.getEditorArea();
        
        String viewId = SwitchProductView.ID;
        layout.addStandaloneView(
        		viewId,
        		false,
        		IPageLayout.TOP, (float) 0.055, editorArea);
        layout.getViewLayout(viewId).setCloseable(false);
        layout.getViewLayout(viewId).setMoveable(false);
        
        viewId = ExplorerResourceView.ID;
        layout.addStandaloneView(
        		viewId,
        		false,
        		IPageLayout.LEFT, (float) 0.25, editorArea);
//        		
        viewId = ProductActionView.ID;
        layout.addStandaloneView(
        		viewId,
        		false,
        		IPageLayout.TOP, (float) 0.03, editorArea);

        layout.getViewLayout(viewId).setCloseable(false);
        layout.getViewLayout(viewId).setMoveable(false);
	}
}
