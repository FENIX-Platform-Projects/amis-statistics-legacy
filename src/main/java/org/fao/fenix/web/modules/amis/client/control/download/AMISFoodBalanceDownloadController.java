package org.fao.fenix.web.modules.amis.client.control.download;




import com.extjs.gxt.ui.client.widget.form.Radio;

import org.fao.fenix.web.modules.amis.client.view.download.foodbalance.AMISDownloadFoodBalance;

import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import java.util.*;

public class AMISFoodBalanceDownloadController extends AMISDownloadController {

    public static List<AMISCodesModelData> radioOptionsList;

    public static AMISCodesModelData selectedByOptionModel;

	public static void getOptionsAgentRadio(final AMISDownloadFoodBalance download, final String defaultCode, Radio radioByCountry, Radio radioByProduct, Radio radioByDatasource) {
	 	  radioOptionsList = new ArrayList();

          AMISCodesModelData topcode = new AMISCodesModelData("DATASOURCE", "DATASOURCE");
          radioOptionsList.add(topcode);
          radioByDatasource.setId("DATASOURCE");
          radioByDatasource.setBoxLabel("Data Sources");

          topcode = new AMISCodesModelData("COUNTRY", "COUNTRY");
          radioOptionsList.add(topcode);
          radioByCountry.setId("COUNTRY");
          radioByCountry.setBoxLabel("Countries/Regions");
          radioByCountry.setValue(true);

          topcode = new AMISCodesModelData("PRODUCT", "PRODUCT");
          radioOptionsList.add(topcode);
          radioByProduct.setId("PRODUCT");
          radioByProduct.setBoxLabel("Products");

    	  if ( defaultCode != null) {
		   for(AMISCodesModelData topCode : radioOptionsList) {
		    System.out.println("topCode: " + topCode.getCode());
		    if (topCode.getCode().equals(defaultCode)) {
                AMISFoodBalanceDownloadController.selectedByOptionModel = topCode;
                 download.buildSourcesSelector(topCode);
                 download.buildSelectors(topCode);
                break;
		    }
		    }  
		  }
		 }

    public static Boolean checkFoodBalanceSelectors(AMISDownloadFoodBalance download) {
        Boolean check = true;

       if(AMISFoodBalanceDownloadController.selectedByOptionModel==null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="COUNTRY"){
            if (!checkSelector(download.getAreas().getSelectorPanel().getList())) {
                check = false;
                FenixAlert.alert("Selection Error", "Please select at least one Country/Region ");
            }
        }

        if(AMISFoodBalanceDownloadController.selectedByOptionModel!=null && AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="PRODUCT"){
                if (!checkSelector(download.getItems().getSelectorPanel().getList())) {
                    check = false;
                    FenixAlert.alert("Selection Error", "Please select at least one Product");
                }
        }

        if(AMISFoodBalanceDownloadController.selectedByOptionModel==null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="DATASOURCE"){
            if (!checkSelector(download.getDataSources().getSelectorPanel().getList())) {
                check = false;
                FenixAlert.alert("Selection Error", "Please select at least one Data Source ");
            }
        }



        System.out.println("---------------- checkFoodBalanceSelectors END check  = "+check);

        return check;
    }


}
