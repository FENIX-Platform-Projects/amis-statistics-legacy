package org.fao.fenix.web.modules.excelimporter.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.excelimporter.DataTypeFixer;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.FindDao;
import org.fao.fenix.core.persistence.file.CsvFactory;
import org.fao.fenix.core.persistence.file.MetadataFactory;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.excelimporter.common.services.ExcelImporterService;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ExcelImporterServiceImpl extends RemoteServiceServlet implements ExcelImporterService {

	private CodecDao codecDao;
	
	private FindDao findDao;
	
	private CsvFactory csvFactory;
	
	private MetadataFactory metadataFactory;
	
	private String path;
	
	public ExcelImporterServiceImpl(org.springframework.core.io.Resource resource) throws FenixException {
		try {
			this.setPath(resource.getFile().getPath());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	public String exportCSV(Long datasetID) throws FenixGWTException {
		try {
			File tmp = csvFactory.createDatasetCsvFile(datasetID);
			String filename = Math.random() + "_DATASET.csv";
			File gwt = new File(path + File.separator + filename);
			FileUtils.moveFile(tmp, gwt);
			return filename;
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String exportXML(Long datasetID) throws FenixGWTException {
		try {
			File tmp = metadataFactory.createDatasetMetadataFile(datasetID);
			String filename = Math.random() + "_METADATA.xml";
			File gwt = new File(path + File.separator + filename);
			FileUtils.moveFile(tmp, gwt);
			return filename;
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String exportZIP(Long datasetID) throws FenixGWTException {
		try {
			String filename = Math.random() + "_ARCHIVE.zip";
			File zipFile = new File(path + File.separator + filename);
			File csvFile = csvFactory.createDatasetCsvFile(datasetID);
			File xmlFile = metadataFactory.createDatasetMetadataFile(datasetID);
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
			byte[] buf = new byte[1024];
			FileInputStream in = new FileInputStream(csvFile);
			out.putNextEntry(new ZipEntry(csvFile.getName()));
			int len;
			while ((len = in.read(buf)) > 0)
				out.write(buf, 0, len);
			in = new FileInputStream(xmlFile);
			out.putNextEntry(new ZipEntry(xmlFile.getName()));
			while ((len = in.read(buf)) > 0)
				out.write(buf, 0, len);
			out.closeEntry();
			in.close();
			out.close();
			return filename;
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<ResourceChildModel> findSimilarDatasets(String sourceName, String sourceRegion, String periodTypeCode, List<DescriptorVO> descriptorVOs) {
		
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		
		List<Descriptor> descriptors = vos2descriptors(descriptorVOs);
		DataTypeFixer fixer = new DataTypeFixer();
		descriptors = fixer.fixDataTypes(descriptors);
		
		List<Long> datasetTypes = findSimilarDatasetTypes(descriptors);
		for (Long dtid : datasetTypes) {
			List<Dataset> datasets = findDao.findDatasets(dtid, sourceName, sourceRegion, periodTypeCode);	
			for (Dataset d : datasets)
				models.add(dataset2resourceChildModel(d));
		}	
		
		return models;
	}
	
	private List<Long> findSimilarDatasetTypes(List<Descriptor> descriptors) {
		List<List<Long>> datasetTypes = new ArrayList<List<Long>>();
		int min = Integer.MAX_VALUE;
		List<Long> result = new ArrayList<Long>();
		for (Descriptor des : descriptors) {
			if (!des.getOptions().isEmpty()) {
				for (Option o : des.getOptions()) {
					List<Long> types = findDao.findDatasetTypes(des.getHeader(), des.getContentDescriptor(), des.isKey(), o.getOptionName(), o.getOptionValue());
					datasetTypes.add(types);
				}
			} else {
				List<Long> types = findDao.findDatasetTypes(des.getHeader(), des.getContentDescriptor(), des.isKey());
				datasetTypes.add(types);
			}
		}
		for (List<Long> list : datasetTypes) {
			if ((list.size() > 0) && (list.size() < min)) {
				min = list.size();
				result = list;
			}
		}
		return result;
	}
	
	public List<CodingNameModelData> findAllCodingSystems(String codingType) {
		List<CodingNameModelData> l = new ArrayList<CodingNameModelData>();
		List<CodingSystem> codingSystems = codecDao.findAllCodingSystems(codingType);
		for (CodingSystem cs : codingSystems)
			l.add(new CodingNameModelData(cs.getTitle()));
		return l;
	}
	
	private ResourceChildModel dataset2resourceChildModel(Dataset d) {
		ResourceChildModel m = new ResourceChildModel();
		m.setName(d.getTitle());
		m.setSource(d.getSource().getTitle());
		m.setPeriodTypeCode(d.getPeriodTypeCode());
		m.setDateModified(FieldParser.parseDate(d.getDateLastUpdate()));
		String category = codecDao.getLabelFromCodeCodingSystem(d.getCategories(), "Categories", "0", "EN");
		m.setCategoryLabel(category);
		m.setId(String.valueOf(d.getResourceId()));
		return m;
	}
	
	private List<Descriptor> vos2descriptors(List<DescriptorVO> vos) {
		List<Descriptor> ds = new ArrayList<Descriptor>();
		for (DescriptorVO vo : vos)
			ds.add(vo2descriptor(vo));
		return ds;
	}
	
	private Descriptor vo2descriptor(DescriptorVO vo) {
		Descriptor d = new Descriptor();
		d.setHeader(vo.getHeader());
		d.setContentDescriptor(vo.getContentDescriptor());
		for (OptionVO ovo : vo.getOptions())
			d.addOption(vo2option(ovo));
		return d;
	}
	
	private Option vo2option(OptionVO vo) {
		Option o = new Option();
		o.setOptionName(vo.getOptionName());
		o.setOptionValue(vo.getOptionValue());
		return o;
	}
	
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}

	public void setCsvFactory(CsvFactory csvFactory) {
		this.csvFactory = csvFactory;
	}

	public void setMetadataFactory(MetadataFactory metadataFactory) {
		this.metadataFactory = metadataFactory;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
}