package org.fao.fenix.web.modules.core.client.image;

import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ImageFenixController {

	public static SelectionListener<ButtonEvent> getImage(final ImageFenixView imageFenixView) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				DataListItem dataListItem = imageFenixView.sideListImage.list.getSelectedItem();
				final String fileName = dataListItem.getId();
				
				if (imageFenixView.caller.equals("Report")){
					
					BirtServiceEntry.getInstance().addImageToReport(imageFenixView.reportViewer.getRptDesign(),
							fileName, new AsyncCallback<List<String>>() {

								public void onSuccess(List<String> result) {
									DataListItem item = new DataListItem();
									item.setIconStyle("");
									item.setItemId(result.get(1));
									item.setText("Image (" + fileName + ")");
									imageFenixView.reportViewer.getSideBar().getListResource().add(item);
									imageFenixView.reportViewer.getReport().setHTML(result.get(2));
									
									imageFenixView.getWindow().close();
									
								}

								public void onFailure(Throwable caught) {
									Info.display("addImageToReport", caught.getLocalizedMessage());
									imageFenixView.getWindow().close();
								}

							});
					
				} else if (imageFenixView.caller.equals("Text")){
					
					imageFenixView.getWindow().close();
					imageFenixView.textEditorToolbar.extended.insertImage("../imageRepository/" + fileName);
					
				}
				
			}
		};
	}
}
