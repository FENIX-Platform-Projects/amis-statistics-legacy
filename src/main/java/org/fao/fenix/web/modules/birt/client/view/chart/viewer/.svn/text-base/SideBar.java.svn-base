package org.fao.fenix.web.modules.birt.client.view.chart.viewer;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class SideBar {

	
	private ContentPanel mainCont;
	private ContentPanel datasetList;
	private VerticalPanel vpContDS;
	
	private ContentPanel chartAxis;
	private VerticalPanel vpChartAxis;
	private ContentPanel otherDimension;
	private VerticalPanel vpOtherDim;
	private ContentPanel legend; 
	
	public ContentPanel getMainCont() {
		return mainCont;
	}

	public VerticalPanel getVpContDS() {
		return vpContDS;
	}

	public VerticalPanel getVpChartAxis() {
		return vpChartAxis;
	}

	public VerticalPanel getVpOtherDim() {
		return vpOtherDim;
	}

	public ContentPanel getLegend() {
		return legend;
	}

	public SideBar(){
		mainCont = new ContentPanel();
		mainCont.setBodyBorder(false);
		mainCont.setHeaderVisible(false);
		mainCont.setLayout(new AccordionLayout());  
		mainCont.setIconStyle("icon-accordion");
		
		datasetList = new ContentPanel();  
		datasetList.setHeading(BabelFish.print().datasetList());  
		datasetList.setScrollMode(Scroll.AUTO);  
		datasetList.setExpanded(true);
		vpContDS = new VerticalPanel();
		vpContDS.setSpacing(5);
		datasetList.add(vpContDS);
		mainCont.add(datasetList);
		
		chartAxis = new ContentPanel();  
		chartAxis.setHeading(BabelFish.print().selectChartAxis());  
		chartAxis.setScrollMode(Scroll.AUTO);
		chartAxis.setExpanded(false);
		vpChartAxis = new VerticalPanel();
		vpChartAxis.setScrollMode(Scroll.AUTO);
		vpChartAxis.setSpacing(5);
		chartAxis.add(vpChartAxis);
		mainCont.add(chartAxis);
		
		otherDimension = new ContentPanel();  
		otherDimension.setHeading(BabelFish.print().selectDimValues());  
		otherDimension.setScrollMode(Scroll.AUTO);  
		otherDimension.setExpanded(false);
		vpOtherDim = new VerticalPanel();
		vpOtherDim.setSpacing(5);
		vpOtherDim.setScrollMode(Scroll.AUTO);
		otherDimension.add(vpOtherDim);
		mainCont.add(otherDimension);
	}

	public ContentPanel getDatasetList() {
		return datasetList;
	}

	public ContentPanel getChartAxis() {
		return chartAxis;
	}

	public ContentPanel getOtherDimension() {
		return otherDimension;
	}
	
	
}
