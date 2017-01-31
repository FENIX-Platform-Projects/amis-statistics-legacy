package org.fao.fenix.web.modules.adam.client.view.makers;

import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomShowBox;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;


public class ADAMChartMaker extends ADAMBoxMaker {

	public static HorizontalPanel buildChartBox(ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener, String width, String height, boolean isSmall, boolean showLinks) {
		HorizontalPanel h2 = new HorizontalPanel();
		h2.setHorizontalAlign(HorizontalAlignment.LEFT);
//		h2.setVerticalAlign(VerticalAlignment.MIDDLE);
		h2.setSpacing(5);
		
		
		//System.out.println("-->vo.getChartValues(): " + vo.getChartValues());
		//System.out.println("-->vo.getChartValues().isEmpty(): " + vo.getChartValues().isEmpty() );
		
		if ( !vo.getChartValues().isEmpty()){
			
			StringBuffer chartString = new StringBuffer();
			chartString.append("<div>");
			if (isSmall) {
				//System.out.println("-->vo.getSmallHtmlImageMap(): " + vo.getSmallHtmlImageMap());
				chartString.append(vo.getSmallHtmlImageMap());
			} else {
				chartString.append(vo.getBigHtmlImageMap());
			}
			chartString.append("</div>");
			HTML chart = new HTML(chartString.toString());
			
			
			h2.add(chart);
		}
		else {
			// empty panel
			h2.add(new ADAMEmptyValuesPanel().build("Any data available for the current selection", width, height));
		}	
		
		h2.layout();
		if ( showLinks )
			h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), isSmall));
		return h2;
	}
	
	public static HorizontalPanel buildCustomChartBox(ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, boolean showLinks) {
		HorizontalPanel h2 = new HorizontalPanel();
		h2.setHorizontalAlign(HorizontalAlignment.LEFT);
//		h2.setVerticalAlign(VerticalAlignment.MIDDLE);
		h2.setSpacing(5);
		StringBuffer chartString = new StringBuffer();
		chartString.append("<div>");
		if (isSmall) {
			chartString.append(vo.getSmallHtmlImageMap());
		} else {
			chartString.append(vo.getBigHtmlImageMap());
		}
		chartString.append("</div>");
		HTML chart = new HTML(chartString.toString());
//		System.out.println("chart.gethtml: " + chartString.toString());
		h2.add(chart);
		h2.layout();

		if ( showLinks )
			h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), isSmall));
		return h2;
	}
	
	public static VerticalPanel buildCustomChart(ADAMQueryVO qvo, ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener, ADAMCustomShowBox adamCustomShowBox) {
		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
		HorizontalPanel h1 = buildCustomChartBoxMenu(qvo, vo, ADAMConstants.SMALL_MENU_GAP, objectSizeListener, true, adamCustomShowBox);
		HorizontalPanel h2 = buildCustomChartBox(vo, objectSizeListener, true, false);
		p.add(h1);
		p.add(h2);
		return p;
	}


	
	
	
	public static VerticalPanel buildSmallChart(ADAMQueryVO qvo, ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean addRemoveChartIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) {
		VerticalPanel p = new VerticalPanel();
		
		System.out.println("------ buildSmallChart SMALL_MENU_GAP_WIDTH = "+ ADAMConstants.SMALL_MENU_GAP + " | title = "+vo.getTitle());
		
		VerticalPanel h1 = buildChartBoxMenu(qvo, vo, ADAMConstants.SMALL_MENU_GAP, position, color, objectSizeListener, true, addRemoveChartIcon, removeListener, removeLabel);
		HorizontalPanel h2 = buildChartBox(vo, objectSizeListener, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, true, false);
		
		p.add(h1);
//		p.add(new Html("<hr width='100%' size=1 color='black'>"));
		p.add(h2);
		
		System.out.println("%%%%%%%%%%%%%%%%%%% buildSmallChart "+vo.getTitle()+" | discaimer = "+vo.getDisclaimer());
		if(vo.getDisclaimer()!=null)
			p.add(buildDisclaimer(vo.getDisclaimer()));

		
		return p;
	}
	
	
	
	static Html buildDisclaimer(String disclaimer) {
		return new Html("<font size='1px' color='#1D4589' style='margin-left:5px'>"+disclaimer+"</font>");
	}

	public static VerticalPanel buildSmallCustomChart(ADAMQueryVO qvo, ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener, String position) {
		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
		
		// this is because there is a button more 
		Integer panelWidth = Integer.valueOf(ADAMConstants.SMALL_MENU_GAP.substring(0, ADAMConstants.SMALL_MENU_GAP.indexOf("px"))) - 60 ;
		HorizontalPanel h1 = buildSmallCustomChartBoxMenu(qvo,vo, panelWidth.toString(), objectSizeListener, true, position);
		HorizontalPanel h2 = buildChartBox(vo, objectSizeListener, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, true, false);
		p.add(h1);
		p.add(h2);
		return p;
	}
	
	/** TODO: SET THE SWTICH ALSO TO THE BIG CHART?? **/
	public static VerticalPanel buildBigChart(ADAMQueryVO qvo, ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener) {
		System.out.println("VO IS NULL? " + (vo == null));
		System.out.println("\t" + vo.getSmallImagePath());
		System.out.println("\t" + vo.getBigImagePath());
		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
		
		System.out.println("-------------- buildBigChart "+ ADAMConstants.BIG_MENU_GAP);
		//VerticalPanel h1 = buildChartBoxMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, objectSizeListener, false, false, null, null);
		VerticalPanel h1 = buildChartBoxMenu(qvo,vo, ADAMConstants.BIG_MENU_GAP, position, color, objectSizeListener, false, false, null, null);
		HorizontalPanel h2 = buildChartBox(vo, objectSizeListener, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT, false, false);
		p.add(h1);
		p.add(h2);
		
		if(vo.getDisclaimer()!=null)
			p.add(buildDisclaimer(vo.getDisclaimer()));
		
		return p;
	}
	
	

}