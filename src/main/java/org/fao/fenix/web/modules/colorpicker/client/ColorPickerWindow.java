package org.fao.fenix.web.modules.colorpicker.client;

import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class ColorPickerWindow extends FenixWindow {
	
	private static final String WINDOW_WIDTH = "400px";
	
	private static final String WINDOW_HEIGHT = "315px";
	
	private ColorPicker colorPicker;
	
	private Button button;
	
	private HTML html;
	
	public ColorPickerWindow(HTML html) {
		colorPicker = new ColorPicker();
		button = new Button("Done, Pick This Color!");
		this.setHtml(html);
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
		getCenter().add(colorPicker);		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenter().setBottomComponent(button);
		getCenterData().setSize(300);
		addCenterPartToWindow();
		button.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String hex = colorPicker.getHexColor();
				html.setHTML("<div align='center' style='border: 4px black solid; background-color: #" + hex + "; color:  #" + hex + "; font-weight: bold; '>#" + hex + "</div>");
				getWindow().close();
			};
		});
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Color Picker");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("sld");
		getWindow().setCollapsible(false);
	}

	public void setHtml(HTML html) {
		this.html = html;
	}
	
}