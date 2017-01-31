package org.fao.fenix.web.modules.ofcchart.client.view.wizard;


import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartFormatBeanVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class OfcChartType extends TabItem {
	
	private VerticalPanel cont;
	private ContentPanel mainCont;
	private HorizontalPanel chartTypeActivated;
	private Button next;
		
	public HorizontalPanel getChartTypeActivated() {
		return chartTypeActivated;
	}

	public void setChartTypeActivated(HorizontalPanel chartTypeActivated) {
		this.chartTypeActivated = chartTypeActivated;
	}
	
	private HorizontalPanel buildPreview(String color){
		HorizontalPanel preview = new HorizontalPanel();
		preview.setSize("160px", "114px");
		DOM.setStyleAttribute(preview.getElement(), "backgroundColor", color);
		preview.setSpacing(5);
		
		return preview;
	}

	public OfcChartType(OfcChartFormatBeanVO chartFormartBean, OfcChartWizard chartWizard){
		mainCont = new ContentPanel();
		mainCont.setHeaderVisible(false);
		cont = new VerticalPanel();
		//cont.setSpacing(10);
		next = new Button(BabelFish.print().next());
		next.addSelectionListener(OfcChartWizardController.changeTab(1, chartWizard));	
		setText(BabelFish.print().chartType());
		setScrollMode(Scroll.AUTO);
		chartFormartBean.setChartType("Bar");
		
		// *************** FIRST ROW *******************
		HorizontalPanel firstRow=new HorizontalPanel();
		firstRow.setSpacing(5);
		
		Image imgBar=new Image("birt-images/BarBeside.png");
		DOM.setStyleAttribute(imgBar.getElement(), "cursor", "pointer");
		VerticalPanel vpBar = new VerticalPanel();
		HorizontalPanel contBar=buildPreview("#FF0000");
		//default chart
		setChartTypeActivated(contBar);
		imgBar.addClickListener(OfcChartWizardController.activeDisactive(contBar, "Bar", chartFormartBean, this));
		contBar.add(imgBar);
		vpBar.add(contBar);
		vpBar.add(new HTML(BabelFish.print().barChart() + " " + BabelFish.print().chart()));
		vpBar.setHorizontalAlign(HorizontalAlignment.CENTER);
		firstRow.add(vpBar);
		
		Image imgArea=new Image("birt-images/AreaBeside.png");
		DOM.setStyleAttribute(imgArea.getElement(), "cursor", "pointer");
		VerticalPanel vpArea = new VerticalPanel();
		HorizontalPanel contArea=buildPreview("#cbc4c4");
		imgArea.addClickListener(OfcChartWizardController.activeDisactive(contArea, "Area", chartFormartBean, this));
		contArea.add(imgArea);
		vpArea.add(contArea);
		vpArea.add(new HTML(BabelFish.print().areaChart() + " " + BabelFish.print().chart()));
		vpArea.setHorizontalAlign(HorizontalAlignment.CENTER);
		firstRow.add(vpArea);
				
		Image imgTube=new Image("birt-images/TubeBeside.png");
		DOM.setStyleAttribute(imgTube.getElement(), "cursor", "pointer");
		VerticalPanel vpTube = new VerticalPanel();
		HorizontalPanel contTube=buildPreview("#cbc4c4");
		imgTube.addClickListener(OfcChartWizardController.activeDisactive(contTube, "Tube" , chartFormartBean, this));
		contTube.add(imgTube);
		vpTube.add(contTube);
		vpTube.add(new HTML(BabelFish.print().tubeChart() + " " + BabelFish.print().chart()));
		vpTube.setHorizontalAlign(HorizontalAlignment.CENTER);
		firstRow.add(vpTube);
		
		cont.add(firstRow);
		
		// *************** SECOND ROW *******************
		
		HorizontalPanel secondRow=new HorizontalPanel();
		secondRow.setSpacing(5);
		
		Image imgCone=new Image("birt-images/ConeBeside.png");
		DOM.setStyleAttribute(imgCone.getElement(), "cursor", "pointer");
		VerticalPanel vpCone = new VerticalPanel();
		HorizontalPanel contCone=buildPreview("#cbc4c4");
		imgCone.addClickListener(OfcChartWizardController.activeDisactive(contCone, "Cone", chartFormartBean, this));
		contCone.add(imgCone);
		vpCone.add(contCone);
		vpCone.add(new HTML(BabelFish.print().coneChart() + " " + BabelFish.print().chart()));
		vpCone.setHorizontalAlign(HorizontalAlignment.CENTER);
		secondRow.add(vpCone);
		
		Image imgLine=new Image("birt-images/LineBeside.png");
		DOM.setStyleAttribute(imgLine.getElement(), "cursor", "pointer");
		VerticalPanel vpLine = new VerticalPanel();
		HorizontalPanel contLine=buildPreview("#cbc4c4");
		imgLine.addClickListener(OfcChartWizardController.activeDisactive(contLine, "Line", chartFormartBean, this));
		contLine.add(imgLine);
		vpLine.add(contLine);
		vpLine.add(new HTML(BabelFish.print().lineChart() + " " + BabelFish.print().chart()));
		vpLine.setHorizontalAlign(HorizontalAlignment.CENTER);
		secondRow.add(vpLine);
				
		Image imgPie=new Image("birt-images/PieBeside.png");
		DOM.setStyleAttribute(imgPie.getElement(), "cursor", "pointer");
		VerticalPanel vpPie = new VerticalPanel();
		HorizontalPanel contPie=buildPreview("#cbc4c4");
		imgPie.addClickListener(OfcChartWizardController.activeDisactive(contPie, "Pie", chartFormartBean, this));
		contPie.add(imgPie);
		vpPie.add(contPie);
		vpPie.add(new HTML(BabelFish.print().pieChart() + " " + BabelFish.print().chart()));
		vpPie.setHorizontalAlign(HorizontalAlignment.CENTER);
		secondRow.add(vpPie);
		
		cont.add(secondRow);
		
		// *************** THIRD ROW *******************
		
		HorizontalPanel thirdRow=new HorizontalPanel();
		thirdRow.setSpacing(5);
		
		Image imgPyramid=new Image("birt-images/PyramidBeside.png");
		DOM.setStyleAttribute(imgPyramid.getElement(), "cursor", "pointer");
		VerticalPanel vpPyramid = new VerticalPanel();
		HorizontalPanel contPyramid=buildPreview("#cbc4c4");
		imgPyramid.addClickListener(OfcChartWizardController.activeDisactive(contPyramid, "Pyramid", chartFormartBean, this));
		contPyramid.add(imgPyramid);
		vpPyramid.add(contPyramid);
		vpPyramid.add(new HTML(BabelFish.print().pyramidChart() + " " + BabelFish.print().chart()));
		vpPyramid.setHorizontalAlign(HorizontalAlignment.CENTER);
		thirdRow.add(vpPyramid);
		
		Image imgScatter=new Image("birt-images/ScatterBeside.png");
		DOM.setStyleAttribute(imgScatter.getElement(), "cursor", "pointer");
		VerticalPanel vpScatter = new VerticalPanel();
		HorizontalPanel contScatter=buildPreview("#cbc4c4");
		imgScatter.addClickListener(OfcChartWizardController.activeDisactive(contScatter, "Scatter" , chartFormartBean, this));
		contScatter.add(imgScatter);
		vpScatter.add(contScatter);
		vpScatter.add(new HTML(BabelFish.print().scatterChart() + " " + BabelFish.print().chart()));
		vpScatter.setHorizontalAlign(HorizontalAlignment.CENTER);
		thirdRow.add(vpScatter);
		
		
		Image imgBarLine=new Image("birt-images/BarLineBeside.png");
		DOM.setStyleAttribute(imgBarLine.getElement(), "cursor", "pointer");
		VerticalPanel vpBarLine = new VerticalPanel();
		HorizontalPanel contBarLine=buildPreview("#cbc4c4");
		imgBarLine.addClickListener(OfcChartWizardController.activeDisactive(contBarLine, "BarLine", chartFormartBean, this));
		contBarLine.add(imgBarLine);
		vpBarLine.add(contBarLine);
		vpBarLine.add(new HTML(BabelFish.print().barLineChart() + " " + BabelFish.print().chart()));
		vpBarLine.setHorizontalAlign(HorizontalAlignment.CENTER);
		thirdRow.add(vpBarLine);
		
		
		cont.add(thirdRow);
		
		mainCont.add(cont);
		
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		vp.add(next);
		vp.setHorizontalAlign(HorizontalAlignment.RIGHT);
		
		mainCont.setBottomComponent(vp);
				
		add(mainCont);
	}
	

}