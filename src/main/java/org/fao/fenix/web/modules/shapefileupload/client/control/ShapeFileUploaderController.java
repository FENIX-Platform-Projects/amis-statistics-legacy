package org.fao.fenix.web.modules.shapefileupload.client.control;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;

public class ShapeFileUploaderController {
	
	public static SelectionListener<ComponentEvent> open(final FileUpload shapeFile, final FormPanel formPanel){
		return new SelectionListener<ComponentEvent>(){
			public void componentSelected(ComponentEvent ce) {
				if (shapeFile.getFilename().equals("")) {
					FenixAlert.error(BabelFish.print().noDataset(), BabelFish.print().errorSelectDataset());
				} else {
					//FenixAlert.error(formPanel.getAction(), formPanel.getAction());
					formPanel.submit();
				}
			}
		};
	}
}
