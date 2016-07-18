package org.fao.fenix.web.modules.adam.client.control.utils;

import java.util.Map;

import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;

public class ADAMSubSectorORUtils {

	public static String getLimitDisclaimer(){
		return "The chart is limited to the top 3, so that it is not overcrowded.";
	}
	
	public static String getDifferenceChartDisclaimer(String selectedItem, String aggregationLabel){
		return "* Difference = [Total "+aggregationLabel+" for "+ selectedItem +"] - [Total "+aggregationLabel+" of each OR/Sub-Sector]. Positive values are displayed as <font color='green'>green bars</font> and negative values as <font color='red'>red bars</font>";
	}

public static String getApproriateBarChartTitle(Map<String, String> selectedItems, boolean isCountryChart){
		
        String title="Top Ten ";
        
		String itemType = "";
		if(isCountryChart)
			itemType = "Recipient Countries";
		else
			itemType = "Resource Partners";

		if(selectedItems == null){
			title += itemType; 
		}
		else {		
			if(selectedItems.size() > 10)
				title += " of Selected "+itemType;			 
			else
				title = "Selected "+itemType+" Comparison";
		}

		return title;
	}
	
public static String getApproriateTimeseriesTitle(Map<String, String> selectedItems, boolean isCountryChart){
		
		String timeseriesTitle="Time series of";
        
		String itemType = "";
		if(isCountryChart)
			itemType = "Recipient Countries";
		else
			itemType = "Resource Partners";
		
		if(selectedItems == null){
			timeseriesTitle += " Top "+itemType;	
		}
		else {						
			timeseriesTitle += " Top Selected "+itemType; // time series is always limited to 3 lines
		}

		return timeseriesTitle;
	}

public static String getRecipientCountryLabel(Map<String, String> gaulList) {
	String selectedCountriesLabel = "";
	if(gaulList==null)
		selectedCountriesLabel = "All Recipient Countries";
	else {
		if (gaulList.size() == 1)
			selectedCountriesLabel = ADAMViewMenuBuilder.getUnFormattedDescription(gaulList);
		else
			selectedCountriesLabel = "the Selected Recipient Countries";
	}
	
	return selectedCountriesLabel;
}

	
}


