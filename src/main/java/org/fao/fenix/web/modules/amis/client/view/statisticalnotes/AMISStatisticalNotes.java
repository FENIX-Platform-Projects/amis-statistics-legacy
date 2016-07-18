package org.fao.fenix.web.modules.amis.client.view.statisticalnotes;


import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class AMISStatisticalNotes {
	
	ContentPanel panel;
	
	Integer titleSpacing = 10;
	
	Integer bulletSpacing = 5;
	
	public AMISStatisticalNotes() {
		panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
	}
	
//	public ContentPanel build() {
//		
//		panel.add(FormattingUtils.addHSpace(5));
//		panel.add(FormattingUtils.addVSpace(20));
//		//panel.add(buildTitle());
//		
//		//panel.add(FormattingUtils.addVSpace(20));
//		panel.add(buildGeneral());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		panel.add(buildProduction());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		panel.add(buildUtilization());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		
//		panel.add(buildTrade());
//		panel.add(FormattingUtils.addVSpace(15));
//		
//		panel.add(buildStocks());
//		panel.add(FormattingUtils.addVSpace(25));
//		
//		panel.add(buildDisclaimer());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		
//		panel.layout();
//		
//		return panel;
//	}
	
	public ContentPanel build() {

		RootPanel.get("OLAP_IFRAME").setVisible(false);
		RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
		RootPanel.get("COMPARE_NOTES").setVisible(false);
		//RootPanel.get("WHITE_SPACE").setVisible(false);
		panel.add(addStatisticalNotes());
//		panel.add(FormattingUtils.addHSpace(5));
//		panel.add(FormattingUtils.addVSpace(20));
//		//panel.add(buildTitle());
//		
//		//panel.add(FormattingUtils.addVSpace(20));
//		panel.add(buildGeneral());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		panel.add(buildProduction());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		panel.add(buildUtilization());
//		
//		panel.add(FormattingUtils.addVSpace(15));
//		
//		panel.add(buildTrade());
//		panel.add(FormattingUtils.addVSpace(15));
//		
//		panel.add(buildStocks());
//		panel.add(FormattingUtils.addVSpace(25));
//		
//		panel.add(buildDisclaimer());
//		
//		panel.add(FormattingUtils.addVSpace(15));
		
		panel.layout();
		
		return panel;
	}
	
	 private Html addStatisticalNotes() {
			
		 System.out.println("Class: AMISsTATISTICALnOTES Text: addStatisticalNotes START");
			Html html = new Html();
		//	String div = "<div><img src='amis-docs/SupplyandDemandDefinitionsFP.html' border='0'/></div>";
			//frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 770px;
			//String div = "<iframe src=\"amis-docs/SupplyandDemandDefinitionsFP.html\" width=1000 height=500 marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=auto border='0'></iframe>";
		//	String div = "<iframe src=\"amis-docs/SupplyandDemandDefinitionsFP.html\" frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px  height: 770px\"></iframe>";
			String div = "<iframe src=\"amis-docs/SupplyandDemandDefinitionsFP.html\"  frameBorder=\"0\" scrolling=auto style=\"width: 1100px;  height: 770px;\">Your browser does not support iframes.</iframe>";
//			String div = "<div><img src='dataviewer-images/download-guide/guide_e.png' border='0'/></div>";
			html.setHtml(div);	
			System.out.println("Class: AMISsTATISTICALnOTES Text: addStatisticalNotes END");
			return html;
		}
	
	private VerticalPanel buildTitle() {
		VerticalPanel panel = new VerticalPanel();
		HTML html = new HTML();
		html.setHTML("<div class='amis_title'>STATISTICAL NOTES</div>");
		panel.add(html);
		return panel;
	}

	private VerticalPanel buildBulletPoint(String text) {
		VerticalPanel panel = new VerticalPanel();
		
		
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.add(FormattingUtils.addHSpace(7));
		
	//	HTML icon = new HTML();	
		//icon.setHTML("<img src='amis-images/blueArrow.png' />");
		//p.add(icon);
		
		p.add(FormattingUtils.addHSpace(3));
		
		HTML html = new HTML();
		html.setHTML("<div class='statistical_note_point'>-&nbsp;&nbsp;" + text +"</div>");
		p.add(html);
		
		
		panel.add(p);
		panel.add(FormattingUtils.addVSpace(3));
		
		return panel;
	}
	
	private HorizontalPanel buildDisclaimerTitle(String text) {
		HorizontalPanel panel = new HorizontalPanel();
		
		HTML html = new HTML();
		html.setHTML("<div class='disclaimer_note_title'>" + text +"</div>");
		
		panel.add(html);
	
		return panel;
	}
	
	private HorizontalPanel buildCategoryTitle(String text) {
		HorizontalPanel panel = new HorizontalPanel();
		
		HTML html = new HTML();
		html.setHTML("<div class='statistical_note_title'>" + text +"</div>");
		
		panel.add(html);
	
		return panel;
	}
	
	
	
	private VerticalPanel buildGeneral() {
		VerticalPanel panel = new VerticalPanel();

		// title
		panel.add(buildCategoryTitle("General"));
		
		panel.add(FormattingUtils.addVSpace(titleSpacing));
		
	
		// bullet points
		panel.add(buildBulletPoint("AMIS estimates and forecasts are based on official and unofficial sources."));
		panel.add(buildBulletPoint("Unless otherwise stated, all charts and tables refer to AMIS data as source."));
		
		panel.add(buildBulletPoint("Estimates of world imports and exports may not always match, mainly because shipments and deliveries do not necessarily occur in the same marketing year."));
		panel.add(buildBulletPoint("Tonnes refer to metric tonnes. "));
		panel.add(buildBulletPoint("All totals are computed from unrounded data."));
		panel.add(buildBulletPoint("Regional totals may include estimates for countries not listed. The countries shown in the tables were chosen based on their importance of either production or trade in each region. The totals shown for Central America include countries in the Caribbean."));
		panel.add(buildBulletPoint("Estimates for China also include those for the Taiwan Province, Hong Kong SAR and Macao  SAR, unless otherwise stated."));
		panel.add(buildBulletPoint("Up to 2006 or 2006/07, the European Union includes 25 member states. From 2007 or 2007/08 onwards, the European Union includes 27 member states."));
		panel.add(buildBulletPoint("‘-‘ means nil or negligible. "));	

        return panel;
	}
	
	private VerticalPanel buildProduction() {
		VerticalPanel panel = new VerticalPanel();
		
		// title
		panel.add(buildCategoryTitle("Production"));
		panel.add(FormattingUtils.addVSpace(titleSpacing));
		
		// bullet points
		panel.add(buildBulletPoint("Cereals: Data refer to the calendar year in which the whole harvest or bulk of harvest takes place."));	

		return panel;
	}
	
	private VerticalPanel buildUtilization() {
		VerticalPanel panel = new VerticalPanel();

		// title
		panel.add(buildCategoryTitle("Utilization"));
		panel.add(FormattingUtils.addVSpace(titleSpacing));
		
		// bullet points
		panel.add(buildBulletPoint("Cereals: Data are on individual country’s marketing year basis."));	

		return panel;
	}
	
	private VerticalPanel buildStocks() {
		VerticalPanel panel = new VerticalPanel();

		// title
		panel.add(buildCategoryTitle("Stocks"));
		panel.add(FormattingUtils.addVSpace(titleSpacing));
		
		// bullet points
		panel.add(buildBulletPoint("Cereals: Data refer to carry-overs at the close of national crop seasons ending in the year shown."));	

		
		return panel;
	}
	
	

	private VerticalPanel buildTrade() {
		VerticalPanel panel = new VerticalPanel();

		// title
		panel.add(buildCategoryTitle("Trade"));
		panel.add(FormattingUtils.addVSpace(titleSpacing));
		
		// bullet points
		panel.add(buildBulletPoint("Trade between European Union member states is excluded, unless otherwise stated."));	
		panel.add(buildBulletPoint("Wheat: Trade data include wheat ﬂour in wheat grain equivalent. The time reference period is July/June, unless otherwise stated."));	
		panel.add(buildBulletPoint("Coarse grains: The time reference period is July/June, unless otherwise stated."));	
		panel.add(buildBulletPoint("Rice: The time reference period is January/ December."));	
		panel.add(buildBulletPoint("Oilseeds: The time reference period is October/September, unless otherwise stated."));	
		
		return panel;
	}

	
	private HorizontalPanel buildDisclaimer() {
		HorizontalPanel holder =  new HorizontalPanel();
		holder.add(FormattingUtils.addHSpace(20));
		
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("disclaimer_panel");
		
		panel.add(FormattingUtils.addVSpace(20));
		panel.add(buildDisclaimerTitle("Disclaimer"));
		
		panel.add(FormattingUtils.addVSpace(10));
		
		HTML html = new HTML();
		String text = "The designations employed and the presentation of material in this publication do not imply the expression, of any opinion whatsoever on the part of the Agricultural Market Information System (AMIS) concerning the legal status of any country, territory, city or area or of its authorities, or concerning the delimitation of its frontiers or boundaries.";
		html.setHTML("<div class='disclaimer_note'>"+ text +"</div>");
		panel.add(html);
		
		panel.add(FormattingUtils.addVSpace(20));
		
		holder.add(panel);
		holder.add(FormattingUtils.addHSpace(20));
		return holder;
	}
	
	private HorizontalPanel buildDisclaimerOld() {
		HorizontalPanel holder = new HorizontalPanel();
		holder.addStyleName("disclaimer_panel");
		
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("disclaimer_panel");
		
		// title
		panel.add(buildDisclaimerTitle("Disclaimer"));
		panel.add(FormattingUtils.addVSpace(titleSpacing));
		
		HTML html = new HTML();

		String text = "The designations employed and the presentation of material in this publication do not imply the expression, of any opinion whatsoever on the part of the Agricultural Market Information System (AMIS) concerning the legal status of any country, territory, city or area or of its authorities, or concerning the delimitation of its frontiers or boundaries.";
		html.setHTML("<div>"+ text +"</div>");
		panel.add(html);
		
		holder.add(panel);
		
		return holder;
	}

	
	

}
