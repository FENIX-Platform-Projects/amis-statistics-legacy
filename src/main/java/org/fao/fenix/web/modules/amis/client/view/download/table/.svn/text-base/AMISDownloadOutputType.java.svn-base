package org.fao.fenix.web.modules.amis.client.view.download.table;

import org.fao.fenix.web.modules.amis.client.view.download.AMISDownloadOptions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

public class AMISDownloadOutputType {
	
	ContentPanel panel;
	
	AMISDownloadFlatTable flatTable;
	
	
	public AMISDownloadOutputType() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		flatTable = new AMISDownloadFlatTable();	
	}

		public ContentPanel build(AMISDownloadOptions options) {
		panel.removeAll();
		
		FieldSet fieldSet = new FieldSet();
		
		fieldSet.setCollapsible(true);
		fieldSet.setHeading("Output Options");

		fieldSet.add(flatTable.build(options));
		
		panel.add(fieldSet);
		
		return panel;
	}


	public AMISDownloadFlatTable getFlatTable() {
		return flatTable;
	}

	
}
