package org.fao.fenix.web.modules.adam.client.view.venn.matrix;

import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMEmptyValuesPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class ADAMVennRecipientMatrix extends ADAMVennMatrix {

	
	
	public ADAMVennRecipientMatrix() {
		/*panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		
		matrixPanel = new ContentPanel();
		matrixPanel.setHeaderVisible(false);
		matrixPanel.setBorders(false);
		
		
		menuPanel = new ContentPanel();
		menuPanel.setHeaderVisible(false);
		menuPanel.setBorders(false);
	
		
		window = new Window();
		window.setHeading("Priorities");
		
//		window.setHeading("Organizational Results per Recipient Countries");
		window.setAutoHeight(true);
		window.setAutoWidth(true);
//		window.setWidth(Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH));
		window.setScrollMode(Scroll.AUTO);*/

	}
	
	
	public VerticalPanel build(ADAMResultVO rvo) {
	
		VerticalPanel p = new VerticalPanel();
		VerticalPanel h1 = buildRecipientMatrixBoxMenu(rvo, ADAMConstants.BIGGER_MENU_GAP);
		
		if(ADAMBoxMaker.countriesSelected.size() == 1)
			rvo.setTableColumnWidth("300");
		else
			rvo.setTableColumnWidth("250");
		
		p.add(h1);
	
		if(rvo.getTableContents().size() == 0)
			p.add(ADAMEmptyValuesPanel.build("No Government/FAO agreed priorities available for the current selection"));
		else	
			p.add(ADAMTableMaker.buildBigMatrix(rvo));
		
		
		return p;
	}
	
/*	public ContentPanel build(ADAMResultVO rvo, ADAMQueryVO qvo, Boolean showWindow) {
		panel.removeAll();
		
		//menuPanel.add(addMenu(rvo, qvo));
		
		menuPanel.add(buildMatrixBoxMenu(rvo, qvo, ADAMConstants.BIG_MENU_VENN_RECIPIENT_MATRIX_GAP_WIDTH, "", "", null, false, false));
		matrixPanel.add(addMatrix(rvo));
		
		panel.add(menuPanel);
		panel.add(matrixPanel);
		
		
		return panel;
		if ( showWindow ) {
			window.add(panel);
			window.show();
		}
	}*/
	
	/*public void build(ADAMResultVO rvo, ADAMQueryVO qvo, Boolean showWindow) {
		panel.removeAll();
		
		
		menuPanel.add(addMenu(rvo, qvo));
		matrixPanel.add(addMatrix(rvo));
		
		panel.add(menuPanel);
		panel.add(matrixPanel);
		
		
		if ( showWindow ) {
			window.add(panel);
			window.show();
		}
	}*/
	
	/*private  ContentPanel addMenu(ADAMResultVO rvo, ADAMQueryVO qvo) { 
		return buildBigVennMatrixRecipientMenu(rvo, qvo, "", "", null, false);
	}
	
	private ContentPanel addMatrix(ADAMResultVO rvo) {
		return ADAMVennRecipientMatrixController.buildVennRecipientMatrixPanel(rvo);
	}
	*/

}
