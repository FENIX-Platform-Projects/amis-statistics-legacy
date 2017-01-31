package org.fao.fenix.web.modules.coding.client;




import org.fao.fenix.web.modules.coding.client.view.creator.CodingCodeSelector;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchWindow;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */


public class Coding implements EntryPoint {

	
	public static CodingSearchWindow dcmtSearchWindow;
	public static CodingCodeSelector dcmtCodeSelector;
  /**
   * This is the entry point method.
   * 
   */
	public void onModuleLoad() {
		HorizontalPanel hPanel = new HorizontalPanel();
	  dcmtCodeSelector = new CodingCodeSelector();
	  dcmtCodeSelector.build(hPanel);
	 // dcmtSearchWindow = new DcmtSearchWindow(); 
	 // dcmtSearchWindow.build();
		
	//	dcmtImporterWindow = new DcmtImporterWindow();
	// 	dcmtImporterWindow.build();
	
	
		
	
	  
  }
  
}