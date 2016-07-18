package org.fao.fenix.web.modules.amis.client.view.utils.filters;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.amis.client.control.AMISFilterController;
import org.fao.fenix.web.modules.amis.client.control.AMISProductionController;
import org.fao.fenix.web.modules.amis.client.view.home.AMISDatasource;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISFilterVO;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class AMISFilter {

	ComboBox<AMISCodesModelData> combo;
	ListStore<AMISCodesModelData> store;

	HashMap<String, String> selectedCodes ;

	AMISMultiselectionFilter multiselection;

	String title;

	AMISFilterVO filterVO;

	public AMISFilter() {

		combo = new ComboBox<AMISCodesModelData>();
		store = new ListStore<AMISCodesModelData>();

		selectedCodes = new HashMap<String, String>();

		multiselection = new AMISMultiselectionFilter();

		title = new String();
	}

	// TODO: Probably the title should be in the filterVO
	public HorizontalPanel buildStandardFilter(AMISDatasource visualization, AMISFilterVO filterVO, AMISCodesModelData code, String title) {
		this.filterVO = filterVO;
		this.title = title;

		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText("Please make a selection");
		combo.setStore(store);
		combo.setWidth(200);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());

		if ( filterVO.getUseCodingSystem() ) {
			// build dynamic drop down
			return buildWithCodingSystem(visualization, code);
		}
		else {
			// build with fixed drop down
			return buildWithFixedValues(visualization, code);
		}

	}

	private HorizontalPanel buildWithCodingSystem(AMISDatasource visualization, AMISCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setStyleAttribute("backgroundColor", "#FFFFFF");
		p.setVerticalAlign(VerticalAlignment.MIDDLE);


		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'>" +  title +": </div>"));
//		p.add(new Html("<div class='visualize_filters_combo'> " + BabelFish.print().getString(title) +" </div>"));
		p.add(FormattingUtils.addHSpace(10));
		p.add(combo);
		p.setHeight(25);

		if ( filterVO.getIsMultiSelection() )  {
			IconButton icon = new IconButton("addIcon");
			p.add(FormattingUtils.addHSpace(2));
			p.add(icon);

			/** TODO: do the multiselection is a more efficient way... (i.e. not use pass parameter _MULTI)**/
			icon.addSelectionListener(AMISFilterController.multiselectionFilter(visualization, this, filterVO.getFilterType() +"_MULTI", code));

		}
		p.add(FormattingUtils.addHSpace(2));

		// to avoid to call the combo
		combo.enableEvents(false);

		combo.addSelectionChangedListener(AMISFilterController.updateFilter(visualization, this, combo, filterVO.getFilterType(), code, selectedCodes));

		AMISProductionController.loadCodingSystem(combo, store, filterVO.getFilterType(), code, filterVO.getDefaultCodes(), selectedCodes, filterVO.getIsMultiSelection());

		return p;
	}


	private HorizontalPanel buildWithFixedValues(AMISDatasource visualization, AMISCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setStyleAttribute("backgroundColor", "#FFFFFF");
		p.setVerticalAlign(VerticalAlignment.MIDDLE);

		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " + title +": </div>"));
		p.add(FormattingUtils.addHSpace(10));
		p.add(combo);
		p.setHeight(25);

		if ( filterVO.getIsMultiSelection() )  {
			IconButton icon = new IconButton("addIcon");
			p.add(FormattingUtils.addHSpace(2));
			p.add(icon);

			/** TODO: do the multiselection is a more efficient way... (i.e. not use pass parameter _MULTI)**/
//			icon.addSelectionListener(FAOSTATVisualizeByDomainController.multiselectionFilter(visualization, this, filterVO.getFilterType() +"_MULTI", code));

		}
		p.add(FormattingUtils.addHSpace(2));

		// to avoid to call the combo
		combo.enableEvents(false);

		combo.addSelectionChangedListener(AMISFilterController.updateFilter(visualization, this, combo, filterVO.getFilterType(), code, selectedCodes));

		loadFixedValues();

		return p;
	}

	// TODO: Call the server to retrieve the codes and the labels (for now they are taken from the XML)
	private void loadFixedValues() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		store.removeAll();



			store.removeAll();
			selectedCodes.clear();

//			System.out.println("---------> DEFAULTCODES: " + defaultCodes);


			/** TODO: language... **/
			AMISCodesModelData multiselection =null;
			if ( filterVO.getIsMultiSelection() ) {
				multiselection = new AMISCodesModelData("MULTI", "Multiselection");
				store.add(multiselection);
			}

			for(AMISCodesModelData code : filterVO.getCodes()) {
				store.add(code);
				map.put(code.getCode(), code.getLabel());

				// defualt codes TODO: Optimze...
				if ( filterVO.getDefaultCodes() != null && !filterVO.getDefaultCodes().isEmpty() ) {
					if ( filterVO.getDefaultCodes().size() > 1 ) {
						// set MULTI (multiselection) as default code
						combo.setValue(multiselection);
					}
					else {
						 if (code.getCode().equals(filterVO.getDefaultCodes().get(0).getCode())) {
							 combo.setValue(code);
						}
					}
				}
				else {
					combo.setValue(store.getAt(0));
					selectedCodes.put(store.getAt(0).getCode(), store.getAt(0).getLabel());
				}
			}

			if ( filterVO.getDefaultCodes() != null && !filterVO.getDefaultCodes().isEmpty() ) {
				for(AMISCodesModelData code : filterVO.getDefaultCodes() ) {
					selectedCodes.put(code.getCode(), map.get(code.getCode()));
				}
			}

			System.out.println("-----> SELECTED CODES: " + selectedCodes);

			combo.enableEvents(true);

	}

	public ComboBox<AMISCodesModelData> getCombo() {
		return combo;
	}

	public ListStore<AMISCodesModelData> getStore() {
		return store;
	}

	public HashMap<String, String> getSelectedCodes() {
		return selectedCodes;
	}

	public AMISMultiselectionFilter getMultiselection() {
		return multiselection;
	}


	public native static String getTemplate() /*-{
	 return  [
	    '<tpl for=".">',
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>',
	    '</tpl>'
	    ].join("");
	  }-*/;

	public String getTitle() {
		return title;
	}


}
