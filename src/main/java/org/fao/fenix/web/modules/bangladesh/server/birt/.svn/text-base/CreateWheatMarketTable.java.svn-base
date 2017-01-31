package org.fao.fenix.web.modules.bangladesh.server.birt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.bangladesh.BangladeshBean;
import org.fao.fenix.core.domain.bangladesh.InternationalMarketBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;



public class CreateWheatMarketTable {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	BangladeshBean bangladeshBean = null;
	
	InternationalMarketBean srwBean = null;
	
	InternationalMarketBean hrwBean = null;
	
	List<Date> srwDates;
	
	List<Date> hrwDates;
	
	String tableColor = "#000000";

	String fontSize = "6.5pt";
	
	private static DecimalFormat formatChangeValue;
	
	private static DecimalFormat formatTableValue;
	
	public static Map<Integer, String> monthLabelMap;
	
	static {
		monthLabelMap = new HashMap<Integer, String>();
		monthLabelMap.put(0, "Jan");
		monthLabelMap.put(1, "Feb");
		monthLabelMap.put(2, "Mar");
		monthLabelMap.put(3, "Apr");
		monthLabelMap.put(4, "May");
		monthLabelMap.put(5, "Jun");
		monthLabelMap.put(6, "Jul");
		monthLabelMap.put(7, "Aug");
		monthLabelMap.put(8, "Sep");
		monthLabelMap.put(9, "Oct");
		monthLabelMap.put(10, "Nov");
		monthLabelMap.put(11, "Dec");
	}
	
	
	
	public CreateWheatMarketTable(ReportDesignHandle designHandle, ElementFactory designFactory, BangladeshBean bangladeshBean){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.bangladeshBean = bangladeshBean;
		this.srwBean = bangladeshBean.getWheatSRW();
		this.hrwBean = bangladeshBean.getWheatHRW();
		formatChangeValue = new DecimalFormat("#.#");
		formatTableValue = new DecimalFormat("#");
	}
	
	
	public GridHandle buildTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1whatfutures", 1, 6);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		calculateDates();
		calculateChangeValues();
		
		/** SRW TITLE **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div style='font-weight:bold'> Chicago Board of Trade SRW Futures (USD/MT) </div> ");
		gridCellHandle.getContent().add(text);
		
		/** SRW TABLE **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildContentSRWTable());
		
		/** HRW title **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div style='font-weight:bold'> Kansas Board of Trade HRW Futures (USD/MT) </div> ");
		gridCellHandle.getContent().add(text);
		
		/** SRW TABLE **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildContentHRWTable());
		
		/** SOURCE **/
		row = (RowHandle) dataGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildSource());
		
		return dataGridHandle;
	}
	
	private GridHandle buildContentSRWTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1whatsrwfutures", 6, 5);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
//		List<Double> values = new ArrayList<Double>();
//		values.get(0).
		

		/** SET ROW 0 **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> Closing price </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(srwDates.get(0).getMonth()) + "-" + (srwDates.get(0).getYear()- 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(srwDates.get(1).getMonth()) + "-" + (srwDates.get(1).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(srwDates.get(2).getMonth()) + "-" + (srwDates.get(2).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
				
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(srwDates.get(3).getMonth()) + "-" + (srwDates.get(3).getYear() - 100 ) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(srwDates.get(4).getMonth()) + "-" + (srwDates.get(4).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		/** SET ROW 1 **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ srwBean.getPreviousDate().getDate().getDate() + "-" + monthLabelMap.get( srwBean.getPreviousDate().getDate().getMonth()) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getPreviousDate().getValues().get(srwDates.get(0))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getPreviousDate().getValues().get(srwDates.get(1))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getPreviousDate().getValues().get(srwDates.get(2))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getPreviousDate().getValues().get(srwDates.get(3))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getPreviousDate().getValues().get(srwDates.get(4))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		/** SET ROW 2 **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ srwBean.getCurrentDate().getDate().getDate() + "-" + monthLabelMap.get( srwBean.getCurrentDate().getDate().getMonth()) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getCurrentDate().getValues().get(srwDates.get(0))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getCurrentDate().getValues().get(srwDates.get(1))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getCurrentDate().getValues().get(srwDates.get(2))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getCurrentDate().getValues().get(srwDates.get(3))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(srwBean.getCurrentDate().getValues().get(srwDates.get(4))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		/** SET ROW 3 (CHANGE) **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> <b> change </b></div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(srwBean.getChangeValues().get(0)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(srwBean.getChangeValues().get(1))+ "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(srwBean.getChangeValues().get(2)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(srwBean.getChangeValues().get(3)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(srwBean.getChangeValues().get(4)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "22%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(5);
		col.setProperty("width", "13%");
		
		return dataGridHandle;
	}
	
	private GridHandle buildContentHRWTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1whathrwfutures", 6, 4);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");		
		
		/** SET ROW 0 **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> Closing price </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
	
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(hrwDates.get(0).getMonth()) + "-" + (hrwDates.get(0).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(hrwDates.get(1).getMonth()) + "-" + (hrwDates.get(1).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(hrwDates.get(2).getMonth()) + "-" + (hrwDates.get(2).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(hrwDates.get(3).getMonth()) + "-" + (hrwDates.get(3).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> "+ monthLabelMap.get(hrwDates.get(4).getMonth()) + "-" + (hrwDates.get(4).getYear() - 100) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		/** SET ROW 1 **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ hrwBean.getPreviousDate().getDate().getDate() + "-" + monthLabelMap.get( hrwBean.getPreviousDate().getDate().getMonth()) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getPreviousDate().getValues().get(hrwDates.get(0))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getPreviousDate().getValues().get(hrwDates.get(1))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getPreviousDate().getValues().get(hrwDates.get(2))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getPreviousDate().getValues().get(hrwDates.get(3))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getPreviousDate().getValues().get(hrwDates.get(4))) + "</div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		/** SET ROW 2 **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ hrwBean.getCurrentDate().getDate().getDate() + "-" + monthLabelMap.get( hrwBean.getCurrentDate().getDate().getMonth()) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getCurrentDate().getValues().get(hrwDates.get(0))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getCurrentDate().getValues().get(hrwDates.get(1))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getCurrentDate().getValues().get(hrwDates.get(2))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getCurrentDate().getValues().get(hrwDates.get(3))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatTableValue.format(hrwBean.getCurrentDate().getValues().get(hrwDates.get(4))) + "</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		/** SET ROW 3 (CHANGE) **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> <b> change </b></div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(hrwBean.getChangeValues().get(0)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(hrwBean.getChangeValues().get(1))+ "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(hrwBean.getChangeValues().get(2)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(hrwBean.getChangeValues().get(3)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> "+ formatChangeValue.format(hrwBean.getChangeValues().get(4)) + "%</div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "22%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "13%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(5);
		col.setProperty("width", "13%");
		
		return dataGridHandle;
	}
	
	
	
	private GridHandle buildSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1wheattablesource", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,fontSize, "left");
		/** TODO: THAT HAS TO BE TAKEN FROM ANOTHER DATASET or using another Indicator??? **/
		text.setContent("<div style='font-style: italic;'>Source: U.S. Wheat Associates Weekly Price Report <br>" +
														 "Conversion rates: 1 USD/bu = "+ srwBean.getBuValue() +" USD/MT </div>");
		gridCellHandle.getContent().add(text);	

		return dataGridHandle;
	}
	
	
	private void calculateDates(){  
		calculateSRWDates();
		calculateHRWDates();
	}
	
	private void calculateSRWDates(){  
//		System.out.println("calculateSRWDates");
		srwDates = new ArrayList<Date>();
		InternationalMarketBean srw = bangladeshBean.getWheatSRW();
		Set dates = srw.getCurrentDate().getValues().keySet();

		Iterator datesIter = dates.iterator();
		while (datesIter.hasNext()) {
			Date date = (Date) datesIter.next();
//			System.out.println("SRW DATES: " + date.toString());
			srwDates.add(date);
		}
	}
	
	private void calculateHRWDates(){  
//		System.out.println("calculateHRWDates");
		hrwDates = new ArrayList<Date>();
		InternationalMarketBean hrw = bangladeshBean.getWheatHRW();
		Set dates = hrw.getCurrentDate().getValues().keySet();

		Iterator datesIter = dates.iterator();
		while (datesIter.hasNext()) {
			Date date = (Date) datesIter.next();
//			System.out.println("HRW DATES: " + date.toString());
			hrwDates.add(date);
		}
	}
	
	
	private void calculateChangeValues(){  
		calculateSRWChangeValues();
		calculateHRWChangeValues();
	}
	
	private void calculateSRWChangeValues(){  
		srwBean.setChangeValues(new ArrayList<Double>());
		try {
			srwBean.getChangeValues().add(((srwBean.getCurrentDate().getValues().get(srwDates.get(0)) - srwBean.getPreviousDate().getValues().get(srwDates.get(0))) / srwBean.getPreviousDate().getValues().get(srwDates.get(0))) * 100);
			srwBean.getChangeValues().add(((srwBean.getCurrentDate().getValues().get(srwDates.get(1)) - srwBean.getPreviousDate().getValues().get(srwDates.get(1))) / srwBean.getPreviousDate().getValues().get(srwDates.get(1))) * 100);
			srwBean.getChangeValues().add(((srwBean.getCurrentDate().getValues().get(srwDates.get(2)) - srwBean.getPreviousDate().getValues().get(srwDates.get(2))) / srwBean.getPreviousDate().getValues().get(srwDates.get(2))) * 100);
			srwBean.getChangeValues().add(((srwBean.getCurrentDate().getValues().get(srwDates.get(3)) - srwBean.getPreviousDate().getValues().get(srwDates.get(3))) / srwBean.getPreviousDate().getValues().get(srwDates.get(3))) * 100);
			srwBean.getChangeValues().add(((srwBean.getCurrentDate().getValues().get(srwDates.get(4)) - srwBean.getPreviousDate().getValues().get(srwDates.get(4))) / srwBean.getPreviousDate().getValues().get(srwDates.get(4))) * 100);
			System.out.println(srwBean);
		}
		catch (Exception e) {
		}
	}
	
	private void calculateHRWChangeValues(){  
		hrwBean.setChangeValues(new ArrayList<Double>());
		try {
			hrwBean.getChangeValues().add(((hrwBean.getCurrentDate().getValues().get(hrwDates.get(0)) - hrwBean.getPreviousDate().getValues().get(hrwDates.get(0))) / hrwBean.getPreviousDate().getValues().get(hrwDates.get(0))) * 100);
			hrwBean.getChangeValues().add(((hrwBean.getCurrentDate().getValues().get(hrwDates.get(1)) - hrwBean.getPreviousDate().getValues().get(hrwDates.get(1))) / hrwBean.getPreviousDate().getValues().get(hrwDates.get(1))) * 100);
			hrwBean.getChangeValues().add(((hrwBean.getCurrentDate().getValues().get(hrwDates.get(2)) - hrwBean.getPreviousDate().getValues().get(hrwDates.get(2))) / hrwBean.getPreviousDate().getValues().get(hrwDates.get(2))) * 100);
			hrwBean.getChangeValues().add(((hrwBean.getCurrentDate().getValues().get(hrwDates.get(3)) - hrwBean.getPreviousDate().getValues().get(hrwDates.get(3))) / hrwBean.getPreviousDate().getValues().get(hrwDates.get(3))) * 100);
			hrwBean.getChangeValues().add(((hrwBean.getCurrentDate().getValues().get(hrwDates.get(4)) - hrwBean.getPreviousDate().getValues().get(hrwDates.get(4))) / hrwBean.getPreviousDate().getValues().get(hrwDates.get(4))) * 100);
			System.out.println(hrwBean);
		}
		catch (Exception e) {
		}
	}
}