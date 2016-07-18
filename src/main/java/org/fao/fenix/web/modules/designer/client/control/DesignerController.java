package org.fao.fenix.web.modules.designer.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.utils.ColorPickerCaller;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.designer.client.view.DesignerBoxSettings;
import org.fao.fenix.web.modules.designer.client.view.DesignerBoxSettingsWindow;
import org.fao.fenix.web.modules.designer.client.view.DesignerGridPanel;
import org.fao.fenix.web.modules.designer.client.view.DesignerGridWindow;
import org.fao.fenix.web.modules.designer.client.view.DesignerTabItem;
import org.fao.fenix.web.modules.designer.client.view.DesignerTabPanel;
import org.fao.fenix.web.modules.designer.client.view.DesignerWindow;
import org.fao.fenix.web.modules.designer.common.vo.BoxVO;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;
import org.fao.fenix.web.modules.designer.common.vo.DesignerVO;
import org.fao.fenix.web.modules.designer.common.vo.PageVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.latex.common.services.LatexServiceEntry;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.re.client.control.image.CatalogueContextMenuImageController;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.image.ResourceExplorerImage;
import org.fao.fenix.web.modules.re.client.view.layer.ResourceExplorerLayer;
import org.fao.fenix.web.modules.re.client.view.map.ResourceExplorerMap;
import org.fao.fenix.web.modules.re.client.view.olap.ResourceExplorerOlap;
import org.fao.fenix.web.modules.re.client.view.tableview.ResourceExplorerTableView;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.text.client.view.viewer.TextViewer;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class DesignerController {

	private static List<Map<String, Integer>> pixels;

	// apparently the only way to remove the ClickHandlers from the CheckBox
	private static Map<CheckBox, HandlerRegistration> handlersMap = new HashMap<CheckBox, HandlerRegistration>();

	public static void mapBox(DesignerBox box) {
		Integer id = Random.nextInt();
		box.setBoxID(id);
	}

	public static void rgbBox(DesignerBox box) {
		String rgb = createRGB();
		box.setBoxRGB(rgb);
	}

	public static Listener<BaseEvent> tabItemSelect(final DesignerTabItem dti) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				dti.getDesignerGridWindow().getWindow().hide();
				dti.getDesignerGridWindow().getWindow().show();
			};
		};
	}
	
	public static Listener<BaseEvent> tabItemClose(final DesignerTabPanel dtp, final DesignerTabItem dti) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				dti.getDesignerGridWindow().getWindow().close();
				dtp.getTabItems().remove(dti);
			};
		};
	}

	public static ClickHandler boxRadioButton(final DesignerBox box, final List<DesignerBox> boxes, final DesignerGridWindow w) {
		return new ClickHandler() {
			public void onClick(ClickEvent e) {
				enableBoxes(false, boxes);
				enableBox(true, box);
				cleanGrid(w);
				addCheckBoxHandlers(w, box.getBoxID(), box.getBoxRGB());
			}
		};
	}

	public static void addCheckBoxHandlers(DesignerGridWindow w, Integer boxID, String rgb) {
		DesignerGridPanel p = w.getDesignerGridPanel();
		int end_x = DesignerGridPanel.PORTRAIT_COLS;
		int end_y = DesignerGridPanel.PORTRAIT_ROWS;
		if (w.getOrientation().equals("LANDSCAPE")) {
			end_x = DesignerGridPanel.LANDSCAPE_COLS;
			end_y = DesignerGridPanel.LANDSCAPE_ROWS;
		}
		for (int i = 0; i < end_x; i++) {
			for (int j = 0; j < end_y; j++) {
				CheckBox cb = (CheckBox) p.getPanel().getData(i + ":" + j);
				addCheckBoxHandler(w, cb, boxID, rgb);
			}
		}
	}

	public static void addCheckBoxHandler(DesignerGridWindow w, CheckBox cb, Integer boxID, String rgb) {
		HandlerRegistration h = handlersMap.get(cb);
		if (h == null) {
			h = cb.addClickHandler(pixel(w, cb, boxID, rgb));
			handlersMap.put(cb, h);
		} else {
			h.removeHandler();
			handlersMap.remove(h);
			h = cb.addClickHandler(pixel(w, cb, boxID, rgb));
			handlersMap.put(cb, h);
		}
	}

	public static void enableBoxes(boolean enabled, List<DesignerBox> boxes) {
		for (DesignerBox box : boxes)
			enableBox(enabled, box);
	}

	public static void enableBox(boolean enabled, DesignerBox box) {
		box.getResourceName().setEnabled(enabled);
		box.getResourceTypeList().setEnabled(enabled);
		box.getRe().setEnabled(enabled);
		box.getRemoveBox().setEnabled(enabled);
		box.getDeleteBox().setEnabled(enabled);
	}

	public static SelectionListener<IconButtonEvent> removeBox(final DesignerGridWindow w, final Integer boxID, final String rgb) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				removeBoxAgent(w, boxID, rgb);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> editResource(final DesignerBox designerBox) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ResourceType rt = ResourceType.valueOf(designerBox.getResourceTypeList().getValue(designerBox.getResourceTypeList().getSelectedIndex()));
				if (designerBox.getDesignerBoxSettings() == null)
					designerBox.setDesignerBoxSettings(new DesignerBoxSettings());
				DesignerBoxSettingsWindow w = new DesignerBoxSettingsWindow(designerBox.getDesignerBoxSettings());
				designerBox.setDesignerBoxSettingsWindow(w);
				switch (rt) {
					case IMAGE :
						CatalogueContextMenuImageController.open(Long.valueOf(designerBox.getResourceID()));
					break;
//					case TABLE :
//						new TableViewWindow().build(Long.valueOf(designerBox.getResourceID()), Long.valueOf(designerBox.getResourceID()), false, false);
//					break;
					case CHART :
						new ChartViewer(String.valueOf(designerBox.getResourceID()));
					break;
					case MAP :
						MapWindow mw = new MapWindow(String.valueOf(designerBox.getResourceID()), designerBox.getResourceName().getValue());
						mw.show();
					break;
					case TEXT :
						TextServiceEntry.getInstance().getText(designerBox.getResourceID(), new AsyncCallback<List<String>>() {
							public void onSuccess(final List<String> result) {
								new TextViewer(designerBox.getResourceID(), result).build(true);
							}
							public void onFailure(Throwable caught) {
								FenixAlert.alert("Error: RPC Failed", "TextService:getText() Failed");
							}
						});
					break;
				}
				
			}
		};
	}

	public static SelectionListener<IconButtonEvent> deleteBox(final DesignerTabItem tab, final DesignerBox designerBox) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				Integer boxID = designerBox.getBoxID();
				String rgb = designerBox.getBoxRGB();
				deSelectBox(tab.getDesignerGridWindow(), boxID, rgb);
				tab.getBoxWrapper().remove(designerBox.getBox());
				tab.getBoxWrapper().getLayout().layout();
				if (tab.getBoxes().isEmpty())
					tab.getDesignerGridWindow().getWindow().close();
				tab.getWrapper().getLayout().layout();
				if (tab.getBoxWrapper().getItemCount() == 0) {
					tab.getDesignerGridWindow().getDesignerGridPanel().setSelectedPixels(new ArrayList<String>());
					tab.getDesignerGridWindow().getDesignerGridPanel().getSelectedPixelsMap().clear();
					disableGrid(tab.getDesignerGridWindow(), boxID, rgb);
				}
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> designerBoxSettings(final DesignerBox designerBox) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ResourceType rt = ResourceType.valueOf(designerBox.getResourceTypeList().getValue(designerBox.getResourceTypeList().getSelectedIndex()));
				if (designerBox.getDesignerBoxSettings() == null)
					designerBox.setDesignerBoxSettings(new DesignerBoxSettings());
				DesignerBoxSettingsWindow w = new DesignerBoxSettingsWindow(designerBox.getDesignerBoxSettings());
				designerBox.setDesignerBoxSettingsWindow(w);
//				designerBox.getDesignerBoxSettingsWindow().build(false);
				switch (rt) {
					case TEXT :
						designerBox.getDesignerBoxSettingsWindow().build(false);
					break;
					case TABLE :
						designerBox.getDesignerBoxSettingsWindow().build(true);
					break;
					case OLAP :
						designerBox.getDesignerBoxSettingsWindow().build(true);
					break;
					default:
						designerBox.getDesignerBoxSettingsWindow().build(true);
					break;
				}
			}
		};
	}
	
	public static void deSelectBox(final DesignerGridWindow w, final Integer boxID, final String rgb) {
		int max_x = findMaxX(w.getDesignerGridPanel().getSelectedPixelsMap().get(boxID));
		int max_y = findMaxY(w.getDesignerGridPanel().getSelectedPixelsMap().get(boxID));
		int min_x = findMinX(w.getDesignerGridPanel().getSelectedPixelsMap().get(boxID));
		int min_y = findMinY(w.getDesignerGridPanel().getSelectedPixelsMap().get(boxID));
		List<String> boxSelectedPixels = w.getDesignerGridPanel().getSelectedPixelsMap().get(boxID);
		for (int i = min_x; i < 1 +max_x; i++) {
			for (int j = min_y; j < 1 +max_y; j++) {
				String coordinates = i + ":" + j;
				if (boxSelectedPixels.contains(coordinates)) {
					CheckBox cb = new CheckBox();
					cb.setValue(false);
					cb.setEnabled(true);
					cb.setTitle(i + ":" + j);
					addCheckBoxHandler(w, cb, boxID, rgb);
					w.getDesignerGridPanel().getGrid().remove(w.getDesignerGridPanel().getGrid().getWidget(j, i));
					w.getDesignerGridPanel().getGrid().setWidget(j, i, cb);
				}
			}
		}
		List<String> selectedPixels = w.getDesignerGridPanel().getSelectedPixels();
		for (int i = selectedPixels.size() - 1; i >= 0; i--)
			if (boxSelectedPixels.contains(selectedPixels.get(i)))
				selectedPixels.remove(selectedPixels.get(i));
		w.getDesignerGridPanel().getSelectedPixelsMap().remove(boxID);
	}

	public static void removeBoxAgent(final DesignerGridWindow w, final Integer boxID, final String rgb) {
		DesignerGridPanel p = w.getDesignerGridPanel();
		List<String> boxSelectedPixels = w.getDesignerGridPanel().getSelectedPixelsMap().get(boxID);
		if (boxSelectedPixels != null) {
			int end_x = DesignerGridPanel.PORTRAIT_COLS;
			int end_y = DesignerGridPanel.PORTRAIT_ROWS;
			if (w.getOrientation().equals("LANDSCAPE")) {
				end_x = DesignerGridPanel.LANDSCAPE_COLS;
				end_y = DesignerGridPanel.LANDSCAPE_ROWS;
			}
			for (int i = 0; i < end_x; i++) {
				for (int j = 0; j < end_y; j++) {
					String coordinates = i + ":" + j;
					if (boxSelectedPixels.contains(coordinates)) {
						CheckBox cb = new CheckBox();
						cb.setValue(false);
						cb.setEnabled(true);
						cb.setTitle(i + ":" + j);
						addCheckBoxHandler(w, cb, boxID, rgb);
						w.getDesignerGridPanel().getGrid().remove(w.getDesignerGridPanel().getGrid().getWidget(j, i));
						w.getDesignerGridPanel().getGrid().setWidget(j, i, cb);
					}
				}
			}
			List<String> selectedPixels = w.getDesignerGridPanel().getSelectedPixels();
			for (int i = selectedPixels.size() - 1; i >= 0; i--)
				if (boxSelectedPixels.contains(selectedPixels.get(i)))
					selectedPixels.remove(selectedPixels.get(i));
			w.getDesignerGridPanel().getSelectedPixelsMap().remove(boxID);
		}
	}

	public static SelectionListener<IconButtonEvent> addBox(final DesignerTabItem dti) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				dti.addBox();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> createNewPageLandscape(final DesignerWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				w.getDesignerTabPanel().addLandscapeTabItem(DesignerConstants.LANDSCAPE.name());
			}
		};
	}

	public static SelectionListener<IconButtonEvent> cleanGrid(final DesignerWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				TabItem ti = w.getDesignerTabPanel().getTabPanel().getSelectedItem();
				DesignerTabItem dti = w.getDesignerTabPanel().getTabItemMap().get(ti);
				DesignerGridWindow w = dti.getDesignerGridWindow();
				cleanGrid(w);
			}
		};
	}

	public static void cleanGrid(DesignerGridWindow w) {
		DesignerGridPanel p = w.getDesignerGridPanel();
		int end_x = DesignerGridPanel.PORTRAIT_COLS;
		int end_y = DesignerGridPanel.PORTRAIT_ROWS;
		if (w.getOrientation().equals("LANDSCAPE")) {
			end_x = DesignerGridPanel.LANDSCAPE_COLS;
			end_y = DesignerGridPanel.LANDSCAPE_ROWS;
		}
		for (int i = 0; i < end_x; i++) {
			for (int j = 0; j < end_y; j++) {
				String coordinates = i + ":" + j;
				List<String> selectedPixels = w.getDesignerGridPanel().getSelectedPixels();
				if (!selectedPixels.contains(coordinates)) {
					CheckBox cb = (CheckBox) p.getPanel().getData(coordinates);
					cb.setValue(false);
					cb.setEnabled(true);
				}
			}
		}
	}

	public static void disableGrid(DesignerGridWindow w, Integer boxID, String rgb) {
		DesignerGridPanel p = w.getDesignerGridPanel();
		int end_x = DesignerGridPanel.PORTRAIT_COLS;
		int end_y = DesignerGridPanel.PORTRAIT_ROWS;
		if (w.getOrientation().equals("LANDSCAPE")) {
			end_x = DesignerGridPanel.LANDSCAPE_COLS;
			end_y = DesignerGridPanel.LANDSCAPE_ROWS;
		}
		for (int i = 0; i < end_x; i++) {
			for (int j = 0; j < end_y; j++) {
				String coordinates = i + ":" + j;
				CheckBox cb = new CheckBox();
				cb.setTitle(coordinates);
				addCheckBoxHandler(w, cb, boxID, rgb);
				cb.setValue(false);
				cb.setEnabled(false);
				p.getPanel().setData(coordinates, cb);
				w.getDesignerGridPanel().getGrid().remove(w.getDesignerGridPanel().getGrid().getWidget(j, i));
				w.getDesignerGridPanel().getGrid().setWidget(j, i, cb);
			}
		}
		p.getPanel().getLayout().layout();
	}

	public static SelectionListener<IconButtonEvent> createNewPagePortrait(final DesignerWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				w.getDesignerTabPanel().addPortraitTabItem(DesignerConstants.PORTRAIT.name());
			}
		};
	}

	public static ClickHandler pixel(final DesignerGridWindow w, final CheckBox cb, final Integer boxID, final String rgb) {
		return new ClickHandler() {
			public void onClick(ClickEvent e) {
				if (pixels == null) {
					pixels = new ArrayList<Map<String, Integer>>();
				}
				if (cb.getValue()) {
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("x", readX(cb));
					map.put("y", readY(cb));
					pixels.add(map);
					if (pixels.size() == 2) {
						removeBoxAgent(w, boxID, rgb);
						fillBox(w.getDesignerGridPanel(), boxID, rgb);
					}
				}
			}
		};
	}

	public static Integer getBoxID(HorizontalPanel box) {
		return (Integer) box.getData("ID");
	}

	public static String getBoxRGB(HorizontalPanel box) {
		return (String) box.getData("RGB");
	}

	private static void fillBox(DesignerGridPanel p, Integer boxID, String rgb) {
		Map<String, Integer> map_0 = pixels.get(0);
		Integer x_0 = map_0.get("x");
		Integer y_0 = map_0.get("y");
		Map<String, Integer> map_1 = pixels.get(1);
		Integer x_1 = map_1.get("x");
		Integer y_1 = map_1.get("y");
		int start_x = x_0;
		int start_y = y_0;
		int end_x = x_1;
		int end_y = y_1;
		if (x_1 < x_0) {
			start_x = x_1;
			end_x = x_0;
		}
		if (y_1 < y_0) {
			start_y = y_1;
			end_y = y_0;
		}
		List<String> boxPixels = new ArrayList<String>();
		for (int i = start_x; i <= end_x; i++) {
			for (int j = start_y; j <= end_y; j++) {
				p.getGrid().remove(p.getGrid().getWidget(j, i));
				Html ib = createColoredPixel(rgb);
				p.getGrid().setWidget(j, i, ib);
				boxPixels.add(i + ":" + j);
			}
		}
		List<String> selectedPixels = p.getSelectedPixels();
		p.getSelectedPixelsMap().put(boxID, boxPixels);
		for (String coordinates : boxPixels)
			selectedPixels.add(coordinates);
		pixels = null;
	}

	public static void fillBox(DesignerGridPanel p, Integer boxID, String rgb, int x_0, int x_1, int y_0, int y_1) {
		int start_x = x_0;
		int start_y = y_0;
		int end_x = x_1;
		int end_y = y_1;
		if (x_1 < x_0) {
			start_x = x_1;
			end_x = x_0;
		}
		if (y_1 < y_0) {
			start_y = y_1;
			end_y = y_0;
		}
		List<String> boxPixels = new ArrayList<String>();
		for (int i = start_x; i <= end_x; i++) {
			for (int j = start_y; j <= end_y; j++) {
				p.getGrid().remove(p.getGrid().getWidget(j, i));
				Html ib = createColoredPixel(rgb);
				p.getGrid().setWidget(j, i, ib);
				boxPixels.add(i + ":" + j);
			}
		}
		List<String> selectedPixels = p.getSelectedPixels();
		p.getSelectedPixelsMap().put(boxID, boxPixels);
		for (String coordinates : boxPixels)
			selectedPixels.add(coordinates);
		pixels = null;
	}

	public static String createRGB() {
		String rgb = "rgb(" + Random.nextInt(256) + ", " + Random.nextInt(256) + ", " + Random.nextInt(256) + ")";
		return rgb;
	}

	public static Html createColoredPixel(String rgb) {
		Html ib = new Html("<div width='10px' height='10px' style='background-color: " + rgb + ";'>&nbsp;</div>");
		// Image ib = new Image("toolBox-images/mapToolbox.gif");
		ib.setSize("10px", "10px");
		return ib;
	}

	private static Integer readX(CheckBox cb) {
		String coordinates = cb.getTitle();
		return Integer.parseInt(coordinates.substring(0, coordinates.indexOf(':')));
	}

	private static Integer readY(CheckBox cb) {
		String coordinates = cb.getTitle();
		return Integer.parseInt(coordinates.substring(1 + coordinates.indexOf(':')));
	}

	public static SelectionListener<IconButtonEvent> re(final DesignerBox box) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ListBox resourceType = box.getResourceTypeList();
				if (resourceType.getSelectedIndex() > 0) {
					ResourceType rt = ResourceType.valueOf(resourceType.getValue(resourceType.getSelectedIndex()));
					switch (rt) {
					case TEXT:
						new ResourceExplorerText(box);
						break;
					case MAP:
						new ResourceExplorerMap(box);
						break;
					case CHART:
						new ResourceExplorerChart(box);
						break;
					case OLAP:
						new ResourceExplorerOlap(box);
						break;
					case IMAGE:
						new ResourceExplorerImage(box);
						break;
					case TABLE:
						new ResourceExplorerTableView(box);
						break;
					case LAYER:
						new ResourceExplorerLayer(box);
						break;
					}
				} else {
					FenixAlert.info("INFO", "Please Select a Resource Type.");
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> createReport(final DesignerWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String language = CheckLanguage.getLanguage();
				DesignerVO dvo = collectParameters(w);
				try {
					final LoadingWindow lw = new LoadingWindow("Report Designer", "Creating PDF.", "Please Wait...");
					LatexServiceEntry.getInstance().exportPDF(dvo, language, new AsyncCallback<String>() {
						public void onSuccess(String filepath) {
							lw.destroyLoadingBox();
							Window.open("../exportObject/" + filepath, "_blank", "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
							lw.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							lw.destroyLoadingBox();
							FenixAlert.error("ERROR", e.getMessage());
							lw.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> save(final DesignerWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				if (isValid(w)) {
					DesignerVO dvo = collectParameters(w);
					Map<String, List<ResourceViewVO>> latexReportParameters = convert(dvo);
					if (dvo.getReportID() == null) {
						MetadataEditorWindow mw = new MetadataEditorWindow();
						mw.build(false, true, false, MESaver.getSaveLatexReportListener(mw, w, latexReportParameters));
					} else {
						update(latexReportParameters);
					}
				} else {
					FenixAlert.info(BabelFish.print().info(), "Please add at least one page to your report. Every page must contain at least one box. Please make sure that every box has an associated resource.");
				}
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> saveAs(final DesignerWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				if (isValid(w)) {
					MESaveAs.prepopulateLatexReport(w.getReportID(), true, false, w);
				} else {
					FenixAlert.info(BabelFish.print().info(), "Please add at least one page to your report. Every page must contain at least one box. Please make sure that every box has an associated resource.");
				}
			}
		};
	}
	
	public static boolean isValid(DesignerWindow w) {
		if (w.getDesignerTabPanel().getTabItems().isEmpty())
			return false;
		for (DesignerTabItem ti : w.getDesignerTabPanel().getTabItems()) {
			if (ti.getBoxes().isEmpty())
				return false;
			for (DesignerBox db : ti.getBoxes()) {
				if (db.getResourceID() == null)
					return false;
				if (db.getResourceName() == null || db.getResourceName().equals(""))
					return false;
			}
		}
		return true;
	}

	public static void update(final Map<String, List<ResourceViewVO>> latexReportParameters) {
		final LoadingWindow l = new LoadingWindow("LaTeX Repor", "Updating Report", "Please Wait...");
		try {
			LatexServiceEntry.getInstance().save(latexReportParameters, new AsyncCallback<Long>() {
				public void onSuccess(Long id) {
					l.destroyLoadingBox();
					FenixAlert.info(BabelFish.print().info(), BabelFish.print().updateCompleted());
					l.destroyLoadingBox();
				}

				public void onFailure(Throwable e) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException ee) {
			l.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().info(), ee.getMessage());
			l.destroyLoadingBox();
		}
	}

	public static DesignerVO collectParameters(DesignerWindow w) {
		DesignerVO vo = new DesignerVO();
		if (w.getReportID() != null)
			vo.setReportID(w.getReportID());
		for (DesignerTabItem dti : w.getDesignerTabPanel().getTabItems()) {
			PageVO pvo = new PageVO();
			DesignerConstants orientation = DesignerConstants.valueOf(dti.getDesignerGridWindow().getOrientation());
			pvo.setOrientation(orientation);
			for (Integer boxID : dti.getDesignerGridWindow().getDesignerGridPanel().getSelectedPixelsMap().keySet()) {
				BoxVO bvo = new BoxVO();
				bvo.setEndX(findMaxX(dti.getDesignerGridWindow().getDesignerGridPanel().getSelectedPixelsMap().get(boxID)));
				bvo.setEndY(findMaxY(dti.getDesignerGridWindow().getDesignerGridPanel().getSelectedPixelsMap().get(boxID)));
				bvo.setStartX(findMinX(dti.getDesignerGridWindow().getDesignerGridPanel().getSelectedPixelsMap().get(boxID)));
				bvo.setStartY(findMinY(dti.getDesignerGridWindow().getDesignerGridPanel().getSelectedPixelsMap().get(boxID)));
				for (DesignerBox db : dti.getBoxes()) {
					if (db.getBoxID().intValue() == boxID.intValue()) {
						bvo.setResourceID(db.getResourceID());
						bvo.setResourceType(db.getResourceType());
						bvo.setResourceName(db.getResourceTitle());
						if (db.getDesignerBoxSettings() != null) {
							DesignerBoxSettings dbs = db.getDesignerBoxSettings();
							if (dbs.getAddCaption().getValue()) {
								bvo.setCaption(dbs.getCaption().getValue());
								bvo.setCaptionFont(dbs.getCaptionFontFamily().getValue(dbs.getCaptionFontFamily().getSelectedIndex()));
								bvo.setCaptionSize(dbs.getCaptionFontSize().getValue(dbs.getCaptionFontSize().getSelectedIndex()));
								bvo.setCaptionColor(dbs.getCaptionColor().getText());
								bvo.setCaptionPosition(dbs.getCaptionPosition().getValue(dbs.getCaptionPosition().getSelectedIndex()));
							}
							if (dbs.getAddBox().getValue()) {
								bvo.setBoxBackgroundColor(dbs.getBoxBackgroundColor().getText());
								bvo.setBoxLineColor(dbs.getBoxLineColor().getText());
							}
						}
					}
				}
				pvo.addBox(bvo);
			}
			vo.addPage(pvo);
		}
		return vo;
	}
	
	public static SelectionListener<ButtonEvent> close(final DesignerBoxSettingsWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().close();
			}
		};
	}

	public static Map<String, List<ResourceViewVO>> convert(DesignerVO dvo) {

		Map<String, List<ResourceViewVO>> map = new HashMap<String, List<ResourceViewVO>>();
		List<ResourceViewVO> reports = new ArrayList<ResourceViewVO>();
		List<ResourceViewVO> pages = new ArrayList<ResourceViewVO>();
		List<ResourceViewVO> boxes = new ArrayList<ResourceViewVO>();

		ResourceViewVO report = new ResourceViewVO();
		report.setType(DesignerConstants.REPORT.name());

		if (dvo.getReportID() != null)
			report.setResourceId(Long.valueOf(dvo.getReportID()));

		String reportCode = String.valueOf(Random.nextDouble());
		report.setOlapFunction(reportCode);

		reports.add(report);

		for (PageVO p : dvo.getPages()) {

			ResourceViewVO page = new ResourceViewVO();
			page.setType(p.getOrientation().name());

			String pageCode = String.valueOf(Random.nextDouble());
			page.setQuery(pageCode);
			page.setOlapFunction(reportCode);

			for (BoxVO b : p.getBoxes()) {

				ResourceViewVO box = new ResourceViewVO();
				box.setType(b.getResourceType().name());
				box.setQuery(pageCode);
				box.setResourceId(b.getResourceID());
				box.setTitle(b.getResourceName());

				ResourceViewSettingVO startPixel = new ResourceViewSettingVO();
				startPixel.setResourceViewSettingType(DesignerConstants.STRING.name());
				startPixel.setSettingName(DesignerConstants.START_PIXEL.name());
				startPixel.setValue(b.getStartX() + ":" + b.getStartY());
				box.addResourceViewSetting(startPixel);

				ResourceViewSettingVO endPixel = new ResourceViewSettingVO();
				endPixel.setResourceViewSettingType(DesignerConstants.STRING.name());
				endPixel.setSettingName(DesignerConstants.END_PIXEL.name());
				endPixel.setValue(b.getEndX() + ":" + b.getEndY());
				box.addResourceViewSetting(endPixel);
				
				List<ResourceViewSettingVO> styleSettings = collectStyleSettings(b);
				for (ResourceViewSettingVO ss : styleSettings)
					box.addResourceViewSetting(ss);

				boxes.add(box);
			}

			pages.add(page);
		}

		map.put(DesignerConstants.REPORT.name(), reports);
		map.put(DesignerConstants.PAGE.name(), pages);
		map.put(DesignerConstants.BOX.name(), boxes);

		return map;
	}
	
	public static List<ResourceViewSettingVO> collectStyleSettings(BoxVO b) { 
		
		List<ResourceViewSettingVO> l = new ArrayList<ResourceViewSettingVO>();
		
		ResourceViewSettingVO caption = new ResourceViewSettingVO();
		caption.setResourceViewSettingType(DesignerConstants.STRING.name());
		caption.setSettingName(DesignerConstants.CAPTION.name());
		caption.setValue(b.getCaption());
		l.add(caption);
		
		ResourceViewSettingVO captionFontFamily = new ResourceViewSettingVO();
		captionFontFamily.setResourceViewSettingType(DesignerConstants.STRING.name());
		captionFontFamily.setSettingName(DesignerConstants.CAPTION_FONTFAMILY.name());
		captionFontFamily.setValue(b.getCaptionFont());
		l.add(captionFontFamily);
		
		ResourceViewSettingVO captionFontSize = new ResourceViewSettingVO();
		captionFontSize.setResourceViewSettingType(DesignerConstants.STRING.name());
		captionFontSize.setSettingName(DesignerConstants.CAPTION_SIZE.name());
		captionFontSize.setValue(b.getCaptionSize());
		l.add(captionFontSize);
		
		ResourceViewSettingVO captionFontColor = new ResourceViewSettingVO();
		captionFontColor.setResourceViewSettingType(DesignerConstants.STRING.name());
		captionFontColor.setSettingName(DesignerConstants.CAPTION_COLOR.name());
		captionFontColor.setValue(b.getCaptionColor());
		l.add(captionFontColor);
		
		ResourceViewSettingVO captionPosition = new ResourceViewSettingVO();
		captionPosition.setResourceViewSettingType(DesignerConstants.STRING.name());
		captionPosition.setSettingName(DesignerConstants.CAPTION_POSITION.name());
		captionPosition.setValue(b.getCaptionPosition());
		l.add(captionPosition);
		
		ResourceViewSettingVO boxLineColor = new ResourceViewSettingVO();
		boxLineColor.setResourceViewSettingType(DesignerConstants.STRING.name());
		boxLineColor.setSettingName(DesignerConstants.BOX_LINE_COLOR.name());
		boxLineColor.setValue(b.getBoxLineColor());
		l.add(boxLineColor);
		
		ResourceViewSettingVO boxBackgroundColor = new ResourceViewSettingVO();
		boxBackgroundColor.setResourceViewSettingType(DesignerConstants.STRING.name());
		boxBackgroundColor.setSettingName(DesignerConstants.BOX_BACKGROUND_COLOR.name());
		boxBackgroundColor.setValue(b.getBoxBackgroundColor());
		l.add(boxBackgroundColor);
		
		return l;
	}

	public static int findMinX(List<String> coordinates) {
		int min = Integer.MAX_VALUE;
		for (String c : coordinates) {
			int tmp = Integer.parseInt(c.substring(0, c.indexOf(':')));
			if (tmp < min)
				min = tmp;
		}
		return min;
	}

	public static int findMaxX(List<String> coordinates) {
		int max = Integer.MIN_VALUE;
		for (String c : coordinates) {
			int tmp = Integer.parseInt(c.substring(0, c.indexOf(':')));
			if (tmp > max)
				max = tmp;
		}
		return max;
	}

	public static int findMinY(List<String> coordinates) {
		int min = Integer.MAX_VALUE;
		for (String c : coordinates) {
			int tmp = Integer.parseInt(c.substring(1 + c.indexOf(':')));
			if (tmp < min)
				min = tmp;
		}
		return min;
	}

	public static int findMaxY(List<String> coordinates) {
		int max = Integer.MIN_VALUE;
		for (String c : coordinates) {
			int tmp = Integer.parseInt(c.substring(1 + c.indexOf(':')));
			if (tmp > max)
				max = tmp;
		}
		return max;
	}
	
	public static ClickHandler colorPicker(final HTML html) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				ColorPicker cp = new ColorPicker(html);
				cp.build(ColorPickerCaller.DESIGNER);	
			}
		};
	}

}