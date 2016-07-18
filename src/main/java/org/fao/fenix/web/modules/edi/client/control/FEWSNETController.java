package org.fao.fenix.web.modules.edi.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.edi.client.view.fewsnet.FEWSNETWindow;
import org.fao.fenix.web.modules.edi.common.services.FEWSNETServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class FEWSNETController {
	
	public static SelectionListener<ButtonEvent> importZIPs(final FEWSNETWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String baseURL = w.getFewsnetTabPanel().getSettingsPanel().getHttpSettingsPanel().getBaseUrl().getValue();
				ListBox hiNDVI = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIList();
				ListBox hiNDVIDA = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIDAList();
				ListBox hiNDVIPYD = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIPYDList();
				ListBox haNDVI = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIList();
				ListBox haNDVIDA = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIDAList();
				ListBox haNDVIPYD = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIPYDList();
				if (hiNDVI.getSelectedIndex() > 0)
					importZIP(baseURL, "Hispaniola NDVI - ", "HISPANIOLA_", "raster-hispaniola-ndvi", hiNDVI, w.getFewsnetTabPanel().getDataPanel().getPeriodDateMap());
				if (hiNDVIDA.getSelectedIndex() > 0)
					importZIP(baseURL, "Hispaniola NDVI-DA - ", "HISPANIOLA_DA_", "raster-hispaniola-ndvi-da", hiNDVIDA, w.getFewsnetTabPanel().getDataPanel().getPeriodDateMap());
				if (hiNDVIPYD.getSelectedIndex() > 0)
					importZIP(baseURL, "Hispaniola NDVI-PYD - ", "HISPANIOLA_PYD_", "raster-hispaniola-ndvi-da", hiNDVIPYD, w.getFewsnetTabPanel().getDataPanel().getPeriodDateMap());
				if (haNDVI.getSelectedIndex() > 0)
					importZIP(baseURL, "Haiti NDVI - ", "HAITI_", "raster-hispaniola-ndvi", haNDVI, w.getFewsnetTabPanel().getDataPanel().getPeriodDateMap());
				if (haNDVIDA.getSelectedIndex() > 0)
					importZIP(baseURL, "Haiti NDVI-DA - ", "HAITI_", "raster-hispaniola-ndvi-da", haNDVIDA, w.getFewsnetTabPanel().getDataPanel().getPeriodDateMap());
				if (haNDVIPYD.getSelectedIndex() > 0)
					importZIP(baseURL, "Haiti NDVI-PYD - ", "HAITI_", "raster-hispaniola-ndvi-da", haNDVIPYD, w.getFewsnetTabPanel().getDataPanel().getPeriodDateMap());
			}
		};
	}
	
	public static void importZIP(String baseURL, String title, String code, String requestedStyle, ListBox list, Map<Integer, Date> periodDateMap) {
		
		// fetch the ZIP's URL
		String zipURL = "";
		int period = 1;
		for (int i = list.getItemCount() -1 ; i > 0 ; i--) {
			if (list.isItemSelected(i)) {
				zipURL = baseURL + list.getValue(i);
				title += list.getItemText(i);
				break;
			} else {
				period++;
			}
		}
		
		// create dates
		Date fromDate = periodDateMap.get(period);
		Date toDate = new Date(fromDate.getYear(), fromDate.getMonth(), 5 + fromDate.getDate());
		
		// create Resource for metadata
		code += period;
		ResourceVO rvo = createResourceVO(title, code, fromDate, toDate);
		
		// prepare bin
		final List<String> filesToDelete = new ArrayList<String>();
		
		// import the layer
		final LoadingWindow l = new LoadingWindow("FEWSNET Importer", "Connecting to FEWSNET to get the layers.", "Please wait...");
		try {
			FEWSNETServiceEntry.getInstance().importZipFiles(zipURL, rvo, requestedStyle, new AsyncCallback<List<String>>() {
				public void onSuccess(final List<String> bin) {
					l.destroyLoadingBox();
					final LoadingWindow l2 = new LoadingWindow("FEWSNET Importer", "Importing layers in the Workstation", "Please wait...");
					try {
						FEWSNETServiceEntry.getInstance().harvest(bin, new AsyncCallback<Boolean>() {
							public void onSuccess(Boolean isDone) {
								l2.destroyLoadingBox();
								FenixAlert.info(BabelFish.print().info(), "Done!");
								l2.destroyLoadingBox();
							}
							public void onFailure(Throwable e2) {
								l2.destroyLoadingBox();
								FenixAlert.error(BabelFish.print().error(), e2.getMessage());
								l2.destroyLoadingBox();							
							}
						});
					} catch (FenixGWTException e1) {
						l2.destroyLoadingBox();
						FenixAlert.error(BabelFish.print().error(), e1.getMessage());
						l2.destroyLoadingBox();
					}
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable e2) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e2.getMessage());
					l.destroyLoadingBox();							
				}
			});
		} catch (FenixGWTException e1) {
			l.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), e1.getMessage());
			l.destroyLoadingBox();
		}
		
		System.out.println(filesToDelete.size() + " files to delete");
		for (String s : filesToDelete)
			System.out.println("\t" + s);
		
		
	}
	
	public static ResourceVO createResourceVO(String title, String code, Date fromDate, Date toDate) {
		ResourceVO rvo = new ResourceVO();
		rvo.setTitle(title);
		rvo.setAbstractAbstract(BabelFish.print().fewsnetAbstract());
		rvo.setProvider("FENIX Workstation");
		rvo.setProviderContact("fabio.grita@fao.org");
		rvo.setProviderRegion("259");
		rvo.setSource("FEWSNET");
		rvo.setSourceContact("http://earlywarning.usgs.gov/fews/help.php");
		rvo.setSourceRegion("259");
		rvo.setRegion("108");
		rvo.setCategories("007");
		rvo.setSharingCode("PublicAndDownload");
		rvo.setDateLastUpdate(new Date());
		rvo.setStartDate(fromDate);
		rvo.setEndDate(toDate);
		rvo.setCode(code);
		rvo.setKeywords("ndvi, hispaniola, haiti");
		return rvo;
	}
	
	public static SelectionListener<ButtonEvent> getZIPs(final FEWSNETWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<String> zipURLs = collectZIPURLs(w);
				final LoadingWindow l = new LoadingWindow("FEWSNET Importer", "Connecting to FEWSNET to get the layers.", "Please wait...");
				try {
					FEWSNETServiceEntry.getInstance().getZipFiles(zipURLs, new AsyncCallback<List<String>>() {
						public void onSuccess(List<String> files) {
							l.destroyLoadingBox();
							for (String file : files)
								Window.open("../exportObject/" + file, "_blank", "status=no");
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable e2) {
							l.destroyLoadingBox();
							FenixAlert.error(BabelFish.print().error(), e2.getMessage());
							l.destroyLoadingBox();							
						}
					});
				} catch (FenixGWTException e1) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e1.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}
	
	public static List<String> collectZIPURLs(final FEWSNETWindow w) {
		String baseURL = w.getFewsnetTabPanel().getSettingsPanel().getHttpSettingsPanel().getBaseUrl().getValue();
		List<String> zipURLs = new ArrayList<String>();
		ListBox hiNDVI = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIList();
		for (int i = 1 ; i < hiNDVI.getItemCount() ; i++)
			if (hiNDVI.isItemSelected(i))
				zipURLs.add(baseURL + hiNDVI.getValue(i));
		ListBox hiNDVIDA = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIDAList();
		for (int i = 1 ; i < hiNDVIDA.getItemCount() ; i++)
			if (hiNDVIDA.isItemSelected(i))
				zipURLs.add(baseURL + hiNDVIDA.getValue(i));
		ListBox hiNDVIPYD = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIPYDList();
		for (int i = 1 ; i < hiNDVIPYD.getItemCount() ; i++)
			if (hiNDVIPYD.isItemSelected(i))
				zipURLs.add(baseURL + hiNDVIPYD.getValue(i));
		ListBox haNDVI = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIList();
		for (int i = 1 ; i < haNDVI.getItemCount() ; i++)
			if (haNDVI.isItemSelected(i))
				zipURLs.add(baseURL + haNDVI.getValue(i));
		ListBox haNDVIDA = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIDAList();
		for (int i = 1 ; i < haNDVIDA.getItemCount() ; i++)
			if (haNDVIDA.isItemSelected(i))
				zipURLs.add(baseURL + haNDVIDA.getValue(i));
		ListBox haNDVIPYD = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIPYDList();
		for (int i = 1 ; i < haNDVIPYD.getItemCount() ; i++)
			if (haNDVIPYD.isItemSelected(i))
				zipURLs.add(baseURL + haNDVIPYD.getValue(i));	
		return zipURLs;
	}
	
	public static ChangeHandler oneAtATime(final FEWSNETWindow w, final String code) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				ListBox hiNDVI = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIList();
				ListBox hiNDVIDA = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIDAList();
				ListBox hiNDVIPYD = w.getFewsnetTabPanel().getDataPanel().getHispaniolaNDVIPYDList();
				ListBox haNDVI = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIList();
				ListBox haNDVIDA = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIDAList();
				ListBox haNDVIPYD = w.getFewsnetTabPanel().getDataPanel().getHaitiNDVIPYDList();
				if (code.equalsIgnoreCase("HISPANIOLA")) {
					hiNDVIDA.setSelectedIndex(0);
					hiNDVIPYD.setSelectedIndex(0);
					haNDVI.setSelectedIndex(0);
					haNDVIDA.setSelectedIndex(0);
					haNDVIPYD.setSelectedIndex(0);
				} else if (code.equalsIgnoreCase("HISPANIOLA_DA")) {
					hiNDVI.setSelectedIndex(0);
					hiNDVIPYD.setSelectedIndex(0);
					haNDVI.setSelectedIndex(0);
					haNDVIDA.setSelectedIndex(0);
					haNDVIPYD.setSelectedIndex(0);
				} else if (code.equalsIgnoreCase("HISPANIOLA_PYD")) {
					hiNDVI.setSelectedIndex(0);
					hiNDVIDA.setSelectedIndex(0);
					haNDVI.setSelectedIndex(0);
					haNDVIDA.setSelectedIndex(0);
					haNDVIPYD.setSelectedIndex(0);
				} else if (code.equalsIgnoreCase("HAITI")) {
					hiNDVI.setSelectedIndex(0);
					hiNDVIDA.setSelectedIndex(0);
					hiNDVIPYD.setSelectedIndex(0);
					haNDVIDA.setSelectedIndex(0);
					haNDVIPYD.setSelectedIndex(0);
				} else if (code.equalsIgnoreCase("HAITI_DA")) {
					hiNDVI.setSelectedIndex(0);
					hiNDVIDA.setSelectedIndex(0);
					hiNDVIPYD.setSelectedIndex(0);
					haNDVI.setSelectedIndex(0);
					haNDVIPYD.setSelectedIndex(0);
				} else if (code.equalsIgnoreCase("HAITI_PYD")) {
					hiNDVI.setSelectedIndex(0);
					hiNDVIDA.setSelectedIndex(0);
					hiNDVIPYD.setSelectedIndex(0);
					haNDVI.setSelectedIndex(0);
					haNDVIDA.setSelectedIndex(0);
				}
				Info.display(BabelFish.print().info(), "You can select only one layer at a time.");
			}
		};
	}
	
}