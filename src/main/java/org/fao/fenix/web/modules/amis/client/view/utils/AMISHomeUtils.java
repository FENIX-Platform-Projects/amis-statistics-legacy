package org.fao.fenix.web.modules.amis.client.view.utils;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.menu.AMISMenuController;
import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu.AMISBoxMenuFlatTable;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.constants.AMISInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.VerticalPanel;


public class AMISHomeUtils {
	 public static ContentPanel getHtmlFootnoteTable(/*AMISResultVO rvo, Boolean showColumnHeaders,*/ String style, String worldCode) {
			System.out.println("#####################################################################################Start "+worldCode);
			ContentPanel panel = new ContentPanel();
			//panel.setBorders(false);
			panel.setBodyBorder(false);
			panel.setHeaderVisible(false);
			panel.setStyleAttribute("backgroundColor", "#FFFFFF");
//			panel.addStyleName("content_box");
			panel.setId(AMISConstants.FOOTNOTE_TABLE.toString());
			HorizontalPanel p = new HorizontalPanel();
			p.setHorizontalAlign(HorizontalAlignment.CENTER);
			p.setVerticalAlign(VerticalAlignment.BOTTOM);
			p.setStyleAttribute("backgroundColor", "#FFFFFF");
			//p.add(buildMenu(AMISInterfaceConstants.TABLE_WIDTH));
			p.add(buildMenu("1080px"));
			panel.add(p);
			ContentPanel tablePanel = new ContentPanel();
			tablePanel.setBodyBorder(false);
			tablePanel.setHeaderVisible(false);
			//tablePanel.setBorders(false);
		//	tablePanel.setWidth(AMISInterfaceConstants.TABLE_WIDTH);
			tablePanel.setStyleAttribute("backgroundColor", "#FFFFFF");
			
//			ContentPanel cp = new ContentPanel();
//			cp.setHeaderVisible(false);
//			cp.setBorders(true);
//			cp.setWidth("1080px");
//		
//			VerticalPanel vp = new VerticalPanel();
//		//	HorizontalPanel vp = new HorizontalPanel();
//			vp.setHorizontalAlignment(vp.ALIGN_CENTER);
//			StringBuffer html = new StringBuffer();
//			html.append("<table width='1080px' background='#FFFFFF' border='1' bordercolor='gray'>");
//		    html.append("<TR  bgcolor='#FFFFFF'>");  class
//			html.append("<TD bgcolor='#FFFFFF' nowrap><center><font size='10px' color='blue'>No data available for the current selection</font></center></TD>");
//			html.append("</TR>");
//			html.append("</table>");
			
			
			
			StringBuffer html = new StringBuffer();
			html.append("<p><table width='1080px' class='"+style+"' align='center' border='1'>");
			int columns = 2;
			
//			html.append("<TR>");
////			//empty first column
//			html.append("<TH class='"+ style +"_title' nowrap><center>" + "Column1" + "</TH>");
//			html.append("<TH class='"+ style +"_title' nowrap><center>" + "Column3" + "</TH>");
//			html.append("</TR>");
			// the data
			//
			html.append("<tbody>");
			if((worldCode!=null)&&(worldCode.equals("999000")))
			{
				//Global
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[1]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Production\" (except for soybeans) refers to the first year of the split year shown. Rice production is expressed in milled terms." + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[2]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Domestic Supply\" equals production plus opening stocks." + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[3]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Trade\" refers to the following export seasons: <br>  - July/June for wheat and maize <br> - October/September for soybean<br> - January/December (calendar) for rice (corresponding to second year of the split year shown).<br><br> For the forecast year, world exports and imports are always assumed to be equal." + "</TD>");
				//html.append("<TD nowrap align=\"left\">" + "\"Trade\" refers to the following export seasons: July/June for wheat and maize, October/September for soybean and January/December (calendar) for rice (corresponding to second year of the split year shown).For the forecast year, world exports and imports are always assumed to be equal." + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[4]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Closing Stocks\" may not equal the difference between supply and utilization due to differences in individual national marketing years." + "</TD>");
				html.append("</TR>");
			}
			else
			{
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[1]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Production\" refers to the full amount of the harvest before any deductions are taken for post-harvest losses, seed use, etc. " + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[2]" + "</center></TD>");
//				html.append("<TD nowrap align=\"left\">" + "\"Domestic Availability\" refers to Opening Stocks (quantity of stocks held at the beginning of the marketing year) plus Production.  " + "</TD>");
                html.append("<TD nowrap align=\"left\">" + "\"Domestic Supply\" refers to Opening Stocks (quantity of stocks held at the beginning of the marketing year) plus Production.  " + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[3]" + "</center></TD>");
//				html.append("<TD nowrap align=\"left\">" + "\"Domestic Utilization\" includes food, feed and other uses." + "</TD>");
                html.append("<TD nowrap align=\"left\">" + "\"Domestic Utilization\" includes food, feed and other uses." + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[4]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Trade (imports and/or exports)\" is based on the national marketing year (NMY)." + "</TD>");
				html.append("</TR>");
				html.append("<TR class='"+ style +"_row1' align='center'>");
				html.append("<TD nowrap><center>" + "[5]" + "</center></TD>");
				html.append("<TD nowrap align=\"left\">" + "\"Closing stocks\" refers to stocks at the end of the marketing year." + "</TD>");
				html.append("</TR>");
			}
			
//			html.append("<TR class='"+ style +"_row1' align='center'>");
//			html.append("<TD ><center>" + "[1]" + "</center></TD>");
//			html.append("<TD ><center>" + "Descrioption 1" + "</center></TD>");
//			html.append("</TR>");
//			html.append("<TR class='"+ style +"_row1' align='center'>");
//			html.append("<TD ><center>" + "[2]" + "</center></TD>");
//			html.append("<TD ><center>" + "Descrioption 2" + "</center></TD>");
//			html.append("</TR>");
//			html.append("<TR class='"+ style +"_row1' align='center'>");
//			html.append("<TD ><center>" + "[3]" + "</center></TD>");
//			html.append("<TD ><center>" + "Descrioption 3" + "</center></TD>");
//			html.append("</TR>");
//			html.append("<TR class='"+ style +"_row1' align='center'>");
//			html.append("<TD ><center>" + "[4]" + "</center></TD>");
//			html.append("<TD ><center>" + "Descrioption 4" + "</center></TD>");
//			html.append("</TR>");
//			html.append("</table></p>");
			
			html.append("</tbody>");
			System.out.println("Html to string "+html.toString());
			tablePanel.add(new Html(html.toString()));
			
			//cp.add(vp);
			//return cp;
			panel.add(tablePanel);
			return panel;
//			if(rvo.getTableHeaders()== null && rvo.getTableContents()==null  ){
//				StringBuffer html = new StringBuffer();
//				html.append("<table width='1080px' background='#FFFFFF' border='1' bordercolor='gray'>");
//			    html.append("<TR  bgcolor='#FFFFFF'>");
//				html.append("<TD bgcolor='#FFFFFF' nowrap><center><font size='10px' color='blue'>No data available for the current selection</font></center></TD>");
//				html.append("</TR>");
//				html.append("</table>");
	//
//				rvo.setTableHTML(html.toString());
//				rvo.setRows(Long.valueOf(0));
//			}
//			else {
//				int rowCount = 0;
//				StringBuffer html = new StringBuffer();
//				html.append("<p><table width='1080px' class='"+style+"'>");
	//
//				LOGGER.info("getTableContents size: " + rvo.getTableContents().size());
	//
//				int columnCount = rvo.getTableHeaders().size();
//				int columns = columnCount + 2;
	//
//				// colgroup
//				html.append("<colgroup>");
//				html.append("<col class='"+ style +"_col1'>");
//				for(int i = 2; i <columns; i++) {
//					//html.append("<col class='"+ style +"_col" + i +"'>");
//					html.append("<col class='"+ style +"_col'>");
//				}
//				html.append("</colgroup>");
	//
//				// table header
//				if ( showColumnHeaders ) {
//					html.append("<TR>");
//					//empty first column
//					html.append("<TH class='"+ style +"_header_col1' nowrap />");
	//
//					for (int i = 0; i < columnCount; i++) {
//						if(i == columnCount-1)
//							html.append("<TH class='"+ style +"_header_last' nowrap>" + rvo.getTableHeaders().get(i) + " (forecast)</TH>");
//						else
//							html.append("<TH class='"+ style +"_header' nowrap>" + rvo.getTableHeaders().get(i) + "</TH>");
//					}
//					html.append("</TR>");
//				}
//				if ( rvo.getMeasurementUnit()!=null ) {
//					html.append("<TR>");
//					//empty first column
//					html.append("<TD class='"+ style +"_row1' nowrap />");
//					html.append("<TD colspan='"+columnCount+"' class='"+ style +"_mu_row' nowrap><center><i>" + rvo.getMeasurementUnit() + "</i></center></TD>");
//					html.append("</TR>");
//				}
//				// the data
	//
//				html.append("<tbody>");
//				for (int j = 0; j < rvo.getTableContents().size(); j++) {
	//
//					rowCount++;
//					if ( rowCount % 2 == 0)
//						html.append("<TR class='"+ style +"_row1'>");
//					else
//						html.append("<TR class='"+ style +"_row2'>");
	//
//					List<String> content =rvo.getTableContents().get(j);
	//
//					for (int k = 0; k < content.size(); k++) {
//						if(k == 0)
//							html.append("<TD nowrap><b>" + content.get(k) + "</b></TD>");
//						else
//							html.append("<TD nowrap><center>" + content.get(k) + "</center></TD>");
//					}
//					html.append("</TR>");
//				}
//				html.append("</table></P>");
	//
//				LOGGER.info("ROWCOUNT:" + rowCount);
//				LOGGER.info("TABLE:" + html.toString());
	//
	//
//				rvo.setTableHTML(html.toString());
//				rvo.setRows(Long.valueOf(rowCount));
//			}

		}

	 public static ContentPanel buildMenu(/*AMISQueryVO qvo, AMISResultVO rvo,*/ String width){ 
			
			// main panel
			ContentPanel panel = new ContentPanel();
					
			panel.setHeaderVisible(false);
			panel.setBorders(false);
			panel.setBodyBorder(false);
			
			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleName("table_top_panel");
			
			panel.setWidth(width);
		
			HorizontalPanel panelL = new HorizontalPanel();		
			HorizontalPanel panelR = new HorizontalPanel();	
				
			panelL.addStyleName("content_chart_top_panel_left");
			panelR.addStyleName("content_chart_top_panel_right");

	  	//    panelR.add(buildButtons(qvo, rvo));
			panelR.add(statisticalNotesLink());
			panelL.add(buildTitle(/*qvo, rvo,*/ "table_title")); 

			//panelR.add(buildButtons(qvo, rvo));
//			panelL.add(buildTitle(qvo, rvo, "content_title")); 

			
			toolBar.add(panelL);
			toolBar.add(new FillToolItem());  
			toolBar.add(panelR);
			
			//panel.setTopComponent(toolBar);
			panel.add(toolBar);
			
			return panel;
		}
	 
	 public static HorizontalPanel buildTitle(/*AMISQueryVO qvo, AMISResultVO rvo,*/ String titleStyle) {
			HorizontalPanel panel = new HorizontalPanel();
			
			panel.setHorizontalAlign(HorizontalAlignment.LEFT);
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
			String title = "Definitions";
			panel.add(new Html("<div class='" + titleStyle + "'> " + title + " </div>"));
			
			return panel;
			}
	 
//	 public static ContentPanel statisticalNotesLink()
//	 {
//			ContentPanel panel = new ContentPanel();
//			panel.setStyleName("table_top_panel");
//			panel.setHeaderVisible(false);
//			panel.setBorders(false);
//			panel.setBodyBorder(false);
//			HorizontalPanel hp = new HorizontalPanel();
//			hp.setStyleName("statistical_notes_link");
//			
//			hp.setHorizontalAlign(HorizontalAlignment.RIGHT);
//			hp.setVerticalAlign(VerticalAlignment.MIDDLE);
//		
//		//	hp.add(new Html("<div class='" + "selector-hyperlink a" + "'> " + "Statistical Notes" + " </div>"));
//			Html firstPart = new Html(AMISInterfaceConstants.STATISTICAL_NOTES_LINK_FIRST_PART);
//			firstPart.setStyleName("statistical_notes_link");
//			//firstPart.setStyleAttribute("background-color", "#DC3018");
//			hp.add(firstPart);
//			HorizontalPanel space = FormattingUtils.addHSpace(5);
//			//space.setStyleName("statistical_notes_link");
////			space.setStyleAttribute("background-color", "#DC3018");
//			hp.add(space);
//			//ClickHtml html = new ClickHtml();
//			ClickHtml html = new ClickHtml();
//			//<div class="left" id="FAOSTAT-LOGO"><a href="#HOME"><img src="dataviewer-images/faostat_logo.png"></a></img></div>
//		//	html.setHtml("<a style=\"float:right; color:white; padding-right:5px;\"  href=\"#STATISTICALNOTES\">" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</a></div>");
//		//	html.setHtml("<div class=\"statistical_notes_link_second_part\"><a href=\"#STATISTICALNOTES\">" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</a></div>");
//			//html.setHtml("<div id=\"statistics_link_contents_right\"><span class=\"footer\"><a class=\"footer-link\" href=\"#STATISTICALNOTES\">"+AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART+"</a></span></div>");
//			html.addListener(Events.OnClick, new 
//			//AMISMenuController.openView(AMISCurrentView.HOME, mainMenu)
//			//html.setStyleName("statistical_notes_link");
//			//html.setHtml("<a class='statistical_notes_link pointer' href=\"#STATISTICALNOTES\">" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</a>");
//			//	html.addListener(Events.OnClick, AMISController.loginListenerClick(user, password, amisInput.getDataSourceListBox(), amisInput));
//			//hp.add(html);
//		//panel.add(html);
//		//	hp.add(new Html("<div> " + title + " </div>"));
//		//	hp.add(new Html("<a href=\"#STATISTICALNOTES"></a> "));
//			panel.add(hp);
//			return panel;
//	 }
	 
	 public static ContentPanel statisticalNotesLink()
	 {
			ContentPanel panel = new ContentPanel();
			panel.setStyleName("table_top_panel");
			panel.setHeaderVisible(false);
			panel.setBorders(false);
			panel.setBodyBorder(false);
			HorizontalPanel hp = new HorizontalPanel();
			hp.setStyleName("statistical_notes_link");
			
			hp.setHorizontalAlign(HorizontalAlignment.RIGHT);
			hp.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		//	hp.add(new Html("<div class='" + "selector-hyperlink a" + "'> " + "Statistical Notes" + " </div>"));
			Html firstPart = new Html(AMISInterfaceConstants.STATISTICAL_NOTES_LINK_FIRST_PART);
			firstPart.setStyleName("statistical_notes_link");
			//firstPart.setStyleAttribute("background-color", "#DC3018");
			hp.add(firstPart);
			HorizontalPanel space = FormattingUtils.addHSpace(5);
			//space.setStyleName("statistical_notes_link");
//			space.setStyleAttribute("background-color", "#DC3018");
			hp.add(space);
			//ClickHtml html = new ClickHtml();
			ClickHtml html = new ClickHtml();
			html.setHtml("<div class='statistical_notes_link pointer'>" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</div>");
			html.addListener(Events.OnClick, AMISController.statisticalNoteLinkAction(AMISHome.mainMenu));
			//html.addListener(Events.OnClick, AMISMenuController.openView(AMISCurrentView.STATISTICALNOTES,mainMenu));
			
			hp.add(html);
			//<div class="left" id="FAOSTAT-LOGO"><a href="#HOME"><img src="dataviewer-images/faostat_logo.png"></a></img></div>
		//	html.setHtml("<a style=\"float:right; color:white; padding-right:5px;\"  href=\"#STATISTICALNOTES\">" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</a></div>");
		//	html.setHtml("<div class=\"statistical_notes_link_second_part\"><a href=\"#STATISTICALNOTES\">" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</a></div>");
			//html.setHtml("<div id=\"statistics_link_contents_right\"><span class=\"footer\"><a class=\"footer-link\" href=\"#STATISTICALNOTES\">"+AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART+"</a></span></div>");
			//AMISMenuController.openView(AMISCurrentView.HOME, mainMenu)
			//html.setStyleName("statistical_notes_link");
			//html.setHtml("<a class='statistical_notes_link pointer' href=\"#STATISTICALNOTES\">" + AMISInterfaceConstants.STATISTICAL_NOTES_LINK_SECOND_PART + "</a>");
			//	html.addListener(Events.OnClick, AMISController.loginListenerClick(user, password, amisInput.getDataSourceListBox(), amisInput));
			//hp.add(html);
		//panel.add(html);
		//	hp.add(new Html("<div> " + title + " </div>"));
		//	hp.add(new Html("<a href=\"#STATISTICALNOTES"></a> "));
			panel.add(hp);
			return panel;
	 }
}
