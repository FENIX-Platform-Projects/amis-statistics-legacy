package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;


public class HaitiCropCalendarPanel {

	private ContentPanel panel;
	
	private VerticalPanel vpanel;
	
	private ComboBox<DimensionItemModel> provincesList;
	
	private ListStore<DimensionItemModel> provincesStore;
	
	private Html image;
	
	private Button exportImage;
	
	private final String PANEL_HEIGHT = "675px";
	
	private final String PANEL_WIDTH = "1325px";
	
	private HTML title;
	
	
	public HaitiCropCalendarPanel() {
		panel = new ContentPanel();
		panel.setHeight(PANEL_HEIGHT);
		panel.setWidth(PANEL_WIDTH);
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
		vpanel = new VerticalPanel();
		vpanel.setSpacing(10);
		provincesList = new ComboBox<DimensionItemModel>();
		provincesList.setTriggerAction(TriggerAction.ALL);
		provincesStore = new ListStore<DimensionItemModel>();
		provincesList.setDisplayField("name");
		provincesList.setWidth("250px");
		provincesList.setStore(provincesStore);
		image = new Html();
		title = new HTML();
	}
	
	
	public ContentPanel build() {
		HTML space = new HTML("");
		space.setHeight("20px");	
		panel.add(space);
		title = new HTML("<div style='font-weight: bold; font-size: 13pt; text-align: center;'>" + HaitiLangEntry.getInstance("en").cropCalendar() + ":</div>");
		panel.add(title);
		vpanel.add(buildListBox("en"));
		vpanel.add(buildImagePanel("en"));
		panel.add(vpanel);
		return panel;
	}
	
	public ContentPanel build(String gaulCode) {
		HTML space = new HTML("");
		space.setHeight("20px");	
		panel.add(space);
		title = new HTML("<div style='font-weight: bold; font-size: 13pt; text-align: center;'>" + HaitiLangEntry.getInstance("en").cropCalendar() + ":</div>");
		panel.add(title);
		vpanel.add(buildListBox("en"));
		vpanel.add(buildImagePanel("en"));
		panel.add(vpanel);
		setProvinces(gaulCode, "EN");;
		return panel;
	}
	
	public ContentPanel rebuild(String gaulCode, String language) {
		if ( language == null)
			language = "en";
		cleanInterface();
		setProvinces(gaulCode, language);
		return panel;
	}
	
	
	public ContentPanel build(String gaulCode, String width, String height, String language) {
		HTML space = new HTML("");
		space.setHeight("20px");	
		panel.add(space);
		title = new HTML("<div style='font-weight: bold; font-size: 13pt; text-align: center;'>" + HaitiLangEntry.getInstance(language).cropCalendar() + "</div>");
		panel.add(title);
		panel.setHeight(height);
		panel.setWidth(width);
		vpanel.add(buildListBox(language));
		vpanel.add(buildImagePanel(language));
		panel.add(vpanel);
		setProvinces(gaulCode, language);
		return panel;
	}
	
	private VerticalPanel buildListBox(String language) { 
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		HTML label = new HTML("<div style='font-weight: bold; font-size: 10pt;'>" + HaitiLangEntry.getInstance(language).selectAprovince() + ":</div>");
		HTML space = new HTML("");
		space.setWidth("20px");
		panel.add(label);		
		panel.add(space);
		panel.add(provincesList);
		if ( language == null)
			language = "EN";
		provincesList.addSelectionChangedListener(HaitiController.getCropCalendar(this, language));
		return panel;
	}
	
	private VerticalPanel buildImagePanel(String language) { 
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		HTML space = new HTML("");
		space.setWidth("20px");	
		panel.add(space);
		panel.add(image);
		panel.add(space);
		exportImage = new Button(HaitiLangEntry.getInstance(language).exportImage());
		exportImage.setVisible(false);
		panel.add(exportImage);
		if ( language == null)
			language = "EN";
		exportImage.addSelectionListener(HaitiController.exportCalendarImage(this, language));
		return panel;
	}

	
	public void setProvinces(String gaulCode, String language) { 
		/** TODO: retrieving hardcoded english gaul codes **/
		HaitiController.setCropCalendarProvince(gaulCode, "EN", provincesList, provincesStore);
	}

	
	private void cleanInterface(){
		image.setHtml("");
		exportImage.setVisible(false);
	}
	


	public Html getImage() {
		return image;
	}


	public void setImage(Html image) {
		this.image = image;
	}


	public ContentPanel getPanel() {
		return panel;
	}


	public Button getExportImage() {
		return exportImage;
	}


	public ComboBox<DimensionItemModel> getProvincesList() {
		return provincesList;
	}


	public ListStore<DimensionItemModel> getProvincesStore() {
		return provincesStore;
	}

	
}
