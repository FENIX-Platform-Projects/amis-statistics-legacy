package org.fao.fenix.web.modules.cpf.client.control.find;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.cpf.client.view.find.CPFFindPanel;
import org.fao.fenix.web.modules.cpf.client.view.find.CPFSearchOutput;
import org.fao.fenix.web.modules.cpf.common.constants.CPFConstants;
import org.fao.fenix.web.modules.cpf.common.model.CPFCodesModelData;
import org.fao.fenix.web.modules.cpf.common.services.CPFServiceEntry;
import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;
import org.fao.fenix.web.modules.cpf.common.vo.CPFResultVO;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CPFFindController {


	public static SelectionChangedListener<BaseModel> filterCriteria(final ComboBox<BaseModel> combo, final TextField<String> textField) {
			return new SelectionChangedListener<BaseModel>() {
				public void selectionChanged(SelectionChangedEvent<BaseModel> se) {
					if(combo.getValue().get("code").equals("*")) {
					//	textField.setEnabled(false);
						//get all CPFS
					} else {
					//	textField.setEnabled(true);
						System.out.println("combo.getValue().get('code') = "+combo.getValue().get("code"));
					}
				}
			};
	}
	
	
	public static SelectionChangedListener<BaseModel> setValue(final ComboBox<BaseModel> combo) {
		return new SelectionChangedListener<BaseModel>() {
			public void selectionChanged(SelectionChangedEvent<BaseModel> se) {
				System.out.println("VALUE FIELD = "+combo.getValueField() + " | "+combo.getValue().get("title"));
			}
		};
}
	

	public static KeyListener findTextFieldEnterKeyListener(final CPFFindPanel findPanel, final TextField<String> findTextField) {
		return new KeyListener() {
			public void componentKeyPress(ComponentEvent event) {
				 if(event.getKeyCode()==13)
		            {
					// findAction(findPanel, findTextField);
		            }
		            super.componentKeyPress(event);
			}
		};
	}

	public static Listener<ComponentEvent> findTextFieldListener(final CPFFindPanel findPanel, final TextField<String> findTextField, final ComboBox<BaseModel> findByCombo) {
		return new Listener<ComponentEvent>() {

			public void handleEvent(ComponentEvent ce) {
				 findAction(findPanel, findTextField, findByCombo);
			}
		};
	}


	public static Listener<ComponentEvent> findOnClickListener(final CPFFindPanel findPanel, final TextField<String> findTextField,  final ComboBox<BaseModel> findByCombo) {
		return new Listener<ComponentEvent>() {

			public void handleEvent(ComponentEvent ce) {
				// findAction(findPanel, findTextField, findByCombo);
			}
		};
	}


	private static void findAction(final CPFFindPanel findPanel, final TextField<String> findTextField,  final ComboBox<BaseModel> findByCombo) {

		System.out.println("findAction: findTextField value = " + findTextField.getValue());
		System.out.println("findAction: findByCombo code value = " + findByCombo.getValue().get("code"));
		System.out.println("findAction: currentString =  " + findPanel.currentFindValue);
		
		String textBoxValue = findTextField.getValue();
		
	/**	if (!findPanel.currentFindValue.equals(findTextField.getValue()) ) {
			if(findByCombo.getValue().get("code").equals("*")) {
				List<String> values = new ArrayList<String>();
				
				// CPF Names
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getCpfNames(), textBoxValue, values);

				// CPF Codes
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getCpfCodes(), textBoxValue, values);

				// Countries
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getCountries(), textBoxValue, values);

				// FAO Strategic Frameworks
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getFAOStrategicFrameworks(), textBoxValue, values);
				//output.build(values);

			}	
			else if (findByCombo.getValue().get("code")){
				// CPF Codes
				List<String> values = new ArrayList<String>();
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getCpfCodes(), textBoxValue, values);					
				//output.build(values);
				
			} else if (findByCombo.getValue().get("name")){
				// CPF Names
				List<String> values = new ArrayList<String>();
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getCpfNames(), textBoxValue, values);
				//output.build(values);
			
			}else if (findByCombo.getValue().get("or")){
				//  FAO Strategic Frameworks
				List<String> values = new ArrayList<String>();
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getFAOStrategicFrameworks(), textBoxValue, values);	
				//output.build(values);
			}else if (findByCombo.getValue().get("country")){
				// CPF Country
				List<String> values = new ArrayList<String>();
				values = getFilterValuesFromCache(findPanel.getCpfSearchCache().getCountries(), textBoxValue, values);	
				//output.build(values);
			}
		}**/
       
		findPanel.currentFindValue = findTextField.getValue();
		
	}


	public static void loadCodingSystem(final List<String> values, LinkedHashMap<String, String> valuesMap, final String filterType)  {

			System.out.println("loadCodingSystem");
			CPFQueryVO qvo =  new CPFQueryVO();
			qvo.setOutput(CPFConstants.CODING_SYSTEM.toString());
			qvo.setTypeOfOutput(filterType);

			try {
				CPFServiceEntry.getInstance().askCPF(qvo, new AsyncCallback<CPFResultVO>() {

					@SuppressWarnings("unchecked")
					public void onSuccess(CPFResultVO vo) {

						System.out.println("call returned loading the coding systems...: " + filterType);

						for(CPFCodesModelData code : vo.getCodes()) {
							values.add(code.getLabel().toLowerCase());
	//						values.put(code.getCode(), code.getLabel());
						}

						System.out.println("end loading the coding systems...: " + filterType);
					}

					public void onFailure(Throwable arg0) {

					}
				});
			} catch (FenixGWTException e) {

				e.printStackTrace();
			}

	}
	
	private static List<String> getFilterValuesFromCache(List<String> cachedItemList, String textBoxValue, List<String> filterList){
		
		if (textBoxValue.length() > 2 ) {
			for(Integer i=0; i < cachedItemList.size(); i ++) {
				if ( cachedItemList.get(i).toLowerCase().contains(textBoxValue.toLowerCase())) {
					cachedItemList.add(cachedItemList.get(i));
				}
			}
		}
		
		return filterList;
	}
	
	
	public static Listener<ComponentEvent> setTextBox(final CPFSearchOutput searchOracleOutput, final TextField<String> searchTextBox, final String label) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				setTextBoxAgent(searchOracleOutput, searchTextBox, label);
			}
		};
	}
	
	/** TODO: no delete all the text...**/
	public static void setTextBoxAgent(final CPFSearchOutput searchOracleOutput, final TextField<String> searchTextBox, final String label) {
		searchOracleOutput.getPanel().hide();
		
		searchTextBox.setValue(label);
	
		/** lauch the search **/
		//checkClickedStrings(searchTextBox, label);
		
	}
	
}