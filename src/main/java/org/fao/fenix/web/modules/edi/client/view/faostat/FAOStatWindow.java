package org.fao.fenix.web.modules.edi.client.view.faostat;

import org.fao.fenix.web.modules.edi.client.control.FAOStatController;
import org.fao.fenix.web.modules.edi.client.view.EDIWindow;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

public class FAOStatWindow extends EDIWindow {

	private FAOStatTabPanel faoStatTabPanel;
	
	private FAOStatButtonsPanel faoStatButtonsPanel;
	
	private Long datasetID;
	
	public FAOStatWindow() {
		super();
		faoStatTabPanel = new FAOStatTabPanel();
		faoStatButtonsPanel = new FAOStatButtonsPanel();
		this.setButtonsPanel(faoStatButtonsPanel);
		this.setTabPanel(faoStatTabPanel);
	}
	
	@Override
	public void build(EDISettings datasource) {
		super.build(datasource);
		faoStatTabPanel.getDataPanel().getGroupList().addChangeHandler(FAOStatController.groupList(this));
		faoStatTabPanel.getDataPanel().getDomainList().addChangeHandler(FAOStatController.domainList(this));
		faoStatTabPanel.getDataPanel().getItemList().addChangeHandler(FAOStatController.itemList(this));
		faoStatTabPanel.getPanel().setSelection(faoStatTabPanel.getDataPanel().getTabItem());
		faoStatButtonsPanel.getImportDatasetButton().addSelectionListener(FAOStatController.importFAOStatDataset(this));
		faoStatButtonsPanel.getGetExcelButton().addSelectionListener(FAOStatController.exportExcel(this, true, false));
		faoStatButtonsPanel.getGetExcelWithCodesButton().addSelectionListener(FAOStatController.exportExcel(this, true, true));
	}

	public FAOStatTabPanel getFaoStatTabPanel() {
		return faoStatTabPanel;
	}

	public FAOStatButtonsPanel getFaoStatButtonsPanel() {
		return faoStatButtonsPanel;
	}

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}
	
}