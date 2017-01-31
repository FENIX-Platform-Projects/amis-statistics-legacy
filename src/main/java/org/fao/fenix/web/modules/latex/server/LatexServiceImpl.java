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
package org.fao.fenix.web.modules.latex.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fao.fenix.core.designer.Box;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.domain.resourceview.NumericResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.resourceview.ResourceViewSetting;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.resourceview.ResourceViewDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.designer.common.vo.BoxVO;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;
import org.fao.fenix.web.modules.designer.common.vo.DesignerVO;
import org.fao.fenix.web.modules.designer.common.vo.PageVO;
import org.fao.fenix.web.modules.latex.common.services.LatexService;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.springframework.core.io.Resource;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LatexServiceImpl extends RemoteServiceServlet implements LatexService {

	private static final long serialVersionUID = -1863365925483381048L;

	private final static Logger LOGGER = Logger.getLogger(LatexServiceImpl.class);

	private String dir;

	private LatexUtils latexUtils;

	private LatexBuilder latexBuilder;

	private ResourceViewDao resourceViewDao;

	private FenixPermissionManager fenixPermissionManager;

	public LatexServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String exportPDF(Long reportID, String language) throws FenixGWTException {
		Map<String, List<ResourceViewVO>> map = load(reportID);
		DesignerVO vo = new DesignerVO();
		vo.setReportID(reportID);
		List<ResourceViewVO> pages = map.get(DesignerConstants.PAGE.name());
		List<ResourceViewVO> boxes = map.get(DesignerConstants.BOX.name());
		for (ResourceViewVO page : pages) {
			DesignerConstants orientation = DesignerConstants.valueOf(page.getType());
			PageVO pageVO = new PageVO();
			pageVO.setOrientation(orientation);
			for (ResourceViewVO box : boxes) {
				BoxVO boxVO = new BoxVO();
				boxVO.setResourceID(Long.valueOf(box.getQuery()));
				boxVO.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.valueOf(box.getType()));
				boxVO.setResourceName(box.getTitle());
				List<ResourceViewSettingVO> settings = box.getSettings();
				for (ResourceViewSettingVO s : settings) {
					DesignerConstants c = DesignerConstants.valueOf(s.getSettingName());
					switch (c) {
						case START_PIXEL:
							int idxStart = s.getValue().indexOf(':');
							boxVO.setStartX(Integer.valueOf(s.getValue().substring(0, idxStart)));
							boxVO.setStartY(Integer.valueOf(s.getValue().substring(1 + idxStart)));
						break;
						case END_PIXEL:
							int idxEnd = s.getValue().indexOf(':');
							boxVO.setEndX(Integer.valueOf(s.getValue().substring(0, idxEnd)));
							boxVO.setEndY(Integer.valueOf(s.getValue().substring(1 + idxEnd)));
						break;
						case CAPTION:
							boxVO.setCaption(s.getValue());
						break;
						case CAPTION_FONTFAMILY:
							boxVO.setCaptionFont(s.getValue());
						break;
						case CAPTION_SIZE:
							boxVO.setCaptionSize(s.getValue());
						break;
						case CAPTION_POSITION:
							boxVO.setCaptionPosition(s.getValue());
						break;
						case CAPTION_COLOR:
							boxVO.setCaptionColor(s.getValue());
						break;
						case BOX_LINE_COLOR:
							boxVO.setBoxLineColor(s.getValue());
						break;
						case BOX_BACKGROUND_COLOR:
							boxVO.setBoxBackgroundColor(s.getValue());
						break;
					}
				}
				pageVO.addBox(boxVO);
			}
			vo.addPage(pageVO);
		}
		return exportPDF(vo, language);
	}

	/* ********************************************************************************************************************************* */
	/* ********************************************************************************************************************************* */
	/*
	 * ************************************************************ S A V E
	 * ************************************************************
	 */
	/* ********************************************************************************************************************************* */
	/* ********************************************************************************************************************************* */

	public Long save(Map<String, List<ResourceViewVO>> latexReportParameters) throws FenixGWTException {

		Long reportID = null;
		List<ResourceView> reports = new ArrayList<ResourceView>();
		List<ResourceView> pages = new ArrayList<ResourceView>();
		List<ResourceView> boxes = new ArrayList<ResourceView>();

		for (String key : latexReportParameters.keySet()) {
			DesignerConstants dc = DesignerConstants.valueOf(key);
			switch (dc) {
			case REPORT:
				List<ResourceViewVO> reportVOs = latexReportParameters.get(key);
				for (ResourceViewVO reportVO : reportVOs)
					reports.add(LaTeXVOConverter.vo2ResourceView(reportVO));
				break;
			case PAGE:
				List<ResourceViewVO> pageVOs = latexReportParameters.get(key);
				for (ResourceViewVO pageVO : pageVOs)
					pages.add(LaTeXVOConverter.vo2ResourceView(pageVO));
				break;
			case BOX:
				List<ResourceViewVO> boxVOs = latexReportParameters.get(key);
				for (ResourceViewVO boxVO : boxVOs)
					boxes.add(LaTeXVOConverter.vo2ResourceView(boxVO));
				break;
			}
		}

		for (ResourceView tmpReport : reports) {

			// if the report already exists, modify it
			if (tmpReport.getResourceId() != null && tmpReport.getResourceId() > 0) {
				return modify(tmpReport.getResourceId(), reports, pages, boxes);
			}

			ResourceView report = new ResourceView();

			String reportCode = tmpReport.getOlapFunction();

			for (ResourceView tmpPage : pages) {

				if (tmpPage.getOlapFunction().equals(reportCode)) {

					ResourceView page = new ResourceView();

					String pageCode = tmpPage.getQuery();

					for (ResourceView tmpBox : boxes) {

						if (tmpBox.getQuery().equals(pageCode)) {

							// collect style

							ResourceView box = new ResourceView();
							for (ResourceViewSetting s : tmpBox.getSettings()) { // these
								// should
								// be
								// 2:
								// first
								// and
								// last
								// pixel
								// of
								// the
								// box
								resourceViewDao.save(s);
								box.addResourceViewSetting(s);
							}
							box.setType(tmpBox.getType());
							box.setSharingCode(SharingCode.Public.name());
							box.setQuery(String.valueOf(tmpBox.getResourceId())); // this
							// is
							// the
							// id
							// of
							// the
							// resource
							// to
							// be
							// put
							// in
							// this
							// spot
							// of
							// the
							// report
							box.setTitle(tmpBox.getTitle());
							resourceViewDao.save(box);

							NumericResourceViewSetting boxPointer = new NumericResourceViewSetting();
							boxPointer.setQuantity(Double.valueOf(box.getResourceId()));
							resourceViewDao.save(boxPointer);
							page.addResourceViewSetting(boxPointer);
						}

					}

					page.setType(tmpPage.getType());
					page.setSharingCode(SharingCode.Public.name());
					resourceViewDao.save(page);

					NumericResourceViewSetting pagePointer = new NumericResourceViewSetting();
					pagePointer.setQuantity(Double.valueOf(page.getResourceId()));
					resourceViewDao.save(pagePointer);
					report.addResourceViewSetting(pagePointer);

				}

			}

			report.setType(DesignerConstants.REPORT.name());
			report.setSharingCode(SharingCode.Public.name());

			resourceViewDao.save(report);
			reportID = report.getResourceId();
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, reportID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, reportID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, reportID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, reportID, true);

			LOGGER.info("LaTeX Report saved with ID: " + reportID);
		}

		return reportID;
	}

	private Long modify(Long reportID, List<ResourceView> reports, List<ResourceView> pages, List<ResourceView> boxes) throws FenixGWTException {

		ResourceView report = resourceViewDao.find(reportID);
		removeOldSettings(report);

		for (ResourceView tmpPage : pages) {

			ResourceView page = new ResourceView();

			String pageCode = tmpPage.getQuery();

			for (ResourceView tmpBox : boxes) {

				if (tmpBox.getQuery().equals(pageCode)) {

					ResourceView box = new ResourceView();
					for (ResourceViewSetting s : tmpBox.getSettings()) { // these
						// should
						// be
						// 2:
						// first
						// and
						// last
						// pixel
						// of
						// the
						// box
						resourceViewDao.save(s);
						box.addResourceViewSetting(s);
					}
					box.setType(tmpBox.getType());
					box.setSharingCode(SharingCode.Public.name());
					box.setQuery(String.valueOf(tmpBox.getResourceId())); // this
					// is
					// the
					// id
					// of
					// the
					// resource
					// to
					// be
					// put
					// in
					// this
					// spot
					// of
					// the
					// report
					box.setTitle(tmpBox.getTitle());
					resourceViewDao.save(box);

					NumericResourceViewSetting boxPointer = new NumericResourceViewSetting();
					boxPointer.setQuantity(Double.valueOf(box.getResourceId()));
					resourceViewDao.save(boxPointer);
					page.addResourceViewSetting(boxPointer);
				}

			}

			page.setType(tmpPage.getType());
			page.setSharingCode(SharingCode.Public.name());
			resourceViewDao.save(page);

			NumericResourceViewSetting pagePointer = new NumericResourceViewSetting();
			pagePointer.setQuantity(Double.valueOf(page.getResourceId()));
			resourceViewDao.save(pagePointer);
			report.addResourceViewSetting(pagePointer);

		}

		report.setType(DesignerConstants.REPORT.name());
		report.setSharingCode(SharingCode.Public.name());

		resourceViewDao.update(report);

		LOGGER.info("LaTeX Report updated with ID: " + reportID);

		return reportID;
	}

	private void removeOldSettings(ResourceView report) {
		List<ResourceViewSetting> pagePointers = report.getSettings();
		for (ResourceViewSetting pagePointer : pagePointers) {
			Long pageID = (long) (((NumericResourceViewSetting) pagePointer).getQuantity().doubleValue());
			ResourceView page = resourceViewDao.find(pageID);
			if (page != null) {
				List<ResourceViewSetting> boxPointers = page.getSettings();
				for (ResourceViewSetting boxPointer : boxPointers) {
					Long boxID = (long) (((NumericResourceViewSetting) boxPointer).getQuantity().doubleValue());
					ResourceView box = resourceViewDao.find(boxID);
					if (box != null)
						resourceViewDao.delete(box);
				}
				resourceViewDao.delete(page);
			}
		}
		resourceViewDao.update(report);
	}

	/* ********************************************************************************************************************************* */
	/* ********************************************************************************************************************************* */
	/*
	 * ************************************************************ L O A D
	 * ************************************************************
	 */
	/* ********************************************************************************************************************************* */
	/* ********************************************************************************************************************************* */

	public Map<String, List<ResourceViewVO>> load(Long reportID) throws FenixGWTException {

		Map<String, List<ResourceViewVO>> map = new HashMap<String, List<ResourceViewVO>>();
		List<ResourceViewVO> reports = new ArrayList<ResourceViewVO>();
		List<ResourceViewVO> pages = new ArrayList<ResourceViewVO>();
		List<ResourceViewVO> boxes = new ArrayList<ResourceViewVO>();

		// the report itself
		ResourceView report = resourceViewDao.find(reportID);
		ResourceViewVO reportVO = LaTeXVOConverter.resourceView2vo(report);
		reports.add(reportVO);
		map.put(DesignerConstants.REPORT.name(), reports);

		// read report's pointers to pages
		List<ResourceViewSetting> pagePointers = report.getSettings();
		for (ResourceViewSetting pagePointer : pagePointers) {

			Long pageID = (long) (((NumericResourceViewSetting) pagePointer).getQuantity().doubleValue());
			ResourceView page = resourceViewDao.find(pageID);

			if (page != null) {

				ResourceViewVO pageVO = LaTeXVOConverter.resourceView2vo(page);
				pages.add(pageVO);

				// read page's box pointers
				List<ResourceViewSetting> boxPointers = page.getSettings();
				for (ResourceViewSetting boxPointer : boxPointers) {

					Long boxID = (long) (((NumericResourceViewSetting) boxPointer).getQuantity().doubleValue());
					ResourceView box = resourceViewDao.find(boxID);

					if (box != null) {

						ResourceViewVO boxVO = LaTeXVOConverter.resourceView2vo(box);
						boxes.add(boxVO);

					}

				}

			}

		}

		map.put(DesignerConstants.PAGE.name(), pages);
		map.put(DesignerConstants.BOX.name(), boxes);

		return map;
	}

	public String exportPDF(DesignerVO vo, String language) throws FenixGWTException {

		StringBuilder sb = new StringBuilder();

		sb.append(latexBuilder.documentclass());
		sb.append(latexBuilder.usepackage(vo.getPages().get(0).getOrientation())); // how
		// to
		// change
		// orientation
		// at
		// each
		// page?
		sb.append(latexBuilder.defineColors(vo.getPages()));
		sb.append(latexBuilder.textblockSettings());
		sb.append(latexBuilder.beginDocument());

		for (int i = 0; i < vo.getPages().size(); i++) {
			PageVO pvo = vo.getPages().get(i);
			for (BoxVO bvo : pvo.getBoxes()) {
				List<Box> boxes = new ArrayList<Box>();
				boxes.add(vo2box(bvo));
				sb.append(latexBuilder.build(boxes, language));
			}
			if (i < vo.getPages().size() - 1) {
				sb.append("\\null\n");
				sb.append("\\newpage\n");
			}
		}

		sb.append(latexBuilder.endDocument());

		String filepath = exportPDF(sb.toString());
		return filepath;
	}

	private Box vo2box(BoxVO vo) {
		Box b = new Box();
		b.setEndX(vo.getEndX());
		b.setEndY(vo.getEndY());
		b.setStartX(vo.getStartX());
		b.setStartY(vo.getStartY());
		if (vo.getResourceID() != null)
			b.setResourceID(vo.getResourceID());
		if (vo.getResourceType() != null)
			b.setResourceType(ResourceType.valueOf(vo.getResourceType().name()));
		b.setCaption(vo.getCaption());
		b.setCaptionColor(vo.getCaptionColor());
		b.setCaptionFont(vo.getCaptionFont());
		b.setCaptionPosition(vo.getCaptionPosition());
		b.setCaptionSize(vo.getCaptionSize());
		b.setBoxBackgroundColor(vo.getBoxBackgroundColor());
		b.setBoxLineColor(vo.getBoxLineColor());
		return b;
	}

	public String exportPDF(String latexAreaContent) throws FenixGWTException {
		// String cleanLatexAreaContent =
		// HTML2LaTeX.cleanLatexAreaContent(latexAreaContent);
		if (containsSweave(latexAreaContent)) {
			return exportLatexPDF(latexAreaContent, true);
		} else {
			return exportLatexPDF(latexAreaContent, false);
		}
	}

	private String exportLatexPDF(String cleanLatexAreaContent, boolean isSweave) throws FenixGWTException {
		try {
			String pdfPath = null;
			if (isSweave)
				pdfPath = latexUtils.exportSweavePDF(cleanLatexAreaContent);
			else
				pdfPath = latexUtils.exportLatexPDF(cleanLatexAreaContent);
			File srcFile = new File(pdfPath);
			String filename = UUID.randomUUID() + ".pdf";
			File destFile = new File(dir + File.separator + filename);
			FileUtils.moveFile(srcFile, destFile);
			System.out.println("File moved to " + destFile.getAbsolutePath());
			return filename;
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public boolean containsSweave(String latexAreaContent) throws FenixGWTException {
		int sweaveIDX = latexAreaContent.indexOf("\\SweaveOpts");
		if (sweaveIDX > -1)
			return true;
		return false;
	}

	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setLatexUtils(LatexUtils latexUtils) {
		this.latexUtils = latexUtils;
	}

	public void setLatexBuilder(LatexBuilder latexBuilder) {
		this.latexBuilder = latexBuilder;
	}

	public void setResourceViewDao(ResourceViewDao resourceViewDao) {
		this.resourceViewDao = resourceViewDao;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

}