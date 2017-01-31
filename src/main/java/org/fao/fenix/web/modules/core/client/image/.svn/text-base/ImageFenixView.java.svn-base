package org.fao.fenix.web.modules.core.client.image;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditorToolbar;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;

public class ImageFenixView extends FenixWindow {

	SideListImage sideListImage;
	HTML imageContent;
	Button ok;
	String caller;
	ReportViewer reportViewer;
	TextEditorToolbar textEditorToolbar;
	
	public ImageFenixView(ReportViewer reportViewer){
		this.caller="Report";
		this.reportViewer = reportViewer;
		buildInterface();
		
	}
	
	public ImageFenixView(TextEditorToolbar textEditorToolbar){
		this.caller="Text";
		this.textEditorToolbar = textEditorToolbar;
		buildInterface();
		
	}
	
	private void buildInterface(){
		
		imageContent = new HTML("<h1>Image Viewer</h1>");
		ok = new Button(BabelFish.print().ok());
		ok.addSelectionListener(ImageFenixController.getImage(this));
		
		setTitle("Image Gallery");
		setSize(750, 420);
		setCollapsible(true);
		setMaximizable(true);
		
		setCenterProperties();
		setWestProperties();
		
		sideListImage = new SideListImage(imageContent,getWest());
		getWest().add(sideListImage.getMainCont());
		addWestPartToWindow();
		
		setCenterProperties();
		getCenter().add(imageContent);
		getCenter().setBottomComponent(ok);
		addCenterPartToWindow();
		getCenter().setScrollMode(Scroll.AUTO);

		getCenter().setHeading("Preview");
		getWestData().setFloatable(true);
		
		show();
			
	}
	
}
