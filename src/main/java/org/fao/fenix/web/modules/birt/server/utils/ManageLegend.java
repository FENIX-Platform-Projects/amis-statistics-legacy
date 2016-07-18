package org.fao.fenix.web.modules.birt.server.utils;

import org.eclipse.birt.chart.model.attribute.Direction;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.layout.Legend;

public class ManageLegend {
	
	static public void modifyLegend(String position, Legend lg){
		
		if (position.equals("Right")){
			lg.setPosition(Position.RIGHT_LITERAL);
		} else if (position.equals("Left")){
			lg.setPosition(Position.LEFT_LITERAL);
		} else if (position.equals("Above")){
			lg.setPosition(Position.ABOVE_LITERAL);
		} else if (position.equals("Below")){
			lg.setPosition(Position.BELOW_LITERAL);
			lg.setDirection(Direction.LEFT_RIGHT_LITERAL);
		}
	}

}
