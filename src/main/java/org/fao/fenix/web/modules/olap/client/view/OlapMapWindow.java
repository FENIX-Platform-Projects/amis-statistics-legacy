package org.fao.fenix.web.modules.olap.client.view;

import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapMapController;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class OlapMapWindow extends FenixWindow {

	private VerticalPanel wrapper;
	
	private Button createMapButton;	
	private Button selectFilters;
	
	private ListBox xDropDown = new ListBox();	
	private ListBox zDropDown = new ListBox();
	private ListBox yDropDown = new ListBox();
	private ListBox wDropDown = new ListBox();
	
	private ListBox geoDropDown = new ListBox();
	
	private boolean isFirstOpening;
	
	public OlapMapWindow() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		isFirstOpening = true;
	}
	
	public void build(OLAPParametersVo params, OlapWindow olapWindow) {
		if (isFirstOpening) {
			isFirstOpening = false;
			wrapper.add(createGeoDropDown(params, olapWindow));
			buildCenterPanel();
			show();
		} else {
			reset(params, olapWindow);
		}
	}
	
	public void addFilterDropDowns(OLAPParametersVo p, Map<String, String> values, ListBox list, String dimensionToAlter) {
		HorizontalPanel dropDownPanel = new HorizontalPanel();
		HTML label = new HTML("<b>"+BabelFish.print().mapFilter()+": </b>");
		label.setWidth("100px");
		list.addChangeListener(OlapMapController.alterOlapMapParameters(p, dimensionToAlter));
		list.addItem(BabelFish.print().pleaseSelect() + "...");
		for (String key : values.keySet())
			list.addItem(values.get(key), key);
		list.setWidth("300px");
		dropDownPanel.add(label);
		dropDownPanel.add(list);
		dropDownPanel.setSpacing(10);
		wrapper.add(dropDownPanel);
		wrapper.getLayout().layout();
	}
	
	public void reset(OLAPParametersVo params, OlapWindow olapWindow) {
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
		build(params, olapWindow);
	}
	
	private HorizontalPanel createGeoDropDown(OLAPParametersVo p, OlapWindow olapWindow) {
		HorizontalPanel geoDropDownPanel = new HorizontalPanel();
		HTML label = new HTML("<b>"+BabelFish.print().geoDimension()+": </b>");
		label.setWidth("100px");
		geoDropDown.addItem(p.getXLabel(), p.getX());
		geoDropDown.addItem(p.getZLabel(), p.getZ());
		geoDropDown.setWidth("300px");
		if (!p.getYLabels().isEmpty())
			geoDropDown.addItem(p.getYLabel(), p.getY());
		if (!p.getWLabels().isEmpty())
			geoDropDown.addItem(p.getWLabel(), p.getW());
		for (int i = 0 ; i < geoDropDown.getItemCount() ; i++) {
			if (geoDropDown.getValue(i).equalsIgnoreCase("featureCode")) {
				geoDropDown.setItemSelected(i, true);
				break;
			}
		} 
		
		OlapMapController.selectFlexGeoDimension(p.getDataSourceId(), geoDropDown); // TODO test	
		
		selectFilters = new Button(BabelFish.print().selectFilters(), OlapMapController.selectFilters(this, p, olapWindow));
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
		createMapButton.setMinWidth(550);
		buttonsPanel.add(createMapButton);
		return buttonsPanel;
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(wrapper);
		getCenterData().setSize(500);
		addCenterPartToWindow();
		getWindow().setHeading(BabelFish.print().projectDataOnMap());
		getWindow().setSize("500px", "400px");
		getWindow().setModal(true);
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