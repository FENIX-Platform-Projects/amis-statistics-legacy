package org.fao.fenix.web.modules.udtable.client.view;

import org.fao.fenix.web.modules.re.client.view.util.Pager.FenixPager;
import org.fao.fenix.web.modules.table.client.view.TablePager;
import org.fao.fenix.web.modules.udtable.client.control.UDTablePagerController;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class UDTablePager extends TablePager {

private FenixPager fenixPager;

public FenixPager getFenixPager() {
	return fenixPager;
}

public void setFenixPager(FenixPager fenixPager) {
	this.fenixPager = fenixPager;
}

	public HorizontalPanel build(UDTableGrid tableGrid, UDTableFilter tableFilter) {
		this.setPanel(new HorizontalPanel());
		buildNewInterface();
		addListeners(tableGrid, tableFilter);
		newInterfaceFormat();
		return this.getPanel();
	}
	
	public void buildInterface() {
		super.buildInterface();
	}
	
	/*public static void updatePagerInfo(final UDTablePager tablePager, final Long resourceId,  final int page) {
		UDTableServiceEntry.getInstance().getRecordSize(resourceId, new AsyncCallback<Integer>() {
				public void onSuccess(Integer result) {
					HTML info = tablePager.getPageInfo();
					int pages = result / TableConstants.ITEMS_PER_PAGE;
					if ((result % TableConstants.ITEMS_PER_PAGE) != 0)
						pages++;
					info.setHTML(I18N.print().page() + " " + page + " / " + pages);
					tablePager.setActualPage(page);
					tablePager.setTotalRows(result);
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "");
				}
			});
	
   }*/
	
	public void addListeners(UDTableGrid tableGrid, UDTableFilter tableFilter) {
		this.getStart().addSelectionListener(UDTablePagerController.start(tableGrid, tableFilter));
		this.getForward().addSelectionListener(UDTablePagerController.next(tableGrid, tableFilter));
		this.getBack().addSelectionListener(UDTablePagerController.previous(tableGrid, tableFilter));
		this.getEnd().addSelectionListener(UDTablePagerController.end(tableGrid, tableFilter));
		this.getGoToPage().addSelectionListener(UDTablePagerController.goToPage(tableGrid, tableFilter));
		this.getRefresh().addSelectionListener(UDTablePagerController.goToPage(tableGrid, tableFilter));
	}
	
	

      public void validateButtons(){
		
		if (fenixPager.calculateIndexFrom() == 0){
			back.disable();
			start.disable();
		} else {
			back.enable();
			start.enable();
		}
		
		if (fenixPager.calculateIndexTo() == fenixPager.getListSize()){
			forward.disable();
			end.disable();
		} else {
			forward.enable();
			end.enable();
		}
	
	}
	
      public void writeInfo(){
  		String s = "of" + " ";
  		s += fenixPager.calculateNumberOfPages() + " ";
  		
  		totalPagesInfo.setHTML(s);
  		
  		String s2 = " Displaying" + " ";
    		if (fenixPager.calculateNumberOfPages()==0){
    			s2 +=  "No results";
    		} else{
    			s2 += (fenixPager.calculateIndexFrom() +1 )+ " ";
    			s2 += "- ";
        		s2 += "<b>" + fenixPager.calculateIndexTo() + "</b>" ;
        		s2 += " of ";
        		s2 += "<b>" +fenixPager.getListSize() + "</b>";
    		}		
    	 		
  		pageInfo.setHTML(s2);
  	}
      

}
