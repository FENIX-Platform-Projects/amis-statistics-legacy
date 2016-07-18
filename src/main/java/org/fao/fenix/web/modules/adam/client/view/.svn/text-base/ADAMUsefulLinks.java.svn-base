package org.fao.fenix.web.modules.adam.client.view;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMUsefulLinks {

	private static HashMap<String, String> externalLinksMap = new LinkedHashMap<String, String>();
	private static HashMap<String, String> internalLinksMap = new LinkedHashMap<String, String>();
	 
	public static void build() {  		
		
		 ADAMVisibilityController.restoreLinksVisibility();
		
		 if (RootPanel.get("LINKS").getWidgetCount() > 0)
				RootPanel.get("LINKS").remove(RootPanel.get("LINKS").getWidget(0));
			
			RootPanel.get("LINKS").add(buildHTMLPanel());
	}  
	
	 
	private static HTMLPanel buildHTMLPanel(){
		setExternalLinksMap();
		setInternalLinksMap();
		
		HTMLPanel html = new HTMLPanel(createHTML());
		html.setStyleName("align-left");

		return html;
	}
	
	
	private static String createHTML() {
		StringBuilder table = new StringBuilder();
		
		table.append("<div id='profile-box'>");
		table.append("<div class='links-content'>ADAM aims to provide information from various sources on aid flows in the agriculture, forestry, fishing and rural development sectors as well as other sectors in which FAO works. The below links offer more information to support those who are looking for comprehensive information related to global spending in agriculture.</div>");		
		table.append("</div>");
		
		table.append("<div id='profile-box'>");
	    table.append("<div class='profile-title'>External Links </div>");

		for(String name: externalLinksMap.keySet()){
			table.append("<div class='links-heading'><a href='"+externalLinksMap.get(name)+"' class='links' target='_blank'>"+name+"</a></div>");	
		}
		table.append("</div>");
			
		table.append("<div id='profile-box'>");
		table.append("<div class='links-content'>The below links will further support FAO staff in the development of Country Programming Frameworks (CPF) and resource mobilization strategies.</div>");		
		table.append("</div>");
		
		table.append("<div id='profile-box'>");
	    table.append("<div class='profile-title'>Internal FAO Links</div>");

		for(String name: internalLinksMap.keySet()){
			table.append("<div class='links-heading'><a href='"+internalLinksMap.get(name)+"' class='links' target='_blank'>"+name+"</a></div>");	
		}
		table.append("</div>");
		

		//System.out.println("table: " + table);
		return table.toString();
	}
	
	private static void setExternalLinksMap() {
		externalLinksMap.put("Aid Data", "http://www.aiddata.org/content/index");
		externalLinksMap.put("Donor Tracker", "http://donortracker.org/");
		externalLinksMap.put("Food and Agriculture Organization of the United Nations (FAO) Corporate Resource Mobilization", "http://www.fao.org/tc/resource-mobilization/rmhomepage/en/");
		externalLinksMap.put("Food Security Aid Map", "http://foodsecurity.ngoaidmap.org/");
		externalLinksMap.put("International Fund for Agricultural Development(IFAD)-Funded Projects and Programmes Map", "http://www.ifad.org/operations/gmaps/");
		externalLinksMap.put("The Organisation for Economic Co-operation and Development - Development Assistance Committee (OECD-DAC)", "http://www.oecd.org/dac/");
		externalLinksMap.put("UN Office for the Coordination of Humanitarian Affairs (UNOCHA) Financial Tracking Service", "http://fts.unocha.org/");
		externalLinksMap.put("World Bank: Mapping for Results", "http://maps.worldbank.org/");
		externalLinksMap.put("World Food Programme (WFP) Food Aid Information System", "http://www.wfp.org/fais/");
	}
		
	private static void setInternalLinksMap() {
		internalLinksMap.put("Country Briefs OnLine (BOL)", "http://countrybriefs.fao.org");
		internalLinksMap.put("Country Programming Framework (CPF) Intranet", "http://intranet.fao.org/cpf");
		internalLinksMap.put("Field Programme Management Information System (FPMIS)", "https://extranet.fao.org/fpmis/");
		internalLinksMap.put("Resource Mobilization Intranet", "http://intranet.fao.org/rm");
	}
}

