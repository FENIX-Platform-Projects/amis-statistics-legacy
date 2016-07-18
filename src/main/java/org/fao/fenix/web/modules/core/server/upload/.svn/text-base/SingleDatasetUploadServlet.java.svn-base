package org.fao.fenix.web.modules.core.server.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.core.utils.StreamCloner;
import org.fao.fenix.core.x.RSSFactory;
import org.fao.fenix.core.x.XResourceType;
import org.fao.fenix.web.modules.chartdesigner.server.ChartDesignUpdater;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.x.common.vo.RSSConfigurationVO;
import org.fao.fenix.web.modules.x.server.utils.RSSWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class for Servlet: SingleDatasetUploadServlet
 * 
 * @web.servlet name="SingleDatasetUploadServlet" display-name="SingleDatasetUploadServlet"
 * 
 * @web.servlet-mapping url-pattern="/SingleDatasetUploadServlet"
 * 
 * 
 *                      TODO>> The progress bar is following the loading of the file into the server
 *                      memory. It is not reflecting the subsequent process of interpreting the file
 *                      into java objects and writing these to the database.<br/> TODO>> Inform the
 *                      user that the saving in the database has succeeded. <br/>
 * 
 */

public class SingleDatasetUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		Logger.getRootLogger().warn("SingleDatasetUploadServlet: START...");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DatasetImporter datasetImporter = (DatasetImporter) wac.getBean("datasetImporter");

		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			
			List fileItemList = upload.parseRequest(request);
			
			for (Object o : fileItemList)
				Logger.getRootLogger().warn(((FileItem)o).getFieldName());
			
			FileItem datasetFileItem = findFileItemByCode("DATASET", fileItemList);
			FileItem metadataFileItem = findFileItemByCode("METADATA", fileItemList);
			FileItem policyFileItem = findFileItemByCode("POLICY", fileItemList);
			
			String policyValue = extractValue(policyFileItem);
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyValue);
			
			Long datasetID = null;
			
			if (datasetFileItem.getName().endsWith("csv")) {
				FileItem delimiterFileItem = findFileItemByCode("DELIMITER", fileItemList);
				String delimiter = extractValue(delimiterFileItem);
				datasetID = datasetImporter.importDataset(datasetFileItem.getInputStream(), metadataFileItem.getInputStream(), policy, delimiter);
			} else if (datasetFileItem.getName().endsWith("xls")) {
				FileItem excelTemplateFileItem = findFileItemByCode("TEMPLATE", fileItemList);
				String excelTemplate = extractValue(excelTemplateFileItem);
				if (excelTemplate.equalsIgnoreCase("GIEWS Workstation")) {
					datasetID = datasetImporter.importExcelDataset(datasetFileItem.getInputStream(), metadataFileItem.getInputStream(), policy, excelTemplate, null, null, null);
				} else if (excelTemplate.equalsIgnoreCase("World Bank")) {
					
					FileItem muValueFileItem = findFileItemByCode("MU_VALUE", fileItemList);
					String muValue = extractValue(muValueFileItem);
					Logger.getRootLogger().warn("muValue is " + muValue);
					
					StreamCloner sc = new StreamCloner(metadataFileItem.getInputStream());
					InputStream metadata_stream_1 = sc.clone();
					InputStream metadata_stream_2 = sc.clone();
					String[] worldBankXMLValues = parseWorldBankXML(metadata_stream_1);
					String muHeader = worldBankXMLValues[0];
//					String muValue = worldBankXMLValues[1];
					String quantityHeader = worldBankXMLValues[2];
					datasetID = datasetImporter.importExcelDataset(datasetFileItem.getInputStream(), metadata_stream_2, policy, excelTemplate, muHeader, muValue, quantityHeader);
				}
			} else if (datasetFileItem.getName().endsWith("zip")) {
				FileItem delimiterFileItem = findFileItemByCode("DELIMITER", fileItemList);
				String delimiter = extractValue(delimiterFileItem);
				datasetImporter.bulkImport(datasetFileItem.getInputStream(), UploadPolicy.ignore, delimiter);
			}
			
			updateRSS(wac);
			updateChartDesigns(wac, datasetID);
			response.getOutputStream().print("datasetID:" + datasetID);
			Logger.getRootLogger().warn("SingleDatasetUploadServlet: END.");
			
		} catch (FileUploadException e) {
			response.getOutputStream().print("ERROR: " + e.getMessage());
			Logger.getRootLogger().warn("SingleDatasetUploadServlet: EXIT with exc", e);
		} catch (Exception e) { // catch all
			response.getOutputStream().print("ERROR: " + e.getMessage());	
			Logger.getRootLogger().warn("SingleDatasetUploadServlet: EXIT with exc", e);			
		}

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
	
	/**
	 * @param metadata_stream_1
	 * @return [0] = Measurement Unit Header, [1] = Measurement Unit Value, [2] = Quantity Header
	 */
	private static String[] parseWorldBankXML(InputStream metadata_stream_1) throws FenixException {
		String[] values = new String[3];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(metadata_stream_1);
			Element element = document.getDocumentElement();
			NodeList descriptorsNode = element.getElementsByTagName("descriptors");
			for (int i = 0; i < descriptorsNode.getLength(); i++) {
				Element descriptorsElement = (Element) descriptorsNode.item(i);
				NodeList descriptorNode = descriptorsElement.getElementsByTagName("descriptor");
				for (int j = 0; j < descriptorNode.getLength(); j++) {
					Element descriptorElement = (Element) descriptorNode.item(j);
					String header = descriptorElement.getElementsByTagName("header").item(0).getTextContent();
					String contentDescriptor = descriptorElement.getElementsByTagName("contentDescriptor").item(0).getTextContent();
					if (contentDescriptor.equalsIgnoreCase(DataType.measurementUnit.name())) {
						values[0] = header;
						values[1] = "VALUE_TO_BE_DEFINED";
					} else if (contentDescriptor.equalsIgnoreCase(DataType.quantity.name())) {
						values[2] = header;
					}
				}
			}
		} catch (ParserConfigurationException e) {
			throw new FenixException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
		Logger.getRootLogger().warn("values[0]: " + values[0]);
		Logger.getRootLogger().warn("values[1]: " + values[1]);
		Logger.getRootLogger().warn("values[2]: " + values[2]);
		return values;
	}
	
	private void updateRSS(WebApplicationContext wac) {
		RSSFactory rssFactory = (RSSFactory) wac.getBean("rssFactory");
		RSSWriter rssWriter = (RSSWriter) wac.getBean("rssWriter");
		UrlFinder urlFinder = (UrlFinder) wac.getBean("urlFinder");
		RSSConfigurationVO vo = createRSSConfiguration(wac, "00D", "02", "03", "04");
		
		// for the RSS generation no UniqueValue is needed 
		String rss = rssFactory.createLatestDatasetRSS(vo.getChannelTitle(), vo.getChannelDescription(), vo.getChannelLink(), vo.getImageLink(), urlFinder.fenixWebUrl, urlFinder.xServicesUrl, false);
		
		rssWriter.writeDatasetRSS(rss, XResourceType.dataset);
	}
	
	private void updateChartDesigns(WebApplicationContext wac, Long datasetID) {
		ChartDesignUpdater chartDesignUpdater = (ChartDesignUpdater)wac.getBean("chartDesignUpdater");
		List<Long> datasetIDs = new ArrayList<Long>();
		datasetIDs.add(datasetID);
		chartDesignUpdater.recreateCharts(datasetIDs);
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

}