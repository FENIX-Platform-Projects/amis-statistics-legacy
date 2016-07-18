package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.LocalController;
import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.TablePager;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class LocalPager extends TablePager {

	private Button refresh;
	
	public HorizontalPanel build(LocalTablePanel communicationTablePanel) {
		this.setPanel(new HorizontalPanel());
		buildInterface();
		addListeners(communicationTablePanel);
		format();
		return this.getPanel();
	}
	
	public void buildInterface() {
		super.buildInterface();
		refresh = new Button("Refresh");
		getPanel().add(refresh);
	}
	
	public static void updatePagerInfo(final LocalPager tablePager, final int page) {
		try {
			CommunicationServiceEntry.getInstance().getLocalSize(new AsyncCallback<Integer>() {
					public void onSuccess(Integer result) {
						HTML info = tablePager.getPageInfo();
						int pages = result / TableConstants.ITEMS_PER_PAGE;
						if ((result % TableConstants.ITEMS_PER_PAGE) != 0)
							pages++;
						info.setHTML(BabelFish.print().page() + " " + page + " / " + pages);
						tablePager.setActualPage(page);
						tablePager.setTotalRows(result);
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "");
					}
				});
		} catch (FenixGWTException e) {
			FenixAlert.alert(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public void addListeners(LocalTablePanel communicationTablePanel) {
		this.getStart().addSelectionListener(LocalController.start(communicationTablePanel));
		this.getForward().addSelectionListener(LocalController.next(communicationTablePanel));
		this.getBack().addSelectionListener(LocalController.previous(communicationTablePanel));
		this.getEnd().addSelectionListener(LocalController.end(communicationTablePanel));
		this.getGoToPage().addSelectionListener(LocalController.goToPage(communicationTablePanel));
		this.getRefresh().addSelectionListener(LocalController.goToPage(communicationTablePanel));
	}

	public Button getRefresh() {
		return refresh;
	}
	
}
