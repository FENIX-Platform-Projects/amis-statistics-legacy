package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
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

public class CommunicationModulePager extends TablePager {

	private Button refresh;
	
	public HorizontalPanel build(CommunicationTablePanel communicationTablePanel) {
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
	
	public static void updatePagerInfo(final CommunicationModulePager tablePager, final int page) {
		try {
			CommunicationServiceEntry.getInstance().getRecordSize(new AsyncCallback<Integer>() {
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
	
	public void addListeners(CommunicationTablePanel communicationTablePanel) {
		this.getStart().addSelectionListener(CommunicationController.start(communicationTablePanel));
		this.getForward().addSelectionListener(CommunicationController.next(communicationTablePanel));
		this.getBack().addSelectionListener(CommunicationController.previous(communicationTablePanel));
		this.getEnd().addSelectionListener(CommunicationController.end(communicationTablePanel));
		this.getGoToPage().addSelectionListener(CommunicationController.goToPage(communicationTablePanel));
		this.getRefresh().addSelectionListener(CommunicationController.goToPage(communicationTablePanel));
	}
	
	public Button getRefresh() {
		return refresh;
	}
	
}
