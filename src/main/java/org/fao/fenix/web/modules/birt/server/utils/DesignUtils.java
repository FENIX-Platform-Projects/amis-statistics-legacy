package org.fao.fenix.web.modules.birt.server.utils;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;

public class DesignUtils {

	public static void addLine(ReportDesignHandle designHandle, ElementFactory designFactory, String color, String size) throws SemanticException {
		// add space
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		// the line below doesn't work when you try to export that
		// report - birt bug
		// text.setContent("<hr style='width: 100%; height: 2px;'>");
		// Workaround
//		text.setContent("<br><div style='width: 100%; height:"+ size +"px; background-color:'"+ color +";'>&nbsp;</div><br>");
		text.setContent("<br><div style='width: 100%; height:" + size + "; background-color:"+ color +";'>&nbsp;</div><br>");

		designHandle.getBody().add(text);
	}
	
	public static void addSpace(ReportDesignHandle designHandle) throws SemanticException {
		// add space
		TextItemHandle spaceOne = designHandle.getElementFactory().newTextItem("");
		spaceOne.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceOne.setHeight("0.2cm");
		designHandle.getBody().add(spaceOne);
	}
	
	public static void setBorderGrid(GridHandle grid, String color) throws SemanticException {
		grid.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		grid.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setBorderGrid(TextItemHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	

	public static void setBorderGrid(TextItemHandle gridCellHandle, String color, String size) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,size);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,size);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,size);
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,size);
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
//	
	public static void setCenterGrid(TextItemHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
	}
	
	public static void setTopBorderGrid(TextItemHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
	}
	
	public static void setTopBorderGrid(GridHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
	}
	
	public static void setBottomBorderGrid(TextItemHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setLeftBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setRightBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setCenterGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
	}
	
	public static void setTopBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
	}
	
	public static void setOnlyTopBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
	}
	
	public static void setOnlyBottomBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setTopBottomBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setBottomBorderGrid(CellHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	
	public static TextItemHandle createText(ReportDesignHandle designHandle, TextItemHandle text, String fontSize, String align) throws SemanticException {
		text = designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, fontSize);
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, align);
//		text.setProperty(StyleHandle.TEXT_UNDERLINE_PROP, );
		return text;
	}
	
	
	public static void setBorderRow(RowHandle handle, String color) throws SemanticException {
		handle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		handle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		handle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		handle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		handle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		handle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		handle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		handle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		handle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		handle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		handle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		handle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setBottomBorderGrid(GridHandle gridCellHandle, String color) throws SemanticException {
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		gridCellHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		gridCellHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setBorderGrid(ColumnHandle grid, String color) throws SemanticException {
		grid.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		grid.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
	public static void setBorderGrid(RowHandle grid, String color) throws SemanticException {
		grid.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		grid.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
		grid.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		grid.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, color);
		grid.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, color);
	}
	
}