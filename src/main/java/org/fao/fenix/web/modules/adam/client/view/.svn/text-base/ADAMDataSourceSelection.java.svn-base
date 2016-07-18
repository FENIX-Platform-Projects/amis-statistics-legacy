package org.fao.fenix.web.modules.adam.client.view;


import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMDatasetController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.utils.ADAMClientUtils;
import org.fao.fenix.web.modules.adam.common.model.DatasetModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class ADAMDataSourceSelection {

	
	public static HorizontalPanel panel;

	private static Boolean isBuild = false;
	
	public static ComboBox<DatasetModel> datasetCombo;
	
	public static ListStore<DatasetModel> datasetStore;
	
	public ADAMDataSourceSelection() {

	}
	
	

	public static HorizontalPanel build() {
		panel = new HorizontalPanel();
		panel.addStyleName("datasource_selection");
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
	//	panel.setSpacing(5);
		
		IconButton icon = new IconButton("adam_info");
		icon.setToolTip("Click for more information on the Data Sources");
		icon.setHeight(20);
		icon.addSelectionListener(ADAMDatasetController.showDatasourceInfo());
	
		
		Html dataSource = new Html("Data Source:");
		dataSource.setWidth(80);
//		dataSource.setStyleName("top-links");
		

		
		datasetStore = new ListStore<DatasetModel>();
		datasetCombo= new ComboBox<DatasetModel>();
		datasetCombo.setTriggerAction(TriggerAction.ALL);
		datasetCombo.setStore(datasetStore);
		datasetCombo.setDisplayField("name");
		datasetCombo.setEmptyText("Please select a Data Source");
		datasetCombo.setWidth(ADAMConstants.DATASET_LIST_WIDTH);
		datasetStore.add(ADAMBoxMaker.defaultDatasetCodes());
		datasetCombo.setValue(datasetStore.getAt(0));
		datasetCombo.addSelectionChangedListener(ADAMController.setCurrentDatasetSelection(datasetCombo));
	//	datasetCombo.setTemplate(getTemplate()); 
		
		
//		mainMenuBar.add(dataSource);
		
//		mainMenuBar.add(datasetCombo);
//		
//		mainMenuBar.add(new Html("&nbsp;&nbsp;&nbsp;&nbsp;"));
		
		panel.add(icon);
		panel.add(ADAMClientUtils.addHSpace(5));
		panel.add(dataSource);
		panel.add(datasetCombo);
	
		
		
		isBuild = true;
		return panel;
	}



	public static HorizontalPanel getPanel() {
		return panel;
	}



	public static void setPanel(HorizontalPanel panel) {
		ADAMDataSourceSelection.panel = panel;
	}



	public static Boolean isBuild() {
		return isBuild;
	}



	public static void setIsBuild(Boolean isBuild) {
		ADAMDataSourceSelection.isBuild = isBuild;
	}
	
	private native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{name}" qtitle="">{name}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;  
	
	
	

	
}