package org.fao.fenix.web.modules.fieldclimate.client.view;

import java.util.Date;

import org.fao.fenix.web.modules.fieldclimate.client.control.FieldClimateController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public class FieldClimatePanel {

	private ContentPanel fieldClimatePanel;
	
	private VerticalPanel wrapper;
	
	private Slider slider;
	
	private Button importButton;
	
	private Button cancelButton;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private static final int SPACING = 5;
	
	private static final String FIELD_WIDTH = "250px";
	
	public FieldClimatePanel() {
		fieldClimatePanel = new ContentPanel(new FillLayout());
		fieldClimatePanel.setHeaderVisible(false);
		fieldClimatePanel.setBodyBorder(false);
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth(FIELD_WIDTH);
		gaulList.setEmptyText("Please select a station from the list");
		FieldClimateController.fillFieldClimateStationList(gaulStore);
		slider = new Slider();
	}
	
	public ContentPanel build() {
		fieldClimatePanel.add(buildWrapper());
		return fieldClimatePanel;
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
		Html label = new Html("<b>Please select a station from the list:</b>");
		label.setWidth(FIELD_WIDTH);
		countryPanel.add(label);
		countryPanel.add(gaulList);
		return countryPanel;
	}
	
	private VerticalPanel buildTimePanel() {
		VerticalPanel timePanel = new VerticalPanel();
		timePanel.setSpacing(SPACING);
		Date today = new Date();
		slider.setWidth(FIELD_WIDTH);  
		slider.setIncrement(1);  
		slider.setMinValue(1);
		slider.setMaxValue(calculateSliderSize());  
		slider.setClickToChange(true);
		slider.setValue(1);
		Html label = new Html("<b>Import data from </b>" + FieldClimateController.date2string(today));
		label.setWidth(FIELD_WIDTH);
		Html subLabel = new Html("<i>move the slider to change the time interval</i>");
		subLabel.setWidth(FIELD_WIDTH);
		timePanel.add(label);
		timePanel.add(subLabel);
		timePanel.add(slider);
		slider.addListener(Events.Change, FieldClimateController.timeSliderListener(label, slider, this));
		return timePanel;
	}
	
	private int calculateSliderSize() {
		int size = 1;
		Date start = new Date(105, 0, 1);
		Date end = new Date();
		size = (int)((end.getTime() - start.getTime()) / 86400000);
		return size;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(SPACING);
		importButton = new Button("Import Data");
		cancelButton = new Button("Close Window");
		buttonsPanel.add(importButton);
		buttonsPanel.add(cancelButton);
		return buttonsPanel;
	}

	public Slider getSlider() {
		return slider;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public Button getImportButton() {
		return importButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}
	
}