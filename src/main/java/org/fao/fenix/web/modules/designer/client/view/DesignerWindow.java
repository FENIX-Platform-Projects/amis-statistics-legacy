package org.fao.fenix.web.modules.designer.client.view;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Window;

public class DesignerWindow extends FenixWindow {

	private DesignerTabPanel designerTabPanel;
	
	private DesignerToolbar designerToolbar;
	
	private static final String WINDOW_WIDTH = "450px";
	
	private static final String WINDOW_HEIGHT = "500px";
	
	private Long reportID;
	
	private String reportTitle;
	
	public DesignerWindow() {
		designerTabPanel = new DesignerTabPanel();
		designerToolbar = new DesignerToolbar();
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
		this.getWindow().setPagePosition((Window.getClientWidth() - 50 - this.getWindow().getWidth()), this.getWindow().getAbsoluteTop());
	}
	
	public void build(Long reportID, String reportTitle, Map<String, List<ResourceViewVO>> map) { // TODO Load Existing Report
		
//		System.out.println("DesignerWindow - DesignerWindow - DesignerWindow - DesignerWindow - DesignerWindow");
//		for (String key : map.keySet()) {
//			System.out.println("\t" + key);
//			List<ResourceViewVO> l = map.get(key);
//			for (ResourceViewVO r : l) {
//				System.out.println("\t\t" + r.getType() + " - " + r.getQuery());
//				List<ResourceViewSettingVO> ll = r.getSettings();
//				for (ResourceViewSettingVO rr : ll) {
//					System.out.println("\t\t\t" + rr.getSettingName() + " | " + rr.getValue());
//				}
//			}
//		}
//		System.out.println("DesignerWindow - DesignerWindow - DesignerWindow - DesignerWindow - DesignerWindow");
		
		this.setReportID(reportID);
		this.setReportTitle(reportTitle);
		buildCenterPanel(map);
		format(reportTitle);
		show();
		this.getWindow().setPagePosition((Window.getClientWidth() - 50 - this.getWindow().getWidth()), this.getWindow().getAbsoluteTop());
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().setTopComponent(designerToolbar.build(this));
		getCenter().add(designerTabPanel.build());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.NONE);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void buildCenterPanel(Map<String, List<ResourceViewVO>> map) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().setTopComponent(designerToolbar.build(this));
		getCenter().add(designerTabPanel.build(map));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.NONE);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Report Designer");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("reportIcon");
		getWindow().setCollapsible(false);
	}
	
	private void format(String reportTitle) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading(reportTitle);
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("reportIcon");
		getWindow().setCollapsible(false);
	}

	public DesignerTabPanel getDesignerTabPanel() {
		return designerTabPanel;
	}

	public DesignerToolbar getDesignerToolbar() {
		return designerToolbar;
	}

	public Long getReportID() {
		return reportID;
	}

	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	
}
