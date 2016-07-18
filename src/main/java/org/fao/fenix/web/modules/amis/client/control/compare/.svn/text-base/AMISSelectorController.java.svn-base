package org.fao.fenix.web.modules.amis.client.control.compare;


import java.util.List;

import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompare;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompareSelectorMaker;
import org.fao.fenix.web.modules.amis.client.view.compare.utils.AMISSelectorPanel;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginPanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;

import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISSelectorController {

	public static void getSelectors(final AMISSelectorPanel panel, final ComboBox<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store,AMISQueryVO qvo, String selectionType, final Boolean addSelectAll, final AMISCodesModelData modelToSetAsSelected) {
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
					panel.getPanel().removeAll();
					store.removeAll();


					for(AMISCodesModelData topcode : vo.getCodes()) {
						store.add(topcode);

					}

					if(combo!=null && combo.getStore().getCount() > 0) {
						if(modelToSetAsSelected!=null) {
							AMISCodesModelData foundModel =  combo.getStore().findModel("code", modelToSetAsSelected.getCode());

							if(foundModel!=null)
								combo.setValue(foundModel);
							else
								combo.setValue(combo.getStore().getAt(0));
						}
						else
							combo.setValue(combo.getStore().getAt(0));

					}


				}

				public void onFailure(Throwable arg0) {

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void getSelectors(final AMISCompare compare, final AMISSelectorPanel panel, final CheckBoxListView<AMISCodesModelData> checkBoxView, final ListStore<AMISCodesModelData> store, final AMISQueryVO qvo, String selectionType, final Boolean addSelectAll, final AMISCodesModelData modelToSetAsSelected) {
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
					panel.getPanel().removeAll();
					store.removeAll();

					
					if(qvo.getTypeOfOutput().equals(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.name()) && compare.getColumn3().getItemByItemId("message")!=null) {
						Html html = (Html)compare.getColumn3().getItemByItemId("message");
						compare.getColumn3().remove(html);
					}

					//System.out.println("---------> getSelectors: getcodes " + vo.getCodes().size());

					for(AMISCodesModelData topcode : vo.getCodes()) {
						store.add(topcode);
					}


					if(checkBoxView!=null && checkBoxView.getStore().getCount() == 0 && qvo.getTypeOfOutput().equals(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.name())) {
						Html message = new Html("<p class='no_items_message'>There are no elements in common, so please re-select the products</p>");						
						message.setStyleName("no_items_message");
						message.setId("message");
						checkBoxView.hide();
						compare.getColumn3().insert(message, 1);
						compare.getColumn3().layout();
					}
						
				/**	if(checkBoxView!=null && checkBoxView.getStore().getCount() > 0) {

						if(modelToSetAsSelected!=null) {
							AMISCodesModelData foundModel = checkBoxView.getStore().findModel("code", modelToSetAsSelected.getCode());

							if(foundModel!=null)
								checkBoxView.setChecked(foundModel, true);

							else
								System.out.println("----------- getSelectors: checkBoxView .... foundModel IS NULL "+modelToSetAsSelected.getLabel());

						}

					}**/
					
					//System.out.println("---------> getSelectors: view store " + checkBoxView.getStore().getCount());

					

				}

				public void onFailure(Throwable arg0) {

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Listener<FieldEvent> getDataSourceCountryListListener(final AMISCompare compare, final CheckBoxGroup checkBoxGroup, final AMISCompareSelectorMaker selectorMaker) {
		return  new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
			
			//For National users
//				if((AMISLoginPanel.getAmisUserParameters().getUsername()!=null)&&(!AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT)))
			/**	if((AMISLoginPanel.getAmisUserParameters()!=null)&&(AMISLoginPanel.getAmisUserParameters().getUsername()!=null)&&(!AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT)))
				{ 

					Component areaSelector = compare.getColumn1().getItemByItemId(AMISConstants.AMIS_COUNTRIES.toString());
					CheckBoxListView<AMISCodesModelData> areaList = (CheckBoxListView<AMISCodesModelData>) areaSelector;

					List<CheckBox> datasourceCheckBoxes = checkBoxGroup.getValues();

					if(!datasourceCheckBoxes.isEmpty()) {	
						//If only the National Data Source is selected then the country list should only contain the registered user's country
						if(datasourceCheckBoxes.size() == 1 && datasourceCheckBoxes.get(0).getId().equals(AMISConstants.NATIONAL.toString())) {
							if(areaSelector!=null) {
								areaList.getStore().removeAll();
								areaList.getStore().add(new AMISCodesModelData(CCBS.COUNTRY_CODES.get(0), CCBS.COUNTRY_NAMES.get(0)));
							}
						}
						//Otherwise the full country list should display
						else {
							if(areaList.getStore().getCount() == 1)
								AMISCompareController.rebuildCountriesSelector(compare, selectorMaker);
						}
					} else {
						// If no data source is selected and the country list only contains the registered user country
						// Re-populate with the full country list
						if(areaList.getStore().getCount() == 1) 
							AMISCompareController.rebuildCountriesSelector(compare, selectorMaker);
					}

				}	 **/
			}
		};
	}

}
