package org.fao.fenix.web.modules.tinymcereport.client.control;

import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCECustomizePageFormatWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportGalleryWindow;
import org.fao.fenix.web.modules.tinymcereport.common.services.TinyMCEServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TinyMCEReportGalleryController {

	public static void populateTemplatesListStore(final TinyMCEReportGalleryWindow w) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Templates.", BabelFish.print().pleaseWait());
		try {
			TinyMCEServiceEntry.getInstance().findAllTemplates(new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String, String> map) {
					l.destroyLoadingBox();
					TreeMap<String, String> sorted = sortByKeys(map);
					w.getTemplatesListStore().removeAll();
					w.getTemplatesListStore().add(new GaulModelData("<b>Quick Links</b>", ""));
					w.getTemplatesListStore().add(new GaulModelData("- Customize Page Format", "CUSTOMIZE_PAGE_FORMAT"));
					w.getTemplatesListStore().add(new GaulModelData("<b>Available Templates Created by the Users</b>", ""));
					for (String title : sorted.keySet()) {
						GaulModelData md = new GaulModelData("- " + title, map.get(title));
						w.getTemplatesListStore().add(md);
					}
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E2) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			l.destroyLoadingBox();
		}
	}
	
	public static SelectionChangedListener<GaulModelData> selectionChangedListener(final TinyMCEReportGalleryWindow w) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				if (se.getSelectedItem().getGaulCode().length() > 0) {
					if (se.getSelectedItem().getGaulCode().equalsIgnoreCase("CUSTOMIZE_PAGE_FORMAT")) {
						customizePageFormat(w);
					} else {
						w.getHtml().setHTML(se.getSelectedItem().getGaulCode());
					}
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> customizePage(final TinyMCECustomizePageFormatWindow fw) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				try {
					isValidPageFormat(fw);
					String html = "";
					for (int i = 0 ; i < Integer.valueOf(fw.getPages().getValue()) ; i++) {
						html += "<div align=\"center\" style=\"background-color: #D0DDED; \"><div style=\"width: " + fw.getWidth().getValue() + "mm; height: " + fw.getHeight().getValue() + "mm; background-color: #ffffff; border: 1px solid black;\"></div></div>";
						if (i < Integer.valueOf(fw.getPages().getValue()) - 1)
							html += "<!-- PAGE_BREAK -->";
					}
					fw.getTinyMCEReportGalleryWindow().getHtml().setHTML(html);
					fw.getWindow().hide();
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	public static void isValidPageFormat(TinyMCECustomizePageFormatWindow fw) throws FenixGWTException {
		if (fw.getWidth().getValue() == null)
			throw new FenixGWTException("Please Select the Page Width");
		if (fw.getHeight().getValue() == null)
			throw new FenixGWTException("Please Select the Page Height");
		if (fw.getPages().getValue() == null)
			throw new FenixGWTException("Please Select the Number of Pages");
		try {
			Integer.valueOf(fw.getWidth().getValue());
			Integer.valueOf(fw.getHeight().getValue());
			Integer.valueOf(fw.getPages().getValue());
		} catch (NumberFormatException e) {
			throw new FenixGWTException("Please Insert Number Only");
		}
	}
	
	public static ChangeHandler pageFormat(final TinyMCECustomizePageFormatWindow fw) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				if (fw.getFormatList().getSelectedIndex() > 0) {
					if (fw.getFormatList().getValue(fw.getFormatList().getSelectedIndex()).equalsIgnoreCase("A4")) {
						fw.getWidth().setValue("200");
						fw.getHeight().setValue("270");
					}
				}
			}
		};
	}
	
	public static void customizePageFormat(TinyMCEReportGalleryWindow w) {
		TinyMCECustomizePageFormatWindow cw = new TinyMCECustomizePageFormatWindow(w);
		cw.build();
	}
	
	public static SelectionListener<ButtonEvent> selectTemplate(final TinyMCEReportGalleryWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				TinyMCENativeController.setContent(w.getTinyMCEPanelCode(), w.getHtml().getHTML());
				w.getWindow().hide();
			}
		};
	}
	
	public static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
}