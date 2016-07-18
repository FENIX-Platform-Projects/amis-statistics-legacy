package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;

import java.util.List;

import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATAggregationConstant;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATTableConstant;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CalculationParametersVO implements IsSerializable {

	private FAOSTATAggregationConstant calculationType;
	private FAOSTATTableConstant filterColumn;
	private List<String> subtractionMinuend;
	private List<String> subtractionSubtrahend;
	private String resultLabel;
	
	// this is used for the growth_rate TODO: 				
	//	<DATEQUERY>
	//		<MAX>D.Year</MAX>
	//		<TIMESPAN type="year">10</TIMESPAN>
	//	</DATEQUERY>
	private List<String> timeperiod_present;
	private List<String> timeperiod_past;

	
	
	public FAOSTATAggregationConstant getCalculationType() {
		return calculationType;
	}
	public void setCalculationType(FAOSTATAggregationConstant calculationType) {
		this.calculationType = calculationType;
	}
	public FAOSTATTableConstant getFilterColumn() {
		return filterColumn;
	}
	public void setFilterColumn(FAOSTATTableConstant filterColumn) {
		this.filterColumn = filterColumn;
	}
	public List<String> getSubtractionMinuend() {
		return subtractionMinuend;
	}
	public void setSubtractionMinuend(List<String> subtractionMinuend) {
		this.subtractionMinuend = subtractionMinuend;
	}
	public List<String> getSubtractionSubtrahend() {
		return subtractionSubtrahend;
	}
	public void setSubtractionSubtrahend(List<String> subtractionSubtrahend) {
		this.subtractionSubtrahend = subtractionSubtrahend;
	}
	public void setResultLabel(String resultLabel) {
		this.resultLabel = resultLabel;		
	}
	
	public String getResultLabel() {
		return resultLabel;
	}
	public List<String> getTimeperiod_present() {
		return timeperiod_present;
	}
	public void setTimeperiod_present(List<String> timeperiodPresent) {
		timeperiod_present = timeperiodPresent;
	}
	public List<String> getTimeperiod_past() {
		return timeperiod_past;
	}
	public void setTimeperiod_past(List<String> timeperiodPast) {
		timeperiod_past = timeperiodPast;
	}
	
	

}
