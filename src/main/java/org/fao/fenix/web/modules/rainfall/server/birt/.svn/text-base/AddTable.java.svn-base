package org.fao.fenix.web.modules.rainfall.server.birt;

import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;

public class AddTable {
	
	public static GridHandle getTable(List<List<String>> data, ReportDesignHandle design){
		GridHandle table=null;
		try {
			table = design.getElementFactory().newGridItem("Table", data.get(0).size(), data.size());
			table.setWidth("98%");
			
//			for (int c=0; c<data.get(0).size(); c++){
//				if (c==0) ((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue(firstCol);
//				else ((ColumnHandle)table.getColumns().get(c)).getWidth().setStringValue(otherCol);
//			}
			
			
			TextItemHandle text;
			for (int i=0; i<data.size(); i++){
				for (int j=0; j<data.get(i).size(); j++){
					text=design.getElementFactory().newTextItem("");
					text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					text.setContent(data.get(i).get(j));
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
//					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
//					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
//					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
//					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "10px");
					
//					if (i == 0 && j == 0) {
//						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//					} else if (i == 0 && j > 0) {
//						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
//					}
					
					if (i==0) {
//						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "13px");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#15428B");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.COLOR_PROP, "#FFFFFF");
					} 
					
					if (j == 0){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
					} 
					
					if (j > 0){
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
					} 
					
					if (i > 0 && i % 2 != 0) {
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "12px");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#FFFFFF");
					} else if (i > 0 && i % 2 == 0) {
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.FONT_SIZE_PROP, "12px");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
						((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#d0dded");
					}
					
					((CellHandle) ((RowHandle) table.getRows().get(i)).getCells().get(j)).getContent().add(text);
				
					
				}
			}
			
			table.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return table;
		
		
	}

}
