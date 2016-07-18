package org.fao.fenix.web.modules.haiticnsatool.client.view;



import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;


public class HaitiCNSAChartPanel {
	
	private Html title;
	
	private VerticalPanel panel;
	
	private ContentPanel chartPanel;
	
	private Button  exportChart;
	
	String width;

	String height;
	
	String language;
	
	String panelHeight = "500px";
	
	String panelChartHeight = "300px";
		
	public HaitiCNSAChartPanel() {
		panel = new VerticalPanel();
		panel.setSpacing(5);
		exportChart = new Button();
		chartPanel = new ContentPanel();
		chartPanel.setHeaderVisible(false);
		chartPanel.setBodyBorder(false);
		title = new Html();
	}
	
	public VerticalPanel build(String language, String width, String height) {
		this.width = width;
		this.language = language;
		this.height = height;
		
		panel.add(buildTitle(language));
		panel.add(buildChartPanel(language, width));
//		panel.add(buildButtons(language));

		return panel;
	}
	
	private HorizontalPanel buildTitle(String language){
		HorizontalPanel h = new HorizontalPanel();
		h.add(title);
		return h;
	}
	
	private HorizontalPanel buildButtons(String language){
		HorizontalPanel h = new HorizontalPanel();
		exportChart.setText("Export Chart");
//		exportChart.addSelectionListener(HaitiCNSAController.
		h.add(exportChart);
		return h;
	}
	
	private ContentPanel buildChartPanel(String language, String width){
		chartPanel.setWidth(width);
		chartPanel.setHeight(panelHeight);
		return chartPanel;
	}

	public VerticalPanel getPanel() {
		return panel;
	}

	public ContentPanel getChartPanel() {
		return chartPanel;
	}

	public Button getExportChart() {
		return exportChart;
	}

	public String getHeight() {
		return height;
	}

	public String getLanguage() {
		return language;
	}

	public String getWidth() {
		return width;
	}

	public String getPanelChartHeight() {
		return panelChartHeight;
	}

	public Html getTitle() {
		return title;
	}
	


	
}
