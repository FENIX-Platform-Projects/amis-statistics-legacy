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
package org.fao.fenix.web.modules.sldeditor.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.utils.ColorPickerCaller;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorBackground;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorLabel;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorLine;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorRaster;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorRule;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorRuleForBackgrounds;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorRuleForLabels;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorRuleForLines;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorRuleForRaster;
import org.fao.fenix.web.modules.sldeditor.client.view.SLDEditorWindow;
import org.fao.fenix.web.modules.sldeditor.common.services.SLDEditorServiceEntry;
import org.fao.fenix.web.modules.sldeditor.common.vo.ColorMapBigGrid;
import org.fao.fenix.web.modules.sldeditor.common.vo.ColorMapSmallGrid;
import org.fao.fenix.web.modules.sldeditor.common.vo.Interval;
import org.fao.fenix.web.modules.sldeditor.common.vo.RGBContrast;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorConstants;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;
import org.fao.fenix.web.modules.sldeditor.common.vo.SldEditorUtils;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Rectangle;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Popup;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

@SuppressWarnings("deprecation")
public class SLDEditorController {

	public static SelectionListener<ButtonEvent> createSLD(final SLDEditorWindow w, final int algorithm) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<SLDEditorRuleVO> rules = collectRules(w, algorithm);
				String sldName = w.getSldEditorPanel().getSldEditorName().getStyleNameField().getValue();
				String sldDescription = w.getSldEditorPanel().getSldEditorName().getDescriptionArea().getValue();
				if(sldName == null)
				{
					sldName = "";
				}
				if(sldDescription == null)
				{
					sldDescription = "";
				}
				if (isValid(w)) {
					final LoadingWindow l = new LoadingWindow("SLD Editor", "Creating the SLD File", "Please wait...");
					try {
							SLDEditorServiceEntry.getInstance().createSLD(sldName, sldDescription, rules, new AsyncCallback<String[]>() {
								public void onSuccess(String[] sldInfo) {
									l.destroyLoadingBox();
									Window.open("../exportObject/" + sldInfo[0], "", "");
									SldEditorUtils.setSldNameFile(sldInfo[0]);
									SldEditorUtils.setSldPathFile(sldInfo[1]);
									l.destroyLoadingBox();
								}
							public void onFailure(Throwable e) {
								l.destroyLoadingBox();
								FenixAlert.error("ERROR", e.getMessage());
								l.destroyLoadingBox();
							}
						});
					} catch (FenixGWTException e) {
						FenixAlert.error("ERROR", e.getMessage());
					}
				} else {
					FenixAlert.info("INFO", "Please check your parameters.");
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> createSLDRaster(final SLDEditorWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<SLDEditorRuleVO> rules = collectRulesRaster(w);
				String sldName = w.getSldEditorPanel().getSldEditorName().getStyleNameField().getValue();
				String sldDescription = w.getSldEditorPanel().getSldEditorName().getDescriptionArea().getValue();
				if (isValid(w)) {
					final LoadingWindow l = new LoadingWindow("SLD Editor", "Creating the SLD File", "Please wait...");
					try {
							SLDEditorServiceEntry.getInstance().createSLDRaster(sldName, sldDescription, rules, new AsyncCallback<String[]>() {
								public void onSuccess(String[] sldInfo) {
									l.destroyLoadingBox();
									Window.open("../exportObject/" + sldInfo[0], "", "");
									SldEditorUtils.setSldNameFile(sldInfo[0]);
									SldEditorUtils.setSldPathFile(sldInfo[1]);
									l.destroyLoadingBox();
								}
							public void onFailure(Throwable e) {
								l.destroyLoadingBox();
								FenixAlert.error("ERROR", e.getMessage());
								l.destroyLoadingBox();
							}
						});
					} catch (FenixGWTException e) {
						FenixAlert.error("ERROR", e.getMessage());
					}
				} else {
					FenixAlert.info("INFO", "Please check your parameters.");
				}
			}
		};
	}
	
	public static boolean isValid(SLDEditorWindow w) {
//		if (!w.getSldEditorPanel().getSldEditorName().getStyleNameField().isValid())
//			return false;
//		if (!w.getSldEditorPanel().getSldEditorLine().getRules().isEmpty())
//			return false;
//		for (SLDEditorRuleForLines lr : w.getSldEditorPanel().getSldEditorLine().getRules()) {
//			if (!lr.getRuleNameField().isValid())
//				return false;
//			if (lr.getStrokeColor().getText().equals(""))
//				return false;
//			if (!lr.getStrokeWidth().isValid())
//				return false;
//			if (lr.getAddTextSymbolizer().getValue()) {
//				if (!lr.getPropertyNameField().isValid())
//					return false;
//				if (lr.getAddFilter().getValue()) {
//					if (!lr.getPropertyValueField().isValid())
//						return false;
//				}
//			}
//		}
		return true;
	}
	
	public static String colorPicker2Hex(String rgb) {
		String hex = "";
		int idx_1 = rgb.indexOf('_');
		int red = Integer.valueOf(rgb.substring(0, idx_1));
		rgb = rgb.substring(1 + idx_1);
		int idx_2 = rgb.indexOf('_');
		int green = Integer.valueOf(rgb.substring(0, idx_2));
		int blue = Integer.valueOf(rgb.substring(1 + idx_2));
		if (red < 10)
			hex += "0";
		hex += Integer.toHexString(red).toUpperCase();
		if (green < 10)
			hex += "0";
		hex += Integer.toHexString(green).toUpperCase();
		if (blue < 10)
			hex += "0";
		hex += Integer.toHexString(blue).toUpperCase();
		return hex;
	}
	
	public static List<SLDEditorRuleVO> collectRules(SLDEditorWindow w, int algorithm) {
		int indexSelected =0; 
		List<SLDEditorRuleVO> vos = new ArrayList<SLDEditorRuleVO>();
		for (SLDEditorRuleForBackgrounds br : w.getSldEditorPanel().getSldEditorBackground().getRules()) {
			if (br.getFillPolygons().getValue()) {
				SLDEditorRuleVO vo = new SLDEditorRuleVO();
				if(!(br.isCreateIntervals()))
				{
				String min = br.getFrom().getValue();
				String max = br.getTo().getValue();
				String minColor = br.getMinColor().getText();
				String maxColor = br.getMaxColor().getText();
				int intervals = Integer.valueOf(br.getIntervals().getValue());
				Map<String, String> fillParameters = SLDEditorColorRampMaker.ramp(min, max, minColor, maxColor, intervals, algorithm);
				vo.setFillParameters(fillParameters);
				vo.setCreateInterval(false);
				}
				else 
				{
					vo.fillParametersIntervals = new ArrayList<Interval>();
					for(int i=0; i<SLDEditorRuleForBackgrounds.getRules().size();i++)
		    		 {
		    			 vo.getFillParametersIntervals().add(new Interval(SLDEditorRuleForBackgrounds.getRules().get(i).getMinValue(),SLDEditorRuleForBackgrounds.getRules().get(i).getMaxValue(),SLDEditorRuleForBackgrounds.getRules().get(i).getColor()));
		    		 }
					vo.setCreateInterval(true);
				}
				indexSelected = br.getExtremeValuesListBox().getSelectedIndex();
				if(indexSelected == -1)
				{
					indexSelected = 0;
				}
				vo.setExtremeValues(br.getExtremeValuesListBox().getValue(indexSelected));
				vo.setType(SLDEditorConstants.BACKGROUND);
				vo.setLabelIntervalPropertyName(br.getLabelPropertyNameIntervalField().getValue());
				if (br.getAddFilter().getValue()) {
					vo.setFilterPropertyName(br.getFilterPropertyNameField().getValue());
					vo.setFilterPropertyValue(br.getFilterPropertyValueField().getValue());
					if (br.getIsListBox().getSelectedIndex() == 0)
						vo.setIs(true);
					else
						vo.setIs(false);
					vo.setComparison(SLDEditorConstants.valueOf(br.getComparisonListBox().getValue((br.getComparisonListBox().getSelectedIndex()==-1?0:br.getComparisonListBox().getSelectedIndex()))));
				}
				vos.add(vo);
			}
		}
		for (SLDEditorRuleForLines lr : w.getSldEditorPanel().getSldEditorLine().getRules()) {
			if(lr != null)
			{
				SLDEditorRuleVO vo = new SLDEditorRuleVO();
				if(lr.getStrokeColor() != null)
				{
					vo.getStrokeParameters().put("stroke", lr.getStrokeColor().getText());
				}
				if(lr.getStrokeWidth() != null)
				{
					if(lr.getStrokeWidth().getValue() != null)
					{
						if (!lr.getStrokeWidth().getValue().equals(""))
							vo.getStrokeParameters().put("stroke-width", lr.getStrokeWidth().getValue());
						else
							vo.getStrokeParameters().put("stroke-width", "1");
						}
					}
				if (lr.getLineStyleListBox().getSelectedIndex() > 0)
					vo.getStrokeParameters().put("stroke-dasharray", "5 2");
				vo.setType(SLDEditorConstants.LINE);
				if (lr.getAddFilter().getValue()) {
					vo.setFilterPropertyName(lr.getFilterPropertyNameField().getValue());
					vo.setFilterPropertyValue(lr.getFilterPropertyValueField().getValue());
					if (lr.getIsListBox().getSelectedIndex() == 0)
						vo.setIs(true);
					else
						vo.setIs(false);
					vo.setComparison(SLDEditorConstants.valueOf(lr.getComparisonListBox().getValue((lr.getComparisonListBox().getSelectedIndex()==-1?0:lr.getComparisonListBox().getSelectedIndex()))));
				}
				vos.add(vo);
			}
		}
		for (SLDEditorRuleForLabels lr : w.getSldEditorPanel().getSldEditorLabel().getRules()) {
			if(lr != null)
			{
				if(lr.getStyleLabels() != null)
				{
					if (lr.getStyleLabels().getValue()) {
						SLDEditorRuleVO vo = new SLDEditorRuleVO();
						vo.setType(SLDEditorConstants.LABEL);
						vo.setLabelPropertyName(lr.getLabelPropertyNameField().getValue());
						vo.getFontParameters().put("font-family", lr.getFontFamilyListBox().getValue((lr.getFontFamilyListBox().getSelectedIndex() == -1?0:lr.getFontFamilyListBox().getSelectedIndex())));
						vo.getFontParameters().put("font-size", lr.getFontSizeTextField().getValue());
						vo.getFontParameters().put("font-weight", lr.getFontWeightListBox().getValue((lr.getFontWeightListBox().getSelectedIndex()==-1?0:lr.getFontWeightListBox().getSelectedIndex())));
						vo.getFontParameters().put("font-style", lr.getFontStyleListBox().getValue((lr.getFontStyleListBox().getSelectedIndex()==-1?0:lr.getFontStyleListBox().getSelectedIndex())));
						vo.getFontParameters().put("fill", lr.getFillColor().getText());
						vo.getFontParameters().put("fill-opacity", String.valueOf(Double.valueOf(lr.getFillOpacity().getValue()) / 100));
						if (lr.getAddFilter().getValue()) {
							vo.setFilterPropertyName(lr.getFilterPropertyNameField().getValue());
							vo.setFilterPropertyValue(lr.getFilterPropertyValueField().getValue());
							if (lr.getIsListBox().getSelectedIndex() == 0)
								vo.setIs(true);
							else
								vo.setIs(false);
							vo.setComparison(SLDEditorConstants.valueOf(lr.getComparisonListBox().getValue((lr.getComparisonListBox().getSelectedIndex()==-1?0:lr.getComparisonListBox().getSelectedIndex()))));
						}
						vos.add(vo);
					}
				}
			}
		}
		return vos;
	}
	
	public static List<SLDEditorRuleVO> collectRulesRaster(SLDEditorWindow w) {
		List<SLDEditorRuleVO> vos = new ArrayList<SLDEditorRuleVO>();
		for (SLDEditorRuleForRaster br : w.getSldEditorPanel().getSldEditorRaster().getRules()) {
			if(br != null)
			{
				SLDEditorRuleVO vo = new SLDEditorRuleVO();
				
				if(br.getSliderOpacityFormat().getValue())
				{
					vo.getSliderOpacityOnlyMap().put("fill-opacity", String.valueOf(Double.valueOf(br.getFillOpacityOnly().getValue()) / 100));
				}
				if(br.getFormatColorMap().getValue())
				{
					vo.setColorMapSelected(true);
					vo.getColorMapItem().put("type-attribute", br.getColorMapTypeListBox().getValue((br.getColorMapTypeListBox().getSelectedIndex()==-1?0:br.getColorMapTypeListBox().getSelectedIndex())));
					vo.getColorMapItem().put("extended-attribute", br.getColorMapExtendedListBox().getValue((br.getColorMapExtendedListBox().getSelectedIndex() == -1?0:br.getColorMapExtendedListBox().getSelectedIndex())));
					int selectedOne = br.getOpacityForEachIntervalListBox().getSelectedIndex();
					selectedOne = (selectedOne == -1?0:selectedOne);
					vo.getColorMapItem().put("color-map-items", br.getOpacityForEachIntervalListBox().getValue(selectedOne));
					if(selectedOne == 1)
					{
						vo.setColorMapGlobalOpacity(true);
						if(SLDEditorRuleForRaster.getRulesColorMap() != null)
						{
							vo.itemsSmallGrid = new ArrayList<ColorMapSmallGrid>();
							for(int i=0; i<SLDEditorRuleForRaster.getRulesColorMap().size();i++)
				    		 {
								vo.getItemsSmallGrid().add(new ColorMapSmallGrid(SLDEditorRuleForRaster.getRulesColorMap().get(i).getquantity(),SLDEditorRuleForRaster.getRulesColorMap().get(i).getlabel(),SLDEditorRuleForRaster.getRulesColorMap().get(i).getColor()));
				    		 }
						}
						vo.getColorMapItem().put("fill-opacity", String.valueOf(Double.valueOf(br.getFillOpacityOnly().getValue()) / 100));
					}
					else if(selectedOne == 2)
					{
						if(SLDEditorRuleForRaster.getBigRrules() != null)
						{
							vo.itemsBigGrid = new ArrayList<ColorMapBigGrid>();
							for(int i=0; i<SLDEditorRuleForRaster.getBigRrules().size();i++)
				    		 {
								vo.getItemsColorMapBigGrids().add(new ColorMapBigGrid(SLDEditorRuleForRaster.getBigRrules().get(i).getquantity(),SLDEditorRuleForRaster.getBigRrules().get(i).getlabel(),SLDEditorRuleForRaster.getBigRrules().get(i).getColor(), SLDEditorRuleForRaster.getBigRrules().get(i).getopacity())); 
							 }
						}
					}
				}
				if(br.getSelectionChannelFormat().getValue())
				{
					int selectedTwo = br.getSelectionChannelTypeListBox().getSelectedIndex();
					selectedTwo = (selectedTwo == -1?0:selectedTwo);
					vo.getChannelSelectionMap().put("type-attribute", br.getSelectionChannelTypeListBox().getValue(selectedTwo));
					if(selectedTwo == 1)
					{
						vo.getChannelSelectionMap().put("red-channel-name", br.getRedChannel().getValue());
						vo.getChannelSelectionMap().put("green-channel-name", br.getGreenChannel().getValue());
						vo.getChannelSelectionMap().put("blue-channel-name", br.getBlueChannel().getValue());
					}
					else if(selectedTwo == 2)
					{
						vo.getChannelSelectionMap().put("gray-channel-name", br.getGrayChannel().getValue());
					}
				}
				if(br.getContrastEnhancementFormat().getValue())
				{
					int selectedThree = br.getContrastEnhancementTypeListBox().getSelectedIndex();
					selectedThree = (selectedThree == -1?0:selectedThree);
					vo.getContrastEnhancementMap().put("contrast-type", br.getContrastEnhancementTypeListBox().getValue(selectedThree));
					if(selectedThree == 1)
					{
						int selectedFour = br.getGlobalContrastListBox().getSelectedIndex();
						selectedFour = (selectedFour==-1?0:selectedFour);
						vo.getContrastEnhancementMap().put("global-contrast-value", br.getGlobalContrastListBox().getValue(selectedFour));
						if(selectedFour == 2)
						{
							vo.getContrastEnhancementMap().put("gamma-value", br.getGammaValue().getValue());
						}
					}
					else if((selectedThree == 2)||(selectedThree == 3))
					{
						vo.rgbcontrast = new ArrayList<RGBContrast>();
						for(int i=0; i<SLDEditorRuleForRaster.getRedGreenBluerules().size();i++)
			    		 {
							vo.getRgbcontrast().add(new RGBContrast(SLDEditorRuleForRaster.getRedGreenBluerules().get(i).getnameChannel(),SLDEditorRuleForRaster.getRedGreenBluerules().get(i).gettypeChannelContrast(),SLDEditorRuleForRaster.getRedGreenBluerules().get(i).getgammaValueContrast())); 
						 }
					}
				}
				vos.add(vo);
			}	
		}
		return vos;
	}
	
	
	public static List<Interval> collectBackgroundRules(SLDEditorWindow w, int algorithm) {
		List<Interval> intervalList = null;
		for (SLDEditorRuleForBackgrounds br : w.getSldEditorPanel().getSldEditorBackground().getRules()) {
			if (br.getFillPolygons().getValue()) {
				String min = br.getFrom().getValue();
				String max = br.getTo().getValue();
				String minColor = br.getMinColor().getText();
				String maxColor = br.getMaxColor().getText();
				int intervals = Integer.valueOf(br.getIntervals().getValue());
				intervalList = SLDEditorColorRampMaker.rampCreateIntervals(min, max, minColor, maxColor, intervals, algorithm, intervalList);
			}
		}		
		return intervalList;
	}
	
	public static ClickHandler colorPicker(final HTML html) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				ColorPicker cp = new ColorPicker(html);
				cp.build(ColorPickerCaller.SLD_EDITOR);	
			}
		};
	}
	
	public static void colorPickerListener(final HTML html) {
		ColorPicker cp = new ColorPicker(html);
		cp.buildColorInterval(ColorPickerCaller.SLD_EDITOR);
	}
	
	public static Listener<BaseEvent> fillPolygons(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (r.getFillPolygons().getValue()) {
					r.setCreateIntervals(false);
					r.getActionPanel().add(r.buildPolygonAlgorithmPanel(r, sldEditorBackground));
					r.getActionPanel().getLayout().layout();
				} else {
					r.setCreateIntervals(false);
					int i=0;
					for (HorizontalPanel p : r.getRemovablePanels())
					{
						r.getActionPanel().remove(p);
					}
					for (ContentPanel cp : r.getRemovableContentPanels())
						r.getActionPanel().remove(cp);
					removeFilters(r);
					r.getActionPanel().getLayout().layout();
					r.getRemovablePanels().clear();
					r.getRemovableContentPanels().clear();
				}
			};
		};
	}
	
	public static void fillEqualInterval(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground)
	{	
		fillEqualInterval(r, sldEditorBackground, null);
	}
	
	public static void fillEqualInterval(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground, SLDEditorRuleVO data)
	{	
		if(data == null)
		{
			r.clearEqualInterval();
			r.clearPolygonAlgorithmListBox();
			r.clearExtremeValuesListBox();
			removeFilters(r);
			HorizontalPanel toBeRemoved = null;
			for (HorizontalPanel p : r.getRemovablePanels())
			{
					if((p!= null)&&(p.getId().equalsIgnoreCase("PolygonAlgorithmPanel")))
					{
						try
						{
						if(r.getActionPanel() != null)
						{
							r.getActionPanel().remove(p);
							toBeRemoved = p;
						}
						}
						catch(RuntimeException ex)
						{
							//System.out.println("Handle Event Equal Interval Pannello non rimosso eccezione");
						}
						catch(Exception exg)
						{
							//System.out.println("Handle Event Equal Interval Pannello non rimosso eccezione");
						}
					}
			}
			if(toBeRemoved != null)
			{
				r.getRemovablePanels().remove(toBeRemoved);
			}
		}
		r.getActionPanel().add(r.buildTitleButtonPanel("Equal Interval"));
		r.getActionPanel().add(r.buildLabelPropertyNameAreaPanel());
		r.getActionPanel().add(r.buildIntervalsPanel());
		r.getActionPanel().add(r.buildFromPanel());
		r.getActionPanel().add(r.buildToPanel());
		r.getActionPanel().add(r.buildMinColorPanel());
		r.getActionPanel().add(r.buildMaxColorPanel());
		r.getActionPanel().add(r.buildExtremeValuesPanel());
		if(data == null)
		{
			r.getActionPanel().add(r.buildCreateIntervalsButtonPanel(0));
			SLDEditorWindow.setAlgorithm(0);
			r.getActionPanel().add(r.buildAddFilter());	
			r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
		}
		else
		{
			r.getActionPanel().add(r.buildCreateIntervalsButtonPanel(0, data));
			SLDEditorWindow.setAlgorithm(0);
			if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0)
			{
				r.getActionPanel().add(r.buildAddFilter(data));	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
				r.addFilter(data);
			}
			else
			{
				r.getActionPanel().add(r.buildAddFilter());	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
			}
		}
		r.getActionPanel().add(r.buildButtonsPanel());
		r.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorBackground));
		if(data == null)
		{
			r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorBackground));
		}
		else
		{
			r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorBackground, data));
		}
		if(data != null)
		{
			String app = data.getLabelIntervalPropertyName();
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			r.getLabelPropertyNameIntervalField().setValue(app);
			
			if((data.getFillParametersIntervals() != null)&&(data.getFillParametersIntervals().size() >0))
			{
				r.getIntervals().setValue(data.getFillParametersIntervals().size() + "");
			}
			else
			{
				r.getIntervals().setValue("");
			}			
			app = data.getMinTot() +"";
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			r.getFrom().setValue(app);
			
			app = data.getMaxTot() +"";
			if((app ==null)||(app.length()==0)||(app.equals("-1"))||(app.equals("-1.0")))
			{
				app = "";
			}
			r.getTo().setValue(app);
			app = data.getMinColor();
			if((app ==null)||(app.length()==0))
			{
				app = "<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>";
			}
			else
			{
				app = "<div align='center' style='background-color:"+ app+"; color: white; font-weight: bold; font-style: italic;'>"+ app+"</div>";
			}
			r.getMinColor().setHTML(app);
			
			app = data.getMaxColor();
			if((app ==null)||(app.length()==0))
			{
				app = "<div align='center' style='background-color: #009530; color: white; font-weight: bold; font-style: italic;'>#009530</div>";
			}
			else
			{
				app = "<div align='center' style='background-color:"+ app+"; color: white; font-weight: bold; font-style: italic;'>"+ app+"</div>";
			}
			r.getMaxColor().setHTML(app);
		} 
		else
		{
			r.getActionPanel().getLayout().layout();
		}
	}
	
	public static void fillEqualArea(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground)
	{	
		fillEqualArea(r, sldEditorBackground, null);
	}
	
	public static void fillEqualArea(final SLDEditorRuleForBackgrounds r, final SLDEditorBackground sldEditorBackground, SLDEditorRuleVO data)
	{
		if(data == null)
		{
			r.clearEqualInterval();
			r.clearPolygonAlgorithmListBox();
			r.clearExtremeValuesListBox();			
			removeFilters(r);
			HorizontalPanel toBeRemoved = null;
			for (HorizontalPanel p : r.getRemovablePanels())
			{
					if((p!= null)&&(p.getId().equalsIgnoreCase("PolygonAlgorithmPanel")))
					{
						toBeRemoved = p;
						r.getActionPanel().remove(p);
					}
			}
			if(toBeRemoved != null)
			{
				r.getRemovablePanels().remove(toBeRemoved);
			}
		}
		r.getActionPanel().add(r.buildTitleButtonPanel("Equal Area"));
		r.getActionPanel().add(r.buildLabelPropertyNameAreaPanel());
		r.getActionPanel().add(r.buildIntervalsPanel());
		r.getActionPanel().add(r.buildFromPanel());
		r.getActionPanel().add(r.buildToPanel());
		r.getActionPanel().add(r.buildMinColorPanel());
		r.getActionPanel().add(r.buildMaxColorPanel());
		r.getActionPanel().add(r.buildExtremeValuesPanel());
			
		if(data == null)
		{
			r.getActionPanel().add(r.buildCreateIntervalsButtonPanel(1));
			SLDEditorWindow.setAlgorithm(1);
			r.getActionPanel().add(r.buildAddFilter());	
			r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
		}
		else
		{
			r.getActionPanel().add(r.buildCreateIntervalsButtonPanel(1, data));
			SLDEditorWindow.setAlgorithm(1);	
			if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0)
			{
				r.getActionPanel().add(r.buildAddFilter(data));	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
				r.addFilter(data);
			}
			else
			{
				r.getActionPanel().add(r.buildAddFilter());	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
			}
		}
		r.getActionPanel().add(r.buildButtonsPanel());
		r.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorBackground));
		
		if(data == null)
		{
			r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorBackground));
		}
		else
		{
			r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorBackground, data));
		}
		
		if(data != null)
		{
			String app = data.getLabelIntervalPropertyName();
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			r.getLabelPropertyNameIntervalField().setValue(app);
			if((data.getFillParametersIntervals() != null)&&(data.getFillParametersIntervals().size() >0))
			{
				r.getIntervals().setValue(data.getFillParametersIntervals().size() + "");
			}
			else
			{
				r.getIntervals().setValue("");
			}
			
			app = data.getMinTot() +"";
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			r.getFrom().setValue(app);
			
			app = data.getMaxTot() +"";
			if((app ==null)||(app.length()==0)||(app.equals("-1"))||(app.equals("-1.0")))
			{
				app = "";
			}
			r.getTo().setValue(app);
			app = data.getMinColor();
			if((app ==null)||(app.length()==0))
			{
				app = "<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>";
			}
			else
			{
				app = "<div align='center' style='background-color:"+ app+"; color: white; font-weight: bold; font-style: italic;'>"+ app+"</div>";
			}
			r.getMinColor().setHTML(app);
			
			app = data.getMaxColor();
			if((app ==null)||(app.length()==0))
			{
				app = "<div align='center' style='background-color: #009530; color: white; font-weight: bold; font-style: italic;'>#009530</div>";
			}
			else
			{
				app = "<div align='center' style='background-color:"+ app+"; color: white; font-weight: bold; font-style: italic;'>"+ app+"</div>";
			}
			r.getMaxColor().setHTML(app);
		} 
		else
		{
			r.getActionPanel().getLayout().layout();
		}
	}
	
	public static Listener<BaseEvent> formatLines(final SLDEditorRuleForLines r, final SLDEditorLine sldEditorLine) {
		return(formatLines(r, sldEditorLine, null));
	}
	
	public static Listener<BaseEvent> formatLines(final SLDEditorRuleForLines r, final SLDEditorLine sldEditorLine, final SLDEditorRuleVO data) {
		
		if(data != null)
		{
			r.getActionPanel().add(r.buildStrokeColorPanel());
			r.getActionPanel().add(r.buildStrokeWidthPanel());
			r.getActionPanel().add(r.buildLineStylePanel());
			
			if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0)
			{
				r.getActionPanel().add(r.buildAddFilter(data));	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
				r.addFilter(data);
			}
			else
			{
				r.getActionPanel().add(r.buildAddFilter());	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
			}
			r.getActionPanel().add(r.buildButtonsPanel());
			r.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorLine));
			r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorLine));
			
			String app= data.getStrokeParameters().get("stroke");
			if((app ==null)||(app.length()==0))
			{
				r.getStrokeColor().setHTML("<div align='center' style='background-color: #000000; color: white; font-weight: bold; font-style: italic;'>#000000</div>");
			}
			else
			{
				r.getStrokeColor().setHTML("<div align='center' style='background-color: "+app+"; color: white; font-weight: bold; font-style: italic;'> " +app+"</div>");
			}
			
			app = data.getStrokeParameters().get("stroke-width");
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			r.getStrokeWidth().setValue(app);
			
			app = data.getStrokeParameters().get("stroke-dasharray");
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			if(app.indexOf(' ') == -1)
			{
				r.getLineStyleListBox().setSelectedIndex(0);
			}
			else
			{
				r.getLineStyleListBox().setSelectedIndex(1);
			}		
			r.getFormatLines().setValue(true);
		}				
		
		Listener<BaseEvent> ris = new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (r.getFormatLines().getValue()){				
					r.getActionPanel().add(r.buildStrokeColorPanel());
					r.getActionPanel().add(r.buildStrokeWidthPanel());
					r.getActionPanel().add(r.buildLineStylePanel());					
					if(data == null)
					{
						r.getActionPanel().add(r.buildAddFilter());	
						r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
					}
					else
					{
						if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0)
						{
							r.getActionPanel().add(r.buildAddFilter(data));	
							r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
							r.addFilter(data);
						}
						else
						{
							r.getActionPanel().add(r.buildAddFilter());	
							r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
						}					
					}
					
					r.getActionPanel().add(r.buildButtonsPanel());
					r.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorLine));
					r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorLine));						

					if(data != null){
								
						String app= data.getStrokeParameters().get("stroke");
						if((app ==null)||(app.length()==0))
						{
							r.getStrokeColor().setHTML("<div align='center' style='background-color: #000000; color: white; font-weight: bold; font-style: italic;'>#000000</div>");
						}
						else
						{
							r.getStrokeColor().setHTML("<div align='center' style='background-color: "+app+"; color: white; font-weight: bold; font-style: italic;'> " +app+"</div>");
						}
						
						app = data.getStrokeParameters().get("stroke-width");
						if((app ==null)||(app.length()==0))
						{
							app = "";
						}
						r.getStrokeWidth().setValue(app);
						
						app = data.getStrokeParameters().get("stroke-dasharray");
						if((app ==null)||(app.length()==0))
						{
							app = "";
						}
						if(app.indexOf(' ') == -1)
						{
							r.getLineStyleListBox().setSelectedIndex(0);
						}
						else
						{
							r.getLineStyleListBox().setSelectedIndex(1);
						}	
						r.getFormatLines().setValue(true);
					}					
					r.getActionPanel().getLayout().layout();
				}
				else{
					removeFilters(r);
					for (HorizontalPanel p : r.getRemovablePanels())
						r.getActionPanel().remove(p);
					r.getActionPanel().getLayout().layout();
					r.getRemovablePanels().clear();
				}				
			};
		};
		
		return(ris);
	}	
	
	public static Listener<BaseEvent> styleLabels(final SLDEditorRuleForLabels r, final SLDEditorLabel sldEditorLabel) {
		return(styleLabels(r, sldEditorLabel, null));
	}
	
public static Listener<BaseEvent> styleLabels(final SLDEditorRuleForLabels r, final SLDEditorLabel sldEditorLabel, final SLDEditorRuleVO data) {
		
		if(data != null)
		{
			r.getActionPanel().add(r.buildLabelPropertyNamePanel());
			r.getActionPanel().add(r.buildFontFamilyPanel());
			r.getActionPanel().add(r.buildFontStylePanel());
			r.getActionPanel().add(r.buildFontSizePanel());
			r.getActionPanel().add(r.buildFontWeightPanel());
			r.getActionPanel().add(r.buildFillColorPanel());
			r.getActionPanel().add(r.buildFillOpacityPanel());
			if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0)
			{
				r.getActionPanel().add(r.buildAddFilter(data));	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
				r.addFilter(data);
			}
			else
			{
				r.getActionPanel().add(r.buildAddFilter());	
				r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
			}
			
			r.getActionPanel().add(r.buildButtonsPanel());
			r.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorLabel));
			r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorLabel));
			
			String app = data.getLabelPropertyName();
			if((app ==null)||(app.length()==0))
			{
				app = "";
			}
			r.getLabelPropertyNameField().setValue(app);
			
			int index = -1;
			ListBox lb = r.getFontFamilyListBox();
			
			app = data.getFontParameters().get("font-family");
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
					lb.setSelectedIndex(index);
			}			
			
			index = -1;
			lb = r.getFontStyleListBox();
			app = data.getFontParameters().get("font-style");
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
					lb.setSelectedIndex(index);
			}
			
			index = -1;
			lb = r.getFontWeightListBox();
			app = data.getFontParameters().get("font-weight");
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
					lb.setSelectedIndex(index);
			}
			
			app = data.getFontParameters().get("font-size");
			if((app ==null)||(app.length()==0))
			{			
				app = "";
			}
			r.getFontSizeTextField().setValue(app);
			
			app= data.getFontParameters().get("fill");
			if((app ==null)||(app.length()==0))
			{
				app= "<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>";
			}
			r.getFillColor().setHTML("<div align='center' style='background-color: "+app+"; color: white; font-weight: bold; font-style: italic;'>"+app+"</div>");
			
			try
			{
				int x = (int)(Double.parseDouble(data.getFontParameters().get("fill-opacity"))*100);
				r.getFillOpacity().setValue(x);
			}
			catch(Exception ex)
			{
				r.getFillOpacity().setValue(0);
			}
			r.getStyleLabels().setValue(true);
		}				
		
		Listener<BaseEvent> ris = new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (r.getStyleLabels().getValue()) {
					r.getActionPanel().add(r.buildLabelPropertyNamePanel());
					r.getActionPanel().add(r.buildFontFamilyPanel());
					r.getActionPanel().add(r.buildFontStylePanel());
					r.getActionPanel().add(r.buildFontSizePanel());
					r.getActionPanel().add(r.buildFontWeightPanel());
					r.getActionPanel().add(r.buildFillColorPanel());
					r.getActionPanel().add(r.buildFillOpacityPanel());
					if(data == null)
					{
						r.getActionPanel().add(r.buildAddFilter());	
						r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
					}
					else
					{
						if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0)
						{
							r.getActionPanel().add(r.buildAddFilter(data));	
							r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
							r.addFilter(data);
						}
						else
						{
							r.getActionPanel().add(r.buildAddFilter());	
							r.setAddFilterPanelIDX(r.getActionPanel().getItemCount());
						}					
					}
					r.getActionPanel().add(r.buildButtonsPanel());
					r.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorLabel));
					r.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(r, sldEditorLabel));
					r.getActionPanel().getLayout().layout();
							

					if(data != null){
						String app = data.getLabelPropertyName();
						if((app ==null)||(app.length()==0))
						{
							app = "";
						}
						r.getLabelPropertyNameField().setValue(app);
						
						int index = -1;
						ListBox lb = r.getFontFamilyListBox();
						
						app = data.getFontParameters().get("font-family");
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
								lb.setSelectedIndex(index);
						}						
						index = -1;
						lb = r.getFontStyleListBox();
						app = data.getFontParameters().get("font-style");
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
								lb.setSelectedIndex(index);
						}
						
						index = -1;
						lb = r.getFontWeightListBox();
						app = data.getFontParameters().get("font-weight");
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
								lb.setSelectedIndex(index);
						}
						
						app = data.getFontParameters().get("font-size");
						if((app ==null)||(app.length()==0))
						{			
							app = "";
						}
						r.getFontSizeTextField().setValue(app);
						
						app= data.getFontParameters().get("fill");
						if((app ==null)||(app.length()==0))
						{
							app= "<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>";
						}
						r.getFillColor().setHTML("<div align='center' style='background-color: "+app+"; color: white; font-weight: bold; font-style: italic;'>"+app+"</div>");
						
						try
						{
							int x = (int)(Double.parseDouble(data.getFontParameters().get("fill-opacity"))*100);
							r.getFillOpacity().setValue(x);
						}
						catch(Exception ex)
						{
							r.getFillOpacity().setValue(0);
						}
					}
				}
				else{
					removeFilters(r);
					for (HorizontalPanel p : r.getRemovablePanels())
						r.getActionPanel().remove(p);
					r.getActionPanel().getLayout().layout();
					r.getRemovablePanels().clear();
				}				
			};
		};
		
		return(ris);
	}
	
	public static SelectionListener<ButtonEvent> close(final SLDEditorWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().close();
			}
		};
	}
	
	public static void show(HorizontalPanel p, boolean show) {
		for (int i = 0 ; i < p.getItemCount() ; i++)
			p.getWidget(i).setVisible(show);
	}
	
	public static Listener<BaseEvent> addFilter(final SLDEditorRule r) {
		return(addFilter(r, null));
	}
	
	public static Listener<BaseEvent> addFilter(final SLDEditorRule r, final SLDEditorRuleVO data) {
		
		if(data != null){
			if(data.getFilterPropertyName() != null && data.getFilterPropertyName().length() != 0){
				r.getActionPanel().insert(r.buildFilterConditionPanel(), r.getAddFilterPanelIDX());
				r.getActionPanel().insert(r.buildFilterPropertyNamePanel(), r.getAddFilterPanelIDX());
				String app = data.getFilterPropertyName(); 
				if((app != null)&&(app.length()!=0))
				{
					r.getFilterPropertyNameField().setValue(app);
				}
				else
				{
					r.getFilterPropertyNameField().setValue("");
				}	
				app = data.getFilterPropertyValue(); 
				if((app != null)&&(app.length()>0))
				{
					r.getFilterPropertyValueField().setValue(app);
				}
				else
				{
					r.getFilterPropertyValueField().setValue("");
				}	
				
				if(data.isIs())
					r.getIsListBox().setSelectedIndex(0);
				else
					r.getIsListBox().setSelectedIndex(1);
				
				if(data.getComparison() == SLDEditorConstants.PropertyIsEqualTo){
					r.getComparisonListBox().setSelectedIndex(0);
				}
				else if(data.getComparison() == SLDEditorConstants.PropertyIsLessThan){
					r.getComparisonListBox().setSelectedIndex(1);
				}
				else{
					r.getComparisonListBox().setSelectedIndex(2);
				}
			}
		}		
		Listener<BaseEvent> ris = new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (r.getAddFilter().getValue()) {
					r.getActionPanel().insert(r.buildFilterConditionPanel(), r.getAddFilterPanelIDX());
					r.getActionPanel().insert(r.buildFilterPropertyNamePanel(), r.getAddFilterPanelIDX());
					
					if(data != null){
						String app = data.getFilterPropertyName(); 
						if((app != null)&&(app.length()!=0))
						{
							r.getFilterPropertyNameField().setValue(app);
						}
						else
						{
							r.getFilterPropertyNameField().setValue("");
						}	
						
						app = data.getFilterPropertyValue(); 
						if((app != null)&&(app.length()>0))
						{
							r.getFilterPropertyValueField().setValue(app);
						}
						else
						{
							r.getFilterPropertyValueField().setValue("");
						}	
						
						if(data.isIs())
							r.getIsListBox().setSelectedIndex(0);
						else
							r.getIsListBox().setSelectedIndex(1);
						
						if(data.getComparison() == SLDEditorConstants.PropertyIsEqualTo){
							r.getComparisonListBox().setSelectedIndex(0);
						}
						else if(data.getComparison() == SLDEditorConstants.PropertyIsLessThan){
							r.getComparisonListBox().setSelectedIndex(1);
						}
						else{
							r.getComparisonListBox().setSelectedIndex(2);
						}
					}
					r.getActionPanel().getLayout().layout();
					
				} else {
					removeFilters(r);
				}
			};
		};
		return(ris);
	}
	
	public static void removeFilters(SLDEditorRule r) {
		r.getAddFilter().setValue(false);
		for (HorizontalPanel p : r.getRemovableFilters())
			r.getActionPanel().remove(p);
		r.getActionPanel().getLayout().layout();
		r.getRemovableFilters().clear();
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorLine sldEditorLine) {
		return addRule(sldEditorLine, false);
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorLabel sldEditorLabel, boolean onData) {
		
		if(onData)
		{
			SLDEditorRuleForLabels rule = new SLDEditorRuleForLabels(sldEditorLabel);
			sldEditorLabel.getTabItem().add(rule.build());
			sldEditorLabel.getRules().add(rule);
		}
		SelectionListener<ButtonEvent> ris =  new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForLabels rule = new SLDEditorRuleForLabels(sldEditorLabel);
				sldEditorLabel.getTabItem().add(rule.build());
				sldEditorLabel.getTabItem().getLayout().layout();
				sldEditorLabel.getRules().add(rule);
			}
		};
		
		return ris;
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorBackground sldEditorBackground, boolean onData, boolean format) {
		
		if(onData)
		{
			SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(sldEditorBackground);
			sldEditorBackground.getTabItem().add(rule.build());
			sldEditorBackground.getRules().add(rule);
			if(format)
			{
				sldEditorBackground.getTabItem().getLayout().layout();
			}
		}
		
		SelectionListener<ButtonEvent> ris =  new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(sldEditorBackground);
				sldEditorBackground.getTabItem().add(rule.build());
				sldEditorBackground.getTabItem().getLayout().layout();
				sldEditorBackground.getRules().add(rule);
			}
		};
		
		return ris;
	}
	
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorLine sldEditorLine, boolean onData) {
		
		if(onData)
		{
			SLDEditorRuleForLines rule = new SLDEditorRuleForLines(sldEditorLine);
			sldEditorLine.getTabItem().add(rule.build());
			sldEditorLine.getRules().add(rule);
		}
		SelectionListener<ButtonEvent> ris =  new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForLines rule = new SLDEditorRuleForLines(sldEditorLine);
				sldEditorLine.getTabItem().add(rule.build());
				sldEditorLine.getTabItem().getLayout().layout();
				sldEditorLine.getRules().add(rule);
			}
		};
		
		return ris;
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorLabel sldEditorLabel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForLabels rule = new SLDEditorRuleForLabels(sldEditorLabel);
				sldEditorLabel.getTabItem().add(rule.build());
				sldEditorLabel.getTabItem().getLayout().layout();
				sldEditorLabel.getRules().add(rule);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorRaster sldEditorRaster) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForRaster rule = new SLDEditorRuleForRaster(sldEditorRaster);
				sldEditorRaster.getTabItem().add(rule.build());
				sldEditorRaster.getTabItem().getLayout().layout();
				sldEditorRaster.getRules().add(rule);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorRaster sldEditorRaster, boolean onData) {
		
		if(onData)
		{
			SLDEditorRuleForRaster rule = new SLDEditorRuleForRaster(sldEditorRaster);
			sldEditorRaster.getTabItem().add(rule.build());
			sldEditorRaster.getRules().add(rule);
		}
		SelectionListener<ButtonEvent> ris =  new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForRaster rule = new SLDEditorRuleForRaster(sldEditorRaster);
				sldEditorRaster.getTabItem().add(rule.build());
				sldEditorRaster.getTabItem().getLayout().layout();
				sldEditorRaster.getRules().add(rule);
			}
		};
		return ris;
	}
	
	public static SelectionListener<ButtonEvent> addRule(final SLDEditorBackground sldEditorBackground/*, final double idRule*/) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(sldEditorBackground);
				sldEditorBackground.getTabItem().add(rule.build());
				sldEditorBackground.getTabItem().getLayout().layout();
				sldEditorBackground.getRules().add(rule);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> removeRule(final SLDEditorRuleForLines element, final SLDEditorLine sldEditorLine) 
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeFilters(element);
				for (HorizontalPanel p : element.getRemovablePanels())
					element.getActionPanel().remove(p);
				element.getActionPanel().getLayout().layout();
				element.getRemovablePanels().clear();
				if(sldEditorLine.getRules().size() > 1)
				{
					for (HorizontalPanel p : element.getRemovableCheckbox())
						element.getActionPanel().remove(p);
					element.getLine().removeFromParent();
				}
				else
				{
					element.getFormatLines().setValue(false);
				}
				
				sldEditorLine.getRules().remove(element);
			}
			};
	}
	
	public static SelectionListener<ButtonEvent> removeRule(final SLDEditorRuleForLabels element, final SLDEditorLabel sldEditorLabel) 
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeFilters(element);
				for (HorizontalPanel p : element.getRemovablePanels())
					element.getActionPanel().remove(p);
				element.getActionPanel().getLayout().layout();
				element.getRemovablePanels().clear();
				if(sldEditorLabel.getRules().size() > 1)
				{
					for (HorizontalPanel p : element.getRemovableCheckbox())
						element.getActionPanel().remove(p);
					element.getLine().removeFromParent();
				}
				else
				{
					element.getStyleLabels().setValue(false);
				}
				sldEditorLabel.getRules().remove(element);
			}
			};
	}	
	
	public static SelectionListener<ButtonEvent> removeRule(final SLDEditorRuleForBackgrounds element, final SLDEditorBackground sldEditorBackground) 
	{
		return removeRule(element, sldEditorBackground, null); 
	}
	
	public static SelectionListener<ButtonEvent> removeRule(final SLDEditorRuleForBackgrounds element, final SLDEditorBackground sldEditorBackground, final SLDEditorRuleVO data) 
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeFilters(element);
				element.setCreateIntervals(false);
				int i=0;
				for (HorizontalPanel p : element.getRemovablePanels())
				{
					if((p.getId() == null)||(!(p.getId().equalsIgnoreCase("PolygonAlgorithmPanel"))))
					{
						element.getActionPanel().remove(p);
					}
				}
				for (ContentPanel cp : element.getRemovableContentPanels())
					element.getActionPanel().remove(cp);
				element.getActionPanel().getLayout().layout();
				element.getRemovablePanels().clear();
				element.getRemovableContentPanels().clear();
				if(sldEditorBackground.getRules().size() > 1)
				{
					for (HorizontalPanel p : element.getRemovableCheckbox())
						element.getActionPanel().remove(p);
					element.getLine().removeFromParent();
				}
				else
				{
					element.getFillPolygons().setValue(false);
				}
				sldEditorBackground.getRules().remove(element);
				if(data != null)
				{
					addRule(sldEditorBackground, true, true);
				}
				else 
				{
					//System.out.println("SLDEditorController removeRule DATA == NULL");
				}
			}
			};
	}
	
	public static SelectionListener<ButtonEvent> removeRule(final SLDEditorRuleForRaster element, final SLDEditorRaster sldEditorRaster) 
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (HorizontalPanel p : element.getRemovableSliderOpacityPanels())
					element.getActionPanel().remove(p);
				
				for (HorizontalPanel p : element.getRemovableColorMapPanels())
					element.getActionPanel().remove(p);
				
				for (ContentPanel p : element.getRemovableColorMapContentPanels())
					element.getActionPanel().remove(p);
				
				for (HorizontalPanel p : element.getRemovableChannelSelectionPanels())
					element.getActionPanel().remove(p);
				
				for (HorizontalPanel p : element.getRemovableContrastEnhancementPanels())
					element.getActionPanel().remove(p);
				
				for (ContentPanel p : element.getRemovableContrastEnhancementContentPanels())
					element.getActionPanel().remove(p);
				
				for (HorizontalPanel p : element.getRemovableFormatRasterPanels())
					element.getActionPanel().remove(p);
				
				element.getActionPanel().getLayout().layout();
				element.getRemovableSliderOpacityPanels().clear();		
				element.getRemovableColorMapPanels().clear();
				element.getRemovableColorMapContentPanels().clear();
				element.getRemovableChannelSelectionPanels().clear();
				element.getRemovableContrastEnhancementPanels().clear();
				element.getRemovableContrastEnhancementContentPanels().clear();
				element.getRemovableFormatRasterPanels().clear();
				
				if(sldEditorRaster.getRules().size() > 1)
				{
					for (HorizontalPanel p : element.getRemovableCheckbox())
						element.getActionPanel().remove(p);
					element.getLine().removeFromParent();
				}
				else
				{
					element.getFormatRasterRule().setValue(false);
				}
				
				sldEditorRaster.getRules().remove(element);
			}
			};
	}
	
	public static SelectionListener<ButtonEvent> createIntevals(final SLDEditorRuleForBackgrounds r, final int algorithm) 
	{
		return createIntevals(r, algorithm, null); 
	}
	
	public static SelectionListener<ButtonEvent> createIntevals(final SLDEditorRuleForBackgrounds r, final int algorithm, final SLDEditorRuleVO data) 
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				//If the user created the classification of intervals 
				r.setCreateIntervals(true);
				List<Interval> rules = null;
				if(data == null)
				{
					rules = collectBackgroundRules(SLDEditorRuleForBackgrounds.getWindow(), algorithm);
					SLDEditorRuleForBackgrounds.setRules(rules);
				}
				else
				{
					if(SLDEditorRuleForBackgrounds.getRules() == null)
					{
						if(data.getFillParametersIntervals().get((data.getFillParametersIntervals().size()-1)).getMaxValue() == -1)
						{
							data.getFillParametersIntervals().remove(data.getFillParametersIntervals().size()-1);
						}
						SLDEditorRuleForBackgrounds.setRules(data.getFillParametersIntervals());
					}
				}
							
				if (isValid(SLDEditorRuleForBackgrounds.getWindow())) {			
				final Rectangle rec = r.getActionPanel().getBounds(true);
				
				final Popup p = r.buildIntervalOfClassificatioPanel();
				if(data == null)
				{
					p.add(r.buildIntervalsGridPanel(r, rules));
				}
				else
				{
					p.add(r.buildIntervalsGridPanel(r, SLDEditorRuleForBackgrounds.getRules()));
				}
				
				p.setAutoHide(false);
				p.showAt(r.getActionPanel().getAbsoluteLeft() + rec.width +5, r.getActionPanel().getAbsoluteTop() +( 1/3 *  rec.height));
				}
				else {
						FenixAlert.info("INFO", "Please check your parameters.");
					}				
			}
		};
	}	
	
	public static SelectionListener<ButtonEvent> colorPickerAction(final com.extjs.gxt.ui.client.widget.Window r) 
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {				
				 MessageBox.alert("Color Code", "Please copy the color code and past it in Color column:", new Listener<MessageBoxEvent>() {  
		    	      public void handleEvent(MessageBoxEvent ce) {  
		    	    	//  System.out.println("Ok");
		    		       }  
		    		     });
			}
		};
	}

	public static void IntervalsOfClasHide(final SLDEditorRuleForBackgrounds r) {
		r.getIntervalsOfClassification().hide();
	}

	public static SelectionListener<ButtonEvent> uploadSLD(
			SLDEditorWindow sldEditorWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if((SldEditorUtils.getSldPathFile() == null)||(SldEditorUtils.getSldPathFile().equals("")))
				{
					FenixAlert.error("ERROR", "It is necessary creating sld before.");
				}
				else if((SldEditorUtils.getSldNameFile() == null)||(SldEditorUtils.getSldNameFile().equals("")))
				{
					FenixAlert.error("ERROR", "It is necessary creating sld before.");
				}
				else
				{
					  MessageBox.prompt("Sld Informations", "Please enter the name of the sld.", new Listener<MessageBoxEvent>() {  
						   public void handleEvent(MessageBoxEvent ce) {  
							   final LoadingWindow l = new LoadingWindow("SLD Editor", "Uploading the SLD File", "Please wait...");
								try {
										SLDEditorServiceEntry.getInstance().uploadSLD(SldEditorUtils.getSldNameFile(), SldEditorUtils.getSldPathFile(), ce.getValue(), new AsyncCallback<String>() {
											public void onSuccess(String success) {
												l.destroyLoadingBox();
												 MessageBox.info("Sld Informations", "File sld uploaded.", new Listener<MessageBoxEvent>() {  
										    	      public void handleEvent(MessageBoxEvent ce) {  
										    	    
										    	      }
										    	      });
												l.destroyLoadingBox();
											}
										public void onFailure(Throwable e) {
											l.destroyLoadingBox();
											FenixAlert.error("ERROR", e.getMessage());
											l.destroyLoadingBox();
										}
									});
								} catch (FenixGWTException e) {
									FenixAlert.error("ERROR", e.getMessage());
								}			
						   }
						   });
					  }
				}
			};
	}
	
	public static SelectionListener<ButtonEvent> saveSLD(final SLDEditorWindow sldEditorWindow) {
		
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if((SldEditorUtils.getSldPathFile() == null)||(SldEditorUtils.getSldPathFile().equals("")))
				{
					FenixAlert.error("ERROR", "It is necessary creating sld before.");
				}
				else if((SldEditorUtils.getSldNameFile() == null)||(SldEditorUtils.getSldNameFile().equals("")))
				{
					FenixAlert.error("ERROR", "It is necessary creating sld before.");
				}
				else
				{								
					 MessageBox.prompt("Sld Informations", "Please enter the path of the file sld.", new Listener<MessageBoxEvent>() {  
			    	      public void handleEvent(MessageBoxEvent ce) {  
			    	    	  final String boxPath = ce.getValue();
			    	    	  MessageBox.prompt("Sld Informations", "Please enter the name of the sld.", new Listener<MessageBoxEvent>() {  
					    	      public void handleEvent(MessageBoxEvent ce) {	    	    	  

									final LoadingWindow l = new LoadingWindow("SLD Editor", "Saving the SLD File", "Please wait...");
									try {
											SLDEditorServiceEntry.getInstance().saveSLD(SldEditorUtils.getSldPathFile(), SldEditorUtils.getSldNameFile(), boxPath, ce.getValue(), new AsyncCallback<String>() {
												public void onSuccess(String success) {
													l.destroyLoadingBox();
													 MessageBox.info("Sld Informations", "File sld saved.", new Listener<MessageBoxEvent>() {  
											    	      public void handleEvent(MessageBoxEvent ce) {
											    	      }
											    	      });
													l.destroyLoadingBox();
												}
											public void onFailure(Throwable e) {
												l.destroyLoadingBox();
												FenixAlert.error("ERROR", e.getMessage());
												l.destroyLoadingBox();
											}
										});
									} catch (FenixGWTException e) {
										FenixAlert.error("ERROR", e.getMessage());
									}			
					    	      }
			});
			    		       }
			    		     });
				}
			}
				};
	}
		
	public static Listener<BaseEvent> opacitySliderFormat(final SLDEditorRuleForRaster sldEditorRuleForRaster, final SLDEditorRaster sldEditorRaster) 
	{
		Listener<BaseEvent> ris = new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (sldEditorRuleForRaster.getSliderOpacityFormat().getValue()) {
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildSliderOpacityPanelS());
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
				} else {
					for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableSliderOpacityPanels())
						sldEditorRuleForRaster.getActionPanel().remove(p);
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
					sldEditorRuleForRaster.getRemovableSliderOpacityPanels().clear();
				}
			};
		};
		
		return ris;
	}

	public static Listener<BaseEvent> formatColorMap(final SLDEditorRuleForRaster sldEditorRuleForRaster, final SLDEditorRaster sldEditorRaster) 
	{
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (sldEditorRuleForRaster.getFormatColorMap().getValue()) {
					sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildColorMapTypePanel());
					sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildColorMapBitColPanel());
					sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildOpacityForEachIntervalPanel());
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
				} else {
					for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableColorMapPanels())
						sldEditorRuleForRaster.getActionPanel().remove(p);
					for (ContentPanel p : sldEditorRuleForRaster.getRemovableColorMapContentPanels())
						sldEditorRuleForRaster.getActionPanel().remove(p);
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
					sldEditorRuleForRaster.getRemovableColorMapPanels().clear();
					sldEditorRuleForRaster.getRemovableColorMapContentPanels().clear();
				}
			};
		};
	}
	
	public static Listener<BaseEvent> formatChannelSelection(final SLDEditorRuleForRaster sldEditorRuleForRaster, final SLDEditorRaster sldEditorRaster) 
	{
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (sldEditorRuleForRaster.getSelectionChannelFormat().getValue()) {
					sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildSelectionChannelTypePanel());
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
				} else {
					for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableChannelSelectionPanels())
						sldEditorRuleForRaster.getActionPanel().remove(p);
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
					sldEditorRuleForRaster.getRemovableChannelSelectionPanels().clear();
				}
			};
		};
	}

	public static Listener<BaseEvent> contrastEnhancementFormat(final SLDEditorRuleForRaster sldEditorRuleForRaster, final SLDEditorRaster sldEditorRaster) {
	
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (sldEditorRuleForRaster.getContrastEnhancementFormat().getValue()) {
					sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildContrastEnhancementPanel());
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
				} else {
					for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableContrastEnhancementPanels())
						sldEditorRuleForRaster.getActionPanel().remove(p);
					for (ContentPanel p : sldEditorRuleForRaster.getRemovableContrastEnhancementContentPanels())
						sldEditorRuleForRaster.getActionPanel().remove(p);
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
					sldEditorRuleForRaster.getRemovableContrastEnhancementPanels().clear();
					sldEditorRuleForRaster.getRemovableContrastEnhancementContentPanels().clear();
				}
			};
		};
	}
	
	public static Listener<BaseEvent> rasterRuleFormat(final SLDEditorRuleForRaster sldEditorRuleForRaster, final SLDEditorRaster sldEditorRaster) {
		
		return(rasterRuleFormat(sldEditorRuleForRaster, sldEditorRaster, null));
	}

	public static Listener<BaseEvent> rasterRuleFormat(final SLDEditorRuleForRaster sldEditorRuleForRaster, final SLDEditorRaster sldEditorRaster, final SLDEditorRuleVO data) {
	
		if(data != null)
		{
			boolean sliderOpacityCeck = false;
			boolean colorMapCheck = false;
			boolean channelSelectionCheck = false;
			boolean contrastEnhancementCheck = false;
			if(data.getSliderOpacityOnlyMap() != null)
			{
				if((data.getSliderOpacityOnlyMap().get("fill-opacity")) != null)
				{
					if(!(data.isColorMapSlider()))
					{
						sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatOpacityPanel(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
						sliderOpacityCeck = true;
					}
					else
					{
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatOpacityPanel(sldEditorRaster));
					}
				}
				else
				{
					sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatOpacityPanel(sldEditorRaster));
				}
			}
			else
			{
				sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatOpacityPanel(sldEditorRaster));
			}
			
			if((data.getColorMapItem() != null)&&(data.isColorMapFound()))
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				colorMapCheck = true;
			}
			else
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel(sldEditorRaster), sldEditorRuleForRaster.getActionPanel().getItemCount());
			}
			if((data.getChannelSelectionMap().containsKey("red-channel-name"))||(data.getChannelSelectionMap().containsKey("green-channel-name"))||(data.getChannelSelectionMap().containsKey("blue-channel-name"))||(data.getChannelSelectionMap().containsKey("gray-channel-name")))
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatChannelSelectionPanel(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				channelSelectionCheck = true;
				if((data.getChannelSelectionMap().containsKey("red-channel-name"))||(data.getChannelSelectionMap().containsKey("green-channel-name"))||(data.getChannelSelectionMap().containsKey("blue-channel-name")))
				{
				}
				else if(data.getChannelSelectionMap().containsKey("gray-channel-name"))
				{
				}
				else
				{
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatChannelSelectionPanel(sldEditorRaster), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}	
			}
			else
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatChannelSelectionPanel(sldEditorRaster), sldEditorRuleForRaster.getActionPanel().getItemCount());
			}
			
			if(data.getContrastValueType()!= null)
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatContrastEnhancementPanel(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				contrastEnhancementCheck = true;
			}
			else
			{
				sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatContrastEnhancementPanel(sldEditorRaster));
			}
			
			if(sliderOpacityCeck)
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatOpacityPanel2(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
			}
			if(colorMapCheck)
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel2(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel3(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel4(sldEditorRaster, data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				if(data.isColorMapSlider())
				{
					//Color Map Global Opacity
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel5(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel6(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}
				else
				{
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildFormatColorMapPanel7(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}
			}
			if(channelSelectionCheck)
			{
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildSelectionChannelTypePanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				if((data.getChannelSelectionMap().containsKey("red-channel-name"))||(data.getChannelSelectionMap().containsKey("green-channel-name"))||(data.getChannelSelectionMap().containsKey("blue-channel-name")))
				{
					sldEditorRuleForRaster.buildRedGreenBlueSelectionsPanel(data);
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildRedChannelPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildGreenChannelPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildBlueChannelPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}
				else if(data.getChannelSelectionMap().containsKey("gray-channel-name"))
				{
					sldEditorRuleForRaster.buildGraySelectionPanel(data);
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildGrayChannelPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}
			}
			if(contrastEnhancementCheck)
			{	
				sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildContrastEnhancementPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				if(data.getContrastValueType().equals("global-contrast-value"))
				{
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.buildGlobalContrastPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
					if((data.getContrastEnhancementMap() != null)&&(data.getContrastEnhancementMap().get("global-contrast-value")!= null))
					{
						if((data.getContrastEnhancementMap().get("global-contrast-value")).equals("Gamma"))
						{
							sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.gammaValuePanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
						}
					}
				}
				else if(data.getContrastValueType().equals("rgb-contrast-value"))
				{
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.RedGreenBlueChannelContrastPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}
				else if(data.getContrastValueType().equals("gray-contrast-value"))
				{
					sldEditorRuleForRaster.getActionPanel().insert(sldEditorRuleForRaster.GrayChannelContrastPanel(data), sldEditorRuleForRaster.getActionPanel().getItemCount());
				}
			}			
			
			sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildButtonsPanel());
			sldEditorRuleForRaster.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorRaster));
			sldEditorRuleForRaster.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(sldEditorRuleForRaster, sldEditorRaster));

			sldEditorRuleForRaster.getFormatRasterRule().setValue(true);
		}				
				
		Listener<BaseEvent> ris = new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (sldEditorRuleForRaster.getFormatRasterRule().getValue()) {						
					if(data == null)
					{
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatOpacityPanel(sldEditorRaster));
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatColorMapPanel(sldEditorRaster));
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatChannelSelectionPanel(sldEditorRaster));
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildFormatContrastEnhancementPanel(sldEditorRaster));
						sldEditorRuleForRaster.getActionPanel().add(sldEditorRuleForRaster.buildButtonsPanel());
						sldEditorRuleForRaster.getAddRuleButton().addSelectionListener(SLDEditorController.addRule(sldEditorRaster));
						sldEditorRuleForRaster.getDeleteRuleButton().addSelectionListener(SLDEditorController.removeRule(sldEditorRuleForRaster, sldEditorRaster));
					}
					else
					{
						
					}
					sldEditorRuleForRaster.getActionPanel().getLayout().layout();
				}
				else {							
						for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableSliderOpacityPanels())
							sldEditorRuleForRaster.getActionPanel().remove(p);
						
						for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableColorMapPanels())
							sldEditorRuleForRaster.getActionPanel().remove(p);
						
						for (ContentPanel p : sldEditorRuleForRaster.getRemovableColorMapContentPanels())
							sldEditorRuleForRaster.getActionPanel().remove(p);
						
						for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableChannelSelectionPanels())
							sldEditorRuleForRaster.getActionPanel().remove(p);
						
						for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableContrastEnhancementPanels())
							sldEditorRuleForRaster.getActionPanel().remove(p);
						
						for (ContentPanel p : sldEditorRuleForRaster.getRemovableContrastEnhancementContentPanels())
							sldEditorRuleForRaster.getActionPanel().remove(p);
						int i=0;
						for (HorizontalPanel p : sldEditorRuleForRaster.getRemovableFormatRasterPanels())
						{
							sldEditorRuleForRaster.getActionPanel().remove(p);
							i++;
						}
						sldEditorRuleForRaster.getRemovableSliderOpacityPanels().clear();		
						sldEditorRuleForRaster.getRemovableColorMapPanels().clear();
						sldEditorRuleForRaster.getRemovableColorMapContentPanels().clear();
						sldEditorRuleForRaster.getRemovableChannelSelectionPanels().clear();
						sldEditorRuleForRaster.getRemovableContrastEnhancementPanels().clear();
						sldEditorRuleForRaster.getRemovableContrastEnhancementContentPanels().clear();
						sldEditorRuleForRaster.getRemovableFormatRasterPanels().clear();
						sldEditorRuleForRaster.getActionPanel().getLayout().layout();
					}
				};
			};
			return ris;
	}

	public static void createMsgBox() {
		 MessageBox.prompt("Sld Informations", "Please enter the path of the file sld.", new Listener<MessageBoxEvent>() {  
   	      public void handleEvent(MessageBoxEvent ce) {  
   	    	  final String boxPath = ce.getValue();
   	    	if(boxPath != null)
   	    	{
   	    	  MessageBox.prompt("Sld Informations", "Please enter the name of the sld.", new Listener<MessageBoxEvent>() {  
		    	      public void handleEvent(MessageBoxEvent ce) {	    	    	  

						final LoadingWindow l = new LoadingWindow("SLD Editor", "Creating the Sld Editor Interface", "Please wait...");
						try {
							if(ce.getValue() != null)
							{
								SLDEditorServiceEntry.getInstance().createSLDeditor(boxPath, ce.getValue(), new AsyncCallback<List<SLDEditorRuleVO>>() {
									public void onSuccess(List<SLDEditorRuleVO> success) {
										l.destroyLoadingBox();
										if(success!= null)
										{
//											System.out.println("Success diverso da null");
//											System.out.println("Success diverso da null success.size() " + success.size());
//											if(success.size() != 0)
//											{
//												System.out.println("Success.get(0) diverso da null");
//												System.out.println("Success.get(0) type sld "+success.get(0).getTypeSld());
//											
//												System.out.println("Success diverso da null Name " + success.get(0).getNameUserLayer());
//												System.out.println("Success diverso da null Title " + success.get(0).getTitleUserStyle());
//												System.out.println("Success diverso da null Abstract " + success.get(0).getAbstractUserStyle());
//	
//												for(int w =1; w<success.size(); w++)
//												{
//													System.out.println("w= "+w +" TYPE " + success.get(w).getType());
//													
//													//LINE
//													System.out.println("LINE ");
//													System.out.println("Stroke = "+success.get(w).getStrokeParameters().get("stroke"));
//													
//													//If Stroke Dasharray== null setting NORMAL on ListBox
//													System.out.println("Stroke Dasharray = "+success.get(w).getStrokeParameters().get("stroke-dasharray"));
//													System.out.println("Stroke-width = "+success.get(w).getStrokeParameters().get("stroke-width"));
//													System.out.println("\n");
//													
//													//FILTER
//													
//													System.out.println("FILTER ");
//													System.out.println("FILTER property name = "+success.get(w).getFilterPropertyName());
//													System.out.println("FILTER property value = "+success.get(w).getFilterPropertyValue());
//													System.out.println("FILTER property is = "+success.get(w).isIs());
//													System.out.println("FILTER property comparison = "+success.get(w).getComparison());
//													
//													
//													//LABEL
//													System.out.println("LABEL ");
//													System.out.println("Label property name = "+success.get(w).getLabelPropertyName());
//													System.out.println("Font size = "+success.get(w).getFontParameters().get("font-size"));
//													System.out.println("Font style = "+success.get(w).getFontParameters().get("font-style"));
//													System.out.println("Font family = "+success.get(w).getFontParameters().get("font-family"));
//													System.out.println("Font weight = "+success.get(w).getFontParameters().get("font-weight"));
//													System.out.println("Fill = "+success.get(w).getFontParameters().get("fill"));
//													System.out.println("Fill-opacity = "+success.get(w).getFontParameters().get("fill-opacity"));
//													
//																							
//													
//													if(success.get(0).getFillParametersIntervals()!= null)
//													{
//														List<Interval> intervals = success.get(0).getFillParametersIntervals(); 
//														for(int k=0; k< intervals.size(); k++)
//														{
//															System.out.println("Min Value "+intervals.get(k).getMinValue() +" Max Value "+intervals.get(k).getMaxValue()+" Color "+ intervals.get(k).getColor());
//														}
//													}
//													
//												}
//												//POLYGON
//												System.out.println("POLYGON ");
//												System.out.println("w= 0 " +" TYPE " + success.get(0).getType());
//												System.out.println("Polygon Label property name = "+success.get(0).getLabelIntervalPropertyName());
//												System.out.println("Polygon Min Tot = "+success.get(0).getMinTot());
//												System.out.println("Polygon Max Tot = "+success.get(0).getMaxTot());
//												System.out.println("Polygon Min Color = "+success.get(0).getMinColor());
//												System.out.println("Polygon Max Color = "+success.get(0).getMaxColor());
//												System.out.println("Polygon Label = "+success.get(0).getLabelIntervalPropertyName());
//												System.out.println("Polygon Filter = "+success.get(0).getFilterPropertyName());
//												System.out.println("Polygon Filter Value= "+success.get(0).getFilterPropertyValue());
//												System.out.println("Polygon Filter is= "+success.get(0).isIs());		
//	
												
//												//RASTER
//											for(int w =0; w<success.size(); w++)
//											{
//												System.out.println("\n\n w = "+w);
//												System.out.println("OPACITY ");
//												System.out.println("Opacity = "+success.get(w).getSliderOpacityOnlyMap().get("fill-opacity"));
//												
//												System.out.println("COLOR MAP ");
//												System.out.println("Type Attribute = "+success.get(w).getColorMapItem().get("type-attribute"));
//												System.out.println("Extended Attribute = "+success.get(w).getColorMapItem().get("extended-attribute"));
//												if(success.get(w).isColorMapSlider())
//												{
//													System.out.println("Color Map Slider True = Small Grid");
//													if(success.get(w).getItemsSmallGrid()!= null)
//													{
//														int size = success.get(w).getItemsSmallGrid().size();
//														for(int x=0;x<size; x++)
//														{//Aggiungere opacity
//															System.out.println("Quantity "+success.get(w).getItemsSmallGrid().get(x).getquantity()+" Label "+success.get(w).getItemsSmallGrid().get(x).getlabel()+ " Color "+success.get(w).getItemsSmallGrid().get(x).getColor());
//														}
//													}													
//												}
//												else
//												{
//													System.out.println("Color Map Slider False = Big Grid");
//													if(success.get(w).getItemsColorMapBigGrids()!= null)
//													{
//														int size = success.get(w).getItemsColorMapBigGrids().size();
//														for(int x=0;x<size; x++)
//														{
//															System.out.println("Quantity "+success.get(w).getItemsColorMapBigGrids().get(x).getquantity()+" Label "+success.get(w).getItemsColorMapBigGrids().get(x).getlabel()+ " Color "+success.get(w).getItemsColorMapBigGrids().get(x).getColor()+" Opacity "+success.get(w).getItemsColorMapBigGrids().get(x).getopacity());
//														}
//													}
//												}
//												if(success.get(w).getChannelSelectionMap() != null)
//												{
//												System.out.println("CHANNEL SELECTION ");
//												System.out.println("Channel selection Red = "+success.get(w).getChannelSelectionMap().get("red-channel-name"));
//												
//												System.out.println("Channel selection Green = "+success.get(w).getChannelSelectionMap().get("green-channel-name"));
//												
//												System.out.println("Channel selection Blue = "+success.get(w).getChannelSelectionMap().get("blue-channel-name"));
//												System.out.println("CONTRAST ENHANCEMENT ");
//												if(success.get(w).getRgbcontrast() != null)
//												{
//													System.out.println("Contrast Enhancement Global Value Type 1= "+success.get(w).getContrastValueType());
//													int size = success.get(w).getRgbcontrast().size();
//														for(int x=0;x<size; x++)
//														{
//															System.out.println("Channel Name "+success.get(w).getRgbcontrast().get(x).getnameChannel()+" Type Contrast "+success.get(w).getRgbcontrast().get(x).gettypeChannelContrast()+ " Gamma Value "+success.get(w).getRgbcontrast().get(x).getgammaValueContrast());
//														}
//												}
//												//}
//												
//												System.out.println("Channel selection Gray = "+success.get(w).getChannelSelectionMap().get("gray-channel-name"));
//												System.out.println("CONTRAST ENHANCEMENT ");
//												if(success.get(w).getContrastEnhancementMap()!= null)
//												{
//													System.out.println("Contrast Enhancement Global Value Type 2= "+success.get(w).getContrastValueType());
//													if(success.get(w).getRgbcontrast() != null)
//													{
//														int size = success.get(w).getRgbcontrast().size();
//															for(int x=0;x<size; x++)
//															{
//																System.out.println("Channel Name "+success.get(w).getRgbcontrast().get(x).getnameChannel()+" Type Contrast "+success.get(w).getRgbcontrast().get(x).gettypeChannelContrast()+ " Gamma Value "+success.get(w).getRgbcontrast().get(x).getgammaValueContrast());
//															}
//													}
//												}
//												if(success.get(w).getContrastEnhancementMap()!= null)
//												{
//												System.out.println("Contrast Enhancement Global Contrast = "+success.get(w).getContrastEnhancementMap().get("global-contrast-value"));
//												System.out.println("Contrast Enhancement Global Value Type = "+success.get(w).getContrastValueType());
//												if(success.get(w).getContrastEnhancementMap()!= null)
//												{
//													System.out.println("Contrast Enhancement Global Value Gamma Value = "+success.get(w).getContrastEnhancementMap().get("gamma-value"));												
//												}
//												}
//											}
//											}
										//}
//										if(success == null)
//										{
//											System.out.println("Success UGUALE null");
//										}
										if(success.get(0).getTypeSld().equals("shapefile"))
										{
										//	System.out.println("Success diverso da null Shapefile");
											SldEditorUtils.setSldType("shapefile");
											SldEditorUtils.setCreatingInterface(true);
											SldEditorUtils.setVoForInterface(success);
											new SLDEditorWindow().build(success);
										}
										else if(success.get(0).getTypeSld().equals("raster"))
										{
											SldEditorUtils.setSldType("raster");
											SldEditorUtils.setCreatingInterface(true);
											SldEditorUtils.setVoForInterface(success);
											new SLDEditorWindow("raster").build(success);
										}
										l.destroyLoadingBox();
										}
									}
								public void onFailure(Throwable e) {
									l.destroyLoadingBox();
									FenixAlert.error("ERROR", e.getMessage());
									l.destroyLoadingBox();
								}
							});
							}
						} catch (FenixGWTException e) {
							FenixAlert.error("ERROR", e.getMessage());
						}			
		    	      }
});
   	      }
   		       }
   		     });
	}	
}