package org.fao.fenix.web.modules.map.client.colorpicker.ui;

import org.fao.fenix.web.modules.map.client.colorpicker.definitions.FCPBarPaletteDefinition;
import org.fao.fenix.web.modules.map.client.colorpicker.definitions.FCPPaletteDefinition;
import org.fao.fenix.web.modules.map.client.colorpicker.util.ButtonStyleCommand;
import org.fao.fenix.web.modules.map.client.colorpicker.util.ColorUtils;
import org.fao.fenix.web.modules.map.client.view.sldeditor.LineSymbolizerForm;
import org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm;
import org.fao.fenix.web.modules.map.client.view.sldeditor.PolygonSymbolizerForm;
import org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class FenixColorPicker extends Composite implements ColorSelectedHandler{

	private HorizontalPanel container;
    private ColorPanel colorPallete;
    private ColorPanel colorBar;
    private FCPPaletteDefinition colorPalleteGen = new FCPPaletteDefinition();
    private FCPBarPaletteDefinition colorBarGen = new FCPBarPaletteDefinition();
    private TextBox txtHue;
    private TextBox txtSaturation;
    private TextBox txtValue;
    private TextBox txtRed;
    private TextBox txtGreen;
    private TextBox txtBlue;
    private FlowPanel colorSwatch = new FlowPanel();
    
    private PointSymbolizerForm pointSymb = null;
    private PolygonSymbolizerForm polygonSymb = null;
    private LineSymbolizerForm lineSymb = null;
    private SldClassifier classifier = null;
    private String component = null;
    private Window colorWindow = null;  
    
    private String initColor;
    private Element element;
    
    public FenixColorPicker (PointSymbolizerForm pointSymb, String component){
    	this.pointSymb = pointSymb;
    	this.component = component;
    	
    	if(component.equalsIgnoreCase("stroke")){
    		this.initColor = pointSymb.getStrokeColor();
    	}else if(component.equalsIgnoreCase("fill")){
    		this.initColor = pointSymb.getFillColor();
    	}
    	
    	build();
    }
    
    public FenixColorPicker (PolygonSymbolizerForm polygonSymb, String component){
    	this.polygonSymb = polygonSymb;
    	this.component = component;
    	
    	if(component.equalsIgnoreCase("stroke")){
    		this.initColor = polygonSymb.getStrokeColor();
    	}else if(component.equalsIgnoreCase("fill")){
    		this.initColor = polygonSymb.getFillColor();
    	}
    	
    	build();
    }
    
    public FenixColorPicker (LineSymbolizerForm lineSymb, String component){
    	this.lineSymb = lineSymb;
    	this.component = component;
    	
    	if(component.equalsIgnoreCase("stroke")){
    		this.initColor = lineSymb.getStrokeColor();
    	}
    	
    	build();
    }   
    
    public FenixColorPicker (SldClassifier classifier, String component){
    	this.classifier = classifier;
    	this.component = component;
    	
    	if(component.equalsIgnoreCase("start")){
    		this.initColor = classifier.getStartColor();
    	}else if(component.equalsIgnoreCase("end")){
    		this.initColor = classifier.getEndColor();
    	}else if(component.equalsIgnoreCase("mid")){
    		this.initColor = classifier.getMidColor();
    	}
    	
    	build();
    } 
    
    public FenixColorPicker (String componentId, Element element){    	
    	this.component = componentId;
    	this.element = element;
    	this.initColor = initialize(componentId).toString(); 	
    	build();
    }  
    
    private native JavaScriptObject initialize(String Id)/*-{
    	var color = $doc.getElementById(Id);
    	return color.value;
    }-*/;
    
    private native void setCellColor(String Id, String color)/*-{
    	var text = $doc.getElementById(Id);
    	text.value = color;
    }-*/;
    
    private void build(){
    	
			colorWindow = new Window();
	
			colorWindow.setTitle("Color Picker");
			colorWindow.setHeading("Color Picker");
			colorWindow.setCollapsible(false);
			colorWindow.setWidth(300);
			colorWindow.setHeight(300);
			colorWindow.setPosition(500, 50);
			colorWindow.focus();
			colorWindow.setPlain(true);			
			
			container = new HorizontalPanel();
	        container.setSpacing(5);

	        ButtonStyleCommand barButtonStyle = new ButtonStyleCommand();
	        barButtonStyle.setBorder("none");
	        barButtonStyle.setHeight("4px");
	        barButtonStyle.setMargin("0");
	        barButtonStyle.setPadding("0");
	        barButtonStyle.setWidth("20px");

	        colorPallete = new ColorPanel(colorPalleteGen);
	        colorPallete.addColorSelectedHandler(this);
	        container.add(colorPallete);

	        colorBar = new ColorPanel(colorBarGen, barButtonStyle);
	        colorBar.addColorSelectedHandler(this);
	        container.add(colorBar);
	        
	        txtHue = new DataBox();
	        txtSaturation = new DataBox();
	        txtValue = new DataBox();
	        txtRed = new DataBox();
	        txtGreen = new DataBox();
	        txtBlue = new DataBox();
	        
	        VerticalPanel dataPanel = new VerticalPanel();

	        colorSwatch.setWidth("58px");
	        colorSwatch.setHeight("40px");
	        DOM.setStyleAttribute(colorSwatch.getElement(), "border", "1px solid black");
	        DOM.setStyleAttribute(colorSwatch.getElement(), "marginBottom", "4px");
	        if(this.initColor != null && this.initColor.indexOf("#") != -1){
	        	 DOM.setStyleAttribute(colorSwatch.getElement(), "backgroundColor", this.initColor);
	        }	      
	        dataPanel.add(colorSwatch);
	        
	        {
	            HorizontalPanel f = new HorizontalPanel();
	            f.add(new HTML("<span style='font-family:courier'>H&nbsp;</span>"));
	            f.add(txtHue);
	            dataPanel.add(f);
	        }
	        
	        {
	            HorizontalPanel f = new HorizontalPanel();
	            f.add(new HTML("<span style='font-family:courier'>S&nbsp;</span>"));
	            f.add(txtSaturation);
	            dataPanel.add(f);
	        }

	        {
	            HorizontalPanel f = new HorizontalPanel();
	            f.add(new HTML("<span style='font-family:courier'>V&nbsp;</span>"));
	            f.add(txtValue);
	            dataPanel.add(f);
	        }

	        dataPanel.add(new HTML("<hr />"));

	        {
	            HorizontalPanel f = new HorizontalPanel();
	            f.add(new HTML("<span style='font-family:courier'>R&nbsp;</span>"));
	            f.add(txtRed);
	            dataPanel.add(f);
	        }

	        {
	            HorizontalPanel f = new HorizontalPanel();
	            f.add(new HTML("<span style='font-family:courier'>G&nbsp;</span>"));
	            f.add(txtGreen);
	            dataPanel.add(f);
	        }

	        {
	            HorizontalPanel f = new HorizontalPanel();
	            f.add(new HTML("<span style='font-family:courier'>B&nbsp;</span>"));
	            f.add(txtBlue);
	            dataPanel.add(f);
	        }
	        
	        container.add(dataPanel);
			
			SelectionListener<ButtonEvent> applyListener = new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					colorWindow.hide();
					colorWindow = null;
				}
			};
			
			colorWindow.add(container);
			
			ButtonBar buttonBar = new ButtonBar();
			buttonBar.add(new Button("Apply", applyListener));

			colorWindow.add(buttonBar);			
			colorWindow.show();
    }    	

    public void onColorSelected (ColorSelectedEvent e){
    	
        if (e.getSource() == colorPallete) {
            txtRed.setText(Integer.toString(e.getRed()));
            txtGreen.setText(Integer.toString(e.getGreen()));
            txtBlue.setText(Integer.toString(e.getBlue()));
            
            double[] HSV = ColorUtils.RGBtoHSV(e.getRed(), e.getGreen(), e.getBlue());
            
            txtHue.setText(Integer.toString((int)(HSV[0] * 255)));
            txtSaturation.setText(Integer.toString((int)(HSV[1] * 255)));
            txtValue.setText(Integer.toString((int)(HSV[2] * 255)));
            
            DOM.setStyleAttribute(colorSwatch.getElement(), "backgroundColor", "#"+e.getHexValue());
        }
        else if (e.getSource() == colorBar) {
            double newHue = 1.0 - (e.getPosition() / (double)e.getMaxPositionY());
            colorPalleteGen.setStaticHue(newHue);
            colorPallete.redrawPalette();
        }
        
    	if(this.pointSymb != null){
    		if (this.component.equalsIgnoreCase("stroke")){
    			this.pointSymb.setStrokeColor("#"+e.getHexValue());
    		}else if (this.component.equalsIgnoreCase("fill")){
    			this.pointSymb.setFillColor("#"+e.getHexValue());
    		}
    	}else if(this.polygonSymb != null){
    		if (this.component.equalsIgnoreCase("stroke")){
    			this.polygonSymb.setStrokeColor("#"+e.getHexValue());
    		}else if (this.component.equalsIgnoreCase("fill")){
    			this.polygonSymb.setFillColor("#"+e.getHexValue());
    		}
    	}else if(this.lineSymb != null){
    		if (this.component.equalsIgnoreCase("stroke")){
    			this.lineSymb.setStrokeColor("#"+e.getHexValue());
    		}
    	}else if(this.classifier != null){
    		if (this.component.equalsIgnoreCase("end")){
    			this.classifier.setEndColor("#"+e.getHexValue());
    		}else if (this.component.equalsIgnoreCase("start")){
    			this.classifier.setStartColor("#"+e.getHexValue());
    		}else if (this.component.equalsIgnoreCase("mid")){
    			this.classifier.setMidColor("#"+e.getHexValue());
    		}
    	}else if(this.component != null){
    		setCellColor(this.component, "#"+e.getHexValue());
    		DOM.setStyleAttribute(element, "background", "#"+e.getHexValue());
    		DOM.setStyleAttribute(element, "color", "#"+e.getHexValue());
    	}
    }
    
    public void destroyFenixColorPicker(){
    	if (this.colorWindow != null){
    		this.colorWindow.hide();
    		this.colorWindow = null;
    	}
    }
}
