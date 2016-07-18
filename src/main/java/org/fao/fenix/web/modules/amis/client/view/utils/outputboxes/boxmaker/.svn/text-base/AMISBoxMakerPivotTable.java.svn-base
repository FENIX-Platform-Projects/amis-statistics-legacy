package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker;

import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu.AMISBoxMenuPivotTable;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class AMISBoxMakerPivotTable extends BoxMaker{

	public static ContentPanel build(final AMISQueryVO qvo, final AMISResultVO rvo, final String width, String height) {
		if(rvo.getRows()!=null)
		{
			if(rvo.getRows()<=0)
			{
				System.out.println("AMISBoxMakerPivotTable rvo.getRows()<=0 ");
				qvo.setTitle("");
				rvo.setTitle("");
			}
			else
			{
				qvo.setTitle("Maximum " + qvo.getLimit() +" rows shown");
				rvo.setTitle("Maximum " + qvo.getLimit() +" rows shown");
			}
		}
		else
		{
			qvo.setTitle("Maximum " + qvo.getLimit() +" rows shown");
			rvo.setTitle("Maximum " + qvo.getLimit() +" rows shown");
		}
		
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.addStyleName("content_box");
		
		panel.add(AMISBoxMenuPivotTable.buildMenu(qvo, rvo, width));
		
		panel.add(FormattingUtils.addVSpace(5));

		ContentPanel tablePanel = new ContentPanel();
		tablePanel.setBodyBorder(false);
		tablePanel.setHeaderVisible(false);
		tablePanel.setWidth(width);
//		tablePanel.setSize(width, height);
		
		tablePanel.setScrollMode(Scroll.AUTO);

		// TODO: if no table, write something? call another value in the hashmap if they are not finished?
		if ( rvo.getRows() == null ) {
			tablePanel.add(new Html("<div style='padding:20px' class=''> " + rvo.getTableHTML() + "</div>"));
		}
		else {
			if ( rvo.getRows() > 0 ) {
					tablePanel.add(new Html("<div style='padding:20px' class=''> " + rvo.getTableHTML() + "</div>"));
				}
			else {
				HorizontalPanel h = new HorizontalPanel();
				h.add(FormattingUtils.addHSpace(10));
				Html html = new Html("<div class='pivot_table_empty_table'><font size=\"4\"> No data available for the current selection... </font></div>");
				//Html html2 = new Html("<h1 style=\"font-size: 20px;margin:2px 5px 5px\"> Title: </h1>");
			//	html.setHeight(300);
				h.add(html);
				//h.add(html2);
				tablePanel.add(h);
			}
		}
		
		tablePanel.layout();

		panel.add(tablePanel);
		
//		tablePanel.setWidth(width);
//		tablePanel.setHeight(height);
		

//		panel.add(chart);

//		System.out.println("rvo: " + rvo.getChartValues());
//		/** TODO: test if the values are empty **/
//		if ( rvo.getChartValues().isEmpty() ) {
//			panel.add(FAOSTATEmptyValuesPanel.build("No Data available", width, height));
//		}
//		else {
//			StringBuffer chartString = new StringBuffer();
//			chartString.append("<div>");
//			chartString.append(rvo.getSmallHtmlImageMap());
//	
//			chartString.append("</div>");
//			HTML chart = new HTML(chartString.toString());
//			HorizontalPanel h = new HorizontalPanel();
//			h.addStyleName("chart_box");
//			h.add(chart);
//			panel.add(h);
//		}
		
//		panel.add(DataViewerClientUtils.addHSpace(2));
		
		return panel;
	} 
	
	
}
