package org.fao.fenix.web.modules.r.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ROutputVO implements IsSerializable {

	private String outputType;
	
	private String outputName;
	
	private int outputIndex;
	
	private boolean isSquareMatrix;
	
	private int repeatRows;
	
	public ROutputVO() {
		outputType = "";
		outputName = "";
		outputIndex = 0;
		isSquareMatrix = false;
		repeatRows = 0;
	}

	public int getRepeatRows() {
		return repeatRows;
	}

	public void setRepeatRows(int repeatRows) {
		this.repeatRows = repeatRows;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	public int getOutputIndex() {
		return outputIndex;
	}

	public void setOutputIndex(int outputIndex) {
		this.outputIndex = outputIndex;
	}

	public boolean isSquareMatrix() {
		return isSquareMatrix;
	}

	public void setSquareMatrix(boolean isSquareMatrix) {
		this.isSquareMatrix = isSquareMatrix;
	}
	
}