package org.fao.fenix.web.modules.dataviewer.server;


import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

import org.fao.fenix.fwds.core.bean.DBBean;
import org.fao.fenix.fwds.core.bean.FWDSBean;
import org.fao.fenix.fwds.core.bean.RBean;
import org.fao.fenix.fwds.core.bean.SQLBean;
import org.fao.fenix.fwds.core.constant.DATASOURCE;
import org.fao.fenix.fwds.core.constant.ORDERBY;
import org.fao.fenix.fwds.core.constant.R;
import org.fao.fenix.fwds.core.constant.SQL;
import org.fao.fenix.fwds.tools.client.FWDSClient;
import org.fao.fenix.fwds.tools.exception.FWDSException;

import org.fao.fenix.r.tools.client.RClient;
import org.fao.fenix.web.modules.core.client.utils.FormatValues;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CONSTANT;

import com.google.gwt.i18n.client.LocaleInfo;

public class DataServiceUtils {
	
	private String wdsIP;

	private String wdsPORT;

	private String rIP;

	private String rPORT;
	
	private String rGwtIP;

	private String rGwtPORT;
	
	private final static Logger LOGGER = Logger.getLogger(DataServiceUtils.class);

	public List<DWCodesModelData> getCodes(String cs, String domainCode, String language, DATASOURCE datasource) throws AxisFault, FenixGWTException {
		List<DWCodesModelData> l = new ArrayList<DWCodesModelData>();
		FWDSClient c = new FWDSClient(wdsIP, wdsPORT);
		DBBean db = new DBBean(datasource);
		SQLBean sql = getCodesQuery(cs, domainCode, language);
		FWDSBean b = new FWDSBean(sql, db);
		List<List<String>> table = new ArrayList<List<String>>();
		try {
			table = c.querySynch(b);
		} catch (FWDSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < table.size(); i++)
			l.add(new DWCodesModelData(table.get(i).get(0), table.get(i).get(1)));
		return l;
	}

	public SQLBean getCodesQuery(String cs, String code, String language) throws FenixGWTException {
		CONSTANT c = CONSTANT.valueOf(cs.toUpperCase());
		switch (c) {
		case ITEM:
			return getItemCodes(code, language);
		case COUNTRY:
			return getCountryCodes(code, language);
		case ELEMENT:
			return getElementCodes(code, language);
		case DOMAIN:
			return getDomainCodes(code, language);
		case GROUP:
			return getGroupCodes(language);
		default:
			throw new FenixGWTException(c.name() + " is not a valid coding system name.");
		}
	}
	
	public SQLBean getItemCodes(String domainCode, String language) {
		SQLBean sql = new SQLBean();
		sql.select(null, "I.ItemCode", "Code");
		sql.select(null, "I.ItemName" + language, "Label");
		sql.from("item", "I");
		sql.from("domainitem", "DI");
		sql.where(SQL.DATE.name(), "DI.ItemCode", "=", "I.ItemCode", null);
		sql.where(SQL.TEXT.name(), "DI.DomainCode", "=", domainCode, null);
		sql.orderBy("I.ItemName" + language, ORDERBY.ASC.name());
		return sql;
	}
	
	public SQLBean getGroupCodes(String language) {
		SQLBean sql = new SQLBean();
		sql.select(null, "D.GroupCode", "Code");
		sql.select(null, "D.GroupName" + language, "Label");
		sql.from("domain", "D");
		sql.groupBy("D.GroupCode");
		sql.groupBy("D.GroupName" + language);
		sql.orderBy("D.GroupName" + language, ORDERBY.ASC.name());
		return sql;
	}

	public SQLBean getDomainCodes(String groupCode, String language) {
		SQLBean sql = new SQLBean();
		sql.select(null, "D.DomainCode", "Code");
		sql.select(null, "D.DomainName" + language, "Label");
		sql.from("domain", "D");
		sql.where(SQL.TEXT.name(), "D.GroupCode", "=", groupCode, null);
		sql.groupBy("D.DomainCode");
		sql.groupBy("D.DomainName" + language);
		sql.orderBy("D.DomainName" + language, ORDERBY.ASC.name());
		return sql;
	}

	public SQLBean getElementCodes(String domainCode, String language) {
		SQLBean sql = new SQLBean();
//		sql.select(null, "E.ElementCode", "Code");
		sql.select(null, "E.ElementListCode", "Code");
		sql.select(null, "E.ElementListName" + language, "Label");
		sql.from("element", "E");
		sql.from("domainelement", "DE");
		sql.where(SQL.DATE.name(), "DE.ElementCode", "=", "E.ElementCode", null);
		sql.where(SQL.TEXT.name(), "DE.DomainCode", "=", domainCode, null);
//		sql.groupBy("E.ElementCode");
//		sql.groupBy("E.ElementName" + language);
//		sql.orderBy("E.ElementName" + language, ORDERBY.ASC.name());
		sql.groupBy("E.ElementListCode");
		sql.groupBy("E.ElementListName" + language);
		sql.orderBy("E.ElementListName" + language, ORDERBY.ASC.name());
		return sql;
	}

	public SQLBean getCountryCodes(String domainCode, String language) {
		SQLBean sql = new SQLBean();
		sql.select(null, "A.AreaCode", "Code");
		sql.select(null, "A.AreaName" +language, "Label");
		sql.from("areagrouparea", "A");
		sql.from("domainarea", "DA");
		sql.where(SQL.DATE.name(), "DA.AreaCode", "=", "A.AreaCode", null);
		sql.where(SQL.TEXT.name(), "DA.DomainCode", "=", domainCode, null);
		sql.where(SQL.TEXT.name(), "A.Type", "=", "External", null);
		sql.groupBy("A.AreaCode");
		sql.groupBy("A.AreaName" + language);
		sql.orderBy("A.AreaName" + language, ORDERBY.ASC.name());
		return sql;
	}
	
	public Double annualGrowtRateLeastSquare(List<Double> values) {
        RClient c = new RClient(rIP, rPORT);
        RBean b = new RBean();
        // TODO: it's not needed
        b.setDb(new DBBean(DATASOURCE.FAOSTAT));
        b.setFunction(R.growthrate2.name());  
        
        for(Double value : values) 
        	b.addRawValue(value);

        List<List<String>> table = c.evalSynch(b);
        Double value = null;
        try {
        	value = Double.valueOf(table.get(0).get(0));

			String out = FormatValues.formatValue(value, 3);
        	value = Double.valueOf(out);    	
        }
        catch (Exception e) {
		}
        return value;
	}
	
	public String getRGwtURL(String language) {
		
		String url = "http://"+ rGwtIP + ":" + rGwtPORT + "/r-gwt/gui/GUI.html?locale="+ language;
//		String iframe = "<iframe src=\"http://ldvapp07.fao.org:8030/r-gwt/gui/GUI.html?locale="+ LocaleInfo.getCurrentLocale().getLocaleName() +"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
		
		LOGGER.info("url: " + url);
		return url;
	}
	
	public void setWdsIP(String wdsIP) {
		this.wdsIP = wdsIP;
	}

	public void setWdsPORT(String wdsPORT) {
		this.wdsPORT = wdsPORT;
	}

	public void setrIP(String rIP) {
		this.rIP = rIP;
	}

	public void setrPORT(String rPORT) {
		this.rPORT = rPORT;
	}

	public void setrGwtIP(String rGwtIP) {
		this.rGwtIP = rGwtIP;
	}

	public void setrGwtPORT(String rGwtPORT) {
		this.rGwtPORT = rGwtPORT;
	}

	

}
