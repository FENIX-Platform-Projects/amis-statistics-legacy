package org.fao.fenix.web.modules.re.client.control.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.ipc.client.view.ConfirmWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.chart.CatalogueContextMenuChart;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueChartController extends CatalogueController {
	
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				
				onDoubleClickAction(resourceExplorer);
				
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer){
		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		
	    final List<ResourceChildModel> resources = new ArrayList<ResourceChildModel>();
//	    System.out.println("CatalogueChartController @ 52 - resourceExplorer.getCaller()? " + resourceExplorer.getCaller());
		
		if (resourceExplorer.getCaller().equals(WhoCall.USER)){
//			new ChartViewer(model.getId());
//			PMModel.addResourceToProjectManager(resources, ResourceType.CHARTVIEW);
//			if (REModel.CLOSE)
//				resourceExplorer.getWindow().close();
			resources.add(model);
			Map<Long, String> ids = new HashMap<Long, String>();
			for (ResourceChildModel m : resources)
				ids.put(Long.valueOf(m.getId()), m.getName());
			ChartDesignerController.loadChart(ids, WhoCall.USER, "", null, null);
//			PMModel.addResourceToProjectManager(resources, ResourceType.CHARTVIEW);
			if (REModel.CLOSE) 
				resourceExplorer.getWindow().close();
		} 
		
		else if (resourceExplorer.getCaller().equals(WhoCall.DESIGNER)) {
//			 DesignerBox designerBox = ((ResourceExplorerChart)resourceExplorer).getDesignerBox();
//			 designerBox.setResourceID(Long.valueOf(model.getId()));
//			 designerBox.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.CHART);
//			 designerBox.setResourceTitle(model.getName());
//			 designerBox.getResourceName().setValue(model.getName());
//			 resourceExplorer.getWindow().close();
			 String tinyMCEPanelID = ((ResourceExplorerChart)resourceExplorer).getTinyMCEPanelID();
			 TinyMCEController.loadChart(tinyMCEPanelID, Long.valueOf(model.getId()));
			 resourceExplorer.getWindow().close();
		 }
		
		// TODO keep image proportion
		// TODO restore image if RE is closed without selection
		else  if (resourceExplorer.getCaller().equals(WhoCall.REPORT)) {
			String tinyMCEPanelID = ((ResourceExplorerChart)resourceExplorer).getTinyMCEPanelID();
			String tinyMCEPanelMarkID = ((ResourceExplorerChart)resourceExplorer).getTinyMCEPanelMarkID();
			TinyMCEController.addChartToTemplate(tinyMCEPanelID, Long.valueOf(model.getId()), tinyMCEPanelMarkID);
			resourceExplorer.getWindow().close();
		}
		
//		else  if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){
//			final ReportViewer reportViewer = ((ResourceExplorerChart)resourceExplorer).getReportViewer();
//			final ResourceChildModel modelBis = model;
//			final LoadingWindow loading = new LoadingWindow();
//			loading.create();
//			BirtServiceEntry.getInstance().createReport(Long.valueOf(model.getId()), reportViewer.getObjectPosition(), reportViewer.getRptDesign(), "chart", 400, 300, reportViewer.getKeyTemplate(), new AsyncCallback<String>(){
//				public void onSuccess(String s) {
//					if ((reportViewer.getKeyTemplate().equals("template1") || reportViewer.getKeyTemplate().equals("template2")) && !reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText().equals("( " + BabelFish.print().empty() + ")")){
//						reportViewer.getSideBar().deleteResource("chart", reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getItemId());
//						//Window.alert(reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText());
//					}
//					reportViewer.getSideBar().addElementToListResource(modelBis.getId(), modelBis.getName(), "chart", reportViewer.getObjectPosition());
//					reportViewer.getReport().setHTML(s);
//					loading.destroy();
//				}
//				public void onFailure(Throwable caught) {
//					Info.display("Service call failed!", "Service call to {0} failed", "createReport()");
//					loading.destroy();
//				}
//			});
//			resourceExplorer.getWindow().close();
//		}
		
	}
	
	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuChart(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueChartController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}

	public static void associateDatasetToChart(final ResourceExplorer resourceExplorerChart, final ResourceChildModel dataset) {
		final Grid<ResourceChildModel> table = resourceExplorerChart.getCatalogue().getGrid();
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		System.out.println("CHART -> " + selectedItems.get(0).getName() + " | " + selectedItems.get(0).getId());
		System.out.println("dataset -> " + dataset.getName() + " | " + dataset.getId());
		new ConfirmWindow(BabelFish.print().updateChart(),BabelFish.print().areYouSureUpdateChart() , updateChart(Long.parseLong(selectedItems.get(0).getId()), Long.parseLong(dataset.getId())));

//		MessageBox box = new MessageBox();	
//		box.setDefaultTextHeight(150);
//		box.setButtons(MessageBox.YESNO);
//		box.setIcon(MessageBox.WARNING);
//		box.setTitle("<b>" + I18N.print().confirmResourceDelete() + "</b>");
//		box.setMessage("<b>" + I18N.print().areYouSureDeleteResource() + "</b>");
//		box.addCallback(new Listener<MessageBoxEvent>() {
//			public void handleEvent(MessageBoxEvent event) {
//				Button btn = event.getButtonClicked();
//				if (btn.getText().toLowerCase().equals(I18N.print()) {
//					REServiceEntry.getInstance().associateDatesetToChart(Long.parseLong(selectedItems.get(0).getId()), Long.parseLong(dataset.getId()), new AsyncCallback<Boolean>(){
//
//						public void onSuccess(Boolean result) {
//							FenixAlert.info(I18N.print().updateCompleted(), I18N.print().updateSuccessful() , "");
//						}
//						
//						public void onFailure(Throwable caught) {
//							FenixAlert.error(I18N.print().error(), "Unable to set the dataset", "");
//						}
//						
//					});
//				} else {
//					// Info.display("confirmDelete", "The '{0}' button was pressed",
//					// btn.getText());
//					Dialog dialog = (Dialog) event.getComponent();
//					dialog.hide();
//				}
//			}
//		});
//
//		box.show();
	}
	
	private static SelectionListener<ButtonEvent> updateChart(final Long chartId,final Long datasetId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				REServiceEntry.getInstance().associateDatesetToChart(chartId, datasetId, new AsyncCallback<Boolean>(){

					public void onSuccess(Boolean result) {
						FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful() , "");
					}
					
					public void onFailure(Throwable caught) {
						FenixAlert.error(BabelFish.print().error(), "Unable to set the dataset", "");
					}
				});
			}
		};
	}
}
