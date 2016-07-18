package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.top20;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByTopicController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeTop20Controller;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomainFilters;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeDomainPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATVisualizeParametersPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATVisualizeTitlePanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsInfoVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;


public class FAOSTATVisualizeTop20 extends FAOSTATVisualizeByDomain {
	    
	    // left panel
	    FAOSTATVisualizeTop20Topics topics;

	    // TODO: they are needed both? 
	    // In the use case should be used just infoVO (the key is actually the current filename)
	    // a code is needed?
	    LinkedHashMap<String, FAOSTATVisualizeQuestionsVO> questions;
	    
	    LinkedHashMap<String, FAOSTATVisualizeQuestionsInfoVO> infoVO;
	    
	    public FAOSTATVisualizeTop20() {
	    	initialize();
	    }
	    
	    private void initialize() {
	    	panel = new ContentPanel();
	    	panel.setHeaderVisible(false);
	    	panel.setBodyBorder(false);
			panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.MAIN_CONTENT_MIN_HEIGHT) + "px");
	    	
	    	topics = new FAOSTATVisualizeTop20Topics();
	    	questions = new LinkedHashMap<String, FAOSTATVisualizeQuestionsVO>();
	    	infoVO = new LinkedHashMap<String, FAOSTATVisualizeQuestionsInfoVO>();
	    	
	    	centerPanel = new ContentPanel();
			centerPanel.setBodyBorder(false);
			centerPanel.setHeaderVisible(false);
			
			centerData = new BorderLayoutData(LayoutRegion.CENTER);  
			centerData.setMargins(new Margins(5));  
			
			filtersPanel = new FAOSTATVisualizeByDomainFilters();
			
			parametersPanel = new FAOSTATVisualizeParametersPanel();
			
			titlePanel = new FAOSTATVisualizeTitlePanel();
			
			googleCategory = FAOSTATCurrentView.VISUALIZE_TOP_20.toString();
	    }
		
	    public ContentPanel build() {
	    	initialize();
			
			panel.add(buildMainPanel());
			panel.layout();

			// populating the tree
			FAOSTATVisualizeTop20Controller.getVOs(this);
			
			return panel;
		}
	    
	    private HorizontalPanel buildMainPanel(){
			HorizontalPanel p = new HorizontalPanel();
			p.setSpacing(10);
			
			p.add(buildTopicsPanel());

			p.add(buildCenterPanel());

			return p;
		}
	    

	    public ContentPanel buildTopicsPanel() {
	    	return topics.build(this);
	    }
	    
	    
		private VerticalPanel buildCenterPanel() {
			VerticalPanel p = new VerticalPanel();		
			
			// title panel
			p.add(titlePanel.build());
			
			p.add(DataViewerClientUtils.addVSpace(10));

			// filters panel
			p.add(filtersPanel.build(null));
			
			// parameters panel
			p.add(parametersPanel.build(false));

			ContentPanel c = new ContentPanel();
			c.setHeaderVisible(false);
			c.setBodyBorder(false);
			c.add(centerPanel, centerData);
			p.add(c);
			return p;
		}

		public FAOSTATVisualizeTop20Topics getTopics() {
			return topics;
		}

		public ContentPanel getPanel() {
			return panel;
		}

		public LinkedHashMap<String, FAOSTATVisualizeQuestionsVO> getQuestions() {
			return questions;
		}

		public LinkedHashMap<String, FAOSTATVisualizeQuestionsInfoVO> getInfoVO() {
			return infoVO;
		}

		public ContentPanel getCenterPanel() {
			return centerPanel;
		}
}
