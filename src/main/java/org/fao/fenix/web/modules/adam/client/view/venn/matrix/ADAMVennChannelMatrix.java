package org.fao.fenix.web.modules.adam.client.view.venn.matrix;

import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMEmptyValuesPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class ADAMVennChannelMatrix extends ADAMVennMatrix {

	
	
	public ADAMVennChannelMatrix() {
	/*	panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		
		matrixPanel = new ContentPanel();
		matrixPanel.setHeaderVisible(false);
		matrixPanel.setBorders(false);
		
		menuPanel = new ContentPanel();
		menuPanel.setHeaderVisible(false);
		menuPanel.setBorders(false);*/
	}
	
	/*public void build(ADAMResultVO rvo, ADAMQueryVO qvo) {
		panel.removeAll();
		menuPanel.removeAll();
		matrixPanel.removeAll();
		
		menuPanel.add(addMenu(rvo,qvo));
		matrixPanel.add(addMatrix(rvo));
		
		panel.add(menuPanel);
		panel.add(matrixPanel);
		
		panel.layout();
		
	}*/
	
    public VerticalPanel build(ADAMResultVO rvo) {
		
		VerticalPanel p = new VerticalPanel();
		VerticalPanel h1 = buildChannelMatrixBoxMenu(rvo, ADAMConstants.BIGGER_MENU_GAP);
		
		if(ADAMBoxMaker.countriesSelected.size() == 1)
			rvo.setTableColumnWidth("300");
		else
			rvo.setTableColumnWidth("250");
		
		p.add(h1);
		
		if(rvo.getTableContents().size() == 0)
			p.add(ADAMEmptyValuesPanel.build("No agreed priorities with FAO are available for the current selection"));
		else	
			p.add(ADAMTableMaker.buildBigMatrix(rvo));
		
		return p;
	}
	
	/*private  ContentPanel addMenu(ADAMResultVO rvo, ADAMQueryVO qvo) { 
		return buildBigVennMatrixChannelMenu(rvo, qvo, "", "", null, false);
	}
	
	private ContentPanel addMatrix(ADAMResultVO rvo) {
		return ADAMVennChannelMatrixController.buildVennChannelMatrixPanel(rvo);
	}*/
	

}
