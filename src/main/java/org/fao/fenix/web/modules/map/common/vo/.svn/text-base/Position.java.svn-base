package org.fao.fenix.web.modules.map.common.vo;

public enum Position {

	TopLeft,
	Top,
	TopRight,
	Left,
	Center,
	Right,
	BottomLeft,
	Bottom,
	BottomRight;
	
	public static Position valueOfIgnoreCase(String name) {
		for(Position type : Position.values()) 
			if(type.toString().equalsIgnoreCase(name))
				return type;
		return null;
	}
	


}
