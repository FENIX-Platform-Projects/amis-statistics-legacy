package org.fao.fenix.web.modules.birt.client.view.report.template;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.map.ResourceExplorerMap;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Template1{

	private VerticalPanel mainCont;
	
	public VerticalPanel getMainCont() {
		return mainCont;
	}

	public Template1(final ReportViewer reportViewer){
		mainCont = new VerticalPanel();
		mainCont.setSpacing(5);
		mainCont.setSize("120px", "150px");
		mainCont.setBorderWidth(1);
		DOM.setStyleAttribute(mainCont.getElement(), "backgroundColor", "#FFFFFF");
		
		HorizontalPanel firstRow = new HorizontalPanel();
		firstRow.setSpacing(3);
		
		IconButton map=new IconButton("templateMap");
		map.setSize(37, 37);
		map.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent e){
				reportViewer.setObjectPosition(1);
				new ResourceExplorerMap(reportViewer);
			}
		}); 
		firstRow.add(map);
		
		IconButton chart=new IconButton("templateChart");
		chart.setSize(37, 37);
		chart.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent e){
				reportViewer.setObjectPosition(2);
				new ResourceExplorerChart(reportViewer);
			}
		}); 
		firstRow.add(chart);
				
		mainCont.add(firstRow);
		
		HorizontalPanel secondRow = new HorizontalPanel();
		secondRow.setSpacing(3);
		IconButton table = new IconButton("templateLargeTable");
		table.setSize(80, 37);
		table.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent componentEvent){
				reportViewer.setObjectPosition(3);
//				new ResourceExplorerTable(reportViewer);
				new ResourceExplorerDataset(reportViewer);
			}
		}); 
		secondRow.add(table);
		
		mainCont.add(secondRow);
		
		HorizontalPanel thirdRow = new HorizontalPanel();
		thirdRow.setSpacing(3);
		IconButton text = new IconButton("templateLargeText");
		text.setSize(80, 37);
		text.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent componentEvent){
				reportViewer.setObjectPosition(4);
				new ResourceExplorerText(reportViewer);
			}
		}); 
		thirdRow.add(text);
		
		mainCont.add(thirdRow);
		
		mainCont.setCellHorizontalAlignment(thirdRow, HorizontalPanel.ALIGN_CENTER);
		mainCont.setCellWidth(thirdRow, "100%");
		
		mainCont.setCellHorizontalAlignment(secondRow, HorizontalPanel.ALIGN_CENTER);
		mainCont.setCellWidth(secondRow, "100%");
		
		mainCont.setCellHorizontalAlignment(firstRow, HorizontalPanel.ALIGN_CENTER);
		mainCont.setCellWidth(firstRow, "100%");
		
		
	}
	
	
	
}
