package org.fao.fenix.web.modules.edi.client.view.faostat;

import org.fao.fenix.web.modules.edi.client.control.EDIController;
import org.fao.fenix.web.modules.edi.client.view.EDISettingsPanel;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

public class FAOStatSettingsPanel extends EDISettingsPanel {

	public FAOStatSettingsPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		for (int i = 0 ; i < this.getConnectionTypeList().getItemCount() ; i++)
			if (this.getConnectionTypeList().getValue(i).equals(EDISettings.JDBC.name()))
				this.getConnectionTypeList().setSelectedIndex(i);
		EDIController.connectionTypeAgent(this);
		for (int i = 0 ; i < this.getDatabaseSettingsPanel().getDatabaseDriver().getItemCount() ; i++) {
			if (this.getDatabaseSettingsPanel().getDatabaseDriver().getItemText(i).equals(EDISettings.SQLSERVER2000.name()))
				this.getDatabaseSettingsPanel().getDatabaseDriver().setSelectedIndex(i);
		}
		for (int i = 0 ; i < this.getDatabaseSettingsPanel().getDatasourceList().getItemCount() ; i++)
			if (this.getDatabaseSettingsPanel().getDatasourceList().getValue(i).equals(EDISettings.FAOSTAT.name()))
				this.getDatabaseSettingsPanel().getDatasourceList().setSelectedIndex(i);
		this.getDatabaseSettingsPanel().getDbUrl().setValue("jdbc:sqlserver://");
		this.getDatabaseSettingsPanel().getDbServerName().setValue("FAOSTAT-GLBL\\Dissemination");
		this.getDatabaseSettingsPanel().getDbName().setValue("Warehouse");
		this.getDatabaseSettingsPanel().getDbUsername().setValue("Warehouse");
		this.getDatabaseSettingsPanel().getDbPassword().setValue("w@reh0use");
	}
	
}