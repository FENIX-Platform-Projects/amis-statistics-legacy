package org.fao.fenix.web.modules.datasetupload.client.view;

import org.fao.fenix.web.modules.core.client.uploader.view.UploaderWindow;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.datasetupload.client.control.DatasetUploaderController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class DatasetUploaderWindow extends UploaderWindow {

	private HorizontalPanel viewDatasetLinksPanel;

	private Hyperlink viewAsTableLink;

	private Hyperlink viewAsChartLink;


	private HTML viewDatasetLabel1;
	private HTML viewDatasetLabel2;


	public void build() {
		//set the window properties              
		setWindowProperties(BabelFish.print().datasetUploaderForm());

		//create form
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getSingleDatasetUploadServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());

		setFormPanel(buildBasicFormPanel(urlServletUpload));

		//set form listeners
		getFormPanel().addListener(Events.BeforeSubmit, DatasetUploaderController.getBeforeSubmitListener(this));
		getFormPanel().addListener(Events.Submit, DatasetUploaderController.getAfterSubmitFormListener(this));

		buildDatasetUploadForm();

		getCenter().add(getFormPanel());

		show();
	}


	private void addUploadButtonListener() {
		getUploadButton().addSelectionListener(DatasetUploaderController.getUploadListener(this));
	}


	private void buildDatasetUploadForm() {
		//addResourceTypeCombo();
		addBasicFileUploadFields();
		addDelimiterCombo();
		addUploadPolicyCombo();
		addRemarksArea();
		initializeLinksPanel();

		addButtons();
		addUploadButtonListener();

	}


	protected void initializeLinksPanel() {
		viewDatasetLinksPanel = new HorizontalPanel();
		viewDatasetLinksPanel.setSpacing(5);
		viewDatasetLabel1 = new HTML();
		viewDatasetLabel2 = new HTML();
		viewAsTableLink = new Hyperlink();
		viewAsChartLink = new Hyperlink();

		viewDatasetLinksPanel.add(viewDatasetLabel1);
		viewDatasetLinksPanel.add(viewAsTableLink);
		viewDatasetLinksPanel.add(viewDatasetLabel2);
		viewDatasetLinksPanel.add(viewAsChartLink);
	}


	public void setViewDatasetLinks() {
		
		DatasetUploaderController.updateChart(this);

		fillRemarksArea("<font color='green'>"+ BabelFish.print().uploadSuccessful() + "<br/></font>");

		viewDatasetLabel1.setHTML("<font color='green'>Click to view the dataset as a </font>");

		viewAsTableLink.setHTML("<font size='2pt' color='blue'>table</font>");
		viewAsTableLink.addClickListener(DatasetUploaderController.getOpenAsTableListener(this));
		DOM.setStyleAttribute(viewAsTableLink.getElement(), "cursor", "pointer");

		viewDatasetLabel2.setHTML("<font color='green'>or</font>");

		viewAsChartLink.setHTML("<font size='2pt' color='blue'>chart</font>");
		viewAsChartLink.addClickListener(DatasetUploaderController.getOpenAsChartListener(this));
		DOM.setStyleAttribute(viewAsChartLink.getElement(), "cursor", "pointer");

		addToFeedbackArea(viewDatasetLinksPanel);

	}

}
