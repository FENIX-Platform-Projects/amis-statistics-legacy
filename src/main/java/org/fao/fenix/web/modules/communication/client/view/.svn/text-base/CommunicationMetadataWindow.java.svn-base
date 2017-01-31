package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;

public class CommunicationMetadataWindow extends FenixWindow {

	public static VerticalPanel panel;
	
	public CommunicationMetadataWindow() {
		panel = new VerticalPanel();
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		format();
	}
	
	public void format() {
		getCenter().setHeaderVisible(false);
		getCenter().setScrollMode(Scroll.AUTO);
		getWindow().setMaximizable(true);
		getWindow().setCollapsible(true);
		getWindow().setTitle(BabelFish.print().scopeNetwork());
		getWindow().setSize("300px", "200px");
		getWindow().setHeading(BabelFish.print().viewCommunicationResourceMetadata());
	}
	
	public void build(ResourceChildModel model) {
		
		HorizontalPanel metadataPanel = new HorizontalPanel();
		HTML label = new HTML("<b>" + BabelFish.print().id() + ": </b>");
		HTML value = new HTML(model.getId());
		metadataPanel.add(label);
		metadataPanel.add(value);
		metadataPanel.setSpacing(10);
		panel.add(metadataPanel);
		
		metadataPanel = new HorizontalPanel();
		label = new HTML("<b>" + BabelFish.print().title() + ": </b>");
		value = new HTML(model.getName());
		metadataPanel.add(label);
		metadataPanel.add(value);
		metadataPanel.setSpacing(10);
		panel.add(metadataPanel);
		
		metadataPanel = new HorizontalPanel();
		label = new HTML("<b>" + BabelFish.print().hostLabel() + ": </b>");
		value = new HTML(model.getHostLabel());
		metadataPanel.add(label);
		metadataPanel.add(value);
		metadataPanel.setSpacing(10);
		panel.add(metadataPanel);
		
		metadataPanel = new HorizontalPanel();
		label = new HTML("<b>" + BabelFish.print().host() + ": </b>");
		value = new HTML(model.getHost());
		metadataPanel.add(label);
		metadataPanel.add(value);
		metadataPanel.setSpacing(10);
		panel.add(metadataPanel);
		
		metadataPanel = new HorizontalPanel();
		label = new HTML("<b>" + BabelFish.print().digest() + ": </b>");
		value = new HTML(model.getDigest());
		metadataPanel.add(label);
		metadataPanel.add(value);
		metadataPanel.setSpacing(10);
		panel.add(metadataPanel);
		
		metadataPanel = new HorizontalPanel();
		label = new HTML("<b>" + BabelFish.print().localId() + ": </b>");
		value = new HTML(model.getLocalId());
		metadataPanel.add(label);
		metadataPanel.add(value);
		metadataPanel.setSpacing(10);
		panel.add(metadataPanel);
		
		show();
	}
	
}
