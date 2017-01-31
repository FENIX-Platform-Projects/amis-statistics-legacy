package org.fao.fenix.web.modules.coding.client.control.search;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.ui.HTML;

public class CodingPagerController {
	
	static CodingFindConvertController codingFindConvertController;
	
	public static void updatePagerInfo(final CodingSearchResults window, final int page) {
				HTML info = window.getCodingPager().getPageInfo();
				int pages = window.getCodingPager().getTotalPages();
				info.setHTML(BabelFish.print().page() + " " + page + " / " + pages);
				window.getCodingPager().setActualPage(page);
				window.getCodingPager().getPageBox().setText(window.getCodingPager().getActualPage().toString());
	}

	public static SelectionListener<ButtonEvent> next(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				System.out.println("PAGES " + window.getCodingPager().getActualPage() + " " + window.getCodingPager().getTotalPages() );
				if ( window.getCodingPager().getActualPage() < window.getCodingPager().getTotalPages()) {
					Integer fromIndex = TableConstants.ITEMS_PER_PAGE * window.getCodingPager().getActualPage(); // + 1;
					Integer toIndex = TableConstants.ITEMS_PER_PAGE;
					if ((fromIndex + toIndex) > window.getCodingPager().getTotalRows()) {
						toIndex = window.getCodingPager().getTotalRows() - 1;
						
					}
					else
						toIndex = fromIndex + toIndex;
					
					codingFindConvertController.buildTableCodes(window, fromIndex, toIndex);	
					int newPage = 1 + window.getCodingPager().getActualPage();
					updatePagerInfo(window, newPage);
				} 
				else {
					System.out.println("LAST PAGE");
				}
			};
		};
	}

	public static SelectionListener<ButtonEvent> previous(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if ( window.getCodingPager().getActualPage() != 1  ) {
					Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (window.getCodingPager().getActualPage() -2 );
					Integer toIndex = TableConstants.ITEMS_PER_PAGE;
					if (fromIndex <= 0)
						fromIndex = 0;
					toIndex = fromIndex + toIndex;
				
					codingFindConvertController.buildTableCodes(window, fromIndex, toIndex);	
					int newPage = window.getCodingPager().getActualPage() -1 ;
					updatePagerInfo(window, newPage);
				} 
				else {
					System.out.println("FIRST PAGE");
				}
			};
		};
	}

	public static SelectionListener<ButtonEvent> start(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = 0;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				codingFindConvertController.buildTableCodes(window, fromIndex, toIndex);	
			 	updatePagerInfo(window, 1);
			};
		};
	}

	public static SelectionListener<ButtonEvent> end(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (window.getCodingPager().getTotalPages() - 1);
				Integer toIndex = window.getCodingPager().getTotalRows();
				codingFindConvertController.buildTableCodes(window, fromIndex, toIndex);	
				int newPage = window.getCodingPager().getTotalPages();
				updatePagerInfo(window, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> goToPage(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer page = Integer.parseInt(window.getCodingPager().getPageBox().getText());
				if (page <= 0) 
					page = 1;			
				if (page > window.getCodingPager().getTotalPages())
					page = window.getCodingPager().getTotalPages();
				
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (page -1);
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > window.getCodingPager().getTotalRows())
					toIndex = window.getCodingPager().getTotalRows();
				else
					toIndex = fromIndex + toIndex; 
	
				codingFindConvertController.buildTableCodes(window, fromIndex, toIndex);	
				int newPage = page;
				updatePagerInfo(window, newPage);
			};
		};
	}

	
}
