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

package org.fao.fenix.web.modules.text.client.view.editor;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomShowBox;
import org.fao.fenix.web.modules.birt.client.control.report.ReportViewerController;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.client.control.TextEditorController;
import org.fao.fenix.web.modules.text.common.model.Text;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.RichTextArea;

public class TextEditor extends FenixWindow {

	private final int SPACING=10;

	private Button saveResource;
	private Button linkResource;
	private Button saveResourceAs;
	private Button ok;

	//birt stuff
	private String rptdesign;
	private ReportViewer reportViewer;
	private DataListItem comment;

	public TextEditorToolbar editorToolBar;
	public RichTextArea textArea;

	public String textTitle;
	public Long textID;
	public String textContent;
	public String textReferenceDate;
	public String textLastUpdated;
	public String textAreaContent;
	
	//passed from TextGroup
	public Text textModel;

	private String index;

	public Window viewerWindow;
	
	// ADAM
	ADAMCustomShowBox adamCustomShowBox;
	
	public TextEditor(){
		setTextViewId(null);
		setTitle(null);
		setTextContent(null);
		setReferenceDate(null);
		setLastUpdated(null);
		setTextViewId(null);
		setRptDesign("NoBirt");
		setTextModel(null);
	}
	
	public TextEditor(RichTextArea textArea){
		setTextViewId(null);
		setTitle(null);
		setTextContent(textArea.getHTML());
		setReferenceDate(null);
		setLastUpdated(null);
		setTextViewId(null);
		setRptDesign("NoBirt");
		setTextModel(null);
	}
	
	public TextEditor(RichTextArea text, String type){
		setTextViewId(null);
		setTextTitle(null);
		setTextContent(text.getHTML());
		setReferenceDate(null);
		setLastUpdated(null);
		setTextViewId(null);
		setRptDesign(type);
		setTextModel(null);
	}
	
	public TextEditor(String text, String type){
		setTextViewId(null);
		setTextTitle(null);
		setTextContent(text);
		setReferenceDate(null);
		setLastUpdated(null);
		setTextViewId(null);
		setRptDesign(type);
		setTextModel(null);
	}

	public TextEditor(ReportViewer reportViewer, String text, DataListItem comment){
		setTextViewId(null);
		setTextTitle(null);
		setTextContent(text);
		setReferenceDate(null);
		setLastUpdated(null);
		setTextViewId(null);
		setRptDesign(reportViewer.getRptDesign());
		setReportViewer(reportViewer);
		setComment(comment);
		setTextModel(null);
	}

	public TextEditor(Long id, List<String> textList, Window viewer) {
		setRptDesign("NoBirt");

		setViewerWindow(viewer);
		setViewerWindowIndex(null);
		setTextModel(null);

		final List<String> ls = (ArrayList<String>) textList;

		setTextViewId(id);
		setTextTitle((String) ls.get(1));
		setTextContent((String) ls.get(2));
		if ((String) ls.get(3) != null && !((String) ls.get(3)).equals("null")) 
			setReferenceDate((String) ls.get(3));
		if ((String) ls.get(4) != null) {
			setLastUpdated((String) ls.get(4));
		}

	}

	public TextEditor(Long id, String index, List<String> textList, Window window) {
		setRptDesign("NoBirt");

		setViewerWindow(window);
		setViewerWindowIndex(index);
		setTextModel(null);

		final List<String> ls = (ArrayList<String>) textList;

		setTextViewId(id);
		setTextTitle((String) ls.get(1));
		setTextContent((String) ls.get(2));
		if ((String) ls.get(3) != null && !((String) ls.get(3)).equals("null")) 
			setReferenceDate((String) ls.get(3));
		if ((String) ls.get(4) != null) 
			setLastUpdated((String) ls.get(4));
		

	}
	
	public TextEditor(Text text) {
		setTextViewId(text.getId());
		setTitle(text.getName());
		setTextContent(text.getContent());
		setReferenceDate(text.getReferenceDate());
		setLastUpdated(null);
		setTextModel(text);
		setRptDesign("NoBirt");
	}
	
	public void build(){
		if(getTextTitle()!=null){
			setTitle(getTextTitle() + " ("+BabelFish.print().referenceDate()+": " + getReferenceDate()+ ")");
		} else {
			setTitle(BabelFish.print().textEditor());
		}
	

		setCollapsible(true);
		setMaximizable(true);
		setSize(600, 500);
		
		formatTextArea();

		if(getTextContent()!=null){
			textArea.setHTML(getTextContent());
		}

		editorToolBar = new TextEditorToolbar(textArea, textArea.getHTML(), this);
		
		setCenterProperties();
		getCenter().setTopComponent(editorToolBar.getToolbar());
		getCenter().add(textArea);
		getCenter().setBottomComponent(getButtonPanel(textArea));
		addCenterPartToWindow();

		//on close handler
		if(getViewerWindow()!=null){
		  getWindow().addWindowListener(TextEditorController.onCloseEditor(getWindow(), getViewerWindow()));
		}
		
    	getCenter().setHeaderVisible(false);

		show();
	}
	
	

	public ContentPanel buildTextEditor(){
		formatTextArea();

		if(getTextContent()!=null){
			textArea.setHTML(getTextContent());
		}

		editorToolBar = new TextEditorToolbar(textArea, textArea.getHTML(), this);
		
		setCenterProperties();
		getCenter().setTopComponent(editorToolBar.getToolbar());
		getCenter().add(textArea);
		getCenter().setBottomComponent(getButtonPanel(textArea));
    	getCenter().setHeaderVisible(false);

		return getCenter();
	}
	
	private void formatTextArea(){
        textArea = new RichTextArea();

		textArea.setHeight("300px");
		textArea.setWidth("100%");

	}
	
	
	private HorizontalPanel getButtonPanel(RichTextArea area){
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.setSpacing(SPACING);

		if (rptdesign.equals("NoBirt")){

			if(getTextViewId()!=null){
				// Add 'Save' button to overwrite/update existing text
				saveResource = new Button(BabelFish.print().save());
				saveResource.setWidth("100px");
				
				if(getTextModel()!=null)
					saveResource.addListener(Events.OnClick, TextEditorController.saveFromTextGroup(this, getTextModel()));
				else 
					saveResource.addListener(Events.OnClick, TextEditorController.save(this));
				buttons.add(saveResource);
			}

			// Add 'Save As' button to create new text
			saveResourceAs = new Button(BabelFish.print().saveAs());
			saveResourceAs.addListener(Events.OnClick, TextEditorController.saveAs(this));
			if(getTextModel()==null)
				buttons.add(saveResourceAs);


        	// Add Link Text to a Resource button
        	/*linkResource = new Button(strings.linkToResource());

        	linkResource.addClickListener(new ClickListener() {
        		public void onClick(Widget sender) {
        			new FenixResourceExplorer().build("all", "TOOLBOX");
        		}
        	});

        	bp.add(linkResource);*/
        }
		else if (rptdesign.equalsIgnoreCase("ADAM_CUSTOM")){
			ok = new Button(BabelFish.print().ok());
			ok.setWidth("60px");
			buttons.add(ok);
			ok.addSelectionListener(ADAMCustomController.setText(TextEditor.this));
		}
		else if (rptdesign.equalsIgnoreCase("ADAM")){
			ok = new Button(BabelFish.print().ok());
			ok.setWidth("60px");
			buttons.add(ok);
			ok.addSelectionListener(ADAMCustomController.setText(TextEditor.this));
		}
		else {
			ok = new Button(BabelFish.print().ok());
			ok.setWidth("60px");
			buttons.add(ok);
			if( comment == null) {				
				ok.addSelectionListener(ReportViewerController.addComment(getReportViewer(), TextEditor.this));
			}
			else {
				ok.addSelectionListener(ReportViewerController.changeModifiesComment(getReportViewer(), TextEditor.this, comment));
			}
		}

		return buttons;

	}

	public ReportViewer getReportViewer() {
		return reportViewer;
	}

	public void setReportViewer(ReportViewer reportViewer) {
		this.reportViewer = reportViewer;
	}

	public RichTextArea getTextArea() {
		return textArea;
	}

	public String getRptDesign() {
		return rptdesign;
	}

	public void setRptDesign(String rptdesign) {
		this.rptdesign = rptdesign;
	}


	public Long getTextViewId() {
		return textID;
	}

	public void setTextViewId(Long textID) {
		this.textID = textID;
	}

	public String getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public void setReferenceDate(String textReferenceDate) {
		this.textReferenceDate = textReferenceDate;
	}

	public String getReferenceDate() {
		return textReferenceDate;
	}

	public void setLastUpdated(String textLastUpdated) {
		this.textLastUpdated = textLastUpdated;
	}

	public String getLastUpdated() {
		return textLastUpdated;
	}

	public void setViewerWindowIndex(String index) {
		this.index = index;
	}

	public String getViewerWindowIndex() {
		return index;
	}

	public void setViewerWindow(Window viewer) {
		this.viewerWindow = viewer;
	}

	public Window getViewerWindow() {
		return viewerWindow;
	}

	public Button getSaveResourceAs() {
		return saveResourceAs;
	}

	public DataListItem getComment() {
		return comment;
	}

	public void setComment(DataListItem comment) {
		this.comment = comment;
	}

	public Text getTextModel() {
		return textModel;
	}

	public void setTextModel(Text text) {
		this.textModel = text;
	}

	public void setTextArea(RichTextArea textArea) {
		this.textArea = textArea;
	}

	public ADAMCustomShowBox getAdamCustomShowBox() {
		return adamCustomShowBox;
	}

	public void setAdamCustomShowBox(ADAMCustomShowBox adamCustomShowBox) {
		this.adamCustomShowBox = adamCustomShowBox;
	}


	
	
}
