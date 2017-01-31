/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.metadataeditor.client.view;

import org.fao.fenix.web.modules.fpi.common.vo.PeriodTypeCodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.PeriodTypeCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;

public class KeysPanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<GaulModelData> providerGaulStore;
	
	private ComboBox<GaulModelData> providerGaulList;
	
	private TextField<String> source;
	
	private TextField<String> sourceContact;
	
	private TextField<String> provider;
	
	private TextField<String> providerContact;
	
	private ComboBox<PeriodTypeCodeModelData> periodList;
	
	private ListStore<PeriodTypeCodeModelData> periodStore; 
	
	private static final String RED = "#CA1616";
	
	public KeysPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight("600px");
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
	}
	
	public ContentPanel build(boolean isDataset, boolean isEditable) {
		wrapper.add(buildStepTwoPanel(isDataset));
		if (isDataset) 
			wrapper.add(buildPeriodTypeCodePanel(isEditable));
		wrapper.add(buildSourcePanel(isEditable));
		wrapper.add(buildProviderPanel(isEditable));
		panel.add(wrapper);
		return panel;
	}
	
	private HorizontalPanel buildStepTwoPanel(boolean isDataset) {
		HorizontalPanel stepTwoPanel = new HorizontalPanel();
		stepTwoPanel.setSpacing(10);
		stepTwoPanel.setBorders(true);
		stepTwoPanel.setWidth("750px");
		HTML stepTwo = new HTML("<font color='#15428B'>" + BabelFish.print().stepTwo() + "</font>");
		if (!isDataset)
			stepTwo.setHTML("<font color='#15428B'>" + BabelFish.print().tooltipsource() + "</font>");
		stepTwoPanel.add(stepTwo);
		return stepTwoPanel;
	}
	
	public Boolean validate() { 
		if (gaulList.getSelection().size() < 1) 
			return false;
		if (source.getValue().equals(""))  
			return false;
		if ((periodList != null) && (periodList.getSelection().size() < 1)) 
			return false;
		return true;
	}
	
	public VerticalPanel buildSourcePanel(boolean isEditable) {
		
		VerticalPanel tmp = new VerticalPanel();
		
		HorizontalPanel sourcePanel = new HorizontalPanel();
		sourcePanel.setSpacing(10);
//		sourcePanel.setBorders(true);
//		sourcePanel.setWidth("750px");
		
		HTML sourceLabel = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().title() + ": </font></b>");
		sourceLabel.setWidth("50px");
		source = new TextField<String>();
		source.setAllowBlank(false);
		source.setWidth("135px");
		source.setReadOnly(!isEditable);
		
		HTML regionLabel = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().region() + ": </font></b>");
		regionLabel.setWidth("50px");
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setReadOnly(!isEditable);
		
		HTML sourceContactLabel = new HTML("<b>" + BabelFish.print().contact() + ": </b>");
		sourceContactLabel.setWidth("100px");
		sourceContact = new TextField<String>();
		sourceContact.setWidth("150px");
		sourceContact.setReadOnly(!isEditable);
		
		sourcePanel.add(sourceLabel);
		sourcePanel.add(source);
		sourcePanel.add(regionLabel);
		sourcePanel.add(gaulList);
		sourcePanel.add(sourceContactLabel);
		sourcePanel.add(sourceContact);
		
		tmp.setSpacing(10);
		tmp.setBorders(true);
		tmp.setWidth("750px");
		tmp.add(new HTML("<b>" + BabelFish.print().source() + "</b>"));
		tmp.add(sourcePanel);
		
		return tmp;
	}
	
	public VerticalPanel buildProviderPanel(boolean isEditable) {
		
		VerticalPanel tmp = new VerticalPanel();
		
		HorizontalPanel providerPanel = new HorizontalPanel();
		providerPanel.setSpacing(10);
//		providerPanel.setBorders(true);
//		providerPanel.setWidth("750px");
		
		HTML providerLabel = new HTML("<b>" + BabelFish.print().title() + ": </b>");
		providerLabel.setWidth("50px");
		provider = new TextField<String>();
		provider.setWidth("135px");
		provider.setReadOnly(!isEditable);
		
		HTML regionLabel = new HTML("<b>" + BabelFish.print().region() + ": </b>");
		regionLabel.setWidth("50px");
		providerGaulStore = new ListStore<GaulModelData>(); 
		providerGaulList = new ComboBox<GaulModelData>();
		providerGaulList.setTriggerAction(TriggerAction.ALL);
		providerGaulList.setStore(providerGaulStore);
		providerGaulList.setDisplayField("gaulLabel");
		providerGaulList.setReadOnly(!isEditable);
		
		HTML providerContactLabel = new HTML("<b>" + BabelFish.print().contact() + ": </b>");
		providerContactLabel.setWidth("100px");
		providerContact = new TextField<String>();
		providerContact.setWidth("150px");
		providerContact.setReadOnly(!isEditable);
		
		providerPanel.add(providerLabel);
		providerPanel.add(provider);
		providerPanel.add(regionLabel);
		providerPanel.add(providerGaulList);
		providerPanel.add(providerContactLabel);
		providerPanel.add(providerContact);
		
		tmp.setSpacing(10);
		tmp.setBorders(true);
		tmp.setWidth("750px");
		tmp.add(new HTML("<b>" + BabelFish.print().provider() + "</b>"));
		tmp.add(providerPanel);
		
		return tmp;
	}
	
	public HorizontalPanel buildPeriodTypeCodePanel(boolean isEditable) {
		HorizontalPanel periodTypeCodePanel = new HorizontalPanel();
		periodTypeCodePanel.setSpacing(10);
		periodTypeCodePanel.setBorders(true);
		periodTypeCodePanel.setWidth("750px");
		HTML label = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().periodicity() + ": </font></b>");
		periodList = new ComboBox<PeriodTypeCodeModelData>();
		periodList.setTriggerAction(TriggerAction.ALL);
		periodStore = createPeriodTypeStore();
		periodList.setStore(periodStore);
		periodList.setAllowBlank(false);
		periodList.setDisplayField("periodTypeCode");
		periodList.setReadOnly(!isEditable);
		periodTypeCodePanel.add(label);
		periodTypeCodePanel.add(periodList);
		label.setWidth("75px");
		periodList.setWidth("200px");
		return periodTypeCodePanel;
	}
	
	private ListStore<PeriodTypeCodeModelData> createPeriodTypeStore() {
		ListStore<PeriodTypeCodeModelData> periodStore = new ListStore<PeriodTypeCodeModelData>();
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.daily.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.weekly.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.decade.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.monthly.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.yearly.name()));
		return periodStore;
	}

	public ListStore<GaulModelData> getGaulStore() {
		return gaulStore;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public TextField<String> getSource() {
		return source;
	}

	public ComboBox<PeriodTypeCodeModelData> getPeriodList() {
		return periodList;
	}

	public ListStore<PeriodTypeCodeModelData> getPeriodStore() {
		return periodStore;
	}

	public TextField<String> getSourceContact() {
		return sourceContact;
	}

	public TextField<String> getProvider() {
		return provider;
	}

	public TextField<String> getProviderContact() {
		return providerContact;
	}

	public ComboBox<GaulModelData> getProviderGaulList() {
		return providerGaulList;
	}

	public ListStore<GaulModelData> getProviderGaulStore() {
		return providerGaulStore;
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
}
