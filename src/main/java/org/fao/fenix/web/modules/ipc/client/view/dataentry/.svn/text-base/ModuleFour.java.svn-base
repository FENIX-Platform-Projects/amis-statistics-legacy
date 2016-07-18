package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ModuleFour extends Module {
	
	private Integer panelHeight = 190;
	private Integer tapHeight = 150;

	public ModuleFour() {
		super();
		panel.setSize("975px", "525px");
	}

	public ContentPanel build() {
		return panel;
	}
	
	public VerticalPanel buildModuleFourPanel(ModuleVO mvo, Map<String, List<CodeVo>> codes) {
		
		VerticalPanel moduleFourPanel = new VerticalPanel();
		moduleFourPanel.setSpacing(IPCXMLDataEntryUtils.SPACING * IPCXMLDataEntryUtils.SPACING);
		
		moduleFourPanel.add(IPCHeaderBuilder.buildModuleFourHeaders_1());
		HorizontalPanel rowOne = new HorizontalPanel();
		rowOne.setSpacing(IPCXMLDataEntryUtils.SPACING);
		VerticalPanel imminentPhasePanel = IPCXMLDataEntryUtils.buildBigDropDownPanel(IPCXMLDataEntryUtils.findDropDown(mvo.getDropDowns(), "DD-04"), codes.get("DD-04"));
		rowOne.add(imminentPhasePanel);
		ListBox listBox = (ListBox)imminentPhasePanel.getData("dropDown");
		dropDownMap.put("DD-04", listBox);
		VerticalPanel immediateHazardPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
		rowOne.add(immediateHazardPanel);
		immediateHazardPanel.setHeight(panelHeight);
		VerticalPanel tap =  (VerticalPanel) immediateHazardPanel.getWidget(0);
		tap.setHeight(tapHeight);
		HTML textArea = (HTML)immediateHazardPanel.getData("textArea");
//		RichTextArea textArea = (RichTextArea)immediateHazardPanel.getData("textArea");
		FreeTextVO ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), "FT-19");
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
		textAreaMap.put("FT-19", textArea);
		moduleFourPanel.add(rowOne);
		
		moduleFourPanel.add(IPCHeaderBuilder.buildModuleFourHeaders_2());
		moduleFourPanel.add(new HTML("<b>Physical Capital</b>"));
		moduleFourPanel.add(addRow(mvo, "FT-20", "DD-05", "FT-21", codes));
		moduleFourPanel.add(new HTML("<b>Social Capital</b>"));
		moduleFourPanel.add(addRow(mvo, "FT-22", "DD-06", "FT-23", codes));
		moduleFourPanel.add(new HTML("<b>Financial Capital</b>"));
		moduleFourPanel.add(addRow(mvo, "FT-24", "DD-07", "FT-25", codes));
		moduleFourPanel.add(new HTML("<b>Natural Capital</b>"));
		moduleFourPanel.add(addRow(mvo, "FT-26", "DD-08", "FT-27", codes));
		moduleFourPanel.add(new HTML("<b>Human Capital</b>"));
		moduleFourPanel.add(addRow(mvo, "FT-28", "DD-09", "FT-29", codes));
		moduleFourPanel.add(new HTML("<b>Local Political Capital</b>"));
		moduleFourPanel.add(addRow(mvo, "FT-30", "DD-10", "FT-31", codes));
		
		return moduleFourPanel;
	}
	
	public HorizontalPanel addRow(ModuleVO mvo, String ftCodeOne, String ddCode, String ftCodeTwo, Map<String, List<CodeVo>> codes) {
		
		HorizontalPanel rowPanel = new HorizontalPanel();
		rowPanel.setSpacing(IPCXMLDataEntryUtils.SPACING);
		
		VerticalPanel ftPanelOne = IPCXMLDataEntryUtils.buildSmallTextAreaPanel();
		rowPanel.add(ftPanelOne);
//		RichTextArea textArea = (RichTextArea)ftPanelOne.getData("textArea");
		ftPanelOne.setHeight(panelHeight);
		VerticalPanel tap =  (VerticalPanel) ftPanelOne.getWidget(0);
		tap.setHeight(tapHeight);
		HTML textArea = (HTML)ftPanelOne.getData("textArea");
		FreeTextVO ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), ftCodeOne);
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
		textAreaMap.put(ftCodeOne, textArea);
		
		VerticalPanel ddPanel = IPCXMLDataEntryUtils.buildSmallDropDownPanel(IPCXMLDataEntryUtils.findDropDown(mvo.getDropDowns(), ddCode), codes.get(ddCode));
		rowPanel.add(ddPanel);
		ListBox listBox = (ListBox)ddPanel.getData("dropDown");
		dropDownMap.put(ddCode, listBox);
		
		VerticalPanel ftPanelTwo = IPCXMLDataEntryUtils.buildSmallTextAreaPanel();
		rowPanel.add(ftPanelTwo);
		ftPanelTwo.setHeight(panelHeight);
		tap =  (VerticalPanel) ftPanelTwo.getWidget(0);
		tap.setHeight(tapHeight);
		textArea = (HTML)ftPanelOne.getData("textArea");
//		textArea = (RichTextArea)ftPanelTwo.getData("textArea");
		ftvo = IPCXMLDataEntryUtils.findFreeText(mvo.getFreeTexts(), ftCodeTwo);
		textArea.setHTML(ftvo.getBulletPoints().get(0).getText()); 
		textAreaMap.put(ftCodeTwo, textArea);
		
		return rowPanel;
	}

}
