package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class ExcelImporterPanel {

	private ContentPanel panel;

	private CardLayout layout;

	private Button nextButton;

	private Button prevButton;

	private StepA step_a;

	private StepB step_b;

	private StepC step_c;

	private StepD step_d;

	private StepE step_e;
	
	private StepM step_m;
	
	private StepMU step_mu;
	
	private StepServiceMessage stepServiceMessage;

	private final static String STEP_WIDTH = "200px";

	public ExcelImporterPanel() {
		panel = new ContentPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		panel.setHeaderVisible(false);
		nextButton = new Button("Next >");
		prevButton = new Button("< Previous");
		step_a = new StepA("step_a_suggestion", STEP_WIDTH);
		step_b = new StepB("step_b_suggestion", STEP_WIDTH);
		step_c = new StepC("step_c_suggestion", STEP_WIDTH);
		step_d = new StepD("step_d_suggestion", STEP_WIDTH);
		step_e = new StepE("step_e_suggestion", STEP_WIDTH);
		step_m = new StepM("step_m_suggestion", STEP_WIDTH);
		step_mu = new StepMU("step_mu_suggestion", STEP_WIDTH);
		stepServiceMessage = new StepServiceMessage("step_service_message_suggestion", STEP_WIDTH);
	}

	public ContentPanel build() {

		// build steps
		panel.add(step_a.build());
		panel.add(step_b.build());
		panel.add(step_c.build());
		panel.add(step_m.build());
		panel.add(step_d.build());
		panel.add(step_e.build());
		panel.add(step_mu.build());
		panel.add(stepServiceMessage.build());

		// create buttons
		addButtons();

		// return panel
		return panel;
	}

	private void addButtons() {
		prevButton.setEnabled(false);
		nextButton.addSelectionListener(ExcelImporterController.next(this));
		prevButton.addSelectionListener(ExcelImporterController.prev(this));
		panel.addButton(prevButton);
		panel.addButton(nextButton);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
	}

	public Button getNextButton() {
		return nextButton;
	}

	public Button getPrevButton() {
		return prevButton;
	}

	public StepA getStep_a() {
		return step_a;
	}

	public StepB getStep_b() {
		return step_b;
	}

	public StepC getStep_c() {
		return step_c;
	}

	public StepD getStep_d() {
		return step_d;
	}

	public StepE getStep_e() {
		return step_e;
	}

	public StepM getStep_m() {
		return step_m;
	}

	public StepServiceMessage getStepServiceMessage() {
		return stepServiceMessage;
	}

	public StepMU getStep_mu() {
		return step_mu;
	}

	public CardLayout getLayout() {
		return layout;
	}

}