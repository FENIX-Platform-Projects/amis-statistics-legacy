package org.fao.fenix.web.modules.cpf.client.view.find;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.core.client.utils.CustomListStore;
import org.fao.fenix.web.modules.cpf.client.control.find.CPFFindController;
import org.fao.fenix.web.modules.cpf.client.view.menu.CPFMainMenu;
import org.fao.fenix.web.modules.cpf.client.view.utils.layout.FormattingUtils;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class CPFFindPanel {

	
	//cache the data
	CPFSearchCache cpfSearchCache;
	CPFSearchOutput cpfSearchOutput;
	CPFSearchResults cpfSearchResults;
	//CPFSearchTypes cpfSearchTypes;
	
	CPFMainMenu CPFMainMenu;
	HorizontalPanel holder;
	
	ContentPanel panel;
	
	private ComboBox<BaseModel> findByCombo;
	private ComboBox<BaseModel> loaderCombo;
	public TextField<String> findTextField;
	private ListStore<BaseModel> store;
	
	ClickHtml find;
	
	// Dimensions
	String panelWidth = "300px";
	String widthCombo = "100px";
	String widthTextField = "200px";
	
	public String currentFindValue = "";
	
	
	public CPFFindPanel(CPFMainMenu mainMenu) {
			
		cpfSearchCache = new CPFSearchCache();
		cpfSearchOutput = new CPFSearchOutput();
		cpfSearchResults = new CPFSearchResults();
		//cpfSearchTypes = new CPFSearchTypes();
		
		
		panel = new ContentPanel();
		panel.addStyleName("blue-background");
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setBorders(false);
		panel.setAutoWidth(true);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		this.CPFMainMenu = mainMenu;
		
		holder = new HorizontalPanel();
		
		store = new ListStore<BaseModel>();
		store = populateStore(store);	
		
		findByCombo = new ComboBox<BaseModel>();
		findByCombo.setStore(store);
		findByCombo.setValue(store.getAt(0));
		findByCombo.setDisplayField("label");  
		findByCombo.setTypeAhead(true);   
		findByCombo.setTriggerAction(TriggerAction.ALL);   
		findByCombo.addSelectionChangedListener(CPFFindController.filterCriteria(findByCombo, findTextField));
		findByCombo.setWidth(widthCombo);
		
		ListStore<BaseModel> storeY = new ListStore<BaseModel>();     
		
		List<ModelData> mods = new ArrayList<ModelData>();
		BaseModel type = new BaseModel();   
	    type.set("title", "frank");   
	    type.set("topicId", "topic id 1");   
	    type.set("author", "author 1");   
	    type.set("excerpt", "post text 1");  
	    
	    storeY.add(type);
	    
	    mods.add(type);
	    
	    BaseModel type1 = new BaseModel();   
	    type1.set("title", "spencer");    
	    type1.set("topicId", "topic id 2");   
	    type1.set("author", "author 2");   
	    type1.set("excerpt", "post text 2");  
	    
	    storeY.add(type1);
	    
	    mods.add(type1);
	    
	    BaseModel type2 = new BaseModel();   
	    type2.set("title", "greg area");     
	    type2.set("topicId", "topic id 2");   
	    type2.set("author", "author 2");   
	    type2.set("excerpt", "post text 2");  
	    
	    storeY.add(type2);
	    
	    mods.add(type2);
	    
	    
	 // add paging support for a local collection of models   
	    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(mods);   
	  
	    // loader   
	    PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);   
	    loader.setRemoteSort(true);   
	  
	    // add paging support for a local collection of models   
	  //  PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(mods);   
	  
	    // loader   
	   // PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
	   // loader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {   
	   //     public void handleEvent(LoadEvent be) {   
	   //       be.<ModelData> getConfig().set("start", be.<ModelData> getConfig().get("offset"));   
	   //     }   
	   //   });   
	
	    
		CustomListStore<BaseModel> storeX = new CustomListStore<BaseModel>(loader);     
		
	     
	 
	   /* loaderCombo = new ComboBox<BaseModel>();   
	    loaderCombo.setWidth(250);   
	    loaderCombo.setDisplayField("title");   
	    loaderCombo.setValueField("title");   
	    loaderCombo.setItemSelector("div.search-item");   
	    loaderCombo.setTemplate(getTemplate());   
	    loaderCombo.setStore(storeX);   
	    loaderCombo.setHideTrigger(true);  
	    loaderCombo.addSelectionChangedListener(CPFFindController.setValue(loaderCombo));
	    loaderCombo.setMinChars(3); 
	    loaderCombo.setTypeAhead(false);*/
	    
	    loaderCombo = new ComboBox<BaseModel>();
	    loaderCombo.setWidth(250);   
	    loaderCombo.setDisplayField("title");
	    loaderCombo.setItemSelector("div.search-item"); 
	    loaderCombo.setTemplate(getTemplate());   
	   // loaderCombo.setId("id");
	   // storeX.applyFilters("title", loaderCombo.getT.get);
	    loaderCombo.setStore(storeX);
	    loaderCombo.setTypeAhead(false);
	    loaderCombo.setHideTrigger(true);  
	    loaderCombo.setMinChars(3);
	    //loaderCombo.setTriggerAction(ComboBox.TriggerAction.ALL);

	    
	    //loaderCombo.setPageSize(10);   

	    
	    System.out.println("STORE SIZE = "+storeX.getCount());
	    
		findTextField = new TextField<String>();
		findTextField.setEmptyText("");
		findTextField.setSelectOnFocus(true);
		findTextField.setWidth(widthTextField);
		findTextField.addKeyListener(CPFFindController.findTextFieldEnterKeyListener(this, findTextField));
		findTextField.addListener(Events.KeyUp, CPFFindController.findTextFieldListener(this, findTextField, findByCombo));

		String text = "Find";
		find = new ClickHtml();
		find.setHtml("<div class='login_button'>" + text + "</div>");
		find.addListener(Events.OnClick, CPFFindController.findOnClickListener(this, findTextField, findByCombo));
		
		
	}
	
	private native String getTemplate() /*-{  
    return [  
    '<tpl for="."><div class="search-item">',  
    '<h3><span>{title}</span> {author}</h3>',  
    '{excerpt}',  
    '</div></tpl>'  
    ].join("");  
  }-*/;   
 
 

	 
	private ListStore<BaseModel> populateStore(ListStore<BaseModel> store) {
		BaseModel model = new BaseModel();
		model.set("code", "*");
		model.set("label", "All");
		
		store.add(model);
		
		model = new BaseModel();
		model.set("code", "cpfcode");
		model.set("label", "By Code");
		
		store.add(model);
		
		model = new BaseModel();
		model.set("code", "cpfname");
		model.set("label", "By Name");
		
		store.add(model);
		
		model = new BaseModel();
		model.set("code", "country");
		model.set("label", "By Country");
		
		store.add(model);
		
		model = new BaseModel();
		model.set("code", "or");
		model.set("label", "By FAO Strategic Framework");
		
		store.add(model);

		return store;
	}

	public ContentPanel build() {
		panel.setWidth(panelWidth);	
		panel.add(buildForm());
		
		return panel;
	}
	
	private HorizontalPanel buildForm() {
		//holder.setStyleAttribute("padding", "6px");
		holder.setBorders(false);
		holder.addStyleName("login_panel");
		
		holder.add(FormattingUtils.addHSpace(5));
		holder.add(findByCombo);
	
		
		holder.add(FormattingUtils.addHSpace(10));
		holder.add(findTextField);
		
		holder.add(FormattingUtils.addHSpace(10));
		//holder.add(find);
		holder.add(loaderCombo);
		holder.add(FormattingUtils.addHSpace(5));
		
		return holder;
	}

	public CPFMainMenu getCPFMainMenu() {
		return CPFMainMenu;
	}
	
	public ContentPanel getPanel() {
		return panel;
	}

	public HorizontalPanel getHolder() {
		return holder;
	}

	public ComboBox<BaseModel> getFindByCombo() {
		return findByCombo;
	}

	public TextField<String> getFindTextField() {
		return findTextField;
	}

	public CPFSearchCache getCpfSearchCache() {
		return cpfSearchCache;
	}

}
