package org.fao.fenix.web.modules.adam.common.enums;



public enum ADAMSelectedDataset {
	
	//ADAM_CRS = Dataset code for OECD (DAC) DATA
	//AID_DATA = Dataset code for AID (NON_DAC) DATA
	//ADAM_CRS_AID_DATA = To view both DAC and Non Dac together
	//FAO_SUB_SECTORS_DATA = View for FAO related Sub sectors
	
	ADAM_CRS, AID_DATA, ADAM_CRS_AID_DATA, ADAM_FPMIS, FAO_SUB_SECTORS_DATA;

	public static ADAMSelectedDataset getSelectedDataset(String code) {

		if (!isValid(code))
			return null;

		return valueOf(code);
	}
	
	
	public static ADAMSelectedDataset valueOfIgnoreCase(String name) {
		for (ADAMSelectedDataset code : ADAMSelectedDataset.values())
			if (code.toString().equalsIgnoreCase(name))
				return code;
		return null;
	}
	
	
	public static boolean isValid(String name) {
		boolean isValid = false;
		for (ADAMSelectedDataset rt : ADAMSelectedDataset.values()) {
			if (name.equals(rt.name()))
				isValid = true;
		}
		return isValid;
	}
	
}
