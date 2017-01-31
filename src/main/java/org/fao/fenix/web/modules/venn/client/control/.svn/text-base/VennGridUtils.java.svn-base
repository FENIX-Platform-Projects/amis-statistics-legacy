package org.fao.fenix.web.modules.venn.client.control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.venn.client.view.VennProjectsGridPanel;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGridMD;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.ui.HTML;

public class VennGridUtils {
	
	
	// if aggregationHader == null, the aggregation is made on column0 (the venn intersection)
	public static List<TabItem> createSummaryProjectsTab(List<VennProjectsBean> intersactions, VennGraphBeanVO vennGraphBean, String aggregationHeader) {
		
		List<TabItem> result = new ArrayList<TabItem>();
		
		// this is used for the center panel
		VerticalPanel centerPanel = new VerticalPanel();
		centerPanel.setSpacing(3);
		centerPanel.setWidth(850);
		centerPanel.setHeight(590);
//		centerPanel.setScrollMode(Scroll.AUTO);
		TabItem centerProjectsTabPanel = buildTabItem("Projects", 865, 610, Scroll.AUTO);
//		centerPanel.add(new HTML("<div> Projects intersected </div>"));
		centerProjectsTabPanel.add(centerPanel);

		// this is used for the portlet panel
		VerticalPanel portletPanel = new VerticalPanel();
		portletPanel.setSpacing(3);
		TabItem portletProjectsTabPanel = buildTabItem("Projects", 800, 210, Scroll.AUTO);
		portletProjectsTabPanel.setAutoWidth(true);
		portletProjectsTabPanel.add(portletPanel);
		
		portletPanel.setScrollMode(Scroll.AUTO);
		portletPanel.setWidth(800);
		portletPanel.setHeight(210);
		centerPanel.add(buildSummaryGridPanel(intersactions, vennGraphBean, aggregationHeader));
		portletPanel.add(buildSummaryGridPanel(intersactions, vennGraphBean, aggregationHeader));
		
		
		result.add(centerProjectsTabPanel);
		result.add(portletProjectsTabPanel);
		return result;
	}
	
	

	
	public static List<TabItem> createIntersectionProjects(List<VennProjectsBean> intersactions, VennGraphBeanVO vennGraphBean) {
		
		List<TabItem> result = new ArrayList<TabItem>();
		
		// this is used for the center panel
		VerticalPanel centerPanel = new VerticalPanel();
		centerPanel.setSpacing(3);
		centerPanel.setWidth(850);
		centerPanel.setHeight(590);
//		centerPanel.setScrollMode(Scroll.AUTO);
		TabItem centerProjectsTabPanel = buildTabItem("Projects", 865, 610, Scroll.AUTO);
//		centerPanel.add(new HTML("<div> Projects intersected </div>"));
		centerProjectsTabPanel.add(centerPanel);

		// this is used for the portlet panel
		VerticalPanel portletPanel = new VerticalPanel();
		portletPanel.setSpacing(3);
		TabItem portletProjectsTabPanel = buildTabItem("Projects", 800, 210, Scroll.AUTO);
		portletProjectsTabPanel.setAutoWidth(true);
		portletProjectsTabPanel.add(portletPanel);
		
		portletPanel.setScrollMode(Scroll.AUTO);
		portletPanel.setWidth(800);
		portletPanel.setHeight(210);
		for (VennProjectsBean intersaction : intersactions) {
//			System.out.println("intersavction: " + intersaction.getLabel());
			if (intersaction.getProjectsRows() != null) {
//				VennIntersectionsBean inter = vennBudgetBean.getIntersection(intersectionIdx);
				if (!intersaction.getProjectsRows().isEmpty()) {
//					System.out.println("contents" + intersaction.getProjectsRows());
					centerPanel.add(buildProjectIntersectionsGridPanel(intersaction, vennGraphBean));
					portletPanel.add(buildProjectIntersectionsGridPanel(intersaction, vennGraphBean));
				} 
			}
		}
		
		result.add(centerProjectsTabPanel);
		result.add(portletProjectsTabPanel);
		return result;
	}
	
	private static VerticalPanel buildProjectIntersectionsGridPanel(VennProjectsBean intersaction, VennGraphBeanVO vennGraphBean) {
		
//		DecimalFormat formatValue = new DecimalFormat("#.##");
		
		
		VerticalPanel gridContentPanel = new VerticalPanel();
//		gridContentPanel.setSpacing(5);
		gridContentPanel.setBorders(false);
		gridContentPanel.setAutoHeight(true);
		gridContentPanel.setAutoWidth(true);
		
		FieldSet fieldSet = new FieldSet();


//		HTML label = new HTML();
//		String str = "000000" + Integer.toHexString(intersaction.getColor()); //$NON-NLS-1$ 
//		String color = "#" + str.substring(str.length() - 6);
//		label.setHTML("<div style='color:" + color + "'><u>" + intersaction.getLabel() + "</u></div>");
		
		
		fieldSet.setHeading(vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel());
		fieldSet.setCheckboxToggle(true);
		fieldSet.setBorders(true);
		fieldSet.collapse();
		
//		gridContentPanel.add(label);
		

		VennProjectsGridPanel gridPanel = new VennProjectsGridPanel();
//		gridContentPanel.add(gridPanel.build("700px", intersaction.getHeaders().size(), intersaction.getProjectsRows().size()));
		fieldSet.add(gridPanel.build("700px", intersaction.getHeaders().size(), intersaction.getProjectsRows().size()));
		Grid<VennGridMD> grid = gridPanel.getGrid();

		// build panel
//		System.out.println("creating grid");
		Integer quantityIdx = 0;
		// headers
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
				// the +1 is because the fist header column is the id of the row
				// without the +1 if it missed the header ID
//				quantityIdx = i + 1;
				quantityIdx = i ;
			grid.getColumnModel().setColumnHeader(i, intersaction.getHeaders().get(i).getHeader());
		}

		List<Double> values = new ArrayList<Double>();
		Double sum = new Double(0);
		ListStore<VennGridMD> store = grid.getStore();
		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
//			System.out.println("->" + projectRow);
			VennGridMD md = new VennGridMD();
			// there is the id as first cell value
//			for (int i = 1; i < projectRow.size(); i++) {
			// if the there is no the id in the first cell value
			for (int i = 0; i < projectRow.size(); i++) {
//				md.addColumnValue(i - 1, projectRow.get(i));
				md.addColumnValue(i, projectRow.get(i));
				if (i == quantityIdx) {
					try {
//						System.out.println("projectRow.get(i): " + projectRow.get(i));
						Double value = Double.parseDouble(projectRow.get(i));
						values.add(value);
						sum = sum + value;
					} catch (NumberFormatException nfe) {
					}
				}
			}
//			System.out.println("add to list");
			store.add(md);
		}

		
		
		// TODO: to be translated the HTML
		// creating SUM AND AVG
		if ( sum == 0) {
			fieldSet.add(new HTML("<b>Budget projects sum: Not Available</b>"));
			fieldSet.add(new HTML("<b>Budget projects average: Not Available</b>"));
//			gridContentPanel.add(new HTML("<b>Budget projects sum: Not Available</b>"));
//			gridContentPanel.add(new HTML("<b>Budget projects average: Not Available</b>"));
		}
		else {
			fieldSet.add(new HTML("<b>Budget projects sum: " + shortValue(sum) + "</b>"));
			fieldSet.add(new HTML("<b>Budget projects average: " + shortValue(sum / values.size()) + "</b>"));
//			gridContentPanel.add(new HTML("<b>Budget projects sum: " + shortValue(sum) + "</b>"));
//			gridContentPanel.add(new HTML("<b>Budget projects average: " + shortValue(sum / values.size()) + "</b>"));
		}

		// add panel
		gridContentPanel.add(fieldSet);
		return gridContentPanel;
	}
	
	
	
	
	private static VerticalPanel buildSummaryGridPanel(List<VennProjectsBean> intersactions, VennGraphBeanVO vennGraphBean, String aggregationHeader) {
		VennProjectsGridPanel gridPanel = new VennProjectsGridPanel();
		VerticalPanel gridContentPanel = new VerticalPanel();
//		gridContentPanel.setSpacing(5);
		gridContentPanel.setBorders(false);
		gridContentPanel.setAutoHeight(true);
		gridContentPanel.setAutoWidth(true);
		

		
		// build grid structure

		Integer quantityIdx = 0;
		Integer gridColumns = 0;
		Integer gridRows = 0;
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
					for (int i = 0; i < intersaction.getHeaders().size(); i++) {	
						System.out.println("intersaction header: " + i + " | " + intersaction.getHeaders().get(i).getHeader());
						if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
							// the +1 is because the fist header column is the id of the row
							// without the +1 if it missed the header ID
//							quantityIdx = i + 1;
							quantityIdx = i + 1 ;
					}
					gridRows = gridRows + intersaction.getProjectsRows().size();
					gridColumns =  intersaction.getHeaders().size() + 1;
				}
				// +1 is the donor first column
				
			}
		}
		
		System.out.println("grid columns: " + gridColumns);
		System.out.println("grid rows: " + gridRows);
		
		
		// Build Grid
		gridPanel.buildSummaryGrid("700px", gridColumns, gridRows, 0, quantityIdx);
		Grid<VennGridMD> grid = gridPanel.getGrid();
		GroupingStore<VennGridMD> store = gridPanel.getStoreSummary();
		store.removeAll();
		if ( aggregationHeader == null)
			store.groupBy("column0");  
		else 
			store.groupBy(getAggregationIndex(intersactions, aggregationHeader));
		
		
		// for each intersection
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {					
					// for each intersection should be added a row
					// the first column contains the row name intersection
					// headers
					
					// Donor (TODO: multilanguage)
					grid.getColumnModel().setColumnHeader(0 , "Donor/Intersection");
					for (int i = 0; i < intersaction.getHeaders().size(); i++) {
						System.out.println("i: " + i);
						grid.getColumnModel().setColumnHeader(i + 1, intersaction.getHeaders().get(i).getHeader());
					}
					
					// setting the grid values
					// content
					for (List<String> projectRow : intersaction.getProjectsRows()) {
						VennGridMD md = new VennGridMD();
						md.addColumnValue(0, vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel());
						for (int i = 0; i < projectRow.size(); i++) {
							// +1 because of the donor column
							
							if ( (i +1) == quantityIdx) {
								try {
									md.addColumnValue(i + 1, Double.valueOf(projectRow.get(i)));
								}
								catch (Exception e) {
								}
							}
							else	
								md.addColumnValue(i + 1, projectRow.get(i));
						}
						store.add(md);
					}
					
				} 
			}
		}


		// add panel
		gridContentPanel.add(grid);
		return gridContentPanel;
	}
	
	
	private static String getAggregationIndex(List<VennProjectsBean> intersactions, String aggregationHeader) {
		int index = 0;
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty()) {
					for (int i = 0; i < intersaction.getHeaders().size(); i++) {	
						System.out.println("intersaction header: " + i + " | " + intersaction.getHeaders().get(i).getHeader());
						if (intersaction.getHeaders().get(i).getHeader().toLowerCase().equals(aggregationHeader.toLowerCase()))
							// the +1 is because the fist header column is the id of the row
							// without the +1 if it missed the header ID
//							quantityIdx = i + 1;
							index = i + 1 ;
					}
				}
			}
		}
		return "column" + index; 
	}
	
	
	private static String shortValue(Double value) {
		String number = String.valueOf(value);
		if ( number.length() - number.indexOf(".") > 3)
			number = number.substring(0, number.indexOf(".") + 3);
		
		return number;
	}
	
	public static List<TabItem> createBudgetModality(List<VennProjectsBean> intersactions, VennGraphBeanVO vennGraphBean) {
		
		List<TabItem> result = new ArrayList<TabItem>();
		
		// this is used for the center panel
		VerticalPanel centerPanel = new VerticalPanel();
		centerPanel.setSpacing(10);
		centerPanel.setWidth(850);
		centerPanel.setHeight(590);
		centerPanel.setScrollMode(Scroll.AUTO);
		TabItem centerProjectsTabPanel = buildTabItem("Budget/Modality", 865, 610, Scroll.AUTO);
		centerPanel.add(new HTML("<div> Budget/Modality </div>"));
		centerProjectsTabPanel.add(centerPanel);

		// this is used for the portlet panel
		VerticalPanel portletPanel = new VerticalPanel();
		portletPanel.setSpacing(10);
		TabItem portletProjectsTabPanel = buildTabItem("Budget/Modality", 800, 210, Scroll.AUTO);
		portletProjectsTabPanel.setAutoWidth(true);
		portletProjectsTabPanel.add(portletPanel);
		
//		portletPanel.setScrollMode(Scroll.AUTO);
		portletPanel.setWidth(800);
		portletPanel.setHeight(210);
		portletPanel.add(new HTML("<div> Budget/Modality </div>"));
//		int intersectionIdx=0;
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
//				VennIntersectionsBean inter = vennBudgetBean.getIntersection(intersectionIdx);
				if (!intersaction.getProjectsRows().isEmpty()) {
//					System.out.println("contents" + intersaction.getProjectsRows());
					centerPanel.add(buildDonorBudgetFundingModality(intersaction, vennGraphBean));
					portletPanel.add(buildDonorBudgetFundingModality(intersaction, vennGraphBean));
				} 
			}
//			intersectionIdx++;
		}
		
		result.add(centerProjectsTabPanel);
		result.add(portletProjectsTabPanel);
		return result;
	}
	
	
	private static VerticalPanel buildDonorBudgetFundingModality(VennProjectsBean intersaction, VennGraphBeanVO vennGraphBean) {	
		VerticalPanel gridContentPanel = new VerticalPanel();
		gridContentPanel.setSpacing(5);
		gridContentPanel.setBorders(true);
		gridContentPanel.setAutoHeight(true);
		gridContentPanel.setAutoWidth(true);

//		HTML label = new HTML();
//		String str = "000000" + Integer.toHexString(intersaction.getColor()); //$NON-NLS-1$ 
//		String color = "#" + str.substring(str.length() - 6);
//		label.setHTML("<div style='color:" + color + "'><u>" + intersaction.getLabel() + "</u></div>");
//		gridContentPanel.add(label);

		VennProjectsGridPanel gridPanel = new VennProjectsGridPanel();
		gridContentPanel.add(gridPanel.build("700px", intersaction.getHeaders().size(), intersaction.getProjectsRows().size()));
		Grid<VennGridMD> grid = gridPanel.getGrid();

		List<String> headers = getDonorBudgetFundingModalityHeaders();
		
		// build panel
//		System.out.println("creating grid");
		Integer quantityIdx = 0;
		// headers
		for (int i = 0; i <headers.size(); i++) 	
			grid.getColumnModel().setColumnHeader(i, intersaction.getHeaders().get(i).getHeader());
		

		List<Double> values = new ArrayList<Double>();
		Double sum = new Double(0);
		ListStore<VennGridMD> store = grid.getStore();
		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
//			System.out.println("->" + projectRow);
			VennGridMD md = new VennGridMD();
			// there is the id as first cell value
			for (int i = 1; i < projectRow.size(); i++) {
				md.addColumnValue(i - 1, projectRow.get(i));
				if (i == quantityIdx) {
					try {
//						System.out.println("projectRow.get(i): " + projectRow.get(i));
						Double value = Double.parseDouble(projectRow.get(i));
						values.add(value);
						sum = sum + value;
					} catch (NumberFormatException nfe) {
					}
				}
			}
//			System.out.println("add lto list");
			store.add(md);
		}

		// creating SUM AND AVG
		gridContentPanel.add(new HTML("<b>Budget projects sum: " + sum + "</b>"));
		gridContentPanel.add(new HTML("<b>Budget projects average: " + sum / values.size() + "</b>"));

		// add panel
		return gridContentPanel;
	}
	
	private static List<String> getDonorBudgetFundingModalityHeaders(){
		List<String> headers = new ArrayList<String>();
		headers.add("Donor");
		headers.add("Budget (mn)");
		headers.add("Modality");
		return headers;
	}
	
	private static List<List<String>> getDonorBudgetFundingModalityContent(VennIntersectionsBean intersaction) {
		List<List<String>> content = new ArrayList<List<String>>();
		return content;
	}
	
	
	public static HashMap<String, HashMap<String, Double>> calculateDonorsAndFunding(VennBeanVO vennBean) {
		HashMap<String, HashMap<String, Double>> donors = new HashMap<String, HashMap<String,Double>>();
		
		for (String key  : vennBean.getVennCountryBeanVO().keySet()) {
			VennCountryBeanVO vennCountry = vennBean.getVennCountryBeanVO().get(key);
			List<VennProjectsBean> intersactions = vennCountry.getAllIntersections();
			for (VennProjectsBean intersaction : intersactions) {
				if (intersaction.getProjectsRows() != null) {
					if (!intersaction.getProjectsRows().isEmpty())
						calcultateDonorAndFundingIntersaction(donors, intersaction);
				}
			}
		}
		
		return donors;
	}
	
	
	private static void calcultateDonorAndFundingIntersaction(HashMap<String, HashMap<String, Double>> donors,  VennProjectsBean intersaction) {
		Integer quantityIdx = 0;
		
		// hardcoded for now
		Integer donorIdx = 1;
		Integer fundingModalityIdx = 7;
		
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity"))
				// the +1 is because the fist header column is the id of the row
				quantityIdx = i + 1;
		}

		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
			// there is the id as first cell value
			try {
				String donor = projectRow.get(donorIdx);
				
				Double value = Double.valueOf(projectRow.get(quantityIdx));
				
				String fundingModality = projectRow.get(fundingModalityIdx);
				
				// create hashMap with donors and fundings
				if( donors.get(donor) == null) {
					HashMap<String, Double> fundingModalityHM = new HashMap<String, Double>();
					fundingModalityHM.put(fundingModality, value);
					donors.put(donor, fundingModalityHM);
				}
				else {
					if( donors.get(donor).get(fundingModality) == null) {
						donors.get(donor).put(fundingModality, value);
					}
					else {
						Double v =  donors.get(donor).get(fundingModality);
						value = v + value;						
						donors.get(donor).put(fundingModality, value);
					}
				}
			} catch (NumberFormatException nfe) {
			}
			
		}
	}
	
	
	
	private static TabItem buildTabItem(String title, int width, int height, Scroll scroll) {
		TabItem tabItem = new TabItem(title);
		tabItem.setWidth(width);
		tabItem.setHeight(height);
		tabItem.setScrollMode(scroll);
		return tabItem;
	}
	
	private static VerticalPanel buildVerticalPanel(String title, int width, int height, Scroll scroll) {
		VerticalPanel panel = new VerticalPanel();
		panel.setWidth(width);
		panel.setHeight(height);
		panel.setScrollMode(scroll);
		return panel;
	}
}
