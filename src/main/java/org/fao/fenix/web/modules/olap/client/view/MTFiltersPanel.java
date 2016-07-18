package org.fao.fenix.web.modules.olap.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.ListBox;

public class MTFiltersPanel extends MTStepPanel {
	
	private VerticalPanel wrapper;
	
	private String WIDTH = "835px";
	
	private String HEIGHT = "535px";
	
	private final static int SPACING = 5;
	
	private final static int VISIBLE_ITEMS = 10;
	
	private final static String FIELD_WIDTH = "395px";
	
	private final static String BUTTONS_WIDTH = "198px";
	
	private List<VerticalPanel> panels;

	public MTFiltersPanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new VerticalPanel();
		wrapper.setSize(WIDTH, HEIGHT);
		wrapper.setScrollMode(Scroll.AUTO);
		panels = new ArrayList<VerticalPanel>();
		this.getLayoutContainer().add(wrapper);
	}
	
	public VerticalPanel buildWrapper(DatasetVO dvo) {
		int counter = 0;
		List<DescriptorViewVO> dvvos = new ArrayList<DescriptorViewVO>();
		for (DescriptorViewVO dvvo : dvo.getDescriptorViews()) {
			dvvos.add(dvvo);
			counter++;
			if (counter == 2) {
				wrapper.insert(buildFilter(dvvos), 0);
				counter = 0;
				dvvos = new ArrayList<DescriptorViewVO>();
			}
		}
		return wrapper;
	}
	
	private HorizontalPanel buildFilter(List<DescriptorViewVO> dvvos) {
		HorizontalPanel w = new HorizontalPanel();
		List<VerticalPanel> dimensionPanels = new ArrayList<VerticalPanel>();
		for (DescriptorViewVO dvvo : dvvos) {
			if (!dvvo.getContentDescriptor().equalsIgnoreCase("quantity")) {
				VerticalPanel vp = buildDimension(dvvo); 
				w.add(vp);
				dimensionPanels.add(vp);
			}
		}
		return w;
	}
	
	private VerticalPanel buildDimension(DescriptorViewVO dvvo) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		Html l = new Html("<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>" + dvvo.getHeader() + "</div>");
		p.add(l);
		ListBox list = new ListBox(true);
		list.setVisibleItemCount(VISIBLE_ITEMS);
		list.setWidth(FIELD_WIDTH);
		if (dvvo.getValues() != null)
			for (UniqueValueVO uvvo : dvvo.getValues())
				if (!MTController.containsLabel(list, uvvo.getLabel()))
					list.addItem(uvvo.getLabel(), uvvo.getCode());
		p.add(list);
		p.add(buildButtonsPanel(list));
		p.setData("LIST", list);
		p.setData("HEADER", dvvo.getHeader());
		p.setData("CONTENT_DESCRIPTOR", dvvo.getContentDescriptor());
		panels.add(p);
		return p;
	}
	
	private HorizontalPanel buildButtonsPanel(ListBox list) {
		HorizontalPanel p = new HorizontalPanel();
		Button selectAllButton = new Button("Select All");
		selectAllButton.setWidth(BUTTONS_WIDTH);
		selectAllButton.addSelectionListener(ChartDesignerController.selectAll(list, true));
		Button deSelectAllButton = new Button("Deselect All");
		deSelectAllButton.setWidth(BUTTONS_WIDTH);
		deSelectAllButton.addSelectionListener(ChartDesignerController.selectAll(list, false));
		p.add(selectAllButton);
		p.add(deSelectAllButton);
		return p;
	}

	public List<VerticalPanel> getPanels() {
		return panels;
	}
	
}