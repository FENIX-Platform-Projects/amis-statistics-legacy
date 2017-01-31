package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMBoxController;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMDonorMatrixMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMFAOSectorMatrixMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.model.FAOSectorMatrixModel;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMDonorMatrixController extends ADAMController{

	public static void createDonorMatrixView(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters){
		ADAMDonorMatrixVO vo = createDonorMatrixVO(donorList, gaulList, filters, baseDate);		
		addDonorMatrix(vo);
	}
	
	/*public static Command setDonorMatrixView(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				currentVIEW = ADAMCurrentVIEW.DONORMATRIX;
				ADAMController.containsMaps = false;
				objectWindowId = objectWindow.getNext();
						
				removeBoxes();
				removeQuestions();
				filters();		
			}
		};
	};*/

	/*public static void createDonorMatrix(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters, List<String> dates ){		
		ADAMDonorMatrixVO vo = createDonorMatrixVO(donorList, gaulList, filters, dates);
		addDonorMatrix(vo);		
	}*/

	public static void addDonorMatrix(final ADAMDonorMatrixVO vo) {
		//final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		//removeAllCustomObjects();	
		
		//ADAMVisibilityController.restoreAdamViewVisibility();
		
		//System.out.println("addDonorMatrix: vo.getDonorsDacMembership() IS EMPTY?"+ vo.getDonorsDacMembership().isEmpty());
		
		RootPanel.get("ANALYSE_TOP").setStyleName("big-box content");	 
		if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
	
		RootPanel.get("ANALYSE_TOP").add(new ADAMLoadingPanel().buildWaitingPanel());
		/*final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();*/
	
		OLAPFilterVo dateFilter = vo.getOlapParametersVO().getFilters().get(0);
		final List<String> dates = new ArrayList<String>();
		
		
		for(String year: dateFilter.getDimensionMap().keySet()) {
			System.out.println(year);
			dates.add(year);
		}
		
		ADAMServiceEntry.getInstance().populateEmptyAxesDonorMatrix (vo, dates, new AsyncCallback<ADAMDonorMatrixVO>() {
			public void onSuccess(final ADAMDonorMatrixVO updatedMatrixVo) {
				
				
				System.out.println("addDonorMatrix:updatedVO vo.getDonorsDacMembership() IS EMPTY?"+ updatedMatrixVo.getDonorsDacMembership().isEmpty());
				//vo.setOlapParametersVO(updatedVO);
				if(updatedMatrixVo.getOlapParametersVO()==null){
					VerticalPanel panel = ADAMDonorMatrixMaker.build(updatedMatrixVo, null);
					
					
					if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
						RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
					
					RootPanel.get("ANALYSE_TOP").add(panel);
					
					
					
					//loadingWindow.destroyLoadingBox();
					
				} else {
					//System.out.println("updatedMatrixVo.getOlapParametersVO(): " + updatedMatrixVo.getOlapParametersVO());
				 ADAMServiceEntry.getInstance().getDonorMatrixRows (updatedMatrixVo.getOlapParametersVO(), new AsyncCallback<List<DonorMatrixModel>>() {
						public void onSuccess(List<DonorMatrixModel> models) {
							
							//loadingWindow.destroyLoadingBox();
							
							if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
								RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
							
							VerticalPanel panel = ADAMDonorMatrixMaker.build(updatedMatrixVo, models);
							RootPanel.get("ANALYSE_TOP").add(panel);
					
										
							if(updatedMatrixVo.getOlapParametersVO().getZLabels().size() > 1){
								ADAMQueryVO q = ADAMQueryVOBuilder.totalODADonorCommitment(updatedMatrixVo.getOlapParametersVO().getXLabels(), updatedMatrixVo.getOlapParametersVO().getZLabels(), dates, ADAMBoxColors.yellow.name());
								ADAMController.addChart("TOP_LEFT", "matrix", q, ADAMController.objectWindowId);
								ADAMQueryVO q2 = ADAMQueryVOBuilder.totalODADonorCommitmentTimeSeries(updatedMatrixVo.getOlapParametersVO().getXLabels(), updatedMatrixVo.getOlapParametersVO().getZLabels(), ADAMBoxColors.yellow.name());
								ADAMController.addChart("BOTTOM_LEFT", "matrix", q2, ADAMController.objectWindowId);
								ADAMQueryVO q3 = ADAMQueryVOBuilder.faoSectorODADonorCommitmentTimeSeries(updatedMatrixVo.getOlapParametersVO().getXLabels(), updatedMatrixVo.getOlapParametersVO().getZLabels(), ADAMBoxColors.yellow.name());
								ADAMController.addChart("BOTTOM_RIGHT", "matrix", q3, ADAMController.objectWindowId);
							}
							
							if(updatedMatrixVo.getOlapParametersVO().getXLabels().size() > 1){
								ADAMQueryVO q = ADAMQueryVOBuilder.totalODACountryCommitment(updatedMatrixVo.getOlapParametersVO().getZLabels(),updatedMatrixVo.getOlapParametersVO().getXLabels(), dates, ADAMBoxColors.yellow.name());
								
								if(updatedMatrixVo.getOlapParametersVO().getZLabels().size() > 1)
									ADAMController.addChart("TOP_RIGHT", "matrix", q, ADAMController.objectWindowId);
								else
									ADAMController.addChart("BOTTOM_LEFT", "matrix", q, ADAMController.objectWindowId);
								
								ADAMQueryVO q2 = ADAMQueryVOBuilder.totalODACountryCommitmentTimeSeries(updatedMatrixVo.getOlapParametersVO().getXLabels(), updatedMatrixVo.getOlapParametersVO().getZLabels(), ADAMBoxColors.yellow.name());
								ADAMController.addChart("MAP_LEFT", "matrix", q2, ADAMController.objectWindowId);
								ADAMQueryVO q3 = ADAMQueryVOBuilder.faoSectorODACountryCommitmentTimeSeries(updatedMatrixVo.getOlapParametersVO().getXLabels(), updatedMatrixVo.getOlapParametersVO().getZLabels(), ADAMBoxColors.yellow.name());
								ADAMController.addChart("MAP_RIGHT", "matrix", q3, ADAMController.objectWindowId);								
							}
							
							
						}
						public void onFailure(Throwable caught) {
							//Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows", caught.getLocalizedMessage());
							if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
								RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
							
							//loadingWindow.destroyLoadingBox();
						}
					});
				}
				
//				if(updatedMatrixVo.getOlapParametersVO().getZLabels().size() > 1){
//					ADAMQueryVO q = ADAMQueryVOBuilder.totalODADonorCommitment(updatedMatrixVo.getOlapParametersVO().getXLabels(), updatedMatrixVo.getOlapParametersVO().getZLabels(), dates, ADAMBoxColors.yellow.name());
//					ADAMController.addChart("TOP_LEFT", "matrix", q, ADAMController.objectWindowId);
//				}
//				
//				if(updatedMatrixVo.getOlapParametersVO().getXLabels().size() > 1){
//					ADAMQueryVO q = ADAMQueryVOBuilder.totalODACountryCommitment(updatedMatrixVo.getOlapParametersVO().getZLabels(),updatedMatrixVo.getOlapParametersVO().getXLabels(), dates, ADAMBoxColors.yellow.name());
//					ADAMController.addChart("BOTTOM_LEFT", "matrix", q, ADAMController.objectWindowId);
//				}
			}
		public void onFailure(Throwable caught) {
				//Info.display("populateEmptyAxesDonorMatrix: Failed", "Error in populating Empty Axes ", caught.getLocalizedMessage());
				//loadingWindow.destroyLoadingBox();
				
				if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
					RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
			}
		});
		
	}	
	
   public static void addDonorMatrix(final ADAMDonorMatrixVO vo, List<String> dates, final List<DonorMatrixModel> models) {
		
		//ADAMVisibilityController.restoreAdamViewVisibility();
		
		//ADAMVisibilityController.restoreViewMenuVisibility();
		
		//addViewMenu("Resource Partner Matrix:", ADAMBoxMaker.countriesSelected, ADAMBoxMaker.donorsSelected, null, null, null);
		
		final OLAPParametersVo params = vo.getOlapParametersVO();
		
		RootPanel.get("ANALYSE_TOP").setStyleName("big-box content");	 
		if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
		
		RootPanel.get("ANALYSE_TOP").add(new ADAMLoadingPanel().buildWaitingPanel());
		
		//final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		//loadingWindow.showLoadingBox();
	
		if(params!=null){					
					//loadingWindow.destroyLoadingBox();
					
			  if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
				RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
			
					VerticalPanel panel = ADAMDonorMatrixMaker.build(vo, models);
					RootPanel.get("ANALYSE_TOP").add(panel);
					
					OLAPFilterVo dateFilter = vo.getOlapParametersVO().getFilters().get(0);
						
					if(params.getZLabels().size() > 1){
						ADAMQueryVO q = ADAMQueryVOBuilder.totalODADonorCommitment(params.getXLabels(), params.getZLabels(), dates, ADAMBoxColors.yellow.name());
						ADAMController.addChart("TOP_LEFT", "matrix", q, ADAMController.objectWindowId);
						ADAMQueryVO q2 = ADAMQueryVOBuilder.totalODADonorCommitmentTimeSeries(params.getXLabels(), params.getZLabels(), ADAMBoxColors.yellow.name());
						ADAMController.addChart("BOTTOM_LEFT", "matrix", q2, ADAMController.objectWindowId);
						ADAMQueryVO q3 = ADAMQueryVOBuilder.faoSectorODADonorCommitmentTimeSeries(params.getXLabels(), params.getZLabels(), ADAMBoxColors.yellow.name());
						ADAMController.addChart("BOTTOM_RIGHT", "matrix", q3, ADAMController.objectWindowId);
					}
					
					if(params.getXLabels().size() > 1){
						ADAMQueryVO q = ADAMQueryVOBuilder.totalODACountryCommitment(params.getZLabels(),params.getXLabels(), dates, ADAMBoxColors.yellow.name());
						
						if(params.getZLabels().size() > 1)
							ADAMController.addChart("TOP_RIGHT", "matrix", q, ADAMController.objectWindowId);
						else
							ADAMController.addChart("BOTTOM_LEFT", "matrix", q, ADAMController.objectWindowId);
						
						ADAMQueryVO q2 = ADAMQueryVOBuilder.totalODACountryCommitmentTimeSeries(params.getXLabels(), params.getZLabels(), ADAMBoxColors.yellow.name());
						ADAMController.addChart("MAP_LEFT", "matrix", q2, ADAMController.objectWindowId);
						ADAMQueryVO q3 = ADAMQueryVOBuilder.faoSectorODACountryCommitmentTimeSeries(params.getXLabels(), params.getZLabels(), ADAMBoxColors.yellow.name());
						ADAMController.addChart("MAP_RIGHT", "matrix", q3, ADAMController.objectWindowId);								
					}
					
			
		} else 
			Info.display("Retrieved Matrix Rows: Failed", "Params is null");
	}	
		
	public static void addFAOSectorMatrix(final ADAMDonorMatrixVO matrixVo, final String selectedRecipientCode, final String selectedRecipientLabel, final List<DonorMatrixModel> matrixModels) {
		
		//ADAMVisibilityController.removeViewMenuContent();
		//objectWindowId = objectWindow.getNext();
		
		
	//	System.out.println("addFAOSectorMatrix" + objectWindowId);

		RootPanel.get("ANALYSE_TOP").setStyleName("big-box content");	 
		if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
		
		RootPanel.get("TOP_LEFT").setStyleName("none");	 
		if (RootPanel.get("TOP_LEFT").getWidgetCount() > 0)
			RootPanel.get("TOP_LEFT").remove(RootPanel.get("TOP_LEFT").getWidget(0));
	
		RootPanel.get("TOP_RIGHT").setStyleName("none");	 
		if (RootPanel.get("TOP_RIGHT").getWidgetCount() > 0)
			RootPanel.get("TOP_RIGHT").remove(RootPanel.get("TOP_RIGHT").getWidget(0));
	
		RootPanel.get("BOTTOM_LEFT").setStyleName("none");	 
		if (RootPanel.get("BOTTOM_LEFT").getWidgetCount() > 0)
			RootPanel.get("BOTTOM_LEFT").remove(RootPanel.get("BOTTOM_LEFT").getWidget(0));
				
		RootPanel.get("BOTTOM_RIGHT").setStyleName("none");	 
		if (RootPanel.get("BOTTOM_RIGHT").getWidgetCount() > 0)
			RootPanel.get("BOTTOM_RIGHT").remove(RootPanel.get("BOTTOM_RIGHT").getWidget(0));
		
		RootPanel.get("MAP_LEFT").setStyleName("none");	 
		if (RootPanel.get("MAP_LEFT").getWidgetCount() > 0)
			RootPanel.get("MAP_LEFT").remove(RootPanel.get("MAP_LEFT").getWidget(0));
				
		RootPanel.get("MAP_RIGHT").setStyleName("none");	 
		if (RootPanel.get("MAP_RIGHT").getWidgetCount() > 0)
			RootPanel.get("MAP_RIGHT").remove(RootPanel.get("MAP_RIGHT").getWidget(0));
	
			
		RootPanel.get("ANALYSE_TOP").add(new ADAMLoadingPanel().buildWaitingPanel());
		
		//final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		//loadingWindow.showLoadingBox();
	
				final OLAPParametersVo newVO = createFAOSectorVO(matrixVo.getOlapParametersVO(), selectedRecipientCode, selectedRecipientLabel);
				
				ADAMServiceEntry.getInstance().populateVOWithSectors(newVO, selectedRecipientCode, new AsyncCallback<OLAPParametersVo>() {
					public void onSuccess(final OLAPParametersVo updatedVO) {
				
				 ADAMServiceEntry.getInstance().getFAOSectorMatrixRows (updatedVO, new AsyncCallback<List<FAOSectorMatrixModel>>() {
						public void onSuccess(List<FAOSectorMatrixModel> models) {
											
							VerticalPanel panel = ADAMFAOSectorMatrixMaker.build(updatedVO, matrixVo, models, matrixModels, selectedRecipientLabel, selectedRecipientCode);
							
							if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
								RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
							
							RootPanel.get("ANALYSE_TOP").add(panel);
						
							//loadingWindow.destroyLoadingBox();
								
					
			}
		public void onFailure(Throwable caught) {
			if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
				RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
			
			//Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows", caught.getLocalizedMessage());
				//loadingWindow.destroyLoadingBox();
			}
		});
	}	

public void onFailure(Throwable caught) {
		//Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows 1 ", caught.getLocalizedMessage());
	if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
		RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
	
	//	loadingWindow.destroyLoadingBox();
	}
});
}
		
		
	
	public static Listener<BaseEvent> openADAMCountryView(final String recipientCode, final String recipientLabel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				
				ADAMBoxMaker.countriesSelected.clear();
				ADAMBoxMaker.countriesSelected.put("Gaul_"+recipientCode, recipientLabel);
				ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulList.getStore().findModel("gaulCode", "Gaul_"+recipientCode));
					
				ADAMBoxMaker.countriesSelected.clear();
				
				callViewAgent(ADAMCurrentVIEW.ADAMVIEW, "openADAMCountryView", ADAMCurrentVIEW.BROWSE.name());	
				
				RootPanel.get("TAB4").setStyleName("tab_menu_default");
				RootPanel.get("TAB2").setStyleName("tab_menu_selected");
			        	
			}
		};
	}
	
/*	public static void openADAMView(final String recipientCode, final String recipientLabel) {
				ADAMBoxMaker.countriesSelected.clear();
				ADAMBoxMaker.countriesSelected.put("Gaul_"+recipientCode, recipientLabel);
				ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulList.getStore().findModel("gaulCode", "Gaul_"+recipientCode));
				
				//ADAMBoxMaker.donorsSelected.clear();
				//ADAMBoxMaker.donorList.setValue(ADAMBoxMaker.donorList.getStore().getAt(0));
				
				callViewAgent(ADAMCurrentVIEW.ADAMVIEW);
				
				RootPanel.get("TAB4").setStyleName("tab_menu_default");
				RootPanel.get("TAB2").setStyleName("tab_menu_selected");
	}*/
	
	public static void openDonorProfileView(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, final String donorCode, final String donorLabel) {
		
		
		//ADAMBoxMaker.donorsSelected.clear();
		//ADAMBoxMaker.donorsSelected.put("Donor_"+donorCode, donorLabel);
		//ADAMBoxMaker.donorList.setValue(ADAMBoxMaker.donorList.getStore().findModel("gaulCode", "Donor_"+donorCode));
			
		callDonorProfileViewAgent(vo, matrixModels, donorCode, donorLabel);
		
		RootPanel.get("TAB4").setStyleName("tab_menu_default");
		RootPanel.get("TAB5").setStyleName("tab_menu_selected");
		
   }
	
	public static void callDonorProfileViewAgent(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, final String donorCode, final String donorLabel) {
		currentVIEW = ADAMCurrentVIEW.PROFILES;
		
		removeAllContents(true); //removeAllContents(false);
		//ADAMVisibilityController.removeHomeContent();
		filters();
		callDonorProfileView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, vo, matrixModels, donorCode, donorLabel); 
	
	}
	
	public static void callDonorProfileView(final Map<String, String> donorList, final Map<String, String> gaulList, final Map<String, String> sectorList, final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, final String donorCode, final String donorLabel) {
		 currentVIEW = ADAMCurrentVIEW.PROFILES;
		 
		 ADAMBoxController.resizeObjects();
		
		 removeAllContents(false);
	
		 HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		 Boolean countryAdded = addCountryFilter(filters, gaulList);
		 Boolean donorAdded = addDonorFilter(filters, donorList);
		 Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		
		 createDonorProfileViewFromMatrix(vo, matrixModels, donorList, filters, donorCode, donorLabel);
	}
	
	/***public static SelectionListener<ButtonEvent> createDonorMatrix(final ComboBox<GaulModelData> adamCodesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeBoxes();
				ADAMController.currentVIEW = ADAMCurrentVIEW.DONORMATRIX;
				
				//ADAMController.removeFilters();
				if(adamCodesList==null)
					System.out.println("adamCodesList is NULL");
				
				GaulModelData selected = adamCodesList.getSelection().get(0);
				String code = selected.getGaulCode().substring(1 + selected.getGaulCode().indexOf('_'));
				System.out.println("code = "+code);
				String label = selected.getGaulLabel();
				System.out.println("label = "+label);
				String entity = selected.getGaulCode().substring(0, selected.getGaulCode().indexOf('_'));
				System.out.println("entity = "+entity);
				
				// create OLAPParametersVO
				OLAPParametersVo vo = createOLAPParametersVo(code, label, entity);
				
				ADAMDonorMatrixMaker.addDonorMatrix(vo);
				//RootPanel.get("CENTER").setStyleName("big-" + ADAMColorConstants.color.get(ADAMBoxContent.MAP) + "-box border content");
				//RootPanel.get("CENTER").add(ADAMDonorMatrixMaker.build(vo));
			}
		};
	}

	***/
	
public static OLAPParametersVo createFAOSectorVO (final OLAPParametersVo matrixVO, final String recipientcode, final String recipientLabel ){
		
	    OLAPParametersVo p = new OLAPParametersVo();
		p.setDataSourceType(ResourceType.AGGREGATEDVIEW);
		p.setFunction("SUM");
		p.setDecimals(2);
		p.setShowTotals(true);
		p.setDataSourceTitle("FAO Sector Breakdown");
		p.setOlapTitle(""); 
			
		p.setX("donorcode"); //datatype
		p.setXLabel("donorcode");  // sql column name
		p.setShowXLabel(false);
		p.setXLabels(matrixVO.getXLabels());
		p.setxCodes(matrixVO.getxCodes());
		p.setSortedXLabels(matrixVO.getSortedXLabels());
		
		p.setY("aggregate");  //datatype
		p.setYLabel("Assistance Type"); // sql column name
		p.setShowYLabel(false);
		Map<String, String> ylabels = new HashMap<String, String>();
		ylabels.put("Total Commitment", "Total Commitment");
		ylabels.put("Total Disbursement", "Total Disbursement");
		//ylabels.put("% Commitment", "% Commitment");
		//ylabels.put("% Disbursement", "% Disbursement"); //ylabels.put("label", "label");
		p.setYLabels(ylabels);
			
		List<String> selectedYDimensions = new ArrayList<String>();
		selectedYDimensions.add("Total Commitment");
		selectedYDimensions.add("Total Disbursement");
		p.setAggregateSelectedYDimensions(selectedYDimensions);
		p.setAggregateSelectedXDimensions( new ArrayList<String>());
		p.setAggregateSelectedZDimensions( new ArrayList<String>());
		
		
		p.setZ("purposecode"); //datatype
		p.setZLabel("purposecode"); // sql column name	
		p.setZHeader("FAO Sectors");
		Map<String, String> zlabels = new HashMap<String, String>();
		
		p.setZLabels(zlabels);
			
		OLAPFilterVo recipientFilter = new OLAPFilterVo();
		recipientFilter.setDimension("recipientcode");
		recipientFilter.setDimensionLabel("Recipient Country");
			
		Map<String, String> filterValues = new HashMap<String, String>();
		filterValues.clear();
		filterValues.put(recipientcode, recipientLabel);
		recipientFilter.setDimensionMap(filterValues);
			
		List<OLAPFilterVo> filters = matrixVO.getFilters();
		if(filters.size() > 1)
			filters.remove(1);
		filters.add(recipientFilter);
		
		p.setFilters(filters);
		
		p.setAggregatedTableViewName("adam_faosectormatrix_data");

		p.setSortXLabels(matrixVO.isSortXLabels());
		
		
		
		p.setReportOrientation("landscape");
		
		return p;
	}

	public static ADAMDonorMatrixVO createDonorMatrixVO (final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters, List<String> dates){
		
		ADAMDonorMatrixVO vo = new ADAMDonorMatrixVO();
		vo.setSelectedDataset(ADAMController.currentlySelectedDatasetCode);
		List<String> nonDacCodes = new ArrayList<String>();
		List<String> dacCodes = new ArrayList<String>();
		
		OLAPParametersVo p = new OLAPParametersVo();
		p.setDataSourceType(ResourceType.AGGREGATEDVIEW);
		p.setFunction("SUM");
		p.setDecimals(2);
		p.setShowTotals(true);
		p.setDataSourceTitle("Resource Partner Matrix");
		p.setOlapTitle(""); 
		
		//	p.setW("Key SOs and ORs as identified in CPF");
		//	p.setWLabel("Key SOs and ORs as identified in CPF");

		//Map<String, String> wlabels = new HashMap<String, String>();
		//p.setWLabels(wlabels);
		
		p.setX("donorcode"); //datatype
		p.setXLabel("donorcode");  // sql column name
		p.setShowXLabel(false);
		Map<String, String> xlabels = new LinkedHashMap<String, String>();
		Map<String, String> xcodes= new HashMap<String, String>();
		
		System.out.println("--------- donorList.isEmpty() = "+donorList.isEmpty());
			
		
		if(!donorList.isEmpty()) {
			xcodes.clear();

			for(String code : donorList.keySet()) {
				
				System.out.println("--------- donorList code = "+code);
				if(!code.equalsIgnoreCase("") && !code.equalsIgnoreCase("GLOBAL")){
						
					String c = getCode(code);
					xlabels.put(c, donorList.get(code));
					xcodes.put(donorList.get(code), c);
					
					if(code.contains("nondac"))
						nonDacCodes.add(code);
					else
						dacCodes.add(code);
				}
			
			}
		}
		
		p.setXLabels(xlabels);
		p.setxCodes(xcodes);
		
		System.out.println("--------- xlabels.isEmpty() = "+p.getXLabels().isEmpty());
		System.out.println("--------- xcodes.isEmpty() = "+p.getxCodes().isEmpty());
		
		
		p.setY("aggregate");  //datatype
		p.setYLabel("Assistance Type"); // sql column name
		p.setShowYLabel(false);
		Map<String, String> ylabels = new HashMap<String, String>();
		ylabels.put("Total ODA", "Total ODA");
		ylabels.put("FAO sectors", "FAO sectors");
		//ylabels.put("Web link", "Web link");
		p.setYLabels(ylabels);
		
		
		List<String> selectedYDimensions = new ArrayList<String>();
		selectedYDimensions.add("Total ODA");
		selectedYDimensions.add("FAO sectors");
		p.setAggregateSelectedYDimensions(selectedYDimensions);
		p.setAggregateSelectedXDimensions( new ArrayList<String>());
		p.setAggregateSelectedZDimensions( new ArrayList<String>());
		//List<String> yColumnsToBeSummed = new ArrayList<String>();
	//	yColumnsToBeSummed.add("Total ODA");
		//yColumnsToBeSummed.add("FAO sectors");
		//p.yColumnsToBeSummed();
		
		p.setZ("recipientcode"); //datatype
		p.setZLabel("recipientcode"); // sql column name
		p.setZHeader("Recipient Country");
		
		
		Map<String, String> zlabels = new HashMap<String, String>();
		
		
		System.out.println("--------- gaulList.isEmpty()) = "+gaulList.isEmpty());
		
		if(!gaulList.isEmpty()){
			for(String code : gaulList.keySet()) {
				System.out.println("gaul list --------------- code = "+ code);
				
				if(!code.equalsIgnoreCase("") && !code.equalsIgnoreCase("GLOBAL")){
					String c = getCode(code);
					zlabels.put(c, gaulList.get(code));
				}
			}
		}
		
				
		p.setZLabels(zlabels);
					
		System.out.println("--------- zlabels.isEmpty() = "+p.getZLabels().isEmpty());
		
		List<OLAPFilterVo> filterList = new ArrayList<OLAPFilterVo>();
		
		OLAPFilterVo filter = new OLAPFilterVo();
		filter.setDimension("year");
		filter.setDimensionLabel("Year");
		
		Map<String, String> filterValues = new HashMap<String, String>();
		for(String date: dates){
			filterValues.put(date, date);
		}
		
		filter.setDimensionMap(filterValues);
				
		filterList.add(filter);
		
		p.setFilters(filterList);
		 
		p.setReportOrientation("landscape");
	
		p.setAggregatedTableViewName("adam_donormatrix_data");
		
		vo.setDacDonorCodes(dacCodes);
		vo.setNonDacDonorCodes(nonDacCodes);
		vo.setOlapParametersVO(p);
		
		
		vo.setDonorsDacMembership(new HashMap<String, String>());
		
		
	/*	xlabels.put("801","Australia");
		xlabels.put("1","Austria");
		xlabels.put("2","Belgium");
		xlabels.put("301","Canada");
		xlabels.put("918","EU Institutions");
		xlabels.put("18","Finland");
		xlabels.put("4","France");
		xlabels.put("5","Germany");
		xlabels.put("40","Greece");
		xlabels.put("905","IDA");
		xlabels.put("988","IFAD");
		xlabels.put("21","Ireland");
		xlabels.put("6","Italy");
		xlabels.put("701","Japan");
		xlabels.put("742","Korea");
		xlabels.put("22","Luxembourg");
		xlabels.put("7","Netherlands");
		xlabels.put("8","Norway");
		xlabels.put("9","Portugal");
		xlabels.put("50","Spain");
		xlabels.put("11","Switzerland");
		xlabels.put("959","UNDP");
		xlabels.put("12","United Kingdom");
		xlabels.put("302","United States");
		
		p.setXLabels(xlabels);*/
		
		
	/*	zlabels.put("4","Algeria");
		zlabels.put("8","Angola");
		zlabels.put("29","Benin");
		zlabels.put("35","Botswana");
		zlabels.put("42","Burkina Faso");
		zlabels.put("43","Burundi");
		zlabels.put("44","Cambodia");
		zlabels.put("45","Cameroon");
		zlabels.put("47","Cape Verde");
		zlabels.put("49","Central African Republic");
		zlabels.put("50","Chad");
		zlabels.put("58","Comoros");
		zlabels.put("66","Cote d Ivoire");
		zlabels.put("68","Democratic Republic of the Congo");
		zlabels.put("70","Djibouti");
		
		
		p.setZLabels(zlabels);*/
		
		
	

	/*	xcodes.put("Australia","801");
		xcodes.put("Austria","1");
		xcodes.put("Belgium","2");
		xcodes.put("Canada","301");
		xcodes.put("EU Institutions","918");
		xcodes.put("Finland","18");
		xcodes.put("France","4");
		xcodes.put("Germany","5");
		xcodes.put("Greece","40");
		xcodes.put("IDA","905");
		xcodes.put("IFAD","988");
		xcodes.put("Ireland","21");
		xcodes.put("Italy","6");
		xcodes.put("Japan","701");
		xcodes.put("Korea","742");
		xcodes.put("Luxembourg","22");
		xcodes.put("Netherlands","7");
		xcodes.put("Norway","8");
		xcodes.put("Portugal","9");
		xcodes.put("Spain","50");
		xcodes.put("Switzerland","11");
		xcodes.put("UNDP","959");
		xcodes.put("United Kingdom","12");
		xcodes.put("United States","302");
		
		p.setxCodes(xcodes);*/
		

		
		return vo;
	}

public static SelectionListener<ButtonEvent> exportExcel(final OLAPParametersVo parameters) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				try {
					OlapServiceEntry.getInstance().createExcel(parameters, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							Window.open("../olapExcel/" + result, "_blank", "status=no");
						}

						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}
	
	   
		public static Listener<ComponentEvent> hideColumns(final OLAPParametersVo parameters, final Grid<DonorMatrixModel> matrixGrid, final HorizontalPanel panel) {
			return new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent ce) {
					
					if(matrixGrid!=null) {
						CheckBox box = ((CheckBox)ce.getComponent());	
						if(box.getValue()) {
							for (int i = 0; i < parameters.getSortedXLabels().size(); i++) {
								matrixGrid.getColumnModel().getColumnById("webPage"+i).setHidden(false);
							}
							box.setBoxLabel(BabelFish.print().hideAllWebLinkColumns());  
							
						} else {
							for (int i = 0; i < parameters.getSortedXLabels().size(); i++) {
								matrixGrid.getColumnModel().getColumnById("webPage"+i).setHidden(true);
							}
							box.setBoxLabel(BabelFish.print().showAllWebLinkColumns());  
						}
						
						box.recalculate();
						panel.getLayout().layout();
						matrixGrid.reconfigure(matrixGrid.getStore(), matrixGrid.getColumnModel()); 
					
					}	
				}
			};
		}
		
		public static Listener<ComponentEvent> hideDisbursementColumns(final OLAPParametersVo parameters, final Grid<FAOSectorMatrixModel> matrixGrid, final ToolBar toolBar) {
			return new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent ce) {
					
					if(matrixGrid!=null) {
						CheckBox box = ((CheckBox)ce.getComponent());	
						if(box.getValue()) {
							for (int i = 0; i < parameters.getXLabels().size(); i++) {
								matrixGrid.getColumnModel().getColumnById("disbursementOda"+i).setHidden(true);
							}
							
							box.setBoxLabel(BabelFish.print().showAllDisbursementColumns());  
							
						} else {
							for (int i = 0; i < parameters.getXLabels().size(); i++) {
								matrixGrid.getColumnModel().getColumnById("disbursementOda"+i).setHidden(false);
							}
							box.setBoxLabel(BabelFish.print().hideAllDisbursementColumns());  
						}
						
						box.recalculate();
						toolBar.layout();
						matrixGrid.reconfigure(matrixGrid.getStore(), matrixGrid.getColumnModel()); 
					
					}	
				}
			};
		}
		
		
		public static Listener<ComponentEvent> hideCommitmentColumns(final OLAPParametersVo parameters, final Grid<FAOSectorMatrixModel> matrixGrid, final ToolBar toolBar) {
			return new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent ce) {
					if(matrixGrid!=null) {
						CheckBox box = ((CheckBox)ce.getComponent());
						
						if(box.getValue()) {
							for (int i = 0; i < parameters.getXLabels().size(); i++) {
								matrixGrid.getColumnModel().getColumnById("commitmentOda"+i).setHidden(true);
							}
							box.setBoxLabel(BabelFish.print().showAllCommitmentColumns());  
							
						} else {
							for (int i = 0; i < parameters.getXLabels().size(); i++) {
								matrixGrid.getColumnModel().getColumnById("commitmentOda"+i).setHidden(false);
							}
							box.setBoxLabel(BabelFish.print().hideAllCommitmentColumns());  
						}
						
						box.recalculate();
						toolBar.layout();
						matrixGrid.reconfigure(matrixGrid.getStore(), matrixGrid.getColumnModel()); 
					
					}	
				}
			};
		}
		
		public static Listener<ComponentEvent> hideORsColumn(final Grid<FAOSectorMatrixModel> matrixGrid, final ToolBar toolBar) {
			return new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent ce) {
					
					if(matrixGrid!=null) {
						CheckBox box = ((CheckBox)ce.getComponent());	
						if(box.getValue()) {
							matrixGrid.getColumnModel().getColumnById("mappedORs").setHidden(false);
							box.setBoxLabel("Hide FAO Organizational Results Column");  
							
						} else {
							matrixGrid.getColumnModel().getColumnById("mappedORs").setHidden(true);
							box.setBoxLabel("Show FAO Organizational Results Column");  
						}
						
						box.recalculate();
						toolBar.layout();
						matrixGrid.reconfigure(matrixGrid.getStore(), matrixGrid.getColumnModel()); 
					
					}	
				}
			};
		}
		
		public static Listener<BaseEvent> showFaoSectorView(final ADAMDonorMatrixVO matrixVo, final String selectedRecipientCode, final String selectedRecipientLabel, final List<DonorMatrixModel> models) {
			return new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent ce) {
					
					addFAOSectorMatrix(matrixVo, selectedRecipientCode, selectedRecipientLabel, models);	
				        	
				}
			};
		}
		
//		public static Listener<ComponentEvent> showFaoSectorViewCheckBox(final ADAMDonorMatrixVO matrixVo, final String selectedRecipientCode, final String selectedRecipientLabel, final List<DonorMatrixModel> models) {
//			return new Listener<ComponentEvent>() {
//				public void handleEvent(ComponentEvent ce) {
//					CheckBox box = ((CheckBox)ce.getComponent());	
//					if(box.getValue()) {
//						addFAOSectorMatrix(matrixVo, selectedRecipientCode, selectedRecipientLabel, models);		
//					}
//					
//				}
//			};
//		}
		
		public static SelectionListener<ButtonEvent> backToMatrixViewFromProfiles(final ADAMDonorMatrixVO matrixVo, final List<String> dates, final List<DonorMatrixModel> models) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					currentVIEW = ADAMCurrentVIEW.DONORMATRIX;
					ADAMController.containsMaps = false;
					objectWindowId = objectWindow.getNext();
							
					removeBoxes();
					//removeQuestions();
					filters();		
					
					addDonorMatrix(matrixVo, dates, models);
					
					RootPanel.get("TAB5").setStyleName("tab_menu_default");
					RootPanel.get("TAB4").setStyleName("tab_menu_selected");
				}
			};
		}
		
		public static SelectionListener<ButtonEvent> backToMatrixViewFromFAOSectorView(final ADAMDonorMatrixVO matrixVo, final List<String> dates, final List<DonorMatrixModel> models) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					addDonorMatrix(matrixVo, dates, models);
				}
			};
		}
		
		
		
		public static Listener<BaseEvent> showProjects(final String donorCode,final String recipientCountryCode, final String sectorCode) {
			return new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent ce) {
					
				//	GWT.log(donorCode+": "+  recipientCountryCode+": "+ sectorCode, null);
				       
					    
					final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
					
					//Map<String, String> donorList = new HashMap<String, String>();		
					//donorList.put(donorCode, donorLabel);
					List<String> donors = new ArrayList<String>();		
					donors.add(donorCode);
					filters.put("donorcode", donors);
					
					//Map<String, String> countryList = new HashMap<String, String>();
					//countryList.put(recipientCountryCode, recipientCountryLabel);
					List<String> countries = new ArrayList<String>();	
					countries.add(recipientCountryCode);
					filters.put("recipientcode", countries);
					
					//Map<String, String> sectorList = new HashMap<String, String>();
					//sectorList.put(sectorCode, sectorLabel);
					List<String> sectors = new ArrayList<String>();		
					sectors.add(sectorCode);
					filters.put("purposecode", sectors);
					
					ADAMViewMenuBuilder.buildWaitingPanel();
					ADAMViewMenuBuilder.hideWaitingPanel();
					
				//	showProjectsAgentView(filters, false);
					
					/***ADAMQueryVO qSize = ADAMQueryVOBuilder.getRowsCount(baseDate, filters);
					ADAMServiceEntry.getInstance().askADAM(qSize, new AsyncCallback<ADAMResultVO>() {
						public void onSuccess(ADAMResultVO vo) {
							System.out.println("vo size: " + vo.getRows());
							
							if ( vo.getRows() < 15000 ) {
								final Long rows = vo.getRows();
								ADAMQueryVO q = ADAMQueryVOBuilder.getProjects(baseDate, filters, false);
								ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
									public void onSuccess(ADAMResultVO vo) {
										//ADAMViewMenu.hideWaitingPanel();
										
									ADAMVisibilityController.restoreProjectsVisibility();
										
									ADAMShowProjectsPanel adamShowProjectsPanel = new ADAMShowProjectsPanel();
									adamShowProjectsPanel.build(vo);
									adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(vo.getTableHTML());
									adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("<div class='content'><b>Total Number of Projects: " + rows + "</b><div>");
										
									adamShowProjectsPanel.getPanel().layout();
									
									if (RootPanel.get("PROJECTS_VIEW").getWidgetCount() > 0)
										RootPanel.get("PROJECTS_VIEW").remove(RootPanel.get("PROJECTS_VIEW").getWidget(0));
									RootPanel.get("PROJECTS_VIEW").setStyleName("big-blue-box border content");
									RootPanel.get("PROJECTS_VIEW").add(adamShowProjectsPanel.getPanel());
										
									}
									public void onFailure(Throwable e) {
										ADAMViewMenu.hideWaitingPanel();
										FenixAlert.error(BabelFish.print().error(), e.getMessage());
									}
								});
							}
							else {
								ADAMViewMenu.hideWaitingPanel();
								FenixAlert.info("List of Projects", "The query returns too many projects: " + vo.getRows());
							}
							
						}
						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().error(), e.getMessage());
						}
					});**/
				        	
				}
			};
		}
		
		public static Listener<BaseEvent> showORDescriptions(final MessageBox box) {
			return new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent ce) {
				   box.show();				        	
				}
			};
		}
		
	/*public static SelectionListener<ButtonEvent> hideColumns(final OLAPParametersVo parameters, final Grid<DonorMatrixModel> matrixGrid, final ContentPanel panel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				if(matrixGrid!=null) {
					
					int i = 0;
					FenixAlert.error(BabelFish.print().info(), "column hidden "+matrixGrid.getColumnModel().getColumnById("name").isHidden());
					matrixGrid.getColumnModel().getColumnById("name").setHidden(true);
					
					
					//matrixGrid.getColumnModel().getColumnById("totalOda"+i+"all").setHidden(true);
					//matrixGrid.getColumnModel().getColumnById("webPage"+i).setHidden(true);	
					
					matrixGrid.reconfigure(matrixGrid.getStore(), matrixGrid.getColumnModel()); 
					
					FenixAlert.error(BabelFish.print().info(), "after column hidden "+matrixGrid.getColumnModel().getColumnById("name").isHidden());
				
				}	
				else 
					FenixAlert.error(BabelFish.print().info(), "GRID IS NULL");
				
			}
		};
	}*/
	
	
	/****private static OLAPParametersVo createOLAPParametersVo (String code, String label, String entity){
			
		OLAPParametersVo p = new OLAPParametersVo();
		p.setDataSourceType(ResourceType.AGGREGATEDVIEW);
		p.setFunction("NONE");
		p.setDecimals(2);
		p.setShowTotals(true);
		
		//	p.setW("Key SOs and ORs as identified in CPF");
		//	p.setWLabel("Key SOs and ORs as identified in CPF");

		//Map<String, String> wlabels = new HashMap<String, String>();

		//p.setWLabels(wlabels);
		
		//code = "3";
		//entity = "Gaul";
		//label = "Albania";
		
		p.setX("donorcode"); //datatype
		p.setXLabel("donorcode");  // sql column name
		Map<String, String> xlabels = new HashMap<String, String>();
		
		p.setY("aggregate");  //datatype
		p.setYLabel("Assistance Type"); // sql column name
		Map<String, String> ylabels = new HashMap<String, String>();
		ylabels.put("Total ODA", "Total ODA");
		ylabels.put("% on Production", "% on Production");
		ylabels.put("Web Page", "Web Page");
		p.setYLabels(ylabels);
				
		p.setZ("recipientcode"); //datatype
		p.setZLabel("recipientcode"); // sql column name
		Map<String, String> zlabels = new HashMap<String, String>();
		
		
		Map<String, String> xcodes= new HashMap<String, String>();
		
		
		List<OLAPFilterVo> filterList = new ArrayList<OLAPFilterVo>();
		
		OLAPFilterVo filter = new OLAPFilterVo();
		filter.setDimension("year");
		filter.setDimensionLabel("Year");
		
		Map<String, String> filterValues = new HashMap<String, String>();
		filterValues.put("2008-01-01", "2008-01-01");
		filter.setDimensionMap(filterValues);
		
		filterList.add(filter);
		
		p.setFilters(filterList);
		
		

		
		if (entity.equalsIgnoreCase("Gaul")) {
			zlabels.put(code, label);
			p.setZLabels(zlabels);
		}
		else if(entity.equalsIgnoreCase("Donor")){
			xlabels.put(code, label);
			p.setXLabels(xlabels);
			xcodes.clear();
			
			xcodes.put(label, code);
		
		}
	
	p.setxCodes(xcodes);
	
		
		return p;
	}**/
	
}