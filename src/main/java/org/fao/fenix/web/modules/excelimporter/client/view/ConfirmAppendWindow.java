package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.excelimporter.client.control.GhostCodeListFormController;
import org.fao.fenix.web.modules.excelimporter.client.control.GhostFormController;
import org.fao.fenix.web.modules.excelimporter.client.control.GhostImageImporterController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ConfirmAppendWindow extends FenixWindow {

	private ContentPanel wrapper;
	
	private Button confirmButton;
	
	private Button cancelButton;
	
	private ExcelImporterPanel p;
	
	private CodeListPanel clp;
	
	private ImageImporterPanel iip;
	
	public ConfirmAppendWindow(ExcelImporterPanel p) {
		wrapper = new ContentPanel(new FitLayout());
		this.p = p;
	}
	
	public ConfirmAppendWindow(CodeListPanel p) {
		wrapper = new ContentPanel(new FitLayout());
		this.clp = p;
	}
	
	public ConfirmAppendWindow(ImageImporterPanel p) {
		wrapper = new ContentPanel(new FitLayout());
		this.iip = p;
	}
	
	public void build(String message) {
		buildCenterPanel(message);
		format();
		show();
	}
	
	public void buildForCodeList(String message) {
		buildCenterPanelForCodeList(message);
		format();
		show();
	}
	
	public void buildForImage(String message) {
		buildCenterPanelForImage(message);
		format();
		show();
	}
	
	public void build(Grid<ResourceChildModel> grid) {
		ResourceChildModel m = grid.getSelectionModel().getSelectedItem(); 
		String message = null;
		if (m == null) {
			message = "You didn't select any existing dataset. Your data will be stored as a new dataset. Are you sure?";
		} else {
			message = "Your data will be appended to '" + m.getName() + "' which is a " + m.getPeriodTypeCode() + " dataset by " + m.getSource() + ". Are you sure?";
		}
		buildCenterPanel(message);
		format();
		show();
	}
	
	private void buildCenterPanel(String message) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildWrapper(message));		
		getCenter().setSize("275px", "100px");
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void buildCenterPanelForCodeList(String message) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildWrapperForCodeList(message));		
		getCenter().setSize("275px", "100px");
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void buildCenterPanelForImage(String message) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildWrapperForImage(message));		
		getCenter().setSize("275px", "100px");
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private ContentPanel buildWrapper(String message) {
		wrapper.setHeaderVisible(false);
		Html messageHTML = new Html("<b><H1><font color='green'>" + message + "</font></H1></b>");
		wrapper.add(messageHTML);
		wrapper.setBottomComponent(buildButtonsPanel());
		return wrapper;
	}
	
	private ContentPanel buildWrapperForCodeList(String message) {
		wrapper.setHeaderVisible(false);
		Html messageHTML = new Html("<b><H1><font color='green'>" + message + "</font></H1></b>");
		wrapper.add(messageHTML);
		wrapper.setBottomComponent(buildButtonsPanelForCodeList());
		return wrapper;
	}
	
	private ContentPanel buildWrapperForImage(String message) {
		wrapper.setHeaderVisible(false);
		Html messageHTML = new Html("<b><H1><font color='green'>" + message + "</font></H1></b>");
		wrapper.add(messageHTML);
		wrapper.setBottomComponent(buildButtonsPanelForImage());
		return wrapper;
	}
	
	@SuppressWarnings("deprecation")
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		confirmButton = new Button("Confirm");
		confirmButton.addSelectionListener(GhostFormController.confirmAppend(this.p, this));
		cancelButton = new Button("Cancel", new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getWindow().close();
			}
		});
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(cancelButton);
		return buttonsPanel;
	}
	
	@SuppressWarnings("deprecation")
	private HorizontalPanel buildButtonsPanelForCodeList() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		confirmButton = new Button("Confirm");
		confirmButton.addSelectionListener(GhostCodeListFormController.confirmAppend(this.clp, this));
		cancelButton = new Button("Cancel", new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getWindow().close();
			}
		});
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(cancelButton);
		return buttonsPanel;
	}
	
	@SuppressWarnings("deprecation")
	private HorizontalPanel buildButtonsPanelForImage() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		confirmButton = new Button("Confirm");
		confirmButton.addSelectionListener(GhostImageImporterController.confirmAppend(this.iip, this));
		cancelButton = new Button("Cancel", new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getWindow().close();
			}
		});
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(cancelButton);
		return buttonsPanel;
	}
	
	private void format() {
		this.getWindow().setHeading("Please Confirm Your Choice");
		this.getWindow().setModal(true);
		this.getWindow().setSize("400px", "125px");
	}

	public Button getConfirmButton() {
		return confirmButton;
	}
	
}