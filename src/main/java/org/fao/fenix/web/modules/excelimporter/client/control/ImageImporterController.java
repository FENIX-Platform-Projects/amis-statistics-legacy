package org.fao.fenix.web.modules.excelimporter.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.excelimporter.client.view.ConfirmAppendWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterStepB;
import org.fao.fenix.web.modules.excelimporter.client.view.StepM;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.ImageWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ImageImporterController {

	public static ClickHandler openImages(final Long imageID) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				List<Long> imageIDs = new ArrayList<Long>();
				imageIDs.add(imageID);
				final LoadingWindow lw = new LoadingWindow("Open Image", "FENIX is retrieving your image from the DB.", "Please Wait...");
				try {
					REServiceEntry.getInstance().getImages(imageIDs, new AsyncCallback<List<String>>() {
						public void onSuccess(List<String> paths) {
							lw.destroyLoadingBox();
							for (String p : paths) {
								ImageWindow w = new ImageWindow(imageID, p);
								w.build();
							}
							lw.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							lw.destroyLoadingBox();
							FenixAlert.error("Error", e.getMessage());
							lw.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					lw.destroyLoadingBox();
					FenixAlert.error("Error", e.getMessage());
					lw.destroyLoadingBox();
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> next(final ImageImporterPanel p) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (p.getLayout().getActiveItem().equals(p.getStep_a().getLayoutContainer())) {
					if (p.getStep_a().getDataFile().isValid()) {
						p.getLayout().setActiveItem(p.getStep_b().getLayoutContainer());
						p.getPrevButton().setEnabled(true);
					} else {
						FenixAlert.info("INFO", "Please select an image to import.");
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_b().getLayoutContainer())) { 
					if (isValid(p.getStep_b())) {
						p.getLayout().setActiveItem(p.getStep_m().getLayoutContainer());
					} else {
						FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_m().getLayoutContainer())) {
					if (isValid(p.getStep_m())) {
						new ConfirmAppendWindow(p).buildForImage("You are going to upload " + p.getStep_a().getDataFile().getValue() + ". Are you sure?");
						p.getNextButton().setEnabled(false);
					} else {
						FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
					}
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> prev(final ImageImporterPanel p) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (p.getLayout().getActiveItem().equals(p.getStep_a().getLayoutContainer())) {
//					p.getLayout().setActiveItem(p.getStep_m().getLayoutContainer());
//					p.getPrevButton().setEnabled(true);
					p.getNextButton().setEnabled(true);
				}	else if (p.getLayout().getActiveItem().equals(p.getStep_m().getLayoutContainer())) {
					p.getLayout().setActiveItem(p.getStep_a().getLayoutContainer());
					p.getPrevButton().setEnabled(false);
					p.getNextButton().setEnabled(true);
				}
			}
		};
	}
	
	public static boolean isValid(ImageImporterStepB sb) {
		if (!sb.getSourceField().isValid())
			return false;
		if (!sb.getGaulList().isValid())
			return false;
		return true;
	}
	
	public static boolean isValid(StepM sm) {
		if (!sm.getTitleField().isValid())
			return false;
		if (!sm.getCategoryList().isValid())
			return false;
		if (!sm.getKeywordsField().isValid())
			return false;
		if (!sm.getGaulList().isValid())
			return false;
		return true;
	}
	
}