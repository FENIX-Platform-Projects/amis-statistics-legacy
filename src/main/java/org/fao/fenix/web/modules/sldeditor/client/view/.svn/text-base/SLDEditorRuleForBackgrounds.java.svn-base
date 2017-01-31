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

import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;
import org.fao.fenix.web.modules.sldeditor.common.vo.Interval;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import java.util.ArrayList;
import java.util.List;  

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
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
import com.extjs.gxt.ui.client.widget.Popup;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SLDEditorRuleForBackgrounds extends SLDEditorRule {

	static private SLDEditorWindow window;
	
	private TextField<String> intervals;
	
	private TextField<String> from;
	
	private TextField<String> to;
	
	private HTML minColor;
	
	private HTML maxColor;
	
	private HTML intervalColor;
	
	private ListBox polygonAlgorithmListBox;
	
	private ListBox extremeValuesListBox;
	
	private CheckBox fillPolygons;

	private Button createIntervalsButton;
	
	private Popup intervalsOfClassification; 
	
	private boolean createIntervals;
	
	private static List<Interval> rules;
	
	//private double rulesId;
		
	public SLDEditorRuleForBackgrounds(SLDEditorBackground sldEditorBackground) {
		minColor = new HTML("<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>");
		maxColor = new HTML("<div align='center' style='background-color: #009530; color: white; font-weight: bold; font-style: italic;'>#009530</div>");
		intervals = new TextField<String>();
		from = new TextField<String>();
		to = new TextField<String>();
		polygonAlgorithmListBox = new ListBox();
		extremeValuesListBox = new ListBox();
		fillPolygons = new CheckBox();
		this.getActionPanel().add(buildFillPolygonsPanel(sldEditorBackground));
		intervalsOfClassification = new Popup();
		createIntervals = false;
	}
	
	public SLDEditorRuleForBackgrounds(SLDEditorBackground sldEditorBackground, boolean dataOn) {
		minColor = new HTML("<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>");
		maxColor = new HTML("<div align='center' style='background-color: #009530; color: white; font-weight: bold; font-style: italic;'>#009530</div>");
		intervals = new TextField<String>();
		from = new TextField<String>();
		to = new TextField<String>();
		polygonAlgorithmListBox = new ListBox();
		extremeValuesListBox = new ListBox();
		fillPolygons = new CheckBox();
		//this.getActionPanel().add(buildFillPolygonsPanel(sldEditorBackground));
		intervalsOfClassification = new Popup();
		createIntervals = false;
	}
	
	//public void clearEqualInterval(boolean area)
	public void clearEqualInterval()
	{
		getLabelPropertyNameIntervalField().setValue("");
//		if(area)
//		{
//			getLabelPropertyNameIntervalField().setReadOnly(true);
//		}
//		else
//		{
			getLabelPropertyNameIntervalField().setReadOnly(false);
//		}
		intervals.setValue("");
		from.setValue("");
		to.setValue("");
		minColor.setHTML("<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>");
		maxColor.setHTML("<div align='center' style='background-color: #009530; color: white; font-weight: bold; font-style: italic;'>#009530</div>");
	}	
	
	public static List<Interval> getRules() {
		return rules;
	}

	public static void setRules(List<Interval> rules) {
		SLDEditorRuleForBackgrounds.rules = rules;
	}

	public HorizontalPanel buildFillPolygonsPanel(SLDEditorBackground sldEditorBackground) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		fillPolygons.setBoxLabel("Fill Polygons");
		fillPolygons.addListener(Events.OnClick, SLDEditorController.fillPolygons(this, sldEditorBackground));
		p.add(fillPolygons);
		removableCheckbox.add(p);
		return p;
	}
	
	public HorizontalPanel buildToPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html toLabel = new Html("<b>To Value</b>");
		toLabel.setWidth(LABEL_WIDTH);
		toLabel.setHeight("25px");
		p.add(toLabel);
		to.setWidth(MEDIUM_FIELD_WIDTH);
		to.setEmptyText("e.g. 370");
		p.add(to);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFromPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fromLabel = new Html("<b>From Value</b>");
		fromLabel.setWidth(LABEL_WIDTH);
		fromLabel.setHeight("25px");
		p.add(fromLabel);
		from.setWidth(MEDIUM_FIELD_WIDTH);
		from.setEmptyText("e.g. 125");
		p.add(from);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildIntervalsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html intervalsLabel = new Html("<b>Intervals</b>");
		intervalsLabel.setWidth(LABEL_WIDTH);
		intervalsLabel.setHeight("25px");
		p.add(intervalsLabel);
		intervals.setWidth(MEDIUM_FIELD_WIDTH);
		intervals.setEmptyText("e.g. 8");
		p.add(intervals);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildMinColorPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html minColorLabel = new Html("<b>Starting Color</b>");
		minColorLabel.setWidth(LABEL_WIDTH);
		minColorLabel.setHeight("25px");
		p.add(minColorLabel);
		minColor.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(minColor);
		minColor.addClickHandler(SLDEditorController.colorPicker(minColor));
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildMaxColorPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html maxColorLabel = new Html("<b>Ending Color</b>");
		maxColorLabel.setWidth(LABEL_WIDTH);
		maxColorLabel.setHeight("25px");
		p.add(maxColorLabel);
		maxColor.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(maxColor);
		maxColor.addClickHandler(SLDEditorController.colorPicker(maxColor));
		removablePanels.add(p);
		return p;
	}
	
	public Popup buildIntervalOfClassificatioPanel() {
		return intervalsOfClassification;
	}

	public HorizontalPanel buildPolygonAlgorithmPanel(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground) {
		return buildPolygonAlgorithmPanel(r, sldEditorBackground, null);
	}
	
	public HorizontalPanel buildPolygonAlgorithmPanel(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground, SLDEditorRuleVO data) {
		HorizontalPanel p = null;
		if(data == null)
		{
			p = new HorizontalPanel();
			p.setId("PolygonAlgorithmPanel");
			p.setSpacing(SPACING);
			p.setVerticalAlign(VerticalAlignment.MIDDLE);
			Html poligonAlgorithmLabel = new Html("<b>Intervals Algorithm</b>");
			poligonAlgorithmLabel.setWidth(LABEL_WIDTH);
			poligonAlgorithmLabel.setHeight("25px");
			p.add(poligonAlgorithmLabel);
			polygonAlgorithmListBox.clear();
			polygonAlgorithmListBox.addItem("", "");
			polygonAlgorithmListBox.addItem("Equal Interval", "equalIntervalValue");
			polygonAlgorithmListBox.addItem("Equal Area", "equalAreaValue");
			polygonAlgorithmListBox.setWidth(MEDIUM_FIELD_WIDTH);
			polygonAlgorithmListBox.addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent arg0) {
					int index = polygonAlgorithmListBox.getSelectedIndex();
					if (index != -1) {
						String selectedItem = (String) polygonAlgorithmListBox
								.getItemText(index);
						if (selectedItem.equalsIgnoreCase("Equal Interval")) {
							// Equal Interval Algorithm
							polygonAlgorithmListBox.clear();
							SLDEditorController.fillEqualInterval(r,
									sldEditorBackground);
						} else if (selectedItem.equalsIgnoreCase("Equal Area")) {
							// Equal Area Algorithm
							polygonAlgorithmListBox.clear();
							SLDEditorController.fillEqualArea(r,
									sldEditorBackground);
						}
					}
				}
			});
			p.add(polygonAlgorithmListBox);
			removablePanels.add(p);
		}
		else
		{
			if(data.getLabelIntervalPropertyName().equalsIgnoreCase("AREA"))
			{
				//Calling Equal area method
				SLDEditorController.fillEqualArea(r, sldEditorBackground, data);
			}
			else
			{
				//Calling Equal interval method
				SLDEditorController.fillEqualInterval(r, sldEditorBackground, data);
			}
		}
		return p;
	}

	public HorizontalPanel buildExtremeValuesPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html extremeValuesLabel = new Html("<b>Extreme Values</b>");
		extremeValuesLabel.setWidth(LABEL_WIDTH);
		extremeValuesLabel.setHeight("25px");
		p.add(extremeValuesLabel);
		extremeValuesListBox.clear();
		extremeValuesListBox.addItem("Lower Values Included", "lowerValuesIncluded");
		extremeValuesListBox.addItem("Higher Values Included", "higherValuesIncluded");
		extremeValuesListBox.addItem("Both Extreme Values Included", "bothValuesIncluded");
		extremeValuesListBox.addItem("Both Extreme Values Excluded", "bothValuesExcluded");
		extremeValuesListBox.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(extremeValuesListBox);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildCreateIntervalsButtonPanel(int algorithm) {
		return buildCreateIntervalsButtonPanel(algorithm, null);
	}
	
	public HorizontalPanel buildCreateIntervalsButtonPanel(int algorithm, SLDEditorRuleVO data) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		if(data == null)
		{
			this.createIntervalsButton = new Button("Create Intervals", SLDEditorController.createIntevals(this, algorithm));
		}
		else 
		{
			this.createIntervalsButton = new Button("Create Intervals", SLDEditorController.createIntevals(this, algorithm, data));
		}
		createIntervalsButton.setWidth(BUTTON_WIDTH);
		p.add(createIntervalsButton);
		removablePanels.add(p);
		return p;
		
	}

	public ContentPanel buildIntervalsGridPanel(final SLDEditorRuleForBackgrounds r, List<Interval> rulesPar) {	
		GridView gridView = new GridView();
		final CheckBoxSelectionModel<Interval> sm = new CheckBoxSelectionModel<Interval>();
		final ListStore<Interval> store = new ListStore<Interval>();
		store.add(rulesPar);
		
		 List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		 configs.add(sm.getColumn()); 
		 
		    ColumnConfig column = new ColumnConfig();  
		     column.setId("minValue");  
		     column.setHeader("Minor Value");  
		     column.setAlignment(HorizontalAlignment.CENTER);  
		     column.setWidth(150);  
		     column.setNumberFormat(NumberFormat.getDecimalFormat());  
		     column.setEditor(new CellEditor(new NumberField()));  
		     
		     configs.add(column); 

		     column = new ColumnConfig();  
		     column.setId("maxValue");  
		     column.setHeader("Maximum Value");  
		     column.setAlignment(HorizontalAlignment.CENTER);  
		     column.setWidth(150);  
		     column.setNumberFormat(NumberFormat.getDecimalFormat());  
		     column.setEditor(new CellEditor(new NumberField()));  
		     
		     configs.add(column); 

			 column = new ColumnConfig();  
			 column.setId("color");  
			 column.setHeader("Color (Use Color Picker to change.)");  
			 column.setWidth(100);  
			 column.setAlignment(HorizontalAlignment.CENTER);  
			 TextField<String> text = new TextField<String>();    
			 text.setAllowBlank(false);    
			 text.setAutoValidate(true);    
			
			 column.setRenderer(new GridCellRenderer<Interval>() {
				
				    public Object render(Interval model, String property,
				                                    ColumnData config,
				               int rowIndex, int colIndex,
				               ListStore<Interval> store,
				               Grid<Interval> grid) {
				  
				          Object value = model.get(property);    
				          String colorM = model.getColor().trim();
				          if(colorM.contains("#"))
				          {
				        	 colorM = colorM.substring(1);
				          }
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
			 
			 column.setEditor(new CellEditor(text));
			 configs.add(column);
		     ColumnModel cm = new ColumnModel(configs);  
	
		     ContentPanel cp = new ContentPanel();
		     cp.setHeading("Intervals of the classification");  
		     cp.setFrame(true);  
		     cp.setSize(550, 300);  
		     cp.setLayout(new FitLayout());  
		     
		     ToolBar toolBarUp = new ToolBar();  
		     Button add = new Button("Close the window of the classification.");
		     add.addSelectionListener(new SelectionListener<ButtonEvent>() {  
		    	 @Override  
			     public void componentSelected(ButtonEvent ce) {
		    		 store.commitChanges();
		    		 List<Interval> listInterval = store.getModels();
		    		 
		    		 if(rules != null)
		    		 {
		    			 rules.clear();
		    			 rules = new ArrayList<Interval>();
		    		 }
		    		 for(int i=0; i<listInterval.size();i++)
		    		 {
		    			 rules.add(new Interval(listInterval.get(i).getMinValue(),listInterval.get(i).getMaxValue(),listInterval.get(i).getColor()));
		    		 }
		    		 SLDEditorController.IntervalsOfClasHide(r);
		    	 }
		     });
		     toolBarUp.setAlignment(HorizontalAlignment.CENTER);
		     toolBarUp.add(add);  
		     cp.setTopComponent(toolBarUp);
		     final RowEditor<Interval> re = new RowEditor<Interval>();  
		     final Grid<Interval> grid = new Grid<Interval>(store, cm);  
		     
		     grid.setView(gridView);		     
		     grid.setSelectionModel(sm);  
		     grid.setAutoExpandColumn("minValue");
		     grid.setAutoExpandColumn("maxValue");
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
		     
		     cp.addButton(new Button("Add Interval", new SelectionListener<ButtonEvent>() {  
		   
		       @Override  
		       public void componentSelected(ButtonEvent ce) {  
		         Interval interval = new Interval();
		         interval.setMinValue(0);
		         interval.setMaxValue(0);
		         interval.setColor("New Color");
		         re.stopEditing(false);  
			     store.insert(interval, 0);  
			     re.startEditing(store.indexOf(interval), true);  
		       }  
		     }));      
		   
		     cp.addButton(new Button("Remove Selected Interval", new SelectionListener<ButtonEvent>() {  
		   
		       @Override  
		       public void componentSelected(ButtonEvent ce) {  
		    	   List<Interval> toDelete = grid.getSelectionModel().getSelectedItems();
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
		     removableContentPanels.add(cp);
		return cp;
	}
	
	public TextField<String> getIntervals() {
		return intervals;
	}

	public TextField<String> getFrom() {
		return from;
	}

	public TextField<String> getTo() {
		return to;
	}

	public HTML getMinColor() {
		return minColor;
	}

	public HTML getMaxColor() {
		return maxColor;
	}

	public ListBox getPolygonAlgorithmListBox() {
		return polygonAlgorithmListBox;
	}
	
	public ListBox getExtremeValuesListBox() {
		return extremeValuesListBox;
	}
	
	public CheckBox getFillPolygons() {
		return fillPolygons;
	}
	
	public void clearPolygonAlgorithmListBox()
	{
		this.polygonAlgorithmListBox.clear();
	}
	
	public void clearExtremeValuesListBox()
	{
		this.extremeValuesListBox.clear();
	}
		
	public Popup getIntervalsOfClassification() {
		return intervalsOfClassification;
	}

	public void setIntervalsOfClassification(Popup intervalsOfClassification) {
		this.intervalsOfClassification = intervalsOfClassification;
	}
	
	public static SLDEditorWindow getWindow() {
		return window;
	}

	public static void setWindow(SLDEditorWindow window) {
		SLDEditorRuleForBackgrounds.window = window;
	}
	
	public boolean isCreateIntervals() {
		return createIntervals;
	}

	public void setCreateIntervals(boolean createIntervals) {
		this.createIntervals = createIntervals;
	}
}