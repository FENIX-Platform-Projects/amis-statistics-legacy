package org.fao.fenix.web.modules.re.client.view.communication;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchParameters;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SearchParametersCommunication extends SearchParameters {
	
	ListBox lookIn;
	
	public SearchParametersCommunication(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
		HTML labelNode=new HTML(("<b>"+BabelFish.print().lookIn()+":<b>"));
		labelNode.setWidth("60px");
		//getNodemainCont().add(labelNode);
		lookIn=new ListBox();
		lookIn.setWidth("130px");
		lookIn.addItem(BabelFish.print().scopeNode());
		lookIn.addItem(BabelFish.print().scopeNetwork());
		lookIn.setEnabled(true);
		
		lookIn.addChangeListener(CommunicationController.scopeListener(resourceExplorer));		
		//getNodemainCont().add(lookIn);
	}
	
	public String getLookIn() {
		return lookIn.getItemText(lookIn.getSelectedIndex());
	}

}
