package org.fao.fenix.web.modules.birt.client.control.report;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ReportViewerController {

	public static SelectionListener<ButtonEvent> addComment(final ReportViewer reportViewer,final TextEditor textEditor) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("addComment");
				textEditor.getWindow().close();
				BirtServiceEntry.getInstance().addTextToReport(reportViewer.getRptDesign(),textEditor.getTextArea().getHTML(), new AsyncCallback<List<String>>() {
							public void onSuccess(List<String> result) {
								System.out.println("item.setItemId(result.get(1)); " + result.get(1));
								System.out.println("item.setItemId(result.get(2)); " + result.get(2));
								DataListItem item = new DataListItem();
								item.setIconStyle("noteIcon");
								item.setItemId(result.get(1));
								item.setText("Comment");
								reportViewer.getSideBar().getListResource().add(item);
								reportViewer.getReport().setHTML(result.get(2));
							}

							public void onFailure(Throwable caught) {
								Info.display("addTextToReport", caught.getLocalizedMessage());
							}

						});
			}
		};
	}
	
	public static SelectionListener<MenuEvent> modifyComment(final ReportViewer reportViewer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				/** TODO: get text to send **/
				final DataListItem item = reportViewer.getSideBar().getListResource().getSelectedItem();
				if ( item.getText().equalsIgnoreCase("comment")){	
					BirtServiceEntry.getInstance().getCommentText(reportViewer.getRptDesign(), item.getItemId(), new AsyncCallback<String>() {
						public void onSuccess(String result) {
	
							new TextEditor(reportViewer, result, item).build();
						}

						public void onFailure(Throwable caught) {
							Info.display("changeModifiesComment", caught.getLocalizedMessage());
						}

					});
				
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> changeModifiesComment(final ReportViewer reportViewer, final TextEditor textEditor, final DataListItem comment) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("changeModifiesComment");
				textEditor.getWindow().close();
				/** TODO: check if is a comment **/
				BirtServiceEntry.getInstance().changeCommentText(reportViewer.getRptDesign(), comment.getItemId(), textEditor.getTextArea().getHTML(), new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> result) {
						System.out.println("bellas");
						System.out.println("item.setItemId(result.get(1)); " + result.get(1));
						System.out.println("item.setItemId(result.get(2)); " + result.get(2));
						comment.setItemId(result.get(1));
			

						reportViewer.getReport().setHTML(result.get(2));
					}

					public void onFailure(Throwable caught) {
						Info.display("changeModifiesComment", caught.getLocalizedMessage());
					}

				});
			}
		};
	}

	public static SelectionListener<MenuEvent> addSeparator(final ReportViewer reportViewer, final String separator) {
		return new SelectionListener<MenuEvent>() {
			@SuppressWarnings({ "deprecation", "deprecation" })
			public void componentSelected(MenuEvent ce) {
				BirtServiceEntry.getInstance().separatorObjectByReport(reportViewer.getRptDesign(), separator,
						reportViewer.getSideBar().getListResource().getSelectedItem().getItemId(),
						new AsyncCallback<List<String>>() {

							public void onSuccess(List<String> result) {
								int index = reportViewer.getSideBar().getListResource().indexOf(
										reportViewer.getSideBar().getListResource().getSelectedItem()) + 1;

								DataListItem item = new DataListItem();
								// item.setIconStyle("noteIcon");
								item.setItemId(result.get(0));
								if (separator.equals("br")) {
									item.setText(BabelFish.print().space());
								} else if (separator.equals("hr")) {
									item.setText(BabelFish.print().horizontalBar());
								}

								reportViewer.getSideBar().getListResource().insert(item, index);
								reportViewer.getReport().setHTML(result.get(1));
							}

							public void onFailure(Throwable caught) {
								Info.display("addSeparator", caught.getLocalizedMessage());
							}

						});
			}
		};
	}

	public static SelectionListener<MenuEvent> deleteObject(final ReportViewer reportViewer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				final DataListItem item = reportViewer.getSideBar().getListResource().getSelectedItem();
				BirtServiceEntry.getInstance().removeObjectByReport(reportViewer.getRptDesign(), item.getItemId(), new AsyncCallback<String>() {

							public void onSuccess(String result) {
								reportViewer.getReport().setHTML(result);
								if (item.getIconStyle() != null) {
									if (item.getIconStyle().equals("mapIcon")) {
										reportViewer.getSideBar().deleteResource("map", item.getItemId());
									} else if (item.getIconStyle().equals("chartIcon")) {
										reportViewer.getSideBar().deleteResource("chart", item.getItemId());
									} else if (item.getIconStyle().equals("tableIcon")) {
										reportViewer.getSideBar().deleteResource("table", item.getItemId());
									} else if (item.getIconStyle().equals("textResourceIcon")) {
										reportViewer.getSideBar().deleteResource("text", item.getItemId());
									}
								}
								reportViewer.getSideBar().getListResource().remove(item);
							}

							public void onFailure(Throwable caught) {
								Info.display("removeObjectByReport", caught.getLocalizedMessage());
							}
						});
			}
		};
	}

	public static SelectionListener<MenuEvent> moveObject(final ReportViewer reportViewer, final String upDown) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				final DataListItem item = reportViewer.getSideBar().getListResource().getSelectedItem();
				BirtServiceEntry.getInstance().moveObjectByReport(reportViewer.getRptDesign(), upDown, item.getItemId(), new AsyncCallback<String>() {

							public void onSuccess(String result) {
								reportViewer.getReport().setHTML(result);
								if (upDown.equals("down")) {
									reportViewer.getSideBar().getListResource().moveSelectedDown();
								} else {
									reportViewer.getSideBar().getListResource().moveSelectedUp();
								}
							}

							public void onFailure(Throwable caught) {
								Info.display("moveObjectByReport", caught.getLocalizedMessage());
							}
						});
			}
		};
	}

	public static SelectionListener<IconButtonEvent> saveAs(final ReportViewer reportViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				//if (reportViewer.getReportViewid() == 0L) {
					MetadataEditorWindow meWindow = new MetadataEditorWindow();
					meWindow.build(false, true, false, MESaver.getSaveReportListener(meWindow, reportViewer));
//				} else {
//					MESaveAs.prepopulateReport(reportViewer.getReportViewid(), true, false, reportViewer);
//				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> save(final ReportViewer reportViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {

				if (reportViewer.getReportViewid() == 0L) {
					MetadataEditorWindow meWindow = new MetadataEditorWindow();
					meWindow.build(false, true, false, MESaver.getSaveReportListener(meWindow, reportViewer));
				} else {
					BirtServiceEntry.getInstance().saveReport(reportViewer.getRptDesign(),
							reportViewer.getReportViewid(), reportViewer.getReportVo(), new AsyncCallback<Long>() {

								public void onSuccess(Long reportViewId) {
									Info.display("saveReport", "Saved");
								}

								public void onFailure(Throwable caught) {
									FenixAlert
											.error(BabelFish.print().error(),
													"The report update failed because you do not have update permission or there is a technical problem");
								}

							});
				}
			}
		};
	}
}
