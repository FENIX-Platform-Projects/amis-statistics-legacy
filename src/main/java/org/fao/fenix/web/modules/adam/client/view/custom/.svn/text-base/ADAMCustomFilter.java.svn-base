package org.fao.fenix.web.modules.adam.client.view.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMCustomComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMCustomMultiSelection;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;


public class ADAMCustomFilter {
	
	private Boolean active = false;
	
//	private VerticalPanel panel;
	
	private FieldSet panel;
	
	private ADAMCustomMultiSelection multiSelection;
	
	private HorizontalPanel selectionPanel;
	
	private HorizontalPanel buttonPanel;
	
	private IconButton multiSelectionIcon;
	
	private Html selectedStuff;
	
	private Button selectAll;
	
	private Button deselectAll;
	
	private Button close;
	
	public ADAMCustomFilter() {
//		panel = new VerticalPanel();
		panel = new FieldSet();
		multiSelection = new ADAMCustomMultiSelection();
		multiSelectionIcon = new IconButton("select");
		selectedStuff = new Html();
		
//		panel.setBorders(true);
		panel.setWidth(ADAMConstants.SELECTION_AXIS_WIDTH);
	}
		
	public FieldSet build(String title, String columncode, String codeToSearch) {
		
		panel.add(buildPanel(title));
		panel.add(buildMulselectionPanel(columncode, codeToSearch));
		
		return panel;
	}
	
	
	
	private HorizontalPanel buildPanel(String title) {
		HorizontalPanel panel = new HorizontalPanel();
		HorizontalPanel selectionInfo = new HorizontalPanel();
		selectionInfo.add(selectedStuff);
		Html html = new Html("<div class='small-content'> "+ title +":</div>");
		html.setWidth(ADAMConstants.SELECTION_TITLE_LARGE_WIDTH);
		multiSelectionIcon.setToolTip("Select More " + title);
		multiSelectionIcon.addSelectionListener(setVisiblePanel());
		panel.add(html);
		panel.add(multiSelectionIcon);
		panel.add(selectionInfo);
		return panel;
	}
	
	private VerticalPanel buildMulselectionPanel(String columncode, String codeToSearch) {
		VerticalPanel panel = new VerticalPanel();
		selectionPanel = new HorizontalPanel();
		buttonPanel = new HorizontalPanel();
		ADAMCustomController.addMultiselectionAgent(multiSelection, selectionPanel, codeToSearch, "");
		panel.add(selectionPanel);
		selectionPanel.setVisible(false);
		
		buttonPanel.add(buildButtonPanel());
		panel.add(buttonPanel);
		buttonPanel.setVisible(false);
			
		return panel;
	}
	
	private HorizontalPanel buildButtonPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		selectAll = new Button("Select All");	
		deselectAll = new Button("Deselect All");	
		close = new Button("Close");
		close.setToolTip("Close Window");
		close.setIconStyle("delete");
		
		panel.add(selectAll);
		panel.add(deselectAll);
		panel.add(close);
		
		enanche();
		
		return panel;
	}
	
	private void enanche() {
		close.addSelectionListener(setPanelInvisible());
		selectAll.addSelectionListener(ADAMCustomController.selectAllEvent(multiSelection.getTreePanel(), multiSelection.getInfoPanel()));
		deselectAll.addSelectionListener(ADAMCustomController.deselectAllEvent(multiSelection.getTreePanel(), multiSelection.getInfoPanel()));
	}
	 
	
	public SelectionListener<IconButtonEvent> setVisiblePanel() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				setVisibility();
			}
		};
	}
	
	public void setVisibility() {
		System.out.println("visible" +  selectionPanel.isVisible());
		if ( selectionPanel.isVisible() ) {
			selectionPanel.setVisible(false);
			buttonPanel.setVisible(false);
		}
		else {
			selectionPanel.setVisible(true);
			buttonPanel.setVisible(true);
		}
		selectionPanel.layout();
	}
	
	public void forceHideVisibility() {
		selectionPanel.setVisible(false);
		buttonPanel.setVisible(false);
		selectionPanel.layout();
	}
	
	public void forceShowVisibility() {
		selectionPanel.setVisible(true);
		buttonPanel.setVisible(true);
		selectionPanel.layout();
	}
	
	public SelectionListener<ButtonEvent> setPanelInvisible() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				buttonPanel.setVisible(false);
				selectionPanel.setVisible(false);
				selectionPanel.layout();
			}
		};
	}
	

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

//	public VerticalPanel getPanel() {
//		return panel;
//	}
//
//	public void setPanel(VerticalPanel panel) {
//		this.panel = panel;
//	}

	public ADAMCustomMultiSelection getMultiSelection() {
		return multiSelection;
	}

	public void setMultiSelection(ADAMCustomMultiSelection multiSelection) {
		this.multiSelection = multiSelection;
	}

	public HorizontalPanel getSelectionPanel() {
		return selectionPanel;
	}

	public void setSelectionPanel(HorizontalPanel selectionPanel) {
		this.selectionPanel = selectionPanel;
	}

	public Html getSelectedStuff() {
		return selectedStuff;
	}

	public FieldSet getPanel() {
		return panel;
	}

	
	
	
	
}
