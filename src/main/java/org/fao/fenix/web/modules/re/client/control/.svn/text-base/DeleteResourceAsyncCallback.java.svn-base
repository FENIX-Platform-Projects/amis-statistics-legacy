package org.fao.fenix.web.modules.re.client.control;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DeleteResourceAsyncCallback implements AsyncCallback<Object> {

	public void onSuccess(Object result) {
		// refresh the catalogue
		if (REModel.isReIsopen()) {
			ResourceExplorer explorer = REModel.getResourceExplorer();
			CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons()
					.getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
			
			explorer.getWindow().getLayout().layout();
		}
		Info.display(BabelFish.print().deleteCompleted(), BabelFish.print().deleteSuccessful());
	}

	public void onFailure(Throwable caught) {
		FenixAlert.error(BabelFish.print().error(),
				"The delete failed because there is a technical problem.");
	}

}
