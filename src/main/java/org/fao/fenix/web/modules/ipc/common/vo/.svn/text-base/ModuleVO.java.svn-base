package org.fao.fenix.web.modules.ipc.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ModuleVO implements IsSerializable {

	private Integer level;
	
	private List<FreeTextVO> freeTexts;
	
	private List<DropDownVO> dropDowns;
	
	public ModuleVO() {
		freeTexts = new ArrayList<FreeTextVO>();
		dropDowns = new ArrayList<DropDownVO>();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<FreeTextVO> getFreeTexts() {
		return freeTexts;
	}

	public void setFreeTexts(List<FreeTextVO> freeTexts) {
		this.freeTexts = freeTexts;
	}

	public List<DropDownVO> getDropDowns() {
		return dropDowns;
	}

	public void setDropDowns(List<DropDownVO> dropDowns) {
		this.dropDowns = dropDowns;
	}
	
	public void addFreeTextVO(FreeTextVO ft) {
		if (this.freeTexts == null)
			this.freeTexts = new ArrayList<FreeTextVO>();
		this.freeTexts.add(ft);
	}
	
	public void addDropDownVO(DropDownVO dd) {
		if (this.dropDowns == null)
			this.dropDowns = new ArrayList<DropDownVO>();
		this.dropDowns.add(dd);
	}
	
}