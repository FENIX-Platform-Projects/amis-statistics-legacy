package org.fao.fenix.web.modules.olap.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.ListBox;

public class OlapRuler {
	
	private String DEFAULT_COLUMN_WIDTH = "125px";
	
	private String DEFAULT_FUNCTION_WIDTH = "75px";
	
	private String DEFAULT_FUNCTION_HEIGHT = "25px";
	
	private HorizontalPanel firstLine;
	
	private HorizontalPanel secondLine;
	
	private HorizontalPanel thirdLine;
	
	private String functionWidth;
	
	private String functionHeight;
	
	private String columnLabelWidth;
	
	private String subRowWidth;
	
	private Map<String, TextField<String>> columnLabelWidths;
	
	private Map<String, TextField<String>> rowLabelHeights;
	
	private TextField<String> functionWidthField;
	
	private TextField<String> functionHeightField;
	
	private TextField<String> columnLabel;
	
	private TextField<String> subRowLabel;
	
	public OlapRuler() {
		firstLine = new HorizontalPanel();
		secondLine = new HorizontalPanel();
		thirdLine = new HorizontalPanel();
		functionWidth = "75px";
		functionHeight = "25px";
		columnLabelWidth = "125px";
		columnLabelWidths = new HashMap<String, TextField<String>>();
		rowLabelHeights = new HashMap<String, TextField<String>>();
		functionWidthField = new TextField<String>();
		functionWidthField.setValue(DEFAULT_FUNCTION_WIDTH.substring(0, DEFAULT_FUNCTION_WIDTH.indexOf("px")));
		functionHeightField = new TextField<String>();
		functionHeightField.setValue(DEFAULT_FUNCTION_HEIGHT.substring(0, DEFAULT_FUNCTION_HEIGHT.indexOf("px")));
		columnLabel = new TextField<String>();
		columnLabel.setValue(DEFAULT_COLUMN_WIDTH.substring(0, DEFAULT_COLUMN_WIDTH.indexOf("px")));
		subRowWidth = "125px";
		subRowLabel = new TextField<String>();
		subRowLabel.setValue(DEFAULT_COLUMN_WIDTH.substring(0, DEFAULT_COLUMN_WIDTH.indexOf("px")));
		subRowLabel.setWidth("125px");
	}

	public void showWidthsAndHeights(boolean show, OlapWindow w) {
		firstLine.removeAll();
		secondLine.removeAll();
		thirdLine.removeAll();
		w.getWidthsWrapper().removeAll();
		w.getWidthsWrapper().setLayout(new FillLayout());
		w.getHeightsWrapper().removeAll();
		w.getHeightsWrapper().setLayout(new FillLayout());
		if(show) {
			w.getWidthsWrapper().add(buildWidthsPanel(w));
			w.getHeightsWrapper().add(buildHeightsPanel(w));
		}
		w.getWidthsWrapper().getLayout().layout();
		w.getHeightsWrapper().getLayout().layout();
	}
	
	private VerticalPanel buildHeightsPanel(OlapWindow w) {
		AxisPanel axisPanel = w.getTabPanel().getSingleDatasetPanel().getZAxis();
		ListBox l = axisPanel.getValues();
		boolean hasSubRows = hasSubRows(w);
		if (hasSubRows) {
			axisPanel = w.getTabPanel().getSingleDatasetPanel().getWAxis();
			l = axisPanel.getValues();
		}
		VerticalPanel panel = new VerticalPanel();
		String fontSize = getFontSize(w);
		panel.setStyleAttribute("backgroundColor", "#A1CC16");
		Html space_1 = new Html("<div style='background-color: #FFFFFF; font-size: " + fontSize + ";'>&nbsp;</div>");
		Html space_2 = new Html("<div style='background-color: #FFFFFF; font-size: " + fontSize + ";'>&nbsp;</div>");
		panel.add(space_1);
		panel.add(space_2);
		if (hasSubColumns(w)) {
			Html space_3 = new Html("<div style='background-color: #FFFFFF;'>&nbsp;</div>");
			panel.add(space_3);
		}
		Html title = new Html("<div style='background-color: #A1CC16; text-align: center; font-family: sans-serif; color: #FFFFFF; font-weight: bold; display: table-cell; vertical-align: middle; border-bottom: solid 1px #FFFFFF; font-size: " + fontSize + ";'>Resize Rows Height</div>");
		panel.add(title);
		if (containsDate(axisPanel)) {
			for (int i = l.getItemCount() - 1 ; i >= 0 ; i--)
				if (l.isItemSelected(i))
					panel.add(buildRowHeightPanel(l.getItemText(i), fontSize));
		} else {
			for (int i = 0 ; i < l.getItemCount() ; i++)
				if (l.isItemSelected(i))
					panel.add(buildRowHeightPanel(l.getItemText(i), fontSize));
		}
		removeZombieRowLabelHeight(l);
		return panel;
	}
	
	private boolean containsDate(AxisPanel axisPanel) {
		ListBox l = axisPanel.getDimensions();
		if (l.getValue(l.getSelectedIndex()).contains("date") || l.getValue(l.getSelectedIndex()).contains("Date"))
			return true;
		return false;
	}
	
	private HorizontalPanel buildRowHeightPanel(String label, String fontSize) {
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html title = null;
		if (rowLabelHeights.keySet().contains(label)) {
			int height = Integer.valueOf(rowLabelHeights.get(label).getValue());
			title = new Html("<div style='font-size: " + fontSize + "; background-color: #A1CC16; height: " + height + "px; text-align: center; font-family: sans-serif; color: #FFFFFF; font-weight: bold; display: table-cell; vertical-align: middle;'>" + label + "</div>");
			title.setWidth(DEFAULT_COLUMN_WIDTH);
		} else {
			title = new Html("<div style='font-size: " + fontSize + "; background-color: #A1CC16; height: 25px; text-align: center; font-family: sans-serif; color: #FFFFFF; font-weight: bold; display: table-cell; vertical-align: middle;'>" + label + "</div>");
			title.setWidth(DEFAULT_COLUMN_WIDTH);
		}
		p.add(title);
		if (rowLabelHeights.keySet().contains(label)) {
			p.add(rowLabelHeights.get(label));
		} else {
			TextField<String> rowHeight = new TextField<String>();
			rowHeight.setWidth("50px");
			rowHeight.setValue("25");
			p.add(rowHeight);
			rowLabelHeights.put(label, rowHeight);
		}
		return p;
	}
	
	private boolean hasSubColumns(OlapWindow w) {
		for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().getItemCount() ; i++)
			if (w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().isItemSelected(i))
				return true;
		return false;
	}
	
	private boolean hasSubRows(OlapWindow w) {
		for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getWAxis().getValues().getItemCount() ; i++)
			if (w.getTabPanel().getSingleDatasetPanel().getWAxis().getValues().isItemSelected(i))
				return true;
		return false;
	}
	
	private VerticalPanel buildWidthsPanel(OlapWindow w) {
		OLAPParametersVo p = OlapController.retrieveParameters(w);
		boolean hasSubRows = hasSubRows(w);
		String fontSize = getFontSize(w);
		VerticalPanel panel = new VerticalPanel();
		panel.setStyleAttribute("backgroundColor", "#A1CC16");
		firstLine.setStyleAttribute("backgroundColor", "#A1CC16");
		secondLine.setStyleAttribute("backgroundColor", "#A1CC16");
		thirdLine.setStyleAttribute("backgroundColor", "#A1CC16");
		Html title = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center; width: " + calculateTitleWidth(w, p) + "'>Resize Columns Width</div>");
		firstLine.add(title);
		panel.add(firstLine);
		Html item = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + p.getZLabel() + "</div>");
		item.setWidth(p.getColumnLabelWidth());
		secondLine.add(item);
		if (hasSubRows) {
			Html wLabel = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + p.getWLabel() + "</div>");
			wLabel.setWidth(p.getSubRowLabelWidth());
			secondLine.add(wLabel);
		}
		createXLabels(w, p);
		if (p.isShowTotals() || p.getShowRowSummary()) {
			Html function = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + p.getFunction() + "</div>");
			function.setWidth(p.getFunctionWidth());
			secondLine.add(function);
		}
		panel.add(secondLine);
		// z-label width
		columnLabel.setWidth(p.getColumnLabelWidth());
		columnLabel.setValue(p.getColumnLabelWidth().substring(0, p.getColumnLabelWidth().indexOf("px")));
		thirdLine.add(columnLabel);
		if (hasSubRows) {
			thirdLine.add(subRowLabel);
			subRowLabel.setWidth(p.getSubRowLabelWidth());
			subRowLabel.setValue(p.getSubRowLabelWidth().substring(0, p.getSubRowLabelWidth().indexOf("px")));
		}
		// x-values widths
		createXValues(w, p);
		// aggregation function width
		if (p.isShowTotals() || p.getShowRowSummary()) {
			functionWidthField.setWidth(p.getFunctionWidth());
			functionWidthField.setValue(p.getFunctionWidth().substring(0, p.getFunctionWidth().indexOf("px")));
			thirdLine.add(functionWidthField);
		}
		panel.add(thirdLine);
		return panel;
	}
	
	private void createXLabels(OlapWindow w, OLAPParametersVo p) {
		AxisPanel panel = w.getTabPanel().getSingleDatasetPanel().getXAxis();
		ListBox l = w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues();
		boolean hasSubColumns = hasSubColumns(w);
		if (hasSubColumns) {
			l = w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues();
			panel = w.getTabPanel().getSingleDatasetPanel().getYAxis();
		}
		boolean containsDate = containsDate(panel);
		List<String> emptyWidths = new ArrayList<String>();
		String fontSize = getFontSize(w);
		if (p.getColumnLabelsWidths().isEmpty()) {
			if (containsDate) {
				for (int i = l.getItemCount() - 1 ; i >= 0 ; i--) {
					if (l.isItemSelected(i)) {
						Html xLabelItem = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + l.getItemText(i) + "</div>");
						xLabelItem.setWidth(DEFAULT_COLUMN_WIDTH);
						emptyWidths.add(DEFAULT_COLUMN_WIDTH);
						secondLine.add(xLabelItem);
					}
				}
			} else {
				for (int i = 0 ; i < l.getItemCount() ; i++) {
					if (l.isItemSelected(i)) {
						Html xLabelItem = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + l.getItemText(i) + "</div>");
						xLabelItem.setWidth(DEFAULT_COLUMN_WIDTH);
						emptyWidths.add(DEFAULT_COLUMN_WIDTH);
						secondLine.add(xLabelItem);
					}
				}
			}
		} else {
			if (containsDate) {
				for (int i = l.getItemCount() - 1; i >= 0 ; i--) {
					if (l.isItemSelected(i)) {
						Html xLabelItem = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + l.getItemText(i) + "</div>");
						xLabelItem.setWidth(p.getColumnLabelsWidths().get(l.getItemText(i)));
						emptyWidths.add(p.getColumnLabelsWidths().get(l.getItemText(i)));
						secondLine.add(xLabelItem);
					}
				}
			} else {
				for (int i = 0 ; i < l.getItemCount() ; i++) {
					if (l.isItemSelected(i)) {
						Html xLabelItem = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>" + l.getItemText(i) + "</div>");
						xLabelItem.setWidth(p.getColumnLabelsWidths().get(l.getItemText(i)));
						emptyWidths.add(p.getColumnLabelsWidths().get(l.getItemText(i)));
						secondLine.add(xLabelItem);
					}
				}
			}
		}
		// add empty spaces
		if (hasSubColumns) {
			int emptyWidthsIterations = calculateEmptyWidths(w);
			for (int i = 0 ; i < emptyWidthsIterations ; i++) {
				Html empty = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>&nbsp;</div>");
				empty.setWidth(emptyWidths.get(i));
				secondLine.add(empty);
			}
		}
	}
	
	private int calculateEmptyWidths(OlapWindow w) {
		int xs = countSelectedItems(w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues()) - 1;
		int ys = countSelectedItems(w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues());
		return xs * ys;
	}
	
	private int countSelectedItems(ListBox l) {
		int c = 0;
		for (int i = 0 ; i < l.getItemCount() ; i++)
			if (l.isItemSelected(i))
				c++;
		return c;
	}
	
	private void removeZombieColumnLabelWidth(ListBox l) {
		List<String> selected = new ArrayList<String>();
		List<String> removed = new ArrayList<String>();
		for (int i = 0 ; i < l.getItemCount() ; i++) 
			if (l.isItemSelected(i)) 
				selected.add(l.getItemText(i));
		for (String key : columnLabelWidths.keySet())
			if (!selected.contains(key))
				removed.add(key);
		for (String key : removed)
			columnLabelWidths.remove(key);
	}
	
	private void removeZombieRowLabelHeight(ListBox l) {
		List<String> selected = new ArrayList<String>();
		List<String> removed = new ArrayList<String>();
		for (int i = 0 ; i < l.getItemCount() ; i++) 
			if (l.isItemSelected(i)) 
				selected.add(l.getItemText(i));
		for (String key : rowLabelHeights.keySet())
			if (!selected.contains(key))
				removed.add(key);
		for (String key : removed)
			rowLabelHeights.remove(key);
	}
	
	private void createXValues(OlapWindow w, OLAPParametersVo p) {
		AxisPanel panel = w.getTabPanel().getSingleDatasetPanel().getXAxis();
		ListBox l = w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues();
		boolean hasSubColumns = hasSubColumns(w);
		if (hasSubColumns) {
			l = w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues();
			panel = w.getTabPanel().getSingleDatasetPanel().getYAxis();
		}
		boolean containsDate = containsDate(panel);
		List<String> emptyWidths = new ArrayList<String>();
		String fontSize = getFontSize(w);
		
		if (containsDate) {
			for (int i = l.getItemCount() - 1; i >= 0 ; i--) {
				if (l.isItemSelected(i)) {
					if (columnLabelWidths.containsKey(l.getItemText(i))) {
						TextField<String> tf = columnLabelWidths.get(l.getItemText(i)); 
						tf.setWidth(p.getColumnLabelsWidths().get(l.getItemText(i)));
						emptyWidths.add(p.getColumnLabelsWidths().get(l.getItemText(i)));
						tf.setValue(p.getColumnLabelsWidths().get(l.getItemText(i)).substring(0, p.getColumnLabelsWidths().get(l.getItemText(i)).indexOf("px")));
						thirdLine.add(tf);
						columnLabelWidths.put(l.getItemText(i), tf);
					} else {
						TextField<String> xValue = new TextField<String>();
						xValue.setWidth(DEFAULT_COLUMN_WIDTH);
						emptyWidths.add(DEFAULT_COLUMN_WIDTH);
						xValue.setValue(DEFAULT_COLUMN_WIDTH.substring(0, DEFAULT_COLUMN_WIDTH.indexOf("px")));
						thirdLine.add(xValue);
						columnLabelWidths.put(l.getItemText(i), xValue);
					}
				}
			}
		} else {
			for (int i = 0 ; i < l.getItemCount() ; i++) {
				if (l.isItemSelected(i)) {
					if (columnLabelWidths.containsKey(l.getItemText(i))) {
						TextField<String> tf = columnLabelWidths.get(l.getItemText(i)); 
						tf.setWidth(p.getColumnLabelsWidths().get(l.getItemText(i)));
						emptyWidths.add(p.getColumnLabelsWidths().get(l.getItemText(i)));
						tf.setValue(p.getColumnLabelsWidths().get(l.getItemText(i)).substring(0, p.getColumnLabelsWidths().get(l.getItemText(i)).indexOf("px")));
						thirdLine.add(tf);
						columnLabelWidths.put(l.getItemText(i), tf);
					} else {
						TextField<String> xValue = new TextField<String>();
						xValue.setWidth(DEFAULT_COLUMN_WIDTH);
						emptyWidths.add(DEFAULT_COLUMN_WIDTH);
						xValue.setValue(DEFAULT_COLUMN_WIDTH.substring(0, DEFAULT_COLUMN_WIDTH.indexOf("px")));
						thirdLine.add(xValue);
						columnLabelWidths.put(l.getItemText(i), xValue);
					}
				}
			}
		}
		// add empty spaces
		if (hasSubColumns) {
			int emptyWidthsIterations = calculateEmptyWidths(w);
			for (int i = 0 ; i < emptyWidthsIterations ; i++) {
				Html empty = new Html("<div style='font-size: " + fontSize + "; font-family: sans-serif; color: #FFFFFF; font-weight: bold; text-align: center;'>&nbsp;</div>");
				empty.setWidth(emptyWidths.get(i));
				thirdLine.add(empty);
			}
		}
		removeZombieColumnLabelWidth(l);
	}
	
	private String calculateTitleWidth(OlapWindow w, OLAPParametersVo p) {
		int width = 0;
		if (!w.getOlapRuler().getColumnLabelWidths().isEmpty()) {
			for (TextField<String> tf : w.getOlapRuler().getColumnLabelWidths().values()) {
				width += Integer.valueOf(tf.getValue());
			}
		} else {
			int ys = countSelectedItems(w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues());
			if (ys > 0) {
				for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().getItemCount() ; i++) { 
					if (w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().isItemSelected(i)) {
						width += Integer.valueOf(DEFAULT_COLUMN_WIDTH.substring(0, DEFAULT_COLUMN_WIDTH.indexOf("px")));
					}
				}
				width *= countSelectedItems(w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues());
			} else {
				for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues().getItemCount() ; i++) { 
					if (w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues().isItemSelected(i)) {
						width += Integer.valueOf(DEFAULT_COLUMN_WIDTH.substring(0, DEFAULT_COLUMN_WIDTH.indexOf("px")));
					}
				}
			}
		}
		width += Integer.valueOf(p.getColumnLabelWidth().substring(0, p.getColumnLabelWidth().indexOf("px")));
		if (w.getTabPanel().getSingleDatasetPanel().getShowColTotals().getValue() || w.getTabPanel().getSingleDatasetPanel().getShowTotals().getValue()) {  
			width += Integer.valueOf(functionWidth.substring(0, functionWidth.indexOf("px")));
		}
		if (hasSubRows(w))
			width += Integer.valueOf(p.getSubRowLabelWidth().substring(0, p.getSubRowLabelWidth().indexOf("px")));
		return String.valueOf(width) + "px";
	}
	
	private String getFontSize(OlapWindow w) {
		return w.getTabPanel().getSingleDatasetPanel().getFontSize().getItemText(w.getTabPanel().getSingleDatasetPanel().getFontSize().getSelectedIndex()) + "pt";
	}

	public String getFunctionWidth() {
		return functionWidth;
	}

	public String getFunctionHeight() {
		return functionHeight;
	}

	public String getColumnLabelWidth() {
		return columnLabelWidth;
	}

	public Map<String, TextField<String>> getColumnLabelWidths() {
		return columnLabelWidths;
	}

	public TextField<String> getFunctionWidthField() {
		return functionWidthField;
	}

	public TextField<String> getColumnLabel() {
		return columnLabel;
	}

	public void setFunctionWidth(String functionWidth) {
		this.functionWidth = functionWidth;
	}

	public void setFunctionHeight(String functionHeight) {
		this.functionHeight = functionHeight;
	}

	public void setColumnLabelWidth(String columnLabelWidth) {
		this.columnLabelWidth = columnLabelWidth;
	}

	public Map<String, TextField<String>> getRowLabelHeights() {
		return rowLabelHeights;
	}

	public TextField<String> getFunctionHeightField() {
		return functionHeightField;
	}

	public String getSubRowWidth() {
		return subRowWidth;
	}

	public void setSubRowWidth(String subRowWidth) {
		this.subRowWidth = subRowWidth;
	}

	public TextField<String> getSubRowLabel() {
		return subRowLabel;
	}

	public void setSubRowLabel(TextField<String> subRowLabel) {
		this.subRowLabel = subRowLabel;
	}
	
}