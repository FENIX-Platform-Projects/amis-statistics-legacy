package org.fao.fenix.web.modules.x.client.control;

import org.fao.fenix.web.modules.x.client.view.XExplorerPager;
import org.fao.fenix.web.modules.x.client.view.XExplorerResultPanel;
import org.fao.fenix.web.modules.x.client.view.XExplorerSearchPanel;
import org.fao.fenix.web.modules.x.common.vo.XExplorerSearchParametersVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class XExplorerPagerController extends XExplorerController {

	public static SelectionListener<IconButtonEvent> next(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel, final XExplorerPager pager) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				int actualPage = Integer.parseInt(pager.getPageNumber().getValue());
				int firstResultIndex = actualPage * pager.getMaxResults();
				int totalPages = resultPanel.getPager().getTotalResults() / resultPanel.getPager().getMaxResults();
				if (resultPanel.getPager().getTotalResults() % resultPanel.getPager().getMaxResults() != 0)
					totalPages++;
				if (firstResultIndex <= ((totalPages - 1) * resultPanel.getPager().getMaxResults())) {
					pager.setFirstResultIndex(firstResultIndex);
					XExplorerSearchParametersVO p = collectParameters(searchPanel, resultPanel);
					updateTable(resultPanel, p);
					pager.getPageNumber().setValue(String.valueOf(1 + actualPage));
				}
			};
		};
	}
	
	public static SelectionListener<IconButtonEvent> prev(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel, final XExplorerPager pager) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				int actualPage = Integer.parseInt(pager.getPageNumber().getValue());
				int firstResultIndex = (actualPage - 2) * pager.getMaxResults();
				if (firstResultIndex >= 0) {
					pager.setFirstResultIndex(firstResultIndex);
					XExplorerSearchParametersVO p = collectParameters(searchPanel, resultPanel);
					updateTable(resultPanel, p);
					pager.getPageNumber().setValue(String.valueOf(actualPage - 1));
				}
			};
		};
	}
	
	public static SelectionListener<IconButtonEvent> first(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel, final XExplorerPager pager) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				int firstResultIndex = 0;
				pager.setFirstResultIndex(firstResultIndex);
				XExplorerSearchParametersVO p = collectParameters(searchPanel, resultPanel);
				updateTable(resultPanel, p);
				pager.getPageNumber().setValue(String.valueOf(1));
			};
		};
	}
	
	public static SelectionListener<IconButtonEvent> last(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel, final XExplorerPager pager) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				int totalPages = resultPanel.getPager().getTotalResults() / resultPanel.getPager().getMaxResults();
				if (resultPanel.getPager().getTotalResults() % resultPanel.getPager().getMaxResults() != 0)
					totalPages++;
				int firstResultIndex = (totalPages - 1) * resultPanel.getPager().getMaxResults();
				pager.setFirstResultIndex(firstResultIndex);
				XExplorerSearchParametersVO p = collectParameters(searchPanel, resultPanel);
				updateTable(resultPanel, p);
				pager.getPageNumber().setValue(String.valueOf(totalPages));
			};
		};
	}
	
	public static SelectionListener<ButtonEvent> goToPage(final XExplorerSearchPanel searchPanel, final XExplorerResultPanel resultPanel, final XExplorerPager pager) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				int totalPages = resultPanel.getPager().getTotalResults() / resultPanel.getPager().getMaxResults();
				if (resultPanel.getPager().getTotalResults() % resultPanel.getPager().getMaxResults() != 0)
					totalPages++;
				int actualPage = Integer.parseInt(pager.getPageNumber().getValue());
				if (actualPage <= totalPages) {
					int firstResultIndex = (actualPage - 1) * pager.getMaxResults();
					System.out.println("Go To Page: " + firstResultIndex);
					pager.setFirstResultIndex(firstResultIndex);
					XExplorerSearchParametersVO p = collectParameters(searchPanel, resultPanel);
					updateTable(resultPanel, p);
//					pager.getPageNumber().setValue(String.valueOf(totalPages));
				}
			};
		};
	}
	
}
