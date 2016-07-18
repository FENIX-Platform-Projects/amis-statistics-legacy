/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.excelimporter.client.view;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class CodeListPanel {

	private ContentPanel panel;

	private CardLayout layout;

	private Button nextButton;

	private Button prevButton;

	private CodeListStepA step_a;
	
	private CodeListStepB step_b;
	
	private StepServiceMessage stepServiceMessage; 
	
	private final static String STEP_WIDTH = "200px";
	
	public CodeListPanel() {
		panel = new ContentPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		panel.setHeaderVisible(false);
		nextButton = new Button("Next >");
		prevButton = new Button("< Previous");
		step_a = new CodeListStepA("codeList_step_a_suggestion", STEP_WIDTH);
		step_b = new CodeListStepB("codeList_step_b_suggestion", STEP_WIDTH);
		stepServiceMessage = new StepServiceMessage("step_service_message_suggestion", STEP_WIDTH);
	}
	
	public ContentPanel build() {

		// build steps
		panel.add(step_a.build());
		panel.add(step_b.build());
		panel.add(stepServiceMessage.build());

		// create buttons
		addButtons();

		// return panel
		return panel;
	}
	
	private void addButtons() {
		prevButton.setEnabled(false);
		panel.addButton(prevButton);
		panel.addButton(nextButton);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public CardLayout getLayout() {
		return layout;
	}

	public Button getNextButton() {
		return nextButton;
	}

	public Button getPrevButton() {
		return prevButton;
	}

	public CodeListStepA getStep_a() {
		return step_a;
	}

	public CodeListStepB getStep_b() {
		return step_b;
	}

	public StepServiceMessage getStepServiceMessage() {
		return stepServiceMessage;
	}

	public static String getStepWidth() {
		return STEP_WIDTH;
	}
	
}