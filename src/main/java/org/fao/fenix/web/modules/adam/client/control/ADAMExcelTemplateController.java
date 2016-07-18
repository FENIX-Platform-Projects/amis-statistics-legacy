package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilderFAO;
import org.fao.fenix.web.modules.adam.client.view.ADAMViewMenu;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMDonorMatrixMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMVennMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.venn.common.services.VennServiceEntry;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMExcelTemplateController extends ADAMController {
	
	public static SelectionListener<ButtonEvent> createExcelTemplateExport() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createExcelTemplateExportAgent();
			}
		};
	}
	
	public static SelectionListener<MenuEvent> createExcelTemplateExportMenu() {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				createExcelTemplateExportAgent();
			}
		};
	}
	
	public static void createExcelTemplateExportAgent() {
		
		
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
			createExcelTemplateExportAgentADAMView();
		}
		
		if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
			createExcelTemplateExportAgentFAOView();
		}
		

	}
	
	public static void createExcelTemplateExportAgentADAMView() {
		ADAMViewMenuBuilder.showWaitingPanel("Exporting Excel");

		final Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		final Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		final Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
		
	

		
		 final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		 final Boolean countryAdded = addCountryFilter(filters, gaulList);
		 System.out.println("countryAdded: " + countryAdded);
		 final Boolean donorAdded = addDonorFilter(filters, donorList);
		 System.out.println("donorAdded: " + donorAdded);
		 final Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		 System.out.println("sectorAdded: " + sectorAdded);

		 System.out.println("filters: " + filters);

		// that's for the donormatrix
		ADAMDonorMatrixVO vo = ADAMDonorMatrixController.createDonorMatrixVO(donorList, gaulList, filters, baseDate);

		
		/** TODO: PUT IT DIRECTLY IN ADAM SERVICE IMPL **/
		 ADAMServiceEntry.getInstance().populateEmptyAxesDonorMatrix (vo, baseDate, new AsyncCallback<ADAMDonorMatrixVO>() {
				public void onSuccess(final ADAMDonorMatrixVO matrixVO) {	
					
						
						 if( donorAdded && !countryAdded & !sectorAdded ){
							 exportExcelDonorView(filters, matrixVO.getOlapParametersVO());
						 }

						 else if( countryAdded && !donorAdded & !sectorAdded ){
							 exportExcelCountryView(filters, matrixVO.getOlapParametersVO());
						 }

						 else if( countryAdded && donorAdded & !sectorAdded ){
							 exportExcelCountryDonorView(filters, matrixVO.getOlapParametersVO());
						 }
						 
						 else if ( !donorAdded && !countryAdded && sectorAdded ) {
							 exportExcelSectorView(filters, matrixVO.getOlapParametersVO());
						 }
						 else if (!donorAdded && countryAdded && sectorAdded ) {
							 exportExcelCountrySectorView(filters, matrixVO.getOlapParametersVO());
						 }
						 else if (donorAdded && !countryAdded && sectorAdded ) {
							 exportExcelDonorSectorView(filters, matrixVO.getOlapParametersVO());
						 }
						 else if (donorAdded && countryAdded && sectorAdded ) {
							 exportExcelCountryDonorSectorView(filters, matrixVO.getOlapParametersVO());
						 }

						 else if( !countryAdded && !donorAdded & !sectorAdded ){
							 exportExcelGlobalView(filters, matrixVO.getOlapParametersVO());
						 }
				}
			public void onFailure(Throwable caught) {
					Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows", caught.getLocalizedMessage());
					ADAMViewMenuBuilder.hideWaitingPanel();
				}
			});
	}
	
	private static void exportExcelGlobalView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String mostFinancedTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			mostFinancedTitle = "Most Financed SOs";
		} else {
			mostFinancedTitle = "Most Financed Sectors";
		}
		
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(mostFinancedTitle,ADAMBoxContent.BAR.toString(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);

		qvos.put(BabelFish.print().topSectors() + " " + referencePeriodString, topSector);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	private static void exportExcelCountryView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String mostFinancedTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			mostFinancedTitle = "Most Financed SOs";
		} else {
			mostFinancedTitle = "Most Financed Sectors";
		}
		
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.BAR.toString(), mostFinancedTitle, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, true);
		
		
		qvos.put(BabelFish.print().oda() + " " + referencePeriodString, topSector);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);				
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	private static void exportExcelDonorView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		
		String mostFinancedTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			mostFinancedTitle = "Most Financed SOs";
		} else {
			mostFinancedTitle = "Most Financed Sectors";
		}
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.BAR.toString(), mostFinancedTitle, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, true);
		
		
		qvos.put(BabelFish.print().oda() + " " + referencePeriodString, topSector);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	private static void exportExcelCountryDonorView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String mostFinancedTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			mostFinancedTitle = "Most Financed SOs";
		} else {
			mostFinancedTitle = "Most Financed Sectors";
		}
		
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.BAR.toString(), mostFinancedTitle, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, true);
		
		
		qvos.put(BabelFish.print().oda() + " " + referencePeriodString, topSector);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelSectorView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String sectorsTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			sectorsTitle = "SOs";
		} else {
			sectorsTitle = "Sectors";
		}
		
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorsTitle, new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, false);
		
		
		qvos.put("Sectors Time series", topSectorTimeSerie);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelCountrySectorView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String sectorsTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			sectorsTitle = "SOs";
		} else {
			sectorsTitle = "Sectors";
		}
		
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorsTitle, new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, false);
		
		
		qvos.put("Sectors Time series", topSectorTimeSerie);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelDonorSectorView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String sectorsTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			sectorsTitle = "SOs";
		} else {
			sectorsTitle = "Sectors";
		}
		
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorsTitle, new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, false);
		
		
		qvos.put("Sectors Time series", topSectorTimeSerie);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelCountryDonorSectorView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		String sectorsTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			sectorsTitle = "SOs";
		} else {
			sectorsTitle = "Sectors";
		}
		
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorsTitle, new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		ADAMQueryVO projects = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(),baseDate, filters, false);
		
		
		qvos.put("Sectors Time series", topSectorTimeSerie);
		qvos.put(BabelFish.print().projectsList() + " " + referencePeriodString, projects);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	/**********
	 * 
	 * FAO VIEW
	 *  
	 */
	
	
	public static void createExcelTemplateExportAgentFAOView() {
		ADAMViewMenuBuilder.showWaitingPanel("Exporting Excel");

		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		final Map<String, String> soList = ADAMBoxMaker.soSelected;

		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		 final Boolean countryAdded = addCountryFilter(filters, gaulList);
		 System.out.println("countryAdded: " + countryAdded);
		 final Boolean donorAdded = addDonorFilter(filters, donorList);
		 System.out.println("donorAdded: " + donorAdded);
		
		 final HashMap<String, String> sos = ADAMFAOViewController.getSO(soList);
		 final Boolean soAdded = !sos.isEmpty();
		 System.out.println("soAdded: " + soAdded);

		 System.out.println("filters: " + filters);
		
		 
		// that's for the donormatrix
		ADAMDonorMatrixVO vo = ADAMDonorMatrixController.createDonorMatrixVO(donorList, gaulList, filters, baseDate);

		
		/** TODO: PUT IT DIRECTLY IN ADAM SERVICE IMPL **/
		 ADAMServiceEntry.getInstance().populateEmptyAxesDonorMatrix (vo, baseDate, new AsyncCallback<ADAMDonorMatrixVO>() {
				public void onSuccess(final ADAMDonorMatrixVO matrixVO) {

					 if( donorAdded && !countryAdded && !soAdded) {
						 exportExcelDonorFAOView(filters, matrixVO.getOlapParametersVO());
					 }

					 else if( countryAdded && !donorAdded && !soAdded) {
						 exportExcelCountryViewFAOView(filters, matrixVO.getOlapParametersVO());
					 }

					 else if( countryAdded && donorAdded && !soAdded) {
						 exportExcelCountryDonorFAOView(filters, matrixVO.getOlapParametersVO());
					 }
					 
					 else if( !countryAdded && !donorAdded && soAdded) {
						 exportExcelSOFAOView(sos, matrixVO.getOlapParametersVO());
					 }
					 
					 else if( countryAdded && !donorAdded && soAdded) {
						 exportExcelCountrySOFAOView(filters, sos, matrixVO.getOlapParametersVO());
					 }
					 
					 else if( !countryAdded && donorAdded && soAdded) {
						 exportExcelDonorSOFAOView(filters, sos, matrixVO.getOlapParametersVO());
					 }
					 
					 else if( countryAdded && donorAdded && soAdded) {
						 exportExcelCountryDonorSOFAOView(filters, sos, matrixVO.getOlapParametersVO());
					 }
					 else if( !countryAdded && !donorAdded && !soAdded) {
						 /*** global view **/
						 exportExcelGlobalFAOView(matrixVO.getOlapParametersVO());
					 }
				}
			public void onFailure(Throwable caught) {
					Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows", caught.getLocalizedMessage());
					ADAMViewMenuBuilder.hideWaitingPanel();
				}
			});
	}

	private static void exportExcelGlobalFAOView(OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SOs)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);

		qvos.put("Top Strategic Objectives " + referencePeriodString, topSOsChart);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put("Resource Partner Matrix " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	private static void exportExcelCountryViewFAOView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
			
		qvos.put("Top Strategic Objectives", topSOsChart);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	private static void exportExcelDonorFAOView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
		
		qvos.put("Top Strategic Objectives " + referencePeriodString, topSOsChart);

		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	private static void exportExcelCountryDonorFAOView(HashMap<String, List<String>> filters, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
		
		qvos.put("Top Strategic Objectives", topSOsChart);


		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelSOFAOView(HashMap<String, String> soList, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);

		qvos.put("Time series of SO", topSOsChart);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelCountrySOFAOView(HashMap<String, List<String>> filters, HashMap<String, String> soList, OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);

		qvos.put("Time series of SO", topSOsChart);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelDonorSOFAOView(HashMap<String, List<String>> filters, HashMap<String, String> soList,  OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);

		qvos.put("Time series of SO", topSOsChart);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	private static void exportExcelCountryDonorSOFAOView(HashMap<String, List<String>> filters, HashMap<String, String> soList,  OLAPParametersVo matrixVO) {
		// TODO: the list of the ADAMQuery to be exported
		LinkedHashMap<String, ADAMQueryVO> qvos = new LinkedHashMap<String, ADAMQueryVO>();  
		
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);

		qvos.put("Time series of SO", topSOsChart);
		
		
		// TODO: that's the matrix vo.
		LinkedHashMap<String, OLAPParametersVo> matrixVOs = new LinkedHashMap<String, OLAPParametersVo>();
		matrixVOs.put(BabelFish.print().partnerMatrix() + " " + referencePeriodString, matrixVO);
		
		ADAMServiceEntry.getInstance().createExcelReport(qvos, matrixVOs, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				System.out.println("RESULT: " + result);
				Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

			public void onFailure(Throwable arg0) {
				ADAMViewMenuBuilder.hideWaitingPanel();
			}

		});
	}
	
	
	

}
