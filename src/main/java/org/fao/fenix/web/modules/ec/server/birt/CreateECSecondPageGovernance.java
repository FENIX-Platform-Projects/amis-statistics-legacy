package org.fao.fenix.web.modules.ec.server.birt;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateECSecondPageGovernance {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String grey = "#C9CBCB";
	
	String foodSituationFontSize = "7.5pt";
	
	public CreateECSecondPageGovernance(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	
	public GridHandle buildGovernance() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("governanceSituation", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** GOVERNANCE TABLE **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildGovernanceTable());
		
		/** GOVERNANCE SOURCE **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildGovernanceSource());
		
		


		return dataGridHandle;
	}
	
	private GridHandle buildGovernanceTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("governanceSituationTable", 2, 4);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** SET TABLE TITLES **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Consumer and market oriented measures</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Producer oriented measures</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Trade policy measures </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>Safety net (increased or introduced)</div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		/** POLICIES TABLE TITLES **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div> "+ ecBean.getGovernanceBean().getPolicyConsumer() +"</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div> "+ ecBean.getGovernanceBean().getPolicyProducer() +"</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>  "+ ecBean.getGovernanceBean().getPolicyTrade() +" </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, foodSituationFontSize, "left");
		text.setContent("<div>  " + ecBean.getGovernanceBean().getPolicySafety() + " </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);
		gridCellHandle.getContent().add(text);


		return dataGridHandle;
	}
	
	private GridHandle buildGovernanceSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("governanceSituationSource", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "right");
		text.setContent("<div>Market and trade policy measures adopted (as of 1 December 2008)</div>");
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	
	
	
	
}