package org.fao.fenix.web.modules.communication.client.control;

import org.fao.fenix.web.modules.communication.client.view.LocalTablePanel;
import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class LocalController {

	public static void updatePagerInfo(final LocalTablePanel localTablePanel, final int page) {
		try {
			CommunicationServiceEntry.getInstance().getRecordSize(new AsyncCallback<Integer>() {
					public void onSuccess(Integer result) {
						HTML info = localTablePanel.getLocalPager().getPageInfo();
						int pages = result / TableConstants.ITEMS_PER_PAGE;
						if ((result % TableConstants.ITEMS_PER_PAGE) != 0)
							pages++;
						info.setHTML(BabelFish.print().page() + " " + page + " / " + pages);
						localTablePanel.getLocalPager().setActualPage(page);
						localTablePanel.getLocalPager().setTotalRows(result);
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "");
					}
				});
		} catch (FenixGWTException e) {
			FenixAlert.alert(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static SelectionListener<ButtonEvent> next(final LocalTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * tablePanel.getLocalPager().getActualPage(); // + 1;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > tablePanel.getLocalPager().getTotalRows())
					fromIndex = tablePanel.getLocalPager().getTotalRows() - 1;
				tablePanel.getLocalTable().getTable().removeAll();
				tablePanel.getLocalTable().build(tablePanel.getLocalTable().getTable(), fromIndex, toIndex);
				int newPage = 1 + tablePanel.getLocalPager().getActualPage();
				if ((fromIndex + toIndex) > tablePanel.getLocalPager().getTotalRows())
					newPage = 1 + tablePanel.getLocalPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> previous(final LocalTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (tablePanel.getLocalPager().getActualPage() - 2);
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if (fromIndex <= 0)
					fromIndex = 0;
				tablePanel.getLocalTable().getTable().removeAll();
				tablePanel.getLocalTable().build(tablePanel.getLocalTable().getTable(), fromIndex, toIndex);
				int newPage = tablePanel.getLocalPager().getActualPage() - 1;
				if ((TableConstants.ITEMS_PER_PAGE * (tablePanel.getLocalPager().getActualPage() - 1)) <= 0)
					newPage = 1;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> start(final LocalTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = 0;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				tablePanel.getLocalTable().getTable().removeAll();
				tablePanel.getLocalTable().build(tablePanel.getLocalTable().getTable(), fromIndex, toIndex);
				updatePagerInfo(tablePanel, 1);
			};
		};
	}

	public static SelectionListener<ButtonEvent> end(final LocalTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = tablePanel.getLocalPager().getTotalRows() - 1;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE - 1;
				tablePanel.getLocalTable().getTable().removeAll();
				tablePanel.getLocalTable().build(tablePanel.getLocalTable().getTable(), fromIndex, toIndex);
				int newPage = 1 + tablePanel.getLocalPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> goToPage(final LocalTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer page = Integer.parseInt(tablePanel.getLocalPager().getPageBox().getText());
				if (page <= 0)
					page = 1;
				Integer fromIndex = (page * TableConstants.ITEMS_PER_PAGE) - 1;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > tablePanel.getLocalPager().getTotalRows())
					fromIndex = tablePanel.getLocalPager().getTotalRows() - 1;
				if (fromIndex < 0)
					fromIndex = 0;
				tablePanel.getLocalTable().getTable().removeAll();
				tablePanel.getLocalTable().build(tablePanel.getLocalTable().getTable(), fromIndex, toIndex);
				int newPage = page;
				if ((fromIndex + toIndex) > tablePanel.getLocalPager().getTotalRows())
					newPage = 1 + tablePanel.getLocalPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(tablePanel, newPage);
			};
		};
	}
	
}
