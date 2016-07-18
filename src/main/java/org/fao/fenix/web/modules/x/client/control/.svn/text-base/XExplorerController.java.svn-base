package org.fao.fenix.web.modules.x.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.x.client.view.XExplorerResultPanel;
import org.fao.fenix.web.modules.x.client.view.XExplorerSearchPanel;
import org.fao.fenix.web.modules.x.client.view.XExplorerWindow;
import org.fao.fenix.web.modules.x.common.exception.XLayerException;
import org.fao.fenix.web.modules.x.common.services.XServiceEntry;
import org.fao.fenix.web.modules.x.common.vo.XDescriptorVO;
import org.fao.fenix.web.modules.x.common.vo.XExplorerModelData;
import org.fao.fenix.web.modules.x.common.vo.XExplorerSearchParametersVO;
import org.fao.fenix.web.modules.x.common.vo.XResourceVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class XExplorerController {

	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> close(final XExplorerWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> delete(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
				List<XExplorerModelData> selectedItems = selection.getSelectedItems();
				for (XExplorerModelData selected : selectedItems) {
					final String resourceID = selected.getResourceID();
					if (resourceID != null) {
						try {
							XServiceEntry.getInstance().delete(resourceID, new AsyncCallback() {
								public void onSuccess(Object result) {
									System.out.println(resourceID + " XResource deleted.");
									searchFunction(searchPanel, resultPanel);
								}

								public void onFailure(Throwable e) {
									System.out.println(resourceID + " XResource deletion error.");
									FenixAlert.error("ERROR", e.getMessage());
								}
							});
						} catch (FenixGWTException e) {
							FenixAlert.error("ERROR", e.getMessage());
						} finally {
							FenixAlert.info("SUCCESS", "Delete complete!");
						}
					}
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> download(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel resultPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
				XExplorerModelData selected = selection.getSelectedItem();
				String resourceType = selected.getResourceType();
				System.out.println("XResourceType ---> " + resourceType);
				if (resourceType.equalsIgnoreCase("dataset"))
					downloadDataset(xExplorerSearchPanel, resultPanel);
				else if (resourceType.equalsIgnoreCase("dbfeaturelayer"))
					downloadDBFeatureLayer(xExplorerSearchPanel, resultPanel);
				else if (resourceType.equalsIgnoreCase("shplayer")) 
					downloadShpFeatureLayer(xExplorerSearchPanel, resultPanel);
				else if (resourceType.equalsIgnoreCase("rasterlayer")) 
					downloadRasterLayer(xExplorerSearchPanel, resultPanel); 
				else if (resourceType.equalsIgnoreCase("text"))
					downloadText(xExplorerSearchPanel, resultPanel);
			}
		};
	}
	
	private static void deleteChunks(String url, String localID, final String layerType, Long importedLayerID) {
		try {
			if ((url != null) && (localID != null)) {
				final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Deleting remote temporary files.", "Loading...");
				XServiceEntry.getInstance().deleteChunks(url, localID, importedLayerID, layerType, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			}
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	private static void deleteChunks(String url, String localID) {
		try {
			if ((url != null) && (localID != null)) {
				final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Deleting remote temporary files.", "Loading...");
				XServiceEntry.getInstance().deleteChunks(url, localID, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			}
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void downloadDBFeatureLayer(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel resultPanel) {
		GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
		XExplorerModelData selected = selection.getSelectedItem();
		final String url = selected.getUrl();
		final String localID = selected.getLocalID();
		try {
			if ((url != null) && (localID != null)) {
				final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Connecting to remote node to download required information.", "Loading...");
				XServiceEntry.getInstance().requestDBFeatureLayerChunkList(url, localID, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						loading.destroyLoadingBox();
						downloadChunks(localID, url, "dbFeatureLayer");
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			}
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	private static void downloadRasterLayer(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel resultPanel) {
		GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
		XExplorerModelData selected = selection.getSelectedItem();
		final String url = selected.getUrl();
		final String localID = selected.getLocalID();
		try {
			if ((url != null) && (localID != null)) {
				final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Connecting to remote node to download required information.", "Loading...");
				XServiceEntry.getInstance().requestRasterLayerChunkList(url, localID, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						loading.destroyLoadingBox();
						downloadChunks(localID, url, "rasterLayer");
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			}
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	private static void downloadShpFeatureLayer(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel resultPanel) {
		GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
		XExplorerModelData selected = selection.getSelectedItem();
		final String url = selected.getUrl();
		final String localID = selected.getLocalID();
		try {
			if ((url != null) && (localID != null)) {
				final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Connecting to remote node to download required information.", "Loading...");
				XServiceEntry.getInstance().requestShpFeatureLayerChunkList(url, localID, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						loading.destroyLoadingBox();
						downloadChunks(localID, url, "shpFeatureLayer");
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			}
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void downloadChunks(final String localResourceID, final String url, final String layerType) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Finding all required layer part(s).", "Loading...");
			XServiceEntry.getInstance().findAllChunks(localResourceID, url, new AsyncCallback<List<String>>() {
				public void onSuccess(List<String> result) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					requestAllChunks(url, result, localResourceID, layerType);
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void requestAllChunks(final String url, List<String> chunkPaths, final String localResourceID, final String layerType) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Downloading all required layer part(s).", "Loading...");
			XServiceEntry.getInstance().requestAllChunks(url, chunkPaths, localResourceID, new AsyncCallback<List<String>>() {
				public void onSuccess(List<String> result) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					mergeChunks(url, localResourceID, result, layerType);
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void mergeChunks(final String url, final String localID, List<String> chunkPaths, final String layerType) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Merging all required layer part(s).", "Loading...");
			XServiceEntry.getInstance().mergeChunks(chunkPaths, new AsyncCallback<String>() {
				public void onSuccess(String result) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					if (layerType.equalsIgnoreCase("dbFeatureLayer"))
						importDBFeatureLayer(url, localID, result);
					else if (layerType.equalsIgnoreCase("shpFeatureLayer"))
						importShpFeatureLayer(url, localID, result);
					else if (layerType.equalsIgnoreCase("rasterLayer"))
						importRasterLayer(url, localID, result);
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void importDBFeatureLayer(final String url, final String localID, String mergedFilePath) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Importing DBFeatureLayer", "Loading...");
			XServiceEntry.getInstance().importDBFeatureLayer(url, localID, mergedFilePath, new AsyncCallback<Long>() {
				public void onSuccess(Long result) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					saveDBFeatureLayerAsFenixResource(url, localID, result);
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}  catch (XLayerException e) {
			deleteChunks(url, localID);
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	private static void importShpFeatureLayer(final String url, final String localID, String mergedFilePath) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Importing ShpFeatureLayer", "Loading...");
			XServiceEntry.getInstance().importShpFeatureLayer(url, localID, mergedFilePath, new AsyncCallback<Long>() {
				public void onSuccess(Long importedLayerID) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					deleteChunks(url, localID, "shpFeatureLayer", importedLayerID);
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}  catch (XLayerException e) {
			deleteChunks(url, localID);
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	private static void importRasterLayer(final String url, final String localID, String mergedFilePath) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Importing ShpFeatureLayer", "Loading...");
			XServiceEntry.getInstance().importRasterLayer(url, localID, mergedFilePath, new AsyncCallback<Long>() {
				public void onSuccess(Long importedLayerID) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					deleteChunks(url, localID, "rasterLayer", importedLayerID);
				}

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		} catch (XLayerException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void saveDBFeatureLayerAsFenixResource(final String url, final String localID, final Long dbFeatureLayerID) {
		try {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Adding Layer to the catalogue.", "Loading...");
			MetadataServiceEntry.getInstance().buildDBFeatureLayerResourceVO(dbFeatureLayerID, new AsyncCallback<ResourceVO>() {
				public void onSuccess(ResourceVO result) {
					loading.destroyLoadingBox();
					loading.destroyLoadingBox();
					MESaver.saveImportedDBFeatureLayer(result, dbFeatureLayerID, "TEMP NAME");
					FenixAlert.info("INFO", "Layer has been succesfully imported!");
					deleteChunks(url, localID, "dbFeatureLayer", result.getResourceId());
				};

				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	private static void downloadDataset(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel resultPanel) {
		GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
		XExplorerModelData selected = selection.getSelectedItem();
		final String url = selected.getUrl();
		final String localID = selected.getLocalID();
		Map<Map<String, String>, Map<String, String>> filterMap = xExplorerSearchPanel.getxExplorerDatasetFilter().getFilterMap();
		Map<String, XDescriptorVO> descriptorMap = xExplorerSearchPanel.getxExplorerDatasetFilter().getDescriptorMap();
		if ((url != null) && (localID != null)) {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Connecting to remote node to download required information.", "Loading...");
			try {
				XServiceEntry.getInstance().requestDataset(url, localID, filterMap, descriptorMap, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						System.out.println("Dataset download succesfull!");
						FenixAlert.info("SUCCESS", result);
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						System.out.println("Error downloading dataset at " + url + " with remote ID " + localID);
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			} catch (FenixGWTException e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		}
	}
	
	private static void downloadText(final XExplorerSearchPanel xExplorerSearchPanel, final XExplorerResultPanel resultPanel) {
		GridSelectionModel<XExplorerModelData> selection = resultPanel.getGrid().getSelectionModel();
		XExplorerModelData selected = selection.getSelectedItem();
		final String url = selected.getUrl();
		final String localID = selected.getLocalID();
		if ((url != null) && (localID != null)) {
			final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "Connecting to remote node to download required information.", "Loading...");
			try {
				XServiceEntry.getInstance().requestText(url, localID, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
						FenixAlert.info("SUCCESS", result);
						loading.destroyLoadingBox();
					}
	
					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						System.out.println("Error downloading text at " + url + " with remote ID " + localID);
						FenixAlert.error("ERROR", e.getMessage());
						loading.destroyLoadingBox();
					}
				});
			} catch (FenixGWTException e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		}
	}

	public static SelectionListener<ButtonEvent> synchronize(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final String resourceType = getValueFromList(searchPanel.getDataTypeList());
				final String url = getValueFromList(searchPanel.getNodeList());
				int maxResults = Integer.parseInt(searchPanel.getMaxResultsPerSynchIteration().getValue());
				final LoadingWindow loading = new LoadingWindow("X", "FENIX is synchronizing the DB with required node. This operation may take several minutes.", "Please wait...");
				try {
					XServiceEntry.getInstance().requestUpdate(url, resourceType, maxResults, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							loading.destroyLoadingBox();
							searchFunction(searchPanel, resultPanel);
							FenixAlert.info("SUCCESS", result);
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							System.out.println("Error synchronizing " + url + " for " + resourceType);
							FenixAlert.error("ERROR", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> search(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("Calling search function...");
				searchFunction(searchPanel, resultPanel);
			}
		};
	}

	private static void searchFunction(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel) {
		XExplorerSearchParametersVO p = collectParameters(searchPanel, resultPanel);
		updateTable(resultPanel, p);
		resultPanel.getPager().getPageNumber().setValue(String.valueOf("1"));
	}

	public static void updateTable(final XExplorerResultPanel resultPanel, final XExplorerSearchParametersVO p) {
		try {
			XServiceEntry.getInstance().getSearchSize(p, new AsyncCallback<Integer>() {
				public void onSuccess(Integer totalResult) {
					resultPanel.getPager().setMaxResults(p.getMaxItemsPerPage());
					resultPanel.getPager().setTotalResults(totalResult);
					int totalPages = resultPanel.getPager().getTotalResults() / resultPanel.getPager().getMaxResults();
					if (resultPanel.getPager().getTotalResults() % resultPanel.getPager().getMaxResults() != 0)
						totalPages++;
					resultPanel.getPager().getTotalPages().setHTML("of " + String.valueOf(totalPages));
					try {
						XServiceEntry.getInstance().search(p, new AsyncCallback<List<XResourceVO>>() {
							public void onSuccess(List<XResourceVO> smallResult) {
								resultPanel.getStore().removeAll();
								for (XResourceVO vo : smallResult)
									resultPanel.getStore().add(vo2modelData(vo));
							};
							public void onFailure(Throwable e) {
								System.out.println("Error in updateTable");
								FenixAlert.error("ERROR", e.getMessage());
							}
						});
					} catch (FenixGWTException e) {
						FenixAlert.error("ERROR", e.getMessage());
					}
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	public static XExplorerModelData vo2modelData(XResourceVO vo) {
		XExplorerModelData m = new XExplorerModelData();
		m.setName(vo.getTitle());
		m.setDateLastUpdate(vo.getDateLastUpdate());
		m.setNode(vo.getUrlLabel());
		m.setRegion(vo.getRegion());
		m.setSource(vo.getSource());
		m.setUrl(vo.getUrl());
		m.setLocalID(vo.getLocalID());
		m.setResourceID(String.valueOf(vo.getResourceId()));
		m.setAbstractAbstract(vo.getAbstractAbstract());
		m.setCategory(vo.getCategories());
		m.setCode(vo.getCode());
		m.setEndDate(vo.getEndDate());
		m.setKeywords(vo.getKeywords());
		m.setStartDate(vo.getStartDate());
		m.setProvider(vo.getProvider());
		m.setDatasetType(vo.getDatasetType());
		m.setPeriodTypeCode(vo.getPeriodTypeCode());
		m.setResourceType(vo.getResourceType());
		return m;
	}

	public static XExplorerSearchParametersVO collectParameters(XExplorerSearchPanel panel, XExplorerResultPanel resultPanel) {
		XExplorerSearchParametersVO p = new XExplorerSearchParametersVO();
		for (int i = 0; i < panel.getCategoryList().getSelection().size(); i++)
			p.setCategory(panel.getCategoryList().getSelection().get(i).getCategoryValue());
		for (int i = 0; i < panel.getGaulList().getSelection().size(); i++)
			p.setGeographicArea(panel.getGaulList().getSelection().get(i).getGaulCode());
		p.setKeywords(panel.getKeywordsTextField().getValue());
		p.setOrderBy(getValueFromList(panel.getOrderByList()));
		p.setOrderDirection(getValueFromList(panel.getOrderDirectionList()));
		p.setResourceType(getValueFromList(panel.getDataTypeList()));
		p.setTitle(panel.getTitleTextField().getValue());
		p.setUrls(getSelectedURLs(panel.getNodeList()));
		p.setValues(panel.getValuesTextField().getValue());
		p.setFirstResultIndex(resultPanel.getPager().getFirstResultIndex());
		p.setMaxResults(resultPanel.getPager().getMaxResults());
		p.setMaxItemsPerPage(Integer.parseInt(panel.getMaxItemsPerPage().getValue()));
		return p;
	}

	private static List<String> getSelectedURLs(ListBox list) {
		List<String> nodes = new ArrayList<String>();
		if (list.getSelectedIndex() == 0) {
			for (int i = 1; i < list.getItemCount(); i++)
				nodes.add(list.getValue(i));
		} else {
			for (int i = 1; i < list.getItemCount(); i++)
				if (list.isItemSelected(i))
					nodes.add(list.getValue(i));
		}
		return nodes;
	}

	private static String getValueFromList(ListBox list) {
		try {
			return list.getValue(list.getSelectedIndex());
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

	public static void fillNodeList(final ListBox nodeList) {
		clearList(nodeList);
		nodeList.addItem("All Nodes", "all");
		try {
			XServiceEntry.getInstance().findAllNodes(new AsyncCallback<Map<String, String>>() {
				public void onSuccess(Map<String, String> result) {
					for (String key : result.keySet())
						nodeList.addItem(result.get(key), key);
				}
				public void onFailure(Throwable e) {
					System.out.println("Error in finding all the nodes.");
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}

	public static void clearList(ListBox list) {
		for (int i = list.getItemCount() - 1; i >= 0; i--)
			list.removeItem(i);
	}

	public static boolean listContains(ListBox list, String text, String code) {
		for (int i = list.getItemCount() - 1; i >= 0; i--)
			if (list.getItemText(i).equalsIgnoreCase(text) && list.getValue(i).equalsIgnoreCase(code))
				return true;
		return false;
	}

}