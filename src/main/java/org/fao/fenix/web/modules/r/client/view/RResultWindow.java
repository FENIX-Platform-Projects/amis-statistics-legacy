package org.fao.fenix.web.modules.r.client.view;

import org.fao.fenix.web.modules.r.common.vo.RResultVO;
import org.fao.fenix.web.modules.r.common.vo.RUserSettingsVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class RResultWindow extends FenixWindow {

	private RResultPanel rResultPanel;
	
	private static final String WINDOW_WIDTH = "475px";
	
	private static final String WINDOW_HEIGHT = "550px";
	
	public RResultWindow() {
		rResultPanel = new RResultPanel();
	}
	
	public void build(RResultVO rvo, RUserSettingsVO usvo) {
		buildCenterPanel(rvo, usvo);
		format(rvo);
		show();
	}
	
	private void buildCenterPanel(RResultVO rvo, RUserSettingsVO usvo) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(rResultPanel.build(rvo, usvo));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format(RResultVO rvo) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//		getWindow().setHeading(I18N.print().getString(vo.getCommand()));
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("R");
		getWindow().setCollapsible(false);
	}

	public RResultPanel getrResultPanel() {
		return rResultPanel;
	}
	
}
