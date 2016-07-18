package org.fao.fenix.web.modules.shared.window.client.view;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.Widget;

public abstract class FenixToolBase {

	protected ContentPanel toolBase;
	
	protected ContentPanel west;  
	
	protected ContentPanel center;  
	
	protected ContentPanel east;  
	
	protected ContentPanel south;
	
	protected BorderLayoutData toolbarData;
	
	protected BorderLayoutData westData;
	
	protected BorderLayoutData eastData;
	
	protected BorderLayoutData southData;
	
	protected BorderLayoutData centerData;
	
	protected float defaultWestSize = 250;
	
	public FenixToolBase(){
		toolBase = new ContentPanel();
		toolBase.setLayout(new BorderLayout());
		toolBase.setSize(550, 400);				
	}
	
	public void setTitle(String title){
		toolBase.setHeading(title);
	}
	
	public void setSize(int width, int height){
		toolBase.setSize(width, height);
	}
	
	public void setSize(String width, String height){
		toolBase.setSize(width, height);
	}
	
	public void setAutomaticScrollBar(){
		toolBase.setScrollMode(Scroll.AUTO);
	}
	
	public ContentPanel getWest() {
		return west;
	}

	public ContentPanel getCenter() {
		return center;
	}

	public ContentPanel getEast() {
		return east;
	}

	public ContentPanel getSouth() {
		return south;
	}

	public BorderLayoutData getToolbarData() {
		return toolbarData;
	}

	public BorderLayoutData getWestData() {
		return westData;
	}

	public BorderLayoutData getEastData() {
		return eastData;
	}

	public BorderLayoutData getSouthData() {
		return southData;
	}

	public BorderLayoutData getCenterData() {
		return centerData;
	}
	
	public void addToolbar(ToolBar toolbar) {
		BorderLayoutData toolbarData = new BorderLayoutData(LayoutRegion.NORTH);  
		toolbarData.setFloatable(false);
		toolbarData.setMinSize(25);
		toolbarData.setMaxSize(25);
		toolbarData.setSize(25);
		toolBase.add(toolbar, toolbarData);
		toolBase.setData("toolbar", toolbar);
	}
	
	public ToolBar getToolbar() {
		return (ToolBar)toolBase.getData("toolbar");
	}
	
	public void setWestProperties(){
		west = new ContentPanel();
		west.setLayout(new FitLayout());
		westData = new BorderLayoutData(LayoutRegion.WEST, defaultWestSize); 
		westData.setSplit(true);  
		westData.setCollapsible(true);
		westData.setFloatable(true);
	}
	
	public void resizeWest(float newSize){
		defaultWestSize = newSize;
	}
	
	public void fillWestPart(Widget widget){
		setWestProperties();	
		west.add(widget);
		toolBase.add(west, westData);
	}
			
	public void addWestPartToWindow(){
		toolBase.add(getWest(), westData);
	}
	
	public void addEastPartToWindow(){
		toolBase.add(getEast(), eastData);
	}
	
	public void removeWestPartToWindow(){
		toolBase.remove(getWest());
	}
	
	public void setCenterProperties(){
		center=new ContentPanel();
		center.setLayout(new FitLayout());
		centerData = new BorderLayoutData(LayoutRegion.CENTER);	
	}
	
	public void setEastProperties(){
		east = new ContentPanel();
		east.setLayout(new FitLayout());
		eastData = new BorderLayoutData(LayoutRegion.EAST);	
		eastData.setSplit(true);  
		eastData.setCollapsible(true);
		eastData.setFloatable(true);
	}
	
	public void fillCenterPart(Widget widget){
		setCenterProperties();		
		center.add(widget);
		toolBase.add(center, centerData);
	}
	
	public void addCenterPartToWindow(){
		toolBase.add(getCenter(), centerData);
	}
	
	public void removeCenterPartToWindow(){
		toolBase.remove(getCenter());
	}
			
	public void fillEastPart(Widget widget){
		setEastProperties();	
		east.add(widget);
		toolBase.add(east, eastData);
	}
	
	public void fillSouthPart(Widget widget){
		south=new ContentPanel();
		southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);  
		southData.setSplit(true);  
		southData.setCollapsible(true);  
		southData.setFloatable(true);  
		south.add(widget);
		toolBase.add(south, southData);
	}
	
	public void show(){
		toolBase.show();
	}
	
	public void setSize(){
		toolBase.setSize(toolBase.getWidth(), toolBase.getHeight());
	}

	public ContentPanel getToolBase() {
		return toolBase;
	}
	
}