package org.fao.fenix.web.modules.r.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RFunctionVO implements IsSerializable {

	private String command;
	
	private String name;
	
	private String description;
	
	private int inputs;
	
	private String rFormula;
	
	private boolean repeat;
	
	private int repeatRows;
	
	private List<ROptionVO> options;
	
	private List<ROutputVO> outputs;
	
	private List<RPlotVO> plots;
	
	public RFunctionVO() {
		repeat = false;
		options = new ArrayList<ROptionVO>();
		outputs = new ArrayList<ROutputVO>();
		plots = new ArrayList<RPlotVO>();
	}
	
	public void addPlot(RPlotVO o) {
		if (this.plots == null)
			this.plots = new ArrayList<RPlotVO>();
		this.plots.add(o);
	}
	
	public void addOption(ROptionVO o) {
		if (this.options == null)
			this.options = new ArrayList<ROptionVO>();
		this.options.add(o);
	}
	
	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public int getRepeatRows() {
		return repeatRows;
	}

	public void setRepeatRows(int repeatRows) {
		this.repeatRows = repeatRows;
	}

	public void addOutput(ROutputVO o) {
		if (this.outputs == null)
			this.outputs = new ArrayList<ROutputVO>();
		this.outputs.add(o);
	}

	public List<ROutputVO> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<ROutputVO> outputs) {
		this.outputs = outputs;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
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

	public int getInputs() {
		return inputs;
	}

	public void setInputs(int inputs) {
		this.inputs = inputs;
	}

	public String getrFormula() {
		return rFormula;
	}

	public void setrFormula(String rFormula) {
		this.rFormula = rFormula;
	}

	public List<ROptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<ROptionVO> options) {
		this.options = options;
	}

	public List<RPlotVO> getPlots() {
		return plots;
	}

	public void setPlots(List<RPlotVO> plots) {
		this.plots = plots;
	}
	
}