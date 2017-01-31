package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class AddNodeWindow extends FenixWindow {

	private String[] fields = new String[]{BabelFish.print().host(),
										   BabelFish.print().hostLabel(), 
										   BabelFish.print().group()};
	
	private VerticalPanel fieldsPanel;
	
	public void build() {
		setCenterProperties();
		getCenter().add(buildCenterPanel());
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWindow().setHeading(BabelFish.print().addNode());
		format();
		show();
	}
	
	public VerticalPanel buildCenterPanel() {
		fieldsPanel = new VerticalPanel();
		for (String field : fields) {
			HorizontalPanel fieldPanel = buildFieldPanel(field);
			fieldsPanel.add(fieldPanel);
			fieldsPanel.setData(field, fieldPanel);
		}
		fieldsPanel.add(buildButtonsPanel());
		return fieldsPanel;
	}
	
	public HorizontalPanel buildFieldPanel(String field) {
		HorizontalPanel fieldPanel = new HorizontalPanel();
		fieldPanel.setSpacing(10);
		HTML label = new HTML("<b>" + field + ": </b>");
		TextBox value = new TextBox();
		fieldPanel.add(label);
		fieldPanel.add(value);
		fieldPanel.setData("value", value);
		label.setWidth("100px");
		value.setWidth("200px");
		return fieldPanel;
	}
	
	public HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		Button addNodeButton = new Button(BabelFish.print().addNode());
		addNodeButton.addSelectionListener(CommunicationController.getAddNodeListener(fieldsPanel));
		Button close = new Button(BabelFish.print().close(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});
		buttonsPanel.add(addNodeButton);
		buttonsPanel.add(close);
		return buttonsPanel;
	}
	
	public void format() {
		getWindow().setSize("350px", "200px");
		getWindow().setModal(true);
	}
	
}