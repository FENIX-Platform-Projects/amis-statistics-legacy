/*
 
 */

package org.fao.fenix.web.modules.map.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.control.LayerListController;
import org.fao.fenix.web.modules.map.client.control.vo.PDDModel;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

/**
 *
 * @author ETj
 */

/*package private*/ class DimensionPanel {

	private final ContentPanel mainpanel_ = new ContentPanel();
	private final ContentPanel panel_ = new ContentPanel();
	private final MapWindow parent_;

	public DimensionPanel(MapWindow parent)
	{
		parent_ = parent;

		mainpanel_.setLayout(new RowLayout());
		mainpanel_.setBodyBorder(false);
		mainpanel_.setHeading("Dimensions");
		mainpanel_.setIconStyle("mapDimensions");

		panel_.setLayout(new RowLayout());
		panel_.setBodyBorder(false);
		panel_.setHeading("No dimensions");
		panel_.setStyleAttribute("padding", "4px");
		mainpanel_.add(panel_);
	}
//		dummyDimPanel.setLayout(new RowLayout());
//		dummyDimPanel.setHeading("Dimensions");
//		listPanel.add(dummyDimPanel);

	public ContentPanel getGUI()
	{
		return mainpanel_;
	}

	public void reset() {
		// System.out.println("REMOVE ALL");
		panel_.removeAll();
		panel_.setHeading("No dimensions");
	}

	public void buildLayerDimensions(long geoViewId, long clientLayerId, String title, ClientProjectedDataDimension dims[]) {
		panel_.removeAll();
		if(dims==null) {
			panel_.setHeading("No dimensions");
		}
		else
		{
			panel_.setHeading(title); 

			List<ComboBox> cblist = new ArrayList<ComboBox>();

			for(ClientProjectedDataDimension pdd: dims) {
				Html label = new Html("<div style=\"padding-top:4px\"><B>"+pdd.label+"</B></div>");

				if(pdd.allowableValues.size() > 1) {
					ListStore<PDDModel> ls = new ListStore<PDDModel>();
					PDDModel selected = null;

					PDDModel mpdd = new PDDModel(ClientProjectedDataDimension.UNCONSTRAINED_CLIENT_VALUE, "--Any--");
					ls.add(mpdd);

					if(pdd.isUnconstrained) {
						selected = mpdd;						
					}

					for (String[] idval : pdd.allowableValues) {
						String clientKey = idval[0];
						String clientLabel = idval[1];
//						if(clientKey.equals("")) {
//							System.out.println("Dimension " + pdd.name + " : workaround for empty key");
//							clientKey = "__!!!__";
//						}
						if(clientLabel.equals("")) {
							System.out.println("Dimension " + pdd.name + " : workaround for empty label");
							clientLabel = "--NONE--";
						}

						mpdd = new PDDModel(clientKey, clientLabel);
						ls.add(mpdd);

						if(!pdd.isUnconstrained && pdd.currentId.equals(idval[0])) {
							selected = mpdd;
						}
					}

					System.out.println("Building dimension " + pdd.name + " '"+pdd.label+"'");
					if(selected!=null)
						System.out.println("                   " + pdd.name + "(default: >" +selected.getName() +"<   >" + selected.getLabel() + "< )" );

//					for(Entry<String, String>entry: pdd.allowableValues.entrySet()) {
//						PDDModel mpdd = new PDDModel(entry.getKey(), entry.getValue());
//						ls.add(mpdd);
//
//						if(pdd.currentId.equals(entry.getKey()))
//							selected = mpdd;
//					}

					// System.out.println("DIMESNION -> " + pdd.name);

					ComboBox<PDDModel> combo = new ComboBox<PDDModel>();
					combo.setTriggerAction(TriggerAction.ALL);
					combo.setData("dimensionName", pdd.name);
					combo.setData("dimensionOriginalValue", pdd.currentId);
					combo.setStore(ls);
					combo.setEmptyText("<NONE>");
					combo.setAllowBlank(true);
					combo.setForceSelection(true);
					combo.setDisplayField("label");
					combo.setWidth(150);

					combo.setValue(selected);
					combo.setEditable(false);

					cblist.add(combo);

					panel_.insert(combo, 0);
					panel_.insert(label, 0);
					//panel_.insert(new Label("<B>"+pdd.label+"</B>"),0);


				} // unique value for the combo: just print it as a label
				else if(pdd.allowableValues.size() == 1) {
					panel_.add(label);
					Html value = new Html("<div style=\"padding-left:10px\">"+pdd.allowableValues.get(0)[1]+"</div>");
					panel_.add(value);
				}
				else { // no values for the combo: just print it as a label
					panel_.add(label);
					Html value = new Html("<div style=\"padding-left:10px\">???</div>");
					panel_.add(value);
				}
			}

			Button b = new Button("Update", LayerListController.updateDimension(parent_, geoViewId, clientLayerId));
			b.setData("combos", cblist);
			b.setStyleAttribute("align", "right");
			panel_.add(b);
		}
		panel_.getLayout().layout();

		//panel_.layout();
	}

}
