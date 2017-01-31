package org.fao.fenix.web.modules.imftool.client.view;

import org.fao.fenix.web.modules.imftool.client.control.IMFToolController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.Hyperlink;

public class IMFToolPanel {

	private VerticalPanel wrapper;
	
	private Html mainTitle;
	
	private Html subTitle;
	
	private Html emailLabel;
	
	private Html emailSuffix;
	
	private Html latestUpdate;
	
	private TextField<String> email;
	
	private Button exchangeButton;
	
	private Button cpiButton;
	
	private Button bothButton;
	
	private int SPACING = 5;
	
	private String PANEL_WIDTH = "170px";
	
	private String WIDTH = "160px";
	
	private String BUTTON_WIDTH = "155px";
	
	private String SMALL_WIDTH = "100px";
	
	private String exchangeRatesURL = "";
	
	private String cpiURL = "";
	
	private Hyperlink disclaimer;
	
	public IMFToolPanel() {
		wrapper = new VerticalPanel();
		wrapper.setStyleAttribute("backgroundColor", "white");
		mainTitle = new Html("<div align='center' style='font-family: sans-serif; font-weight: bold; color: #1D317E; font-size: 12pt; text-decoration: underline;'>IMF Data</div>");
		subTitle = new Html("<div align='center' style='font-family: sans-serif; font-weight: bold; color: #1D317E; font-size: 7pt; text-decoration: underline;'>Exchange Rates and CPI</div>");
		emailLabel = new Html("<div style='font-family: sans-serif; color: #1D317E; font-size: 6pt;'><br>Please Insert Your E-mail Address</div>");
		exchangeButton = new Button("Send Exchange Rates");
		cpiButton = new Button("Send CPI");
		bothButton = new Button("Send Both");
		latestUpdate = new Html("<div align='right' style='font-family: sans-serif; color: #1D317E; font-size: 6pt; font-style: italic;'>Last Update: Jan 25th 2011</div>");
		disclaimer = new Hyperlink();
	}
	
	@SuppressWarnings("deprecation")
	public VerticalPanel build() {
		wrapper.setSpacing(SPACING);
		wrapper.setWidth(PANEL_WIDTH);
		wrapper.setBorders(true);
		exchangeButton.setWidth(BUTTON_WIDTH);
//		exchangeButton.setIconStyle("email");
		exchangeButton.setEnabled(false);
		cpiButton.setWidth(BUTTON_WIDTH);
//		cpiButton.setIconStyle("email");
		cpiButton.setEnabled(false);
		bothButton.setWidth(BUTTON_WIDTH);
//		bothButton.setIconStyle("email");
		bothButton.setEnabled(false);
		latestUpdate.setWidth(BUTTON_WIDTH);
		disclaimer.setHTML("<div align='right' style='font-family: sans-serif; color: red; font-size: 6pt; font-weight: bold; text-decoration: underline; cursor: hand; cursor: pointer;'>" + BabelFish.print().termsOfUse() + "</div>");
		disclaimer.addClickHandler(IMFToolController.disclaimer());
		wrapper.add(mainTitle);
		wrapper.add(subTitle);
		wrapper.add(emailLabel);
		wrapper.add(buildEMailPanel());
		wrapper.add(exchangeButton);
		wrapper.add(cpiButton);
		wrapper.add(bothButton);
		wrapper.add(latestUpdate);
		wrapper.add(disclaimer);
		enhanceButtons();
		return wrapper;
	}
	
	private HorizontalPanel buildEMailPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setWidth(WIDTH);
		email = new TextField<String>();
		email.setWidth(SMALL_WIDTH);
		email.setEmptyText("e.g. John.Doe");
		emailSuffix = new Html("<div style='font-family: sans-serif; color: #1D317E; font-size: 7pt; font-weight: bold;'>@fao.org</div>");
		p.add(email);
		p.add(emailSuffix);
		return p;
	}
	
	private void enhanceButtons() {
		exchangeButton.addSelectionListener(IMFToolController.sendData(this, true, false));
		cpiButton.addSelectionListener(IMFToolController.sendData(this, false, true));
		bothButton.addSelectionListener(IMFToolController.sendData(this, true, true));
	}

	public Html getEmailSuffix() {
		return emailSuffix;
	}

	public Html getLatestUpdate() {
		return latestUpdate;
	}

	public TextField<String> getEmail() {
		return email;
	}

	public Button getExchangeButton() {
		return exchangeButton;
	}

	public Button getCpiButton() {
		return cpiButton;
	}

	public Button getBothButton() {
		return bothButton;
	}

	public String getExchangeRatesURL() {
		return exchangeRatesURL;
	}

	public void setExchangeRatesURL(String exchangeRatesURL) {
		this.exchangeRatesURL = exchangeRatesURL;
	}

	public String getCpiURL() {
		return cpiURL;
	}

	public void setCpiURL(String cpiURL) {
		this.cpiURL = cpiURL;
	}
	
}