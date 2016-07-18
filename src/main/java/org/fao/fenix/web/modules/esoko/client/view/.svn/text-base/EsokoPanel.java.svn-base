package org.fao.fenix.web.modules.esoko.client.view;

import java.util.Date;

import org.fao.fenix.web.modules.esoko.client.control.EsokoController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

@SuppressWarnings("deprecation")
public class EsokoPanel {

	private ContentPanel esokoPanel;
	
	private VerticalPanel wrapper;
	
	private Slider slider;
	
	private DateField fromDate;
	
	private DateField toDate;
	
	private Button importButton;
	
	private Button cancelButton;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private int minYear;
	
	private int maxYear;
	
	private static final int SPACING = 5;
	
	private static final String FIELD_WIDTH = "365px";
	
	private static final String BUTTON_WIDTH = "180px";
	
	public EsokoPanel() {
		esokoPanel = new ContentPanel(new FillLayout());
		esokoPanel.setHeaderVisible(false);
		esokoPanel.setBodyBorder(false);
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth(FIELD_WIDTH);
		gaulList.setEmptyText(BabelFish.print().pleaseSelectACountryFromTheList());
		EsokoController.fillEsokoCountryList(gaulStore);
		slider = new Slider();
		fromDate = new DateField();
		toDate = new DateField();
	}
	
	public ContentPanel build() {
		esokoPanel.add(buildWrapper());
		return esokoPanel;
	}
	
	private VerticalPanel buildWrapper() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(SPACING);
		wrapper.add(buildCountryPanel());
		wrapper.add(buildTimePanel());
		wrapper.add(buildButtonsPanel());
		return wrapper;
	}
	
	private VerticalPanel buildCountryPanel() {
		VerticalPanel countryPanel = new VerticalPanel();
		countryPanel.setSpacing(SPACING);
		Html label = new Html("<b>" + BabelFish.print().pleaseSelectACountryFromTheList() + "</b>");
		label.setWidth(FIELD_WIDTH);
		countryPanel.add(label);
		countryPanel.add(gaulList);
		return countryPanel;
	}
	
	private HorizontalPanel buildTimePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fromLabel = new Html("<b>From:</b>");
		Html toLabel = new Html("<b>To:</b>");
		fromDate.setValue(new Date(104, 0, 1));
		toDate.setValue(new Date());
		p.add(fromLabel);
		p.add(fromDate);
		p.add(toLabel);
		p.add(toDate);
		return p;
	}
	
//	private VerticalPanel buildTimePanel() {
//		VerticalPanel timePanel = new VerticalPanel();
//		timePanel.setSpacing(SPACING);
//		Date today = new Date();
//		minYear = 1900 + today.getYear();
//		maxYear = 1900 + today.getYear();
//		slider.setWidth(FIELD_WIDTH);  
//		slider.setIncrement(1);  
//		slider.setMinValue(2004);
//		slider.setMaxValue(maxYear);  
//		slider.setClickToChange(true);
//		slider.setValue(2010);
//		Html label = new Html("<b>" + BabelFish.print().importDataFrom() + " " + minYear + " " + BabelFish.print().to() + " " + maxYear + "</b>");
//		label.setWidth(FIELD_WIDTH);
//		Html subLabel = new Html("<i>" + BabelFish.print().moveTheSlider() + "</i>");
//		subLabel.setWidth(FIELD_WIDTH);
//		timePanel.add(label);
//		timePanel.add(subLabel);
//		timePanel.add(slider);
//		slider.addListener(Events.Change, EsokoController.timeSliderListener(label, slider, this));
//		return timePanel;
//	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(SPACING);
		importButton = new Button(BabelFish.print().importPrices());
		importButton.setWidth(BUTTON_WIDTH);
		cancelButton = new Button(BabelFish.print().closeWindow());
		cancelButton.setWidth(BUTTON_WIDTH);
		buttonsPanel.add(importButton);
		buttonsPanel.add(cancelButton);
		return buttonsPanel;
	}
	
	public ContentPanel getEsokoPanel() {
		return esokoPanel;
	}

	public Slider getSlider() {
		return slider;
	}

	public Button getImportButton() {
		return importButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public ListStore<GaulModelData> getGaulStore() {
		return gaulStore;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public DateField getFromDate() {
		return fromDate;
	}

	public DateField getToDate() {
		return toDate;
	}
	
}