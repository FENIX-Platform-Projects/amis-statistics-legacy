package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.olap.client.control.MT2ChartDesign;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.ListBox;

public class MT2ChartDesignerWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "275px";
	
	private static final String WINDOW_HEIGHT = "155px";
	
	private static final Integer SPACING = 5;
	
	private static final String FIELD_WIDTH = "250px";
	
	private ListBox xAxis;
	
	private ListBox yAxis;
	
	private Button createChartButton;
	
	private String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	public MT2ChartDesignerWindow() {
		xAxis = new ListBox();
		yAxis = new ListBox();
		xAxis.setWidth(FIELD_WIDTH);
		yAxis.setWidth(FIELD_WIDTH);
		createChartButton = new Button("Create Chart");
		createChartButton.setIconStyle("workspaceChart");
		createChartButton.setWidth(FIELD_WIDTH);
	}
	
	public void build(OLAPParametersVo pvo) {
		buildCenterPanel(pvo);
		format();
		show();
	}
	
	private void buildCenterPanel(OLAPParametersVo pvo) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildWrapper(pvo));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildWrapper(OLAPParametersVo pvo) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		fill(xAxis, pvo, "X");
		fill(yAxis, pvo, "Y");
		createChartButton.addSelectionListener(MT2ChartDesign.mt2chartDesignerWindow(this, pvo));
		Html lbl_1 = new Html(TITLE + "Please Select a Dimension for the X-Axis</div>");
		Html lbl_2 = new Html(TITLE + "Please Select a Dimension for the Y-Axis</div>");
		p.add(lbl_1);
		p.add(xAxis);
		p.add(lbl_2);
		p.add(yAxis);
		p.add(createChartButton);
		return p;
	}
	
	private ListBox fill(ListBox l, OLAPParametersVo pvo, String axis) {
		l.addItem(pvo.getXLabel(), pvo.getX());
		l.addItem(pvo.getZLabel(), pvo.getZ());
		if (pvo.getWLabel().length() > 0); 
			l.addItem(pvo.getWLabel(), pvo.getW());
		if (pvo.getYLabel().length() > 0);
			l.addItem(pvo.getYLabel(), pvo.getY());
		for (int i = l.getItemCount() - 1 ; i >= 0 ; i--) {
			try {
				if (l.getItemText(i).length() < 1)
					l.removeItem(i);
				if (axis.equals("X") && (l.getValue(i).contains("date") || l.getValue(i).contains("Date")))
					l.setItemSelected(i, true);
			} catch (IndexOutOfBoundsException e) {
//				System.out.println("IndexOutOfBoundsException for " + i);
			}
		}
		return l;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Multidimensional Table To Chart");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("olap");
		getWindow().setCollapsible(false);
//		getWindow().setModal(true);
	}

	public ListBox getxAxis() {
		return xAxis;
	}

	public ListBox getyAxis() {
		return yAxis;
	}
	
}