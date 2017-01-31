package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;


public class FAOSTATVisualizeCompareItemFilter extends FAOSTATVisualizeFilter {

	
	
	public HorizontalPanel build(FAOSTATVisualizeByDomain visualization, FAOSTATVisualizeFilter filter, String title, String filterType, Boolean multi, DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		
		this.title = title;
		
		
		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		combo.setStore(store);
		combo.setWidth(100);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());  

		
		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " + BabelFish.print().getString(title) +" </div>"));
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(combo);
		p.setHeight(25);
		if ( multi )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(DataViewerClientUtils.addHSpace(2));
			p.add(icon);
			
			/** TODO: do the multiselection is a more efficient way... (i.e. not use pass parameter _MULTI)**/
			icon.addSelectionListener(FAOSTATVisualizeByDomainController.multiselectionFilter(visualization, this, filterType +"_MULTI", code));
			
		}
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		combo.enableEvents(false);
		
		combo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateFilter(visualization, this, combo, filterType, code, selectedCodes));
		
		
		
		FAOSTATVisualizeController.loadCodingSystem(combo, store, filterType, code,  multi);
		
		return p;
	}
	

	
}
