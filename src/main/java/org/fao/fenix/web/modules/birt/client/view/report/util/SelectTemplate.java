package org.fao.fenix.web.modules.birt.client.view.report.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.report.template.Blank;
import org.fao.fenix.web.modules.birt.client.view.report.template.Template1;
import org.fao.fenix.web.modules.birt.client.view.report.template.Template2;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;

import com.google.gwt.user.client.ui.VerticalPanel;

public class SelectTemplate {

	public static Map<String, List<String>> mapTemplate = new HashMap<String, List<String>>();

	static {
		/*
		 * 0 - rptdesgin
		 * 1 - class name to create template
		 */
		List<String> element;
		element = new ArrayList<String>();
		element.add("blank");
		element.add("Blank");
		mapTemplate.put("blankTemplate", element);
		
		element = new ArrayList<String>();
		element.add("template1");
		element.add("template1");
		mapTemplate.put("template1", element);
		
		element = new ArrayList<String>();
		element.add("template2");
		element.add("template2");
		mapTemplate.put("template2", element);
	}
	
	public static VerticalPanel getClassTemplate(String keyTemplate, ReportViewer reportViewer){
		
		String classNameTemplate = mapTemplate.get(keyTemplate).get(1);
		if (classNameTemplate.equals("template1")){
			Template1 template1 = new Template1(reportViewer);
			return template1.getMainCont();
		} else if (classNameTemplate.equals("template2")){
			Template2 template2 = new Template2(reportViewer);
			return template2.getMainCont();
		} else if (classNameTemplate.equals("Blank")){
			Blank blank = new Blank(reportViewer);
			return blank.getMainCont();
		} 
		
		return null;
		
	}
	
	public static String getNameTemplate(String keyTemplate){
		return mapTemplate.get(keyTemplate).get(0);
	}
	
}
