package org.fao.fenix.web.modules.designer.client.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;

public class DesignerGridPanel {

	private Grid grid;
	
	private HorizontalPanel panel;
	
	public final static int PORTRAIT_ROWS = 28;
	
	public final static int PORTRAIT_COLS = 19;
	
	public final static int LANDSCAPE_ROWS = 19;
	
	public final static int LANDSCAPE_COLS = 28;
	
	private Map<Integer, List<String>> selectedPixelsMap = new HashMap<Integer, List<String>>();
	
	private List<String> selectedPixels = new ArrayList<String>();
		
	public DesignerGridPanel() {
		panel = new HorizontalPanel();
	}
	
	public void addSelectedPixel(String coordinates) {
		if (selectedPixels == null)
			selectedPixels = new ArrayList<String>();
		selectedPixels.add(coordinates);
	}
	
	public void addSelectedPixelsMapEntry(Integer boxID, List<String> selectedPixels) {
		if (selectedPixelsMap == null)
			selectedPixelsMap = new HashMap<Integer, List<String>>();
		selectedPixelsMap.put(boxID, selectedPixels);
	}
	
	public HorizontalPanel buildLandscape() {
		grid = new Grid(LANDSCAPE_ROWS, LANDSCAPE_COLS);
		for (int row = 0; row < LANDSCAPE_ROWS; row++) {
			for (int col = 0; col < LANDSCAPE_COLS; col++) {
				CheckBox cb = new CheckBox();
				cb.setSize("10px", "10px");
				cb.setTitle(col + ":" + row); //  set the coordinates
//				cb.addClickHandler(DesignerController.pixel(this, cb));
				cb.setEnabled(false);
				grid.setWidget(row, col, cb);
				panel.setData(col + ":" + row, cb);
			}
		}
		grid.setSize("480px", "385px");
		panel.add(grid);
		return panel;
	}
	
	public HorizontalPanel buildPortrait() {
		grid = new Grid(PORTRAIT_ROWS, PORTRAIT_COLS);
		for (int row = 0; row < PORTRAIT_ROWS; ++row) {
			for (int col = 0; col < PORTRAIT_COLS; ++col) {
				CheckBox cb = new CheckBox();
				cb.setSize("10px", "10px");
				cb.setTitle(col + ":" + row); //  set the coordinates
//				cb.addClickHandler(DesignerController.pixel(this, cb));
				cb.setEnabled(false);
				grid.setWidget(row, col, cb);
				panel.setData(col + ":" + row, cb);
			}
		}
		grid.setSize("385px", "480px");
		panel.add(grid);
		return panel;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}

	public Grid getGrid() {
		return grid;
	}

	public Map<Integer, List<String>> getSelectedPixelsMap() {
		return selectedPixelsMap;
	}

	public List<String> getSelectedPixels() {
		return selectedPixels;
	}

	public void setSelectedPixelsMap(Map<Integer, List<String>> selectedPixelsMap) {
		this.selectedPixelsMap = selectedPixelsMap;
	}

	public void setSelectedPixels(List<String> selectedPixels) {
		this.selectedPixels = selectedPixels;
	}
	
}