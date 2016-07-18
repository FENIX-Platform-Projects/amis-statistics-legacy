package org.fao.fenix.web.modules.birt.client.utils;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartViewerController;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.core.client.view.FenixSettings;
import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ColorPicker {

	private Window colPick;

	private VerticalPanel panel;

	private final int NUM_ROWS = 12;

	private final Element obj;

	private ChartViewer chartViewer;

	private String caller;

	private HTML html;

	public ColorPicker(HTML html) {
		colPick = new Window();
		this.obj = html.getElement();
		this.html = html;
		panel = new VerticalPanel();
		colPick.setSize("285px", "230px");
		colPick.setHeading("Color Picker");
		DOM.setStyleAttribute(colPick.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	public ColorPicker(Widget obj) {
		colPick = new Window();
		this.obj = obj.getElement();
		panel = new VerticalPanel();
		colPick.setSize("285px", "230px");
		colPick.setHeading("Color Picker");
		DOM.setStyleAttribute(colPick.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	public ColorPicker(Widget obj, int x, int y) {
		colPick = new Window();
		this.obj = obj.getElement();
		colPick.setPagePosition(x, y);
		panel = new VerticalPanel();
		colPick.setSize("285px", "230px");
		colPick.setHeading("Color Picker");
		DOM.setStyleAttribute(colPick.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	public ColorPicker(Element obj, int x, int y) {
		colPick = new Window();
		this.obj = obj;
		colPick.setPagePosition(x, y);
		panel = new VerticalPanel();
		colPick.setSize("285px", "230px");
		colPick.setHeading("Color Picker");
		DOM.setStyleAttribute(colPick.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	public ColorPicker(Widget obj, int x, int y, ChartViewer chartViewer) {
		this.chartViewer = chartViewer;
		colPick = new Window();
		this.obj = obj.getElement();
		colPick.setPagePosition(x, y);
		panel = new VerticalPanel();
		colPick.setSize("285px", "230px");
		colPick.setHeading("Color Picker");
		DOM.setStyleAttribute(colPick.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	private String getHex(int code) {
		String res = null;
		if (code > 9) {
			switch (code) {
			case 10:
				res = "AA";
				break;
			case 11:
				res = "BB";
				break;
			case 12:
				res = "CC";
				break;
			case 13:
				res = "DD";
				break;
			case 14:
				res = "EE";
				break;
			case 15:
				res = "FF";
				break;
			default:
				break;
			}
		} else {
			res = String.valueOf(code) + String.valueOf(code);
		}

		return res;

	}

	public static String getColorDec(String rgb) {
		/*
		 * rgb rgb(#, #, #)
		 */
		String color = "";
		String temp = rgb.substring(4, (rgb.length() - 1));
		int lun = temp.length();
		String subStrRGB = "";
		for (int i = 0; i < lun; i++) {

			if (i == (lun - 1))
				subStrRGB += temp.substring(i, (i + 1));

			if (temp.substring(i, (i + 1)).equals(",") || i == (lun - 1)) {
				if (i == (lun - 1)) {
					color += subStrRGB.trim();
				} else {
					color += subStrRGB.trim() + "_";
				}

				subStrRGB = "";
			} else {
				subStrRGB += temp.substring(i, (i + 1));

			}

		}

		return color;
	}

	private String getColor(String rgb) {
		/*
		 * rgb rgb(#, #, #)
		 */
		String color = "#";
		String temp = rgb.substring(4, (rgb.length() - 1));
		int lun = temp.length();
		String subStrRGB = "";
		for (int i = 0; i < lun; i++) {

			if (i == (lun - 1))
				subStrRGB += temp.substring(i, (i + 1));

			if (temp.substring(i, (i + 1)).equals(",") || i == (lun - 1)) {
				int t = Integer.valueOf(subStrRGB.trim()).intValue();
				if (Integer.toHexString(t).length() == 1) {
					color += "0" + Integer.toHexString(t);
				} else {
					color += Integer.toHexString(t);
				}
				subStrRGB = "";
			} else {
				subStrRGB += temp.substring(i, (i + 1));

			}

		}

		return color;
	}

	private List createCells(String rComp, int bComp, int driRov) {
		List cells = new ArrayList();
		Button cell;
		if (driRov == 0) {
			for (int i = 0; i < 6; i++) {
				cell = new Button();
				String r = rComp;
				String g = this.getHex(3 * i);
				String b = this.getHex(bComp * 3);
				String color = "#" + r + g + b;
				DOM.setStyleAttribute(cell.getElement(), "backgroundColor", color);
				DOM.setStyleAttribute(cell.getElement(), "border", "1px solid");
				cell.addClickListener(new ClickListener() {
					public void onClick(Widget wid) {
						DOM.setStyleAttribute(obj, "backgroundColor", getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						checkCaller(ColorPicker.this.caller, getColorDec(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						colPick.hide();

					}
				});
				cell.setSize("15px", "15px");
				cells.add(cell);
			}
		} else if (driRov == 5) {
			for (int i = 5; i >= 0; i--) {
				cell = new Button();
				String r = rComp;
				String g = this.getHex(3 * i);
				String b = this.getHex(bComp * 3);
				String color = "#" + r + g + b;
				DOM.setStyleAttribute(cell.getElement(), "backgroundColor", color);
				DOM.setStyleAttribute(cell.getElement(), "border", "1px solid");
				cell.addClickListener(new ClickListener() {
					public void onClick(Widget wid) {
						DOM.setStyleAttribute(obj, "backgroundColor", getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						checkCaller(ColorPicker.this.caller, getColorDec(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						colPick.hide();

					}
				});
				cell.setSize("15px", "15px");
				cells.add(cell);
			}
		}

		return cells;
	}
	
	private List createCellsColorIntervals(String rComp, int bComp, int driRov) {
		List cells = new ArrayList();
		Button cell;
		if (driRov == 0) {
			for (int i = 0; i < 6; i++) {
				cell = new Button();
				String r = rComp;
				String g = this.getHex(3 * i);
				String b = this.getHex(bComp * 3);
				String color = "#" + r + g + b;
				DOM.setStyleAttribute(cell.getElement(), "backgroundColor", color);
				DOM.setStyleAttribute(cell.getElement(), "border", "1px solid");
				cell.addClickListener(new ClickListener() {
					public void onClick(Widget wid) {
						DOM.setStyleAttribute(obj, "backgroundColor", getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						checkCaller(ColorPicker.this.caller, getColorDec(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));					
						String color = getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor"));
						if(color.charAt(0) == '#')
						{
							color = color.substring(1);
						}
						color = color.toUpperCase();
						 MessageBox.alert("Color Code", "Please copy the color code and past it in Color Column:\n\n"+ color , new Listener<MessageBoxEvent>() {    
				    	      public void handleEvent(MessageBoxEvent ce) {  
				    		       }  
				    		     });
						colPick.hide();

					}
				});
				cell.setSize("15px", "15px");
				cells.add(cell);
			}
		} else if (driRov == 5) {
			for (int i = 5; i >= 0; i--) {
				cell = new Button();
				String r = rComp;
				String g = this.getHex(3 * i);
				String b = this.getHex(bComp * 3);
				String color = "#" + r + g + b;
				DOM.setStyleAttribute(cell.getElement(), "backgroundColor", color);
				DOM.setStyleAttribute(cell.getElement(), "border", "1px solid");
				cell.addClickListener(new ClickListener() {
					public void onClick(Widget wid) {
						DOM.setStyleAttribute(obj, "backgroundColor", getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						checkCaller(ColorPicker.this.caller, getColorDec(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
						String color = getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor"));
						if(color.charAt(0) == '#')
						{
							color = color.substring(1);
						}
						color = color.toUpperCase();
						 MessageBox.alert("Color Code", "Please copy the color code and past it in Color Column:\n\n"+ color , new Listener<MessageBoxEvent>() {  
				    	      public void handleEvent(MessageBoxEvent ce) {  
				    		         //Button btn = ce.getButtonClicked();  
				    		     //    Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
				    		       }  
				    		     });
						colPick.hide();

					}
				});
				cell.setSize("15px", "15px");
				cells.add(cell);
			}
		}

		return cells;
	}

	private HorizontalPanel setRow(int bComp, int countRComp) {
		HorizontalPanel hr = new HorizontalPanel();
		for (int j = 0; j < 3; j++) {

			if (countRComp < 6) {
				if (j == 0) {
					List l = createCells("33", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 1) {
					List l = createCells("66", bComp, 5);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 2) {
					List l = createCells("FF", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				}
			} else {
				if (j == 0) {
					List l = createCells("00", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 1) {
					List l = createCells("99", bComp, 5);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 2) {
					List l = createCells("CC", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				}
			}

		}

		return hr;
	}
	
	private HorizontalPanel setRowColorInterval(int bComp, int countRComp) {
		HorizontalPanel hr = new HorizontalPanel();
		for (int j = 0; j < 3; j++) {

			if (countRComp < 6) {
				if (j == 0) {
					List l = createCellsColorIntervals("33", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 1) {
					List l = createCellsColorIntervals("66", bComp, 5);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 2) {
					List l = createCellsColorIntervals("FF", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				}
			} else {
				if (j == 0) {
					List l = createCellsColorIntervals("00", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 1) {
					List l = createCellsColorIntervals("99", bComp, 5);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				} else if (j == 2) {
					List l = createCellsColorIntervals("CC", bComp, 0);
					for (int c = 0; c < l.size(); c++) {
						hr.add((Button) l.get(c));
					}
				}
			}

		}

		return hr;
	}

	public void build(String caller) {
		this.caller = caller;
		int cont = 1;
		for (int i = 0; i < NUM_ROWS; i++) {
			if (i < 6) {
				panel.add(setRow(i, i));
			} else if (i >= 6) {
				panel.add(setRow((6 - cont), i));
				cont++;
			}
		}
		// add row with rgb component equal
		Button cell;
		HorizontalPanel hr = new HorizontalPanel();
		for (int i = 0; i < 16; i++) {
			
			cell = new Button();
			String rgb = this.getHex(i);
			String color = "#" + rgb + rgb + rgb;
			DOM.setStyleAttribute(cell.getElement(), "backgroundColor", color);
			DOM.setStyleAttribute(cell.getElement(), "border", "1px solid");
			cell.addClickListener(new ClickListener() {
				public void onClick(Widget wid) {
					DOM.setStyleAttribute(obj, "backgroundColor", getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
					checkCaller(ColorPicker.this.caller, getColorDec(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
					//colPick.hide();
					colPick.close();
				}
			});
			cell.setSize("15px", "15px");
			hr.add(cell);
		}
		panel.add(hr);

		colPick.add(panel);
		colPick.show();
	}

	public void buildColorInterval(String caller) {
		this.caller = caller;
		int cont = 1;
		for (int i = 0; i < NUM_ROWS; i++) {
			if (i < 6) {
				panel.add(setRowColorInterval(i, i));
			} else if (i >= 6) {
				panel.add(setRowColorInterval((6 - cont), i));
				cont++;
			}
		}
		// add row with rgb component equal
		Button cell;
		HorizontalPanel hr = new HorizontalPanel();
		for (int i = 0; i < 16; i++) {
			
			cell = new Button();
			String rgb = this.getHex(i);
			String color = "#" + rgb + rgb + rgb;
			DOM.setStyleAttribute(cell.getElement(), "backgroundColor", color);
			DOM.setStyleAttribute(cell.getElement(), "border", "1px solid");
			cell.addClickListener(new ClickListener() {
				public void onClick(Widget wid) {
					DOM.setStyleAttribute(obj, "backgroundColor", getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
					checkCaller(ColorPicker.this.caller, getColorDec(DOM.getStyleAttribute(wid.getElement(), "backgroundColor")));
					String color = getColor(DOM.getStyleAttribute(wid.getElement(), "backgroundColor"));
					if(color.charAt(0) == '#')
					{
						color = color.substring(1);
					}
					color = color.toUpperCase();
					 MessageBox.alert("Color Code", "Please copy the color code and past it in Color Column:\n\n"+ color , new Listener<MessageBoxEvent>() {  
			    	      public void handleEvent(MessageBoxEvent ce) {  
			    		       }  
			    		     });
					colPick.hide();
//					colPick.hide();
					colPick.close();
				}
			});
			cell.setSize("15px", "15px");
			hr.add(cell);
		}
		panel.add(hr);

		colPick.add(panel);
		colPick.show();
	}
	
	private void checkCaller(String caller, String color) {
		if (caller.equals(ColorPickerCaller.BIRT_BG)) {
			ChartViewerController.changeChartbackground(chartViewer, color);
		} else if (caller.equals(ColorPickerCaller.SLD_EDITOR)) {
			String hex = SLDEditorController.colorPicker2Hex(color);
			this.html.setHTML("<div align='center' style='border: 4px black solid; background-color: #" + hex + "; color:  #" + hex + "; font-weight: bold; '>#" + hex + "</div>");
		} else if (caller.equals(ColorPickerCaller.DESIGNER)) {
			String hex = SLDEditorController.colorPicker2Hex(color);
			this.html.setHTML("<div align='center' style='border: 4px black solid; background-color: #" + hex + "; color: #" + hex + "; font-weight: bold; '>#" + hex + "</div>");
		}
	}

}
