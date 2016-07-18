package org.fao.fenix.web.modules.designer.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.designer.client.control.DesignerController;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;

public class DesignerTabPanel {

	private TabPanel tabPanel;
	
	private Map<TabItem, DesignerTabItem> tabItemMap;
	
	private List<DesignerTabItem> tabItems;
	
	private int pageNumber;
	
	public DesignerTabPanel() {
		tabPanel = new TabPanel();
		tabItemMap = new HashMap<TabItem, DesignerTabItem>();
		tabItems = new ArrayList<DesignerTabItem>();
		tabPanel.add(buildManualTab());
		pageNumber = 1;
	}
	
	private TabItem buildManualTab() {
		TabItem ti = new TabItem(BabelFish.print().info());
		ti.setIconStyle("info");
		ti.setClosable(false);
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(10);
		ti.add(p);
		Html h = new Html(BabelFish.print().latexReportManual());
		p.add(h);
		return ti;
	}
	
	public TabPanel build() {
		return tabPanel;
	}
	
	public TabPanel build(Map<String, List<ResourceViewVO>> map) {
		List<ResourceViewVO> pages = map.get(DesignerConstants.PAGE.name());
		List<ResourceViewVO> boxes = map.get(DesignerConstants.BOX.name());
		for (ResourceViewVO page : pages) {
			DesignerConstants orientation = DesignerConstants.valueOf(page.getType()); 
			DesignerTabItem ti = null;
			switch (orientation) {
				case PORTRAIT:
					ti = addPortraitTabItem(orientation.name());
				break;
				case LANDSCAPE:
					ti = addLandscapeTabItem(orientation.name());
				break;
			}
			for (ResourceViewSettingVO pointer : page.getSettings()) {
				String pageCode = pointer.getValue();
				for (ResourceViewVO box : boxes) {
					
					if (String.valueOf(box.getResourceId()).equals(pageCode)) {
						
						DesignerBox designerBox = ti.addBox();
						
						designerBox.setResourceID(Long.valueOf(box.getQuery()));
						designerBox.setResourceType(ResourceType.valueOf(box.getType()));
						designerBox.setResourceTitle(box.getTitle());
						designerBox.getResourceName().setValue(box.getTitle());
						
						designerBox.getResourceName().setValue(designerBox.getResourceTitle());
						for (int i = 0 ; i < designerBox.getResourceTypeList().getItemCount() ; i++)
							if (designerBox.getResourceTypeList().getValue(i).equalsIgnoreCase(designerBox.getResourceType().name()))
								designerBox.getResourceTypeList().setItemSelected(i, true);
						
						Integer boxID = designerBox.getBoxID();
						String boxRGB = designerBox.getBoxRGB();
						int[] xy_a = getPixelCoordinates(box.getSettings().get(0));
						int[] xy_b = getPixelCoordinates(box.getSettings().get(1));
						DesignerController.fillBox(ti.getDesignerGridWindow().getDesignerGridPanel(), boxID, boxRGB, xy_a[0], xy_b[0], xy_a[1], xy_b[1]);
						
						List<ResourceViewSettingVO> settings = box.getSettings();
						designerBox.setDesignerBoxSettings(new DesignerBoxSettings());
						
						boolean addCaption = addCaption(settings);
						if (addCaption)
							designerBox.getDesignerBoxSettings().getAddCaption().setValue(true);
						boolean addBox = addBox(settings);
						if (addBox)
							designerBox.getDesignerBoxSettings().getAddBox().setValue(true);
							
						for (ResourceViewSettingVO s : settings) {
							DesignerConstants c = DesignerConstants.valueOf(s.getSettingName()); 
							switch (c) {
								case CAPTION: 
									designerBox.getDesignerBoxSettings().getCaption().setValue(s.getValue()); 
								break;
								case CAPTION_FONTFAMILY:
									for (int i = 0 ; i < designerBox.getDesignerBoxSettings().getCaptionFontFamily().getItemCount() ; i++) {
										if (designerBox.getDesignerBoxSettings().getCaptionFontFamily().getValue(i).equals(s.getValue())) {
											designerBox.getDesignerBoxSettings().getCaptionFontFamily().setSelectedIndex(i);
											break;
										}
									}
									
								break;
								case CAPTION_SIZE:
									for (int i = 0 ; i < designerBox.getDesignerBoxSettings().getCaptionFontSize().getItemCount() ; i++) {
										if (designerBox.getDesignerBoxSettings().getCaptionFontSize().getValue(i).equals(s.getValue())) {
											designerBox.getDesignerBoxSettings().getCaptionFontSize().setSelectedIndex(i);
											break;
										}
									}
								break;
								case CAPTION_POSITION:
									for (int i = 0 ; i < designerBox.getDesignerBoxSettings().getCaptionPosition().getItemCount() ; i++) {
										if (designerBox.getDesignerBoxSettings().getCaptionPosition().getValue(i).equals(s.getValue())) {
											designerBox.getDesignerBoxSettings().getCaptionPosition().setSelectedIndex(i);
											break;
										}
									}
								break;
								case CAPTION_COLOR:
									designerBox.getDesignerBoxSettings().getCaptionColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + s.getValue() + "; font-weight: bold; '>" + s.getValue() + "</div>");
								break;
								case BOX_LINE_COLOR:
									designerBox.getDesignerBoxSettings().getBoxLineColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + s.getValue() + "; font-weight: bold; '>" + s.getValue() + "</div>");
								break;
								case BOX_BACKGROUND_COLOR:
									designerBox.getDesignerBoxSettings().getBoxBackgroundColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + s.getValue() + "; font-weight: bold; '>" + s.getValue() + "</div>");
								break;
							}
						}
					}
				}
			}
		}
		return tabPanel;
	}
	
	private boolean addCaption(List<ResourceViewSettingVO> settings) {
		boolean addCaption = false;
		for (ResourceViewSettingVO s : settings) {
			DesignerConstants c = DesignerConstants.valueOf(s.getSettingName()); 
			switch (c) {
				case CAPTION: addCaption = true; break;
				case CAPTION_COLOR: addCaption = true; break;
				case CAPTION_FONTFAMILY: addCaption = true; break;
				case CAPTION_POSITION: addCaption = true; break;
				case CAPTION_SIZE: addCaption = true; break;
			}
		}
		return addCaption;
	}
	
	private boolean addBox(List<ResourceViewSettingVO> settings) {
		boolean addBox = false;
		for (ResourceViewSettingVO s : settings) {
			DesignerConstants c = DesignerConstants.valueOf(s.getSettingName()); 
			switch (c) {
				case BOX_BACKGROUND_COLOR: addBox = true; break;
				case BOX_LINE_COLOR: addBox = true; break;
			}
		}
		return addBox;
	}
	
	private int[] getPixelCoordinates(ResourceViewSettingVO s) {
		int[] xy = new int[2];
		String coded = s.getValue();
		xy[0] = Integer.valueOf(coded.substring(0, coded.indexOf(':')));
		xy[1] = Integer.valueOf(coded.substring(1 + coded.indexOf(':')));
		return xy;
	}
	
	public DesignerTabItem addPortraitTabItem(String tabItemHeader) {
		DesignerTabItem designerTabItem = new DesignerTabItem(tabItemHeader, pageNumber);
		TabItem ti = designerTabItem.buildPortrait(pageNumber++);
		tabPanel.add(ti);
		tabPanel.setSelection(ti);
		ti.addListener(Events.Select, DesignerController.tabItemSelect(designerTabItem));
		ti.addListener(Events.Close, DesignerController.tabItemClose(this, designerTabItem));
		tabItemMap.put(ti, designerTabItem);
		tabItems.add(designerTabItem);
		return designerTabItem;
	}
	
	public DesignerTabItem addLandscapeTabItem(String tabItemHeader) {
		DesignerTabItem designerTabItem = new DesignerTabItem(tabItemHeader, pageNumber);
		TabItem ti = designerTabItem.buildLandscape(pageNumber++);
		tabPanel.add(ti);
		tabPanel.setSelection(ti);
		ti.addListener(Events.Select, DesignerController.tabItemSelect(designerTabItem));
		ti.addListener(Events.Close, DesignerController.tabItemClose(this, designerTabItem));
		tabItemMap.put(ti, designerTabItem);
		tabItems.add(designerTabItem);
		return designerTabItem;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public Map<TabItem, DesignerTabItem> getTabItemMap() {
		return tabItemMap;
	}

	public List<DesignerTabItem> getTabItems() {
		return tabItems;
	}
	
}