package org.fao.fenix.web.modules.tinymcereport.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerServiceEntry;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.esoko.client.view.TinyMCEAlertWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.re.client.control.olap.CatalogueContextMenuOlapController;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.image.ResourceExplorerImage;
import org.fao.fenix.web.modules.re.client.view.olap.ResourceExplorerOlap;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportGalleryWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;
import org.fao.fenix.web.modules.tinymcereport.common.services.TinyMCEServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TinyMCEController {
	
	public static SelectionListener<MenuEvent> export(final TinyMCEReportWindow w, final String tinyMCEPanelID, final String format) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				exportAgent(w, tinyMCEPanelID, format);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> freeze(final String tinyMCEPanelID, final TinyMCEFreezerWindow fw) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				try {
					Long resourceID = fw.extractID();
					String resourceType = fw.extractResourceType();
					final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Apply changes.", BabelFish.print().pleaseWait());
					TinyMCEServiceEntry.getInstance().freeze(resourceID, resourceType, fw.getToDate().getValue(), new AsyncCallback<String>() {
						public void onSuccess(String html) {
							l.destroyLoadingBox();
							System.out.println("FREEZE HTML: " + html);
							String content = TinyMCENativeController.getContent(tinyMCEPanelID);
							content = content.replaceAll(fw.getHtml(), html);
							TinyMCENativeController.replaceAll(tinyMCEPanelID);
							TinyMCENativeController.setContent(tinyMCEPanelID, content);
							fw.getWindow().hide();
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable E2) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException E1) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	public static void exportAgent(TinyMCEReportWindow w, String tinyMCEPanelID, String format) {
		String html = TinyMCENativeController.getContent(tinyMCEPanelID);
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Exporting Report.", BabelFish.print().pleaseWait());
		try {
			TinyMCEServiceEntry.getInstance().export(html, format, new AsyncCallback<String>() {
				public void onSuccess(String filename) {
					l.destroyLoadingBox();
					Window.open("../exportObject/" + filename, "_blank", "status=no");
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
	
	public static SelectionListener<MenuEvent> saveAs(final TinyMCEReportWindow w, final String tinyMCEPanelID, final boolean isTemplate) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				boolean isEditable = (FenixUser.hasUserRole() || FenixUser.hasAdminRole()); 
				if (isEditable) {
					MESaveAs.prepopulateTinyMCEReport(w.getReportID(), isEditable, false, w, isTemplate);
				} else {
					FenixAlert.info(BabelFish.print().info(), "To Save a Resource, Please Sign In");
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> save(final TinyMCEReportWindow w, final String tinyMCEPanelID, final boolean isTemplate) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				saveAgent(w, tinyMCEPanelID, isTemplate);
			}
		};
	}
	
	public static void saveAgent(TinyMCEReportWindow w, String tinyMCEPanelID, boolean isTemplate) {
		boolean isEditable = (FenixUser.hasUserRole() || FenixUser.hasAdminRole()); 
		if (isEditable) {
			if (w.getReportID() == null) {
				MetadataEditorWindow mew = new MetadataEditorWindow();
				mew.build(false, true, false, MESaver.getSaveTinyMCEReportListener(mew, tinyMCEPanelID, w, isTemplate));
			} else {
				final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Updating report.", BabelFish.print().pleaseWait());
				try {
					TinyMCEServiceEntry.getInstance().save(w.getReportID(), TinyMCENativeController.getContent(tinyMCEPanelID), isTemplate, new AsyncCallback<Long>() {
						public void onSuccess(Long id) {
							l.destroyLoadingBox();
							FenixAlert.info(BabelFish.print().info(), "Report successfully updated.");
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
		} else {
			FenixAlert.info(BabelFish.print().info(), "To Save a Resource, Please Sign In");
		}
	}
	
	public static void doubleClick(String html, String tinyMCEPanelID) {
		String originalHTML = html;
		html = html.toLowerCase();
		if (html.startsWith("<img") || html.startsWith("<iframe") || html.startsWith("<div")) {
			String id = extractID(html);
			if (id.startsWith("chart")) {
				Long chartID = extractID(id, "chart_");
				System.out.println("html? " + html);
				
				String originalWidth = "";
				String originalHeight = "";
				
				// restore original width
				int a = "width=\"".length() + html.indexOf("width=\"");
				int b = html.indexOf("\"", a);
				if (a > -1 && b > -1) {
					originalWidth = html.substring(a, b);
				}
				
				// restore original height
				a = "height=\"".length() + html.indexOf("height=\"");
				b = html.indexOf("\"", a);
				System.out.println("HTML: " + html);
				System.out.println("A: " + a);
				System.out.println("B: " + b);
				if (a > -1 && b > -1) {
					originalHeight = html.substring(a, b);
					System.out.println("C: " + originalHeight);
				}
				
				editChart(chartID, tinyMCEPanelID, originalHTML, originalWidth, originalHeight); // TODO proper values
			} else if (id.startsWith("olap")) {
				Long olapID = extractID(id, "olap_");
				editOLAP(olapID, tinyMCEPanelID, originalHTML);
			} else if (id.startsWith("add_chart")) {
				addChartToTemplate(tinyMCEPanelID, originalHTML);
			} else if (id.startsWith("add_olap")) {
				addOLAPToTemplate(tinyMCEPanelID, originalHTML);
			}
		}
	}
	
	public static void rightClick(String html, String tinyMCEPanelID) {
		try {
			isLoggedIn();
			if (html.contains("CHART_") || html.contains("OLAP_")) {
				TinyMCEFreezerWindow f = new TinyMCEFreezerWindow(html, tinyMCEPanelID);
				f.build();
			}
		} catch (FenixGWTException e) {
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
		}
	}
	
	public static void addOLAPToTemplate(String tinyMCEPanelID, String originalHTML) {
		String content = TinyMCENativeController.getContent(tinyMCEPanelID);
		String w = getWidth(originalHTML);
		String h = getHeight(originalHTML);
		String mark = "<div id=\"" + Math.random() + "\" width=\"" + w + "\" height=\"" + h + "\" style=\"width: " + w + "; height: " + h + ";\">MARK</div>";
		content = content.replace(originalHTML, mark);
		TinyMCENativeController.replaceAll(tinyMCEPanelID);
		TinyMCENativeController.setContent(tinyMCEPanelID, content);
		new ResourceExplorerOlap(tinyMCEPanelID, mark, originalHTML);
	}
	
	public static ClickHandler addImage(final ImageImporterPanel p, final Long imageID) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				loadImage(p.getTinyMCEPanelID(), imageID);
			}
		};
	}
	
	public static void addChartToTemplate(String tinyMCEPanelID, String originalHTML) {
		String content = TinyMCENativeController.getContent(tinyMCEPanelID);
		String w = getWidth(originalHTML);
		String h = getHeight(originalHTML);
		String mark = "<div id=\"" + Math.random() + "\" width=\"" + w + "\" height=\"" + h + "\" style=\"width: " + w + "; height: " + h + ";\">MARK</div>";
		content = content.replace(originalHTML, mark);
		TinyMCENativeController.replaceAll(tinyMCEPanelID);
		TinyMCENativeController.setContent(tinyMCEPanelID, content);
		new ResourceExplorerChart(tinyMCEPanelID, mark, originalHTML);
	}
	
	public static String getWidth(String tag) {
		String w = "";
		if (tag.contains("width=\"")) {
			int start = tag.indexOf("width=\"");
			int end = tag.indexOf("\"", 1 + start + "width=\"".length());
			if ((start > -1) && (end > -1))
			w = tag.substring(start + "width=\"".length(), end);
		}
		return w;
	}
	
	public static String getHeight(String tag) {
		String w = "";
		if (tag.contains("height=\"")) {
			int start = tag.indexOf("height=\"");
			int end = tag.indexOf("\"", 1 + start + "height=\"".length());
			if ((start > -1) && (end > -1))
				w = tag.substring(start + "height=\"".length(), end);
		}
		System.out.println("h: " + w);
		return w;
	}
	
	public static boolean isLoggedIn() throws FenixGWTException {
		if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
			return true;
		} else {
			throw new FenixGWTException("To create a new resource, please sign in.");
		}
	}
	
	public static void editChart(Long chartID, String tinyMCEPanelID, String originalHTML, String originalWidth, String originalHeigth) {
		String content = TinyMCENativeController.getContent(tinyMCEPanelID);
		content = content.replace(originalHTML, "<div>MARK</div>");
		TinyMCENativeController.replaceAll(tinyMCEPanelID);
		TinyMCENativeController.setContent(tinyMCEPanelID, content);
		Map<Long, String> ids = new HashMap<Long, String>();
		ids.put(chartID, "Temporary Chart Title");
		ChartDesignerController.loadChart(ids, WhoCall.TINYMCE_EDIT, tinyMCEPanelID, originalWidth, originalHeigth);
	}
	
	public static void editOLAP(Long olapID, String tinyMCEPanelID, String originalHTML) {
		String content = TinyMCENativeController.getContent(tinyMCEPanelID);
		content = content.replace(originalHTML, "<div>MARK</div>");
		content = content.replace("/fenix-web/", "http://127.0.0.1:8080/fenix-web/");
		TinyMCENativeController.replaceAll(tinyMCEPanelID);
		TinyMCENativeController.setContent(tinyMCEPanelID, content);
		CatalogueContextMenuOlapController.open(olapID, "Temporary Chart Title", WhoCall.TINYMCE_EDIT, tinyMCEPanelID);
	}
	
	public static Long extractID(String tag, String prefix) {
		String id = "";
		int start = tag.indexOf(prefix);
		id = tag.substring(start + prefix.length());
		return Long.valueOf(id);
	}
	
	public static String extractID(String tag) {
		String id = "";
		int start = tag.indexOf("id=\"");
		int end = tag.indexOf("\"", start + "id=\"".length());
		id = tag.substring(start + "id=\"".length(), end);
		return id;
	}

	public static SelectionListener<ButtonEvent> preview(final String panelID) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				TinyMCENativeController.preview(panelID);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> loadTemplate(final String panelID) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				TinyMCENativeController.loadTemplate(panelID);
				new TinyMCEReportGalleryWindow(panelID).build();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> print(final String panelID) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				TinyMCENativeController.print(panelID);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> print(final String panelID, final TinyMCEAlertWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				TinyMCENativeController.print(panelID);
				w.getWindow().hide();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> infoPrint(final String panelID) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				TinyMCEAlertWindow w = new TinyMCEAlertWindow();
				w.build("By default, every browser disable the background colors when an user decides to print a web page. " +
						"Please follow these instructions if you have any background color to be printed in your report:" +
						"<br><br><b>Internet Explorer:</b><br>&nbsp;&nbsp;&nbsp;1) <i>Tools</i> Menu<br>" +
						"&nbsp;&nbsp;&nbsp;2) <i>Internet Options...</i> menu item<br>" +
						"&nbsp;&nbsp;&nbsp;3) <i>Advanced</i> tabe<br>" +
						"&nbsp;&nbsp;&nbsp;4) <i>Print background color and images</i> checkbox" +
						"<br><br><b>Firefox</b><br>" +
						"&nbsp;&nbsp;&nbsp;1) <i>File</i> menu<br>" +
						"&nbsp;&nbsp;&nbsp;2) <i>Page setup...</i> menu item<br>" +
						"&nbsp;&nbsp;&nbsp;3) <i>Print Backgorund (colors & images)</i> checkbox", TinyMCEController.print(panelID, w));
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> fullScreen(final String panelID) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				TinyMCENativeController.fullScreen(panelID);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> selectChart(final String panelID) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				new ResourceExplorerChart(panelID);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> selectImage(final String panelID) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				new ResourceExplorerImage(panelID);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> createNewImage(final String panelID) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				try {
					isLoggedIn();
					ImageImporterWindow w = new ImageImporterWindow(true);
					w.setTinyMCEPanelID(panelID);
					w.build();
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> selectOLAP(final String panelID) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				new ResourceExplorerOlap(panelID);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> createNewOLAP(final String panelID) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				try {
					isLoggedIn();
					new MTWindow(WhoCall.TINYMCE_ADD, panelID).build();
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> createNewChart(final String panelID) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				try {
					isLoggedIn();
					new ChartDesignerWindow(WhoCall.TINYMCE_ADD, panelID).build();
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	public static void loadChart(final String panelID, final Long chartID) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Chart.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().exportToWebsite(chartID, new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String, String> tagMap) {
					l.destroyLoadingBox();
					TinyMCENativeController.setContent(panelID, tagMap.get("IMG"));
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
	
	public static void addChartToTemplate(final String panelID, final Long chartID, final String markID) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Chart.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().exportToWebsite(chartID, new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String, String> tagMap) {
					l.destroyLoadingBox();
					String originalContent = TinyMCENativeController.getContent(panelID);
					String mark = markID;
					String w = getWidth(mark);
					String h = getHeight(mark);
					String img = tagMap.get("IMG");
					if (img.contains("width='")) {
						int start = img.indexOf("width='");
						int end = 1 + img.indexOf("'", 1 + start + "width='".length());
						String width = img.substring(start, end);
						img = img.replace(width, "width='" + w + "'");
					} else {
						img = img.replace("/>", "width='" + w + "' />");
					}
					if (img.contains("height='")) {
						int start = img.indexOf("height='");
						int end = 1 + img.indexOf("'", 1 + start + "height='".length());
						String height = img.substring(start, end);
						img = img.replace(height, "height='" + h + "'");
					} else {
						img = img.replace("/>", "height='" + h + "' />");
					}
					img = img.replace("/>", "style=\"width: " + w + "; height: " + h + ";\"/>");
					String tmp = originalContent.replace(markID, img);
					TinyMCENativeController.replaceAll(panelID);
					TinyMCENativeController.setContent(panelID, tmp);
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
	
	public static void addOLAPToTemplate(final String panelID, final Long olapID, final String markID) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Multidimensional Table.", BabelFish.print().pleaseWait());
		try {
			OlapServiceEntry.getInstance().getHTML(olapID, new AsyncCallback<String>() {
				public void onSuccess(String iframe) {
					l.destroyLoadingBox();
					String originalContent = TinyMCENativeController.getContent(panelID);
					System.out.println(panelID + " >>> originalContent\n\n" + originalContent + "\n\n");
					System.out.println("markID " + markID + " contains mark? " + originalContent.contains(markID));
					String mark = markID;
					String w = getWidth(mark);
					String h = getHeight(mark);
					String img = iframe;
					if (img.contains("width=\"")) {
						int start = img.indexOf("width=\"");
						int end = 1 + img.indexOf("\"", 1 + start + "width=\"".length());
						String width = img.substring(start, end);
						img = img.replace(width, "width=\"" + w + "\"");
					} else {
						img = img.replace("/>", "width=\"" + w + "\" />");
					}
					if (img.contains("height=\"")) {
						int start = img.indexOf("height=\"");
						int end = 1 + img.indexOf("\"", 1 + start + "height=\"".length());
						String height = img.substring(start, end);
						img = img.replace(height, "height=\"" + h + "\"");
					} else {
						img = img.replace("/>", "height=\"" + h + "\" />");
					}
					String tmp = originalContent.replace(markID, img);
					TinyMCENativeController.replaceAll(panelID);
					TinyMCENativeController.setContent(panelID, tmp);
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
	
	public static void reLoadChart(final String panelID, final Long chartID, final String originalContent, final String originalWidth, final String originalHeight) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Chart.", BabelFish.print().pleaseWait());
		try {
			ChartDesignerServiceEntry.getInstance().exportToWebsite(chartID, new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String, String> tagMap) {
					l.destroyLoadingBox();
					String tmp = originalContent.replace("<div>MARK</div>", tagMap.get("IMG"));
					
					// restore original width
					int a = tmp.indexOf("width='");
					int b = 1 + tmp.indexOf("'", a + "width='".length());
					if (a > -1 && b > -1) {
						String c = tmp.substring(a, b);
						tmp = tmp.replace(c, "width='" + originalWidth + "'");
					}
					
					// restore original height
					a = tmp.indexOf("height='");
					b = 1 + tmp.indexOf("'", a + "height='".length());
					if (a > -1 && b > -1) {
						String c = tmp.substring(a, b);
						tmp = tmp.replace(c, "height='" + originalHeight + "'");
					}
					
					// add CSS
					tmp = tmp.replace("/>", " style='height: " + originalHeight + "; width: " + originalWidth + "; ' />");
					
					TinyMCENativeController.replaceAll(panelID);
					TinyMCENativeController.setContent(panelID, tmp);
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
	
	public static void reLoadOLAP(final String panelID, final Long olapID, final String originalContent) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Multidimensional Table.", BabelFish.print().pleaseWait());
		try {
			OlapServiceEntry.getInstance().getHTML(olapID, new AsyncCallback<String>() {
				public void onSuccess(String html) {
					l.destroyLoadingBox();
					String tmp = originalContent.replace("<div>MARK</div>", html);
					TinyMCENativeController.replaceAll(panelID);
					TinyMCENativeController.setContent(panelID, tmp);
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
	
	public static void loadImage(final String panelID, final Long imageID) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Image.", BabelFish.print().pleaseWait());
		try {
			REServiceEntry.getInstance().getIMGTag(imageID, new AsyncCallback<String>() {
				public void onSuccess(String imgTag) {
					l.destroyLoadingBox();
					TinyMCENativeController.setContent(panelID, imgTag);
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
	
	public static void loadOLAP(final String panelID, final Long olapID) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Multidimensional Table.", BabelFish.print().pleaseWait());
		try {
			OlapServiceEntry.getInstance().getHTML(olapID, new AsyncCallback<String>() {
				public void onSuccess(String html) {
					l.destroyLoadingBox();
//					System.out.println("olapID? " + olapID);
//					System.out.println(html);
					TinyMCENativeController.setContent(panelID, html);
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
	
	public static void close(final TinyMCEReportWindow w) {
		ToolButton tb = getCloseButton(w);
		tb.removeAllListeners();
		tb.addSelectionListener(new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				if (w.getReportID() == null) {
					String html = TinyMCENativeController.getContent(w.getTinyMCEPanel().getCode());
					deleteGhostResources(html);
				}
				w.getWindow().hide();
			};
		});
	}
	
	public static void loadReport(final TinyMCEReportWindow w) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Report.", BabelFish.print().pleaseWait());
		try {
			TinyMCEServiceEntry.getInstance().load(w.getReportID(), new AsyncCallback<String>() {
				public void onSuccess(String html) {
					l.destroyLoadingBox();
					prepare(w, html);
//					TinyMCENativeController.setContent(w.getTinyMCEPanel().getCode(), html);
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
	
	private static void prepare(final TinyMCEReportWindow w, final String html) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Clean Up the Report Board.", BabelFish.print().loading());
		w.getWindow().disable();
		Timer t = new Timer() {
			public void run() {
				TinyMCENativeController.replaceAll(w.getTinyMCEPanel().getCode());
				TinyMCENativeController.cleanup(w.getTinyMCEPanel().getCode());
				TinyMCENativeController.setContent(w.getTinyMCEPanel().getCode(), html);
				l.destroyLoadingBox();
				w.getWindow().enable();
			}
		};
		t.schedule(2000);
	}
	
	public static ToolButton getCloseButton(TinyMCEReportWindow w) {
		for (int i = 0 ; i < Integer.MAX_VALUE ; i++) {
			if (((ToolButton)w.getWindow().getHeader().getTool(i)).getStyleName().contains("x-tool-close")) {
				return (ToolButton)w.getWindow().getHeader().getTool(i);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void deleteGhostResources(String html) {
		List<Long> chartIDs = collectGhostChartIDs(html.toLowerCase());
		List<Long> olapIDs = collectGhostOLAPIDs(html.toLowerCase());
		Map<String, List<Long>> resourceTypeIDsMap = new HashMap<String, List<Long>>();
		resourceTypeIDsMap.put("CHART", chartIDs);
		resourceTypeIDsMap.put("OLAP", olapIDs);
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Deleting Unused Resources.", BabelFish.print().pleaseWait());
		try {
			TinyMCEServiceEntry.getInstance().deleteGhostResources(resourceTypeIDsMap, new AsyncCallback() {
				public void onSuccess(Object result) {
					l.destroyLoadingBox();
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
	
	public static List<Long> collectGhostChartIDs(String html) {
		List<String> tags = extractTags(html, "<img", ">");
		List<Long> ids = new ArrayList<Long>();
		for (String tag : tags) {
			try {
				Long id = extractChartID(tag);
				ids.add(id);
			} catch (FenixGWTException e) {
				GWT.log(e.getMessage());
			}
		}
		return ids;
	}

	public static List<Long> collectGhostOLAPIDs(String html) {
		List<String> tags = extractTags(html, "<iframe", ">");
		List<Long> ids = new ArrayList<Long>();
		for (String tag : tags) {
			try {
				Long id = extractOLAPID(tag);
				ids.add(id);
			} catch (FenixGWTException e) {
				GWT.log(e.getMessage());
			}
		}
		return ids;
	}
	
	public static Long extractChartID(String tag) throws FenixGWTException {
		try {
			Long id = null;
			int start = tag.indexOf("id=\"chart_") + "id=\"chart_".length();
			int end =   tag.indexOf("\"", start);
			if (start > -1 && end > -1)
				id = Long.valueOf(tag.substring(start, end));
			return id;
		} catch (NumberFormatException e) {
			throw new FenixGWTException("NumberFormatException");
		}
	}
	
	public static Long extractOLAPID(String tag) throws FenixGWTException {
		try {
			Long id = null;
			int start = tag.indexOf("id=\"olap_") + "id=\"olap_".length();
			int end =   tag.indexOf("\"", start);
			if (start > -1 && end > -1)
				id = Long.valueOf(tag.substring(start, end));
			return id;
		} catch (NumberFormatException e) {
			throw new FenixGWTException("NumberFormatException");
		}
	}
	
	public static List<String> extractTags(String html, String prefix, String suffix) {
		List<String> tags = new ArrayList<String>();
		for (int i = 0 ; i < html.length() ; i++) {
			int start = html.indexOf(prefix);
			int end   = 1 + html.indexOf(suffix, start + prefix.length());
			if (start > -1 && end > -1) {
				String tag = html.substring(start, end);
				if (!tags.contains(tag))
					tags.add(tag);
				i += end;
			}
		}
		return tags;
	}
	
}
