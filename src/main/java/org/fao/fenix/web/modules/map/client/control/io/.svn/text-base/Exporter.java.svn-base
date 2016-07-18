package org.fao.fenix.web.modules.map.client.control.io;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class Exporter {
	
	private String layerListName;
	private String layerName;
	private String geoserverUrl;	
	private Long geoviewid;
	
	private MapWindow mapWindow;
	private LayerItem layerItem;

	public Exporter(LayerItem layerItem, MapWindow mw) {
		String url = (layerItem.getWMSUrl().indexOf("wms") > 0 ? 
				layerItem.getWMSUrl().substring(0, layerItem.getWMSUrl().indexOf("wms")) :
				layerItem.getWMSUrl());
		
		this.geoserverUrl = url;		
		this.mapWindow = mw;
		this.layerItem = layerItem;		
		this.geoviewid = layerItem.getUniqueId();
		this.layerName = layerItem.getLayerName();
		this.layerListName = layerItem.getLayerTitle();
		
		showExporterWindow();
	}
	
	private void showExporterWindow(){
		Window exporterWin = new Window();
		
		if(layerItem.isVect()){
			exporterWin.setTitle("Shape file exporter window  - " + this.layerListName);
			exporterWin.setHeading("Shape file exporter window - " + this.layerListName);
		}else{
			exporterWin.setTitle("GeoTiff exporter window  - " + this.layerListName);
			exporterWin.setHeading("GeoTiff exporter window - " + this.layerListName);
		}

		exporterWin.setCollapsible(false);
		exporterWin.setWidth(350);
		exporterWin.setAutoHeight(true);
		exporterWin.setPosition(100, 350);
		
		// Create a FormPanel and point it at a service.
		FormPanel form = new FormPanel();
		form.setAutoHeight(true);
		form.setFrame(true);
		form.setHeaderVisible(false);
		
		final TextField<String> minX = new TextField<String>();
		minX.setName("MINX");
		minX.setTitle("minX: ");
		minX.setFieldLabel("minX");
		minX.setReadOnly(false);
		minX.setWidth("200px");
		minX.setHeight("20px");
		form.add(minX);

		final TextField<String> minY = new TextField<String>();
		minY.setName("MINY");
		minY.setTitle("minY: ");
		minY.setFieldLabel("minY");
		minY.setReadOnly(false);
		minY.setWidth("200px");
		minY.setHeight("20px");
		form.add(minY);

		final TextField<String> maxX = new TextField<String>();
		maxX.setName("MAXX");
		maxX.setTitle("maxX: ");
		maxX.setFieldLabel("maxX");
		maxX.setReadOnly(false);
		maxX.setWidth("200px");
		maxX.setHeight("20px");
		form.add(maxX);

		final TextField<String> maxY = new TextField<String>();
		maxY.setName("MAXY");
		maxY.setTitle("maxY: ");
		maxY.setFieldLabel("maxY");
		maxY.setReadOnly(false);
		maxY.setWidth("200px");
		maxY.setHeight("20px");
		form.add(maxY);
		
		exporterWin.add(form);
		
		final Status statusBar = new Status();
		statusBar.clearStatus("Ready");
		statusBar.setEnabled(true);		
		exporterWin.add(statusBar);
		
		SelectionListener<ButtonEvent> expListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Info.display("Button select", "The Export button as been pressed !");
				
				boolean submitErrors = false;
				
				statusBar.clearStatus("Ready");
				
				String minx, miny, maxx, maxy;
				minx = minX.getValue().toString();
				miny = minY.getValue().toString();
				maxx = maxX.getValue().toString();
				maxy = maxY.getValue().toString();
				
				if (minx == null || miny == null || maxx == null || maxy == null ||
					minx.equals("") || miny.equals("") || maxx.equals("") || maxy.equals("")) {
					submitErrors = true;
					FenixAlert.error(BabelFish.print().error(), "Invalid Envelope coordinates.");
				}
				
				if (!submitErrors) {
					if (layerItem.isVect()){
						windowOpen("vector", "top=10, left=10, width=250, height=200, status=no, menubar=no, toolbar=no scrollbar=no", 
								minx, miny, maxx, maxy);
					}else{
						windowOpen("raster", "top=10, left=10, width=250, height=200, status=no, menubar=no, toolbar=no scrollbar=no", 
								minx, miny, maxx, maxy);
					}
				}
			}  
        };  
        
		SelectionListener<ButtonEvent> aoiListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				setFieldsId(minX.getItemId(),minY.getItemId(),maxX.getItemId(),maxY.getItemId());
				Info.display("Selection", "Select the Area of Interest into the map");
				statusBar.setBusy("Select the Area of Interest into the map");
			}  
        }; 
        
        ButtonBar buttonBar = new ButtonBar();  
        buttonBar.add(new Button("Export", expListener));       
        buttonBar.add(new Button("Area of Interest", aoiListener));
        exporterWin.setBottomComponent(buttonBar);
   
		exporterWin.show();
	}

	private native void windowOpen(String name, String features, String minx, String miny, String maxx, String maxy)/*-{
		
		var url = this.@org.fao.fenix.web.modules.map.client.control.io.Exporter::geoserverUrl;	
		var layName = this.@org.fao.fenix.web.modules.map.client.control.io.Exporter::layerName;	
		
		if(name=="vector"){
			url += "ows?outputFormat=shape-zip&request=GetFeature&Service=WFS&version=1.0.0&typeName="+layName+"&BBOX="+minx+","+miny+","+maxx+","+maxy;
		}else if(name=="raster"){		
			url += "ows?bbox="+minx+","+miny+","+maxx+","+maxy+"&Format=geotiff&request=GetCoverage&service=WCS&version=1.0.0&coverage="+layName+"&resx=0.1&resy=0.1&crs=EPSG:4326";
		}
		$wnd.window.open(url, name, features);
	}-*/;
	
	private native void setFieldsId(String minxId, String minyId, String maxxId, String maxyId)/*-{		
		$wnd.envelopeFieldsId[0] = minxId;	
		$wnd.envelopeFieldsId[1] = minyId;	
		$wnd.envelopeFieldsId[2] = maxxId;	
		$wnd.envelopeFieldsId[3] = maxyId;		
	}-*/;
}
















