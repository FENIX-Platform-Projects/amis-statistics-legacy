package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.dnd.DropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.dom.client.Style.VerticalAlign;

public class FAOSTATdownloadPivotAxisPanel {
	
	ContentPanel panel;
	
	/** TODO: REMOVE **/
//	FAOSTATDownloadPivotDimension dimension;
	
	FAOSTATDownloadPivotDimensionList dimension;
	
	public FAOSTATdownloadPivotAxisPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		dimension = new FAOSTATDownloadPivotDimensionList();
	}
	
	
	public ContentPanel build(String title, FAOSTATDimensionConstant pivotFilterType){
		panel.removeAll();
		
		dimension.build(title, pivotFilterType);

		
		
		panel.add(addAxis(title, dimension.getPanel()));
		
		panel.layout();
		
		return panel;
	}
	
	private HorizontalPanel addAxis(String axis, ContentPanel defaultSelection) {
		final HorizontalPanel panel = new HorizontalPanel();
		
		panel.add(DataViewerClientUtils.addHSpace(5));
		
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		final VerticalPanel targetPanel = new VerticalPanel();
		targetPanel.setBorders(true);

		Html title = new Html("<div class='download_option_text'>" + axis + "</div>");
		title.setWidth(60);
		panel.add(title);
		
		panel.add(DataViewerClientUtils.addHSpace(5));
		
		panel.add(defaultSelection);
	
		
    
	    return panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}


	public FAOSTATDownloadPivotDimensionList getDimension() {
		return dimension;
	}
	
	

	
}
