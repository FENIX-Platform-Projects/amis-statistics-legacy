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
package org.fao.fenix.web.modules.sldeditor.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;
import org.fao.fenix.web.modules.sldeditor.common.vo.ColorMapBigGrid;
import org.fao.fenix.web.modules.sldeditor.common.vo.ColorMapSmallGrid;
import org.fao.fenix.web.modules.sldeditor.common.vo.RGBContrast;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SLDEditorRuleForRaster extends SLDEditorRule {

	//Raster Rule
	private CheckBox formatRasterRule;

	//Opacity
	private CheckBox sliderOpacityFormat;
	
	private Slider fillOpacityOnly;
	
	//Color Map
	private CheckBox formatColorMap;
	
	private ListBox colorMapTypeListBox;
	
	private ListBox colorMapExtendedListBox;

	private ListBox opacityForEachIntervalListBox;
	
	private static List<ColorMapSmallGrid> rules;
	
	private static List<ColorMapBigGrid> bigRrules;
	
	//Selection Channel
		
	private CheckBox selectionChannelFormat;
	
	private ListBox selectionChannelTypeListBox;
	
	private TextField<String> redChannel;
	
	private TextField<String> greenChannel;
	
	private TextField<String> blueChannel;
	
	private TextField<String> grayChannel;
	
	//Contrast Enhancement 
	
	private CheckBox contrastEnhancementFormat;
	
	private ListBox contrastEnhancementTypeListBox;
	
	private ListBox globalContrastListBox;
	
	private TextField<String> gammaValue;
	
	private static List<RGBContrast> redGreenBluerules;
	
	
	public SLDEditorRuleForRaster(SLDEditorRaster sldEditorRaster) {
		sliderOpacityFormat = new CheckBox();
		fillOpacityOnly = new Slider();
		
		formatColorMap = new CheckBox();
		colorMapTypeListBox = new ListBox();
		colorMapExtendedListBox = new ListBox();
		opacityForEachIntervalListBox = new ListBox();
				
		selectionChannelFormat = new CheckBox();
		selectionChannelTypeListBox = new ListBox();
				
		contrastEnhancementFormat = new CheckBox();
		contrastEnhancementTypeListBox = new ListBox();
		
		formatRasterRule = new CheckBox();
		
		rules = new ArrayList<ColorMapSmallGrid>();
		bigRrules = new ArrayList<ColorMapBigGrid>();
		redGreenBluerules = new ArrayList<RGBContrast>();
		
		this.getActionPanel().add(buildFormatRasterRulePanel(sldEditorRaster));
	}

	public HorizontalPanel buildFormatRasterRulePanel(SLDEditorRaster sldEditorRaster) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		formatRasterRule.setBoxLabel("Raster Rule");
		formatRasterRule.addListener(Events.OnClick, SLDEditorController.rasterRuleFormat(this, sldEditorRaster));
		p.add(formatRasterRule);
		removableCheckbox.add(p);
		return p;
	}

	public HorizontalPanel buildFormatContrastEnhancementPanel(SLDEditorRaster sldEditorRaster) {
		return buildFormatContrastEnhancementPanel(sldEditorRaster, null);
	}
	
	public HorizontalPanel buildFormatContrastEnhancementPanel(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		contrastEnhancementFormat = new CheckBox();
		contrastEnhancementFormat.setBoxLabel("Contrast Enhancement");
		contrastEnhancementFormat.addListener(Events.OnClick, SLDEditorController.contrastEnhancementFormat(this, sldEditorRaster));
		if(data != null)
		{
			contrastEnhancementFormat.setValue(true);
		}
		p.add(contrastEnhancementFormat);
		removableFormatRasterPanels.add(p);
		return p;
	}


	public HorizontalPanel buildFormatOpacityPanel(SLDEditorRaster sldEditorRaster) {
		return buildFormatOpacityPanel(sldEditorRaster, null);
	}
	
	public HorizontalPanel buildFormatOpacityPanel(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		sliderOpacityFormat = new CheckBox();
		sliderOpacityFormat.setBoxLabel("Slider Opacity");
		sliderOpacityFormat.addListener(Events.OnClick, SLDEditorController.opacitySliderFormat(this, sldEditorRaster));
		if(data != null)
		{
			sliderOpacityFormat.setValue(true);
		}
		p.add(sliderOpacityFormat);
		removableFormatRasterPanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFormatOpacityPanel2(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		return this.buildSliderOpacityPanelS(data);
	}
	
	public HorizontalPanel buildFormatColorMapPanel(SLDEditorRaster sldEditorRaster) {
		return buildFormatColorMapPanel(sldEditorRaster, null);
	}
	
	public HorizontalPanel buildFormatColorMapPanel(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		formatColorMap = new CheckBox();
		formatColorMap.setBoxLabel("Color Map");
		formatColorMap.addListener(Events.OnClick, SLDEditorController.formatColorMap(this, sldEditorRaster));
		if(data != null)
		{
			formatColorMap.setValue(true);
		}
		p.add(formatColorMap);
		removableFormatRasterPanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFormatColorMapPanel2(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		return this.buildColorMapTypePanel(data);
	}
	
	public HorizontalPanel buildFormatColorMapPanel3(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		return this.buildColorMapBitColPanel(data);
	}
	
	public HorizontalPanel buildFormatColorMapPanel4(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		return this.buildOpacityForEachIntervalPanel(data);
	}

	public HorizontalPanel buildFormatChannelSelectionPanel(SLDEditorRaster sldEditorRaster) {
		return buildFormatChannelSelectionPanel(sldEditorRaster, null);
	}

	public HorizontalPanel buildFormatChannelSelectionPanel(SLDEditorRaster sldEditorRaster, SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		selectionChannelFormat = new CheckBox();
		selectionChannelFormat.setBoxLabel("Channel Selection");
		selectionChannelFormat.addListener(Events.OnClick, SLDEditorController.formatChannelSelection(this, sldEditorRaster));
		if(data != null)
		{
			selectionChannelFormat.setValue(true);
		}
		p.add(selectionChannelFormat);
		removableFormatRasterPanels.add(p);
		return p;
	}

	public HorizontalPanel buildColorMapTypePanel() {
		return buildColorMapTypePanel(null);
	}
	
	public HorizontalPanel buildColorMapTypePanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html colorMapTypeLabel = new Html("<b>Type Attribute</b>");
		colorMapTypeLabel.setWidth(FIELD_WIDTH);
		colorMapTypeLabel.setHeight("25px");
		p.add(colorMapTypeLabel);
		colorMapTypeListBox.clear();
		colorMapTypeListBox.addItem("Ramp", "Ramp");
		colorMapTypeListBox.addItem("Interval", "Interval");
		colorMapTypeListBox.addItem("Value", "Value");
		colorMapTypeListBox.setWidth(LONG_FIELD_WIDTH);
		if(data != null)
		{
			int index = -1;
			ListBox lb = colorMapTypeListBox;
			
			String app = data.getColorMapItem().get("type-attribute");
			lb.setSelectedIndex(0);
			if((app ==null)||(app.length()==0))
			{				
			}
			else{
				for(int i=0;i<lb.getItemCount();i++){
					if(lb.getValue(i).equalsIgnoreCase(app)){
						index = i;
						break;
					}
				}
				if(index != -1)
				{
					lb.setSelectedIndex(index);
				}
				else
				{
					//System.out.println("Type Lb index final -1  = "+index);
				}
			}
		}
		p.add(colorMapTypeListBox);
		removableColorMapPanels.add(p);
		return p;
	}

	public HorizontalPanel buildColorMapBitColPanel() {
		return buildColorMapBitColPanel(null);
	}
	
	public HorizontalPanel buildColorMapBitColPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html colorMapExtendedLabel = new Html("<b>Extended Attribute</b>");
		colorMapExtendedLabel.setWidth(FIELD_WIDTH);
		colorMapExtendedLabel.setHeight("25px");
		p.add(colorMapExtendedLabel);
		colorMapExtendedListBox.clear();
		colorMapExtendedListBox.addItem("Normal Range Color", "Normal Range Color");
		colorMapExtendedListBox.addItem("Extended Range Color", "Extended Range Color");
		colorMapExtendedListBox.setWidth(LONG_FIELD_WIDTH);
		if(data != null)
		{
			int index = -1;
			ListBox lb = colorMapExtendedListBox;
			String app = data.getColorMapItem().get("extended-attribute");
			lb.setSelectedIndex(0);
			if((app ==null)||(app.length()==0))
			{				
			}
			else{
				for(int i=0;i<lb.getItemCount();i++){
					if(lb.getValue(i).equalsIgnoreCase(app)){
						index = i;
						break;
					}
				}
				if(index != -1)
				{
					lb.setSelectedIndex(index);
				}
				else
				{
					//System.out.println("Type Color Lb index final -1  = "+index);
				}
			}
		}
		p.add(colorMapExtendedListBox);
		removableColorMapPanels.add(p);
		return p;
	}

	public HorizontalPanel buildOpacityForEachIntervalPanel() {
		return buildOpacityForEachIntervalPanel(null);
	}
	public HorizontalPanel buildOpacityForEachIntervalPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html opacityForEachIntervalLabel = new Html("<b>Color Map Items</b>");
		opacityForEachIntervalLabel.setWidth(FIELD_WIDTH);
		opacityForEachIntervalLabel.setHeight("25px");
		p.add(opacityForEachIntervalLabel);
		opacityForEachIntervalListBox.clear();
		opacityForEachIntervalListBox.addItem("", "");
		opacityForEachIntervalListBox.addItem("Color Entry with Global Opacity", "Color Entry with Global Opacity");
		opacityForEachIntervalListBox.addItem("Color Entry with Opacity For Each Interval", "Color Entry with Opacity For Each Interval");
		opacityForEachIntervalListBox.setWidth(LONG_FIELD_WIDTH);
		opacityForEachIntervalListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				int index = opacityForEachIntervalListBox.getSelectedIndex();
				 String selectedItem = (String) opacityForEachIntervalListBox.getItemText(index);
				 if(selectedItem.equalsIgnoreCase("Color Entry with Opacity For Each Interval"))
				 {
					buildGlobalOpacityPanel();
				 }
				 else if(selectedItem.equalsIgnoreCase("Color Entry with Global Opacity"))
				 {
					 buildIntervalOpacityPanel();
				 }				
			}
		});
		
		if(data != null)
		{
			if(data.isColorMapSlider())
			{
				opacityForEachIntervalListBox.setSelectedIndex(1);
			}
			else
			{
				opacityForEachIntervalListBox.setSelectedIndex(2);
			}
		}
		p.add(opacityForEachIntervalListBox);
		removableColorMapPanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildSelectionChannelTypePanel() {
		return buildSelectionChannelTypePanel(null);
	}
	
	public HorizontalPanel buildSelectionChannelTypePanel(SLDEditorRuleVO data) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html opacityForEachIntervalLabel = new Html("<b>Selection Channel Type</b>");
		opacityForEachIntervalLabel.setWidth(FIELD_WIDTH);
		opacityForEachIntervalLabel.setHeight("25px");
		p.add(opacityForEachIntervalLabel);
		selectionChannelTypeListBox.clear();
		selectionChannelTypeListBox.addItem("", "");
		selectionChannelTypeListBox.addItem("Red/Green/Blue Channels", "Red/Green/Blue Channels");
		selectionChannelTypeListBox.addItem("Gray Channel", "Gray Channel");
		selectionChannelTypeListBox.setWidth(LONG_FIELD_WIDTH);
		selectionChannelTypeListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				int index = selectionChannelTypeListBox.getSelectedIndex();
				 String selectedItem = (String) selectionChannelTypeListBox.getItemText(index);
				 if(selectedItem.equalsIgnoreCase("Red/Green/Blue Channels"))
				 {
					buildRedGreenBlueSelectionsPanel();
					 
				 }
				 else if(selectedItem.equalsIgnoreCase("Gray Channel"))
				 {
					 buildGraySelectionPanel();
				 }
			}

		});
		
		if(data != null)
		{
			if((data.getChannelSelectionMap().containsKey("red-channel-name"))||(data.getChannelSelectionMap().containsKey("green-channel-name"))||(data.getChannelSelectionMap().containsKey("blue-channel-name")))
			{
				selectionChannelTypeListBox.setSelectedIndex(1);
			}
			else if(data.getChannelSelectionMap().containsKey("gray-channel-name"))
			{
				selectionChannelTypeListBox.setSelectedIndex(2);
			}
		}
		
		p.add(selectionChannelTypeListBox);
		p.setId("selectionChannelListBox");
		removableChannelSelectionPanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildContrastEnhancementPanel() {
		return buildContrastEnhancementPanel(null);
	}
	
	public HorizontalPanel buildContrastEnhancementPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html contrastEnhancementLabel = new Html("<b>Contrast Type</b>");
		contrastEnhancementLabel.setWidth(FIELD_WIDTH);
		contrastEnhancementLabel.setHeight("25px");
		p.add(contrastEnhancementLabel);
		contrastEnhancementTypeListBox.clear();
		contrastEnhancementTypeListBox.addItem("", "");
		contrastEnhancementTypeListBox.addItem("Global Contrast", "Global Contrast");
		contrastEnhancementTypeListBox.addItem("Red/Green/Blue Channel Contrast", "Red/Green/Blue Channel Contrast");
		contrastEnhancementTypeListBox.addItem("Gray Channel Contrast", "Gray Channel Contrast");
		contrastEnhancementTypeListBox.setWidth(LONG_FIELD_WIDTH);
		contrastEnhancementTypeListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				int index = contrastEnhancementTypeListBox.getSelectedIndex();
				if (index != -1) {
					String selectedItem = (String) contrastEnhancementTypeListBox
							.getItemText(index);
					if (selectedItem.equalsIgnoreCase("Global Contrast")) {
						globalContrastListBox = new ListBox();
						buildGlobalContrastPanel();
					} else if (selectedItem
							.equalsIgnoreCase("Red/Green/Blue Channel Contrast")) {
						RedGreenBlueChannelContrastPanel();
					} else if (selectedItem
							.equalsIgnoreCase("Gray Channel Contrast")) {
						GrayChannelContrastPanel();
					}
				}
			}

		});
		
		if((data != null)&&(data.getContrastValueType()!= null))
		{	
			if(data.getContrastValueType().equals("global-contrast-value"))
			{
				contrastEnhancementTypeListBox.setSelectedIndex(1);
			}
			else if(data.getContrastValueType().equals("rgb-contrast-value"))
			{
				contrastEnhancementTypeListBox.setSelectedIndex(2);
			}
			else if(data.getContrastValueType().equals("gray-contrast-value"))
			{
				contrastEnhancementTypeListBox.setSelectedIndex(3);
			}
		}
		
		p.add(contrastEnhancementTypeListBox);
		removableContrastEnhancementPanels.add(p);
		return p;
	}
	
	protected void GrayChannelContrastPanel() {
		GrayChannelContrastPanel(null);
	}
	
	public ContentPanel GrayChannelContrastPanel(SLDEditorRuleVO data) {
		int i=0;
		int iArray[] = new int[2];
		for(int x=0; x<iArray.length; x++)
		{
			iArray[x] = -1;
		}
		int arrayIndex=0;
		i=0;
		for (HorizontalPanel p : this.getRemovableContrastEnhancementPanels())
		{
			if(p!=null)
			{
				if((p.getId().equals("GLOBAL_CONTRAST_ENHANCEMENT_PANEL"))||(p.getId().equals("GLOBAL_CONTRAST_ENHANCEMENT_GAMMA_PANEL")))
				{
					this.getActionPanel().remove(p);
					iArray[arrayIndex] = i;
					arrayIndex++;
				}
				i++;
			}
		}
		//for(int x=0; x<iArray.length; x++)
		for(int x=(iArray.length-1); x>=0; x--)
		{
			if(iArray[x] != -1)
			{
				this.getRemovableContrastEnhancementPanels().remove(iArray[x]);
			}
		}
		
		int index=-1;
		i=0;
		for (ContentPanel p : this.getRemovableContrastEnhancementContentPanels())
		{
			if(p!=null)
			{
				if(p.getId().equals("RGB_CHANNEL_CONTRAST_PANEL"))
				{
					this.getActionPanel().remove(p);
					index = i;
				}
				i++;
			}
		}
		if(index != -1)
		{
			this.getRemovableContrastEnhancementContentPanels().remove(index);
		}
		GridView gridView = new GridView();
		final CheckBoxSelectionModel<RGBContrast> sm = new CheckBoxSelectionModel<RGBContrast>(); 
		final ListStore<RGBContrast> store = new ListStore<RGBContrast>();
		
		List<RGBContrast> items = new ArrayList<RGBContrast>();
		if(data!= null)
		{
			if((data.getRgbcontrast()!= null)&&(data.getRgbcontrast().size() != 0))
			{
				store.add(data.getRgbcontrast());
			}
			else
			{
				store.add(items);
			}
		}
		else
		{
			store.add(items);
		}
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		configs.add(sm.getColumn()); 
		 
		ColumnConfig column = new ColumnConfig();  
		
		 final SimpleComboBox<String> combo = new SimpleComboBox<String>();    
		 combo.setTriggerAction(TriggerAction.ALL);    
		 combo.add("Gray");    
		 
		 CellEditor editor = new CellEditor(combo) {    
		    
			 public Object preProcessValue(Object value) {    
				 	if (value == null) {    
				 		return value;    
				 	}    
				 	return combo.findModel(value.toString());    
			   }    
			       
			  public Object postProcessValue(Object value) {    
				  if (value == null) {    
					  return value;    
				  }    
				  return ((ModelData) value).get("value");    
			  }    
			  };    
			     
		column = new ColumnConfig("nameChannel", "Channel Name", 120);  
		column.setAlignment(HorizontalAlignment.LEFT);  
		column.setEditor(editor);    
		configs.add(column);  
		
		 final SimpleComboBox<String> comboType = new SimpleComboBox<String>();    
		 comboType.setTriggerAction(TriggerAction.ALL);    
		 comboType.add("Normalize");    
		 comboType.add("Histogram");    
		 comboType.add("Gamma");    
		 
		 CellEditor editor2 = new CellEditor(comboType) {    
		    
			 public Object preProcessValue(Object value) {    
				 	if (value == null) {    
				 		return value;    
				 	}    
				 	return comboType.findModel(value.toString());    
			   }    
			       
			  public Object postProcessValue(Object value) {    
				  if (value == null) {    
					  return value;    
				  }    
				  return ((ModelData) value).get("value");    
			  }    
			  };    
			     
		column = new ColumnConfig("typeChannelContrast", "Type Contrast", 120);  
		column.setAlignment(HorizontalAlignment.LEFT);  
		column.setEditor(editor2);    
		configs.add(column);  
		
		column = new ColumnConfig();  
	     column.setId("gammaValueContrast");  
	     column.setHeader("Gamma Value (only for Gamma Type)");  
	     column.setAlignment(HorizontalAlignment.CENTER);  
	     column.setWidth(150);  
	     column.setNumberFormat(NumberFormat.getDecimalFormat());  
	     column.setEditor(new CellEditor(new NumberField()));  
	 
	     configs.add(column);  
		 ColumnModel cm = new ColumnModel(configs);  
		 ContentPanel cp = new ContentPanel();  
	    // cp.setIcon(Resources.ICONS.table());  
	     cp.setHeading("Contrast Enhancement for Gray Channel");  
	     cp.setFrame(true);  
	     cp.setSize(600, 190);  
	     cp.setLayout(new FitLayout());  
	     
	     ToolBar toolBarUp = new ToolBar();  
	     Button add = new Button("Save the contrast enhancement items.");
	     add.addSelectionListener(new SelectionListener<ButtonEvent>() {  
	    	 @Override  
		     public void componentSelected(ButtonEvent ce) {
	    		 store.commitChanges();
	    		 List<RGBContrast> listItems = store.getModels();
	    		 
	    		 if(redGreenBluerules != null)
	    		 {
	    			 redGreenBluerules.clear();
	    			 redGreenBluerules = new ArrayList<RGBContrast>();
	    		 }
	    		 for(int i=0; i<listItems.size();i++)
	    		 {
	    			 redGreenBluerules.add(new RGBContrast(listItems.get(i).getnameChannel(),listItems.get(i).gettypeChannelContrast(),listItems.get(i).getgammaValueContrast()));
	    		 }
	    	 }
	     });
	     toolBarUp.setAlignment(HorizontalAlignment.CENTER);
	     toolBarUp.add(add);  
	     cp.setTopComponent(toolBarUp);
 
	     final RowEditor<RGBContrast> re = new RowEditor<RGBContrast>();  
	     final Grid<RGBContrast> grid = new Grid<RGBContrast>(store, cm);  
	     
	     grid.setView(gridView);
		 
	     grid.setSelectionModel(sm);  
	     grid.setAutoExpandColumn("nameChannel");
	     grid.setAutoExpandColumn("typeChannelContrast");
	     grid.setAutoExpandColumn("gammaValueContrast");
	     grid.setBorders(true);
	     grid.addPlugin(re);
	     grid.addPlugin(sm); 
	     
	     grid.addListener(Events.CellClick, new Listener<BaseEvent>(){
	    	 public void handleEvent(BaseEvent be) {
	    		 GridEvent ge = (GridEvent)be;
	    		 int colonna;
	    		 if((colonna = ge.getColIndex()) == 2)
	    		 {
	    			 int riga = ge.getRowIndex();
	    			 String property = ge.getProperty();
	              }
	            }
	     });
	     cp.add(grid);
	     cp.setButtonAlign(HorizontalAlignment.CENTER);
	     cp.addButton(new Button("Add New Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {  
	    	   //store.rejectChanges();  
	    	 RGBContrast rgbgrid = new RGBContrast();
	    	 rgbgrid.setnameChannel("New Name Channel");
	    	 rgbgrid.settypeChannelContrast("New Type Contrast");
	    	 rgbgrid.setgammaValueContrast(0);
	         re.stopEditing(false);  
		     store.insert(rgbgrid, 0);  
		     re.startEditing(store.indexOf(rgbgrid), true);  
	       }  
	     }));      
	   
	     cp.addButton(new Button("Remove Selected Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {  
	    	   List<RGBContrast> toDelete = grid.getSelectionModel().getSelectedItems();
	    	   if(toDelete.size() !=0)
	    	   {
		    	   int i=0;
		    	   for(i=0; i<toDelete.size(); i++)
		    	   {
		    		   grid.getStore().remove(toDelete.get(i));
		    	   }
		    	   store.commitChanges(); 
	    	   }
	       }  
	     }));
	     removableContrastEnhancementContentPanels.add(cp);
	     
	     this.getActionPanel().add(cp);
	     if(data == null)
	     {
	    	 this.getActionPanel().getLayout().layout();
	     }
	     cp.setId("GRAY_CHANNEL_CONTRAST_PANEL");
	     return cp;	
	}

	protected void RedGreenBlueChannelContrastPanel() {
		RedGreenBlueChannelContrastPanel(null);
	}

	public ContentPanel RedGreenBlueChannelContrastPanel(SLDEditorRuleVO data) {
		int i=0;
		int iArray[] = new int[2];
		for(int x=0; x<iArray.length; x++)
		{
			iArray[x] = -1;
		}
		int arrayIndex=0;
		i=0;
		for (HorizontalPanel p : this.getRemovableContrastEnhancementPanels())
		{
			if(p!=null)
			{
				if((p.getId().equals("GLOBAL_CONTRAST_ENHANCEMENT_PANEL"))||(p.getId().equals("GLOBAL_CONTRAST_ENHANCEMENT_GAMMA_PANEL")))
				{
					this.getActionPanel().remove(p);
					iArray[arrayIndex] = i;
					arrayIndex++;
				}
				i++;
			}
		}
		for(int x=(iArray.length-1); x>=0; x--)
		{
			if(iArray[x] != -1)
			{
				this.getRemovableContrastEnhancementPanels().remove(iArray[x]);
			}
		}
		int index=-1;
		i=0;
		for (ContentPanel p : this.getRemovableContrastEnhancementContentPanels())
		{
			if(p!=null)
			{
				if(p.getId().equals("GRAY_CHANNEL_CONTRAST_PANEL"))
				{
					this.getActionPanel().remove(p);
					index = i;
				}
				i++;
			}
		}
		if(index != -1)
		{
			this.getRemovableContrastEnhancementContentPanels().remove(index);
		}
		GridView gridView = new GridView();
		final CheckBoxSelectionModel<RGBContrast> sm = new CheckBoxSelectionModel<RGBContrast>(); 
		final ListStore<RGBContrast> store = new ListStore<RGBContrast>();
		
		List<RGBContrast> items = new ArrayList<RGBContrast>();
		if(data != null)
		{
			if((data.getRgbcontrast()!= null)&&(data.getRgbcontrast().size() != 0))
			{
				store.add(data.getRgbcontrast());
			}
			else
			{
				store.add(items);
			}
		}
		else
		{
			store.add(items);
		}
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		configs.add(sm.getColumn()); 
		 
		ColumnConfig column = new ColumnConfig();  
		
		 final SimpleComboBox<String> combo = new SimpleComboBox<String>();    
		 combo.setTriggerAction(TriggerAction.ALL);    
		 combo.add("Red");    
		 combo.add("Green");    
		 combo.add("Blue");    
		 
		 CellEditor editor = new CellEditor(combo) { 
			 public Object preProcessValue(Object value) {    
				 	if (value == null) {    
				 		return value;    
				 	}    
				 	return combo.findModel(value.toString());    
			   }    
			       
			  public Object postProcessValue(Object value) {    
				  if (value == null) {    
					  return value;    
				  }    
				  return ((ModelData) value).get("value");    
			  }    
			  };    
			     
		column = new ColumnConfig("nameChannel", "Channel Name", 120);  
		column.setAlignment(HorizontalAlignment.LEFT);  
		column.setEditor(editor);    
		configs.add(column);  
		
		 final SimpleComboBox<String> comboType = new SimpleComboBox<String>();    
		 comboType.setTriggerAction(TriggerAction.ALL);    
		 comboType.add("Normalize");    
		 comboType.add("Histogram");    
		 comboType.add("Gamma");    
		 
		 CellEditor editor2 = new CellEditor(comboType) {    
		    
			 public Object preProcessValue(Object value) {    
				 	if (value == null) {    
				 		return value;    
				 	}    
				 	return comboType.findModel(value.toString());    
			   }    
			       
			  public Object postProcessValue(Object value) {    
				  if (value == null) {    
					  return value;    
				  }    
				  return ((ModelData) value).get("value");    
			  }    
			  };    
			     
		column = new ColumnConfig("typeChannelContrast", "Type Contrast", 120);  
		column.setAlignment(HorizontalAlignment.LEFT);  
		column.setEditor(editor2);    
		configs.add(column);  
		
		column = new ColumnConfig();  
	     column.setId("gammaValueContrast");  
	     column.setHeader("Gamma Value (only for Gamma Type)");  
	     column.setAlignment(HorizontalAlignment.CENTER);  
	     column.setWidth(150);  
	     column.setNumberFormat(NumberFormat.getDecimalFormat());  
	     column.setEditor(new CellEditor(new NumberField()));  
	 
		configs.add(column);  
		 
		 ColumnModel cm = new ColumnModel(configs);  
			
	      
	     ContentPanel cp = new ContentPanel();
	     cp.setHeading("Contrast Enhancement for Red/Green/Blue Channel");  
	     cp.setFrame(true);  
	     cp.setSize(600, 220);  
	     cp.setLayout(new FitLayout());  
	     
	     ToolBar toolBarUp = new ToolBar();  
	     Button add = new Button("Save the contrast enhancement items.");
	     add.addSelectionListener(new SelectionListener<ButtonEvent>() {  
	    	 @Override  
		     public void componentSelected(ButtonEvent ce) {
	    		 store.commitChanges();
	    		 List<RGBContrast> listItems = store.getModels();
	    		 
	    		 if(redGreenBluerules != null)
	    		 {
	    			 redGreenBluerules.clear();
	    			 redGreenBluerules = new ArrayList<RGBContrast>();
	    		 }
	    		 for(int i=0; i<listItems.size();i++)
	    		 {
	    			 redGreenBluerules.add(new RGBContrast(listItems.get(i).getnameChannel(),listItems.get(i).gettypeChannelContrast(),listItems.get(i).getgammaValueContrast()));
	    		 }
	    	 }
	     });
	     toolBarUp.setAlignment(HorizontalAlignment.CENTER);
	     toolBarUp.add(add);  
	     cp.setTopComponent(toolBarUp);
	     
	     final RowEditor<RGBContrast> re = new RowEditor<RGBContrast>();  
	     final Grid<RGBContrast> grid = new Grid<RGBContrast>(store, cm);  
	     
	     grid.setView(gridView);
		 
	     grid.setSelectionModel(sm);  
	     grid.setAutoExpandColumn("nameChannel");
	     grid.setAutoExpandColumn("typeChannelContrast");
	     grid.setAutoExpandColumn("gammaValueContrast");
	     grid.setBorders(true);
	     grid.addPlugin(re);
	     grid.addPlugin(sm); 
	     
	     grid.addListener(Events.CellClick, new Listener<BaseEvent>(){
	    	 public void handleEvent(BaseEvent be) {
	    		 GridEvent ge = (GridEvent)be;
	    		 int colonna;
	    		 if((colonna = ge.getColIndex()) == 2)
	    		 {
	    			 int riga = ge.getRowIndex();
	    			 String property = ge.getProperty();
	              }
	            }
	     });
	     
	     cp.add(grid);
	     cp.setButtonAlign(HorizontalAlignment.CENTER);
	     cp.addButton(new Button("Add New Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {  
	    	   //store.rejectChanges();  
	    	 RGBContrast rgbgrid = new RGBContrast();
	    	 rgbgrid.setnameChannel("New Name Channel");
	    	 rgbgrid.settypeChannelContrast("New Type Contrast");
	    	 rgbgrid.setgammaValueContrast(0);
	         re.stopEditing(false);  
		     store.insert(rgbgrid, 0);  
		     re.startEditing(store.indexOf(rgbgrid), true);  
	       }  
	     }));      
	   
	     cp.addButton(new Button("Remove Selected Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {
	    	   List<RGBContrast> toDelete = grid.getSelectionModel().getSelectedItems();
	    	   if(toDelete.size() !=0)
	    	   {
		    	   int i=0;
		    	   
		    	   for(i=0; i<toDelete.size(); i++)
		    	   {
		    		   grid.getStore().remove(toDelete.get(i));
		    	   }
		    	   store.commitChanges(); 
	    	   }
	       }  
	     }));
	     removableContrastEnhancementContentPanels.add(cp);
	     
	     this.getActionPanel().add(cp);
	     if(data == null)
	     {
	    	 this.getActionPanel().getLayout().layout();
	     }
	     cp.setId("RGB_CHANNEL_CONTRAST_PANEL");
	     return cp;
		
	}

	public void buildGlobalContrastPanel()
	{
		buildGlobalContrastPanel(null);
	}

	public HorizontalPanel buildGlobalContrastPanel(SLDEditorRuleVO data) {
		int i=0;
		int iArray[] = new int[2];
		for(int x=0; x<iArray.length; x++)
		{
			iArray[x] = -1;
		}
		int arrayIndex=0;
		i=0;
		for (ContentPanel p : this.getRemovableContrastEnhancementContentPanels())
		{
			if(p!=null)
			{
				if((p.getId().equals("RGB_CHANNEL_CONTRAST_PANEL"))||(p.getId().equals("GRAY_CHANNEL_CONTRAST_PANEL")))
				{
					this.getActionPanel().remove(p);
					iArray[arrayIndex] = i;
					arrayIndex++;
				}
				i++;
			}
		}
		for(int x=(iArray.length-1); x>=0; x--)
		{
			if(iArray[x] != -1)
			{
				this.getRemovableContrastEnhancementContentPanels().remove(iArray[x]);
			}
		}
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html globalContrastLabel = new Html("<b>Global Contrast Value</b>");
		globalContrastLabel.setWidth(FIELD_WIDTH);
		globalContrastLabel.setHeight("25px");
		p.add(globalContrastLabel);
		if(globalContrastListBox == null)
		{
			globalContrastListBox = new ListBox();
		}
		globalContrastListBox.clear();
		globalContrastListBox.addItem("Normalize", "Normalize");
		globalContrastListBox.addItem("Histogram", "Histogram");
		globalContrastListBox.addItem("Gamma", "Gamma");
		globalContrastListBox.setWidth(LONG_FIELD_WIDTH);
		globalContrastListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				int index = globalContrastListBox.getSelectedIndex();
				if(index != -1)
				{
					String selectedItem = (String) globalContrastListBox
							.getItemText(index);
					if (selectedItem.equalsIgnoreCase("Gamma")) {
						gammaValuePanel();
					}
				}				
			}
		});
		if((data != null)&&(data.getContrastEnhancementMap()!= null))
		{
			String value = data.getContrastEnhancementMap().get("global-contrast-value");
			if (value != null) {
				if (value.equals("Normalize")) {
					globalContrastListBox.setSelectedIndex(0);
				} else if (value.equals("Histogram")) {
					globalContrastListBox.setSelectedIndex(1);
				} else if (value.equals("Gamma")) {
					globalContrastListBox.setSelectedIndex(2);
				}
			}
		}
		p.add(globalContrastListBox);
		p.setId("GLOBAL_CONTRAST_ENHANCEMENT_PANEL");
		removableContrastEnhancementPanels.add(p);
		this.getActionPanel().add(p);
		if(data == null)
		{
			this.getActionPanel().getLayout().layout();
		}
		return p;
	}

	public void gammaValuePanel() {
		gammaValuePanel(null);
	}
	
	public HorizontalPanel gammaValuePanel(SLDEditorRuleVO data) {
		HorizontalPanel p= null;
		gammaValue = new TextField<String>();
		gammaValue.setAllowBlank(false);
		if(data == null)
		{
			this.getActionPanel().add(buildGammaValuePanel());
			this.getActionPanel().getLayout().layout();
		}
		else
		{
			p = buildGammaValuePanel(data);
		}
		return p;
	}

	public HorizontalPanel buildGammaValuePanel() {
		return buildGammaValuePanel(null);
	}
	
	public HorizontalPanel buildGammaValuePanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html gammaValueLabel = new Html("<b>Gamma Value</b>");
		gammaValueLabel.setWidth(FIELD_WIDTH);
		gammaValueLabel.setHeight("25px");
		p.add(gammaValueLabel);
		gammaValue.setWidth(LONG_FIELD_WIDTH);
		gammaValue.setEmptyText("e.g 2");
		if(data != null)
		{
			String gammaValueString = data.getContrastEnhancementMap().get("gamma-value");
			if((gammaValueString != null)&&(!gammaValueString.equals("")))
			{
				gammaValue.setValue(gammaValueString);
			}
		}
		p.add(gammaValue);
		p.setId("GLOBAL_CONTRAST_ENHANCEMENT_GAMMA_PANEL");	
		removableContrastEnhancementPanels.add(p);
		return p;
	}
	
	protected void buildRedGreenBlueSelectionsPanel() {
		buildRedGreenBlueSelectionsPanel(null);
	}

	public void buildRedGreenBlueSelectionsPanel(SLDEditorRuleVO data) {
		int i=0;
		int iArray[] = new int[3];
		for(int x=0; x<iArray.length; x++)
		{
			iArray[x] = -1;
		}
		int arrayIndex=0;
		i=0;
		for (HorizontalPanel p : this.getRemovableChannelSelectionPanels())
		{
			if(p!=null)
			{
				if(p.getId().equals("GRAY_PANEL"))
				{
					this.getActionPanel().remove(p);
					iArray[arrayIndex] = i;
					arrayIndex++;
				}
				i++;
			}
		}
		for(int x=(iArray.length-1); x>=0; x--)
		{
			if(iArray[x] != -1)
			{
				this.getRemovableChannelSelectionPanels().remove(iArray[x]);
			}
		}
		redChannel = new TextField<String>();
		redChannel.setAllowBlank(false);
		greenChannel = new TextField<String>();
		greenChannel.setAllowBlank(false);
		blueChannel = new TextField<String>();
		blueChannel.setAllowBlank(false);
		if(data == null)
		{
			this.getActionPanel().add(buildRedChannelPanel());
			this.getActionPanel().add(buildGreenChannelPanel());
			this.getActionPanel().add(buildBlueChannelPanel());
			this.getActionPanel().getLayout().layout();
		}
	}

	protected void buildGraySelectionPanel() {
		buildGraySelectionPanel(null);
	}
	
	public void buildGraySelectionPanel(SLDEditorRuleVO data) {
		int i=0;
		int iArray[] = new int[3];
		for(int x=0; x<iArray.length; x++)
		{
			iArray[x] = -1;
		}
		int arrayIndex=0;
		i=0;
		for (HorizontalPanel p : this.getRemovableChannelSelectionPanels())
		{
			if(p!=null)
			{
				if((p.getId().equals("RED_PANEL"))||(p.getId().equals("GREEN_PANEL"))||(p.getId().equals("BLUE_PANEL")))
				{
					this.getActionPanel().remove(p);
					iArray[arrayIndex] = i;
					arrayIndex++;
				}
				i++;
			}
		}
		for(int x=(iArray.length-1); x>=0; x--)
		{
			if(iArray[x] != -1)
			{
				this.getRemovableChannelSelectionPanels().remove(iArray[x]);
			}
		}
		grayChannel = new TextField<String>();
		grayChannel.setAllowBlank(false);
		if(data == null)
		{
			this.getActionPanel().add(buildGrayChannelPanel());
			this.getActionPanel().getLayout().layout();
		}
	}
	
	public HorizontalPanel buildRedChannelPanel() {
		return buildRedChannelPanel(null);
	}
	
	public HorizontalPanel buildRedChannelPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html redChannelLabel = new Html("<b>Red Channel Name</b>");
		redChannelLabel.setWidth(FIELD_WIDTH);
		redChannelLabel.setHeight("25px");
		p.add(redChannelLabel);
		redChannel.setWidth(LONG_FIELD_WIDTH);
		redChannel.setEmptyText("e.g. NameRedChannel");
		if(data != null)
		{
			if(data.getChannelSelectionMap() != null)
			{
				if(data.getChannelSelectionMap().containsKey("red-channel-name"))
				{
					redChannel.setValue(data.getChannelSelectionMap().get("red-channel-name"));
				}
			}
			
		}
		p.add(redChannel);
		p.setId("RED_PANEL");
		removableChannelSelectionPanels.add(p);
		return p;
	}

	public HorizontalPanel buildGreenChannelPanel() {
		return buildGreenChannelPanel(null);
	}
	
	public HorizontalPanel buildGreenChannelPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html greenChannelLabel = new Html("<b>Green Channel Name</b>");
		greenChannelLabel.setWidth(FIELD_WIDTH);
		greenChannelLabel.setHeight("25px");
		p.add(greenChannelLabel);
		greenChannel.setWidth(LONG_FIELD_WIDTH);
		greenChannel.setEmptyText("e.g. NameGreenChannel");
		if(data != null)
		{
			if(data.getChannelSelectionMap() != null)
			{
				if(data.getChannelSelectionMap().containsKey("green-channel-name"))
				{
					greenChannel.setValue(data.getChannelSelectionMap().get("green-channel-name"));
				}
			}			
		}
		p.add(greenChannel);
		p.setId("GREEN_PANEL");
		removableChannelSelectionPanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildBlueChannelPanel() {
		return buildBlueChannelPanel(null);
	}
	
	public HorizontalPanel buildBlueChannelPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html blueChannelLabel = new Html("<b>Blue Channel Name</b>");
		blueChannelLabel.setWidth(FIELD_WIDTH);
		blueChannelLabel.setHeight("25px");
		p.add(blueChannelLabel);
		blueChannel.setWidth(LONG_FIELD_WIDTH);
		blueChannel.setEmptyText("e.g. NameBlueChannel");
		if(data != null)
		{
			if(data.getChannelSelectionMap() != null)
			{
				if(data.getChannelSelectionMap().containsKey("blue-channel-name"))
				{
					blueChannel.setValue(data.getChannelSelectionMap().get("blue-channel-name"));
				}
			}			
		}
		p.add(blueChannel);
		p.setId("BLUE_PANEL");
		removableChannelSelectionPanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildGrayChannelPanel() {
		return buildGrayChannelPanel(null);
	}
	
	public HorizontalPanel buildGrayChannelPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html grayChannelLabel = new Html("<b>Gray Channel Name</b>");
		grayChannelLabel.setWidth(FIELD_WIDTH);
		grayChannelLabel.setHeight("25px");
		p.add(grayChannelLabel);
		grayChannel.setWidth(LONG_FIELD_WIDTH);
		grayChannel.setEmptyText("e.g. NameGrayChannel");
		if(data != null)
		{
			if(data.getChannelSelectionMap() != null)
			{
				if(data.getChannelSelectionMap().containsKey("gray-channel-name"))
				{
					grayChannel.setValue(data.getChannelSelectionMap().get("gray-channel-name"));
				}
			}			
		}
		p.add(grayChannel);
		p.setId("GRAY_PANEL");
		removableChannelSelectionPanels.add(p);
		return p;
	}
	
	
	public HorizontalPanel buildFormatColorMapPanel5(SLDEditorRuleVO data) {
		HorizontalPanel p = buildSliderOpacityPanel(data);
		removableColorMapPanels.add(p);
		return p;
	}
	
	public ContentPanel buildFormatColorMapPanel6(SLDEditorRuleVO data) {
		ContentPanel p = buildSmallGridPanel(data);
		return p;
	}
	
	public ContentPanel buildFormatColorMapPanel7(SLDEditorRuleVO data) {
		ContentPanel p = buildBigGridPanel(data);
		return p;
	}
	
	protected void buildIntervalOpacityPanel() {
		 if((removableSliderOpacityPanels != null)&&(removableSliderOpacityPanels.size() != 0))
		 {
			 this.getActionPanel().remove(removableSliderOpacityPanels.get(0));
			 removableSliderOpacityPanels.clear();
		 }
		 int index=-1;
		 int i=0;
			for (ContentPanel p : this.getRemovableColorMapContentPanels())
			{
				if(p!=null)
				{
					if(p.getId().equals("COLOR_MAP_WITH_BIG_GRID"))
					{
						this.getActionPanel().remove(p);
						index = i;
					}
					i++;
				}
			}
			if(index != -1)
			{
				this.getRemovableColorMapContentPanels().remove(index);
			}
		HorizontalPanel p = buildSliderOpacityPanel();
		removableColorMapPanels.add(p);
		this.getActionPanel().add(p);
		this.getActionPanel().add(buildSmallGridPanel());
		this.getActionPanel().getLayout().layout();
	}

	protected void buildGlobalOpacityPanel() {
		int index=-1;
		int i=0;
		for (ContentPanel p : this.getRemovableColorMapContentPanels())
		{
			if(p!=null)
			{
				if(p.getId().equals("COLOR_MAP_WITH_SMALL_GRID"))
				{
					this.getActionPanel().remove(p);
					index = i;
				}
				i++;
			}
		}
		if(index != -1)
		{
			this.getRemovableColorMapContentPanels().remove(index);
		}
		index=-1;
		i=0;
		for (HorizontalPanel p : this.getRemovableColorMapPanels())
		{
			if(p!=null)
			{
				if(p.getId().equals("SLIDER_OPACITY_PANEL"))
				{
					this.getActionPanel().remove(p);
					index = i;
				}
				i++;
			}
		}
		if(index != -1)
		{
			this.getRemovableColorMapPanels().remove(index);
		}
		ContentPanel cp = buildBigGridPanel();
		removableColorMapContentPanels.add(cp);
		this.getActionPanel().add(cp);	
		this.getActionPanel().getLayout().layout();		
	}
	
	private ContentPanel buildSmallGridPanel() {
		return buildSmallGridPanel(null);
	}
	
	private ContentPanel buildSmallGridPanel(SLDEditorRuleVO data) {
		GridView gridView = new GridView();
		final CheckBoxSelectionModel<ColorMapSmallGrid> sm = new CheckBoxSelectionModel<ColorMapSmallGrid>(); 
		final ListStore<ColorMapSmallGrid> store = new ListStore<ColorMapSmallGrid>();
		List<ColorMapSmallGrid> items;
		if(data != null)
		{
			if((data.getItemsSmallGrid() != null)&&(data.getItemsSmallGrid().size() != 0))
			{
				store.add(data.getItemsSmallGrid());
			}
			else
			{
				items = new ArrayList<ColorMapSmallGrid>();
				store.add(items);
			}
		}
		else
		{
			items = new ArrayList<ColorMapSmallGrid>();
			store.add(items);
		}
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		configs.add(sm.getColumn()); 
		 
		ColumnConfig column = new ColumnConfig();  
		column.setId("quantity");  
		column.setHeader("Quantity Value");  
		column.setAlignment(HorizontalAlignment.CENTER);  
		column.setWidth(120);  
		column.setNumberFormat(NumberFormat.getDecimalFormat());  
		column.setEditor(new CellEditor(new NumberField()));  
		     
		configs.add(column); 
		 
		column = new ColumnConfig();  
	    column.setId("label");  
	    column.setHeader("Label");  
	    column.setAlignment(HorizontalAlignment.CENTER);  
	    column.setWidth(100);  
	    TextField<String> text = new TextField<String>();    
	    text.setAllowBlank(false);    
	    text.setAutoValidate(true);    
	    column.setEditor(new CellEditor(text));  
	    configs.add(column); 
	    
	    column = new ColumnConfig();  
		column.setId("color");  
		column.setHeader("Color (Use Color Picker to change.)");  
		column.setWidth(130);  
		column.setAlignment(HorizontalAlignment.CENTER);  
		TextField<String> text2 = new TextField<String>();    
		text2.setAllowBlank(false);    
		text2.setAutoValidate(true);    
		column.setRenderer(new GridCellRenderer<ColorMapSmallGrid>() {
				
			    public Object render(ColorMapSmallGrid model, String property,
			                                    ColumnData config,
			               int rowIndex, int colIndex,
			               ListStore<ColorMapSmallGrid> store,
			               Grid<ColorMapSmallGrid> grid) {
			  
			          Object value = model.get(property);    
			          String colorM = model.getColor().trim();
			          if(colorM.contains("#"))
			          {
			        	  colorM = colorM.substring(1);
			          }
			      //  System.out.println("********************model.getColor()******************* "+ colorM);  
			          if (value != null)            
			              //return "<span " +
			              return "<div " +
			                    // "style='background-color:yellow;'>" +
			                     "style='background-color:#"+ colorM+";'>" +
			                     value.toString() +
			                    // "</span>";
			          			"</div>";
			           return null;    
			      }
			 });
		 
		 column.setEditor(new CellEditor(text2));
		 configs.add(column);
		 ColumnModel cm = new ColumnModel(configs);    
	     ContentPanel cp = new ContentPanel();
	     cp.setHeading("Color Map Entry");  
	     cp.setFrame(true);  
	     cp.setSize(600, 220);  
	     cp.setLayout(new FitLayout());  
	     
	     ToolBar toolBarUp = new ToolBar();  
	     Button add = new Button("Save the color map items.");
	     add.addSelectionListener(new SelectionListener<ButtonEvent>() {  
	    	 @Override  
		     public void componentSelected(ButtonEvent ce) {
	    		 store.commitChanges();
	    		 List<ColorMapSmallGrid> listItems = store.getModels();
	    		 if(rules != null)
	    		 {
	    			 rules.clear();
	    			 rules = new ArrayList<ColorMapSmallGrid>();
	    		 }
	    		 String color = "#"; 
	    		 for(int i=0; i<listItems.size();i++)
	    		 {
	    			 color = "#"; 
	    			 if(!(listItems.get(i).getColor().contains("#")))
		    			{
		    				color = color.concat(listItems.get(i).getColor());
		    			}
		    			else
		    			{
		    				color = listItems.get(i).getColor();
		    			}
	    			 rules.add(new ColorMapSmallGrid(listItems.get(i).getquantity(),listItems.get(i).getlabel(),color));
	    		 }
	    	 }
	     });
	     toolBarUp.setAlignment(HorizontalAlignment.CENTER);
	     toolBarUp.add(add);  
	     cp.setTopComponent(toolBarUp);
	     final RowEditor<ColorMapSmallGrid> re = new RowEditor<ColorMapSmallGrid>();  
	     final Grid<ColorMapSmallGrid> grid = new Grid<ColorMapSmallGrid>(store, cm);  
	     grid.setView(gridView);
		 grid.setSelectionModel(sm);  
	     grid.setAutoExpandColumn("quantity");
	     grid.setAutoExpandColumn("label");
	     grid.setAutoExpandColumn("color");
	     grid.setBorders(true);
	     grid.addPlugin(re);
	     grid.addPlugin(sm); 
	     
	     grid.addListener(Events.CellClick, new Listener<BaseEvent>(){
	    	 public void handleEvent(BaseEvent be) {
	    		 GridEvent ge = (GridEvent)be;
	    		 int colonna;
	    		 if((colonna = ge.getColIndex()) == 2)
	    		 {
	    			 int riga = ge.getRowIndex();
	    			 String property = ge.getProperty();
	             }
	            }
	     }); 
	     cp.add(grid);
	     cp.setButtonAlign(HorizontalAlignment.CENTER);
	     cp.addButton(new Button("Color Picker", new SelectionListener<ButtonEvent>() {  
			   
		       @Override  
		       public void componentSelected(ButtonEvent ce) {  
		    	   SLDEditorController.colorPickerListener(new HTML("CA1616")); 
		       }  
		     }));  
	       cp.addButton(new Button("Add New Entry", new SelectionListener<ButtonEvent>() {  
	       @Override  
	       public void componentSelected(ButtonEvent ce) {  
	    	   //store.rejectChanges();  
	    	 ColorMapSmallGrid smallgrid = new ColorMapSmallGrid();
	    	 smallgrid.setquantity(0);
	    	 smallgrid.setlabel("New Label");
	    	 smallgrid.setColor("New Color");
	         re.stopEditing(false);  
		     store.insert(smallgrid, 0);  
		     re.startEditing(store.indexOf(smallgrid), true);  
	       }  
	     }));      
	   
	     cp.addButton(new Button("Remove Selected Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {
	    	   List<ColorMapSmallGrid> toDelete = grid.getSelectionModel().getSelectedItems();
	    	   if(toDelete.size() !=0)
	    	   {
		    	   int i=0;
		    	   for(i=0; i<toDelete.size(); i++)
		    	   {
		    		   grid.getStore().remove(toDelete.get(i));
		    	   }
		    	   store.commitChanges(); 
	    	   }
	       }  
	     }));
	     cp.setId("COLOR_MAP_WITH_SMALL_GRID");
	     removableColorMapContentPanels.add(cp);
	     return cp;
	}
		
	private ContentPanel buildBigGridPanel() {
		return buildBigGridPanel(null);
	}
	
	private ContentPanel buildBigGridPanel(SLDEditorRuleVO data) {
		GridView gridView = new GridView();
		final CheckBoxSelectionModel<ColorMapBigGrid> sm = new CheckBoxSelectionModel<ColorMapBigGrid>(); 
		final ListStore<ColorMapBigGrid> store = new ListStore<ColorMapBigGrid>();
		
		List<ColorMapBigGrid> items = new ArrayList<ColorMapBigGrid>();
		if(data != null)
		{
			if((data.getItemsColorMapBigGrids()!= null)&&(data.getItemsColorMapBigGrids().size() != 0))
			{
				store.add(data.getItemsColorMapBigGrids());
			}
			else
			{
				store.add(items);
			}	
		}
		else
		{
			store.add(items);
		}
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		configs.add(sm.getColumn()); 
		 
		ColumnConfig column = new ColumnConfig();  
		column.setId("quantity");  
		column.setHeader("Quantity Value");  
		column.setAlignment(HorizontalAlignment.CENTER);  
		column.setWidth(120);  
		column.setNumberFormat(NumberFormat.getDecimalFormat());  
		column.setEditor(new CellEditor(new NumberField()));  
		     
		configs.add(column); 
		 
		column = new ColumnConfig();  
	    column.setId("label");  
	    column.setHeader("Label");
	    column.setAlignment(HorizontalAlignment.CENTER);  
	    column.setWidth(100);  
	    TextField<String> text = new TextField<String>();    
	    text.setAllowBlank(false);    
	    text.setAutoValidate(true);    
	    column.setEditor(new CellEditor(text));  
	    configs.add(column); 
	    
	    column = new ColumnConfig();  
		column.setId("color");  
		column.setHeader("Color (Use Color Picker to change.)");  
		column.setWidth(130);  
		column.setAlignment(HorizontalAlignment.CENTER);  
		TextField<String> text2 = new TextField<String>();    
		text2.setAllowBlank(false);    
		text2.setAutoValidate(true);
		
		column.setRenderer(new GridCellRenderer<ColorMapBigGrid>() {
				
			    public Object render(ColorMapBigGrid model, String property,
			                                    ColumnData config,
			               int rowIndex, int colIndex,
			               ListStore<ColorMapBigGrid> store,
			               Grid<ColorMapBigGrid> grid) {
			  
			          Object value = model.get(property);    
			          String colorM = model.getColor().trim();
			          if(colorM.contains("#"))
			          {
			        	  colorM = colorM.substring(1);
			          }
			      //  System.out.println("********************model.getColor()******************* "+ colorM);  
			          if (value != null)            
			              //return "<span " +
			              return "<div " +
			                    // "style='background-color:yellow;'>" +
			                     "style='background-color:#"+ colorM+";'>" +
			                     value.toString() +
			                    // "</span>";
			          			"</div>";
			           return null;    
			      }
			 });
		 
		 column.setEditor(new CellEditor(text2));
		 configs.add(column);
		 
		 final SimpleComboBox<String> combo = new SimpleComboBox<String>();    
		 combo.setTriggerAction(TriggerAction.ALL);    
		 combo.add("0");    
		 combo.add("0.1");    
		 combo.add("0.2");    
		 combo.add("0.3");   
		 combo.add("0.4");
		 combo.add("0.5");
		 combo.add("0.6");
		 combo.add("0.7");
		 combo.add("0.8");
		 combo.add("0.9");
		 combo.add("1");
		 
		 CellEditor editor = new CellEditor(combo) {    
		    
			 public Object preProcessValue(Object value) {    
				 	if (value == null) {    
				 		return value;    
				 	}    
				 	return combo.findModel(value.toString());    
			   }    
			       
			  public Object postProcessValue(Object value) {    
				  if (value == null) {    
					  return value;    
				  }    
				  return ((ModelData) value).get("value");    
			  }    
			  };    
			     
		column = new ColumnConfig("opacity", "Opacity", 70);  
		column.setAlignment(HorizontalAlignment.LEFT);  
		column.setEditor(editor);    
		configs.add(column);  
		 
		 ColumnModel cm = new ColumnModel(configs);  
			
	      
	     ContentPanel cp = new ContentPanel();
	     cp.setHeading("Color Map Entry");  
	     cp.setFrame(true);  
	     cp.setSize(600, 220);  
	     cp.setLayout(new FitLayout());  
	     
	     ToolBar toolBarUp = new ToolBar();  
	     Button add = new Button("Save the color map items.");
	     add.addSelectionListener(new SelectionListener<ButtonEvent>() {  
	    	 @Override  
		     public void componentSelected(ButtonEvent ce) {
	    		 store.commitChanges();
	    		 List<ColorMapBigGrid> listItems = store.getModels();
	    		 
	    		 if(bigRrules != null)
	    		 {
	    			 bigRrules.clear();
	    			 bigRrules = new ArrayList<ColorMapBigGrid>();
	    		 }
	    		 String color = "#";
	    		 for(int i=0; i<listItems.size();i++)
	    		 {
	    			color = "#"; 
	    			if(!(listItems.get(i).getColor().contains("#")))
	    			{
	    				color = color.concat(listItems.get(i).getColor());
	    			}
	    			else
	    			{
	    				color = listItems.get(i).getColor();
	    			}
	    			 bigRrules.add(new ColorMapBigGrid(listItems.get(i).getquantity(),listItems.get(i).getlabel(),color, listItems.get(i).getopacity()));
	    		 }
	    	 }
	     });
	     toolBarUp.setAlignment(HorizontalAlignment.CENTER);
	     toolBarUp.add(add);  
	     cp.setTopComponent(toolBarUp);
	     final RowEditor<ColorMapBigGrid> re = new RowEditor<ColorMapBigGrid>();  
	     final Grid<ColorMapBigGrid> grid = new Grid<ColorMapBigGrid>(store, cm);  
	     
	     grid.setView(gridView);
		 
	     grid.setSelectionModel(sm);  
	     grid.setAutoExpandColumn("quantity");
	     grid.setAutoExpandColumn("label");
	     grid.setAutoExpandColumn("color");
	     grid.setBorders(true);
	     grid.addPlugin(re);
	     grid.addPlugin(sm); 
	     
	     grid.addListener(Events.CellClick, new Listener<BaseEvent>(){
	    	 public void handleEvent(BaseEvent be) {
	    		 GridEvent ge = (GridEvent)be;
	    		 int colonna;
	    		 if((colonna = ge.getColIndex()) == 2)
	    		 {
	    			 int riga = ge.getRowIndex();
	    			 String property = ge.getProperty();
	              }
	            }
	     });
	     cp.add(grid);
	     cp.setButtonAlign(HorizontalAlignment.CENTER);
	     cp.addButton(new Button("Color Picker", new SelectionListener<ButtonEvent>() {  
			   
		       @Override  
		       public void componentSelected(ButtonEvent ce) {
		    	   SLDEditorController.colorPickerListener(new HTML("CA1616"));		    	  
		       }  
		     }));
	     cp.addButton(new Button("Add New Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {  
	    	   //store.rejectChanges();  
	    	 ColorMapBigGrid biggrid = new ColorMapBigGrid();
	    	 biggrid.setquantity(0);
	    	 biggrid.setlabel("New Label");
	    	 biggrid.setColor("New Color");
	    	 biggrid.setopacity("0");
	         re.stopEditing(false);  
		     store.insert(biggrid, 0);  
		     re.startEditing(store.indexOf(biggrid), true);  
	       }  
	     }));      
	   
	     cp.addButton(new Button("Remove Selected Entry", new SelectionListener<ButtonEvent>() {  
	   
	       @Override  
	       public void componentSelected(ButtonEvent ce) {
	    	   List<ColorMapBigGrid> toDelete = grid.getSelectionModel().getSelectedItems();
	    	   if(toDelete.size() !=0)
	    	   {
		    	   int i=0; 
		    	   for(i=0; i<toDelete.size(); i++)
		    	   {
		    		   grid.getStore().remove(toDelete.get(i));
		    	   }
		    	   store.commitChanges(); 
	    	   }
	       }  
	     }));
	     cp.setId("COLOR_MAP_WITH_BIG_GRID");
	     return cp;
	}

	public HorizontalPanel buildSliderOpacityPanel() {
		return buildSliderOpacityPanel(null);
	}
	
	public HorizontalPanel buildSliderOpacityPanel(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fillOpacityLabel = new Html("<b>Global Opacity</b>");
		fillOpacityLabel.setWidth(FIELD_WIDTH);
		fillOpacityLabel.setHeight("25px");
		p.add(fillOpacityLabel);
		fillOpacityOnly.setWidth(LONG_FIELD_WIDTH);
		fillOpacityOnly.setIncrement(1);
		fillOpacityOnly.setValue(100);
		if(data != null)
		{
			if(data.getSliderOpacityOnlyMap() != null)
			{
				if((data.getSliderOpacityOnlyMap().get("fill-opacity")) != null)
				{
					double value = new Double(data.getSliderOpacityOnlyMap().get("fill-opacity"));
					fillOpacityOnly.setValue((int)(value *100));
				}
			}
		}
		p.add(fillOpacityOnly);
		p.setId("SLIDER_OPACITY_PANEL");
		return p;
	}

	public HorizontalPanel buildSliderOpacityPanelS() {
		return buildSliderOpacityPanelS(null);
	}
	
	public HorizontalPanel buildSliderOpacityPanelS(SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fillOpacityLabel = new Html("<b>Global Opacity</b>");
		fillOpacityLabel.setWidth(FIELD_WIDTH);
		fillOpacityLabel.setHeight("25px");
		p.add(fillOpacityLabel);
		fillOpacityOnly.setWidth(LONG_FIELD_WIDTH);
		fillOpacityOnly.setIncrement(1);
		fillOpacityOnly.setValue(100);
		if(data != null)
		{
			if(data.getSliderOpacityOnlyMap() != null)
			{
				if((data.getSliderOpacityOnlyMap().get("fill-opacity")) != null)
				{
					double value = new Double(data.getSliderOpacityOnlyMap().get("fill-opacity"));
					fillOpacityOnly.setValue((int)(value *100));
				}
			}
		}
		p.add(fillOpacityOnly);
		removableSliderOpacityPanels.add(p);
		return p;
	}
	
	public CheckBox getFormatColorMap() {
		return formatColorMap;
	}
	
	public CheckBox getSelectionChannelFormat() {
		return selectionChannelFormat;
	}
	
	public CheckBox getSliderOpacityFormat()
	{
		return sliderOpacityFormat;
	}
	
	public CheckBox getContrastEnhancementFormat() {
		return contrastEnhancementFormat;
	}
	
	public ListBox getColorMapTypeListBox() {
		return colorMapTypeListBox;
	}
	
	public ListBox getColorMapExtendedListBox() {
		return colorMapExtendedListBox;
	}
	
	public ListBox getOpacityForEachIntervalListBox() {
		return opacityForEachIntervalListBox;
	}
	
	public TextField<String> getRedChannel() {
		return redChannel;
	}
	
	public TextField<String> getGreenChannel() {
		return greenChannel;
	}
	
	public TextField<String> getBlueChannel() {
		return blueChannel;
	}
	
	public TextField<String> getGrayChannel() {
		return grayChannel;
	}
	
	public CheckBox getFormatRasterRule() {
		return formatRasterRule;
	}

	public Slider getFillOpacityOnly() {
		return fillOpacityOnly;
	}
	
	public static List<ColorMapSmallGrid> getRulesColorMap() {
		return rules;
	}

	public static List<ColorMapBigGrid> getBigRrules() {
		return bigRrules;
	}
	
	public ListBox getSelectionChannelTypeListBox() {
		return selectionChannelTypeListBox;
	}
	
	public ListBox getContrastEnhancementTypeListBox() {
		return contrastEnhancementTypeListBox;
	}
	
	public ListBox getGlobalContrastListBox() {
		return globalContrastListBox;
	}

	public TextField<String> getGammaValue() {
		return gammaValue;
	}

	public void setGammaValue(TextField<String> gammaValue) {
		this.gammaValue = gammaValue;
	}
	
	public static List<RGBContrast> getRedGreenBluerules() {
		return redGreenBluerules;
	}
	
	public static void setRedGreenBluerules(List<RGBContrast> redGreenBluerules) {
		SLDEditorRuleForRaster.redGreenBluerules = redGreenBluerules;
	}
}
