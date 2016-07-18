package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu;


import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.client.control.boxmenu.BoxMenuController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class BoxMenu {
	
	public static HorizontalPanel buildTitle(AMISQueryVO qvo, AMISResultVO rvo, String titleStyle) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
	
		String title = rvo.getTitle();
		String aggregationLabel = rvo.getAggregationLabel();
		String timeLabel = rvo.getTimerangeLabel();
		
		System.out.println("TIMEPERIOD TITLE:");
		System.out.println("YEARS: " + qvo.getYears().isEmpty());
		
		
		if(timeLabel!=null && aggregationLabel!=null)
			title += timeLabel + " " + aggregationLabel; 
		
		
		/**Boolean check = false;
		if ( qvo.getCalculationParametersVO() != null )
			System.out.println("qvo.getCalculationParametersVO().getTimeperiod_present(): " + qvo.getCalculationParametersVO().getTimeperiod_present());
		
		
		if( !qvo.getYears().isEmpty() ) {
			title = title  + " " +  buildTimerangeTitle(qvo);
			check = true;
		}
		else if(rvo.getMaxDateLimit()!=null && rvo.getTimeIntervalSpan()==null && !check) {	
			title =  title + " ("+rvo.getMaxDateLimit()+") ";
			check = true;
		}
		else if(rvo.getMaxDateLimit()!= null && rvo.getTimeIntervalSpan()!=null && !check) {	
			System.out.println("!!!");
			title = title + " ("+ (Integer.valueOf(rvo.getMaxDateLimit()) - rvo.getTimeIntervalSpan()) +" - "+rvo.getMaxDateLimit()+")";
			check = true;
		}
		// TODO: add on rvo
		else if(qvo.getCalculationParametersVO() != null  && !check ) {
//			title = title + " " + buildGrowthRateTitle(qvo, rvo);
//			check = true;
		}***/
		
		System.out.println("TITLE: " + title);
		
//		System.out.println("TITLE: " + title);
		panel.add(new Html("<div class='" + titleStyle + "'> " + title + " </div>"));
		
		
		return panel;
	}
	
	
	public static HorizontalPanel buildInfoWindow(String description) {
		HorizontalPanel panel = new HorizontalPanel();
		IconButton icon = new IconButton("info");
		icon.setToolTip("Additional Information");
		icon.addSelectionListener(BoxMenuController.showInfo(description));
		panel.add(icon);
		return panel;
	}
	

}
