package org.fao.fenix.web.modules.adam.client.view.profile;

import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class ADAMDonorProfileTabs {
	
	  
	  public static TabPanel build(ADAMDonorProfileVO vo) {  		   
		  
		    TabPanel panel = new TabPanel(); 
		    panel.setPlain(true);
		    TabItem profileItem = new TabItem(BabelFish.print().profile());
		     
		    profileItem.add(new ADAMDonorProfile().build(vo));
		    profileItem.addStyleName("pad-text");  
		   
		    TabItem processesItem = new TabItem(BabelFish.print().processes());  
		    processesItem.add(new ADAMDonorProcesses().build(vo));
		    processesItem.addStyleName("pad-text");  
		    
		    
		    panel.add(profileItem);  
		    panel.add(processesItem);  
		  
		     return panel;
	}  
	   
}  

