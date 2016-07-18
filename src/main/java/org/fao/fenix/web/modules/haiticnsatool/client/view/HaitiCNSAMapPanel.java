package org.fao.fenix.web.modules.haiticnsatool.client.view;

import org.fao.fenix.web.modules.haiti.client.view.HaitiOLPanel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.Html;


public class HaitiCNSAMapPanel extends FenixToolBase {
	
	private HaitiOLPanel haitiOLPanel;	

	private String language;
	
	private String height;
	
	private String width;

	private String title;
	
	public HaitiCNSAMapPanel(String gaul0code, String width, String height, String language, long id) {
		this.language = language;
		this.height = height;
		this.width = width;
		haitiOLPanel = new HaitiOLPanel(id);
		
	}
	
	public void build(HaitiCNSAPanel haitiCNSAPanel) {
		buildCenterPanel(haitiCNSAPanel);
		format();
	}
	
	private void buildCenterPanel(HaitiCNSAPanel haitiCNSAPanel) {
		setCenterProperties();
		getCenter().add(haitiOLPanel.build(haitiCNSAPanel, this, language));
		addCenterPartToWindow();
	}
	
	private void format() {
		getToolBase().setHeading(title);
		getCenter().setHeaderVisible(false);
		getCenter().setBodyBorder(false);
		getToolBase().setBodyBorder(false);
		getToolBase().setHeight(computeHeightRatio(width, height));
		getToolBase().setWidth(computeWidthRatio(width));
	}

	public HaitiOLPanel getHaitiOLPanel() {
		return haitiOLPanel;
	}
	
	private Integer computeHeightRatio(String width, String height) {
		Double finalHeight = new Double(300);
		Double h = Double.parseDouble(height.substring(0, height.length() - 2));
		Double w = Double.parseDouble(width.substring(0, width.length() - 2));
		
		System.out.println("H: " + h);
		System.out.println("W: " + w);
		
		
		
		
		if ( finalHeight > h) {			
			return finalHeight.intValue();
		}
		
		else {
//			Double calcultateH = (Double) (w / 1.9);
			Double calcultateH = (Double) (w / 2.8);
			System.out.println("calcultateH: " + calcultateH);
			if ( calcultateH > finalHeight)	
				return calcultateH.intValue();
		}

		
		return finalHeight.intValue();
	}
	
	private Integer computeWidthRatio(String width) {
		Double finalWidth = new Double(700);
		Double w = Double.parseDouble(width.substring(0, width.length() - 2));
		
		System.out.println("computeWidthRatio W: " + w);
		
	
		w = (w / 2) - 10;
		
		System.out.println("after computeWidthRatio W: " + w);
		
		if ( finalWidth > w) {			
			return w.intValue();
		}
		
		else {
			return 330;
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	
	
	
	
	
}
