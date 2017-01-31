package org.fao.fenix.web.modules.udtable.client.control;


import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.udtable.client.view.UDTableFilter;
import org.fao.fenix.web.modules.udtable.client.view.UDTableGrid;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;



public class UDTablePagerController {


 	public static SelectionListener<ButtonEvent> next(final UDTableGrid tableGrid, final UDTableFilter tableFilter) {
 		return new SelectionListener<ButtonEvent>() {
 			public void componentSelected(ButtonEvent event) {
 			    final UDTableVO tableVo = tableFilter.getUDTableController().getTableVO();
 				final List<UDTableFilterVO> tableVOFilterList = tableFilter.getUDTableController().getUDTableFilterVOList();
 	 	 	  	
 				
 				Integer fromIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE * tableGrid.getTablePager().getActualPage(); // +
 				// 1
 				// ;
 				Integer toIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				if ((fromIndex + toIndex) > tableGrid.getTablePager().getTotalRows())
 					fromIndex = tableGrid.getTablePager().getTotalRows() - 1;
 				tableGrid.getEditorGrid().getStore().removeAll();
 				tableGrid.build(tableFilter.getUDTableController().getUDTableFilterVOList(), tableVo.getResourceId(), tableGrid.caller, fromIndex, toIndex);
 				int newPage = 1 + tableGrid.getTablePager().getActualPage();
 				if ((fromIndex + toIndex) > tableGrid.getTablePager().getTotalRows())
 					newPage = 1 + tableGrid.getTablePager().getTotalRows() / TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				 updatePagerInfo(tableGrid, tableVOFilterList, newPage);
 			};
 		};
 	}

 	public static SelectionListener<ButtonEvent> previous(final UDTableGrid tableGrid, final UDTableFilter tableFilter) {
 		return new SelectionListener<ButtonEvent>() {
 			public void componentSelected(ButtonEvent event) {
 				 final UDTableVO tableVo = tableFilter.getUDTableController().getTableVO();
 				final List<UDTableFilterVO> tableVOFilterList = tableFilter.getUDTableController().getUDTableFilterVOList();
 	 	  		
  				
 				Integer fromIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE * (tableGrid.getTablePager().getActualPage() - 2);
 				Integer toIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				if (fromIndex <= 0)
 					fromIndex = 0;
 				tableGrid.getEditorGrid().getStore().removeAll();
 				tableGrid.build(tableFilter.getUDTableController().getUDTableFilterVOList(), tableVo.getResourceId(), tableGrid.caller, fromIndex, toIndex);
 				
 				int newPage = tableGrid.getTablePager().getActualPage() - 1;
 				if ((TableConstants.UDTABLE_ITEMS_PER_PAGE * (tableGrid.getTablePager().getActualPage() - 1)) <= 0)
 					newPage = 1;
 				updatePagerInfo(tableGrid, tableVOFilterList, newPage);
 			};
 		};
 	}

 	public static SelectionListener<ButtonEvent> start(final UDTableGrid tableGrid, final UDTableFilter tableFilter) {
 		return new SelectionListener<ButtonEvent>() {
 			public void componentSelected(ButtonEvent event) {
 				 final UDTableVO tableVo = tableFilter.getUDTableController().getTableVO();
 				final List<UDTableFilterVO> tableVOFilterList = tableFilter.getUDTableController().getUDTableFilterVOList();
 	  			
 				Integer fromIndex = 0;
 				Integer toIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				tableGrid.getEditorGrid().getStore().removeAll();
 				tableGrid.build(tableFilter.getUDTableController().getUDTableFilterVOList(), tableVo.getResourceId(), tableGrid.caller, fromIndex, toIndex);
 				
 				updatePagerInfo(tableGrid, tableVOFilterList, 1);
 			};
 		};
 	}

 	public static SelectionListener<ButtonEvent> end(final UDTableGrid tableGrid, final UDTableFilter tableFilter) {
 		return new SelectionListener<ButtonEvent>() {
 			public void componentSelected(ButtonEvent event) {
 				 final UDTableVO tableVo = tableFilter.getUDTableController().getTableVO();
 				final List<UDTableFilterVO> tableVOFilterList = tableFilter.getUDTableController().getUDTableFilterVOList();
 	  			
  				
 				Integer fromIndex = tableGrid.getTablePager().getTotalRows() - 1;
 				Integer toIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE - 1;
 				tableGrid.getEditorGrid().getStore().removeAll();
 				tableGrid.build(tableFilter.getUDTableController().getUDTableFilterVOList(), tableVo.getResourceId(), tableGrid.caller, fromIndex, toIndex);
 				
 				int newPage = 1 + tableGrid.getTablePager().getTotalRows() / TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				updatePagerInfo(tableGrid, tableVOFilterList, newPage);
 			};
 		};
 	}

 	public static SelectionListener<ButtonEvent> goToPage(final UDTableGrid tableGrid, final UDTableFilter tableFilter) {
 		return new SelectionListener<ButtonEvent>() {
 			public void componentSelected(ButtonEvent event) {
 				 final UDTableVO tableVo = tableFilter.getUDTableController().getTableVO();
 				final List<UDTableFilterVO> tableVOFilterList = tableFilter.getUDTableController().getUDTableFilterVOList();
  				
 				Integer page = Integer.parseInt(tableGrid.getTablePager().getPageBox().getText());
 				if (page <= 0)
 					page = 1;
 				Integer fromIndex = (page * TableConstants.UDTABLE_ITEMS_PER_PAGE) - 1;
 				Integer toIndex = TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				if ((fromIndex + toIndex) > tableGrid.getTablePager().getTotalRows())
 					fromIndex = tableGrid.getTablePager().getTotalRows() - 1;
 				if (fromIndex < 0)
 					fromIndex = 0;
 				tableGrid.getEditorGrid().getStore().removeAll();
 				tableGrid.build(tableVOFilterList, tableVo.getResourceId(), tableGrid.caller, fromIndex, toIndex);
 				
 				int newPage = page;
 				if ((fromIndex + toIndex) > tableGrid.getTablePager().getTotalRows())
 					newPage = 1 + tableGrid.getTablePager().getTotalRows() / TableConstants.UDTABLE_ITEMS_PER_PAGE;
 				updatePagerInfo(tableGrid,tableVOFilterList, newPage);
 			};
 		};
 	}

 	
 	public static void updatePagerInfo(final UDTableGrid tableGrid, final List<UDTableFilterVO> tableVOFilterList, final int page) {
			UDTableServiceEntry.getInstance().getResultSetSizeForVOFilter(tableVOFilterList, UDTableController.getLanguage(), new AsyncCallback<Integer>() {
				public void onSuccess(Integer result) {
					System.out.println("---------- HERE ------------------");
					HTML info = tableGrid.getTablePager().getPageInfo();
					int pages = result / TableConstants.UDTABLE_ITEMS_PER_PAGE;
					System.out.println("pages = "+pages + " page = "+ page);
					
					if ((result % TableConstants.UDTABLE_ITEMS_PER_PAGE) != 0)
						pages++;
					info.setHTML(BabelFish.print().page() + " " + page + " / " + pages);
					tableGrid.getTablePager().setActualPage(page);
					tableGrid.getTablePager().setTotalRows(result);
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "");
				}
			});
		
	}

}