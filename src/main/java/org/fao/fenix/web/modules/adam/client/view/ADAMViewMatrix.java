package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceAsync;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMViewMatrix {
	
	/**
	 * 
	 *  This creates the Countries in the Columns and The ORs chart
	 *  
	 * @param rvo
	 * @return
	 */
	public static SelectionListener<MenuEvent> showChartRecipientsORs(final ADAMResultVO rvo, final String type) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
			
				showChartRecipientORsAgent(rvo, type);
				
				
			}

		};
	}
	
	private static void showChartRecipientORsAgent(final ADAMResultVO rvo, String type) {
		// CENTER intersection
		VennIntersectionsBean vennIntersectionABC = rvo.getVennBean().getVennGraphBeanVO().getIntersection("abc");
		
		// FAO/Countries intersection
		VennIntersectionsBean vennIntersectionAB = rvo.getVennBean().getVennGraphBeanVO().getIntersection("ab");
		
		LinkedHashMap<String, String> codesHM = new LinkedHashMap<String, String>();
		
		Map<String, String> codes = new TreeMap<String, String>();
		
		if ( vennIntersectionABC.getIsIntersected()) {
		
			for(String code : vennIntersectionABC.getAggregatedDacCodes()) {
				codes.put(code, rvo.getPriorities().get(code));			
			}
		}
		
		if ( vennIntersectionAB.getIsIntersected()) {

			for(String code : vennIntersectionAB.getAggregatedDacCodes()) {
				codes.put(code, rvo.getPriorities().get(code));			
			}
		}
		
		
		TreeMap<String, String> a = ADAMController.sortByKeys(codes);

		for(String code : a.keySet()) {
			codesHM.put(code, a.get(code));
		}
		
		System.out.println("CODEHM: " + codesHM);
		
		System.out.println(" rvo.getCountriesPriorities(): " +  rvo.getCountriesPriorities());
		
		
		
		
		
		
//		showTableRecipientsOrsAgent(codesHM, rvo.getCountriesPriorities());
		shoChartRecipientsOrsAgentGWT(codesHM, rvo.getCountriesPriorities());
	}
	
	private static void shoChartRecipientsOrsAgentGWT(LinkedHashMap<String, String> codesHM, Map<String, Map<String, String>> recipients) {
		
		ADAMResultVO vo = new ADAMResultVO();
		
		
		
		// get the countries list
		Map<String, String> recipientsList = new LinkedHashMap<String, String>();
		
		
		for (String code : ADAMBoxMaker.countriesSelected.keySet()) {
			System.out.println("CODE: " + code);

			recipientsList.put(ADAMController.getCode(code), ADAMBoxMaker.countriesSelected.get(code));
		}
		
		LinkedHashMap<String, String> recipientsSorted = ADAMController.sortByValues(recipientsList);



		/** For every country **/
		Map<String, Integer> recipientsCounting = new HashMap<String, Integer>();
		
		LinkedHashMap<String, Map<String, Double>> chartValues = new LinkedHashMap<String, Map<String,Double>>();


		Map<String, Double> value = new LinkedHashMap<String, Double>();
		for(String or : codesHM.keySet()) {
			
			Integer sum = getRowValue(or, recipientsSorted, recipients, recipientsCounting);
			value.put(or, Double.valueOf(sum));
			
			
//			contents.add(createHtmlRowRecipientsORsGWT(or, recipientsSorted, recipients, recipientsCounting));
		}
		chartValues.put("values", value);
		
		System.out.println("chartvalues: " + chartValues);
		
		vo.setChartValues(chartValues);
		
		
		// query vo (TEST)
		final ADAMQueryVO qvo = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.BAR.name(), "Most Financed Sectors", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT),"", "", 10);	
		String title = "Organizational Results per Recipient Countries";
		vo.setTitle(title);
		
		
		ADAMServiceEntry.getInstance().createChartFromRVO(qvo, vo, new AsyncCallback<ADAMResultVO>() {
			
			@Override
			public void onSuccess(ADAMResultVO arg0) {


				buildChartWindow(qvo, arg0);
				
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
	private static void buildChartWindow(ADAMQueryVO qvo, ADAMResultVO vo) {
		Window window = new Window();
		ContentPanel summaryPanel = new ContentPanel();
		summaryPanel.setScrollMode(Scroll.AUTO);				
	
		
		window.setHeading("Organizational Results per Recipient Countries");
		window.setAutoHeight(true);
		window.setAutoWidth(true);
//		window.setWidth(Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH));
		window.setScrollMode(Scroll.AUTO);
		
//		VerticalPanel chartPanel = ADAMChartMaker.buildSmallChart(vo, "", "", null);
		
		VerticalPanel chartPanel = ADAMChartMaker.buildBigChart(qvo, vo, "", "", null);


		window.add(chartPanel);


		window.layout();
		window.show();
	}
	
	private static Integer getRowValue(String or, LinkedHashMap<String, String> recipientsSorted, Map<String, Map<String, String>> recipients, Map<String, Integer> countriesCounting) {

		
		Integer total = new Integer(0);
		
		for(String recipient : recipientsSorted.keySet()) {
			Boolean added = false;
			// test or
			if ( recipients.containsKey(or)) {
				if ( recipients.get(or).containsKey(recipient) ) {
					addCounting(countriesCounting, recipient);
					total++;
					added = true;
				}
			}
			// test so
			else {	
				String so = String.valueOf(or.charAt(0));
				if ( recipients.get(so).containsKey(recipient) ) {
					addCounting(countriesCounting, recipient);
					total++;
				}
			}
		}

		return total;
	}
	
	

	/**
	 * 
	 *  This creates the Countries in the Columns and The ORs in the Rows
	 *  
	 * @param rvo
	 * @return
	 */
	public static SelectionListener<MenuEvent> showTableRecipientsORs(final ADAMResultVO rvo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				
				showTableRecipientsORsAgent(rvo);
			}

		};
	}
	
	public static SelectionListener<ButtonEvent> showTableRecipientsORsButton(final ADAMResultVO rvo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				showTableRecipientsORsAgent(rvo);
			}

		};
	}
	
	private static void showTableRecipientsORsAgent(final ADAMResultVO rvo) {
		// CENTER intersection
		VennIntersectionsBean vennIntersectionABC = rvo.getVennBean().getVennGraphBeanVO().getIntersection("abc");
		
		// FAO/Countries intersection
		VennIntersectionsBean vennIntersectionAB = rvo.getVennBean().getVennGraphBeanVO().getIntersection("ab");
		
		LinkedHashMap<String, String> codesHM = new LinkedHashMap<String, String>();
		
		Map<String, String> codes = new TreeMap<String, String>();
		
		if ( vennIntersectionABC.getIsIntersected()) {
		
			for(String code : vennIntersectionABC.getAggregatedDacCodes()) {
				codes.put(code, rvo.getPriorities().get(code));			
			}
		}
		
		if ( vennIntersectionAB.getIsIntersected()) {

			for(String code : vennIntersectionAB.getAggregatedDacCodes()) {
				codes.put(code, rvo.getPriorities().get(code));			
			}
		}
		
		
		TreeMap<String, String> a = ADAMController.sortByKeys(codes);

		for(String code : a.keySet()) {
			codesHM.put(code, a.get(code));
		}
		
		System.out.println("CODEHM: " + codesHM);
		
		System.out.println(" rvo.getCountriesPriorities(): " +  rvo.getCountriesPriorities());
		
		
		
		
		
		
//		showTableRecipientsOrsAgent(codesHM, rvo.getCountriesPriorities());
		showTableRecipientsOrsAgentGWT(codesHM, rvo.getCountriesPriorities());
	}
	
	private static void showTableRecipientsOrsAgentGWT(LinkedHashMap<String, String> codesHM, Map<String, Map<String, String>> recipients) {
		
		ADAMResultVO vo = new ADAMResultVO();
		
		// get the countries list
		Map<String, String> recipientsList = new LinkedHashMap<String, String>();
		
		
		for (String code : ADAMBoxMaker.countriesSelected.keySet()) {
			System.out.println("CODE: " + code);

			recipientsList.put(ADAMController.getCode(code), ADAMBoxMaker.countriesSelected.get(code));
		}
		
		LinkedHashMap<String, String> recipientsSorted = ADAMController.sortByValues(recipientsList);

		List<String> headers = new ArrayList<String>();
		List<List<String>> contents = new ArrayList<List<String>>();

		//headers.add("ORs/Recipients");
		headers.add("Organizational Result");
		headers.add("Short OR Description");
		
		for (String key : recipientsSorted.keySet()) {
			headers.add(recipientsSorted.get(key));
		}
		headers.add("Total");
		
		
		System.out.println("headers: " + headers);



		/** For every country **/
		Map<String, Integer> recipientsCounting = new HashMap<String, Integer>();

		
		for(String or : codesHM.keySet()) {
			contents.add(createHtmlRowRecipientsORsGWT(or, codesHM.get(or), recipientsSorted, recipients, recipientsCounting));
		}
		
		vo.setTableHeaders(headers);
		vo.setTableContents(contents);

		
		// ADD totals
		contents.add(createHtmlSumRecipientsORs(recipientsSorted, recipientsCounting));
		
		String title = "Organizational Results per Recipient Countries";
		vo.setTitle(title);

		buildMatrixWindow(vo);
	}
	
	private static void buildMatrixWindow(ADAMResultVO vo) {
		Window window = new Window();
		ContentPanel summaryPanel = new ContentPanel();
		summaryPanel.setScrollMode(Scroll.AUTO);				
	
		
		window.setHeading("Organizational Results per Recipient Countries");
		window.setAutoHeight(true);
		window.setAutoWidth(true);
//		window.setWidth(Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH));
		window.setScrollMode(Scroll.AUTO);

		window.add(ADAMTableMaker.buildBigTableExpandedMenu(vo, "", "", null, false));
		window.add(ADAMTableMaker.buildBigTableExpandedGrid(vo, "", "", null, false));

		window.layout();
		window.show();
	}
	
	private static void showTableRecipientsOrsAgent(LinkedHashMap<String, String> codesHM, Map<String, Map<String, String>> recipients) {
		StringBuffer table = new StringBuffer();
		
		table.append("<div class='small-content'> ");
		table.append("<TABLE BORDER='1' BGCOLOR='#FFFFFF' WIDTH='100%' CELLPADDING='2' CELLSPACING='0'>");
		
		
		
		// get the countries list
		Map<String, String> recipientsList = new LinkedHashMap<String, String>();

		
		for (String code : ADAMBoxMaker.countriesSelected.keySet()) {
			System.out.println("CODE: " + code);
			
			recipientsList.put(ADAMController.getCode(code), ADAMBoxMaker.countriesSelected.get(code));
		}
		
		LinkedHashMap<String, String> recipientsSorted = ADAMController.sortByValues(recipientsList);

		
		System.out.println("countries priorities: " + recipients);
		System.out.println("recipientsList: " + recipientsList);
		
		createHtmlHeadersLabel(table, recipientsSorted);
	 

		/** For every country **/
		Map<String, Integer> recipientsCounting = new HashMap<String, Integer>();

		
		for(String or : codesHM.keySet()) {
			createHtmlRowRecipientsORs(table, or, recipientsSorted, recipients, recipientsCounting);
		}
		
		
		// ADD totals
		createHtmlSumRecipientsORs(table, recipientsSorted, recipientsCounting);
		
		table.append("</TABLE>");
		table.append("</div>");
	
		
		System.out.println("TABLE: " + table.toString());
		
		
		Window window = new Window();
//		HTML htmlTitle = new HTML("<div class='link-title'> ORs </div>");
		
		
		VerticalPanel summaryPanel = new VerticalPanel();
		summaryPanel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		summaryPanel.setScrollMode(Scroll.AUTO);	
		summaryPanel.add(new Html(table.toString()));

		
//		window.add(htmlTitle);
		window.setHeading("Organizational Results per Recipient Countries");
		window.setHeight(450);
		window.setWidth(600);
		window.setScrollMode(Scroll.AUTO);

	


		window.add(summaryPanel);
		window.show();
	}
	
	private static List<String> createHtmlSumRecipientsORs(LinkedHashMap<String, String> map, Map<String, Integer> countingMap) {
		List<String> row = new ArrayList<String>();
		row.add("Total");
		
		
		
		for(String key : map.keySet()) {
			
			if ( countingMap.containsKey(key)) {
				row.add(countingMap.get(key).toString());
			}
			else 
				row.add("0");
		}


		return row;
	}
	
	private static String createHtmlSumRecipientsORs(StringBuffer table, LinkedHashMap<String, String> map, Map<String, Integer> countingMap) {
		table.append("<TR ALIGN='CENTER' CELLPADDING='2' CELLSPACING='0'>");
		
		table.append("<TD  width='35px' >");
		table.append("</TD>");
		
		for(String key : map.keySet()) {
			table.append("<TD  width='35px' BGCOLOR='#FF8040'>");
			if ( countingMap.containsKey(key)) {
				table.append(countingMap.get(key));
			}
			table.append("</TD>");
		}

		table.append("</TR>");
		
		return table.toString();
	}
	
	private static List<String> createHtmlRowRecipientsORsGWT(String or, String orDesc, LinkedHashMap<String, String> recipientsSorted, Map<String, Map<String, String>> recipients, Map<String, Integer> countriesCounting) {
		
		List<String> contents = new ArrayList<String>();
		
//		table.append("<TD  width='35px' BGCOLOR='#FF8040' >");
//		table.append("<B>" + or + "</B>");
//		table.append("</TD>");
		
		contents.add(or);
		
		//OR description
		contents.add(orDesc);
		
		
		Integer total = new Integer(0);
		
//		System.out.println(" recipients.keySet(): " +  recipients.keySet());

		
		for(String recipient : recipientsSorted.keySet()) {
//			table.append("<TD>");
			Boolean added = false;
			// test or
			if ( recipients.containsKey(or)) {
				if ( recipients.get(or).containsKey(recipient) ) {
					contents.add("X");
//					table.append("X");
					addCounting(countriesCounting, recipient);
					total++;
					added = true;
				}
			}
			// test so
			else {	
				String so = String.valueOf(or.charAt(0));
				if ( recipients.get(so).containsKey(recipient) ) {
					contents.add("X");
//					table.append("X");
					addCounting(countriesCounting, recipient);
					total++;
					added = true;
				}
			}
			
			if ( !added )
				contents.add("");
				
//			table.append("</TD>");
		}
		
		contents.add(total.toString());
//		table.append("<TD  width='35px' BGCOLOR='#FF8040'>");
//		table.append(total);
//		table.append("</TD>");
//
//		table.append("</TR>");
		return contents;
	}
	
	private static String createHtmlRowRecipientsORs(StringBuffer table, String or, LinkedHashMap<String, String> recipientsSorted, Map<String, Map<String, String>> recipients, Map<String, Integer> countriesCounting) {
		table.append("<TR ALIGN='CENTER' CELLPADDING='2' CELLSPACING='0'>");
		
		table.append("<TD  width='35px' BGCOLOR='#FF8040' >");
		table.append("<B>" + or + "</B>");
		table.append("</TD>");
		
		Integer total = new Integer(0);
		
//		System.out.println(" recipients.keySet(): " +  recipients.keySet());

		
		for(String recipient : recipientsSorted.keySet()) {
			table.append("<TD>");
			// test or
			if ( recipients.containsKey(or)) {
				if ( recipients.get(or).containsKey(recipient) ) {
					table.append("X");
					addCounting(countriesCounting, recipient);
					total++;
				}
			}
			// test so
			else {	
				String so = String.valueOf(or.charAt(0));
				if ( recipients.get(so).containsKey(recipient) ) {
					table.append("X");
					addCounting(countriesCounting, recipient);
					total++;
				}
			}
			table.append("</TD>");
		}
		
		
		table.append("<TD  width='35px' BGCOLOR='#FF8040'>");
		table.append(total);
		table.append("</TD>");

		table.append("</TR>");
		return table.toString();
	}
	
	
	/**
	 * 
	 *  This creates the OR in the Column and The Countries in the Rows
	 *  
	 * @param rvo
	 * @return
	 */
	public static SelectionListener<MenuEvent> showTableORRecipients(final ADAMResultVO rvo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				
				// CENTER intersection
				VennIntersectionsBean vennIntersectionABC = rvo.getVennBean().getVennGraphBeanVO().getIntersection("abc");
				
				// FAO/Countries intersection
				VennIntersectionsBean vennIntersectionAB = rvo.getVennBean().getVennGraphBeanVO().getIntersection("ab");
				
				LinkedHashMap<String, String> codesHM = new LinkedHashMap<String, String>();
				
				Map<String, String> codes = new TreeMap<String, String>();
				
				if ( vennIntersectionABC.getIsIntersected()) {
				
					for(String code : vennIntersectionABC.getAggregatedDacCodes()) {
						codes.put(code, rvo.getPriorities().get(code));			
					}
				}
				
				if ( vennIntersectionAB.getIsIntersected()) {

					for(String code : vennIntersectionAB.getAggregatedDacCodes()) {
						codes.put(code, rvo.getPriorities().get(code));			
					}
				}
				
				
				TreeMap<String, String> a = ADAMController.sortByKeys(codes);

				for(String code : a.keySet()) {
					codesHM.put(code, a.get(code));
				}
				
				System.out.println("CODEHM: " + codesHM);
				
				showTableORRecipientsAgent(codesHM, rvo.getCountriesPriorities());
			}

		};
	}
	
	private static void showTableORRecipientsAgent(LinkedHashMap<String, String> codesHM, Map<String, Map<String, String>> recipients) {
		StringBuffer table = new StringBuffer();
		
		table.append("<div class='small-content'> ");
		table.append("<TABLE BORDER='1' BGCOLOR='#FFFFFF' WIDTH='100%' CELLPADDING='2' CELLSPACING='0'>");
		
		
		
		// get the countries list
		Map<String, String> recipientsList = new LinkedHashMap<String, String>();
		
		
		for (String code : ADAMBoxMaker.countriesSelected.keySet()) {
			System.out.println("CODE: " + code);
			
			recipientsList.put(ADAMController.getCode(code), ADAMBoxMaker.countriesSelected.get(code));
		}
		

		System.out.println("countries priorities: " + recipients);
		System.out.println("recipientsList: " + recipientsList);
		
		createHtmlHeadersCode(table, codesHM);
	 

		/** For every country **/
		Map<String, Integer> orCounting = new HashMap<String, Integer>();
		
		LinkedHashMap<String, String> recipientsSorted = ADAMController.sortByValues(recipientsList);
		for(String country : recipientsSorted.keySet()) {
			createHtmlRowORsRecipients(table, country, recipientsList.get(country), codesHM, recipients, orCounting);
		}
		
		
		// ADD totals
		createHtmlSumORsRecipients(table, codesHM, orCounting);
		
		table.append("</TABLE>");
		table.append("</div>");
	
		
//		System.out.println("TABLE: " + table.toString());
		
		
		Window window = new Window();
//		HTML htmlTitle = new HTML("<div class='link-title'> ORs </div>");
		
		
		VerticalPanel summaryPanel = new VerticalPanel();
			summaryPanel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
			summaryPanel.setScrollMode(Scroll.AUTO);	
			summaryPanel.add(new Html(table.toString()));

		
//		window.add(htmlTitle);
		window.setHeading("Organizational Results per Recipient Countries");
		window.setHeight(450);
		window.setWidth(600);
		window.setScrollMode(Scroll.AUTO);

	


		window.add(summaryPanel);
		window.show();
	}
	
	

	
	
	private static String createHtmlRowORsRecipients(StringBuffer table, String code, String label, LinkedHashMap<String, String> ors, Map<String, Map<String, String>> recipients, Map<String, Integer> orCounting) {
		table.append("<TR ALIGN='CENTER' CELLPADDING='2' CELLSPACING='0'>");
		
		table.append("<TD  width='35px' BGCOLOR='#FF8040' >");
		table.append("<B>" + label + "</B>");
		table.append("</TD>");
		
		Integer total = new Integer(0);
		
		for(String or : ors.keySet()) {
			table.append("<TD>");
			// test or
			if ( recipients.containsKey(or)) {
				if ( recipients.get(or).containsKey(code) ) {
					table.append("X");
					addCounting(orCounting, or);
					total++;
				}
			}
			// test so
			else {	
				String so = String.valueOf(or.charAt(0));
				if ( recipients.get(so).containsKey(code) ) {
					table.append("X");
					addCounting(orCounting, or);
					total++;
				}
			}
				
			table.append("</TD>");
		}
		
		table.append("<TD BGCOLOR='#FF8040'>");
		table.append(total);
		table.append("</TD>");

		table.append("</TR>");
		return table.toString();
	}
	
	private static void addCounting(Map<String, Integer> map, String key) {
		Integer value = 1;
		if( map.containsKey(key)) {
			value = map.get(key);
			value++;
			
		}
		map.put(key, value);
	}
	
	private static String createHtmlHeadersLabel(StringBuffer table, LinkedHashMap<String, String> headers) {
		table.append("<TR ALIGN='CENTER' CELLPADDING='2' CELLSPACING='0'>");
		
		table.append("<TD width='20px' >");
		table.append("</TD>");
		
		for(String header : headers.keySet()) {
			table.append("<TD width='35px' BGCOLOR='#FF8040'>");
			table.append("<B>" + headers.get(header) + "</B>");
			table.append("</TD>");
		}

		table.append("</TR>");
		
		return table.toString();
	}
	
	private static String createHtmlHeadersCode(StringBuffer table, LinkedHashMap<String, String> headers) {
		table.append("<TR ALIGN='CENTER' CELLPADDING='2' CELLSPACING='0'>");
		
		table.append("<TD>");
		table.append("</TD>");
		
		for(String header : headers.keySet()) {
			table.append("<TD BGCOLOR='#FF8040'>");
			table.append("<B>" + header + "</B>");
			table.append("</TD>");
		}

		table.append("</TR>");
		
		return table.toString();
	}
	
	private static String createHtmlSumORsRecipients(StringBuffer table, LinkedHashMap<String, String> ors, Map<String, Integer> orcountring) {
		table.append("<TR ALIGN='CENTER' CELLPADDING='2' CELLSPACING='0'>");
		
		table.append("<TD>");
		table.append("</TD>");
		
		for(String or : ors.keySet()) {
			table.append("<TD BGCOLOR='#FF8040'>");
			if ( orcountring.containsKey(or)) {
				table.append(orcountring.get(or));
			}
			table.append("</TD>");
		}

		table.append("</TR>");
		
		return table.toString();
	}

	public static void test2(){
	  	System.out.println("aoisjdoaisjd");
	}
	
	
	public static native void getPriority()/*-{
		  	var intersection = $wnd.intersection;
		  	// alert(intersection);
		  	@org.fao.fenix.web.modules.adam.client.view.venn.ADAMVennPriorities::callPriorityAgent(Ljava/lang/String;)(intersection);
	}-*/;


	
	
}
