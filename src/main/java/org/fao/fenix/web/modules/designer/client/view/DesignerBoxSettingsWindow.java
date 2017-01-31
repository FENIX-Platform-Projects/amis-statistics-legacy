package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.designer.client.control.DesignerController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class DesignerBoxSettingsWindow extends FenixWindow {

	private DesignerBoxSettings designerBoxSettings;
	
	private static final String WINDOW_WIDTH = "350px";
	
	private static final String WINDOW_HEIGHT = "500px";
	
	public DesignerBoxSettingsWindow(DesignerBoxSettings designerBoxSettings) {
//		designerBoxSettings = new DesignerBoxSettings();
		this.setDesignerBoxSettings(designerBoxSettings);
	}
	
	public void build(boolean addCaption) {
		buildCenterPanel(addCaption);
		format();
		show();
		designerBoxSettings.getOkButton().addSelectionListener(DesignerController.close(this));
		designerBoxSettings.getPanel().setSize("360px", "490px");
		setSize("375px", "525px");
	}
	
	private void buildCenterPanel(boolean addCaption) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(designerBoxSettings.build(addCaption));
		designerBoxSettings.getPanel().getLayout().layout();
		getCenter().setSize("335px", "470px");
		getCenter().setScrollMode(Scroll.NONE);
		getCenter().setBodyBorder(false);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Style Editor");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("sld");
//		getWindow().setCollapsible(false);
	}

	public DesignerBoxSettings getDesignerBoxSettings() {
		return designerBoxSettings;
	}

	public void setDesignerBoxSettings(DesignerBoxSettings designerBoxSettings) {
		this.designerBoxSettings = designerBoxSettings;
	}
	
}