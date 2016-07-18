package org.fao.fenix.web.modules.amis.client.control.download;


import java.util.*;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.objects.AMISFaostatPivotTableController;
import org.fao.fenix.web.modules.amis.client.view.download.*;
import org.fao.fenix.web.modules.amis.client.view.download.foodbalance.AMISDownloadFoodBalance;
import org.fao.fenix.web.modules.amis.client.view.download.table.AMISDownloadOutputType;
import org.fao.fenix.web.modules.amis.client.view.download.table.AMISDownloadTablePanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISDownloadController {
	
	public static Integer tokensAsync = 0;
	
	public static Integer tokensReached = 0;
	
	private static Timer timer;
	
	public static List<AMISCodesModelData> radioList;

    static AMISDownloadEntry downloadViewEntry;

    public static boolean isFoodBalance = false;

    /**
	 * 
	 * This method creates the download panel
	 * 
	 */

    public static void callDownloadView() {
        downloadViewEntry = new AMISDownloadEntry(AMISCurrentView.DOWNLOAD_STANDARD);
        callDownloadViewAgent(AMISCurrentView.DOWNLOAD_STANDARD);
    }

    public static void callDownloadSubView(AMISCurrentView subView) {
        downloadViewEntry = new AMISDownloadEntry(subView);
        callDownloadViewAgent(subView);
    }

    public static void callDownloadViewAgent(AMISCurrentView view) {
        downloadViewEntry.build(view);
    }

	/**public static void callDownloadView() {
		RootPanel.get("OLAP_IFRAME").setVisible(false);
		RootPanel.get("COMPARE_NOTES").setVisible(false);
		RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
		//RootPanel.get("WHITE_SPACE").setVisible(false);
		RootPanel.get("MAIN_CONTENT").add(new AMISDownload().build());
	}**/

	public static void getQVOParameters(AMISDownload download, AMISQueryVO qvo) {

		HashMap<String, String> areas = new HashMap<String, String>();
        if(isFoodBalance)  {
            if(AMISFoodBalanceDownloadController.selectedByOptionModel==null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="COUNTRY")    {
                getCodes(areas, download.getAreas().getSelectorPanel().getList().getSelectionModel().getSelectedItems(), AMISConstants.AMIS_COUNTRIES.toString());
            }
            else if(AMISFoodBalanceDownloadController.selectedByOptionModel!=null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="PRODUCT"
                    || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="DATASOURCE" ){
               getCodes(areas, download.getAreasCombo().getSelectorPanel().getCombo().getSelection(), AMISConstants.AMIS_COUNTRIES.toString());
            }

        } else {
            getCodes(areas, download.getAreas().getSelectorPanel().getList().getSelectionModel().getSelectedItems(), AMISConstants.AMIS_COUNTRIES.toString());
        }

			
		HashMap<String, String> years = new HashMap<String, String>();
        if(download.getYears()!=null)
		   getCodes(years, download.getYears().getList().getSelectionModel().getSelectedItems(), AMISConstants.CODING_SYSTEM_FAOSTAT_YEAR.toString());


		HashMap<String, String> items = new HashMap<String, String>();
        if(isFoodBalance)  {
            if(AMISFoodBalanceDownloadController.selectedByOptionModel==null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="COUNTRY"
                    || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="DATASOURCE")
                 getCodes(items, download.getItemsCombo().getSelectorPanel().getCombo().getSelection(), AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_DISAGGREGATIONS.toString());
            else if(AMISFoodBalanceDownloadController.selectedByOptionModel!=null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="PRODUCT"){
                 getCodes(items, download.getItems().getSelectorPanel().getList().getSelectionModel().getSelectedItems(), AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_DISAGGREGATIONS.toString());
            }
        }
        else  {
            getCodes(items, download.getItems().getSelectorPanel().getList().getSelectionModel().getSelectedItems(), AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_DISAGGREGATIONS.toString());
        }

		HashMap<String, String> elements = new HashMap<String, String>();
        if(download.getElements()!=null)
		   getCodes(elements, download.getElements().getList().getSelectionModel().getSelectedItems(), AMISConstants.CODING_SYSTEM_FAOSTAT_ELEMENT.toString());
			
		// setting dimentions
		qvo.setAreas(areas);
		qvo.setElements(elements);
		qvo.setItems(items);
		qvo.setYears(years);

		// setting output type (Pivot or Flat Table)
		setOutputType(download.getOutputType(), qvo);

		//Set Download options
		setDownloadOptions(download.getOptions(), qvo);
	
	}
	
	private static Boolean checkSelectors(AMISDownload download) {

        if(isFoodBalance) {
            return AMISFoodBalanceDownloadController.checkFoodBalanceSelectors((AMISDownloadFoodBalance)download);
        }   else {
            return checkDownloadSelectors(download);
        }
	}


    private static Boolean checkDownloadSelectors(AMISDownload download) {

        Boolean check = true;


        if ( checkSelector(download.getAreas().getSelectorPanel().getList()) == false) {
            check = false;
            FenixAlert.alert("Selection Error", "Please select at least on Area/Country");
        }

        if ( check != false )
            if ( download.getYears()!=null && checkSelector(download.getYears().getList()) == false) {
                check = false;
                FenixAlert.alert("Selection Error", "Please select at least one Year");
            }

        if ( check != false )
                if ( checkSelector(download.getItems().getSelectorPanel().getList()) == false) {
                    check = false;
                    FenixAlert.alert("Selection Error", "Please select at least one Item");
                }


        if ( check != false )
            if (download.getElements()!=null &&  checkSelector(download.getElements().getList()) == false) {
                check = false;
                FenixAlert.alert("Selection Error", "Please select at least one Element");
            }


        System.out.println();

        return check;
    }

	
	public static Boolean checkSelector(ListView<AMISCodesModelData> list) {
         System.out.println("checkSelector selectedItems =  "+  list.getSelectionModel().getSelectedItems().size());


        if ( list.getSelectionModel().getSelectedItems().size() > 0) {
            System.out.println("checkSelector 5  RETURN TRUE");
			return true;
		}
		else {
			return false;
        }
	}

    public static Boolean checkSelector(ComboBox<AMISCodesModelData> combo) {
        if (combo.getSelection().size() > 0) {
            return true;
        }
        else
            return false;
    }
	
	private static void setOutputType(AMISDownloadOutputType outputType, AMISQueryVO qvo) {
		qvo.setOutput(AMISConstants.TABLE.toString());
	}


private static void setDownloadOptions(AMISDownloadOptions options, AMISQueryVO qvo) {
		
		// setting the selects for the download (affects the Flat Table)
        if(isFoodBalance)
		   AMISConstantsVO.setFoodBalanceSelects(qvo);
		else
           AMISConstantsVO.setSelects(qvo, options.getShowCodes().getValue(), options.getShowUnits().getValue());

    // setting the Table Headers for the download (affects the Flat Table)
       if(isFoodBalance==false)
            AMISConstantsVO.setTableHeaders(qvo, options.getShowCodes().getValue(), options.getShowUnits().getValue());
		
		// set orderBys for query for the download (affects the Flat Table)
		AMISConstantsVO.setOrderBys(qvo);
	
		
		// show flags
		//qvo.setAddFlag(options.getShowFlags().getValue());

		// codes
       if(isFoodBalance==false) {
            qvo.setShowCodes(options.getShowCodes().getValue());

            try {
                // show null values
                qvo.setShowNull(options.getShowNull().getValue());
            }catch (Exception e) {
            }

            try {
                // MU
                qvo.setShowMeasurementUnit(options.getShowUnits().getValue());
            }catch (Exception e) {
            }

            try {
                // thousand separator
                qvo.setThousandSeparator(options.getThousandCombo().getValue().getCode());
            }catch (Exception e) {
            }

            try {
                // Decimal separator
                qvo.setDecimalseparator(options.getDecimalCombo().getValue().getCode());
            }catch (Exception e) {
            }

            try {
                // Decimals values
                qvo.setShowDec(options.getDecimalValuesCombo().getValue().getCode());
            }catch (Exception e) {
            }
        }
	}
	
	
	public static void getDomainsAgentRadio(final AMISDownload download, final String defaultDomainCode, Radio radioFaostat, Radio radioPsd, Radio radioCcbs, Radio radioIgc, Radio radioAmis, Radio radioNational) {
	  System.out.println("Class: AmisFaostatDownload getDomainsAgent2: " + defaultDomainCode);
		 // treeStore.removeAll();
		  radioList = new ArrayList();
		  //AMISCodesModelData topcode = new AMISCodesModelData("Faostat", "Faostat");
		  AMISCodesModelData topcode = new AMISCodesModelData("FAOSTAT", "FAOSTAT");
		  ArrayList<String> codeList = new ArrayList();
		  codeList.add("'QC'");
		  codeList.add("'TP'");
		  codeList.add("'PC'");
		  topcode.setCodeList(codeList);
		  //treeStore.add(topcode, true);
		  radioList.add(topcode);
		  radioFaostat.setBoxLabel("FAOSTAT");
		  //topcode = new AMISCodesModelData("Psd", "Psd");
		  topcode = new AMISCodesModelData("USDA-PSD", "USDA-PSD");
		  //treeStore.add(topcode, true);
		  radioList.add(topcode);
		  radioPsd.setBoxLabel("USDA-PSD");
		  //topcode = new AMISCodesModelData("CCbs", "Ccbs");
//		  topcode = new AMISCodesModelData("FAO-CBS", "FAO-CBS");
          topcode = new AMISCodesModelData("FAO-AMIS", "FAO-AMIS");
		  //treeStore.add(topcode, true);  
		  radioList.add(topcode);
//		  radioCcbs.setBoxLabel("FAO-CBS");
          radioCcbs.setBoxLabel("FAO-AMIS");
		  
		  topcode = new AMISCodesModelData("IGC", "IGC");
		  radioList.add(topcode);
		  radioIgc.setBoxLabel("IGC");
		   
		  topcode = new AMISCodesModelData("AMIS", "AMIS");
		//  radioList.add(topcode);
		  radioAmis.setBoxLabel("AMIS");
		  radioAmis.setEnabled(false);

       // topcode = new AMISCodesModelData("NATIONAL", "NATIONAL");
        topcode = new AMISCodesModelData("NATIONAL", "NATIONAL");
       // ArrayList<String> list = new ArrayList<String>();
       // for(String code: CCBS.COUNTRY_CODES)
         //   list.add(code);

      //  topcode.setCodeList(list);
        radioList.add(topcode);
        radioNational.setBoxLabel("NATIONAL");
        //radioNational.setBoxLabel("NATIONAL-as of mid-October 2013");




		/**  if(!FenixUser.hasAdminRole()) {
			  radioNational.setBoxLabel("NATIONAL (Requires 'Log In')"); 
			  radioNational.setEnabled(false);
		  }	
		  else 
		  {
			  topcode = new AMISCodesModelData("NATIONAL", "NATIONAL");
			  ArrayList<String> list = new ArrayList<String>();
			  for(String code: CCBS.COUNTRY_CODES)
				  list.add(code);
				  
			  topcode.setCodeList(list);
			 
			  radioNational.setBoxLabel("NATIONAL"); 
			  radioNational.setEnabled(true);
						 
			  radioList.add(topcode);
		  }     **/

        if(!FenixUser.hasAdminRole()) {
//            radioNational.setEnabled(false);
            radioNational.hide();
        }
        else{
            radioNational.show();
        }

        if ( defaultDomainCode != null) {
		   System.out.println("defaultDomainCode: " + defaultDomainCode);
		//   for(AMISCodesModelData topCode : treeStore.getAllItems()) {
		   for(AMISCodesModelData topCode : radioList) {
		    System.out.println("topCode: " + topCode.getCode());
		    if (topCode.getCode().equals(defaultDomainCode)) {
		     System.out.println("select tree: " + topCode.getCode());
		     //tree.getSelectionModel().select(topCode, true);
		     //tree.setExpanded(topCode, true);
            // if(isFoodBalance)
               //  ((AMISDownloadFoodBalance)download).buildSelectors(new AMISCodesModelData("COUNTRY", "Country"), topCode);
             //else
               download.buildSelectors(topCode);
		    }
		    }
		  }
		 }

    public static void getDataSourcesAgent(final AMISDownload download, final AMISDownloadSelectorPanel panel, final String defaultDomainCode, final Boolean addSelectAll) {
        System.out.println("Class: AmisFaostatDownload getDataSourcesAgent: " + defaultDomainCode);

        panel.getPanel().removeAll();
        ListStore store = panel.getStore();
        store.removeAll();


        store.add(getDataSourcesList());

        if ( panel.getTitle() != null )
            panel.getPanel().add(panel.addTitle(panel.getTitle()));


        if (defaultDomainCode != null) {
            List<AMISCodesModelData> defaultSelectedModel = new ArrayList<AMISCodesModelData>();

            System.out.println("defaultDomainCode: " + defaultDomainCode);
            for(Object datasource : store.getModels()) {
                //"NATIONAL"
                System.out.println("source: " + ((AMISCodesModelData)datasource).getCode());
                if (((AMISCodesModelData)datasource).getCode().equals(defaultDomainCode)) {
                    defaultSelectedModel.add(((AMISCodesModelData)datasource));

                    //Set DefaultSelection
                    panel.getList().getSelectionModel().setSelection(defaultSelectedModel);

                    // System.out.println("select tree: " + datasource.getCode());
                    if(isFoodBalance)
                        ((AMISDownloadFoodBalance)download).buildSelectors(new AMISCodesModelData("COUNTRY", "Country"));
                    else
                        download.buildSelectors(((AMISCodesModelData)datasource));
                }
            }
        }

        panel.addList();

        panel.getList().getSelectionModel().addSelectionChangedListener(AMISDownloadController.reloadFilter(download));

        panel.getPanel().layout();

        if ( addSelectAll ) {
            panel.getPanel().add(FormattingUtils.addVSpace(5));
            panel.getPanel().add(panel.buildSelectAllPanel());
        }


        panel.getPanel().layout();

    }

    private static List<AMISCodesModelData> getDataSourcesList(){
        List<AMISCodesModelData> models = new LinkedList<AMISCodesModelData>();

//        AMISCodesModelData source = new AMISCodesModelData("CBS", "FAO-CBS");
        AMISCodesModelData source = new AMISCodesModelData("CBS", "FAO-AMIS");
        models.add(source);

        source = new AMISCodesModelData("PSD", "USDA-PSD");
        models.add(source);

        source = new AMISCodesModelData("IGC", "IGC");
        models.add(source);

        if(isFoodBalance)
        {
            //National Data have to be visible
            if(FenixUser.hasAdminRole()) {
                source = new AMISCodesModelData("NATIONAL", "NATIONAL");
                models.add(source);
            }
        }
        else{
            source = new AMISCodesModelData("NATIONAL", "NATIONAL");
            models.add(source);
        }

        return models;
    }
	public static void getSelectors(final AMISDownloadSelectorPanel panel, AMISQueryVO qvo, final String selectionType, final Boolean addSelectAll, final AMISDownload download) {
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {

                    System.out.println("vvvvvvvvvv AMISDownloadController: getSelectors  ONSUCCESS  selectionType = !!!! "+selectionType);

					panel.getPanel().removeAll();
					ListStore store = panel.getStore();
					store.removeAll();
					
					
					for(AMISCodesModelData topcode : vo.getCodes()) {
						store.add(topcode);

					}

                    System.out.println("vvvvvvvvvv AMISDownloadController: getSelectors  store  = "+store.getCount());

                    /** TODO: make a unique builder **/
					if ( panel.getTitle() != null )
						panel.getPanel().add(panel.addTitle(panel.getTitle()));


					panel.addList();
					panel.getPanel().layout();

                    System.out.println("vvvvvvvvvv AMISDownloadController: getSelectors  get list  = "+panel.getList());
                    System.out.println("vvvvvvvvvv AMISDownloadController: getSelectors addSelectAll  = "+addSelectAll);

                    if ( addSelectAll ) {
						panel.getPanel().add(FormattingUtils.addVSpace(5));
						panel.getPanel().add(panel.buildSelectAllPanel());
					}

					
					panel.getPanel().layout();
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




    public static void getComboSelectors(final AMISDownloadComboSelectorPanel panel, AMISQueryVO qvo) {
        try {

            System.out.println(" ------------------ getComboSelectors "+qvo.getOutput() + " | "+qvo.getTypeOfOutput());

            AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

                @SuppressWarnings("unchecked")
                public void onSuccess(AMISResultVO vo) {

                    System.out.println(" ------------------ getComboSelectors ");

                    panel.getPanel().removeAll();
                    ListStore store = panel.getStore();
                    store.removeAll();


                    for(AMISCodesModelData topcode : vo.getCodes()) {
                        store.add(topcode);

                    }



                    if ( panel.getTitle() != null )
                        panel.getPanel().add(panel.addTitle(panel.getTitle()));

                    panel.addCombo();
                    panel.getPanel().layout();
                }

                public void onFailure(Throwable arg0) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getDataSourceComboSelectors(final AMISDownload download, final AMISDownloadComboSelectorPanel panel) {
                 System.out.println(" ------------------ getDataSourceComboSelectors ");

                    panel.getPanel().removeAll();
                    ListStore<AMISCodesModelData> store = panel.getStore();
                    store.removeAll();

                    store.add(getDataSourcesList());

                    if ( panel.getTitle() != null )
                        panel.getPanel().add(panel.addTitle(panel.getTitle()));

                    panel.addCombo();

                    panel.getCombo().addSelectionChangedListener(AMISDownloadController.reloadFilter(download));

                    panel.getPanel().layout();

    }
	
	public static SelectionListener<ButtonEvent> exportCSV(final AMISDownload download) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				exportCSVAgent(download);
			}
		};
	}


    public static SelectionListener<ButtonEvent> exportCSV(final AMISDownload download, final ArrayList<String> codeList, final String selectedDataSource) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				//GOOGLE ANALYTICS TRACK EVENT: DOWNLOADING DATA
				AMISController.trackUserEvent(AMISConstants.DOWNLOADING.name());
				
				exportCSVAgent(download,codeList, selectedDataSource);
			}
		};
	}
	
	
	public static void exportCSVAgent(AMISDownload download) {
		
		if ( checkSelectors(download) ) {
            System.out.println("------------------ exportCSVAgent:  ");

			// clean the tokens
			tokensAsync = 0;
			tokensReached = 0;
			
			AMISQueryVO qvo = new AMISQueryVO();
			AMISConstantsVO.setLanguageSettings(qvo);		
			qvo.setOutput(AMISConstants.EXPORT.toString());


            if(!download.getSelectedDatasources().isEmpty()){
                HashMap<String, String> databases = new HashMap<String, String>();
                getCodes(databases, download.getSelectedDatasources());
                qvo.setDatabases(databases);
            }

			getQVOParameters(download, qvo);

            if(isFoodBalance){
                qvo.setTypeOfOutput(AMISConstants.EXPORT_EXCEL_FOOD_BALANCE.toString());
                if(AMISFoodBalanceDownloadController.selectedByOptionModel==null)
                    qvo.setxLabel("COUNTRY");
                else
                    qvo.setxLabel(AMISFoodBalanceDownloadController.selectedByOptionModel.getCode());

            } else {
                qvo.setTypeOfOutput(AMISConstants.EXPORT_CSV.toString());
            }

			List<String> froms = new ArrayList<String>();
            froms.add("amis_all_datasources " ); // combined data sources view
            qvo.setFroms(froms);

            qvo.setValueType("'TOTAL'");

			final LoadingWindow loadingWindow = new LoadingWindow("Exporting", "Please wait..", "loading");
	
			loadingWindow.showLoadingBox();
			
			if ( tokensAsync > 0)
				checkExportIfAllTheAsyncCallsAreReturned(qvo, download, loadingWindow);
			else
				exportCSVAgentCall(qvo, download, loadingWindow);
		}
        else
            System.out.println("------------------ exportCSVAgent: CHECK SELECTORS FALSE");
	}
	
public static void exportCSVAgent(AMISDownload download, ArrayList<String> codeList, String selectedDataSource) {

    System.out.println("exportCSVA gent START ");

		if ( checkSelectors(download) ) {
			// clean the tokens
			tokensAsync = 0;
			tokensReached = 0;
			//System.out.println("AmisFaostatDownloadController exportCSVAgent AMISController.getCurrentDatasetView() " +AMISController.getCurrentDatasetView());
			AMISQueryVO qvo = new AMISQueryVO();
			qvo.setTitle("Source: "+selectedDataSource + " (Distributed by AMIS Statistics)");
            qvo.setSource(selectedDataSource);

            AMISConstantsVO.setLanguageSettings(qvo);
			qvo.setOutput(AMISConstants.EXPORT.toString());
			Map<String, String> selectedDatasets = new LinkedHashMap<String, String>();
			selectedDatasets.put(AMISController.getCurrentDatasetView().toString(), AMISController.getCurrentDatasetView().toString());

            qvo.setDatabases(selectedDatasets);

            getQVOParameters(download, qvo);

			qvo.setCodeList(codeList);
			
			if(AMISController.getCurrentDatasetView().equals(AMISCurrentDatasetView.PSD)) {
				qvo.setSelectedDataset(AMISConstants.PSD.toString());	
			}
			else if(AMISController.getCurrentDatasetView().equals(AMISCurrentDatasetView.CBS)) {
				qvo.setSelectedDataset(AMISConstants.CBS.toString());
			}
			else if(AMISController.getCurrentDatasetView().equals(AMISCurrentDatasetView.IGC)) {
				qvo.setSelectedDataset(AMISConstants.IGC.toString());
			}
			else if(AMISController.getCurrentDatasetView().equals(AMISCurrentDatasetView.AMIS)) {
				qvo.setSelectedDataset(AMISConstants.AMIS.toString());
			}
			else if(AMISController.getCurrentDatasetView().equals(AMISCurrentDatasetView.NATIONAL)) {
				qvo.setSelectedDataset(AMISConstants.NATIONAL.toString());
			}



            System.out.println("exportCSVAgent output type "+ qvo.getTypeOfOutput());
//			List<String> froms = new ArrayList<String>();
//			froms.add("Data D");
//			froms.add("Element E");
//			froms.add("Area A");
//			froms.add("Item I");
//			froms.add("DomainArea DA");
//			qvo.setFroms(froms);
			
			List<String> froms = new ArrayList<String>();
			//froms.add(AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS) +" D ");	
			//froms.add("amis_all_datasources D" ); // combined data sources view
			froms.add("amis_all_datasources " ); // combined data sources view
			//froms.add(AMISController.getTableNameForCodingSystem(AMISConstants.AMIS.toString()+"_ELEMENTS") + " E ");	
			qvo.setFroms(froms);
			
			qvo.setValueType("'TOTAL'");
	
	//		// TODO: Do a better switcher (in QVO?)
	//		qvo.setOutputType(AMISConstants.EXPORT_CSV.toString());
	//		if ( download.getOutputType().getFlatTable().getCheckBox().getValue() ) {
	//			qvo.setResourceType(AMISConstants.TABLE.toString());
	//		}
	//		else{
	//			qvo.setResourceType(AMISConstants.PIVOT_TABLE.toString());
	//		}
	
			
			final LoadingWindow loadingWindow = new LoadingWindow("Exporting", "Please wait..", "loading");
	
	//		final LoadingWindow loadingWindow = new LoadingWindow("Exporting", BabelFish.print().pleaseWait(), "loading");
			loadingWindow.showLoadingBox();
			
			if ( tokensAsync > 0)
				checkExportIfAllTheAsyncCallsAreReturned(qvo, download, loadingWindow);
			else
				exportCSVAgentCall(qvo, download, loadingWindow);
		}

	}
	
	private static void exportCSVAgentCall(final AMISQueryVO qvo, final AMISDownload download, final LoadingWindow loadingWindow) {
		System.out.println("AmisFaostatDownloadController exportCSVAgentCall Text: Before calling server");
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				

				public void onSuccess(AMISResultVO vo) {
						if(vo.getExportFilename()!=null)
						{
							if(vo.getExportFilename().equals(""))
							{
								//No data available for the current selection... 
								System.out.println("AmisFaostatDownloadController exportCSVAgentCall Text: No data available for the current selection...");
                               if(qvo.getTypeOfOutput().contains("HTML")){
								buildExportTable(qvo, vo, download, loadingWindow);
                               }
                               else {
                                buildNoDataAvailableTable(download, loadingWindow);
                               }
							}
							else
							{
								System.out.println("AmisFaostatDownloadController exportCSVAgentCall Text: data available for the current selection...");
								loadingWindow.destroyLoadingBox();
								com.google.gwt.user.client.Window.open("../exportObject/" + vo.getExportFilename(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
							}
						}
						else
						{
							System.out.println("AmisFaostatDownloadController exportCSVAgentCall Text: File null...");
							
						}	
					
				}
				
				public void onFailure(Throwable arg0) {
					loadingWindow.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static SelectionListener<ButtonEvent> showTable(final AMISDownload download) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				showTableAgent(download);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showTable(final AMISDownload download, final ArrayList<String> codeList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				showTableAgent(download, codeList);
			}
		};
	}
	
	
	public static void showTableAgent(AMISDownload download) {
		
		if ( checkSelectors(download) ) {
			// clean the tokens
			tokensAsync = 0;
			tokensReached = 0;
			
			AMISQueryVO qvo = new AMISQueryVO();
			AMISConstantsVO.setLanguageSettings(qvo);		
			qvo.setTypeOfOutput(AMISConstants.HTML.toString());
	//		qvo.setOutputType(AMISConstants.PIVOT_TABLE.toString());
			
			getQVOParameters(download, qvo);
		

			
		
			final LoadingWindow loadingWindow = new LoadingWindow("Creating tables", "Please wait..", "loading");
	//		final LoadingWindow loadingWindow = new LoadingWindow("Exporting", BabelFish.print().pleaseWait(), "loading");
			loadingWindow.showLoadingBox();
			
			if ( tokensAsync > 0)
				checkPivotTableIfAllTheAsyncCallsAreReturned(qvo, download, loadingWindow);
			else
				showTableAgentCall(qvo, download, loadingWindow);
		}

	}
	

public static void showTableAgent(AMISDownload download, ArrayList<String> codeList) {
		
		if ( checkSelectors(download) ) {
			// clean the tokens
			tokensAsync = 0;
			tokensReached = 0;
				
			AMISQueryVO qvo = new AMISQueryVO();
			Map<String, String> selectedDatasets = new LinkedHashMap<String, String>();
			selectedDatasets.put(AMISController.getCurrentDatasetView().toString(), AMISController.getCurrentDatasetView().toString());
			qvo.setDatabases(selectedDatasets);
			
			AMISConstantsVO.setLanguageSettings(qvo);		
			qvo.setTypeOfOutput(AMISConstants.HTML.toString());
			qvo.setSelectedDataset(AMISController.getCurrentDatasetView().toString());
			
			getQVOParameters(download, qvo);
	
			qvo.setCodeList(codeList);

			
			List<String> froms = new ArrayList<String>();
			froms.add("amis_all_datasources" ); // combined data sources view	
			
			qvo.setFroms(froms);
			qvo.setValueType("'TOTAL'");
			
			final LoadingWindow loadingWindow = new LoadingWindow("Creating tables", "Please wait..", "loading");
			loadingWindow.showLoadingBox();
			
			if ( tokensAsync > 0)
				checkPivotTableIfAllTheAsyncCallsAreReturned(qvo, download, loadingWindow);
			else
				showTableAgentCall(qvo, download, loadingWindow);
		}

	}
	
		private static void showTableAgentCall(AMISQueryVO qvo, final AMISDownload download, final LoadingWindow loadingWindow) {
			
			/** TODO: check is it's Flat or Pivot Table, do a better check **/
				buildFlatTable(qvo, download, loadingWindow);
	}

	public static void buildFlatTable(AMISQueryVO qvo, final AMISDownload download, final LoadingWindow loadingWindow) {
		AMISDownloadTablePanel tablePanel = download.getTablePanel();	
		tablePanel.getTablesPanel().removeAll();
		tablePanel.getViewMorePanel().hide();
//		tablePanel.getViewMorePanel().removeAll();
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		loadingWindow.destroyLoadingBox();
		
		// LIMIT and Title (?)
		qvo.setLimit(100);
		//qvo.setTitle("Table sample [ max " + qvo.getLimit() +" rows ]");
		qvo.setTitle("Maximum " + qvo.getLimit() +" rows shown");
		
		tablePanel.getTablesPanel().add(panel);
		
		tablePanel.getTablesPanel().add(FormattingUtils.addVSpace(15));
		
		
		download.getTablePanel().getPanel().layout();
		AMISFaostatPivotTableController.addPivotTable(panel, qvo, "1070", "300");
	}
	
	public static void buildExportTable(AMISQueryVO qvo, AMISResultVO rvo, final AMISDownload download, final LoadingWindow loadingWindow) {
		System.out.println("AmisFaostatDownloadController buildExportTable Text: start");
		AMISDownloadTablePanel tablePanel = download.getTablePanel();	
		tablePanel.getTablesPanel().removeAll();
		tablePanel.getViewMorePanel().hide();
//		tablePanel.getViewMorePanel().removeAll();
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		loadingWindow.destroyLoadingBox();
		
		// LIMIT and Title (?)
		qvo.setLimit(100);
		//qvo.setTitle("Table sample [ max " + qvo.getLimit() +" rows ]");
		qvo.setTitle("Maximum " + qvo.getLimit() +" rows shown");
		
		tablePanel.getTablesPanel().add(panel);
		
		tablePanel.getTablesPanel().add(FormattingUtils.addVSpace(15));
		
		
		download.getTablePanel().getPanel().layout();
		AMISFaostatPivotTableController.addPivotExportTable(panel, qvo, rvo, "1070", "300");
	}

    public static void buildNoDataAvailableTable(final AMISDownload download, final LoadingWindow loadingWindow) {
        System.out.println("AmisFaostatDownloadController buildNoDataAvailableTable Text: start");
        AMISDownloadTablePanel tablePanel = download.getTablePanel();
        tablePanel.getTablesPanel().removeAll();
        tablePanel.getViewMorePanel().hide();

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);
        panel.add(new Html("<div class='pivot_table_empty_table'><font size='3'> No data available for the current selection</font></div>"));
        loadingWindow.destroyLoadingBox();

        tablePanel.getTablesPanel().add(panel);

        tablePanel.getTablesPanel().add(FormattingUtils.addVSpace(15));

        download.getTablePanel().getPanel().layout();

    }

	
	public static Listener<ComponentEvent> viewMoreTables(final AMISDownloadTablePanel tablePanel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				buildPivotTablesAgent(tablePanel);
			}
		};
	}

	
	public static void buildPivotTablesAgent(AMISDownloadTablePanel tablePanel) {
		
		LinkedHashMap<String, String> map = tablePanel.getCodes();
		AMISQueryVO qvo = tablePanel.getQvo();
		AMISConstants dimension = tablePanel.getDimension();

		Integer currentIndex = tablePanel.getCurrentIndex();
		Integer maxResults = tablePanel.getMaxResults();
		
		System.out.println("currentIndex: " + currentIndex);
		System.out.println("maxResults: " + maxResults);
		
		int i = 0;
		for( String key : map.keySet() ) {
			if ( i >= currentIndex && i < (currentIndex + maxResults) ) {
				Map<String, String> values = new HashMap<String, String>();
				values.put(key, map.get(key));
				AMISController.addToFilters(qvo, values, dimension.toString(), null);
				
				// TODO: REMOVE IT...server side!!!!
				qvo.setTitle(map.get(key));
				
				ContentPanel panel = new ContentPanel();
				panel.setHeaderVisible(false);
				panel.setBodyBorder(false);
	
				tablePanel.getTablesPanel().add(panel);
								
				tablePanel.getTablesPanel().layout();
				AMISFaostatPivotTableController.addPivotTable(panel, qvo, "1050", "300");
				
				tablePanel.getTablesPanel().add(FormattingUtils.addVSpace(35));
			}
			i++;
		}
		
		tablePanel.updateIndex(currentIndex + maxResults);
	}
	

	public static void getCodes(final HashMap<String, String> map, List<AMISCodesModelData> selectedCodes, String resourceType) {
		HashMap<String, String> codes = new HashMap<String, String>();
		
		HashMap<String, String> aggregatedCodes = new HashMap<String, String>();
		
		for(AMISCodesModelData code : selectedCodes) { 
			if ( !code.isList() ) {
				// the code
				codes.put(code.getCode(), code.getLabel());
			}
			else {
				// the code is disaggregated
				aggregatedCodes.put(code.getCode(), code.getLabel());
			}
		}
		
		// adding codes
		map.putAll(codes);
		
		if ( !aggregatedCodes.isEmpty() ) {
			// an async call is required
			tokensAsync = tokensAsync +1;
			
			AMISQueryVO qvo =  new AMISQueryVO();
			qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
			qvo.setTypeOfOutput(resourceType);		
			AMISConstantsVO.setLanguageSettings(qvo);

			qvo.getAggregationsFilter().putAll(aggregatedCodes);
		
			// TODO: get codes
			try {
				AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
					
					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {
						
						for(AMISCodesModelData code : vo.getCodes()) {
							map.put(code.getCode(), code.getLabel());
						}

//						System.out.println("MAP: " + map);
						tokensReached++;
					}
					
					public void onFailure(Throwable arg0) {
		
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
			System.out.println("aggregatedCodes: " + aggregatedCodes);
		}
		
	}


    public static void getCodes(final HashMap<String, String> map, List<AMISCodesModelData> selectedCodes) {
        HashMap<String, String> codes = new HashMap<String, String>();

        HashMap<String, String> aggregatedCodes = new HashMap<String, String>();

        for(AMISCodesModelData code : selectedCodes) {
            if ( !code.isList() ) {
                // the code
                codes.put(code.getCode(), code.getLabel());
            }
            else {
                // the code is disaggregated
                aggregatedCodes.put(code.getCode(), code.getLabel());
            }
        }

        // adding codes
        map.putAll(codes);

    }



    public static Listener<ComponentEvent> reloadDataSourcesFilter(final AMISDownload download) {
        return new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent ce) {
                System.out.println("================= reloadDataSourcesFilter CALLED!!");
                if(AMISDownloadController.isFoodBalance)   {
                    System.out.println("================= reloadDataSourcesFilter food balance !!");
                    ((AMISDownloadFoodBalance)download).buildSelectors(AMISFoodBalanceDownloadController.selectedByOptionModel);
                } else {
                    //TO-DO

                }

                download.getTablePanel().getTablesPanel().removeAll();

                download.getTitlePanel().getPanel().layout();

            }
        };
    }

    public static SelectionChangedListener<AMISCodesModelData> reloadFilter(final AMISDownload download) {
        return new SelectionChangedListener<AMISCodesModelData>() {
        @Override
        public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> event) {
            if(AMISDownloadController.isFoodBalance)   {
                System.out.println("================= reloadDataSourcesFilter food balance !! "+event.getSelection());
                ((AMISDownloadFoodBalance)download).buildSelectors(event.getSelection(), AMISFoodBalanceDownloadController.selectedByOptionModel);
            } else {
                //TO-DO

            }

        }
    };
   }

	public static Listener<ComponentEvent> reloadFilter(final AMISDownload download, final AMISDownloadSelectorPanel panel, final AMISCodesModelData domainFilter, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				reloadFilterAgent(download, panel, domainFilter, selectionType, isTotalAndList, addSelectAll, width, height);
			}
		};
	}

    public static Listener<ComponentEvent> reloadFilter(final AMISDownload download, final AMISDownloadSelectorPanel panel, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {
        return new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent ce) {
                reloadFilterAgent(download, panel, selectionType, isTotalAndList, addSelectAll, width, height);
            }
        };
    }
	
	
	public static void reloadFilterAgent(AMISDownload download, AMISDownloadSelectorPanel panel, AMISCodesModelData domainFilter, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {
		panel.getPanel().removeAll();
		panel.build(download, domainFilter, null, selectionType, isTotalAndList, addSelectAll, width, height);
		panel.getPanel().layout();
	}


    public static void reloadFilterAgent(AMISDownload download, AMISDownloadSelectorPanel panel, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {
        panel.getPanel().removeAll();
        panel.build(download, "CBS", null, selectionType, isTotalAndList, addSelectAll, width, height);
        panel.getPanel().layout();
    }

    public static Listener<ComponentEvent> reloadComboFilter(final AMISDownload download, final AMISDownloadComboSelectorPanel panel, final AMISCodesModelData domainFilter, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {
        return new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent ce) {
                reloadFilterAgent(download, panel, domainFilter, selectionType, isTotalAndList, addSelectAll, width, height);
            }
        };
    }


    public static Listener<ComponentEvent> reloadComboFilter(final AMISDownload download, final AMISDownloadComboSelectorPanel panel, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {
        return new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent ce) {
                reloadFilterAgent(download, panel, selectionType, isTotalAndList, addSelectAll, width, height);
            }
        };
    }


    public static void reloadComboFilterAgent(AMISDownload download, AMISDownloadComboSelectorPanel panel, AMISCodesModelData domainFilter, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {
        panel.getPanel().removeAll();
        panel.build(download, domainFilter, null, selectionType, isTotalAndList, addSelectAll, width, height);
        panel.getPanel().layout();
    }
	
	public static void checkExportIfAllTheAsyncCallsAreReturned(final AMISQueryVO qvo, final AMISDownload download, final LoadingWindow loadingWindow) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					/** TODO: start download **/
					System.out.println("start download...");
					exportCSVAgentCall(qvo, download, loadingWindow);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(10);
	}
	
	public static void checkPivotTableIfAllTheAsyncCallsAreReturned(final AMISQueryVO qvo, final AMISDownload download, final LoadingWindow loadingWindow) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					/** TODO: start download **/
					System.out.println("start download...");
					showTableAgentCall(qvo, download, loadingWindow);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(10);
	}
	
	public static void destroy() {
		if (timer != null)
		timer.cancel();
	}


    public static SelectionListener<ButtonEvent> setFoodBalanceElementsForSelectedProduct(final ListView<AMISCodesModelData> elements,  final AMISDownload download,  final HorizontalPanel panel) {
        return new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {

                ToggleButton toggleButton = ((ToggleButton)ce.getComponent());

                if(toggleButton.isPressed()) {
                    List<AMISCodesModelData> products = download.getItems().getSelectorPanel().getList().getSelectionModel().getSelectedItems();

                    if(products.isEmpty() || products.size() > 1){
                        toggleButton.toggle(false);
                        FenixAlert.error("Food Balance Products Selection", ("Please select 1 product"));
                    }
                    else {
                        isFoodBalance = true;
                        toggleButton.setText("Show All Elements");

                        for(AMISCodesModelData code : products) {
                            if ( !code.isList() ) {
                                // the code
                                System.out.println("AMISDownloadController setFoodBalance selected product code = "+code.getCode()+" | "+code.getLabel());
                            }
                            else {
                                System.out.println("AMISDownloadController setFoodBalance selected product = "+code.getCode()+" | "+code.getLabel());

                            }
                        }


                    }
                } else {
                    isFoodBalance = false;
                    toggleButton.setText("Show Food Balance Elements");

                }

                toggleButton.recalculate();
                panel.getLayout().layout();

                System.out.println("AMISDownloadController setFoodBalance isFoodBalance '"+AMISDownloadController.isFoodBalance+"'");
                //selectAllAgent(list);
            }
        };
    }
}
