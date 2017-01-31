package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import com.extjs.gxt.ui.client.widget.Html;

public class OtherUsesFormField {

	private Html htmlField;
	//Number of element in the North of the "Other Uses" form
	private int northDim = 2;
	//Indices of the element in the "Other Uses" form
	//North
	private int otherUsesTextField = 0;
	private int otherUsesCombo = 1;	
	//South
	private int otherUsesFlagCombo = 2;
	private int seedsTextField = 3;
	private int seedsCombo = 4;
	private int postHarvestedTextField = 5;
	private int postHarvestedCombo = 6;
	private int industrialUseTextField = 7;
	private int industrialUseCombo = 8;
	
	//Get value from other uses form
	double otherUsesTot = 0;
	boolean isValidArray[] = new boolean[7]; 
	String otherUsesFlag = "";
	
	//Other Uses By field Fieldset Values
	private double seeds;
	private double postHarvestLosses;
	private double industrialUse;
	
	//Other Uses Total Value
	private double totalValue;
	private String flagValue;
	private boolean totalValueNull;

	public OtherUsesFormField() {
		this.htmlField = new Html();
	}
	
	public OtherUsesFormField(double otherUsesTot, boolean isValidArray[], String otherUsesFlag) {
		this.otherUsesTot = otherUsesTot;
		this.isValidArray = isValidArray;
		this.otherUsesFlag = otherUsesFlag;
		
		seeds = 0;
		postHarvestLosses = 0;
		industrialUse = 0;
	}
	
	void setHtmlProperty()
	{
		//String htmlText = "<div class = 'ccbs-Label'>Please fill all the fields of either \"Other Uses Total\" or \"Other Uses by Field\".</div>";
		String htmlText = "<div class = 'ccbs-Label'>Please fill all the fields appropriate to you.</div>";
		this.htmlField.setHtml(htmlText);
	}
	
	public Html getHtmlField() {
		return htmlField;
	}

	public void setHtmlField(Html htmlField) {
		this.htmlField = htmlField;
	}

	public int getNorthDim() {
		return northDim;
	}

	public void setNorthDim(int northDim) {
		this.northDim = northDim;
	}

	public int getOtherUsesTextField() {
		return otherUsesTextField;
	}

	public void setOtherUsesTextField(int otherUsesTextField) {
		this.otherUsesTextField = otherUsesTextField;
	}

	public int getOtherUsesCombo() {
		return otherUsesCombo;
	}

	public void setOtherUsesCombo(int otherUsesCombo) {
		this.otherUsesCombo = otherUsesCombo;
	}

	public int getOtherUsesFlagCombo() {
		return otherUsesFlagCombo;
	}

	public void setOtherUsesFlagCombo(int otherUsesFlagCombo) {
		this.otherUsesFlagCombo = otherUsesFlagCombo;
	}

	public int getSeedsTextField() {
		return seedsTextField;
	}

	public void setSeedsTextField(int seedsTextField) {
		this.seedsTextField = seedsTextField;
	}

	public int getSeedsCombo() {
		return seedsCombo;
	}

	public void setSeedsCombo(int seedsCombo) {
		this.seedsCombo = seedsCombo;
	}

	public int getPostHarvestedTextField() {
		return postHarvestedTextField;
	}

	public void setPostHarvestedTextField(int postHarvestedTextField) {
		this.postHarvestedTextField = postHarvestedTextField;
	}

	public int getPostHarvestedCombo() {
		return postHarvestedCombo;
	}

	public void setPostHarvestedCombo(int postHarvestedCombo) {
		this.postHarvestedCombo = postHarvestedCombo;
	}

	public int getIndustrialUseTextField() {
		return industrialUseTextField;
	}

	public void setIndustrialUseTextField(int industrialUseTextField) {
		this.industrialUseTextField = industrialUseTextField;
	}

	public int getIndustrialUseCombo() {
		return industrialUseCombo;
	}

	public void setIndustrialUseCombo(int industrialUseCombo) {
		this.industrialUseCombo = industrialUseCombo;
	}

	public double getOtherUsesTot() {
		return otherUsesTot;
	}

	public void setOtherUsesTot(double otherUsesTot) {
		this.otherUsesTot = otherUsesTot;
	}

	public boolean[] getIsValidArray() {
		return isValidArray;
	}

	public void setIsValidArray(boolean[] isValidArray) {
		this.isValidArray = isValidArray;
	}

	public String getOtherUsesFlag() {
		return otherUsesFlag;
	}

	public void setOtherUsesFlag(String otherUsesFlag) {
		this.otherUsesFlag = otherUsesFlag;
	}
	
	public double getSeeds() {
		return seeds;
	}

	public void setSeeds(double seeds) {
		this.seeds = seeds;
	}

	public double getPostHarvestLosses() {
		return postHarvestLosses;
	}

	public void setPostHarvestLosses(double postHarvestLosses) {
		this.postHarvestLosses = postHarvestLosses;
	}

	public double getIndustrialUse() {
		return industrialUse;
	}

	public void setIndustrialUse(double industrialUse) {
		this.industrialUse = industrialUse;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public String getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(String flagValue) {
		this.flagValue = flagValue;
	}

	public boolean isTotalValueNull() {
		return totalValueNull;
	}

	public void setTotalValueNull(boolean totalValueNull) {
		this.totalValueNull = totalValueNull;
	}
	
}

