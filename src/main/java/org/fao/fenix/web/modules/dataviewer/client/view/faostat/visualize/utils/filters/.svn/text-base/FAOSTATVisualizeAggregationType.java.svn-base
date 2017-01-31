package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class FAOSTATVisualizeAggregationType extends FAOSTATVisualizeFilter {
	
	
			
	public HorizontalPanel build(FAOSTATVisualize visualization, org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter filterVO, String title, String filterType, Boolean multi, DWCodesModelData code, List<DWCodesModelData> defaultCodes) {
		this.filterVO = filterVO;
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		this.title = title;

		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		combo.setStore(store);
		combo.setWidth(80);
		combo.setTriggerAction(TriggerAction.ALL);
		

		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " + FAOSTATLanguage.print().getString("aggregation") + "</div>"));
		p.add(DataViewerClientUtils.addHSpace(2));
		p.add(combo);
		p.setHeight(25);
		if ( multi )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(DataViewerClientUtils.addHSpace(2));
			p.add(icon);
		}
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		combo.enableEvents(false);

		
		// listener TODO: should be passed?
		combo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateFilter(visualization, this, combo, filterType, code, selectedCodes));

		// TODO: Dynamically? LOAD Aggregation
		store.add(new DWCodesModelData("AVG", FAOSTATLanguage.print().average()));
		store.add(new DWCodesModelData("SUM", FAOSTATLanguage.print().sum()));
	
		
		// TODO: set a default code	
		combo.setValue(store.getAt(0));
		selectedCodes.put(store.getAt(0).getCode(), store.getAt(0).getLabel());
		combo.enableEvents(true);

		
		return p;
	}
	
	
}
