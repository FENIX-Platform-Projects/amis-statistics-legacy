package org.fao.fenix.web.modules.ipc.client.view.viewer;

import java.util.List;

import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;

public class IPCReportViewBuilder extends IPCViewBuilder{

	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	/* 
	 * **************************************************  SUMMARY VIEW (area of analysis + TAB 1 contents + projected trend)
	 * **************************************************
	 */
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */

	public static String buildSummary(List<ProvinceVO> provinceList) {
		
		StringBuilder sb = new StringBuilder();	
		sb.append("<table width='100%' style='border-top-style:solid;border-top-color:black;border-top-width:1;border-bottom-style:solid;border-bottom-color:black;border-bottom-width:1;border-left-style:solid;border-left-color:black;border-left-width:1;border-right-style:solid;border-right-color:black;border-right-width:1'>");

		sb.append(IPCReportViewHeaderBuilder.buildTabOneHeaders());
		
		for (ProvinceVO province: provinceList) {
			
			List<ModuleVO> modules = province.getModules();
			ModuleVO moduleOne = modules.get(0);
			ModuleVO moduleTwo = modules.get(1);
			ModuleVO moduleThree = modules.get(2);
			sb.append(buildAreaRow(province.getProvinceLabel(), moduleOne, moduleTwo, moduleThree));		
		}
		
		sb.append("</table>");
		return sb.toString();
	}

	
	public static String buildAreaRow(String areaOfAnalysisLabel, ModuleVO moduleOne, ModuleVO moduleTwo, ModuleVO moduleFour) {
		StringBuilder sb = new StringBuilder();
		sb.append(buildFirstRow(areaOfAnalysisLabel, moduleOne, moduleTwo, moduleFour));
		for (int i = 1; i < 12; i++) {
			String code = "FT-";
			if (i < 10)
				code += ("0" + i);
			else
				code += i;
			sb.append(buildRow(moduleOne, code));
		}
	//	sb.append("</table>");
		return sb.toString();
	}
	
	private static String buildFirstRow(String areaOfAnalysisLabel, ModuleVO moduleOne, ModuleVO moduleTwo, ModuleVO moduleThree) {
		FreeTextVO ftvo_1 = findFreeText(moduleOne, "FT-00");
		FreeTextVO ftvo_2 = findFreeText(moduleTwo, "FT-12");
		DropDownVO dd_1 = findDropDown(moduleTwo, "DD-00");  // phase classification
		DropDownVO dd_2 = findDropDown(moduleTwo, "DD-01");  // risk
		DropDownVO dd_3 = findDropDown(moduleThree, "DD-03"); // projected trend
		
		String range = "<b>"+ ftvo_1.getRangeLabel();
		System.out.println("-" + ftvo_1.getLevelLabel() + "*" + ftvo_1.getRangeLabel());
//		if(ftvo_1.getExactValue()!=null && ftvo_1.getExactValue()!=0)
//			range += "(value = "+ ftvo_1.getExactValue() + ")";
		if(ftvo_1.getLevelLabel()!="")
			range += " " + ftvo_1.getLevelLabel(); 
		
		range += "</b><br>";
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td><div style='font-family: sans-serif; font-size: 8pt; font-weight: bold; color: #336699;'>"+areaOfAnalysisLabel+"</div></td>"); // Area
		sb.append("<td><div style='font-family: sans-serif; font-size: 8pt; font-style: italic;'>Crude Mortality Rate</div></td>");
		String text_1 = ftvo_1.getBulletPoints().get(0).getText();
		sb.append("<td width=200 ><div style='font-family: sans-serif; font-size: 8pt;'>" + range + " " + text_1 + "</div></td>");
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 8pt; font-weight: bold;'>" + dd_1.getDropDownLabel() + "</div></td>");
		String text_2 = ftvo_2.getBulletPoints().get(0).getText();
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 8pt;'>" + text_2 + "</div></td>");
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 8pt; font-weight: bold;'>" + dd_2.getDropDownLabel() + "</div></td>");
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 8pt; font-weight: bold;'>" + dd_3.getDropDownLabel() + "</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String buildRow(ModuleVO vo, String code) {
		FreeTextVO ftvo = findFreeText(vo, code);
		
		String range = "<b>"+ ftvo.getRangeLabel();
//		if(ftvo_1.getExactValue()!=null && ftvo_1.getExactValue()!=0)
//			range += "(value = "+ ftvo_1.getExactValue() + ")";
		if(ftvo.getLevelLabel()!="")
			range += " " + ftvo.getLevelLabel(); 
		
		range += "</b><br>";
		
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td><div style='font-family: sans-serif; font-size: 9pt; font-weight: bold; color: #336699;'></div></td>"); // empty td
		sb.append("<td><div style='font-family: sans-serif; font-size: 8pt; font-style: italic;'>" + ftvo.getName() + "</div></td>");
		String text = ftvo.getBulletPoints().get(0).getText();
		sb.append("<td><div style='font-family: sans-serif; font-size: 8pt;'>" + range + " " + text + "</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

}