package org.fao.fenix.web.modules.aquastat.client.control;

import org.fao.fenix.web.modules.aquastat.common.services.AquastatServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AquastatController {

	@SuppressWarnings("unchecked")
	public static void createCharts() {
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
		AquastatServiceEntry.getInstance().createCharts(new AsyncCallback() {
			public void onSuccess(Object result) {
				loading.destroyLoadingBox();
				FenixAlert.info(BabelFish.print().info(), "Charts successfully created.");
				loading.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
				loading.destroyLoadingBox();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public static void createCharts(final String areaCode) {
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
		AquastatServiceEntry.getInstance().createCharts(areaCode, new AsyncCallback() {
			public void onSuccess(Object result) {
				loading.destroyLoadingBox();
				FenixAlert.info(BabelFish.print().info(), "Charts successfully created for country " + areaCode + ".");
				loading.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
				loading.destroyLoadingBox();
			}
		});
	}
	
}