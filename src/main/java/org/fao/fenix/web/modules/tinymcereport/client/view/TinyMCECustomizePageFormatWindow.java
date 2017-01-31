package org.fao.fenix.web.modules.tinymcereport.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEReportGalleryController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class TinyMCECustomizePageFormatWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "250px";
	
	private static final String FIELD_WIDTH = "225px";
	
	private static final String WINDOW_HEIGHT = "275px";
	
	private static final String STYLE = "<div style='font-family: sans-serif; font-weight: bold;'>"; 
	
	private static final int SPACING = 5;
	
	private ListBox formatList;
	
	private TextField<String> width;
	
	private TextField<String> height;
	
	private TextField<String> pages;
	
	private Button okButton;
	
	private TinyMCEReportGalleryWindow tinyMCEReportGalleryWindow; 
	
	public TinyMCECustomizePageFormatWindow(TinyMCEReportGalleryWindow tinyMCEReportGalleryWindow) {
		this.setTinyMCEReportGalleryWindow(tinyMCEReportGalleryWindow);
		formatList = new ListBox();
		width = new TextField<String>();
		height = new TextField<String>();
		pages = new TextField<String>();
		okButton = new Button("OK");
		okButton.addSelectionListener(TinyMCEReportGalleryController.customizePage(this));
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildPanel());
		getCenter().setBottomComponent(okButton);
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		HTML lbl1 = new HTML(STYLE + "Available Formats</div>");
		lbl1.setWidth(FIELD_WIDTH);
		fillFormatList();
		formatList.setWidth(FIELD_WIDTH);
		formatList.addChangeHandler(TinyMCEReportGalleryController.pageFormat(this));
		HTML lbl2 = new HTML(STYLE + "Page Width <i>(mm)</i></div>");
		lbl2.setWidth(FIELD_WIDTH);
		width.setWidth(FIELD_WIDTH);
		width.setValue("");
		HTML lbl3 = new HTML(STYLE + "Page Height <i>(mm)</i></div>");
		lbl3.setWidth(FIELD_WIDTH);
		height.setWidth(FIELD_WIDTH);
		height.setValue("");
		HTML lbl4 = new HTML(STYLE + "Number of Pages</div>");
		lbl4.setWidth(FIELD_WIDTH);
		pages.setWidth(FIELD_WIDTH);
		pages.setValue("1");
		p.add(lbl1);
		p.add(formatList);
		p.add(lbl2);
		p.add(width);
		p.add(lbl3);
		p.add(height);
		p.add(lbl4);
		p.add(pages);
		return p;
	}
	
	private void fillFormatList() {
		formatList.addItem("Please Select...", "");
		formatList.addItem("A4", "A4");
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Customize Page Format");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("reportIcon");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
	}

	public TinyMCEReportGalleryWindow getTinyMCEReportGalleryWindow() {
		return tinyMCEReportGalleryWindow;
	}

	public void setTinyMCEReportGalleryWindow(
			TinyMCEReportGalleryWindow tinyMCEReportGalleryWindow) {
		this.tinyMCEReportGalleryWindow = tinyMCEReportGalleryWindow;
	}

	public ListBox getFormatList() {
		return formatList;
	}

	public TextField<String> getWidth() {
		return width;
	}

	public TextField<String> getHeight() {
		return height;
	}

	public TextField<String> getPages() {
		return pages;
	}

	public Button getOkButton() {
		return okButton;
	}
	
}