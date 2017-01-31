package org.fao.fenix.web.modules.designer.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class DesignerTabItem {

	private TabItem tabItem;
	
	private ContentPanel wrapper;
	
	private VerticalPanel boxWrapper;
	
	private DesignerTabItemToolbar designerTabItemToolbar;
	
	private DesignerGridWindow designerGridWindow;
	
	private List<DesignerBox> boxes;
	
	public DesignerTabItem(String tabItemHeader, int pageNumber) {
		tabItem = new TabItem(tabItemHeader + " #" + pageNumber);
		designerTabItemToolbar = new DesignerTabItemToolbar();
		wrapper = new ContentPanel(new FitLayout());
		wrapper.setHeaderVisible(false);
		boxWrapper = new VerticalPanel();
		boxWrapper.setHeight("400px");
		boxWrapper.setScrollMode(Scroll.AUTO);
		designerGridWindow = new DesignerGridWindow();
		boxes = new ArrayList<DesignerBox>();
	}
	
	public TabItem buildPortrait(int pageNumber) {
		wrapper.setTopComponent(designerTabItemToolbar.build(this));
		wrapper.add(boxWrapper);
		tabItem.add(wrapper);
		designerGridWindow.buildPortrait(pageNumber);
		tabItem.setClosable(true);
		tabItem.setIconStyle("portrait");
		return tabItem;
	}
	
	public TabItem buildLandscape(int pageNumber) {
		wrapper.setTopComponent(designerTabItemToolbar.build(this));
		wrapper.add(boxWrapper);
		tabItem.add(wrapper);
		designerGridWindow.buildLandscape(pageNumber);
		tabItem.setClosable(true);
		tabItem.setIconStyle("landscape");
		return tabItem;
	}
	
	public DesignerBox addBox() {
		DesignerBox box = new DesignerBox();
		boxWrapper.add(box.build(this));
		if (boxWrapper.getLayout() != null)
			boxWrapper.getLayout().layout();
		wrapper.getLayout().layout();
		boxes.add(box);
		return box;
	}

	public DesignerGridWindow getDesignerGridWindow() {
		return designerGridWindow;
	}

	public VerticalPanel getBoxWrapper() {
		return boxWrapper;
	}

	public List<DesignerBox> getBoxes() {
		return boxes;
	}

	public ContentPanel getWrapper() {
		return wrapper;
	}
	
}