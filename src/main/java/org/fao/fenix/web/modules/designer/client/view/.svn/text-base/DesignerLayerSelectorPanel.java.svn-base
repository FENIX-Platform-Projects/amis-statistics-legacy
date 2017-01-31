package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class DesignerLayerSelectorPanel {

	private VerticalPanel wrapper;
	
	private TextField<String> layerNameField;
	
	private Long layerID;
	
	private ResourceType layerType;
	
	private CheckBox addBaseLayer;
	
	private CheckBox addGaul0;
	
	private CheckBox addGaul1;
	
	private CheckBox addGaul2;
	
	private CheckBox zoomTo;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private IconButton changeLayerButton;
	
	private final int SPACING = 10;
	
	private final String LABEL_WIDTH = "150px";
	
	private final String FIELD_WIDTH = "240px";
	
	public DesignerLayerSelectorPanel() {
		wrapper = new VerticalPanel();
		layerNameField = new TextField<String>();
		addBaseLayer = new CheckBox();
		addGaul0 = new CheckBox();
		addGaul1 = new CheckBox();
		addGaul2 = new CheckBox();
		zoomTo = new CheckBox();
		gaulStore = new ListStore<GaulModelData>();
		MEController.fillGaulStore(gaulStore);
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		changeLayerButton = new IconButton("sld");
	}
	
	public DesignerLayerSelectorPanel(Long layerID, String layerName) {
		wrapper = new VerticalPanel();
		layerNameField = new TextField<String>();
		addBaseLayer = new CheckBox();
		addGaul0 = new CheckBox();
		addGaul1 = new CheckBox();
		addGaul2 = new CheckBox();
		zoomTo = new CheckBox();
		gaulStore = new ListStore<GaulModelData>();
		MEController.fillGaulStore(gaulStore);
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		changeLayerButton = new IconButton("sld");
		this.setLayerID(layerID);
		this.getLayerNameField().setValue(layerName);
	}
	
	public VerticalPanel build() {
		wrapper.add(buildLayerNamePanel());
//		wrapper.add(buildBaseLayerPanel());
		wrapper.add(buildAddGAUL0Panel());
		wrapper.add(buildAddGAUL1Panel());
		wrapper.add(buildAddGAUL2Panel());
		wrapper.add(buildZoomToPanel());
		return wrapper;
	}
	
	private HorizontalPanel buildLayerNamePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		layerNameField.setWidth(FIELD_WIDTH);
		layerNameField.setEmptyText("Selected Layer Name");
		p.add(layerNameField);
		changeLayerButton.setToolTip("Click To Change the Selected Layer");
		p.add(changeLayerButton);
		return p;
	}
	
	private HorizontalPanel buildBaseLayerPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		addBaseLayer.setBoxLabel("Add Base Layer");
		addBaseLayer.setValue(true);
		p.add(addBaseLayer);
		return p;
	}
	
	private HorizontalPanel buildAddGAUL0Panel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		addGaul0.setBoxLabel("Add Countries Boundaries");
		addGaul0.setValue(true);
		p.add(addGaul0);
		return p;
	}
	
	private HorizontalPanel buildAddGAUL1Panel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		addGaul1.setBoxLabel("Add States Boundaries");
		addGaul1.setValue(true);
		p.add(addGaul1);
		return p;
	}
	
	private HorizontalPanel buildAddGAUL2Panel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		addGaul2.setBoxLabel("Add Provinces Boundaries");
		addGaul2.setValue(true);
		p.add(addGaul2);
		return p;
	}
	
	private HorizontalPanel buildZoomToPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		zoomTo.setBoxLabel("Zoom To");
		p.add(zoomTo);
		p.add(gaulList);
		return p;
	}

	public TextField<String> getLayerNameField() {
		return layerNameField;
	}

	public Long getLayerID() {
		return layerID;
	}

	public ResourceType getLayerType() {
		return layerType;
	}

	public CheckBox getAddBaseLayer() {
		return addBaseLayer;
	}

	public CheckBox getAddGaul0() {
		return addGaul0;
	}

	public CheckBox getAddGaul1() {
		return addGaul1;
	}

	public CheckBox getAddGaul2() {
		return addGaul2;
	}

	public ListStore<GaulModelData> getGaulStore() {
		return gaulStore;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public IconButton getChangeLayerButton() {
		return changeLayerButton;
	}

	public CheckBox getZoomTo() {
		return zoomTo;
	}

	public void setLayerID(Long layerID) {
		this.layerID = layerID;
	}
	
}