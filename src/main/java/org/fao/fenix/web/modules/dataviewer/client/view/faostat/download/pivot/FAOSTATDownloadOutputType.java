package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadOptions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

public class FAOSTATDownloadOutputType {
	
	ContentPanel panel;
	
	FAOSTATDownloadFlatTable flatTable;
	
	FAOSTATDownloadPivotSelectionPanel pivotTable;
	
	public FAOSTATDownloadOutputType() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		flatTable = new FAOSTATDownloadFlatTable();	
		pivotTable = new FAOSTATDownloadPivotSelectionPanel();
	}

	
	public ContentPanel build(FAOSTATDownload download, Boolean isFBS, Boolean isTradeMatrix) {
		panel.removeAll();
		
		FieldSet fieldSet = new FieldSet();
		
		fieldSet.setCollapsible(true);
		fieldSet.setHeading(FAOSTATLanguage.print().outputOptions());

	
		fieldSet.add(flatTable.build(download, pivotTable));
				
		fieldSet.add(pivotTable.build(download, flatTable, isFBS, isTradeMatrix));
		
		panel.add(fieldSet);
		
		return panel;
	}


	public FAOSTATDownloadFlatTable getFlatTable() {
		return flatTable;
	}


	public FAOSTATDownloadPivotSelectionPanel getPivotTable() {
		return pivotTable;
	}
	
	
}
