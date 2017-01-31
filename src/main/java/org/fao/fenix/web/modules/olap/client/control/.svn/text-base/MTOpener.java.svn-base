package org.fao.fenix.web.modules.olap.client.control;

import org.fao.fenix.web.modules.olap.client.view.MTDatasourceFieldSet;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;


public class MTOpener extends MTController {

	public static void load(OLAPParametersVo p, String olapTitle, String caller, String tinyMCEPanelID) {
		MTWindow w = new MTWindow(caller, tinyMCEPanelID);
		w.build();
		w.setMtID(p.getResourceViewID());
		w.getWindow().setHeading(olapTitle);
		MTDatasourceFieldSet fs = new MTDatasourceFieldSet();
		ResourceChildModel m = new ResourceChildModel();
		m.setId(String.valueOf(p.getDataSourceId()));
		w.getMtPanel().getMtDatasourcePanel().getModels().add(m);
		loadDatasetAgent(w, fs, p);
	}
	
	enum Axis {
		X, Y, Z, W, FILTER;
	}
	
}
