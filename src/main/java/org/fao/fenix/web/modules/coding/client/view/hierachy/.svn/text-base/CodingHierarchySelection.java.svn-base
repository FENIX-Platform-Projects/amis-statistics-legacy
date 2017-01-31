package org.fao.fenix.web.modules.coding.client.view.hierachy;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.control.hierarchy.CodingHierarchyController;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;



public class CodingHierarchySelection extends FenixWindow {
	
	private VerticalPanel panel;
	private TextBox searchTextBox;
	private Button selectionButton;
	private Button clear;
	private Button cancel;
	private List<String> searchList = new ArrayList<String>();
	private ListBox codingSystemBox;
	private ContentPanel toolBoxPanel;
	private CodingSystemVo codingSystem;
	private CodeVo code;
	private List<CodeVo> resultedCodes;
	
	
	CodingHierarchyWindow codingHierarchyWindow;

	public void build() {
		codingHierarchyWindow = new CodingHierarchyWindow();
		setCenterProperties();
		getCenter().add(buildPanel());
		addCenterPartToWindow();
		format();
		show();
	}
	
	public CodingHierarchySelection() {
		panel = new VerticalPanel();
		initiateSearchByMap();
	}
	
	
	public VerticalPanel buildPanel() {
		panel = new VerticalPanel();
		panel.setSpacing(10);	
		panel.add(buildWherePanel());
		panel.add(buildSearchPanel());		
		panel.add(buildButtonPanel());
		enhance();
		return panel;
	}
		
	private void enhance() {
		selectionButton.addSelectionListener(CodingHierarchyController.getCodingHierarchyToolBox(this));
		clear.addSelectionListener(CodingHierarchyController.printCodes(resultedCodes));
//		cancel.addSelectionListener(CodingFindConvertController.getCancelMenuListener(window));
	}

	private HorizontalPanel buildSearchPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML label = new HTML("<b>" +  BabelFish.print().code() + ": </b>");

		searchTextBox = new TextBox();
		label.setWidth("70px");
		searchTextBox.setWidth("140px");
		panel.setWidth("450px");
		panel.add(label);
		panel.add(searchTextBox);
		return panel;
	}
	
	private HorizontalPanel buildWherePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML label1 = new HTML("<b>" + BabelFish.print().codingSystem()+ ": </b>");
		codingSystemBox = new ListBox();
		getCodingSystemListBox(codingSystemBox);
		label1.setWidth("70px");
		panel.add(label1);
		panel.add(codingSystemBox);
		codingSystemBox.setWidth("150px");
		panel.setWidth("450px");
		return panel;
	}
	
	
	
	private HorizontalPanel buildButtonPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(15);
		selectionButton = new Button(BabelFish.print().findConvert());
		clear = new Button(BabelFish.print().clear());
		cancel = new Button(BabelFish.print().cancel());
		panel.add(selectionButton);
		panel.add(clear);
		panel.add(cancel);
		panel.setWidth("450px");
		return panel;
	}
	
	
	
	private ContentPanel buildHierarchyToolBox() {
		toolBoxPanel = new ContentPanel();
		toolBoxPanel.setPosition(5,5);
		toolBoxPanel.setSize("450px", "120px");
//		codingHierarchyToolBox = new CodingHierarchyToolBox();
//		toolBoxPanel.add(codingHierarchyToolBox.build(getCodingSystem(), getCode()));
		toolBoxPanel.setBorders(false);
		toolBoxPanel.setScrollMode(Scroll.AUTO);
		toolBoxPanel.setHeaderVisible(false);		
		return toolBoxPanel;
	}
	
	public void buildCodingHierarchyToolBox(){
		
	}
	
	
	
	public void initiateSearchByMap(){
		searchList.add("----");
		searchList.add(BabelFish.print().code());
		searchList.add(BabelFish.print().label());
		searchList.add(BabelFish.print().description());
		searchList.add(BabelFish.print().codeSystem());
	}
	
	public void getSearchingListBox(ListBox listBox) {
		for (int i=0; i < searchList.size(); i++) {
			listBox.addItem(searchList.get(i));
		}
	}
	
	public void getCodingSystemListBox(ListBox listBox) {
		listBox.addItem("----");
		CodingHierarchyController.findAllCodingSystems(listBox);		
	}
	
	

	
	
	
	
	
	
	

	protected void format() {
		setSize(500, 350);
		getCenter().setHeaderVisible(false);
//		setTitle("<b> " + I18N.print().codingFindConvert() + " </b>");

	}

	
	
	

	public CodingHierarchyWindow getCodingHierarchyWindow() {
		return codingHierarchyWindow;
	}

	public void setCodingHierarchyWindow(CodingHierarchyWindow codingHierarchyWindow) {
		this.codingHierarchyWindow = codingHierarchyWindow;
	}

	public ContentPanel getToolBoxPanel() {
		return toolBoxPanel;
	}

	public void setToolBooxPanel(ContentPanel toolBoxPanel) {
		this.toolBoxPanel = toolBoxPanel;
	}
	
	public CodingSystemVo getCodingSystem() {
		return codingSystem;
	}

	public void setCodingSystem(CodingSystemVo codingSystem) {
		this.codingSystem = codingSystem;
	}

	public CodeVo getCode() {
		return code;
	}

	public void setCode(CodeVo code) {
		this.code = code;
	}

	public List<CodeVo> getResultedCodes() {
		return resultedCodes;
	}

	public void setResultedCodes(List<CodeVo> resultedCodes) {
		this.resultedCodes = resultedCodes;
	}
	
	
}