package org.fao.fenix.web.modules.birt.server.exportmap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.elements.MasterPage;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.map.mapretriever.LegendRetriever;
import org.fao.fenix.map.mapretriever.MapRetriever;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;

public class MapReport {
	
	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	MapView mapView;
	List<GeoView> gvList;
	int width;
	int height;
	
	public MapReport(MapView mapView, List<GeoView> gvList, int width, int height){
		this.mapView = mapView;
		this.gvList = gvList;
		this.width = width;
		this.height = height;
	}
	
	public String createReport() {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();;

		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");
			simpleMasterPage.setPageType("a4");
			
			//simpleMasterPage.setOrientation("landscape");
			simpleMasterPage.setOrientation("portrait");
			
			simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, "1.45cm");
			simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, "1.45cm");

			
			designHandle.getMasterPages().add(simpleMasterPage);

			createBody();

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);
			

			designHandle.close();
			Platform.shutdown();

			System.out.println("Finished Map report ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return System.getProperty("java.io.tmpdir") + File.separator + rep;
	}
	
	private void createBody() throws SemanticException{
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<center style='font-size:20px; font-weight:bold;'>" + mapView.getTitle() + "</center><br>");
		
		designHandle.getBody().add(text);
		
		GridHandle dataGridHandle = designFactory.newGridItem("dataGrid", 1, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		designHandle.getBody().add(dataGridHandle);
		
		MapRetriever map = new MapRetriever(mapView,System.getProperty("java.io.tmpdir"));
		map.setWidth(width);
		map.setHeight(height);
		BufferedImage image = map.getMapImage();
		String nameFile=String.valueOf((Math.random() * 10)) + ".png";
		try{
			//File f=new File(System.getProperty("java.io.tmpdir") + File.separator + "/MapImg");
			ImageIO.write(image, "png", new File(System.getProperty("java.io.tmpdir") + File.separator + nameFile));
			ImageHandle imageHandle = designHandle.getElementFactory().newImage("Map");
			imageHandle.setDisplayName("map");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			imageHandle.setFile("\"" + System.getProperty("java.io.tmpdir") + File.separator + nameFile + "\"");
		
			RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(imageHandle);
						
			//space
			row = (RowHandle) dataGridHandle.getRows().get(1);
			CellHandle spaceCell = (CellHandle) row.getCells().get(0);
			text = designHandle.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<br><br>");
			spaceCell.getContent().add(text);
			
			LegendRetriever lr = new LegendRetriever();
			
			row = (RowHandle) dataGridHandle.getRows().get(2);
			CellHandle legendCell = (CellHandle) row.getCells().get(0);
			
			for (GeoView gv : gvList){
				lr.setLayer(gv);
				
				String fileName = System.getProperty("java.io.tmpdir") + File.separator + gv.getTitle() + ".png";
				File legendImage = new File(fileName); 
				
				if(lr.retrieve(legendImage)) {
					
					text = designHandle.getElementFactory().newTextItem("text");
					text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					text.setContent("<div style='font-size:12px;'>" + gv.getTitle() + "</div>");
					legendCell.getContent().add(text);
					
					ImageHandle legend = designHandle.getElementFactory().newImage("Lenged");
					legend.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
					legend.setFile("\"" + fileName + "\"");
					legendCell.getContent().add(legend);
					
					text = designHandle.getElementFactory().newTextItem("text");
					text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					text.setContent("<br>");
					legendCell.getContent().add(text);
					
				} else {
					System.out.println("ERROR adding legend.");
				}
				
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
