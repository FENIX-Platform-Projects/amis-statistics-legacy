package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOSTATCompareData {
	
	ContentPanel panel;
	
	// domains + selection
	ContentPanel mainPanel;
	
	// center selection panel
	ContentPanel selectionMainPanel;
	
	FAOSTATCompareSelectionPanel selectionPanel;
	
	Html title;
	
	ContentPanel outputPanel;
	
	ContentPanel timerangePanel;
	
	FAOSTATCompareDomainPanel domains;
	
	FAOSTATCompareDataButtons buttonsPanel;

	public FAOSTATCompareData() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		mainPanel = new ContentPanel();
		mainPanel.setBodyBorder(false);
		mainPanel.setHeaderVisible(false);
		
		selectionMainPanel = new ContentPanel();
		selectionMainPanel.setBodyBorder(false);
		selectionMainPanel.setHeaderVisible(false);

		outputPanel = new ContentPanel();
		outputPanel.setAutoWidth(true);
		outputPanel.setAutoHeight(true);
		outputPanel.setBodyBorder(false);
		outputPanel.setHeaderVisible(false);
		
		domains = new FAOSTATCompareDomainPanel();
		selectionPanel = new FAOSTATCompareSelectionPanel();
		buttonsPanel = new FAOSTATCompareDataButtons();
	}
	
	public ContentPanel build() {
		
		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(false) + "px"));
		
		panel.add(DataViewerClientUtils.addVSpace(15));

		panel.add(buildMainPanel());
		
//		panel.add(DataViewerClientUtils.addVSpace(5));
		
//		panel.add(buildOutputPanel());		
//		
//		panel.add(DataViewerClientUtils.addVSpace(15));
		
		return panel;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.add(DataViewerClientUtils.addHSpace(10));
		
		p.add(buildDomains());
		
		p.add(DataViewerClientUtils.addHSpace(10));
		
		p.add(buildSelectionPanel());
		
		return p;
	}
	
	private ContentPanel buildSelectionPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		Html html = new Html();
//		html.setHtml("click on the tree...bla bla bla");
		selectionMainPanel.add(html);
		
		panel.add(selectionMainPanel);
		
		panel.add(DataViewerClientUtils.addVSpace(5));
		
		panel.add(buildOutputPanel());		
		
		panel.add(DataViewerClientUtils.addVSpace(15));
		
		return panel;
	}
	
	private HorizontalPanel buildOutputPanel(){
		HorizontalPanel p = new HorizontalPanel();

//		p.add(DataViewerClientUtils.addHSpace(30));
		p.add(outputPanel);
		
		return p;
	}
	
	private VerticalPanel buildDomains() {
		
		VerticalPanel panel = new VerticalPanel();
		
		panel.add(domains.build(this));
		
		return panel;
	}

	public FAOSTATCompareSelectionPanel getSelectionPanel() {
		return selectionPanel;
	}

	public FAOSTATCompareDomainPanel getDomains() {
		return domains;
	}

	public ContentPanel getOutputPanel() {
		return outputPanel;
	}

	public ContentPanel getSelectionMainPanel() {
		return selectionMainPanel;
	}

	
	



	
}
