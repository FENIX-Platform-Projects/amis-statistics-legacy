package org.fao.fenix.web.modules.coding.client.view.search;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.control.search.CodingFindConvertController;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;



public class CodingSearchMenu {//extends FenixWindow {
	
	private VerticalPanel panel;
	private TextBox searchTextBox;
	private Button findConvertButton;
	private Button clear;
	private Button cancel;
	private List<String> searchList = new ArrayList<String>();
	private ListBox searchByBox;
	private ListBox codingSystemBox;
	private ListBox convertCodingSystemBox;
	private ListBox langCodes;
	private ListBox category;
	
	
	CodingSearchResults codingSearchResults;
	
	public CodingSearchMenu() {
		panel = new VerticalPanel();
		category = new ListBox();
		codingSystemBox = new ListBox();
		langCodes = new ListBox();
		convertCodingSystemBox = new ListBox();

		searchByBox = new ListBox();
		initiateSearchByMap();
	}
	
	
	public VerticalPanel build(CodingSearchWindow window) {
		panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.add(buildSearchPanel(false));
		panel.add(buildCategoryPanel(false));
		panel.add(buildWherePanel());
//		panel.add(
		buildConvertPanel();
		panel.add(buildButtonPanel(false));
		enhance(window);
		return panel;
	}
	
	public VerticalPanel build(CodingSearchWindow window, ListStore<DimensionItemModel> listStore, ComboBox<DimensionItemModel> combo,  CodingSystemVo codingSystemVo) {
		panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.add(buildSearchPanel(true));
		panel.add(buildCategoryPanel(true));
		panel.add(buildWherePanel(codingSystemVo));
		panel.add(buildButtonPanel(true));
		enhance(window, listStore, combo, codingSystemVo);
		return panel;
	}
		
	
	private void enhance(CodingSearchWindow window) {
		codingSearchResults = new CodingSearchResults();
		Table table = codingSearchResults.build(window);
		findConvertButton.addSelectionListener(CodingFindConvertController.findConvertButton(searchByBox, searchTextBox, codingSystemBox, convertCodingSystemBox, langCodes, table, codingSearchResults));
		clear.addSelectionListener(CodingFindConvertController.getClearMenuListener(window));
		cancel.addSelectionListener(CodingFindConvertController.getCancelMenuListener(window));
	}
	
	private void enhance(CodingSearchWindow window, ListStore<DimensionItemModel> listStore, ComboBox<DimensionItemModel> combo,  CodingSystemVo codingSystemVo) {
		codingSearchResults = new CodingSearchResults();
		Table table = codingSearchResults.build(window, listStore, combo);
		table.setSelectionMode(SelectionMode.MULTI);
		findConvertButton.addSelectionListener(CodingFindConvertController.findConvertButton(codingSystemVo, langCodes, searchTextBox, table, codingSearchResults));
		cancel.addSelectionListener(CodingFindConvertController.getCancelMenuListener(window));
	}

	private HorizontalPanel buildSearchPanel(Boolean isTableCall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML label = new HTML("<b>" +  BabelFish.print().searchBy() + ": </b>");
		getSearchingListBox(searchByBox);
		searchTextBox = new TextBox();
		label.setWidth("70px");
		searchByBox.setWidth("150px");
		searchTextBox.setWidth("150px");
		panel.setWidth("450px");
		panel.add(label);
		panel.add(searchByBox);
		panel.add(searchTextBox);
			
		if ( isTableCall ) {
			searchByBox.setSelectedIndex(2);
			searchByBox.setEnabled(false);
		}
					
		return panel;
	}
	
	private HorizontalPanel buildCategoryPanel(Boolean isTableCall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML label1 = new HTML("<b>" + BabelFish.print().in() + ": </b>");
		HTML label2 = new HTML("<b>" + BabelFish.print().category() + " </b>");
		getCategories(category);
		category.addChangeListener(fillCommoditiesList(category));
		label1.setWidth("70px");
		label2.setWidth("90px");
		panel.add(label1);
		panel.add(category);
		panel.add(label2);
		category.setWidth("150px");
		panel.setWidth("450px");
		
		if ( isTableCall )
			category.setEnabled(false);
		
		return panel;
	}
	
	private HorizontalPanel buildWherePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML label1 = new HTML("<b>" + BabelFish.print().in() + ": </b>");
		HTML label2 = new HTML("<b>" + BabelFish.print().classification() + " </b>");
		getCodingSystemListBox(codingSystemBox);
		getLangCodes(langCodes);
		label1.setWidth("70px");
		label2.setWidth("90px");
		langCodes.setWidth("50px");
		panel.add(label1);
		panel.add(codingSystemBox);
		panel.add(label2);
		panel.add(langCodes);
		codingSystemBox.setWidth("150px");
		panel.setWidth("450px");
		return panel;
	}
	
	private HorizontalPanel buildConvertPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		HTML label1 = new HTML("<b> " + BabelFish.print().ConvertInto() +": </b>");
		HTML label2 = new HTML("<b> " + BabelFish.print().classification() +" </b>");
		getSecondCodingSystemListBox(convertCodingSystemBox);
		
		label1.setWidth("70px");
		label2.setWidth("120px");
		convertCodingSystemBox.setWidth("150px");
		panel.setWidth("450px");
		return panel;
	}
	
	private HorizontalPanel buildButtonPanel(Boolean isTableCall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(15);
		findConvertButton = new Button(BabelFish.print().findConvert());
		clear = new Button(BabelFish.print().clear());
		cancel = new Button(BabelFish.print().cancel());
		panel.add(findConvertButton);
		if ( !isTableCall ) 
			panel.add(clear);
		panel.add(cancel);
		panel.setWidth("450px");
		return panel;
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
	
	public void getCategories(ListBox listBox) {
		listBox.addItem("----");		
		listBox.addItem("Commodity");
		listBox.addItem("Geographic");
		listBox.addItem("Indicator");
		listBox.addItem("EconomicActivity");
		listBox.addItem("Occupation");
		listBox.addItem("Other");	
	}
	
	public void getCodingSystemListBox(ListBox listBox) {
		listBox.addItem("----");
//		listBox.addItem("ALL");
//		CodingFindConvertController.findAllCodingSystems(listBox);		
	}
	
	public void getSecondCodingSystemListBox(ListBox listBox) {
		listBox.addItem("----");
//		CodingFindConvertController.findAllCodingSystems(listBox);		
	}
	
	
	
	public void getLangCodes(ListBox listBox) {
		CodingFindConvertController.findOfficialLangCodesFao(listBox);		
	}
	
	private ChangeListener fillCommoditiesList(final ListBox category) {
		ChangeListener t = new ChangeListener() {  
			public void onChange(Widget arg0) {
				codingSystemBox.clear();
				codingSystemBox.addItem("----");
//				codingSystemBox.addItem("ALL");
				CodingFindConvertController.findAllCodingSystems(codingSystemBox, category);
			}

		};  
		return t;
	}


	public ListBox getCodingSystemBox() {
		return codingSystemBox;
	}
	
	
	
	

	
	
	
	private HorizontalPanel buildWherePanel(CodingSystemVo codingSystemVo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML label1 = new HTML("<b>" + BabelFish.print().in() + ": </b>");
		HTML label2 = new HTML("<b>" + BabelFish.print().classification() + " </b>");
//		getCodingSystemListBox(codingSystemBox);
		codingSystemBox = new ListBox();
		codingSystemBox.addItem(codingSystemVo.getTitle());
		codingSystemBox.setEnabled(false);
		getLangCodes(langCodes);
		label1.setWidth("70px");
		label2.setWidth("90px");
		langCodes.setWidth("50px");
		panel.add(label1);
		panel.add(codingSystemBox);
		panel.add(label2);
		panel.add(langCodes);
		codingSystemBox.setWidth("150px");
		panel.setWidth("450px");
		return panel;
	}
	

	
	
}