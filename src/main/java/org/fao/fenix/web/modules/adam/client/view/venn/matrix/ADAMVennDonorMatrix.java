package org.fao.fenix.web.modules.adam.client.view.venn.matrix;

import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMEmptyValuesPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class ADAMVennDonorMatrix extends ADAMVennMatrix {

	
	
	public ADAMVennDonorMatrix() {
		/*panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		
		matrixPanel = new ContentPanel();
		matrixPanel.setHeaderVisible(false);
		matrixPanel.setBorders(false);
		
		menuPanel = new ContentPanel();
		menuPanel.setHeaderVisible(false);
		menuPanel.setBorders(false);*/
	}
	
	/*public void build(ADAMResultVO rvo) {
		panel.removeAll();
		menuPanel.removeAll();
		matrixPanel.removeAll();
		
		menuPanel.add(addMenu(rvo));
		matrixPanel.add(addMatrix(rvo));
		
		panel.add(menuPanel);
		panel.add(matrixPanel);
		
		panel.layout();
		
//		window.add(panel);
//		window.show();
	}
	*/
	
	public VerticalPanel build(ADAMResultVO rvo) {
		
		VerticalPanel p = new VerticalPanel();
		VerticalPanel h1 = buildDonorMatrixBoxMenu(rvo, ADAMConstants.BIGGER_MENU_GAP);
		
		if(ADAMBoxMaker.countriesSelected.size() > 1 ){
			rvo.setGroupTable(true);
			rvo.setGroupingTableColumn(0);		
		}
		
		p.add(h1);
		
		if(rvo.getTableContents().size() == 0)
			p.add(ADAMEmptyValuesPanel.build("No Resource Partner priorities available for the current selection"));
		else	
			p.add(ADAMTableMaker.buildBigMatrix(rvo));
			
		return p;
	}
	
	/*private  ContentPanel addMenu(ADAMResultVO rvo) { 
		return buildBigVennMatrixDonorMenu(rvo, "", "", null, false);
	}
	
	private ContentPanel addMatrix(ADAMResultVO rvo) {
		return ADAMVennDonorMatrixController.buildVennDonorMatrixPanel(rvo);
	}*/
	

}
