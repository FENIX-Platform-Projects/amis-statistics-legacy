package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata;

import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.abbreviations.FAOSTATMetadataAbbreviation;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.classification.FAOSTATMetadataClassification;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.currency.FAOSTATMetadataCurrency;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.glossary.FAOSTATMetadataGlossary;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.methodology.FAOSTATMetadataMethodology;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.units.FAOSTATMetadataUnits;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualizeSecondaryMenu;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATMetadata {
	
	
	public FAOSTATMetadata(FAOSTATCurrentView view) {
		buildMetadataSubMenu(view);
    }
	
	public void build(FAOSTATCurrentView view) {
		System.out.println("building view "+view);
		
		switch (view) {		
			case METADATA_ABBREVIATIONS:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATMetadataAbbreviation().build());
			break;
			case METADATA_GLOSSARY:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATMetadataGlossary().build());
			break;
			case METADATA_CLASSIFICATION: 
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATMetadataClassification().build());	
				break;
			case METADATA_METHODOLOGY: 
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATMetadataMethodology().build());	
				break;
			case METADATA_UNITS:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATMetadataUnits().build());
			break;
			case METADATA_CURRENCY:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATMetadataCurrency().build());
			break;
		}
		
	}
	
	private static void buildMetadataSubMenu(FAOSTATCurrentView view) {
		System.out.println("building sub menu");
		
		if ( RootPanel.get("SECONDARY_MENU_METADATA").getWidgetCount() > 0 ) {
			RootPanel.get("SECONDARY_MENU_METADATA").remove(RootPanel.get("SECONDARY_MENU_METADATA").getWidget(0));
		}
		
		RootPanel.get("SECONDARY_MENU_METADATA").add(new FAOSTATMetadataSecondaryMenu().build(view));

	}
	
	
}
