package org.fao.fenix.web.modules.birt.server.utils;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.label.GaulLabel;
import org.fao.fenix.core.domain.label.ReferenceLabel;
import org.fao.fenix.core.persistence.CodecDao;

public class MatchCodeLabel {

	@Deprecated
	public static String getLabel(String datasetType, String dimension, String code) {
		if (dimension.equals("featureCode") || dimension.equals("partnerCode")) {
			String val = GaulLabel.getLable(code);
			return getValue(val, code);
		}  else if (dimension.equals("localImpExpCommCode") || dimension.equals("indexCode") || dimension.equals("cropAreaTypeCode") || dimension.equals("agriculturalPracticeCode") || dimension.equals("yieldTypeCode") || dimension.equals("populationTypeCode") || dimension.equals("genderCode") || dimension.equals("ageRangeCode") || dimension.equals("residenceCode")){
				List<String> element = new ArrayList<String>();
				element.add(code);
				element.add(dimension);
				String val = ReferenceLabel.getLabel(element);
				return getValue(val, code);
		}/* else if (dimension.equals("indicatorCode")) {
			if (datasetType.equals("WdiDataset")){
				String val = WDILabel.getLabel(code);
				return getValue(val, code);
			} else if (datasetType.equals("NhsDataset")){
				String val = NHSLabel.getLabel(code);
				return getValue(val, code);
			} else if (datasetType.equals("IfsEuiDataset")){
				String val = IFS_EIULabel.getLabel(code);
				return getValue(val, code);
			} else if (datasetType.equals("RigaDataset")){
				String val = RIGALabel.getLabel(code);
				return getValue(val, code);
			} 
		}*/ 
		
		return code;
		
	} 
	
	public static String getLabel(GwtConnector gwtConnector, CodecDao codecDao, long datasetId, String code, String columnName, String language){
		String contentDescriptor = gwtConnector.findContentDescriptorFromColumnName(datasetId, columnName).name(); 
		System.out.println("########## " + contentDescriptor);
		if (contentDescriptor.equals(DataType.date.name()))
			return gwtConnector.formatDate(datasetId, code);
		else {
			System.out.println("########## columnName: " + columnName); 
			List<Option> optionList = gwtConnector.getOptionsFromColumnName(datasetId, columnName);
			System.out.println("########## " + optionList.size() + " options");
			for (Option op : optionList){
				System.out.println(code + ", " + op.getOptionValue() + ", " + op.getOptionRegion());
				if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null){
					String label = codecDao.getLabelFromCodeCodingSystem(code, op.getOptionValue(), op.getOptionRegion(), language);
					System.out.println("########## " + code + ": " + label);
					return label;
				}
			}
		}
		return code;
	}
	
	@Deprecated
	private static String getValue(String val, String code){
		if (val == null)
			return code;
		else 
			return val;
	}
}
