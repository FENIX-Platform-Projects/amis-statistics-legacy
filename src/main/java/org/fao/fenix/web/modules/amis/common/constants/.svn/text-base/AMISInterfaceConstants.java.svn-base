package org.fao.fenix.web.modules.amis.common.constants;


public class AMISInterfaceConstants {

	// TODO: check the CSS to get the right width
	public static Integer INTERFACE_WIDTH = 1100;


	public final static String BOX_MENU_HEIGHT = "45";

	// This is the center panel where the objects are visualized
		public static Integer CENTER_PANEL_WIDTH = (INTERFACE_WIDTH) - 30;

		// This is the center panel where the objects are visualized
		public static Integer CENTER_PANEL_HORIZONTAL_SPACING = 25;

		// This is the center panel where the objects are visualized
		public static Integer CENTER_PANEL_VERTICAL_SPACING = 30;



		// Defaults Dimensions (if the width and the height of the objects are not defined in the XML)
		// CHART
		//public static  String CHART_WIDTH = "500";
		public static  String CHART_WIDTH = "1000";
		public static String CHART_HEIGHT = "250";
		public static String CHART_HEIGHT_BIG = "300";

		// MAP
		public static  String MAP_WIDTH = "800";

		public static String MAP_HEIGHT = "300";


		// TABLE
		public static  String TABLE_WIDTH = "400";

		public static String TABLE_HEIGHT = "200";


		public static String STATISTICAL_NOTES_LINK_FIRST_PART = "For more detailed definitions see  ";
		
		public static String STATISTICAL_NOTES_LINK_SECOND_PART = " Statistical Notes";
	/**
	 *
	 * TODO: Change calculate removing the height of the menu from the panel
	 * 		 and not like now adding it...
	 *
	 * @param height
	 * @return
	 */
	public static String calculateHeightWithBoxMenu(String height) {

//		System.out.println("height: " + height);
//		String current = height.substring(0, height.length());
//		String boxMenu = BOX_MENU_HEIGHT.substring(0, BOX_MENU_HEIGHT.length());
		String current = String.valueOf(Integer.parseInt(height) + (Integer.parseInt(BOX_MENU_HEIGHT))) + "px";

		System.out.println("height: " + height + " | " + current);
		return current;
	}


//	public final static String MEDIUM_WIDTH_PIXEL = "750";
//	public final static String MEDIUM_HEIGHT_PIXEL = "270";

	public final static String MEDIUM_WIDTH = "750";
	public final static String MEDIUM_HEIGHT = "270";

//	public final static String SMALL_WIDTH_PIXEL = "530px";
//	public final static String SMALL_HEIGHT_PIXEL = "250px";

	public final static String SMALL_WIDTH = "530";
	public final static String SMALL_HEIGHT = "250";



}
