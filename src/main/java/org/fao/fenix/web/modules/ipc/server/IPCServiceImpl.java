package org.fao.fenix.web.modules.ipc.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.coding.FenixGaul0;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.FlexDataset;
import org.fao.fenix.core.domain.ipc.IPCBean;
import org.fao.fenix.core.domain.ipc.IPCProvinceBean;
import org.fao.fenix.core.domain.ipc.IPCProvincesInfo;
import org.fao.fenix.core.domain.ipc.IPCWorkflowInfo;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.projecteddata.ProjectedData;
import org.fao.fenix.core.domain.perspective.FenixDomainUser;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.SaveDao;
import org.fao.fenix.core.persistence.IPCReport.IPCDao;
import org.fao.fenix.core.persistence.coding.FenixGaulDao;
import org.fao.fenix.core.persistence.file.FileFactory;
import org.fao.fenix.core.persistence.map.GeoViewDao;
import org.fao.fenix.core.persistence.map.ProjectedDataDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.perspective.FenixDomainUserDao;
import org.fao.fenix.core.persistence.perspective.MapDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.persistence.utils.DatasetUtils;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.map.geoserver.ProjectedDataMetadao;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.vo.FenixGaulVo;
import org.fao.fenix.web.modules.core.common.vo.FpiGaulVo;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ipc.common.services.IPCService;
import org.fao.fenix.web.modules.ipc.common.vo.IPCCodesVO;
import org.fao.fenix.web.modules.ipc.common.vo.MapVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.ipc.utils.DatasetCreationUtils;
import org.fao.fenix.web.modules.ipc.utils.XmlUtils;
import org.fao.fenix.web.modules.ipc.utils.DatasetCreationUtils.DatasetType;
import org.gwtwidgets.server.spring.ServletUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.security.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IPCServiceImpl extends RemoteServiceServlet implements IPCService {

	private static final long serialVersionUID = 1L;
	
	//	private String xml = Setting.getSystemPath() + File.separator + "ipc" + File.separator + "IPC.xml";
	private String xml = "/home/vortex/Scrivania/IPC.xml"; // remove and enable the other row
	
	
	
	protected Element      phaseMetadataTemplate, riskMetadataTemplate, trendMetadataTemplate;
	public static final String PHASE_METADATA_TEMPLATE_FILE = "phaseMetadata.xml";
	public static final String RISK_METADATA_TEMPLATE_FILE  = "riskMetadata.xml";
	public static final String TREND_METADATA_TEMPLATE_FILE = "trendMetadata.xml";
	
	FenixGaulDao fenixGaulDao;
	FenixDomainUserDao fenixDomainUserDao;
	CodecDao codecDao;
	SaveDao saveDao;
	IPCDao ipcDao;

	FileFactory fileFactory;
	DatasetImporter datasetImporter;
	DatasetDao datasetDao;
	FenixPermissionManager fenixPermissionManager;
	
	MapDao mapDao;
	GeoViewDao geoViewDao;
	ProjectedDataDao projectedDataDao;
	ProjectedDataMetadao projectedDataMetadao;
	WMSMapProviderDao wmsMapProviderDao;
	LayerGaulUtils layerGaulUtils;

	
	Logger LOGGER = Logger.getLogger(IPCServiceImpl.class);
			
	public String getIPCApplName() {
		return Setting.getIPCApplName();
	}
	
	public List<CodeVo> getCountryList() {
		List<Code> codes = codecDao.findAllGaul0();
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}
		
	private CodeVo code2vo(Code c) {
		CodeVo vo = new CodeVo();
		vo.setCode(c.getCode());
		vo.setDescription(c.getDescription());
		vo.setLabel(c.getLabel());
		vo.setLangCode(c.getLangCode());
		return vo;
	}
	

	public List<String> getLayerList() {
		return fenixGaulDao.getAllGaulLayerNames();
	}
	
	public Map<String, String> getAdministrativeUnits(String layerCode, String gaulCodeCountry) {
	   return codecDao.findGaulLevelItemsByCountry(layerCode, gaulCodeCountry);
	}
	
	public String openIPCCreateWorkflow(String layerCode, String layerLabel, String gaulCodeCountry, String countryLabel) {

		System.out.println("Layer Code = "+layerCode + "; layerLabel = "+layerLabel + "; gaulCountryCode = "+ gaulCodeCountry);
		
		String subAreas = getSortedSubAreas(layerCode, gaulCodeCountry);
	
		String fenixIPCParameters = "baseAreaCode="+gaulCodeCountry+"|"+countryLabel+"|"+layerLabel+"|"+layerCode+ "&baseAreaName="+ countryLabel + " " + layerLabel + "&" + subAreas;

		String usersFullName = "";
	
			FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
			if (du != null) {
				usersFullName = du.getFirstName()+" "+ du.getLastName();
			}
		
		HttpServletRequest request = ServletUtils.getRequest();
		HttpSession session = request.getSession();
	  

		String ipcParameters = "name="+usersFullName+"&ss=login&key=" + (String)session.getAttribute("IPCSecurityToken") + "&task=create&" + fenixIPCParameters;

		System.out.println("openIPCCreateWorkflow: ipcParameters = " + ipcParameters);

		String urlLocation = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+Setting.getIPCApplName();
	
		return urlLocation + "/org.fao.ipc.IPC/service?" + ipcParameters;
		
	}

	public String openIPCShowWorkflows(String gaulCodeCountry) {

		HttpServletRequest request = ServletUtils.getRequest();
		HttpSession session = request.getSession();
	  
		String ipcParameters = "ss=login&key=" + (String)session.getAttribute("IPCSecurityToken") + "&task=list&baseAreaCode=" + gaulCodeCountry;

		System.out.println("openIPCShowWorkflows: ipcParameters = " + ipcParameters);

		String urlLocation = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+Setting.getIPCApplName();
	
		return urlLocation + "/org.fao.ipc.IPC/service?" + ipcParameters;

	}
	
	public FenixGaulVo mapFenixGaul0(FenixGaul0 gaul, String level) {
		FenixGaulVo vo = new FenixGaulVo();
		vo.setCode(gaul.getCode());
		vo.setContinent(gaul.getContinent());
		vo.setName(gaul.getName());
		vo.setRegion(gaul.getRegion());
		vo.setLevel(level);
		return vo;
	}

	public FpiGaulVo domainToVo(FenixGaulDao domain) {
		return new FpiGaulVo();
	}

	public void setFenixGaulDao(FenixGaulDao fenixGaulDao) {
		this.fenixGaulDao = fenixGaulDao;
	}
	
	public void setFenixDomainUserDao(FenixDomainUserDao fenixDomainUserDao) {
		this.fenixDomainUserDao = fenixDomainUserDao;
	}
	

	private String authenticate(URL url) throws Exception
	{
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		int responseCode = conn.getResponseCode();	
		if (responseCode != HttpURLConnection.HTTP_OK)
			throw new Exception("http error: " + responseCode + "(" +  
					conn.getResponseMessage() + ")");

		BufferedReader reader = new BufferedReader(new  
				InputStreamReader(conn.getInputStream()));
		return reader.readLine();
	}
	


	public String getSecurityTokenFromIPC(String username, String name) {
		
		HttpServletRequest request = ServletUtils.getRequest();
		HttpSession session = request.getSession();
	  
		String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+Setting.getIPCApplName()+"/org.fao.ipc.IPC/service?ss=authenticate&username="+username;
		
		System.out.println("url =  "+url);

		String security_token = "";

		try {
			security_token = authenticate(new URL(url));
			session.setAttribute("IPCSecurityToken", security_token);
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return security_token;
	}
	
	private String getSortedSubAreas(String layerCode, String gaulCodeCountry) {
		
		Map<String, String> adminUnitsMap = getAdministrativeUnits(layerCode, gaulCodeCountry);
		
		List<String> adminUnitCodes = new ArrayList<String>(adminUnitsMap.keySet());
		List<String> adminUnitNames = new ArrayList<String>(adminUnitsMap.values());
		
		adminUnitsMap.clear();
		
		TreeSet<String> sortedSet = new TreeSet<String>(adminUnitNames);
		Object[] sortedArray = sortedSet.toArray();

		for (int i=0; i<sortedArray.length; i++) {
			adminUnitsMap.put(adminUnitCodes.get(adminUnitNames.indexOf(sortedArray[i])), (String) sortedArray[i]);
		}
		
		String subAreas = "subAreas=";

		//append to the subAreas string, using the now sorted map
		Iterator<Map.Entry<String, String>> iterator = adminUnitsMap.entrySet().iterator();
 
		for (int i = 0; i < adminUnitsMap.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();
			subAreas += entry.getKey() + ":" + entry.getValue() + "|";
		}
		
		return subAreas;
	}
	
	
	public List<CodeVo> getUserCountryList() {

		HttpServletRequest request = ServletUtils.getRequest();
		String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+Setting.getIPCApplName()+"/org.fao.ipc.IPC/service?ss=findBaseAreas";
		List<CodeVo> vos = new ArrayList<CodeVo>();

		try{
			Document baseAreasDocument = getXMLFromIPC(url);
			Element rootElement = baseAreasDocument.getRootElement();   
			
			if(baseAreasDocument==null){
				LOGGER.error("baseAreasDocument = NULL");	
			}
			List<Element> children = rootElement.getChildren();

			for (Element child : children) {
				String gaulCode = child.getAttribute("code").getValue();
				LOGGER.info("gaulCode = "+gaulCode);
				String gaulLabel = codecDao.getLabelFromCodeCodingSystem(gaulCode, "GAUL0", "0", "EN");
				LOGGER.info("gaulLabel = "+gaulLabel);
				CodeVo code = new CodeVo();
				code.setLabel(gaulLabel);
				code.setCode(gaulCode);
				vos.add(code);
			}
		} catch(Exception ex){
			LOGGER.error("problem parsing IPC XML");
		}
		return vos;
	}
	
	
	public List<CodeVo> getCountryWorkflowList(String countryGaulCode) {

		HttpServletRequest request = ServletUtils.getRequest();
		String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+Setting.getIPCApplName()+"/org.fao.ipc.IPC/service?ss=findWorkflows&baseAreaCode="+countryGaulCode;
		List<CodeVo> vos = new ArrayList<CodeVo>();

		try{
			Document workflowDocument = getXMLFromIPC(url);
			Element rootElement = workflowDocument.getRootElement();   
			List<Element> children = rootElement.getChildren();

			for (Element child : children) {
				String workflowName = child.getAttribute("name").getValue();
				String workflowId = child.getAttribute("id").getValue();
				CodeVo code = new CodeVo();
				code.setLabel(workflowName);
				code.setCode(workflowId);
				vos.add(code);
			}
		} catch(Exception ex){
			LOGGER.error("problem parsing IPC XML");
		}
		return vos;
	}

	
	
	private Document getXMLFromIPC(String urlString) throws Exception
	{
		URL url = new URL(urlString);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();

		int responseCode = conn.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK)
			throw new Exception("http error: " + responseCode + "(" + conn.getResponseMessage() + ")");

		SAXBuilder builder = new SAXBuilder();
		return builder.build(conn.getInputStream());
	}

	/******
	 * 
	 * NEW IPC STUFF
	 * 
	 ******/
	
	
	public List<CodeVo> getAreasCodes(String countryGaulCode, String gaulLayer) {
		List<Code> codes = new ArrayList<Code>(); 
		System.out.println(countryGaulCode + " | " + gaulLayer);
		if ( gaulLayer.equals("GAUL0")) {
			Code c = new Code();
			c.setCode(countryGaulCode);
			codes.add(c);
		}
		else if ( gaulLayer.equals("GAUL1")) {
			codes = codecDao.findAllGaul1FromGaul0(countryGaulCode);
		}
		else if ( gaulLayer.equals("GAUL2")) {
			codes = codecDao.findAllGaul2FromGaul0(countryGaulCode);
		} 
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}

	
	public UserVO getCurrentUserInfo() {
		UserVO user = new UserVO();
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		if (du != null) {
			user.setId(du.getId());
			user.setFirstName(du.getFirstName());
			user.setLastName(du.getLastName());
		}
		return user;
	}

	
	public Long saveWorkflow(WorkflowInfoVO workflowInfoVO) {
		IPCWorkflowInfo ipcWorkflow = new IPCWorkflowInfo();
		/** getting moderator **/
		FenixDomainUser moderator = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		ipcWorkflow.setModerator(moderator);
		
		/** getting observers **/
		for(UserVO observer : workflowInfoVO.getObservers()) {
			/** query db to FenixDomainUser **/
			FenixDomainUser o = fenixDomainUserDao.findById(observer.getId());
			System.out.println(o.getLastName());
			/*** TODO: ADD THE OBSERVER WHEN THE RELATION IN IPCWOFKFLOWINFO TABLE WILL BE READY **/
			//ipcWorkflow.addObserver(o);
		}		
		/*** TODO: WORKAROUND THE MODERATOR IS ALSO AN OBSERVER AND A CONTRIBUTOR -> CHANGE IT IN A MODE CLEAR WAY **/
//		ipcWorkflow.addObserver(moderator);
		
		
		
		/** set Geo Area **/
		ipcWorkflow.setGeographicAreaCode(workflowInfoVO.getGeographicArea().getCode());
		ipcWorkflow.setGeographicAreaLabel(workflowInfoVO.getGeographicArea().getLabel());
		
		/** set Layer **/
		ipcWorkflow.setReferenceLayerCode(workflowInfoVO.getReferenceLayer().getCode());
		ipcWorkflow.setReferenceLayerLabel(workflowInfoVO.getReferenceLayer().getLabel());
		
		/** additional info **/
		ipcWorkflow.setName(workflowInfoVO.getWorkflowName());
		ipcWorkflow.setDescription(workflowInfoVO.getDescription());
		ipcWorkflow.setDate(workflowInfoVO.getDate());
		ipcWorkflow.setPeriod(workflowInfoVO.getPeriod());
		ipcWorkflow.setStatusCode(workflowInfoVO.getStatus().getCode());
		ipcWorkflow.setStatusLabel(workflowInfoVO.getStatus().getLabel());
		
		
		
		/** SAVE WORKFLOW ON DB **/
		saveDao.save(ipcWorkflow);
		
		/*** TODO: WORKAROUND THE MODERATOR IS ALSO AN OBSERVER AND A CONTRIBUTOR -> CHANGE IT IN A MODE CLEAR WAY **/
		UserVO u = new UserVO();
		u.setId(moderator.getId());
		workflowInfoVO.getContributors().add(u);
		
		String xml = new String();
		try {
			xml = getXmlString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/** for every contributor save the provinces **/
		if ( workflowInfoVO.getContributors() != null) {
			for(UserVO contributor : workflowInfoVO.getContributors()) {			
				/** query db to FenixDomainUser **/
				FenixDomainUser c = fenixDomainUserDao.findById(contributor.getId());			
//				for(IPCCodesVO province : workflowInfoVO.getInterestedAreas()) {
				for(ProvinceVO province : workflowInfoVO.getProvinces()) {
					IPCProvincesInfo ipcProvinceInfo = new IPCProvincesInfo();
					ipcProvinceInfo.setContributor(c);
					ipcProvinceInfo.setWorkflowID(ipcWorkflow.getId());
					ipcProvinceInfo.setIsEmpty(true);
					ipcProvinceInfo.setPeriod(ipcWorkflow.getPeriod());
					ipcProvinceInfo.setProvinceCode(province.getProvinceCode());
					ipcProvinceInfo.setProvinceLabel(province.getProvinceLabel());
					ipcProvinceInfo.setXml(xml);
					saveDao.save(ipcProvinceInfo);
					
				}
			}
		}
		Long workflowId = ipcDao.getWorkflowID(moderator.getId(), workflowInfoVO.getGeographicArea().getCode(), workflowInfoVO.getDate());
		return workflowId;
	}
	
	/******
	 * 
	 * CREATE IPC DATASETS
	 * 
	 ******/
	
	public void createIPCDatasetsAndMap(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow)
	{
		try{
			String configPath = Setting.getSystemPath() + File.separator + "ipc";
			
			Element phaseMetadataTemplate = XmlUtils.read(new File(configPath + File.separator + "templates"+ File.separator + PHASE_METADATA_TEMPLATE_FILE));
			Element riskMetadataTemplate  = XmlUtils.read(new File(configPath + File.separator + "templates"+ File.separator +  RISK_METADATA_TEMPLATE_FILE));
			Element trendMetadataTemplate = XmlUtils.read(new File(configPath + File.separator + "templates"+ File.separator +  TREND_METADATA_TEMPLATE_FILE));

			//create datasets
			String phaseDatasetId = uploadDataset(userSpecificProvinceVO, workflow, phaseMetadataTemplate, DatasetCreationUtils.DatasetType.PHASE_TYPE);
			String riskDatasetId  = uploadDataset(userSpecificProvinceVO, workflow, riskMetadataTemplate, DatasetCreationUtils.DatasetType.RISK_TYPE);
			String trendDatasetId = uploadDataset(userSpecificProvinceVO, workflow, trendMetadataTemplate, DatasetCreationUtils.DatasetType.TREND_TYPE);

			//REINSTATE!!!!!create map
			createIPCMap(userSpecificProvinceVO.get(0).getContributor_id().toString(), workflow, phaseDatasetId, riskDatasetId, trendDatasetId); 
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}

	}	
	
	private String uploadDataset(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow, Element metadataTemplate, DatasetType type) throws IOException, Exception
	{
		String userId = userSpecificProvinceVO.get(0).getContributor_id().toString();	
		String workflowId = workflow.getWorkflowId().toString();
		String localResourceId =  DatasetCreationUtils.createResourceId(workflowId, userId, type);

	
		LOGGER.warn("User ID: " + userId);
		LOGGER.warn("Workflow ID: " + workflowId);
		LOGGER.warn("Local Resource ID: " + localResourceId);
		LOGGER.warn("Country Label: " + workflow.getGeographicArea().getLabel());
		LOGGER.warn("Country Code: " + workflow.getGeographicArea().getCode());
		LOGGER.warn("Period: " + workflow.getPeriod());
		LOGGER.warn("TYPE: " + type);
		
		
		//create dataset zip: csv + metadata
		//DatasetCreationUtils.createZipFile(localResourceId, userSpecificProvinceVO, workflow, metadataTemplate, type);
		DatasetCreationUtils.createDatasetFiles(localResourceId, userSpecificProvinceVO, workflow, metadataTemplate, type);
		
		String datasetId = "";

		try{
			//import zip file
			datasetId = importIPCDataset(localResourceId, workflow, type);
		} catch(IOException io){
			io.printStackTrace();	
		}
		
		LOGGER.warn("datasetId: " + datasetId);
		return datasetId;

	}	

	private String importIPCDataset(String localResourceId, WorkflowInfoVO workflow, DatasetType type) throws Exception {

		String id = "";

		try {

		//	fileFactory.unzip(System.getProperty("java.io.tmpdir") + File.separator + localResourceId + ".zip");
			String datasetName = System.getProperty("java.io.tmpdir") + File.separator + localResourceId + ".csv";
			LOGGER.warn("Dataset name: " + datasetName);
			String metadataName = System.getProperty("java.io.tmpdir") + File.separator + localResourceId + "_metadata.xml";
			LOGGER.warn("Metadata name: " + metadataName);
			FileInputStream mfile = new FileInputStream(new File(metadataName));
			LOGGER.warn("mfile EXISTS: " + !(mfile == null));
			FileInputStream dfile = new FileInputStream(new File(datasetName));
			LOGGER.warn("dfile EXISTS: " + !(dfile == null));

			// IMPORT RESOURCE
			FileInputStream datasetFile = new FileInputStream(new File(datasetName));
			FileInputStream metadataFile = new FileInputStream(new File(metadataName));

			//AuthUtils authUtils = new AuthUtils();
			//authUtils.loginAsSystemUser();

			Long longId = datasetImporter.importDataset(datasetFile, metadataFile, UploadPolicy.overwrite, ",");
			id = longId.toString();

			//set the code
			Dataset dataset = datasetDao.findById(longId);
			dataset.setCode(localResourceId); //e.g IPC_RISK_workflowId or IPC_PHASE_workflowid
			datasetDao.save(dataset);

			fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
			
			/*
			 * TODO: Set user permissions
			 */
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.READ, longId, true);
			//fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.WRITE, id, true);
			//fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.DELETE, id, true);
			//fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.DOWNLOAD, id, true);
			//fenixPermissionManager.updateGroupPermissionOnResource(FenixRole.ANONYMOUS, FenixPermission.READ, id, true);

			//authUtils.restore();


		} catch (FileNotFoundException e) {
			throw new FenixException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new FenixException(e.getMessage());
		}


		return id;

	}

	/******
	 * 
	 * CREATE IPC MAP
	 * 
	 ******/
	
	
	
	private void createIPCMap(String userId, WorkflowInfoVO workflow, String phaseDatasetId, 
			String riskDatasetId, String trendDatasetId) 
	throws FenixException {
		
		Long workflowId = workflow.getWorkflowId();
		
		String gaulCode = workflow.getGeographicArea().getCode();
		String gaulLabel = workflow.getGeographicArea().getLabel();

		String layerLabel = workflow.getReferenceLayer().getLabel();
		String layerCode = (workflow.getReferenceLayer().getCode()).toLowerCase();
		String mapIdentifier = "IPC_MAP_"+workflow.getWorkflowId()+"_"+userId;
	

		LOGGER.warn("phaseDatasetId: " + phaseDatasetId);
		LOGGER.warn("riskDatasetId: " + riskDatasetId);
		LOGGER.warn("trendDatasetId: " + trendDatasetId);
		LOGGER.warn("mapIdentifier: " + mapIdentifier);
		LOGGER.warn("layerCode: " + layerCode);
		
		//AuthUtils authUtils = new AuthUtils();
		//authUtils.loginAsSystemUser();

		//If the map view already exists for the workflow and user then delete it 
	/*	if(mapDao.findByCode(mapIdentifier)!=null)  {
			try{
				mapDao.delete(mapDao.findByCode(mapIdentifier));
			} catch(Exception ex){
				LOGGER.error("Could NOT delete the existing map");
			}

		} else   LOGGER.warn("Map does not exist");
*/

		if(mapDao.findByCode(mapIdentifier)==null)  {
			//1. Retrieve Datasets and do the checks
			Dataset phaseDataset = datasetDao.findById(Long.valueOf(phaseDatasetId)); 
			Dataset riskDataset = datasetDao.findById(Long.valueOf(riskDatasetId)); 
			Dataset trendDataset = datasetDao.findById(Long.valueOf(trendDatasetId)); 

			/*   if (!DatasetUtils.containsFeatureCode(riskDataset) && !DatasetUtils.containsFeatureCode(phaseDataset) && !DatasetUtils.containsFeatureCode(trendDataset)) {
			   LOGGER.warn("Datasets '" + riskDataset.getTitle() + " and/or " + phaseDataset.getTitle() + " and/or " + trendDataset.getTitle()+" ' have no geographic references.");
			   throw new FenixException("Datasets '" + riskDataset.getTitle() + " and/or " + phaseDataset.getTitle() + " and/or " + trendDataset.getTitle()+ "' have no geographic references.");
		   } else LOGGER.warn("Dataset(s) CONTAINS FEATURECODE!");*/


			//2. Create new map view 
			MapView mapView = new MapView();

			//set map view metadata
			mapView.setTitle(gaulLabel+ " Integrated Food Security Phase Classification");
			mapView.setSource(phaseDataset.getSource());
			mapView.setProvider(phaseDataset.getProvider());
			mapView.setRegion(gaulCode);
			mapView.setDateLastUpdate(new Date());
			mapView.setAbstractAbstract(phaseDataset.getAbstractAbstract());
			mapView.setKeywords("IPC, early warning");
			mapView.setCategories("023");
			mapView.setCode(mapIdentifier); //IPC_MAP_workflowId

			//Set the country as the Bounding Box extent
			BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(gaulCode)); //GAUL0
			mapView.setBoundingBox(bbox);

			//Add GAUL0 layer 
//			WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
//			if (gaul0 == null)
//				throw new NullPointerException("Gaul 0 (layer_gaul0) not found");
//
//			GeoView gaul0GeoView = new GeoView();
//			gaul0GeoView.setWmsMapProvider(gaul0);
//			gaul0GeoView.setTitle(gaulLabel + " Country Boundary");
//			//gaul0GeoView.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(gaul0Code));
//			// gaul0GeoView.setStyleName("polygon_filled");
//			mapView.addGeoView(gaul0GeoView);

			//Add GAULX layer = layer which the data is projected on 
			String referenceLayerCode = "layer_" + layerCode;

			WMSMapProvider wmp = wmsMapProviderDao.findByCode(referenceLayerCode);
			if (wmp == null)
				throw new NullPointerException("Gaul "+"layer_" + layerCode+" not found");

			if (!(wmp instanceof DBFeatureLayer)) {
				LOGGER.error("Layer '" + wmp.getLayerName() + "' (id:" + wmp.getResourceId() + ")" + " is a " + wmp.getClass().getSimpleName() + " and cannot be joined.");
				throw new FenixException("Layer '" + wmp.getLayerName() + "' (id:" + wmp.getResourceId() + ")" + " is a " + wmp.getClass().getSimpleName() + " and cannot be joined.");
			}
			

			GeoView refGeoViewBorder = new GeoView();
			refGeoViewBorder.setWmsMapProvider(wmp);
			refGeoViewBorder.setTitle(gaulLabel + " " + layerLabel + " Border");
//			refGeoViewBorder.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(gaul0Code));
			mapView.addGeoView(refGeoViewBorder);
			
			
			mapDao.save(mapView);


			//create each projected dataset geoview
			if(datasetCheck(phaseDataset)) {
				GeoView geoView1 = createProjectedDatasetGeoView(userId, workflowId, referenceLayerCode, phaseDataset, "Current or Imminent Phase", "ipccurrentphase", false);
				mapView.addGeoView(geoView1);
				geoViewDao.save(geoView1);
			} else LOGGER.warn("Phase Dataset can't be projected");


			if(datasetCheck(riskDataset)) {
				GeoView geoView2 = createProjectedDatasetGeoView(userId, workflowId, referenceLayerCode, riskDataset, "Risk of Worsening Phase", "ipcriskphase", false);
				mapView.addGeoView(geoView2);
				geoViewDao.save(geoView2);
			}else  LOGGER.warn("Risk Dataset can't be projected");

			if(datasetCheck(trendDataset)) {
				GeoView geoView3 = createProjectedDatasetGeoView(userId, workflowId, referenceLayerCode, trendDataset, "Projected Trend", "ipctrend", true);
				mapView.addGeoView(geoView3);
				geoViewDao.save(geoView3);
			} else  LOGGER.warn("Trend Dataset can't be projected");

			
			
			// add gaul1 if it is at level2
			if ( layerCode.equals("gaul2")) {
				WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
				if (gaul1 == null)
					throw new NullPointerException("Gaul 1 (layer_gaul1) not found");

				GeoView gaul1Geoview = new GeoView();
				gaul1Geoview.setWmsMapProvider(gaul1);
				gaul1Geoview.setTitle(gaulLabel + " Level 1 Border");
//				refGeoViewBorder.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(gaul0Code));
				mapView.addGeoView(gaul1Geoview);
			}
			
			// adding gaul0
			WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
			if (gaul0 == null)
				throw new NullPointerException("Gaul 0 (layer_gaul0) not found");

			GeoView gaul0GeoView = new GeoView();
			gaul0GeoView.setWmsMapProvider(gaul0);
			gaul0GeoView.setTitle(gaulLabel + " Country Boundary");
			//gaul0GeoView.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(gaul0Code));
			// gaul0GeoView.setStyleName("polygon_filled");
			mapView.addGeoView(gaul0GeoView);
			
			GeoView refGeoViewLabels = new GeoView();
			refGeoViewLabels.setWmsMapProvider(wmp);
			refGeoViewLabels.setTitle(gaulLabel + " " + layerLabel + " Labels");
			refGeoViewLabels.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(gaulCode));
			refGeoViewLabels.setStyleName("zzlayer_"+layerCode+"_label"); // Labels
			mapView.addGeoView(refGeoViewLabels);

			
			
			

			mapDao.save(mapView); //re-save the mapView

			/*
			 * TODO: Set user permissions
			 */
			// Set the permissions on the Map View
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.READ, mapView.getResourceId(), true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.WRITE, mapView.getResourceId(), true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.IPC_USER_GROUP, FenixPermission.DOWNLOAD, mapView.getResourceId(), true);

			//authUtils.restore();

			LOGGER.info("Created map with id " +  mapView.getResourceId());

		} else {
			LOGGER.info("Map exists no need to recreate !!");
		}
	}
	
	   private Boolean datasetCheck(Dataset dataset){
		   if (dataset instanceof FlexDataset || ((CoreDataset) dataset).getCoreType() == CoreDataset.CoreType.QUALITATIVE && !DatasetUtils.containsFeatureCode(dataset)) {
			   LOGGER.warn("Dataset '" + dataset.getTitle() + "' is not of the right type and can't be projected.");
			  return false;
		   } else {
			   LOGGER.warn("Dataset CAN BE PROJECTED and CONTAINS feature codes!");
			   return true;
		   }
	   }

	   
		private GeoView createProjectedDatasetGeoView(String userId, Long workflowId, String layerCode, Dataset dataset, String displayName, String styleName, boolean isTrend){
			   // Find layer which the data is to be projected
			
			   String layerReference = "";
			
			  // if(isTrend)
				//   layerReference = layerCode+"centroids";
			  // else 
				   layerReference = layerCode;
			   
			
				   
			   WMSMapProvider wmp = wmsMapProviderDao.findByCode(layerReference);
			   
			   DBFeatureLayer dbfl = (DBFeatureLayer) wmp;
			   
			   // Project data
				LOGGER.warn("createProjectedDatasetGeoView: dataset core type is "+((CoreDataset) dataset).getCoreType());
				ProjectedData projData = new ProjectedData(dataset, dbfl);
			   projData.setTitle(displayName);
			   projData.setAbstractAbstract(projData.getAbstractAbstract());
			   //set constraints on the user id and workflow id
			   projData.setDimValue(DataType.firstIndicator.name(), userId);
			   projData.setDimValue(DataType.secondIndicator.name(), workflowId.toString());
			   projData.unconstrainDimension(DataType.date.name());
			   

			   if (!projectedDataDao.doesContainRows(projData, false))
				   throw new FenixException("The "+displayName+ " dataset projects no data onto this layer ("+layerReference+")");

			   projectedDataMetadao.save(projData, dataset.getTitle(), dataset.getAbstractAbstract());

			   //Create the geoView
			   GeoView geoView = new GeoView();
			   if(styleName!="")
				   geoView.setStyleName(styleName);
			   geoView.setWmsMapProvider(projData);
			   geoView.setTitle(projData.getTitle());
			 
			   
			  // geoViewDao.save(geoView);
			   
			   return geoView;
		}
		
		/******
		 * 
		 * FIND IPC MAP
		 * 
		 ******/
		public MapVO findIPCMap(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow)
		{
			MapVO vo = new MapVO();
			
			String mapCode = "IPC_MAP_"+workflow.getWorkflowId()+"_"+userSpecificProvinceVO.get(0).getContributor_id().toString();
			MapView map = mapDao.findByCode(mapCode);
			
			String mapIdTitle = null;
			
			if(map!=null) {
				vo.setMapId(map.getResourceId());
				vo.setMapTitle(map.getTitle());
			} else { 
				/*
				 * TODO: create map
				 */
				LOGGER.error("IPC MAP with CODE = "+mapCode + " DOES NOT EXIST!");
			}
			
			return vo;
		}	
	
		public List<WorkflowInfoVO> getContributorWorkflows(Long contributor_id, String countryCode) {
		List<WorkflowInfoVO> workflows = new ArrayList<WorkflowInfoVO>();
		List<IPCWorkflowInfo> ipcWorkflowsInfo = ipcDao.getUserWorkflows(contributor_id, countryCode);
		for(IPCWorkflowInfo workflow : ipcWorkflowsInfo) {
			WorkflowInfoVO w = new WorkflowInfoVO();
			w.setWorkflowId(workflow.getId());
			w.setWorkflowName(workflow.getName());
			w.setDescription(workflow.getDescription());
			w.setDate(workflow.getDate());
			w.setPeriod(workflow.getPeriod());
			w.setReferenceLayer(new IPCCodesVO(workflow.getReferenceLayerCode(), workflow.getReferenceLayerLabel()));
			w.setStatus(new IPCCodesVO(workflow.getStatusCode(), workflow.getStatusLabel()));
			w.setGeographicArea(new IPCCodesVO(workflow.getGeographicAreaCode(), workflow.getGeographicAreaLabel()));
			workflows.add(w);
		}
		return workflows;
	}
	
	public List<ProvinceVO> getWorkflowProvinces(Long contributor_id, Long workflow_id) {
		List<ProvinceVO> provinces = new ArrayList<ProvinceVO>();
		List<IPCProvincesInfo> ipcWorkflowsInfo = ipcDao.getUserProvinces(contributor_id, workflow_id);
		for(IPCProvincesInfo province : ipcWorkflowsInfo) {
			ProvinceVO p = new ProvinceVO();
			p.setId(province.getId());
			p.setContributor_id(contributor_id);
			p.setPeriod(province.getPeriod());	
			p.setPhaseClassification(province.getPhaseClassification());
			p.setProjectedTrend(province.getProjectedTrend());
			p.setProvinceCode(province.getProvinceCode());
			p.setProvinceLabel(province.getProvinceLabel());
			p.setRiskLevel(province.getRiskLevel());
			p.setXml(province.getXml());
			provinces.add(p);
		}
		return provinces;
	}
		
	
	
	public List<WorkflowInfoVO> getObserverProvinces(Long observer_id, String countryCode) {
		/** TODO: get observer workflows **/
		
		List<IPCWorkflowInfo> workflows = ipcDao.findAllWorkflows(countryCode);
		List<WorkflowInfoVO> ws = new ArrayList<WorkflowInfoVO>();
		for(IPCWorkflowInfo workflow : workflows) {
			List<IPCProvincesInfo> provinces = ipcDao.findAllProvinces(workflow.getId());		
		/** TODO: this is the right one when the observers will work
//		List<IPCWorkflowInfo> workflows = ipcDao.findAllObserverWorkflows(observer_id, countryCode);
//		List<WorkflowInfoVO> ws = new ArrayList<WorkflowInfoVO>();
//		for(IPCWorkflowInfo workflow : workflows) {
//			List<IPCProvincesInfo> provinces = ipcDao.getObserverProvinces(workflow.getId()); **/
				for(IPCProvincesInfo province : provinces) {
					WorkflowInfoVO w = new WorkflowInfoVO();
					w.setWorkflowId(workflow.getId());
					w.setWorkflowName(workflow.getName());
					w.setDescription(workflow.getDescription());
					w.setDate(workflow.getDate());
					w.setPeriod(workflow.getPeriod());
					w.setReferenceLayer(new IPCCodesVO(workflow.getReferenceLayerCode(), workflow.getReferenceLayerLabel()));
					w.setStatus(new IPCCodesVO(workflow.getStatusCode(), workflow.getStatusLabel()));
					w.setGeographicArea(new IPCCodesVO(workflow.getGeographicAreaCode(), workflow.getGeographicAreaLabel()));
					
					List<ProvinceVO> ps = new ArrayList<ProvinceVO>();
					ProvinceVO p = new ProvinceVO();
					p.setId(province.getId());
					p.setContributor_id(province.getContributor().getId());
					p.setContributor_firstName(province.getContributor().getFirstName());
					p.setContributor_lastName(province.getContributor().getLastName());
					p.setProvinceCode(province.getProvinceCode());
					p.setProvinceLabel(province.getProvinceLabel());
					p.setXml(province.getXml());
					ps.add(p);
					
					w.setProvinces(ps);
					ws.add(w);
				}
		}
		
		/** save the workflowInfoVO **/
		
		return ws;
	}

	public List<UserVO> getUsers(List<CodeVo> name) {
		List<UserVO> users = new ArrayList<UserVO>();
			List<FenixDomainUser> dus = fenixDomainUserDao.findAll();
			for(FenixDomainUser du : dus) {
				for(CodeVo n : name) {
					if( du.getFirstName().endsWith(n.getCode()) && du.getLastName().equals(n.getLabel()) ) {
						UserVO user = new UserVO();
						user.setId(du.getId());
						user.setFirstName(du.getFirstName());
						user.setLastName(du.getLastName());
						users.add(user);
						break;
					}
				}
			}
		
		return users;
	}


	public void deleteWork(WorkflowInfoVO workflowInfoVO) {
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Long workflowId = workflowInfoVO.getWorkflowId();
		/** delete existing  workflow **/
		ipcDao.deleteIPCProvinceWorkflow(du.getId(), workflowId);

	}
	
	public void saveWork(WorkflowInfoVO workflowInfoVO, ProvinceVO provinceVO) {
		
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Long workflowId = workflowInfoVO.getWorkflowId();
		/** delete existing  workflow **/
		IPCProvincesInfo ipcProvinceInfo = new IPCProvincesInfo();
		ipcProvinceInfo.setContributor(du);
		ipcProvinceInfo.setWorkflowID(workflowInfoVO.getWorkflowId());
		ipcProvinceInfo.setIsEmpty(true);
		ipcProvinceInfo.setPeriod(workflowInfoVO.getPeriod());
		ipcProvinceInfo.setProvinceCode(provinceVO.getProvinceCode());
		ipcProvinceInfo.setProvinceLabel(provinceVO.getProvinceLabel());
		ipcProvinceInfo.setXml(provinceVO.getXml());
		saveDao.save(ipcProvinceInfo);
		
	
	}


	public Long getWorkflowID(WorkflowInfoVO workflowInfoVO) {
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		
		return ipcDao.getWorkflowID(du.getId(), workflowInfoVO.getGeographicArea().getCode(), workflowInfoVO.getDate());
	}

	private static String getXmlString() throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        String configPath = Setting.getSystemPath() + File.separator + "ipc";
        
        BufferedReader reader = new BufferedReader(
                new FileReader(configPath + File.separator + "IPC.xml"));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

	
	public String getXml(Long workId) {
		return ipcDao.getXml(workId);
	}

	
	public Boolean isModerator(Long workflowId) {
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		return ipcDao.isModerator(du.getId(), workflowId);
	}

	


	public List<UserVO> getContributors(Long workflowId) {
		List<FenixDomainUser> contributors = ipcDao.getContributors(workflowId);
		List<UserVO> usersVO = new ArrayList<UserVO>();
		LOGGER.info("Contributors: " + contributors.size());
		for(FenixDomainUser user : contributors) {
			UserVO userVO = new UserVO();
			userVO.setId(user.getId());
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
			userVO.setFirstAndLastName(user.getFirstName() + " " + user.getLastName());
			usersVO.add(userVO);
		}
		
		return usersVO;
	}
	
	public List<UserVO> getObservers(Long workflowId) {
//		List<FenixDomainUser> contributors = ipcDao.getObservers(workflowId);
		List<UserVO> usersVO = new ArrayList<UserVO>();

//		for(FenixDomainUser user : contributors) {
//			UserVO userVO = new UserVO();
//			userVO.setId(user.getId());
//			userVO.setFirstName(user.getFirstName());
//			userVO.setLastName(user.getLastName());
//			userVO.setFirstAndLastName(user.getFirstName() + " " + user.getLastName());
//			usersVO.add(userVO);
//		}
		
		return usersVO;
	}

	
	public List<WorkflowInfoVO> getModeratorsWorkflows(String countryCode) {
		List<IPCWorkflowInfo> workflows = ipcDao.findAllWorkflows(countryCode);
		List<WorkflowInfoVO> ws = new ArrayList<WorkflowInfoVO>();
		for(IPCWorkflowInfo workflow : workflows) {
//			List<IPCProvincesInfo> provinces = ipcDao.getUserProvinces(workflow.getModerator().getId(), workflow.getId());		
//				for(IPCProvincesInfo province : provinces) {
					WorkflowInfoVO w = new WorkflowInfoVO();
					w.setWorkflowId(workflow.getId());
					w.setWorkflowName(workflow.getName());
					w.setDescription(workflow.getDescription());
					w.setDate(workflow.getDate());
					w.setPeriod(workflow.getPeriod());
					w.setModerator_id(workflow.getModerator().getId());
					w.setModeratorFirstName(workflow.getModerator().getFirstName());
					w.setModeratorLastName(workflow.getModerator().getLastName());			
					w.setReferenceLayer(new IPCCodesVO(workflow.getReferenceLayerCode(), workflow.getReferenceLayerLabel()));
					w.setStatus(new IPCCodesVO(workflow.getStatusCode(), workflow.getStatusLabel()));
					w.setGeographicArea(new IPCCodesVO(workflow.getGeographicAreaCode(), workflow.getGeographicAreaLabel()));
					
//					List<ProvinceVO> ps = new ArrayList<ProvinceVO>();
//					ProvinceVO p = new ProvinceVO();
//					p.setId(province.getId());
//					p.setContributor_id(province.getContributor().getId());
//					p.setContributor_firstName(province.getContributor().getFirstName());
//					p.setContributor_lastName(province.getContributor().getLastName());
//					p.setProvinceCode(province.getProvinceCode());
//					p.setProvinceLabel(province.getProvinceLabel());
//					p.setXml(province.getXml());
//					ps.add(p);
					
//					w.setProvinces(ps);
					ws.add(w);
//				}
		}
		return ws;
	}

	
	public Boolean deleteIPCWorkflow(Long workflowId) {
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		Boolean isModerator = isModerator(workflowId);
		if ( isModerator) {
			ipcDao.deleteIPCWorkflow(workflowId);
			return true;
		}
		return false;	
	}

	
	public Boolean saveWorkflowInformations(WorkflowInfoVO newWflowInfoVO, WorkflowInfoVO workflowInfoVO) {
		IPCBean ipcBean = WF2IPCbean(newWflowInfoVO);
		filterProvinces(ipcBean, newWflowInfoVO, workflowInfoVO);
		filterContributors(ipcBean, newWflowInfoVO, workflowInfoVO);
		filterObservers(ipcBean, newWflowInfoVO, workflowInfoVO);
	
		
		String xml = new String();
		try {
			xml = getXmlString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ipcBean.setXml(xml);			
		
		ipcDao.updateWorkflowInformations(ipcBean);	
		
		return null;
	}
	
	
		
	private void filterProvinces(IPCBean ipcBean, WorkflowInfoVO newWflowInfoVO, WorkflowInfoVO workflowInfoVO){
		List<IPCProvinceBean> addedProvinces = new ArrayList<IPCProvinceBean>();
		List<IPCProvinceBean> removedProvinces = new ArrayList<IPCProvinceBean>();
		List<IPCProvinceBean> currentProvinces = new ArrayList<IPCProvinceBean>();
		for(ProvinceVO province : newWflowInfoVO.getProvinces()) {	
			Boolean check = false;
			for(ProvinceVO oldProvince : workflowInfoVO.getProvinces()) {
				if( oldProvince.getProvinceCode().equals(province.getProvinceCode())) {
					check = true;
					break;
				}
			}
			if ( !check ) {
				addedProvinces.add(new IPCProvinceBean(province.getProvinceCode(), province.getProvinceLabel()));
			}
		}
		
		for(ProvinceVO oldProvince : workflowInfoVO.getProvinces()) {
			Boolean check = false;
			IPCProvinceBean p = new IPCProvinceBean();
			currentProvinces.add(new IPCProvinceBean(oldProvince.getProvinceCode(), oldProvince.getProvinceLabel()));			
			for(ProvinceVO province : newWflowInfoVO.getProvinces()) {
				if( province.getProvinceCode().equals(oldProvince.getProvinceCode())) {
					check = true;
					break;
				}
			}
			if ( !check ) {
				removedProvinces.add(new IPCProvinceBean(oldProvince.getProvinceCode(), oldProvince.getProvinceLabel()));
			}
		}
		LOGGER.info("Provinces to add: " + addedProvinces.size());
		LOGGER.info("Provinces to remove: " + removedProvinces.size());
		LOGGER.info("Current Provinces : " + currentProvinces.size());
		
		ipcBean.setAddedProvince(addedProvinces);
		ipcBean.setRemovedProvince(removedProvinces);
		ipcBean.setCurrentProvinces(currentProvinces);
	}
		
	private void filterContributors(IPCBean ipcBean, WorkflowInfoVO newWflowInfoVO, WorkflowInfoVO workflowInfoVO){
		List<Long> added = new ArrayList<Long>();
		List<Long> removed = new ArrayList<Long>();
		for(UserVO user : newWflowInfoVO.getContributors()) {
			Boolean check = false;
			for(UserVO oldUser : workflowInfoVO.getContributors()) {
				if( oldUser.getId() == user.getId()) {
					check = true;
					break;
				}
			}
			if ( !check )
				added.add(user.getId());
		}
		
		for(UserVO oldUser : workflowInfoVO.getContributors()) {
			Boolean check = false;
			for(UserVO user : newWflowInfoVO.getContributors()) {
				if( oldUser.getId() == user.getId()) {
					check = true;
					break;
				}
			}
			if ( !check )
				removed.add(oldUser.getId());
		}
		LOGGER.info("Contributors to add: " + added);
		LOGGER.info("Contributors to remove: " + removed);
		
		ipcBean.setAddedContributors(added);
		ipcBean.setRemovedContributors(removed);
	}
	
	private void filterObservers(IPCBean ipcBean, WorkflowInfoVO newWflowInfoVO, WorkflowInfoVO workflowInfoVO){
		List<Long> added = new ArrayList<Long>();
		List<Long> removed = new ArrayList<Long>();
		for(UserVO user : newWflowInfoVO.getObservers()) {
			Boolean check = false;
			for(UserVO oldUser : workflowInfoVO.getObservers()) {
				if( oldUser.getId() == user.getId()) {
					check = true;
					break;
				}
			}
			if ( !check )
				added.add(user.getId());
		}
		
		for(UserVO oldUser : workflowInfoVO.getObservers()) {
			Boolean check = false;
			for(UserVO user : newWflowInfoVO.getObservers()) {
				if( oldUser.getId() == user.getId()) {
					check = true;
					break;
				}
			}
			if ( !check )
				removed.add(oldUser.getId());
		}
		LOGGER.info("Observers to add: " + added);
		LOGGER.info("Observers to remove: " + removed);
		
		ipcBean.setAddedObservers(added);
		ipcBean.setRemovedObservers(removed);
	}

	private IPCBean WF2IPCbean(WorkflowInfoVO wf){
		IPCBean bean = new IPCBean();
		bean.setWorkflowId(wf.getWorkflowId());
		bean.setGeographicAreaCode(wf.getGeographicArea().getCode());
		bean.setGeographicAreaLabel(wf.getGeographicArea().getLabel());
		bean.setReferenceLayerCode(wf.getReferenceLayer().getCode());
		bean.setReferenceLayerLabel(wf.getReferenceLayer().getLabel());
		bean.setDescription(wf.getDescription());
		bean.setName(wf.getWorkflowName());
		bean.setStatusCode(wf.getStatus().getCode());
		bean.setStatusLabel(wf.getStatus().getLabel());
		bean.setPeriod(wf.getPeriod());
		return bean;
	}
	
	
	
	public void setSaveDao(SaveDao saveDao) {
		this.saveDao = saveDao;
	}
	
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}
	
	public void setFileFactory(FileFactory fileFactory) {
		this.fileFactory = fileFactory;
	}

	public void setDatasetImporter(DatasetImporter datasetImporter) {
		this.datasetImporter = datasetImporter;
	}
	
	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}
	
	public void setMapDao(MapDao mapDao) {
		this.mapDao = mapDao;
	}

	public void setProjectedDataDao(ProjectedDataDao projectedDataDao) {
		this.projectedDataDao = projectedDataDao;
	}

	public void setGeoViewDao(GeoViewDao geoViewDao) {
		this.geoViewDao = geoViewDao;
	}
	
	public void setProjectedDataMetadao(ProjectedDataMetadao projectedDataMetadao) {
		this.projectedDataMetadao = projectedDataMetadao;
	}
	
	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}
	
	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}
	
	public void setIpcDao(IPCDao ipcDao) {
		this.ipcDao = ipcDao;
	}
}
