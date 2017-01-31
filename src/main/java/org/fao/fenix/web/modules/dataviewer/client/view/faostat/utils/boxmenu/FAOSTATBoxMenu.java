package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.boxmenu.FAOSTATBoxMenuController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguageConstants;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATAggregationConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class FAOSTATBoxMenu {
	
	public static HorizontalPanel buildTitle(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String titleStyle) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		String title = rvo.getTitle();
		
		String aggregationType = "";
		try {
			aggregationType = FAOSTATLanguage.print().getString(qvo.getAggregationType().toLowerCase()) + " ";
		} catch (Exception e) {
			aggregationType = "";
		}
		Boolean addAggregationType = addAggregationType(qvo);

		
//		System.out.println("TIMEPERIOD TITLE:");
//		System.out.println("YEARS: " + qvo.getYears().isEmpty());
		

		if ( qvo.getCalculationParametersVO() != null ) {
			FAOSTATAggregationConstant type = qvo.getCalculationParametersVO().getCalculationType();
			if(type.equals(FAOSTATAggregationConstant.AVERAGE_OVER_TIME)){		
				aggregationType = FAOSTATLanguage.print().average() + " ";
			}
			
			else if(type.toString().toUpperCase().contains("GROWTH")){		
				aggregationType = "";
			}
		}
		
		
		Boolean check = false;

		if( !qvo.getYears().isEmpty() ) {
			title = title  + " " +  buildTimerangeTitle(qvo, addAggregationType, aggregationType);
			check = true;
		}
		else if(rvo.getMaxDateLimit()!=null && rvo.getTimeIntervalSpan()==null && !check) {	
			title =  title + " ("+rvo.getMaxDateLimit()+") ";
			check = true;
		}
		else if(rvo.getMaxDateLimit()!= null && rvo.getTimeIntervalSpan()!=null && !check) {	
//			System.out.println("AGGREGATION TYPE: " + qvo.getAggregationType().toLowerCase());
			title = title + " (";
			if ( addAggregationType)
				title = title + aggregationType;
			
			title = title + (Integer.valueOf(rvo.getMaxDateLimit()) - rvo.getTimeIntervalSpan()) +" - "+rvo.getMaxDateLimit()+")";
//			title = title + " ("+ (Integer.valueOf(rvo.getMaxDateLimit()) - rvo.getTimeIntervalSpan()) +" - "+rvo.getMaxDateLimit()+")";
//			title = title + " [" + qvo.getAggregationType().toLowerCase() + "]";
			check = true;
		}
		
		// TODO: add on rvo
		else if(qvo.getCalculationParametersVO() != null  && !check ) {
//			title = title + " " + buildGrowthRateTitle(qvo, rvo);
//			check = true;
		}
		

		
		
//		System.out.println("TITLE: " + title);
		
		panel.add(new Html("<div class='" + titleStyle + "'> " + title + " </div>"));
		
		return panel;
	}
	
	private static Boolean addAggregationType(DWFAOSTATQueryVO qvo) {
		
		// If the object is a timeserie by defualt skip it
		if ( DataViewerBoxContent.valueOf(qvo.getTypeOfOutput()).equals(DataViewerBoxContent.TIMESERIE)) {
			return false;
		}
		else if( qvo.getRunCalculationQuery()) { 
			if(qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.GROWTH_RATE)){
				return false;
			}
		}
		
		return true;
		
	}
	
	/** TODO: Build the title also with the timespan **/
	private static String buildGrowthRateTitle(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {

//		System.out.println("buildGrowthRateTitle");
		
		StringBuffer dates = new StringBuffer();
		dates.append("(");
		
		// past values
		if ( qvo.getCalculationParametersVO().getTimeperiod_past() != null ) {
			if ( qvo.getCalculationParametersVO().getTimeperiod_past().size() > 1 ) {
				dates.append("[ ");
			}
			for(String value : qvo.getCalculationParametersVO().getTimeperiod_past() ) {
				dates.append(value);	
				dates.append(" ");
			}
			if ( qvo.getCalculationParametersVO().getTimeperiod_past().size() > 1 ) {
				dates.append(" ]");
			}
			
			dates.append(" - ");
	   }
		
		// present values
		if ( qvo.getCalculationParametersVO().getTimeperiod_present().size() > 1 ) {
			dates.append("[ ");
		}
		for(String value : qvo.getCalculationParametersVO().getTimeperiod_present() ) {
			dates.append(value);	
			dates.append(" ");
		}
		if ( qvo.getCalculationParametersVO().getTimeperiod_present().size() > 1 ) {
			dates.append(" ]");
		}

		dates.append(")");
		
		return dates.toString();
	}
	
	private static String buildTimerangeTitle(DWFAOSTATQueryVO qvo, Boolean addAggregationType, String aggregationType) {
		
//		System.out.println("buildTimerangeTitle: " + qvo.getYears());
		StringBuffer dates = new StringBuffer();
	
		if ( !qvo.getYears().isEmpty() ) {

			 LinkedHashMap<String, String> sort = FAOSTATMainController.sortByValuesDESC(qvo.getYears());
			 
			 int i = 0;
			 String presValue = null; 
			 String  pastValue = null;
			 for(String key : sort.keySet()) {
				 if ( i == (sort.size() - 1) ) {
					 pastValue = key;
				 }
				  if ( i == 0) {
						 presValue = key;
				 }
				 i++;
			 }

			dates.append("(");
			
			//adding the aggregation type
			if ( addAggregationType ) {
				if ( qvo.getYears().size() > 1)
					dates.append(aggregationType);
			}
			
			dates.append(pastValue);
			
			if ( !presValue.equals(pastValue)) {
				dates.append(" - " + presValue);
			}
			
			dates.append(")");

			

			
		}
		return dates.toString();
	}
	
//	public static HorizontalPanel buildTitle(String title, String titleStyle, String description) {
//		HorizontalPanel panel = new HorizontalPanel();
//		
//		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
//		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
//
//		panel.add(DataViewerClientUtils.addHSpace(2));
//		
//		if(description!=null & description!="")
//			panel.add(buildInfoWindow(description));
//		
//		panel.add(DataViewerClientUtils.addHSpace(2));
//		panel.add(new Html("<div class='" + titleStyle + "'> " + title + " </div>"));
//		
//		return panel;
//	}
	
	public static HorizontalPanel buildInfoWindow(String description) {
		HorizontalPanel panel = new HorizontalPanel();
		IconButton icon = new IconButton("info");
		icon.setToolTip(FAOSTATLanguage.print().additionalInformation());
		icon.addSelectionListener(FAOSTATBoxMenuController.showInfo(description));
		panel.add(icon);
		return panel;
	}

}
