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

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerParametersCollector;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.designer.client.control.DesignerController;
import org.fao.fenix.web.modules.designer.client.view.DesignerWindow;
import org.fao.fenix.web.modules.designer.common.vo.DesignerVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.SharingCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.MetadataVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.client.view.MTParametersCollector;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.control.TableSaver;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;
import org.fao.fenix.web.modules.text.client.control.TextEditorController;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MESaveAs extends MEOpener {
	
	public static void prepopulateTinyMCEReport(final Long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final TinyMCEReportWindow w, final boolean isTemplate) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					w.setReportID(null);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveTinyMCEReportListener(window, w.getTinyMCEPanel().getCode(), w, isTemplate);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateChartDesign(final Long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final ChartDesignerWindow cdw) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					List<ChartDesignerParametersVO> vos = ChartDesignerParametersCollector.collectParameters(cdw);
					cdw.setChartDesignID(null);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveChartDesignListener(window, vos, cdw);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	public static void prepopulateLatexReport(final Long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final DesignerWindow dw) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					DesignerVO dvo = DesignerController.collectParameters(dw);
					dvo.setReportID(null); // to make it save a new resource
					dw.setReportID(null);  // to make it save a new resource
					Map<String, List<ResourceViewVO>> latexReportParameters = DesignerController.convert(dvo);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveLatexReportListener(window, dw, latexReportParameters);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateTableView(final Long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final TableViewWindow w, final TableVO tvo, final List<TableFilterVO> filterList) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					ResourceViewVO rvvo = TableSaver.collectUserSelections(tvo, filterList);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveTableViewListener(window, rvvo, resourceId, true);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateOlap(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final OlapWindow w) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					OLAPParametersVo olapParametersVo = OlapController.retrieveParameters(w);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveOlapListener(window, olapParametersVo, w);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateMT(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final MTWindow w) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					w.setMtID(null);
					OLAPParametersVo olapParametersVo = MTParametersCollector.retrieveParameters(w);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveMTListener(window, olapParametersVo, w);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateText(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final TextEditor textEditor) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					String year = Integer.toString(textEditor.editorToolBar.dateField.getValue().getYear() + 1900);
					String month = Integer.toString(textEditor.editorToolBar.dateField.getValue().getMonth() + 1);
					String day = Integer.toString(textEditor.editorToolBar.dateField.getValue().getDate());
					
					final String date = year + "-" + month + "-" +day;
					/** if exist removes the <p> tag used when copy and paste from word **/
					String text = TextEditorController.cleanText((textEditor.textArea).getHTML());
					System.out.println("MESaveAs.prepopulateText()\n\n" + text);
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveTextListener(window, text, date);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);

				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateTextGroup(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final List<Long> textIds, final TextGroupWindow tgw) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					final MetadataEditorWindow window = new MetadataEditorWindow();

					window.build(false, isEditable, hasResourceSpecificFields);
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());
					
					System.out.println("MESaveAs.prepopulateTextGroup textList size\n\n" + textIds.size());
					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveTextGroupListener(window, textIds, tgw);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);

				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	public static void prepopulateMap(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final MapWindow mapWindow) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					MetadataEditorWindow window = new MetadataEditorWindow();
					window.build(false, isEditable, hasResourceSpecificFields);

					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveMapListener(window, mapWindow);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateChart(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final ChartViewer chartViewer) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					MetadataEditorWindow window = new MetadataEditorWindow();
					window.build(false, isEditable, hasResourceSpecificFields);

					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveChartListener(window, chartViewer);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void prepopulateChart(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final ChartViewer chartViewer, final MetadataEditorWindow window) {
		
		try {

			final LoadingWindow l = new LoadingWindow("Metadata", "Loading existing metadata.", "Please wait...");
			
			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					l.destroyLoadingBox();
					
					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());

					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveChartListener(window, chartViewer);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
					
					l.destroyLoadingBox();
				};

				public void onFailure(Throwable e) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
					l.destroyLoadingBox();
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
		
	}
	
	public static void prepopulateReport(final long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final ReportViewer reportViewer) {

		try {

			MetadataServiceEntry.getInstance().findMetadataById(resourceId, new AsyncCallback<MetadataVO>() {

				public void onSuccess(MetadataVO vo) {

					MetadataEditorWindow window = new MetadataEditorWindow();
					window.build(false, isEditable, hasResourceSpecificFields);

					ResourceVO rvo = vo.getResourceVo();

					fillGeneralInfoPanel(window, rvo);
					fillKeysPanel(window, vo);
					fillResourceSpecificFields(vo, window);

					window.getTabPanel().getMetadataPanel().getSharingList().setValue(new SharingCodeModelData(rvo.getSharingCode()));
					window.getTabPanel().getMetadataPanel().getGaulList().setValue(vo.getGaulModelData());
					window.getTabPanel().getMetadataPanel().getCategoryList().setValue(vo.getCategoryModelData());
//					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getGeneralInfo());
					window.getTabPanel().getTabPanel().setSelection(window.getTabPanel().getKeys());

					SelectionListener<ButtonEvent> saveListener = MESaver.getSaveReportListener(window, reportViewer);
					window.getButtonsPanel().getSave().addSelectionListener(saveListener);
				};

				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};

			});

		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

}
