package org.fao.fenix.web.modules.coding.client.view.creator;

import org.fao.fenix.web.modules.coding.client.control.creator.DcmtCodingCreatorController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class DcmtCodingCreatorWindow extends FenixWindow {

	DcmtCodingCreatorMenu dcmtCodingCreatorMenu;
	DcmtCodingCreatorResult dcmtCodingCreatorResult;
	TabPanel tabs;
	TabItem searchTab;
	TabItem createTab;

	public void build() {
		VerticalPanel panel = new VerticalPanel();
		tabs = new TabPanel();  
		tabs.setWidth(450);  
		tabs.setAutoHeight(true);  		  
		searchTab = new TabItem(BabelFish.print().searchCode());  
		searchTab.addStyleName("pad-text");
		searchTab.add(buildMenuPanel());
		tabs.add(searchTab); 
		TabItem createTab = new TabItem(BabelFish.print().createDcmtCode());  
		createTab.addStyleName("pad-text");  
		createTab.add(buildCreateCodePanel());
		//createTab.setEnabled(false);
		tabs.add(createTab);	
		setCenterProperties();
		getCenter().add(tabs);
		addCenterPartToWindow();
		format();
		enhance();
		show();
	}

	private ContentPanel buildMenuPanel() {
		dcmtCodingCreatorMenu = new DcmtCodingCreatorMenu();
		ContentPanel panel = dcmtCodingCreatorMenu.build(this, dcmtCodingCreatorResult);
		return panel;
	}
	
	private HorizontalPanel buildCreateCodePanel() {
		dcmtCodingCreatorResult = new DcmtCodingCreatorResult();
		HorizontalPanel hPanel = dcmtCodingCreatorResult.build(this);
		return hPanel;
	}

	protected void format() {
		setSize(800, 550);
		window.setHeading("<b> " + BabelFish.print().createCode() +  " </b>");
		getCenter().setHeaderVisible(false);
	}
	
	private void enhance() {
		getWindow().addWindowListener(new WindowListener() {
			@Override
			public void windowHide(WindowEvent we) {
				DcmtCodingCreatorController.uploadModifiedCodesClosing(getDcmtCodingCreatorMenu());
//				super.windowClose(we); // ETj 20090727 commented out, because that method does not exist anymore
				System.out.println("***** DcmtCodingCreatorWindow::enhance() FIXME");
			}
		});
	}


	
	
	public TabPanel getTabs() {
		return tabs;
	}

	public TabItem getSearchTab() {
		return searchTab;
	}

	public TabItem getCreateTab() {
		return createTab;
	}

	public DcmtCodingCreatorMenu getDcmtCodingCreatorMenu() {
		return dcmtCodingCreatorMenu;
	}

	public DcmtCodingCreatorResult getDcmtCodingCreatorResult() {
		return dcmtCodingCreatorResult;
	}
	
	
}