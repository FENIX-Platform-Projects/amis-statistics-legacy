package org.fao.fenix.web.modules.core.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FenixGaulVo implements IsSerializable {

	private long code;
	private String name;
	private String continent;
	private String region;
	private String gaul0Code;
	private String gaul1code;
	private String level;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getGaul0Code() {
		return gaul0Code;
	}
	public void setGaul0Code(String gaul0Code) {
		this.gaul0Code = gaul0Code;
	}
	public String getGaul1code() {
		return gaul1code;
	}
	public void setGaul1code(String gaul1code) {
		this.gaul1code = gaul1code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
