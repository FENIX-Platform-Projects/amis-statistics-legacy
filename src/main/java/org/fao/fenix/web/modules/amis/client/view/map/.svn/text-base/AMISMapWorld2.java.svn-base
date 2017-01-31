package org.fao.fenix.web.modules.amis.client.view.map;

import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.HTML;

public class AMISMapWorld2 {
	
	static ContentPanel panel;
	
	static String imagePath;
	
	public AMISMapWorld2() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setHeight(440); 

	}
	
	public static void buildMap(String path, String defaultMap) {
		panel.removeAll();
		imagePath = path;	
		panel.add(buildMapHTML(defaultMap));
//		panel.add(buildLegendHTML());
		panel.layout();
	}
	

	
	
//	public static HTML buildLegendHTML() { 
//		HTML html = new HTML();
//		StringBuffer legend = new StringBuffer();
//	
//		legend.append("<div align=\"LEFT\" id=\"legend\">");
//		legend.append("<table width=\"500\" border=\"0\">");
//		legend.append("<tr>");
//		legend.append("<td width=\"20\"  bgcolor=\"#518cc5\">&nbsp;</td>");
//		legend.append("<td width=\"166\" class=\"legend_text\">&nbsp;G20 Countries</td>");
//		legend.append("<td width=\"20\" bgcolor=\"#e6a524\">&nbsp;</td>");
//		legend.append("<td width=\"476\" class=\"legend_text\">&nbsp;Invited Member Countries</td>");
//		legend.append("</tr>");
//		legend.append("</table>");
//		legend.append("</div>");
//		
//		html.setHTML(legend.toString());
//		return html;
//		
//	}
	
	public static HTML buildMapHTML(final String defaultMap) {

		
		System.out.println("AMISMapWorld buildMap");

		
		HTML mapHtml = new HTML();
		StringBuffer map = new StringBuffer();

		
		String mapID = String.valueOf((Math.random() * 10));
		mapID = mapID.replace(".", "");
		
		System.out.println("mapID: " + mapID);
		
		map.append("<div class=\"zindex\">");
		map.append("<MAP name='"+ mapID +"'> ");
		
		// 9, ARGENTINA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('ARGENTINA', '9', 'Argentina', event)\"  " +
//				   " onClick=\"setCountryID('ARGENTINA', '9', 'Argentina', event)\" "+		
				   " coords=\"173,318,169,329,170,346,173,365,178,380,183,395,192,405,201,411,214,412,215,406,206,404,200,397,204,390,200,381,203,374,205,368,213,363,214,356,207,345,202,336,202,327,193,319,180,312,174,318\" " +
				   " />");

		// 10, AUSTRALIA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('AUSTRALIA', '10', 'Australia', event)\"  " +
				   " coords=\"649,352,656,329,669,315,692,302,704,295,715,293,720,287,733,290,727,298,737,305,747,287,752,290,756,306,763,328,742,351,729,363,710,379,704,356,683,349,651,354\" " +
				   " />");
		
		// 21, BRAZIL
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('BRAZIL', '21', 'Brazil', event)\"  " +
				   " coords=\"160,250,156,260,147,274,155,287,168,291,183,297,195,309,203,321,207,329,207,335,221,351,229,338,234,328,246,319,253,299,257,280,259,269,239,260,219,249,206,238,178,241,160,251\" " +
				   " />");
		
		// 138, MEXICO
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('MEXICO', '138', 'Mexico', event)\"  " +
				   " coords=\"122,197,116,206,107,209,104,217,90,214,74,208,64,200,55,190,49,170,63,165,84,168,100,184,99,196,104,200,115,193,121,198\" " +
				   " />");
		
		// 231, USA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('USA', '231', 'United States of America', event)\"  " +
				   " coords=\"103,181,117,176,129,175,135,184,146,186,151,169,170,157,188,141,201,134,214,128,205,118,183,125,158,132,150,128,138,120,85,118,58,122,39,143,42,157,96,173\" " +
				   " />");
		
		
		// 231, USA-ALASKA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('USA', '231', 'United States of America', event)\"  " +
				   " coords=\"59,93,93,57,62,53,38,61,27,67,16,78,4,86,1,107,34,97,59,93\" " +
				   " />");
		
		// 33, CANADA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('CANADA', '33', 'Canada', event)\"  " +
				   " coords=\"95,60,108,55,120,57,131,44,154,33,209,23,246,19,261,25,227,41,223,49,236,76,225,85,236,109,232,128,188,118,159,127,132,113,71,116,62,114,66,94,95,60\" " +
				   " />");
		
		// 202, SOUTH AFRICA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('SOUTH_AFRICA', '202', 'South Africa', event)\"  " +
				   " coords=\"395,339,397,353,417,356,435,346,449,332,440,314,417,321,402,328,395,339\" " +
				   " />");
		
		// 159, NIGERIA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('NIGERIA', '159', 'Nigeria', event)\"  " +
				   " coords=\"360,240,363,218,379,216,395,216,396,229,387,240,378,247,360,241\" " +
				   " />");
		
	
		// 59, EGYPT
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('EGYPT', '59', 'Egypt', event)\"  " +
				   " coords=\"418,166,418,195,450,198,449,184,443,170,429,165,418,166\" " +
				   " />");
		
		// 106, ITALY
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('ITALY', '106', 'Italy', event)\"  " +
				   " coords=\"377,140,373,145,383,151,396,155,400,148,401,139,389,134,386,126,377,127,373,132,377,139\" " +
				   " />");
		
		// , FRANCE (no code available for the PSD)
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('FRANCE', '', 'France', event)\"  " +
				   " coords=\"370,136,356,136,348,128,340,118,354,116,361,113,369,119,371,133\" " +
				   " />");
		
		// , GERMANY (no code available for the PSD)
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('GERMANY', '', 'Germany', event)\"  " +
				   " coords=\"371,118,370,110,376,104,384,104,388,112,383,116,386,121,371,121\" " +
				   " />");
		
		// 203, SPAIN
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('SPAIN', '203', 'Spain', event)\"  " +
				   " coords=\"349,135,334,133,331,137,337,140,338,154,349,155,357,147,362,139,349,134\" " +
				   " />");
		
		// 229, UK
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('UK', '229', 'United Kingdom', event)\"  " +
				   " coords=\"340,114,338,102,340,92,352,91,359,103,361,109,351,114,340,114\" " +
				   " />");
		
		// 230, Ukraine
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('UKRAINE', '230', 'Ukraine', event)\"  " +
				   " coords=\"406,118,408,113,415,109,424,111,431,110,438,114,445,117,447,122,441,129,425,128,406,120\" " +
				   " />");
		
		// 223 , TURKEY
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('TURKEY', '223', 'Turkey', event)\"  " +
				   " coords=\"419,138,420,149,431,157,452,154,465,152,455,140,439,139,420,138\" " +
				   " />");
		
		// ,
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('', '', '', event)\"  " +
				   " coords=\"\" " +
				   " />");
		
		// 194, Saudi Arabia
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('SAUDI_ARABIA', '194', 'Saudi Arabia', event)\"  " +
				   " coords=\"452,165,469,172,480,178,495,193,504,192,500,202,481,207,469,207,459,196,453,183,449,175,451,166\" " +
				   " />");
		
		
		// 108, KAZAKHSTAN
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('KAZAKHSTAN', '108', 'Kazakhstan', event)\"  " +
				   " coords=\"481,140,467,132,465,123,462,115,478,110,490,113,491,104,508,101,522,104,542,114,554,117,552,124,548,137,525,142,509,135,495,130,495,138,481,141\" " +
				   " />");
		
		// 100, INDIA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('INDIA', '100', 'India', event)\"  " +
				   " coords=\"551,165,540,180,538,190,547,198,557,214,569,234,577,226,577,212,590,197,608,190,610,180,587,181,551,165\" " +
				   " />");
		
		
		// 216, THAILAND
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('THAILAND', '216', 'Thailand', event)\"  " +
				   " coords=\"624,200,621,207,626,218,625,231,634,238,640,239,635,223,641,217,644,214,637,206,624,199\" " +
				   " />");
		
		// 237, VIETNAM
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('VIETNAM', '237', 'Vietnam', event)\"  " +
				   " coords=\"632,192,644,190,646,201,654,210,658,225,653,229,644,231,650,221,648,211,635,199,632,193\" " +
				   " />");
		
		// 117, KOREA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('KOREA', '117', 'Korea, Republic of', event)\"  " +
				   " coords=\"671,147,675,162,682,160,685,154,680,148,669,145\" " +
				   " />");
		
		// 110, JAPAN
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('JAPAN', '110', 'Japan', event)\"  " +
				   " coords=\"690,130,701,128,712,138,716,151,715,162,704,168,690,170,687,163,688,155,695,150,695,141,686,136,690,129\" " +
				   " />");
		
		// 41, CHINA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('CHINA', '41', 'China (Mainland)', event)\"  " +
				   " coords=\"557,120,555,134,539,145,550,158,559,158,562,167,580,174,593,176,603,173,613,177,623,186,629,194,646,189,656,194,670,184,670,171,661,162,652,142,668,133,660,122,644,115,632,107,629,120,628,131,614,139,587,137\" " +
				   " />");
		
		// 185, RUSSIA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('RUSSIA', '185', 'Russian Federation', event)\"  " +
				   " coords=\"414,59,425,64,441,62,452,56,466,49,488,43,508,36,548,32,597,40,624,49,670,53,723,72,721,86,716,114,694,106,686,90,663,93,665,101,690,118,679,127,655,109,638,102,625,114,606,119,557,114,499,99,483,106,465,111,460,127,452,130,434,108,419,94,415,58\" " +
				   " />");

		
		
		// 171, PHILIPPINES
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('PHILIPPINES', '171', 'Philippines', event)\"  " +
				   " coords=\"682,203,688,204,691,212,696,215,702,221,706,227,708,238,700,241,690,236,680,226,681,204\" " +
				   " />");
		
		// 101, INDONESIA
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('INDONESIA', '101', 'Indonesia', event)\"  " +
				   " coords=\"619,239,627,240,637,249,650,257,659,252,674,250,682,243,695,249,712,249,743,257,749,266,745,281,710,284,674,284,638,271,618,240\" " +
				   " />");
		
		

		
		map.append("</MAP> ");
		
		map.append("<IMG src='"+ imagePath +"' Usemap='#"+ mapID +"' >"); 
		map.append("</div> ");

		mapHtml.setHTML(map.toString());
		
		mapHtml.addMouseMoveHandler(new MouseMoveHandler() {

			public void onMouseMove(MouseMoveEvent arg0) {
				changeImageJS(defaultMap);
			}
		});
		
		mapHtml.addMouseOutHandler(new MouseOutHandler() {
	
			public void onMouseOut(MouseOutEvent arg0) {
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
			 imagePath = "amis-images/map-images/"+ countryFile + "_" + highlighted + ".gif";
//			 imagePath = "amis-images/map-images/"+ countryFile + "_" + highlighted + ".png";
//			 defaultMap = countryID + "_" + highlighted;
		}
		else {
			 imagePath = "amis-images/map-images/"+ defaultMap + ".gif";
		}

		System.out.println("changeImagePath: " + imagePath);
		System.out.println("defaultMap: " + defaultMap);
		buildMap(imagePath, defaultMap);
	}
	
	public static native void changeImageJS(String defaultMap)/*-{
	
	  	var countryFile = $wnd.countryFile;
		  	 
	  	if ( countryFile != '' ) {
	  		//alert(countryFile + " change image is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld2::changeImagePath(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(countryFile, "HIGHLIGHTED", defaultMap);
	  	}
	  	else{
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld2::changeImagePath(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(null, null, defaultMap);
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
		
		String defaultMap = countryFile + "_HIGHLIGHTED";
		String imagePath = "amis-images/map-images/"+ defaultMap + ".gif";
//		String imagePath = "amis-images/map-images/"+ defaultMap + ".png";


		System.out.println("selectCountry: " + imagePath);
		System.out.println("defaultMap: " + defaultMap);
		buildMap(imagePath, defaultMap);
		AMISHome.getDatasetViewAgent(countryID, countryLabel);
	}

	public static native void getSelectedCountryJS(String defaultMap)/*-{
	
	  
	  	var countryID = $wnd.countryID;
	  	var countryFile = $wnd.countryFile;
	  	var countryLabel = $wnd.countryLabel;

	  	if ( countryID != '' ) {
//	  		alert(countryID + " selectCountry is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld2::selectCountry(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(countryFile, countryID, countryLabel);
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

	public static ContentPanel getPanel() {
		return panel;
	}

	
	
	
}
