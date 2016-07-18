package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.client.control.IPCXMLController;
import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;

public class IPCXMLDataEntryUtils {
	
	public final static Integer SPACING = 3;
	
	public final static String DROP_DOWN_WIDTH = "400px";
	
	public final static String FREE_TEXT_WIDTH = "400px";
	
	public final static String LABEL_WIDTH = "100px";
	
	public final static String LONG_LABEL_WIDTH = "250px";
	
	public final static String FIELD_WIDTH = "200px";
	
	public final static String LONG_FIELD_WIDTH = "350px";
	
	public final static String FIELD_HEIGHT = "150px";
	
	public final static String TEXT_AREA_WIDTH = "250px";
	
	public final static String TEXT_AREA_HEIGHT = "50px";
	
	
	public static DropDownVO findDropDown(List<DropDownVO> dropDowns, String code) {
		for (DropDownVO ddvo : dropDowns)
			if (ddvo.getCode().equalsIgnoreCase(code))
				return ddvo;
		return null;
	}
	
	public static FreeTextVO findFreeText(List<FreeTextVO> freeTexts, String code) {
		for (FreeTextVO ftvo : freeTexts)
			if (ftvo.getCode().equalsIgnoreCase(code))
				return ftvo;
		return null;
	}
	

	
	public static VerticalPanel buildBigTextAreaPanel() {
		return buildTextAreaPanel("450px", "430px", 430);
	}
	
	public static VerticalPanel buildSmallTextAreaPanel() {
		return buildTextAreaPanel("300px", "280px", 283);
	}
	
	public static VerticalPanel buildBigDropDownPanel(DropDownVO ddvo, List<CodeVo> codes) {
		return buildDropDownPanel(ddvo, codes, "450px", "200px", "250px");
	}
	
	public static VerticalPanel buildSmallDropDownPanel(DropDownVO ddvo, List<CodeVo> codes) {
		return buildDropDownPanel(ddvo, codes, "290px", "100px", "200px");
	}
	
	private static VerticalPanel buildTextAreaPanel(String panelWidth, String textAreaWidth, Integer buttonWidth) {
		VerticalPanel textAreaPanel = new VerticalPanel();
		textAreaPanel.setSpacing(SPACING);
		textAreaPanel.setBorders(true);
		textAreaPanel.setWidth(panelWidth);	
		VerticalPanel ta = new VerticalPanel();
		ta.setWidth(textAreaWidth);

		ta.setScrollMode(Scroll.AUTO);
		textAreaPanel.setScrollMode(Scroll.AUTO);
//		RichTextArea textArea = new RichTextArea();
//		textArea.setHTML("");
//		textArea.setSize(textAreaWidth, FIELD_HEIGHT);
//		textArea.setEnabled(true);	
		HTML html = new HTML();

		
		ta.add(html);
		textAreaPanel.add(ta);
		textAreaPanel.add(buildButtonsPanel(html, buttonWidth));
		textAreaPanel.setData("textArea", html);
		
//		textAreaPanel.add(textArea);
//		textAreaPanel.add(buildButtonsPanel(textArea, buttonWidth));
//		textAreaPanel.setData("textArea", textArea);
		return textAreaPanel;
	}
	
	private static HorizontalPanel buildButtonsPanel(RichTextArea textArea, int buttonWidth) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(SPACING);
		Button edit = new Button("<b>Edit</b>");
		edit.setMinWidth(buttonWidth);
		edit.addSelectionListener(IPCXMLController.editText(textArea));
		buttonsPanel.add(edit);
		return buttonsPanel;
	}
	
	private static HorizontalPanel buildButtonsPanel(HTML html, int buttonWidth) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(SPACING);
		Button edit = new Button("<b>Edit</b>");
		edit.setMinWidth(buttonWidth);
		edit.addSelectionListener(IPCXMLController.editText(html));
		buttonsPanel.add(edit);
		return buttonsPanel;
	}
	
	private static VerticalPanel buildDropDownPanel(DropDownVO ddvo, List<CodeVo> codes, String panelWidth, String labelWidth, String fieldWidth) {
		VerticalPanel dropDownPanel = new VerticalPanel();
		dropDownPanel.setSpacing(IPCXMLDataEntryUtils.SPACING);
		dropDownPanel.setBorders(true);
		dropDownPanel.setSize(panelWidth, "190px");
		HTML label = new HTML("<b>" + ddvo.getName() + ": </b>");
		label.setWidth(labelWidth);
		ListBox listBox = new ListBox();
		int i=1;
		listBox.addItem("", "0");
		if ( codes != null)
			for(CodeVo c : codes) {
				listBox.addItem(c.getLabel(), c.getCode());
				if ( ddvo.getDropDownCode().equals(c.getCode()))
					listBox.setSelectedIndex(i);
				i++;
			}
		listBox.setWidth(fieldWidth);
		dropDownPanel.add(label);
		dropDownPanel.add(listBox);
		dropDownPanel.setData("dropDown", listBox);
		return dropDownPanel;
	}
	
}