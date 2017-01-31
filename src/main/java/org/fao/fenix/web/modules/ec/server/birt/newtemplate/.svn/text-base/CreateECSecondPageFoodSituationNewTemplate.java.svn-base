package org.fao.fenix.web.modules.ec.server.birt.newtemplate;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateECSecondPageFoodSituationNewTemplate {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String green = "#047221";
	
	String darkGreen = "#3B9D4B";
	
	String orange = "#FF9933";
	
	String red = "#CA1616";
	
	String darkRed = "#A12312";
	
	String yellow = "#FFE32F";	
	
	String foodSituationSize = "0.4cm";
	
	String foodSituationFontSize = "7.5pt";
	
	String foodSituationBorderSize = "2px";
	
	String faoHungerFontSize = "6.5pt";
	
//	String textFontSize = "9.5pt";
	
	public CreateECSecondPageFoodSituationNewTemplate(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public GridHandle buildGIEWSNote() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("faoFoodSituation", 2, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div> " + ecBean.getCountry() + " is not part of the countries in crisis requiring external assistance </div>");	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setContent("<div><a href='http://www.fao.org/giews/english/hotspots/index.htm'>FAO/GIEWS</a>CPFS 2010</div>");
		gridCellHandle.getContent().add(text);
	
				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "82.5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "17.5%");

		return dataGridHandle;
	}
	
	
	public GridHandle buildFAOFoodSituation() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("faoFoodSituation", 4, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** COLOURS FAO **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkRed);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getFao().getValue().equals("3")) 
			DesignUtils.setBorderGrid(text, "#000000", "3px");
		
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getFao().getValue().equals("2"))
			DesignUtils.setBorderGrid(text, "#000000", "3px");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getFao().getValue().equals("1"))
			DesignUtils.setBorderGrid(text, "#000000", "3px");
		gridCellHandle.getContent().add(text);
		
		/** title **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setContent("<div><a href='http://www.fao.org/giews/english/hotspots/index.htm'>FAO/GIEWS</a>CPFS 2010</div>");
		gridCellHandle.getContent().add(text);
		
		/** Definitions **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  foodSituationFontSize, "left");
		text.setContent("<div>Exceptional shortfall in aggregate food production supplies</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Widespread lack of access</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Severe localized food insecurity</div>");
		gridCellHandle.getContent().add(text);
		
	
				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "27.5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "27.5%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "27.5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "17.5%");

		return dataGridHandle;
	}
	
	public GridHandle buildFAOFoodSituationLegend() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("faoFoodSituationLegend", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>FAO/GIEWS identifies 30 countries facing emergency food crisis and are in need of" +
				"external assistance. These countries are classified into three based on domestic " +
				"production and supply conditions.</div>");
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	
	public GridHandle buildWFPFoodSituation() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("wfpFoodSituation", 6, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** COLOURS FAO **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkRed);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("5")) 
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("4"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, yellow);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("3"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("2"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkGreen);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("1"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		
		/** title **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
//		text.setContent("<div><a href=' http://www.wfp.org/hunger/mapFirefoxHTML%5CShell%5COpen%5CCommand'>WFP/" + ecBean.getFoodSituationBean().getWfp().getDate() + "</a> Hunger Map</div>");
		text.setContent("<div><a href=' http://www.wfp.org/hunger/mapFirefoxHTML%5CShell%5COpen%5CCommand'>WFP/2009</a> Hunger Map</div>");
		gridCellHandle.getContent().add(text);
		
		/** Definitions **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  foodSituationFontSize, "left");
		text.setContent("<div>Very high hunger (>= 35% undernour.)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Moderately high hunger (20-34% undernour.)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Moderately low hunger (10-19% undernour.)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Very low hunger (5-9% undernour.)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Extremely low hunger (<5% undernour.)</div>");
		gridCellHandle.getContent().add(text);

				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "16.6%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(5);
		col.setProperty("width", "16.6%");
		

		return dataGridHandle;
	}
	
	
	public GridHandle buildFAOHungerMap() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("faoHungerMap", 6, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** COLOURS FAO **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#bb281d");
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		Double faoUndernourishmentValue = null;
		
		try {
			faoUndernourishmentValue = Double.valueOf(ecBean.getSocialEconomicBean().getFAO1().getValue());
		}catch (Exception e) {

		}
		
		System.out.println("buildFAOHungerMap(): FAO Undernourishment Value: " + faoUndernourishmentValue);
		
		if ( faoUndernourishmentValue != null )
			if ( faoUndernourishmentValue >= 35 ) 
				DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#ea2b30");
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		
//		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("4"))
		if ( faoUndernourishmentValue != null )
			if ( faoUndernourishmentValue < 35 && faoUndernourishmentValue >= 25 ) 
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#f35e6b");
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( faoUndernourishmentValue != null )
			if ( faoUndernourishmentValue < 24 && faoUndernourishmentValue >= 15 ) 
//		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("3"))
				DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#f792a2");
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
//		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("2"))
		if ( faoUndernourishmentValue != null )
			if ( faoUndernourishmentValue < 15 && faoUndernourishmentValue >= 5 ) 
				DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#f9c7d5");
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
//		if ( ecBean.getFoodSituationBean().getWfp().getValue().equals("1"))
		if ( faoUndernourishmentValue != null)
			if ( faoUndernourishmentValue < 5  ) 
				DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		
		/** title **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
//		text.setContent("<div><a href=' http://www.wfp.org/hunger/mapFirefoxHTML%5CShell%5COpen%5CCommand'>WFP/" + ecBean.getFoodSituationBean().getWfp().getDate() + "</a> Hunger Map</div>");
		text.setContent("<div><a href='http://www.fao.org/hunger/en/'>FAO Hunger Map</a></div>");
		gridCellHandle.getContent().add(text);
		
		/** Definitions **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  faoHungerFontSize, "left");
		text.setContent("<div>Very high <br> (>= 35%)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, faoHungerFontSize, "left");
		text.setContent("<div>High <br> (25-34%)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, faoHungerFontSize, "left");
		text.setContent("<div>Moderately high (15-24%)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, faoHungerFontSize, "left");
		text.setContent("<div>Moderately low (5-14%)</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, faoHungerFontSize, "left");
		text.setContent("<div>Very low <br> (< 5% under.)</div>");
		gridCellHandle.getContent().add(text);

				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "16.6%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(5);
		col.setProperty("width", "16.6%");
		

		return dataGridHandle;
	}
	
	public GridHandle buildWFPFoodSituationLegend() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("wfpFoodSituationLegend", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>WFP Hunger Map classifies all countries of the world into five based on % of population undernourished.</div>");
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	public GridHandle buildFAOHungerMapLegend() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("faoHungerMapLegendTable", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>FAO Hunger Map classifies all countries of the world into five based on % of population undernourished.</div>");
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	
	public GridHandle buildIFPRIIFoodSituation() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("ifpriFoodSituation", 6, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** COLOURS FAO **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkRed);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getIfpri().getValue().equals("5")) 
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getIfpri().getValue().equals("4"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, yellow);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getIfpri().getValue().equals("3"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getIfpri().getValue().equals("2"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkGreen);
		text.setContent("<div> </div>");
		text.setHeight(foodSituationSize);
		if ( ecBean.getFoodSituationBean().getIfpri().getValue().equals("1"))
			DesignUtils.setBorderGrid(text, "#000000", foodSituationBorderSize);
		gridCellHandle.getContent().add(text);
		
		
		/** title **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
//		text.setContent("<div><a href='http://www.ifpri.org/sites/default/files/publications/ghi08.pdf'>IFPRI/" + ecBean.getFoodSituationBean().getIfpri().getDate() + "</a> GHI</div>");
		text.setContent("<div><a href='http://www.ifpri.org/sites/default/files/publications/ghi09.pdf'>IFPRI/2009</a> GHI</div>");
		gridCellHandle.getContent().add(text);
		
		/** Definitions **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  foodSituationFontSize, "left");
		text.setContent("<div>Extremely alarming</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Alarming</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Serious</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Moderate</div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Low</div>");
		gridCellHandle.getContent().add(text);

				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "16.6%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "16.6%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(5);
		col.setProperty("width", "16.6%");
		

		return dataGridHandle;
	}
	
	public GridHandle buildIFPRIFoodSituationLegend() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("ifpriFoodSituationLegend", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>IFPRI Global Hunger Index is based on a simple average of: % of population undernourished, " +
						"% ofunderweight in children under five, and % of children dying before the age of five.</div>");
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	public GridHandle buildFEWSNETFoodSituation() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("fewsnetFoodSituation", 2, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** TEXT **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		if ( ecBean.getFoodSituationBean().getShowFewsnetLink() != null) {
			if ( !ecBean.getFoodSituationBean().getShowFewsnetLink().equals("0"))
				text.setContent("<div>" + ecBean.getFoodSituationBean().getFewsnetText() + " ...<a href=' "+ ecBean.getFoodSituationBean().getFewsnetLink() +"'>more</a></div>");
			else
				text.setContent("<div>" + ecBean.getCountry() + " " + ecBean.getFoodSituationBean().getFewsnetText() + "</div>");
		}
		gridCellHandle.getContent().add(text);	
		
		
		/** SOURCE **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "center");
		text.setContent("<div><a href='http://www.fews.net'>FEWSNET</a></div>");
		gridCellHandle.getContent().add(text);

				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "83.4%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "16.6%");	

		return dataGridHandle;
	}
	
	
}