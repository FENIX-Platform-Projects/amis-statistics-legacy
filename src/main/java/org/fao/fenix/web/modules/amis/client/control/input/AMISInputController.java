package org.fao.fenix.web.modules.amis.client.control.input;

import java.util.ArrayList;

import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInputNew;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class AMISInputController {
//	private static ListStore<AMISCodesModelData> store;

	/** 
	 * 
	 * This method creates the download panel
	 * 
	 */
	public static void callInputView(AMISMainMenu mainMenu) {
		//System.out.println("Class: AMISInputController Function: callInputView Text:Start");
		RootPanel.get("OLAP_IFRAME").setVisible(false);
		RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
		RootPanel.get("MAIN_CONTENT").add(new AMISInputNew(mainMenu).buildInputUpload());
		RootPanel.get("COMPARE_NOTES").setVisible(false);
		//RootPanel.get("WHITE_SPACE").setVisible(false);
		//System.out.println("Class: AMISInputController Function: callInputView Text:End");
	}
	
	public static void getElementsForComboBox(ListStore<AMISCodesModelData> countries, String output, String typeOfOutput, String selectedDataset, boolean element, ComboBox<AMISCodesModelData> combo)
	{
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(output);
		qvo.setTypeOfOutput(typeOfOutput);	
		qvo.setSelectedDataset(selectedDataset);
		AMISConstantsVO.setLanguageSettings(qvo);		
		//qvo.setDomains(buildDomain(domainFilter));
		//qvo.setGetTotalAndList(isTotalAndList);
		ArrayList<String> codeList = new ArrayList();
		codeList.add("'QC'");
		codeList.add("'TP'");
		codeList.add("'PC'");
		qvo.setCodeList(codeList);
		getStoreForComboBox(qvo, countries, element, combo);
		System.out.println("Before return Store ...size ");
	}
	
	
	//public static void getSelectors(final AMISDownloadSelectorPanel panel, AMISQueryVO qvo, String selectionType, final Boolean addSelectAll) {
	public static void getStoreForComboBox(AMISQueryVO qvo, final ListStore<AMISCodesModelData> countries, final boolean element, final ComboBox<AMISCodesModelData> combo) {
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
					
					for(AMISCodesModelData topcode : vo.getCodes()) {
						countries.add(topcode);
					}
					System.out.println("Store ...size "+countries.getCount());
					
					if(element)
					{
						//Case element
						int index = 0;
						String app = null;
						for(int i= 0; i< countries.getCount(); i++)
						{
							app = countries.getAt(i).getLabel();
							index = app.indexOf("(");
							app = app.substring(0, index);
							countries.getAt(i).setLabel(app);
							System.out.println("i =  "+i+ " Label = "+countries.getAt(i).getLabel());
						}
						
					}
					
					if(countries.getCount()>0)
					{
					  	combo.setValue(countries.getAt(0));
					}
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public static void doLogout() {
		UserServiceEntry.getInstance().logout(new AsyncCallback<String>() {
			public void onSuccess(String result) {
				FenixUser.giveAnonymousRole();
				RootPanel.get("USER_LOGIN_WELCOME").clear();
				//new AMISInput().buildInputUpload();
				if((History.getToken()!=null)&&(History.getToken().length()>0))
				{
					System.out.println(" AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
				}
				System.out.println(" AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
				if(AMISController.currentSelectedView.equals(AMISCurrentView.INPUT))
				{
					int widgetCount = RootPanel.get("MAIN_CONTENT").getWidgetCount();
					
					for(int i=0; i<widgetCount; i++)
					{
						RootPanel.get("MAIN_CONTENT").getWidget(i).removeFromParent();
					}					
					RootPanel.get("MAIN_CONTENT").add(new AMISInput().buildInputUpload());
				}
				//Fenix.buildMenu(true, true);
			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
				RootPanel.get("USER_LOGIN_WELCOME").clear();
			}
		});
	}*/
}