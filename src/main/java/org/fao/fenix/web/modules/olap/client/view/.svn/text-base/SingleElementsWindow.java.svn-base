package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableCellVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableRowVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class SingleElementsWindow extends FenixWindow {

	private VerticalPanel wrapper;
	
	private static String COLOR_ONE = "#D0DDED";
	
	private static String COLOR_TWO = "#1D4589";
	
	public SingleElementsWindow() {
		wrapper = new VerticalPanel();
	}
	
	public void build(UDTableVO table) {
		buildCenterPanel(table);
		format();
		show();
	}
	
	private void buildCenterPanel(UDTableVO table) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		wrapper.add(createHTML(table));
		getCenter().add(wrapper);
		addCenterPartToWindow();
	}
	
	private HTML createHTML(UDTableVO table) {
		HTML html = new HTML("");
		String s = "<table width='100%' border='1'>";
		s += createHeaders(table);
		for (int i = 0 ; i < table.getRows().size() ; i++) {
			UDTableRowVO row = table.getRows().get(i);
			s += "<tr>";
			for (UDTableCellVO cell : row.getCells()) 
				s += "<td><div align='center' style='font-size: 8pt; font-family:sans-serif; '>" + cell.getValue() + "</div></td>";
			s += "</tr>";
		}
		html.setHTML(s);
		return html;
	}
	
	private String createHeaders(UDTableVO table) {
		String s = "<tr>";
		for (UDTableFilterVO f : table.getFilters())
			s += "<td><div align='center' style='font-size: 10pt; font-family:sans-serif; color: #1D4589; font-weight: bold; '>" + f.getHeader() + "</div></td>";
		s += "</tr>";
		return s;
	}
	
	private void format() {
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setSpacing(10);
		setSize("500px", "300px");
		getWindow().setHeading(BabelFish.print().singleElements());
	}
	
}