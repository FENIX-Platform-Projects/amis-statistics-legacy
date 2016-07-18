package org.fao.fenix.web.modules.amis.client.view.inputccbs;

public class ProductionFormField {
	
	//Fieldset
	private String fildSetHeading;
	//Fieldset Layout
	private int layoutLabelWidth;
	//Horizontal Panel property
	private int horizontalPanelSpacing;
	//Label element
	private String Plabelname;
	private String Alabelname;
	private String Ylabelname;
	private String labelStyleName;
	private String PlabelWidth;
	private String AlabelWidth;
	private String YlabelWidth;
	//NumberField element
	private String PnumberFieldTitle;
	private String AnumberFieldTitle;
	private String YnumberFieldTitle;
	private String PnumberFieldId;
	private String AnumberFieldId;
	private String YnumberFieldId;
	private boolean numberFieldAllow;
	private int numberFieldWidth;
	//Combo Element
	private boolean comboAllow;
	private String PcomboListener;
	private String AcomboListener;
	private String YcomboListener;
	
	//With or Without numberField
	private boolean withnumberfield;
	
	//Calculating
	private boolean productionFlag[] = new boolean[3];
	private boolean areaHarvestedFlag[] = new boolean[3];
	private boolean yieldFlag[] = new boolean[3];
	private double production = 0;
	private double areaHarvested = 0;
	private double average = 0;
	
	public ProductionFormField(String fildSetHeading, int layoutLabelWidth, int horizontalPanelSpacing) {
		this.fildSetHeading = fildSetHeading;
		this.layoutLabelWidth = layoutLabelWidth;
		this.horizontalPanelSpacing = horizontalPanelSpacing;
	}
	
	public ProductionFormField(boolean productionFlag[], boolean areaHarvestedFlag[], boolean yieldFlag[], double production, double areaHarvested, double average) {
		this.productionFlag = productionFlag;
		this.areaHarvestedFlag = areaHarvestedFlag;
		this.yieldFlag = yieldFlag;
		this.production = production;
		this.areaHarvested = areaHarvested;
		this.average = average;
	}
	
	public void setLabelProperty(String Plabelname, String Alabelname, String Ylabelname, String labelStyleName, String PlabelWidth, String AlabelWidth, String YlabelWidth)
	{
		this.Plabelname = Plabelname;
		this.Alabelname = Alabelname;
		this.Ylabelname = Ylabelname;
		this.labelStyleName = labelStyleName;
		this.PlabelWidth = PlabelWidth;
		this.AlabelWidth = AlabelWidth;
		this.YlabelWidth = YlabelWidth;
	}

	public void setNumberFieldProperty(String PnumberFieldTitle, String AnumberFieldTitle, String YnumberFieldTitle, String PnumberFieldId, String AnumberFieldId, String YnumberFieldId, boolean numberFieldAllow, int numberFieldWidth)
	{
		this.PnumberFieldTitle = PnumberFieldTitle;
		this.AnumberFieldTitle = AnumberFieldTitle;
		this.YnumberFieldTitle = YnumberFieldTitle;
		this.PnumberFieldId = PnumberFieldId;
		this.AnumberFieldId = AnumberFieldId;
		this.YnumberFieldId = YnumberFieldId;
		this.numberFieldAllow = numberFieldAllow;
		this.numberFieldWidth = numberFieldWidth;
	}
	
	public void setComboProperty(boolean comboAllow, String PcomboListener, String AcomboListener, String YcomboListener)
	{
		this.comboAllow = comboAllow;
		this.PcomboListener = PcomboListener;
		this.AcomboListener = AcomboListener;
		this.YcomboListener = YcomboListener;
	}

	public String getFildSetHeading() {
		return fildSetHeading;
	}


	public void setFildSetHeading(String fildSetHeading) {
		this.fildSetHeading = fildSetHeading;
	}


	public int getLayoutLabelWidth() {
		return layoutLabelWidth;
	}


	public void setLayoutLabelWidth(int layoutLabelWidth) {
		this.layoutLabelWidth = layoutLabelWidth;
	}


	public int getHorizontalPanelSpacing() {
		return horizontalPanelSpacing;
	}


	public void setHorizontalPanelSpacing(int horizontalPanelSpacing) {
		this.horizontalPanelSpacing = horizontalPanelSpacing;
	}


	public String getPlabelname() {
		return Plabelname;
	}


	public void setPlabelname(String plabelname) {
		Plabelname = plabelname;
	}


	public String getAlabelname() {
		return Alabelname;
	}


	public void setAlabelname(String alabelname) {
		Alabelname = alabelname;
	}


	public String getYlabelname() {
		return Ylabelname;
	}


	public void setYlabelname(String ylabelname) {
		Ylabelname = ylabelname;
	}


	public String getLabelStyleName() {
		return labelStyleName;
	}


	public void setLabelStyleName(String labelStyleName) {
		this.labelStyleName = labelStyleName;
	}


	public String getPlabelWidth() {
		return PlabelWidth;
	}


	public void setPlabelWidth(String plabelWidth) {
		PlabelWidth = plabelWidth;
	}


	public String getAlabelWidth() {
		return AlabelWidth;
	}


	public void setAlabelWidth(String alabelWidth) {
		AlabelWidth = alabelWidth;
	}


	public String getYlabelWidth() {
		return YlabelWidth;
	}


	public void setYlabelWidth(String ylabelWidth) {
		YlabelWidth = ylabelWidth;
	}


	public String getPnumberFieldTitle() {
		return PnumberFieldTitle;
	}


	public void setPnumberFieldTitle(String pnumberFieldTitle) {
		PnumberFieldTitle = pnumberFieldTitle;
	}


	public String getAnumberFieldTitle() {
		return AnumberFieldTitle;
	}


	public void setAnumberFieldTitle(String anumberFieldTitle) {
		AnumberFieldTitle = anumberFieldTitle;
	}


	public String getYnumberFieldTitle() {
		return YnumberFieldTitle;
	}


	public void setYnumberFieldTitle(String hnumberFieldTitle) {
		YnumberFieldTitle = hnumberFieldTitle;
	}


	public String getPnumberFieldId() {
		return PnumberFieldId;
	}


	public void setPnumberFieldId(String pnumberFieldId) {
		PnumberFieldId = pnumberFieldId;
	}


	public String getAnumberFieldId() {
		return AnumberFieldId;
	}


	public void setAnumberFieldId(String anumberFieldId) {
		AnumberFieldId = anumberFieldId;
	}


	public String getYnumberFieldId() {
		return YnumberFieldId;
	}


	public void setYnumberFieldId(String hnumberFieldId) {
		YnumberFieldId = hnumberFieldId;
	}


	public boolean getNumberFieldAllow() {
		return numberFieldAllow;
	}


	public void setNumberFieldAllow(boolean numberFieldAllow) {
		this.numberFieldAllow = numberFieldAllow;
	}


	public int getNumberFieldWidth() {
		return numberFieldWidth;
	}


	public void setNumberFieldWidth(int numberFieldWidth) {
		this.numberFieldWidth = numberFieldWidth;
	}


	public boolean getComboAllow() {
		return comboAllow;
	}


	public void setComboAllow(boolean comboAllow) {
		this.comboAllow = comboAllow;
	}


	public String getPcomboListener() {
		return PcomboListener;
	}


	public void setPcomboListener(String pcomboListener) {
		PcomboListener = pcomboListener;
	}


	public String getAcomboListener() {
		return AcomboListener;
	}


	public void setAcomboListener(String acomboListener) {
		AcomboListener = acomboListener;
	}


	public String getYcomboListener() {
		return YcomboListener;
	}


	public void setYcomboListener(String ycomboListener) {
		YcomboListener = ycomboListener;
	}

	public boolean isWithnumberfield() {
		return withnumberfield;
	}

	public void setWithnumberfield(boolean withnumberfield) {
		this.withnumberfield = withnumberfield;
	}

	public boolean[] getProductionFlag() {
		return productionFlag;
	}

	public void setProductionFlag(boolean[] productionFlag) {
		this.productionFlag = productionFlag;
	}

	public boolean[] getAreaHarvestedFlag() {
		return areaHarvestedFlag;
	}

	public void setAreaHarvestedFlag(boolean[] areaHarvestedFlag) {
		this.areaHarvestedFlag = areaHarvestedFlag;
	}

	public boolean[] getYieldFlag() {
		return yieldFlag;
	}

	public void setYieldFlag(boolean[] yieldFlag) {
		this.yieldFlag = yieldFlag;
	}

	public double getProduction() {
		return production;
	}

	public void setProduction(double production) {
		this.production = production;
	}

	public double getAreaHarvested() {
		return areaHarvested;
	}

	public void setAreaHarvested(double areaHarvested) {
		this.areaHarvested = areaHarvested;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}
	
	
	
	
}
