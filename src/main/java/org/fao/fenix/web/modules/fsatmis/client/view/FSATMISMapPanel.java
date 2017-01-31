package org.fao.fenix.web.modules.fsatmis.client.view;

import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.FSATMISToolbarController;
import org.fao.fenix.web.modules.olap.client.control.OlapMapController;
import org.fao.fenix.web.modules.olap.client.view.OlapTool;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class FSATMISMapPanel extends FenixToolBase {

	private VerticalPanel wrapper;
	
	private Button createMapButton;
	
	private Button selectFilters;
	
	private ListBox xDropDown = new ListBox();
	
	private ListBox zDropDown = new ListBox();
	
	private ListBox yDropDown = new ListBox();
	
	private ListBox wDropDown = new ListBox();
	
	private ListBox geoDropDown = new ListBox();
	
	private boolean isFirstOpening;
	
	public FSATMISMapPanel() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		isFirstOpening = true;
	}
	
	public void build(OLAPParametersVo params, OlapTool olapTool, FSATMISTabPanel fsatmisTabPanel) {
		if (isFirstOpening) {
			isFirstOpening = false;
			wrapper.add(createGeoDropDown(params, olapTool, fsatmisTabPanel));
			buildCenterPanel();
		} else {
			reset(params, olapTool, fsatmisTabPanel);
		}
	}
	
	public void addFilterDropDowns(OLAPParametersVo p, Map<String, String> values, ListBox list, String dimensionToAlter) {
		HorizontalPanel dropDownPanel = new HorizontalPanel();
		HTML label = new HTML("<b>"+BabelFish.print().mapFilter()+": </b>");
		label.setWidth("150px");
		list.addChangeListener(OlapMapController.alterOlapMapParameters(p, dimensionToAlter));
		list.addItem(BabelFish.print().pleaseSelect() + "...");
		for (String key : values.keySet())
			list.addItem(values.get(key), key);
		list.setWidth("350px");
		dropDownPanel.add(label);
		dropDownPanel.add(list);
		dropDownPanel.setSpacing(10);
		wrapper.add(dropDownPanel);
		wrapper.getLayout().layout();
	}
	
	public void reset(OLAPParametersVo params, OlapTool olapTool, FSATMISTabPanel fsatmisTabPanel) {
		for (int i = wrapper.getItemCount() - 1 ; i >= 0 ; i--)
			wrapper.remove(wrapper.getWidget(i));
		for (int i = xDropDown.getItemCount() - 1 ; i >= 0 ; i--)
			xDropDown.removeItem(i);
		for (int i = zDropDown.getItemCount() - 1 ; i >= 0 ; i--)
			zDropDown.removeItem(i);
		for (int i = yDropDown.getItemCount() - 1 ; i >= 0 ; i--)
			yDropDown.removeItem(i);
		for (int i = wDropDown.getItemCount() - 1 ; i >= 0 ; i--)
			wDropDown.removeItem(i);
		for (int i = geoDropDown.getItemCount() - 1 ; i >= 0 ; i--)
			geoDropDown.removeItem(i);
		isFirstOpening = true;
		geoDropDown.setEnabled(true);
		selectFilters.setEnabled(true);
		build(params, olapTool, fsatmisTabPanel);
	}
	
	private HorizontalPanel createGeoDropDown(OLAPParametersVo p, OlapTool olapTool, FSATMISTabPanel fsatmisTabPanel) {
		HorizontalPanel geoDropDownPanel = new HorizontalPanel();
		HTML label = new HTML("<b>"+BabelFish.print().geoDimension()+": </b>");
		label.setWidth("150px");
		geoDropDown.addItem(p.getXLabel(), p.getX());
		geoDropDown.addItem(p.getZLabel(), p.getZ());
		geoDropDown.setWidth("350px");
		if (!p.getYLabels().isEmpty())
			geoDropDown.addItem(p.getYLabel(), p.getY());
		if (!p.getWLabels().isEmpty())
			geoDropDown.addItem(p.getWLabel(), p.getW());
		boolean isCore = false;
		for (int i = 0 ; i < geoDropDown.getItemCount() ; i++)
			if (geoDropDown.getValue(i).equalsIgnoreCase("featureCode")) {
				geoDropDown.setItemSelected(i, true);
				isCore = true;
				break;
			}
		
		if (!isCore)
			OlapMapController.selectFlexGeoDimension(p.getDataSourceId(), geoDropDown); // TODO test
		
		selectFilters = new Button(BabelFish.print().selectFilters(), FSATMISToolbarController.selectFilters(this, p, olapTool, fsatmisTabPanel));
		geoDropDownPanel.add(label);
		geoDropDownPanel.add(geoDropDown);
		geoDropDownPanel.add(selectFilters);
		geoDropDownPanel.setSpacing(10);
		return geoDropDownPanel;
	}
	
	public HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		createMapButton = new Button(BabelFish.print().createMap());
		createMapButton.setMinWidth(700);
		buttonsPanel.add(createMapButton);
		return buttonsPanel;
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(wrapper);
		getCenterData().setSize(1000);
		addCenterPartToWindow();
		getToolBase().setHeaderVisible(false);
		getToolBase().setSize(1000, 700);
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public ListBox getxDropDown() {
		return xDropDown;
	}

	public ListBox getzDropDown() {
		return zDropDown;
	}

	public ListBox getyDropDown() {
		return yDropDown;
	}

	public ListBox getwDropDown() {
		return wDropDown;
	}

	public ListBox getGeoDropDown() {
		return geoDropDown;
	}

	public Button getSelectFilters() {
		return selectFilters;
	}

	public Button getCreateMapButton() {
		return createMapButton;
	}
	
}