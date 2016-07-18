package org.fao.fenix.web.modules.core.common.vo;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.google.gwt.user.client.rpc.AsyncCallback;

@Deprecated
/**
 * use Coding system GAUL0
 */
public class GAULCountryList {

	private LinkedHashMap<String, String> gaulCountryMap = new LinkedHashMap<String, String>();
	public LinkedHashMap<String, String> getGaulCountryMap() {
		return gaulCountryMap;
	}

	public void setGaulCountryMap(LinkedHashMap<String, String> gaulCountryMap) {
		this.gaulCountryMap = gaulCountryMap;
	}

	private LinkedHashMap<String, String> gaulStaticCountryMap;
	
	public GAULCountryList() {
	}
	
	public GAULCountryList(boolean getStaticMap) {
		if (getStaticMap==false ) {
			if(getGaulCountryMap().size() < 1)
				populateGAULCountryMap();
		} else { //temporary work around for fire fox issue
			gaulStaticCountryMap = new LinkedHashMap<String,String>();
			initializeStaticMap();
		}
	}
	public LinkedHashMap<String, String> getGAULCountryList() {
		return gaulCountryMap;
	}

  /*	public String getGAULCountryLabel(String key) {

		String label = (String) gaulCountryMap.get(key);

		return label;
	}*/
	
	public String getGAULCountryLabel(String key) {

		String label = getGaulCountryMap().get(key);

		return label;
	}
	

	public void populateGAULCountryMap() {

//		System.out.println("populateGAULCountryMap START !!!");

		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos)
					gaulCountryMap.put(vo.getCode(), vo.getLabel());
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
		
	}

	
	public LinkedHashMap<String,String> getStaticCountryList() {
		return gaulStaticCountryMap;
	}

	private void setStaticCountryList(LinkedHashMap<String,String> countryMap) {
		this.gaulStaticCountryMap = countryMap;
	}

	public String getStaticCountryLabel(String key) {
		String label = (String) gaulStaticCountryMap.get(key);

		return label;
	}

	private void initializeStaticMap(){
		gaulStaticCountryMap.put("0","World");
		gaulStaticCountryMap.put("1","Afghanistan");
		gaulStaticCountryMap.put("2","Aksai Chin");
		gaulStaticCountryMap.put("3","Albania");
		gaulStaticCountryMap.put("4","Algeria");
		gaulStaticCountryMap.put("5","American Samoa");
		gaulStaticCountryMap.put("7","Andorra");
		gaulStaticCountryMap.put("8","Angola");
		gaulStaticCountryMap.put("9","Anguilla");
		gaulStaticCountryMap.put("10","Antarctica");
		gaulStaticCountryMap.put("11","Antigua and Barbuda");
		gaulStaticCountryMap.put("12","Argentina");
		gaulStaticCountryMap.put("13","Armenia");
		gaulStaticCountryMap.put("14","Aruba");
		gaulStaticCountryMap.put("15","Arunashal Pradesh");
		gaulStaticCountryMap.put("16","Ashmore and Cartier Islands");
		gaulStaticCountryMap.put("17","Australia");
		gaulStaticCountryMap.put("18","Austria");
		gaulStaticCountryMap.put("19","Azerbaijan");
		gaulStaticCountryMap.put("20","Bahamas");
		gaulStaticCountryMap.put("21","Bahrain");
		gaulStaticCountryMap.put("22","Baker Island");
		gaulStaticCountryMap.put("23","Bangladesh");
		gaulStaticCountryMap.put("24","Barbados");
		gaulStaticCountryMap.put("25","Bassas da India");
		gaulStaticCountryMap.put("26","Belarus");
		gaulStaticCountryMap.put("27","Belgium");
		gaulStaticCountryMap.put("28","Belize");
		gaulStaticCountryMap.put("29","Benin");
		gaulStaticCountryMap.put("30","Bermuda");
		gaulStaticCountryMap.put("31","Bhutan");
		gaulStaticCountryMap.put("32","Bird Island");
		gaulStaticCountryMap.put("33","Bolivia");
		gaulStaticCountryMap.put("34","Bosnia and Herzegovina");
		gaulStaticCountryMap.put("35","Botswana");
		gaulStaticCountryMap.put("36","Bouvet Island");
		gaulStaticCountryMap.put("37","Brazil");
		gaulStaticCountryMap.put("38","British Indian Ocean Territory");
		gaulStaticCountryMap.put("39","British Virgin Islands");
		gaulStaticCountryMap.put("40","Brunei Darussalam");
		gaulStaticCountryMap.put("41","Bulgaria");
		gaulStaticCountryMap.put("42","Burkina Faso");
		gaulStaticCountryMap.put("43","Burundi");
		gaulStaticCountryMap.put("44","Cambodia");
		gaulStaticCountryMap.put("45","Cameroon");
		gaulStaticCountryMap.put("46","Canada");
		gaulStaticCountryMap.put("47","Cape Verde");
		gaulStaticCountryMap.put("48","Cayman Islands");
		gaulStaticCountryMap.put("49","Central African Republic");
		gaulStaticCountryMap.put("50","Chad");
		gaulStaticCountryMap.put("51","Chile");
		gaulStaticCountryMap.put("52","China/India");
		gaulStaticCountryMap.put("53","China");
		gaulStaticCountryMap.put("54","Christmas Island");
		gaulStaticCountryMap.put("55","Clipperton Island");
		gaulStaticCountryMap.put("56","Cocos (Keeling) Islands");
		gaulStaticCountryMap.put("57","Colombia");
		gaulStaticCountryMap.put("58","Comoros");
		gaulStaticCountryMap.put("59","Congo");
		gaulStaticCountryMap.put("60","Cook Islands");
		gaulStaticCountryMap.put("61","Costa Rica");
		gaulStaticCountryMap.put("62","Croatia");
		gaulStaticCountryMap.put("63","Cuba");
		gaulStaticCountryMap.put("64","Cyprus");
		gaulStaticCountryMap.put("65","Czech Republic");
		gaulStaticCountryMap.put("66","Cote d'Ivoire");
		gaulStaticCountryMap.put("67","Dem People's Rep of Korea");
		gaulStaticCountryMap.put("68","Democratic Republic of the Congo");
		gaulStaticCountryMap.put("69","Denmark");
		gaulStaticCountryMap.put("40763","Dhekelia and Akrotiri SBA");
		gaulStaticCountryMap.put("70","Djibouti");
		gaulStaticCountryMap.put("71","Dominica");
		gaulStaticCountryMap.put("72","Dominican Republic");
		gaulStaticCountryMap.put("73","Ecuador");
		gaulStaticCountryMap.put("40765","Egypt");
		gaulStaticCountryMap.put("75","El Salvador");
		gaulStaticCountryMap.put("76","Equatorial Guinea");
		gaulStaticCountryMap.put("77","Eritrea");
		gaulStaticCountryMap.put("78","Estonia");
		gaulStaticCountryMap.put("79","Ethiopia");
		gaulStaticCountryMap.put("80","Europa Island");
		gaulStaticCountryMap.put("81","Falkland Islands (Malvinas)");
		gaulStaticCountryMap.put("82","Faroe Islands");
		gaulStaticCountryMap.put("83","Fiji");
		gaulStaticCountryMap.put("84","Finland");
		gaulStaticCountryMap.put("85","France");
		gaulStaticCountryMap.put("86","French Guiana");
		gaulStaticCountryMap.put("87","French Polynesia");
		gaulStaticCountryMap.put("88","French Southern and Antarctic Territories");
		gaulStaticCountryMap.put("89","Gabon");
		gaulStaticCountryMap.put("90","Gambia");
		gaulStaticCountryMap.put("91","Gaza Strip");
		gaulStaticCountryMap.put("92","Georgia");
		gaulStaticCountryMap.put("93","Germany");
		gaulStaticCountryMap.put("94","Ghana");
		gaulStaticCountryMap.put("95","Gibraltar");
		gaulStaticCountryMap.put("96","Glorioso Island");
		gaulStaticCountryMap.put("97","Greece");
		gaulStaticCountryMap.put("98","Greenland");
		gaulStaticCountryMap.put("99","Grenada");
		gaulStaticCountryMap.put("100","Guadeloupe");
		gaulStaticCountryMap.put("101","Guam");
		gaulStaticCountryMap.put("103","Guatemala");
		gaulStaticCountryMap.put("104","Guernsey");
		gaulStaticCountryMap.put("105","Guinea-Bissau");
		gaulStaticCountryMap.put("106","Guinea");
		gaulStaticCountryMap.put("107","Guyana");
		gaulStaticCountryMap.put("108","Haiti");
		gaulStaticCountryMap.put("40760","Hala'ib triangle");
		gaulStaticCountryMap.put("109","Heard Island and McDonald Islands");
		gaulStaticCountryMap.put("110","Holy See");
		gaulStaticCountryMap.put("111","Honduras");
		gaulStaticCountryMap.put("33364","Hong Kong");
		gaulStaticCountryMap.put("112","Howland Island");
		gaulStaticCountryMap.put("113","Hungary");
		gaulStaticCountryMap.put("114","Iceland");
		gaulStaticCountryMap.put("61013","Ilemi triangle");
		gaulStaticCountryMap.put("115","India");
		gaulStaticCountryMap.put("116","Indonesia");
		gaulStaticCountryMap.put("117","Iran (Islamic Republic of)");
		gaulStaticCountryMap.put("118","Iraq");
		gaulStaticCountryMap.put("119","Ireland");
		gaulStaticCountryMap.put("120","Isle of Man");
		gaulStaticCountryMap.put("121","Israel");
		gaulStaticCountryMap.put("122","Italy");
		gaulStaticCountryMap.put("123","Jamaica");
		gaulStaticCountryMap.put("40781","Jammu Kashmir");
		gaulStaticCountryMap.put("126","Japan");
		gaulStaticCountryMap.put("127","Jarvis Island");
		gaulStaticCountryMap.put("128","Jersey");
		gaulStaticCountryMap.put("129","Johnston Atoll");
		gaulStaticCountryMap.put("130","Jordan");
		gaulStaticCountryMap.put("131","Juan de Nova Island");
		gaulStaticCountryMap.put("132","Kazakhstan");
		gaulStaticCountryMap.put("133","Kenya");
		gaulStaticCountryMap.put("134","Kingman Reef");
		gaulStaticCountryMap.put("135","Kiribati");
		gaulStaticCountryMap.put("136","Kuril islands");
		gaulStaticCountryMap.put("137","Kuwait");
		gaulStaticCountryMap.put("138","Kyrgyzstan");
		gaulStaticCountryMap.put("139","Lao People's Democratic Republic");
		gaulStaticCountryMap.put("140","Latvia");
		gaulStaticCountryMap.put("141","Lebanon");
		gaulStaticCountryMap.put("142","Lesotho");
		gaulStaticCountryMap.put("143","Liancourt Rock");
		gaulStaticCountryMap.put("144","Liberia");
		gaulStaticCountryMap.put("145","Libyan Arab Jamahiriya");
		gaulStaticCountryMap.put("146","Liechtenstein");
		gaulStaticCountryMap.put("147","Lithuania");
		gaulStaticCountryMap.put("148","Luxembourg");
		gaulStaticCountryMap.put("40762","Ma'tan al-Sarra");
		gaulStaticCountryMap.put("149","Macau");
		gaulStaticCountryMap.put("150","Madagascar");
		gaulStaticCountryMap.put("151","Madeira Islands");
		gaulStaticCountryMap.put("152","Malawi");
		gaulStaticCountryMap.put("153","Malaysia");
		gaulStaticCountryMap.put("154","Maldives");
		gaulStaticCountryMap.put("155","Mali");
		gaulStaticCountryMap.put("156","Malta");
		gaulStaticCountryMap.put("157","Marshall Islands");
		gaulStaticCountryMap.put("158","Martinique");
		gaulStaticCountryMap.put("159","Mauritania");
		gaulStaticCountryMap.put("160","Mauritius");
		gaulStaticCountryMap.put("161","Mayotte");
		gaulStaticCountryMap.put("162","Mexico");
		gaulStaticCountryMap.put("163","Micronesia (Federated States of)");
		gaulStaticCountryMap.put("164","Midway Island");
		gaulStaticCountryMap.put("165","Moldova, Republic of");
		gaulStaticCountryMap.put("166","Monaco");
		gaulStaticCountryMap.put("167","Mongolia");
		gaulStaticCountryMap.put("2647","Montenegro");
		gaulStaticCountryMap.put("168","Montserrat");
		gaulStaticCountryMap.put("169","Morocco");
		gaulStaticCountryMap.put("170","Mozambique");
		gaulStaticCountryMap.put("171","Myanmar");
		gaulStaticCountryMap.put("172","Namibia");
		gaulStaticCountryMap.put("173","Nauru");
		gaulStaticCountryMap.put("174","Navassa Island");
		gaulStaticCountryMap.put("175","Nepal");
		gaulStaticCountryMap.put("176","Netherlands Antilles");
		gaulStaticCountryMap.put("177","Netherlands");
		gaulStaticCountryMap.put("178","New Caledonia");
		gaulStaticCountryMap.put("179","New Zealand");
		gaulStaticCountryMap.put("180","Nicaragua");
		gaulStaticCountryMap.put("181","Niger");
		gaulStaticCountryMap.put("182","Nigeria");
		gaulStaticCountryMap.put("183","Niue");
		gaulStaticCountryMap.put("184","Norfolk Island");
		gaulStaticCountryMap.put("185","Northern Mariana Islands");
		gaulStaticCountryMap.put("186","Norway");
		gaulStaticCountryMap.put("187","Oman");
		gaulStaticCountryMap.put("188","Pakistan");
		gaulStaticCountryMap.put("189","Palau");
		gaulStaticCountryMap.put("190","Palmyra Atoll");
		gaulStaticCountryMap.put("191","Panama");
		gaulStaticCountryMap.put("192","Papua New Guinea");
		gaulStaticCountryMap.put("193","Paracel Islands");
		gaulStaticCountryMap.put("194","Paraguay");
		gaulStaticCountryMap.put("195","Peru");
		gaulStaticCountryMap.put("196","Philippines");
		gaulStaticCountryMap.put("197","Pitcairn");
		gaulStaticCountryMap.put("198","Poland");
		gaulStaticCountryMap.put("199","Portugal");
		gaulStaticCountryMap.put("200","Puerto Rico");
		gaulStaticCountryMap.put("201","Qatar");
		gaulStaticCountryMap.put("202","Republic of Korea");
		gaulStaticCountryMap.put("2648","Republic of Serbia");
		gaulStaticCountryMap.put("203","Romania");
		gaulStaticCountryMap.put("204","Russian Federation");
		gaulStaticCountryMap.put("205","Rwanda");
		gaulStaticCountryMap.put("206","Reunion");
		gaulStaticCountryMap.put("207","Saint Helena");
		gaulStaticCountryMap.put("208","Saint Kitts and Nevis");
		gaulStaticCountryMap.put("209","Saint Lucia");
		gaulStaticCountryMap.put("210","Saint Pierre et Miquelon");
		gaulStaticCountryMap.put("211","Saint Vincent and the Grenadines");
		gaulStaticCountryMap.put("212","Samoa");
		gaulStaticCountryMap.put("213","San Marino");
		gaulStaticCountryMap.put("214","Sao Tome and Principe");
		gaulStaticCountryMap.put("215","Saudi Arabia");
		gaulStaticCountryMap.put("216","Scarborough Reef");
		gaulStaticCountryMap.put("217","Senegal");
		gaulStaticCountryMap.put("218","Senkaku Islands");
		gaulStaticCountryMap.put("220","Seychelles");
		gaulStaticCountryMap.put("221","Sierra Leone");
		gaulStaticCountryMap.put("222","Singapore");
		gaulStaticCountryMap.put("223","Slovakia");
		gaulStaticCountryMap.put("224","Slovenia");
		gaulStaticCountryMap.put("225","Solomon Islands");
		gaulStaticCountryMap.put("226","Somalia");
		gaulStaticCountryMap.put("227","South Africa");
		gaulStaticCountryMap.put("228","South Georgia and the South Sandwich Islands");
		gaulStaticCountryMap.put("229","Spain");
		gaulStaticCountryMap.put("230","Spratly Islands");
		gaulStaticCountryMap.put("231","Sri Lanka");
		gaulStaticCountryMap.put("40764","Sudan");
		gaulStaticCountryMap.put("233","Suriname");
		gaulStaticCountryMap.put("234","Svalbard and Jan Mayen Islands");
		gaulStaticCountryMap.put("235","Swaziland");
		gaulStaticCountryMap.put("236","Sweden");
		gaulStaticCountryMap.put("237","Switzerland");
		gaulStaticCountryMap.put("238","Syrian Arab Republic");
		gaulStaticCountryMap.put("239","Tajikistan");
		gaulStaticCountryMap.put("240","Thailand");
		gaulStaticCountryMap.put("241","The former Yugoslav Republic of Macedonia");
		gaulStaticCountryMap.put("242","Timor-Leste");
		gaulStaticCountryMap.put("243","Togo");
		gaulStaticCountryMap.put("244","Tokelau");
		gaulStaticCountryMap.put("245","Tonga");
		gaulStaticCountryMap.put("246","Trinidad and Tobago");
		gaulStaticCountryMap.put("247","Tromelin Island");
		gaulStaticCountryMap.put("248","Tunisia");
		gaulStaticCountryMap.put("249","Turkey");
		gaulStaticCountryMap.put("250","Turkmenistan");
		gaulStaticCountryMap.put("251","Turks and Caicos islands");
		gaulStaticCountryMap.put("252","Tuvalu");
		gaulStaticCountryMap.put("253","Uganda");
		gaulStaticCountryMap.put("254","Ukraine");
		gaulStaticCountryMap.put("255","United Arab Emirates");
		gaulStaticCountryMap.put("256","U.K. of Great Britain and Northern Ireland");
		gaulStaticCountryMap.put("257","United Republic of Tanzania");
		gaulStaticCountryMap.put("258","United States Virgin Islands");
		gaulStaticCountryMap.put("259","United States of America");
		gaulStaticCountryMap.put("260","Uruguay");
		gaulStaticCountryMap.put("261","Uzbekistan");
		gaulStaticCountryMap.put("262","Vanuatu");
		gaulStaticCountryMap.put("263","Venezuela");
		gaulStaticCountryMap.put("264","Viet Nam");
		gaulStaticCountryMap.put("265","Wake Island");
		gaulStaticCountryMap.put("266","Wallis and Futuna");
		gaulStaticCountryMap.put("267","West Bank");
		gaulStaticCountryMap.put("268","Western Sahara");
		gaulStaticCountryMap.put("269","Yemen");
		gaulStaticCountryMap.put("270","Zambia");
		gaulStaticCountryMap.put("271","Zimbabwe");
		
		setStaticCountryList(gaulStaticCountryMap);
	}
}
