package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

// extends something...
public class FAOSTATVisualizeByTopicFilters {

	public ContentPanel panel;
	
	
	public FAOSTATVisualizeByTopicFilters() { 
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
	}
	
	public ContentPanel build() {	
		HorizontalPanel p = new HorizontalPanel();
		
		
		p.add(new Html("<div class='visualize_filters_title'>"+FAOSTATLanguage.print().choose()+":<diiv>"));
		p.add(addHSpace(15));
		p.add(buildFilter(FAOSTATLanguage.print().element(), true));
		p.add(addHSpace(10));
		p.add(buildFilter(FAOSTATLanguage.print().area(), true));
		p.add(addHSpace(10));
		p.add(buildFilter(FAOSTATLanguage.print().fromYear(), false));
		p.add(addHSpace(10));
		p.add(buildFilter(FAOSTATLanguage.print().toYear(), false));
		p.add(addHSpace(10));
		
		panel.add(p);
		
		return panel;
	}
	
	private HorizontalPanel buildFilter(String title, Boolean multi) {
		HorizontalPanel p = new HorizontalPanel();
		
		ComboBox<DWCodesModelData> combo = new ComboBox<DWCodesModelData>();
		ListStore<DWCodesModelData> store = new ListStore<DWCodesModelData>();
		combo.setStore(store);
		combo.setWidth(100);
		
		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " + title +" </div>"));
		p.add(addHSpace(5));
		p.add(combo);
		p.setHeight(25);
		if ( multi )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(addHSpace(2));
			p.add(icon);
		}
		
		return p;
	}
	
	private HorizontalPanel addHSpace(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}
}

