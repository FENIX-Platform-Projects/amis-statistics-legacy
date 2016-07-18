package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMViewMenu;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMVennMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.venn.common.services.VennServiceEntry;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMReportController extends ADAMController {
	
	public static void createLeftTitle(ADAMReportBeanVO vo) {
		StringBuffer title = new StringBuffer();
		StringBuffer underTitle = new StringBuffer();
		
		Integer countriesSelected = ADAMBoxMaker.countriesSelected.size();
		Integer donorsSelected = ADAMBoxMaker.donorsSelected.size();
		
		// checking how many countries/donors
		
		// GLOBAL VIEW
		if ( countriesSelected == 0 && donorsSelected == 0) {
			vo.setLeftTitle("Global View");
		}
		
		// COUNTRY VIEW
		if ( countriesSelected > 0 && donorsSelected == 0) {
			// just one country
			if ( countriesSelected == 1) {
				for ( String key : ADAMBoxMaker.countriesSelected.keySet()) 
					title.append("Country View: " + ADAMBoxMaker.countriesSelected.get(key));
			}
			// multicountry
			else {
				title.append("Multi Country");
				underTitle.append("*See below for the complete list");
			}
		}
		
		// DONOR VIEW
		if ( countriesSelected == 0 && donorsSelected > 0) {
			// just one DONOR
			if ( donorsSelected == 1) {
				for ( String key : ADAMBoxMaker.donorsSelected.keySet()) 
					title.append("Resource Partner View: " + ADAMBoxMaker.donorsSelected.get(key));
			}
			// mulridonors
			else {
				title.append("Multi Resource Partners");
				underTitle.append("*See below for the complete list");
			}
		}
		
		// COUNTRY/DONOR VIEW
		if ( countriesSelected > 0 && donorsSelected > 0) {
			// just one country/DONOR
			if ( donorsSelected == 1 && countriesSelected == 1) {
				for ( String key : ADAMBoxMaker.countriesSelected.keySet()) 
					title.append("Country/Resource Partner View: " + ADAMBoxMaker.countriesSelected.get(key) + "/");
				for ( String key : ADAMBoxMaker.donorsSelected.keySet()) 
					title.append(ADAMBoxMaker.donorsSelected.get(key));
			}
			// multicountry/donors
			else {
				title.append("Multi Countries/Resource Partners");
				underTitle.append("*See below for the complete list");
			}
		}
		
		
		
		
		vo.setLittleLeftTitle(underTitle.toString());
		vo.setLeftTitle(title.toString());
	}
	
	
	public static void getCountriesDonors(ADAMReportBeanVO vo) {
		// get all countries
		
		List<String> countries = new ArrayList<String>();
		
		for ( String key : ADAMBoxMaker.countriesSelected.keySet()) {
			countries.add(ADAMBoxMaker.countriesSelected.get(key));
		} 
		
		
		// get all donors
		
		List<String> donors = new ArrayList<String>();
		
		
		for ( String key : ADAMBoxMaker.donorsSelected.keySet()) {
			donors.add(ADAMBoxMaker.donorsSelected.get(key));
		} 

		vo.setCountries(countries);
		vo.setDonors(donors);
	}
	
	
	
	
	public static String createRightTitle() {
		StringBuffer title = new StringBuffer();
		
		String fromDate = ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel();
		String toDate = ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel();
		
		
		System.out.println("From date:" + fromDate);
		System.out.println("To date:" + toDate);
		if( fromDate.equals(toDate)) {
			title.append(fromDate);
		}
		else {
			title.append(fromDate + " - " + toDate);
		}
		
		
		return title.toString();
	}
	
	
	
}
