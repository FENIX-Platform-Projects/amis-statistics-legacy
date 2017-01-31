package org.fao.fenix.web.modules.imftool.client.control;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.imftool.client.view.IMFDisclaimerWindow;
import org.fao.fenix.web.modules.imftool.client.view.IMFToolPanel;
import org.fao.fenix.web.modules.imftool.common.services.IMFServiceEntry;
import org.fao.fenix.web.modules.imftool.common.vo.IMFToolVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IMFToolController {
	
	public static final String DOMAIN = "@fao.org";

	public static void getLastDataInfo(final IMFToolPanel p) {
		p.getLatestUpdate().setHtml("<div align='right' style='font-family: sans-serif; color: orange; font-size: 6pt; font-weight: bold;'>Loading Data From IMF...</div>");
		try {
			IMFServiceEntry.getInstance().getLastDataInfo(new AsyncCallback<IMFToolVO>() {
				public void onSuccess(IMFToolVO vo) {
					p.getLatestUpdate().setHtml("<div align='right' style='font-family: sans-serif; color: green; font-size: 6pt; font-weight: bold;'>Last Update: " + vo.getLastUpdate() + "</div>");
					p.setCpiURL(vo.getCpiURL());
					p.setExchangeRatesURL(vo.getExchangeRatesURL());
					p.getExchangeButton().setEnabled(true);
					p.getCpiButton().setEnabled(true);
					p.getBothButton().setEnabled(true);
				}
				public void onFailure(Throwable e1) {
					p.getLatestUpdate().setHtml("<div align='right' style='font-family: sans-serif; color: red; font-size: 6pt; font-weight: bold;'>The Service is Currently Unavailable</div>");
				}
			});
		} catch(FenixGWTException e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}
	
	public static SelectionListener<ButtonEvent> sendData(final IMFToolPanel p, final boolean hasExchangeRates, final boolean hasCPI) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				sendDataAgent(p, hasExchangeRates, hasCPI);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> close(final FenixWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().hide();
			}
		};
	}
	
	public static void sendDataAgent(final IMFToolPanel p, boolean hasExchangeRates, boolean hasCPI) {
		final IMFToolVO vo = collectParameters(p);
		if (!hasExchangeRates)
			vo.setExchangeRatesURL(null);
		if (!hasCPI)
			vo.setCpiURL(null);
		enableButtons(p, false);
		try {
			final IMFDisclaimerWindow loading = new IMFDisclaimerWindow();
			loading.build(BabelFish.print().info(), "Connecting to IMF to retrieve last data. Please Wait...", false);
			IMFServiceEntry.getInstance().sendData(vo, hasCPI, hasExchangeRates, new AsyncCallback<Long>() {
				public void onSuccess(Long id) {
					loading.getWindow().hide();
					new IMFDisclaimerWindow().build(BabelFish.print().info(), "An e-mail will be sent within 5 minutes to the address '" + vo.getEmail() + "'", true);
					enableButtons(p, true);
				}
				public void onFailure(Throwable e1) {
					FenixAlert.error(BabelFish.print().error(), e1.getMessage());
				}
			});
		} catch(FenixGWTException e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}
	
	public static void enableButtons(IMFToolPanel p, boolean enable) {
		p.getExchangeButton().setEnabled(enable);
		p.getCpiButton().setEnabled(enable);
		p.getBothButton().setEnabled(enable);
	}
	
	public static IMFToolVO collectParameters(IMFToolPanel p) {
		IMFToolVO vo = new IMFToolVO();
		vo.setCpiURL(p.getCpiURL());
		vo.setEmail(p.getEmail().getValue() + DOMAIN);
		vo.setExchangeRatesURL(p.getExchangeRatesURL());
		return vo;
	}
	
	public static ClickHandler disclaimer() {
		return new ClickHandler() {
			public void onClick(ClickEvent e) {
				new IMFDisclaimerWindow().build(BabelFish.print().termsOfUse(), BabelFish.print().imfToolDisclaimer(), true);
			}
		};
	}
	
}