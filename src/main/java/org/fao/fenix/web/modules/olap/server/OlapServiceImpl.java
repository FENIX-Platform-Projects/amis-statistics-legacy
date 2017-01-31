/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.olap.server;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.coding.CodingHierarchy;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.CQLFilter;
import org.fao.fenix.core.domain.map.cql.CQLFilterOp;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.olap.OLAPBean;
import org.fao.fenix.core.domain.olap.OLAPFilter;
import org.fao.fenix.core.domain.olap.OLAPMapBean;
import org.fao.fenix.core.domain.olap.OLAPParameters;
import org.fao.fenix.core.domain.resourceview.BooleanResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.DescriptorView;
import org.fao.fenix.core.domain.resourceview.NumericResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.resourceview.ResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.StringResourceViewSetting;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.domain.udtable.UDTable;
import org.fao.fenix.core.domain.udtable.UDTableCell;
import org.fao.fenix.core.domain.udtable.UDTableFilter;
import org.fao.fenix.core.domain.udtable.UDTableRow;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.domain.udtable.UniqueValue;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.olap.OLAPExcel;
import org.fao.fenix.core.olap.OLAPUpdater;
import org.fao.fenix.core.olap.chart.OLAPChartConfiguration;
import org.fao.fenix.core.olap.chart.OLAPChartFactory;
import org.fao.fenix.core.olap.chart.OLAPChartType;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.olap.FieldParserComparator;
import org.fao.fenix.core.persistence.olap.OLAPCss;
import org.fao.fenix.core.persistence.olap.OLAPDao;
import org.fao.fenix.core.persistence.olap.OLAPDaoUtils;
import org.fao.fenix.core.persistence.olap.OLAPHtml;
import org.fao.fenix.core.persistence.olap.OLAPHtmlParser;
import org.fao.fenix.core.persistence.olap.OLAPJDBC;
import org.fao.fenix.core.persistence.resourceview.ResourceViewDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.persistence.security.ResourcePermission;
import org.fao.fenix.core.persistence.udtable.UDTableDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.core.utils.GaulUtils;
import org.fao.fenix.core.utils.MTSaver;
import org.fao.fenix.map.geoserver.io.GeoserverIO;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.mapretriever.ImageMerger;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.map.quickmap.DBFLGeometryResolver;
import org.fao.fenix.map.quickmap.GeoPairImpl;
import org.fao.fenix.map.quickmap.LayerValuesProviderImpl;
import org.fao.fenix.map.quickmap.QuickFeatureBuilder;
import org.fao.fenix.map.quickmap.QuickFeatureCollection;
import org.fao.fenix.map.quickmap.QuickMap;
import org.fao.fenix.map.quickmap.QuickMap.Range;
import org.fao.fenix.map.quickmap.iface.FeatureBuilder;
import org.fao.fenix.map.quickmap.iface.GeoPair;
import org.fao.fenix.map.quickmap.iface.GeometryResolver;
import org.fao.fenix.map.quickmap.iface.LayerValuesProvider;
import org.fao.fenix.map.style.TextSymbUtil;
import org.fao.fenix.map.util.ColourUtil;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.server.utils.CodingVoConverter;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.fsatmis.common.vo.FSATMISModelData;
import org.fao.fenix.web.modules.fsatmis.server.utils.FSATMISTableCacheData;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.services.OlapService;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.olap.server.birt.CreateReport;
import org.fao.fenix.web.modules.olap.server.utils.OLAPUtils;
import org.jfree.chart.plot.PlotOrientation;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class OlapServiceImpl extends RemoteServiceServlet implements
		OlapService {

	private OLAPDao olapDao;

	private MTSaver mtSaver;

	private UDTableDao udtableDao;

	private DatasetDao datasetDao;

	private ResourceViewDao resourceViewDao;

	private GwtConnector gwtConnector;

	private LayerGaulUtils layerGaulUtils;

	private WMSMapProviderDao wmsMapProviderDao;

	private FenixPermissionManager fenixPermissionManager;

	private OLAPJDBC olapJdbc;

	private OLAPUpdater olapUpdater;

	private UrlFinder urlFinder;

	final private static int HEIGHT = 500;

	final private static int WIDTH = 600;

	private final static Logger LOGGER = Logger
			.getLogger(OlapServiceImpl.class);

	private Map<Long, List<FSATMISModelData>> tableMap = FSATMISTableCacheData
			.getTableCache();

	/* *************************************************************************************************************** */
	/* *************************************************************************************************************** */
	/*
	 * ************************************************** LOAD OLAP
	 * **************************************************
	 */
	/* *************************************************************************************************************** */
	/* *************************************************************************************************************** */

	@SuppressWarnings("static-access")
	public String getIFrame(Long resourceViewID) throws FenixGWTException {
		Map<String, String> measures = mtSaver.measureOLAP(resourceViewID);
		String iframe = "<iframe style=\"border: 3px solid #000000;\" id='OLAP_"
				+ resourceViewID + "' src=\"";
		iframe += "http://" + urlFinder.serverip + ":" + urlFinder.port;
		iframe += "/fenix-web/OLAP/";
		iframe += String.valueOf(resourceViewID);
		iframe += ".html\" width=\""
				+ measures.get("width")
				+ "\" height=\""
				+ measures.get("height")
				+ "\" scrolling=\"no\">Your browser does not support iframes.</iframe>";
		return iframe;
	}

	public String getHTML(Long resourceViewID) throws FenixGWTException {
		try {
			return mtSaver.getHTML(resourceViewID);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public List<DatasetVO> loadOlap(Long resourceViewVO) {
		List<DatasetVO> vos = new ArrayList<DatasetVO>();
		ResourceView r = resourceViewDao.findByID(resourceViewVO);
		for (Dataset d : r.getDatasets())
			vos.add(dataset2vo(d, true));
		return vos;
	}

	public OLAPParametersVo load(Long mtID) throws FenixGWTException {
		OLAPParameters p = mtSaver.loadMT(mtID);
		OLAPParametersVo opvo=OLAPUtils.parameters2vo(p);
		return opvo;
	}

	public ResourceViewVO loadOlapUserSettings(Long resourceViewVO) {
		ResourceView r = resourceViewDao.findByID(resourceViewVO);
		return resourceView2vo(r);
	}

	private ResourceViewVO resourceView2vo(ResourceView r) {
		ResourceViewVO vo = new ResourceViewVO();
		vo.setTitle(r.getTitle());
		vo.setOlapHTML(r.getOlapHTML());
		vo.setOlapFunction(r.getOlapFunction());
		vo.setResourceId(r.getResourceId());
		for (Dataset d : r.getDatasets())
			vo.addDataset(dataset2vo(d, false));
		for (DescriptorView dv : r.getDescriptorViews())
			vo.addDescriptorView(descriptor2vo(dv));
		for (ResourceViewSetting s : r.getSettings())
			vo.addResourceViewSetting(setting2vo(s));
		return vo;
	}

	@SuppressWarnings("deprecation")
	private DescriptorViewVO descriptor2vo(DescriptorView d) {
		DescriptorViewVO vo = new DescriptorViewVO();
		vo.setAxis(d.getAxis());
		vo.setContentDescriptor(d.getContentDescriptor());
		for (Dataset dataset : d.getDatasets()) {
			vo.addDataset(dataset2vo(dataset, false));
			List<UniqueTextValues> uts = findDescriptorViewUniqueTextValues(d);
			for (UniqueTextValues ut : uts)
				vo.addUniqueValue(uniqueTextValue2vo(ut));
			List<UniqueDateValues> uds = findDescriptorViewUniqueDateValues(d);
			for (UniqueDateValues ud : uds)
				vo.addUniqueValue(uniqueDateValue2vo(ud));
		}
		vo.setHeader(d.getHeader());
		vo.setIsPlotDimension(d.getIsPlotDimension());
		return vo;
	}

	@SuppressWarnings("deprecation")
	private List<UniqueDateValues> findDescriptorViewUniqueDateValues(
			DescriptorView d) {
		List<UniqueDateValues> udvs = new ArrayList<UniqueDateValues>();
		// olapJdbc.openConnection();
		// List<Long> ids = olapJdbc.findUniqueDateValue(d.getHeader(),
		// d.getContentDescriptor());
		// olapJdbc.closeConnection();
		List<Long> ids = olapDao.findUniqueDateValue(d.getId());
		// LOGGER.info(ids.size() + " ids found");
		udvs = resourceViewDao.findUniqueDateValues(ids, "EN");
		// LOGGER.info(udvs.size() + " udvs found");
		return udvs;
	}

	@SuppressWarnings("deprecation")
	private List<UniqueTextValues> findDescriptorViewUniqueTextValues(
			DescriptorView d) {
		List<UniqueTextValues> udvs = new ArrayList<UniqueTextValues>();
		// olapJdbc.openConnection();
		// List<Long> ids = olapJdbc.findUniqueTextValue(d.getHeader(),
		// d.getContentDescriptor());
		// olapJdbc.closeConnection();
		List<Long> ids = olapDao.findUniqueTextValue(d.getId());
		// LOGGER.info(ids.size() + " ids found");
		udvs = resourceViewDao.findUniqueTextValues(ids, "EN");
		// LOGGER.info(udvs.size() + " udvs found");
		return udvs;
	}

	@SuppressWarnings("deprecation")
	private DescriptorViewVO descriptor2vo(Descriptor d) {
		DescriptorViewVO vo = new DescriptorViewVO();
		vo.setContentDescriptor(d.getContentDescriptor());
		vo.setHeader(d.getHeader());
		return vo;
	}

	private ResourceViewSettingVO setting2vo(ResourceViewSetting r) {
		ResourceViewSettingVO vo = new ResourceViewSettingVO();
		vo.setId(r.getId());
		vo.setSettingName(r.getSettingName());
		if (r instanceof BooleanResourceViewSetting) {
			vo.setValue(String.valueOf(((BooleanResourceViewSetting) r)
					.isSelected()));
		} else if (r instanceof NumericResourceViewSetting) {
			vo.setValue(String.valueOf(((NumericResourceViewSetting) r)
					.getQuantity().intValue()));
		} else if (r instanceof StringResourceViewSetting) {
			vo.setValue(String.valueOf(((StringResourceViewSetting) r)
					.getValue()));
		}
		return vo;
	}

	private UniqueValueVO uniqueTextValue2vo(UniqueTextValues uv) {
		UniqueValueVO vo = new UniqueValueVO();
		vo.setDatatype(uv.getDatatype());
		vo.setDs_id(uv.getDs_id());
		vo.setHeader(uv.getHeader());
		vo.setLangCode(uv.getLangCode());
		vo.setLabel(uv.getLabel());
		vo.setCode(uv.getValue());
		return vo;
	}

	private UniqueValueVO uniqueDateValue2vo(UniqueDateValues uv) {
		UniqueValueVO vo = new UniqueValueVO();
		vo.setDatatype(uv.getDatatype());
		vo.setDs_id(uv.getDs_id());
		vo.setHeader(uv.getHeader());
		vo.setLangCode(uv.getLangCode());
		vo.setLabel(FieldParser.parseDate(uv.getValue()));
		vo.setCode(FieldParser.parseDate(uv.getValue()));
		return vo;
	}

	private DatasetVO dataset2vo(Dataset d, boolean addDescriptors) {
		DatasetVO vo = new DatasetVO();
		vo.setDatasetName(d.getTitle());
		vo.setDsId(String.valueOf(d.getResourceId()));
		if (addDescriptors)
			for (Descriptor des : d.getDatasetType().getDescriptors())
				vo.addDescriptorViewVO(descriptor2vo(des));
		return vo;
	}

	/* *************************************************************************************************************** */
	/* *************************************************************************************************************** */
	/*
	 * ************************************************** LOAD OLAP
	 * **************************************************
	 */
	/* *************************************************************************************************************** */
	/* *************************************************************************************************************** */

	public FenixResourceVo getNewOlapResource(Long id) {
		ResourceView rv = resourceViewDao.findByID(id);
		FenixResourceVo fenixResource = null;
		PermissionVo permissionVo = getPermissions(id);
		if (rv != null)
			fenixResource = FenixResourceBuilder.build(rv, permissionVo);
		return fenixResource;
	}

	private PermissionVo getPermissions(long resourceId) {
		PermissionVo vo = new PermissionVo();
		ResourcePermission permission = fenixPermissionManager
				.getPermissions(resourceId);
		vo.setCanRead(permission.isHasReadPermission());
		vo.setCanWrite(permission.isHasWritePermission());
		vo.setCanDelete(permission.isHasDeletePermission());
		vo.setCanDownload(permission.isHasDownloadPermission());
		return vo;
	}

	public Long saveOlap(OLAPParametersVo pvo) {

		OLAPParameters p = vo2parameters(pvo);

		// Long olapID = resourceViewDao.saveOLAP(p);
		// fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP,
		// FenixPermission.READ, olapID, true);
		// fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP,
		// FenixPermission.WRITE, olapID, true);
		// fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP,
		// FenixPermission.DELETE, olapID, true);
		// fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP,
		// FenixPermission.DOWNLOAD, olapID, true);
		// olapUpdater.writeHtmlFile(resourceViewDao.find(olapID));

		Long mtID = mtSaver.saveMT(p);
		fenixPermissionManager.updateGroupPermissionOnResource(
				FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ,
				mtID, true);
		fenixPermissionManager.updateGroupPermissionOnResource(
				FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE,
				mtID, true);
		fenixPermissionManager.updateGroupPermissionOnResource(
				FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE,
				mtID, true);
		fenixPermissionManager.updateGroupPermissionOnResource(
				FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD,
				mtID, true);
		olapUpdater.writeHtmlFile(pvo.getOlapHtml(), mtID);

		return mtID;
	}

	public String addToReport(OLAPParametersVo pvo) throws FenixGWTException {
		OLAPParameters p = vo2parameters(pvo);
		Long mtID = mtSaver.saveMT(p);
		olapUpdater.writeHtmlFile(pvo.getOlapHtml(), mtID);
		// String iframe = getIFrame(mtID);
		String html = getHTML(mtID);

		int start = html.indexOf("<div id=\"");
		int end = 1 + html.indexOf(">", start);
		if ((start > -1) && (end > -1)) {
			String s = html.substring(start, end);
			int from = s.indexOf("id=\"");
			int to = s.indexOf("\"", from + "id=\"".length());
			if ((from > -1) && (to > -1)) {
				String id = s.substring(from + "id=\"".length(), to);
				html = html.replaceAll(id, "olap_" + mtID);
			}
		}

		return html;
	}

	public List<UniqueValueVO> getDimensionValues(Long datasetID,
			String columnHeader, String langCode) {
		List<UniqueValueVO> vos = new ArrayList<UniqueValueVO>();
		List<UniqueValue> uvs = olapDao.getDimensionValues(datasetID,
				columnHeader, langCode);
		for (UniqueValue uv : uvs)
			vos.add(uniqueValue2VO(uv));
		return vos;
	}

	public Map<String, List<UniqueValueVO>> showUnSelectedValues(
			OLAPParametersVo pvo, String langCode) throws FenixGWTException {
		try {
			Map<String, List<UniqueValueVO>> m = new HashMap<String, List<UniqueValueVO>>();
			List<UniqueValueVO> xvos = getDimensionValues(
					pvo.getDataSourceId(), pvo.getXLabel(), langCode);
			m.put("X", xvos);
			List<UniqueValueVO> zvos = getDimensionValues(
					pvo.getDataSourceId(), pvo.getZLabel(), langCode);
			m.put("Z", zvos);
			if (pvo.getW() != null && pvo.getW().length() > 0) {
				List<UniqueValueVO> wvos = getDimensionValues(pvo
						.getDataSourceId(), pvo.getWLabel(), langCode);
				m.put("W", wvos);
			}
			if (pvo.getY() != null && pvo.getY().length() > 0) {
				List<UniqueValueVO> yvos = getDimensionValues(pvo
						.getDataSourceId(), pvo.getYLabel(), langCode);
				m.put("Y", yvos);
			}
			return m;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public List<UniqueValueVO> getSavedDimensionValues(Long resourceViewID,
			String contentDescriptor, String langCode) throws FenixGWTException {
		List<UniqueValueVO> vos = new ArrayList<UniqueValueVO>();
		try {
			if (contentDescriptor.contains("date")
					|| contentDescriptor.contains("Date")) {
				List<Long> ids = olapDao
						.findUniqueDateValueByResourceViewID(resourceViewID);
				List<UniqueDateValues> udvs = resourceViewDao
						.findUniqueDateValues(ids, langCode);
				for (UniqueDateValues uv : udvs)
					vos.add(uniqueValue2VO(uv));
			} else {
				List<Long> ids = olapDao
						.findUniqueTextValueByResourceViewID(resourceViewID);
				List<UniqueTextValues> udvs = resourceViewDao
						.findUniqueTextValues(ids, langCode);
				for (UniqueTextValues uv : udvs)
					vos.add(uniqueValue2VO(uv));
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
		return vos;
	}

	private UniqueValueVO uniqueValue2VO(UniqueValue uv) {
		UniqueValueVO vo = new UniqueValueVO();
		vo.setDatatype(uv.getDatatype());
		vo.setDs_id(uv.getDs_id());
		vo.setHeader(uv.getHeader());
		vo.setId(uv.getId());
		if (uv instanceof UniqueTextValues) {
			vo.setCode(((UniqueTextValues) uv).getValue());
			vo.setLabel(((UniqueTextValues) uv).getLabel());
			vo.setLangCode(uv.getLangCode());
		} else if (uv instanceof UniqueDateValues) {
			vo.setCode(FieldParser
					.parseDate(((UniqueDateValues) uv).getValue()));
			vo.setLabel(FieldParser.parseDate(((UniqueDateValues) uv)
					.getValue()));
			vo.setPeriodType(((UniqueDateValues) uv).getPeriodType());
		}
		return vo;
	}

	public String findGeoColumnName(Long datasetId) {
		return olapDao.findGeoColumnName(datasetId);
	}

	@SuppressWarnings("unchecked")
	public PagingLoadResult<FSATMISModelData> findAllFSATMISModelData(
			final PagingLoadConfig config, Long datasetId) {

		if (tableMap == null || tableMap.isEmpty())
			loadFilteredData(datasetId);

		ArrayList<FSATMISModelData> sublist = new ArrayList<FSATMISModelData>();
		int start = config.getOffset();
		int limit = FSATMISTableCacheData.getTableData(datasetId).size();

		if (config.getLimit() > 0)
			limit = Math.min(start + config.getLimit(), limit);
		for (int i = config.getOffset(); i < limit; i++)
			sublist.add(FSATMISTableCacheData.getTableData(datasetId).get(i));

		return new BasePagingLoadResult(sublist, config.getOffset(),
				FSATMISTableCacheData.getTableData(datasetId).size());
	}

	public List<FSATMISModelData> getFSATMISModelData(Long datasetId) {

		List<FSATMISModelData> data = new ArrayList<FSATMISModelData>();
		List<UDTableFilter> filters = createFilters(datasetId);
		UDTable table = udtableDao.getTable(filters, "EN", false);
		for (UDTableRow r : table.getRows()) {
			data.add(createFSATMISModelData(r));
		}

		return data;
	}

	private void loadFilteredData(Long datasetId) {

		List<FSATMISModelData> data = new ArrayList<FSATMISModelData>();
		List<UDTableFilter> filters = createFilters(datasetId);
		UDTable table = udtableDao.getTable(filters, "EN", false);
		for (UDTableRow r : table.getRows()) {
			data.add(createFSATMISModelData(r));
		}

		FSATMISTableCacheData.addTableDataToCache(datasetId, data);
	}

	/*
	 * First all the filters are created from dataset's descriptors, then
	 * allowed values are added from the user selection.
	 */
	private List<UDTableFilter> mergeFilters(List<UDTableFilter> sysFilters,
			List<UDTableFilter> userFilters) {
		for (UDTableFilter uf : userFilters) {
			if (!uf.getAllowedValues().isEmpty()) {
				for (UDTableFilter sf : sysFilters) {
					if (sf.getHeader().equalsIgnoreCase(uf.getHeader())) {
						List<String> allowedValues = new ArrayList<String>();
						for (String av : uf.getAllowedValues())
							allowedValues.add(av);
						sf.setAllowedValues(allowedValues);
						break;
					}
				}
			}
		}
		return sysFilters;
	}

	private List<UDTableFilter> createFilters(Long datasetId) {
		List<UDTableFilter> filters = new ArrayList<UDTableFilter>();
		List<Descriptor> descriptors = gwtConnector
				.getDatasetDescriptors(datasetId);
		for (Descriptor d : descriptors)
			filters.add(createFilter(datasetId, d));
		return filters;
	}

	private UDTableFilter createFilter(Long datasetId, Descriptor d) {
		UDTableFilter f = new UDTableFilter();
		f.setDataType(d.getDataType().name());
		f.setHeader(d.getHeader());
		f.setResourceId(datasetId);
		return f;
	}

	public List<String> createOlapCharts(OLAPParametersVo p, String html) {

		List<String> paths = new ArrayList<String>();
		OLAPHtmlParser parser = new OLAPHtmlParser();
		OLAPParameters parameters = vo2parameters(p);
		List<Map<String, Map<String, Double>>> chartList = parser
				.createChartValues(parameters, html);

		OLAPChartConfiguration c = createChartConfiguration(p);

		try {
			for (Map<String, Map<String, Double>> values : chartList) {
				BufferedImage img = OLAPChartFactory.createBufferedImage(c,
						values);
				String chartFileName = String.valueOf(Math.random())
						+ "_FS-ATMIS.png";
				File chartFile = new File(Setting.getSystemPath()
						+ "/olapCharts/" + chartFileName);
				ImageIO.write(img, "png", chartFile);
				paths.add(chartFileName);

			}
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}

		return paths;
	}

	public List<Map<String, Map<String, Double>>> getChartData(
			OLAPParametersVo p, String html) {
		OLAPHtmlParser parser = new OLAPHtmlParser();
		OLAPParameters parameters = vo2parameters(p);
		List<Map<String, Map<String, Double>>> result = parser
				.createChartValues(parameters, html);
		if (parameters.getChartTitles() != null)
			p.setChartsTitles(parameters.getChartTitles());
		else
			System.out.println("parameters.getChartTitles() is null");
		return result;
	}

	private OLAPChartConfiguration createChartConfiguration(OLAPParametersVo p) {
		OLAPChartConfiguration c = new OLAPChartConfiguration();
		OLAPChartType ct = OLAPChartType.valueOfIgnoreCase(p.getChartType());
		c.setChartType(ct);
		c.setLegend(true);
		c.setPlotOrientation(PlotOrientation.VERTICAL);
		c.setTitle(p.getDataSourceTitle() + "(" + p.getFunction() + ")");
		c.setTooltips(true);
		c.setUrls(true);
		c.setxLabel(p.getZLabel());
		c.setyLabel(p.getValueLabel());
		return c;
	}

	private CQLFilter getFilter(LayerValuesProvider provider, int gaulLevel) {
		List<CQLFilter> filters = new ArrayList<CQLFilter>();

		for (GeoPair geoPair : provider) {
			filters.add(getFilter(geoPair.getFID(), gaulLevel));
		}

		return CQLFilterOp.or(filters);
	}

	public CQLFilter getFilter(String regionCode, int gaulLevel) {
		if (gaulLevel == 0)
			return GaulCQLFilterFactory.createGaul0Is(regionCode);
		else if (gaulLevel == 1)
			return GaulCQLFilterFactory.createGaul1Is(regionCode);
		else
			throw new IllegalArgumentException("unknown GAUL level");
	}

	public List<String> createOLAPMap(OLAPParametersVo p, String geoDataType,
			String geoLabel, Map<String, String> filters, String html,
			int intervals, String minColor, String maxColor,
			boolean showBaseLayer) throws FenixGWTException {

		// find layer code
		String layerCode = udtableDao.getLayerCode(p.getDataSourceId(),
				geoDataType, geoLabel);

		// layer values provider
		LayerValuesProviderImpl provider = new LayerValuesProviderImpl(
				layerCode);

		// create OLAPMapBeans
		OLAPHtmlParser parser = new OLAPHtmlParser();
		OLAPParameters parameters = vo2parameters(p);
		List<OLAPMapBean> beans = parser.createOlapMapBeans(parameters, html,
				geoDataType, geoLabel, filters);
		if (beans.isEmpty())
			throw new FenixGWTException(
					"You selection has no values, the map will not be created.");

		// add GeoPairs
		// LOGGER.info(beans.size() + " beans to build the filter...");
		for (OLAPMapBean b : beans) {
			if (!b.getFid().equalsIgnoreCase("n.a.")) {
				GeoPairImpl geoPair = new GeoPairImpl(b.getFid(), b.getValue());
				provider.addPair(geoPair);
			}
		}
		// .info(provider.getPairs().size() + " pairs...");
		// for (GeoPair g : provider.getPairs())
		// LOGGER.info(g.getFID() + ", " + g.getValue());

		return createMap(parameters, provider, filters, p.getDataSourceTitle(),
				intervals, minColor, maxColor, showBaseLayer);
	}

	private List<String> createMap(OLAPParameters p,
			LayerValuesProvider provider, Map<String, String> filters,
			String mapTitle, int intervals, String minColor, String maxColor,
			boolean showBaseLayer) {

		// initiate result
		List<String> paths = new ArrayList<String>();

		// GAUL level
		int gaulLevel = 0;
		if (provider.getLayerCode()
				.charAt(provider.getLayerCode().length() - 1) == '1')
			gaulLevel = 1;

		GeometryResolver geometryResolver = new DBFLGeometryResolver(provider);
		((DBFLGeometryResolver) geometryResolver)
				.setLayerGaulUtils(layerGaulUtils);

		FeatureBuilder featureBuilder = new QuickFeatureBuilder(
				geometryResolver);

		QuickFeatureCollection fc = new QuickFeatureCollection(provider,
				featureBuilder);
		QuickMap quickMap = new QuickMap();
		quickMap.setWidth(WIDTH);
		quickMap.setHeight(HEIGHT);
		quickMap.setIntervals(intervals);

		quickMap.setMaxColor(OLAPUtils.getUserColor(minColor));
		quickMap.setMinColor(OLAPUtils.getUserColor(maxColor));

		BufferedImage quickimage = quickMap.render(fc);

		List<Range> ranges = quickMap.getLastRanges();

		CQLFilter gaul1Filters = getFilter(provider, 1);

		try {
			GeoView geoViewG0;

			BoundingBox bbox = quickMap.getLastBbox();

			// building the lower layers
			List<GeoView> lowerLayers = new ArrayList<GeoView>();
			if (showBaseLayer) {
				geoViewG0 = new GeoView(wmsMapProviderDao
						.findByCode("layer_baseraster"));
				lowerLayers.add(geoViewG0);
			}
			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul1"));
			lowerLayers.add(geoViewG0);

			// building the upper layers
			List<GeoView> upperLayers = new ArrayList<GeoView>();

			// the style can be created once for all
			Long layerId = olapDao.findLayerIdFromLayerCode(provider
					.getLayerCode());
			String admName = GaulUtils.getMainLabelFieldname(gaulLevel);
			String labels = createLabelStyle(layerId, admName, "000000");

			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul1"));
			geoViewG0.setStyleName(labels);
			geoViewG0.setCqlFilter(gaul1Filters);
			upperLayers.add(geoViewG0);
			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul0"));
			upperLayers.add(geoViewG0);

			BufferedImage lowerImage = getLayerImage(bbox, lowerLayers);
			BufferedImage upperImage = getLayerImage(bbox, upperLayers);

			// create additional layers
			List<BufferedImage> images = new ArrayList<BufferedImage>();

			images.add(lowerImage);
			images.add(quickimage);
			images.add(upperImage);

			// add transparencies
			List<Float> transparencies = new ArrayList<Float>();
			for (int i = 0; i < images.size(); i++)
				transparencies.add(new Float(1.0));

			// merge and save
			BufferedImage merged = ImageMerger.merge(images, transparencies);
			String mergedFileName = String.valueOf(Math.random())
					+ "_MERGED.png";
			File mergedFile = new File(Setting.getSystemPath() + "/olapMaps/"
					+ mergedFileName);
			ImageIO.write(merged, "png", mergedFile);

			StringBuffer table = createImageTableWithSideLegend(p,
					mergedFileName, ranges, filters, mapTitle);
			String mapPath = createImageWithLegend(table);
			paths.add(mapPath);

		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FenixGWTException e) {
			LOGGER.error(e);
		}

		// return result
		return paths;
	}

	@Deprecated
	public List<String> createOLAPMap(OLAPParametersVo p, String geoDataType,
			String geoLabel) {
		List<String> paths = new ArrayList<String>();
		return paths;
	}

	private String createImageWithLegend(StringBuffer table) {
		String path = String.valueOf(Math.random()) + "_OLAP_MAP.html";
		File file = new File(Setting.getSystemPath() + "/olapMaps/" + path);
		try {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(table.toString().getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
		return path;
	}

	private StringBuffer createImageTableWithSideLegend(OLAPParameters p,
			String mapPath, List<Range> ranges, Map<String, String> filters,
			String mapTitle) {
		StringBuffer sb = new StringBuffer();
		DecimalFormat twoDigit = new DecimalFormat("#,##0.00");
		sb.append("<table width='100%'>");

		sb.append("<tr><td colspan = '2' align='center'><H1>" + mapTitle
				+ "</H1></td>");

		Range firstRange = ranges.get(0);
		String firstRGB = Integer.toHexString(firstRange.color.getRGB());
		firstRGB = firstRGB.substring(2, firstRGB.length());
		sb.append("<tr><td rowspan = '" + ranges.size() + "'><img src='"
				+ mapPath + "'/></td>");
		sb.append("<td><b>Legend</b></td></tr>");

		if (ranges.size() > 1) {
			for (int i = 1; i < ranges.size(); i++) {
				Range r = ranges.get(i);
				String rgb = Integer.toHexString(r.color.getRGB());
				rgb = rgb.substring(2, rgb.length());
				sb.append("<tr>");
				sb.append("<td width='25%' bgcolor='#" + rgb
						+ "'><font size='3'>" + twoDigit.format(r.min) + " to "
						+ twoDigit.format(r.max) + "</font></td>");
				sb.append("</tr>");
			}
		}

		sb.append("</table>");
		sb.append(createFiltersLegend(p, filters).toString());
		return sb;
	}

	private StringBuffer createFiltersLegend(OLAPParameters p,
			Map<String, String> filters) {

		StringBuffer sb = new StringBuffer();

		sb.append("<table width='75%'>");

		sb
				.append("<tr><td><div style='font-weight: bold; font-family: sans-serif;'>Plotted Dimension:</div></td><td><div style='font-family: sans-serif;'>");
		sb.append(p.getValueLabel());
		String mu = " [thousands of USD]";
		if (p.getFunction().equalsIgnoreCase("COUNT"))
			mu = " [number of intervention]";
		sb.append(mu);
		sb.append("</div></td></tr>");

		sb
				.append("<tr><td><div style='font-weight: bold; font-family: sans-serif;'>Function:</div></td><td><div style='font-family: sans-serif;'>");
		sb.append(OLAPDaoUtils.getFunctionLabel(p.getFunction()));
		sb.append("</div></td></tr>");

		Map<String, String> labels = getFilterLabels(p, filters);
		for (String f : labels.keySet()) {
			sb
					.append("<tr><td><div style='font-weight: bold; font-family: sans-serif;'>");
			sb.append(f);
			sb.append(":</div></td><td><div style='font-family: sans-serif;'>");
			sb.append(labels.get(f));
			sb.append("</div></td></tr>");
		}

		sb.append("</table>");
		return sb;
	}

	private Map<String, String> getFilterLabels(OLAPParameters p,
			Map<String, String> filters) {
		Map<String, String> labels = new HashMap<String, String>();
		for (String key : filters.keySet()) {
			if (key.equalsIgnoreCase("x")) {
				String dimension = p.getX();
				String header = p.getXLabel();
				String label = olapDao.findFilterLabel(p.getDataSourceId(),
						dimension, header, filters.get(key));
				labels.put(header, label);
			} else if (key.equalsIgnoreCase("z")) {
				String dimension = p.getZ();
				String header = p.getZLabel();
				String label = olapDao.findFilterLabel(p.getDataSourceId(),
						dimension, header, filters.get(key));
				labels.put(header, label);
			} else if (key.equalsIgnoreCase("y")) {
				String dimension = p.getY();
				String header = p.getYLabel();
				String label = olapDao.findFilterLabel(p.getDataSourceId(),
						dimension, header, filters.get(key));
				labels.put(header, label);
			} else if (key.equalsIgnoreCase("w")) {
				String dimension = p.getW();
				String header = p.getWLabel();
				String label = olapDao.findFilterLabel(p.getDataSourceId(),
						dimension, header, filters.get(key));
				labels.put(header, label);
			}
		}
		return labels;
	}

	private BufferedImage getLayerImage(BoundingBox bbox, String layerCode) {
		// create the map builder
		WMSMapRetriever mm = new WMSMapRetriever(System
				.getProperty("java.io.tmpdir"));
		mm.setHeight(HEIGHT);
		mm.setWidth(WIDTH);
		// add GAUL0 layer
		WMSMapProvider layer = wmsMapProviderDao.findByCode(layerCode);
		GeoView geoViewG0 = new GeoView(layer);
		mm.addLayer(geoViewG0);
		mm.setBoundingBox(bbox);
		// create the image
		BufferedImage image = mm.getMapImage();
		return image;
	}

	private BufferedImage getLayerImage(BoundingBox bbox, List<GeoView> gvlist) {
		// create the map builder
		WMSMapRetriever mm = new WMSMapRetriever(System
				.getProperty("java.io.tmpdir"));
		mm.setHeight(HEIGHT);
		mm.setWidth(WIDTH);
		mm.setBoundingBox(bbox);

		for (GeoView geoView : gvlist) {
			mm.addLayer(geoView);
		}

		// create the image
		BufferedImage image = mm.getMapImage();
		return image;
	}

	private BufferedImage getLayerImageWithLabels(BoundingBox bbox,
			String layerCode) throws FenixGWTException {
		// create the map builder
		WMSMapRetriever mm = new WMSMapRetriever(System
				.getProperty("java.io.tmpdir"));
		mm.setHeight(HEIGHT);
		mm.setWidth(WIDTH);
		// add GAUL0 layer
		WMSMapProvider layer = wmsMapProviderDao.findByCode(layerCode);
		GeoView geoViewG0 = new GeoView(layer);

		Long layerId = olapDao.findLayerIdFromLayerCode(layerCode);
		String labels = createLabelStyle(layerId, "ADM1_NAME", "CA1616");
		geoViewG0.setStyleName(labels);

		mm.addLayer(geoViewG0);
		mm.setBoundingBox(bbox);
		// create the image
		BufferedImage image = mm.getMapImage();
		return image;
	}

	public String createLabelStyle(long layerId, String fieldName, String scolor)
			throws FenixGWTException {

		Color color = ColourUtil.hex2colour(scolor);
		String sld = TextSymbUtil.createLabelStyle(fieldName, color);
		String stylename = "label_" + layerId + "_" + fieldName.toLowerCase()
				+ "_" + ColourUtil.colour2string(color);

		WMSMapProvider mp = wmsMapProviderDao.findById(layerId);
		String layername = mp.getLayerName().replace(":", "_");
		GeoServer geoServer = ((InternalWMSLayer) mp).getGeoServer();

		GeoserverIO gsio = new GeoserverIO();
		if (!gsio.existsStyle(geoServer, stylename)) {
			// LOGGER.info("Creating style " + stylename);
			GeoserverPublisher.publishLayerStyle(geoServer, stylename, sld,
					stylename);
		}

		return stylename;
	}

	public List<CodeVo> getCodingHierarchyFamily(Long datasetId,
			String dimension, String header, String code) {
		List<CodeVo> vos = new ArrayList<CodeVo>();
		List<CodingHierarchy> uncles = olapDao.getCodingHierarchyFamily(
				datasetId, dimension, header, code);
		for (CodingHierarchy h : uncles)
			vos.add(CodingVoConverter.code2Vo(h));
		return vos;
	}

	public List<CodeVo> getCodingHierarchyUncles(Long datasetId,
			String dimension, String header, String code) {
		List<CodeVo> vos = new ArrayList<CodeVo>();
		List<CodingHierarchy> uncles = olapDao.getCodingHierarchyUncles(
				datasetId, dimension, header, code);
		for (CodingHierarchy h : uncles)
			vos.add(CodingVoConverter.code2Vo(h));
		return vos;
	}

	public String createReport(String report, String orientation) {

		CreateReport olapReport = new CreateReport();
		String nameFile = olapReport.createReport(report, orientation);
		String exportFile = BirtUtil.randomFileExport() + ".PDF";

		IReportEngine reportEngine = BirtUtil.getReportEngine();
		try {

			IReportRunnable design = reportEngine.openReportDesign(nameFile);
			IRunTask task1 = reportEngine.createRunTask(design);
			task1.run(nameFile + ".rptdocument");

			IReportDocument document = reportEngine.openReportDocument(nameFile
					+ ".rptdocument");

			IRenderOption options = new RenderOption();

			options.setOutputFormat(DesignChoiceConstants.FORMAT_TYPE_PDF);

			options.setOutputFileName(Setting.getSystemPath()
					+ "/exportObject/" + exportFile);

			IRenderTask task = reportEngine.createRenderTask(document);
			task.setRenderOption(options);

			task.render();
			task.close();

		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
		}

		return exportFile;
	}

	public String createExcel(OLAPParametersVo params) throws FenixGWTException {
		try {
			OLAPParameters p = vo2parameters(params);
			p = sortLabels(p);
			p = createLabelCodeMaps(p);
			List<OLAPBean> beans = olapDao.createCube(p);
			return OLAPExcel.createExcel(p, beans, Setting.getSystemPath());
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	@Deprecated
	public List<String> findDimensionValues(String datasetId, String dimension) {
		Dataset dataset = datasetDao.findById(Long.parseLong(datasetId));
		return datasetDao.getDistinct(dataset, dimension);
	}

	public Map<String, String> findDimensionValuesWithLabels(String datasetId,
			String dimension, String header) {
		return olapDao.findDimensionValuesWithLabels(datasetId, dimension,
				header);
	}

	@SuppressWarnings("deprecation")
	public Map<String, String> findAllDatasetTypeDescriptors(String datasetId) {
		List<Descriptor> descriptors = olapDao
				.findDatasetTypeDimensions(datasetId);
		Map<String, String> map = new HashMap<String, String>();
		for (Descriptor descriptor : descriptors)
			map.put(descriptor.getHeader(), descriptor.getContentDescriptor());
		return map;
	}

	public Map<String, String> findAllCoreDataset() {
		List<CoreDataset> datasets = olapDao.findAllCoreDataset();
		Map<String, String> map = new HashMap<String, String>();
		for (CoreDataset dataset : datasets) {
			map
					.put(dataset.getTitle(), String.valueOf(dataset
							.getResourceId()));
		}
		return map;
	}

	public List<String> findAllCoreDatasetType() {
		return olapDao.findAllCoreDatasetType();
	}

	public Map<String, String> findAllFSATMISDataset() {
		return olapDao.findAllFSATMISType();
	}
	public void reshapeParam(OLAPParametersVo p) {
		
		if(p.getX() == null || p.getX().length()== 0 || p.getX().equals("")  || p.getXLabels().isEmpty()){
			p.setX("");
			p.setXLabels(new HashMap<String, String>());
			p.setXLabel("");
		}
		if(p.getY() == null || p.getY().length()== 0 || p.getY().equals("")  || p.getYLabels().isEmpty()){
			p.setY("");
			p.setYLabels(new HashMap<String, String>());
			p.setYLabel("");
		}
		if(p.getW() == null || p.getW().length()== 0 || p.getW().equals("")  || p.getWLabels().isEmpty()){
			p.setW("");
			p.setWLabels(new HashMap<String, String>());
			p.setWLabel("");
		}
		if(p.getZ() == null || p.getZ().length()== 0 || p.getZ().equals("")  || p.getZLabels().isEmpty()){
			p.setZ("");
			p.setZLabels(new HashMap<String, String>());
			p.setZLabel("");
		}
	}
	
	public String createTable(OLAPParametersVo parameters) {
		
		//mackx
		//reshapeParam(parameters);
		
		// set parameters
		LOGGER.info("OLAPParametersVo: " + parameters);
		OLAPParameters p = vo2parameters(parameters);

		// create and persist CSS
		String cssContent = OLAPCss.build(p).toString();
		//LOGGER.info("cssContent: >" + cssContent);
		
		//mackx
		try{
			FileWriter ffx = new FileWriter(
			"C:\\Documents and Settings\\Manik\\Desktop\\manik_fao\\olap_test\\mmmm.css");
			ffx.write(cssContent);
			ffx.close();
		} catch (Exception dsd) {
		}
		//

		Long cssID = olapDao.saveCSS(cssContent);
		String cssPath = olapUpdater.writeCssFile(cssContent, cssID);

		String css = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://"
				+ urlFinder.serverip
				+ ":"
				+ urlFinder.port
				+ "/fenix-web/OLAP/" + cssPath + "\">";
		LOGGER.info("CSS Definition: " + css);

		// create cube
		List<OLAPBean> beans = olapDao.createCube(p);

		// create HTML
		String ret = OLAPHtml.createCubeHtml(p, beans, css);
		if (!p.getYLabels().isEmpty() && !p.getWLabels().isEmpty()) {
			ret = OLAPHtml.createFourDimensionsTable(beans, p, css).toString();
		}
		
		LOGGER.info("HTML: >" + ret);

		return ret;
	}

	private OLAPParameters vo2parameters(OLAPParametersVo vo) {

		OLAPParameters p = new OLAPParameters();
		
		reshapeParam(vo);
		
		p.setDataSourceId(vo.getDataSourceId());
		p.setDataSourceTitle(vo.getDataSourceTitle());

		List<OLAPFilter> filters = new ArrayList<OLAPFilter>();
		for (OLAPFilterVo f : vo.getFilters())
			filters.add(vo2filter(f));
		p.setFilters(filters);

		p.setValue(vo.getValue());
		p.setValueLabel(vo.getValueLabel());
		p.setW(vo.getW());
		p.setWLabel(vo.getWLabel());
		p.setWLabels(vo.getWLabels());
		p.setX(vo.getX());
		p.setXLabel(vo.getXLabel());
		p.setXLabels(vo.getXLabels());
		p.setY(vo.getY());
		p.setYLabel(vo.getYLabel());
		p.setYLabels(vo.getYLabels());
		p.setZ(vo.getZ());
		p.setZLabel(vo.getZLabel());
		p.setZLabels(vo.getZLabels());

		p.setShowXLabel(vo.isShowXLabel());
		p.setShowYLabel(vo.isShowYLabel());
		p.setShowWLabel(vo.isShowWLabel());
		p.setShowZLabel(vo.isShowZLabel());

		p.setShowSingleValues(vo.isShowSingleValues());
		p.setShowTotals(vo.isShowTotals());
		
		p.setFunction(vo.getFunction());
		
		//mackx == extended feature

		p.setColFunction   (vo.getColFunction   ());
		p.setRowFunction   (vo.getRowFunction   ());
		p.setSubRowFunction(vo.getSubRowFunction());
		p.setSubColFunction(vo.getSubColFunction());
		
		p.setShowBorder(vo.getShowBorder());
		p.setShowColSubject(vo.getShowColSubject());
		p.setShowColSummary(vo.getShowColSummary());

		p.setShowGridLine(vo.getShowGridLine());
		p.setShowRowSubject(vo.getShowRowSubject());
		p.setShowRowSummary(vo.getShowRowSummary());
		p.setShowShading(vo.getShowShading());
		
		p.setShading1Color   (vo.getShading1Color   ());
		p.setShading2Color   (vo.getShading2Color   ());

		p.setShowSubColSubject(vo.getShowSubColSubject());
		p.setShowSubColSummary(vo.getShowSubColSummary());
		p.setShowSubRowSubject(vo.getShowSubRowSubject());
		p.setShowSubRowSummary(vo.getShowSubRowSummary());
		
		p.setwLabelShowTitle(vo.getwLabelShowTitle());
		p.setxLabelShowTitle(vo.getxLabelShowTitle());
		p.setyLabelShowTitle(vo.getyLabelShowTitle());
		p.setzLabelShowTitle(vo.getzLabelShowTitle());
		//extended feature end
		
		p.setChartType(vo.getChartType());

		p.setDecimals(vo.getDecimals());
		p.setDecimalsForTotals(vo.getDecimalsForTotals());
		p.setDecimalsForVariation(vo.getDecimalsForVariation());

		p.setDecimalsForTotals(vo.getDecimalsForTotals());
		p.setDecimalsForVariation(vo.getDecimalsForVariation());

		p.setOlapHtml(vo.getOlapHtml());
		p.setShowRowVariation(vo.isShowRowVariation());
		p.setShowColVariation(vo.isShowColVariation());

		p.setShowBaseLayer(vo.isShowBaseLayer());
		p.setUseFlashCharts(vo.isUseFlashCharts());
		p.setReportOrientation(vo.getReportOrientation());
		p.setMapClasses(vo.getMapClasses());
		p.setMinColor(vo.getMinColor());
		p.setMaxColor(vo.getMaxColor());

		p.setxUniqueValuesMap(vo.getxUniqueValuesMap());
		p.setyUniqueValuesMap(vo.getyUniqueValuesMap());
		p.setzUniqueValuesMap(vo.getzUniqueValuesMap());
		p.setwUniqueValuesMap(vo.getwUniqueValuesMap());

		p.setAggregateXAsMonthly(vo.isAggregateXAsMonthly());
		p.setAggregateZAsMonthly(vo.isAggregateZAsMonthly());
		p.setAggregateYAsMonthly(vo.isAggregateYAsMonthly());
		p.setAggregateWAsMonthly(vo.isAggregateWAsMonthly());

		p.setAggregateXAsYearly(vo.isAggregateXAsYearly());
		p.setAggregateZAsYearly(vo.isAggregateZAsYearly());
		p.setAggregateYAsYearly(vo.isAggregateYAsYearly());
		p.setAggregateWAsYearly(vo.isAggregateWAsYearly());

		p.setResourceViewID(vo.getResourceViewID());
		p.setDataSourceType(vo.getDataSourceType());

		p.setxUseFromDateToDate(vo.isxUseFromDateToDate());
		p.setxFromDate(vo.getxFromDate());
		p.setxToDate(vo.getxToDate());
		p.setxUseMostRecentData(vo.isxUseMostRecentData());
		p.setxMostRecentDataStartDate(vo.getxMostRecentDataStartDate());

		p.setzUseFromDateToDate(vo.iszUseFromDateToDate());
		p.setzFromDate(vo.getzFromDate());
		p.setzToDate(vo.getzToDate());
		p.setzUseMostRecentData(vo.iszUseMostRecentData());
		p.setzMostRecentDataStartDate(vo.getzMostRecentDataStartDate());

		p.setyUseFromDateToDate(vo.isyUseFromDateToDate());
		p.setyFromDate(vo.getyFromDate());
		p.setyToDate(vo.getyToDate());
		p.setyUseMostRecentData(vo.isyUseMostRecentData());
		p.setyMostRecentDataStartDate(vo.getyMostRecentDataStartDate());

		p.setwUseFromDateToDate(vo.iswUseFromDateToDate());
		p.setwFromDate(vo.getwFromDate());
		p.setwToDate(vo.getwToDate());
		p.setwUseMostRecentData(vo.iswUseMostRecentData());
		p.setwMostRecentDataStartDate(vo.getwMostRecentDataStartDate());

		p.setColHeaderColor(vo.getColHeaderColor());
		p.setColFontColor(vo.getColFontColor());
		p.setRowHeaderColor(vo.getRowHeaderColor());
		p.setRowFontColor(vo.getRowFontColor());
		p.setSubColHeaderColor(vo.getSubColHeaderColor());
		p.setSubColFontColor(vo.getSubColFontColor());
		p.setSubRowHeaderColor(vo.getSubRowHeaderColor());
		p.setSubRowFontColor(vo.getSubRowFontColor());
		p.setFontFamily(vo.getFontFamily());
		p.setFontSize(vo.getFontSize());
		p.setSummaryBackgroundColor(vo.getSummaryBackgroundColor());
		p.setSummaryFontColor(vo.getSummaryFontColor());
		p.setContentBackgroundColor(vo.getContentBackgroundColor());
		p.setContentFontColor(vo.getContentFontColor());
		p.setOlapTitleFontColor(vo.getOlapTitleFontColor());
		p.setOlapTitle(vo.getOlapTitle());
		p.setNotesTitle(vo.getNotesTitle());
		p.setNotesFontColor(vo.getNotesFontColor());

		p.setxFromDate(vo.getxFromDate());
		p.setxToDate(vo.getxToDate());
		p.setxUseFromDateToDate(vo.isxUseFromDateToDate());
		p.setxUseMostRecentData(vo.isxUseMostRecentData());
		p.setxLatestDays(vo.getxLatestDays());
		p.setxLatestMonths(vo.getxLatestMonths());
		p.setxLatestYears(vo.getxLatestYears());

		p.setzFromDate(vo.getzFromDate());
		p.setzToDate(vo.getzToDate());
		p.setzUseFromDateToDate(vo.iszUseFromDateToDate());
		p.setzUseMostRecentData(vo.iszUseMostRecentData());
		p.setzLatestDays(vo.getzLatestDays());
		p.setzLatestMonths(vo.getzLatestMonths());
		p.setzLatestYears(vo.getzLatestYears());

		p.setwFromDate(vo.getwFromDate());
		p.setwToDate(vo.getwToDate());
		p.setwUseFromDateToDate(vo.iswUseFromDateToDate());
		p.setwUseMostRecentData(vo.iswUseMostRecentData());
		p.setwLatestDays(vo.getwLatestDays());
		p.setwLatestMonths(vo.getwLatestMonths());
		p.setwLatestYears(vo.getwLatestYears());

		p.setyFromDate(vo.getyFromDate());
		p.setyToDate(vo.getyToDate());
		p.setyUseFromDateToDate(vo.isyUseFromDateToDate());
		p.setyUseMostRecentData(vo.isyUseMostRecentData());
		p.setyLatestDays(vo.getyLatestDays());
		p.setyLatestMonths(vo.getyLatestMonths());
		p.setyLatestYears(vo.getyLatestYears());

		if (vo.getAggregatedTableViewName() != null)
			p.setAggregatedTableViewName(vo.getAggregatedTableViewName());

		if (vo.getZHeader() != null)
			p.setZHeader(vo.getZHeader());

		p.setFunctionHeight(vo.getFunctionHeight());
		p.setFunctionWidth(vo.getFunctionWidth());
		p.setColumnLabelWidth(vo.getColumnLabelWidth());
		p.setColumnLabelsWidths(vo.getColumnLabelsWidths());
		p.setRowLabelsHeights(vo.getRowLabelsHeights());
		p.setSubRowLabelWidth(vo.getSubRowLabelWidth());

		p.setPeriodTypeCode(vo.getPeriodTypeCode());

		if (vo.getAggregateSelectedYDimensions() != null)
			p.setAggregateSelectedYDimensions(vo
					.getAggregateSelectedYDimensions());
		if (vo.getAggregateSelectedXDimensions() != null)
			p.setAggregateSelectedXDimensions(vo
					.getAggregateSelectedXDimensions());
		if (vo.getAggregateSelectedZDimensions() != null)
			p.setAggregateSelectedZDimensions(vo
					.getAggregateSelectedZDimensions());

		p.setDatesFormat(vo.getDatesFormat());
		p.setVariationThreshold(vo.getVariationThreshold());
		p.setVariationThresholdRed(vo.getVariationThresholdRed());
		p.setVariationThresholdYellow(vo.getVariationThresholdYellow());
		p.setShowVariationArrows(vo.getShowVariationArrows());

		return p;
	}

	private OLAPFilter vo2filter(OLAPFilterVo vo) {
		OLAPFilter f = new OLAPFilter();
		f.setDimension(vo.getDimension());
		f.setDimensionLabel(vo.getDimensionLabel());
		f.setDimensionMap(vo.getDimensionMap());
		return f;
	}

	public List<String> getChartsTitles(OLAPParametersVo p, String html) {
		OLAPHtmlParser parser = new OLAPHtmlParser();
		OLAPParameters parameters = vo2parameters(p);
		return parser.getChartTitles(parameters, html);
	}

	/**
	 * Worst method ever because of stupid EXT-GWT.
	 * 
	 * @param r
	 *            UDTable's row
	 * @return Model data for EXT-GWT table.
	 */
	private FSATMISModelData createFSATMISModelData(UDTableRow r) {
		FSATMISModelData m = new FSATMISModelData();
		for (UDTableCell c : r.getCells()) {
			if (c.getTitle().equals("Project Name"))
				m.setProjectName(c.getLabel());
			else if (c.getTitle().equals("Project Symbol"))
				m.setProjectSymbol(c.getLabel());
			else if (c.getTitle().equals("Country"))
				m.setCountry(c.getLabel());
			else if (c.getTitle().equals("Province"))
				m.setProvince(c.getLabel());
			else if (c.getTitle().equals("Donor"))
				m.setDonor(c.getLabel());
			else if (c.getTitle().equals("Donor Type"))
				m.setDonorType(c.getLabel());
			else if (c.getTitle().equals("Location"))
				m.setLocation(c.getLabel());
			else if (c.getTitle().equals("Project Status"))
				m.setProjectStatus(c.getLabel());
			else if (c.getTitle().equals("From Date"))
				m.setFromDate(c.getLabel());
			else if (c.getTitle().equals("To Date"))
				m.setToDate(c.getLabel());
			else if (c.getTitle().equals("Priority Sector"))
				m.setPrioritySector(c.getLabel());
			else if (c.getTitle().equals("Priority Description"))
				m.setPriorityDescription(c.getLabel());
			else if (c.getTitle().equals("Budget"))
				m.setBudget(c.getLabel());
			else if (c.getTitle().equals("Currency"))
				m.setCurrency(c.getLabel());
			else if (c.getTitle().equals("Food Security"))
				m.setFoodSecurity(c.getLabel());
			else if (c.getTitle().equals("Budget Type"))
				m.setBudgetType(c.getLabel());
			else if (c.getTitle().equals("Implementing Agency"))
				m.setImplementingAgency(c.getLabel());
			else if (c.getTitle().equals("Implementing Agency Type"))
				m.setImplementingAgencyType(c.getLabel());
			else if (c.getTitle().equals("Notes"))
				m.setNotes(c.getLabel());
		}
		return m;
	}

	private static OLAPParameters sortLabels(OLAPParameters p) {

		TreeSet<String> sortedXLabels = new TreeSet<String>(p.getXLabels()
				.values());
		TreeSet<String> sortedZLabels = new TreeSet<String>(p.getZLabels()
				.values());
		TreeSet<String> sortedYLabels = new TreeSet<String>();
		TreeSet<String> sortedWLabels = new TreeSet<String>();

		if (p.getX().contains("date") || p.getX().contains("Date")) {
			sortedXLabels = new TreeSet<String>(new FieldParserComparator());
			sortedXLabels.addAll(p.getXLabels().values());
		}
		if (p.getZ().contains("date") || p.getZ().contains("Date")) {
			sortedZLabels = new TreeSet<String>(new FieldParserComparator());
			sortedZLabels.addAll(p.getZLabels().values());
		}

		if (!p.getYLabels().isEmpty()) {
			sortedYLabels = new TreeSet<String>(p.getYLabels().values());
			if (p.getY().contains("date") || p.getY().contains("Date")) {
				sortedYLabels = new TreeSet<String>(new FieldParserComparator());
				sortedYLabels.addAll(p.getYLabels().values());
			}
		}

		if (!p.getWLabels().isEmpty()) {
			sortedWLabels = new TreeSet<String>(p.getWLabels().values());
			if (p.getW().contains("date") || p.getW().contains("Date")) {
				sortedWLabels = new TreeSet<String>(new FieldParserComparator());
				sortedWLabels.addAll(p.getWLabels().values());
			}
		}

		p.setSortedXLabels(sortedXLabels);
		p.setSortedZLabels(sortedZLabels);
		p.setSortedYLabels(sortedYLabels);
		p.setSortedWLabels(sortedWLabels);

		return p;
	}

	private static OLAPParameters createLabelCodeMaps(OLAPParameters p) {

		Map<String, String> xCodes = new HashMap<String, String>();
		Map<String, String> zCodes = new HashMap<String, String>();
		Map<String, String> yCodes = new HashMap<String, String>();
		Map<String, String> wCodes = new HashMap<String, String>();

		for (String xKey : p.getXLabels().keySet())
			xCodes.put(p.getXLabels().get(xKey), xKey);
		p.setxCodes(xCodes);

		for (String zKey : p.getZLabels().keySet())
			zCodes.put(p.getZLabels().get(zKey), zKey);
		p.setzCodes(zCodes);

		if (!p.getYLabels().isEmpty())
			for (String yKey : p.getYLabels().keySet())
				yCodes.put(p.getYLabels().get(yKey), yKey);
		p.setyCodes(yCodes);

		if (!p.getWLabels().isEmpty())
			for (String wKey : p.getWLabels().keySet())
				wCodes.put(p.getWLabels().get(wKey), wKey);
		p.setwCodes(wCodes);

		return p;
	}

	public void setOlapDao(OLAPDao olapDao) {
		this.olapDao = olapDao;
	}

	public void setGwtConnector(GwtConnector gwtConnector) {
		this.gwtConnector = gwtConnector;
	}

	public void setUdtableDao(UDTableDao udtableDao) {
		this.udtableDao = udtableDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setFenixPermissionManager(
			FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

	public void setResourceViewDao(ResourceViewDao resourceViewDao) {
		this.resourceViewDao = resourceViewDao;
	}

	public void setOlapJdbc(OLAPJDBC olapJdbc) {
		this.olapJdbc = olapJdbc;
	}

	public void setOlapUpdater(OLAPUpdater olapUpdater) {
		this.olapUpdater = olapUpdater;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setMtSaver(MTSaver mtSaver) {
		this.mtSaver = mtSaver;
	}

}