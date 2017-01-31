package org.fao.fenix.web.modules.adam.client.view.analyse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseDataController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseValidationController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.Element;

public class ADAMAnalysisListPanel extends LayoutContainer {
	public VerticalPanel panel;

	Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
	Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
	Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
	Map<String, String> subSectorsList = ADAMBoxMaker.subSectorsSelected;
	Map<String, String> channelList = ADAMBoxMaker.channelsSelected;

	HashMap<String, List<String>> filters;

    public HashMap<ADAMAnalyseVIEW, Map<String, String>> analysesMap = new LinkedHashMap<ADAMAnalyseVIEW, Map<String, String>>();

	Boolean isOpen = false;

	final String WANT_TO_ANALYSE_PREFIX = "I want to analyse ";
	final String WANT_TO_IDENTIFY_PREFIX = "I want to identify ";
	final String ANALYSE_PREFIX = "Analyse ";


	public ADAMAnalysisListPanel(HashMap<String, List<String>> filters) {
		panel = new VerticalPanel();
		panel.setStyleAttribute("background", "#FFFFFF");
		panel.setLayout(new FillLayout());
		panel.setScrollMode(Scroll.AUTO);
		//panel.setStyleAttribute("align", "center");
		//panel.setSize(WIDTH, HEIGHT);
		this.filters = filters;
		populateAnalysesMap();
	}

	private void populateAnalysesMap() {
		Map<String, String> analysis = new HashMap<String, String>();
		analysis.put("Venn Analysis", "the common priorities between recipient countries, resource partners and FAO and view funding allocations in the identified priority areas.");
		analysesMap.put(ADAMAnalyseVIEW.ANALYSE_VENN, analysis);

		analysis = new HashMap<String, String>();
		analysis.put("Priority Analysis", "the Government/FAO agreed priorities in sectors in which FAO works versus the national development priorities, and identify resource partners funding those priority areas.");
		analysesMap.put(ADAMAnalyseVIEW.ANALYSE_PRIORITIES, analysis);

		analysis = new HashMap<String, String>();
		analysis.put("Resource Partner Matrix", "resource partner spending in FAO related sectors  versus  Official Development Assistance (ODA).");
		analysesMap.put(ADAMAnalyseVIEW.ANALYSE_PARTNER_MATRIX, analysis);

		analysis = new HashMap<String, String>();
		analysis.put("FAO Comparative Advantage", "FAO's comparative advantage with respect to other implementing agencies.");
		analysesMap.put(ADAMAnalyseVIEW.ANALYSE_FAO_COMPARATIVE_ADVANTAGE, analysis);

		analysis = new HashMap<String, String>();
		analysis.put("Projects Analysis", "individual projects based on my selection.");
		analysesMap.put(ADAMAnalyseVIEW.ANALYSE_PROJECTS, analysis);

		analysis = new HashMap<String, String>();
		analysis.put("Implementing Agency Analysis", "the most active implementing agencies by sector and to which resource partners are contributing.");
		analysesMap.put(ADAMAnalyseVIEW.ANALYSE_IMPLEMENTING_AGENCIES, analysis);

	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		add(panel);
		build();
	}

	/*private void buildOriginal() {

		Html label = new Html();
		label.setHtml("<div style='text-align:left;padding:10px;'><span style='font-weight:bold; color:#000000; font-size:13px;'>Please click on one of the ADAM analysis types below:</span></div>");

		if(!analysesMap.isEmpty())
			panel.add(label);

		for(ADAMAnalyseVIEW key: analysesMap.keySet()){
			Map<String, String> analysis = analysesMap.get(key);

			for(String title: analysis.keySet())
				panel.add(buildAnalysisTitleDescriptionPanel(key.name(), title,getAnalysisDescription(key, false), ADAMAnalyseValidationController.setTypeAndValidateListener(key, filters, this)));
		}

		panel.getLayout().layout();

	}*/

	private void build() {

		Html label = new Html();
		label.setHtml("<div style='text-align:left;padding-left:8px;padding-bottom:10px;'><span style='font-weight:bold; color:#1D4589; font-size:13px;'>Please click on one of the options below:</span></div>");

		if(!analysesMap.isEmpty())
			panel.add(label);

		List<HorizontalPanel> list = new ArrayList<HorizontalPanel>();

		for(ADAMAnalyseVIEW key: analysesMap.keySet()){
			Map<String, String> analysis = analysesMap.get(key);
			for(String title: analysis.keySet()) {
				list.add(buildAnalysisPanel(key.name(), title.toUpperCase(),getAnalysisDescription(key, false), ADAMAnalyseValidationController.setTypeAndValidateListener(key, filters, this)));
			}
		}

		VerticalPanel holder = new VerticalPanel();
		//holder.setStyleAttribute("padding-left", "20px");

		HorizontalPanel widgetsPanel = new HorizontalPanel();

		int counter = 1;
		for(HorizontalPanel item: list){
			widgetsPanel.add(item);

			if ( counter % 2 == 0 ) {
				holder.add(widgetsPanel);
				widgetsPanel = new HorizontalPanel();
			}

			counter++;

		}

		panel.add(holder);
		panel.getLayout().layout();

		}

	public static HorizontalPanel addHSpace(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}

	public static HorizontalPanel addVSpace(Integer height) {
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(height);
		return p;
	}

   public String getAnalysisTitle(ADAMAnalyseVIEW analysisType){
	String name = "";
	   if(analysesMap.containsKey(analysisType)) {
		   Map<String, String> analysisItem = analysesMap.get(analysisType);

		   for(String title: analysisItem.keySet()) {
			   name = title;
		   }
	   }else
		  name = "";

	   return name;
   }

   public String getAnalysisDescription(ADAMAnalyseVIEW analysisType, boolean isRelatedAnalysis){


		String description = "";

		if(isRelatedAnalysis)
			description = getAnalysePrefix();
		else {
			if(!analysisType.equals(ADAMAnalyseVIEW.ANALYSE_IMPLEMENTING_AGENCIES))
				description = getWantToAnalysePrefix();
			else
				description = getWantToIdentifyPrefix();
		}


		if(analysesMap.containsKey(analysisType)) {
			   Map<String, String> analysisItem = analysesMap.get(analysisType);

			   for(String title: analysisItem.keySet()) {
				   description += analysisItem.get(title);
			   }
		   }else
			   description += "";

		   return description;
	   }

 /*  private static HorizontalPanel buildAnalysisTitleDescriptionPanel(String analysisTypeId, String analysisTitle, String analysisDescription, Listener<BaseEvent> listener) {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleAttribute("background", "#E0E0E0");
		hPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		hPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		hPanel.setStyleAttribute("padding", "10px");
		hPanel.setSpacing(3);
		hPanel.setId(analysisTypeId);


		ClickHtml label = new ClickHtml();
		label.setId(analysisTypeId+"_LABEL");
		label.setHtml("<div style='text-align:left;'><span style='font-variant:small-caps; font-weight:bold; color:#1B65A4; font-size:13px; cursor:pointer'>" + analysisTitle+ ": </span> <span style='color:#474747;'>"+analysisDescription+"</span></div>");
		label.addListener(Events.OnClick, listener);
		hPanel.add(label);

		ClickHtml label = new ClickHtml();
		label.setId(analysisTypeId+"_LABEL");
		label.setStyleAttribute("text-align", "left");
		label.setStyleAttribute("font-variant", "small-caps");
		label.setStyleAttribute("font-weight", "bold");
		label.setStyleAttribute("color", "#1B65A4");
		label.setStyleAttribute("font-size", "13px");
		label.setStyleAttribute("cursor", "pointer");
		label.setHtml(analysisTitle+ ":");
		label.addListener(Events.OnClick, listener);
		hPanel.add(label);

		ClickHtml desc = new ClickHtml();
		desc.setId(analysisTypeId+"_DESC");
		desc.setStyleAttribute("text-align", "left");
		desc.setStyleAttribute("color", "#474747");
		desc.setHtml(analysisDescription);
		//desc.addListener(Events.OnClick, listener);

		hPanel.add(label);
		hPanel.add(desc);

		return hPanel;
	}*/

   private static HorizontalPanel buildAnalysisPanel(String analysisTypeId, String analysisTitle, String analysisDescription, Listener<BaseEvent> listener) {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleAttribute("background", "#FFFFFF");
		hPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		hPanel.setVerticalAlign(VerticalAlignment.TOP);
		//hPanel.setStyleAttribute("padding", "10px");
		hPanel.setSpacing(5);
		hPanel.setId(analysisTypeId);
		hPanel.setWidth(560);
		//hPanel.setHeight(180);
		hPanel.setHeight(130);

		VerticalPanel vPanelHolder1 = new VerticalPanel();
		vPanelHolder1.setVerticalAlign(VerticalAlignment.TOP);
		vPanelHolder1.setId(analysisTypeId);
		vPanelHolder1.setStyleAttribute("padding-left", "10px");


		//Title Holder
		VerticalPanel labelHolder = new VerticalPanel();
		labelHolder.setStyleAttribute("background", "#003A6C");

		//Title
		ClickHtml label = new ClickHtml();
		label.setId(analysisTypeId+"_LABEL");
		label.setStyleName("analysisTitle");
		label.setHtml(analysisTitle);
		label.setTitle("Go to");
		label.addListener(Events.OnClick, listener);
		labelHolder.add(label);

		//Description Holder
		VerticalPanel descHolder = new VerticalPanel();
		descHolder.setWidth(370);

		//Description
		ClickHtml desc = new ClickHtml();
		desc.setId(analysisTypeId+"_DESC");
		desc.setStyleName("analysisDesc");
		desc.setHtml(analysisDescription);
		desc.setStyleAttribute("padding-top", "5px");
		descHolder.add(desc);

		vPanelHolder1.add(labelHolder);
		vPanelHolder1.add(descHolder);

		//Image Holder
		VerticalPanel vPanelHolder2= new VerticalPanel();
		vPanelHolder2.setVerticalAlign(VerticalAlignment.TOP);

		//Image
		VerticalPanel imgHolder = new VerticalPanel();
		imgHolder.setStyleAttribute("border", "solid 1px gray");
		imgHolder.setStyleAttribute("padding-top", "3px");

		ClickHtml img = new ClickHtml();
		img.setHtml("<img src='adam-images/screenshots/"+analysisTypeId+".jpg' border='0' width='150px' height='100px' title='Go to "+analysisTitle+"'/>");
		img.setStyleAttribute("cursor", "pointer");

		//img.setStyleAttribute("border", "solid 2px black");
		imgHolder.add(img);
		vPanelHolder2.add(imgHolder);


		hPanel.add(vPanelHolder2); // contains image
		hPanel.add(vPanelHolder1); // contains title and description


		return hPanel;
	}

   public static VerticalPanel buildSelectedAnalysisTitleDescriptionPanel(String analysisTitle, String analysisDescription, HashMap<String, List<String>> filters) {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setStyleAttribute("background", "#003A6C");
		vPanel.setSpacing(6);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		hPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		//hPanel.setSpacing(5);

		Html label = new Html("<div style='text-align:left;'><span style='font-variant:small-caps; font-weight:bold; color:#EAB040; font-size:13px;'>" + analysisTitle+ "</span></div>"); //
		label.setWidth(ADAMConstants.BIG_MENU_GAP_WIDTH);
		hPanel.add(label);

		Button back = new Button("<font color='#1D4589'><b>Back to Options<b></font>");
		back.setToolTip("Back to the list of Analysis Options");
		back.setIconStyle("backIcon");
		back.addSelectionListener(ADAMAnalyseDataController.returnBackToAnalysesList(filters));

		hPanel.add(back);
		vPanel.add(hPanel);

		Html description = new Html("<div style='text-align:left;'><span style='color:#FFFFFF;font-weight:bold;'>"+analysisDescription+"</span></div>");
		vPanel.add(description);

		return vPanel;
	}

   public static HorizontalPanel buildRelatedAnalysisPanel(String relatedAnalysisTitle, String relatedAnalysisDescription, HashMap<String, List<String>> filters, ADAMAnalyseVIEW type) {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleAttribute("background", "#E0E0E0");
		hPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		hPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		hPanel.setSpacing(4);

		Html label = new Html("<div style='text-align:left;'><span style='font-variant:small-caps; font-weight:bold;color:#003A6C;'>Related </span><span style='font-variant:small-caps; font-weight:bold;color:#333333;'>"+relatedAnalysisTitle+": </span><span style='color: #1D4589;'>" +relatedAnalysisDescription+"</span></div>"); //
		hPanel.add(label);

		Button relatedAnalysis = new Button("Show "+relatedAnalysisTitle);
		relatedAnalysis.setToolTip("Show "+relatedAnalysisTitle);
		relatedAnalysis.setIconStyle("showIcon");
		relatedAnalysis.addSelectionListener(ADAMAnalyseValidationController.openRelatedAnalysis(filters, type));

		hPanel.add(relatedAnalysis);

		return hPanel;
	}

	private String getWantToAnalysePrefix() {
		return WANT_TO_ANALYSE_PREFIX;
	}

	private String getWantToIdentifyPrefix() {
		return WANT_TO_IDENTIFY_PREFIX;
	}

	private String getAnalysePrefix() {
		return ANALYSE_PREFIX;
	}

}
