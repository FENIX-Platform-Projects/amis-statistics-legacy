package org.fao.fenix.web.modules.giews.client.control;

import org.fao.fenix.web.modules.giews.common.services.GIEWSServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GIEWSController {

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> createSingleProject(final ComboBox<GaulModelData> gaulList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow("Loading", "FENIX engine is creating GIEWS projects. Please wait.", "Creating projects...");
				for (int i = 0; i < gaulList.getSelection().size(); i++) {
					String code = gaulList.getSelection().get(i).getGaulCode();
					String label = gaulList.getSelection().get(i).getGaulLabel();
					GIEWSServiceEntry.getInstance().createSingleProject(code, label, new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
						}
						public void onFailure(Throwable caught) {
							loading.destroyLoadingBox();
						}
					});
				}
			}
		};
	};
	
	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> createProjects() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow("Loading", "FENIX engine is creating GIEWS projects. Please wait.", "Creating projects...");
				GIEWSServiceEntry.getInstance().createProjects(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroyLoadingBox();
					}
					public void onFailure(Throwable caught) {
						loading.destroyLoadingBox();
					}
				});
			};
		};
	}
	
	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> updateProjects() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow("Loading", "FENIX engine is creating GIEWS projects. Please wait.", "Creating projects...");
				GIEWSServiceEntry.getInstance().updateProjects(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroyLoadingBox();
					}
					public void onFailure(Throwable caught) {
						loading.destroyLoadingBox();
					}
				});
			};
		};
	}
	
	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> deleteProjects() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow("Loading", "FENIX engine is creating GIEWS projects. Please wait.", "Creating projects...");
				GIEWSServiceEntry.getInstance().deleteProjects(new AsyncCallback() {
					public void onSuccess(Object result) {
						loading.destroyLoadingBox();
					}
					public void onFailure(Throwable caught) {
						loading.destroyLoadingBox();
					}
				});
			};
		};
	}
	
}