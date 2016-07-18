package org.fao.fenix.web.modules.coding.client.view.creator;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.ui.HTML;

public class DcmtCreatorConfirm extends FenixWindow {	
	
	public DcmtCreatorConfirm(String confirmMessage, List<DcmtCodingCreatorGridMD> md, SelectionListener<ButtonEvent> okListener) {
		setCenterProperties();
		getCenter().add(buildCenterPanel(confirmMessage, md, okListener));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWindow().setHeading(BabelFish.print().confirmYourChoice());
		format();
		show();
	}
	
	private VerticalPanel buildCenterPanel(String confirmMessage, List<DcmtCodingCreatorGridMD> md, SelectionListener<ButtonEvent> okListener) {
		VerticalPanel centerPanel = new VerticalPanel();
		centerPanel.add(buildCodesPanel(md));
		centerPanel.add(buildMessagePanel(confirmMessage));
		centerPanel.add(buildButtonsPanel(okListener));
		return centerPanel;
	}
	
	private HorizontalPanel buildCodesPanel(List<DcmtCodingCreatorGridMD> md){
		HorizontalPanel codesPanel = new HorizontalPanel();
		codesPanel.add(buildGrid(md));		
		codesPanel.setSize(450, 230);
		codesPanel.setScrollMode(Scroll.AUTO);
		return codesPanel;
	}
	
	private Grid buildGrid(List<DcmtCodingCreatorGridMD> md){
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		List<DcmtCodingCreatorGridMD> backup = new ArrayList<DcmtCodingCreatorGridMD>();
		backup.addAll(md);
		ListStore listStore = new ListStore<DcmtCodingCreatorGridMD>();	    

		listStore.add(backup);
		
//		CheckBoxSelectionModel<DcmtCodingCreatorGridMD> sm = new CheckBoxSelectionModel<DcmtCodingCreatorGridMD>();  
		  
//		  configs.add(sm.getColumn());  
		
		
		ColumnConfig column = new ColumnConfig();  
		column.setId("code");  
		column.setHeader(BabelFish.print().code());  
		column.setWidth(100);  	   
		configs.add(column);

		column = new ColumnConfig();  
		column.setId("dcmtCode");  
		column.setHeader("Dcmt " + BabelFish.print().code());  
		column.setWidth(100);
		configs.add(column);
		 
		column = new ColumnConfig();  
		column.setId("label");  
		column.setHeader(BabelFish.print().label());  
		column.setWidth(220);  
		configs.add(column); 
			
	    ColumnModel cm = new ColumnModel(configs);  
		
		Grid grid = new Grid<DcmtCodingCreatorGridMD>(listStore ,cm); 
		grid.disableTextSelection(true);
		grid.setSize(450, 230);
		return grid;
	} 
	
	
	private HorizontalPanel buildMessagePanel(String confirmMessage) {
		HorizontalPanel messagePanel = new HorizontalPanel();
		messagePanel.setSpacing(10);
		HTML message = new HTML("<font color='red'>" + confirmMessage + "</font>");
		messagePanel.add(message);
		return messagePanel;
	}
	
	private HorizontalPanel buildButtonsPanel(SelectionListener<ButtonEvent> okListener) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		Button ok = new Button(BabelFish.print().ok(), okListener);
		ok.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});

		Button cancel = new Button(BabelFish.print().close(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});
		buttonsPanel.add(ok);
		buttonsPanel.add(cancel);
		return buttonsPanel;
	}
	
	private void format() {
		getWindow().setSize("500px", "350px");
		getWindow().setModal(true);
	}
	
}
