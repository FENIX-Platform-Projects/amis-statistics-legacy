package org.fao.fenix.web.modules.core.client.toolbox;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;


public class FenixToolbox {
	
	public static boolean toolboxIsopen;
	Window windowToolbox;
	VerticalPanel verticalPanel;
	
	public Window getWindowToolbox() {
		return windowToolbox;
	}


	public FenixToolbox(){
		windowToolbox = new Window();
		FenixToolbox.toolboxIsopen = true;
		windowToolbox.setMinWidth(10);
		windowToolbox.setSize(60, 370);
		windowToolbox.setPagePosition(15, ((com.google.gwt.user.client.Window.getClientHeight() / 2) - 154));
		windowToolbox.setResizable(false);
		windowToolbox.setIconStyle("tools");
		verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(6);
				
		
		PushButton search = new PushButton(new Image("toolBox-images/searchToolbox.gif"));
		search.setTitle(BabelFish.print().resourceExplorerWindowHeader());
		search.addClickListener(FenixToolboxController.open("search"));
		verticalPanel.add(search);
		verticalPanel.setCellHorizontalAlignment(search, HorizontalPanel.ALIGN_CENTER);		
		
		verticalPanel.add(new Image("toolBox-images/separatorToolbox.gif"));
		
		PushButton project = new PushButton(new Image("toolBox-images/projectToolbox.gif"));
		project.addClickListener(FenixToolboxController.open("project"));
		project.setTitle(BabelFish.print().projectManager());
		verticalPanel.add(project);
		verticalPanel.setCellHorizontalAlignment(project, HorizontalPanel.ALIGN_CENTER);	
		
		verticalPanel.add(new Image("toolBox-images/separatorToolbox.gif"));
		
		PushButton map = new PushButton(new Image("toolBox-images/mapToolbox.gif"));
		map.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().mapTB());
		map.addClickListener(FenixToolboxController.open("map"));
		verticalPanel.add(map);
		verticalPanel.setCellHorizontalAlignment(map, HorizontalPanel.ALIGN_CENTER);
		
		PushButton table = new PushButton(new Image("toolBox-images/tableToolbox.gif"));
		table.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().tableTB());
		table.addClickListener(FenixToolboxController.open("table"));
		verticalPanel.add(table);
		verticalPanel.setCellHorizontalAlignment(table, HorizontalPanel.ALIGN_CENTER);
		
		PushButton olap = new PushButton(new Image("toolBox-images/olap.png"));
		olap.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().olap());
		olap.addClickListener(FenixToolboxController.open("olap"));
		verticalPanel.add(olap);
		verticalPanel.setCellHorizontalAlignment(olap, HorizontalPanel.ALIGN_CENTER);
		
		PushButton chart = new PushButton(new Image("toolBox-images/chartToolbox.gif"));
		chart.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().chartTB());
		chart.addClickListener(FenixToolboxController.open("chart"));
		verticalPanel.add(chart);
		verticalPanel.setCellHorizontalAlignment(chart, HorizontalPanel.ALIGN_CENTER);
		
		PushButton text = new PushButton(new Image("toolBox-images/textToolbox.gif"));
		text.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().textTB());
		text.addClickListener(FenixToolboxController.open("text"));
		verticalPanel.add(text);
		verticalPanel.setCellHorizontalAlignment(text, HorizontalPanel.ALIGN_CENTER);
		
		PushButton image = new PushButton(new Image("images/bigCamera.png"));
		image.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().image());
		image.addClickListener(FenixToolboxController.open("image"));
		verticalPanel.add(image);
		verticalPanel.setCellHorizontalAlignment(image, HorizontalPanel.ALIGN_CENTER);
		
		PushButton report = new PushButton(new Image("toolBox-images/reportToolbox.gif"));
		report.setTitle(BabelFish.print().loadOrCreateNew() + " " + BabelFish.print().reportTB());
		report.addClickListener(FenixToolboxController.open("report"));
		verticalPanel.add(report);
		verticalPanel.setCellHorizontalAlignment(report, HorizontalPanel.ALIGN_CENTER);
		
		verticalPanel.add(new Image("toolBox-images/separatorToolbox.gif"));
		
		PushButton upload = new PushButton(new Image("toolBox-images/uploadToolbox.gif"));
		upload.setTitle(BabelFish.print().newUpload());
		upload.addClickListener(FenixToolboxController.open("upload"));
		verticalPanel.add(upload);
		verticalPanel.setCellHorizontalAlignment(upload, HorizontalPanel.ALIGN_CENTER);
				
		windowToolbox.add(verticalPanel);
		// on close handler
		windowToolbox.addWindowListener(FenixToolboxController.onCloseManager(this));
		windowToolbox.show();
	}

}
