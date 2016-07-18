package org.fao.fenix.web.modules.re.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.SearchButtonsController;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;


public class SearchButtons {
	
	private HorizontalPanel cont;
	protected Button reset;
	protected Button search;
	protected SearchParameters searchParameters; 
	protected Catalogue catalogue;
	private ResourceExplorer resourceExplorer;
	private FenixSearchParameters fenixSearchParameters;
	
	public FenixSearchParameters getFenixSearchParameters() {
		return fenixSearchParameters;
	}

	public void setFenixSearchParameters(FenixSearchParameters fenixSearchParameters) {
		this.fenixSearchParameters = fenixSearchParameters;
	}
	
	

	public HorizontalPanel getCont() {
		return cont;
	}

	public SearchButtons(ResourceExplorer resourceExplorer){
		this.resourceExplorer=resourceExplorer;
		
		reset=new Button(BabelFish.print().reset());
		search=new Button(BabelFish.print().search());
		
		cont = new HorizontalPanel();  
		cont.setHorizontalAlign(HorizontalAlignment.CENTER);
		cont.setSpacing(10);
		cont.add(search);
		cont.add(reset);
		
		this.search.addSelectionListener(SearchButtonsController.getResources(resourceExplorer));
		this.reset.addSelectionListener(SearchButtonsController.cleanSearchParameters(resourceExplorer.getSearchParameters()));
		
		
	}
	

	
}
