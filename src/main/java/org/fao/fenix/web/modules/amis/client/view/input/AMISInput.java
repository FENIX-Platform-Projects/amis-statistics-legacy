package org.fao.fenix.web.modules.amis.client.view.input;

import java.util.Map;

import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.AMISLogInController;
import org.fao.fenix.web.modules.amis.client.control.input.AMISInputController;
import org.fao.fenix.web.modules.amis.client.control.input.DataSourceString;
import org.fao.fenix.web.modules.amis.client.control.input.MeasurementUnit;
import org.fao.fenix.web.modules.amis.client.control.input.Month;
import org.fao.fenix.web.modules.amis.client.control.input.TestDataSourceString;
import org.fao.fenix.web.modules.amis.client.control.input.TestMeasurementUnit;
import org.fao.fenix.web.modules.amis.client.control.input.TestMonth;
import org.fao.fenix.web.modules.amis.client.control.input.TestYear;
import org.fao.fenix.web.modules.amis.client.control.input.Year;
import org.fao.fenix.web.modules.amis.client.view.inputImporter.ImporterWindow;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.CCBSWindow;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginPanel;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginRegisterPanel;
import org.fao.fenix.web.modules.amis.client.view.login.AMISRegistrationForm;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.enums.CBSURLParameters;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListImporterWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.Window.Navigator;

//import javax.servlet.http.HttpServletRequest;

public class AMISInput extends LayoutContainer{

    private FormData formData;
    Window complex;
    Window uploadComplex;
    ContentPanel panel;
    ContentPanel north;
    ContentPanel west;
    ContentPanel center;
    ContentPanel east;
    ContentPanel south;
    ContentPanel tot;
    //ContentPanel uploadPanel;
    LayoutContainer uploadPanel;
    Button inputButton;
    //Button inputButton_cbsMonthlyTool;
    Button uploadButton;
    TabItem itemFaostat;
    TabItem itemPsd;
    TabItem itemCCbs;
    TabItem itemMonthly;
    TabItem itemAnnual;
    ComboBox<Year> comboStartYear;
    ComboBox<Year> comboYear;
    ComboBox<Year> comboEndYear;
    ComboBox<Month> comboMonth;
    ListStore<Year> yearsCombo;
    ListStore<Year> endYearsStore;
    String startYearSelected;
    String typeOfYear;
    String yearForMonthString [];
    Html yearOneForMonth;
    Html yearTwoForMonth;
    Html yearThreeForMonth;
    Html yearFourForMonth;
    Html yearFiveForMonth;
    Html yearSixForMonth;
    Html yearSevenForMonth;
    Html yearEightForMonth;
    Html yearNineForMonth;
    Html yearTenForMonth;
    Html yearElevenForMonth;
    Html yearOneForYear;
    Html yearTwoForYear;
    Html yearThreeForYear;
    Html yearFourForYear;
    Html yearFiveForYear;
    Html yearSixForYear;
    Html yearSevenForYear;
    Html yearEightForYear;
    Html yearNineForYear;
    Html yearTenForYear;
    Html yearElevenForYear;
    //TextField<String> yearOneForMonth;
//	TextField<String> yearTwoForMonth;
//	TextField<String> yearThreeForMonth;
//	TextField<String> yearFourForMonth;
//	TextField<String> yearFiveForMonth;
//	TextField<String> yearSixForMonth;
//	TextField<String> yearSevenForMonth;
//	TextField<String> yearEightForMonth;
//	TextField<String> yearNineForMonth;
//	TextField<String> yearTenForMonth;
//	TextField<String> yearElevenForMonth;
//	TextField<String> yearOneForYear;
//	TextField<String> yearTwoForYear;
//	TextField<String> yearThreeForYear;
//	TextField<String> yearFourForYear;
//	TextField<String> yearFiveForYear;
//	TextField<String> yearSixForYear;
//	TextField<String> yearSevenForYear;
//	TextField<String> yearEightForYear;
//	TextField<String> yearNineForYear;
//	TextField<String> yearTenForYear;
//	TextField<String> yearElevenForYear;
    TextField<String> to;
    Html oneTypeOfYear;
    Html twoTypeOfYear;
    Html threeTypeOfYear;
    Html fourTypeOfYear;
    Html fiveTypeOfYear;
    Html sixTypeOfYear;
    Html sevenTypeOfYear;
    Html eightTypeOfYear;
    Html nineTypeOfYear;
    Html tenTypeOfYear;
    Html elevenTypeOfYear;
    //TextField oneTypeOfYear;
//	TextField twoTypeOfYear;
//	TextField threeTypeOfYear;
//	TextField fourTypeOfYear;
//	TextField fiveTypeOfYear;
//	TextField sixTypeOfYear;
//	TextField sevenTypeOfYear;
//	TextField eightTypeOfYear;
//	TextField nineTypeOfYear;
//	TextField tenTypeOfYear;
//	TextField elevenTypeOfYear;
    CheckBox marketingCb;
    CheckBox calendarCb;
    FieldSet fieldSet;
    AMISLoginRegisterPanel loginRegisterPanel;
    AMISMainMenu mainMenu;

    //To check if the tool is open ....
    public static boolean openedCbsTool;

    //private HttpServletRequest request;

    ListStore<AMISCodesModelData> countrylistStore = new ListStore<AMISCodesModelData>();
    ListStore<AMISCodesModelData> dataSourcelistStore = new ListStore<AMISCodesModelData>();
    private /*static*/ ComboBox<AMISCodesModelData>   countryListBox   = new ComboBox<AMISCodesModelData>();
    private /*static*/ ComboBox<AMISCodesModelData>   dataSourceListBox   = new ComboBox<AMISCodesModelData>();
    private Html countryLabel;
    private Html selectFromComboBox;
    private Html selectFromComboBoxForAmisSecretariat;
    String titleWidth = "120px";
    Integer leftSpacing = 15;
    AMISCodesModelData dataSourceSelected = new AMISCodesModelData();
    AMISCodesModelData countrySelected = new AMISCodesModelData();

    public static String action = new String();
    //private String username;

	/*public AMISInput() {
		System.out.println(" Amisinput Constructor AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
		//System.out.println("Class: AmisInput Function: constructor Text:Start");
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setBorders(false);
		panel.setHeaderVisible(false);
		loginRegisterPanel = new AMISLoginRegisterPanel(this);
		openedCbsTool = false;
//		if(FenixUser.hasAdminRole())
//		{
//			buildInputButtons();
//		}
//		else
//		{
//			loginRegisterPanel = new AMISLoginRegisterPanel(this);
//		}
		System.out.println("Class: AmisInput Function: buildInputUpload Text:end   FenixUser.hasAdminRole() "+FenixUser.hasAdminRole());
	}*/


    // New AMISInput Constructor
    // AMISInput is no longer passed to AMISLoginRegisterPanel, as now the AMISLoginRegisterPanel and log in handling
    // is at a more higher level
    // i.e. The same login panel is accessed in AMISInput as well as the external 'Log In' link
    public AMISInput(AMISMainMenu mainMenu) {
        System.out.println(" Amisinput Constructor AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
        panel = new ContentPanel();
        panel.setBodyBorder(false);
        panel.setBorders(false);
        panel.setHeaderVisible(false);
        this.mainMenu = mainMenu;
        openedCbsTool = false;

        System.out.println("Class: AmisInput Function: AMISInput(AMISMainMenu mainMenu)  FenixUser.hasAdminRole() "+FenixUser.hasAdminRole());
    }

    public ContentPanel buildInputUpload() {
        System.out.println("Class: AmisInput Function: buildInputUpload Text:FenixUser.hasAdminRole() "+FenixUser.hasAdminRole());

        // The INPUT TOOL set to only open for AMIS Secretariat
        // i.e. INPUT DATA in Menu only available when AMIS Secretariat logged in, so no need for
        // registration panel


        //if(!FenixUser.hasAdminRole())
       // {
            // loginRegisterPanel must be initiated here (as opposed to the constructor), to avoid AMISLoginPanel.getAmisUserParameters() being lost
          //  loginRegisterPanel = new AMISLoginRegisterPanel(mainMenu);
           // panel.add(loginRegisterPanel.build());
            //panel.add(new Html(""));

       // }
      //  else
       // {

        if (FenixUser.hasAdminRole()) {
            System.out.println("Class: AmisInput Function: AMISInput(AMISMainMenu mainMenu)  FenixUser.hasAdminRole() "+FenixUser.hasAdminRole()+ " | AMISLoginPanel.getAmisUserParameters().getUsername() = "+ AMISLoginPanel.getAmisUserParameters().getUsername());

            setUsername(AMISLoginPanel.getAmisUserParameters().getUsername());

            panel.removeAll();

            if((getUsername()!=null)&&(getUsername().equals(CCBS.IGC_USER))){
                panel.add(buildIGCText());
            }
            else {
                //String userAgent = request.getHeader("user-agent");
                String userAgent = Navigator.getUserAgent();
//                UserAgentStringInfoProvider obj = new UserAgentStringInfoProvider(new HttpServletRequest());
//                detectFirefox
                if((userAgent!=null)&&(userAgent.contains("MSIE")))
                {
                    FenixAlert.info("Warning", "This application is better viewed with Mozilla Firefox and Google Chrome.", null);
                }
//                    MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);

                panel.add(buildInputButtons());
                //Start
//				if((getUsername()!=null)&&(getUsername().equals(CCBS.AMIS_SECRETARIAT)))
//				{
//					System.out.println("Class: AmisInput Function: buildInputUpload Text:FenixUser.hasAdminRole()  SECRETARIAT -- NAMES "+CCBS.COUNTRY_NAMES.size() + " | "+CCBS.COUNTRY_NAMES);
//
//					getCountryListBox().getStore().removeAll();
//
//					for(int i=0; i< CCBS.COUNTRY_NAMES.size(); i++)
//					{
//						getCountryListBox().getStore().add(new AMISCodesModelData(CCBS.COUNTRY_CODES.get(i), CCBS.COUNTRY_NAMES.get(i)));
//					}
//					setSelectCountryAndDatasourceInputPage();
//				}
//				else
//				{
//
//					System.out.println("Class: AmisInput Function: buildInputUpload Text:FenixUser.hasAdminRole() NOT SECRETARIAT  --- NAMES "+CCBS.COUNTRY_NAMES.size() + " | "+CCBS.COUNTRY_NAMES);
//
//					setSelectCountryTextInputPage(CCBS.COUNTRY_NAMES.get(0));
//					countryLabel.setHtml("<div class='input_panel_text'><b>"+CCBS.COUNTRY_NAMES.get(0) +"</b></div>");
//					panel.add(new Html(""));
//					getCountrySelected().setLabel(CCBS.COUNTRY_NAMES.get(0));
//					getCountrySelected().setCode(CCBS.COUNTRY_CODES.get(0));
//					dataSourceListBox.getStore().removeAll();
//					AMISCodesModelData dataSourceModel = new AMISCodesModelData(CCBS.DATA_SOURCE_NAMES.get(0), CCBS.DATA_SOURCE_NAMES.get(0));
//					dataSourceListBox.getStore().add(dataSourceModel);
//					dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
//
//					System.out.println("Class: AmisInput Function: buildInputUpload Text:getCountrySelected().getLabel() "+getCountrySelected().getLabel());
//					System.out.println("Class: AmisInput Function: buildInputUpload Text:getCountrySelected().getCode() "+getCountrySelected().getCode());
//
//
//				}
                //End
            }


            //CCBS.DATA_SOURCE_NAMES.clear();
            //CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining + " (Training Set)"));

            //getPanel().getLayout().layout();

        }//FINE
        //panel.add(buildInputUploadButtonPanel());
        //	System.out.println("Class: AmisInput Function: buildInputUpload Text:End");
        return panel;
    }

    private HorizontalPanel buildIGCText() {
        HorizontalPanel panel = new HorizontalPanel();
        panel.setSpacing(10);
        panel.setHeight(500);
        HTML html = new HTML();
        String text1 = "";
        text1 = "&nbsp;&nbsp;Only registered AMIS members can input data.";
        html.setHTML("<div class='amis_login_register_text' style='cursor:pointer;'>"+ text1 +"</div>");
        panel.add(html);

        return panel;
    }


//	public ContentPanel buildCountryListBox()
//	{
//		ContentPanel p = new ContentPanel();
//		p.setBodyBorder(false);
//		p.setBorders(true);
//		p.setHeaderVisible(false);
//		p.setStyleAttribute("padding", "20px");
//		countryListBox.setWidth("140px");
//		countryListBox.setDisplayField("label");
//		countryListBox.setStore(countrylistStore);
//		countryListBox.setTriggerAction(TriggerAction.ALL);
//		countryListBox.getStore().removeAll();
//		countryListBox.setValue(null);
//		p.add(countryListBox);
//		return p;
//	//	countryListBox.addSelectionChangedListener(countryComboListener());
//	}

    public HorizontalPanel buildCountryAndDataSourceListBox(HorizontalPanel hpGo)
    {
        HorizontalPanel p = new HorizontalPanel();
        countryListBox.setWidth("200px");
        countryListBox.setDisplayField("label");
        countryListBox.setStore(countrylistStore);
        countryListBox.setTriggerAction(TriggerAction.ALL);
        countryListBox.getStore().removeAll();
        countryListBox.setValue(null);
        countryListBox.removeAllListeners();
        countryListBox.addSelectionChangedListener(AMISLogInController.countryComboListener(countryListBox, dataSourceListBox, this));
        p.add(countryListBox);
        p.add(FormattingUtils.addHSpace(10));
        dataSourceListBox.setWidth("800px");
        dataSourceListBox.setDisplayField("label");
        dataSourceListBox.setStore(dataSourcelistStore);
        dataSourceListBox.setTriggerAction(TriggerAction.ALL);
        dataSourceListBox.getStore().removeAll();
        dataSourceListBox.setValue(null);
        dataSourceListBox.removeAllListeners();
        dataSourceListBox.addSelectionChangedListener(AMISLogInController.dataSourceComboListener(dataSourceListBox, hpGo, this));
        p.setVerticalAlign(VerticalAlignment.MIDDLE);
        p.add(dataSourceListBox);
        return p;
    }

    public HorizontalPanel buildCountryLabelAndDataSourceListBox(HorizontalPanel hpGo)
    {
        HorizontalPanel p = new HorizontalPanel();
//		countryListBox.setWidth("140px");
//		countryListBox.setDisplayField("label");
//		countryListBox.setStore(countrylistStore);
//		countryListBox.setTriggerAction(TriggerAction.ALL);
//		countryListBox.getStore().removeAll();
//		countryListBox.setValue(null);
//		//countryListBox.addSelectionChangedListener(AMISController.countryComboListener(countryListBox, dataSourceListBox, this));
//		p.add(countryListBox);
        //countryLabel = new Label();
        countryLabel = new Html("<div class='input_panel_text'><b></b></div>");
        countryLabel.setStyleName("input_panel_text");
        countryLabel.setWidth("200px");
        p.add(countryLabel);
        p.add(FormattingUtils.addHSpace(10));
        dataSourceListBox.setWidth("800px");
        //dataSourceListBox.setWidth("170px");
        dataSourceListBox.setDisplayField("label");
        dataSourceListBox.setStore(dataSourcelistStore);
        dataSourceListBox.setTriggerAction(TriggerAction.ALL);
        dataSourceListBox.getStore().removeAll();
        dataSourceListBox.setValue(null);
        dataSourceListBox.addSelectionChangedListener(AMISLogInController.dataSourceComboListener(dataSourceListBox, hpGo, this));
        p.setVerticalAlign(VerticalAlignment.MIDDLE);
        p.add(dataSourceListBox);
        return p;
    }

    private void buildUploadMainPanel(){
        //System.out.println("Class: AmisInput Function: buildUploadMainPanel Text:Start");

        //final Dialog complex = new Dialog();
        uploadComplex = new Window();
        uploadComplex.setBodyBorder(false);
        uploadComplex.setHeading("Upload a dataset (Excel or CSV)");
        uploadComplex.setWidth(470);
        //uploadComplex.setWidth(1000);
        uploadComplex.setHeight(150);
        //complex.setHideOnButtonClick(true);
        BorderLayout layout = new BorderLayout();
        uploadComplex.setLayout(layout);
        //AmisExcelImporterWindow uploadPanel = new AmisExcelImporterWindow();
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setSplit(true);
        data.setCollapsible(true);
        uploadComplex.setResizable(true);
        uploadComplex.setIconStyle("uploadData");
        //uploadComplex.add(uploadPanel.build(), data);
        createUploadPanel();
        uploadComplex.add(uploadPanel);
        //System.out.println("Class: AmisInput Function: buildUploadMainPanel Text:End");
    }

    private void createUploadPanel()
    {
        VerticalPanel vp = new VerticalPanel();
        vp.setSpacing(12);
        //uploadPanel = new ContentPanel();
        //uploadPanel.setLayout(new FormLayout());
        //uploadPanel.setHeaderVisible(false);
        uploadPanel = new LayoutContainer();
        //uploadPanel.setHeaderVisible(false);
        HorizontalPanel dataSetPanel = new HorizontalPanel();
        //HorizontalPanel metaDataPanel = new HorizontalPanel();
        //dataPanel.setSpacing(10);
        final FormPanel panel = new FormPanel();
        //panel.setStyleAttribute("backgroundColor", "#FFFFFF");
        //dataSetPanel.setStyleAttribute("background-color", "#FFFFFF");
        //metaDataPanel.setStyleAttribute("background-color", "#FFFFFF");
        vp.setStyleAttribute("background-color", "#FFFFFF");
        panel.setStyleAttribute("background-color", "#FFFFFF");
        uploadPanel.setStyleAttribute("background-color", "#FFFFFF");
        panel.setBodyBorder(false);
        vp.setBorders(false);
        //panel.setHeading("File Upload Example");
        panel.setHeaderVisible(false);
        //panel.setFrame(true);
        //panel.setAction("myurl");
        panel.setEncoding(Encoding.MULTIPART);
        panel.setMethod(Method.POST);
        panel.setButtonAlign(HorizontalAlignment.CENTER);
        panel.setWidth(450);
        //panel.setWidth(1000);
        panel.setHeight(100);
        //FormLayout fl = new FormLayout();
//		fl.setLabelWidth(120);
//		panel.setLayout(fl);
        Html label = new Html("<b>" + "Dataset File" + ": </b>");
        label.setWidth(100);
        FileUploadField datasetFile = new FileUploadField();
        datasetFile.setAllowBlank(false);
        datasetFile.setStyleAttribute("backgroundColor", "white");
        datasetFile.setName("uploadeddatasetFile");
        //datasetFile.setFieldLabel("Dataset File");
        datasetFile.setEmptyText("Excel or CVS file containing your data.");
        datasetFile.setWidth(300);
        dataSetPanel.add(label);
        dataSetPanel.add(datasetFile);
        vp.add(dataSetPanel);
        //panel.add(dataSetPanel);
        //panel.add(datasetFile);
//		label = new Html("<b>" + "Metadata File" + ": </b>");
//		label.setWidth(100);
//		FileUploadField metadataFile = new FileUploadField();
//		metadataFile.setAllowBlank(false);
//		metadataFile.setName("uploadedmetadataFile");
//		//metadataFile.setFieldLabel("Metadata File");
//		metadataFile.setWidth(300);
//		metadataFile.setEmptyText("Metadata XML file, if you have it.");
//		metaDataPanel.add(label);
//		metaDataPanel.add(metadataFile);
//		vp.add(metaDataPanel);

        //panel.add(metaDataPanel);
        //panel.add(metadataFile);
        panel.add(vp);
        Button btn = new Button("Reset");
        btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                panel.reset();
            }
        });
        panel.addButton(btn);
        btn = new Button("Submit");
        btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!panel.isValid()) {
                    return;
                }
                // normally would submit the form but for example no server set up to
                // handle the post
                // panel.submit();
                MessageBox.info("Action", "Your file has been uploaded", null);
            }
        });
        panel.addButton(btn);
        uploadPanel.add(panel);
    }

    private void buildMainPanelCCbsTool(){
        System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected ppppp "+ getDataSourceSelected().getLabel());
        System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected "+ getDataSourceSelected().getCode());
        System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getLabel());
        System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getCode());
//		String datasourceLabel = getDataSourceSelected().getLabel();
//		String datasourceCode = getDataSourceSelected().getCode();
//		String countryLabel = getCountrySelected().getLabel();
//		String countryCode = getCountrySelected().getCode();
//		CCBSWindow w = new CCBSWindow();
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow build");
//		w.build(this);
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow show");
//		w.show();
//		System.out.println("Class: AMIS Function:build Text: After CCBSWindow show");

        //String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
        //String query = "?=1&";
//        String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";

        //START
        AMISServiceEntry.getInstance().getCBSURL(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String url) {

                String datasourceLabel = getDataSourceSelected().getLabel();
                String datasourceCode = getDataSourceSelected().getCode();
                String countryLabel = getCountrySelected().getLabel();
                String countryCode = getCountrySelected().getCode();
                String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
                //TEST: String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html"+query+"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
                String iframe = "<iframe src=\""+url+query+"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 770px;\">Your browser does not support iframes.</iframe>";
                String query2 = "?"+CBSURLParameters.datasourceLabel+"="+datasourceLabel+"&"+CBSURLParameters.datasourceCode+"="+datasourceCode+"&"+CBSURLParameters.countryLabel+"="+countryLabel+"&"+CBSURLParameters.countryCode+"="+countryCode+"";
                System.out.println(" query "+ query);
                System.out.println(" iframe "+ iframe);
                Html html = new Html(iframe);
                openedCbsTool = true;
                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                    RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));

//				ContentPanel panel = new ContentPanel();
//				panel.add(5 v)
                //html.setBorders(true);
                html.setStyleAttribute("background", "#FFFFFF");

                RootPanel.get("MAIN_CONTENT").add(html);
            }

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("Cbs get url Exception!!!!");
            }
        });

        //END

//        String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html"+query+"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
//        String query2 = "?"+CBSURLParameters.datasourceLabel+"="+datasourceLabel+"&"+CBSURLParameters.datasourceCode+"="+datasourceCode+"&"+CBSURLParameters.countryLabel+"="+countryLabel+"&"+CBSURLParameters.countryCode+"="+countryCode+"";
//        System.out.println(" query "+ query);
//        System.out.println(" iframe "+ iframe);
//        Html html = new Html(iframe);
//        openedCbsTool = true;
//		if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
//			RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
//
////		ContentPanel panel = new ContentPanel();
////		panel.add(5 v)
//
//
//        RootPanel.get("MAIN_CONTENT").add(html);
    }

    private void buildMainPanelCCbsTool_monthlyTool(){
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected ppppp "+ getDataSourceSelected().getLabel());
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected "+ getDataSourceSelected().getCode());
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getLabel());
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getCode());
//		String datasourceLabel = getDataSourceSelected().getLabel();
//		String datasourceCode = getDataSourceSelected().getCode();
//		String countryLabel = getCountrySelected().getLabel();
//		String countryCode = getCountrySelected().getCode();
//		CCBSWindow w = new CCBSWindow();
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow build");
//		w.build(this);
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow show");
//		w.show();
//		System.out.println("Class: AMIS Function:build Text: After CCBSWindow show");

        //String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
        //String query = "?=1&";
//        String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
        //http://127.0.0.1:8000/cbsmonths/src/main/webapp/index.html

        AMISServiceEntry.getInstance().getCBSMONTHSURL(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String url) {

//					String datasourceLabel = getDataSourceSelected().getLabel();
//					String datasourceCode = getDataSourceSelected().getCode();
//					String countryLabel = getCountrySelected().getLabel();
//					String countryCode = getCountrySelected().getCode();
                //String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
                //TEST: String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html"+query+"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
                ////getUsername()
                String query = "";
                if((getUsername()!=null)&&(getUsername().equals(CCBS.AMIS_SECRETARIAT)))
                {
                    query = "?user_role="+getUsername()+"&app_name="+"AMIS_CBS_MONTHS";
                }
                else
                {
                    //Country
                    query = "?user_role="+CCBS.COUNTRY_NAMES.get(0)+"&app_name="+"AMIS_CBS_MONTHS";
                }
                String iframe = "<iframe src=\""+url+query+"\" marginwidth=\"75px\" frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 868px;\">Your browser does not support iframes.</iframe>";
                // String query2 = "?"+CBSURLParameters.datasourceLabel+"="+datasourceLabel+"&"+CBSURLParameters.datasourceCode+"="+datasourceCode+"&"+CBSURLParameters.countryLabel+"="+countryLabel+"&"+CBSURLParameters.countryCode+"="+countryCode+"";
                //System.out.println(" query "+ query);
                System.out.println(" iframe "+ iframe);
                Html html = new Html(iframe);
                openedCbsTool = true;
                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                    RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));

//					ContentPanel panel = new ContentPanel();
//					panel.add(5 v)
                //html.setBorders(true);
                html.setStyleAttribute("background", "#FFFFFF");

                RootPanel.get("MAIN_CONTENT").add(html);
            }

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("Cbs get url Exception!!!!");
            }
        });
    }

    private void buildMainPanelCCbsTool_PopulationIframe(){
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected ppppp "+ getDataSourceSelected().getLabel());
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected "+ getDataSourceSelected().getCode());
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getLabel());
        //System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getCode());
//		String datasourceLabel = getDataSourceSelected().getLabel();
//		String datasourceCode = getDataSourceSelected().getCode();
//		String countryLabel = getCountrySelected().getLabel();
//		String countryCode = getCountrySelected().getCode();
//		CCBSWindow w = new CCBSWindow();
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow build");
//		w.build(this);
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow show");
//		w.show();
//		System.out.println("Class: AMIS Function:build Text: After CCBSWindow show");

        //String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
        //String query = "?=1&";
//        String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
        //http://127.0.0.1:8000/cbsmonths/src/main/webapp/index.html

        AMISServiceEntry.getInstance().getPopulationURL(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String url) {

//					String datasourceLabel = getDataSourceSelected().getLabel();
//					String datasourceCode = getDataSourceSelected().getCode();
//					String countryLabel = getCountrySelected().getLabel();
//					String countryCode = getCountrySelected().getCode();
                //String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
                //TEST: String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html"+query+"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
                ////getUsername()
                String query = "";
                if((getUsername()!=null)&&(getUsername().equals(CCBS.AMIS_SECRETARIAT)))
                {
                   // query = "?user_role="+getUsername()+"&app_name="+"AMIS_CBS_MONTHS";
                }
                else
                {
                    //Country
                    query = "?country="+CCBS.COUNTRY_CODES.get(0);
                }
                String iframe = "<iframe src=\""+url+query+"\" marginwidth=\"75px\" frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 868px;\">Your browser does not support iframes.</iframe>";
                // String query2 = "?"+CBSURLParameters.datasourceLabel+"="+datasourceLabel+"&"+CBSURLParameters.datasourceCode+"="+datasourceCode+"&"+CBSURLParameters.countryLabel+"="+countryLabel+"&"+CBSURLParameters.countryCode+"="+countryCode+"";
                //System.out.println(" query "+ query);
                System.out.println(" iframe "+ iframe);
                Html html = new Html(iframe);
                openedCbsTool = true;
                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                    RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));

//					ContentPanel panel = new ContentPanel();
//					panel.add(5 v)
                //html.setBorders(true);
                html.setStyleAttribute("background", "#FFFFFF");

                RootPanel.get("MAIN_CONTENT").add(html);
            }

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("Cbs get url Exception!!!!");
            }
        });

//		String iframe = "<iframe src=\"http://127.0.0.1:8080/cbsmonths/src/main/webapp/index.html\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
//
//		Html html = new Html(iframe);
//		openedCbsTool = true;
//			if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
//				RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
//
//		html.setStyleAttribute("background", "#FFFFFF");
//		RootPanel.get("MAIN_CONTENT").add(html);

    }

    private void buildMainPanelCCbsTool_annualTool(){
        //	System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected ppppp "+ getDataSourceSelected().getLabel());
        //	System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getDataSourceSelected "+ getDataSourceSelected().getCode());
        //	System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getLabel());
        //	System.out.println("Class: AMIS1 Function:build Text: Before CCBSWindow getCountrySelected "+ getCountrySelected().getCode());
//		String datasourceLabel = getDataSourceSelected().getLabel();
//		String datasourceCode = getDataSourceSelected().getCode();
//		String countryLabel = getCountrySelected().getLabel();
//		String countryCode = getCountrySelected().getCode();
//		CCBSWindow w = new CCBSWindow();
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow build");
//		w.build(this);
//		System.out.println("Class: AMIS Function:build Text: Before CCBSWindow show");
//		w.show();
//		System.out.println("Class: AMIS Function:build Text: After CCBSWindow show");

        //String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
        //String query = "?=1&";
//        String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
        //http://127.0.0.1:8000/cbsmonths/src/main/webapp/index.html
        //The url and the port are the same for Monthly Tool and Annual Tool
        AMISServiceEntry.getInstance().getCBSMONTHSURL(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String url) {

//					String datasourceLabel = getDataSourceSelected().getLabel();
//					String datasourceCode = getDataSourceSelected().getCode();
//					String countryLabel = getCountrySelected().getLabel();
//					String countryCode = getCountrySelected().getCode();
                //String query = "?datasourceLabel="+datasourceLabel+"&datasourceCode="+datasourceCode+"&countryLabel="+countryLabel+"&countryCode="+countryCode+"";
                //TEST: String iframe = "<iframe src=\"http://localhost:8080/cbs/data/index.html"+query+"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
                ////getUsername()
                String query = "";
                if((getUsername()!=null)&&(getUsername().equals(CCBS.AMIS_SECRETARIAT)))
                {
                    query = "?user_role="+getUsername()+"&app_name="+"AMIS_CBS_YEARS";
                }
                else
                {
                    //Country
                    query = "?user_role="+CCBS.COUNTRY_NAMES.get(0)+"&app_name="+"AMIS_CBS_YEARS";
                }
                String iframe = "<iframe src=\""+url+query+"\" marginwidth=\"75px\" frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 868px;\">Your browser does not support iframes.</iframe>";
                // String query2 = "?"+CBSURLParameters.datasourceLabel+"="+datasourceLabel+"&"+CBSURLParameters.datasourceCode+"="+datasourceCode+"&"+CBSURLParameters.countryLabel+"="+countryLabel+"&"+CBSURLParameters.countryCode+"="+countryCode+"";
                //System.out.println(" query "+ query);
                System.out.println(" iframe "+ iframe);
                Html html = new Html(iframe);
                openedCbsTool = true;
                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                    RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));

//					ContentPanel panel = new ContentPanel();
//					panel.add(5 v)
                //html.setBorders(true);
                html.setStyleAttribute("background", "#FFFFFF");

                RootPanel.get("MAIN_CONTENT").add(html);
            }

            @Override
            public void onFailure(Throwable arg0) {
                System.out.println("Cbs get url Exception!!!!");
            }
        });

//		String iframe = "<iframe src=\"http://127.0.0.1:8080/cbsmonths/src/main/webapp/index.html\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
//
//		Html html = new Html(iframe);
//		openedCbsTool = true;
//			if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
//				RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
//
//		html.setStyleAttribute("background", "#FFFFFF");
//		RootPanel.get("MAIN_CONTENT").add(html);

    }


    private void buildMainPanel(){
        //	System.out.println("Class: AmisInput Function: buildMainPanel4_3 Text:Start");

        //final Dialog complex = new Dialog();
        complex = new Window();
        complex.setBodyBorder(false);
        complex.setHeading("AMIS Data Entry Form");
        complex.setWidth(840);
        complex.setHeight(660);
        //complex.setHideOnButtonClick(true);
        BorderLayout layout = new BorderLayout();
        complex.setLayout(layout);

        // west
        ContentPanel panel = new ContentPanel();
        panel.setHeading("Parameters");
        panel.removeAll();
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST);
        //BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 215, 100, 315);
        //data.setMargins(new Margins(0, 5, 0, 0));

        data.setSplit(true);
        data.setCollapsible(true);
        createWestPanel(panel);
        complex.add(panel, data);
        BorderLayoutData data2 = new BorderLayoutData(LayoutRegion.SOUTH, 50, 20, 45);
        Button cancelButton = new Button("Cancel", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                complex.hide();
            }
        });
        //inputButton.setIconStyle("sendToExcel");
        cancelButton.setHeight(20);
        cancelButton.setWidth(60);

        Button saveButton = new Button("Save", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                complex.hide();
            }
        });
        //inputButton.setIconStyle("sendToExcel");
        saveButton.setHeight(20);
        saveButton.setWidth(60);
        FormPanel panelForButtons = new FormPanel();
        panelForButtons.setButtonAlign(HorizontalAlignment.RIGHT);
        panelForButtons.setHeaderVisible(false);
        HorizontalPanel hp = new HorizontalPanel();
        hp.setSpacing(7);
        hp.add(saveButton);
        hp.add(cancelButton);
        panelForButtons.add(hp);
        complex.add(panelForButtons, data2);

        // center
        panel = new ContentPanel();
        //panel.setHeading("Price Module");
        data = new BorderLayoutData(LayoutRegion.CENTER);
        panel.setWidth(40);
        panel.setHeight(700);
        //System.out.println("Class: AmisInput Function: buildMainPanel4_3 Text: 1");
        TabPanel panelTab = new TabPanel();
        panelTab.setBorders(false);
        itemMonthly = new TabItem("Monthly Values");
        itemMonthly.addStyleName("pad-text");
        itemMonthly.setHeight(590);
        itemMonthly.setWidth(20);


        //System.out.println("Class: AmisInput Function: buildMainPanel4_3 Text: 2");
        itemAnnual = new TabItem("Annual Values");
        itemAnnual.addStyleName("pad-text");
        itemAnnual.setHeight(590);
        itemAnnual.setWidth(20);

        createMonthlyContentTab();
        createAnnualContentTab();

        //System.out.println("Class: AmisInput Function: buildMainPanel4_3 Text: 3");
        panelTab.add(itemMonthly);
        panelTab.add(itemAnnual);
        //System.out.println("Class: AmisInput Function: buildMainPanel4_3 Text: 4");
        panel.add(panelTab, new FitData(4));

        complex.add(panel, data);
    }

    public ContentPanel buildInputButtons(){
        String admin;
//		if(FenixUser.hasAdminRole())
//				{
//					admin= "Admin";
//				}
//		else
//		{
//			admin= "Not Admin";
//		}

        ContentPanel p = new ContentPanel();
        p.setBodyBorder(false);
        p.setBorders(false);
        p.setHeaderVisible(false);
        //p.setStyleAttribute("padding", "10px");
        System.out.println("Class: AMIS Function:buildInputButtons Text: getUsername() "+ getUsername());
        //String introText = "The Commodity Balance Sheet data entry form is available as either an empty sheet or can be pre-poulated with FAO-CBS data as a guide.";
        String introText = "The Commodity Balance Sheet(CBS) data management forms allows the AMIS authorized users to enter and manage national databases in accordance with AMIS standards.";
        p.add(buildInputPanel("INPUT DATA ONLINE", introText, "INPUT"));
        p.add(buildInputPanel_monthlyTool("Open the MONTHLY CBS Data Management Form", "CBS_MONTHLY_TOOL"));
        p.add(buildInputPanel_annualTool("Open the ANNUAL CBS Data Management Form", "CBS_ANNUAL_TOOL"));
        p.add(buildInputPanel_populationIframe("Open the Population Data Management Form", "CBS_POPULATION_IFRAME"));
        //p.add(buildGo("CbsMonthsTool", "try"));
        //p.add(buildInputPanel("INPUT DATA ONLINE", "Start", introText, "INPUT", getUsername()));

        introText = "A Commodity Balance Sheet defined in a specifically formatted CSV/Excel file, can be uploaded together with its corresponding metadata file.";
        p.add(buildInputPanel("IMPORT DATA", "Import", introText, "UPLOAD", getUsername()));

        return p;

    }

    public VerticalPanel buildInputPanel(String title, String onClickString, String introduction, String id, String usernameValue){

        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        p.setStyleName("input-panel");

        p.add(buildHeader(title, id));
        HorizontalPanel hpGo = buildGo(onClickString, id);
        Html intro = new Html("<div class='input_panel_text'>"+introduction+"</div>");
        p.add(intro);
        if(title.equalsIgnoreCase("INPUT DATA ONLINE"))
        {
            //	String selectTextFirstPart = "Please select a data source to view/edit ";
            HorizontalPanel hpCountryAndDataSource;
            if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
            {
                //Case: Amis Secretariat
                //Countries combo and Datasource combo
                hpCountryAndDataSource = buildCountryAndDataSourceListBox(hpGo);
                selectFromComboBoxForAmisSecretariat = new Html();
                p.add(selectFromComboBoxForAmisSecretariat);
            }
            else
            {
                //Case Amis Country
                //Country label anf Datasource combo
                hpCountryAndDataSource = buildCountryLabelAndDataSourceListBox(hpGo);
                selectFromComboBox = new Html();
                p.add(selectFromComboBox);
            }

            //String selectTextCountry = getCountryLabel().getHtml();
            //	String selectTextSecondPart = " data.";
            //	selectFromComboBox = new Html("<div class='input_panel_text'>"+selectTextFirstPart+ "<b>" + selectTextCountry + "</b>" +selectTextSecondPart +"</div>");

            //p.add(buildCountryAndDataSourceListBox(hpGo));
            //p.add(hpCountryLabelAndDataSourceListBox);
            p.add(hpCountryAndDataSource);
        }
        p.add(hpGo);
        //p.setHorizontalAlign(HorizontalAlignment.CENTER);
        //p.setVerticalAlign(VerticalAlignment.MIDDLE);
        //System.out.println("Inputbutton Size  Start = ");


        inputButton = new Button(onClickString, new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                System.out.println("Input Button click!!!!!");
                //buildCloseCbsToolMessage();
                System.out.println("Before  buildMainPanelCCbsTool!!!!!");
                buildMainPanelCCbsTool();

//			buildMainPanel();
//			complex.show();
            }
        });
        //inputButton.setIconStyle("sendToExcel");
        inputButton.setHeight(40);
        inputButton.setWidth(150);
        inputButton.setIconStyle("inputData");
        inputButton.setIconAlign(IconAlign.RIGHT);
        add(inputButton);
        //p.add(inputButton);


        return p;
    }

    public VerticalPanel buildInputPanel(String title, String introduction, String id){

        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        //p.setStyleName("input-panel");
        p.add(buildHeader(title, id));
        Html intro = new Html("<div class='input_panel_text'>"+introduction+"</div>");
        p.add(intro);
        return p;
    }
    //This function is used by the cbs monthly tool
    public VerticalPanel buildInputPanel_monthlyTool(String onClickString, String id){
        //p.add(FormattingUtils.addHSpace(10));
        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        //p.setStyleName("input-panel");

        //p.add(buildHeader(title, id));
        HorizontalPanel hpGo = buildGo(onClickString, id);
        p.add(hpGo);
        return p;
    }

    //This function is used by the cbs annual tool
    public VerticalPanel buildInputPanel_annualTool(String onClickString, String id){
        //p.add(FormattingUtils.addHSpace(10));
        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        //p.setStyleName("input-panel");

        //p.add(buildHeader(title, id));
        HorizontalPanel hpGo = buildGo(onClickString, id);
        p.add(hpGo);
        return p;
    }

    //This function is used by the population iframe
    public VerticalPanel buildInputPanel_populationIframe(String onClickString, String id){
        //p.add(FormattingUtils.addHSpace(10));
        VerticalPanel p = new VerticalPanel();
        p.setSpacing(10);
        p.setStyleName("input-panel");

        //p.add(buildHeader(title, id));
        HorizontalPanel hpGo = buildGo(onClickString, id);
        p.add(hpGo);
        return p;
    }

//	public ClickHandler cbsCloseToolClick(/*final TextField<String> username, final TextField<String> password, final ComboBox<AMISCodesModelData> dataSourceListBox, final AMISInput amisInput*/) {
//		return new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent arg0) {
//
//				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
//				    public void handleEvent(MessageBoxEvent ce) {
//				       Button btn = ce.getButtonClicked();
//				       //Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());
//				       if((btn!=null)&&(btn.getText().equalsIgnoreCase("YES")))
//				       {
//				    	  // CCBS.CHANGES_IN_THE_GRID = false;
//				    	   //window.getWindow().hide();
//				    	   if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
//								RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
//							if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
//							{
//								for(int i=0; i<RootPanel.get("MAIN_CONTENT").getWidgetCount(); i++)
//									RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(i));
//							}
//							openedCbsTool = false;
////							getPanel().add(buildInputButtons());
//							AMISController.populateComboBox(AMISLoginPanel.getAmisUserParameters().getUsername(), loginRegisterPanel.getAmisInput());
//							RootPanel.get("MAIN_CONTENT").add(buildInputButtons());
//				       }
//				    }
//				    };
//			System.out.println("Close Cbs Tool Button pressed!!!");
//			MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);
//
//			}
//		};
//	}

    private Listener<ComponentEvent> cbsCloseToolClick(/*final TextField<String> username, final TextField<String> password, final ComboBox<AMISCodesModelData> dataSourceListBox, final AMISInput amisInput*/) {
        return new Listener<ComponentEvent>() {
            @Override
            public void handleEvent(ComponentEvent ce) {

//                final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
//                    public void handleEvent(MessageBoxEvent ce) {
//                        Button btn = ce.getButtonClicked();
//                        //Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());
//                        if((btn!=null)&&(btn.getText().equalsIgnoreCase("YES")))
//                        {
//                            // CCBS.CHANGES_IN_THE_GRID = false;
//                            //window.getWindow().hide();
//                            if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
//                                RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
//                            if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
//                            {
//                                for(int i=0; i<RootPanel.get("MAIN_CONTENT").getWidgetCount(); i++)
//                                    RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(i));
//                            }
//                            openedCbsTool = false;
////							getPanel().add(buildInputButtons());
//                            //Start
//                            //AMISLogInController.populateComboBox(AMISLoginPanel.getAmisUserParameters().getUsername(), loginRegisterPanel.getAmisInput());
//                            //End
//                            RootPanel.get("MAIN_CONTENT").add(buildInputButtons());
//                        }
//                    }
//                };

                if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                    RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                {
                    for(int i=0; i<RootPanel.get("MAIN_CONTENT").getWidgetCount(); i++)
                        RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(i));
                }
                openedCbsTool = false;
//							getPanel().add(buildInputButtons());
                //Start
                //AMISLogInController.populateComboBox(AMISLoginPanel.getAmisUserParameters().getUsername(), loginRegisterPanel.getAmisInput());
                //End
                RootPanel.get("MAIN_CONTENT").add(buildInputButtons());
                System.out.println("Close Cbs Tool Button pressed!!!");
                //MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);
                System.out.println("Before msg Confirm 2!! ");
            }
        };
    }

    private Listener<ComponentEvent> cbsCloseToolClick(final AMISInput amisInput) {
        return new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent ce) {
                System.out.println("Class: AMISInput Function:cbsCloseToolClick Text: Close Cbs Tool Button pressed!!! action = "+AMISInput.action);
                if((AMISInput.action!=null)&&(AMISInput.action.equals("CBS_POPULATION_IFRAME")))
                {
                    System.out.println("Close Cbs Tool Button pressed!!!");
                    if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                        RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                    if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                    {
                        for(int i=0; i<RootPanel.get("MAIN_CONTENT").getWidgetCount(); i++)
                            RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(i));
                    }
                    openedCbsTool = false;
//							getPanel().add(buildInputButtons());
                    //Start
                    //AMISLogInController.populateComboBox(AMISLoginPanel.getAmisUserParameters().getUsername(), amisInput);
                    //End
                    RootPanel.get("MAIN_CONTENT").add(buildInputButtons());
                    //  MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);
                }
                else{
                    final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
                        public void handleEvent(MessageBoxEvent ce) {
                            Button btn = ce.getButtonClicked();
                            //Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());
                            if((btn!=null)&&(btn.getText().equalsIgnoreCase("YES")))
                            {
                                // CCBS.CHANGES_IN_THE_GRID = false;
                                //window.getWindow().hide();
                                if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                                    RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                                if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
                                {
                                    for(int i=0; i<RootPanel.get("MAIN_CONTENT").getWidgetCount(); i++)
                                        RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(i));
                                }
                                openedCbsTool = false;
//							getPanel().add(buildInputButtons());
                                //Start
                                //AMISLogInController.populateComboBox(AMISLoginPanel.getAmisUserParameters().getUsername(), amisInput);
                                //End
                                RootPanel.get("MAIN_CONTENT").add(buildInputButtons());
                            }
                        }
                    };
//                    MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);
                    MessageBox.confirm("Confirm", "Are you sure you want to leave the CBS Tool?", l);
                }
            }
        };
    }


    public HorizontalPanel buildCloseCbsToolMessage(String action) {
        System.out.println("Class: AMISInput Function:buildCloseCbsToolMessage Text: Close Cbs Tool Button pressed!!!");
        HorizontalPanel vp = new HorizontalPanel();
        HorizontalPanel panel = new HorizontalPanel();
        panel.addStyleName("login_welcome_panel");
        panel.setStyleAttribute("backgroundColor", "#FFFFFF");
        //panel.setStyleAttribute("float", "right");
        panel.setSpacing(2);
        vp.setStyleAttribute("backgroundColor", "#FFFFFF");

        //this is used to close the tool
        AMISInput.action = action;
        //vp.setStyleAttribute("float", "right");
//		HTML html = new HTML();
//		html.setHTML("<div class='login_welcome'>" + text + "</div>");
//		panel.add(html);
//		HTML htmlcbsCloseTool = new HTML();
//		//htmlcbsCloseTool.setHTML("<div class= 'login_welcome link' style= 'cursor:pointer;'> Close Cbs Tool </div>");
//		htmlcbsCloseTool.setHTML("<div class= 'login_welcome cbslink' style= 'cursor:pointer;'> Close Cbs Tool </div>");
//		htmlcbsCloseTool.addClickHandler(cbsCloseToolClick());
//		htmlcbsCloseTool.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent arg0) {
//
//				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
//				    public void handleEvent(MessageBoxEvent ce) {
//				       Button btn = ce.getButtonClicked();
//				       //Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());
//				       if((btn!=null)&&(btn.getText().equalsIgnoreCase("YES")))
//				       {
//				    	  // CCBS.CHANGES_IN_THE_GRID = false;
//				    	   //window.getWindow().hide();
//				    	   if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
//								RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
//							if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
//							{
//								for(int i=0; i<RootPanel.get("MAIN_CONTENT").getWidgetCount(); i++)
//									RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(i));
//							}
//
////							getPanel().add(buildInputButtons());
//
//							RootPanel.get("MAIN_CONTENT").add(buildInputButtons());
//				       }
//				    }
//				    };
//			System.out.println("Close Cbs Tool Button pressed!!!");
//			MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);
//
//			//AMISInputController.doLogout();
//
////				RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
//			//RootPanel.get("CLOSE_CBS_TOOL").add(buildCloseCbsToolMessage());
//			}
//		});
        panel.add(FormattingUtils.addHSpace(5));
        //	panel.add(htmlcbsCloseTool);

        ClickHtml go = new ClickHtml();
        if((AMISInput.action!=null)&&(AMISInput.action.equals("CBS_POPULATION_IFRAME")))
        {
            go.setHtml(" Close Population Tool "+"<img src='amis-images/cross.gif' align=\"top\"> ");
        }
        else
        {
            go.setHtml(" Close Cbs Tool "+"<img src='amis-images/cross.gif' align=\"top\"> ");
        }

        //go.addStyleName("input_panel_go red");
        go.addStyleName("cbs_panel_go");
        go.addListener(Events.OnClick, cbsCloseToolClick(this));
        panel.add(go);

//		ClickHtml go = new ClickHtml();
//		go.setHtml("<img src='amis-images/start_arrows.png'> "+"Prova");
//		go.addStyleName("input_panel_go");
//		panel.add(go);
//		private HorizontalPanel buildGo(String title, String id) {
//			HorizontalPanel p = new HorizontalPanel();
//			p.addStyleName("input_panel_go");
//			ClickHtml go = new ClickHtml();
//			go.setHtml("<img src='amis-images/start_arrows.png'> "+title);
//			go.addStyleName("input_panel_go");
//			if(id.equals("INPUT"))
//			{
//				go.disable();
//			}
//			go.addListener(Events.OnClick, openInput(id));
//			p.add(go);
//			p.add(FormattingUtils.addHSpace(10));
//
//			return p;
//		}

        vp.setHorizontalAlign(HorizontalAlignment.CENTER);
        //panel.add(FormattingUtils.addHSpace(10));
        //vp.add(FormattingUtils.addVSpace(5));
        vp.setTableWidth("100%");
        vp.add(panel);
        return vp;
    }

    public HorizontalPanel buildInputUploadButtonPanel(){
        //System.out.println("Class: AmisInput Function: buildMainPanel4_3 Text:Start");

        HorizontalPanel p = new HorizontalPanel();
        p.setSpacing(100);
        p.setHorizontalAlign(HorizontalAlignment.CENTER);
        p.setVerticalAlign(VerticalAlignment.MIDDLE);
        //System.out.println("Inputbutton Size  Start = ");
        inputButton = new Button("Input Data Online", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                buildMainPanelCCbsTool();

//			buildMainPanel();
//			complex.show();
            }
        });
        //inputButton.setIconStyle("sendToExcel");
        inputButton.setHeight(40);
        inputButton.setWidth(150);
        inputButton.setIconStyle("inputData");
        inputButton.setIconAlign(IconAlign.RIGHT);
        add(inputButton);
        p.add(inputButton);

        //uploadButton = new Button("Import Data");
        uploadButton = new Button("Import Data", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                //System.out.println("Class: AmisInput Function: buildUploadMainPanel Text:Before call the function");
                //buildUploadMainPanel();
                //uploadComplex.show();
                FenixAlert.info("Under Construction", "This function is under construction and will be activated as soon as possible!", null);
            }
        });
        uploadButton.setIconStyle("inputData");
        uploadButton.setIconAlign(IconAlign.RIGHT);
        uploadButton.setHeight(40);
        uploadButton.setWidth(150);
        //uploadButton.addSelectionListener(uploadPanel(this));
        p.add(uploadButton);
        return p;
    }

    private void configPanel(final ContentPanel panel) {
        panel.setCollapsible(true);
        panel.setAnimCollapse(false);
        ListStore<Year> years = new ListStore<Year>();
        years.add(TestYear.getYear());
        ComboBox<Year> combo;
        for(int i=0; i<22;i++)
        {
            combo = new ComboBox<Year>();
            //combo.setEmptyText("Select an year...");
            combo.setDisplayField("year");
            combo.setWidth(70);
            combo.setStore(years);
            combo.setTypeAhead(true);
            combo.setTriggerAction(TriggerAction.ALL);
            panel.add(combo);
        }
    }

    void createWestPanel(ContentPanel vpTot)
    {
//		VerticalPanel vpTot = new VerticalPanel();
//		vpTot.setSpacing(0);

        VerticalPanel vp = new VerticalPanel();
        //vp.setHeight(20);
        vp.setSpacing(10);
        boolean element = false;
        String output= AMISConstants.CODING_SYSTEM.toString();
        String selectedDataset = AMISConstants.FAOSTAT.toString();

        Html country = new Html("Country");
        country.setWidth(150);
        country.setHeight(10);
        vp.add(country);

        ListStore<AMISCodesModelData> countries = new ListStore<AMISCodesModelData>();
        String typeOfOutput = AMISConstants.AMIS_COUNTRIES.toString();
        element = false;
        ComboBox<AMISCodesModelData> combo;
        combo = new ComboBox<AMISCodesModelData>();
        // combo.setEmptyText("Select an year...");
        combo.setDisplayField("label");
        combo.setWidth(150);
        combo.setStore(countries);
        combo.setTypeAhead(true);
        combo.setTriggerAction(TriggerAction.ALL);
        AMISInputController.getElementsForComboBox(countries, output, typeOfOutput, selectedDataset, element, combo);
        //System.out.println("After AMISInputController.getCountry()");
        //System.out.println("countries in createWestPanel country "+ countries);
//		System.out.println("countries in createWestPanel size = "+ countries.getCount());
//	    if(countries.getCount()>0)
//	    {
//	    	combo.setValue(countries.getAt(0));
//	    }
        vp.add(combo);
        vpTot.add(vp);

        vp = new VerticalPanel();
        vp.setSpacing(10);
        Html product = new Html("Commodity");
        product.setWidth(150);
        product.setHeight(10);
        vp.add(product);
        ListStore<AMISCodesModelData> products = new ListStore<AMISCodesModelData>();
        typeOfOutput = AMISConstants.AMIS_PRODUCTS.toString();
        element = false;
        ComboBox<AMISCodesModelData> combo2;
        combo2 = new ComboBox<AMISCodesModelData>();
        // combo.setEmptyText("Select an year...");
        combo2.setDisplayField("label");
        combo2.setWidth(150);
        combo2.setStore(products);
        combo2.setTypeAhead(true);
        combo2.setTriggerAction(TriggerAction.ALL);
        AMISInputController.getElementsForComboBox(products, output, typeOfOutput, selectedDataset, element, combo2);

//	     if(products.getCount()>0)
//	    {
//	    	combo2.setValue(products.getAt(0));
//	    }
        //hp.add(combo2);
        vp.add(combo2);
        vpTot.add(vp);

        vp = new VerticalPanel();
        vp.setSpacing(10);
        Html typeCode = new Html("Element");
        typeCode.setWidth(150);
        typeCode.setHeight(10);
        vp.add(typeCode);
        ListStore<AMISCodesModelData> elements = new ListStore<AMISCodesModelData>();
        typeOfOutput = AMISConstants.AMIS_ELEMENTS.toString();
        ComboBox<AMISCodesModelData> combo3;
        combo3 = new ComboBox<AMISCodesModelData>();
        combo3.setDisplayField("label");
        combo3.setWidth(150);
        combo3.setStore(elements);
        combo3.setTypeAhead(true);
        combo3.setTriggerAction(TriggerAction.ALL);
        element = true;
        AMISInputController.getElementsForComboBox(elements, output, typeOfOutput, selectedDataset, element, combo3);
        // combo.setEmptyText("Select an year...");
//	    if(elements.getCount()>0)
//	    {
//	    	combo3.setValue(elements.getAt(0));
//	    }
        //   hp.add(combo3);
        vp.add(combo3);
        vpTot.add(vp);

        vp = new VerticalPanel();
        vp.setSpacing(10);
        Html unitsCode = new Html("Measurement Unit");
        unitsCode.setWidth(150);
        unitsCode.setHeight(10);
        vp.add(unitsCode);
        ListStore<MeasurementUnit> measurementUnit = new ListStore<MeasurementUnit>();
        measurementUnit.add(TestMeasurementUnit.getMeasurementUnit());
        ComboBox<MeasurementUnit> combo4;
        combo4 = new ComboBox<MeasurementUnit>();
        // combo.setEmptyText("Select an year...");
        combo4.setDisplayField("measurementUnit");
        combo4.setWidth(150);
        combo4.setStore(measurementUnit);
        combo4.setTypeAhead(true);
        combo4.setTriggerAction(TriggerAction.ALL);
        if(measurementUnit.getCount()>0)
        {
            combo4.setValue(measurementUnit.getAt(0));
        }
        vp.add(combo4);
        vpTot.add(vp);

        vp = new VerticalPanel();
        vp.setSpacing(10);
        Html dataSourceCode = new Html("Data Source");
        dataSourceCode.setWidth(150);
        dataSourceCode.setHeight(10);
        vp.add(dataSourceCode);
        ListStore<DataSourceString> dataSource = new ListStore<DataSourceString>();
        dataSource.add(TestDataSourceString.getDataSourceString());
        ComboBox<DataSourceString> combo5;
        combo5 = new ComboBox<DataSourceString>();
        // combo.setEmptyText("Select an year...");
        combo5.setDisplayField("data");
        combo5.setWidth(150);
        combo5.setStore(dataSource);
        combo5.setTypeAhead(true);
        combo5.setTriggerAction(TriggerAction.ALL);
        combo5.setValue(dataSource.getAt(0));
        if(dataSource.getCount()>0)
        {
            combo5.setValue(dataSource.getAt(0));
        }
        vp.add(combo5);
        vpTot.add(vp);

        vp = new VerticalPanel();
        vp.setSpacing(10);
        HorizontalPanel hpTop = new HorizontalPanel();
        hpTop.setSpacing(5);
        Html startYears = new Html("Start Year");
        startYears.setWidth(70);
        startYears.setHeight(10);
        hpTop.add(startYears);
        Html endYears = new Html("End Year");
        endYears.setWidth(70);
        endYears.setHeight(10);
        hpTop.add(endYears);

        vp.add(hpTop);
        HorizontalPanel hpForYears = new HorizontalPanel();
        hpForYears.setSpacing(5);
        ListStore<Year> years5 = new ListStore<Year>();
        years5.add(TestYear.getYear());
        comboStartYear = new ComboBox<Year>();
        // combo.setEmptyText("Select an year...");
        comboStartYear.setDisplayField("year");
        comboStartYear.setWidth(70);
        comboStartYear.setStore(years5);
        comboStartYear.setTypeAhead(true);
        comboStartYear.setTriggerAction(TriggerAction.ALL);
//	    if(years5.getCount()>0)
//		{
//	    	comboStartYear.setValue(years5.getAt(0));
//		}
        // System.out.println("Before selectionChangedListener");
        comboStartYear.addSelectionChangedListener(selectionChangedListener);
        hpForYears.add(comboStartYear);
        // System.out.println("After selectionChangedListener");
        endYearsStore = new ListStore<Year>();
        comboEndYear = new ComboBox<Year>();
        // combo.setEmptyText("Select an year...");
        comboEndYear.setDisplayField("year");
        comboEndYear.setWidth(70);
        comboEndYear.setStore(endYearsStore);
        comboEndYear.setTypeAhead(true);
        comboEndYear.setTriggerAction(TriggerAction.ALL);
        comboEndYear.addSelectionChangedListener(selectionChangedListener2);
        hpForYears.add(comboEndYear);
        //  combo6.addListener(eventType, listener)
        vp.add(hpForYears);

        HorizontalPanel hpCheckbox = new HorizontalPanel();
        hpCheckbox.setSpacing(5);
        Html calendarYear = new Html("Calendar Year");
        calendarYear.setWidth(90);
        calendarYear.setHeight(10);
        //hpTop.add(unitsCode);
        hpCheckbox.add(calendarYear);

        calendarCb = new CheckBox();
        calendarCb.setEmptyText("Calendar Year");
        calendarCb.setValue(false);
        calendarCb.addListener(Events.Change, changeCalendarCheckBox());
        hpCheckbox.add(calendarCb);
        vp.add(hpCheckbox);

        hpCheckbox = new HorizontalPanel();
        hpCheckbox.setSpacing(5);
        Html marketingYear = new Html("Marketing Year");
        marketingYear.setWidth(90);
        marketingYear.setHeight(10);
        //hpTop.add(unitsCode);
        hpCheckbox.add(marketingYear);

        marketingCb = new CheckBox();
        marketingCb.setEmptyText("Marketing Year");
        marketingCb.setValue(false);
        marketingCb.addListener(Events.Change, changeMarketingCheckBox(vpTot));
        //cb.ad.addSelectionChangedListener(selectionChangedListener);
        hpCheckbox.add(marketingCb);
        vp.add(hpCheckbox);
        vpTot.add(vp);
    }

    private Listener<FieldEvent> changeCalendarCheckBox() {
        return new Listener<FieldEvent>() {
            public void handleEvent(FieldEvent be) {

                if(calendarCb.getValue())
                {
                    //System.out.println("calendarCb Show");
                    typeOfYear = "Calendar Year";
                    marketingCb.setValue(false);
                    oneTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    twoTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    threeTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    fourTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    fiveTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    sixTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    sevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    eightTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    nineTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    tenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    elevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    // oneTypeOfYear.setEmptyText(typeOfYear);
//					 twoTypeOfYear.setEmptyText(typeOfYear);
//					 threeTypeOfYear.setEmptyText(typeOfYear);
//					 fourTypeOfYear.setEmptyText(typeOfYear);
//					 fiveTypeOfYear.setEmptyText(typeOfYear);
//					 sixTypeOfYear.setEmptyText(typeOfYear);
//					 sevenTypeOfYear.setEmptyText(typeOfYear);
//					 eightTypeOfYear.setEmptyText(typeOfYear);
//					 nineTypeOfYear.setEmptyText(typeOfYear);
//					 tenTypeOfYear.setEmptyText(typeOfYear);
//					 elevenTypeOfYear.setEmptyText(typeOfYear);
                }
                else
                {

                    if(!marketingCb.getValue())
                    {
                        typeOfYear = "";
                        //calendarCb.setValue(false);
                        oneTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        twoTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        threeTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        fourTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        fiveTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        sixTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        sevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        eightTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        nineTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        tenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        elevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        // oneTypeOfYear.setEmptyText(typeOfYear);
//					 twoTypeOfYear.setEmptyText(typeOfYear);
//					 threeTypeOfYear.setEmptyText(typeOfYear);
//					 fourTypeOfYear.setEmptyText(typeOfYear);
//					 fiveTypeOfYear.setEmptyText(typeOfYear);
//					 sixTypeOfYear.setEmptyText(typeOfYear);
//					 sevenTypeOfYear.setEmptyText(typeOfYear);
//					 eightTypeOfYear.setEmptyText(typeOfYear);
//					 nineTypeOfYear.setEmptyText(typeOfYear);
//					 tenTypeOfYear.setEmptyText(typeOfYear);
//					 elevenTypeOfYear.setEmptyText(typeOfYear);
                    }
                }
            }
        };
    }


    private Listener<FieldEvent> changeMarketingCheckBox(final ContentPanel vp) {
        return new Listener<FieldEvent>() {
            public void handleEvent(FieldEvent be) {
                if(marketingCb.getValue())
                {
                    typeOfYear = "Marketing Year";
                    calendarCb.setValue(false);
                    oneTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    twoTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    threeTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    fourTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    fiveTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    sixTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    sevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    eightTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    nineTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    tenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    elevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                    // oneTypeOfYear.setEmptyText(typeOfYear);
//					 twoTypeOfYear.setEmptyText(typeOfYear);
//					 threeTypeOfYear.setEmptyText(typeOfYear);
//					 fourTypeOfYear.setEmptyText(typeOfYear);
//					 fiveTypeOfYear.setEmptyText(typeOfYear);
//					 sixTypeOfYear.setEmptyText(typeOfYear);
//					 sevenTypeOfYear.setEmptyText(typeOfYear);
//					 eightTypeOfYear.setEmptyText(typeOfYear);
//					 nineTypeOfYear.setEmptyText(typeOfYear);
//					 tenTypeOfYear.setEmptyText(typeOfYear);
//					 elevenTypeOfYear.setEmptyText(typeOfYear);
                    //System.out.println("marketingCb Show");
                    fieldSet = new FieldSet();
                    fieldSet.setHeading("Marketing Year");
                    fieldSet.setWidth(180);
                    fieldSet.setCheckboxToggle(true);

                    FormLayout layout = new FormLayout();
                    layout.setLabelWidth(35);
                    fieldSet.setLayout(layout);

                    HorizontalPanel hpMarketing = new HorizontalPanel();
                    hpMarketing.setSpacing(3);
                    HorizontalPanel hpMarketingFrom = new HorizontalPanel();
                    Html fromMonth = new Html("From");
                    fromMonth.setWidth(35);
                    fromMonth.setHeight(10);
                    hpMarketingFrom.add(fromMonth);

                    ListStore<Month> months = new ListStore<Month>();
                    months.add(TestMonth.getShortMonth());
                    comboMonth = new ComboBox<Month>();
                    // combo.setEmptyText("Select an year...");
                    comboMonth.setDisplayField("month");
                    comboMonth.setWidth(55);
                    comboMonth.setStore(months);
                    comboMonth.setTypeAhead(true);
                    comboMonth.setTriggerAction(TriggerAction.ALL);
                    hpMarketingFrom.add(comboMonth);
                    comboMonth.addSelectionChangedListener(selectionMarketingChangedListener);

                    hpMarketing.add(hpMarketingFrom);
                    fieldSet.add(hpMarketing);

                    HorizontalPanel hpMarketingTo = new HorizontalPanel();
                    Html toMonth = new Html("To");
                    toMonth.setWidth(20);
                    toMonth.setHeight(10);
                    hpMarketingTo.add(toMonth);

                    to = new TextField<String>();
                    to.setFieldLabel("To");
                    to.setWidth(40);
                    to.setEnabled(false);
                    hpMarketingTo.add(to);
                    hpMarketing.add(hpMarketingTo);
                    fieldSet.add(hpMarketing);
                    vp.add(fieldSet);
                    vp.getLayout().layout();
                }
                else
                {
                    if(!calendarCb.getValue())
                    {
                        typeOfYear = "";
                        //System.out.println("marketingCb Hide");
                        oneTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        twoTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        threeTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        fourTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        fiveTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        sixTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        sevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        eightTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        nineTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        tenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        elevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
                        // oneTypeOfYear.setEmptyText(typeOfYear);
//					 twoTypeOfYear.setEmptyText(typeOfYear);
//					 threeTypeOfYear.setEmptyText(typeOfYear);
//					 fourTypeOfYear.setEmptyText(typeOfYear);
//					 fiveTypeOfYear.setEmptyText(typeOfYear);
//					 sixTypeOfYear.setEmptyText(typeOfYear);
//					 sevenTypeOfYear.setEmptyText(typeOfYear);
//					 eightTypeOfYear.setEmptyText(typeOfYear);
//					 nineTypeOfYear.setEmptyText(typeOfYear);
//					 tenTypeOfYear.setEmptyText(typeOfYear);
//					 elevenTypeOfYear.setEmptyText(typeOfYear);
                    }
                    fieldSet.hide();
                }
            }
        };
    }

    SelectionChangedListener<Year> selectionChangedListener = new SelectionChangedListener<Year>() {
        @Override
        public void selectionChanged(SelectionChangedEvent<Year> se) {
            if (se.getSelection().size() > 0)
            {
                //System.out.println("selectionChangedListener!!!!!!!!!");
                endYearsStore.removeAll();
                comboEndYear.setStore(endYearsStore);
                ListStore<Year> yearComboStart = comboStartYear.getStore();
                //System.out.println("comboStartYear.getValue() "+comboStartYear.getValue().get("year"));
                startYearSelected = comboStartYear.getValue().get("year");
                int yearComboStartSize = yearComboStart.getCount();
                for(int i=0; i<yearComboStart.getCount(); i++)
                {
                    String iYear = yearComboStart.getAt(i).get("year");
                    if(iYear.equals(startYearSelected))
                    {
                        i++;
                        //Loop on the rows to visualizeYears
                        int j=0;
                        while(i<yearComboStartSize)
                        {
                            if(j<10)
                            {
                                endYearsStore.add(new Year(yearComboStart.getAt(i).getYear()));
                            }
                            j++;
                            i++;
                        }
                        comboEndYear.setStore(endYearsStore);
                    }
                }
            }
        }
    };

    SelectionChangedListener<Year> selectionChangedListener2 = new SelectionChangedListener<Year>() {
        @Override
        public void selectionChanged(SelectionChangedEvent<Year> se) {
            if (se.getSelection().size() > 0)
            {
                //yearOneForMonth.setEmptyText("");
                //yearOneForMonth.setValue("");
                yearOneForMonth.setHtml("<b>" + "" + " </b>");
                yearTwoForMonth.setHtml("<b>" + "" + " </b>");
                yearThreeForMonth.setHtml("<b>" + "" + " </b>");
                yearFourForMonth.setHtml("<b>" + "" + " </b>");
                yearFiveForMonth.setHtml("<b>" + "" + " </b>");
                yearSixForMonth.setHtml("<b>" + "" + " </b>");
                yearSevenForMonth.setHtml("<b>" + "" + " </b>");
                yearEightForMonth.setHtml("<b>" + "" + " </b>");
                yearNineForMonth.setHtml("<b>" + "" + " </b>");
                yearTenForMonth.setHtml("<b>" + "" + " </b>");
                yearElevenForMonth.setHtml("<b>" + "" + " </b>");

//					yearTwoForMonth.setEmptyText("");
//					yearThreeForMonth.setEmptyText("");
//					yearFourForMonth.setEmptyText("");
//					yearFiveForMonth.setEmptyText("");
//					yearSixForMonth.setEmptyText("");
//					yearSevenForMonth.setEmptyText("");
//					yearEightForMonth.setEmptyText("");
//					yearNineForMonth.setEmptyText("");
//					yearTenForMonth.setEmptyText("");
//					yearElevenForMonth.setEmptyText("");

                yearOneForYear.setHtml("<b>" + "" + " </b>");
                yearTwoForYear.setHtml("<b>" + "" + " </b>");
                yearThreeForYear.setHtml("<b>" + "" + " </b>");
                yearFourForYear.setHtml("<b>" + "" + " </b>");
                yearFiveForYear.setHtml("<b>" + "" + " </b>");
                yearSixForYear.setHtml("<b>" + "" + " </b>");
                yearSevenForYear.setHtml("<b>" + "" + " </b>");
                yearEightForYear.setHtml("<b>" + "" + " </b>");
                yearNineForYear.setHtml("<b>" + "" + " </b>");
                yearTenForYear.setHtml("<b>" + "" + " </b>");
                yearElevenForYear.setHtml("<b>" + "" + " </b>");
//					yearOneForYear.setEmptyText("");
//					yearTwoForYear.setEmptyText("");
//					yearThreeForYear.setEmptyText("");
//					yearFourForYear.setEmptyText("");
//					yearFiveForYear.setEmptyText("");
//					yearSixForYear.setEmptyText("");
//					yearSevenForYear.setEmptyText("");
//					yearEightForYear.setEmptyText("");
//					yearNineForYear.setEmptyText("");
//					yearTenForYear.setEmptyText("");
//					yearElevenForYear.setEmptyText("");

                String start = startYearSelected;
                Year queryFormModel = comboEndYear.getValue();
                String endYearSelected = queryFormModel.get("year");

                int iStartYear= Integer.parseInt(startYearSelected);
                //	System.out.println("iStartYear = "+iStartYear);
                int iEndYear= Integer.parseInt(endYearSelected);
                //	System.out.println("iEndYear = "+iEndYear);
                int i=0;
                for(int iYear=iStartYear; iYear<=iEndYear; iYear++)
                {
                    //System.out.println("iYear = "+iYear);
                    String name = yearForMonthString[i];
                    //System.out.println("Name = "+name);
                    name = "year"+name+"ForMonth";
                    if(name.equalsIgnoreCase("yearOneForMonth"))
                    {
                        //System.out.println("One = ");
                        //yearOneForMonth.setEmptyText(""+iYear);
                        //yearOneForMonth.setValue(""+iYear);
                        yearOneForMonth.setHtml("<b>" + iYear + " </b>");
                        yearOneForYear.setHtml("<b>" + iYear + " </b>");
                        //yearOneForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearTwoForMonth"))
                    {
                        //System.out.println("Two = ");
                        yearTwoForMonth.setHtml("<b>" + iYear + " </b>");
                        yearTwoForYear.setHtml("<b>" + iYear + " </b>");
                        //yearTwoForMonth.setEmptyText(""+iYear);
                        //yearTwoForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearThreeForMonth"))
                    {
                        //System.out.println("Three = ");
                        yearThreeForMonth.setHtml("<b>" + iYear + " </b>");
                        yearThreeForYear.setHtml("<b>" + iYear + " </b>");
//							yearThreeForMonth.setEmptyText(""+iYear);
//							yearThreeForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearFourForMonth"))
                    {
                        //System.out.println("Four = ");
                        yearFourForMonth.setHtml("<b>" + iYear + " </b>");
                        yearFourForYear.setHtml("<b>" + iYear + " </b>");
                        //yearFourForMonth.setEmptyText(""+iYear);
                        //yearFourForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearFiveForMonth"))
                    {
                        //System.out.println("Two = ");
                        yearFiveForMonth.setHtml("<b>" + iYear + " </b>");
                        yearFiveForYear.setHtml("<b>" + iYear + " </b>");
                        //yearFiveForMonth.setEmptyText(""+iYear);
                        //yearFiveForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearSixForMonth"))
                    {
                        //System.out.println("Three = ");
                        yearSixForMonth.setHtml("<b>" + iYear + " </b>");
                        yearSixForYear.setHtml("<b>" + iYear + " </b>");
                        //yearSixForMonth.setEmptyText(""+iYear);
                        //yearSixForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearSevenForMonth"))
                    {
                        //System.out.println("Four = ");
                        yearSevenForMonth.setHtml("<b>" + iYear + " </b>");
                        yearSevenForYear.setHtml("<b>" + iYear + " </b>");
//							yearSevenForMonth.setEmptyText(""+iYear);
//							yearSevenForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearEightForMonth"))
                    {
                        //System.out.println("Four = ");
                        yearEightForMonth.setHtml("<b>" + iYear + " </b>");
                        yearEightForYear.setHtml("<b>" + iYear + " </b>");
//							yearEightForMonth.setEmptyText(""+iYear);
//							yearEightForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearNineForMonth"))
                    {
                        //System.out.println("Two = ");
                        yearNineForMonth.setHtml("<b>" + iYear + " </b>");
                        yearNineForYear.setHtml("<b>" + iYear + " </b>");
                        //yearNineForMonth.setEmptyText(""+iYear);
                        //yearNineForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearTenForMonth"))
                    {
                        //System.out.println("Three = ");
                        yearTenForMonth.setHtml("<b>" + iYear + " </b>");
                        yearTenForYear.setHtml("<b>" + iYear + " </b>");
                        //yearTenForMonth.setEmptyText(""+iYear);
                        //yearTenForYear.setEmptyText(""+iYear);
                    }
                    else if(name.equalsIgnoreCase("yearElevenForMonth"))
                    {
                        //System.out.println("Four = ");
                        yearElevenForMonth.setHtml("<b>" + iYear + " </b>");
                        yearElevenForYear.setHtml("<b>" + iYear + " </b>");
//							yearElevenForMonth.setEmptyText(""+iYear);
//							yearElevenForYear.setEmptyText(""+iYear);
                    }
                    i++;
                }
            }
        }
    };

    SelectionChangedListener<Month> selectionMarketingChangedListener = new SelectionChangedListener<Month>() {
        @Override
        public void selectionChanged(SelectionChangedEvent<Month> se) {
            if (se.getSelection().size() > 0)
            {
                String month = comboMonth.getValue().getMonth();
                String oneMonthBefore = "";
                if(month.equalsIgnoreCase("Jan"))
                {
                    oneMonthBefore = "Dec";
                }
                else if(month.equalsIgnoreCase("Feb"))
                {
                    oneMonthBefore = "Jan";
                }
                else if(month.equalsIgnoreCase("Mar"))
                {
                    oneMonthBefore = "Feb";
                }
                else if(month.equalsIgnoreCase("Apr"))
                {
                    oneMonthBefore = "Mar";
                }
                else if(month.equalsIgnoreCase("May"))
                {
                    oneMonthBefore = "Apr";
                }
                else if(month.equalsIgnoreCase("Jun"))
                {
                    oneMonthBefore = "May";
                }
                else if(month.equalsIgnoreCase("Jul"))
                {
                    oneMonthBefore = "Jun";
                }
                else if(month.equalsIgnoreCase("Aug"))
                {
                    oneMonthBefore = "Jul";
                }
                else if(month.equalsIgnoreCase("Sep"))
                {
                    oneMonthBefore = "Aug";
                }
                else if(month.equalsIgnoreCase("Oct"))
                {
                    oneMonthBefore = "Sep";
                }
                else if(month.equalsIgnoreCase("Nov"))
                {
                    oneMonthBefore = "Oct";
                }
                else if(month.equalsIgnoreCase("Dec"))
                {
                    oneMonthBefore = "Nov";
                }
                to.setEmptyText(oneMonthBefore);
            }
        }
    };


    void createMonthlyContentTab()
    {
        VerticalPanel vp = new VerticalPanel();
        vp.setSpacing(4);
        formData = new FormData("-20");
        HorizontalPanel hpTop = new HorizontalPanel();
        HorizontalPanel hp = null;
        hpTop.setSpacing(4);

        Html yearsTest = new Html("Year");
        yearsTest.setWidth(70);
        hpTop.add(yearsTest);
        yearsTest = new Html("Jan");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Feb");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Mar");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Apr");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("May");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Jun");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Jul");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Aug");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Sep");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Oct");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Nov");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        yearsTest = new Html("Dec");
        yearsTest.setWidth(40);
        hpTop.add(yearsTest);
        vp.add(hpTop);
        //System.out.println("Before yearsCombo");

//		createSingleHorizontalPanel(yearOneForMonth, hp, vp);
//		createSingleHorizontalPanel(yearTwoForMonth, hp, vp);
//		createSingleHorizontalPanel(yearThreeForMonth, hp, vp);
//		createSingleHorizontalPanel(yearFourForMonth, hp, vp);
//		createSingleHorizontalPanel(yearFiveForMonth, hp, vp);
//		createSingleHorizontalPanel(yearSixForMonth, hp, vp);
//		createSingleHorizontalPanel(yearSevenForMonth, hp, vp);
//		createSingleHorizontalPanel(yearEightForMonth, hp, vp);
//		createSingleHorizontalPanel(yearNineForMonth, hp, vp);
//		createSingleHorizontalPanel(yearTenForMonth, hp, vp);
//		createSingleHorizontalPanel(yearElevenForMonth, hp, vp);
        yearForMonthString = new String[11];
        yearForMonthString[0] = "One";
        yearForMonthString[1] = "Two";
        yearForMonthString[2] = "Three";
        yearForMonthString[3] = "Four";
        yearForMonthString[4] = "Five";
        yearForMonthString[5] = "Six";
        yearForMonthString[6] = "Seven";
        yearForMonthString[7] = "Eight";
        yearForMonthString[8] = "Nine";
        yearForMonthString[9] = "Ten";
        yearForMonthString[10] = "Eleven";

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        // yearOneForMonth = new TextField<String>();
        // yearOneForMonth = new LabelField();
        yearOneForMonth = new Html();
        yearOneForMonth.setWidth(70);
        hp.add(yearOneForMonth);
        //label.setWidth(100);
        //yearOneForMonth.setFieldLabel("");
        // yearOneForMonth.setEnabled(false);


        NumberField month;
        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
//	    yearTwoForMonth = new TextField<String>();
//	    yearTwoForMonth.setWidth(70);
//	    yearTwoForMonth.setEnabled(false);
        yearTwoForMonth = new Html();
        yearTwoForMonth.setWidth(70);
        // hp.add(yearOneForMonth);
        hp.add(yearTwoForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearThreeForMonth = new Html();
        yearThreeForMonth.setWidth(70);
        hp.add(yearThreeForMonth);
//	    yearThreeForMonth = new TextField<String>();
//	    yearThreeForMonth.setWidth(70);
//	    yearThreeForMonth.setEnabled(false);
//	    hp.add(yearThreeForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearFourForMonth = new Html();
        yearFourForMonth.setWidth(70);
        hp.add(yearFourForMonth);
//	    yearFourForMonth = new TextField<String>();
//	    yearFourForMonth.setWidth(70);
//	    yearFourForMonth.setEnabled(false);
//	    hp.add(yearFourForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearFiveForMonth = new Html();
        yearFiveForMonth.setWidth(70);
        hp.add(yearFiveForMonth);
//	    yearFiveForMonth = new TextField<String>();
//	    yearFiveForMonth.setWidth(70);
//	    yearFiveForMonth.setEnabled(false);
//	    hp.add(yearFiveForMonth);
//
        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearSixForMonth = new Html();
        yearSixForMonth.setWidth(70);
        hp.add(yearSixForMonth);
//	    yearSixForMonth = new TextField<String>();
//	    yearSixForMonth.setWidth(70);
//	    yearSixForMonth.setEnabled(false);
//	    hp.add(yearSixForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearSevenForMonth = new Html();
        yearSevenForMonth.setWidth(70);
        hp.add(yearSevenForMonth);
//	    yearSevenForMonth = new TextField<String>();
//	    yearSevenForMonth.setWidth(70);
//	    yearSevenForMonth.setEnabled(false);
//	    hp.add(yearSevenForMonth);


        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearEightForMonth = new Html();
        yearEightForMonth.setWidth(70);
        hp.add(yearEightForMonth);
//	    yearEightForMonth = new TextField<String>();
//	    yearEightForMonth.setWidth(70);
//	    yearEightForMonth.setEnabled(false);
//	    hp.add(yearEightForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearNineForMonth = new Html();
        yearNineForMonth.setWidth(70);
        hp.add(yearNineForMonth);
//	    yearNineForMonth = new TextField<String>();
//	    yearNineForMonth.setWidth(70);
//	    yearNineForMonth.setEnabled(false);
//	    hp.add(yearNineForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearTenForMonth = new Html();
        yearTenForMonth.setWidth(70);
        hp.add(yearTenForMonth);
//	    yearTenForMonth = new TextField<String>();
//	    yearTenForMonth.setWidth(70);
//	    yearTenForMonth.setEnabled(false);
//	    hp.add(yearTenForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
//	    yearElevenForMonth = new TextField<String>();
//	    yearElevenForMonth.setWidth(70);
//	    yearElevenForMonth.setEnabled(false);
//	    hp.add(yearElevenForMonth);
        yearElevenForMonth = new Html();
        yearElevenForMonth.setWidth(70);
        hp.add(yearElevenForMonth);

        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);
        itemMonthly.add(vp);
    }

    VerticalPanel createSingleHorizontalPanel(TextField<String> yearForMonth, HorizontalPanel hp, VerticalPanel vp)
    {
        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearForMonth = new TextField<String>();
        yearForMonth.setWidth(70);
        hp.add(yearForMonth);

        NumberField month;
        for(int j=0; j< 12; j++)
        {
            month = new NumberField();
            month.setFieldLabel("Mounth");
            month.setWidth(40);
            hp.add(month);
        }
        vp.add(hp);
        return vp;
    }


    void createAnnualContentTab()
    {
        VerticalPanel vp = new VerticalPanel();
        vp.setSpacing(4);
        //FormPanel fp = new FormPanel();
        formData = new FormData("-20");
        HorizontalPanel hpTop = new HorizontalPanel();
        HorizontalPanel hp;
        hpTop.setSpacing(4);

        Html yearsTest = new Html("Year");
        yearsTest.setWidth(70);
        hpTop.add(yearsTest);
        yearsTest = new Html("Type Of Year");
        yearsTest.setWidth(130);
        hpTop.add(yearsTest);
        hpTop.add(yearsTest);
        yearsTest = new Html("Value");
        yearsTest.setWidth(70);
        hpTop.add(yearsTest);
        vp.add(hpTop);
        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearOneForYear = new Html();
        yearOneForYear.setWidth(70);
        hp.add(yearOneForYear);
//	    yearOneForYear = new TextField<String>();
//	    yearOneForYear.setWidth(70);
//	    yearOneForYear.setEnabled(false);
//	    hp.add(yearOneForYear);

        //  oneTypeOfYear = new TextField<String>();
        oneTypeOfYear = new Html();
        oneTypeOfYear.setWidth(130);
        hp.add(oneTypeOfYear);
        // oneTypeOfYear.setEnabled(false);

        NumberField price;
        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearTwoForYear = new Html();
        yearTwoForYear.setWidth(70);
        hp.add(yearTwoForYear);
//	    yearTwoForYear = new TextField<String>();
//	    yearTwoForYear.setWidth(70);
//	    yearTwoForYear.setEnabled(false);
//	    hp.add(yearTwoForYear);

//	    twoTypeOfYear = new TextField<String>();
//	    twoTypeOfYear.setWidth(130);
//	    twoTypeOfYear.setEnabled(false);
//	    hp.add(twoTypeOfYear);
        twoTypeOfYear = new Html();
        twoTypeOfYear.setWidth(130);
        hp.add(twoTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearThreeForYear = new Html();
        yearThreeForYear.setWidth(70);
        hp.add(yearThreeForYear);
//	    yearThreeForYear = new TextField<String>();
//	    yearThreeForYear.setWidth(70);
//	    yearThreeForYear.setEnabled(false);
//	    hp.add(yearThreeForYear);

//	    threeTypeOfYear = new TextField<String>();
//	    threeTypeOfYear.setWidth(130);
//	    threeTypeOfYear.setEnabled(false);
//	    hp.add(threeTypeOfYear);
        threeTypeOfYear = new Html();
        threeTypeOfYear.setWidth(130);
        hp.add(threeTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearFourForYear = new Html();
        yearFourForYear.setWidth(70);
        hp.add(yearFourForYear);
//	    yearFourForYear = new TextField<String>();
//	    yearFourForYear.setWidth(70);
//	    yearFourForYear.setEnabled(false);
//	    hp.add(yearFourForYear);

//	    fourTypeOfYear = new TextField<String>();
//	    fourTypeOfYear.setWidth(130);
//	    fourTypeOfYear.setEnabled(false);
//	    hp.add(fourTypeOfYear);
        fourTypeOfYear = new Html();
        fourTypeOfYear.setWidth(130);
        hp.add(fourTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearFiveForYear = new Html();
        yearFiveForYear.setWidth(70);
        hp.add(yearFiveForYear);
//	    yearFiveForYear = new TextField<String>();
//	    yearFiveForYear.setWidth(70);
//	    yearFiveForYear.setEnabled(false);
//	    hp.add(yearFiveForYear);

//	    fiveTypeOfYear = new TextField<String>();
//	    fiveTypeOfYear.setWidth(130);
//	    fiveTypeOfYear.setEnabled(false);
//	    hp.add(fiveTypeOfYear);
        fiveTypeOfYear = new Html();
        fiveTypeOfYear.setWidth(130);
        hp.add(fiveTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearSixForYear = new Html();
        yearSixForYear.setWidth(70);
        hp.add(yearSixForYear);
//	    yearSixForYear = new TextField<String>();
//	    yearSixForYear.setWidth(70);
//	    yearSixForYear.setEnabled(false);
//	    hp.add(yearSixForYear);

//	    sixTypeOfYear = new TextField<String>();
//	    sixTypeOfYear.setWidth(130);
//	    sixTypeOfYear.setEnabled(false);
//	    hp.add(sixTypeOfYear);

        sixTypeOfYear = new Html();
        sixTypeOfYear.setWidth(130);
        hp.add(sixTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearSevenForYear = new Html();
        yearSevenForYear.setWidth(70);
        hp.add(yearSevenForYear);
//	    yearSevenForYear = new TextField<String>();
//	    yearSevenForYear.setWidth(70);
//	    yearSevenForYear.setEnabled(false);
//	    hp.add(yearSevenForYear);

//	    sevenTypeOfYear = new TextField<String>();
//	    sevenTypeOfYear.setWidth(130);
//	    sevenTypeOfYear.setEnabled(false);
//	    hp.add(sevenTypeOfYear);
        sevenTypeOfYear = new Html();
        sevenTypeOfYear.setWidth(130);
        hp.add(sevenTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearEightForYear = new Html();
        yearEightForYear.setWidth(70);
        hp.add(yearEightForYear);
//	    yearEightForYear = new TextField<String>();
//	    yearEightForYear.setWidth(70);
//	    yearEightForYear.setEnabled(false);
//	    hp.add(yearEightForYear);

//	    eightTypeOfYear = new TextField<String>();
//	    eightTypeOfYear.setWidth(130);
//	    eightTypeOfYear.setEnabled(false);
//	    hp.add(eightTypeOfYear);
        eightTypeOfYear = new Html();
        eightTypeOfYear.setWidth(130);
        hp.add(eightTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearNineForYear = new Html();
        yearNineForYear.setWidth(70);
        hp.add(yearNineForYear);
//	    yearNineForYear = new TextField<String>();
//	    yearNineForYear.setWidth(70);
//	    yearNineForYear.setEnabled(false);
//	    hp.add(yearNineForYear);

//	    nineTypeOfYear = new TextField<String>();
//	    nineTypeOfYear.setWidth(130);
//	    nineTypeOfYear.setEnabled(false);
//	    hp.add(nineTypeOfYear);
        nineTypeOfYear = new Html();
        nineTypeOfYear.setWidth(130);
        hp.add(nineTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearTenForYear = new Html();
        yearTenForYear.setWidth(70);
        hp.add(yearTenForYear);
//	    yearTenForYear = new TextField<String>();
//	    yearTenForYear.setWidth(70);
//	    yearTenForYear.setEnabled(false);
//	    hp.add(yearTenForYear);

//	    tenTypeOfYear = new TextField<String>();
//	    tenTypeOfYear.setWidth(130);
//	    tenTypeOfYear.setEnabled(false);
//	    hp.add(tenTypeOfYear);
        tenTypeOfYear = new Html();
        tenTypeOfYear.setWidth(130);
        hp.add(tenTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);

        hp = new HorizontalPanel();
        hp.setSpacing(4);
        yearElevenForYear = new Html();
        yearElevenForYear.setWidth(70);
        hp.add(yearElevenForYear);
//	    yearElevenForYear = new TextField<String>();
//	    yearElevenForYear.setWidth(70);
//	    yearElevenForYear.setEnabled(false);
//	    hp.add(yearElevenForYear);

//	    elevenTypeOfYear = new TextField<String>();
//	    elevenTypeOfYear.setWidth(130);
//	    elevenTypeOfYear.setEnabled(false);
//	    hp.add(elevenTypeOfYear);
        elevenTypeOfYear = new Html();
        elevenTypeOfYear.setWidth(130);
        hp.add(elevenTypeOfYear);

        price = new NumberField();
        price.setFieldLabel("Price");
        price.setWidth(70);
        hp.add(price);
        vp.add(hp);
        itemAnnual.add(vp);
    }

    void createContentTab(int tab)
    {
        TabItem tabItem= null;
        if(tab==1)
        {
            tabItem = itemFaostat;
        }
        formData = new FormData("-20");
        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("User Information");
        fieldSet.setCheckboxToggle(true);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(75);
        fieldSet.setLayout(layout);

        TextField<String> firstName = new TextField<String>();
        firstName.setFieldLabel("First Name");
        firstName.setAllowBlank(false);
        fieldSet.add(firstName, formData);

        TextField<String> lastName = new TextField<String>();
        lastName.setFieldLabel("Last Name");
        fieldSet.add(lastName, formData);

        TextField<String> company = new TextField<String>();
        company.setFieldLabel("Company");
        fieldSet.add(company, formData);

        TextField<String> email = new TextField<String>();
        email.setFieldLabel("Email");
        fieldSet.add(email, formData);
        tabItem.add(fieldSet);

    }

    public static SelectionListener<ButtonEvent> uploadPanel(final AMISInput input) {
        return new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                //System.out.println("Class: AmisInput Function: componentSelected Text:before input.build()");
                ExcelImporterWindow e = new ExcelImporterWindow();
                e.build();
                //System.out.println("Class: AmisInput Function: componentSelected Text:after input.build()");
            }
        };
    }

    public static SelectionListener<ButtonEvent> inputPanel(final AMISInput input) {
        return new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent ce) {
                //System.out.println("Class: AmisInput Function: componentSelected Text:before input.build()");

                //System.out.println("Class: AmisInput Function: componentSelected Text:after input.build()");
            }
        };
    }

    public ContentPanel getPanel() {
        return panel;
    }

    public void setPanel(ContentPanel panel) {
        this.panel = panel;
    }

	/*
	public HorizontalPanel buildLoginMessage(String text) {
		HorizontalPanel vp = new HorizontalPanel();
		HorizontalPanel panel = new HorizontalPanel();
		panel.addStyleName("login_welcome_panel");
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		//panel.setStyleAttribute("float", "right");
		panel.setSpacing(10);
		vp.setStyleAttribute("backgroundColor", "#FFFFFF");
		//vp.setStyleAttribute("float", "right");
		HTML html = new HTML();
		html.setHTML("<div class='login_welcome'>" + text + "</div>");
		panel.add(html);
		HTML htmlLogout = new HTML();
		htmlLogout.setHTML("<div class= 'login_welcome link' style= 'cursor:pointer;'> Logout </div>");
		htmlLogout.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
			System.out.println("Logout Button pressed!!!");
			AMISLoginPanel.getAmisUserParameters().setUsername("");
			AMISLoginPanel.getAmisUserParameters().setPwd("");
			if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
				RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
			AMISInputController.doLogout();
			}
		});
		panel.add(FormattingUtils.addHSpace(5));
		panel.add(htmlLogout);
		vp.setHorizontalAlign(HorizontalAlignment.RIGHT);
		//panel.add(FormattingUtils.addHSpace(10));
		//vp.add(FormattingUtils.addVSpace(5));
		vp.setTableWidth("100%");
		vp.add(panel);
		return vp;
	} */


    private HorizontalPanel buildHeader(String title, String id) {
        HorizontalPanel p = new HorizontalPanel();
        ClickHtml header = new ClickHtml();
        header.setHtml(title+" <img src='amis-images/import_data_arrows.png'>");
        header.addStyleName("input_panel_header");
        //	header.addListener(Events.OnClick, openInput(id));
        p.add(header);
        p.add(FormattingUtils.addHSpace(10));

        return p;
    }

    private HorizontalPanel buildGo(String title, String id) {
        HorizontalPanel p = new HorizontalPanel();
        p.addStyleName("input_panel_go");
        ClickHtml go = new ClickHtml();
        go.setHtml("<img src='amis-images/start_arrows.png'> "+title);
        go.addStyleName("input_panel_go");
        if(id.equals("INPUT"))
        {
            go.disable();
        }
        go.addListener(Events.OnClick, openInput(id));
        p.add(go);
        p.add(FormattingUtils.addHSpace(10));

        return p;
    }


    private Listener<ComponentEvent> openInput(final String action) {
        return new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent ce) {
                if(action.equals("INPUT"))
                {
                    System.out.println(" in openInput");
                    if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                        RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                    RootPanel.get("CLOSE_CBS_TOOL").add(buildCloseCbsToolMessage(action));
                    buildMainPanelCCbsTool();
                }
                else if(action.equals("CBS_MONTHLY_TOOL"))
                {
                    System.out.println(" in CBS MONTHLY TOOL");
                    if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                        RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                    RootPanel.get("CLOSE_CBS_TOOL").add(buildCloseCbsToolMessage(action));
                    buildMainPanelCCbsTool_monthlyTool();
                }
                else if(action.equals("CBS_ANNUAL_TOOL"))
                {
                    System.out.println(" in CBS ANNUAL TOOL");
                    if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                        RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                    RootPanel.get("CLOSE_CBS_TOOL").add(buildCloseCbsToolMessage(action));
                    buildMainPanelCCbsTool_annualTool();
                }
                else if(action.equals("CBS_POPULATION_IFRAME"))
                {
                    System.out.println(" in CBS_POPULATION_IFRAME");
                    if (RootPanel.get("CLOSE_CBS_TOOL").getWidgetCount() > 0)
                        RootPanel.get("CLOSE_CBS_TOOL").remove(RootPanel.get("CLOSE_CBS_TOOL").getWidget(0));
                    RootPanel.get("CLOSE_CBS_TOOL").add(buildCloseCbsToolMessage(action));
                    buildMainPanelCCbsTool_PopulationIframe();
                }//buildMainPanelCCbsTool_PopulationIframe
                else if(action.equals("UPLOAD")) {
                    //	buildUploadMainPanel();
                    //	uploadComplex.show();
                    //FenixAlert.info("Under Construction", "This function is under construction and will be activated as soon as possible!");
                    //	FenixAlert.info("Under Construction", "This function is under construction and will be activated as soon as possible!", null);
                    new ImporterWindow().build();
                }
            }
        };
    }

    public ComboBox<AMISCodesModelData> getCountryListBox() {
        return countryListBox;
    }

    public AMISCodesModelData getDataSourceSelected() {
        return dataSourceSelected;
    }

    public AMISCodesModelData getCountrySelected() {
        return countrySelected;
    }

    public void setDataSourceSelected(AMISCodesModelData dataSourceSelected) {
        this.dataSourceSelected = dataSourceSelected;
    }

    public void setCountrySelected(AMISCodesModelData countrySelected) {
        this.countrySelected = countrySelected;
    }

    public ComboBox<AMISCodesModelData> getDataSourceListBox() {
        return dataSourceListBox;
    }

    public Html getCountryLabel() {
        return countryLabel;
    }

    public void setSelectCountryTextInputPage(String selectTextCountry)
    {
        String selectTextFirstPart = "Please select a data source to view/edit ";
        //String selectTextCountry = getCountryLabel().getHtml();
        String selectTextSecondPart = " data.";
        selectFromComboBox.setHtml("<div class='input_panel_text'>"+selectTextFirstPart+ "<b>" + selectTextCountry + "</b>" +selectTextSecondPart +"</div>");
    }

    public void setSelectCountryAndDatasourceInputPage()
    {
        String selectTextFirstPart = "Please select a country and a data source to view country data. ";
        selectFromComboBoxForAmisSecretariat.setHtml("<div class='input_panel_text'>"+selectTextFirstPart+ "</div>");
    }

    public String getUsername() {
        return CCBS.USERNAME;
    }

    public void setUsername(String username) {
        CCBS.USERNAME = username;
    }

}