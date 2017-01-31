package org.fao.fenix.web.modules.core.client.control.menu;

public class FenixMenuController {

	public static FenixCommandPool commandPool;
	
	public FenixMenuView fenixMenuView;
	
	public FenixMenuBuilder fenixMenuBuilder;

	public void execute() {
		commandPool = new FenixCommandPool();
		fenixMenuView = new FenixMenuView();
		fenixMenuView.build();
		fenixMenuBuilder = new FenixMenuBuilder();
//		fenixMenuBuilder.buildMainMenu();
//		fenixMenuBuilder.buildSecondary();
	}

}
