package org.fao.fenix.web.modules.chartdesigner.client.view;

import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class ChartDesignerReminder extends FenixWindow {

	private static final String WINDOW_WIDTH = "275px";
	
	private static final String WINDOW_HEIGHT = "100px";
	
	private Button yesButton;
	
	private Button noButton;
	
	private ChartDesignerWindow chartDesignerWindow; 
	
	public ChartDesignerReminder(final ChartDesignerWindow w) {
		this.setChartDesignerWindow(w);
		yesButton = new Button("Yes, Please Proceed");
		noButton = new Button("No, Let Me Do It Now");
		yesButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getWindow().hide();
				if (w.getCaller().equals(WhoCall.TINYMCE_EDIT)) {
					String content = TinyMCENativeController.getContent(w.getTinyMCEPanelID());
					TinyMCEController.reLoadChart(w.getTinyMCEPanelID(), w.getChartDesignID(), content, w.getOriginalWidth(), w.getOriginalHeight());
				}
				w.getWindow().hide();
			}
		});
		noButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getWindow().hide();
			}
		});
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildMessage());
		getCenter().setBottomComponent(buildButtonsPanel());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private HTML buildMessage() {
		HTML html = new HTML("Have you saved your changes?");
		return html;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		p.add(yesButton);
		p.add(noButton);
		return p;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Reminder");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("workspaceChart");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
	}

	public Button getYesButton() {
		return yesButton;
	}

	public Button getNoButton() {
		return noButton;
	}

	public ChartDesignerWindow getChartDesignerWindow() {
		return chartDesignerWindow;
	}

	public void setChartDesignerWindow(ChartDesignerWindow chartDesignerWindow) {
		this.chartDesignerWindow = chartDesignerWindow;
	}
	
}