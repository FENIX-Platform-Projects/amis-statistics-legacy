package org.fao.fenix.web.modules.birt.server.utils.report;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class AddTableToReport {
	
	private static final Logger LOGGER = Logger.getLogger(AddTableToReport.class);

	public static void getTable(String datasetCode, String datasetID, String rptDesign, int objectPosition, String reportType, String tableHTML) {
		LOGGER.info("adding table to a report: " + datasetID + " | " + datasetCode);
		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);

		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designHandle = null;
		try {
			designHandle = session.openDesign(nameReport);
		} catch (Exception e) {
			LOGGER.error("Report not opened!\nReason is " + e.toString());
		}

		try {

			List<Integer> gridReference;
			TextItemHandle text = designHandle.getElementFactory().newTextItem(datasetID);
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent(tableHTML);
			text.setName(String.valueOf(datasetCode));
			text.setDisplayName("table");
			text.setDisplayNameKey(String.valueOf(datasetID));
			
			if (reportType.equals("blankTemplate")) {
				setBorderText(text);
				designHandle.getBody().add(text);
			} 
			
			else if (reportType.equals("template1")) {
				gridReference = FindGridReference.template1(objectPosition);
				GridHandle reportGridHandle = (GridHandle) designHandle.findElement("Grid" + gridReference.get(0));
				RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
				CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
				if (gridCellHandle.getContent().getCount() != 0) {
					gridCellHandle.getContent().drop(gridReference.get(2));
				}
				gridCellHandle.getContent().add(text);
			} 
			
			else if (reportType.equals("template2")) {
				gridReference = FindGridReference.template2(objectPosition);
				GridHandle reportGridHandle = (GridHandle) designHandle.findElement("Grid" + gridReference.get(0));
				RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
				CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
				if (gridCellHandle.getContent().getCount() != 0) {
					gridCellHandle.getContent().drop(gridReference.get(2));
				}
				gridCellHandle.getContent().add(text);
			}

			designHandle.saveAs(nameReport);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void setBorderText(TextItemHandle textHandle) throws SemanticException {
		textHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		textHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		textHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		textHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		textHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		textHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		textHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		textHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
	}

}
