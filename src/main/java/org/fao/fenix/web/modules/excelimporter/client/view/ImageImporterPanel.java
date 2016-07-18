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

import org.fao.fenix.web.modules.excelimporter.client.control.ImageImporterController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class ImageImporterPanel {

	private ContentPanel panel;

	private CardLayout layout;

	private Button nextButton;

	private Button prevButton;
	
	private ImageImporterStepA step_a;
	
	private ImageImporterStepB step_b;
	
	private ImageImporterStepC step_c;
	
	private StepM step_m;
	
	private final static String STEP_WIDTH = "200px";
	
	private String tinyMCEPanelID;
	
	public ImageImporterPanel(boolean isAddToReport) {
		panel = new ContentPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		panel.setHeaderVisible(false);
		nextButton = new Button("Next >");
		prevButton = new Button("< Previous");
		step_a = new ImageImporterStepA("image_step_a_suggestion", STEP_WIDTH);
		step_b = new ImageImporterStepB("image_step_a_suggestion", STEP_WIDTH);
		step_c = new ImageImporterStepC("image_step_a_suggestion", STEP_WIDTH, isAddToReport);
		step_m = new StepM("step_m_suggestion", STEP_WIDTH);
	}
	
	public ContentPanel build() {

		// build steps
		panel.add(step_a.build());
		panel.add(step_b.build());
		panel.add(step_m.build());
		panel.add(step_c.build());

		// create buttons
		addButtons();

		// return panel
		return panel;
	}

	private void addButtons() {
		prevButton.setEnabled(false);
		nextButton.addSelectionListener(ImageImporterController.next(this));
		prevButton.addSelectionListener(ImageImporterController.prev(this));
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

	public ImageImporterStepA getStep_a() {
		return step_a;
	}

	public ImageImporterStepB getStep_b() {
		return step_b;
	}

	public StepM getStep_m() {
		return step_m;
	}

	public CardLayout getLayout() {
		return layout;
	}

	public ImageImporterStepC getStep_c() {
		return step_c;
	}

	public String getTinyMCEPanelID() {
		return tinyMCEPanelID;
	}

	public void setTinyMCEPanelID(String tinyMCEPanelID) {
		this.tinyMCEPanelID = tinyMCEPanelID;
	}
	
}