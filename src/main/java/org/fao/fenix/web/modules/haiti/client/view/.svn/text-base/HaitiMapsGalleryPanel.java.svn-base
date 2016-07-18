package org.fao.fenix.web.modules.haiti.client.view;

import java.util.List;
import java.util.TreeMap;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;


public class HaitiMapsGalleryPanel {

	private ContentPanel panel;
	
	private VerticalPanel vpanel;
	
	private HorizontalPanel hpanel;
	
	private ComboBox<DimensionItemModel> categoriesList;
	
	private ListStore<DimensionItemModel> categoriesStore;
	
	private ComboBox<DimensionItemModel> mapsList;
	
	private ListStore<DimensionItemModel> mapsStore;
	
	private ListBox mapsListBox;
	
	private Html image;
	
	private Button export;
	
	private final String PANEL_HEIGHT = "675px";
	
	private final String PANEL_WIDTH = "1100px";
	
	private final Integer IMAGE_PANEL_HEIGHT = 566;
	
	private final Integer IMAGE_PANEL_WIDTH = 800;	
		
	private HTML title;
	
	private TreeMap<String, List<CodeVo>> categories;
	
	
	public HaitiMapsGalleryPanel() {
		panel = new ContentPanel();
		panel.setHeight(PANEL_HEIGHT);
		panel.setWidth(PANEL_WIDTH);
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
		hpanel = new HorizontalPanel();
		hpanel.setSpacing(10);
		vpanel = new VerticalPanel();
		vpanel.setSpacing(10);
		categoriesList = new ComboBox<DimensionItemModel>();
		categoriesList.setTriggerAction(TriggerAction.ALL);
		categoriesStore = new ListStore<DimensionItemModel>();
		categoriesList.setDisplayField("name");
		categoriesList.setWidth("200px");
		categoriesList.setStore(categoriesStore);
		image = new Html();
		title = new HTML();
		categories = new TreeMap<String, List<CodeVo>>();
		mapsListBox = new ListBox();
		mapsListBox.setHeight("150px");
		mapsListBox.setWidth("200px");
		mapsListBox.setVisibleItemCount(100);
		mapsList = new ComboBox<DimensionItemModel>();
		mapsList.setTriggerAction(TriggerAction.ALL);
		mapsStore = new ListStore<DimensionItemModel>();
		mapsList.setDisplayField("name");
		mapsList.setWidth("200px");
		mapsList.setStore(mapsStore);
	}
	
	
	public ContentPanel build() {
		HTML space = new HTML("");
		space.setHeight("20px");	
		panel.add(space);
		title = new HTML("<div style='font-weight: bold; font-size: 13pt; text-align: center;'>" + HaitiLangEntry.getInstance("en").mapsGallery() + ":</div>");
		panel.add(title);
//		vpanel.add(buildListBox("en"));
//		vpanel.add(buildImagePanel("en"));
		panel.add(vpanel);
		return panel;
	}
//	
	public ContentPanel build(String gaulCode) {
		HTML space = new HTML("");
		space.setHeight("20px");	
		panel.add(space);
		title = new HTML("<div style='font-weight: bold; font-size: 13pt; text-align: center;'>" + HaitiLangEntry.getInstance("en").mapsGallery() + ":</div>");
		panel.add(title);
		vpanel.add(buildCategoryListBox("en"));
		vpanel.add(buildMapsListBox(gaulCode, "en", IMAGE_PANEL_WIDTH, IMAGE_PANEL_HEIGHT));
//		vpanel.add(buildImagePanel(gaulCode, "en"));
		hpanel.add(vpanel);
		hpanel.add(buildImagePanel(gaulCode, "en",  IMAGE_PANEL_WIDTH, IMAGE_PANEL_HEIGHT));
		panel.add(hpanel);
		setCategories(gaulCode, "EN");
		return panel;
	}

	public ContentPanel build(String gaulCode, String width, String height, String language) {
		HTML space = new HTML("");
		space.setHeight("20px");	
		panel.add(space);
		title = new HTML("<div style='font-weight: bold; font-size: 13pt; text-align: center;'>" + HaitiLangEntry.getInstance(language).mapsGallery() + "</div>");
		panel.add(title);
		panel.setHeight(height);
		panel.setWidth(width);
		vpanel.add(buildCategoryListBox(language));
		vpanel.add(buildMapsListBox(gaulCode, language, IMAGE_PANEL_WIDTH, IMAGE_PANEL_HEIGHT));
//		vpanel.add(buildImagePanel(gaulCode, language));
		hpanel.add(vpanel);
		hpanel.add(buildImagePanel(gaulCode, language, IMAGE_PANEL_WIDTH, IMAGE_PANEL_HEIGHT));
		panel.add(hpanel);
		setCategories(gaulCode, language);
		return panel;
	}
	
	private VerticalPanel buildCategoryListBox(String language) { 
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		HTML label = new HTML("<div style='font-weight: bold; font-size: 10pt;'>" + HaitiLangEntry.getInstance(language).selectACategory() + ":</div>");
		HTML space = new HTML("");
		label.setWidth("150px");
		space.setWidth("20px");
		panel.add(label);		
		panel.add(space);
		panel.add(categoriesList);
		categoriesList.addSelectionChangedListener(HaitiController.setMapsListBox(this));
		return panel;
	}
	
	private VerticalPanel buildMapsListBox(String gaulCode, String language, Integer width, Integer height) {
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		HTML label = new HTML("<div style='font-weight: bold; font-size: 10pt;'>" + HaitiLangEntry.getInstance(language).selectAMap() + ":</div>");
		HTML space = new HTML("");
		label.setWidth("150px");
		space.setWidth("20px");
		panel.add(label);		
		panel.add(space);
		panel.add(mapsList);
		mapsList.addSelectionChangedListener(HaitiController.buildMapPreview(this, gaulCode, language, width, height));
//		mapsList
//		panel.add(mapsListBox);
//		mapsListBox.addChangeListener(HaitiController.buildMapPreview(this, gaulCode, language, width, height));
		return panel;
	}
	
	private VerticalPanel buildImagePanel(String gaulCode, String language, Integer width, Integer height) { 
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		HTML label = new HTML("<div style='font-weight: bold; font-size: 10pt;'>" + HaitiLangEntry.getInstance(language).preview() + ":</div>");
		HTML space = new HTML("");
		label.setWidth("150px");
		space.setWidth("20px");	
		panel.add(space);
		panel.add(space);
		panel.add(label);
		panel.add(space);
		export = new Button(HaitiLangEntry.getInstance(language).exportPDFMap());
		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		contentPanel.setWidth(width);
		contentPanel.setHeight(height);
		contentPanel.add(image);
		panel.add(contentPanel);
		panel.add(export);
		export.addSelectionListener(HaitiController.exportMapsPDF(this, gaulCode, language));
		return panel;
	}

	private void setCategories(String gaulCode, String language) { 
		System.out.println("setCategories");
		HaitiController.getMapsGalleryTree(this, gaulCode, language);
	}

//	private void buildPreview(String gaulCode, String language) {
//		VerticalPanel panel = new VerticalPanel();
//		panel.add()
//	}

	public Html getImage() {
		return image;
	}


	public void setImage(Html image) {
		this.image = image;
	}


	public ContentPanel getPanel() {
		return panel;
	}


	

	public TreeMap<String, List<CodeVo>> getCategories() {
		return categories;
	}


	public void setCategories(TreeMap<String, List<CodeVo>> categories) {
		this.categories = categories;
	}


	public ComboBox<DimensionItemModel> getCategoriesList() {
		return categoriesList;
	}


	public void setCategoriesList(ComboBox<DimensionItemModel> categoriesList) {
		this.categoriesList = categoriesList;
	}


	public ListStore<DimensionItemModel> getCategoriesStore() {
		return categoriesStore;
	}


	public void setCategoriesStore(ListStore<DimensionItemModel> categoriesStore) {
		this.categoriesStore = categoriesStore;
	}


	public ListBox getMapsListBox() {
		return mapsListBox;
	}


	public void setMapsListBox(ListBox mapsListBox) {
		this.mapsListBox = mapsListBox;
	}


	public Button getExport() {
		return export;
	}


	public void setExport(Button export) {
		this.export = export;
	}


	public ComboBox<DimensionItemModel> getMapsList() {
		return mapsList;
	}


	public void setMapsList(ComboBox<DimensionItemModel> mapsList) {
		this.mapsList = mapsList;
	}


	public ListStore<DimensionItemModel> getMapsStore() {
		return mapsStore;
	}


	public void setMapsStore(ListStore<DimensionItemModel> mapsStore) {
		this.mapsStore = mapsStore;
	}




	
}
