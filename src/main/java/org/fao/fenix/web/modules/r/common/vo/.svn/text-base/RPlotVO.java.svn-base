package org.fao.fenix.web.modules.r.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RPlotVO implements IsSerializable {

	private String name;
	
	private String description;
	
	private String rFormula;
	
	private boolean repeat;
	
	private List<ROptionVO> options;
	
	public RPlotVO() {
		options = new ArrayList<ROptionVO>();
	}
	
	public void addOption(ROptionVO o) {
		if (this.options == null)
			this.options = new ArrayList<ROptionVO>();
		this.options.add(o);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getrFormula() {
		return rFormula;
	}

	public void setrFormula(String rFormula) {
		this.rFormula = rFormula;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public List<ROptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<ROptionVO> options) {
		this.options = options;
	}
	
}