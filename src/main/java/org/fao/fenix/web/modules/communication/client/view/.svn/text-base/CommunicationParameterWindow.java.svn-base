package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class CommunicationParameterWindow extends FenixWindow {

	public VerticalPanel panel = new VerticalPanel();
	
	public TextBox hostText;	
	public TextBox groupText;	
	public Button setParameters;	
	public HTML hostLabel;	
	public HTML groupLabel;
	
	public CommunicationParameterWindow() {
		
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
		getWindow().setSize("300px", "200px");
		getWindow().setHeading(BabelFish.print().communicationModuleSettings());
	}
	
	public void build() {
		
		hostText = new TextBox();
		groupText = new TextBox();
		setParameters = new Button(BabelFish.print().setParameters());
		hostLabel = new HTML("<b>" + BabelFish.print().hostLabel() + "</b>");
		groupLabel = new HTML("<b>" + BabelFish.print().group() + "</b>");
		
		HorizontalPanel hostPanel = new HorizontalPanel();
		hostPanel.setSpacing(10);
		hostPanel.add(hostLabel);
		hostPanel.add(hostText);
		
		HorizontalPanel groupPanel = new HorizontalPanel();
		groupPanel.setSpacing(10);
		groupPanel.add(groupLabel);
		groupPanel.add(groupText);
		
		panel.add(hostPanel);
		panel.add(groupPanel);
		panel.add(setParameters);
		panel.setSpacing(10);
		panel.setHorizontalAlign(HorizontalAlignment.CENTER);
		
		setParameters.addSelectionListener(CommunicationController.setCommunicationModuleParameters(hostText, groupText));
		
		show();
		
	}
	
}
