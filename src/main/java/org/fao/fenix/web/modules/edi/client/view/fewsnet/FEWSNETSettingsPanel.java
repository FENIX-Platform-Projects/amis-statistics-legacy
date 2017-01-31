package org.fao.fenix.web.modules.edi.client.view.fewsnet;

import org.fao.fenix.web.modules.edi.client.control.EDIController;
import org.fao.fenix.web.modules.edi.client.view.EDISettingsPanel;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

public class FEWSNETSettingsPanel extends EDISettingsPanel {

	public FEWSNETSettingsPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		for (int i = 0 ; i < this.getConnectionTypeList().getItemCount() ; i++)
			if (this.getConnectionTypeList().getValue(i).equals(EDISettings.HTTP.name()))
				this.getConnectionTypeList().setSelectedIndex(i);
		EDIController.connectionTypeAgent(this);
		this.getHttpSettingsPanel().getBaseUrl().setValue("http://igskmncngs600.cr.usgs.gov/ftp2/haiti/");
	}
	
}