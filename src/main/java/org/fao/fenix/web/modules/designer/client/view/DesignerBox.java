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
package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.designer.client.control.DesignerController;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;

public class DesignerBox {

	private HorizontalPanel box;
	
	private RadioButton rb;
	
	private Html colorReminder;
	
	private IconButton removeBox;
	
	private TextField<String> resourceName;
	
	private ListBox resourceTypeList;
	
	private IconButton re;
	
	private IconButton deleteBox;
	
	private IconButton style;
	
	private IconButton editResource;
	
	private Integer boxID;
	
	private String boxRGB;
	
	private Long resourceID;
	
	private String resourceTitle;
	
	private ResourceType resourceType;
	
	private DesignerBoxSettingsWindow designerBoxSettingsWindow;
	
	private DesignerBoxSettings designerBoxSettings;
	
	public DesignerBox() {
		box = new HorizontalPanel();
		box.setSpacing(5);
		box.setVerticalAlign(VerticalAlignment.MIDDLE);
	}
	
	public DesignerBox(Long resourceID, String resourceTitle, String resourceType) {
		box = new HorizontalPanel();
		box.setSpacing(5);
		box.setVerticalAlign(VerticalAlignment.MIDDLE);
	}
	
	public HorizontalPanel build(DesignerTabItem tab) {
		
		// map the box and choose a colour
		DesignerController.mapBox(this);
		DesignerController.rgbBox(this);
		
		rb = new RadioButton("box");
		rb.setValue(true);
		rb.addClickHandler(DesignerController.boxRadioButton(this, tab.getBoxes(), tab.getDesignerGridWindow()));
		box.add(rb);
		box.setData("RADIO_BUTTON", rb);
		
		colorReminder = DesignerController.createColoredPixel(this.getBoxRGB());
		box.add(colorReminder);
		
		removeBox = new IconButton("removeIcon");
		removeBox.setToolTip("Remove Box");
		removeBox.addSelectionListener(DesignerController.removeBox(tab.getDesignerGridWindow(), this.getBoxID(), this.getBoxRGB()));
		box.add(removeBox);
		box.setData("REMOVE_BOX", removeBox);
		
		resourceName = new TextField<String>();
		resourceName.setEmptyText("e.g. My Chart");
		resourceName.setReadOnly(true);
		resourceName.setWidth("125px");
		box.add(resourceName);
		box.setData("RESOURCE_NAME", resourceName);
		
		resourceTypeList = new ListBox();
		resourceTypeList.addItem("Please Select...", "");
		resourceTypeList.addItem("Chart", ResourceType.CHART.name());
		resourceTypeList.addItem("Image", ResourceType.IMAGE.name());
		resourceTypeList.addItem("Map", ResourceType.MAP.name());
		resourceTypeList.addItem("Multidimensional Table", ResourceType.OLAP.name());
		resourceTypeList.addItem("Table", ResourceType.TABLE.name());
		resourceTypeList.addItem("Text", ResourceType.TEXT.name());
		
		resourceTypeList.setWidth("125px");
		box.add(resourceTypeList);
		box.setData("RESOURCE_TYPE", resourceTypeList);
		
		re = new IconButton("reSearchButton");
		re.setToolTip("Pick a Resource");
		re.addSelectionListener(DesignerController.re(this));
		box.add(re);
		box.setData("RESOURCE_EXPLORER", re);
		
		editResource = new IconButton("textEditBtn");
		editResource.setToolTip("Edit Resource");
		editResource.addSelectionListener(DesignerController.editResource(this));
		box.add(editResource);
		
		style = new IconButton("sld");
		style.setToolTip("Edit Style");
		style.addSelectionListener(DesignerController.designerBoxSettings(this));
		box.add(style);
		
		deleteBox = new IconButton("reDeleteButton");
		deleteBox.setToolTip("Delete This Box");
		deleteBox.addSelectionListener(DesignerController.deleteBox(tab, this));
		box.add(deleteBox);
		box.setData("DELETE_BOX", deleteBox);
		
		// set the grid
		DesignerController.enableBoxes(false, tab.getBoxes());
		DesignerController.enableBox(true, this);
		DesignerController.cleanGrid(tab.getDesignerGridWindow());
		DesignerController.addCheckBoxHandlers(tab.getDesignerGridWindow(), this.getBoxID(), this.getBoxRGB());
		
		// return the panel
		return box;
	}

	public RadioButton getRb() {
		return rb;
	}

	public Html getColorReminder() {
		return colorReminder;
	}

	public IconButton getRemoveBox() {
		return removeBox;
	}

	public TextField<String> getResourceName() {
		return resourceName;
	}

	public ListBox getResourceTypeList() {
		return resourceTypeList;
	}

	public IconButton getRe() {
		return re;
	}

	public IconButton getDeleteBox() {
		return deleteBox;
	}

	public Integer getBoxID() {
		return boxID;
	}

	public void setBoxID(Integer boxID) {
		this.boxID = boxID;
	}

	public String getBoxRGB() {
		return boxRGB;
	}

	public void setBoxRGB(String boxRGB) {
		this.boxRGB = boxRGB;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public void setResourceName(TextField<String> resourceName) {
		this.resourceName = resourceName;
	}

	public Long getResourceID() {
		return resourceID;
	}

	public void setResourceID(Long resourceID) {
		this.resourceID = resourceID;
	}

	public HorizontalPanel getBox() {
		return box;
	}

	public DesignerBoxSettingsWindow getDesignerBoxSettingsWindow() {
		return designerBoxSettingsWindow;
	}

	public void setDesignerBoxSettingsWindow(DesignerBoxSettingsWindow designerBoxSettingsWindow) {
		this.designerBoxSettingsWindow = designerBoxSettingsWindow;
	}

	public DesignerBoxSettings getDesignerBoxSettings() {
		return designerBoxSettings;
	}

	public void setDesignerBoxSettings(DesignerBoxSettings designerBoxSettings) {
		this.designerBoxSettings = designerBoxSettings;
	}
	
}