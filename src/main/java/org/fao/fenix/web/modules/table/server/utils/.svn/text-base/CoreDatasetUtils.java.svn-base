/*
 */

package org.fao.fenix.web.modules.table.server.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.CoreContent;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.IndexCoreContent;
import org.fao.fenix.core.domain.dataset.QualitativeCoreContent;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.domain.mu.MeasurementUnit;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;

/**
 *
 * @author ETj
 */
public class CoreDatasetUtils {
	
	private static final Logger LOGGER = Logger.getLogger(CoreDatasetUtils.class);

	public static CoreContent createCoreContent(Dataset dataset, Map<String, DimensionBeanVO> values, String periodType) {
		CoreDataset ds = (CoreDataset)dataset;
		switch(ds.getCoreType()) {
			case EMPTY:
				LOGGER.warn("Dataset is empty.");
			case INDEX:
				IndexCoreContent indexCoreContent = new IndexCoreContent();
				return (CoreContent)updateIndexCoreContent(indexCoreContent, values, periodType);
			case QUANTITATIVE:
				QuantitativeCoreContent quantCoreContent = new QuantitativeCoreContent();
				return (CoreContent)updateQuantitativeCoreContent(quantCoreContent, values, periodType);
			case QUALITATIVE:
				QualitativeCoreContent qualCoreContent = new QualitativeCoreContent();
				return (CoreContent)updateQualitativeCoreContent(qualCoreContent, values, periodType);
			default:
				LOGGER.error("Unhandled type '"+dataset.getType()+" for Dataset '"+dataset.getTitle()+"' id:"+dataset.getId());
				throw new IllegalStateException("Unhandled type '"+dataset.getType()+" for Dataset '"+dataset.getTitle()+"' id:"+dataset.getId());
		}
	}

	public static CoreContent overwriteCoreContent(Dataset dataset, CoreContent coreContent, Map<String, DimensionBeanVO> values, String periodType) {
		CoreDataset ds = (CoreDataset)dataset;
		switch(ds.getCoreType()) {
			case EMPTY:
				LOGGER.warn("Dataset is empty.");
			case INDEX:
				return (CoreContent)updateIndexCoreContent((IndexCoreContent)coreContent, values, periodType);
			case QUANTITATIVE:
				return (CoreContent)updateQuantitativeCoreContent((QuantitativeCoreContent)coreContent, values, periodType);
			case QUALITATIVE:
				return (CoreContent)updateQualitativeCoreContent((QualitativeCoreContent)coreContent, values, periodType);
			default:
				LOGGER.error("Unhandled type '"+dataset.getType()+" for Dataset '"+dataset.getTitle()+"' id:"+dataset.getId());
				throw new IllegalStateException("Unhandled type '"+dataset.getType()+" for Dataset '"+dataset.getTitle()+"' id:"+dataset.getId());
		}
	}

	
	private static IndexCoreContent updateIndexCoreContent(IndexCoreContent indexCoreContent, Map<String, DimensionBeanVO> values, String periodType) {

		Method[] declaredMethods = indexCoreContent.getClass().getMethods();

		for (Map.Entry<String, DimensionBeanVO> entry : values.entrySet()) {
			DimensionBeanVO dimensionVO = entry.getValue();
			
			DataType field = DataType.valueOf(dimensionVO.getColumnDataType());
			String value = dimensionVO.getValue();

			for (Method method : declaredMethods) {
				String methodName = method.getName();

				if (methodName.equalsIgnoreCase("set" + field)) {
					LOGGER.debug("--- methodName = "+ methodName + " codeValue = " + value);

					try {
						Object oAttrValue = istantiateParameter(method, value, periodType);
						Object arguments[] = { oAttrValue };

						LOGGER.debug("---  arguments = "+ arguments);
						method.invoke(indexCoreContent, arguments);
					} catch (IllegalArgumentException e) {
						throw new FenixException(e);
					} catch (IllegalAccessException e) {
						throw new FenixException(e);
					} catch (InvocationTargetException e) {
						throw new FenixException(e);
					}
				}
			}
		}

		LOGGER.debug("--- DATASETDAO createIndexCoreContentCoreContent coreContent = "+ indexCoreContent);

		return indexCoreContent;
	}

	
	private static QuantitativeCoreContent updateQuantitativeCoreContent(QuantitativeCoreContent quantitativeCoreContent, Map<String, DimensionBeanVO> values, String periodType) {

		Method[] declaredMethods = quantitativeCoreContent.getClass().getMethods();

		for (Map.Entry<String, DimensionBeanVO> entry : values.entrySet()) {
			DimensionBeanVO dimensionVO = entry.getValue();
			
			DataType field = DataType.valueOf(dimensionVO.getColumnDataType());
			String value = dimensionVO.getValue();

			for (Method method : declaredMethods) {
				String methodName = method.getName();

				if (methodName.equalsIgnoreCase("set" + field)) {
					LOGGER.debug("--- methodName = "+ methodName + " codeValue = " + value);

					try {
						Object oAttrValue = istantiateParameter(method, value, periodType);
						Object arguments[] = { oAttrValue };

						LOGGER.debug("--- arguments = "+ arguments);
						method.invoke(quantitativeCoreContent, arguments);
					} catch (IllegalArgumentException e) {
						throw new FenixException(e);
					} catch (IllegalAccessException e) {
						throw new FenixException(e);
					} catch (InvocationTargetException e) {
						throw new FenixException(e);
					}
				}
			}
		}

		LOGGER.debug("---  coreContent = "+ quantitativeCoreContent);

		return quantitativeCoreContent;
	}

	private static QualitativeCoreContent updateQualitativeCoreContent(QualitativeCoreContent qualitativeCoreContent, Map<String, DimensionBeanVO> values, String periodType) {

		Method[] declaredMethods = qualitativeCoreContent.getClass().getMethods();

		for (Map.Entry<String, DimensionBeanVO> entry : values.entrySet()) {
			DimensionBeanVO dimensionVO = entry.getValue();
			
			DataType field = DataType.valueOf(dimensionVO.getColumnDataType());
			String value = dimensionVO.getValue();

			for (Method method : declaredMethods) {
				String methodName = method.getName();

				if (methodName.equalsIgnoreCase("set" + field)) {
					LOGGER.debug("--- methodName = "+ methodName + " codeValue = " + value);

					try {
						Object oAttrValue = istantiateParameter(method, value, periodType);
						Object arguments[] = { oAttrValue };

						LOGGER.debug("---  arguments = "+ arguments);
						method.invoke(qualitativeCoreContent, arguments);
					} catch (IllegalArgumentException e) {
						throw new FenixException(e);
					} catch (IllegalAccessException e) {
						throw new FenixException(e);
					} catch (InvocationTargetException e) {
						throw new FenixException(e);
					}
				}
			}
		}

		LOGGER.debug("--- DATASETDAO createqualitativeCoreContentCoreContent coreContent = "+ qualitativeCoreContent);

		return qualitativeCoreContent;
	}

	
	private static Object istantiateParameter(Method contentMethod, String parameter, String periodType) {
		Object parameterObject = null;
		
		
		Class<?>[] parameterTypes = contentMethod.getParameterTypes();
		if (parameterTypes.length != 1) {
			throw new FenixException("Bad setter: number of parameters mismatch (" + contentMethod.getName() + "/" + parameterTypes.length + ")");
		}
		Class<?> fieldType = parameterTypes[0];
			if (fieldType == Double.class) {
				if(parameter==null)
					parameterObject = null;
				else
					parameterObject = new Double(parameter);
			} else if (fieldType == Date.class) {
				parameterObject = FieldParser.parseDateTable(parameter, periodType);
			} else if (fieldType == String.class) {
				parameterObject = parameter;
			} else if (fieldType == int.class) {
				parameterObject = new Integer(parameter).intValue();
			} else if (fieldType == MeasurementUnit.class) {
				parameterObject = new MeasurementUnit(parameter);
			} else {
				throw new FenixException("Unparsable parameter (class " + fieldType.getSimpleName() + ")");
			}

		return parameterObject;
	}


}