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

package org.fao.fenix.web.modules.qb.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class QueryBuilder extends FenixWindow {

	private CardLayout layout;
	public ContentPanel wizardPanel; 

	protected PanelOneDatasetSelection panelOneDatasetSelection;
	protected PanelTwoJoinSelection panelTwoJoinSelection;
	protected PanelThreeFieldSelection panelThreeFieldSelection;
	
	private Button backButton;   
	private Button nextButton;


	public QueryBuilder() {

		buildCenterPanel();

		wizardPanel = new ContentPanel(); 	
		layout = new CardLayout();

		wizardPanel.setLayout(layout);   

		this.panelOneDatasetSelection = new PanelOneDatasetSelection(this);
		this.panelTwoJoinSelection = new PanelTwoJoinSelection(this);
		this.panelThreeFieldSelection = new PanelThreeFieldSelection(this);

		wizardPanel.add(panelOneDatasetSelection);
		wizardPanel.add(panelTwoJoinSelection);
		wizardPanel.add(panelThreeFieldSelection);

		layout.setActiveItem(panelOneDatasetSelection); 

		wizardPanel.setHeading(getStepXofN()+" Select Datasets");
		getCenter().add(wizardPanel);
		getCenter().setBottomComponent(buildButtonBar());
		
		//showHideNavigationButtons();
	}

	public void build() {
		format();
		show();	
	}

	private ButtonBar buildButtonBar(){

		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {   
			public void componentSelected(ButtonEvent ce)  {   
				String btnID = ce.getButton().getId();   
				//CardLayout cardLayout = (CardLayout) wizardPanel.getLayout();   
				//String panelID = cardLayout.getActiveItem().getId();   

				if (btnID.equals("move-back")) {   
					setPreviousPanel();
				} else {   
					setNextPanel(); 
				} 

				wizardPanel.setHeading(layout.getActiveItem().getTitle());

			}   
		};   

		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setAlignment(HorizontalAlignment.RIGHT);
		buttonBar.setHeight(30);

		backButton = new Button("<b><< Back</b>", listener);   
		backButton.setId("move-back");   
		backButton.setWidth(100);

		buttonBar.add(backButton);   

		nextButton = new Button("<b>Next >></b>", listener);   
		nextButton.setId("move-next");
		nextButton.setWidth(100);

		buttonBar.add(nextButton);   

		return buttonBar;

	}

	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setHeaderVisible(false);
		addCenterPartToWindow();
	}

	private void format() {
		setSize("900px", "450px");
		getWindow().setHeading(BabelFish.print().queryBuilder());
		getWindow().setIconStyle("queryBuilderIcon");
		setCollapsible(true);
		getWindow().setResizable(true);
	}

	public PanelThreeFieldSelection getPanelThreeFieldSelection() {
		return panelThreeFieldSelection;
	}

	public void setPanelThreeFieldSelection(PanelThreeFieldSelection panelThreeFieldSelection) {
		this.panelThreeFieldSelection = panelThreeFieldSelection;
	}
	
	
	public PanelTwoJoinSelection getPanelTwoJoinSelection() {
		return panelTwoJoinSelection;
	}

	public void setPanelTwoJoinSelection(PanelTwoJoinSelection panelTwoJoinSelection) {
		this.panelTwoJoinSelection = panelTwoJoinSelection;
	}

	
	public PanelOneDatasetSelection getPanelOneDatasetSelection() {
		return panelOneDatasetSelection;
	}

	public void setPanelOneDatasetSelection(PanelOneDatasetSelection panelOneDatasetSelection) {
		this.panelOneDatasetSelection = panelOneDatasetSelection;
	}
	
	
	private Integer getNumberOfPanels() {
		return this.wizardPanel.getItems().size(); 
	}
	
	private Integer getIndexOfCurrentPanel() {
		return getActivePanelIndex()+1; 
	}
	
	public String getStepXofN(){
		return "Step "+ getIndexOfCurrentPanel()+" of " + getNumberOfPanels()+":";
	}
	
	private boolean isFirstPanel() {
		return (layout.getActiveItem() == this.wizardPanel.getItems().get(0));
	}

	private boolean isLastPanel() {
		return (layout.getActiveItem() == this.wizardPanel.getItems().get(this.wizardPanel.getItems().size()-1));
	}

	private void setNextPanel() {
		if (!isLastPanel()) {
			layout.setActiveItem(getPanel(getActivePanelIndex() + 1));
		}

	}

	private void setPreviousPanel() {
		if (!isFirstPanel()) {
			layout.setActiveItem(getPanel(getActivePanelIndex() - 1));
		}
	}

	private int getActivePanelIndex() {
		return this.wizardPanel.getItems().indexOf(layout.getActiveItem()); 
	}

	private Component getPanel(int index) {
		return this.wizardPanel.getItems().get(index); 
	}

	public void showHideNavigationButtons() {
		
		if(isLastPanel()) {
			nextButton.setVisible(false);
			backButton.setVisible(true);
		} else if(isFirstPanel()) {
			backButton.setVisible(false);
			nextButton.setVisible(true);
		} else{
			nextButton.setVisible(true);
			backButton.setVisible(true);
		}
	}
}