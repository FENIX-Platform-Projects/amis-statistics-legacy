package org.fao.fenix.web.modules.amis.common.vo;

import java.util.List;

import org.fao.fenix.web.modules.amis.common.constants.AMISAggregationConstants;
import  org.fao.fenix.web.modules.amis.common.constants.AMISTableConstants;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AMISCalculationParametersVO implements IsSerializable {

	private AMISAggregationConstants calculationType;
	private AMISTableConstants filterColumn;
	private List<String> subtractionMinuend;
	private List<String> subtractionSubtrahend;
	private List<String> dividend;
	private List<String> divisor;
	private String resultLabel;

	// this is used for the growth_rate TODO:
	//	<DATEQUERY>
	//		<MAX>D.Year</MAX>
	//		<TIMESPAN type="year">10</TIMESPAN>
	//	</DATEQUERY>
	private List<String> timeperiod_present;
	private List<String> timeperiod_past;



	public AMISAggregationConstants getCalculationType() {
		return calculationType;
	}
	public void setCalculationType(AMISAggregationConstants calculationType) {
		this.calculationType = calculationType;
	}
	public AMISTableConstants getFilterColumn() {
		return filterColumn;
	}
	public void setFilterColumn(AMISTableConstants filterColumn) {
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
	public void setDividend(List<String> dividend) {
		this.dividend = dividend;
	}
	public List<String> getDividend() {
		return dividend;
	}
	public void setDivisor(List<String> divisor) {
		this.divisor = divisor;
	}
	public List<String> getDivisor() {
		return divisor;
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
