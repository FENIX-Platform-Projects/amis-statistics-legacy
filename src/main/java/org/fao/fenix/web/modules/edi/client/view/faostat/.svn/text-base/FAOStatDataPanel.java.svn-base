package org.fao.fenix.web.modules.edi.client.view.faostat;

import java.util.Date;

import org.fao.fenix.web.modules.edi.client.view.EDIPanel;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.ListBox;

public class FAOStatDataPanel extends EDIPanel {

	private ListBox groupList;
	
	private ListBox domainList;
	
	private ListBox areaList;
	
	private ListBox yearList;
	
	private ListBox itemList;
	
	private ListBox elementList;
	
	private VerticalPanel wrapper;
	
	private Button addToFilterButton;
	
	private Button removeFromFilterButton;
	
	private Html filterSummary = new Html("<I>Filters summary here.</I>");
	
	private String FIELD_WIDTH = "170px";
	
	private String LABEL_WIDTH = "170px";
	
	private String FIELD_HEIGHT = "25px";
	
	private int VISIBLE_ITEM_COUNT = 7;
	
	public FAOStatDataPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		groupList = new ListBox();
		domainList = new ListBox();
		areaList = new ListBox();
		yearList = new ListBox();
		itemList = new ListBox();
		elementList = new ListBox();
		wrapper = new VerticalPanel();
	}
	
	@Override
	public TabItem build() {
		
		HorizontalPanel groupDomainPanel = new HorizontalPanel();
		groupDomainPanel.setSpacing(getSPACING());
		groupDomainPanel.add(buildGroupPanel());
		groupDomainPanel.add(buildDomainPanel());
		wrapper.add(groupDomainPanel);
		
		HorizontalPanel areaYearPanel = new HorizontalPanel();
		areaYearPanel.setSpacing(getSPACING());
		areaYearPanel.add(buildItemPanel());
		areaYearPanel.add(buildYearPanel());
		wrapper.add(areaYearPanel);
		
		HorizontalPanel itemElementPanel = new HorizontalPanel();
		itemElementPanel.setSpacing(getSPACING());
		itemElementPanel.add(buildAreaPanel());
		itemElementPanel.add(buildElementPanel());
		wrapper.add(itemElementPanel);
		
		wrapper.add(buildButtonsPanel());
		wrapper.add(buildFilterSummaryPanel());
		
		this.getTabItem().add(wrapper);
		return this.getTabItem();
	}
	
	private VerticalPanel buildGroupPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='#0BAE3A'>1 - Group</font></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		groupList.setWidth(FIELD_WIDTH);
		groupList.addItem("Please Select a Group...");
		groupList.addItem(EDISettings.ECONOMIC.name(), "E");
		groupList.addItem(EDISettings.FORESTRY.name(), "F");
		groupList.addItem(EDISettings.PRICES.name(), "P");
		groupList.addItem(EDISettings.PRODUCTION.name(), "Q");
		groupList.addItem(EDISettings.RESOURCES.name(), "R");
		groupList.addItem(EDISettings.SUA.name(), "S");
		groupList.addItem(EDISettings.TRADE.name(), "T");
		p.add(groupList);
		return p;
	}
	
	private VerticalPanel buildDomainPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='#0BAE3A'>2 - Domain</font></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		domainList.setWidth(FIELD_WIDTH);
		p.add(domainList);
		return p;
	}
	
	private VerticalPanel buildAreaPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='#1D4589'>5 - Area</font></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		areaList.setWidth(FIELD_WIDTH);
		areaList.setMultipleSelect(true);
		areaList.setVisibleItemCount(VISIBLE_ITEM_COUNT);
		p.add(areaList);
		return p;
	}
	
	private VerticalPanel buildYearPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='#FA8607'>4 - Year</font></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		yearList.setWidth(FIELD_WIDTH);
		yearList.setMultipleSelect(true);
		yearList.setVisibleItemCount(VISIBLE_ITEM_COUNT);
		p.add(yearList);
		for (int i = (1900 - 1 + new Date().getYear()) ; i >= 1961 ; i--)
			yearList.addItem(String.valueOf(i), String.valueOf(i));
		return p;
	}
	
	private VerticalPanel buildItemPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='#FA8607'>3 - Item</font></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		itemList.setWidth(FIELD_WIDTH);
		itemList.setMultipleSelect(true);
		itemList.setVisibleItemCount(VISIBLE_ITEM_COUNT);
		p.add(itemList);
		return p;
	}
	
	private VerticalPanel buildElementPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='#1D4589'>6 - Element</font></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		elementList.setWidth(FIELD_WIDTH);
		elementList.setMultipleSelect(true);
		elementList.setVisibleItemCount(VISIBLE_ITEM_COUNT);
		p.add(elementList);
		return p;
	}
	
	private VerticalPanel buildFilterSummaryPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING() + getSPACING());
		Html l = new Html("<b><u><font color='#1D4589'>Filter Summary</font></u></b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		filterSummary.setSize(getFIELD_WIDTH(), FIELD_HEIGHT);
		p.add(filterSummary);
		return p;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(getSPACING() + getSPACING());
		addToFilterButton = new Button("Add Selection To Filter");
		addToFilterButton.setWidth(FIELD_WIDTH);
		addToFilterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				FenixAlert.info(BabelFish.print().info(), "This feature has not been implemented yet.");
			}
		});
		removeFromFilterButton = new Button("Remove Selection From Filter");
		removeFromFilterButton.setWidth(FIELD_WIDTH);
		removeFromFilterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				FenixAlert.info(BabelFish.print().info(), "This feature has not been implemented yet.");
			}
		});
		p.add(addToFilterButton);
		p.add(removeFromFilterButton);
		return p;
	}

	public ListBox getGroupList() {
		return groupList;
	}

	public ListBox getDomainList() {
		return domainList;
	}

	public ListBox getAreaList() {
		return areaList;
	}

	public ListBox getYearList() {
		return yearList;
	}

	public ListBox getItemList() {
		return itemList;
	}

	public ListBox getElementList() {
		return elementList;
	}

	public Button getAddToFilterButton() {
		return addToFilterButton;
	}

	public Button getRemoveFromFilterButton() {
		return removeFromFilterButton;
	}

	public Html getFilterSummary() {
		return filterSummary;
	}
	
}