package org.fao.fenix.web.modules.excelimporter.client.view;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class ImageImporterStepC extends ExcelImporterStepPanel {

private VerticalPanel wrapper;
	
	private final static String TITLE_PANEL_WIDTH = "560px";
	
	private final static String TITLE_WIDTH = "550px";
	
	private PushButton image;
	
	private PushButton addToReport;
	
	public ImageImporterStepC(String suggestion, String width) {
		super(suggestion, width);
		this.getLayoutContainer().add(buildWrapper(false));
	}
	
	public ImageImporterStepC(String suggestion, String width, boolean isAddToReport) {
		super(suggestion, width);
		this.getLayoutContainer().add(buildWrapper(isAddToReport));
	}
	
	@Override
	public HorizontalPanel buildSuggestionPanel(String suggestion, String width) {
		HorizontalPanel suggestionPanel = new HorizontalPanel();
		suggestionPanel.setSpacing(10);
		suggestionPanel.setBorders(true);
		HTML suggestionHTML = new HTML("<font color='green'>Your image has been uploaded. You can now use it within the Workstation.</font>");
		suggestionPanel.add(suggestionHTML);
		return suggestionPanel;
	}
	
	private VerticalPanel buildWrapper(boolean isAddToReport) {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.add(buildTitlePanel("Open Your Image"));
		wrapper.add(buildOpenAsPanel(isAddToReport));
		wrapper.setHorizontalAlign(HorizontalAlignment.CENTER);
		return wrapper;
	}
	
	private HorizontalPanel buildTitlePanel(String text) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setWidth(TITLE_PANEL_WIDTH);
		Html label = new Html("<H1>" + text + "</H1>");
		label.setWidth(TITLE_WIDTH);
		p.add(label);
		return p;
	}
	
	private HorizontalPanel buildOpenAsPanel(boolean isAddToReport) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		p.setWidth(TITLE_PANEL_WIDTH);
		image = new PushButton(new Image("images/bigCamera.png"));
		image.setTitle("Image");
		p.add(image);
		if (isAddToReport) {
			addToReport = new PushButton(new Image("images/send_to_report.png"));
			addToReport.setTitle("Add To Current Report");
			p.add(addToReport);
		}
		return p;
	}

	public PushButton getImage() {
		return image;
	}

	public PushButton getAddToReport() {
		return addToReport;
	}
	
}