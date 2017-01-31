package org.fao.fenix.web.modules.core.client.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.user.client.ui.ListBox;

public class CurrencyList {

	private LinkedHashMap<String, String> currencyMap;

	public CurrencyList() {

		currencyMap = new LinkedHashMap<String, String>();
		initializeCurrencyMap();

	}

	public LinkedHashMap<String, String> getCurrencyList() {
		return currencyMap;
	}

	private void setCurrencyList(LinkedHashMap<String, String> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public String getCurrencyLabel(String key) {

		String label = (String) currencyMap.get(key);

		return label;
	}

	private void initializeCurrencyMap() {

		currencyMap.put("AED","United Arab Emirates, Dirhams");
		currencyMap.put("AFN","Afghanistan, Afghanis");
		currencyMap.put("ALL","Albania, Leke");
		currencyMap.put("AMD","Armenia, Drams");
		currencyMap.put("ANG","Netherlands Antilles, Guilders (also called Florins)");
		currencyMap.put("AOA","Angola, Kwanza");
		currencyMap.put("ARS","Argentina, Pesos");
		currencyMap.put("AUD","Australia, Dollars");
		currencyMap.put("AWG","Aruba, Guilders (also called Florins)");
		currencyMap.put("AZN","Azerbaijan, New Manats");
		currencyMap.put("BAM","Bosnia and Herzegovina, Convertible Marka");
		currencyMap.put("BBD","Barbados, Dollars");
		currencyMap.put("BDT","Bangladesh, Taka");
		currencyMap.put("BGN","Bulgaria, Leva");
		currencyMap.put("BHD","Bahrain, Dinars");
		currencyMap.put("BIF","Burundi, Francs");
		currencyMap.put("BMD","Bermuda, Dollars");
		currencyMap.put("BND","Brunei Darussalam, Dollars");
		currencyMap.put("BOB","Bolivia, Bolivianos");
		currencyMap.put("BRL","Brazil, Brazil Real");
		currencyMap.put("BSD","Bahamas, Dollars");
		currencyMap.put("BTN","Bhutan, Ngultrum");
		currencyMap.put("BWP","Botswana, Pulas");
		currencyMap.put("BYR","Belarus, Rubles");
		currencyMap.put("BZD","Belize, Dollars");
		currencyMap.put("CAD","Canada, Dollars");
		currencyMap.put("CDF","Congo/Kinshasa, Congolese Francs");
		currencyMap.put("CHF","Switzerland, Francs");
		currencyMap.put("CLP","Chile, Pesos");
		currencyMap.put("CNY","China, Yuan Renminbi");
		currencyMap.put("COP","Colombia, Pesos");
		currencyMap.put("CRC","Costa Rica, Colones");
		currencyMap.put("CUP","Cuba, Pesos");
		currencyMap.put("CVE","Cape Verde, Escudos");
		currencyMap.put("CYP","Cyprus, Pounds (expires 2008-Jan-31)");
		currencyMap.put("CZK","Czech Republic, Koruny");
		currencyMap.put("DJF","Djibouti, Francs");
		currencyMap.put("DKK","Denmark, Kroner");
		currencyMap.put("DOP","Dominican Republic, Pesos");
		currencyMap.put("DZD","Algeria, Algeria Dinars");
		currencyMap.put("EEK","Estonia, Krooni");
		currencyMap.put("EGP","Egypt, Pounds");
		currencyMap.put("ERN","Eritrea, Nakfa");
		currencyMap.put("ETB","Ethiopia, Birr");
		currencyMap.put("EUR","Euro Member Countries, Euro");
		currencyMap.put("FJD","Fiji, Dollars");
		currencyMap.put("FKP","Falkland Islands (Malvinas), Pounds");
		currencyMap.put("GBP","United Kingdom, Pounds");
		currencyMap.put("GEL","Georgia, Lari");
		currencyMap.put("GGP","Guernsey, Pounds");
		currencyMap.put("GHS","Ghana, Cedis");
		currencyMap.put("GIP","Gibraltar, Pounds");
		currencyMap.put("GMD","Gambia, Dalasi");
		currencyMap.put("GNF","Guinea, Francs");
		currencyMap.put("GTQ","Guatemala, Quetzales");
		currencyMap.put("GYD","Guyana, Dollars");
		currencyMap.put("HKD","Hong Kong, Dollars");
		currencyMap.put("HNL","Honduras, Lempiras");
		currencyMap.put("HRK","Croatia, Kuna");
		currencyMap.put("HTG","Haiti, Gourdes");
		currencyMap.put("HUF","Hungary, Forint");
		currencyMap.put("IDR","Indonesia, Rupiahs");
		currencyMap.put("ILS","Israel, New Shekels");
		currencyMap.put("IMP","Isle of Man, Pounds");
		currencyMap.put("INR","India, Rupees");
		currencyMap.put("IQD","Iraq, Dinars");
		currencyMap.put("IRR","Iran, Rials");
		currencyMap.put("ISK","Iceland, Kronur");
		currencyMap.put("JEP","Jersey, Pounds");
		currencyMap.put("JMD","Jamaica, Dollars");
		currencyMap.put("JOD","Jordan, Dinars");
		currencyMap.put("JPY","Japan, Yen");
		currencyMap.put("KES","Kenya, Shillings");
		currencyMap.put("KGS","Kyrgyzstan, Soms");
		currencyMap.put("KHR","Cambodia, Riels");
		currencyMap.put("KMF","Comoros, Francs");
		currencyMap.put("KPW","Korea (North), Won");
		currencyMap.put("KRW","Korea (South), Won");
		currencyMap.put("KWD","Kuwait, Dinars");
		currencyMap.put("KYD","Cayman Islands, Dollars");
		currencyMap.put("KZT","Kazakhstan, Tenge");
		currencyMap.put("LAK","Laos, Kips");
		currencyMap.put("LBP","Lebanon, Pounds");
		currencyMap.put("LKR","Sri Lanka, Rupees");
		currencyMap.put("LRD","Liberia, Dollars");
		currencyMap.put("LSL","Lesotho, Maloti");
		currencyMap.put("LTL","Lithuania, Litai");
		currencyMap.put("LVL","Latvia, Lati");
		currencyMap.put("LYD","Libya, Dinars");
		currencyMap.put("MAD","Morocco, Dirhams");
		currencyMap.put("MDL","Moldova, Lei");
		currencyMap.put("MGA","Madagascar, Ariary");
		currencyMap.put("MKD","Macedonia, Denars");
		currencyMap.put("MMK","Myanmar (Burma), Kyats");
		currencyMap.put("MNT","Mongolia, Tugriks");
		currencyMap.put("MOP","Macau, Patacas");
		currencyMap.put("MRO","Mauritania, Ouguiyas");
		currencyMap.put("MTL","Malta, Liri (expires 2008-Jan-31)");
		currencyMap.put("MUR","Mauritius, Rupees");
		currencyMap.put("MVR","Maldives (Maldive Islands), Rufiyaa");
		currencyMap.put("MWK","Malawi, Kwachas");
		currencyMap.put("MXN","Mexico, Pesos");
		currencyMap.put("MYR","Malaysia, Ringgits");
		currencyMap.put("MZN","Mozambique, Meticais");
		currencyMap.put("NAD","Namibia, Dollars");
		currencyMap.put("NGN","Nigeria, Nairas");
		currencyMap.put("NIO","Nicaragua, Cordobas");
		currencyMap.put("NOK","Norway, Krone");
		currencyMap.put("NPR","Nepal, Nepal Rupees");
		currencyMap.put("NZD","New Zealand, Dollars");
		currencyMap.put("OMR","Oman, Rials");
		currencyMap.put("PAB","Panama, Balboa");
		currencyMap.put("PEN","Peru, Nuevos Soles");
		currencyMap.put("PGK","Papua New Guinea, Kina");
		currencyMap.put("PHP","Philippines, Pesos");
		currencyMap.put("PKR","Pakistan, Rupees");
		currencyMap.put("PLN","Poland, Zlotych");
		currencyMap.put("PYG","Paraguay, Guarani");
		currencyMap.put("QAR","Qatar, Rials");
		currencyMap.put("RON","Romania, New Lei");
		currencyMap.put("RSD","Serbia, Dinars");
		currencyMap.put("RUB","Russia, Rubles");
		currencyMap.put("RWF","Rwanda, Rwanda Francs");
		currencyMap.put("SAR","Saudi Arabia, Riyals");
		currencyMap.put("SBD","Solomon Islands, Dollars");
		currencyMap.put("SCR","Seychelles, Rupees");
		currencyMap.put("SDG","Sudan, Pounds");
		currencyMap.put("SEK","Sweden, Kronor");
		currencyMap.put("SGD","Singapore, Dollars");
		currencyMap.put("SHP","Saint Helena, Pounds");
		currencyMap.put("SKK","Slovakia, Koruny");
		currencyMap.put("SLL","Sierra Leone, Leones");
		currencyMap.put("SOS","Somalia, Shillings");
		currencyMap.put("SPL","Seborga, Luigini");
		currencyMap.put("SRD","Suriname, Dollars");
		currencyMap.put("STD","So Tome and Principe, Dobras");
		currencyMap.put("SVC","El Salvador, Colones");
		currencyMap.put("SYP","Syria, Pounds");
		currencyMap.put("SZL","Swaziland, Emalangeni");
		currencyMap.put("THB","Thailand, Baht");
		currencyMap.put("TJS","Tajikistan, Somoni");
		currencyMap.put("TMM","Turkmenistan, Manats");
		currencyMap.put("TND","Tunisia, Dinars");
		currencyMap.put("TOP","Tonga, Pa'anga");
		currencyMap.put("TRY","Turkey, New Lira");
		currencyMap.put("TTD","Trinidad and Tobago, Dollars");
		currencyMap.put("TVD","Tuvalu, Tuvalu Dollars");
		currencyMap.put("TWD","Taiwan, New Dollars");
		currencyMap.put("TZS","Tanzania, Shillings");
		currencyMap.put("UAH","Ukraine, Hryvnia");
		currencyMap.put("UGX","Uganda, Shillings");
		currencyMap.put("USD","United States of America, Dollars");
		currencyMap.put("UYU","Uruguay, Pesos");
		currencyMap.put("UZS","Uzbekistan, Sums");
		currencyMap.put("VEB","Venezuela, Bolivares (expires 2008-Jun-30)");
		currencyMap.put("VEF","Venezuela, Bolivares Fuertes");
		currencyMap.put("VND","Viet Nam, Dong");
		currencyMap.put("VUV","Vanuatu, Vatu");
		currencyMap.put("WST","Samoa, Tala");
		currencyMap.put("XAF","Communaut Financire Africaine BEAC, Francs");
		currencyMap.put("XAG","Silver, Ounces");
		currencyMap.put("XAU","Gold, Ounces");
		currencyMap.put("XCD","East Caribbean Dollars");
		currencyMap.put("XDR","International Monetary Fund (IMF) Special Drawing Rights");
		currencyMap.put("XOF","Communaut Financire Africaine BCEAO, Francs");
		currencyMap.put("XPD","Palladium Ounces");
		currencyMap.put("XPF","Comptoirs Franais du Pacifique Francs");
		currencyMap.put("XPT","Platinum, Ounces");
		currencyMap.put("YER","Yemen, Rials");
		currencyMap.put("ZAR","South Africa, Rand");
		currencyMap.put("ZMK","Zambia, Kwacha");
		currencyMap.put("ZWD","Zimbabwe, Zimbabwe Dollars");


		setCurrencyList(currencyMap);

	}

	public ListBox getCurrencyListBox() {
		ListBox list = new ListBox();
		Map map = getCurrencyList();

		list.setWidth("95%");
		list.setName("currency");

		list.addItem("", "");

		Iterator keyValuePairs = map.entrySet().iterator();

		for (int i = 0; i < map.size(); i++) {
			Map.Entry entry = (Entry) keyValuePairs.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			list.addItem(value, key);

		}
		return list;

	}

}
