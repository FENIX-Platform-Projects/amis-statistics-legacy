package org.fao.fenix.web.modules.birt.client.view.chart.wizard;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartWizardController;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class QuantityDimension {
	
	Window window;
	ListBox numDimBox;
	VerticalPanel mainCont;
	HorizontalPanel okCont;
	Button ok;
	ListBox dimensionSel;
	ListBox dimensionNoSel;
	VerticalPanel otherDimensions; 
	List<String> otherDim;
	String datasetId;
	
	public QuantityDimension(long datasetId, ListBox dimensionSel, ListBox dimensionNoSel, VerticalPanel otherDimensions, List<String> otherDim){
		
		this.datasetId = String.valueOf(datasetId);
		this.dimensionSel = dimensionSel;
		this.dimensionNoSel = dimensionNoSel;
		this.otherDimensions = otherDimensions;
		this.otherDim = otherDim;
		
		window = new Window();
		window.setSize(300, 100);
		window.setHeading("Numeric Dimensions");
		mainCont = new VerticalPanel();
		mainCont.setSpacing(10);
		numDimBox = new ListBox();
		numDimBox.setWidth("270px");
		okCont = new HorizontalPanel();
		ok = new Button(BabelFish.print().ok());
		
		ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			
			public void componentSelected(ButtonEvent ce){
				
				String selDim = numDimBox.getItemText(numDimBox.getSelectedIndex());
				for (int i=0; i<QuantityDimension.this.otherDim.size(); i++){
					if (QuantityDimension.this.otherDim.get(i).equals(selDim)){
						QuantityDimension.this.otherDim.remove(i);
						break;
					}
				}
				
				ChartWizardController.fillOtherDimensionYEvent(QuantityDimension.this.datasetId, QuantityDimension.this.dimensionSel, QuantityDimension.this.dimensionNoSel, QuantityDimension.this.otherDimensions, QuantityDimension.this.otherDim);
				window.close();
				
			}
			
		});
		
		
		BirtServiceEntry.getInstance().getQuantityColumnNames(datasetId, otherDim, new AsyncCallback<List<String>>(){
			
			public void onSuccess(List<String> numericDimList){
				
				for (String el : numericDimList){
					numDimBox.addItem(el);
				}
				
				mainCont.add(numDimBox);
				
				okCont.add(ok);
				mainCont.add(okCont);
				mainCont.setCellHorizontalAlignment(okCont, HorizontalPanel.ALIGN_CENTER);
								
				window.add(mainCont);
				window.show();
			}
			
			public void onFailure(Throwable caught){
				Info.display("getQuantityColumnNames", caught.getLocalizedMessage());
			}
			
		});
		
		
	}

}
