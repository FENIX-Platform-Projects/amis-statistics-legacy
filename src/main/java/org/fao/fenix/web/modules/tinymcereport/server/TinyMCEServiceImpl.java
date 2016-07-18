package org.fao.fenix.web.modules.tinymcereport.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.chartdesigner.ChartDesign;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerParameters;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.domain.tinymce.TinyMCEReport;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.chartdesigner.ChartDesignerDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.utils.ChartDesignerUtils;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.core.utils.MTSaver;
import org.fao.fenix.core.utils.PropertiesReader;
import org.fao.fenix.core.utils.TinyMCEUtils;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.chartdesigner.server.VOConverter;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.tinymcereport.common.services.TinyMCEService;
import org.springframework.core.io.Resource;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TinyMCEServiceImpl extends RemoteServiceServlet implements TinyMCEService {

	private final static Logger LOGGER = Logger.getLogger(TinyMCEServiceImpl.class);
	
	private TinyMCEUtils tinyMCEUtils;
	
	private UrlFinder urlFinder;
	
	private FenixPermissionManager fenixPermissionManager;
	
	private String dir;
	
	private String libJSDir;
	
	private ChartDesignerUtils chartDesignerUtils;
	
	private MTSaver mtSaver;
	
	private PropertiesReader propertiesReader;
	
	public TinyMCEServiceImpl(Resource exportDirectory, Resource libJSDir) {
		try {
			setExportDir(exportDirectory.getFile());
			setLibJSDir(libJSDir.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public String freeze(Long id, String resourceType, Date date) throws FenixGWTException {
		if (resourceType.equalsIgnoreCase("CHART")) {
			return freezeChart(id, resourceType, date);
		} else if (resourceType.equalsIgnoreCase("OLAP")) {
			return freezeOLAP(id, resourceType, date);
		} else {
			throw new FenixGWTException(resourceType + " is not a valid resource type.");
		}
	}
	
	public String freezeChart(Long chartDesignID, String resourceType, Date date) throws FenixGWTException {
		try {
			
			// 1) find chart by id
			ChartDesign cd = chartDesignerUtils.findByID(chartDesignID);
			int width = cd.getParameters().get(0).getImageWidth();
			int height = cd.getParameters().get(0).getImageHeight();
		
			// 2) params 2 vos
			List<ChartDesignerParametersVO> vos = new ArrayList<ChartDesignerParametersVO>();
			for (ChartDesignerParameters p : cd.getParameters())
				vos.add(VOConverter.parameters2VO(p));
			
			// 3) edit vos
//			for (ChartDesignerParametersVO vo : vos) {
//				vo.setxUseFromDateToDate(true);
//				vo.setxUseMostRecentData(false);
//				vo.setxFromDate(FieldParser.parseDate("1800-01-01"));
//				vo.setxToDate(date);
//			}
			
			// 4) vos to params
			List<ChartDesignerParameters> ps = convertParameters(vos);
			
			// 5) save new chart
			Long newChartDesignID = chartDesignerUtils.save(ps);
			
			// 6) create image
			String filename = newChartDesignID + ".png";
			String imagePath = dir + File.separator + filename;
			chartDesignerUtils.createChart(imagePath, ps, false, null);
			LOGGER.info("imagePath? " + imagePath);
			
			// 7) set path in params
			for (ChartDesignerParameters p : ps) {
				p.setImagePath(filename);
				p.setxUseFromDateToDate(true);
				p.setxUseMostRecentData(false);
				p.setxFromDate(FieldParser.parseDate("1800-01-01"));
				p.setxToDate(date);
			}
			
			// 8) update params
			chartDesignerUtils.update(ps);
			
			// 9) create tag
			String serverIP = propertiesReader.read("devel.properties", "serverip");
			String port = propertiesReader.read("devel.properties", "port");
			String imgURL = "http://" + serverIP + ":" + port + "/fenix-web/exportObject/" + newChartDesignID + ".png";
			String tag = chartDesignerUtils.createIMGTag(newChartDesignID, imgURL, width, height);
			LOGGER.info("TAG: " + tag);
			
			// return HTML tag
			return tag;
			
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
		
	}
	
	private List<ChartDesignerParameters> convertParameters(List<ChartDesignerParametersVO> vos) {
		List<ChartDesignerParameters> ps = new ArrayList<ChartDesignerParameters>();
		for (ChartDesignerParametersVO vo : vos) {
			ChartDesignerParameters p = VOConverter.vo2parameters(vo);
			List<Dataset> datasources = findDatasources(vo);
			p.setDatasourceIDs(datasources);
			ps.add(p);
		}
		return ps;
	}
	
	private List<Dataset> findDatasources(ChartDesignerParametersVO vo) {
		List<Dataset> l = new ArrayList<Dataset>();
		for (Long id : vo.getDatasourceIDs())
			l.add(chartDesignerUtils.findDataset(id));
		return l;
	}
	
	public String freezeOLAP(Long id, String resourceType, Date date) throws FenixGWTException {
		try {
			return "TODO";
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Map<String, String> findAllTemplates() throws FenixGWTException {
		try {
			return tinyMCEUtils.findAllTemplates();
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String export(String html, String format) throws FenixGWTException {
		try {
			return tinyMCEUtils.export(dir, libJSDir, html, format);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void deleteGhostResources(Map<String, List<Long>> resourceTypeIDsMap) throws FenixGWTException {
		try {
			tinyMCEUtils.deleteGhostResources(resourceTypeIDsMap);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String load(Long tinyMCEID) throws FenixGWTException {
		try {
			return tinyMCEUtils.load(tinyMCEID);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long save(String html, Map<String, List<ChartDesignerParametersVO>> charts, Map<String, OLAPParametersVo> olaps) throws FenixGWTException {
		try {
			return null;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long save(Long reportID, String html, boolean isTemplate) throws FenixGWTException {
		try {
			TinyMCEReport report = tinyMCEUtils.find(reportID);
			html = tinyMCEUtils.replaceShortURLs(html, fenixBaseURL());
			report.setHtml(html);
			report.setIsTemplate(isTemplate);
			return tinyMCEUtils.update(report);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long save(String html, boolean isTemplate) throws FenixGWTException {
		try {
			html = tinyMCEUtils.replaceShortURLs(html, fenixBaseURL());
			if (isTemplate) {
				html = tinyMCEUtils.replaceObjectsForTemplate(html, fenixBaseURL());
			}
			TinyMCEReport r = new TinyMCEReport();
			r.setHtml(html);
			r.setIsTemplate(isTemplate);
			Long reportID = tinyMCEUtils.save(r);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, reportID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, reportID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, reportID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, reportID, true);
			return reportID;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public String fenixBaseURL() {
		return "http://" + urlFinder.serverip + ":" + urlFinder.port + "/fenix-web";
	}

	public void setTinyMCEUtils(TinyMCEUtils tinyMCEUtils) {
		this.tinyMCEUtils = tinyMCEUtils;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}
	
	public void setExportDir(File dir) {
		this.dir = dir.getPath();
	}
	
	public void setLibJSDir(File dir) {
		this.libJSDir = dir.getPath();
	}

	public void setChartDesignerUtils(ChartDesignerUtils chartDesignerUtils) {
		this.chartDesignerUtils = chartDesignerUtils;
	}

	public void setMtSaver(MTSaver mtSaver) {
		this.mtSaver = mtSaver;
	}

	public void setPropertiesReader(PropertiesReader propertiesReader) {
		this.propertiesReader = propertiesReader;
	}
	
}