package org.fao.fenix.web.modules.table.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.client.control.TableViewToolbarController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.RadioButton;

public class TableViewTypeExportWindow extends FenixWindow {

	private ContentPanel contentPanel;

	private RadioButton code = new RadioButton("selection");

	private RadioButton label = new RadioButton("selection");

	private RadioButton codelabel = new RadioButton("selection");

	private VerticalPanel v;

	public TableViewTypeExportWindow(TableViewWindow tableWindow, long resourceId) {
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		v = new VerticalPanel();
		v.setSpacing(10);
		code.setHTML(BabelFish.print().code());
		code.setValue(false);
		label.setHTML(BabelFish.print().label());
		label.setValue(false);
		codelabel.setHTML(BabelFish.print().codelabel());
		codelabel.setValue(true);
		setCenterProperties();
		getCenter().add(buildCenterPanel());
		buildButtonsPanel(tableWindow, resourceId);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWindow().setHeading("Choose Export");
		format();
		show();
	}

	private ContentPanel buildCenterPanel() {
		v.add(code);
		v.add(label);
		v.add(codelabel);
		contentPanel.add(v);
		return contentPanel;
	}

	private void buildButtonsPanel(TableViewWindow tableWindow, long resourceId) {
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);
		Button ok = new Button(BabelFish.print().ok());
		ok.setWidth(40);
		ok.addSelectionListener(TableViewToolbarController.exportToExcel(this, tableWindow, resourceId));
		Button cancel = new Button(BabelFish.print().close(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});
		cancel.setWidth(40);
		contentPanel.addButton(ok);
		contentPanel.addButton(cancel);
	}

	private void format() {
		getWindow().setSize("400px", "200px");
		getWindow().setModal(true);
	}

	public RadioButton getCode() {
		return code;
	}

	public void setCode(RadioButton code) {
		this.code = code;
	}

	public RadioButton getLabel() {
		return label;
	}

	public void setLabel(RadioButton label) {
		this.label = label;
	}

	public RadioButton getCodelabel() {
		return codelabel;
	}

	public void setCodelabel(RadioButton codelabel) {
		this.codelabel = codelabel;
	}

}