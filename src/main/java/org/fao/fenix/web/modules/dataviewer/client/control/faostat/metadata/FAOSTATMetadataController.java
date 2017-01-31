package org.fao.fenix.web.modules.dataviewer.client.control.faostat.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATTableController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.FAOSTATMetadata;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.classification.FAOSTATMetadataClassification;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.methodology.FAOSTATMetadataMethodology;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATQueryVOBuilderVisualize;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class FAOSTATMetadataController  {

	static FAOSTATMetadata metadataView;

	/**
	 *
	 * This method creates the metadata panel
	 *
	 */

   public static void callMetadataView() {

		//initialize menu
	     metadataView = new FAOSTATMetadata(FAOSTATCurrentView.METADATA_ABBREVIATIONS);

		callMetadataViewAgent(FAOSTATCurrentView.METADATA_ABBREVIATIONS);
	}


   public static void callMetadataSubView(FAOSTATCurrentView subView) {

	   System.out.println("callMetadataSubView: call subview");

	   //initialize menu
	   metadataView = new FAOSTATMetadata(subView);

	   callMetadataViewAgent(subView);
   }

	public static void callMetadataViewAgent(FAOSTATCurrentView view) {
		metadataView.build(view);
	}

	public static Listener<ComponentEvent> callMetadataView(final FAOSTATCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callMetadataViewAgent(view);
			}
		};
	}

	public static void getMetadataDomainsAgent(final TreeStore treeStore) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ALL_METADATA_GROUPS_DOMAINS.toString());
		FAOSTATConstants.setFAOSTATDBSettings(qvo);

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {

					treeStore.removeAll();

					for(DWCodesModelData topcode : vo.getCodesHierachy().keySet()) {
						treeStore.add(topcode, true);
						treeStore.add(topcode, vo.getCodesHierachy().get(topcode), true);
					}
				}

				public void onFailure(Throwable arg0) {
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getMetadataMethodologyTypesAgent(final ListStore<DWCodesModelData> listStore,  final ListView<DWCodesModelData> view, final FAOSTATMetadataMethodology methodology) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ALL_METADATA_METHODOLOGIES.toString());
		FAOSTATConstants.setFAOSTATDBSettings(qvo);

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {

					listStore.removeAll();	
					for(DWCodesModelData topcode : vo.getCodes()) {
						listStore.add(topcode);
					}
					
					//default value, on load
					view.getSelectionModel().select(0, false);
					FAOSTATMetadataController.getMethodologyDetails(methodology, listStore.getAt(0));
					
				}

				public void onFailure(Throwable arg0) {
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


public static void getClassification(final FAOSTATMetadataClassification metadata, final DWCodesModelData domain, final DWCodesModelData group) {
		metadata.getDisplayPanel().removeAll();

		List<String> selects = new ArrayList<String>();
		selects.add("ItemCode");
		selects.add("ItemName" + FAOSTATConstants.faostatLanguage);
		selects.add("ItemDescription" + FAOSTATConstants.faostatLanguage);


		List<String> froms = new ArrayList<String>();
		froms.add("Metadata_Item");

		List<String> orderBys = new ArrayList<String>();
		orderBys.add("ItemName"+ FAOSTATConstants.faostatLanguage);

		Map<String,String> groups = new HashMap<String, String>();
		groups.put(group.getCode(), group.getCode());

		Map<String,String> domains = new HashMap<String, String>();
		domains.put(domain.getCode(), domain.getCode());

		Map<String,String> handleNullForFieldMap = new HashMap<String, String>();
		handleNullForFieldMap.put("ItemDescription" + FAOSTATConstants.faostatLanguage, "ItemDescription" + FAOSTATConstants.faostatLanguage);

		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(FAOSTATLanguage.print().classificationOf()+" "+domain.getLabel(), DataViewerBoxContent.FAOSTAT_METADATA_TABLE, selects, froms, orderBys, groups, domains, handleNullForFieldMap, null);

		List<String> columns = new ArrayList<String>();
		columns.add(FAOSTATLanguage.print().itemCode());
		columns.add(FAOSTATLanguage.print().itemname());
		columns.add(FAOSTATLanguage.print().definition());
		qvo.setTableHeaders(columns);


		VerticalPanel v = new VerticalPanel();

		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
        v.add(panel);

        metadata.getDisplayPanel().add(v);


		FAOSTATTableController.addTable(panel, qvo, "800px", "600px");

		metadata.getDisplayPanel().getLayout().layout();

	}



	public static void getUnits(final ContentPanel panel) {
		panel.removeAll();

		List<String> selects = new ArrayList<String>();
		selects.add("UnitAbbreviation"+ FAOSTATConstants.faostatLanguage);
		selects.add("UnitTitle"+ FAOSTATConstants.faostatLanguage);

		List<String> froms = new ArrayList<String>();
		froms.add("Metadata_Unit");

		List<String> orderBys = new ArrayList<String>();
		orderBys.add("UnitTitle"+ FAOSTATConstants.faostatLanguage);

		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(FAOSTATLanguage.print().standardUnitsSymbolsUsedInFAOSTAT(), DataViewerBoxContent.FAOSTAT_METADATA_TABLE, selects, froms, orderBys, null, null, null, null);

		List<String> columns = new ArrayList<String>();
		columns.add(FAOSTATLanguage.print().abbreviation());
		columns.add(FAOSTATLanguage.print().title());
		qvo.setTableHeaders(columns);

		FAOSTATTableController.addTable(panel, qvo, "600px", "600px");
	}

	public static void getLocalCurrency(final ContentPanel panel) {
		panel.removeAll();

		List<String> selects = new ArrayList<String>();
		selects.add("faocountrycode");
		//selects.add("countryname" + FAOSTATConstants.faostatLanguage.toLowerCase());
		selects.add("countrynamee");
		selects.add("isocurrencycode");
		//selects.add("currencyname" + FAOSTATConstants.faostatLanguage.toLowerCase());
		selects.add("currencynamee");
		selects.add("firstyearintofaostat");
		//selects.add("lastyearintofaostat"+ FAOSTATConstants.faostatLanguage.toLowerCase());
		selects.add("lastyearintofaostate");
		selects.add("conversionrates");
		selects.add("conversionoldnewcurrency");
		//selects.add("comments" + FAOSTATConstants.faostatLanguage.toLowerCase());
		selects.add("commentse");

		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(FAOSTATLanguage.print().currencyTableTitle(), "FENIX_METADATA_TABLE", "FENIX_METADATA_TABLE", selects);

		qvo.setFenixDatasetCode("FAOSTAT_CURRENCY");
		qvo.setDescription(FAOSTATLanguage.print().currencyTableDefinition());

		List<String> columns = new ArrayList<String>();
		for(String select: qvo.getSelects())
			columns.add(FAOSTATLanguage.print().getString(select.toLowerCase()));

		qvo.setTableHeaders(columns);

		FAOSTATConstants.setFAOSTATDBSettings(qvo);


		FAOSTATTableController.addTable(panel, qvo, "1000px", "600px");

	}

	public static void getAbbreviations(final ContentPanel panel) {
		panel.removeAll();

		List<String> selects = new ArrayList<String>();
		selects.add("AbbreviationTitle"+ FAOSTATConstants.faostatLanguage);
		selects.add("AbbreviationDefinition"+ FAOSTATConstants.faostatLanguage);

		List<String> froms = new ArrayList<String>();
		froms.add("Metadata_Abbreviation");

		List<String> orderBys = new ArrayList<String>();
		orderBys.add("AbbreviationTitle"+ FAOSTATConstants.faostatLanguage);

		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(FAOSTATLanguage.print().abbreviationList(), DataViewerBoxContent.FAOSTAT_METADATA_TABLE, selects, froms, orderBys, null, null, null, null);

		List<String> columns = new ArrayList<String>();
		columns.add(FAOSTATLanguage.print().acronym());
		columns.add(FAOSTATLanguage.print().definition());
		qvo.setTableHeaders(columns);

		FAOSTATTableController.addTable(panel, qvo, "1000px", "600px");

	}



	public static void getGlossary(final ContentPanel panel) {
		panel.removeAll();

		List<String> selects = new ArrayList<String>();
		selects.add("GlossaryName"+ FAOSTATConstants.faostatLanguage);
		selects.add("GlossaryDefinition"+ FAOSTATConstants.faostatLanguage);
		selects.add("GlossarySource"+ FAOSTATConstants.faostatLanguage);

		List<String> froms = new ArrayList<String>();
		froms.add("Metadata_Glossary");

		List<String> orderBys = new ArrayList<String>();
		orderBys.add("GlossaryName"+ FAOSTATConstants.faostatLanguage);

		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(FAOSTATLanguage.print().glossaryList(), DataViewerBoxContent.FAOSTAT_METADATA_TABLE, selects, froms, orderBys, null, null, null, null);

		List<String> columns = new ArrayList<String>();
		columns.add(FAOSTATLanguage.print().title());
		columns.add(FAOSTATLanguage.print().definition());
		columns.add(FAOSTATLanguage.print().sources());
		qvo.setTableHeaders(columns);

		FAOSTATTableController.addTable(panel, qvo, "1000px", "600px");

	}



   /** public static void getMethodologyDetails(final FAOSTATMetadataMethodology methodology, final DWCodesModelData group) {
    	methodology.getDisplayPanel().removeAll();

		List<String> selects = new ArrayList<String>();
		selects.add("MethodologyTitle"+ FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyNote" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyCoverage" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyReferences" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyCollection" + FAOSTATConstants.faostatLanguage);
	
		List<String> froms = new ArrayList<String>();
		froms.add("Metadata_Methodology");
		
		Map<String,String> handleNullForFieldMap = new HashMap<String, String>();
		handleNullForFieldMap.put("ItemDescription" + FAOSTATConstants.faostatLanguage, "ItemDescription" + FAOSTATConstants.faostatLanguage);
		
		Map<String,List<String>> filterMap = new HashMap<String, List<String>>();
		List<String> filterValues = new ArrayList<String>();
		filterValues.add(group.getCode());
		
		filterMap.put("MethodologyCode", filterValues);

		List<String> orderBys = new ArrayList<String>();
		orderBys.add("MethodologyTitle"+ FAOSTATConstants.faostatLanguage);
		
		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(group.getLabel(), DataViewerBoxContent.FAOSTAT_METADATA_TABLE, selects, froms, orderBys, null, null,handleNullForFieldMap, filterMap, null);

		List<String> columns = new ArrayList<String>();
		columns.add(FAOSTATLanguage.print().itemCode());
		columns.add(FAOSTATLanguage.print().itemname());
		columns.add(FAOSTATLanguage.print().definition());
		columns.add(FAOSTATLanguage.print().definition());
		columns.add(FAOSTATLanguage.print().definition());
		columns.add(FAOSTATLanguage.print().definition());
		
		qvo.setTableHeaders(columns);


		VerticalPanel v = new VerticalPanel();

		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
        v.add(panel);

        methodology.getDisplayPanel().add(v);


		FAOSTATTableController.addTable(panel, qvo, "900px", "600px");

		methodology.getDisplayPanel().getLayout().layout();

	}**/

    public static void getMethodologyDetails(final FAOSTATMetadataMethodology methodology, final DWCodesModelData method) {
    	methodology.getDisplayPanel().removeAll();
    	 
		List<String> selects = new ArrayList<String>();
		selects.add("MethodologyNote" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyCoverage" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyReferences" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyCollection" + FAOSTATConstants.faostatLanguage);
		selects.add("MethodologyEstimation" + FAOSTATConstants.faostatLanguage);

		List<String> froms = new ArrayList<String>();
		froms.add("Metadata_Methodology");
		
		Map<String,String> handleNullForFieldMap = new HashMap<String, String>();
		handleNullForFieldMap.put("ItemDescription" + FAOSTATConstants.faostatLanguage, "ItemDescription" + FAOSTATConstants.faostatLanguage);
		
		Map<String,List<String>> filterMap = new HashMap<String, List<String>>();
		List<String> filterValues = new ArrayList<String>();
		filterValues.add(method.getCode());
		
		filterMap.put("MethodologyCode", filterValues);

		List<String> orderBys = new ArrayList<String>();
		orderBys.add("MethodologyTitle"+ FAOSTATConstants.faostatLanguage);

 
		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getMetadataStandardQuery(method.getLabel(), DataViewerBoxContent.METADATA_METHODOLOGY, selects, froms, orderBys, null, null,handleNullForFieldMap, filterMap);

		List<String> columns = new ArrayList<String>();
		columns.add(FAOSTATLanguage.print().note());
		columns.add(FAOSTATLanguage.print().coverage());
		columns.add(FAOSTATLanguage.print().references());
		columns.add(FAOSTATLanguage.print().dataSourcesAndCollection());
		columns.add(FAOSTATLanguage.print().estimation());
		columns.add(FAOSTATLanguage.print().relatedConcepts());
		
		qvo.setTableHeaders(columns);


		VerticalPanel v = new VerticalPanel();

		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
        v.add(panel);

        methodology.getDisplayPanel().add(v);


		FAOSTATTableController.addTable(panel, qvo, "700px", "600px");

		methodology.getDisplayPanel().getLayout().layout();

	}
}
