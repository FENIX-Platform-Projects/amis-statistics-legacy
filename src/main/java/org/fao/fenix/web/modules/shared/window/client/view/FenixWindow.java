package org.fao.fenix.web.modules.shared.window.client.view;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.Widget;

public abstract class FenixWindow {

	protected final DraggableWindow window;
	protected ContentPanel west;  
	protected ContentPanel center;  
	protected ContentPanel east;  
	protected ContentPanel south;
	protected ContentPanel north;
	protected BorderLayoutData toolbarData;
	protected BorderLayoutData westData;
	protected BorderLayoutData eastData;
	protected BorderLayoutData southData;
	protected BorderLayoutData northData;
	protected BorderLayoutData centerData;
	
	protected float defaultWestSize = 250;
	protected float defaultNorthSize = 250;
	
	public FenixWindow(){
		window = new DraggableWindow();
		window.setLayout(new BorderLayout());
		window.setSize(550, 400);
		window.setResizable(true);				
	}
	
	public void setTitle(String title){
		window.setHeading(title);
	}
	
	public void setSize(int width, int height){
		window.setSize(width, height);
	}
	
	public void setSize(String width, String height){
		window.setSize(width, height);
	}
	
	public void setMaximizable(boolean value){
		window.setMaximizable(value);
	}
	
	public void setModal(boolean value){
		window.setModal(value);
	}
	
	public void setCollapsible(boolean value){
		window.setMinimizable(value);
		window.addWindowListener(new WindowListener(){
			@Override
			public void windowMinimize(WindowEvent we) {
				super.windowMinimize(we);
				if (window.isExpanded()) window.collapse();
				else window.expand();
				 
			}
		});
	}
	
	public void setAutomaticScrollBar(){
		window.setScrollMode(Scroll.AUTO);
	}
	
	public Window getWindow() {
		return window;
	}

	public ContentPanel getWest() {
		return west;
	}
	
	public ContentPanel getNorth() {
		return north;
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
	
	public BorderLayoutData getNorthData() {
		return northData;
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
		window.add(toolbar, toolbarData);
		window.setData("toolbar", toolbar);
	}
	
	public ToolBar getToolbar() {
		return (ToolBar)window.getData("toolbar");
	}
	
	public void setWestProperties(){
		west = new ContentPanel();
		west.setLayout(new FitLayout());
		westData = new BorderLayoutData(LayoutRegion.WEST, defaultWestSize); 
		westData.setSplit(true);  
		westData.setCollapsible(true);
		westData.setFloatable(true);
	}
	
	public void setNorthProperties(){
		north = new ContentPanel();
		north.setLayout(new FitLayout());
		northData = new BorderLayoutData(LayoutRegion.NORTH, defaultNorthSize); 
		northData.setSplit(true);  
		northData.setCollapsible(true);
		northData.setFloatable(true);
		northData.setHidden(false);
		
//		# BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 100);  
//		#     northData.setCollapsible(true);  
//		#     northData.setFloatable(true);  
//		#     northData.setHideCollapseTool(true);  
//		#     northData.setSplit(true);  
//		#     northData.setMargins(new Margins(0, 0, 5, 0));  
	}
	
	public void resizeWest(float newSize){
		defaultWestSize = newSize;
	}

	public void resizeNorth(float newSize){
		defaultNorthSize = newSize;
	}
	
	public void fillWestPart(Widget widget){
		setWestProperties();	
		west.add(widget);
		window.add(west, westData);
	}
	
	public void fillNorthPart(Widget widget){
		setNorthProperties();	
		north.add(widget);
		window.add(north, northData);
	}
			
	public void addWestPartToWindow(){
		window.add(getWest(), westData);
	}
	
	public void addNorthPartToWindow(){
		window.add(getNorth(), northData);
	}
	
	public void addEastPartToWindow(){
		window.add(getEast(), eastData);
	}
	
	public void removeWestPartToWindow(){
		window.remove(getWest());
	}
	
	public void removeNorthPartToWindow(){
		window.remove(getNorth());
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
		window.add(center, centerData);
	}
	
	public void addCenterPartToWindow(){
		window.add(getCenter(), centerData);
	}
	
	public void removeCenterPartToWindow(){
		window.remove(getCenter());
	}
	
			
	public void fillEastPart(Widget widget){
		setEastProperties();	
		east.add(widget);
		window.add(east, eastData);
	}
	
	public void fillSouthPart(Widget widget){
		south=new ContentPanel();
		southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);  
		southData.setSplit(true);  
		southData.setCollapsible(true);  
		southData.setFloatable(true);  
		south.add(widget);
		window.add(south, southData);
	}
	
	public void show(){
		window.show();
	}
	
	public void setSize(){
		window.setSize(window.getWidth(), window.getHeight());
	}

	public void setWindowMovedListener(Runnable windowMovedListener) {
		window.setWindowMovedListener(windowMovedListener);
	}

	public class DraggableWindow extends Window {

		protected Runnable windowMovedListener = null;

		public void setWindowMovedListener(Runnable windowMovedListener) {
			this.windowMovedListener = windowMovedListener;
		}

		@Override
		protected void endDrag(DragEvent de) {
			super.endDrag(de);

			if(windowMovedListener != null)
				windowMovedListener.run();
		}
	}
}
