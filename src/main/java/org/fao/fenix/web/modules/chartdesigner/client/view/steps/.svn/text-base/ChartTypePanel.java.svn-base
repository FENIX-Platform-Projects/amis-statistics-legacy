package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.Image;

public class ChartTypePanel extends ChartDesignerStepPanel {
	
	private VerticalPanel wrapper;
	
	private VerticalPanel barPanel;
	
	private VerticalPanel boxplotPanel;
	
	private VerticalPanel piePanel;
	
	private VerticalPanel linePanel;
	
	private VerticalPanel pointPanel;
	
	private VerticalPanel targetPanel;
	
	private VerticalPanel barLinePanel;
	
	private Button barButton;
	
	private Button lineButton;
	
	private Button barLineButton;
	
	private Button pieButton;
	
	private Button boxplotButton;
	
	private Button pointButton;
	
	private Button targetButton;
	
	private final static int SPACING = 5;
	
	private final static String BUTTON_WIDTH = "140px";

	public ChartTypePanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new VerticalPanel();
		barPanel = new VerticalPanel();
		barPanel.setSpacing(SPACING);
		barPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		barPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		barPanel.setBorders(true);
		boxplotPanel = new VerticalPanel();
		boxplotPanel.setSpacing(SPACING);
		boxplotPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		boxplotPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		boxplotPanel.setBorders(true);
		piePanel = new VerticalPanel();
		piePanel.setSpacing(SPACING);
		piePanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		piePanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		piePanel.setBorders(true);
		linePanel = new VerticalPanel();
		linePanel.setSpacing(SPACING);
		linePanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		linePanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		linePanel.setBorders(true);
		pointPanel = new VerticalPanel();
		pointPanel.setSpacing(SPACING);
		pointPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		pointPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		pointPanel.setBorders(true);
		targetPanel = new VerticalPanel();
		targetPanel.setSpacing(SPACING);
		targetPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		targetPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		targetPanel.setBorders(true);
		barLinePanel = new VerticalPanel();
		barLinePanel.setSpacing(SPACING);
		barLinePanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		barLinePanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		barLinePanel.setBorders(true);
		barButton = new Button("Create New Bar Chart");
		barButton.setWidth(BUTTON_WIDTH);
		lineButton = new Button("Create New Line Chart");
		lineButton.setWidth(BUTTON_WIDTH);
		pieButton = new Button("Create New Pie Chart");
		pieButton.setWidth(BUTTON_WIDTH);
		boxplotButton = new Button("Create New Boxplot");
		boxplotButton.setWidth(BUTTON_WIDTH);
		pointButton = new Button("Create New Points Chart");
		pointButton.setWidth(BUTTON_WIDTH);
		barLineButton = new Button("Create New Bar/Line Chart");
		barLineButton.setWidth(BUTTON_WIDTH);
		targetButton = new Button("Create New Target Chart");
		targetButton.setWidth(BUTTON_WIDTH);
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper.add(buildTopRow());
		wrapper.add(buildBottomRow());
		return wrapper;
	}
	
	private HorizontalPanel buildTopRow() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		barPanel = buildChartPanel(barPanel, "images/barChartBig.png", "BAR");
		linePanel = buildChartPanel(linePanel, "images/lineChartBig.png", "LINE");
		pointPanel = buildChartPanel(pointPanel, "images/pointsChartBig.png", "POINT");
		targetPanel = buildChartPanel(targetPanel, "images/targetsChartBig.png", "TARGET");
		piePanel = buildChartPanel(piePanel, "images/pieChartBig.png", "PIE");
		piePanel.setEnabled(false);
		p.add(barPanel);
		p.add(linePanel);
		p.add(pointPanel);
		p.add(targetPanel);
		p.add(piePanel);
		return p;
	}
	
	private HorizontalPanel buildBottomRow() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		boxplotPanel = buildChartPanel(boxplotPanel, "images/boxplotBig.png", "BOXPLOT");
		boxplotPanel.setEnabled(false);
		p.add(boxplotPanel);
		return p;
	}
	
	private VerticalPanel buildChartPanel(VerticalPanel p, String image, String chartType) {
		Image i = new Image(image);
		i.setWidth("110px");
		i.setHeight("110px");
		Html title = createTitle(chartType);
		p.add(title);
		p.add(i);
		p.add(createButton(chartType));
		return p;
	}
	
	private Html createTitle(String chartType) {
		Html title = null;
		if (chartType.equalsIgnoreCase("BAR")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Bar Chart</H1></div>");
		} else if (chartType.equalsIgnoreCase("LINE")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Line Chart</H1></div>");
		} else if (chartType.equalsIgnoreCase("BARLINE")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Bar/Line Chart</H1></div>");
		} else if (chartType.equalsIgnoreCase("PIE")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Pie Chart</H1></div>");
		} else if (chartType.equalsIgnoreCase("BOXPLOT")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Boxplot</H1></div>");
		} else if (chartType.equalsIgnoreCase("POINT")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Points Chart</H1></div>");
		} else if (chartType.equalsIgnoreCase("TARGET")) {
			title = new Html("<div align='center' style='font-family: sans-serif; color: #1D4589; font-size: 8pt; '><H1>Targets Chart</H1></div>");
		}
		return title;
	}
	
	private Button createButton(String chartType) {
		if (chartType.equalsIgnoreCase("BAR")) {
			return barButton;
		} else if (chartType.equalsIgnoreCase("LINE")) {
			return lineButton;
		} else if (chartType.equalsIgnoreCase("BARLINE")) {
			return barLineButton;
		} else if (chartType.equalsIgnoreCase("PIE")) {
			return pieButton;
		} else if (chartType.equalsIgnoreCase("BOXPLOT")) {
			return boxplotButton;
		} else if (chartType.equalsIgnoreCase("POINT")) {
			return pointButton;
		} else if (chartType.equalsIgnoreCase("TARGET")) {
			return targetButton;
		}
		return null;
	}

	public Button getBarButton() {
		return barButton;
	}

	public Button getLineButton() {
		return lineButton;
	}

	public Button getBarLineButton() {
		return barLineButton;
	}

	public Button getPieButton() {
		return pieButton;
	}

	public Button getBoxplotButton() {
		return boxplotButton;
	}

	public Button getPointButton() {
		return pointButton;
	}

	public Button getTargetButton() {
		return targetButton;
	}
	
}