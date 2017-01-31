package org.fao.fenix.web.modules.olap.client.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerExportToHTMLWindow;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerServiceEntry;
import org.fao.fenix.web.modules.colorpicker.client.ColorPickerWindow;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.client.view.MT2ChartDesignerWindow;
import org.fao.fenix.web.modules.olap.client.view.MTDatasourceFieldSet;
import org.fao.fenix.web.modules.olap.client.view.MTDimensionPanel;
import org.fao.fenix.web.modules.olap.client.view.MTPanel;
import org.fao.fenix.web.modules.olap.client.view.MTParametersCollector;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
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
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import org.fao.fenix.web.modules.olap.client.view.MTFunctionPanel;

public class MTController extends OlapController {

	public static SelectionListener<ButtonEvent> addToReport(final MTWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Creating Multidimensional Table.", BabelFish.print().pleaseWait());
				try {
					isValid(w);
					final OLAPParametersVo parameters = MTParametersCollector.retrieveParameters(w);
					OlapServiceEntry.getInstance().addToReport(parameters, new AsyncCallback<String>() {
						public void onSuccess(String iframe) {
							l.destroyLoadingBox();
							System.out.println("yes this\n" + iframe);
							TinyMCENativeController.setContent(w.getTinyMCEPanelID(), iframe);
							w.getWindow().close();
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable E2) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException E1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					l.destroyLoadingBox();
				}
			}
		};
	}
	
	public static Listener<BaseEvent> resize(final MTWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				String width  = String.valueOf(w.getWindow().getWidth() - 20) + "px";
				String height = String.valueOf(w.getWindow().getHeight() - 100 - 60 - 5) + "px";
				w.getMtPanel().getMtTablePanel().getWrapper().setSize(width, height);
				w.getMtPanel().getMtTablePanel().getWrapper().getLayout().layout();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> panelSelector(final MTPanel p, final LayoutContainer l) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				p.getLayout().setActiveItem(l);
				restoreButtonsColor(p);
				ce.getButton().setText("<b><font color='#CA1616'><u>" + ce.getButton().getText() + "</u><font></b>");
			}
		};
	}
	
	public static void restoreButtonsColor(MTPanel p) {
		p.getDatasourceButton().setText("Datasource");
		p.getFunctionButton().setText("Function");
		p.getLookAndFeelButton().setText("Look&Feel");
		p.getFiltersButton().setText("Filters");
		p.getColumnsButton().setText("Columns");
		p.getSubRowsButton().setText("Sub-Rows");
		p.getRowsButton().setText("Rows");
		p.getSubColumnsButton().setText("Sub-Columns");
		p.getTableButton().setText("View Table");
	}
	
	public static Listener<BaseEvent> enableValues(final MTWindow w, final MTDimensionPanel p) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				enableValuesAgent(w, p);
			}
		};
	}
	
	public static ChangeHandler enableValuesChangeHandler(final MTWindow w, final MTDimensionPanel p) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				enableValuesAgent(w, p);
			}
		};
	}
	
	public static void enableValuesAgent(MTWindow w, MTDimensionPanel p) {
		p.getValuesFieldSet().setEnabled(!(p.getUseFromDateToDate().getValue() || p.getUseMostRecentData().getValue()));
		if (!p.getValuesFieldSet().isEnabled())
			for (int i = 0 ; i < p.getValuesList().getItemCount() ; i++)
				p.getValuesList().setItemSelected(i, true);
		boolean umrd = p.getUseMostRecentData().getValue();
//		boolean aggr_m = p.getAggregateMonthly().getValue(); 
//		boolean aggr_y = p.getAggregateYearly().getValue();
		boolean fdtd = p.getUseFromDateToDate().getValue();
//		if (!umrd && !aggr_m && !aggr_y && !fdtd) {
		if (!umrd && !fdtd) {
			p.getMostRecentDataFieldSet().setEnabled(true);
			p.getFromDateToDateFieldSet().setEnabled(true);
//			p.getAggregateDateFieldSet().setEnabled(true);
		} else {
			p.getMostRecentDataFieldSet().setEnabled(!fdtd);
			p.getFromDateToDateFieldSet().setEnabled(!umrd);
//			p.getAggregateDateFieldSet().setEnabled(!(umrd || fdtd));
		}
		rowsHeightsAgent(w);
		columnWidthsAgent(w);
		if (p.getAggregateMonthly().getValue()) {
			w.getMtPanel().getMtLookAndFeelPanel().getDatesFormat().setItemSelected(2, true);
		} 
		if (p.getAggregateYearly().getValue()) {
			w.getMtPanel().getMtLookAndFeelPanel().getDatesFormat().setItemSelected(3, true);
		} 
//		if (!p.getAggregateMonthly().getValue() && !p.getAggregateYearly().getValue()) {
//			w.getMtPanel().getMtLookAndFeelPanel().getDatesFormat().setItemSelected(0, true);
//			System.out.println("::: MTCotroller @ 168 ::: " + w.getMtPanel().getMtLookAndFeelPanel().getDatesFormat().getSelectedIndex());
//		} 
	}
	
	public static void fillLatestDataList(ListBox l) {
		for (int i = 0 ; i < 31 ; i++) 
			l.addItem(String.valueOf(i), String.valueOf(i));
	}
	
	public static Listener<BaseEvent> useThisDatasetHandler(final CheckBox cb, final MTWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				Long datasetID = Long.valueOf((String)cb.getData("DATASET_ID"));
//				w.getChartDesignerPanel().getAxisTabPanel().getTabItemsMap().get(datasetID).setEnabled(cb.getValue());
//				w.getChartDesignerPanel().getPlotDimensionPanel().getFieldSetMap().get(datasetID).setEnabled(cb.getValue());
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addDataset(final MTWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getMtPanel().getLayout().setActiveItem(w.getMtPanel().getMtDatasourcePanel().getLayoutContainer());
				MTDatasourceFieldSet fs = new MTDatasourceFieldSet();
				new ResourceExplorerDataset(w, fs);
			}
		};
	}
	
	public static void loadDatasetAgent(final MTWindow w, final MTDatasourceFieldSet fs, final OLAPParametersVo p) {
		final List<ResourceChildModel> models = w.getMtPanel().getMtDatasourcePanel().getModels();
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Retrieving datasets, dimensions and values.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().loadDataset(models, CheckLanguage.getLanguage(), new AsyncCallback<ResourceViewVO>() {
				public void onSuccess(ResourceViewVO rvvo) {
					l.destroyLoadingBox();
					addDatasetPanels(w, rvvo.getDatasets(), fs);
					updateMTDimensions(w, fs, p);
					for (DatasetVO dvo : rvvo.getDatasets()) {
						w.getMtPanel().getMtFiltersPanel().buildWrapper(dvo);
						w.getMtPanel().getMtLookAndFeelPanel().getNotesLabel().setValue("Source: " + dvo.getSourceName());
					}
					w.getMtPanel().getMtDatasourcePanel().getAddDatasetButton().setEnabled(false);
					w.getMtPanel().getLayout().setActiveItem(w.getMtPanel().getMtRowsPanel().getLayoutContainer());
					restoreButtonsColor(w.getMtPanel());
					w.getMtPanel().getRowsButton().setText("<b><font color='#CA1616'><u>Rows</u><font></b>");
					loadUserSettings(w, p);
					l.destroyLoadingBox();
				};
				public void onFailure(Throwable e1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					e1.printStackTrace();
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			l.destroyLoadingBox();
		}
	}
	
	public static void loadUserSettings(MTWindow w, OLAPParametersVo p) {
		if (p != null) {
			loadGeneralSettings(w, p);
			loadXDimension(w, p);
			loadZDimension(w, p);
			if (p.getW() != null && p.getW().length() > 0)
				loadWDimension(w, p);
			if (p.getY() != null && p.getY().length() > 0)
				loadYDimension(w, p);
			loadFilters(w, p);
			viewTableAgent(w, true);
		}
	}
	
	private static void loadFilters(MTWindow w, OLAPParametersVo p) {
		if (!p.getFilters().isEmpty()) {
			for (VerticalPanel panel : w.getMtPanel().getMtFiltersPanel().getPanels()) {
				String header = (String)panel.getData("HEADER");
				String contentDescriptor = (String)panel.getData("CONTENT_DESCRIPTOR");
				for (OLAPFilterVo f : p.getFilters()) {
					if (f.getDimension().equalsIgnoreCase(contentDescriptor) && f.getDimensionLabel().equalsIgnoreCase(header)) {
						ListBox l = (ListBox)panel.getData("LIST");
						for (String code : f.getDimensionMap().keySet()) {
							for (int i = 0 ; i < l.getItemCount() ; i++)
								if (l.getValue(i).equalsIgnoreCase(code))
									l.setItemSelected(i, true);
						}
					}
				}
			}
		}
	}
	
	private static void loadGeneralSettings(MTWindow w, OLAPParametersVo p) {
		
		w.getMtPanel().getMtLookAndFeelPanel().getColumnBackgroundColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getColHeaderColor() + "; color: " + p.getColHeaderColor() + "; font-weight: bold; font-style: italic;'>" + p.getColHeaderColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getColumnFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getColFontColor() + "; color: " + p.getColFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getColFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getContentsBackgroundColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getContentBackgroundColor() + "; color: " + p.getContentBackgroundColor() + "; font-weight: bold; font-style: italic;'>" + p.getContentBackgroundColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getContentsFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getContentFontColor() + "; color: " + p.getContentFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getContentFontColor() + "</div>");
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getDatesFormat(), p.getDatesFormat());
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getDecimals(), String.valueOf(p.getDecimals()));
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getDecimals(), String.valueOf(p.getDecimals()));
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getDecimalsForTotals(), String.valueOf(p.getDecimalsForTotals()));
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getDecimalsForVariation(), String.valueOf(p.getDecimalsForVariation()));
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getFontFamily(), p.getFontFamily());
		setListBoxValue(w.getMtPanel().getMtLookAndFeelPanel().getFontSize(), p.getFontSize());
		w.getMtPanel().getMtLookAndFeelPanel().getFunctionWidth().setValue(p.getFunctionWidth());
		w.getMtPanel().getMtLookAndFeelPanel().getRowBackgroundColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getRowHeaderColor() + "; color: " + p.getRowHeaderColor() + "; font-weight: bold; font-style: italic;'>" + p.getRowHeaderColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getRowFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getRowFontColor() + "; color: " + p.getRowFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getRowFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getRowLabelWidth().setValue(p.getColumnLabelWidth().substring(0, p.getColumnLabelWidth().indexOf("px")));
		w.getMtPanel().getMtLookAndFeelPanel().getShowColumnVariation().setValue(p.isShowColVariation());
		
		//mackx
		
		w.getMtPanel().getMtFunctionPanel().getAggregationFunctionListBox().setSelectedIndex(MTFunctionPanel.getFunctionCoreIndex(p.getFunction())  );
		
		try {
			w.getMtPanel().getMtFunctionPanel().getColFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex(p.getColFunction()));
			} catch (Exception excp) {
			w.getMtPanel().getMtFunctionPanel().getColFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex("AVG"));
			}
			try {
			w.getMtPanel().getMtFunctionPanel().getRowFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex(p.getRowFunction()));
			} catch (Exception excp) {
			w.getMtPanel().getMtFunctionPanel().getRowFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex("AVG"));
			}
			try {
			w.getMtPanel().getMtFunctionPanel().getSubColFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex(p.getSubColFunction()));
			} catch (Exception excp) {
			w.getMtPanel().getMtFunctionPanel().getSubColFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex("AVG"));
			}
			try {
			w.getMtPanel().getMtFunctionPanel().getSubRowFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex(p.getSubRowFunction()));
			} catch (Exception excp) {
			w.getMtPanel().getMtFunctionPanel().getSubRowFunctionListBox().setSelectedIndex( MTFunctionPanel.getFunctionCoreIndex("AVG"));
			}
			
		w.getMtPanel().getMtLookAndFeelPanel().getShading1Color().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getShading1Color() + "; color: " + p.getShading1Color() + "; font-weight: bold; font-style: italic;'>" + p.getShading1Color() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getShading2Color().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getShading2Color() + "; color: " + p.getShading2Color() + "; font-weight: bold; font-style: italic;'>" + p.getShading2Color() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getShowBorder().setValue(p.getShowBorder());
		w.getMtPanel().getMtLookAndFeelPanel().getShowColSubject().setValue(p.getShowColSubject());
		w.getMtPanel().getMtLookAndFeelPanel().getShowColumnTotals().setValue(p.getShowColSummary());
		w.getMtPanel().getMtLookAndFeelPanel().getShowGridLine().setValue(p.getShowGridLine());
		w.getMtPanel().getMtLookAndFeelPanel().getShowRowSubject().setValue(p.getShowRowSubject());
		w.getMtPanel().getMtLookAndFeelPanel().getShowRowTotals().setValue(p.getShowRowSummary());
		w.getMtPanel().getMtLookAndFeelPanel().getShowShading().setValue(p.getShowShading());
		w.getMtPanel().getMtLookAndFeelPanel().getShowSubColSubject().setValue(p.getShowSubColSubject());
		w.getMtPanel().getMtLookAndFeelPanel().getShowSubColSummary().setValue(p.getShowSubColSummary());
		w.getMtPanel().getMtLookAndFeelPanel().getShowSubRowSubject().setValue(p.getShowSubRowSubject());
		w.getMtPanel().getMtLookAndFeelPanel().getShowSubRowSummary().setValue(p.getShowSubRowSummary());
		
		w.getMtPanel().getMtLookAndFeelPanel().getwLabelShowTitle().setValue(p.getwLabelShowTitle());
		w.getMtPanel().getMtLookAndFeelPanel().getxLabelShowTitle().setValue(p.getxLabelShowTitle());
		w.getMtPanel().getMtLookAndFeelPanel().getyLabelShowTitle().setValue(p.getyLabelShowTitle());
		w.getMtPanel().getMtLookAndFeelPanel().getzLabelShowTitle().setValue(p.getzLabelShowTitle());
		
		w.getMtPanel().getMtLookAndFeelPanel().setwLabel(p.getwLabel());
		w.getMtPanel().getMtLookAndFeelPanel().setxLabel(p.getxLabel());
		w.getMtPanel().getMtLookAndFeelPanel().setyLabel(p.getyLabel());
		w.getMtPanel().getMtLookAndFeelPanel().setzLabel(p.getzLabel());
		
		//mackx off
		
		w.getMtPanel().getMtLookAndFeelPanel().getShowRowVariation().setValue(p.isShowRowVariation());
		w.getMtPanel().getMtLookAndFeelPanel().getSubColumnBackgroundColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSubColHeaderColor() + "; color: " + p.getSubColHeaderColor() + "; font-weight: bold; font-style: italic;'>" + p.getSubColHeaderColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getSubColumnFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSubColFontColor() + "; color: " + p.getSubColFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getSubColFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getSubRowBackgroundColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSubRowHeaderColor() + "; color: " + p.getSubRowHeaderColor() + "; font-weight: bold; font-style: italic;'>" + p.getSubRowHeaderColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getSubRowFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSubRowFontColor() + "; color: " + p.getSubRowFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getSubRowFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getSubRowLabelWidth().setValue(p.getSubRowLabelWidth());
		w.getMtPanel().getMtLookAndFeelPanel().getSummaryBackgroundColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSummaryBackgroundColor() + "; color: " + p.getSummaryBackgroundColor() + "; font-weight: bold; font-style: italic;'>" + p.getSummaryBackgroundColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getSummaryFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSummaryFontColor() + "; color: " + p.getSummaryFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getSummaryFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getTitleColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getOlapTitleFontColor() + "; color: " + p.getOlapTitleFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getOlapTitleFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getTitleLabel().setValue(p.getOlapTitle());
		w.getMtPanel().getMtLookAndFeelPanel().getNotesColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getNotesFontColor() + "; color: " + p.getNotesFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getNotesFontColor() + "</div>");
		w.getMtPanel().getMtLookAndFeelPanel().getNotesLabel().setValue(p.getNotesTitle());
		w.getMtPanel().getMtLookAndFeelPanel().addContentHeights(p.getRowLabelsHeights());
		w.getMtPanel().getMtLookAndFeelPanel().addContentWidths(p.getColumnLabelsWidths());
		w.getMtPanel().getMtLookAndFeelPanel().getVariationThreshold().setValue(p.getVariationThreshold());
		w.getMtPanel().getMtLookAndFeelPanel().getVariationThresholdRed().setValue(p.getVariationThresholdRed());
		w.getMtPanel().getMtLookAndFeelPanel().getVariationThresholdYellow().setValue(p.getVariationThresholdYellow());
		w.getMtPanel().getMtLookAndFeelPanel().getShowVariationArrows().setValue(p.getShowVariationArrows());
	}
	
	private static void setListBoxValue(ListBox l, String value) {
		for (int i = 0 ; i < l.getItemCount() ; i++) {
			if (l.getValue(i).equals(value)) {
				l.setItemSelected(i, true);
				break;
			}
		}
	}
	
	private static void loadXDimension(MTWindow w, OLAPParametersVo p) {
		w.getMtPanel().getMtColumnsPanel().getAggregateMonthly().setValue(p.isAggregateXAsMonthly());
		w.getMtPanel().getMtColumnsPanel().getAggregateYearly().setValue(p.isAggregateXAsYearly());
		w.getMtPanel().getMtColumnsPanel().getFromDate().setValue(p.getxFromDate());
		w.getMtPanel().getMtColumnsPanel().getToDate().setValue(p.getxToDate());
		w.getMtPanel().getMtColumnsPanel().getUseFromDateToDate().setValue(p.isxUseFromDateToDate());
		w.getMtPanel().getMtColumnsPanel().getUseMostRecentData().setValue(p.isxUseMostRecentData());
		ListBox years = w.getMtPanel().getMtColumnsPanel().getLatestYearsList();
		ListBox months = w.getMtPanel().getMtColumnsPanel().getLatestMonthsList();
		ListBox days = w.getMtPanel().getMtColumnsPanel().getLatestDaysList();
		ListBox values = w.getMtPanel().getMtColumnsPanel().getValuesList();
		ListBox dimensions = w.getMtPanel().getMtColumnsPanel().getDimensionList();
		for (int i = 0 ; i < dimensions.getItemCount() ; i++)
			if (dimensions.getValue(i).equals(p.getX()))
					dimensions.setItemSelected(i, true);
		for (String label : p.getXLabels().keySet()) {
//			System.out.println("@270 " + p.getXLabels().get(label) + "? " + (!containsValue(p.getXLabels().get(label), values)) + " Add: " + p.getXLabels().get(label) + ", " + label);
			if (!containsValue(p.getXLabels().get(label), values)) {
//				values.addItem(label, p.getXLabels().get(label));
				values.addItem(p.getXLabels().get(label), label);
				values.setItemSelected(values.getItemCount() - 1, true);
			}
		}
		
//		for (int i = 0 ; i < values.getItemCount() ; i++)
//			System.out.println("\t\t@278 " + values.getItemText(i) + " = " + values.getValue(i));
		
		for (int i = 0 ; i < years.getItemCount() ; i++)
			if (years.getValue(i).equals(String.valueOf(p.getxLatestYears())))
				years.setItemSelected(i, true);
		for (int i = 0 ; i < days.getItemCount() ; i++)
			if (days.getValue(i).equals(String.valueOf(p.getxLatestDays())))
				days.setItemSelected(i, true);
		for (int i = 0 ; i < months.getItemCount() ; i++)
			if (months.getValue(i).equals(String.valueOf(p.getxLatestMonths())))
				months.setItemSelected(i, true);
	}
	
	private static boolean containsValue(String value, ListBox l) {
		for (int i = 0 ; i < l.getItemCount() ; i++) {
//			if (l.getValue(i).equalsIgnoreCase(value)) {
			if (l.getItemText(i).equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
	
	private static void loadZDimension(MTWindow w, OLAPParametersVo p) {
		w.getMtPanel().getMtRowsPanel().getAggregateMonthly().setValue(p.isAggregateZAsMonthly());
		w.getMtPanel().getMtRowsPanel().getAggregateYearly().setValue(p.isAggregateZAsYearly());
		w.getMtPanel().getMtRowsPanel().getFromDate().setValue(p.getzFromDate());
		w.getMtPanel().getMtRowsPanel().getToDate().setValue(p.getzToDate());
		w.getMtPanel().getMtRowsPanel().getUseFromDateToDate().setValue(p.iszUseFromDateToDate());
		w.getMtPanel().getMtRowsPanel().getUseMostRecentData().setValue(p.iszUseMostRecentData());
		ListBox years = w.getMtPanel().getMtRowsPanel().getLatestYearsList();
		ListBox months = w.getMtPanel().getMtRowsPanel().getLatestMonthsList();
		ListBox days = w.getMtPanel().getMtRowsPanel().getLatestDaysList();
		ListBox values = w.getMtPanel().getMtRowsPanel().getValuesList();
		ListBox dimensions = w.getMtPanel().getMtRowsPanel().getDimensionList();
		for (int i = 0 ; i < dimensions.getItemCount() ; i++)
			if (dimensions.getValue(i).equals(p.getZ()))
					dimensions.setItemSelected(i, true);
		for (String label : p.getZLabels().keySet()) {
			if (!containsValue(p.getZLabels().get(label), values)) {
//				values.addItem(label, p.getZLabels().get(label));
				values.addItem(p.getZLabels().get(label), label);
				values.setItemSelected(values.getItemCount() - 1, true);
			}
		}
		for (int i = 0 ; i < years.getItemCount() ; i++)
			if (years.getValue(i).equals(String.valueOf(p.getzLatestYears())))
				years.setItemSelected(i, true);
		for (int i = 0 ; i < days.getItemCount() ; i++)
			if (days.getValue(i).equals(String.valueOf(p.getzLatestDays())))
				days.setItemSelected(i, true);
		for (int i = 0 ; i < months.getItemCount() ; i++)
			if (months.getValue(i).equals(String.valueOf(p.getzLatestMonths())))
				months.setItemSelected(i, true);
	}
	
	private static void loadWDimension(MTWindow w, OLAPParametersVo p) {
		w.getMtPanel().getMtSubRowsPanel().getAggregateMonthly().setValue(p.isAggregateWAsMonthly());
		w.getMtPanel().getMtSubRowsPanel().getAggregateYearly().setValue(p.isAggregateWAsYearly());
		w.getMtPanel().getMtSubRowsPanel().getFromDate().setValue(p.getwFromDate());
		w.getMtPanel().getMtSubRowsPanel().getToDate().setValue(p.getwToDate());
		w.getMtPanel().getMtSubRowsPanel().getUseFromDateToDate().setValue(p.iswUseFromDateToDate());
		w.getMtPanel().getMtSubRowsPanel().getUseMostRecentData().setValue(p.iswUseMostRecentData());
		ListBox years = w.getMtPanel().getMtSubRowsPanel().getLatestYearsList();
		ListBox months = w.getMtPanel().getMtSubRowsPanel().getLatestMonthsList();
		ListBox days = w.getMtPanel().getMtSubRowsPanel().getLatestDaysList();
		ListBox values = w.getMtPanel().getMtSubRowsPanel().getValuesList();
		ListBox dimensions = w.getMtPanel().getMtSubRowsPanel().getDimensionList();
		for (int i = 0 ; i < dimensions.getItemCount() ; i++)
			if (dimensions.getValue(i).equals(p.getW()))
					dimensions.setItemSelected(i, true);
		for (String label : p.getWLabels().keySet()) {
			if (!containsValue(p.getWLabels().get(label), values)) {
//				values.addItem(label, p.getWLabels().get(label));
				values.addItem(p.getWLabels().get(label), label);
				values.setItemSelected(values.getItemCount() - 1, true);
			}
		}
		for (int i = 0 ; i < years.getItemCount() ; i++)
			if (years.getValue(i).equals(String.valueOf(p.getwLatestYears())))
				years.setItemSelected(i, true);
		for (int i = 0 ; i < days.getItemCount() ; i++)
			if (days.getValue(i).equals(String.valueOf(p.getwLatestDays())))
				days.setItemSelected(i, true);
		for (int i = 0 ; i < months.getItemCount() ; i++)
			if (months.getValue(i).equals(String.valueOf(p.getwLatestMonths())))
				months.setItemSelected(i, true);
	}
	
	private static void loadYDimension(MTWindow w, OLAPParametersVo p) {
		w.getMtPanel().getMtSubColumnsPanel().getAggregateMonthly().setValue(p.isAggregateYAsMonthly());
		w.getMtPanel().getMtSubColumnsPanel().getAggregateYearly().setValue(p.isAggregateYAsYearly());
		w.getMtPanel().getMtSubColumnsPanel().getFromDate().setValue(p.getyFromDate());
		w.getMtPanel().getMtSubColumnsPanel().getToDate().setValue(p.getyToDate());
		w.getMtPanel().getMtSubColumnsPanel().getUseFromDateToDate().setValue(p.isyUseFromDateToDate());
		w.getMtPanel().getMtSubColumnsPanel().getUseMostRecentData().setValue(p.isyUseMostRecentData());
		ListBox years = w.getMtPanel().getMtSubColumnsPanel().getLatestYearsList();
		ListBox months = w.getMtPanel().getMtSubColumnsPanel().getLatestMonthsList();
		ListBox days = w.getMtPanel().getMtSubColumnsPanel().getLatestDaysList();
		ListBox values = w.getMtPanel().getMtSubColumnsPanel().getValuesList();
		ListBox dimensions = w.getMtPanel().getMtSubColumnsPanel().getDimensionList();
		for (int i = 0 ; i < dimensions.getItemCount() ; i++)
			if (dimensions.getValue(i).equals(p.getY()))
					dimensions.setItemSelected(i, true);
		for (String label : p.getYLabels().keySet()) {
			if (!containsValue(p.getYLabels().get(label), values)) {
//				values.addItem(label, p.getYLabels().get(label));
				values.addItem(p.getYLabels().get(label), label);
				values.setItemSelected(values.getItemCount() - 1, true);
			}
		}
		for (int i = 0 ; i < years.getItemCount() ; i++)
			if (years.getValue(i).equals(String.valueOf(p.getyLatestYears())))
				years.setItemSelected(i, true);
		for (int i = 0 ; i < days.getItemCount() ; i++)
			if (days.getValue(i).equals(String.valueOf(p.getyLatestDays())))
				days.setItemSelected(i, true);
		for (int i = 0 ; i < months.getItemCount() ; i++)
			if (months.getValue(i).equals(String.valueOf(p.getyLatestMonths())))
				months.setItemSelected(i, true);
	}
	
	public static void updateMTDimensions(MTWindow win, MTDatasourceFieldSet fs, OLAPParametersVo p) {
		String datasetId = (String)fs.getUseThisDataset().getData("DATASET_ID");
		ListBox x = win.getMtPanel().getMtColumnsPanel().getDimensionList();
		ListBox y = win.getMtPanel().getMtSubColumnsPanel().getDimensionList();
		ListBox z = win.getMtPanel().getMtRowsPanel().getDimensionList();
		ListBox w = win.getMtPanel().getMtSubRowsPanel().getDimensionList();
		updateDimensions(datasetId, x, y, z, w, null, win, p);
		x.addChangeHandler(dimensionChangeHandler(Long.valueOf(datasetId), win.getMtPanel().getMtColumnsPanel(), win));
		y.addChangeHandler(dimensionChangeHandler(Long.valueOf(datasetId), win.getMtPanel().getMtSubColumnsPanel(), win));
		z.addChangeHandler(dimensionChangeHandler(Long.valueOf(datasetId), win.getMtPanel().getMtRowsPanel(), win));
		w.addChangeHandler(dimensionChangeHandler(Long.valueOf(datasetId), win.getMtPanel().getMtSubRowsPanel(), win));
	}
	
	public static ChangeHandler dimensionChangeHandler(final Long datasetID, final MTDimensionPanel p, final MTWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				dimensionChangeHandlerAgent(datasetID, p, w);
			}
		};
	}
	
	public static void dimensionChangeHandlerAgent(final Long datasetID, final MTDimensionPanel p, final MTWindow w) {
		String header=null;
		try {
			header = p.getDimensionList().getItemText(p.getDimensionList().getSelectedIndex());
		} catch (Exception e) {}
		
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Retrieving values.", BabelFish.print().pleaseWait());
		OlapServiceEntry.getInstance().getDimensionValues(datasetID, header, CheckLanguage.getLanguage(), new AsyncCallback<List<UniqueValueVO>>() {
			public void onSuccess(List<UniqueValueVO> vos) {
				l.destroyLoadingBox();
				if (containsDate(vos))
					Collections.sort(vos, new UniqueValueVODateComparator());
				else
					Collections.sort(vos, new UniqueValueVOLabelComparator());
				p.getValuesList().clear();
				for (UniqueValueVO vo : vos) {
//					System.out.println("@425 " + vo.getLabel());
					if (!containsLabel(p.getValuesList(), vo.getLabel())) {
						p.getValuesList().addItem(vo.getLabel(), vo.getCode());
					}
				}
				for (int i = 0 ; i < p.getValuesList().getItemCount() ; i++) {
					p.getValuesList().setItemSelected(i, true);
				}
				columnWidthsAgent(w);
				rowsHeightsAgent(w);
				
				boolean dateSelected=false;
				
				try {
					dateSelected = (p.getDimensionList().getValue(p.getDimensionList().getSelectedIndex()).contains("date")) || (p.getDimensionList().getValue(p.getDimensionList().getSelectedIndex()).contains("Date"));
				} catch (Exception e) {}
				
				if (dateSelected) {
					p.getFromDateToDateFieldSet().setEnabled(true);
					p.getMostRecentDataFieldSet().setEnabled(true);
					p.getAggregateDateFieldSet().setEnabled(true);
				} else {
					p.getFromDateToDateFieldSet().setEnabled(false);
					p.getMostRecentDataFieldSet().setEnabled(false);
					p.getAggregateDateFieldSet().setEnabled(false);
				}
				l.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				l.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				l.destroyLoadingBox();
			}
		});
	}
	
	public static boolean containsLabel(ListBox l, String lbl) {
		for (int i = 0 ; i < l.getItemCount() ; i++)
			if (l.getItemText(i).equals(lbl))
				return true;
		return false;
	}
	
	public static void addDatasetPanels(MTWindow w, List<DatasetVO> dvos, MTDatasourceFieldSet fs) {
		for (DatasetVO dvo : dvos) {
			if (!w.getMtPanel().getMtDatasourcePanel().getAddedDatasets().contains(Long.valueOf(dvo.getDsId()))) {
				w.getMtPanel().getMtDatasourcePanel().getFieldSets().add(fs);
				w.getMtPanel().getMtDatasourcePanel().getWrapper().add(fs.build(dvo, w));
				w.getMtPanel().getMtDatasourcePanel().getWrapper().getLayout().layout();
				w.getMtPanel().getMtDatasourcePanel().getAddedDatasets().add(Long.valueOf(dvo.getDsId()));
			} else {
				FenixAlert.info(BabelFish.print().info(), "'" + dvo.getDatasetName() + "' has already been added to your current selection.");
			}
		}
	}
	
	public static SelectionListener<ButtonEvent> viewTable(final MTWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				viewTableAgent(w, false);
			}
		};
	}
	
	public static void viewTableAgent(final MTWindow w, final boolean showUnSelectedValues) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Creating Multidimensional Table.", BabelFish.print().pleaseWait());
		try {
			isValid(w);
			final OLAPParametersVo parameters = MTParametersCollector.retrieveParameters(w);
			OlapServiceEntry.getInstance().createTable(parameters, new AsyncCallback<String>() {
				public void onSuccess(String html) {
					l.destroyLoadingBox();
					w.getMtPanel().getMtTablePanel().setHtml(html);
					w.getMtPanel().getLayout().setActiveItem(w.getMtPanel().getMtTablePanel().getLayoutContainer());
					restoreButtonsColor(w.getMtPanel());
					w.getMtPanel().getTableButton().setText("<b><font color='#CA1616'><u>View Table</u><font></b>");
					if (showUnSelectedValues) {
						showUnSelectedValues(w, parameters);
					}
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable e) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			l.destroyLoadingBox();
		}
	}
	
	public static void showUnSelectedValues(final MTWindow w, final OLAPParametersVo pvo) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Loading Dimensions Values", BabelFish.print().pleaseWait());
		try {
			OlapServiceEntry.getInstance().showUnSelectedValues(pvo, CheckLanguage.getLanguage(), new AsyncCallback<Map<String,List<UniqueValueVO>>>() {
				public void onSuccess(Map<String, List<UniqueValueVO>> map) {
					l.destroyLoadingBox();
					for (String axis : map.keySet()) {
						if (axis.equals("X")) {
							showUnSelectedValues(w.getMtPanel().getMtColumnsPanel().getValuesList(), map.get(axis));
						} else if (axis.equals("Z")) {
							showUnSelectedValues(w.getMtPanel().getMtRowsPanel().getValuesList(), map.get(axis));
						} else if (axis.equals("W")) {
							showUnSelectedValues(w.getMtPanel().getMtSubRowsPanel().getValuesList(), map.get(axis));
						} else if (axis.equals("Y")) {
							showUnSelectedValues(w.getMtPanel().getMtSubColumnsPanel().getValuesList(), map.get(axis));
						}
					}
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E2) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			l.destroyLoadingBox();
		}
	}
	
	public static void showUnSelectedValues(ListBox l, List<UniqueValueVO> values) {
		for (UniqueValueVO vo : values) {
			if (!containsLabel(l, vo.getLabel())) {
				l.addItem(vo.getLabel(), vo.getCode());
			}
		}
	}
	
	public static boolean isValid(final MTWindow w) throws FenixGWTException {
		if (w.getMtID() == null) {
			if (w.getMtPanel().getMtDatasourcePanel().getFieldSets().isEmpty())
				throw new FenixGWTException("Please select one datasource.");
			if (w.getMtPanel().getMtRowsPanel().getDimensionList().getSelectedIndex() < 0)
				throw new FenixGWTException("Please select one dimension for the Rows.");
			if (w.getMtPanel().getMtRowsPanel().getValuesList().getSelectedIndex() < 0)
				throw new FenixGWTException("Please select at least one value for the Rows.");
			if (w.getMtPanel().getMtColumnsPanel().getDimensionList().getSelectedIndex() < 0)
				throw new FenixGWTException("Please select one dimension for the Columns.");
			if (w.getMtPanel().getMtColumnsPanel().getValuesList().getSelectedIndex() < 0)
				throw new FenixGWTException("Please select at least one value for the Columns.");
		}
		return true;
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
	
	public static void fillFontFamilyList(ListBox l) {
		l.addItem("Sans Serif", "sans-serif");
		l.addItem("Times New Roman", "Times New Roman");
		l.addItem("Arial", "arial");
	}
	
	public static void fillFontSizeList(ListBox l) {
		for (int i = 1 ; i < 21 ; i++)
			l.addItem(String.valueOf(i), String.valueOf(i));
		l.setSelectedIndex(7);
	}
	
	public static void fillDecimalsList(ListBox l) {
		for (int i = 0 ; i < 5 ; i++)
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
	
	public static ChangeHandler columnWidths(final MTWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				columnWidthsAgent(w);
			}
		};
	}
	
	public static void columnWidthsAgent(MTWindow w) {
		List<String> labels = new ArrayList<String>();
		if (w.getMtPanel().getMtSubColumnsPanel().getValuesList().getSelectedIndex() > -1) {
			for (int i = 0 ; i < w.getMtPanel().getMtSubColumnsPanel().getValuesList().getItemCount() ; i++) {
				if (w.getMtPanel().getMtSubColumnsPanel().getValuesList().isItemSelected(i)) {
					labels.add(w.getMtPanel().getMtSubColumnsPanel().getValuesList().getItemText(i));
				}
			}
			if (w.getMtPanel().getMtSubColumnsPanel().getUseFromDateToDate().getValue()) {
				labels = adaptLabelsForFDTD(labels, w.getMtPanel().getMtSubColumnsPanel().getFromDate().getValue(), w.getMtPanel().getMtSubColumnsPanel().getToDate().getValue());
			} else if (w.getMtPanel().getMtSubColumnsPanel().getUseMostRecentData().getValue()) {
				int latestYears = Integer.valueOf(w.getMtPanel().getMtSubColumnsPanel().getLatestYearsList().getValue(w.getMtPanel().getMtSubColumnsPanel().getLatestYearsList().getSelectedIndex()));
				int latestMonths = Integer.valueOf(w.getMtPanel().getMtSubColumnsPanel().getLatestMonthsList().getValue(w.getMtPanel().getMtSubColumnsPanel().getLatestMonthsList().getSelectedIndex()));
				int latestDays = Integer.valueOf(w.getMtPanel().getMtSubColumnsPanel().getLatestDaysList().getValue(w.getMtPanel().getMtSubColumnsPanel().getLatestDaysList().getSelectedIndex()));
				labels = adaptLabelsForMRD(labels, latestYears, latestMonths, latestDays);
			}
		} else {
			for (int i = 0 ; i < w.getMtPanel().getMtColumnsPanel().getValuesList().getItemCount() ; i++) {
				if (w.getMtPanel().getMtColumnsPanel().getValuesList().isItemSelected(i)) {
					labels.add(w.getMtPanel().getMtColumnsPanel().getValuesList().getItemText(i));
				}
			}
			if (w.getMtPanel().getMtColumnsPanel().getUseFromDateToDate().getValue()) {
				labels = adaptLabelsForFDTD(labels, w.getMtPanel().getMtColumnsPanel().getFromDate().getValue(), w.getMtPanel().getMtColumnsPanel().getToDate().getValue());
			} else if (w.getMtPanel().getMtColumnsPanel().getUseMostRecentData().getValue()) {
				int latestYears = Integer.valueOf(w.getMtPanel().getMtColumnsPanel().getLatestYearsList().getValue(w.getMtPanel().getMtColumnsPanel().getLatestYearsList().getSelectedIndex()));
				int latestMonths = Integer.valueOf(w.getMtPanel().getMtColumnsPanel().getLatestMonthsList().getValue(w.getMtPanel().getMtColumnsPanel().getLatestMonthsList().getSelectedIndex()));
				int latestDays = Integer.valueOf(w.getMtPanel().getMtColumnsPanel().getLatestDaysList().getValue(w.getMtPanel().getMtColumnsPanel().getLatestDaysList().getSelectedIndex()));
				labels = adaptLabelsForMRD(labels, latestYears, latestMonths, latestDays);
			}
		}
		w.getMtPanel().getMtLookAndFeelPanel().addContentWidths(labels);
	}
	
	public static List<String> adaptLabelsForFDTD(List<String> labels, Date from, Date to) {
		List<String> filtered = new ArrayList<String>();
		for (String l : labels) {
			try {
				Date c = string2date(l);
				if ((from.compareTo(c) <= 0) & (c.compareTo(to) <= 0))
					filtered.add(l);
			} catch (IllegalArgumentException e) {
//				System.out.println(e.getMessage() + " for " + l);
			}
		}
		return filtered;
	}
	
	public static Date string2date(String s) {
		Date d = new Date();
		d.setYear(Integer.valueOf(s.substring(0, 4)) - 1900);
		d.setMonth(Integer.valueOf(s.substring(5, 7)) - 1);
		d.setDate(Integer.valueOf(s.substring(8)));
		return d;
	}
	
	public static List<String> adaptLabelsForMRD(List<String> labels, int latestYears, int latestMonths, int latestDays) {
		Date a = new Date();
		a.setYear(a.getYear() - latestYears);
		a.setMonth(a.getMonth() - latestMonths);
		a.setDate(a.getDate() - (1 + latestDays));
		return adaptLabelsForFDTD(labels, a, new Date());
	}
	
	public static ChangeHandler rowsHeights(final MTWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				rowsHeightsAgent(w);
			}
		};
	}
	
	public static void rowsHeightsAgent(MTWindow w) {
		List<String> labels = new ArrayList<String>();
		if (w.getMtPanel().getMtSubRowsPanel().getValuesList().getSelectedIndex() > -1) {
			for (int i = 0 ; i < w.getMtPanel().getMtSubRowsPanel().getValuesList().getItemCount() ; i++) {
				if (w.getMtPanel().getMtSubRowsPanel().getValuesList().isItemSelected(i)) {
					labels.add(w.getMtPanel().getMtSubRowsPanel().getValuesList().getItemText(i));
				}
			}
			if (w.getMtPanel().getMtSubRowsPanel().getUseFromDateToDate().getValue()) {
				labels = adaptLabelsForFDTD(labels, w.getMtPanel().getMtSubRowsPanel().getFromDate().getValue(), w.getMtPanel().getMtSubRowsPanel().getToDate().getValue());
			} else if (w.getMtPanel().getMtSubRowsPanel().getUseMostRecentData().getValue()) {
				int latestYears = Integer.valueOf(w.getMtPanel().getMtSubRowsPanel().getLatestYearsList().getValue(w.getMtPanel().getMtSubRowsPanel().getLatestYearsList().getSelectedIndex()));
				int latestMonths = Integer.valueOf(w.getMtPanel().getMtSubRowsPanel().getLatestMonthsList().getValue(w.getMtPanel().getMtSubRowsPanel().getLatestMonthsList().getSelectedIndex()));
				int latestDays = Integer.valueOf(w.getMtPanel().getMtSubRowsPanel().getLatestDaysList().getValue(w.getMtPanel().getMtSubRowsPanel().getLatestDaysList().getSelectedIndex()));
				labels = adaptLabelsForMRD(labels, latestYears, latestMonths, latestDays);
			}
		} else {
//			System.out.println("\tconsidering rows...");
			for (int i = 0 ; i < w.getMtPanel().getMtRowsPanel().getValuesList().getItemCount() ; i++) {
				if (w.getMtPanel().getMtRowsPanel().getValuesList().isItemSelected(i)) {
					labels.add(w.getMtPanel().getMtRowsPanel().getValuesList().getItemText(i));
				}
			}
//			System.out.println("\tlabels: " + labels.size());
			if (w.getMtPanel().getMtRowsPanel().getUseFromDateToDate().getValue()) {
				labels = adaptLabelsForFDTD(labels, w.getMtPanel().getMtRowsPanel().getFromDate().getValue(), w.getMtPanel().getMtRowsPanel().getToDate().getValue());
//				System.out.println("\tlabels: " + labels.size());
			} else if (w.getMtPanel().getMtRowsPanel().getUseMostRecentData().getValue()) {
				int latestYears = Integer.valueOf(w.getMtPanel().getMtRowsPanel().getLatestYearsList().getValue(w.getMtPanel().getMtRowsPanel().getLatestYearsList().getSelectedIndex()));
				int latestMonths = Integer.valueOf(w.getMtPanel().getMtRowsPanel().getLatestMonthsList().getValue(w.getMtPanel().getMtRowsPanel().getLatestMonthsList().getSelectedIndex()));
				int latestDays = Integer.valueOf(w.getMtPanel().getMtRowsPanel().getLatestDaysList().getValue(w.getMtPanel().getMtRowsPanel().getLatestDaysList().getSelectedIndex()));
				labels = adaptLabelsForMRD(labels, latestYears, latestMonths, latestDays);
			}
		}
		w.getMtPanel().getMtLookAndFeelPanel().addContentHeights(labels);
	}
	
	public static SelectionListener<MenuEvent> saveFromMenu(final MTWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				saveAgent(w);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> saveAsFromMenu(final MTWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				boolean isEditable = (FenixUser.hasUserRole() || FenixUser.hasAdminRole()); 
				if (isEditable) {
					MESaveAs.prepopulateMT(w.getMtID(), isEditable, false, w);
				} else {
					FenixAlert.info(BabelFish.print().info(), "To Save a Resource, Please Sign In");
				}
			}
		};
	}
	
	private static void saveAgent(MTWindow w) {
		boolean isEditable = (FenixUser.hasUserRole() || FenixUser.hasAdminRole()); 
		if (isEditable) {
			OLAPParametersVo pvo = MTParametersCollector.retrieveParameters(w);
			pvo.setOlapHtml(w.getMtPanel().getMtTablePanel().getHtml().toString());
			if (w.getMtID() == null) {
				MetadataEditorWindow mew = new MetadataEditorWindow();
				mew.build(false, true, false, MESaver.getSaveMTListener(mew, pvo, w));
			} else {
				updateOLAP(pvo);
			}
		} else {
			FenixAlert.info(BabelFish.print().info(), "To Save a Resource, Please Sign In");
		}
	}
	
	private static void updateOLAP(final OLAPParametersVo pvo) {
		final LoadingWindow l = new LoadingWindow("Update", "Multidimensional Table is going to be updated.", "Please wait...");
		OlapServiceEntry.getInstance().saveOlap(pvo, new AsyncCallback<Long>() {
			public void onSuccess(Long id) {
				l.destroyLoadingBox();
				FenixAlert.info("INFO", "Multidimensional Table has been updated.");
				l.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				l.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				l.destroyLoadingBox();
			}
		});
	}
	
	public static SelectionListener<MenuEvent> exportAsHTML(final MTWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				if (w.getMtID() != null) {
					try {
						OlapServiceEntry.getInstance().getIFrame(w.getMtID(), new AsyncCallback<String>() {
							public void onSuccess(String iframe) {
								Map<String, String> tagMap = new HashMap<String, String>();
								tagMap.put("IFRAME", iframe);
								new ChartDesignerExportToHTMLWindow().build(tagMap);
							}
							public void onFailure(Throwable E2) {
								FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							}
						});
					} catch (FenixGWTException E1) {
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					}
				} else {
					FenixAlert.info(BabelFish.print().info(), "Please Save This Table First.");
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> exportAsExcel(final MTWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Creating Excel file.", BabelFish.print().pleaseWait());
				try {
					isValid(w);
					OLAPParametersVo pvo = MTParametersCollector.retrieveParameters(w);
					OlapServiceEntry.getInstance().createExcel(pvo, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							l.destroyLoadingBox();
							Window.open("../olapExcel/" + result, "_blank", "status=no");
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable E2) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException E1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					l.destroyLoadingBox();
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> openAsChart(final MTWindow w) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				try {
					isValid(w);
					isAggregated(w);
					OLAPParametersVo pvo = MTParametersCollector.retrieveParameters(w);
					MT2ChartDesignerWindow w = new MT2ChartDesignerWindow();
					w.build(pvo);
				} catch (FenixGWTException E1) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	public static void isAggregated(MTWindow w) throws FenixGWTException {
		String message = "Aggregation Functions are not (yet) available for Charts, therefore it is not possible to plot your table. Please de-select the Aggregation Functions and try again. We apologize for any inconvenience.";
		if (w.getMtPanel().getMtColumnsPanel().getAggregateMonthly().getValue() || w.getMtPanel().getMtColumnsPanel().getAggregateYearly().getValue()) {
			throw new FenixGWTException(message);
		}
		if (w.getMtPanel().getMtSubColumnsPanel().getAggregateMonthly().getValue() || w.getMtPanel().getMtSubColumnsPanel().getAggregateYearly().getValue()) {
			throw new FenixGWTException(message);
		}
		if (w.getMtPanel().getMtRowsPanel().getAggregateMonthly().getValue() || w.getMtPanel().getMtRowsPanel().getAggregateYearly().getValue()) {
			throw new FenixGWTException(message);
		}
		if (w.getMtPanel().getMtSubRowsPanel().getAggregateMonthly().getValue() || w.getMtPanel().getMtSubRowsPanel().getAggregateYearly().getValue()) {
			throw new FenixGWTException(message);
		}
	}
	
	public static SelectionListener<ButtonEvent> showManual(final MTWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getMtManualWindow().build();
			}
		};
	}
	
	public static WindowListener close(final MTWindow w) {
		return new WindowListener() {
			public void windowHide(WindowEvent we) {
				super.windowHide(we);
				System.out.println("w.getCaller()? " + w.getCaller());
				if (w.getCaller().equals(WhoCall.TINYMCE_EDIT)) {
					String content = TinyMCENativeController.getContent(w.getTinyMCEPanelID());
					System.out.println("content? " + content);
					TinyMCEController.reLoadOLAP(w.getTinyMCEPanelID(), w.getMtID(), content);
				}
			}
		};
	}
	
}