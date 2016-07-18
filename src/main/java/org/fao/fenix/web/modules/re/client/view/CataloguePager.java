package org.fao.fenix.web.modules.re.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.view.util.Pager.FenixPager;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.ui.HTML;

public abstract class CataloguePager {
	
	private final int SPACING=10;
	protected Button backAll;
	protected Button back;
	protected Button nextAll;
	protected Button next;
	protected Button open;
	protected Button cancel;
	private VerticalPanel mainCont;
	private FenixPager fenixPager;
	private HTML infoPager;
	private ResourceExplorer resourceExplorer;
	
	
	public Button getOpen() {
		return open;
	}

	public VerticalPanel getMainCont() {
		return mainCont;
	}

	public FenixPager getFenixPager() {
		return fenixPager;
	}

	public void setFenixPager(FenixPager fenixPager) {
		this.fenixPager = fenixPager;
	}

	public CataloguePager(ResourceExplorer resourceExplorer){
		this.resourceExplorer=resourceExplorer;
		mainCont=new VerticalPanel();
		mainCont.setLayout(new CenterLayout());
		infoPager=new HTML();
		backAll=new Button("|<");
		back=new Button("<");
		nextAll=new Button(">|");
		next=new Button(">");
		open=new Button(BabelFish.print().open());
		cancel=new Button(BabelFish.print().cancel());
		
		buildInterface();
	}
	
	public void validateButtons(){
		
		if (fenixPager.calculateIndexFrom() == 0){
			back.disable();
			backAll.disable();
		} else {
			back.enable();
			backAll.enable();
		}
		
		if (fenixPager.calculateIndexTo() == fenixPager.getListSize()){
			next.disable();
			nextAll.disable();
		} else {
			next.enable();
			nextAll.enable();
		}
	
	}
	
	public void writeInfo(){
		String s = BabelFish.print().items() + " ";
		s += BabelFish.print().from() + " ";
		if (fenixPager.calculateNumberOfPages()==0){
			s += "<b>" + fenixPager.calculateIndexFrom() + "</b>" + " ";
		} else{
			s += "<b>" + (fenixPager.calculateIndexFrom() +1 )+ "</b>" + " ";
		}		
		s += BabelFish.print().to() + " ";
		s += "<b>" + fenixPager.calculateIndexTo() + "</b>" + "&nbsp;&nbsp;&nbsp;";
		s += "(" + fenixPager.getListSize() + " ";
		s += BabelFish.print().items() + ", ";
		s += fenixPager.calculateNumberOfPages() + " ";
		s += BabelFish.print().pages() + ")";
 		
		infoPager.setHTML(s);
	}
	
	private void buildInterface() {  
				
		HorizontalPanel buttons=new HorizontalPanel();
		buttons.setSpacing(SPACING);
		buttons.add(backAll);
		buttons.add(back);
		buttons.add(infoPager);
		buttons.add(next);
		buttons.add(nextAll);
		mainCont.add(buttons);
		
		this.backAll.addSelectionListener(CataloguePagerController.goFirstPage(resourceExplorer));
		this.back.addSelectionListener(CataloguePagerController.goToPreviousPage(resourceExplorer));
		this.next.addSelectionListener(CataloguePagerController.goToNextPage(resourceExplorer));
		this.nextAll.addSelectionListener(CataloguePagerController.goToLastPage(resourceExplorer));
		
		HorizontalPanel action=new HorizontalPanel();
		action.setSpacing(SPACING);
		action.add(open);
		action.add(cancel);
		mainCont.add(action);
		
	}

}
