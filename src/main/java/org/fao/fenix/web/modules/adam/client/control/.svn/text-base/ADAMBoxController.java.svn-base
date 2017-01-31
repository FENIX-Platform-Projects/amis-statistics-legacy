package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;

import com.google.gwt.user.client.Window;

public class ADAMBoxController {
	
	static Integer minimumWidth = 1050;
	
	static Integer panelButtonDimension = 185;
	
	static Integer linksWidthSize = 200;

	public static void resizeObjects() {
		
		Integer width = Window.getClientWidth();
		Integer height = Window.getClientHeight();
		
		// check the minumum size 
		//System.out.println("width: " + width);
		
		if ( width > minimumWidth ) {

			// calculate the dimension of the objects SMALL BOX WIDTH	
			Integer witdhAdamViewSmallBox = (width - (30 + 30 + 30)) / 2;				
			//System.out.println("CHART_BLEA_WIDTH: " + witdhAdamViewSmallBox);	
			
			Integer smallLabelSizeAdamView = witdhAdamViewSmallBox - panelButtonDimension;			
			setSmallLabelSizeAdamView(smallLabelSizeAdamView);
			setSmallChartTableSizeAdamView(witdhAdamViewSmallBox - 40);
			setSmallBoxSizeAdamView(witdhAdamViewSmallBox);
			
				
//			// calculate the dimension of the objects BIG BOX WIDTH	
			Integer witdhAdamViewBox = (width - (30 + 30 + 10));	
			Integer bigLabelSizeAdamView = witdhAdamViewBox - panelButtonDimension;	
			setBigLabelSizeAdamView(bigLabelSizeAdamView);
			
			// TODO: the links are actually removed...so the size of the big content is without the links
			//       on the right side of the box (in case care also of the links panel)
			
//			setBigChartTableSizeAdamView(witdhAdamViewBox - (30 + linksWidthSize));
			setBigChartTableSizeAdamView(witdhAdamViewBox - (25));
			setBigBoxSizeAdamView(witdhAdamViewBox);
			
		}
	}
	
	// SMALL 
	
	private static void setSmallLabelSizeAdamView(Integer smallLabelSizeAdamView ) {
		//System.out.println("BEFORE - ADAMConstants.SMALL_MENU_GAP_WIDTH: " + ADAMConstants.SMALL_MENU_GAP_WIDTH);
		
		ADAMConstants.SMALL_MENU_GAP_WIDTH = smallLabelSizeAdamView.toString() + "px";
		
		//System.out.println("NOW - ADAMConstants.SMALL_MENU_GAP_WIDTH: " + ADAMConstants.SMALL_MENU_GAP_WIDTH);
	}
	
	
	private static void setSmallChartTableSizeAdamView(Integer size ) {
		//System.out.println("BEFORE - ADAMConstants.SMALL_TABLE_CHART_WIDTH: " + ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		
		ADAMConstants.SMALL_TABLE_CHART_WIDTH = size.toString();
		
		//System.out.println("NOW - ADAMConstants.SMALL_TABLE_CHART_WIDTH: " + ADAMConstants.SMALL_TABLE_CHART_WIDTH);
	}
	
	private static void setSmallBoxSizeAdamView(Integer size ) {
		//System.out.println("BEFORE - ADAMConstants.SMALL_BOX_WIDTH: " + ADAMConstants.SMALL_BOX_WIDTH);
		
		ADAMConstants.SMALL_BOX_WIDTH = size.toString();
		
		//System.out.println("NOW - ADAMConstants.SMALL_BOX_WIDTH: " + ADAMConstants.SMALL_BOX_WIDTH);
	}
	
	
	// BIG 
	
	private static void setBigLabelSizeAdamView(Integer size ) {
		//System.out.println("BEFORE - ADAMConstants.BIG_MENU_GAP_WIDTH: " + ADAMConstants.BIG_MENU_GAP_WIDTH);
		
		ADAMConstants.BIG_MENU_GAP_WIDTH = size.toString() + "px";
		
		//System.out.println("NOW - ADAMConstants.BIG_MENU_GAP_WIDTH " + ADAMConstants.BIG_MENU_GAP_WIDTH);
	}
	
	
	private static void setBigChartTableSizeAdamView(Integer size ) {
		//System.out.println("BEFORE - ADAMConstants.BIG_TABLE_CHART_WIDTH: " + ADAMConstants.BIG_TABLE_CHART_WIDTH);
		
		ADAMConstants.BIG_TABLE_CHART_WIDTH = size.toString();
		
		//System.out.println("NOW - ADAMConstants.BIG_TABLE_CHART_WIDTH: " + ADAMConstants.BIG_TABLE_CHART_WIDTH);
	}
	
	private static void setBigBoxSizeAdamView(Integer size ) {
		//System.out.println("BEFORE - ADAMConstants.BIG_BOX_WIDTH: " + ADAMConstants.BIG_BOX_WIDTH);
		
		ADAMConstants.BIG_BOX_WIDTH = size.toString();
		
		//System.out.println("NOW - ADAMConstants.BIG_BOX_WIDTH: " + ADAMConstants.BIG_BOX_WIDTH);
	}
	

}
