package org.fao.fenix.web.modules.adam.client.view.custom;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;


public class ADAMCustomSelectionBox {
	
	private ContentPanel panel;
	

	
	private ADAMCustomOutputBox adamCustomOutputBox;
	
	private ADAMCustomFilterBox adamCustomFilterBox;
	
	private ADAMCustomOptionsBox adamCustomOptionsBox;
	
	private HorizontalPanel filterPanel;


	public ADAMCustomSelectionBox() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);	
		panel.setBodyBorder(false);
//		panel.setSpacing(10);
		filterPanel = new HorizontalPanel();
	}
	
	public ContentPanel build() {
		FieldSet optionsFieldSet = buildOptionsFieldSet();
		
		FieldSet filterFieldSet = buildFilterFieldSet();

		FieldSet outputFieldSet = buildOutputFieldSet();
		
		
		panel.add(outputFieldSet);
		panel.add(optionsFieldSet);
//		panel.add(filterFieldSet);
		return panel;
	} 
	
	
	private FieldSet buildOutputFieldSet() {
		FieldSet outputFieldSet = new FieldSet();
		outputFieldSet.setCollapsible(true);
		outputFieldSet.setWidth(ADAMConstants.SELECTION_INTERNALPANEL_WIDTH);
		adamCustomOutputBox = new ADAMCustomOutputBox(this);	
		
		outputFieldSet.setHeading("Output Type");	
		outputFieldSet.add(adamCustomOutputBox.build());
		
		return outputFieldSet;
	}

	private FieldSet buildFilterFieldSet() {	
		FieldSet filterFieldSet = new FieldSet();
		filterFieldSet.setCollapsible(true);
		filterFieldSet.setHeading("Filter");
		filterFieldSet.setWidth(ADAMConstants.SELECTION_INTERNALPANEL_WIDTH);
				
		return filterFieldSet;
	}
	
	private FieldSet buildOptionsFieldSet() {	
		FieldSet optionsFieldSet = new FieldSet();
		adamCustomOptionsBox = new ADAMCustomOptionsBox();	
		adamCustomFilterBox = new ADAMCustomFilterBox();
//		filterPanel.add(adamCustomFilterBox.build("recipientcode"));
		
		optionsFieldSet.setCollapsible(true);
		optionsFieldSet.setHeading("Options");
		optionsFieldSet.setWidth(ADAMConstants.SELECTION_INTERNALPANEL_WIDTH);

		optionsFieldSet.add(filterPanel);
		optionsFieldSet.add(adamCustomOptionsBox.build());
	
		return optionsFieldSet;
	}

	public ADAMCustomOutputBox getAdamCustomOutputBox() {
		return adamCustomOutputBox;
	}

	public ADAMCustomFilterBox getAdamCustomFilterBox() {
		return adamCustomFilterBox;
	}

	public ADAMCustomOptionsBox getAdamCustomOptionsBox() {
		return adamCustomOptionsBox;
	}

	public HorizontalPanel getFilterPanel() {
		return filterPanel;
	}
	
	
	
	
	
}
