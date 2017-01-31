package org.fao.fenix.web.modules.cpf.client.view.find;


import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.cpf.client.control.find.CPFFindController;
import org.fao.fenix.web.modules.cpf.client.view.utils.layout.FormattingUtils;


import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class CPFSearchOutput {


	public ContentPanel panel;

	TextField<String> findTextBox;


	public CPFSearchOutput() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.setHeight(FAOSTATSearchConstants.ORACLE_HEIGHT);
//		panel.setScrollMode(Scroll.AUTO);
		panel.hide();
	}

	public ContentPanel build(TextField<String> findTextBox) {
		panel.removeAll();

		this.findTextBox = findTextBox;

		return panel;
	}


	public void build(List<String> values) {
		panel.removeAll();
		panel.setScrollMode(Scroll.AUTO);

//		panel.setStyleName("oraclepanel");
//		panel.addStyleName("overflow-y: auto");

		HorizontalPanel p = new HorizontalPanel();
		p.add(new FormattingUtils().addHSpace(70));

		VerticalPanel v = new VerticalPanel();
		for(String value : values) {
			ClickHtml html = new ClickHtml();
			html.setHtml("<div class='oraclevalues'> "+ value + "</div>");
			html.addListener(Events.OnClick, CPFFindController.setTextBox(this, findTextBox, value));
			v.add(html);
		}

		v.setHeight(175);
		v.setWidth(500);

		v.setBorders(true);
		v.setScrollMode(Scroll.AUTO);

		p.add(v);

		HorizontalPanel closePanel = new HorizontalPanel();
		closePanel.addStyleName("closebuttonstyle");

		IconButton close = new IconButton("delete");
		close.addSelectionListener(hidePanel());

		closePanel.add(close);
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='oraclevalues'>Close</div>");
		html.addListener(Events.OnClick, hidePanelHtml());
		closePanel.add(html);

		p.add(FormattingUtils.addHSpace(5));
		p.add(closePanel);

		panel.add(p);

		if ( values.size() > 0 ) {
			panel.show();
		}
		else
			panel.hide();

		panel.layout();
	}

	private SelectionListener<IconButtonEvent> hidePanel() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				hidePanelAgent();
			}
		};
	}

	public Listener<ComponentEvent> hidePanelHtml() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				hidePanelAgent();
			}
		};
	}

	private void hidePanelAgent() {
		panel.hide();
	}



	public ContentPanel getPanel() {
		return panel;
	}


}
