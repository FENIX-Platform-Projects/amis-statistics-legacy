package org.fao.fenix.web.modules.haiticnsatool.client.view;

import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.FieldSet;


public class HaitiCNSALegendPanel {
	
	private ContentPanel ndvi;	

	private ContentPanel ndvi_va;	
	
	private ContentPanel agric_land;	
	
	private String language;
	
	private String height;
	
	private String width;

	
	public HaitiCNSALegendPanel(String gaul0code, String width, String height, String language) {
		this.language = language;
		this.height = height;
		this.width = width;
		ndvi = new ContentPanel();
//		ndvi.setHeading("Vegetation Index (NDVI)");
		ndvi.setHeaderVisible(false);
		ndvi.add(new Html("<div style='font-size:10.5px'>Vegetation Index (NDVI)<div>"));
		ndvi.setBodyBorder(false);
		agric_land = new ContentPanel();
		agric_land.setBodyBorder(false);
		agric_land.setHeaderVisible(false);
//		agric_land.setHeading("Main Agricultural Land");
		agric_land.add(new Html("<div style='font-size:10.5px'>Main Agricultural Land<div>"));
		ndvi_va = new ContentPanel();
		ndvi_va.setBodyBorder(false);
		ndvi_va.add(new Html("<div style='font-size:10.5px'>NDVI - Difference With Average</div>"));
		ndvi_va.setHeaderVisible(false);
//		ndvi_va.setHeading("Vegetation Index (NDVI) - Diff With Var");
		
		String ndvi_url = "../cnsa/legends/ndvi_"+ language.toUpperCase() +".png";
		String iFrame = "<div align='center'> <img src='" +ndvi_url + "'" + "> </div>";
		ndvi.add(new Html(iFrame));		
		
		String ndvi_va_url = "../cnsa/legends/ndvi_va_"+ language.toUpperCase() +".png";
		iFrame = "<div align='center'> <img src='" +ndvi_va_url + "'" + "> </div>";
		ndvi_va.add(new Html(iFrame));		
		
		String agro_land_url = "../cnsa/legends/agro_land_"+ language.toUpperCase() +".png";
		iFrame = "<div align='center'> <img src='" +agro_land_url + "'" + "> </div>";
		agric_land.add(new Html(iFrame));		
		
	}
	
	public FieldSet build(HaitiCNSAPanel haitiCNSAPanel) {
		fortmat();
		return buildLegendPanel();
	}
	
	
	private FieldSet buildLegendPanel() {
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading(HaitiLangEntry.getInstance(language).layersLegend());
		fieldSet.setCheckboxToggle(true);
		fieldSet.collapse();
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.setWidth(computeRatio(width));

		panel.setSpacing(5);
		panel.add(ndvi_va);
		panel.add(ndvi);
		panel.add(agric_land);
		fieldSet.add(panel);
		fieldSet.setWidth(width);
		fieldSet.setScrollMode(Scroll.AUTO);
		return fieldSet;
	}

	public ContentPanel getNdvi() {
		return ndvi;
	}

	public ContentPanel getNdvi_va() {
		return ndvi_va;
	}

	public ContentPanel getAgro_land() {
		return agric_land;
	}
	
	private void fortmat() {
		ndvi.setSize(210, 275);
		ndvi_va.setSize(210, 275);
		agric_land.setSize(210, 275);
	}
	
	private static String computeRatio(String width) {
		Integer w = Integer.parseInt(width.substring(0, width.length() -2));
		w = w - 25;
		System.out.println("W-panel: " + w);
		
	
		return Integer.toString(w);
	}
	
}
