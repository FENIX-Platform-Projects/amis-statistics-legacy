package org.fao.fenix.web.modules.map.client.view.sldeditor;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.JavaScriptObject;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SymbolizersForm {

	private JavaScriptObject[] rules;	
	private JavaScriptObject symbolizerType;	
	private JavaScriptObject[] symbolizers;
	private boolean symbModifyed = false; 
	private ContentPanel panel;
	private CardLayout cardLayout;
	
	private LineSymbolizerForm[] lineForm = null;
	private PointSymbolizerForm[] pointForm = null;
	private PolygonSymbolizerForm[] polyForm = null;
	
	public SymbolizersForm(){
	}
	
	public void initSymbolizersForm(JavaScriptObject[] rules){
		this.rules = rules;		
		configureSymbolizers();
		createSymbolizerForm();
	}
	
	private native void configureSymbolizers()/*-{
		var symbType = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::rules[0];
		
		if (symbType.symbolizer["Point"])
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbolizerType = "point";
		else if (symbType.symbolizer["Polygon"])
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbolizerType = "polygon";
		else if (symbType.symbolizer["Line"])
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbolizerType = "line";
	}-*/;
	
	private void createSymbolizerForm(){
		panel = new ContentPanel();
		panel.setWidth(370);
		panel.setId("SymbolizerFormPanel");
		panel.setAutoHeight(true);
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		panel.setHeaderVisible(false);

		
		// adding a fake entry: first entry will have position:absolute
		// and won't be displayed. This is a workaround.
		//Info.display("Info", "Adding dummy panel");
		FormLayout dummyFormLayout = new FormLayout();
		FormPanel dummyPanel = new FormPanel();
		dummyPanel.setLayout(dummyFormLayout);
		dummyPanel.setId("dummyPanel");
		dummyPanel.add(new LabelField("Click on the grid -- this message will be never displayed."));
		dummyPanel.setHeaderVisible(false);
		panel.add(dummyPanel);
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

		int numRules = this.rules.length;
		
		String geometry = symbolizerType.toString();		
		
		if (geometry.equalsIgnoreCase("point")){		
			pointForm = new PointSymbolizerForm[numRules];
			
			for(int i=0; i<numRules; i++) {
				pointForm[i] = new PointSymbolizerForm(this.rules[i], i);
				panel.add(pointForm[i].getSymbolizerForm());	
			}
//			cardLayout.setActiveItem(pointForm[0].getSymbolizerForm()); // part of the workaround

		} else if (geometry.equalsIgnoreCase("polygon")){
			polyForm = new PolygonSymbolizerForm[numRules];
			
			for(int i=0; i<numRules; i++) {
				PolygonSymbolizerForm symbform = new PolygonSymbolizerForm(this.rules[i], i);
				polyForm[i] = symbform;

//				if(i==0) panel.add(new PolygonSymbolizerForm(this.rules[i], 999999).getSymbolizerForm()); // trying another workaround

				panel.add(symbform.getSymbolizerForm());
			}
//			cardLayout.setActiveItem(polyForm[0].getSymbolizerForm()); // part of the workaround

		} else if (geometry.equalsIgnoreCase("line")){
			lineForm = new LineSymbolizerForm[numRules];
			
			for(int i=0; i<numRules; i++) {
				lineForm[i] = new LineSymbolizerForm(this.rules[i], i);
				panel.add(lineForm[i].getSymbolizerForm());
			}

//			cardLayout.setActiveItem(lineForm[0].getSymbolizerForm()); // part of the workaround
		}
	}

	private AbstractSymbolizerForm[] getFormArray() {
		if(this.symbolizerType.toString().equalsIgnoreCase("polygon")){
			return polyForm;
		} else if(this.symbolizerType.toString().equalsIgnoreCase("point")){
			return pointForm;
		} else if(this.symbolizerType.toString().equalsIgnoreCase("line")){
			return lineForm;
		} else
			return null; // should not happen
	}

	public void show(int index) {
	//	Info.display("Changing row selection", "Showing form #"+index); // DEBUG
		AbstractSymbolizerForm[] sfarr = getFormArray();
		cardLayout.setActiveItem(sfarr[index].getSymbolizerForm());
	}

	public JavaScriptObject[] updateSymbolizerForm(){
		AbstractSymbolizerForm[] sfarr = getFormArray();
		this.symbolizers = new JavaScriptObject[sfarr.length];

		for(int i=0; i<sfarr.length; i++) {			
			chargeSymbolizers(sfarr[i].updateSymbolizer(), i);
		}

		return this.symbolizers;
	}
	
	private native void chargeSymbolizers(JavaScriptObject obj, int i)/*-{
		if(obj != null){
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbolizers[i] = obj;
			
			if(!this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbModifyed){
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbModifyed = true;
			}
		}else{
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolizersForm::symbolizers[i] = obj;
		}		
	}-*/;
	
	public LayoutContainer getSymbolizerForm(){
		return this.panel;
	}

	public FormPanel getForm(int index) {
		return getFormArray()[index].getSymbolizerForm();
	}
	
	public boolean isSymbolizersModifyed(){
		return this.symbModifyed;
	}
	
	public void resetSymbModifyed(){
		this.symbModifyed = false;
	}	
	
	public boolean isPolygonSymbolizer(){
		return this.polyForm != null;
	}
	
	public boolean isLineSymbolizer(){
		return this.lineForm != null;
	}
	
	public boolean isPointSymbolizer(){
		return this.pointForm != null;
	}
}
