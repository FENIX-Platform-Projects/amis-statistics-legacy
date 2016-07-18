package org.fao.fenix.web.modules.fieldclimate.client.control;

import java.util.Date;

import org.fao.fenix.web.modules.fieldclimate.client.view.FieldClimatePanel;
import org.fao.fenix.web.modules.fieldclimate.client.view.FieldClimateWindow;
import org.fao.fenix.web.modules.fieldclimate.common.services.FCServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Slider;
import com.google.gwt.user.client.rpc.AsyncCallback;

@SuppressWarnings("deprecation")
public class FieldClimateController {
	
	public static SelectionListener<ButtonEvent> synchronize(final FieldClimateWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (!w.getFieldClimatePanel().getGaulList().getSelection().isEmpty()) {
					String station = w.getFieldClimatePanel().getGaulList().getSelection().get(0).getGaulCode();
					int entries = w.getFieldClimatePanel().getSlider().getValue();
					final LoadingWindow l = new LoadingWindow("FieldClimate", "System is contacting FieldClimate to synchronize data.", "Please wait...");
					FCServiceEntry.getInstance().synchronize(station, entries, new AsyncCallback<Object>() {
						public void onSuccess(Object o) {
							l.destroyLoadingBox();
							FenixAlert.info("INFO", "Synchronization complete!");
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							l.destroyLoadingBox();
							FenixAlert.error("ERROR", e.getMessage());
							l.destroyLoadingBox();
						}
					});
				} else {
					FenixAlert.info("INFO", "Please select a station from the list.");
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> close(final FieldClimateWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().close();
			}
		};
	}
	
	public static Listener<BaseEvent> timeSliderListener(final Html label, final Slider slider, final FieldClimatePanel panel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				Date today = new Date();
				int items = slider.getValue();
				Date start = new Date(today.getYear(), today.getMonth(), today.getDate() - items);
				label.setHtml("<b>Import data from </b>" + FieldClimateController.date2string(start));
			};
		};
	}

	public static void fillFieldClimateStationList(ListStore<GaulModelData> gaulStore) {
		GaulModelData m = new GaulModelData("All Stations", "all");
		gaulStore.add(m);
		m = new GaulModelData("Aweil", "00000B92");
		gaulStore.add(m);
		m = new GaulModelData("Bentu", "00000B8D");
		gaulStore.add(m);
		m = new GaulModelData("Bor", "00000B8F");
		gaulStore.add(m);
		m = new GaulModelData("Juba", "00000968");
		gaulStore.add(m);
		m = new GaulModelData("Kwajok", "00000B90");
		gaulStore.add(m);
		m = new GaulModelData("Rumbek", "00000966");
		gaulStore.add(m);
		m = new GaulModelData("Torit", "00000969");
		gaulStore.add(m);
	}
	
	public static String date2string(Date d) {
		String s = "";
		s += String.valueOf(1900 + d.getYear());
		s += "-";
		if (d.getMonth() < 9)
			s += "0";
		s += 1 + d.getMonth();
		s += "-";
		if (d.getDate() < 10)
			s += "0";
		s += d.getDate();
		return s;
	}
	
}