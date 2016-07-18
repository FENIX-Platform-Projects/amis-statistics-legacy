/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.metadataeditor.client.control;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.ResourceSpecificPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.DatasetTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.PeriodTypeCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.SharingCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.FieldMetadataVo;
import org.fao.fenix.web.modules.metadataeditor.common.vo.MetadataVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MEOpener {

	public static void showMetadata(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					MetadataEditorWindow window = new MetadataEditorWindow();
					if (vo.getResourceType().equals(ResourceType.DATASET)) {
						window.build(true, isEditable, hasResourceSpecificFields);
						window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getMetadataTabItem());
						if (window.getTabPanel().getMetadataPanel().getPeriodList() != null)
							window.getTabPanel().getMetadataPanel().getPeriodList().setValue(new PeriodTypeCodeModelData(vo.getResourceVo().getPeriodTypeCode()));
						window.getTabPanel().getDatasetTypePanel().getDbListbox().setValue(new DatasetTypeModelData(vo.getDatasetTypeVo().getTitle()));
						window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
//						window.getTabPanel().getKeysPanel().getProviderGaulList().setValue(vo.getProviderGaulModelData());
						MEController.showDatasetDescriptors(vo.getDatasetTypeVo().getTitle(), window.getTabPanel(), false);
					} else {
						window.build(false, isEditable, hasResourceSpecificFields);
						window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getMetadataTabItem());
					}
					
					ResourceVO rvo = vo.getResourceVo();
					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					/*
					window.getTabPanel().getGeneralInfoPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getGeneralInfoPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getGeneralInfoPanel().getCategoryList().setValue(vo.getCategoryModelData());
					*/
					
					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getSourceGaulList().setValue(vo.getSourceGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
					
					window.getTabPanel().getExtraInfoPanel().getProviderGaulList().setValue(vo.getProviderGaulModelData());

					if (vo.getFieldMetadataVos() != null && vo.getFieldMetadataVos().length > 0) {
						SelectionListener<ButtonEvent> saveListener = MESaver.getUpdateLayerMetadataListener(window, resourceId);
						window.getButtonsPanel().getSave().addSelectionListener(saveListener);
					} else {
						SelectionListener<ButtonEvent> saveListener = MESaver.getUpdateMetadataListener(window, resourceId);
						window.getButtonsPanel().getSave().addSelectionListener(saveListener);
					}

//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};
			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}

	}

	public static void fillResourceSpecificFields(final MetadataVO mvo, final MetadataEditorWindow window) {
		if (mvo.getFieldMetadataVos() != null && mvo.getFieldMetadataVos().length > 0) {
			VerticalPanel wrapper = window.getTabPanel().getResourceSpecificPanel().getWrapper();
			ResourceSpecificPanel rsp = window.getTabPanel().getResourceSpecificPanel();
			for (FieldMetadataVo vo : mvo.getFieldMetadataVos()) {
				if (!vo.isBaseField) {
					String value = "";
					if (mvo.getResourceSpecificFieldMap().get(vo) != null)
						value = mvo.getResourceSpecificFieldMap().get(vo);
					else
						value = "n.a.";
					HorizontalPanel fieldPanel = rsp.buildTextPanel(vo.getName(), value, vo.isMandatory, !vo.isReadonly);
					wrapper.add(fieldPanel);
					if (wrapper.getLayout() != null)
						wrapper.getLayout().layout();
				}
			}
		} else {
			window.getTabPanel().getTabPanel().remove(window.getTabPanel().getResourceSpecificFields());
		}
	}

	public static void fillKeysPanel(MetadataEditorWindow window, MetadataVO vo) {
		System.out.println(vo.getResourceVo().getProvider());
		window.getTabPanel().getMetadataPanel().getSourceName().setValue(vo.getResourceVo().getSource());
		window.getTabPanel().getMetadataPanel().getSourceContact().setValue(vo.getResourceVo().getSourceContact());
//		window.getTabPanel().getKeysPanel().getProviderContact().setValue(vo.getResourceVo().getProviderContact());
//		window.getTabPanel().getKeysPanel().getProvider().setValue(vo.getResourceVo().getProvider());
		window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getSourceGaulModelData());
//		window.getTabPanel().getKeysPanel().getProviderGaulList().setValue(vo.getProviderGaulModelData());
	}

	@SuppressWarnings("unchecked")
	public static void fillGeneralInfoPanel(MetadataEditorWindow window, ResourceVO vo) {

		MetadataPanel panel = window.getTabPanel().getMetadataPanel();

		TextArea abstractArea = panel.getAbstractAbstract();
		abstractArea.setValue(vo.getAbstractAbstract());
		
		TextField<String> textField = panel.getTITLE();
		textField.setValue(vo.getTitle());

		TextField<String> keywordsField = panel.getKeywords();
		keywordsField.setValue(vo.getKeywords());

		TextField<String> codeField = panel.getCode();
		codeField.setValue(vo.getCode());
		
		TextField<String> providerContact = window.getTabPanel().getExtraInfoPanel().getProviderContact();
		providerContact.setValue(vo.getProviderContact());
		
		TextField<String> providerName = window.getTabPanel().getExtraInfoPanel().getProviderName();
		providerName.setValue(vo.getProvider());
		
		DateField startDate = window.getTabPanel().getExtraInfoPanel().getStartDate();
		startDate.setValue(vo.getStartDate());
		
		DateField endDate = window.getTabPanel().getExtraInfoPanel().getEndDate();
		endDate.setValue(vo.getEndDate());
		
		DateField dlu = window.getTabPanel().getExtraInfoPanel().getDateLastUpdate();
		dlu.setValue(vo.getDateLastUpdate());
		
	}

}