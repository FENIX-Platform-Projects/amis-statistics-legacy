package org.fao.fenix.web.modules.ipc.client.view.viewer;

import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;

public class IPCViewBuilder {

	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	/* 
	 * ************************************************** TAB 1
	 * **************************************************
	 */
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */

	public static String buildTabOne(ModuleVO moduleOne, ModuleVO moduleTwo) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table width='100%' border='1pt' bordercolor='#1D4589'>");
		sb.append(IPCViewHeaderBuilder.buildTabOneHeaders());
		sb.append(buildFirstRow(moduleOne, moduleTwo));
		for (int i = 1; i < 12; i++) {
			String code = "FT-";
			if (i < 10)
				code += ("0" + i);
			else
				code += i;
			sb.append(buildRow(moduleOne, code));
		}
		sb.append("</table>");
		return sb.toString();
	}

	private static String buildFirstRow(ModuleVO moduleOne, ModuleVO moduleTwo) {
		FreeTextVO ftvo_1 = findFreeText(moduleOne, "FT-00");
		FreeTextVO ftvo_2 = findFreeText(moduleTwo, "FT-12");
		DropDownVO dd_1 = findDropDown(moduleTwo, "DD-00");
		DropDownVO dd_2 = findDropDown(moduleTwo, "DD-01");
		
		String range = "<b>"+ ftvo_1.getRangeLabel();
		System.out.println("-" + ftvo_1.getLevelLabel() + "*" + ftvo_1.getRangeLabel());
		if(ftvo_1.getLevelLabel()!="")
			range += " " + ftvo_1.getLevelLabel(); 
		
		range += "</b><br>";
		
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt; font-style: italic;'>Crude Mortality Rate</div></td>");
		String text_1 = ftvo_1.getBulletPoints().get(0).getText();
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + range + " " + text_1 +  "</div></td>");
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 10pt; font-weight: bold;'>" + dd_1.getDropDownLabel() + "</div></td>");
		String text_2 = ftvo_2.getBulletPoints().get(0).getText();
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 10pt;'>" + text_2 + "</div></td>");
		sb.append("<td rowspan='12'><div style='font-family: sans-serif; font-size: 10pt; font-weight: bold;'>" + dd_2.getDropDownLabel() + "</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String buildRow(ModuleVO vo, String code) {
		FreeTextVO ftvo = findFreeText(vo, code);
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt; font-style: italic;'>" + ftvo.getName() + "</div></td>");
		String text = ftvo.getBulletPoints().get(0).getText();
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + text + "</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	/* 
	 * ************************************************** TAB 2
	 * **************************************************
	 */
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */

	public static String buildTabTwo(ModuleVO moduleThree) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table width='100%' border='1pt' bordercolor='#1D4589'>");
		sb.append(IPCViewHeaderBuilder.buildTabTwoHeaders());
		DropDownVO ddvo = findDropDown(moduleThree, "DD-02");
		sb.append("<tr>");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt; font-weight: bold;'>" + ddvo.getDropDownLabel() + "</div></td>");
		for (int i = 13; i < 17; i++) {
			String code = "FT-" + i;
			FreeTextVO ftvo = findFreeText(moduleThree, code);
			String text = ftvo.getBulletPoints().get(0).getText();
			sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + text + "</div></td>");
		}
		ddvo = findDropDown(moduleThree, "DD-03");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt; font-weight: bold;'>" + ddvo.getDropDownLabel() + "</div></td>");
		for (int i = 17; i < 19; i++) {
			String code = "FT-" + i;
			FreeTextVO ftvo = findFreeText(moduleThree, code);
			String text = ftvo.getBulletPoints().get(0).getText();
			sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + text + "</div></td>");
		}
		sb.append("</tr>");
		sb.append("</table>");
		return sb.toString();
	}

	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	/* 
	 * ************************************************** TAB 3
	 * **************************************************
	 */
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */

	public static String buildTabThree(ModuleVO moduleFour) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table width='100%' border='1pt' bordercolor='#1D4589'>");
		sb.append(IPCViewHeaderBuilder.buildTabThreeHeaders());
		sb.append(buildRowOne(moduleFour));
		sb.append(buildRowX(moduleFour, "FT-22", "DD-06", "FT-23"));
		sb.append(buildRowX(moduleFour, "FT-24", "DD-07", "FT-25"));
		sb.append(buildRowX(moduleFour, "FT-26", "DD-08", "FT-27"));
		sb.append(buildRowX(moduleFour, "FT-28", "DD-09", "FT-29"));
		sb.append(buildRowX(moduleFour, "FT-30", "DD-10", "FT-31"));
		sb.append("</table>");
		return sb.toString();
	}

	private static String buildRowOne(ModuleVO moduleFour) {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		DropDownVO ddvo = findDropDown(moduleFour, "DD-04");
		sb.append("<td rowspan='6'><div style='font-family: sans-serif; font-size: 10pt; font-weight: bold;'>" + ddvo.getDropDownLabel() + "</div></td>");
		FreeTextVO ftvo = findFreeText(moduleFour, "FT-19");
		sb.append("<td rowspan='6'><div style='font-family: sans-serif; font-size: 10pt;'>" + ftvo.getBulletPoints().get(0).getText() + "</div></td>");
		ftvo = findFreeText(moduleFour, "FT-20");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'><b>Physical Capital:</b> " + ftvo.getBulletPoints().get(0).getText() + "</div></td>");
		ddvo = findDropDown(moduleFour, "DD-05");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;font-weight: bold;'>" + ddvo.getDropDownLabel() + "</div></td>");
		ftvo = findFreeText(moduleFour, "FT-21");
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + ftvo.getBulletPoints().get(0).getText() + "</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}
	
	private static String buildRowX(ModuleVO moduleFour, String textOneCode, String dropDownCode, String textTwoCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		FreeTextVO ftvo = findFreeText(moduleFour, textOneCode);
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'><b>" + ftvo.getName() + ": </b>" + ftvo.getBulletPoints().get(0).getText() + "</div></td>");
		DropDownVO ddvo = findDropDown(moduleFour, dropDownCode);
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + ddvo.getDropDownLabel() + "</div></td>");
		ftvo = findFreeText(moduleFour, textTwoCode);
		sb.append("<td><div style='font-family: sans-serif; font-size: 10pt;'>" + ftvo.getBulletPoints().get(0).getText() + "</div></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	/* 
	 * ************************************************** UTILS
	 * **************************************************
	 */
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */

	protected static FreeTextVO findFreeText(ModuleVO vo, String code) {
		for (FreeTextVO ftvo : vo.getFreeTexts()) {
			if (ftvo.getCode().equalsIgnoreCase(code))
				return ftvo;
		}
		return null;
	}

	protected static DropDownVO findDropDown(ModuleVO vo, String code) {
		for (DropDownVO ddvo : vo.getDropDowns()) {
			if (ddvo.getCode().equalsIgnoreCase(code))
				return ddvo;
		}
		return null;
	}

}