package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.Image;

public class ADAMQuestionMaker extends ADAMBoxMaker {
	
	private static List<ADAMQueryVO> questions;
	
	/*public static void buildQuestions(String entity, String code, String label, HashMap<String, List<String>> filters, List<String> dates, String type, final long objID) {
		String[] colors = new String[]{"red", "green", "yellow"};
		if ( questions != null)
			questions.clear();
		if ( type.equalsIgnoreCase("Gaul_"))
			questions = ADAMQuestionFactory.buildCountryQuestions(code, label, filters, dates);
		else if (type.equalsIgnoreCase("Donor_"))
			questions = ADAMQuestionFactory.buildDonorQuestions(code, label, filters, dates);
		for (int i = 11 ; i < 11 + ADAMController.QUESTIONS ; i++) {
			ADAMBoxContent c = ADAMBoxContent.valueOf(questions.get(i % ADAMController.QUESTIONS).getOutputType());
			System.out.println(i + " | " + c.toString());
			String color = questions.get(i % ADAMController.QUESTIONS).getBoxColor();
			switch (c) {
				case BAR: 
//					ADAMController.addQuestionChart(i, label, colors[i % 3], questions.get(i % ADAMController.QUESTIONS), objID);
					ADAMController.addQuestionChart(i, label, color, questions.get(i % ADAMController.QUESTIONS), objID); 
				break;
				case PIE: 
//					ADAMController.addQuestionChart(i, label, colors[i % 3], questions.get(i % ADAMController.QUESTIONS), objID);
					ADAMController.addQuestionChart(i, label, color, questions.get(i % ADAMController.QUESTIONS), objID); 
				break;
				case TOP_COUNTRIES_BY_DONOR:
					ADAMController.addQuestionGroupTable(i, label, color, questions.get(i % ADAMController.QUESTIONS), objID);
				break;
				case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE:
					ADAMController.addQuestionGroupTable(i, label, color, questions.get(i % ADAMController.QUESTIONS), objID);
				break;
				case FAVOURITE_PURPOSES_QUESTIONS_VIEW:
					ADAMController.addQuestionGroupTable(i, label, color, questions.get(i % ADAMController.QUESTIONS), objID);
				break;
			}
		}
	}
	*/
	public static LinkedHashMap<String, ADAMQueryVO> buildReportQuestions(String entity, String code, String label, HashMap<String, List<String>> filters, List<String> dates, String type, final long objID) {
		LinkedHashMap<String, ADAMQueryVO> map = new LinkedHashMap<String, ADAMQueryVO>();
		List<ADAMQueryVO> qvos = new ArrayList<ADAMQueryVO>();
		String[] colors = new String[]{"red", "green", "yellow"};
		if ( type.equalsIgnoreCase("Gaul_"))
			qvos = ADAMQuestionFactory.buildCountryQuestions(code, label, filters, dates);
		else if (type.equalsIgnoreCase("Donor_"))
			qvos = ADAMQuestionFactory.buildDonorQuestions(code, label, filters, dates);
		Integer i=0;
		for (ADAMQueryVO qvo : qvos ) {
			map.put(i.toString(), qvo);
			i++;
		}
		System.out.println("buildReportQuestions map: " + map);
		return map;
	}
	
	
	public static HorizontalPanel buildQuestionTablePanel(int questionIDX, ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 15px; color: #17376D;'>"+ questions.get(questionIDX % ADAMController.QUESTIONS).getQuestion() + "</div>");
		q.setHeight("120px");
		
		left.add(q);
		left.add(buildQuestionsTableToolbar(vo, objectSizeListener));
		
		Image i = new Image("../adamObjects/" + vo.getSmallImagePath());
		i.setSize("335px", "175px");
		
		right.add(i);
		
		p.add(left);
		p.add(right);
		
		return p;
	}
	
	
	
	public static HorizontalPanel buildQuestionChartPanel(ADAMQueryVO qvo, int questionIDX, ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 20px; color: #224466;'>" + questions.get(questionIDX % ADAMController.QUESTIONS).getQuestion() + "</div>");
		q.setHeight("120px");
		
		left.add(q);
		left.add(buildQuestionsChartToolbar(qvo, vo, objectSizeListener));
		
		Image i = new Image("../adamObjects/" + vo.getSmallImagePath());
		i.setSize("335px", "175px");
		
		right.add(i);
		
		p.add(left);
		p.add(right);
		
		return p;
	}
	
	public static HorizontalPanel buildQuestionTablePanel(int questionIDX, ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener, VerticalPanel gridPanel) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 20px; color: #224466;'>" + questions.get(questionIDX % ADAMController.QUESTIONS).getTitle() + "</div>");
		q.setHeight("120px");
		
		left.add(q);
		left.add(buildQuestionsTableToolbar(vo, objectSizeListener));
		
		right.add(gridPanel);
		
		p.add(left);
		p.add(right);
		
		return p;
	}
	
	public static HorizontalPanel buildWaitingPanel(int questionIDX) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSize(ADAMConstants.SMALL_BOX_WIDTH + "px", ADAMConstants.SMALL_BOX_HEIGHT + "px");
		p.setSpacing(5);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 20px; color: #224466;'>Please Wait...</div>");
		q.setHeight("120px");
		
		left.add(q);
//		left.add(buildQuestionsToolbar());
		
		Image i = new Image("adam-images/loading.gif");
		i.setSize("48px", "48px");
		
		right.add(i);
		
		p.add(left);
		p.add(right);
		
		return p;
	}
	
	public static HorizontalPanel buildQuestionsChartToolbar(ADAMQueryVO qvo, ADAMResultVO vo,SelectionListener<ButtonEvent> objectSizeListener) {
		
		HorizontalPanel p = new HorizontalPanel();
//		p.setSpacing(5);
		
//		Button more = new Button("More...");
//		more.setIconStyle("gear");
//		more.setWidth("90px");
//		Button print = new Button("Print");
//		print.setIconStyle("print");
//		print.setWidth("90px");
		// Export Menu
		Button export = new Button("Export...");
		export.setIconStyle("export");
		MenuItem exportImage = new MenuItem("Export Image");
		exportImage.setIconStyle("pdfIcon");
		exportImage.addSelectionListener(ADAMController.exportChartImage(vo));
		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportChartExcel(qvo, vo));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");

		Menu exportMenu = new Menu();
		exportMenu.add(exportImage);
		exportMenu.add(exportExcel);
//		exportMenu.add(exportAllResources);
		export.setMenu(exportMenu);
		export.setWidth("90px");
		
		Button full = new Button("Full Screen");
		full.setIconStyle("scale");
		full.setWidth("90px");
		full.addSelectionListener(objectSizeListener);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		left.add(full);
//		left.add(more);
		
		
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		right.add(export);
//		right.add(print);
		
		p.add(addInfo(vo.getDescription()));
		p.add(left);
//		p.add(right);
		
		return p;
	}
	
	
	public static HorizontalPanel buildQuestionsTableToolbar(ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener) {
		
		HorizontalPanel p = new HorizontalPanel();
//		p.setSpacing(5);
		
//		Button more = new Button("More...");
//		more.setIconStyle("gear");
//		more.setWidth("90px");
//		Button print = new Button("Print");
//		print.setIconStyle("print");
//		print.setWidth("90px");
		// Export Menu
		Button export = new Button("Export...");
		export.setIconStyle("export");
		MenuItem excel = new MenuItem("Export Excel");
		excel.setIconStyle("sendToExcel");
		excel.addSelectionListener(ADAMController.exportExcelTable(vo, true));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");
		Menu exportMenu = new Menu();
		exportMenu.add(excel);
//		exportMenu.add(exportAllResources);
		export.setMenu(exportMenu);
		export.setWidth("90px");
		
		Button full = new Button("Full Screen");
		full.setIconStyle("scale");
		full.setWidth("90px");
		full.addSelectionListener(objectSizeListener);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		left.add(full);
//		left.add(more);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
//		right.add(export);
//		right.add(print);
		
		p.add(addInfo(vo.getDescription()));
		p.add(left);
		p.add(right);
		
		return p;
	}
	
}