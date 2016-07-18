package org.fao.fenix.web.modules.core.client.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.re.client.control.image.CatalogueContextMenuImageController;
import org.fao.fenix.web.modules.re.client.control.latexreport.CatalogueContextMenuLatexReportController;
import org.fao.fenix.web.modules.re.client.control.olap.CatalogueContextMenuOlapController;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.text.client.view.viewer.TextViewer;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;
import org.fao.fenix.web.modules.text.common.vo.TextGroupVO;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ResourceOpener {

	public static void openResource(final TreeItem selectedItem) {
		final ResourceChildModel model = (ResourceChildModel) selectedItem.getModel();
		resourceOpener(model, selectedItem);
	}

	// Resource Openers
	public static void resourceOpener(final ResourceChildModel model, final TreeItem selectedItem) {

		String type = model.getType();
		String id = model.getId();
		
		if (type.equals(ResourceType.MAPVIEW)) {

			final String gaul0code = selectedItem.getParentItem().getData("projectRegion");

			MapServiceEntry.getInstance().getMap(Long.valueOf(model.getId()), new AsyncCallback<ClientMapView>() {

				public void onSuccess(final ClientMapView cmv) {
					if (gaul0code != null && !gaul0code.equals("0")) {
						MapServiceEntry.getInstance().getCountryExtents(gaul0code, new AsyncCallback<ClientBBox>() {

							public void onSuccess(ClientBBox bbox) {
								cmv.setBbox(bbox);
								MapWindow mw = new MapWindow(cmv);
								mw.show();
							}

							public void onFailure(Throwable e) {
								FenixAlert.alert("Server error", "{0} <BR> Map will not be zoomed on requested country.", e.getMessage());
								MapWindow mw = new MapWindow(cmv);
								mw.show();
							}
						});
					} else {
						MapWindow mw = new MapWindow(cmv);
						mw.show();
					}
				}

				public void onFailure(Throwable e) {
					FenixAlert.alert("RPC Failed", e.getMessage());
				}
			});

		} else if (type.equals(ResourceType.CHARTVIEW)) {
//			new ChartViewer(model.getId());
			Map<Long, String> ids = new HashMap<Long, String>();
			if (model.getName() != null)
				ids.put(Long.valueOf(model.getId()), model.getName());
			else
				ids.put(Long.valueOf(model.getId()), "");
			ChartDesignerController.loadChart(ids, WhoCall.USER, "", null, null);
		} else if (type.equals(ResourceType.TABLEVIEW)) {
			TableServiceEntry.getInstance().getDatasetIdFromTableViewId(Long.valueOf(id), new AsyncCallback<Long>() {

				public void onSuccess(Long resourceId) {
					new TableWindow().showAllData(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
					//new TableWindow().build(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "getDatasetIdFromTableViewId @ openResources");
				}
			});

		} else if (type.equals(ResourceType.DATASET)) { // default to table view
			openDatasetAsTable(model); // new
										// TableWindow().build(Long.valueOf(id));
		} else if (type.equals(ResourceType.REPORT)) {
			new ReportViewer(Long.valueOf(model.getId()));
		} else if(type.equalsIgnoreCase(ResourceType.OLAP) || type.equalsIgnoreCase(ResourceType.RESOURCEVIEW)) {
			CatalogueContextMenuOlapController.open(Long.valueOf(id), model.getName(), WhoCall.USER, "");
		} else if (type.equals(ResourceType.TEXTVIEW)) {
			if (model.hasWritePermission() != null) {
				openTextViewer(Long.valueOf(id), model.hasWritePermission());
			} else {
				openTextViewer(Long.valueOf(id), false);
			}
		} else if (type.equals(ResourceType.TEXTGROUP)) {
			if (model.hasWritePermission() != null) {
				openTextGroup(Long.valueOf(id), model.hasWritePermission());
			} else {
				openTextGroup(Long.valueOf(id), false);
			}
		} else if (type.equals(ResourceType.LAYER)) {
			List<Long> layerIds = new ArrayList<Long>();
			layerIds.add(Long.valueOf(model.getId()));

			MapServiceEntry.getInstance().getLayers(layerIds, new AsyncCallback<List<ClientGeoView>>() {

				public void onSuccess(List<ClientGeoView> cgvlist) {
					ClientMapView mapView = new ClientMapView();
					mapView.setTitle("New map");
					ClientBBox bbox = null;

					for (ClientGeoView clientGeoView : cgvlist) {
						mapView.addLayer(clientGeoView);

						if (bbox == null)
							bbox = clientGeoView.getBBox();
						else
							bbox = bbox.union(clientGeoView.getBBox());
					}

					System.out.println("Zooming new map to " + bbox);
					mapView.setBbox(bbox);
					new MapWindow(mapView);
					Info.display("Map created", "Opening new map");
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "getLayers @ openResources");
				}
			});

		} else if (type.equals(ResourceType.PATTERN)) {
			// FenixAlert.alert("Open Data Theme Failed",
			// "Opening of a Data Theme resource is not available at the moment.");
		} else if (type.equals(ResourceType.CODINGSYSTEM)) {
			new CodingSearchResults().build(Long.valueOf(model.getId()));
		} else if (type.equals(ResourceType.IMAGE)) {
			CatalogueContextMenuImageController.open(Long.valueOf(id));
		}

	}

	public static void openDatasetAsTable(final TreeItem selectedItem) {

		ResourceChildModel model = (ResourceChildModel) selectedItem.getModel();

		if (model.hasWritePermission() != null && model.isFlexDatasetType() != null) {
			new TableWindow().showAllData(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
			//new TableWindow().build(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
		} else {
			
			new TableWindow().showAllData(Long.valueOf(model.getId()), false, false, model.getName());
			//new TableWindow().build(Long.valueOf(model.getId()), false, false, model.getName());
		}
	}

	public static void openDatasetAsTable(final ResourceChildModel model) {
		
		new TableWindow().showAllData(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
		//new TableWindow().build(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
	}

	public static void openDatasetAsChart(final TreeItem selectedItem) {

		final List<List<String>> datasetsMap = new ArrayList<List<String>>();
		final ResourceChildModel model = (ResourceChildModel) selectedItem.getModel();

		BirtServiceEntry.getInstance().isQualitativeDataset(Long.valueOf(model.getId()), new AsyncCallback<Boolean>() {

			public void onSuccess(Boolean result) {
				if (!result) {
					List<String> element = new ArrayList<String>();
					element.add(model.getName()); // i.e. title
					element.add(model.getId());
					datasetsMap.add(element);
				} else {
					FenixAlert.alert("Warning", model.getName() + " is a qualitative dataset. You can't chart it.", "");
				}

				if (datasetsMap.size() > 0) {
					new ChartWizard(datasetsMap);
				}
			}

			public void onFailure(Throwable caught) {
				Info.display("isQualitativeDataset", caught.getLocalizedMessage());
			}
		});
	}

	public static void openTextViewer(final Long id, final boolean isEditable) {
		TextServiceEntry.getInstance().getText(id, new AsyncCallback<List<String>>() {

			public void onSuccess(final List<String> result) {
				// new TextViewer(id,
				// String.valueOf(Fenix.workspaceManager.counter),
				// result).build();
				new TextViewer(id, result).build(isEditable);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("Error: RPC Failed", "TextService:getText() Failed");
			}
		});
	}
	
	public static void openOlapViewer(final Long id, final boolean isEditable) {
		CatalogueContextMenuOlapController.open(Long.valueOf(id), "ResourceOpener @ 226", WhoCall.USER, "");
	}
	
	public static void openImageViewer(final Long id, final boolean isEditable) {
		CatalogueContextMenuImageController.open(Long.valueOf(id));
	}
	
	public static void openLatexReport(final Long id, final String reportName, final boolean isEditable) {
		CatalogueContextMenuLatexReportController.open(Long.valueOf(id), reportName);
	}

	public static void openTextGroup(final Long id, final boolean isEditable) {
		TextServiceEntry.getInstance().getTextGroup(id, new AsyncCallback<TextGroupVO>() {

			public void onSuccess(final TextGroupVO textGroupVO) {
				new TextGroupWindow(textGroupVO).build(isEditable);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("Service call failed", "getTextGroup()");
			}
		});
	}

	// Other function openers
	public static void openMetadata(final Long id, final boolean isEditable) {
		MEOpener.showMetadata(id, isEditable, false);
	}

	public static void openMetadata(ResourceChildModel rsrcModel) {
		if (rsrcModel.getType().equalsIgnoreCase(ResourceType.LAYER) || rsrcModel.getType().equalsIgnoreCase(ResourceType.LAYERSUBTYPE)) {
			MEOpener.showMetadata(Long.parseLong(rsrcModel.getId()), false, true);
		} else {
			MEOpener.showMetadata(Long.parseLong(rsrcModel.getId()), false, false);
		}
	}

	public static void openRename(final ResourceChildModel model) {
		MetadataEditorWindow meWindow = new MetadataEditorWindow();
		if (model.getType().equalsIgnoreCase(ResourceType.LAYER) || model.getType().equalsIgnoreCase(ResourceType.LAYERSUBTYPE)) {
			meWindow.build(false, true, true, MESaver.getUpdateMetadataListener(meWindow, Long.parseLong(model.getId())));
		} else {
			meWindow.build(false, true, false, MESaver.getUpdateMetadataListener(meWindow, Long.parseLong(model.getId())));
		}
	}
}
