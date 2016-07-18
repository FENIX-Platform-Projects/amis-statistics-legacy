package org.fao.fenix.web.modules.amis.client.view.utils.filters;

import java.util.List;

import org.fao.fenix.web.modules.amis.client.control.AMISFilterController;
import org.fao.fenix.web.modules.amis.client.control.AMISMultiSelectionController;
import org.fao.fenix.web.modules.amis.client.control.AMISProductionController;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.home.AMISDatasource;
import org.fao.fenix.web.modules.amis.client.view.home.AMISPsd;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;


import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class AMISAreasFilter extends AMISFilter {

	public HorizontalPanel buildAggregatedAreasFilter(AMISDatasource visualization, String title, String filterType, Boolean isMultiselection, AMISCodesModelData code, List<AMISCodesModelData> defaultCodes) {
		System.out.println("buildAggregatedAreasFilter: START "+title);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);

		this.title = title;

		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText("Please make a selection");
		combo.setStore(store);
		combo.setWidth(100);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());

		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " +  AMISLanguage.print().getString(title) +" </div>"));
		p.add(FormattingUtils.addHSpace(5));
		p.add(combo);
		p.setHeight(25);
		if ( isMultiselection )  {
			IconButton icon = new IconButton("addIcon");
			p.add(FormattingUtils.addHSpace(2));
			p.add(icon);


			/** TODO: do the multiselection is a more efficient way...**/
			icon.addSelectionListener(AMISMultiSelectionController.multiselectionAggregatedAreas(visualization, this, filterType + "_MULTI", code));
		}
		p.add(FormattingUtils.addHSpace(2));

		// to avoid to call the combo
		combo.enableEvents(false);

		// listener TODO: should be passed?
		combo.addSelectionChangedListener(AMISFilterController.updateAggregatedAreasFilter(visualization, this, combo, filterType, code, selectedCodes));

//		FAOSTATVisualizeByDomainController.loadCodingSystem(combo, store, filterType, code);
		AMISProductionController.loadCodingSystem(combo, store, filterType, code, defaultCodes, selectedCodes, isMultiselection);


		return p;
	}


}
