package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadOptions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class FAOSTATDownloadFlatTable {
	
	CheckBox checkBox;
	
	ContentPanel panel;
	
	public FAOSTATDownloadFlatTable() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		checkBox = new CheckBox();
	}
	

	public ContentPanel build(FAOSTATDownload download, FAOSTATDownloadPivotSelectionPanel pivotTable){
		panel.removeAll();
		
//		panel.addStyleName("content_box");
		
		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
	
		p.add(buildTitle(pivotTable, download));
		
//		p.add(DataViewerClientUtils.addVSpace(5));

		
		panel.add(p);
		
		panel.layout();

		return panel;
	}
	
	private HorizontalPanel buildTitle(FAOSTATDownloadPivotSelectionPanel pivotTable,FAOSTATDownload download) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		IconButton icon = new IconButton("info");
		Html html = new Html("<div class='download_option_text'>"+FAOSTATLanguage.print().table()+"</div>");
		
		
		
		// DEFAULT
		checkBox = new CheckBox();
		checkBox.setValue(false);
//		checkBox.setValue(true);
		
		panel.add(checkBox);
		
		checkBox.addListener(Events.Change, changeCheckBox(pivotTable, download));
		
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		panel.add(icon);
		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(html);
		
		return panel;
	}
	
	
	
	
	public CheckBox getCheckBox() {
		return checkBox;
	}


	private Listener<FieldEvent> changeCheckBox(final FAOSTATDownloadPivotSelectionPanel pivotTable, final FAOSTATDownload download ) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				pivotTable.getCheckBox().enableEvents(false);
				pivotTable.getCheckBox().setValue(!checkBox.getValue());
				pivotTable.getCheckBox().enableEvents(true);
				
				System.out.println("checkobx: " + pivotTable.getCheckBox().getValue());
				
				if ( pivotTable.getCheckBox().getValue() ) {
					// show pivot table
						
					download.getOptions().showPivotOptions();
					
					if ( pivotTable.getIsFBS() ) {
						pivotTable.fsbStylePanel.show();
					}
					
					pivotTable.fsbCheckBoxAgent(download);
				}
				else {
					// remove flat table 
					
					pivotTable.getParametersPanel().hide();
					download.getOptions().hidePivotOptions();
					pivotTable.disableFBSView(download);
				}
	

			}
		};
	}


}
