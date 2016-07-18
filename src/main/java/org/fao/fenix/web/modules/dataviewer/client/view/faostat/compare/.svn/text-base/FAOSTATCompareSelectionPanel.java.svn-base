package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATDataController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareDataController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguageConstants;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection.FAOSTATMultipleSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection.MultipleSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeTimerangeFilter;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CONSTANT;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.constants.FAOSTATCompareConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.Widget;

public class FAOSTATCompareSelectionPanel {
	
	ContentPanel panel;	
	
	ContentPanel selectionPanel;
	
	MultipleSelectionPanel areas;
	
	ContentPanel areasSelected;
	
	FAOSTATVisualizeTimerangeFilter timerange;
	
	ContentPanel domainsSelectors;
	
	Boolean isBuild = false;

	LinkedHashMap<String, FAOSTATMultipleSelectionPanel> domainSelectionPanels;
	
	FAOSTATCompareDataButtons buttonsPanel;
	
	public FAOSTATCompareSelectionPanel() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		selectionPanel = new ContentPanel();
		selectionPanel.setBodyBorder(false);
		selectionPanel.setHeaderVisible(false);
		areasSelected = new ContentPanel();
		areasSelected.setBodyBorder(false);
		areasSelected.setHeaderVisible(false);
		domainsSelectors = new ContentPanel();
		domainsSelectors.setBodyBorder(false);
		domainsSelectors.setHeaderVisible(false);
		domainsSelectors.setHeight(FAOSTATCompareConstants.SELECTION_PANEL_HEIGHT);
		domainsSelectors.setWidth(FAOSTATCompareConstants.SELECTION_PANEL_WIDTH);
		domainsSelectors.setScrollMode(Scroll.AUTOY);
		domainSelectionPanels = new LinkedHashMap<String, FAOSTATMultipleSelectionPanel>();
		areas = new MultipleSelectionPanel(CONSTANT.COUNTRY, FAOSTATCompareConstants.AREAS_PANEL_HEIGHT, FAOSTATCompareConstants.AREAS_LIST_WIDTH, FAOSTATCompareConstants.AREAS_LIST_HEIGHT, areasSelected);
		timerange = new FAOSTATVisualizeTimerangeFilter();
		buttonsPanel = new FAOSTATCompareDataButtons();
	}
	
	public ContentPanel build(FAOSTATCompareData compare) {
		isBuild = true;		
		panel.removeAll();		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(7);
		h.add(buildTopSelector(compare));
		h.add(DataViewerClientUtils.addHSpace(10));
		VerticalPanel domains = new VerticalPanel();
		Html title = new Html("<div class='content_title'>"+ FAOSTATLanguage.print().selectItemsElements()+"</div>");
		domains.add(title);
		domains.add(DataViewerClientUtils.addVSpace(2));
		domains.add(domainsSelectors);
		h.add(domains);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(DataViewerClientUtils.addVSpace(10));
		return panel;
	}
	
	public ContentPanel build2(FAOSTATCompareData compare) {
		isBuild = true;		
		panel.removeAll();
		panel.add(DataViewerClientUtils.addVSpace(30));
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(7);
		h.add(domainsSelectors);
		h.add(DataViewerClientUtils.addHSpace(10));
		h.add(buildTopSelector(compare));
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(DataViewerClientUtils.addVSpace(10));
		return panel;
	}

	private VerticalPanel buildTopSelector(FAOSTATCompareData compare) {
		VerticalPanel panel = new VerticalPanel();
		panel.setWidth(FAOSTATCompareConstants.AREAS_LIST_WIDTH);
		Html areasTitle = new Html("<div class='content_title'>"+ FAOSTATLanguage.print().selectCountries()+"</div>");
		panel.add(areasTitle);
		panel.add(DataViewerClientUtils.addVSpace(2));
		panel.add(areas);
		panel.add(areasSelected);
		areas.getGrid().addListener(Events.OnClick, FAOSTATCompareDataController.buildParameters(areasSelected, areas.getGrid(), FAOSTATLanguage.print().areas()));
		panel.add(DataViewerClientUtils.addVSpace(10));
//		Html timerangeTitle = new Html("<div class='content_title'>"+ FAOSTATLanguage.print().selectTimerange()+ "</div>");
//		panel.add(timerangeTitle);
		panel.add(addTimerange());
		panel.add(DataViewerClientUtils.addVSpace(20));
		panel.add(buttonsPanel.build(compare));
		return panel;
	}
	
	
	private VerticalPanel addTimerange() {
		// timerange
		VerticalPanel panel = new VerticalPanel();
		panel.add(DataViewerClientUtils.addVSpace(5));
		panel.add(timerange.build("TIMERANGE", null, 1961, 2010, FAOSTATCompareConstants.TIMERANGE_WIDTH));
		
		return panel;
	}
	
	public HorizontalPanel addDomainSelector(FAOSTATCompareData compare, String title, String domainCode) {
		if ( !isBuild ) {
			compare.getSelectionMainPanel().removeAll();
			compare.getSelectionMainPanel().add(build(compare));
			compare.getSelectionMainPanel().layout();
			// TODO: take all the countries... or based on the domain selected...P.S. issues with chaina trade
			FAOSTATDataController.fillGridWithFilter(areas.getGrid(), CONSTANT.COUNTRY.toString(), domainCode);
		}		
		HorizontalPanel panel = new HorizontalPanel();
//		closeAllSelectionPanels();
		FAOSTATMultipleSelectionPanel sp = new FAOSTATMultipleSelectionPanel(this, title, domainCode);
		domainsSelectors.insert(sp.build(), 0);
		domainsSelectors.layout();
		domainSelectionPanels.put(domainCode, sp);
		return panel;
	}
	
	private void closeAllSelectionPanels() {
		
		System.out.println("domainsSelectors.getItemCount(): " + domainsSelectors.getItemCount());

		if (  domainsSelectors.getItemCount() > 0 ) {
			try {
				for(int i = 0; i <  domainsSelectors.getItemCount(); i++) {
					FieldSet fieldSet = (FieldSet) domainsSelectors.getWidget(i);
					fieldSet.collapse();
					fieldSet.layout();
				}					
			}catch (Exception e) {}
		}
	}

	public LinkedHashMap<String, FAOSTATMultipleSelectionPanel> getDomainSelectionPanels() {
		return domainSelectionPanels;
	}

	public MultipleSelectionPanel getAreas() {
		return areas;
	}

	public FAOSTATVisualizeTimerangeFilter getTimerange() {
		return timerange;
	}

	public ContentPanel getAreasSelected() {
		return areasSelected;
	}
 

}
