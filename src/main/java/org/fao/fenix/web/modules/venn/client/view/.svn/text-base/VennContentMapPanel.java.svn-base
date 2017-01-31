package org.fao.fenix.web.modules.venn.client.view;

import org.fao.fenix.web.modules.haiti.client.view.HaitiOLPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;


public class VennContentMapPanel extends FenixToolBase {
	


	private String language;
	
	private String height;
	
	private String width;

	
	public VennContentMapPanel(String width, String height, String language) {
		this.language = language;
		this.height = height;
		this.width = width;
		
		
	}
	
	public void build(VennContentMapPanel vennContentMapPanel) {
		buildCenterPanel(vennContentMapPanel);
		format();
	}
	
	private void buildCenterPanel(VennContentMapPanel vennContentMapPanel) {
		setCenterProperties();
		addCenterPartToWindow();
	}
	
	private void format() {
		getToolBase().setHeaderVisible(false);
		getCenter().setHeaderVisible(false);
		getCenter().setBodyBorder(false);
		getToolBase().setBodyBorder(false);
		getToolBase().setWidth(width);
		getToolBase().setHeight(computeHeightRatio(width, height));
	}

	
	
	private Integer computeHeightRatio(String width, String height) {
		Double finalHeight = new Double(200);
		Double h = Double.parseDouble(height.substring(0, height.length() - 2));
		Double w = Double.parseDouble(width.substring(0, width.length() - 2));
		
		System.out.println("H: " + h);
		System.out.println("W: " + w);
		
		if ( finalHeight > h) {			
			return finalHeight.intValue();
		}
		
		else {
			Double calcultateH = (Double) (w / 1.9);
			System.out.println("calcultateH: " + calcultateH);
			if ( calcultateH > finalHeight)	
				return calcultateH.intValue();
		}
		
		return finalHeight.intValue();
	}
	
	
	
	
}
