package org.fao.fenix.web.modules.amis.client.view.download.table;

import org.fao.fenix.web.modules.amis.client.view.download.AMISDownloadOptions;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;

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

public class AMISDownloadFlatTable {
	
	CheckBox checkBox;
	
	ContentPanel panel;
	
	public AMISDownloadFlatTable() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

	}
	
	
	public ContentPanel build(AMISDownloadOptions options){
		panel.removeAll();
				
		VerticalPanel p = new VerticalPanel();
	
		p.add(buildTitle(options));
		

		
		panel.add(p);
		
		panel.layout();

		return panel;
	}
	
	private HorizontalPanel buildTitle(AMISDownloadOptions options) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		IconButton icon = new IconButton("info");
		Html html = new Html("<div class='download_option_text'>Table</div>");
		
		panel.add(html);
		
		return panel;
	}
	
	
	
	
	public CheckBox getCheckBox() {
		return checkBox;
	}


}
