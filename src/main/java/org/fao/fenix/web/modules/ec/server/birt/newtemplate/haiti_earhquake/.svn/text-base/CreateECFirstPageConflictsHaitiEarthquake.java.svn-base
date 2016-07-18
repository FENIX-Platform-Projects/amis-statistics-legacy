package org.fao.fenix.web.modules.ec.server.birt.newtemplate.haiti_earhquake;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.core.domain.ec.ECConflictsBean;
import org.fao.fenix.core.domain.ec.ECValuesBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateECFirstPageConflictsHaitiEarthquake {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String tableFontSize = "7.5pt";
	
	Logger LOGGER = Logger.getLogger(CreateECFirstPageConflictsHaitiEarthquake.class);
	
	public CreateECFirstPageConflictsHaitiEarthquake(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	
	
	
	
	public GridHandle buildRefugeesSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("refugeesFoodSituationLegend", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>Source: <a href='http://www.unhcr.org/pages/4a013eb06.html'>UNHCR</a> Statistical Online Population Database, United Nations High Commissioner for Refugees (UNHCR), Data extracted on 22/10/2009.</div>");	
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	
	
	
	public GridHandle buildDisastersSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("disastersSource", 1, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>Source: <a href='http://www.gdacs.org/'>Global Disaster Alert and Coordination System</a> - 3 = 1000 or more people killed or 800000 or more people displaced. 2 = 100 or more people killed or 80000 or more displaced.</div>");
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	private List<ECValuesBean> disastersInformations(ECConflictsBean bean) {
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		
		/** floods **/
		if (!bean.getFloods().isEmpty() && bean.getEarthQuakes().isEmpty()){
			disasters = addFlods(bean);
		}
		
		/** earthquakes **/
		else if (bean.getFloods().isEmpty() && !bean.getEarthQuakes().isEmpty()){
			disasters = addEarthQuakes(bean);
		}
		
		/** floods & earthquakes **/
		else if ( !bean.getFloods().isEmpty() && !bean.getEarthQuakes().isEmpty()){
			disasters = addFloodsAndEarthquakes(bean);
		}
		
		else {
			ECValuesBean v = new ECValuesBean("-", "-");
			v.setSource("No Floods");
			v.setIntensity("-");
			disasters.add(v);
			v = new ECValuesBean("-", "-");
			v.setSource("No Earthquakes");
			v.setIntensity("-");
			disasters.add(v);
			v = new ECValuesBean("-", "-");
			v.setSource("NO DATA");
			v.setIntensity("-");
			disasters.add(v);
		}
		
		return disasters;		
	}
	
	private List<ECValuesBean> addFlods(ECConflictsBean bean){
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		int indicator = 0;
		
		while( indicator != 3) {
			if ( indicator < bean.getFloods().size() ) 
				disasters.add(bean.getFloods().get(indicator));
//			else {
//				ECValuesBean v = new ECValuesBean("-", "-");
//				v.setSource("-");
//				v.setIntensity("-");
//				disasters.add(v);			
//			}
			indicator++;
		}
		ECValuesBean v = new ECValuesBean("-", "-");
		v.setSource("No Earthquakes");
		v.setIntensity("-");
		disasters.add(v);
		v = new ECValuesBean("-", "-");
		v.setSource("NO DATA");
		v.setIntensity("-");
		disasters.add(v);
			
		return disasters;
	}
	
	private List<ECValuesBean> addEarthQuakes(ECConflictsBean bean){
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		int indicator = 0;
		
		while( indicator != 3) {
			if ( indicator < bean.getEarthQuakes().size()) 
				disasters.add(bean.getEarthQuakes().get(indicator));
//			else {
//				ECValuesBean v = new ECValuesBean("-", "-");
//				v.setSource("-");
//				v.setIntensity("-");
//				disasters.add(v);			
//			}
			indicator++;
		}	
		ECValuesBean v = new ECValuesBean("-", "-");
		v.setSource("No Floods");
		v.setIntensity("-");
		disasters.add(v);
		
		v = new ECValuesBean("-", "-");
		v.setSource("NO DATA");
		v.setIntensity("-");
		disasters.add(v);
			
		return disasters;
	}
	
	private List<ECValuesBean> addFloodsAndEarthquakes(ECConflictsBean bean){
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		int indicator = 0;
		int i=0;
		
		while ( i < 2) {
			if (i < bean.getFloods().size()) {
				disasters.add(bean.getFloods().get(i));
				indicator++;
			}
			i++;
		}
		
		i=0;
		while ( i < 2) {
			if ( i < bean.getEarthQuakes().size()) {
				disasters.add(bean.getEarthQuakes().get(i));
				indicator++;
			}
			i++;
		}
		
		while( indicator <= 3) {
			ECValuesBean v = new ECValuesBean("-", "-");
			v.setSource("NO DATA");
			v.setIntensity("-");
			disasters.add(v);			
			indicator++;
		}	
			
		
		return disasters;
	}
	
	private void printECvalue(List<ECValuesBean> beans){
		System.out.println("disasters");
		for(ECValuesBean bean : beans)
			System.out.println(bean.getDate() + " | " + bean.getValue());
	}
}