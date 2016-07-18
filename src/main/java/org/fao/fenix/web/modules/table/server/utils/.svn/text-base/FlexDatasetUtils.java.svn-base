/*
 */

package org.fao.fenix.web.modules.table.server.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.FlexCell;
import org.fao.fenix.core.domain.dataset.Range;
import org.fao.fenix.core.domain.dataset.SingleDate;
import org.fao.fenix.core.domain.dataset.SingleNumeric;
import org.fao.fenix.core.domain.dataset.SingleText;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;


public class FlexDatasetUtils {
	
	private static final Logger LOGGER = Logger.getLogger(FlexDatasetUtils.class);

	 public static List<FlexCell> updateFlexCells( Map<String, DimensionBeanVO> values, List<? extends FlexCell> cellsOriginal) throws FenixException {
	    	
	    	List<FlexCell> cells = new ArrayList<FlexCell>();

	    	LOGGER.info(" cellsOriginal size = "+cellsOriginal.size() + " values map size = " + values.size());
			
	    	try {
	    		for(FlexCell flexCell: cellsOriginal){
	    			Descriptor descriptorOriginal = flexCell.getDescriptor();

	    			LOGGER.info(" descriptorOriginal header = "+descriptorOriginal.getHeader() +" originalDatatype = "+ descriptorOriginal.getDataType() + " descriptorId "+ descriptorOriginal.getId());

	    			DimensionBeanVO dimensionVO = values.get(String.valueOf(descriptorOriginal.getId()));

	    			if(dimensionVO!=null) {
	    				DataType dataType = DataType.valueOf(dimensionVO.getColumnDataType());
	    				LOGGER.info(" MATCH header = "+dimensionVO.getHeader() +" datatype = "+ dimensionVO.getColumnDataType()+" value =  "+dimensionVO.getValue() + " descriptorId = " +descriptorOriginal.getId());

	    				String token = dimensionVO.getValue();	

	    				if (dataType == null) {
	    					throw new FenixException("Unknown data type " + dataType);
	    				} else {
	    					switch (dataType) {
	    					case text:
	    						((SingleText)flexCell).setText(token);
	    						break;
	    					case quantity:
	    						((SingleNumeric)flexCell).setValue(Double.parseDouble(token));
	    						break;
	    					case date:
	    						((SingleDate)flexCell).setDate(FieldParser.parseDate(token));
	    						break;
	    					case range:
	    						((Range)flexCell).setRangeValue(Double.parseDouble(token));
	    						break;
	    					default:
	    						throw new FenixException("Unhandled data type " + dataType);
	    					}

	    					cells.add(flexCell);

	    				}
	    			}

	    		}
	    	}

	    	catch (NoSuchElementException e) {
	    		throw new FenixException(e.getMessage(), e);
	    	} catch (RuntimeException e) {
	    		throw new FenixException(e.getMessage(), e);
	    	}

	    	return cells;
	    }
	 
	 public static List<FlexCell> createFlexCells(Dataset dataset, Map<String, DimensionBeanVO> values) throws FenixException {
		 Iterator<Map.Entry<String, DimensionBeanVO>> valuesIterator   = values.entrySet().iterator();

		 List<FlexCell> cells = new ArrayList<FlexCell>();

		 try {

			 for (int j = 0; j < values.size(); j++) {
				 Map.Entry<String, DimensionBeanVO> columnEntry = valuesIterator.next();
				 DimensionBeanVO dimensionVO = columnEntry.getValue();

				 if(dimensionVO!=null) {

					 DataType dataType = DataType.valueOf(dimensionVO.getColumnDataType());
					 LOGGER.info("  header = "+dimensionVO.getHeader() +" datatype = "+ dimensionVO.getColumnDataType()+" value =  "+dimensionVO.getValue());

					 String token = dimensionVO.getValue();	

					 FlexCell flexCell = null;

					 if (dataType == null) {
						 throw new FenixException("Unknown data type " + dataType);
					 } else {
						 switch (dataType) {
						 case text:
							 flexCell = new SingleText(token);
							 break;
						 case quantity:
							 flexCell = new SingleNumeric(Double.parseDouble(token));
							 break;
						 case date:
							 flexCell = new SingleDate(FieldParser.parseDate(token));
							 break;
						 case range:
							 flexCell = new Range(Double.parseDouble(token));
							 break;
						 default:
							 throw new FenixException("Unhandled data type " + dataType);
						 }

						 flexCell.setDescriptor(GwtConnector.getDescriptorFromColumnName(dataset, dimensionVO.getHeader()));
						 cells.add(flexCell);

					 }
				 }

			 }
		 }
		 catch (NoSuchElementException e) {
			 throw new FenixException(e.getMessage(), e);
		 } catch (RuntimeException e) {
			 throw new FenixException(e.getMessage(), e);
		 }
		 return cells;
	 }

}