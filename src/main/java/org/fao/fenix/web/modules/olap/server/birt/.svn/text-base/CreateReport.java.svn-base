package org.fao.fenix.web.modules.olap.server.birt;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.AutoTextHandle;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.elements.AutoText;
import org.eclipse.birt.report.model.elements.MasterPage;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class CreateReport {

	private ReportDesignHandle designHandle = null;

	private ElementFactory designFactory = null;
	
	private static final Logger LOGGER = Logger.getLogger(CreateReport.class);
	
	private static final String BANNER_WIDTH = "17.75cm";
	
	private static final String BANNER_HEIGHT = "1.5cm";
	
	public String createReport(String report, String orientation) {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();

		try {

			designHandle = session.createDesign();
			designFactory = designHandle.getElementFactory();

			ImageHandle imageHandle = designHandle.getElementFactory().newImage("Header");
			imageHandle.setWidth(BANNER_WIDTH);
			imageHandle.setHeight(BANNER_HEIGHT);
			if (orientation.equalsIgnoreCase("landscape")) {
				imageHandle.setWidth(BANNER_HEIGHT);
				imageHandle.setHeight(BANNER_WIDTH);
			}
			imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ImgForTemplate/fsatmis_banner.png\"");

			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");
			simpleMasterPage.setPageType("a4");
			simpleMasterPage.setOrientation(orientation);
			simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, "1.45cm");
			simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, "1.45cm");
			simpleMasterPage.getPageHeader().add(imageHandle);

			// add footer
			simpleMasterPage.getPageFooter().add(createFooter());

			designHandle.getMasterPages().add(simpleMasterPage);

//			createDataSources();

			// createStyles();

//			createDataSets();

			createBody(report);

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

			designHandle.setProperty("outputFormat", "PDF");
			designHandle.close();
			Platform.shutdown();

//			LOGGER.info("OLAP Export Complete!");

		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
		}

//		LOGGER.info(System.getProperty("java.io.tmpdir") + File.separator + rep);
//		LOGGER.info(rep.substring(0, (rep.length() - 10)));
//		return rep.substring(0, (rep.length() - 10));
		return System.getProperty("java.io.tmpdir") + File.separator + rep;
	}
	
	private void createBody(String report) throws SemanticException {

		// legend label
//		TextItemHandle legendLabel = designHandle.getElementFactory().newTextItem("legend");
//		legendLabel.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//		legendLabel.setContent("<div style='font-weight: bold; font-size: 12pt; color: black;'>Legend</div><br>");
//		designHandle.getBody().add(legendLabel);
//		legendLabel.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
//		legendLabel.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");

		// legend
		TextItemHandle legendBody = designHandle.getElementFactory().newTextItem("legendBody");
		legendBody.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		legendBody.setContent(report);
		designHandle.getBody().add(legendBody);
		legendBody.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
	}
	
	private GridHandle createFooter() throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("dataGrid", 5, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row1 = (RowHandle) dataGridHandle.getRows().get(0);

		CellHandle gridCellHandle = (CellHandle) row1.getCells().get(0);
		TextItemHandle fenix = designHandle.getElementFactory().newTextItem("Fenix");
		fenix.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_PLAIN);
		fenix.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		fenix.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		fenix.setContent("http://lprapp08.fao.org:8080/fenix-web");
		gridCellHandle.getContent().add(fenix);

		gridCellHandle = (CellHandle) row1.getCells().get(1);
		AutoTextHandle autoTextPage = designHandle.getElementFactory().newAutoText("Pages");
		autoTextPage.setProperty(AutoText.AUTOTEXT_TYPE_PROP, "page-number");
		autoTextPage.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		autoTextPage.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		gridCellHandle.getContent().add(autoTextPage);

		gridCellHandle = (CellHandle) row1.getCells().get(2);
		TextItemHandle slash = null;
		slash = designHandle.getElementFactory().newTextItem("Slash");
		slash.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_PLAIN);
		slash.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		slash.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		slash.setContent("/");
		gridCellHandle.getContent().add(slash);

		gridCellHandle = (CellHandle) row1.getCells().get(3);
		AutoTextHandle autoTextTotalPages = designHandle.getElementFactory().newAutoText("Total");
		autoTextTotalPages.setProperty(AutoText.AUTOTEXT_TYPE_PROP, "total-page");
		autoTextTotalPages.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		autoTextTotalPages.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		gridCellHandle.getContent().add(autoTextTotalPages);

		gridCellHandle = (CellHandle) row1.getCells().get(4);
		TextItemHandle date = designHandle.getElementFactory().newTextItem("Date");
		date.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_PLAIN);
		date.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		date.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		date.setContent("Created on " + FieldParser.parseDate(new Date()));
		date.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		gridCellHandle.getContent().add(date);

		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "45%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "45%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "2%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "1%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "2%");

		return dataGridHandle;
	}
	
}
