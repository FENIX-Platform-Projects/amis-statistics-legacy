package org.fao.fenix.web.modules.birt.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ReportVo implements IsSerializable{

	String template;
	List<Long> mapIdList = new ArrayList<Long>();
	List<Long> chartIdList = new ArrayList<Long>();
	List<Long> tableIdList = new ArrayList<Long>();
	List<Long> textIdList = new ArrayList<Long>();
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<Long> getMapIdList() {
		return mapIdList;
	}
	
	public void setMapIdList(List<Long> mapIdList) {
		this.mapIdList = mapIdList;
	}
	
	public void addsetMapIdList(Long id){
		this.mapIdList.add(id);
	}
	
	public List<Long> getChartIdList() {
		return chartIdList;
	}
	
	public void setChartIdList(List<Long> chartIdList) {
		this.chartIdList = chartIdList;
	}
	
	public void addsetChartIdList(Long id){
		this.chartIdList.add(id);
	}
	
	public List<Long> getTableIdList() {
		return tableIdList;
	}
	
	public void setTableIdList(List<Long> tableIdList) {
		this.tableIdList = tableIdList;
	}
	
	public void addsetTableIdList(Long id){
		this.tableIdList.add(id);
	}
	
	public List<Long> getTextIdList() {
		return textIdList;
	}
	
	public void setTextIdList(List<Long> textIdList) {
		this.textIdList = textIdList;
	}
	
	public void addsetTextIdList(Long id){
		this.textIdList.add(id);
	}
	
}
