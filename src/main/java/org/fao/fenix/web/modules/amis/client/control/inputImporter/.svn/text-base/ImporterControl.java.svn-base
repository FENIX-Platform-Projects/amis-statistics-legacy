package org.fao.fenix.web.modules.amis.client.control.inputImporter;

import org.fao.fenix.web.modules.amis.client.view.inputImporter.ImportFormPanel;
import org.fao.fenix.web.modules.amis.client.view.inputImporter.ImporterPanel;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
//import org.fao.fenix.web.modules.core.common.exception.DirectUploadException;
//import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
//import org.fao.fenix.web.modules.core.common.exception.InvalidFormException;
//import org.fao.fenix.web.modules.core.common.exception.ParseDatasetHeadersException;
//import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;
//import org.fao.fenix.web.modules.excelimporter.client.view.CodeListImporterWindow;
//import org.fao.fenix.web.modules.excelimporter.client.view.CodeListPanel;
//import org.fao.fenix.web.modules.excelimporter.client.view.ConfirmAppendWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class ImporterControl {
	
	public static SelectionListener<ButtonEvent> uploadListener(final ImporterPanel contentPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ImportFormPanel formPanel = contentPanel.getImportFormPanel();
				try{
				if(formPanel.getDataFile().isValid())
				{
					ImporterControl.upload(formPanel);
				
//				CodeListPanel p = w.getCodeListPanel();
//				if (p.getLayout().getActiveItem().equals(p.getStep_a().getLayoutContainer())) {
//					try {
//						if (p.getStep_a().getDataFile().isValid()) {
//							ExcelImporterController.directUpload(w);
//							p.getLayout().setActiveItem(p.getStep_b().getLayoutContainer());
//							p.getPrevButton().setEnabled(true);
//						} else {
//							FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
//						}
//					} catch (FenixGWTException e) {
//						FenixAlert.alert("Warning", e.getMessage());
//					} catch (InvalidFormException e) {
//						FenixAlert.alert("Warning", e.getMessage());
//					} catch (DirectUploadException e) {
//						
//					} catch (ParseDatasetHeadersException e) {
//						
//					}
//				} else if (p.getLayout().getActiveItem().equals(p.getStep_b().getLayoutContainer())) {
//					if (isValid(p)) {
//						new ConfirmAppendWindow(p).buildForCodeList("You are going to upload " + w.getCodeListPanel().getStep_a().getDataFile().getValue() + ". Are you sure?");
//						p.getNextButton().setEnabled(false);
//					} else {
//						FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
//					}
//				}
			}
			else {
					FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
				}
			} catch (FenixGWTException e) {
				FenixAlert.alert("Warning", e.getMessage());
			}
		}
		};
	}
	

	public static void upload(ImportFormPanel formPanel)throws FenixGWTException
	{
		String data_extension = formPanel.getDataFile().getFileInput().getValue().substring(formPanel.getDataFile().getFileInput().getValue().length() - 3);
		if (!data_extension.equalsIgnoreCase("CSV")) {
			
			throw new FenixGWTException("Data is not in CSV format. Please provide a valid file.");
		
		} else if (data_extension.equalsIgnoreCase("CSV")) { // logic for CSV file
			
		}
	}
	
}
