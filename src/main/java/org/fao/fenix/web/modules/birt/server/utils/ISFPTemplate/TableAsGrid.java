package org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate;

import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;

public class TableAsGrid {

	public static GridHandle getExtraHeader(List<String> data, ReportDesignHandle design, String firstCol, List<String> otherCol, String tableType){
		GridHandle table=null;
		try {
			table = design.getElementFactory().newGridItem("Table", data.size(), 1);
			table.setWidth("99%");
			
			((ColumnHandle)table.getColumns().get(0)).getWidth().setStringValue(firstCol);
			
			for (int c=1; c<data.size(); c++){
				((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue((otherCol.get(c-1) + "in"));
			}
			
			TextItemHandle text;
			for (int i=0; i<data.size(); i++){
				text=design.getElementFactory().newTextItem("");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				text.setContent(data.get(i));
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
				
				if (tableType.equals("Cereal Production")){
					((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.PADDING_BOTTOM_PROP , "0.2cm");
					//((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_LEFT_PROP , "0.2cm");
					//((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_RIGHT_PROP , "0.2cm");
					((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.PADDING_TOP_PROP , "0.2cm");
				}
				
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.FONT_SIZE_PROP, "8px");
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).getContent().add(text);
						
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return table;
		
	}
	
	public static GridHandle getExtraHeader(List<String> data, ReportDesignHandle design, String firstCol, String otherCol, String tableType){
		GridHandle table=null;
		try {
			table = design.getElementFactory().newGridItem("Table", data.size(), 1);
			table.setWidth("99%");
			
			for (int c=0; c<data.size(); c++){
				if (c==0) ((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue(firstCol);
				else ((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue(otherCol);
			}
			
			TextItemHandle text;
			for (int i=0; i<data.size(); i++){
				text=design.getElementFactory().newTextItem("");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				text.setContent(data.get(i));
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
				
				if (tableType.equals("Cereal Production")){
					((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.PADDING_BOTTOM_PROP , "0.2cm");
					//((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_LEFT_PROP , "0.2cm");
					//((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_RIGHT_PROP , "0.2cm");
					((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.PADDING_TOP_PROP , "0.2cm");
				}
				
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.FONT_SIZE_PROP, "8px");
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
				
				((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(i)).getContent().add(text);
						
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return table;
		
	}
	
	public static GridHandle getTable(List<List<String>> data, ReportDesignHandle design, String firstCol, String otherCol, String fontsize, String tableType){
		GridHandle table=null;
		try {
			table = design.getElementFactory().newGridItem("Table", data.get(0).size(), data.size());
			table.setWidth("99%");
			
			for (int c=0; c<data.get(0).size(); c++){
				if (c==0) ((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue(firstCol);
				else ((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue(otherCol);
			}
			
			
			TextItemHandle text;
			for (int i=0; i<data.size(); i++){
				for (int j=0; j<data.get(i).size(); j++){
					text=design.getElementFactory().newTextItem("");
					text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					text.setContent(data.get(i).get(j));
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
					
					if (tableType.equals("Cereal Production")){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_BOTTOM_PROP , "0.2cm");
						//((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_LEFT_PROP , "0.2cm");
						//((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_RIGHT_PROP , "0.2cm");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.PADDING_TOP_PROP , "0.2cm");
					}
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, fontsize);
					if (i==0){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
					} else if ((i>0) && (j==0)){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
					} else if ((i>0) && (j>0)){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
					}
					
					
					
					/*
					if (j==0){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)). getWidth().setStringValue(firstRow);
					} else {
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)). getWidth().setStringValue(otherRow);
					}
					*/
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).getContent().add(text);
				
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return table;
		
		
	}
	
}
