package org.fao.fenix.web.modules.map.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;


public class StyleDefinition implements IsSerializable {
		
	private String fieldName;
	private	String fontName;
	private	String color;
	private	Integer fontSize;
	private	Position position;
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getFontSize() {
		return fontSize;
	}
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
			
	
	
}
