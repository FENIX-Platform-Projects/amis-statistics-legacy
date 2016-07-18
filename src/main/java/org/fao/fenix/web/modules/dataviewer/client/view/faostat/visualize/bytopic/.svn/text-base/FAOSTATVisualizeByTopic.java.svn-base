package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByTopicController;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;


public class FAOSTATVisualizeByTopic {
	
	
	List<FAOSTATVisualizeTopicsPanel> topics;

    ContentPanel panel;
    
    LayoutContainer container;
    
//	
//	ContentPanel domainsPanel;
//	
//	
//	ContentPanel filtersPanel;
////	FAOSTATVisualizeByDomainFilters filtersPanel;
//	
//	ContentPanel centerPanel;
//	
//	FAOSTATVisualizeTopicsPanel topics;
//	
//	BorderLayoutData centerData;
//
//	Button exportButton;
//	
//	Button tableButton;
	
	
	public FAOSTATVisualizeByTopic() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		topics = new ArrayList<FAOSTATVisualizeTopicsPanel>();

		container = new LayoutContainer();
	}

	public ContentPanel build() {
		
		panel.addStyleName("home_background");
	    panel.add(container);


		
		TableLayout l = new TableLayout();
		l.setCellHorizontalAlign(HorizontalAlignment.CENTER);
		l.setWidth("1080px");
	    container.setLayout(l); 
	  

	        
	      
//	    container.add(DataViewerClientUtils.addVSpace(10));
//	    container.add(new Html("<div class='visualize_topic_header'>ASK FAOSTAT</div>"));
	    container.add(DataViewerClientUtils.addVSpace(20));

	
		
		/** LOAD QUESTIONS **/
		FAOSTATVisualizeByTopicController.createTopics(this);
		
		container.add(DataViewerClientUtils.addVSpace(20));
		
		panel.layout();

		return panel;
	}
	

	
	
	
//	private VerticalPanel centerPanel() {
//		VerticalPanel p = new VerticalPanel();		
//		
//		p.add(addVSpace(20));
//		
//		p.add(filtersPanel);
//		
//		p.add(addVSpace(30));
//		
//		ContentPanel c = new ContentPanel();
//		c.setHeaderVisible(false);
//		c.setBodyBorder(false);
////		c.addStyleName("content_box");
//		c.add(centerPanel, centerData);
//		p.add(c);
//		return p;
//	}

	
	private HorizontalPanel addVSpace(Integer height) {
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(height);
		return p;
	}
	
	

	
//	private ContentPanel buildDomains() {
//		return topics.build("");
//	}

	public ContentPanel getPanel() {
		return panel;
	}

	public List<FAOSTATVisualizeTopicsPanel> getTopics() {
		return topics;
	}

	public LayoutContainer getContainer() {
		return container;
	}

//	public ContentPanel getDomainsPanel() {
//		return domainsPanel;
//	}


	
//	public ContentPanel getCenterPanel() {
//		return centerPanel;
//	}
//
//	public ContentPanel getFiltersPanel() {
//		return filtersPanel;
//	}


	
}
