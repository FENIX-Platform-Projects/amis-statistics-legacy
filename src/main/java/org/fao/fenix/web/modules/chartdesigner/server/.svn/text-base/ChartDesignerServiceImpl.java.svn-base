package org.fao.fenix.web.modules.chartdesigner.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.chartdesigner.ChartDesign;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerParameters;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.chartdesigner.ChartDesignerDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.persistence.security.ResourcePermission;
import org.fao.fenix.core.utils.ChartDesignerExporter;
import org.fao.fenix.core.utils.ChartDesignerUtils;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerService;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.springframework.core.io.Resource;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ChartDesignerServiceImpl extends RemoteServiceServlet implements ChartDesignerService {

	private final static Logger LOGGER = Logger.getLogger(ChartDesignerServiceImpl.class);

	private String dir;
	
	private ChartDesignerUtils chartDesignerUtils;
	
	private ChartDesignerDao chartDesignerDao;
	
	private FenixPermissionManager fenixPermissionManager;
	
	private ChartDesignUpdater chartDesignUpdater;
	
	private ChartDesignerExporter chartDesignerExporter;
	
	private UrlFinder urlFinder;
	
	public ChartDesignerServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public List<String> findSavedCodes(Long datasetID, Long chartDesignID, String contentDescriptor) throws FenixGWTException {
		try {
			if (datasetID == null) {
				return chartDesignerDao.findSavedCodesFromX(datasetID, chartDesignID, contentDescriptor);
			} else {
				return chartDesignerDao.findSavedCodesFromY(datasetID, chartDesignID, contentDescriptor);
			}
		} catch (Exception e) {
			LOGGER.info(">>>>>>>>>> EXCEPTION <<<<<<<<<<");
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long save(List<ChartDesignerParametersVO> vos) throws FenixGWTException {
		try {
			List<ChartDesignerParameters> ps = convertParameters(vos);
			Long chartDesignID = chartDesignerUtils.save(ps);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, chartDesignID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, chartDesignID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, chartDesignID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, chartDesignID, true);
			String filename = chartDesignID + ".png";
			String imagePath = dir + File.separator + filename;
			chartDesignerUtils.createChart(imagePath, ps, false, null);
			for (ChartDesignerParameters p : ps)
				p.setImagePath(filename);
			chartDesignerUtils.update(ps);
			return chartDesignID;
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public String addToReport(List<ChartDesignerParametersVO> vos) throws FenixGWTException {
		try {
			List<ChartDesignerParameters> ps = convertParameters(vos);
			Long chartDesignID = chartDesignerUtils.save(ps);
			String filename = chartDesignID + ".png";
			String imagePath = dir + File.separator + filename;
			chartDesignerUtils.createChart(imagePath, ps, false, null);
			for (ChartDesignerParameters p : ps)
				p.setImagePath(filename);
			chartDesignerUtils.update(ps);
			imagePath = urlFinder.webRoot + getImagePath(ps);
			return chartDesignerUtils.createIMGTag(chartDesignID, imagePath, ps.get(0).getImageWidth(), ps.get(0).getImageHeight());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long update(Long chartDesignID, List<ChartDesignerParametersVO> vos) throws FenixGWTException {
		try {
			List<ChartDesignerParameters> ps = convertParameters(vos);
			chartDesignerUtils.update(chartDesignID, ps);
			String filename = chartDesignID + ".png";
			String imagePath = dir + File.separator + filename;
			chartDesignerUtils.createChart(imagePath, ps, false, null);
			for (ChartDesignerParameters p : ps)
				p.setImagePath(filename);
			chartDesignerUtils.update(ps);
			return chartDesignID;
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void recreateCharts() throws FenixGWTException {
		try {
			chartDesignUpdater.recreateCharts();
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	// TODO implementation missing
	public String openAsOLAP(List<ChartDesignerParametersVO> p) throws FenixGWTException {
		return null;
	}
	
	public String exportAsExcel(List<ChartDesignerParametersVO> vos) throws FenixGWTException {
		try {
			List<ChartDesignerParameters> ps = convertParameters(vos);
			return chartDesignerExporter.exportAsExcel(dir, ps);
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	// TODO implementation missing
	public List<String> exportAsCSV(List<ChartDesignerParametersVO> vos) throws FenixGWTException {
		try {
			List<ChartDesignerParameters> ps = convertParameters(vos);
			return chartDesignerExporter.exportAsCSV(dir, ps);
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Map<String, String> exportToWebsite(Long chartDesignID) throws FenixGWTException {
		try {
			Map<String, String> map = new HashMap<String, String>();
			ChartDesign cd = chartDesignerUtils.findByID(chartDesignID);
			String imagePath = urlFinder.webRoot + getImagePath(cd.getParameters());
			int width = cd.getParameters().get(0).getImageWidth();
			int height = cd.getParameters().get(0).getImageHeight();
			map.put("IMG", chartDesignerUtils.createIMGTag(chartDesignID, imagePath, width, height));
			map.put("IFRAME", chartDesignerUtils.createIFRAMETag(chartDesignID, imagePath));
			map.put("WIDTH", String.valueOf(width)); 
			map.put("HEIGHT", String.valueOf(height));
			return map;
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private String getImagePath(List<ChartDesignerParameters> vos) {
		for (ChartDesignerParameters vo : vos) 
			return "exportObject/" + vo.getImagePath();
		return null;
	}
	
	public List<ChartDesignerParametersVO> load(Long chartDesignID) throws FenixGWTException {
		try {
			List<ChartDesignerParametersVO> parameters = new ArrayList<ChartDesignerParametersVO>();
			ChartDesign cd = chartDesignerUtils.findByID(chartDesignID);
			for (ChartDesignerParameters p : cd.getParameters())
				parameters.add(VOConverter.parameters2VO(p));
			return parameters;
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public FenixResourceVo getNewOlapResource(Long id) throws FenixGWTException {
		try {
			ChartDesign cd = chartDesignerUtils.findByID(id);
			FenixResourceVo fenixResource = null;
			PermissionVo permissionVo = getPermissions(id);
			if (cd != null) 
				fenixResource = FenixResourceBuilder.build(cd, permissionVo);
			return fenixResource;
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private PermissionVo getPermissions(long resourceId) {
		PermissionVo vo = new PermissionVo();
		ResourcePermission permission = fenixPermissionManager.getPermissions(resourceId);
		vo.setCanRead(permission.isHasReadPermission());
		vo.setCanWrite(permission.isHasWritePermission());
		vo.setCanDelete(permission.isHasDeletePermission());
		vo.setCanDownload(permission.isHasDownloadPermission());
		return vo;
	}
	
	public String calculateAxisStep(List<Long> datasetIDs) throws FenixGWTException {
		try {
			return chartDesignerUtils.calculateAxisStep(datasetIDs);
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String viewChart(List<ChartDesignerParametersVO> vos) throws FenixGWTException {
		try {
			String filename = UUID.randomUUID().toString() + ".png";
			String filepath = dir + File.separator + filename;
			List<ChartDesignerParameters> ps = convertParameters(vos);
			chartDesignerUtils.createChart(filepath, ps, false, null);
			return filename;
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
			l.add(chartDesignerDao.findDataset(id));
		return l;
	}
	
	public ResourceViewVO loadDataset(List<ResourceChildModel> models, String langCode) throws FenixGWTException {
		ResourceViewVO rvvo;
		try {
			rvvo = new ResourceViewVO();
//			LOGGER.info("chartDesignerUtils? " + (chartDesignerUtils == null));
//			LOGGER.info(models.size() + " models");
			for (ResourceChildModel m : models) {
				Dataset dataset = chartDesignerUtils.findDataset(Long.valueOf(m.getId()));
				DatasetVO datasetVO = VOConverter.dataset2vo(dataset, true);
//				LOGGER.info(datasetVO.getDescriptorViews().size() + " datasetVO.getDescriptorViews()");
				for (DescriptorViewVO descriptor : datasetVO.getDescriptorViews()) {
					if (!descriptor.getContentDescriptor().contains("quantity")) {
						if (descriptor.getContentDescriptor().contains("date") || descriptor.getContentDescriptor().contains("Date")) {
							List<UniqueDateValues> dateValues = chartDesignerUtils.findUniqueDateValues(dataset.getResourceId(), descriptor.getHeader(), langCode);
							for (UniqueDateValues d : dateValues)
								descriptor.addUniqueValue(VOConverter.uniqueDateValue2vo(d));
						} else {
							List<UniqueTextValues> textValues = chartDesignerUtils.findUniqueTextValues(dataset.getResourceId(), descriptor.getHeader(), langCode);
							for (UniqueTextValues t : textValues)
								descriptor.addUniqueValue(VOConverter.uniqueTextValue2vo(t));
						}
					}
					
				}
				rvvo.addDataset(datasetVO);
			}
		} catch (NumberFormatException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return rvvo;
	}
	
	public List<UniqueValueVO> loadValues(List<Long> datasetIDs, List<DescriptorViewVO> descriptorViewVOs, String langCode) throws FenixGWTException {
		
		List<UniqueValueVO> vos = new ArrayList<UniqueValueVO>();
		
		List<String> textHeaders = new ArrayList<String>();
		List<String> dateHeaders = new ArrayList<String>();
		
		for (DescriptorViewVO vo : descriptorViewVOs) {
			if (vo.getContentDescriptor().contains("date") || vo.getContentDescriptor().contains("Date")) {
				dateHeaders.add(vo.getHeader());
			} else {
				textHeaders.add(vo.getHeader());
			}
		}
		
		if (!textHeaders.isEmpty()) {
			List<UniqueTextValues> textValues = chartDesignerUtils.findUniqueTextValues(datasetIDs, textHeaders, langCode);
			for (UniqueTextValues t : textValues)
				vos.add(VOConverter.uniqueTextValue2vo(t));
		}
		
		if (!dateHeaders.isEmpty()) {
			List<UniqueDateValues> dateValues = chartDesignerUtils.findUniqueDateValues(datasetIDs, dateHeaders);
			for (UniqueDateValues d : dateValues)
				vos.add(VOConverter.uniqueDateValue2vo(d));
		}
		
		return vos;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setChartDesignerUtils(ChartDesignerUtils chartDesignerUtils) {
		this.chartDesignerUtils = chartDesignerUtils;
	}

	public void setChartDesignerDao(ChartDesignerDao chartDesignerDao) {
		this.chartDesignerDao = chartDesignerDao;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

	public void setChartDesignUpdater(ChartDesignUpdater chartDesignUpdater) {
		this.chartDesignUpdater = chartDesignUpdater;
	}

	public void setChartDesignerExporter(ChartDesignerExporter chartDesignerExporter) {
		this.chartDesignerExporter = chartDesignerExporter;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

}