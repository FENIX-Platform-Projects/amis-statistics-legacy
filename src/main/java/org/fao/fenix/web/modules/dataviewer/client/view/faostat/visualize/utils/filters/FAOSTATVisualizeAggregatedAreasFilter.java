package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
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

public class FAOSTATVisualizeAggregatedAreasFilter extends FAOSTATVisualizeFilter {
	
	
			
	/** TODO: change the FAOSTATVisualizeByDomain **/
	public HorizontalPanel buildAggregatedAreasFilter(FAOSTATVisualize visualization, org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter filterVO, String title, String filterType, Boolean isMultiselection, DWCodesModelData code, List<DWCodesModelData> defaultCodes, Boolean disaggregate) {
		this.filterVO = filterVO;
		
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		this.title = title;

		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		combo.setStore(store);
		
		/** TODO: set it dinamically from the qvo**/
		combo.setWidth(150);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());  
		
		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " +  FAOSTATLanguage.print().getString(title) +" </div>"));
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(combo);
		p.setHeight(25);
		if ( isMultiselection )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(DataViewerClientUtils.addHSpace(2));
			p.add(icon);
			

			/** TODO: do the multiselection is a more efficient way...**/
			icon.addSelectionListener(FAOSTATVisualizeByDomainController.multiselectionAggregatedAreas(visualization, this, filterType + "_MULTI", code));
		}
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		combo.enableEvents(false);
		
		// listener TODO: should be passed?
		combo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateAggregatedAreasFilter(visualization, this, combo, filterType, code, selectedCodes, disaggregate));
		
		if ( !filterVO.getDomains().isEmpty() ) {
			for(String key : filterVO.getDomains().keySet() ) {
				/** TODO: should be multidomains **/
				code = new DWCodesModelData(key, filterVO.getDomains().get(key));
			}
		}
		
		FAOSTATVisualizeController.loadCodingSystem(combo, store, filterType, code, defaultCodes, selectedCodes, isMultiselection);
		return p;
	}
	
	
}
