package org.fao.fenix.web.modules.communication.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.communication.client.view.AddNodeWindow;
import org.fao.fenix.web.modules.communication.client.view.CommunicationModuleParametersPanel;
import org.fao.fenix.web.modules.communication.client.view.CommunicationModuleWindow;
import org.fao.fenix.web.modules.communication.client.view.CommunicationTable;
import org.fao.fenix.web.modules.communication.client.view.CommunicationTablePanel;
import org.fao.fenix.web.modules.communication.client.view.LocalTable;
import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixConfirm;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TableEvent;
import com.extjs.gxt.ui.client.event.TableListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.extjs.gxt.ui.client.widget.treetable.TreeTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CommunicationController {

	public static ResourceExplorer localResourceExplorer;

	public static SelectionListener<ButtonEvent> getSearchRemoteListener(final CommunicationTable communicationTable, final ContentPanel remoteSearchPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				ListBox typeList = (ListBox) ((HorizontalPanel) remoteSearchPanel.getData("type")).getData("type");
				TextBox titleBox = (TextBox) ((HorizontalPanel) remoteSearchPanel.getData(BabelFish.print().title())).getData(BabelFish.print().title());
				TextBox hostLabelBox = (TextBox) ((HorizontalPanel) remoteSearchPanel.getData(BabelFish.print().hostLabel())).getData(BabelFish.print().hostLabel());
				TextBox groupBox = (TextBox) ((HorizontalPanel) remoteSearchPanel.getData(BabelFish.print().group())).getData(BabelFish.print().group());
				CommunicationResourceVo vo = new CommunicationResourceVo();
				vo.setTitle(titleBox.getText());
				vo.setHostLabel(hostLabelBox.getText());
				vo.setOGroup(groupBox.getText());
				vo.setType(typeList.getValue(typeList.getSelectedIndex()));
				try {
					CommunicationServiceEntry.getInstance().searchRemote(vo, new AsyncCallback<List<CommunicationResourceVo>>() {
						public void onSuccess(List<CommunicationResourceVo> vos) {
							communicationTable.getTable().removeAll();
							if (vos.size() > 0)
								communicationTable.build(communicationTable.getTable(), vos);
							else
								FenixAlert.info(BabelFish.print().info(), BabelFish.print().searchProducedNoResults());
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

	public static SelectionListener<ButtonEvent> getSearchLocalListener(final LocalTable localTable, final ContentPanel localSearchPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				ListBox typeList = (ListBox) ((HorizontalPanel) localSearchPanel.getData("type")).getData("type");
				TextBox titleBox = (TextBox) ((HorizontalPanel) localSearchPanel.getData(BabelFish.print().title())).getData(BabelFish.print().title());
				CommunicationResourceVo vo = new CommunicationResourceVo();
				vo.setTitle(titleBox.getText());
				vo.setType(typeList.getValue(typeList.getSelectedIndex()));
				try {
					CommunicationServiceEntry.getInstance().searchLocal(vo, new AsyncCallback<List<CommunicationResourceVo>>() {
						public void onSuccess(List<CommunicationResourceVo> vos) {
							localTable.getTable().removeAll();
							if (vos.size() > 0)
								localTable.build(localTable.getTable(), vos);
							else
								FenixAlert.info(BabelFish.print().info(), BabelFish.print().searchProducedNoResults());
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

	public static SelectionListener<ButtonEvent> getClearRemoteListener(final CommunicationTable communicationTable) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				communicationTable.getTable().removeAll();
			}
		};
	}

	public static SelectionListener<ButtonEvent> getClearLocalListener(final LocalTable localTable) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				localTable.getTable().removeAll();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static TableListener getLocalDoubleClickListener(final Table table) {
		return new TableListener() {
			public void tableCellDoubleClick(TableEvent te) {
				final LoadingWindow loading = new LoadingWindow();
				for (TableItem item : table.getSelectedItems()) {
					String resourceId = (String) item.getData("resourceId");
					String type = (String) item.getData("type");
					try {
						if (type.equalsIgnoreCase("Dataset")) {
							CommunicationServiceEntry.getInstance().shareDataset(Long.parseLong(resourceId), new AsyncCallback() {
								public void onSuccess(Object result) {
									loading.destroy();
									FenixAlert.info(BabelFish.print().info(), BabelFish.print().resourceHasBeenShared());
								}

								public void onFailure(Throwable caught) {
									loading.destroy();
									FenixAlert.error("RPC Failed", caught.getMessage());
								}
							});
						}
					} catch (NumberFormatException e) {
						FenixAlert.alert(BabelFish.print().info(), e.getMessage());
					} catch (FenixGWTException e) {
						FenixAlert.alert(BabelFish.print().info(), e.getMessage());
					}
				}
				loading.destroy();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static TableListener getRemoteDoubleClickListener(final Table table) {
		return new TableListener() {
			public void tableCellDoubleClick(TableEvent te) {
				final LoadingWindow loadingWindow = new LoadingWindow();
				for (TableItem item : table.getSelectedItems()) {
					String localId = (String) item.getData("localId");
					String remoteNodeUrl = (String) item.getData("remoteNodeUrl");
					String type = (String) item.getData("type");
					try {
						CommunicationServiceEntry.getInstance().downloadResource(remoteNodeUrl, localId, type, new AsyncCallback() {
							public void onSuccess(Object result) {
								loadingWindow.destroy();
								FenixAlert.info(BabelFish.print().info(), BabelFish.print().resourceHasBeenDownloaded());
							}

							public void onFailure(Throwable caught) {
								loadingWindow.destroy();
								FenixAlert.error("RPC Failed", caught.getMessage());
							}
						});
					} catch (FenixGWTException e) {
						e.printStackTrace();
						FenixAlert.alert(BabelFish.print().info(), "Download error: " + e.getMessage());
					}
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getShowNodeInfoListener(final Table table) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				for (TableItem item : table.getSelectedItems()) {
					String host = (String) item.getData("host");
					FenixAlert.info(BabelFish.print().info(), BabelFish.print().host() + ": " + host);
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> getAddNodeListener() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				new AddNodeWindow().build();
			}
		};
	}

	public static SelectionListener<ButtonEvent> getAddNodeListener(final VerticalPanel fieldsPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				try {
					HorizontalPanel hostPanel = (HorizontalPanel) fieldsPanel.getData(BabelFish.print().host());
					final String host = ((TextBox) hostPanel.getData("value")).getText();
					if (!isValidUrl(host))
						throw new FenixGWTException(BabelFish.print().invalidUrl());
					HorizontalPanel hostLabelPanel = (HorizontalPanel) fieldsPanel.getData(BabelFish.print().hostLabel());
					final String hostLabel = ((TextBox) hostLabelPanel.getData("value")).getText();
					HorizontalPanel groupPanel = (HorizontalPanel) fieldsPanel.getData(BabelFish.print().group());
					final String group = ((TextBox) groupPanel.getData("value")).getText();
					if (!allFieldsAreRequired(host, hostLabel, group))
						throw new FenixGWTException(BabelFish.print().allFieldsAreRequired());
					CommunicationServiceEntry.getInstance().ping(host, new AsyncCallback<String>() {
						public void onSuccess(String pingResult) {
							if (Boolean.parseBoolean(pingResult)) {
								CommunicationResourceVo vo = createVo(host, hostLabel, group);
								try {
									CommunicationServiceEntry.getInstance().saveCommunicationResourceVo(vo, new AsyncCallback<Long>() {
										public void onSuccess(Long id) {
											FenixAlert.info(BabelFish.print().info(), BabelFish.print().nodeHasBeenAdded());
										}

										public void onFailure(Throwable caught) {
											FenixAlert.error("RPC Failed", caught.getMessage());
										}
									});
								} catch (FenixGWTException e) {
									FenixAlert.alert(BabelFish.print().info(), e.getMessage());
								}
							} else {
								new FenixConfirm(BabelFish.print().thisNodeSeemsToBeOffAreYouSureYouWantToAddIt(), getConfirmAddNodeListener(host, hostLabel, group));
							}
						}

						public void onFailure(Throwable caught) {
							FenixAlert.error("RPC Failed", caught.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
				}
			}
		};
	}

	public static boolean allFieldsAreRequired(String host, String hostLabel, String group) {
		if (host == null || hostLabel == null || group == null)
			return false;
		else if (host.equals("") || hostLabel.equals("") || group.equals(""))
			return false;
		return true;
	}

	public static boolean isValidUrl(String url) {
		if (!url.startsWith("http://"))
			return false;
		else if (url.length() < 27)
			return false;
		return true;
	}

	public static SelectionListener<ButtonEvent> getConfirmAddNodeListener(final String host, final String hostLabel, final String group) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				CommunicationResourceVo vo = createVo(host, hostLabel, group);
				try {
					CommunicationServiceEntry.getInstance().saveCommunicationResourceVo(vo, new AsyncCallback<Long>() {
						public void onSuccess(Long id) {
							FenixAlert.info(BabelFish.print().info(), "Node has been added with id " + id);
						}

						public void onFailure(Throwable caught) {
							FenixAlert.error("RPC Failed", caught.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static CommunicationResourceVo createVo(String host, String hostLabel, String group) {
		CommunicationResourceVo vo = new CommunicationResourceVo();
		vo.setHost(host);
		vo.setHostLabel(hostLabel);
		vo.setOGroup(group);
		vo.setType("BusinessCard");
		vo.setTitle(hostLabel);
		return vo;
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> getShareListener(final Table table) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final LoadingWindow loadingWindow = new LoadingWindow();
				loadingWindow.create(BabelFish.print().sharingResource());
				for (TableItem item : table.getSelectedItems()) {
					final String resourceId = (String) item.getData("resourceId");
					final String type = (String) item.getData("type");
					loadingWindow.destroy();
					final LoadingWindow loadingWindow2 = new LoadingWindow();
					loadingWindow2.create(BabelFish.print().sharingResourceWithId() + " " + resourceId);
					try {
						CommunicationServiceEntry.getInstance().getActivePeerListVo(new AsyncCallback<List<CommunicationResourceVo>>() {
							public void onSuccess(List<CommunicationResourceVo> vos) {
								for (final CommunicationResourceVo vo : vos) {
									loadingWindow2.destroy();
									final LoadingWindow loadingWindow3 = new LoadingWindow();
									loadingWindow3.create(BabelFish.print().sharingToPeer() + " " + vo.getHostLabel());
									try {
										if (type.equalsIgnoreCase("Dataset")) {
											CommunicationServiceEntry.getInstance().shareDataset(Long.parseLong(resourceId), vo.getHost(), new AsyncCallback() {
												public void onSuccess(Object result) {
													loadingWindow3.destroy();
													FenixAlert.info(BabelFish.print().info(), BabelFish.print().resourceHasBeenShared());
												}

												public void onFailure(Throwable caught) {
													loadingWindow3.destroy();
													FenixAlert.error("RPC Failed", caught.getMessage());
												}
											});
										} else if (type.equalsIgnoreCase("TextView")) {
											CommunicationServiceEntry.getInstance().shareText(Long.parseLong(resourceId), vo.getHost(), new AsyncCallback() {
												public void onSuccess(Object result) {
													loadingWindow3.destroy();
													FenixAlert.info(BabelFish.print().info(), BabelFish.print().resourceHasBeenShared());
												}

												public void onFailure(Throwable caught) {
													loadingWindow3.destroy();
													FenixAlert.error("RPC Failed", caught.getMessage());
												}
											});
										}
									} catch (NumberFormatException e) {
										FenixAlert.alert(BabelFish.print().info(), e.getMessage());
									} catch (FenixGWTException e) {
										FenixAlert.alert(BabelFish.print().info(), e.getMessage());
									}
								}
							}

							public void onFailure(Throwable caught) {
								loadingWindow2.destroy();
								FenixAlert.error("RPC Failed", caught.getMessage());
							}
						});
					} catch (FenixGWTException e) {
						FenixAlert.alert(BabelFish.print().info(), e.getMessage());
					}
				}
				loadingWindow.destroy();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> getDownloadListener(final Table table, final CommunicationModuleParametersPanel communicationModuleParametersPanel, final CommunicationModuleWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final LoadingWindow loading = new LoadingWindow();
				for (TableItem item : table.getSelectedItems()) {
					String localId = (String) item.getData("localId");
					String remoteNodeUrl = (String) item.getData("remoteNodeUrl");
					String hostLabel = (String) item.getData("hostLabel");
					String type = (String) item.getData("type");
					loading.create(BabelFish.print().downloadingFromNode() + " " + hostLabel);
					try {
						CommunicationServiceEntry.getInstance().downloadResource(remoteNodeUrl, localId, type, new AsyncCallback() {
							public void onSuccess(Object result) {
								loading.destroy();
								afterDownload(communicationModuleParametersPanel, window);
							}

							public void onFailure(Throwable caught) {
								loading.destroy();
								FenixAlert.error("RPC Failed", caught.getMessage());
							}
						});
					} catch (FenixGWTException e) {
						FenixAlert.alert(BabelFish.print().info(), e.getMessage());
					}
				}
			}
		};
	}

	private static void afterDownload(CommunicationModuleParametersPanel communicationModuleParametersPanel, final CommunicationModuleWindow window) {
		ContentPanel controlsPanel = communicationModuleParametersPanel.getControlsPanel();
		RadioButton table = (RadioButton) controlsPanel.getData("table");
		RadioButton chart = (RadioButton) controlsPanel.getData("chart");
		final LoadingWindow loading = new LoadingWindow();
		if (table.isChecked()) {
			loading.create(BabelFish.print().loadingTable());
			REServiceEntry.getInstance().getLastSavedDataset(new AsyncCallback<List<String>>() {
				public void onSuccess(List<String> list) {
				 	new TableWindow().showAllData(Long.parseLong(list.get(1)), false, false, list.get(0));
					//new TableWindow().build(Long.parseLong(list.get(1)), false, false, list.get(0));
					
					loading.destroy();
					window.getNetworkMonitor().destroy();
					window.getWindow().close();
				}

				public void onFailure(Throwable caught) {
					loading.destroy();
					FenixAlert.error("RPC Failure", "getOpenAsTableListener @ DatasetUploaderController");
				}
			});
		} else if (chart.isChecked()) {
			loading.create(BabelFish.print().loadingChart());
			REServiceEntry.getInstance().getLastSavedDataset(new AsyncCallback<List<String>>() {
				public void onSuccess(List<String> list) {
					List<List<String>> datasetMap = new ArrayList<List<String>>();
					datasetMap.add(list);
					new ChartWizard(datasetMap);
					loading.destroy();
					window.getNetworkMonitor().destroy();
					window.getWindow().close();
				}

				public void onFailure(Throwable caught) {
					loading.destroy();
					FenixAlert.error("RPC Failure", "getOpenAsTableListener @ DatasetUploaderController");
				}
			});
		} else
			FenixAlert.info(BabelFish.print().info(), BabelFish.print().resourceHasBeenDownloaded());
		loading.destroy();
	}

	public static Listener<ComponentEvent> getTabListener(final TabPanel tabPanel, final CommunicationModuleParametersPanel parametersPanel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent event) {
				parametersPanel.updateButtons(tabPanel);
			}
		};
	}

	public static void updatePagerInfo(final CommunicationTablePanel communicationTablePanel, final int page) {
		try {
			CommunicationServiceEntry.getInstance().getRecordSize(new AsyncCallback<Integer>() {
				public void onSuccess(Integer result) {
					HTML info = communicationTablePanel.getCommunicationModulePager().getPageInfo();
					int pages = result / TableConstants.ITEMS_PER_PAGE;
					if ((result % TableConstants.ITEMS_PER_PAGE) != 0)
						pages++;
					info.setHTML(BabelFish.print().page() + " " + page + " / " + pages);
					communicationTablePanel.getCommunicationModulePager().setActualPage(page);
					communicationTablePanel.getCommunicationModulePager().setTotalRows(result);
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "");
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.alert(BabelFish.print().info(), e.getMessage());
		}
	}

	public static SelectionListener<ButtonEvent> next(final CommunicationTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * tablePanel.getCommunicationModulePager().getActualPage(); // +
				// 1
				// ;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > tablePanel.getCommunicationModulePager().getTotalRows())
					fromIndex = tablePanel.getCommunicationModulePager().getTotalRows() - 1;
				tablePanel.getCommunicationTable().getTable().removeAll();
				tablePanel.getCommunicationTable().build(tablePanel.getCommunicationTable().getTable(), fromIndex, toIndex);
				int newPage = 1 + tablePanel.getCommunicationModulePager().getActualPage();
				if ((fromIndex + toIndex) > tablePanel.getCommunicationModulePager().getTotalRows())
					newPage = 1 + tablePanel.getCommunicationModulePager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> previous(final CommunicationTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (tablePanel.getCommunicationModulePager().getActualPage() - 2);
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if (fromIndex <= 0)
					fromIndex = 0;
				tablePanel.getCommunicationTable().getTable().removeAll();
				tablePanel.getCommunicationTable().build(tablePanel.getCommunicationTable().getTable(), fromIndex, toIndex);
				int newPage = tablePanel.getCommunicationModulePager().getActualPage() - 1;
				if ((TableConstants.ITEMS_PER_PAGE * (tablePanel.getCommunicationModulePager().getActualPage() - 1)) <= 0)
					newPage = 1;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> start(final CommunicationTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = 0;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				tablePanel.getCommunicationTable().getTable().removeAll();
				tablePanel.getCommunicationTable().build(tablePanel.getCommunicationTable().getTable(), fromIndex, toIndex);
				updatePagerInfo(tablePanel, 1);
			};
		};
	}

	public static SelectionListener<ButtonEvent> end(final CommunicationTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = tablePanel.getCommunicationModulePager().getTotalRows() - 1;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE - 1;
				tablePanel.getCommunicationTable().getTable().removeAll();
				tablePanel.getCommunicationTable().build(tablePanel.getCommunicationTable().getTable(), fromIndex, toIndex);
				int newPage = 1 + tablePanel.getCommunicationModulePager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> goToPage(final CommunicationTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer page = Integer.parseInt(tablePanel.getCommunicationModulePager().getPageBox().getText());
				if (page <= 0)
					page = 1;
				Integer fromIndex = (page * TableConstants.ITEMS_PER_PAGE) - 1;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > tablePanel.getCommunicationModulePager().getTotalRows())
					fromIndex = tablePanel.getCommunicationModulePager().getTotalRows() - 1;
				if (fromIndex < 0)
					fromIndex = 0;
				tablePanel.getCommunicationTable().getTable().removeAll();
				tablePanel.getCommunicationTable().build(tablePanel.getCommunicationTable().getTable(), fromIndex, toIndex);
				int newPage = page;
				if ((fromIndex + toIndex) > tablePanel.getCommunicationModulePager().getTotalRows())
					newPage = 1 + tablePanel.getCommunicationModulePager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ComponentEvent> shareListener(final TreeTable table) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
				for (TreeItem item : table.getSelectedItems()) {
					ResourceChildModel model = (ResourceChildModel) item.getModel();
					Long resourceId = Long.valueOf(model.getId());
					FenixAlert.info("Share", "ID: " + resourceId);
				}
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> setCommunicationModuleParameters(final TextBox host, final TextBox group) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				try {
					CommunicationServiceEntry.getInstance().setCommunicationModuleParameters(host.getText(), group.getText(), new AsyncCallback() {
						public void onSuccess(Object result) {
							FenixAlert.info(BabelFish.print().info(), BabelFish.print().parametersSet());
						}

						public void onFailure(Throwable caught) {
							FenixAlert.alert("RPC Failed", "setCommunicationModuleParameters @ CommunicationController");
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<ComponentEvent> downloadListener(final TreeTable table) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
				for (TreeItem item : table.getSelectedItems()) {
					ResourceChildModel model = (ResourceChildModel) item.getModel();
					Long resourceId = Long.valueOf(model.getId());
					FenixAlert.info("Download", "ID: " + resourceId);
				}
			}
		};
	}

	public static ChangeListener scopeListener(final ResourceExplorer resourceExplorer) {
		localResourceExplorer = resourceExplorer;
		return new ChangeListener() {
			public void onChange(Widget widget) {
				localResourceExplorer.getSearchButtons().getFenixSearchParameters().setScope(((ListBox) widget).getItemText(((ListBox) widget).getSelectedIndex()));
			}
		};
	}

}
