package org.fao.fenix.web.modules.core.client.utils;

public class FenixServiceEntryPoint {

	String serviceNameBirt = "exporter/birtService";
	String serviceNameFaoSat = "exporter/faoStatService";
	String serviceNameFEWSNET = "exporter/fewsnetService";
	String serviceNameIMF = "exporter/imfService";
	String serviceNameBangladesh = "exporter/bangladeshService";
	String serviceNameCommunication = "exporter/communicationService";
	String serviceNameCNSA = "exporter/cnsaService";
	String serviceNameEC = "exporter/ecService";
	String serviceNameEsoko = "exporter/esokoService";
	String serviceNameFC = "exporter/fcService";
	String serviceNameMap = "exporter/mapService";
	String serviceNameUser = "exporteruserService";
	String serviceNameUserNew = "exporter/userService";
	String serviceNameResourceExplorer = "exporter/resourceExplorerService";
	String serviceNameText = "exporter/textService";
	String serviceNameRe = "exporter/reService";
	String serviceNameTable = "exporter/tableService";
	String serviceNameTinyMCE = "exporter/tinyMCEService";
	String serviceNameUDTable = "exporter/udtableService";
	String serviceNameConsole = "exporter/consoleService";
	String serviceNameProject = "exporter/projectService";
	String serviceNameMetadata = "exporter/metadataService";
	String serviceNameFpi = "exporter/fpiService";
	String serviceNameHaiti = "exporter/haitiService";
	String serviceNameExcelImporter = "exporter/excelImporterService";
	String serviceNameLatex = "exporter/latexService";
	String serviceNameChartDesigner = "exporter/chartDesignerService";
	String serviceNameProjectManager = "exporter/projectManagerService";
	String serviceNameIPC = "exporter/ipcService";
	String serviceNameIPCXML = "exporter/ipcxmlService";
	String serviceNameGIEWS = "exporter/giewsService";
	String serviceNameFSATMIS = "exporter/fsatmisService";
	String serviceNameCCBS = "exporter/ccbsService";
	String serviceNameCoding = "exporter/codingService";
	String serviceNameRainfall = "exporter/rainfallService";
	String serviceNameOlap = "exporter/olapService";
	String serviceNameCfs = "exporter/cfsService";
	String serviceNameAquastat = "exporter/aquastatService";
	String serviceNameADAM = "exporter/adamService";
	String serviceNameX = "exporter/xService";
	String serviceNameR = "exporter/rService";
	String serviceNameWcct = "exporter/wcctService";
	String serviceNameWelcome = "exporter/welcomeService";
	String serviceNameStatistics = "exporter/statisticsService";


	String serviceNameSDMX = "exporter/sdmxService";
	String serviceNameSLDEditor = "exporter/sldEditorService";
	String serviceNameFixer = "exporter/fixerService";
	String serviceNameDataViewer = "exporter/dataViewerService";
	//String contextRoot = "tc/adam";
	//String contextRoot = "fenix-web";
	String serviceNameOfcChart = "exporter/ofcChartService";
	String serviceNameQueryBuilder = "exporter/queryBuilderService";
	String serviceNameVenn = "exporter/vennService";
	String serviceNameHaitiCNSATool = "exporter/haitiCNSAToolService";
	String serviceNameSifsia = "exporter/sifsiaService";
	String serviceDataMapper = "exporter/dataMapperService";
	String serviceNameAMIS = "exporter/amisService";
	String serviceNameCPF = "exporter/cpfService";
	String serviceOlapViewer = "exporter/olapViewerService";



	String singleDatasetUploadServlet = "SingleDatasetUploadServlet";
	String ghostFormServlet = "GhostFormServlet";
	String codeListUploaderServlet = "CodeListUploaderServlet";
	String parseDatasetHeadersServlet = "ParseDatasetHeadersServlet";
	String jointLoginUploadServlet = "JointLoginUploaderServlet";
	String xImporterServlet = "XImporterServlet";

	String codingUploadServlet = "CodingUploadServlet";
	String codingMappingUploadServlet = "CodingMappingUploadServlet";
	String codingHierarchyUploadServlet = "CodingHierarchyUploadServlet";


	String changeBannerServlet = "ChangeBannerServlet";

	final static String imageUpload = "SingleImageUploadServlet";

	final static String shapeUpload = "SingleShapeFileUploadServlet";
	final static String shapeStore  = "SingleShapeFileStoreServlet";
	final static String geotiffUpload = "SingleGeotiffUploadServlet";
	final static String geotiffStore  = "SingleGeotiffStoreServlet";
	final static String externalGraphicUpload = "SingleExternalGraphicUploadServlet";
	final static String sldUpload = "SingleSLDUploadServlet";
	final static String sldSave = "SingleSLDSaveServlet";
	final static String sldNewUploader = "SLDUploaderServlet";

	final static String updateChartServlet = "UpdateChartServlet";


	public String getImageUpload(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(imageUpload, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointBirt(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameBirt, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointFaoStat(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameFaoSat, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointFEWSNET(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameFEWSNET, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointIMF(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameIMF, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointBangladesh(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameBangladesh, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointCNSA(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameCNSA, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointEC(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameEC, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointEsoko(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameEsoko, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointFC(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameFC, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointAquastat(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameAquastat, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointADAM(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameADAM, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointX(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameX, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointR(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameR, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointCfs(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameCfs, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointWcct(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameWcct, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointWelcome(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameWelcome, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointStatistics(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameStatistics, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointSDMX(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameSDMX, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointSLDEditor(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameSLDEditor, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointTable(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameTable, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointTinyMCE(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameTinyMCE, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointUDTable(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameUDTable, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointConsole(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameConsole, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointCommunication(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameCommunication, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointMap(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameMap, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointResourceExplorer(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameResourceExplorer, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointFenix(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameResourceExplorer, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointUser(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameUser, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointUserNew(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameUserNew, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointText(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameText, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointRe(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameRe, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointProject(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameProject, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointMetadata(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameMetadata, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointHaiti(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameHaiti, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointHaitiCNSATool(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameHaitiCNSATool, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointExcelImporter(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameExcelImporter, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointLatex(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameLatex, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointChartDesigner(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameChartDesigner, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointFpi(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameFpi, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointOlap(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameOlap, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointProjectManager(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameProjectManager, moduleBaseURL, moduleName);
	}

	public String getSingleDatasetUploadServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(singleDatasetUploadServlet, moduleBaseURL, moduleName);
	}

	public String getGhostFormServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(ghostFormServlet, moduleBaseURL, moduleName);
	}

	public String getCodeListUploaderServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(codeListUploaderServlet, moduleBaseURL, moduleName);
	}

	public String getParseDatasetHeadersServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(parseDatasetHeadersServlet, moduleBaseURL, moduleName);
	}

	public String getXImporterServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(xImporterServlet, moduleBaseURL, moduleName);
	}

	public String getJointLoginUploadServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(jointLoginUploadServlet, moduleBaseURL, moduleName);
	}

	public String getShapeUploadSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(shapeUpload, moduleBaseURL, moduleName);
	}

	public String getShapeStoreSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(shapeStore, moduleBaseURL, moduleName);
	}

	public String getGeotiffUploadSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(geotiffUpload, moduleBaseURL, moduleName);
	}

	public String getGeoTiffStoreSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(geotiffStore, moduleBaseURL, moduleName);
	}

	public String getExternalGraphicUploadSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(externalGraphicUpload, moduleBaseURL, moduleName);
	}

	public String getSLDUploadSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(sldUpload, moduleBaseURL, moduleName);
	}

	public String getSLDNewUploaderSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(sldNewUploader, moduleBaseURL, moduleName);
	}

	public String getSLDSaveSEP(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(sldSave, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointIPC(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameIPC, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointIPCXML(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameIPCXML, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointGIEWS(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameGIEWS, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointFSATMIS(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameFSATMIS, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointCCBS(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameCCBS, moduleBaseURL, moduleName);
	}


	public String getServiceEntryPointCodingService(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameCoding, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointRainfall(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameRainfall, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointCodingUploader(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(codingUploadServlet, moduleBaseURL, moduleName);
	}
	public String getServiceEntryPointCodingMappingUploader(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(codingMappingUploadServlet, moduleBaseURL, moduleName);
	}
	public String getServiceEntryPointCodingHierarchyUploader(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(codingHierarchyUploadServlet, moduleBaseURL, moduleName);
	}
	public String getServiceEntryPointFixerService(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameFixer, moduleBaseURL, moduleName);
	}
	public String getServiceEntryPointOfcChart(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameOfcChart, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointQueryBuilder(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameQueryBuilder, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointVenn(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameVenn, moduleBaseURL, moduleName);
	}

	public String getUpdateChartServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(updateChartServlet, moduleBaseURL, moduleName);
	}

	public String getChangeBannerServletEntryPoint(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(changeBannerServlet, moduleBaseURL, moduleName);
	}

	public String getServiceServletEntryPointSifsia(String moduleBaseURL, String moduleName){
		return getServiceEntryPoint(serviceNameSifsia, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointDataMapper(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceDataMapper, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointDataViewer(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameDataViewer, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointAMIS(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameAMIS, moduleBaseURL, moduleName);
	}

	public String getServiceEntryPointCPF(String moduleBaseURL, String moduleName) {
		return getServiceEntryPoint(serviceNameCPF, moduleBaseURL, moduleName);
	}
	
	
	protected String getServiceEntryPoint(String serviceName, String moduleBaseURL, String moduleName) {
		System.out.println("serviceName : " + serviceName);
		System.out.println("moduleBaseURL : " + moduleBaseURL);
		System.out.println("moduleName : " + moduleName);
	//	System.out.println("contextRoot : " + contextRoot);
		String fenixUrl = "";
		String gwtUrl = "";
		
		// client call from a server
		gwtUrl = moduleBaseURL.replaceFirst(moduleName + "/", "");
		
		/**if (moduleBaseURL.indexOf(contextRoot) > 0) {
			// client call from a server
			gwtUrl = moduleBaseURL.replaceFirst(moduleName + "/", "");
			System.out.println("IF moduleBaseURL.indexOf(contextRoot) ");
		} else {
			System.out.println("ELSE moduleBaseURL.indexOf(contextRoot) ");
			// client call from hosted mode or from GWTTestCase
			gwtUrl = moduleBaseURL.replaceFirst(moduleName, contextRoot);

			// http://localhost:2355
			// 012345678901234567890
			String port = gwtUrl.substring(17, 21);
			System.out.println(port);
			gwtUrl = gwtUrl.replaceFirst(port, "8080");
		}**/
		
		fenixUrl = gwtUrl + serviceName;
		System.out.println("END composed GWT server url name (gwtUrl) : " + gwtUrl);
	    System.out.println("END composed fenix server url name (fenixUrl) : " + fenixUrl);
		return fenixUrl;
	}

}
