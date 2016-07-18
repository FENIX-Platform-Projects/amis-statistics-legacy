/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.metadataeditor.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.designer.client.view.DesignerWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.latex.common.services.LatexServiceEntry;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.common.services.ProjectManagerServiceEntry;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;
import org.fao.fenix.web.modules.text.common.vo.TextGroupVO;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;
import org.fao.fenix.web.modules.tinymcereport.common.services.TinyMCEServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MESaver {

	/* ********************************************************************************************************************************* */
	/* ********************************************************************************************************************************* */
	/* ************************************************** RESOURCE SPECIFIC LISTENERS ************************************************** */
	/* ********************************************************************************************************************************* */
	/* ********************************************************************************************************************************* */
	
	private LoadingWindow loadingWindow;

	public static SelectionListener<ButtonEvent> getSaveChartDesignListener(final MetadataEditorWindow window, final List<ChartDesignerParametersVO> vos, final ChartDesignerWindow cdw) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), "Saving Chart", BabelFish.print().pleaseWait());
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					Map<String, String> metadata = createMetadataMap(rvo);
					createNewChartDesignResource(metadata, vos, window, cdw, loading);
				} catch (FenixGWTException e) {
					System.out.println("exception: " + e.getMessage());
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> getSaveLatexReportListener(final MetadataEditorWindow window, final DesignerWindow dw, final Map<String, List<ResourceViewVO>> reportParametersVo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					Map<String, String> metadata = createMetadataMap(rvo);
					createNewLatexReportResource(metadata, reportParametersVo, window, dw);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveTableViewListener(final MetadataEditorWindow window, final ResourceViewVO rvvo, final Long existingTableViewID, final boolean saveAs) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					Map<String, String> metadata = createMetadataMap(rvo);
					createNewTableViewResource(metadata, rvvo, window, existingTableViewID, saveAs);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveOlapListener(final MetadataEditorWindow window, final OLAPParametersVo olapParametersVo, final OlapWindow ow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					Map<String, String> metadata = createMetadataMap(rvo);
					createNewOlapResource(metadata, olapParametersVo, window, ow);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> getSaveMTListener(final MetadataEditorWindow window, final OLAPParametersVo olapParametersVo, final MTWindow mw) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), "Saving Multidimensional Table", BabelFish.print().pleaseWait());
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					Map<String, String> metadata = createMetadataMap(rvo);
					createNewMTResource(metadata, olapParametersVo, window, mw, loading);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> getSaveTinyMCEReportListener(final MetadataEditorWindow window, final String tinyMCEPanelID, final TinyMCEReportWindow w, final boolean isTemplate) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), "Saving Report", BabelFish.print().pleaseWait());
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					Map<String, String> metadata = createMetadataMap(rvo);
					createNewTinyMCEResource(metadata, tinyMCEPanelID, window, w, loading, isTemplate);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
					loading.destroyLoadingBox();
				}
					
			}
		};
	}

	public static SelectionListener<ComponentEvent> getSaveTableListener(final MetadataEditorWindow window, final Long datasetId, final List<String> tableDimensions) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					createNewTableResource(metadata, datasetId);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveChartListener(final MetadataEditorWindow window, final ChartViewer chartViewer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					createNewChartResource(metadata, chartViewer);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveAsChartListener(final MetadataEditorWindow window, final ChartViewer chartViewer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				MESaveAs.prepopulateChart(chartViewer.getChartViewID(), true, false, chartViewer);
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveTextListener(final MetadataEditorWindow window, final String content, final String referenceDate) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					createNewTextResource(metadata, content, referenceDate);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveTextGroupListener(final MetadataEditorWindow window, final List<Long> textIdList, final TextGroupWindow tgw) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					createNewTextGroupResource(metadata, textIdList, tgw, window);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveReportListener(final MetadataEditorWindow window, final ReportViewer reportViewer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					createNewReportResource(metadata, reportViewer);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getSaveProjectListener(final MetadataEditorWindow window, final List<Long> resourceIdList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					createNewProjectResource(metadata, resourceIdList);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getUpdateMetadataListener(final MetadataEditorWindow window, final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createMetadataMap(rvo);
					updateMetadataResource(metadata, resourceId);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getUpdateLayerMetadataListener(final MetadataEditorWindow window, final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createLayerMetadataMap(rvo);
					updateMetadataResource(metadata, resourceId);
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	/* *************************************************************************************************************************** */
	/*
	 * ************************************************** NEW RESOURCE CREATION **************************************************
	 */
	/* *************************************************************************************************************************** */

	private static void createNewTableResource(final Map<String, String> metadata, final Long datasetId) {
		TableServiceEntry.getInstance().createNewTableResource(datasetId, new AsyncCallback<Long>() {
			public void onSuccess(final Long tableId) {
				if (tableId != null) {
					updateTableMetadata(metadata, tableId);
				} else {
					FenixAlert.error(BabelFish.print().info(), "");
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
			}
		});
	}
	
	private static void createNewMTResource(final Map<String, String> metadata, final OLAPParametersVo olapParametersVo, final MetadataEditorWindow w, final MTWindow mw, final LoadingWindow loading) {
		OlapServiceEntry.getInstance().saveOlap(olapParametersVo, new AsyncCallback<Long>() {
			public void onSuccess(Long id) {
				if (id != null) {
					mw.setMtID(id);
					updateMTMetadata(metadata, id, w, loading);
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
			}
		});
	}
	
	private static void createNewTinyMCEResource(final Map<String, String> metadata, final String tinyMCEPanelID, final MetadataEditorWindow mew, final TinyMCEReportWindow w, final LoadingWindow loading, final boolean isTemplate) {
		try {
			TinyMCEServiceEntry.getInstance().save(TinyMCENativeController.getContent(tinyMCEPanelID), isTemplate, new AsyncCallback<Long>() {
				public void onSuccess(Long id) {
					if (id != null) {
						w.setReportID(id);
						updateTinyMCEMetadata(metadata, id, mew, loading);
					}
				}
				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	private static void createNewOlapResource(final Map<String, String> metadata, final OLAPParametersVo olapParametersVo, final MetadataEditorWindow w, final OlapWindow ow) {
		OlapServiceEntry.getInstance().saveOlap(olapParametersVo, new AsyncCallback<Long>() {
			public void onSuccess(Long id) {
				if (id != null) {
					ow.setResourceViewID(id);
					updateOlapMetadata(metadata, id, w);
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
			}
		});
	}
	
	private static void createNewChartDesignResource(final Map<String, String> metadata, final List<ChartDesignerParametersVO> vos, final MetadataEditorWindow mw, final ChartDesignerWindow cdw, final LoadingWindow loading) {
		try {
			ChartDesignerServiceEntry.getInstance().save(vos, new AsyncCallback<Long>() {
				public void onSuccess(Long id) {
					if (id != null) {
						cdw.setChartDesignID(id);
						updateChartDesignMetadata(metadata, id, mw, loading);
					}
				};
				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
					loading.destroyLoadingBox();
				};
			});
		} catch (FenixGWTException e) {
			loading.destroyLoadingBox();
			e.printStackTrace();
			loading.destroyLoadingBox();
		}
	}

	private static void createNewLatexReportResource(final Map<String, String> metadata, final Map<String, List<ResourceViewVO>> latexReportParameters, final MetadataEditorWindow w, final DesignerWindow dw) {
		try {
			LatexServiceEntry.getInstance().save(latexReportParameters, new AsyncCallback<Long>() {
				public void onSuccess(Long id) {
					if (id != null) {
						dw.setReportID(id);
						updateLatexReportMetadata(metadata, id, w);
					}
				}

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	private static void createNewTableViewResource(final Map<String, String> metadata, final ResourceViewVO rvo, final MetadataEditorWindow w, final Long existingTableViewID, final boolean saveAs) {
		try {
			TableServiceEntry.getInstance().saveTableView(existingTableViewID, rvo, saveAs, new AsyncCallback<Long>() {
				public void onSuccess(Long tableViewID) {
					updateTableViewMetadata(metadata, tableViewID, w);
				}

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	private static void createNewChartResource(final Map<String, String> metadata, final ChartViewer chartViewer) {
		BirtServiceEntry.getInstance().createNewChartResource(chartViewer.getRptdesign(), chartViewer.getChartWizardBean(), new AsyncCallback<Long>() {
			public void onSuccess(final Long id) {
				if (id != null) {
					chartViewer.setChartViewID(id);
					updateChartMetadata(metadata, id);
				} else {
					FenixAlert.error(BabelFish.print().info(), "");
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void createNewTextResource(final Map<String, String> metadata, final String content, final String referenceDate) {
		TextServiceEntry.getInstance().saveText(metadata.get("title"), content, referenceDate, new AsyncCallback<Long>() {
			public void onSuccess(final Long id) {
				if (id != null) {
					updateTextMetadata(metadata, id);

				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void createNewTextGroupResource(final Map<String, String> metadata, final List<Long> textIds, final TextGroupWindow tgw, final MetadataEditorWindow window) {
		TextServiceEntry.getInstance().saveTextGroup(metadata.get("title"), textIds, new AsyncCallback<Long>() {
			public void onSuccess(final Long id) {
				if (id != null) {
					updateTextGroupMetadata(metadata, id, tgw, window);
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void createNewReportResource(final Map<String, String> metadata, final ReportViewer reportViewer) {
		BirtServiceEntry.getInstance().createNewReportResource(reportViewer.getRptDesign(), reportViewer.getReportVo(), new AsyncCallback<Long>() {
			public void onSuccess(final Long id) {
				if (id != null) {
					reportViewer.setReportViewid(id);
					updateReportMetadata(metadata, id);
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void createNewProjectResource(final Map<String, String> metadata, final List<Long> resourceIdList) {
		ProjectManagerServiceEntry.getInstance().createNewResource(metadata.get("title"), resourceIdList, new AsyncCallback<Long>() {
			public void onSuccess(final Long id) {
				if (id != null) {
					updateProjectMetadata(metadata, id);
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	/* ****************************************************************************************************************** */
	/*
	 * ************************************************** GET RESOURCE **************************************************
	 */
	/* ****************************************************************************************************************** */

	private static void getNewChartResource(final Long id) {
		BirtServiceEntry.getInstance().getNewResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(final FenixResourceVo rsrc) {
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
				}
				final List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
				resourceList.add(PMModel.createResourceModel(rsrc));
				PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.CHARTVIEW);
				// Info.display(I18N.print().saveCompleted(), I18N.print().resourceCreatedSuccessfully());
				FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void getNewTableResource(final Long id) {
		try {
			TableServiceEntry.getInstance().getNewResource(id, new AsyncCallback<FenixResourceVo>() {
				public void onSuccess(final FenixResourceVo rsrc) {
					if (REModel.isReIsopen()) {
						ResourceExplorer explorer = REModel.getResourceExplorer();
						CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
					}
					final List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
					resourceList.add(PMModel.createResourceModel(rsrc));
					PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.TABLEVIEW);
					Info.display(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
				}

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}

	private static void getNewTextResource(final Long id) {
		TextServiceEntry.getInstance().getNewTextResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(final FenixResourceVo rsrc) {
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
				}
				final List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
				resourceList.add(PMModel.createResourceModel(rsrc));
				PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.TEXTVIEW);
				// Info.display(I18N.print().saveCompleted(),
				// I18N.print().resourceCreatedSuccessfully());
				FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void getNewTextGroupResource(final Long id, final TextGroupWindow tgw, final MetadataEditorWindow window) {
		TextServiceEntry.getInstance().getNewTextGroupResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(final FenixResourceVo rsrc) {
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
				}
				final List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
				resourceList.add(PMModel.createResourceModel(rsrc));
				PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.TEXTGROUP);
				// Info.display(I18N.print().saveCompleted(),
				// I18N.print().resourceCreatedSuccessfully());

				if (tgw != null)
					tgw.getWindow().close();

				TextServiceEntry.getInstance().getTextGroup(Long.valueOf(rsrc.getId()), new AsyncCallback<TextGroupVO>() {
					public void onSuccess(final TextGroupVO vo) {

						// re-load the window
						new TextGroupWindow(vo).build(rsrc.isHasWritePermission());

						window.getWindow().close();

						FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
					}

					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().error(), e.getMessage());
					}
				});

			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	private static void getNewReportResource(final Long id) {
		BirtServiceEntry.getInstance().getNewResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(final FenixResourceVo rsrc) {
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
				}
				Info.display(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("deprecation")
	private static void getNewLatexReportResource(final Long id, final MetadataEditorWindow w) {
		OlapServiceEntry.getInstance().getNewOlapResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(FenixResourceVo rsrc) {
//				List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
//				resourceList.add(PMModel.createResourceModel(rsrc));
//				PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.LATEXREPORT);
//				w.getWindow().close();
				FenixAlert.info(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	private static void getNewMTResource(final Long id, final MetadataEditorWindow w, final LoadingWindow loading) {
		OlapServiceEntry.getInstance().getNewOlapResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(FenixResourceVo rsrc) {
//				List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
//				resourceList.add(PMModel.createResourceModel(rsrc));
//				PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.OLAP);
				w.getWindow().close();
				loading.destroyLoadingBox();
				FenixAlert.info(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	private static void getNewTinyMCEResource(final Long id, final MetadataEditorWindow w, final LoadingWindow loading) {
		OlapServiceEntry.getInstance().getNewOlapResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(FenixResourceVo rsrc) {
				w.getWindow().close();
				loading.destroyLoadingBox();
				FenixAlert.info(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("deprecation")
	private static void getNewOlapResource(final Long id, final MetadataEditorWindow w) {
		OlapServiceEntry.getInstance().getNewOlapResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(FenixResourceVo rsrc) {
				List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
				resourceList.add(PMModel.createResourceModel(rsrc));
				PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.OLAP);
				w.getWindow().close();
				FenixAlert.info(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	private static void getNewChartDesignResource(final Long id, final MetadataEditorWindow w, final LoadingWindow loading) {
		try {
			ChartDesignerServiceEntry.getInstance().getNewOlapResource(id, new AsyncCallback<FenixResourceVo>() {
				public void onSuccess(FenixResourceVo rsrc) {
					List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
					resourceList.add(PMModel.createResourceModel(rsrc));
					PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.CHARTVIEW);
					w.getWindow().close();
					loading.destroyLoadingBox();
					FenixAlert.info(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
					loading.destroyLoadingBox();
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			loading.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
			loading.destroyLoadingBox();
		}
	}

	@SuppressWarnings("deprecation")
	private static void getNewTableViewResource(final Long id, final MetadataEditorWindow w) {
		w.getWindow().close();
		FenixAlert.info("INFO", "Table View Saved With ID: " + id);
	}

	private static void getNewProjectResource(final Long id) {
		ProjectManagerServiceEntry.getInstance().getNewResource(id, new AsyncCallback<FenixResourceVo>() {
			public void onSuccess(final FenixResourceVo rsrc) {
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
				}
				final List<ResourceChildModel> projectList = new ArrayList<ResourceChildModel>();
				projectList.add(PMModel.createResourceModel(rsrc));
				PMModel.addNewProjectToProjectManager(projectList, PMModel.createResourceModel(rsrc));
				Info.display(BabelFish.print().saveCompleted(), BabelFish.print().resourceCreatedSuccessfully());
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	/* ********************************************************************************************************************* */
	/*
	 * ************************************************** METADATA UPDATE **************************************************
	 */
	/* ********************************************************************************************************************* */

	private static void updateMetadataResource(final Map<String, String> metadata, final Long resourceId) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, resourceId, new AsyncCallback() {
			public void onSuccess(Object result) {
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
				}
				Info.display(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().error(), "The metadata update failed because you do not have update permission or there is a technical problem");
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateTableMetadata(final Map<String, String> metadata, final Long tableId) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, tableId, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewTableResource(tableId);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateChartMetadata(final Map<String, String> metadata, final Long id) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewChartResource(id);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateTextMetadata(final Map<String, String> metadata, final Long id) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewTextResource(id);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateTextGroupMetadata(final Map<String, String> metadata, final Long id, final TextGroupWindow tgw, final MetadataEditorWindow window) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewTextGroupResource(id, tgw, window);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private static void updateMTMetadata(final Map<String, String> metadata, final Long id, final MetadataEditorWindow w, final LoadingWindow loading) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewMTResource(id, w, loading);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private static void updateTinyMCEMetadata(final Map<String, String> metadata, final Long id, final MetadataEditorWindow w, final LoadingWindow loading) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewTinyMCEResource(id, w, loading);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateOlapMetadata(final Map<String, String> metadata, final Long id, final MetadataEditorWindow w) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewOlapResource(id, w);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private static void updateChartDesignMetadata(final Map<String, String> metadata, final Long id, final MetadataEditorWindow w, final LoadingWindow loading) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewChartDesignResource(id, w, loading);
			}

			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
				loading.destroyLoadingBox();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateLatexReportMetadata(final Map<String, String> metadata, final Long id, final MetadataEditorWindow w) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewLatexReportResource(id, w);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateTableViewMetadata(final Map<String, String> metadata, final Long id, final MetadataEditorWindow w) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewTableViewResource(id, w);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateReportMetadata(final Map<String, String> metadata, final Long id) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewReportResource(id);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static void updateProjectMetadata(final Map<String, String> metadata, final Long id) {
		MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new AsyncCallback() {
			public void onSuccess(Object result) {
				getNewProjectResource(id);
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	/* *************************************************************************************************************** */
	/*
	 * ************************************************** UTILITIES **************************************************
	 */
	/* *************************************************************************************************************** */

	@SuppressWarnings("deprecation")
	public static String parseDate(Date date) {
		String p = String.valueOf((1900 + date.getYear()));
		String month = String.valueOf(1 + date.getMonth());
		if ((1 + date.getMonth()) < 10)
			month = "0" + month;
		return p + "-" + month + "-" + String.valueOf(date.getDate());
	}

	private static Map<String, String> createMetadataMap(ResourceVO vo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", vo.getTitle());
		map.put("abstractAbstract", vo.getAbstractAbstract());
		map.put("keywords", vo.getKeywords());
		map.put("provider", vo.getProvider());
		map.put("providerRegion", vo.getProviderRegion());
		map.put("providerContact", vo.getProviderContact());
		map.put("region", vo.getRegion());
		map.put("code", vo.getCode());
		map.put("categories", vo.getCategories());
		if (vo.getStartDate() != null)
			map.put("startDate", parseDate(vo.getStartDate()));
		else
			map.put("startDate", null);
		if (vo.getEndDate() != null)
			map.put("endDate", parseDate(vo.getEndDate()));
		else
			map.put("endDate", null);
		map.put("sharingCode", vo.getSharingCode());
		map.put("dateLastUpdate", parseDate(vo.getDateLastUpdate()));
		map.put("source", vo.getSource());
		map.put("sourceRegion", vo.getSourceRegion());
		map.put("sourceContact", vo.getSourceContact());
		return map;
	}

	private static Map<String, String> createLayerMetadataMap(ResourceVO vo) {
		Map<String, String> map = createMetadataMap(vo);
		if (vo.getResourceSpecificMap() != null)
			for (String field : vo.getResourceSpecificMap().keySet())
				map.put(field, vo.getResourceSpecificMap().get(field));
		return map;
	}

	public static SelectionListener<ButtonEvent> getSaveMapListener(final MetadataEditorWindow window, final MapWindow mapWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				boolean keysCondition = window.getTabPanel().getKeysPanel().validate();
//				boolean generalInfoCondition = window.getTabPanel().getGeneralInfoPanel().validate();
				try {
					boolean isValid = window.getTabPanel().getMetadataPanel().validate();
					ResourceVO rvo = MEController.createResourceVO(window.getTabPanel().getMetadataPanel(), window.getTabPanel().getExtraInfoPanel());
					final Map<String, String> metadata = createLayerMetadataMap(rvo);
					createNewMapResource(metadata, mapWindow);
				} catch (Exception e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static void saveImportedDBFeatureLayer(ResourceVO rvo, Long dbFeatureLayerID, String dbFeatureLayerName) {
		final Map<String, String> metadata = createLayerMetadataMap(rvo);
		updateMetadataResource(metadata, dbFeatureLayerID);
	}

	private static void createNewMapResource(final Map<String, String> metadata, final MapWindow mapWindow) {
		ClientMapView cmv = mapWindow.buildClientMapView();
		MapServiceEntry.getInstance().createMap(cmv, new MapCreated(metadata, mapWindow));
	}

	static class MapCreated implements AsyncCallback<Long> {

		private Map<String, String> metadata;
		private MapWindow mapWindow;

		public MapCreated(Map<String, String> metadata, MapWindow mapWindow) {
			this.metadata = metadata;
			this.mapWindow = mapWindow;
		}

		public void onSuccess(final Long id) {
			MetadataServiceEntry.getInstance().updateMetadata(metadata, id, new MapMetadataUpdated(id, mapWindow));
		}

		public void onFailure(Throwable e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}

	static class MapMetadataUpdated implements AsyncCallback {

		private long id;
		private MapWindow mapWindow;

		public MapMetadataUpdated(long id, MapWindow mapWindow) {
			this.id = id;
			this.mapWindow = mapWindow;
		}

		public void onSuccess(Object result) {
			MapServiceEntry.getInstance().getMap(id, new MapReloaded(mapWindow));
		}

		public void onFailure(Throwable e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}

	static class MapReloaded implements AsyncCallback<ClientMapView> {
		private MapWindow mapWindow;

		public MapReloaded(MapWindow mapWindow) {
			this.mapWindow = mapWindow;
		}

		public void onSuccess(final ClientMapView cmv) {
			Info.display("SaveMap", "SaveMap procedure completed successfully");
			mapWindow.refresh(cmv);
			Info.display("SaveMap", "Map refreshed");
		}

		public void onFailure(Throwable e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}

	}

}
