package org.fao.fenix.web.modules.amis.client.view.download;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.view.download.table.AMISDownloadOutputType;
import org.fao.fenix.web.modules.amis.client.view.download.table.AMISDownloadTablePanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class AMISDownload {
	
	ContentPanel panel;
	
	ContentPanel domainsPanel;
	
	ContentPanel selectionPanel;
	
	AMISDownloadTitlePanel titlePanel;
	
	AMISDownloadTablePanel tablePanel;
	
	AMISDownloadDomainPanel domains;

    AMISDownloadSelectorDataSources datasources;

    AMISDownloadComboSelectorDataSources datasourcesCombo;
	
	AMISDownloadSelectorAreas areas;

    AMISDownloadComboSelectorAreas areasCombo;

	AMISDownloadSelectorPanel years;
	
	AMISDownloadSelectorPanel elements;
	
	AMISDownloadSelectorPanel elementsList;

    AMISDownloadComboSelectorItems itemsCombo;

	AMISDownloadSelectorItems items;
	
	AMISDownloadOptions options;

	protected Button exportButton;
	
	protected Button tableButton;

	AMISDownloadOutputType outputType;

    List<AMISCodesModelData> selectedDatasources = new ArrayList<AMISCodesModelData>();
	
	
	public AMISDownload() {
		System.out.println(" AMISFaostatDownload Constructor AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
				
		domainsPanel = new ContentPanel();
		domainsPanel.setBodyBorder(false);
		domainsPanel.setHeaderVisible(false);
		
		selectionPanel = new ContentPanel();
		selectionPanel.setAutoWidth(true);
		selectionPanel.setAutoHeight(true);
		selectionPanel.setBodyBorder(false);
		selectionPanel.setHeaderVisible(false);
		
		tablePanel = new AMISDownloadTablePanel();
		
		domains = new AMISDownloadDomainPanel();

		options = new AMISDownloadOptions();
		
		//outputType = new AMISDownloadOutputType();
		
		titlePanel = new AMISDownloadTitlePanel();
	}

	public ContentPanel build() {
		
		panel.add(new Html(""));
		panel.add(buildMainPanel());
		
		panel.add(FormattingUtils.addVSpace(10));
		
		panel.add(buildTablePanel());
	
		return panel;
	}
	
	protected HorizontalPanel buildTablePanel() {
		HorizontalPanel h = new HorizontalPanel();
		h.add(FormattingUtils.addHSpace(20));
		h.add(tablePanel.build());
		return h;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildDomains());
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(FormattingUtils.addVSpace(5));
		verticalPanel.add(titlePanel.build());
		verticalPanel.add(FormattingUtils.addVSpace(10));
		verticalPanel.add(selectionPanel);
		p.add(verticalPanel);

		return p;
	}
	
	// SECOND STYLE
	public void buildSelectors(AMISCodesModelData code){
		selectionPanel.removeAll();

		VerticalPanel p = new VerticalPanel();
		//p.add(FormattingUtils.addVSpace(5));
		
		HorizontalPanel h1 = new HorizontalPanel();
		HorizontalPanel h2 = new HorizontalPanel();
		VerticalPanel v1 = new VerticalPanel();
		VerticalPanel v2 = new VerticalPanel();

        System.out.println("&&&&&&&&&&&&&&& AMISDownload: buildSelectors IS CALLED!!! ");
		h1.add(buildCountriesYears(code));
		h1.add(buildElementsItems(code));
		v1.add(h1);
		v1.add(FormattingUtils.addVSpace(20));
		
		
		
		h2.add(FormattingUtils.addHSpace(10));
		//h2.add(outputType.build(options));

		//h2.add(FormattingUtils.addHSpace(20));
		h2.add(options.build());
		h2.add(FormattingUtils.addHSpace(5));
		v2.add(h2);

		p.add(v1);
		p.add(v2);
		p.add(FormattingUtils.addVSpace(10));
		//p.add(addButtons());
		p.add(addButtons(code));
		
		selectionPanel.add(p);
		
		selectionPanel.layout();
	}
	
	protected HorizontalPanel buildCountriesYears(AMISCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildAreas(code));
		p.add(buildYears(code));		
		return p;
	}


    protected HorizontalPanel buildCountries(AMISCodesModelData code) {
        HorizontalPanel p = new HorizontalPanel();
        p.setSpacing(10);

        p.add(buildAreas(code));

        return p;
    }

	
	protected HorizontalPanel buildElementsItems(AMISCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildItems(code));		
		p.add(buildElements(code));
//		p.add(buildElementsList(code));
	
		return p;
	}

    public ContentPanel buildAreas() {
        areas = new AMISDownloadSelectorAreas();
        return areas.build(this,"200px", "130px");
    }

    public ContentPanel buildAreas(AMISCodesModelData code) {
		areas = new AMISDownloadSelectorAreas();
        return areas.build(this, code,"200px", "130px");
	}


    public ContentPanel buildDataSources(AMISCodesModelData code) {
        datasources = new AMISDownloadSelectorDataSources();
        return datasources.build(this, "","200px", "130px");
    }

    private ContentPanel buildYears(AMISCodesModelData code) {
		years = new AMISDownloadSelectorPanel();
		return years.build(this, code, "Years", AMISConstants.DOWNLOAD_TIMERANGE.toString(), false, true, "170px", "130px");
	}
	
	public ContentPanel buildItems(AMISCodesModelData code) {
		items = new AMISDownloadSelectorItems();
		return items.build(this, code,"190px", "130px");
	}

    public ContentPanel buildItems() {
        items = new AMISDownloadSelectorItems();
        return items.build(this,"190px", "130px");
    }

    public ContentPanel buildItemsCombo() {
        itemsCombo = new AMISDownloadComboSelectorItems();
        return itemsCombo.build(this,"190px", "130px");
    }


    public ContentPanel buildItemsCombo(AMISCodesModelData code) {
        itemsCombo = new AMISDownloadComboSelectorItems();
        return itemsCombo.build(this, code,"190px", "130px");
    }

    public ContentPanel buildAreasCombo(AMISCodesModelData code) {
        areasCombo = new AMISDownloadComboSelectorAreas();
        return areasCombo.build(this, code,"190px", "130px");
    }

     public ContentPanel buildAreasCombo() {
        areasCombo = new AMISDownloadComboSelectorAreas();
        return areasCombo.build(this,"190px", "130px");
    }

    public ContentPanel buildDataSourcesCombo() {
        datasourcesCombo = new AMISDownloadComboSelectorDataSources();
        return datasourcesCombo.build(this,"190px", "130px");
    }


    private ContentPanel buildElements(AMISCodesModelData code) {
		elements = new AMISDownloadSelectorPanel();
		return elements.build(this, code, "Elements", AMISConstants.AMIS_ELEMENTS.toString(), false, false, "210px", "130px");
	}
	
	private ContentPanel buildElementsList(AMISCodesModelData code) {
		elementsList = new AMISDownloadSelectorPanel();
		return elementsList.build(this, code, "Elements List", AMISConstants.CODING_SYSTEM_FAOSTAT_ELEMENT_LIST.toString(), false, false, "190px", "130px");
	}

	
	protected HorizontalPanel addButtons(AMISCodesModelData selectedDataSourceModel) {
		
		ArrayList<String> codeList = selectedDataSourceModel.getCodeList();
		
		HorizontalPanel p = new HorizontalPanel();
        p.add(FormattingUtils.addHSpace(300));

        String exportLabel = "Export to CSV";

        if(AMISDownloadController.isFoodBalance)
            exportLabel = "Export to Excel";

		exportButton = new Button(exportLabel);
		exportButton.setIconStyle("sendToExcel");
		
		exportButton.addSelectionListener(AMISDownloadController.exportCSV(this, codeList, selectedDataSourceModel.getLabel()));
		p.add(exportButton);
		
		p.add(FormattingUtils.addHSpace(10));
		
		tableButton = new Button("View Table");
		tableButton.addSelectionListener(AMISDownloadController.showTable(this, codeList));
		tableButton.setIconStyle("tableIcon");

        p.add(tableButton);

		
		return p;
	}


    protected HorizontalPanel addButtons() {


        HorizontalPanel p = new HorizontalPanel();
        p.add(FormattingUtils.addHSpace(300));

        String exportLabel = "Export to CSV";

        if(AMISDownloadController.isFoodBalance)
            exportLabel = "Export to Excel";

        exportButton = new Button(exportLabel);
        exportButton.setIconStyle("sendToExcel");

        exportButton.addSelectionListener(AMISDownloadController.exportCSV(this));
        p.add(exportButton);

        p.add(FormattingUtils.addHSpace(10));

      // tableButton = new Button("View Table");
       // tableButton.addSelectionListener(AMISDownloadController.showTable(this, codeList));
      //  tableButton.setIconStyle("tableIcon");

      //  p.add(tableButton);


        return p;
    }
	
	
	protected ContentPanel buildDomains() {
		return domains.build(this);
	}

    protected ContentPanel buildDataSources() {
        System.out.println("------------ AMISDownload buildDataSources() !!!! ");
        datasources = new AMISDownloadSelectorDataSources();
        return datasources.build(this, "", "200px", "130px");
    }

	public ContentPanel getPanel() {
		return panel;
	}

	public ContentPanel getDomainsPanel() {
		return domainsPanel;
	}

	public ContentPanel getSelectionPanel() {
		return selectionPanel;
	}

	public AMISDownloadDomainPanel getDomains() {
		return domains;
	}

    public AMISDownloadSelectorDataSources getDataSources() {
        return datasources;
    }

	public AMISDownloadSelectorPanel getYears() {
		return years;
	}

	public AMISDownloadSelectorPanel getElements() {
		return elements;
	}

	public AMISDownloadSelectorAreas getAreas() {
        return areas;
	}

	public AMISDownloadTablePanel getTablePanel() {
		return tablePanel;
	}

	public AMISDownloadSelectorItems getItems() {
		return items;
	}

    public AMISDownloadComboSelectorItems getItemsCombo() {
        return itemsCombo;
    }


    public AMISDownloadComboSelectorAreas getAreasCombo() {
        return areasCombo;
    }


	public AMISDownloadOptions getOptions() {
		return options;
	}

	public AMISDownloadOutputType getOutputType() {
		return outputType;
	}

	public void setTablePanel(AMISDownloadTablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public AMISDownloadSelectorPanel getElementsList() {
		return elementsList;
	}

	public AMISDownloadTitlePanel getTitlePanel() {
		return titlePanel;
	}

    public List<AMISCodesModelData> getSelectedDatasources() {
        return selectedDatasources;
    }

    public void setSelectedDatasources(List<AMISCodesModelData> selectedDatasources) {
        this.selectedDatasources = selectedDatasources;
    }
}
