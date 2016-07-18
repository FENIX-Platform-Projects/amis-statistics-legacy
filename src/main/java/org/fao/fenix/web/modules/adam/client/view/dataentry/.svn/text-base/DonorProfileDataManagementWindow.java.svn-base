package org.fao.fenix.web.modules.adam.client.view.dataentry;

import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.DonorProfileEditorController;
import org.fao.fenix.web.modules.adam.common.model.Donor;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DonorProfileDataManagementWindow {

	private LayoutContainer mainContainer;

	private ContentPanel selectionPanel;
	private ContentPanel profilePanel;

	private ToolBar filterToolbar;

	private ListStore<BeanModel> store; 
	private SimpleComboBox<String> sort;  
	private ListView<BeanModel> profileListView;  



	private Window window;


	public DonorProfileDataManagementWindow() {

		window = new Window();
		window.setId("profile-management");
		window.setSize(950, 550);
		window.setHeading(BabelFish.print().resourcePartnerProfileDataManagement());  


		mainContainer = new LayoutContainer();
		mainContainer.setLayout(new BorderLayout());  
		mainContainer.setStyleAttribute("padding", "10px"); 
		mainContainer.setWidth(900);  
		mainContainer.setHeight(500);  

		//Build profile selection panel
		buildSelectionPanel();

		//Build profile edit panel
		buildEditProfilePanel();


		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);  
		centerData.setMargins(new Margins(0));  
		centerData.setMinSize(550);


		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 300);  
		westData.setSplit(true);  
		westData.setCollapsible(true);  
		westData.setMargins(new Margins(0,5,0,0));  


		mainContainer.add(selectionPanel, westData);  
		mainContainer.add(profilePanel, centerData);  


		window.add(mainContainer);

		window.show();


	}




	private void buildSelectionPanel(){
		RpcProxy<List<Donor>> proxy = new RpcProxy<List<Donor>>() {  
			@Override  
			protected void load(Object loadConfig, AsyncCallback<List<Donor>> callback) {  
				ADAMServiceEntry.getInstance().getDonorsWithProfiles(callback);  
			}  
		};  

		ListLoader<ListLoadResult<BeanModel>> loader = new BaseListLoader<ListLoadResult<BeanModel>>(proxy,  
				new BeanModelReader());  
		store = new ListStore<BeanModel>(loader);  
		loader.load();  



		selectionPanel = new ContentPanel();  
		selectionPanel.setHeaderVisible(true);
		selectionPanel.setHeading(BabelFish.print().resourcePartnerProfilesInADAM());

		selectionPanel.setBorders(true);  
		selectionPanel.setBodyBorder(false);  
		selectionPanel.setLayout(new FitLayout());  

		filterToolbar = new ToolBar(); 
		filterToolbar.add(new LabelToolItem(BabelFish.print().enterProfileToFilter()));  


		StoreFilterField<BeanModel> field = new StoreFilterField<BeanModel>() {  
			@Override  
			protected boolean doSelect(Store<BeanModel> store, BeanModel parent, BeanModel record, String property,  
					String filter) {  
				Donor donor = record.getBean();  
				String name = donor.getName().toLowerCase();   
				if (name.indexOf(filter.toLowerCase()) != -1) {  
					return true;  
				}  
				return false;  
			}  

			@Override  
			protected void onFilter() {  
				super.onFilter();  
				profileListView.getSelectionModel().select(0, false);  
			}  

		};  

		field.setWidth(100);  
		field.bind(store);  


		filterToolbar.add(field);  
		filterToolbar.add(new SeparatorToolItem());  
		filterToolbar.add(new LabelToolItem("Sort By:"));  

		sort = new SimpleComboBox<String>();  
		sort.setTriggerAction(TriggerAction.ALL);  
		sort.setEditable(false);  
		sort.setForceSelection(true);  
		sort.setWidth(90);  
		sort.add("Name");  
		sort.add("Last Modified");  
		sort.setSimpleValue("Name");  
		sort.addListener(Events.Select, new Listener<FieldEvent>() {  
			public void handleEvent(FieldEvent be) {  
				sort();  
			}  
		});  

		//SORT HIDDEN
		//filterToolbar.add(sort);  

		selectionPanel.setTopComponent(filterToolbar);  

		//selectionPanel.addButton(new Button("Add a Profile" ));  
		//selectionPanel.setButtonAlign(HorizontalAlignment.LEFT);  


		profileListView = new ListView<BeanModel>() {  
			@Override  
			protected BeanModel prepareData(BeanModel model) {  
				Donor donor = model.getBean();  
				String s = donor.getName();;
				GWT.log(s, null);
				model.set("shortName", Format.ellipse(s, 30)); 
				return model;  
			}  
		};  

		profileListView.setId("profile-chooser-view");  
		//view.setTemplate(getTemplate());  
		profileListView.setBorders(false);  
		profileListView.setStore(store); 
		profileListView.setDisplayProperty("name");  
		profileListView.getSelectionModel().select(0, false) ;
		//view.setItemSelector("div.thumb-wrap");  
		profileListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  
		profileListView.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {  
			public void handleEvent(SelectionChangedEvent<BeanModel> be) {  
				onSelectionChange(be);  
			}  
		});  
		selectionPanel.add(profileListView);  		

	}

	private void buildEditProfilePanel(){
		profilePanel = new ContentPanel();  
		profilePanel.setHeaderVisible(true);
		profilePanel.setBorders(true);  
		profilePanel.setBodyBorder(false);  
		profilePanel.setLayout(new FitLayout());
		profilePanel.setBodyStyle("padding: 6px");  

		profilePanel.addText("<center><b>"+BabelFish.print().pleaseClickOnProfile()+"</b></center>");

	}

	private void sort() {  
		String v = sort.getSimpleValue();  
		if (v.equals("Name")) {  
			store.sort("name", SortDir.ASC);  
		} else {  
			store.sort("date", SortDir.ASC);  
		}  
	}  

	private void onSelectionChange(SelectionChangedEvent<BeanModel> se) {  
		if (se.getSelection().size() > 0) { 
			Donor model = se.getSelectedItem().getBean();
			fillProfilePanel(model);
		} //else {  
			//profilePanel.el().setInnerHtml("");  
		//}  
	}  

	private void fillProfilePanel(Donor donor) {
		profilePanel.remove(profilePanel.getWidget(0));
		profilePanel.setHeading(donor.getName());
		DonorProfileEditorController.getDonorProfile(profilePanel, window, donor.getCode(), donor.getName());
	}


	private native String getTemplate() /*-{ 
	     return ['<tpl for=".">', 
	     '<div class="thumb-wrap" id="{gaulCode}">', 
	     '<span class="x-editable">{gaulLabel} {refDate}</span></div>', 
	      '</tpl>', 
	      '<div class="x-clear"></div>'].join(""); 

	      }-*/;  

}