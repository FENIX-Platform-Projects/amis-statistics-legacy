package org.fao.fenix.web.modules.chartdesigner.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerExportToHTMLWindow;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerReminder;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartTypeModelData;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartAxisPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.DatasourcePanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelTabItem;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelTabPanel;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.colorpicker.client.ColorPickerWindow;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ChartDesignerController {
	
	public static List<String> PALETTE;
	
	static {
		PALETTE = new ArrayList<String>();
		PALETTE.add("1D4589");
		PALETTE.add("006600");
		PALETTE.add("E8B152");
		PALETTE.add("BD3D3A");
		PALETTE.add("824D8B");
		PALETTE.add("1AA3A8");
	}
	
	public static SelectionListener<ButtonEvent> addToReport(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final List<ChartDesignerParametersVO> ps = ChartDesignerParametersCollector.collectParameters(w);
				final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Creating the Chart.", BabelFish.print().pleaseWait());
				try {
					ChartDesignerServiceEntry.getInstance().addToReport(ps, new AsyncCallback<String>() {
						public void onSuccess(String img) {
							l.destroyLoadingBox();
							TinyMCENativeController.setContent(w.getTinyMCEPanelID(), img);
							w.getWindow().close();
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable e1) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e1.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}

	public static ChangeHandler changeDimension(final ChartDesignerWindow w, final String axis) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				if (axis.equalsIgnoreCase("X"))
					changeDimensionsAgent(w.getChartDesignerPanel().getxAxisPanel(), w, w.getChartDesignerPanel().getDatasourcePanel().getModels(), w.getChartDesignerPanel().getxAxisPanel().getValuesList(), false, new ArrayList<String>());
				else if (axis.equalsIgnoreCase("Y"))
					changeDimensionsAgent(w.getChartDesignerPanel().getyAxisPanel(), w, w.getChartDesignerPanel().getDatasourcePanel().getModels(), w.getChartDesignerPanel().getyAxisPanel().getValuesList(), true, new ArrayList<String>());
			}
		};
	}
	
	public static ChangeHandler changeDimension(final ChartDesignerWindow w, final ChartAxisPanel cap) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				changeDimensionsAgent(cap, w, w.getChartDesignerPanel().getDatasourcePanel().getModels(), cap.getValuesList(), true, new ArrayList<String>());
			}
		};
	}
	
	public static List<DescriptorViewVO> findDimensionsByHeaderOrContentDescriptor(ChartAxisPanel p) {
		List<DescriptorViewVO> vos = new ArrayList<DescriptorViewVO>();
		String descriptorHeader = p.getDimensionList().getItemText(p.getDimensionList().getSelectedIndex());
		String contentDescriptor = p.getDimensionList().getValue(p.getDimensionList().getSelectedIndex());
		for (DescriptorViewVO vo : p.getDescriptorViewVOs()) {
			boolean sameType = vo.getContentDescriptor().equalsIgnoreCase(contentDescriptor);
			boolean sameHeader = vo.getHeader().equalsIgnoreCase(descriptorHeader);
			if (sameHeader || sameType)
				vos.add(vo);
		}
		return vos;
	}
	
	public static List<DescriptorViewVO> selectedDescriptors(List<DescriptorViewVO> vos, ListBox dimensions) {
		List<DescriptorViewVO> selectedVOs = new ArrayList<DescriptorViewVO>();
		for (int i = 0 ; i < dimensions.getItemCount() ; i++) {
			if (dimensions.isItemSelected(i)) {
				String header = dimensions.getItemText(i);
				String type   = dimensions.getValue(i);
				for (DescriptorViewVO dvvo : vos) 
					if ((dvvo.getContentDescriptor().equalsIgnoreCase(type)) && (dvvo.getHeader().equalsIgnoreCase(header)))
						if (!selectedVOs.contains(dvvo))
							selectedVOs.add(dvvo);
			}
		} 
		return selectedVOs;
	}
	
	public static void changeDimensionsAgent(final ChartAxisPanel p, final ChartDesignerWindow w, final List<ResourceChildModel> models, final ListBox valuesList, final boolean addSeriesColorMap, final List<String> selectedCodes) {
		final DatasourcePanel datasourcePanel = w.getChartDesignerPanel().getDatasourcePanel();
		final LookAndFeelTabPanel lookAndFeelPanel = w.getChartDesignerPanel().getLookAndFeelTabPanel();
		List<DescriptorViewVO> vos = p.getDescriptorViewVOs();
		final List<DescriptorViewVO> selectedVOs = selectedDescriptors(vos, p.getDimensionList());
		
//		GWT.log("==========================================================================================================================");
//		GWT.log("p.getDatasetID()? " + p.getDatasetID());
//		GWT.log("w.getChartDesignID()? " + w.getChartDesignID());
//		for (DescriptorViewVO dvvo : selectedVOs)
//			GWT.log(dvvo.getHeader());
//		GWT.log("==========================================================================================================================");
		
		
		final Map<Long, String> idNameMap = new HashMap<Long, String>();
		for (ResourceChildModel m : models) {
			idNameMap.put(Long.valueOf(m.getId()), m.getName());
		}
		List<Long> datasetIDs = new ArrayList<Long>();
		if (p.getDatasetID() != null) {
			datasetIDs.add(p.getDatasetID());
		} else {
			datasetIDs = selectedDatasets(datasourcePanel);
		}
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Retrieving dimension's values.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().loadValues(datasetIDs, selectedVOs, CheckLanguage.getLanguage(), new AsyncCallback<List<UniqueValueVO>>() {
				public void onSuccess(List<UniqueValueVO> values) {
					l.destroyLoadingBox();
					valuesList.clear();
					List<String> valuesAlreadyAdded = new ArrayList<String>();
					p.enableDateSpecificFieldSets((values.get(0).getDatatype().contains("date") || values.get(0).getDatatype().contains("date")));
//					GWT.log("ChartDesignerController @ 186 - " + values.size());
					for (UniqueValueVO v : values) {
						if (v.getDatatype().contains("date") || v.getDatatype().contains("Date")) {
							String lbl = format(v);
//							GWT.log("   " + lbl);
							if (!valuesAlreadyAdded.contains(lbl)) {
//								GWT.log("      ADD: " + lbl);
								valuesList.addItem(lbl, v.getCode());
								valuesAlreadyAdded.add(lbl);
							}
						} else {
							String lbl = v.getLabel();
							if (!valuesAlreadyAdded.contains(lbl)) {
								valuesList.addItem(lbl, v.getCode());
								valuesAlreadyAdded.add(lbl);
							}
						}
					}
//					GWT.log("selectedCodes.isEmpty()? " + selectedCodes.isEmpty());
					if (selectedCodes.isEmpty()) {
						for (int i = 0 ; i < valuesList.getItemCount() ; i++)
							valuesList.setItemSelected(i, true);
					} else {
						for (int i = 0 ; i < valuesList.getItemCount() ; i++) {
//							valuesList.setItemSelected(i, true);
						}
					}
					if (addSeriesColorMap && p.getDatasetID() != null) {
						addSeriesColorAgent(p.getDatasetID(), p, lookAndFeelPanel, idNameMap);
						w.getChartDesignerPanel().getChartButton().setEnabled(true);
						// set a default axis title
						createYAxisTitle(p.getDatasetID(), w);
					}
					w.getChartDesignerPanel().getyAxisButton().setEnabled(true);
					w.getChartDesignerPanel().getxAxisButton().setEnabled(true);
					if ((p.getDatasetID() == null) || (p.getDatasetID().equals("null"))) {
//						String xAxisTitle = w.getChartDesignerPanel().getxAxisPanel().getDimensionList().getItemText(w.getChartDesignerPanel().getxAxisPanel().getDimensionList().getSelectedIndex());
//						w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getxAxisTitle().setValue(xAxisTitle);
//						w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getxAxisNumberOfIntervals().setValue(String.valueOf(1 + values.size()));
					}
					if (p.getUseFromDateToDate().getValue() || p.getUseMostRecentData().getValue()) {
						for (int i = 0 ; i < valuesList.getItemCount() ; i++)
							valuesList.setItemSelected(i, true);
					}
					if (w.getChartDesignID() != null) {
						selectSavedValues(p, w, selectedVOs.get(0).getContentDescriptor());
					}
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable e1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e1.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static void selectSavedValues(final ChartAxisPanel p, final ChartDesignerWindow w, final String contentDescriptor) {
//		GWT.log("ChartDesignerController @ 249 selectSavedValues");
		if (w.getChartDesignID() != null) {
			final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Retrieving Saved Values.", BabelFish.print().pleaseWait());
			try {
				ChartDesignerServiceEntry.getInstance().findSavedCodes(p.getDatasetID(), w.getChartDesignID(), contentDescriptor, new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> codes) {
						l.destroyLoadingBox();
//						GWT.log(">>>>> " + contentDescriptor);
//						GWT.log(">>>>> " + codes.size() + " codes");
//						GWT.log(">>>>> " + p.getValuesList().getItemCount() + " p.getValuesList().getItemCount()");
						for (String code : codes) {
							for (int i = 0 ; i < p.getValuesList().getItemCount() ; i++)
								if (p.getValuesList().getValue(i).equalsIgnoreCase(code))
									p.getValuesList().setItemSelected(i, true);
						}
						l.destroyLoadingBox();
					}
					public void onFailure(Throwable E2) {
						l.destroyLoadingBox();
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E2.getMessage());
						l.destroyLoadingBox();
					}
				});
			} catch (FenixGWTException E1) {
				l.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
				l.destroyLoadingBox();
			}
		}
	}
	
	public static void createYAxisTitle(Long datasetID, ChartDesignerWindow w) {
		String yAxisTitle = "";
		List<Long> selectedIDs = new ArrayList<Long>();
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue()) {
				Long id = Long.valueOf((String)cb.getData("DATASET_ID"));
				selectedIDs.add(id);
			}
		}
		List<String> knownLabels = new ArrayList<String>();
		for (Long panelDatasetID : w.getChartDesignerPanel().getPlotDimensionPanel().getMeasurementUnitMap().keySet()) {
			if (selectedIDs.contains(panelDatasetID)) {
				ListBox lb = w.getChartDesignerPanel().getPlotDimensionPanel().getMeasurementUnitMap().get(panelDatasetID);
				int count = count(lb);
				if (count == 0) {
					for (int i = 0 ; i < lb.getItemCount() ; i++) {
						if (!knownLabels.contains(lb.getItemText(i))) {
							yAxisTitle += lb.getItemText(i) + ",";
							knownLabels.add(lb.getItemText(i));
						}
					}
					yAxisTitle.substring(0, yAxisTitle.length() - 1);
				} else {
					for (int i = 0 ; i < lb.getItemCount() ; i++) {
						if (lb.isItemSelected(i) && !knownLabels.contains(lb.getItemText(i))) { 
							yAxisTitle += lb.getItemText(i) + ",";
							knownLabels.add(lb.getItemText(i));
						}
					}
					yAxisTitle.substring(0, yAxisTitle.length() - 1);
				}
			}
		}
		yAxisTitle = yAxisTitle.substring(0, yAxisTitle.length() - 1);
		for (LookAndFeelTabItem ti : w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().values())
			ti.getAxisTitle().setValue(yAxisTitle);
	}
	
	public static ChangeHandler filterChange(final Long datasetID, final ChartDesignerWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				createYAxisTitle(datasetID, w);
			}
		};
	}
	
	public static int count(ListBox lb) {
		int count = 0;
		for (int i = 0 ; i < lb.getItemCount() ; i++)
			if (lb.isItemSelected(i))
				count++;
		return count;
	}
	
	public static String format(UniqueValueVO vo) {
		String lbl = vo.getLabel();
		if ((vo.getPeriodType() != null) || !vo.getPeriodType().equals("")) {
			if (vo.getPeriodType().equalsIgnoreCase("MONTHLY")) {
				lbl = vo.getCode().substring(5, 7) + "-" + vo.getCode().substring(0, 4);
			} else if (vo.getPeriodType().equalsIgnoreCase("ANNUAL") || vo.getPeriodType().equalsIgnoreCase("YEARLY")) {
				lbl = vo.getCode().substring(0, 4);
			}
		}
		return lbl;
	}
	
	public static SelectionListener<ButtonEvent> loadDataset(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new ResourceExplorerDataset(w);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> selectAll(final ListBox l, final boolean select) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for(int i = 0 ; i < l.getItemCount() ; i++)
					l.setItemSelected(i, select);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> createNewSeries(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getChartDesignerPanel().getDatasourcePanel().addSeries("Bar Chart", new ArrayList<DatasetVO>(), "bar", w, true);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addDataset(final ChartDesignerWindow w, final ChartDesignerDatasourceFieldSet fs) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new ResourceExplorerDataset(w, fs);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addDataset(final ChartDesignerWindow w, final String selectedChartType) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				addDatasetAgent(w, selectedChartType);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> addDatasetMenuItem(final ChartDesignerWindow w, final String selectedChartType) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				addDatasetAgent(w, selectedChartType);
			}
		};
	}
	
	public static void addDatasetAgent(ChartDesignerWindow w, String selectedChartType) {
		w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getDatasourcePanel().getLayoutContainer());
		if (selectedChartType.equalsIgnoreCase("BARLINE")) {
			ChartDesignerDatasourceFieldSet fs1 = new ChartDesignerDatasourceFieldSet("BAR");
			new ResourceExplorerDataset(w, fs1);
			FenixAlert.info(BabelFish.print().info(), "Please Select the Dataset for the Bar Chart");
			ChartDesignerDatasourceFieldSet fs2 = new ChartDesignerDatasourceFieldSet("LINE");
			new ResourceExplorerDataset(w, fs2);
			FenixAlert.info(BabelFish.print().info(), "Please Select the Dataset for the Line Chart");
		} else {
			ChartDesignerDatasourceFieldSet fs = new ChartDesignerDatasourceFieldSet(selectedChartType);
			new ResourceExplorerDataset(w, fs);
		}
	}
	
	public static SelectionListener<ButtonEvent> showChartTypePanel(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getChartTypePanel().getLayoutContainer());
			}
		};
	}
	
	public static void loadDatasetAgent(final ChartDesignerWindow w, final ChartDesignerDatasourceFieldSet fs, final boolean addDatasetPanels) {
		final List<ResourceChildModel> models = w.getChartDesignerPanel().getDatasourcePanel().getModels();
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Retrieving datasets, dimensions and values.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().loadDataset(models, CheckLanguage.getLanguage(), new AsyncCallback<ResourceViewVO>() {
				public void onSuccess(ResourceViewVO rvvo) {
					l.destroyLoadingBox();
					if (addDatasetPanels)
						addDatasetPanels(w, rvvo.getDatasets(), fs, true);
					createYAxisPanels(w.getChartDesignerPanel(), fs.getDatasets());
					fillXAxisDimensions(w.getChartDesignerPanel());
					fillYAxisDimensions(w.getChartDesignerPanel(), fs.getDatasets());
					addAxisFormatPanel(w.getChartDesignerPanel(), fs.getDatasets(), true);
					fillPlotDimensions(w, fs.getDatasets());
					w.getChartDesignerPanel().getxAxisPanel().getDimensionList().addChangeHandler(changeDimension(w, "X"));
					for (ChartAxisPanel cap : w.getChartDesignerPanel().getAxisTabPanel().getChartXAxisPanelsMap().values())
						cap.getDimensionList().addChangeHandler(changeDimension(w, cap));
					for (ChartAxisPanel cap : w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().values()) {
						cap.getDimensionList().addChangeHandler(changeDimension(w, cap));
						cap.getValuesList().addChangeHandler(valuesChangeHandler(cap, w.getChartDesignerPanel().getLookAndFeelTabPanel()));
					}
					chartTypeSelectionChangedAgent(w, true);
					w.getChartDesignerPanel().getDatasourceButton().setEnabled(true);
					w.getChartDesignerPanel().getxAxisButton().setEnabled(true);
					linkMinValuesToLabelsDistanceFromXAxis(w);
					selectDatesOnXAxis(w);
					quoteSources(w);
					l.destroyLoadingBox();
				};
				public void onFailure(Throwable e1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e1.getMessage());
					e1.printStackTrace();
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static void selectDatesOnXAxis(ChartDesignerWindow w) {
		ChartAxisPanel xp = w.getChartDesignerPanel().getxAxisPanel();
		for (int i = 0 ; i < xp.getDimensionList().getItemCount() ; i++)
			if (xp.getDimensionList().getValue(i).contains("date") || xp.getDimensionList().getValue(i).contains("Date"))
				xp.getDimensionList().setSelectedIndex(i);
		changeDimensionsAgent(xp, w, w.getChartDesignerPanel().getDatasourcePanel().getModels(), xp.getValuesList(), true, new ArrayList<String>());
		restoreButtonsColor(w.getChartDesignerPanel());
		w.getChartDesignerPanel().getyAxisButton().setText("<b><font color='#CA1616'><u>" + w.getChartDesignerPanel().getyAxisButton().getText() + "</u><font></b>");
		w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getAxisTabPanel().getLayoutContainer());
	}
	
	public static void linkMinValuesToLabelsDistanceFromXAxis(ChartDesignerWindow w) {
		for (LookAndFeelTabItem ti : w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().values())
			ti.getAxisFromValue().addListener(Events.KeyUp, axisValueFromListener(ti.getAxisFromValue(), w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getLabelsDistanceFromAxis()));
	}
	
	public static void link(final ListBox from, final List<ListBox> tos) {
		from.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				for (ListBox l : tos)
					l.setSelectedIndex(from.getSelectedIndex());
			}
		});
	}
	
	public static void link(final TextField<String> from, final List<TextField<String>> tos) {
		from.addListener(Events.KeyUp, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				for (TextField<String> to : tos)
					to.setValue(from.getValue());
			}
		}); 
	}
	
	public static void link(final CheckBox from, final List<CheckBox> tos) {
//		for (CheckBox to : tos)
//			to.setValue(false);
		from.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				for (CheckBox to : tos)
					to.setValue(from.getValue());
			}
		}); 
	}
	
	public static void addGoToButtons(ChartDesignerWindow w, List<DatasetVO> datasets) {
		Map<Long, String> map = new HashMap<Long, String>();
		for (DatasetVO dvo : datasets)
			map.put(Long.valueOf(dvo.getDsId()), dvo.getDatasetName());
		w.getChartDesignerPanel().getxAxisPanel().addGoToButtons(w, map);
	}
	
	public static void addAxisFormatPanel(ChartDesignerPanel chartDesignerPanel, List<DatasetVO> datasets, boolean calculateYAxisMaxValue) {
		for (DatasetVO dvo : datasets) 
			chartDesignerPanel.getLookAndFeelTabPanel().addDataset(Long.valueOf(dvo.getDsId()), dvo.getDatasetName(), calculateYAxisMaxValue);
	}
	
	public static Listener<BaseEvent> datasetCheckBox(final CheckBox cb, final ChartDesignerWindow w, final ChartDesignerDatasourceFieldSet fs) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				loadDatasetAgent(w, fs, false);
			};
		};
	}
	
	public static void createYAxisPanels(final ChartDesignerPanel chartDesignerPanel, List<DatasetVO> datasets) {
		for (DatasetVO dvo : datasets) {
			if (!chartDesignerPanel.getAxisTabPanel().getTabItemsMap().containsKey(Long.valueOf(dvo.getDsId()))) {
				final ChartAxisPanel cap = new ChartAxisPanel("chart_designer_y_axis_suggestion", "600px");
				cap.setDatasetID(Long.valueOf(dvo.getDsId()));
				chartDesignerPanel.getAxisTabPanel().getChartYAxisPanelsMap().put(Long.valueOf(dvo.getDsId()), cap);
				TabItem ti = new TabItem(dvo.getDatasetName());
				ti.add(cap.getWrapper());
				chartDesignerPanel.getAxisTabPanel().getTabItemsMap().put(Long.valueOf(dvo.getDsId()), ti);
				chartDesignerPanel.getAxisTabPanel().getTabPanel().add(ti);
				chartDesignerPanel.getAxisTabPanel().getTabPanel().setSelection(ti);
			}
		}
	}
	
	public static void fillYAxisDimensions(ChartDesignerPanel chartDesignerPanel, List<DatasetVO> datasets) {
		for (int i = 0 ; i < datasets.size() ; i++) {
			DatasetVO d = datasets.get(i);
			ChartAxisPanel p = chartDesignerPanel.getAxisTabPanel().getChartYAxisPanelsMap().get(Long.valueOf(d.getDsId()));
			p.addDimensions(d.getDescriptorViews());
			chartDesignerPanel.getyAxisDatasetIDs().add(Long.valueOf(d.getDsId()));
		}
	}
	
	public static void fillXAxisDimensions(ChartDesignerPanel chartDesignerPanel) {
		List<DatasetVO> datasets = new ArrayList<DatasetVO>();
		for (ChartDesignerDatasourceFieldSet fs : chartDesignerPanel.getDatasourcePanel().getFieldSets())
			for (DatasetVO dataset : fs.getDatasets())
				datasets.add(dataset);
		List<DescriptorViewVO> vos = findCommonDimensions(chartDesignerPanel, datasets);
		chartDesignerPanel.getxAxisPanel().addDimensions(vos);
	}
	
	public static List<DescriptorViewVO> findCommonDimensions(ChartDesignerPanel chartDesignerPanel, List<DatasetVO> datasets) {
		List<DescriptorViewVO> vos = new ArrayList<DescriptorViewVO>();
		List<Long> selectedDatasetIDs = selectedDatasets(chartDesignerPanel);
		for (int i = 0 ; i < datasets.size() ; i++) {
			DatasetVO d = datasets.get(i);
			if (selectedDatasetIDs.contains(Long.valueOf(d.getDsId()))) {
				if (selectedDatasetIDs.size() > 1) {
					DescriptorViewVO fake = new DescriptorViewVO();
					fake.setHeader("********** " + d.getDatasetName() + " **********");
					fake.setContentDescriptor("");
					vos.add(fake);
				}
				for (DescriptorViewVO vo : d.getDescriptorViews()) {
					vos.add(vo);
				}
			}
		}
		return vos;
	}
	
	public static List<Long> selectedDatasets(ChartDesignerPanel chartDesignerPanel) {
		List<Long> ids = new ArrayList<Long>();
		for (ChartDesignerDatasourceFieldSet fs : chartDesignerPanel.getDatasourcePanel().getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue())
				ids.add(Long.valueOf((String)cb.getData("DATASET_ID")));
		}
		return ids;
	}
	
	public static List<Long> selectedDatasets(DatasourcePanel datasourcePanel) {
		List<Long> ids = new ArrayList<Long>();
		for (ChartDesignerDatasourceFieldSet fs : datasourcePanel.getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue())
				ids.add(Long.valueOf((String)cb.getData("DATASET_ID")));
		}
		return ids;
	}
	
	public static void fillPlotDimensions(ChartDesignerWindow w, List<DatasetVO> datasets) {
		ChartDesignerPanel chartDesignerPanel = w.getChartDesignerPanel();
		for (DatasetVO d : datasets) {
			if (!chartDesignerPanel.getPlotDimensionDatasetIDs().contains(Long.valueOf(d.getDsId()))) { 
				chartDesignerPanel.getPlotDimensionPanel().addFilter(d);
				chartDesignerPanel.getPlotDimensionDatasetIDs().add(Long.valueOf(d.getDsId()));
			}
		}
		
		// add listeners to the filters
		for (Long datasetID : chartDesignerPanel.getPlotDimensionPanel().getFiltersMap().keySet()) {
			List<ListBox> lbs = chartDesignerPanel.getPlotDimensionPanel().getFiltersMap().get(datasetID);
			for (ListBox lb : lbs) {
				String descriptor = chartDesignerPanel.getPlotDimensionPanel().getContentDescriptorMap().get(lb);
				if (descriptor.equalsIgnoreCase("measurementUnit"))
					lb.addChangeHandler(ChartDesignerController.filterChange(datasetID, w));
			}
		}
	}
	
	public static void quoteSources(ChartDesignerWindow w) {
//		List<String> names = getSelectedDatasetNames(w, true);
//		String s = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt;'><b>Datasource(s): </b> ";
//		for (int i = 0 ; i < names.size() ; i++) {
//			s += names.get(i);
//			if (i < names.size() - 1)
//				s += ", ";
//		}
//		s += "</div>";
//		w.getChartDesignerPanel().getChartPanel().getSuggestionHTML().setHTML(s);
//		HTML h = new HTML(s);
//		w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getSubTitle().setValue(h.getText());
//		names = getSelectedDatasetNames(w, false);
//		s = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt;'>";
//		for (int i = 0 ; i < names.size() ; i++) {
//			s += names.get(i);
//			if (i < names.size() - 1)
//				s += ", ";
//		}
//		s += "</div>";
//		w.getChartDesignerPanel().getChartPanel().getSuggestionHTML().setHTML(s);
//		h = new HTML(s);
//		w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getTitle().setValue(h.getText());
		List<String> sources = getSelectedDatasetSources(w, true);
		String source = "Source: ";
		for (int i = 0 ; i < sources.size() ; i++) {
			source += sources.get(i);
			if (i < sources.size() - 1)
				source += ", ";
		}
		w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getSubTitle().setValue(source);
	}
	
	public static List<String> getSelectedDatasetNames(ChartDesignerWindow w, boolean showSource) {
		List<String> names = new ArrayList<String>();
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue()) {
				String title  = (String)cb.getData("DATASET_NAME"); 
				String source = (String)cb.getData("DATASET_SOURCE");
				String name = title; 
				if (showSource)
					name += " [" + source + "]";
				names.add(name);
			}
		}
		return names;
	}
	
	public static List<String> getSelectedDatasetSources(ChartDesignerWindow w, boolean showSource) {
		List<String> names = new ArrayList<String>();
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue()) {
				String source = (String)cb.getData("DATASET_SOURCE");
				names.add(source);
			}
		}
		return names;
	}
	
	public static void addDatasetPanels(ChartDesignerWindow w, List<DatasetVO> dvos, ChartDesignerDatasourceFieldSet fs, boolean calculateYAxisMaxValue) {
		for (DatasetVO dvo : dvos) {
			if (!w.getChartDesignerPanel().getDatasourcePanel().getAddedDatasets().contains(Long.valueOf(dvo.getDsId()))) {
				w.getChartDesignerPanel().getDatasourcePanel().getFieldSets().add(fs);
				w.getChartDesignerPanel().getDatasourcePanel().getWrapper().add(fs.build(dvo, w, calculateYAxisMaxValue));
				w.getChartDesignerPanel().getDatasourcePanel().getWrapper().getLayout().layout();
				w.getChartDesignerPanel().getDatasourcePanel().getAddedDatasets().add(Long.valueOf(dvo.getDsId()));
			} else {
				FenixAlert.info(BabelFish.print().info(), "'" + dvo.getDatasetName() + "' has already been added to your current selection.");
			}
		}
	}
	
	public static void addDatasetPanels(ChartDesignerWindow w, DatasetVO dvo, ChartDesignerDatasourceFieldSet fs, boolean calculateYAxisMaxValue) {
		if (!w.getChartDesignerPanel().getDatasourcePanel().getAddedDatasets().contains(Long.valueOf(dvo.getDsId()))) {
			w.getChartDesignerPanel().getDatasourcePanel().getFieldSets().add(fs);
			w.getChartDesignerPanel().getDatasourcePanel().getWrapper().add(fs.build(dvo, w, calculateYAxisMaxValue));
			w.getChartDesignerPanel().getDatasourcePanel().getWrapper().getLayout().layout();
			w.getChartDesignerPanel().getDatasourcePanel().getAddedDatasets().add(Long.valueOf(dvo.getDsId()));
		}
	}
	
	public static SelectionListener<ButtonEvent> panelSelector(final ChartDesignerPanel p, final LayoutContainer l) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				p.getLayout().setActiveItem(l);
				restoreButtonsColor(p);
				ce.getButton().setText("<b><font color='#CA1616'><u>" + ce.getButton().getText() + "</u><font></b>");
			}
		};
	}
	
	public static void restoreButtonsColor(ChartDesignerPanel p) {
		p.getDatasourceButton().setText(BabelFish.print().chart_designer_datasource());
		p.getxAxisButton().setText(BabelFish.print().chart_designer_x_axis());
		p.getyAxisButton().setText(BabelFish.print().chart_designer_tab_axis());
		p.getPlotDimensionButton().setText(BabelFish.print().chart_designer_plot_dimension());
		p.getLookAndFeelButton().setText(BabelFish.print().chart_designer_look_and_feel());
		p.getDataFrequencyButton().setText(BabelFish.print().chart_designer_data_frequency());
		p.getrButton().setText(BabelFish.print().chart_designer_r());
		p.getSaveButton().setText(BabelFish.print().save());
		p.getChartButton().setText(BabelFish.print().chart_designer_chart_panel());
	}
	
	public static SelectionListener<ButtonEvent> showManual(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getChartDesignerManualWindow().build();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> lookAndFeelPanelSelector(final ChartDesignerPanel p, final LayoutContainer l) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				p.getLayout().setActiveItem(l);
				for (TabItem ti : p.getAxisTabPanel().getTabItemsMap().values()) {
					addSeriesColorAgent(p, ti.getText());
				}
			}
		};
	}
	
	public static void fillFontSizeList(ListBox l) {
		for (int i = 5 ; i <= 105 ; i++) 
			l.addItem(String.valueOf(i - 4), String.valueOf((double)i/10));
		l.setSelectedIndex(5);
	}
	
	public static void fillPointTypeList(ListBox l) {
		l.addItem("-", "0");
		for (int i = 1 ; i <= 25 ; i++)
			l.addItem("Symbol " + i, String.valueOf(i));
	}
	
	public static void fillDatesFormat(ListBox l) {
		l.addItem("2010-12-31", "YYYY-MM-DD");
		l.addItem("31-12-10", "DD-MM-YY");
		l.addItem("12-2010", "MM-YYYY");
		l.addItem("2010", "YYYY");
		l.addItem("31/12/2010", "DD/MM/YYYY");
		l.addItem("31/12/10", "DD/MM/YY");
		l.addItem("31-Dec", "DD-Mmm");
		l.addItem("31-Dec-10", "DD-Mmm-YY");
		l.addItem("Dec-10", "Mmm-YY");
		l.addItem("December-10", "Month-YY");
		l.addItem("31-Dec-2010", "DD-Mmm-YYYY");
		l.addItem("31.12.2010", "DD.MM.YYYY");
	}
	
	public static void fillPlotSymbolList(ListBox l) {
		l.addItem("O", "1");
		l.addItem("Triangle", "2");
		l.addItem("+", "3");
		l.addItem("X", "4");
		l.addItem("Type 05", "5");
		l.addItem("Type 06", "6");
		l.addItem("Type 07", "7");
		l.addItem("Type 08", "8");
		l.addItem("Type 09", "9");
		l.addItem("Type 10", "10");
		l.addItem("Type 11", "11");
		l.addItem("Type 12", "12");
		l.addItem("Type 13", "13");
		l.addItem("Type 14", "14");
		l.addItem("Type 15", "15");
		l.addItem("Type 16", "16");
		l.addItem("Type 17", "17");
		l.addItem("Type 18", "18");
		l.addItem("Type 19", "19");
		l.addItem("Type 20", "20");
		l.addItem("Type 21", "21");
		l.addItem("Type 22", "22");
		l.addItem("Type 23", "23");
		l.addItem("Type 24", "24");
		l.addItem("Type 25", "25");
	}
	
	public static void fillFontFamilyList(ListBox l) {
		l.addItem("Normal", "1");
		l.addItem("Bold", "2");
		l.addItem("Italics", "3");
		l.addItem("Bold Italics", "4");
	}
	
	public static void fillLatestDataList(ListBox l) {
		for (int i = 0 ; i < 31 ; i++) 
			l.addItem(String.valueOf(i), String.valueOf(i));
	}
	
	public static ClickHandler colorPicker(final HTML html) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
//				ColorPicker cp = new ColorPicker(html);
//				cp.build(ColorPickerCaller.DESIGNER);	
				new ColorPickerWindow(html).build();
			}
		};
	}
	
	public static Listener<BaseEvent> enableBothAxis(final CheckBox hasXandY, final CheckBox hasX, final CheckBox hasY) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				hasX.setValue(hasXandY.getValue());
				hasY.setValue(hasXandY.getValue());
			}
		};
	}
	
	public static ChangeHandler createSeries(final ChartDesignerPanel p, final Long datasetID, final String datasetTitle) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				Map<String, String> headerColorMap = new HashMap<String, String>();
				for (int i = 0 ; i < p.getyAxisPanel().getValuesList().getItemCount() ; i++)
					if (p.getyAxisPanel().getValuesList().isItemSelected(i))
						headerColorMap.put(p.getyAxisPanel().getValuesList().getItemText(i), "#6600FF");
//				p.getLookAndFeelTabPanel().addSeries(datasetID, datasetTitle, headerColorMap);
			}
		};
	}
	
	public static void viewChartAgent(final ChartDesignerWindow w) {
		final List<ChartDesignerParametersVO> ps = ChartDesignerParametersCollector.collectParameters(w);
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Creating the Chart.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().viewChart(ps, new AsyncCallback<String>() {
				public void onSuccess(String filename) {
					l.destroyLoadingBox();
					w.getChartDesignerPanel().getChartPanel().setImage(filename, String.valueOf(ps.get(0).getImageWidth()) + "px", String.valueOf(ps.get(0).getImageHeight()) + "px");
					w.getChartDesignerPanel().enableButtons();
					w.enableToolbar();
					restoreButtonsColor(w.getChartDesignerPanel());
					w.getChartDesignerPanel().getChartButton().setText("<b><font color='#CA1616'><u>" + w.getChartDesignerPanel().getChartButton().getText() + "</u><font></b>");
					w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getChartPanel().getLayoutContainer());
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable e1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e1.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static SelectionListener<ButtonEvent> viewChart(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent be) {
				try {
					ChartDesignerValidator.isValid(w);
					viewChartAgent(w);
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
				}
			}
		};
	}
	
	public static void addSeriesColorAgent(ChartDesignerPanel p, List<DatasetVO> datasets) {
		for (DatasetVO dvo : datasets)
			addSeriesColorAgent(p, dvo.getDatasetName());
	}
	
	public static void addSeriesColorAgent(Long datasetID, ChartAxisPanel p, LookAndFeelTabPanel lfp, Map<Long, String> idNameMap) {
		Map<String, String> headerColorMap = new HashMap<String, String>();
		ListBox l = p.getValuesList();
		for (int i = 0 ; i < l.getItemCount() ; i++) {
			if (l.isItemSelected(i)) {
				headerColorMap.put(l.getItemText(i), createRandomHex(p.getDatasetID()));
			}
		}
		lfp.getDatasetSettings().get(datasetID).addSeriesColorAgent(headerColorMap);
		
		
//		for (Long datasetID : idNameMap.keySet())
//			GWT.log("   DATASET_ID: " + datasetID);
//		for (Long datasetID : idNameMap.keySet()) {
//			GWT.log("datasetID " + datasetID);
//			Map<String, String> headerColorMap = new HashMap<String, String>();
//			ListBox l = p.getValuesList();
//			for (int i = 0 ; i < l.getItemCount() ; i++) {
//				if (l.isItemSelected(i)) {
//					headerColorMap.put(l.getItemText(i), createRandomHex(p.getDatasetID()));
//				}
//			}
////			lfp.getDatasetSettings().get(datasetID).addSeriesColorAgent(headerColorMap);
//			for (LookAndFeelTabItem ti : lfp.getTabItems())
//				if (ti.getDatasetID().longValue() == datasetID)
//					ti.addSeriesColorAgent(headerColorMap);
//		}
	}
	
	public static void addSeriesColorAgent(ChartDesignerPanel p, String datasetTitle) {
		for (Long dsid : p.getAxisTabPanel().getChartYAxisPanelsMap().keySet()) {
			Map<String, String> headerColorMap = new HashMap<String, String>();
			ListBox l = p.getAxisTabPanel().getChartYAxisPanelsMap().get(dsid).getValuesList();
			for (int i = 0 ; i < l.getItemCount() ; i++)
				if (l.isItemSelected(i))
					headerColorMap.put(l.getItemText(i), createRandomHex(p.getAxisTabPanel().getDatasetID()));
//			p.getLookAndFeelTabPanel().addSeries(dsid, datasetTitle, headerColorMap);
		}
	}
	
	public static String createRandomHex(Long datasetID) {
		String hex = "#";
		if (datasetID != null) {
			if (PALETTE.size() > 0) {
				hex += PALETTE.get(0);
				PALETTE.remove(0);
			} else {
				for (int i = 0 ; i < 6 ; i++)
					hex += Integer.toHexString((int)((Math.random() * 1000) % 16)).toUpperCase();
			}
		} else {
			for (int i = 0 ; i < 6 ; i++)
				hex += Integer.toHexString((int)((Math.random() * 1000) % 16)).toUpperCase();
		}
		return hex;
	}
	
	public static void createFirstSeries(ChartDesignerWindow w) {
		String header = "Bar Chart";
		String selectedChartType = "bar";
//		if (w.getChartDesignerPanel().getChartTypePanel().getLineChartButton().getValue()) {
//			header = "Line Chart";
//			selectedChartType = "line";
//		}
		w.getChartDesignerPanel().getDatasourcePanel().addSeries(header, new ArrayList<DatasetVO>(), selectedChartType, w, true);
	}
	
	public static Listener<BaseEvent> useThisDatasetHandler(final ChartDesignerDatasourceFieldSet fs, final ChartDesignerWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				CheckBox cb = fs.getUseThisDataset();
				Long datasetID = Long.valueOf((String)cb.getData("DATASET_ID"));
				w.getChartDesignerPanel().getAxisTabPanel().getTabItemsMap().get(datasetID).setEnabled(cb.getValue());
				w.getChartDesignerPanel().getPlotDimensionPanel().getFieldSetMap().get(datasetID).setEnabled(cb.getValue());
				createYAxisTitle(datasetID, w);
//				loadDatasetAgent(w, fs, false);
			}
		};
	}
	
	public static void calculateYMaxValue(final TextField<String> stepField, List<Long> datasetIDs) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Calculate Max Value", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().calculateAxisStep(datasetIDs, new AsyncCallback<String>() {
				public void onSuccess(String step) {
					l.destroyLoadingBox();
					stepField.setValue(superCeil(step));
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E0) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E0.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static void calculateYMaxValue(final ChartDesignerWindow w, final ChartDesignerParametersVO p, final TextField<String> stepField, List<Long> datasetIDs) {
		GWT.log("calculateYMaxValue...");
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Calculate Max Value", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().calculateAxisStep(datasetIDs, new AsyncCallback<String>() {
				public void onSuccess(String step) {
					l.destroyLoadingBox();
					stepField.setValue(superCeil(step));
					GWT.log("calculateYMaxValue... " + stepField.getValue() + " >>> " + step);
					selectValues(w, p);
					viewChartAgent(w);
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E0) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E0.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static void selectValues(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		Long datasetID = p.getDatasourceIDs().get(0);
		for (int i = 0 ; i < w.getChartDesignerPanel().getxAxisPanel().getValuesList().getItemCount() ; i++) 
			w.getChartDesignerPanel().getxAxisPanel().getValuesList().setItemSelected(i, false);
		for (int i = 0 ; i < w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().get(datasetID).getValuesList().getItemCount() ; i++)
			w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().get(datasetID).getValuesList().setItemSelected(i, false);
		for (String code : p.getxCodes()) {
			for (int i = 0 ; i < w.getChartDesignerPanel().getxAxisPanel().getValuesList().getItemCount() ; i++) {
				if (w.getChartDesignerPanel().getxAxisPanel().getValuesList().getValue(i).equals(code)) {
					w.getChartDesignerPanel().getxAxisPanel().getValuesList().setItemSelected(i, true);
					break;
				}
			}
		}
		for (String code : p.getyAxes().get(0).getyCodes().keySet()) {
			for (int i = 0 ; i < w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().get(datasetID).getValuesList().getItemCount() ; i++) {
				if (w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().get(datasetID).getValuesList().getValue(i).equals(code)) {
					w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().get(datasetID).getValuesList().setItemSelected(i, true);
					break;
				}
			}
		}
	}
	
	public static String  superCeil(String value) {
		int firstDigit = Integer.valueOf(value.substring(0, 1));
		int power = 0;
		for (int i = 0 ; i < value.length() ; i++) 
			power++;
		return String.valueOf((firstDigit + 1) * Math.pow(10, (power - 1)));
	}
	
	public static SelectionListener<ButtonEvent> showSettingsPanel(final VerticalPanel wrapper, final VerticalPanel settings) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				showSettingsPanelAgent(wrapper, settings);
			}
		};
	}
	
	public static void showSettingsPanelAgent(final VerticalPanel wrapper, final VerticalPanel settings) {
		wrapper.removeAll();
		wrapper.add(settings);
		wrapper.getLayout().layout();
	}
	
	public static ChangeHandler valuesChangeHandler(final ChartAxisPanel p, final LookAndFeelTabPanel lfp) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				LookAndFeelTabItem ti = lfp.getDatasetSettings().get(p.getDatasetID());
				Map<String, String> map = new HashMap<String, String>();
				ListBox l = p.getValuesList();
				for (int i = 0 ; i < l.getItemCount() ;i++)
					if (l.isItemSelected(i))
						map.put(l.getItemText(i), createRandomHex(p.getDatasetID()));
				ti.addSeriesColorAgent(map);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addSeriesColor(final LookAndFeelTabItem ti) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Map<TextField<String>, HTML> m = ti.getSeriesColorMap();
				ti.addSeriesColorAgent();
			}
		};
	}
	
	public static SelectionChangedListener<ChartTypeModelData> chartTypeSelectionChangedListener(final ChartDesignerWindow w, final boolean calculateYMaxValue) {
		return new SelectionChangedListener<ChartTypeModelData>() {
			public void selectionChanged(SelectionChangedEvent<ChartTypeModelData> se) {
				chartTypeSelectionChangedAgent(w, calculateYMaxValue);
			}
		};
	}
	
	public static void chartTypeSelectionChangedAgent(ChartDesignerWindow w, boolean calculateYMaxValue) {
		boolean hasMoreThanOneDataset = hasMoreThanOneDataset(w);
		boolean settingsEnabled = true;
		List<TextField<String>> minValueTos = new ArrayList<TextField<String>>();
		TextField<String> minValueFrom = null;
		List<TextField<String>> maxValueTos = new ArrayList<TextField<String>>();
		TextField<String> maxValueFrom = null;
		List<TextField<String>> stepTos = new ArrayList<TextField<String>>();
		TextField<String> stepFrom = null;
		List<TextField<String>> axisLabelTos = new ArrayList<TextField<String>>();
		TextField<String> axisLabelFrom = null;
		List<CheckBox> axisShowHZGridTos = new ArrayList<CheckBox>();
		CheckBox axisShowHZGridFrom = null;
		List<ListBox> axisPositionTos = new ArrayList<ListBox>();
		ListBox axisPositionFrom = null;
		List<CheckBox> showLegendTos = new ArrayList<CheckBox>();
		CheckBox showLegendFrom = null;
		
		
		List<CheckBox> showBorderTos = new ArrayList<CheckBox>();
		CheckBox showBorderFrom = null;
		
		List<CheckBox> xGridTos = new ArrayList<CheckBox>();
		CheckBox xGridFrom = null;
		List<ListBox> valuesFontFamilyTos = new ArrayList<ListBox>();
		ListBox valuesFontFamilyFrom = null;
		List<ListBox> valuesFontSizeTos = new ArrayList<ListBox>();
		ListBox valuesFontSizeFrom = null;
		for (int i = 0 ; i < w.getChartDesignerPanel().getDatasourcePanel().getFieldSets().size() ; i++) {
			ChartDesignerDatasourceFieldSet fs = w.getChartDesignerPanel().getDatasourcePanel().getFieldSets().get(i);
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue()) {
				Long id = Long.valueOf((String)cb.getData("DATASET_ID"));
				LookAndFeelTabItem ti = w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().get(id);
				if (hasMoreThanOneDataset) {
					enableSettings(ti, settingsEnabled);
					if (settingsEnabled) {
						settingsEnabled = false;
						minValueFrom = ti.getAxisFromValue();
						maxValueFrom = ti.getAxisToValue();
						stepFrom = ti.getAxisNumberOfIntervals();
						axisLabelFrom = ti.getAxisTitle();
						axisShowHZGridFrom = ti.getHasYGrid();
						axisPositionFrom = ti.getAxisPosition();
						showLegendFrom = ti.getShowLegend();
						showBorderFrom = ti.getShowBorder();
						
						xGridFrom = ti.getHasXGrid();
						valuesFontFamilyFrom = ti.getValuesFontFamily();
						valuesFontSizeFrom = ti.getValuesFontSize();
						if (calculateYMaxValue) {
							ChartDesignerController.calculateYMaxValue(ti.getAxisToValue(), w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetIDs());
						}
					} else {
						minValueTos.add(ti.getAxisFromValue());
						ti.getAxisToValue().setValue(minValueFrom.getValue());
						maxValueTos.add(ti.getAxisToValue());
						stepTos.add(ti.getAxisNumberOfIntervals());
						axisLabelTos.add(ti.getAxisTitle());
						axisShowHZGridTos.add(ti.getHasYGrid());
						axisPositionTos.add(ti.getAxisPosition());
						showLegendTos.add(ti.getShowLegend());
					
						showBorderTos.add(ti.getShowBorder());
						xGridTos.add(ti.getHasXGrid());
						valuesFontFamilyTos.add(ti.getValuesFontFamily());
						valuesFontSizeTos.add(ti.getValuesFontSize());
					}
				} else {
					enableSettings(ti, true);
				}
			}
		}
		if (hasMoreThanOneDataset) {
			link(minValueFrom, minValueTos);
			link(maxValueFrom, maxValueTos);
			link(stepFrom, stepTos);
			link(axisLabelFrom, axisLabelTos);
			link(axisShowHZGridFrom, axisShowHZGridTos);
			link(axisPositionFrom, axisPositionTos);
			link(showLegendFrom, showLegendTos);
			
			
			link(showBorderFrom, showBorderTos);
			
			link(xGridFrom, xGridTos);
			link(valuesFontFamilyFrom, valuesFontFamilyTos);
			link(valuesFontSizeFrom, valuesFontSizeTos);
		}
	}
	
	public static void enableSettings(LookAndFeelTabItem ti, boolean enabled) {
		if (ti != null) {
//			ti.getHorizontalGridButton().setEnabled(enabled);
//			ti.getAxisTitleButton().setEnabled(enabled);
//			ti.getAxisValuesButton().setEnabled(enabled);
//			ti.getValuesFontFamily().setEnabled(enabled);
//			ti.getValuesFontSize().setEnabled(enabled);
//			ti.getAxisNumberOfIntervals().setEnabled(enabled);
//			ti.getAxisFromValue().setEnabled(enabled);
//			ti.getAxisToValue().setEnabled(enabled);
			ti.getLabelInclination().setEnabled(enabled);
//			ti.getAxisButton().setEnabled(enabled);
		}
	}
	
//	public static boolean hasBarAndLine(ChartDesignerWindow w) {
//		boolean hasBar = false;
//		boolean hasLine = false;
//		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
//			String chartType = fs.getChartTypeList().getSelection().get(0).getChartTypeCode();
//			if (chartType.equalsIgnoreCase("BAR"))
//				hasBar = true;
//			else if (chartType.equalsIgnoreCase("LINE"))
//				hasLine = true;
//			if (hasBar && hasLine)
//				return true;
//		}
//		return false;
//	}
	
	public static boolean hasMoreThanOneDataset(ChartDesignerWindow w) {
		int count = 0;
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
			if (fs.getUseThisDataset().getValue())
				count++;
		}
		if (count > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Listener<BaseEvent> enableValues(final ChartAxisPanel p) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				p.getValuesFieldSet().setEnabled(!(p.getUseFromDateToDate().getValue() || p.getUseMostRecentData().getValue()));
				if (!p.getValuesFieldSet().isEnabled())
					for (int i = 0 ; i < p.getValuesList().getItemCount() ; i++)
						p.getValuesList().setItemSelected(i, true);
				p.getMostRecentDataFieldSet().setEnabled(!p.getUseFromDateToDate().getValue());
				p.getFromDateToDateFieldSet().setEnabled(!p.getUseMostRecentData().getValue());
			}
		};
	}
	
	public static SelectionListener<MenuEvent> cloneChart(final ChartDesignerWindow W1) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				cloneChartAgent(W1);
			}
		};
	}
	
	public static ChartDesignerWindow cloneChartAgent(final ChartDesignerWindow W1) {
		try {
			ChartDesignerValidator.isValid(W1);
			ChartDesignerWindow W2 = new ChartDesignerWindow();
			W2.build();
			W2.getWindow().setPosition(W1.getWindow().getAbsoluteLeft() + 100, W1.getWindow().getAbsoluteTop());
			W1.getWindow().setPosition(W1.getWindow().getAbsoluteLeft() - 100, W1.getWindow().getAbsoluteTop());
			List<ChartDesignerParametersVO> P1s = ChartDesignerParametersCollector.collectParameters(W1);
			ChartDesignerOpener.loadParameters(W2, P1s, false);
			return W2;
		} catch (FenixGWTException e) {
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
		}
		return null;
	}
	
	public static void loadChart(final Map<Long, String> chartDesignTitleIDs, final String caller, final String tinyMCEPanelID, final String originalWidth, final String originalHeigth) {
		for (final Long chartDesignID : chartDesignTitleIDs.keySet()) {
			final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Chart and Parameters", BabelFish.print().pleaseWait());
			try {
				ChartDesignerServiceEntry.getInstance().load(chartDesignID, new AsyncCallback<List<ChartDesignerParametersVO>>() {
					public void onSuccess(List<ChartDesignerParametersVO> vos) {
						l.destroyLoadingBox();
						ChartDesignerWindow w = new ChartDesignerWindow(caller, tinyMCEPanelID);
						w.build();
						w.setChartDesignID(chartDesignID);
						w.getWindow().setHeading("Chart Designer - " + chartDesignTitleIDs.get(chartDesignID));
						w.setOriginalHeight(originalHeigth);
						w.setOriginalWidth(originalWidth);
						ChartDesignerOpener.loadParameters(w, vos, false);
						l.destroyLoadingBox();
					}
					public void onFailure(Throwable E2) {
						l.destroyLoadingBox();
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E2.getMessage());
						l.destroyLoadingBox();
					}
				});
			} catch (FenixGWTException E1) {
				l.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
				l.destroyLoadingBox();
			}
		}
	}
	
	public static SelectionListener<ButtonEvent> goToXAxis(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getxAxisPanel().getLayoutContainer());
				restoreButtonsColor(w.getChartDesignerPanel());
				w.getChartDesignerPanel().getxAxisButton().setText("<b><font color='#CA1616'><u>" + w.getChartDesignerPanel().getxAxisButton().getText() + "</u><font></b>");
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> goToYAxis(final ChartDesignerWindow w, final Long datasetID) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getAxisTabPanel().getLayoutContainer());
				if (datasetID != null) {
					w.getChartDesignerPanel().getAxisTabPanel().getTabPanel().setSelection(w.getChartDesignerPanel().getAxisTabPanel().getTabItemsMap().get(datasetID));
				}
				restoreButtonsColor(w.getChartDesignerPanel());
				w.getChartDesignerPanel().getyAxisButton().setText("<b><font color='#CA1616'><u>" + w.getChartDesignerPanel().getyAxisButton().getText() + "</u><font></b>");
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addDatePanel(final LookAndFeelTabItem ti) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ti.buildAndAddDatePanel(true);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addValuePanel(final LookAndFeelTabItem ti) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ti.buildAndAddValuePanel(true);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> saveFromMenu(final ChartDesignerWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				saveAgent(w);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> saveFromButtonsPad(final ChartDesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				saveAgent(w);
			}
		};
	}
	
	public static void saveAgent(final ChartDesignerWindow w) {
		boolean isEditable = (FenixUser.hasUserRole() || FenixUser.hasAdminRole()); 
		if (isEditable) {
			try {
				if (w.getChartDesignID() == null) {
					ChartDesignerValidator.isValid(w);
					MetadataEditorWindow mw = new MetadataEditorWindow();
					List<ChartDesignerParametersVO> vos = ChartDesignerParametersCollector.collectParameters(w);
					mw.build(false, isEditable, false, MESaver.getSaveChartDesignListener(mw, vos, w));
				} else {
					updateChartDesign(w);
				}
			} catch (FenixGWTException e) {
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
			}
		} else {
			FenixAlert.info(BabelFish.print().info(), "To Save a Resource, Please Sign In");
		}
	}
	
	public static void updateChartDesign(ChartDesignerWindow w) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Update Chart.", BabelFish.print().pleaseWait());
		List<ChartDesignerParametersVO> vos = ChartDesignerParametersCollector.collectParameters(w);
		try {
			ChartDesignerServiceEntry.getInstance().update(w.getChartDesignID(), vos, new AsyncCallback<Long>() {
				public void onSuccess(Long id) {
					l.destroyLoadingBox();
					FenixAlert.info(BabelFish.print().info(), "Chart Successfully Updated.");
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E2) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E2.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static SelectionListener<MenuEvent> saveAs(final ChartDesignerWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				boolean isEditable = (FenixUser.hasUserRole() || FenixUser.hasAdminRole()); 
				if (isEditable) {
					try {
						ChartDesignerValidator.isValid(w);
						MESaveAs.prepopulateChartDesign(w.getChartDesignID(), isEditable, false, w);
					} catch (FenixGWTException e) {
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + e.getMessage());
					}
				} else {
					FenixAlert.info(BabelFish.print().info(), "To Save a Resource, Please Sign In");
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> exportAsImage(final ChartDesignerWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				if ((w.getChartDesignerPanel().getChartPanel().getImagePath() != null) && !w.getChartDesignerPanel().getChartPanel().getImagePath().equalsIgnoreCase(""))
					Window.open("../exportObject/" + w.getChartDesignerPanel().getChartPanel().getImagePath(), "_blank", "status=no");
			}
		};
	}
	
	public static SelectionListener<MenuEvent> exportAsHTML(final ChartDesignerWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				if (w.getChartDesignID() != null) {
					try {
						ChartDesignerServiceEntry.getInstance().exportToWebsite(w.getChartDesignID(), new AsyncCallback<Map<String,String>>() {
							public void onSuccess(Map<String, String> tagMap) {
								new ChartDesignerExportToHTMLWindow().build(tagMap);
							}
							public void onFailure(Throwable E2) {
								FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E2.getMessage());
							}
						});
					} catch (FenixGWTException E1) {
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
					}
				} else {
					FenixAlert.info(BabelFish.print().info(), "Please Save This Chart First.");
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> exportAsExcel(final ChartDesignerWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Creating Excel file.", BabelFish.print().pleaseWait());
				try {
					ChartDesignerValidator.isValid(w);
					List<ChartDesignerParametersVO> vos = ChartDesignerParametersCollector.collectParameters(w);
					ChartDesignerServiceEntry.getInstance().exportAsExcel(vos, new AsyncCallback<String>() {
						public void onSuccess(String filename) {
							l.destroyLoadingBox();
							Window.open("../exportObject/" + filename, "_blank", "status=no");
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable E2) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E2.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException E1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> exportAsCSV(final ChartDesignerWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Creating Excel file.", BabelFish.print().pleaseWait());
				try {
					ChartDesignerValidator.isValid(w);
					List<ChartDesignerParametersVO> vos = ChartDesignerParametersCollector.collectParameters(w);
					ChartDesignerServiceEntry.getInstance().exportAsCSV(vos, new AsyncCallback<List<String>>() {
						public void onSuccess(List<String> filenames) {
							l.destroyLoadingBox();
							for (String filename : filenames)
								Window.open("../exportObject/" + filename, "_blank", "status=no");
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable E2) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E2.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException E1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq() + E1.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}
	
	public static Listener<BaseEvent> axisValueFromListener(final TextField<String> axisValueFrom, final TextField<String> labelsDistanceFromXAxis) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				labelsDistanceFromXAxis.setValue(axisValueFrom.getValue());
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> defaultImageSize(final LookAndFeelTabItem ti, final String width, final String height) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ti.getImageWidth().setValue(width);
				ti.getImageHeight().setValue(height);
			}
		};
	}
	
	public static Listener<BaseEvent> chartTitleListener(final ChartDesignerWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				w.getWindow().setHeading("ChartDesigner - " + w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getTitle().getValue());
			}
		};
	}
	
	public static Listener<BaseEvent> equidistantDates(final LookAndFeelTabItem ti, final CheckBox cb) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				String TITLE_BLUE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
				String TITLE_RED  = "<div style='font-family: sans-serif; color: #CA1616; font-size: 8pt; font-weight: bold; '>";
				if (cb.getValue()) {
					ti.getxAxisNumberOfLabels().setHtml(TITLE_BLUE + "Maximum Number of Labels</div");
				} else {
					ti.getxAxisNumberOfLabels().setHtml(TITLE_RED + "Labels Frequency</div");
				}
			}
		};
	}
	
	public static Listener<BaseEvent> resize(final ChartDesignerWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				String width  = String.valueOf(w.getWindow().getWidth() - 20) + "px";
				String height = String.valueOf(w.getWindow().getHeight() - 100 - 60 - 5) + "px";
				w.getChartDesignerPanel().getChartPanel().getWrapper().setSize(width, height);
				w.getChartDesignerPanel().getChartPanel().getWrapper().getLayout().layout();
			}
		};
	}
	
	public static WindowListener close(final ChartDesignerWindow w) {
		return new WindowListener() {
			public void windowHide(WindowEvent we) {
				super.windowHide(we);
				if (w.getCaller().equals(WhoCall.TINYMCE_EDIT)) {
					String content = TinyMCENativeController.getContent(w.getTinyMCEPanelID());
					TinyMCEController.reLoadChart(w.getTinyMCEPanelID(), w.getChartDesignID(), content, w.getOriginalWidth(), w.getOriginalHeight());
				}
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> onClose(final ChartDesignerWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ChartDesignerReminder r = new ChartDesignerReminder(w);
				r.build();
			};
		};
	}
	
	public static Listener<BaseEvent> beforeHide(final ChartDesignerWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (w.getCaller().equals(WhoCall.TINYMCE_EDIT)) {
					ChartDesignerReminder r = new ChartDesignerReminder(w);
					r.build();
				}
			}
		};
	}
	
}