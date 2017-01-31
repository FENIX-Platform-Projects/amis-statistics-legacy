package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ModuleThree extends Module {
	
	private Integer panelHeight = 190;
	private Integer tapHeight = 150;

	public ModuleThree() {
		super();
		panel.setSize("980px", "525px");
	}
	
	public ContentPanel build() {
		return panel;
	}
	
	public VerticalPanel buildModuleThreePanel(ModuleVO mvo, HashMap<String, List<CodeVo>> codes) {
		
		VerticalPanel moduleThreePanel = new VerticalPanel();
		moduleThreePanel.setSpacing(IPCXMLDataEntryUtils.SPACING * IPCXMLDataEntryUtils.SPACING);
		
		/* ROW ONE */
		moduleThreePanel.add(IPCHeaderBuilder.buildModuleThreeHeaders_1());
		HorizontalPanel rowOne = new HorizontalPanel();
		rowOne.setSpacing(IPCXMLDataEntryUtils.SPACING);
		VerticalPanel imminentPhasePanel = IPCXMLDataEntryUtils.buildBigDropDownPanel(IPCXMLDataEntryUtils.findDropDown(mvo.getDropDowns(), "DD-02"), codes.get("DD-02"));
		rowOne.add(imminentPhasePanel);
		ListBox listBox = (ListBox)imminentPhasePanel.getData("dropDown");
		dropDownMap.put("DD-02", listBox);
		VerticalPanel immediateHazardPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
//		rowOne.add(immediateHazardPanel);
//		RichTextArea textArea = (RichTextArea)immediateHazardPanel.getData("textArea");
//		FreeTextVO ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-13");
//		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
//		textAreaMap.put("FT-13", textArea);
		immediateHazardPanel.setHeight(panelHeight);
		VerticalPanel tap =  (VerticalPanel) immediateHazardPanel.getWidget(0);
		tap.setHeight(tapHeight);
		HTML textArea = (HTML)immediateHazardPanel.getData("textArea");
		FreeTextVO ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-13");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
		textAreaMap.put("FT-13", textArea);
		rowOne.add(immediateHazardPanel);
		moduleThreePanel.add(rowOne);
		
		
		/* ROW TWO */
		moduleThreePanel.add(IPCHeaderBuilder.buildModuleThreeHeaders_2());
		HorizontalPanel rowTwo = new HorizontalPanel();
		rowTwo.setSpacing(IPCXMLDataEntryUtils.SPACING);
		VerticalPanel directFoodPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
		rowTwo.add(directFoodPanel);
//		textArea = (RichTextArea)directFoodPanel.getData("textArea");
//		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-14");
//		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
//		textAreaMap.put("FT-14", textArea);
		directFoodPanel.setHeight(panelHeight);
		tap =  (VerticalPanel) directFoodPanel.getWidget(0);
		tap.setHeight(tapHeight);
		textArea = (HTML)directFoodPanel.getData("textArea");
		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-14");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
		textAreaMap.put("FT-14", textArea);
		
		
		
		VerticalPanel effectOnLivelihoodPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
		rowTwo.add(effectOnLivelihoodPanel);	
//		textArea = (RichTextArea)effectOnLivelihoodPanel.getData("textArea");
//		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-15");
//		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
//		textAreaMap.put("FT-15", textArea);
		effectOnLivelihoodPanel.setHeight(panelHeight);
		tap =  (VerticalPanel) effectOnLivelihoodPanel.getWidget(0);
		tap.setHeight(tapHeight);
		textArea = (HTML)effectOnLivelihoodPanel.getData("textArea");
		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-15");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
		textAreaMap.put("FT-15", textArea);
		moduleThreePanel.add(rowTwo);
		
		
		/* ROW THREE */
		moduleThreePanel.add(IPCHeaderBuilder.buildModuleThreeHeaders_3());
		HorizontalPanel rowThree = new HorizontalPanel();
		rowThree.setSpacing(IPCXMLDataEntryUtils.SPACING);
		VerticalPanel populationAffectedPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
		rowThree.add(populationAffectedPanel);
//		textArea = (RichTextArea)populationAffectedPanel.getData("textArea");
//		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-16");
//		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
		populationAffectedPanel.setHeight(panelHeight);
		tap =  (VerticalPanel) populationAffectedPanel.getWidget(0);
		tap.setHeight(tapHeight);
		textArea = (HTML)populationAffectedPanel.getData("textArea");
		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-16");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
		textAreaMap.put("FT-16", textArea);
		
		VerticalPanel projectedTrendPanel = IPCXMLDataEntryUtils.buildBigDropDownPanel(IPCXMLDataEntryUtils.findDropDown(mvo.getDropDowns(), "DD-03"), codes.get("DD-03"));
		rowThree.add(projectedTrendPanel);
		listBox = (ListBox)projectedTrendPanel.getData("dropDown");
		dropDownMap.put("DD-03", listBox);
		moduleThreePanel.add(rowThree);
		
		moduleThreePanel.add(IPCHeaderBuilder.buildModuleThreeHeaders_4());
		HorizontalPanel rowFour = new HorizontalPanel();
		rowFour.setSpacing(IPCXMLDataEntryUtils.SPACING);
		VerticalPanel riskFactorsPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
		rowFour.add(riskFactorsPanel);
//		textArea = (RichTextArea)riskFactorsPanel.getData("textArea");
//		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-17");
//		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
		riskFactorsPanel.setHeight(panelHeight);
		tap =  (VerticalPanel) riskFactorsPanel.getWidget(0);
		tap.setHeight(tapHeight);
		textArea = (HTML)riskFactorsPanel.getData("textArea");
		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-17");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
		textAreaMap.put("FT-17", textArea);
		
		VerticalPanel opportunitiesPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
		rowFour.add(opportunitiesPanel);
//		textArea = (RichTextArea)opportunitiesPanel.getData("textArea");
		opportunitiesPanel.setHeight(panelHeight);
		tap =  (VerticalPanel) opportunitiesPanel.getWidget(0);
		tap.setHeight(tapHeight);
		textArea = (HTML)opportunitiesPanel.getData("textArea");
		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-18");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
		textAreaMap.put("FT-18", textArea);
		moduleThreePanel.add(rowFour);
		
		return moduleThreePanel;
	}
	
}