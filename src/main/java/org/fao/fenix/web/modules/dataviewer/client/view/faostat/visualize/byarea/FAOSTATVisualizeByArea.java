package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.byarea;



import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByAreaController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualizeContainer;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;


public class FAOSTATVisualizeByArea extends FAOSTATVisualizeContainer  {

	final BorderLayout layout = new BorderLayout();  
	ContentPanel west; 
	public ContentPanel center;  
	BorderLayoutData westData;
	BorderLayoutData centerData;

	FAOSTATVisualizeSettingsVO settings;
	
	FAOSTATVisualizeByAreaFilters filtersPanel;

	protected void onRender(Element target, int index) {  
		
		super.onRender(target, index);   
		setLayout(layout);   
		setStyleAttribute("padding", "10px");      
		setStyleAttribute("backgroundColor", "#FFFFFF");   
		setBorders(false);	
		
		// TODO: changed the height for the new chart that has been added...
		//	setHeight(1000);
		setHeight(1590);

		settings = new FAOSTATVisualizeSettingsVO();
		
		filtersPanel = new FAOSTATVisualizeByAreaFilters();
		

		west = new ContentPanel();
		west.setHeading(FAOSTATLanguage.print().browseBy());

		center = new ContentPanel();  
		center.setHeaderVisible(false);
		center.setBodyBorder(false);
		
		/** TODO: get filters **/
		center.add(filtersPanel.build(null));
	

		FAOSTATVisualizeByAreaController.getAreas(this, center, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES_CONTINENTS.toString());

		west.add(buildWestPanelContents());

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);  
		westData.setSplit(true);   
		westData.setMaxSize(200);
		westData.setMinSize(150);
		westData.setCollapsible(true);   
		westData.setMargins(new Margins(0,5,0,0));   

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);   
		centerData.setMargins(new Margins(0,5,0,0));   

		add(west, westData);   
		add(center, centerData);   
	}   


	private FlexTable buildWestPanelContents() {
		FlexTable table = new FlexTable();   
		table.getElement().getStyle().setProperty("margin", "5px");   
		table.setCellSpacing(10);   
		table.setCellPadding(2);   

		
		ClickHtml countries = new ClickHtml();
		countries.setHtml(FAOSTATLanguage.print().countries());
		countries.setStyleName("selector-hyperlink");
		countries.addStyleName("area-hyperlink");
		
		countries.addListener(Events.OnClick, FAOSTATVisualizeByAreaController.addAreaList(this, center, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES_CONTINENTS.toString()));

		table.setWidget(0, 1, countries);

		return table;	
	}

	public Html addTitle(String title) {
		return new Html("<div class='domain-title'> " + title + "</div>");
	}


	public ContentPanel getCenterPanel() {
		return center;
	}

	public FAOSTATVisualizeSettingsVO getSettings() {
		return settings;
	}

	public FAOSTATVisualizeByAreaFilters getFiltersPanel() {
		return filtersPanel;
	}
}
