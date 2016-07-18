package org.fao.fenix.web.modules.excelimporter.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.DatasetType;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.FlexDataset;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.excelimporter.DataTypeFixer;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.file.MetadataFactory;
import org.fao.fenix.core.utils.DatasetBuilder;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.core.x.RSSFactory;
import org.fao.fenix.core.x.XResourceType;
import org.fao.fenix.web.modules.core.server.upload.MonitoredDiskFileItemFactory;
import org.fao.fenix.web.modules.core.server.upload.UploadListener;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.x.common.vo.RSSConfigurationVO;
import org.fao.fenix.web.modules.x.server.utils.RSSWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class GhostFormServlet extends HttpServlet implements Servlet  {

	private final static Logger LOGGER = Logger.getLogger(GhostFormServlet.class);
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletContext servletContext = this.getServletConfig().getServletContext();
		
		try {
			
			List<FileItem> fileItemList = (List<FileItem>)upload.parseRequest(request);
			for (FileItem i : fileItemList)
				LOGGER.info("+-- " + i.getFieldName());
			
			// existing dataset ID
			FileItem datasetIDItem = findFileItemByCode("DATASET_ID", fileItemList);
			
			// to append or not to append
			if (datasetIDItem == null) {
				LOGGER.info("Upload brand new dataset...");
				uploadBrandNewDataset(request, response, servletContext, fileItemList);
			} else {
				String datasetIDValue = extractValue(datasetIDItem);
				LOGGER.info("EXISTING DATASET ID: " + datasetIDValue);
				Long existingDatasetID = Long.valueOf(datasetIDValue);
				LOGGER.info("Append to dataset with ID " + existingDatasetID);
				appendToExistingDataset(request, response, servletContext, fileItemList, existingDatasetID);
			}
			
		} catch (FileUploadException e) {
			LOGGER.error(e.getMessage());
			response.getOutputStream().print(e.getMessage());
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.getOutputStream().print(e.getMessage());
			throw new FenixException(e.getMessage());
		}
		
	}
	
	private void appendToExistingDataset(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, List<FileItem> fileItemList, Long existingDatasetID) {
		
		LOGGER.info("DATASET UPLOAD: START...");
		
		// spring beans
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DatasetImporter datasetImporter = (DatasetImporter) wac.getBean("datasetImporter");
		
		try {
		
			// metadata file
			MetadataFactory metadataFactory = (MetadataFactory) wac.getBean("metadataFactory");
			File metadata = metadataFactory.createDatasetMetadataFile(existingDatasetID);
			FileInputStream metadataFile = new FileInputStream(metadata);
			
			// retrieve dataset
			FileItem datasetItem = findFileItemByCode("DATASET", fileItemList);
			
			// upload policy
			FileItem policyItem = findFileItemByCode("POLICY", fileItemList);
			String policyValue = extractValue(policyItem);
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyValue);
		
			// upload the dataset
			Long datasetID = null;
			if (isCSV(datasetItem)) {
				FileItem delimiterItem = findFileItemByCode("DELIMITER", fileItemList);
				String delimiterValue = extractValue(delimiterItem);
				datasetID = datasetImporter.importDataset(datasetItem.getInputStream(), metadataFile, policy, delimiterValue);
			} else {
				FileItem templateItem = findFileItemByCode("TEMPLATE", fileItemList);
				String templateValue = extractValue(templateItem);
				if (templateValue.equalsIgnoreCase("GIEWS Workstation")) {
					datasetID = datasetImporter.importExcelDataset(datasetItem.getInputStream(), metadataFile, policy, templateValue, null, null, null);
				} else if (templateValue.equalsIgnoreCase("World Bank")) {
					FileItem quantityHeaderItem = findFileItemByCode("QUANTITY_HEADER", fileItemList);
					String quantityHeader = extractValue(quantityHeaderItem);
					FileItem muHeaderItem = findFileItemByCode("MU_HEADER", fileItemList);
					String muHeader = extractValue(muHeaderItem);
					FileItem muValueItem = findFileItemByCode("MU_VALUE", fileItemList);
					String muValue = extractValue(muValueItem);
					datasetID = datasetImporter.importExcelDataset(datasetItem.getInputStream(), metadataFile, policy, templateValue, muHeader, muValue, quantityHeader);
				}
			}
			
			// update the rss
			updateRSS(wac);
			
			// done!
			response.getOutputStream().print("datasetID:" + datasetID);
			LOGGER.info("DATASET IMPORTED WITH ID: " + datasetID);
			
		} catch (FenixException e) {
			try {
				response.getOutputStream().print(e.getMessage());
			} catch (IOException e1) {
				LOGGER.error(e1.getMessage());
			}
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			try {
				response.getOutputStream().print(e.getMessage());
			} catch (IOException e1) {
				LOGGER.error(e1.getMessage());
			}
			throw new FenixException(e.getMessage());
		}
		
		LOGGER.info("DATASET UPLOAD: DONE!");
	}
	
	private void uploadBrandNewDataset(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, List<FileItem> fileItemList) {
		
		LOGGER.info("DATASET UPLOAD: START...");
		
		// spring beans
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DatasetImporter datasetImporter = (DatasetImporter) wac.getBean("datasetImporter");
		
		// source and descriptors
		Source source = createSource(fileItemList);
		DatasetType datasetType = createDatasetType();
		List<Descriptor> descriptors = createDescriptors(fileItemList);
		DataTypeFixer fixer = new DataTypeFixer();
		descriptors = fixer.fixDataTypes(descriptors);
		for (Descriptor d : descriptors)
			datasetType.addDescriptor(d);
		
		// is it core?
		boolean isCore = DatasetBuilder.isCoreDataset(descriptors);
		Dataset dataset = null;
		if (isCore) {
			dataset = createCoreDataset(fileItemList);
		} else {
			dataset = createFlexDataset(fileItemList);
		}
		
		// set type, source and policy
		dataset.setDatasetType(datasetType);
		dataset.setSource(source);
		dataset.setSharingCode(SharingCode.Public.name());
		
		try {
			
			// create the metadata
			MetadataFactory metadataFactory = (MetadataFactory) wac.getBean("metadataFactory");
			File metadata = metadataFactory.createDatasetMetadataFile(dataset);
			FileInputStream metadataFile = new FileInputStream(metadata);
			
			// retrieve dataset
			FileItem datasetItem = findFileItemByCode("DATASET", fileItemList);
			
			// upload policy
			FileItem policyItem = findFileItemByCode("POLICY", fileItemList);
			String policyValue = extractValue(policyItem);
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyValue);
			
			// upload the dataset
			Long datasetID = null;
			if (isCSV(datasetItem)) {
				FileItem delimiterItem = findFileItemByCode("DELIMITER", fileItemList);
				String delimiterValue = extractValue(delimiterItem);
				datasetID = datasetImporter.importDataset(datasetItem.getInputStream(), metadataFile, policy, delimiterValue);
			} else {
				FileItem templateItem = findFileItemByCode("TEMPLATE", fileItemList);
				String templateValue = extractValue(templateItem);
				if (templateValue.equalsIgnoreCase("GIEWS Workstation")) {
					datasetID = datasetImporter.importExcelDataset(datasetItem.getInputStream(), metadataFile, policy, templateValue, null, null, null);
				} else if (templateValue.equalsIgnoreCase("World Bank")) {
					FileItem quantityHeaderItem = findFileItemByCode("QUANTITY_HEADER", fileItemList);
					String quantityHeader = extractValue(quantityHeaderItem);
					FileItem muHeaderItem = findFileItemByCode("MU_HEADER", fileItemList);
					String muHeader = extractValue(muHeaderItem);
					FileItem muValueItem = findFileItemByCode("MU_VALUE", fileItemList);
					String muValue = extractValue(muValueItem);
					datasetID = datasetImporter.importExcelDataset(datasetItem.getInputStream(), metadataFile, policy, templateValue, muHeader, muValue, quantityHeader);
				}
			}
			
			// update the rss
			updateRSS(wac);
			
			// done!
			response.getOutputStream().print("datasetID:" + datasetID);
			LOGGER.info("DATASET IMPORTED WITH ID: " + datasetID);
			
		} catch (FileNotFoundException e) {
			try {
				response.getOutputStream().print(e.getMessage());
			} catch (IOException e1) {
				LOGGER.error(e1.getMessage());
			}
			throw new FenixException(e.getMessage());
		} catch (FenixException e) {
			try {
				response.getOutputStream().print(e.getMessage());
			} catch (IOException e1) {
				LOGGER.error(e1.getMessage());
			}
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			try {
				response.getOutputStream().print(e.getMessage());
			} catch (IOException e1) {
				LOGGER.error(e1.getMessage());
			}
			throw new FenixException(e.getMessage());
		}
		
		LOGGER.info("DATASET UPLOAD: DONE!");
	}
	
	private boolean isCSV(FileItem datasetItem) {
		if (datasetItem.getName().endsWith("xls"))
			return false;
		return true;
	}
	
	private void updateRSS(WebApplicationContext wac) {
		RSSFactory rssFactory = (RSSFactory) wac.getBean("rssFactory");
		RSSWriter rssWriter = (RSSWriter) wac.getBean("rssWriter");
		UrlFinder urlFinder = (UrlFinder) wac.getBean("urlFinder");
		RSSConfigurationVO vo = createRSSConfiguration(wac, "00D", "02", "03", "04");
		String rss = rssFactory.createLatestDatasetRSS(vo.getChannelTitle(), vo.getChannelDescription(), vo.getChannelLink(), vo.getImageLink(), urlFinder.fenixWebUrl, urlFinder.xServicesUrl, false);
		rssWriter.writeDatasetRSS(rss, XResourceType.dataset);
	}
	
	private RSSConfigurationVO createRSSConfiguration(WebApplicationContext wac, String channelTitleCode, String channelDescriptionCode, String channelLinkCode, String imageLinkCode) {
		CodecDao codecDao = (CodecDao)wac.getBean("codecDao");
		RSSConfigurationVO vo = new RSSConfigurationVO();
		String channelTitle = codecDao.getCodeFromCodeSystemRegion(channelTitleCode, "RSSConfiguration", "0", "EN").getLabel();
		String channelDescription = codecDao.getCodeFromCodeSystemRegion(channelDescriptionCode, "RSSConfiguration", "0", "EN").getLabel();
		String channelLink = codecDao.getCodeFromCodeSystemRegion(channelLinkCode, "RSSConfiguration", "0", "EN").getLabel();
		String imageLink = codecDao.getCodeFromCodeSystemRegion(imageLinkCode, "RSSConfiguration", "0", "EN").getLabel();
		vo.setChannelDescription(channelDescription);
		vo.setChannelLink(channelLink);
		vo.setChannelTitle(channelTitle);
		vo.setImageLink(imageLink);
		return vo;
	}
	
	private CoreDataset createCoreDataset(List<FileItem> fileItemList) {
		CoreDataset d = new CoreDataset();
		fillDataset(d, fileItemList);
		return d;
	}
	
	private FlexDataset createFlexDataset(List<FileItem> fileItemList) {
		FlexDataset d = new FlexDataset();
		fillDataset(d, fileItemList);
		return d;
	}
	
	private void fillDataset(Dataset d, List<FileItem> fileItemList) {
		
		FileItem titleItem = findFileItemByCode("TITLE", fileItemList);
		String title = extractValue(titleItem);
		d.setTitle(title);
		
		FileItem periodTypeCodeItem = findFileItemByCode("PERIOD_TYPE_CODE", fileItemList);
		String periodTypeCode = extractValue(periodTypeCodeItem);
		d.setPeriodTypeCode(periodTypeCode);
		
		FileItem keywordsItem = findFileItemByCode("KEYWORDS", fileItemList);
		String keywords = extractValue(keywordsItem);
		d.setKeywords(keywords);
		
		FileItem codeItem = findFileItemByCode("CODE", fileItemList);
		String code = extractValue(codeItem);
		d.setCode(code);
		
		FileItem abstractItem = findFileItemByCode("ABSTRACT", fileItemList);
		String abstractabstract = extractValue(abstractItem);
		d.setAbstractAbstract(abstractabstract);
		
		FileItem regionItem = findFileItemByCode("REGION", fileItemList);
		String region = extractValue(regionItem);
		d.setRegion(region);
		
		FileItem categoryItem = findFileItemByCode("CATEGORY", fileItemList);
		String category = extractValue(categoryItem);
		d.setCategories(category);
	}
	
	private DatasetType createDatasetType() {
		DatasetType t = new DatasetType();
		UUID type = UUID.randomUUID();
		t.setTitle(type.toString());
		return t;
	}
	
	private Source createSource(List<FileItem> fileItemList) {
		FileItem sourceNameItem = findFileItemByCode("SOURCE_NAME", fileItemList);
		String sourceName = extractValue(sourceNameItem);
		FileItem sourceRegionItem = findFileItemByCode("SOURCE_REGION", fileItemList);
		String sourceRegion = extractValue(sourceRegionItem);
		Source source = new Source();
		source.setTitle(sourceName);
		source.setCountry(sourceRegion);
		return source;
	}
	
	private List<Descriptor> createDescriptors(List<FileItem> fileItemList) {
		List<Descriptor> descriptors = new ArrayList<Descriptor>();
		for (int i = 0 ; i < Integer.MAX_VALUE ; i++) {
			LOGGER.info("ITERATION #" + i);
			FileItem columnName = findFileItemByCode("COLUMN_NAME_" + i, fileItemList);
			LOGGER.info("COLUMN_NAME_" + i + " is null? " + (columnName == null));
			if (columnName == null)
				break;
			FileItem dataType = findFileItemByCode("DATA_TYPE_" + i, fileItemList);
			Descriptor d = new Descriptor();
			d.setHeader(extractValue(columnName));
			d.setContentDescriptor(extractValue(dataType));
			LOGGER.info(d.getHeader() + " | " + d.getContentDescriptor());
			FileItem csName = findFileItemByCode("CODING_SYSTEM_NAME_" + i, fileItemList);
			LOGGER.info("CODING_SYSTEM_NAME_" + i + " is null? " + (csName == null));
			if (csName != null) {
				FileItem csType = findFileItemByCode("CODING_SYSTEM_TYPE_" + i, fileItemList);
				Option o = new Option(extractValue(csType), extractValue(csName));
				LOGGER.info("\t" + o.getOptionName() + " | " + o.getOptionValue());
				d.addOption(o);
			}
			descriptors.add(d);
			LOGGER.info("______________________________________________________________________");
		}
		
		FileItem muHeaderItem = findFileItemByCode("MU_HEADER", fileItemList);
		String muHeader = extractValue(muHeaderItem);
		if ((muHeader != null) && !muHeader.equals("null")) {
			
			Descriptor d = new Descriptor();
			d.setHeader(muHeader);
			d.setContentDescriptor(DataType.measurementUnit.name());
			descriptors.add(d);
			
			d = new Descriptor();
			d.setHeader("Date");
			d.setContentDescriptor(DataType.date.name());
			descriptors.add(d);
			
			FileItem quantityHeaderItem = findFileItemByCode("QUANTITY_HEADER", fileItemList);
			String quantityHeader = extractValue(quantityHeaderItem);
			d = new Descriptor();
			d.setHeader(quantityHeader);
			d.setContentDescriptor(DataType.quantity.name());
			descriptors.add(d);
		}
		
		return descriptors;
	}
	
	private FileItem findFileItemByCode(String code, List<FileItem> fileItemList) {
		for (FileItem i : fileItemList)
			if (i.getFieldName().startsWith(code))
				return i;
		return null;
	}
	
	private String extractValue(FileItem i) {
		int startIDX = 1 + i.getFieldName().indexOf('{');
		if (startIDX == -1)
			return null;
		int endIDX = i.getFieldName().indexOf('}', startIDX);
		if (endIDX == -1)
			return null;
		return i.getFieldName().substring(startIDX, endIDX);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
