package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Window;

public class CcbsShowCbsInformationWindow extends FenixWindow{

	public void build(String width, String height)
	{
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Build Start");
		//setSize(260, (30 * CCBS.FLAGS.getCount())+4);
		setSize(width, height);
		//getWindow().setWidth(2);
		//setTitle(BabelFish.print().ccbsFlag());
		getWindow().setCollapsible(true);
//		getWindow().setHeading(AMISLanguage.print().ccbsFlag());
//		getWindow().setPosition(70, 70);
		int oneSixthWidth = Window.getClientWidth()/2;
		int oneTenthHeight = Window.getClientHeight()/6;
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: oneQuarterWidth "+oneSixthWidth);
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: oneQuarterHeight "+oneTenthHeight);
		getWindow().setPosition(oneSixthWidth, oneTenthHeight);		
	}

	public void show(String width, String height, ListStore<AMISCodesModelData> listStore, String typeOfGrid)
	{
		//CCBS.FLAGS 
//		String height =""+ (30 * CCBS.FLAGS.getCount());
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: showFlags height = "+height);
//		for(int i=0; i< CCBS.FLAGS.getCount(); i++)
//		{
//			System.out.println("Class: CcbsShowFlagsWindow Function:build Text: showFlags Label: "+CCBS.FLAGS.getAt(i).getLabel());
//			System.out.println("Class: CcbsShowFlagsWindow Function:build Text: showFlags Code: "+CCBS.FLAGS.getAt(i).getCode());
//		}
		
		getWindow().add(buildGridFromModels(width, height, listStore, typeOfGrid));
		getWindow().show();
	}
	
	public ContentPanel buildGridFromModels(String width, String height, ListStore<AMISCodesModelData> listStore, String typeOfGrid) {
		ContentPanel cp = createGridPanel(width, height);
		if(listStore.getCount() > 0) {
			cp.removeAll();
			System.out.println("buildGridFromModels buildGrid 1 typeOfGrid "+typeOfGrid);
//			ListStore<BaseModel> store = new ListStore<BaseModel>();
//			
//			store.add(models);
			List<ColumnConfig> configs;
			ColumnModel cm = null;
			if(typeOfGrid.equals("Flags"))
			{
				getWindow().setHeading(AMISLanguage.print().ccbsFlag());
				configs = createColumnModelForFlags();
				cm = new ColumnModel(configs);
			}
			else if(typeOfGrid.equals("CountryInformation"))
			{
				getWindow().setHeading(AMISLanguage.print().iconViewCountryInformation());
				configs = createColumnModelForCountryInformation();
				cm = new ColumnModel(configs);
			}
			//Grid<BaseModel> grid = createBaseModelGrid(store, cm);
			Grid<AMISCodesModelData> grid = createBaseModelGrid(listStore, cm);
			if(typeOfGrid.equals("CountryInformation"))
			{
				grid.setHeight(40*listStore.getCount());
			//	grid.setHeight(400);
				grid.setWidth(250);
			}
			cp.add(grid);
		} else {
			cp.removeAll();
			cp.add(new Html("<b>No data available for the selections made</b>"));
			System.out.println("buildGridFromModels buildGrid 2 ");
		}	
		return cp;
	}
	
	private static ContentPanel createGridPanel(String width, String height) {
		ContentPanel cp = new ContentPanel();
		cp.setScrollMode(Scroll.AUTO);
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		//cp.setSize("2", "30");
		
		if ( width != null )
			cp.setWidth(width);		
		if ( height != null )
			cp.setHeight(height);
		
		if ( width == null && height == null) {
			cp.setBounds(10, 10, Integer.valueOf(950), 400);  
		}
		
		return cp;
	}
	
	private static List<ColumnConfig> createColumnModelForFlags() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
				
		ColumnConfig column = new ColumnConfig();  
		column.setId("code");  
		column.setHeader("Flag");  
		column.setWidth(50);  
		//column.setRowHeader(true);  
		configs.add(column);  
	
		column = new ColumnConfig();  
		column.setId("label");  
		column.setHeader("Description");  
		column.setWidth(520);  
		//column.setRowHeader(true);  
		configs.add(column);  
	
//        for(String key: tableHeaders.keySet()) {
//        	if(key.equals("faoSector")) {
//        		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),200);
//        		configs.add(column);
//        	} else if(key.equals("comparativeAdvantage")) {
//            		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),100);
//            		column.setRenderer(new GridCellRenderer<BaseModel>() {
//            				      public String render(BaseModel model, String property, ColumnData config, int rowIndex,
//            				          int colIndex, ListStore<BaseModel> store, Grid<BaseModel> grid) {  
//            				    	  String val = (String) model.get(property);
//
//            				    	  config.style += "background-color: #D3D3D3; ";
//            				    	  
//            				    	  if(val.equals("true"))
//            				    		 config.style +=  "color: #1F9901;font-weight:bold;";
//            				    	  else
//            				    		 config.style +=  "color: #1D4589;font-weight:normal;";
//            				    	  
//            				         //config.style += "background-color: #D3D3D3; color: #960018; font-weight: bold;";
//            				        
//            				        return model.get(property);
//            				      }
//            				    }	
//            		);
//            		configs.add(column);
//            	}	
//        	else{
//        		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),120);
//        		configs.add(column);
//        	}	
			
	//	}
		return configs;
		
	}
	
	private static List<ColumnConfig> createColumnModelForCountryInformation() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		
		ColumnConfig column = new ColumnConfig();  
		column.setId("code");  
		column.setHeader("Commodity");  
		column.setWidth(160);  
		//column.setRowHeader(true);  
		configs.add(column);  
	
		column = new ColumnConfig();  
		column.setId("label");  
		column.setHeader("Marketing Year");  
		column.setWidth(140);  
		//column.setRowHeader(true);  
		configs.add(column);  
		
		column = new ColumnConfig();  
		column.setId("domain");  
		column.setHeader("Crops Num.");  
		column.setWidth(100);  
		//column.setRowHeader(true);  
		configs.add(column);  
	
//        for(String key: tableHeaders.keySet()) {
//        	if(key.equals("faoSector")) {
//        		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),200);
//        		configs.add(column);
//        	} else if(key.equals("comparativeAdvantage")) {
//            		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),100);
//            		column.setRenderer(new GridCellRenderer<BaseModel>() {
//            				      public String render(BaseModel model, String property, ColumnData config, int rowIndex,
//            				          int colIndex, ListStore<BaseModel> store, Grid<BaseModel> grid) {  
//            				    	  String val = (String) model.get(property);
//
//            				    	  config.style += "background-color: #D3D3D3; ";
//            				    	  
//            				    	  if(val.equals("true"))
//            				    		 config.style +=  "color: #1F9901;font-weight:bold;";
//            				    	  else
//            				    		 config.style +=  "color: #1D4589;font-weight:normal;";
//            				    	  
//            				         //config.style += "background-color: #D3D3D3; color: #960018; font-weight: bold;";
//            				        
//            				        return model.get(property);
//            				      }
//            				    }	
//            		);
//            		configs.add(column);
//            	}	
//        	else{
//        		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),120);
//        		configs.add(column);
//        	}	
			
	//	}
		return configs;
		
	}
	
	private static Grid<AMISCodesModelData> createBaseModelGrid(ListStore<AMISCodesModelData> store, ColumnModel cm) {
		System.out.println("Class: PagePanelController Function: createBaseModelGrid Text: store.size " +store.getCount());
		Grid<AMISCodesModelData> grid = new Grid<AMISCodesModelData>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
//		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setBorders(true);
		grid.setStripeRows(true);
		for(int i=0;i<store.getCount(); i++)
		{
			System.out.println("Class: PagePanelController Function: showCountryInformation Text:Before i= "+i+" Code:" + store.getAt(i).getCode()+" Label:" + store.getAt(i).getLabel()+" Domain:" + store.getAt(i).getDomain());
			
			
		}
		return grid;
	}
}
