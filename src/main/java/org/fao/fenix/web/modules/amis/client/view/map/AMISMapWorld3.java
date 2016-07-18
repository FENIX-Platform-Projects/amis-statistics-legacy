package org.fao.fenix.web.modules.amis.client.view.map;

import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.HTML;

public class AMISMapWorld3 {

	static HorizontalPanel panel;

	static VerticalPanel mapPanel;

	static VerticalPanel backPanel;

	static String imagePath;

	static Boolean isBackCreated;

	static Boolean isMapCreated;

	public AMISMapWorld3() {
//		panel = new ContentPanel();
//		panel.setBodyBorder(false);
//		panel.setHeaderVisible(false);
		panel = new HorizontalPanel();
		panel.setHeight(408);
		panel.addStyleName("bg_transparent");
	//	panel.add(FormattingUtils.addHSpace(20));

		mapPanel = new VerticalPanel();
		mapPanel.setHeight(408);
		mapPanel.setWidth(860);
		mapPanel.addStyleName("bg_transparent");


		backPanel = new VerticalPanel();
		backPanel.setStyleAttribute("backgroundColor", "#ADD3FA");

		backPanel.setHeight(409);
		backPanel.setWidth(238);
		/*backPanel.addStyleName("bg_transparent");*/

		isBackCreated = false;
		isMapCreated = false;

	}

	public static void buildMap(String path, String defaultMap) {
		mapPanel.removeAll();
		imagePath = path;
		//mapPanel.add(FormattingUtils.addVSpace(5));
		mapPanel.add(buildMapHTML(defaultMap));
		//mapPanel.add(FormattingUtils.addVSpace(5));
		//mapPanel.add(buildLegend());

//		System.out.println("CREATED MAP: " + isMapCreated);
		if ( !isMapCreated ){
			isMapCreated = true;
			panel.add(mapPanel);
		}

//		System.out.println("isBackCreated MAP: " + isBackCreated);
		if ( !isBackCreated ){
			isBackCreated = true;

			backPanel.add(spacer());
			//backPanel.add(FormattingUtils.addHSpace(20));
			backPanel.add(buildBackPanelContent());

			panel.add(backPanel);
		}

		panel.layout();

	}



	private static VerticalPanel buildGoToGlobal() {
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		panel.addStyleName("back_text");

		HTML image = new HTML();
		image.setHTML("<img src=\"amis-images/map-images/world_map.png\" />");
		panel.add(image);
		panel.add(FormattingUtils.addVSpace(7));

		HorizontalPanel h = new HorizontalPanel();
		IconButton icon = new IconButton("blueArrowIcon");
		//panel.add(icon);

		HTML html = new HTML();
		html.setHTML("<div>World Aggregates</div>");

		//h.add(icon);
		h.add(FormattingUtils.addHSpace(7));
		h.add(html);
		panel.add(h);



		html.addClickHandler(setRegion("999000", "WORLD", "BLANK"));
		image.addClickHandler(setRegion("999000", "WORLD", "BLANK"));
		icon.addSelectionListener(new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				System.out.println("uioashdiuahsdiuh");
				selectCountry("999000", "WORLD", "BLANK");
			}
		});

		return panel;
	}

	private static VerticalPanel buildGoToEU() {
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		panel.addStyleName("back_text");

		HTML image = new HTML();
		image.setHTML("<img src=\"amis-images/map-images/EU_map.png\" />");
		panel.add(image);
		panel.add(FormattingUtils.addVSpace(7));

		HorizontalPanel h = new HorizontalPanel();
		IconButton icon = new IconButton("blueArrowIcon");
		//panel.add(icon);

		HTML html = new HTML();
		html.setHTML("<div>European Union Totals</div>");

		//h.add(icon);
		h.add(FormattingUtils.addHSpace(7));
		h.add(html);
		panel.add(h);



		html.addClickHandler(setRegion("999001", "EUROPEAN UNION", "BLANK"));
		image.addClickHandler(setRegion("999001", "EUROPEAN UNION", "BLANK"));
		icon.addSelectionListener(new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				selectCountry("999001", "EUROPEAN UNION", "BLANK");
			}
		});

		return panel;
	}




	public static ClickHandler setRegion(final String code, final String label, final String defaultMap) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("ClickHandler setRegion: " + code + " | " + label + " | " + defaultMap);
				selectCountry(defaultMap, code, label);
			}
		};
	}

	public static VerticalPanel spacer() {
		VerticalPanel panel = new VerticalPanel();
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		panel.setHeight(10);
		panel.setWidth(238);

		return panel;
	}

	public static HorizontalPanel buildBackPanelContent() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(FormattingUtils.addHSpace(30));
		panel.add(buildMoreViewsSection());

		return panel;
	}

	public static VerticalPanel buildMoreViewsSection(){
		VerticalPanel panel = new VerticalPanel();
		//panel.add(FormattingUtils.addVSpace(20));
		//panel.add(buildLabel("AGGREGATES", null, "back_title"));
		panel.add(FormattingUtils.addVSpace(40));
		panel.add(buildGoToGlobal());
		panel.add(FormattingUtils.addVSpace(13));
		//panel.add(buildGoToEU());

		return panel;
	}


	public static VerticalPanel buildLegend() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(buildLabel("AMIS Countries", "#5b8fc6", "legend_text"));
		panel.add(FormattingUtils.addVSpace(5));

		return panel;
	}


	public static HorizontalPanel buildLabel(String title, String color, String style) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		if(color!=null) {

			panel.add(FormattingUtils.addHSpace(10));

			HTML colorPanel = new HTML();
			colorPanel.setHTML("<div style='background-color:"+ color +";width:14px;height:14px;'></div>");

			panel.add(colorPanel);
		}

		HTML titlePanel = new HTML();
		titlePanel.setHTML("<div class='"+style+"'>"+ title +"</div>");

		panel.add(FormattingUtils.addHSpace(5));

		panel.add(titlePanel);

		return panel;
	}


	public static HTML buildMapHTML(final String defaultMap) {


		System.out.println("AMISMapWorld buildMap");

		HTML mapHtml = new HTML();
		StringBuffer map = new StringBuffer();


		String mapID = String.valueOf((Math.random() * 10));
		mapID = mapID.replace(".", "");

		System.out.println("mapID: " + mapID);


//		map.append("<div class=\"zindex\">");
		map.append("<div>");
		map.append("<MAP name='"+ mapID +"'> ");

		 // 12, ARGENTINA




		/** map.append(" <area class=\"area\" shape=\"poly\" href='#'" +
                           " onmouseover=\"return setStatus('ARGENTINA')\"  " +
                           " coords=\"245,403,233,396,221,387,216,366,207,328,212,312,227,316,237,321,240,328,245,323,248,326,241,341,246,349,247,358,238,361,236,365,233,371,233,378,237,383,234,390,245,401,245,403\" " +
                           " />");**/

        map.append(" <area class=\"area\" shape=\"poly\"  href='#'" +
                           " onmousemove=\"setCountryID('ARGENTINA', '12', 'ARGENTINA', event)\"  onClick=\"setCountryID('ARGENTINA', '12', 'ARGENTINA', event)\" " +
                           " coords=\"245,403,233,396,221,387,216,366,207,328,212,312,227,316,237,321,240,328,245,323,248,326,241,341,246,349,247,358,238,361,236,365,233,371,233,378,237,383,234,390,245,401,245,403\" " +
                           " />");

       // onmousemove=\"setCountryID('ARGENTINA', '12', 'Argentina', event)\" 
        // 17, AUSTRALIA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#' " +
                           " onmousemove=\"setCountryID('AUSTRALIA', '17', 'AUSTRALIA', event)\"  onClick=\"setCountryID('AUSTRALIA', '17', 'AUSTRALIA', event)\" " +
                           " coords=\"733,373,734,358,725,347,708,345,694,346,679,349,673,341,684,318,708,305,724,292,737,289,745,281,760,285,758,295,764,300,772,281,777,281,779,294,786,316,788,328,766,348,750,358,744,365,733,373\" " +
                           " />");

        // 37, BRAZIL
                        map.append(" <area class=\"area\" shape=\"poly\" href='#' " +
                           " onmousemove=\"setCountryID('BRAZIL', '37', 'BRAZIL', event)\"  onClick=\"setCountryID('BRAZIL', '37', 'BRAZIL', event)\" " +
                           " coords=\"253,345,239,335,247,325,234,313,230,299,219,289,207,281,202,283,187,277,187,263,196,257,197,246,205,246,209,249,210,240,222,236,226,242,225,248,235,245,242,244,246,239,251,248,256,254,271,259,285,262,293,266,295,275,285,286,283,298,279,315,264,318,263,328,253,345\" " +
                           " />");

        // 162, MEXICO
                        map.append(" <area class=\"area\" shape=\"poly\" href='#' " +
                           " onmousemove=\"setCountryID('MEXICO', '162', 'MEXICO', event)\"  onClick=\"setCountryID('MEXICO', '162', 'MEXICO', event)\" " +
                           " coords=\"91,162,103,164,115,164,121,167,121,173,126,169,129,173,132,180,134,185,130,195,134,201,143,200,149,193,158,192,155,202,147,204,143,206,144,209,139,209,138,213,130,210,121,209,110,204,106,199,108,191,102,182,99,175,97,168,96,173,98,183,100,189,96,188,91,181,89,175,90,167,91,162\" " +
                           " />");


        // 259, USA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#' " +
                           " onmousemove=\"setCountryID('USA', '259', 'UNITED STATES OF AMERICA', event)\"  onClick=\"setCountryID('USA', '259', 'UNITED STATES OF AMERICA', event)\" " +
                         " coords=\"100,117,130,114,169,111,187,122,198,130,212,125,224,121,234,121,235,133,219,145,202,156,184,168,182,184,170,184,166,172,150,177,139,180,129,183,113,172,97,167,85,162,78,149,89,128,100,117\" " +
                         //  " coords=\"100,119,98,113,97,103,99,90,104,81,127,61,137,62,153,59,168,63,174,64,180,67,189,64,202,65,209,66,211,60,217,54,218,62,222,64,227,65,230,60,236,63,232,70,217,74,198,84,194,94,202,98,208,100,208,111,213,108,213,101,221,95,225,89,230,81,241,81,245,90,254,87,257,97,265,107,253,116,232,118,215,127,205,133,192,138,189,137,194,130,197,126,189,121,184,118,178,121,170,118,100,119\" " +
                           " />");


        // 259, USA-ALASKA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('USA', '259', 'UNITED STATES OF AMERICA', event)\"  onClick=\"setCountryID('USA', '259', 'UNITED STATES OF AMERICA', event)\" " +
                         //  " coords=\"97,90,83,87,64,96,36,101,51,94,48,87,56,80,65,79,71,74,60,75,66,70,73,70,75,67,74,64,93,59,107,57,125,59,127,61,108,81,102,89,97,90\" " +
                           " coords=\"111,101,111,92,109,86,115,77,129,63,129,56,104,54,86,55,66,65,56,76,44,84,44,92,29,99,35,105,51,103,73,95,91,93,94,95,111,101\" " +
                        " />");

        // 46, CANADA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('CANADA', '46', 'CANADA', event)\"  onClick=\"setCountryID('CANADA', '46', 'CANADA', event)\" " +
                           " coords=\"107,118,102,111,105,103,107,93,106,90,101,91,98,86,118,69,125,60,134,63,147,60,158,61,161,55,171,47,182,41,199,37,221,34,235,29,255,25,273,23,293,26,270,34,256,42,250,47,258,55,264,62,272,69,263,75,256,84,258,90,257,98,266,107,252,116,228,120,203,132,190,138,194,126,190,120,173,120,148,117,119,118,106,117\" " +
                           " />");

        // 227, SOUTH AFRICA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('SOUTH_AFRICA', '227', 'SOUTH AFRICA', event)\"  onClick=\"setCountryID('SOUTH_AFRICA', '227', 'SOUTH_AFRICA', event)\" " +
                           " coords=\"465,312,471,314,471,322,467,326,473,326,469,332,464,340,448,346,436,348,432,342,431,336,430,329,437,332,439,328,440,321,444,327,447,322,453,324,457,318,465,312\" " +
                           " />");

        // 182, NIGERIA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('NIGERIA', '182', 'NIGERIA', event)\"  onClick=\"setCountryID('NIGERIA', '182', 'NIGERIA', event)\" " +
                           " coords=\"394,236,395,226,396,220,400,215,410,215,417,215,423,214,428,219,425,226,420,234,413,236,410,241,402,240,400,236,394,236\" " +
                           " />");


        // 40765, EGYPT
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('EGYPT', '40765', 'EGYPT', event)\"  onClick=\"setCountryID('EGYPT', '40765', 'EGYPT', event)\" " +
                           " coords=\"450,164,452,175,455,192,478,192,481,188,476,180,476,176,474,166,464,166,461,168,450,164\" " +
                           " />");

        // 999001, EUROPEAN UNION
                        map.append("<area class=\"area\" shape=\"poly\" href='#'  " +
                        " onmouseover=\"setCountryID('EUROPEAN_UNION', '999001', 'EUROPEAN UNION', event)\" onClick=\"setCountryID('EUROPEAN_UNION', '999001', 'EUROPEAN UNION', event)\" "+
                        " coords=\"437,53,367,91,363,109,365,147,377,154,428,149,443,151,453,138,434,119,441,84,438,69\" " +
                        " />");

        //" coords=\"332,145,336,96,349,86,362,106,364,74,373,26,410,26,412,92,444,112,410,146,388,146,375,142,340,148,332,145\" " +

        // 122, ITALY
                       // map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                         //  " onmousemove=\"setCountryID('ITALY', '122', 'ITALY', event)\"  onClick=\"setCountryID('ITALY', '122', 'ITALY', event)\" " +
                         //  " coords=\"406,131,404,127,410,126,418,125,417,131,423,136,432,141,430,145,426,149,424,152,417,149,421,147,426,145,411,145,406,145,407,139,411,139,414,136,410,131,406,131\" " +
                         //  " />");

        // 85, FRANCE
                      //  map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                       //    " onmousemove=\"setCountryID('FRANCE', '85', 'FRANCE', event)\"  onClick=\"setCountryID('FRANCE', '85', 'FRANCE', event)\" " +
                        //   " coords=\"383,133,383,126,377,121,376,118,384,116,390,114,392,112,402,117,405,121,403,127,404,132,401,134,395,135,389,136,383,133\" " +
                         //  " />");

        // 93, GERMANY
                       // map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                         //  " onmousemove=\"setCountryID('GERMANY', '93', 'GERMANY', event)\"  onClick=\"setCountryID('GERMANY', '93', 'GERMANY', event)\" " +
                         //  " coords=\"406,121,406,118,402,116,402,111,404,104,407,102,413,104,416,104,420,110,417,114,415,115,417,117,416,121,406,121\" " +
                         //  " />");

        // 229, SPAIN
                       // map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                        //   " onmousemove=\"setCountryID('SPAIN', '229', 'SPAIN', event)\"  onClick=\"setCountryID('SPAIN', '229', 'SPAIN', event)\" " +
                        //   " coords=\"366,132,365,135,372,137,371,141,369,148,369,150,374,154,381,152,387,148,388,141,394,138,395,136,382,133,374,133,366,132\" " +
                        //   " />");

        // 255, UK
                     //   map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                       //    " onmousemove=\"setCountryID('UK', '255', 'UNITED KINGDOM', event)\" onClick=\"setCountryID('UK', '255', 'UNITED KINGDOM', event)\" " +
                       //    " coords=\"374,115,376,110,378,106,377,102,374,104,371,102,372,96,374,91,380,90,383,92,384,96,386,103,391,107,391,112,385,113,374,115\" " +
                       //    " />");

        // 254, Ukraine
                        map.append(" <area class=\"area\" shape=\"poly\" href='#' " +
                           " onmousemove=\"setCountryID('UKRAINE', '254', 'UKRAINE', event)\" onClick=\"setCountryID('UKRAINE', '254', 'UKRAINE', event)\"  " +
                           " coords=\"439,120,440,114,441,110,454,111,460,109,468,112,478,117,479,122,474,124,469,125,473,128,469,129,466,130,463,126,459,126,456,127,454,126,454,121,450,118,447,120,442,121,439,120\" " +
                           " />");

        // 249 , TURKEY
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('TURKEY', '249', 'TURKEY', event)\" onClick=\"setCountryID('TURKEY', '249', 'TURKEY', event)\"  " +
                           " coords=\"451,138,457,139,467,138,475,137,484,138,491,140,497,150,481,152,474,153,469,153,463,151,462,153,454,151,449,146,451,138\" " +
                           " />");

        // ,
                        map.append(" <area class=\"area\" shape=\"poly\"  " +
                           " onmousemove=\"setCountryID('', '', '', event)\" onClick=\"setCountryID('', '', '', event)\" " +
                           " coords=\"\" " +
                           " />");

        // 215, Saudi Arabia
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('SAUDI_ARABIA', '215', 'SAUDI ARABIA', event)\"  onClick=\"setCountryID('SAUDI_ARABIA', '215', 'SAUDI ARABIA', event)\" " +
                           " coords=\"482,165,493,165,507,172,524,186,535,190,531,199,513,206,504,206,502,207,491,195,485,185,477,175,477,172,483,171,482,165\" " +
                           " />");


        // 132, KAZAKHSTAN
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('KAZAKHSTAN', '132', 'KAZAKHSTAN', event)\" onClick=\"setCountryID('KAZAKHSTAN', '132', 'KAZAKHSTAN', event)\"  " +
                           " coords=\"511,140,501,129,495,121,493,113,501,110,513,112,520,113,522,111,520,105,538,99,550,105,555,103,570,112,584,116,585,124,581,128,579,132,580,136,564,133,561,135,556,136,552,141,543,135,534,132,524,127,521,134,523,138,517,135,511,140\" " +
                           " />");

        // 115, INDIA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('INDIA', '115', 'INDIA', event)\" onClick=\"setCountryID('INDIA', '115', 'INDIA', event)\"  " +
                           " coords=\"579,162,594,170,597,174,612,179,621,178,631,178,637,174,633,192,621,193,614,201,604,211,604,224,599,230,592,223,583,205,579,194,572,195,566,187,573,186,567,179,568,176,573,176,578,165,579,162\" " +
                           " />");


        // 240, THAILAND
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('THAILAND', '240', 'THAILAND', event)\" onClick=\"setCountryID('THAILAND', '240', 'THAILAND', event)\"  " +
                           " coords=\"651,198,648,202,653,207,654,213,656,219,655,229,660,234,666,235,666,234,662,232,661,227,658,222,659,216,661,217,665,218,665,214,666,212,672,212,668,205,664,202,660,204,658,205,657,199,651,198\" " +
                           " />");

        // 264, VIETNAM
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('VIETNAM', '264', 'VIET NAM', event)\" onClick=\"setCountryID('VIETNAM', '264', 'VIET NAM', event)\" " +
                           " coords=\"658,191,665,188,673,195,670,201,681,210,685,221,677,227,673,229,672,224,677,220,679,213,673,205,666,198,660,195,658,191\" " +
                           " />");

        // 202, KOREA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('KOREA', '202', 'REPUBLIC OF KOREA', event)\" onClick=\"setCountryID('KOREA', '202', 'REPUBLIC OF KOREA', event)\" " +
                           " coords=\"698,149,702,147,710,155,706,158,702,157,699,152,698,149\" " +
                           " />");

        // 126, JAPAN
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('JAPAN', '126', 'JAPAN', event)\" onClick=\"setCountryID('JAPAN', '126', 'JAPAN', event)\" " +
                          // " coords=\"709,201,718,208,719,212,727,215,732,226,734,235,728,236,722,235,721,230,719,224,714,225,708,230,705,228,712,217,709,210,709,201\" " +
                         " coords=\" 717,128,719,133,720,139,723,141,725,145,723,150,718,153,713,157,714,166,721,167,726,164,733,160,741,157,739,148,731,138,735,134,731,129,723,125,717,128\" " +
                        " />");

        // 53, CHINA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('CHINA', '53', 'CHINA', event)\"  onClick=\"setCountryID('CHINA', '53', 'CHINA', event)\" " +
                           " coords=\"585,117,577,125,576,136,568,145,582,156,588,159,592,170,615,176,629,175,646,181,648,190,659,194,669,189,678,194,694,191,702,174,688,155,688,148,681,142,689,143,699,133,699,127,695,119,685,118,667,108,652,105,654,113,651,120,658,121,656,128,648,131,649,136,635,139,625,134,614,134,603,128,596,121,585,117\" " +
                           " />");

        // 204, RUSSIA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('RUSSIA', '204', 'RUSSIAN FEDERATION', event)\" onClick=\"setCountryID('RUSSIA', '204', 'RUSSIAN FEDERATION', event)\" " +
                           " coords=\"503,139,477,132,474,122,464,110,442,93,449,79,441,65,454,60,466,65,479,59,486,43,502,39,513,50,535,47,533,34,543,30,569,36,588,45,612,50,623,42,647,45,670,53,687,58,718,60,739,70,752,81,737,90,736,96,740,112,731,111,717,100,714,88,696,92,684,92,689,101,700,108,709,122,708,136,694,130,694,121,668,111,655,107,658,116,644,116,633,118,611,113,606,117,594,115,582,119,566,113,547,105,531,104,525,111,521,114,506,114,499,119,504,127,505,136,503,139\" " +
                           " />");



        // 196, PHILIPPINES
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('PHILIPPINES', '196', 'PHILIPPINES', event)\" onClick=\"setCountryID('PHILIPPINES', '196', 'PHILIPPINES', event)\" " +
                           " coords=\"706,230,711,217,709,208,709,200,713,199,719,206,722,211,730,218,733,226,735,234,731,237,725,237,722,236,718,233,720,228,717,223,714,222,711,229,708,231,706,230\" " +
                           " />");

        // 116, INDONESIA
                        map.append(" <area class=\"area\" shape=\"poly\" href='#'  " +
                           " onmousemove=\"setCountryID('INDONESIA', '116', 'INDONESIA', event)\" onClick=\"setCountryID('INDONESIA', '116', 'INDONESIA', event)\" " +
                           " coords=\"647,235,656,237,668,248,677,254,685,255,686,248,692,246,699,246,705,239,712,242,715,248,723,248,729,246,738,244,742,248,750,251,758,253,767,255,774,257,772,280,763,277,754,272,749,275,732,279,725,283,710,280,693,278,674,273,661,260,651,249,647,235\" " +
                           " />");



		map.append("</MAP> ");

		map.append("<IMG src='"+ imagePath +"' Usemap='#"+ mapID +"' >");
		map.append("</div> ");


		mapHtml.setHTML(map.toString());
		 // System.out.println("AMISMapWorld buildMap HTML = " + map.toString());




//		   public void handleEvent(BaseEvent be) {
//		    changeImageJS(defaultMap);
//		   }
//
//		  });



	/** mapHtml.addMouseOverHandler(new MouseOverHandler() {
		public void onMouseOver(MouseOverEvent arg0) {
			    System.out.println("Mouse OVER!");
				changeImageJS(defaultMap);
			}
		});*/

		mapHtml.addMouseMoveHandler(new MouseMoveHandler() {

			public void onMouseMove(MouseMoveEvent arg0) {
				//System.out.println("Mouse Move!");
				changeImageJS(defaultMap);
			}
		});

		mapHtml.addMouseOutHandler(new MouseOutHandler() {

			public void onMouseOut(MouseOutEvent arg0) {
				//System.out.println("Mouse Out!");
				changeImageJS(defaultMap);
			}
		});

		mapHtml.addClickHandler(getSelectedCountry(defaultMap));


		return mapHtml;
	}


	public static ClickHandler changeImage(final String defaultMap) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("ClickHandler!");
				changeImageJS(defaultMap);
			}
		};
	}

	public static void changeImagePath(String countryFile, String highlighted, String defaultMap) {
		System.out.println("changeImagePath: " + countryFile + " |" + highlighted + "|");

		String imagePath = null;

		if ( countryFile != null ) {
			 imagePath = "amis-images/map-images/"+ countryFile + "_" + highlighted + ".png";
		}
		else {
			 imagePath = "amis-images/map-images/"+ defaultMap + ".png";
		}

//		System.out.println("changeImagePath: " + imagePath);
//		System.out.println("defaultMap: " + defaultMap);
		buildMap(imagePath, defaultMap);
	}

	public static native void changeImageJS(String defaultMap)/*-{

	  	var countryFile = $wnd.countryFile;

	  	if ( countryFile != '' ) {
	  		//alert(countryFile + " change image is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld3::changeImagePath(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(countryFile, "HIGHLIGHTED", defaultMap);
	  	}
	  	else{
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld3::changeImagePath(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(null, null, defaultMap);
	  		//alert(countryID + " change is null");
	  	}


	  	// setting the default value
	  	$wnd.countryID = '';
		$wnd.countryFile = '';
		$wnd.countryLabel = '';

		//alert("$wnd.regionID: " + $wnd.regionID);

	}-*/;



	public static ClickHandler getSelectedCountry(final String defaultMap) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("ClickHandler getSelectedCountry: ");
				getSelectedCountryJS(defaultMap);

			}
		};
	}

	public static void selectCountry(String countryFile, String countryID, String countryLabel) {
		System.out.println("selectCountry: " + countryFile + " | " + countryID + " | " + countryLabel);

		String defaultMap = countryFile;

		if ( !countryFile.equalsIgnoreCase("BLANK"))
			 defaultMap = countryFile + "_HIGHLIGHTED";

		String imagePath = "amis-images/map-images/"+ defaultMap + ".png";

//		System.out.println("selectCountry: " + imagePath);
//		System.out.println("defaultMap: " + defaultMap);
		buildMap(imagePath, defaultMap);
		AMISHome.getDatasetViewAgent(countryID, countryLabel);
	}

	public static native void getSelectedCountryJS(String defaultMap)/*-{


	  	var countryID = $wnd.countryID;
	  	var countryFile = $wnd.countryFile;
	  	var countryLabel = $wnd.countryLabel;

	  	if ( countryID != '' ) {
//	  		alert(countryID + " selectCountry is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld3::selectCountry(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(countryFile, countryID, countryLabel);
	  	}
	  	else{
	  		//alert(countryID + " selectCountry is null");
	  	}

		// setting the default value
	  	$wnd.countryID = '';
	  	$wnd.countryFile = '';
	  	$wnd.countryLabel = '';
//		alert("$wnd.countryID: " + $wnd.countryID);

	}-*/;

	public static HorizontalPanel getPanel() {
		return panel;
	}




}
