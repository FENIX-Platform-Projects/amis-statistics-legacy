package org.fao.fenix.web.modules.coding.client.view.creator;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.control.creator.DcmtCodingCreatorController;
import org.fao.fenix.web.modules.coding.client.control.search.CodingFindConvertController;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;



public class DcmtCodingCreatorMenu extends LayoutContainer {
	
	private HorizontalPanel panel;
	private TextBox searchTextBox;
	private Button search;
	private Button searchCode;
	private List<String> searchList = new ArrayList<String>();
	private ListBox searchByBox;
	private ListBox codingSystemBox;
	private ListBox convertCodingSystemBox;
	private ListBox langCodes;
	private Button saveToFile;
	private Button saveToDb;
	private Button removeCode;
	private HTML codingSystem;
	private HTML csLabel;
	private HTML csDescription;
	private HTML dcmt;
	private HTML dcmtLabel;
	private String label;
	private String cs;
	
	private ListStore<DcmtCodingCreatorGridMD> md;
	private EditorGrid<DcmtCodingCreatorGridMD> grid;
	private DcmtCodingCreatorPager pager;
	private DcmtCodingCreatorMenu dcmtCodingCreatorMenu;
	private List<DcmtCodingCreatorGridMD> modifiedCodes;
	private CodingSystemVo cSystem;
	private int currentIndex;
	private String currentDcmtCode;
	private String lang;


	public DcmtCodingCreatorMenu() {
		initiateSearchByMap();
		initializeModifiedCodes();
		initializeCodingSystem();
		setDcmtCodingCreatorMenu(this);

	}
	
	public void initializeModifiedCodes(){
		this.modifiedCodes = new ArrayList<DcmtCodingCreatorGridMD>();
		System.out.println("initialize modified codes " + this.modifiedCodes);
	} 
	
	public void initializeCodingSystem(){
		this.cSystem = new CodingSystemVo();
	} 


	public ContentPanel build(DcmtCodingCreatorWindow window, DcmtCodingCreatorResult dcmtCodingCreatorResult) {
		ContentPanel panel = new ContentPanel();
		panel.setStyleName("my-bg");
		VerticalPanel vPanel = new VerticalPanel();
		//vPanel.setSpacing(5);
		vPanel.add(buildSearchPanel());
		vPanel.add(buildWherePanel());
		vPanel.add(buildGridPanel());
		vPanel.add(buildButtonPanel());
		vPanel.add(buildDescriptionTextPanel());
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.add(vPanel);
		panel.setHeight(550);

		enhance(window, dcmtCodingCreatorResult);
		return panel;
	}
	

		
	private void enhance(DcmtCodingCreatorWindow window, DcmtCodingCreatorResult dcmtCodingCreatorResult) {
		search.addSelectionListener(DcmtCodingCreatorController.getQuerySizeAndReInitialize(getDcmtCodingCreatorMenu()));
		searchCode.addSelectionListener(DcmtCodingCreatorController.searchCodeButton(grid,  window));
		saveToDb.addSelectionListener(DcmtCodingCreatorController.uploadModifiedCodes(getDcmtCodingCreatorMenu()));		
	}

	private HorizontalPanel buildSearchPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		HTML label = new HTML("<b>" +  BabelFish.print().searchBy() + ": </b>");
		searchByBox = new ListBox();
		getSearchingListBox(searchByBox);
		searchTextBox = new TextBox();
		label.setWidth("70px");
		searchByBox.setWidth("150px");
		searchTextBox.setWidth("230px");
		panel.setWidth("450px");
		panel.add(label);
		panel.add(searchByBox);
		panel.add(searchTextBox);
		return panel;
	}
	
	private HorizontalPanel buildWherePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		HTML label1 = new HTML("<b>" + BabelFish.print().in() + ": </b>");
		HTML label2 = new HTML("<b>" + BabelFish.print().classification() + " </b>");
		codingSystemBox = new ListBox();
		getCodingSystemListBox(codingSystemBox);
		label1.setWidth("70px");
		label2.setWidth("110px");
		panel.add(label1);
		panel.add(codingSystemBox);
		panel.add(label2);
		codingSystemBox.setWidth("150px");
		langCodes = new ListBox();
		getLangCodes(langCodes);
		langCodes.setWidth("55px");
		search = new Button(BabelFish.print().search());
		
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
	        public void handleEvent(ComponentEvent ce) {  
	        	setCs(getCodingSystemBox().getItemText(getCodingSystemBox().getSelectedIndex()));
		        }
		    };  
		search.addListener(Events.OnClick, t);
		panel.add(langCodes);
		panel.add(search);
		panel.setWidth("450px");
		return panel;
	}
	
	
	private VerticalPanel buildButtonPanel() {
		ContentPanel panel = new ContentPanel();
		VerticalPanel vPanel = new VerticalPanel();
		panel.setPosition(0, 5);
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(5);
		searchCode = new Button(BabelFish.print().searchDcmt());
		//removeCode = new Button(I18N.print().removeCode());
		saveToFile = new Button(BabelFish.print().saveToFile());
		saveToDb = new Button(BabelFish.print().saveToDb());
		//hPanel.add(removeCode);	
		hPanel.add(searchCode);
		hPanel.add(saveToFile);
		hPanel.add(saveToDb);
		vPanel.add(hPanel);
		panel.setHeaderVisible(false);	
		panel.setBorders(false);
		panel.setSize(700, 35);
		return vPanel;
	}

	
	private ContentPanel buildDescriptionTextPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setPosition(5,5);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		codingSystem = new HTML();
		csLabel = new HTML();
		csDescription = new HTML();		
		dcmt = new HTML();
		dcmtLabel = new HTML();
		vPanel.add(codingSystem);
		vPanel.add(csLabel);
		vPanel.add(csDescription);
		vPanel.add(dcmt);
		vPanel.add(dcmtLabel);
		panel.setSize("750px", "120px");
		panel.setBorders(false);
		panel.setScrollMode(Scroll.AUTO);
		panel.setHeaderVisible(false);
		panel.add(vPanel);
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
	
	public void getCodingSystemListBox(ListBox listBox) {
		listBox.addItem("----");
		DcmtCodingCreatorController.findAllCodingSystems(listBox);		
	}
	

	
	
	public ContentPanel buildGridPanel(){
		ContentPanel panel = new ContentPanel();
		panel.setPosition(5, 5);
		  
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		 
		md = new ListStore<DcmtCodingCreatorGridMD>();	    
		 
		
		ColumnConfig column = new ColumnConfig();  
		column.setId("code");  
		column.setHeader(BabelFish.print().code());  
		column.setWidth(100);  	   
		column.setMenuDisabled(true);
		column.setSortable(false);
		configs.add(column);
		 
		
		column = new ColumnConfig();  
		column.setId("dcmtCode");  
		column.setHeader("Dcmt " + BabelFish.print().code());  
		column.setWidth(150);
		column.setMenuDisabled(true);
		column.setSortable(false);
		TextField<String> dcmtCode = new TextField<String>();   
		column.setEditor(new CellEditor(dcmtCode));
		configs.add(column);
		
		 
		column = new ColumnConfig();  
		column.setId("label");  
		column.setHeader(BabelFish.print().label());  
		column.setWidth(465);  
		column.setMenuDisabled(true);
		column.setSortable(false);
		configs.add(column); 
			
	    ColumnModel cm = new ColumnModel(configs);  
		
	    
	    
		grid = new EditorGrid<DcmtCodingCreatorGridMD>(md ,cm);  
		GridSelectionModel<DcmtCodingCreatorGridMD> sm =  new GridSelectionModel<DcmtCodingCreatorGridMD>();
		sm.setSelectionMode(SelectionMode.SINGLE);
		grid.setSelectionModel(sm);
		grid.setClicksToEdit(ClicksToEdit.TWO);
		grid.setSize(730, 200);	
		panel.add(grid);
		panel.setHeaderVisible(false);
		panel.setSize(750, 250);
		
		//panel.setScrollMode(Scroll.AUTO);
		panel.setLayout(new FitLayout());
		//panel.setLayout(new FlowLayout());
		panel.setBodyBorder(true);  
		// panel.setBottomComponent(toolBar);  

			
		
		grid.setStyleAttribute("borderTop", "none");	
		grid.setTrackMouseOver(true);
		
		grid.addListener(Events.CellClick, fillDescription());
		grid.addListener(Events.CellDoubleClick, beforeEdit());
		grid.addListener(Events.HeaderClick, sorting());
		grid.addListener(Events.BeforeEdit, edited());
		grid.addListener(Events.AfterEdit, afterEdit());
		

		
		pager = new DcmtCodingCreatorPager();
		panel.setBottomComponent(pager.build(this));
		
		return panel;
	}
	
	private  Listener<ComponentEvent> fillDescription(){
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) { 
		        EditorGrid<DcmtCodingCreatorGridMD> g = (EditorGrid<DcmtCodingCreatorGridMD>) ce.getComponent();
		        DcmtCodingCreatorGridMD m = (DcmtCodingCreatorGridMD) g.getSelectionModel().getSelectedItem();	        
		        getCodingSystem().setHTML("<b>" + getCs() + ":</b>");
		        getCsLabel().setHTML("<b>" +BabelFish.print().label() + ": </b>" + m.getLabel() + "</b>");
		        getCsDescription().setHTML("<b>" + BabelFish.print().explonatoryNote() + ":</b>");
		        getDcmt().setHTML("<b> DCMT: </b>");           	
		        getDcmtLabel().setHTML("<b>" + BabelFish.print().label() + ":</b> ");
		        if ( !m.getDcmtCode().isEmpty() ) {
		        	DcmtCodingCreatorController.getDcmtLabel(getDcmtCodingCreatorMenu(), m.getDcmtCode());
		        }
			}
		};  
		return t;
	}
	
	
	private  Listener<ComponentEvent> sorting(){
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) {  
		        EditorGrid<DcmtCodingCreatorGridMD> g = (EditorGrid<DcmtCodingCreatorGridMD>) ce.getComponent();
				    getCodingSystem().setHTML("<b>" + getCs() + ":</b>");
		         	getCsLabel().setHTML("<b>" +BabelFish.print().label() + ": </b>" );
		            getCsDescription().setHTML("<b>" + BabelFish.print().explonatoryNote() + ":</b>");
		            getDcmt().setHTML("<b> DCMT: </b>");           	
		            getDcmtLabel().setHTML("<b>" + BabelFish.print().label() + ":</b> ");          
		            Element elem = ce.getTarget();
		            DcmtCodingCreatorGridMD m = (DcmtCodingCreatorGridMD) g.getSelectionModel().getSelectedItem();            
		            GridView view = getGrid().getView();
		            pager.setSorting(view.findCellIndex(elem, "x-grid3-hd"));
		            System.out.println(getPager().getSortingType() + " " +  getPager().getColumnIndex() + " " + (getPager().getActualPage() -1));
		            Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (getPager().getActualPage() -1) ;
					Integer toIndex = TableConstants.ITEMS_PER_PAGE;
					System.out.println("fromInde " + fromIndex);
					if ((fromIndex + toIndex) > getPager().getTotalRows())
						fromIndex = getPager().getTotalRows() - 1;
		            DcmtCodingCreatorController.searchButton(getDcmtCodingCreatorMenu(),  fromIndex, toIndex);	
			    }
			};  
		return t;
	}
	
	private  Listener<ComponentEvent> edited(){
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) {  
				EditorGrid<DcmtCodingCreatorGridMD> g = (EditorGrid<DcmtCodingCreatorGridMD>) ce.getComponent();
	            DcmtCodingCreatorGridMD m = (DcmtCodingCreatorGridMD) g.getSelectionModel().getSelectedItem();
	            //System.out.println("edited "+ m.getDcmtCode() + " dcmtCode "+ getCurrentDcmtCode().getDcmtCode()  +"\n");
				//DcmtCodingCreatorController.insertChangedCode(getDcmtCodingCreatorMenu(), m);
	            //System.out.println("edited "+ m.getDcmtCode());
	          
			    }
			};  
		return t;
	}
	
	private  Listener<ComponentEvent> afterEdit(){
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) {  
				EditorGrid<DcmtCodingCreatorGridMD> g = (EditorGrid<DcmtCodingCreatorGridMD>) ce.getComponent();
	            DcmtCodingCreatorGridMD m = (DcmtCodingCreatorGridMD) g.getSelectionModel().getSelectedItem();
	           // System.out.println("afterEdit: "+ m.getCode() +" " + m.getDcmtCode() + " | dcmtCode: "+ getCurrentDcmtCode().getCode()  + " " + getCurrentDcmtCode().getDcmtCode() + " | " + getCurrentIndex());
	            //getMd().getAt(index)
	            DcmtCodingCreatorGridMD now = getMd().getAt(getCurrentIndex());
	           // System.out.println("afterEdit2: "+ now.getCode()+ " " + now.getDcmtCode() + " | m " + m.getCode() + " " + m.getDcmtCode() + " | dcmtCode: "+ getCurrentDcmtCode().getCode()  + " " + getCurrentDcmtCode().getDcmtCode() + " | " + getCurrentIndex() + "\n");
	            System.out.println("afterEdit2: "+ now.getCode()+ " " + now.getDcmtCode() + " | m " + m.getCode() + " " + m.getDcmtCode() + " | dcmtCode: "+ getCurrentDcmtCode()   + " | " + getCurrentIndex() + "\n");
	            if ( now.getDcmtCode() == null && !getCurrentDcmtCode().isEmpty() )
	            	DcmtCodingCreatorController.insertChangedCode(getDcmtCodingCreatorMenu(), now);
	            else if ( (now.getDcmtCode() != null) && (!now.getDcmtCode().equals(getCurrentDcmtCode()))) {
	            	DcmtCodingCreatorController.insertChangedCode(getDcmtCodingCreatorMenu(), now);
	            }
	            getMd().commitChanges();	
			    }
			};  
		return t;
	}
	
	private  Listener<ComponentEvent> beforeEdit(){
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) { 
				EditorGrid<DcmtCodingCreatorGridMD> g = (EditorGrid<DcmtCodingCreatorGridMD>) ce.getComponent();
	            DcmtCodingCreatorGridMD m = (DcmtCodingCreatorGridMD) g.getSelectionModel().getSelectedItem();
				setCurrentDcmtCode(m.getDcmtCode());
	            setCurrentIndex(getMd().indexOf(m));    
	            System.out.println("beforeEdit "+ m.getDcmtCode() + "\n");
			    }
			};  
		return t;
	}

	
	private HorizontalPanel buildPagerPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		//panel.setSpacing(5);
		//panel.setPosition(15, 0);
		pager = new DcmtCodingCreatorPager();
		panel = pager.build(this);
		panel.setBorders(true);
		panel.setLayout(new FlowLayout());
		return panel;
	}
	
	
	
	public void getLangCodes(ListBox listBox) {
		CodingFindConvertController.findOfficialLangCodesFao(listBox);		
	}

	public EditorGrid<DcmtCodingCreatorGridMD> getGrid() {
		return grid;
	}


	public void setGrid(EditorGrid<DcmtCodingCreatorGridMD> grid) {
		this.grid = grid;
	}


	public HorizontalPanel getPanel() {
		return panel;
	}


	public TextBox getSearchTextBox() {
		return searchTextBox;
	}


	public Button getSearch() {
		return search;
	}


	public Button getSearchCode() {
		return searchCode;
	}


	public List<String> getSearchList() {
		return searchList;
	}


	public ListBox getSearchByBox() {
		return searchByBox;
	}


	public ListBox getCodingSystemBox() {
		return codingSystemBox;
	}


	public ListBox getConvertCodingSystemBox() {
		return convertCodingSystemBox;
	}

	public Button getSaveToFile() {
		return saveToFile;
	}

	public Button getSaveToDb() {
		return saveToDb;
	}

	public Button getRemoveCode() {
		return removeCode;
	}

	public HTML getCodingSystem() {
		return codingSystem;
	}

	public void setCodingSystem(HTML codingSystem) {
		this.codingSystem = codingSystem;
	}

	public HTML getCsLabel() {
		return csLabel;
	}

	public void setCsLabel(HTML csLabel) {
		this.csLabel = csLabel;
	}

	public HTML getCsDescription() {
		return csDescription;
	}

	public void setCsDescription(HTML csDescription) {
		this.csDescription = csDescription;
	}


	public HTML getDcmt() {
		return dcmt;
	}

	public void setDcmt(HTML dcmt) {
		this.dcmt = dcmt;
	}

	public HTML getDcmtLabel() {
		return dcmtLabel;
	}

	public void setDcmtLabel(HTML dcmtLabel) {
		this.dcmtLabel = dcmtLabel;
	}

	public String getLabel() {
		return label;
	}


	public ListStore<DcmtCodingCreatorGridMD> getMd() {
		return md;
	}


	public String getCs() {
		return cs;
	}


	public void setCs(String cs) {
		this.cs = cs;
	}


	public DcmtCodingCreatorPager getPager() {
		return pager;
	}


	public void setPager(DcmtCodingCreatorPager pager) {
		this.pager = pager;
	}

	public DcmtCodingCreatorMenu getDcmtCodingCreatorMenu() {
		return dcmtCodingCreatorMenu;
	}


	public void setDcmtCodingCreatorMenu(DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		this.dcmtCodingCreatorMenu = dcmtCodingCreatorMenu;
	}

	

	public List<DcmtCodingCreatorGridMD> getModifiedCodes() {
		return modifiedCodes;
	}

	public void setModifiedCodes(List<DcmtCodingCreatorGridMD> modifiedCodes) {
		this.modifiedCodes = modifiedCodes;
	}

	public CodingSystemVo getCSystem() {
		return cSystem;
	}

	public void setCSystem(CodingSystemVo system) {
		cSystem = system;
	}

	public ListBox getLangCode() {
		return langCodes;
	}

	public void setLangCode(ListBox langCodes) {
		this.langCodes = langCodes;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public String getCurrentDcmtCode() {
		return currentDcmtCode;
	}

	public void setCurrentDcmtCode(String currentDcmtCode) {
		this.currentDcmtCode = currentDcmtCode;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	

}