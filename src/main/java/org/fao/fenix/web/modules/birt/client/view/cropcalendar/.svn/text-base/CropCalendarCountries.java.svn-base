package org.fao.fenix.web.modules.birt.client.view.cropcalendar;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

public class CropCalendarCountries {

	private LinkedHashMap<String,String> countryMap;

	public CropCalendarCountries() {

		countryMap = new LinkedHashMap<String,String>();
		initializeCountryMap();

	}

	private void initializeCountryMap() {
		countryMap.put("3","Albania");
		countryMap.put("4","Algeria");
		countryMap.put("8","Angola");
		countryMap.put("12","Argentina");
		countryMap.put("13","Armenia");
		countryMap.put("19","Azerbaijan");
		countryMap.put("23","Bangladesh");
		countryMap.put("26","Belarus");
		countryMap.put("29","Benin");
		countryMap.put("33","Bolivia");
		countryMap.put("35","Botswana");
		countryMap.put("37","Brazil");
		countryMap.put("42","Burkina Faso");
		countryMap.put("43","Burundi");
		countryMap.put("44","Cambodia");
		countryMap.put("45","Cameroon");
		countryMap.put("47","Cape Verde");
		countryMap.put("49","Central African Republic");
		countryMap.put("50","Chad");
		countryMap.put("51","Chile");
		countryMap.put("53","China");
		countryMap.put("57","Colombia");
		countryMap.put("59","Congo");
		countryMap.put("61","Costa Rica");
		countryMap.put("66","Cote d'Ivoire");
		countryMap.put("63","Cuba");
		countryMap.put("64","Cyprus");
		countryMap.put("67","Dem People's Rep of Korea");
		countryMap.put("68","Democratic Republic of the Congo");
		countryMap.put("72","Dominican Republic");
		countryMap.put("73","Ecuador");
		countryMap.put("75","El Salvador");
		countryMap.put("77","Eritrea");
		countryMap.put("78","Estonia");
		countryMap.put("79","Ethiopia");
		countryMap.put("86","French Guiana");
		countryMap.put("89","Gabon");
		countryMap.put("90","Gambia");
		countryMap.put("92","Georgia");
		countryMap.put("94","Ghana");
		countryMap.put("103","Guatemala");
		countryMap.put("106","Guinea");
		countryMap.put("105","Guinea-Bissau");
		countryMap.put("107","Guyana");
		countryMap.put("108","Haiti");
		countryMap.put("115","India");
		countryMap.put("116","Indonesia");
		countryMap.put("117","Iran  (Islamic Republic of)");
		countryMap.put("118","Iraq");
		countryMap.put("121","Israel");
		countryMap.put("126","Japan");
		countryMap.put("130","Jordan");
		countryMap.put("132","Kazakhstan");
		countryMap.put("133","Kenya");
		countryMap.put("138","Kyrgyzstan");
		countryMap.put("139","Lao People's Democratic Republic");
		countryMap.put("140","Latvia");
		countryMap.put("141","Lebanon");
		countryMap.put("142","Lesotho");
		countryMap.put("144","Liberia");
		countryMap.put("145","Libyan Arab Jamahiriya");
		countryMap.put("147","Lithuania");
		countryMap.put("150","Madagascar");
		countryMap.put("152","Malawi");
		countryMap.put("153","Malaysia");
		countryMap.put("155","Mali");
		countryMap.put("159","Mauritania");
		countryMap.put("162","Mexico");
		countryMap.put("165","Moldova, Republic of");
		countryMap.put("169","Morocco");
		countryMap.put("170","Mozambique");
		countryMap.put("171","Myanmar");
		countryMap.put("172","Namibia");
		countryMap.put("175","Nepal");
		countryMap.put("180","Nicaragua");
		countryMap.put("181","Niger");
		countryMap.put("182","Nigeria");
		countryMap.put("188","Pakistan");
		countryMap.put("191","Panama");
		countryMap.put("194","Paraguay");
		countryMap.put("195","Peru");
		countryMap.put("196","Philippines");
		countryMap.put("202","Republic of Korea");
		countryMap.put("204","Russian Federation");
		countryMap.put("205","Rwanda");
		countryMap.put("215","Saudi Arabia");
		countryMap.put("217","Senegal");
		countryMap.put("221","Sierra Leone");
		countryMap.put("226","Somalia");
		countryMap.put("227","South Africa");
		countryMap.put("231","Sri Lanka");
		countryMap.put("40764","Sudan");
		countryMap.put("233","Suriname");
		countryMap.put("235","Swaziland");
		countryMap.put("238","Syrian Arab Republic");
		countryMap.put("239","Tajikistan");
		countryMap.put("240","Thailand");
		countryMap.put("241","The former Yugoslav Republic of Macedonia");
		countryMap.put("242","Timor-Leste");
		countryMap.put("243","Togo");
		countryMap.put("246","Trinidad and Tobago");
		countryMap.put("248","Tunisia");
		countryMap.put("249","Turkey");
		countryMap.put("250","Turkmenistan");
		countryMap.put("253","Uganda");
		countryMap.put("254","Ukraine");
		countryMap.put("257","United Republic of Tanzania");
		countryMap.put("260","Uruguay");
		countryMap.put("261","Uzbekistan");
		countryMap.put("263","Venezuela");
		countryMap.put("264","Viet Nam");
		countryMap.put("270","Zambia");
		countryMap.put("271","Zimbabwe");	
	}

	public ListBox getCountriesListBox() {
		ListBox list = new ListBox();
		
		Iterator<Map.Entry<String, String>> keyValuePairs = countryMap.entrySet().iterator();

		for (int i = 0; i < countryMap.size(); i++) {
			Map.Entry<String, String> entry = keyValuePairs.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			list.addItem(value, key);

		}
		return list;

	}
	
}
