package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class DesignerLayerSelectorWindow extends FenixWindow {

	private DesignerLayerSelectorPanel designerLayerSelectorPanel;
	
	private Button okButton;
	
	private Button closeButton;
	
	private static final String WINDOW_WIDTH = "270px";

	private static final String WINDOW_HEIGHT = "330px";
	
	public DesignerLayerSelectorWindow() {
		designerLayerSelectorPanel = new DesignerLayerSelectorPanel();
	}
	
	public DesignerLayerSelectorWindow(Long layerID, String layerName) {
		designerLayerSelectorPanel = new DesignerLayerSelectorPanel(layerID, layerName);
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
		getCenter().add(designerLayerSelectorPanel.build());
		getCenter().setBottomComponent(buildButtonsPanel());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.NONE);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(5);
		okButton = new Button(BabelFish.print().ok());
		buttonsPanel.add(okButton);
		closeButton = new Button(BabelFish.print().close());
		buttonsPanel.add(closeButton);
		return buttonsPanel;
	}

	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Layer Selector Window");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("sld");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
	}

	public DesignerLayerSelectorPanel getDesignerLayerSelectorPanel() {
		return designerLayerSelectorPanel;
	}
	
}