package org.fao.fenix.web.modules.chartdesigner.client.control;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartAxisPanel;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

public class ChartDesignerValidator {

	public static boolean isValid(ChartDesignerWindow w) throws FenixGWTException {
		if (countSelectedDatasets(w) < 1)
			throw new FenixGWTException("Please Select at Least One Datasource.");
		else if (w.getChartDesignerPanel().getxAxisPanel().getDimensionList().getSelectedIndex() < 0)
			throw new FenixGWTException("Please Select at Least One Dimension on the X-Axis.");
		else if (w.getChartDesignerPanel().getxAxisPanel().getValuesList().getSelectedIndex() < 0)
			throw new FenixGWTException("Please Select at Least One Value on the X-Axis.");
		validateYAxes(w);
		return true;
	}
	
	private static void validateYAxes(ChartDesignerWindow w) throws FenixGWTException {
		for (ChartAxisPanel ap : w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().values()) {
			if (ap.getDimensionList().getSelectedIndex() < 0)
				throw new FenixGWTException("Please Select at Least One Dimension on the Y-Axis.");
			else if (ap.getValuesList().getSelectedIndex() < 0)
				throw new FenixGWTException("Please Select at Least One Value on the Y-Axis.");
		}
	}
	
	private static int countSelectedDatasets(ChartDesignerWindow w) {
		int counter = 0;
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets())
			if (fs.getUseThisDataset().getValue())
				counter++;
		return counter;
	}
	
}