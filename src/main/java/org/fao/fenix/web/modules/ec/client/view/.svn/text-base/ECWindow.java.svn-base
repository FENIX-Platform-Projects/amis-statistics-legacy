package org.fao.fenix.web.modules.ec.client.view;

import java.util.TreeMap;

import org.fao.fenix.web.modules.ec.client.control.ECController;
import org.fao.fenix.web.modules.ec.common.vo.ECItem;
import org.fao.fenix.web.modules.ec.common.vo.ECItemVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class ECWindow extends FenixWindow {

	public ComboBox<ECItem> countryComboBox = new ComboBox<ECItem>();
	
	private VerticalPanel chartsPanel;
	
	private ContentPanel contentPanel;
	
	public ComboBox<ECItem> releaseDateField;
	
	public ComboBox<ECItem> language;
	
	private Button createReport;
	
	private Button exportPDF;
	
	private int firstWidthInt = 60;

	private int secondWidthInt = 250;
	
	private TreeMap<String, ECItemVO> countries = new TreeMap<String, ECItemVO>();
	
	public ECWindow() {
		contentPanel = new ContentPanel();
		FormLayout layout = new FormLayout();   
	    layout.setLabelWidth(firstWidthInt);  
	    layout.setLabelAlign(LabelAlign.LEFT);  
	    layout.setLabelSeparator("");
	    layout.setLabelPad(10);   
	    contentPanel.setLayout(layout); 
		contentPanel.setFrame(false);
		contentPanel.setHeaderVisible(false);
		contentPanel.setBodyBorder(false);
		contentPanel.setWidth(300);
		createReport = new Button("Create Report");
		exportPDF = new Button("Create All Reports");	
		chartsPanel = new VerticalPanel();
	}
	
	public void build(Boolean isHaitiEmergencyReport) {
		buildCenterPanel(isHaitiEmergencyReport);
		format();
		show();	
	}
	

	
	private void buildCenterPanel(Boolean isHaitiEmergencyReport) {
		setCenterProperties();
		getCenter().setHeaderVisible(false);
		VerticalPanel v = new VerticalPanel();
		
		v.setSpacing(20);
		v.setPosition(0, 10);
		contentPanel.add(buildCountryComboBox());
		contentPanel.add(buildDatesComboBox());
		if ( isHaitiEmergencyReport)
			contentPanel.add(buildLanguageComboBox());
		
		v.add(contentPanel);	
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(10);
		h.add(createReport);
		if ( !isHaitiEmergencyReport)
			h.add(exportPDF);
		v.add(h);
		
		getCenter().add(v);
		
//		getCenter().add(chartsPanel);
		addCenterPartToWindow();
		//Fill Combo boxes and set Button
		if ( !isHaitiEmergencyReport)
			ECController.fillECListBox(this);
		else {
			ECController.fillHaitiEmergencyListBox(this);
		}
			
		
		enhance(isHaitiEmergencyReport);
	}
	
	public ComboBox<ECItem> buildCountryComboBox() {
		countryComboBox = new ComboBox<ECItem>();
		countryComboBox.setTriggerAction(TriggerAction.ALL);
		ListStore<ECItem> store = new ListStore<ECItem>();
		countryComboBox.setStore(store);
		countryComboBox.setFieldLabel("<b>"+ BabelFish.print().country() +"<b>");
		countryComboBox.setEditable(true);
		countryComboBox.setTypeAhead(true);
		countryComboBox.setDisplayField("name");
		countryComboBox.setEmptyText(BabelFish.print().pleaseSelect());
		countryComboBox.setAllowBlank(false);
		countryComboBox.setId("country");
		countryComboBox.setMinListWidth(secondWidthInt);
		countryComboBox.addSelectionChangedListener(ECController.changeDates(this));
		return countryComboBox;
	}
	
	public ComboBox<ECItem> buildDatesComboBox() {
		releaseDateField = new ComboBox<ECItem>();
		releaseDateField.setTriggerAction(TriggerAction.ALL);
		ListStore<ECItem> store = new ListStore<ECItem>();
		releaseDateField.setStore(store);
		releaseDateField.setFieldLabel("<b>"+ BabelFish.print().dates() +"<b>");
		releaseDateField.setEditable(true);
		releaseDateField.setTypeAhead(true);
		releaseDateField.setDisplayField("name");
		releaseDateField.setEmptyText(BabelFish.print().pleaseSelect());
		releaseDateField.setAllowBlank(false);
		releaseDateField.setMinListWidth(secondWidthInt);
		return releaseDateField;
	}
	
	public ComboBox<ECItem> buildLanguageComboBox() {
		language = new ComboBox<ECItem>();
		language.setTriggerAction(TriggerAction.ALL);		
		ListStore<ECItem> store = new ListStore<ECItem>();
		language.setStore(store);
		language.setFieldLabel("<b>"+ BabelFish.print().language() +"<b>");
		language.setEditable(true);
		language.setTypeAhead(true);
		language.setDisplayField("name");
		language.setEmptyText(BabelFish.print().pleaseSelect());
		language.setAllowBlank(false);
		language.setMinListWidth(secondWidthInt);
		language.getStore().add(new ECItem("English", "EN"));
		language.getStore().add(new ECItem("French", "FR"));
		language.setValue(language.getStore().getAt(0));
		return language;
	}
	
	private void enhance(Boolean isHaitiEmergencyReport){
		createReport.addSelectionListener(ECController.createReport(this, isHaitiEmergencyReport));
		exportPDF.addSelectionListener(ECController.exportAllPDF(this, isHaitiEmergencyReport));
//		createReport.addSelectionListener(ECController.createNewReport(this));
	}
	
	private void format() {
		setSize("450px", "200px");
		getWindow().setHeading("Create EC Reports");
		setCollapsible(true);
		getWindow().setResizable(true);
	}
	
	


	public VerticalPanel getChartsPanel() {
		return chartsPanel;
	}

	public void setChartsPanel(VerticalPanel chartsPanel) {
		this.chartsPanel = chartsPanel;
	}

	public TreeMap<String, ECItemVO> getCountries() {
		return countries;
	}

	public void setCountries(TreeMap<String, ECItemVO> countries) {
		this.countries = countries;
	}



	
 
	
	
	
}