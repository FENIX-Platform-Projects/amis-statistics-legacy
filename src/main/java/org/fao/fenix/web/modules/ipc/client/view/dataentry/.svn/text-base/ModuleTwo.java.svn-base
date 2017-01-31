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

public class ModuleTwo extends Module {

	public ModuleTwo() {
		super();
		panel.setSize("925px", "525px");
	}
	
	public ContentPanel build() {
		panel.setTopComponent(IPCHeaderBuilder.buildModuleTwoHeaders());
		return panel;
	}
	
	public HorizontalPanel buildTimePeriodPanel(ModuleVO mvo, final HashMap<String, List<CodeVo>> codes) {
		
		HorizontalPanel timePeriodPanel = new HorizontalPanel();
		timePeriodPanel.setSpacing(IPCXMLDataEntryUtils.SPACING * IPCXMLDataEntryUtils.SPACING);
		
		VerticalPanel projectedPhasePanel = IPCXMLDataEntryUtils.buildSmallDropDownPanel(IPCXMLDataEntryUtils.findDropDown(mvo.getDropDowns(), "DD-00"), codes.get("DD-00"));
		timePeriodPanel.add(projectedPhasePanel);
		System.out.println("buildTimePeriodPanel " + mvo.getLevel());
			
		ListBox listBox = (ListBox)projectedPhasePanel.getData("dropDown");	
		System.out.println("insert:" + listBox.getItemCount());
		dropDownMap.put("DD-00", listBox);
		
		VerticalPanel bulletPointsPanel = IPCXMLDataEntryUtils.buildSmallTextAreaPanel();
//		RichTextArea textArea = (RichTextArea)bulletPointsPanel.getData("textArea");
//		FreeTextVO ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-12");
//		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
//		textAreaMap.put("FT-12", textArea);
		
		bulletPointsPanel.setHeight(190);
//		bulletPointsPanel.setWidth(width);
		VerticalPanel tap =  (VerticalPanel) bulletPointsPanel.getWidget(0);
		tap.setHeight(150);
		HTML textArea = (HTML)bulletPointsPanel.getData("textArea");
		FreeTextVO ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-12");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
		textAreaMap.put("FT-12", textArea);
		
		
		timePeriodPanel.add(bulletPointsPanel);
		
		VerticalPanel riskLevelPanel = IPCXMLDataEntryUtils.buildSmallDropDownPanel(IPCXMLDataEntryUtils.findDropDown(mvo.getDropDowns(), "DD-01"), codes.get("DD-01"));
		timePeriodPanel.add(riskLevelPanel);
		listBox = (ListBox)riskLevelPanel.getData("dropDown");
		
		dropDownMap.put("DD-01", listBox);
		
		return timePeriodPanel;
	}
	

	
}