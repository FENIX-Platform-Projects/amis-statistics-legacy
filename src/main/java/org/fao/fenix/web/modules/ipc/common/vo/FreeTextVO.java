package org.fao.fenix.web.modules.ipc.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FreeTextVO implements IsSerializable {

	private String code;

	private String name;

	private String rangeCode;

	private String rangeLabel;

	private Double exactValue;
	
	private String levelCode;

	private String levelLabel;

	private List<BulletPointVO> bulletPoints;

	private List<DropDownVO> dropDowns;

	private List<FreeTextVO> freeTexts;
	
	public FreeTextVO() {
		code = new String();
		name = new String();
		rangeCode = new String();
		rangeLabel = new String();
		levelCode = new String();
		levelLabel = new String();
		bulletPoints = new ArrayList<BulletPointVO>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRangeCode() {
		return rangeCode;
	}

	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	public String getRangeLabel() {
		return rangeLabel;
	}

	public void setRangeLabel(String rangeLabel) {
		this.rangeLabel = rangeLabel;
	}

	public Double getExactValue() {
		return exactValue;
	}

	public void setExactValue(Double exactValue) {
		this.exactValue = exactValue;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelLabel() {
		return levelLabel;
	}

	public void setLevelLabel(String levelLabel) {
		this.levelLabel = levelLabel;
	}

	public List<BulletPointVO> getBulletPoints() {
		return bulletPoints;
	}

	public void setBulletPoints(List<BulletPointVO> bulletPoints) {
		this.bulletPoints = bulletPoints;
	}

	public List<DropDownVO> getDropDowns() {
		return dropDowns;
	}

	public void setDropDowns(List<DropDownVO> dropDowns) {
		this.dropDowns = dropDowns;
	}

	public List<FreeTextVO> getFreeTexts() {
		return freeTexts;
	}

	public void setFreeTexts(List<FreeTextVO> freeTexts) {
		this.freeTexts = freeTexts;
	}

}