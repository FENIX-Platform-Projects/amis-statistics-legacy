package org.fao.fenix.web.modules.map.client.view.sldeditor;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SLDSave {
	
	private JavaScriptObject[] symbolizers = null;
	private JavaScriptObject sld = null;
	private SymbolizersForm sf;
	private boolean ruleTitleFlag;
	private String urlServletSave;
	
	
	public SLDSave(Styler styler){		
		this.sld = styler.getRasterSLD();		
		
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		this.urlServletSave = ep.getSLDSaveSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
	}
	
	public SLDSave(Styler styler, SymbolizersForm sf, boolean ruleTitleFlag){			
		this.sf = sf;
		this.ruleTitleFlag = ruleTitleFlag;	
		this.sld = styler.getSLD();		
		
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		this.urlServletSave = ep.getSLDSaveSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
	}
	
	protected void saveVectorStyle(){		
		symbolizers = sf.updateSymbolizerForm();				
		onSaveVectorStyle(symbolizers, urlServletSave);						
	}
	
	protected void saveRasterStyle(){	
		onSaveRasterStyle(sld, urlServletSave);						
	}
	
	private native void onSaveVectorStyle(JavaScriptObject[] symbolizer, String urlServletSave)/*-{		
	
		var style = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers;
	
		var id;
	    for(var name in style) id = name; 
		
		for (var i=0; i<symbolizer.length; i++){
			if(symbolizer[i] != null){
				if (this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Polygon"]){
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Polygon"] = symbolizer[i];
				}else if (this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Point"]){
						this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Point"] = symbolizer[i];
				}else if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Line"]){
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Line"] = symbolizer[i];
				}	
			}
		}	
		
		var format = new $wnd.OpenLayers.Format.SLD();
		var data = format.write(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld);	
		
		if(data.indexOf("<?xml") == -1){
			data = '<?xml version="1.0" encoding="UTF-8"?>' + data;
		}		
		
		$wnd.OpenLayers.Request.POST({
			url: urlServletSave,
			data: data,
			callback: handlerPost,
			scope: this												  
		});
		
		function handlerPost(req) {		
			var path = req.responseText;
			
			var variables = [];
			variables = path.split("=");
			
			path = variables[1];
			
			var url = urlServletSave + "?PATH=" + path;			
			$wnd.window.open(url,'save','menubar=yes,toolbar=yes,scrollbars=yes,width=400,height=350,resizable=yes');
		}									
	}-*/;
	
	private native void onSaveRasterStyle(JavaScriptObject style, String urlServletSave)/*-{		
	
		var style = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDSave::sld;	
		
		var format = new $wnd.OpenLayers.Format.XML();
		var data = format.write(style);	
		
		if(data.indexOf("<?xml") == -1){
			data = '<?xml version="1.0" encoding="UTF-8"?>' + data;
		}	
		
		$wnd.OpenLayers.Request.POST({
			url: urlServletSave,
			data: data,
			callback: handlerPost,
			scope: this												  
		});
		
		function handlerPost(req) {		
			var path = req.responseText;
			
			var variables = [];
			variables = path.split("=");
			
			path = variables[1];
			
			var url = urlServletSave + "?PATH=" + path;			
			$wnd.window.open(url,'save','menubar=yes,toolbar=yes,scrollbars=yes,width=400,height=350,resizable=yes');
		}									
	}-*/;
}
