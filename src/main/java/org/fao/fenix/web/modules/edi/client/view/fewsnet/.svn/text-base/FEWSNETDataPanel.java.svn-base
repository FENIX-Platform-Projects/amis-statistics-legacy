package org.fao.fenix.web.modules.edi.client.view.fewsnet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.edi.client.view.EDIPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;

public class FEWSNETDataPanel extends EDIPanel {

	private Map<Integer, String> monthNameMap;
	
	private ListBox hispaniolaNDVIList;
	
	private ListBox hispaniolaNDVIDAList;
	
	// PYD = Previous Year Difference
	private ListBox hispaniolaNDVIPYDList;
	
	private ListBox haitiNDVIList;
	
	private ListBox haitiNDVIDAList;
	
	// PYD = Previous Year Difference
	private ListBox haitiNDVIPYDList;
	
	private VerticalPanel wrapper;
	
	private Date startDate = new Date(109, 11, 26);
	
	private Date endDate;
	
	private List<Integer> periods;
	
	private Map<Integer, Date> periodDateMap;
	
	public FEWSNETDataPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		hispaniolaNDVIList = new ListBox();
		hispaniolaNDVIDAList = new ListBox();
		hispaniolaNDVIPYDList = new ListBox();
		haitiNDVIList = new ListBox();
		haitiNDVIDAList = new ListBox();
		haitiNDVIPYDList = new ListBox();
		wrapper = new VerticalPanel();
		periods = new ArrayList<Integer>();
		monthNameMap = new HashMap<Integer, String>();
		populatePeriods();
		populateMonthNameMap();
		endDate = createEndDate();
		periodDateMap = new HashMap<Integer, Date>();
	}
	
	@Override
	public TabItem build() {
		wrapper.add(buildTextPanel("Hispaniola", "#1D4589"));
		wrapper.add(buildNDVIPanel("Temporally Smoothed NDVI", hispaniolaNDVIList, "hi", "", "#1D4589"));
		wrapper.add(buildNDVIPanel("Mean Anomaly", hispaniolaNDVIDAList, "hi", "stm", "#1D4589"));
		wrapper.add(buildNDVIPanel("Previous Year Difference", hispaniolaNDVIPYDList, "hi", "dif", "#1D4589"));
		wrapper.add(buildTextPanel("Haiti", "#CA1616"));
		wrapper.add(buildNDVIPanel("Temporally Smoothed NDVI", haitiNDVIList, "ha", "", "#CA1616"));
		wrapper.add(buildNDVIPanel("Mean Anomaly", haitiNDVIDAList, "ha", "stm", "#CA1616"));
		wrapper.add(buildNDVIPanel("Previous Year Difference", haitiNDVIPYDList, "ha", "dif", "#CA1616"));
		wrapper.setSpacing(getSPACING());
		this.getTabItem().add(wrapper);
		return this.getTabItem();
	}
	
	private VerticalPanel buildTextPanel(String text, String color) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<div align='center' style='color: " + color + "; font-weight: bold; font-family: sans-serif; font-size: 20pt; text-decoration: underline; '>" + text + "</div>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		return p;
	}
	
	private VerticalPanel buildNDVIPanel(String title, ListBox list, String prefix, String suffix, String color) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b><font color='" + color + "'>" + title + "</font></b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		list.setWidth(getFIELD_WIDTH());
		list.addItem("Please Select a Period...");
		Date fromDate = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate());
		int counter = 1;
		periodDateMap.put(new Integer(counter), fromDate);
		list.insertItem(getDateString(fromDate, counter), createLink(prefix, suffix, counter, fromDate.getYear()), 1);
		counter++;
		for (int i = 2 ; i < Integer.MAX_VALUE ; i++) {
			fromDate.setDate(fromDate.getDate() + 1);
			if (fromDate.compareTo(endDate) < 0) { 
				if (periods.contains(fromDate.getDate())) {
					list.insertItem(getDateString(fromDate, counter), createLink(prefix, suffix, counter, fromDate.getYear()), 1);
					counter++;
					periodDateMap.put(new Integer(counter), fromDate);
				}
			} else {
				break;
			}
		}
		p.add(list);
		return p;
	}
	
	private String createLink(String prefix, String suffix, int period, int year) {
		return prefix + String.valueOf(period) + String.valueOf(1900 + year).substring(2) + suffix + ".zip";
	}
	
	private String getDateString(Date fromDate, int period) {
		Date tmp = new Date(fromDate.getYear(), fromDate.getMonth(), fromDate.getDate());
		tmp.setDate(tmp.getDate() + 10);
		return "Period " + twoDigits(period) + ": " + twoDigits(from(fromDate.getDate())) + getDateSuffix(fromDate.getDate()) + " " + monthNameMap.get(fromDate.getMonth()).substring(0, 3) + " " + (1900 + fromDate.getYear()) + " - " +
			   twoDigits(to(tmp.getDate())) + getDateSuffix(tmp.getDate()) + " " + monthNameMap.get(tmp.getMonth()).substring(0, 3) + " " + (1900 + tmp.getYear());
	}
	
	private int from(int date) {
		int result = 26;
		if ((date >= 1) && (date < 6))
			result = 1;
		else if ((date >= 6) && (date < 11))
			result = 6;
		else if ((date >= 11) && (date < 16))
			result = 11;
		else if ((date >= 16) && (date < 21))
			result = 16;
		else if ((date >= 21) && (date < 26))
			result = 21;
		return result;
	}
	
	private int to(int date) {
		if ((date >= 10) && (date < 15))
			return 10;
		else if ((date >= 15) && (date < 20))
			return 15;
		else if ((date >= 20) && (date < 25))
			return 20;
		else if ((date >= 25) && (date < 28))
			return 25;
		else if (((date >= 28) && (date <= 31)) || ((date >= 0) && (date <= 5)))
			return 28;
		return 5;
	}
	
	private String twoDigits(int n) {
		String s = "";
		if (n < 10)
			s += "0";
		s += String.valueOf(n);
		return s;
	}
	
	private String getDateSuffix(int date) {
		switch (date) {
			case 1: return "st";
			case 21: return "st";
			case 2: return "nd";
			case 22: return "nd";
			case 3: return "rd";
			case 23: return "rd";
			default: return "th";
		}
	}
	
	private Date createEndDate() {
		endDate = new Date();
		if (periods.contains((endDate.getDate()))) {
			System.out.println("Today is a Dissemination Day: " + endDate);
		} else {
			int today = endDate.getDate();
			for (Integer period : periods) {
				if (today > period.intValue()) {
					endDate.setDate(period.intValue());
					break;
				}
			}
		}
		return endDate;
	}
	
	private void populatePeriods() {
		periods.add(1);
		periods.add(6);
		periods.add(11);
		periods.add(16);
		periods.add(21);
		periods.add(26);
	}
	
	private void populateMonthNameMap() {
		monthNameMap.put(0, BabelFish.print().january());
		monthNameMap.put(1, BabelFish.print().february());
		monthNameMap.put(2, BabelFish.print().march());
		monthNameMap.put(3, BabelFish.print().april());
		monthNameMap.put(4, BabelFish.print().may());
		monthNameMap.put(5, BabelFish.print().june());
		monthNameMap.put(6, BabelFish.print().july());
		monthNameMap.put(7, BabelFish.print().august());
		monthNameMap.put(8, BabelFish.print().september());
		monthNameMap.put(9, BabelFish.print().october());
		monthNameMap.put(10, BabelFish.print().november());
		monthNameMap.put(11, BabelFish.print().december());
	}

	public ListBox getHispaniolaNDVIList() {
		return hispaniolaNDVIList;
	}

	public ListBox getHispaniolaNDVIDAList() {
		return hispaniolaNDVIDAList;
	}

	public ListBox getHispaniolaNDVIPYDList() {
		return hispaniolaNDVIPYDList;
	}

	public ListBox getHaitiNDVIList() {
		return haitiNDVIList;
	}

	public ListBox getHaitiNDVIDAList() {
		return haitiNDVIDAList;
	}

	public ListBox getHaitiNDVIPYDList() {
		return haitiNDVIPYDList;
	}

	public Map<Integer, Date> getPeriodDateMap() {
		return periodDateMap;
	}
	
}
