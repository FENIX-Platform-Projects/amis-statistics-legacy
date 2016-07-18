package org.fao.fenix.web.modules.x.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.x.client.view.XExplorerDatasetFilter;
import org.fao.fenix.web.modules.x.client.view.XExplorerMetadataPanel;
import org.fao.fenix.web.modules.x.client.view.XExplorerResultPanel;
import org.fao.fenix.web.modules.x.client.view.XExplorerSearchPanel;
import org.fao.fenix.web.modules.x.client.view.XExplorerWindow;
import org.fao.fenix.web.modules.x.common.services.XServiceEntry;
import org.fao.fenix.web.modules.x.common.vo.XDescriptorVO;
import org.fao.fenix.web.modules.x.common.vo.XExplorerModelData;
import org.fao.fenix.web.modules.x.common.vo.XUniqueValueVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class XExplorerDatasetFilterController extends XExplorerController {

	private static String VALUES_ORACLE_BUFFER = "";
	
	public static KeyListener valuesOracleListener(final TextField<String> valuesOracle, final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel xExplorerResultPanel) {
		return new KeyListener() {
			public void componentKeyUp(ComponentEvent event) {
				super.componentKeyUp(event);
				String content = valuesOracle.getValue();
				if ((content != null) && (content.length() < VALUES_ORACLE_BUFFER.length())) {
					ListBox datasetDimensionList = xExplorerSearchPanel.getxExplorerDatasetFilter().getDatasetDimensionsList();
					datasetDimensionChangeAgent(datasetDimensionList, xExplorerSearchPanel, xExplorerResultPanel);
				} 
				filterList(content, xExplorerSearchPanel);
			}
		};
	}
	
	private static void filterList(String content, XExplorerSearchPanel xExplorerSearchPanel) {
		VALUES_ORACLE_BUFFER = content;
		ListBox unselectedValuesList = xExplorerSearchPanel.getxExplorerDatasetFilter().getUnselectedValuesList();
		for (int i = unselectedValuesList.getItemCount() - 1 ; i >= 0 ; i--) 
			if (!unselectedValuesList.getItemText(i).toLowerCase().contains(content.toLowerCase()))
				unselectedValuesList.removeItem(i);
	}
	
	public static SelectionListener<ButtonEvent> clearFilter(final HTML filter, final Map<Map<String, String>, Map<String, String>> filterMap) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				filter.setHTML("");
				filterMap.clear();
			}		
		};
	}
	
	public static SelectionListener<ButtonEvent> addToFilter(final XExplorerDatasetFilter xExplorerDatasetFilter) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox datasetDimensionsList = xExplorerDatasetFilter.getDatasetDimensionsList();
				ListBox selectedValuesList = xExplorerDatasetFilter.getSelectedValuesList();
				Map<Map<String, String>, Map<String, String>> filterMap = xExplorerDatasetFilter.getFilterMap();
				HTML filter = xExplorerDatasetFilter.getFilter();
				Map<String, String> selectedCodes = new HashMap<String, String>();
				for (int i = 0 ; i < selectedValuesList.getItemCount() ; i++)
					selectedCodes.put(selectedValuesList.getValue(i), selectedValuesList.getItemText(i));
				String header = datasetDimensionsList.getItemText(datasetDimensionsList.getSelectedIndex());
				String localDescriptorID = xExplorerDatasetFilter.getDescriptorMap().get(header).getLocalDescriptorID();
				Map<String, String> key = new HashMap<String, String>();
				key.put(localDescriptorID, header);
				filterMap.put(key, selectedCodes);
				updateFilter(filterMap, filter);
			}		
		};
	}
	
//	public static Map<String, Map<String, String>> createFilterMap(final ListBox datasetDimensionsList, final ListBox selectedValuesList) {
//		Map<String, Map<String, String>> filterMap = new HashMap<String, Map<String,String>>();
//		Map<String, String> selectedCodes = new HashMap<String, String>();
//		for (int i = 0 ; i < selectedValuesList.getItemCount() ; i++)
//			selectedCodes.put(selectedValuesList.getValue(i), selectedValuesList.getItemText(i));
//		try {
//			String header = datasetDimensionsList.getItemText(datasetDimensionsList.getSelectedIndex());
//			filterMap.put(header, selectedCodes);
//		} catch (IndexOutOfBoundsException e) {
//			
//		}
//		return filterMap;
//	}
	
	private static void updateFilter(Map<Map<String, String>, Map<String, String>> filterMap, HTML filter) {
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for (Map<String, String> dimensionMap : filterMap.keySet()) {
			for (String key : dimensionMap.keySet()) {
				j++;
				sb.append("(<b>");
				sb.append(dimensionMap.get(key));
				sb.append(":</b> ");
				Map<String, String> codes = filterMap.get(dimensionMap);
				int i = 0;
				for (String code : codes.keySet()) {
					i++;
					sb.append("<i>");
					sb.append(codes.get(code));
					sb.append("</i>");
					if (i < codes.size())
						sb.append(" <u>OR</u> ");
				}
				sb.append(")");
				if (j < filterMap.size())
					sb.append(" <u>AND</u> ");
			}
		}
		filter.setHTML(sb.toString());
	}
	
	public static SelectionListener<ButtonEvent> add(final ListBox unselectedValuesList, final ListBox selectedValuesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = 0 ; i < unselectedValuesList.getItemCount() ; i++)
					if (unselectedValuesList.isItemSelected(i) && !listContains(selectedValuesList, unselectedValuesList.getItemText(i), unselectedValuesList.getValue(i)))
						selectedValuesList.addItem(unselectedValuesList.getItemText(i), unselectedValuesList.getValue(i));
			}		
		};
	}
	
	public static SelectionListener<ButtonEvent> addAll(final ListBox unselectedValuesList, final ListBox selectedValuesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				clearList(selectedValuesList);
				for (int i = 0 ; i < unselectedValuesList.getItemCount() ; i++)
					selectedValuesList.addItem(unselectedValuesList.getItemText(i), unselectedValuesList.getValue(i));
			}		
		};
	}
	
	public static SelectionListener<ButtonEvent> remove(final ListBox selectedValuesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = selectedValuesList.getItemCount() - 1 ; i >= 0 ; i--)
					if (selectedValuesList.isItemSelected(i))
						selectedValuesList.removeItem(i);
			}		
		};
	}
	
	public static SelectionListener<ButtonEvent> removeAll(final ListBox selectedValuesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = selectedValuesList.getItemCount() - 1 ; i >= 0 ; i--)
					selectedValuesList.removeItem(i);
			}		
		};
	}
	
	public static Listener<BaseEvent> rowClickListener(final XExplorerWindow xExplorerWindow) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				
				final XExplorerSearchPanel xExplorerSearchPanel = xExplorerWindow.getxExplorerSearchPanel();
				final XExplorerResultPanel xExplorerResultPanel = xExplorerWindow.getxExplorerResultPanel();
				
				GridSelectionModel<XExplorerModelData> selection = xExplorerResultPanel.getGrid().getSelectionModel();
				XExplorerModelData selected = selection.getSelectedItem();
				String resourceID = selected.getResourceID();
				
				try {
					XServiceEntry.getInstance().findAllXDescriptorByXResourceID(Long.valueOf(resourceID), new AsyncCallback<List<XDescriptorVO>>() {
						public void onSuccess(List<XDescriptorVO> vos) {
							ListBox datasetDimensionsList = xExplorerSearchPanel.getxExplorerDatasetFilter().getDatasetDimensionsList();
							clearList(datasetDimensionsList);
							for (XDescriptorVO vo : vos)
								if (!vo.getContentDescriptor().equalsIgnoreCase("quantity"))
									datasetDimensionsList.addItem(vo.getHeader(), String.valueOf(vo.getId()));
							
							XExplorerDatasetFilter xExplorerDatasetFilter = xExplorerSearchPanel.getxExplorerDatasetFilter();
							updateDescriptorMap(vos, xExplorerDatasetFilter);
						}
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (NumberFormatException e) {
					FenixAlert.error("ERROR", e.getMessage());
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
				
				final XExplorerMetadataPanel xExplorerMetadataPanel = xExplorerSearchPanel.getxExplorerMetadataPanel();
				XExplorerMetadataController.updateMetadata(xExplorerMetadataPanel, selected);
				
				
				
				((ContentPanel)xExplorerSearchPanel.getSearchPanel().getItem(1)).expand();
			};
		};
	}
	
	private static void updateDescriptorMap(List<XDescriptorVO> descriptors, XExplorerDatasetFilter xExplorerDatasetFilter) {
		xExplorerDatasetFilter.getDescriptorMap().clear();
		for (XDescriptorVO vo : descriptors)
			xExplorerDatasetFilter.getDescriptorMap().put(vo.getHeader(), vo);
	}
	
	public static ChangeListener datasetDimensionChange(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel xExplorerResultPanel) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox list = (ListBox)sender;
				datasetDimensionChangeAgent(list, xExplorerSearchPanel, xExplorerResultPanel);
			}
		};
	}
	
	private static void datasetDimensionChangeAgent(final ListBox datasetDimensionList, final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel xExplorerResultPanel) {
		GridSelectionModel<XExplorerModelData> selection = xExplorerResultPanel.getGrid().getSelectionModel();
		XExplorerModelData selected = selection.getSelectedItem();
		String xResourceID = selected.getResourceID();
		String xDescriptorID = datasetDimensionList.getValue(datasetDimensionList.getSelectedIndex());
		try {
			XServiceEntry.getInstance().findAllXUniqueValuesByXResourceIDAndXDescriptorID(Long.valueOf(xResourceID), Long.valueOf(xDescriptorID), new AsyncCallback<List<XUniqueValueVO>>() {
				public void onSuccess(List<XUniqueValueVO> vos) {
					ListBox unselectedValuesList = xExplorerSearchPanel.getxExplorerDatasetFilter().getUnselectedValuesList();
					ListBox selectedValuesList = xExplorerSearchPanel.getxExplorerDatasetFilter().getSelectedValuesList();
					clearList(unselectedValuesList);
					clearList(selectedValuesList);
					for (XUniqueValueVO vo : vos) {
						if (!vo.getLabel().equals(""))
							unselectedValuesList.addItem(vo.getLabel(), vo.getCode());
						else
							unselectedValuesList.addItem(vo.getCode(), vo.getCode());
					}
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		} catch (NumberFormatException e) {
			FenixAlert.error("ERROR", e.getMessage());
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
}