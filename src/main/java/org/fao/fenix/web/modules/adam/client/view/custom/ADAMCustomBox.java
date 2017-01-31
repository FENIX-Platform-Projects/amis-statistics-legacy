package org.fao.fenix.web.modules.adam.client.view.custom;

import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;


public class ADAMCustomBox {
	
	ContentPanel panel;

	ADAMCustomSelectionBox adamCustomSelectionBox;
	
	ADAMCustomShowBox adamCustomShowBox;
	
	private Button apply;
	
	private Button close;

	public ADAMCustomBox() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		//panel.setSpacing(5);
		panel.setHeight(ADAMConstants.CUSTOM_HEIGHT);
		panel.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		panel.setScrollMode(Scroll.AUTOX);
		apply = new Button("Create");
		close = new Button(BabelFish.print().close());
		
		adamCustomSelectionBox = new ADAMCustomSelectionBox();
		adamCustomShowBox = new ADAMCustomShowBox();
		
	
		
		close.setToolTip("Close Window");
		close.setIconStyle("delete");	
		
		apply.setToolTip("Create the resource");
		apply.setIconStyle("wand");
	}
	

	public ContentPanel build() {
	    
		HorizontalPanel h = new HorizontalPanel();

		Html title = new Html("<div class='title' align='left' padding='3px'>Create your Resource</div>");
		panel.add(title);
		panel.add(h);
		
//		panel.add(adamCustomSelectionBox.build());
		h.add(buildSelectionPanel());
		h.add(buildShowPanel());
		
		enancheButtons();
		return panel;
	}
	
	private VerticalPanel buildSelectionPanel() {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(5);
		ContentPanel selectionPanel = new ContentPanel();
		selectionPanel.setHeaderVisible(false);		
		selectionPanel.setBodyBorder(false);
		
		VerticalPanel buttonPanel = new VerticalPanel();
		
		selectionPanel.setHeight(ADAMConstants.SELECTION_HEIGHT);
		selectionPanel.add(adamCustomSelectionBox.build());
		selectionPanel.setScrollMode(Scroll.AUTOY);
		buttonPanel.add(buildButtonPanel());
		
		mainPanel.add(selectionPanel);
		mainPanel.add(buttonPanel);
		
		return mainPanel;
	}
	
	private VerticalPanel buildShowPanel() {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.add(adamCustomShowBox.build());
		return mainPanel;
	}
	
	private HorizontalPanel buildButtonPanel(){
		HorizontalPanel hpanel = new HorizontalPanel();
		hpanel.setSpacing(10);
		hpanel.add(apply);
		hpanel.add(close);
		return hpanel;
	}

	
	private void enancheButtons() {
		apply.addSelectionListener(ADAMCustomController.applyCreateResource(this));
		close.addSelectionListener(ADAMCustomController.closeCustom());
	}

	public ADAMCustomSelectionBox getAdamCustomSelectionBox() {
		return adamCustomSelectionBox;
	}


	public ADAMCustomShowBox getAdamCustomShowBox() {
		return adamCustomShowBox;
	}
	
	
	
	
	
	
	
}
