package org.fao.fenix.web.modules.esoko.client.control;

import java.util.Date;

import org.fao.fenix.web.modules.esoko.client.view.EsokoPanel;
import org.fao.fenix.web.modules.esoko.client.view.EsokoWindow;
import org.fao.fenix.web.modules.esoko.common.services.EsokoServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
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
public class EsokoController {

	public static Listener<BaseEvent> timeSliderListener(final Html label, final Slider slider, final EsokoPanel panel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				Date today = new Date();
				panel.setMinYear(slider.getValue());
				panel.setMaxYear(1900 + today.getYear());
				label.setHtml("<b>" + BabelFish.print().importDataFrom() + " " + panel.getMinYear() + " " + BabelFish.print().to() + " " + panel.getMaxYear() + "</b>");
			};
		};
	}
	
	public static SelectionListener<ButtonEvent> importEsokoPrices(final EsokoWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				EsokoPanel p = w.getEsokoPanel();
				if (!p.getGaulList().getSelection().isEmpty()) {
					String fromDate = parseDate(p.getFromDate().getValue());
					String toDate = parseDate(p.getToDate().getValue());
					String timePeriod = fromDate + "," + toDate;
					String locationID = p.getGaulList().getSelection().get(0).getGaulCode();
					final LoadingWindow l = new LoadingWindow(BabelFish.print().esokoPricesImporter(), BabelFish.print().systemIsQueryingEsoko(), BabelFish.print().pleaseWait());
					EsokoServiceEntry.getInstance().synchronize(locationID, timePeriod, new AsyncCallback<Long>() {
						public void onSuccess(Long arg0) {
							l.destroyLoadingBox();
							FenixAlert.info(BabelFish.print().info(), BabelFish.print().dataSuccessfullyImported());
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							l.destroyLoadingBox();
							FenixAlert.error(BabelFish.print().error(), e.getMessage());
							l.destroyLoadingBox();
						}
					});
				} else {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().pleaseSelectACountryFromTheList());
				}
			}
		};
	}
	
	public static String parseDate(Date d) {
		String s = "";
		s += String.valueOf(1900 + d.getYear());
		s += "-";
		if (d.getMonth() < 9)
			s += "0";
		s += String.valueOf(1 + d.getMonth());
		s += "-";
		if (d.getDate() < 10)
			s += "0";
		s += String.valueOf(d.getDate());
		return s;
	}
	
	public static SelectionListener<ButtonEvent> closeWindow(final FenixWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().close();
			}
		};
	}
	
	public static void fillEsokoCountryList(ListStore<GaulModelData> gaulStore) {
		GaulModelData m = new GaulModelData(BabelFish.print().afghanistan(), "2000000051");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().benin(), "2000023310");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().burkinaFaso(), "2000023314");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().cameroon(), "2001000038");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().elSalvador(), "2000233113");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().gambia(), "2000100003");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().ghana(), "2000023311");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().guinea(), "2000233110");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().guineaBissau(), "2000100004");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().honduras(), "2000023313");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().ivoryCoast(), "2000023315");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().kenya(), "2000233112");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().madagascar(), "2001000052");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().malawi(), "2001000053");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().mali(), "2000023318");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().mozambique(), "2000100008");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().niger(), "2000023319");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().rwanda(), "2001000058");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().senegal(), "2000023316");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().sierraLeone(), "2000100006");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().southAfrica(), "2001000073");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().sudan(), "2001000062");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().tanzania(), "2001000064");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().togo(), "2000023317");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().uganda(), "2000023312");
		gaulStore.add(m);
		m = new GaulModelData(BabelFish.print().vietnam(), "2001000077");
		gaulStore.add(m);
	}

}