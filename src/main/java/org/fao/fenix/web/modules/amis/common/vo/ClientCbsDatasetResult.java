package org.fao.fenix.web.modules.amis.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClientCbsDatasetResult implements IsSerializable {
	private String database;
	private String region_code;
	private String region_name;
	private String product_code;
	private String product_name;
	private String element_code;
	private String element_name;
	private String units;
	private String annotation;
	private String month;
	private String year;
	private String value;
	private String valueType;
	private String id;
	private String last_update;
	
	//Element_Name, Element_Code, value, annotation, month, year
	public ClientCbsDatasetResult()
	{
		
	}

	public ClientCbsDatasetResult(String database, String region_code, String region_name, String product_code, String product_name, String element_code, String element_name, String units, String year, String month, String value, String annotation, String value_type, String last_update){
		
		this.database = database;
		this.region_code = region_code;
		this.region_name = region_name;
		this.product_code = product_code;
		this.product_name = product_name;
		this.element_code = element_code;
		this.element_name = element_name;
		this.units = units;
		this.year = year;
		this.value = value;
		this.annotation = annotation;
		this.month = month;
		this.valueType = value_type;
		this.last_update = last_update;
	}

	public ClientCbsDatasetResult(String database, String region_code, String region_name, String product_code, String product_name, String element_code, String element_name, String units, String year, String month, String value, String annotation){
		
		this.database = database;
		this.region_code = region_code;
		this.region_name = region_name;
		this.product_code = product_code;
		this.product_name = product_name;
		this.element_code = element_code;
		this.element_name = element_name;
		this.units = units;
		this.year = year;
		this.value = value;
		this.annotation = annotation;
		this.month = month;
	}
	
	public ClientCbsDatasetResult(String database, String region_code, String region_name, String product_code, String product_name, String element_code, String element_name, String units, String year, String value, String annotation, String month, String valueType){
		
		this.database = database;
		this.region_code = region_code;
		this.region_name = region_name;
		this.product_code = product_code;
		this.product_name = product_name;
		this.element_code = element_code;
		this.element_name = element_name;
		this.units = units;
		this.year = year;
		this.value = value;
		this.annotation = annotation;
		this.month = month;
		this.valueType = valueType;
	}
	
	public ClientCbsDatasetResult(String element_name, String element_code, String value, String annotation, String month, String year){
		
		this.element_name = element_name;
		this.element_code = element_code;
		this.month = month;
		this.annotation = annotation;
		this.year = year;
		this.value = value;
	}
	
public ClientCbsDatasetResult(String value, String annotation, String month, String year){
	
		this.month = month;
		this.annotation = annotation;
		this.year = year;
		this.value = value;
	}
	
	public String getDatabase() {
		return database;
	}	
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getRegion_code() {
		return region_code;
	}
	public void setRegion_code(String regionCode) {
		region_code = regionCode;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String regionName) {
		region_name = regionName;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String productCode) {
		product_code = productCode;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String productName) {
		product_name = productName;
	}
	public String getElement_code() {
		return element_code;
	}
	public void setElement_code(String elementCode) {
		element_code = elementCode;
	}
	public String getElement_name() {
		return element_name;
	}
	public void setElement_name(String elementName) {
		element_name = elementName;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DatasetResult [database=" + database + ", element_code="
				+ element_code + ", element_name=" + element_name
				+ ", product_code=" + product_code + ", product_name="
				+ product_name + ", region_code=" + region_code
				+ ", region_name=" + region_name + ", units=" + units
				+ ", value=" + value + ", year=" + year + "]";
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String lastUpdate) {
		last_update = lastUpdate;
	}
}
