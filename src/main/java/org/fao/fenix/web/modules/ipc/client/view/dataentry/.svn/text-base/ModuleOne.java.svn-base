package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ModuleOne extends Module {

	public Map<String, TextField<String>> exactValueMap;
	
	private String code;

	public ModuleOne(String province) {
		super();
		
		setCode(province);
		panel.setSize("975px", "525px");
		exactValueMap = new HashMap<String, TextField<String>>();
	}

	public ContentPanel build() {
		panel.setTopComponent(IPCHeaderBuilder.buildModuleOneHeaders());
		
		return panel;
	}

	public HorizontalPanel buildReferenceOutcomePanel(FreeTextVO ftvo, HashMap<String, List<CodeVo>> codes) {

		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(IPCXMLDataEntryUtils.SPACING * IPCXMLDataEntryUtils.SPACING);
		panel.setBorders(true);
//		panel.setSize(, height)

		VerticalPanel dropDownsPanel = buildDropDownsPanel(ftvo, codes);
		panel.add(dropDownsPanel);

		if (ftvo.getBulletPoints() != null) {
			VerticalPanel bulletPointsPanel = IPCXMLDataEntryUtils.buildBigTextAreaPanel();
			bulletPointsPanel.setHeight(120);
			panel.add(bulletPointsPanel);
//			RichTextArea textArea = (RichTextArea) bulletPointsPanel.getData("textArea");
//			if (ftvo.getBulletPoints().get(0) != null)
//				textArea.setHTML(ftvo.getBulletPoints().get(0).getText());			
//			textAreaMap.put(ftvo.getCode(), textArea);
			
			VerticalPanel tap =  (VerticalPanel) bulletPointsPanel.getWidget(0);
			tap.setHeight(80);
			HTML textArea = (HTML) bulletPointsPanel.getData("textArea");
			if (ftvo.getBulletPoints().get(0) != null)
				textArea.setHTML(ftvo.getBulletPoints().get(0).getText());
			
			textAreaMap.put(ftvo.getCode(), textArea);
			
		}

		return panel;
	}

	private  VerticalPanel buildDropDownsPanel(FreeTextVO ftvo, HashMap<String, List<CodeVo>> codes) {
		VerticalPanel dropDownsPanel = new VerticalPanel();
		dropDownsPanel.setSpacing(IPCXMLDataEntryUtils.SPACING);
		dropDownsPanel.setWidth("435px");
		HTML label = new HTML("<b><u><font size='4pt'>" + ftvo.getName() + ":</font></u></b>");
		label.setWidth(IPCXMLDataEntryUtils.LONG_LABEL_WIDTH);
		dropDownsPanel.add(label);
		List<CodeVo> codelist = codes.get(ftvo.getCode());
		List<CodeVo> phaseClassification = codes.get("level");
		HorizontalPanel rangePanel = buildListBoxPanel("Range", codelist, ftvo.getRangeCode(), ftvo.getRangeLabel(), ftvo.getCode());
		HorizontalPanel exactValuePanel = buildExactValuePanel(ftvo.getExactValue(), ftvo.getCode());
		HorizontalPanel levelPanel = buildListBoxPanel("Level", phaseClassification, ftvo.getLevelCode(), ftvo.getLevelLabel(), ftvo.getCode());
		dropDownsPanel.add(rangePanel);
		dropDownsPanel.add(exactValuePanel);
		dropDownsPanel.add(levelPanel);
		return dropDownsPanel;
	}

	private  HorizontalPanel buildListBoxPanel(String label, List<CodeVo> codes, String code, String value, String ftvoCode) {
		HorizontalPanel listBoxPanel = new HorizontalPanel();
		listBoxPanel.setSpacing(IPCXMLDataEntryUtils.SPACING);
		HTML text = new HTML("<b>" + label + ": </b>");
		if ( label.equals("Level")) {
			text = new HTML("<b>Reliability Score: </b>");
		}
		
		text.setWidth(IPCXMLDataEntryUtils.LABEL_WIDTH);
		ListBox listBox = new ListBox();
		listBox.addItem("", "");
		listBox.setSelectedIndex(0);

		int i = 1;
		for (CodeVo c : codes) {
			listBox.addItem(c.getLabel(), c.getCode());
			if (code.equals(c.getCode()))
				listBox.setSelectedIndex(i);
			i++;
		}
		listBox.setWidth(IPCXMLDataEntryUtils.FIELD_WIDTH);
		dropDownMap.put(label + ftvoCode, listBox);
		listBoxPanel.add(text);
		listBoxPanel.add(listBox);
		return listBoxPanel;
	}

	private  HorizontalPanel buildExactValuePanel(Double value, String ftvoCode) {
		HorizontalPanel exactValuePanel = new HorizontalPanel();
		exactValuePanel.setSpacing(IPCXMLDataEntryUtils.SPACING);
		HTML label = new HTML("<b>" + "Exact Value" + ": </b>");
		label.setWidth(IPCXMLDataEntryUtils.LABEL_WIDTH);
		TextField<String> exactValue = new TextField<String>();
		if (value != null)
			exactValue.setValue(value.toString());
		exactValue.setWidth(IPCXMLDataEntryUtils.FIELD_WIDTH);
		exactValuePanel.add(label);
		exactValuePanel.add(exactValue);
		exactValueMap.put(ftvoCode, exactValue);
		return exactValuePanel;
	}

	public  Map<String, TextField<String>> getExactValueMap() {
		return exactValueMap;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}