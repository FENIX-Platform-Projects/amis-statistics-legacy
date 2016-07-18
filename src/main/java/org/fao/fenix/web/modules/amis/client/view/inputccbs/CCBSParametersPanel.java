package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.fao.fenix.web.modules.amis.client.control.input.Month;
import org.fao.fenix.web.modules.amis.client.control.input.TestMonth;
import org.fao.fenix.web.modules.amis.client.control.inputccbs.PagePanelController;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.client.view.utils.AMISCbsUtility;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.Book;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.amis.common.vo.Cell;
import org.fao.fenix.web.modules.amis.common.vo.CellFormula;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;
import org.fao.fenix.web.modules.amis.common.vo.Page;
//import org.fao.fenix.web.modules.amis.server.SELECT;
//import org.fao.fenix.web.modules.amis.server.as;
//import org.fao.fenix.web.modules.amis.server.year;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.geotools.coverage.processing.operation.Resample;

import com.extjs.gxt.charts.client.model.axis.Keys;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;  
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CCBSParametersPanel extends VerticalPanel
{
	private static BookPanel bookPanel;
	private static AMISCbsUtility aMISCbsUtility;
//	private ListBox   countryListBox   = new ListBox();
//	private ListBox   dataSourceListBox   = new ListBox();	
//	private ListBox   commodityListBox   = new ListBox();
//	private ListBox   varietyListBox   = new ListBox();
//	private ListBox   monthListBox   = new ListBox();
	ListStore<AMISCodesModelData> countrylistStore = new ListStore<AMISCodesModelData>();
	ListStore<AMISCodesModelData> commoditylistStore = new ListStore<AMISCodesModelData>();
	ListStore<AMISCodesModelData> dataSourcelistStore = new ListStore<AMISCodesModelData>();
	private /*static*/ ComboBox<AMISCodesModelData>   countryListBox   = new ComboBox<AMISCodesModelData>();
	private /*static*/ ComboBox<AMISCodesModelData>   dataSourceListBox   = new ComboBox<AMISCodesModelData>();
	private /*static*/ ComboBox<AMISCodesModelData>   commodityListBox   = new ComboBox<AMISCodesModelData>();
//	private static ComboBox<AMISCodesModelData>   varietyListBox   = new ComboBox<AMISCodesModelData>();
	private /*static*/ ComboBox<Month>   monthListBox   = new ComboBox<Month>();
	private int dimension;
	//private static List<String> dataSourceName = new ArrayList();
	//private static List<String> gaulNames = new ArrayList();
	//private static List<String> commodityNames = new ArrayList();
	private static String formData[] = new String[5]; 
	private static CCBSWindow ccbswindow;
	private static LayoutContainer lc;
	//public static String firstElement;
	//public static String secondElement;
	
	//This variable is for FAO-CBS Sheet
//	public static String elementForTraining;
	//This variable is for NEW Sheet
//	public static String elementEmpty;
//	//This variable is for Data Source Sheet
//	public static String elementGeneralDatasource;
	
	private Button loadButton;
	private ComboBox<AMISCodesModelData> c;
	private Radio radio;
	private RadioGroup radioGroup;
    final Html to = new Html("");
	final Html start = new Html(); 
	
	public CCBSParametersPanel(CCBSWindow window, final String gaulCodes[], final AMISInput amisInput)
	{
		ccbswindow = window;
		aMISCbsUtility = new AMISCbsUtility();
		commodityListBox.setWidth("180px");
		monthListBox.setWidth("100px");
		commodityListBox.setDisplayField("label");
		commodityListBox.setStore(commoditylistStore);
		commodityListBox.setTriggerAction(TriggerAction.ALL);
		monthListBox.setTriggerAction(TriggerAction.ALL);
		commodityListBox.setValue(null);
		monthListBox.setValue(null);
		//Contains the rows selected in the four combo box and in the label
		for(int iFormData = 0; iFormData<5; iFormData++)
		{
			formData[iFormData] = "";
		}
		dimension = 10;
		lc = new LayoutContainer();
		TableLayout table = new TableLayout(5);
		table.setCellSpacing(3);  
		table.setCellPadding(1);
		lc.setLayout(table);
		
		Label labelCommodity = new Label("Commodity");
		labelCommodity.setStyleName("ccbs-Label");
		labelCommodity.setWidth("180px");
		ListStore<AMISCodesModelData> commodityStore = new ListStore<AMISCodesModelData>();  
		for (int i = 0; i < CCBS.COUNTRY_NAMES.size(); i++)
		{
			commodityStore.add(new AMISCodesModelData(CCBS.COUNTRY_NAMES.get(i), CCBS.COUNTRY_NAMES.get(i)));
		}
		Label fromMonth = new Label("Marketing Season");
		fromMonth.setStyleName("ccbs-Label");
		fromMonth.setWidth("120px");
		
		ListStore<Month> months = new ListStore<Month>();  
		months.add(new Month(""));  
	    months.add(TestMonth.getMonth());
		monthListBox.setStore(months);
		monthListBox.setDisplayField("month");
		//lc.insert(monthListBox,8);
	    
		
	   // Label toMonth = new Label("To");
	  //  toMonth.setStyleName("ccbs-Label");
	    //lc.insert(toMonth,4);
	    
	   // final Label to = new Label("");
	    final Html to = new Html("");
	  //  to.setStyleName("ccbs-MYLabel");
//	    final Label start = new Label("");
//	    to.setStyleName("ccbs-StartMonthLabel");
	    final Html start = new Html(); 
	    start.setWidth("120px");
	    Label lastYearToShow = new Label("Last year to show");
		lastYearToShow.setStyleName("ccbs-Label");
		lastYearToShow.setWidth("120px");
		
	   // lc.insert(labelDatasource,0);
	   // lc.insert(labelCountries,1);
	    lc.insert(labelCommodity,0);	
	    lc.insert(fromMonth,1);
	    Html html = new Html();
	    html.setWidth(90);
	    lc.insert(lastYearToShow,2);
		add(lc);
		monthListBox.addSelectionChangedListener(monthComboListener(to));
		//These changes have to be done based on the Data Source selected in the previous page(the INPUT page)
	//	elementsLinkedWithDataSource(to, start, amisInput);
		//dataSourceListBox.addSelectionChangedListener(dataSourceComboListener(to, start));
	//	elementsLinkedWithCountry(amisInput);
		//countryListBox.addSelectionChangedListener(countryComboListener());
		
		loadButton = new Button("Load Data");
		loadButton.setWidth(100);
		loadButton.setBorders(true);
		//loadButton.setIconStyle("sendToExcel");
		loadButton.addSelectionListener(preloadAction("1","1", formData, amisInput));
		lc.insert(new Html(),3);
		lc.insert(new Html(),4);
		//lc.insert(dataSourceListBox,7);
		//lc.insert(countryListBox,8);
		lc.insert(commodityListBox,5);
		//lc.insert(monthListBox,11);
		lc.insert(start,6);
		//lc.insert(to,12);
		setWidth(200);	
		
		FieldSet fieldset = new FieldSet();
		fieldset.setWidth(150);
		fieldset.setBorders(false);  
	    //fieldSet.setHeading("Select a Data Source:"); 
	    FormLayout layout = new FormLayout(); 
	    layout.setLabelSeparator("");
	    layout.setLabelAlign(LabelAlign.LEFT); 
	    layout.setLabelWidth(0);
	    layout.setLabelPad(0);
	    fieldset.setLayout(layout);  
		//Radio radio = new Radio();  
		radio = new Radio();  
		radio.setBoxLabel("2011/12"); 
		PagePanelController.setLastYearToShow(radio.getBoxLabel());
		radio.setValue(true);
		//radio.addListener(Events.Change, PagePanelController.disableDefaultYearComboBox(lc, radio));
		radio.addListener(Events.OnClick, PagePanelController.disableDefaultYearComboBox(lc, radio));
		Radio radio2 = new Radio();  
		radio2.setBoxLabel("Select");
		//radio2.addListener(Events.Change, PagePanelController.enableDefaultYearComboBox(lc, radio2));
		radio2.addListener(Events.OnClick, PagePanelController.enableDefaultYearComboBox(lc, radio2));
		//RadioGroup radioGroup = new RadioGroup();  
		radioGroup = new RadioGroup();
		radioGroup.add(radio);  
		radioGroup.add(radio2);
		radioGroup.disable();
		radio.disable();
		radio2.disable();
		fieldset.add(radioGroup);
		lc.insert(fieldset,7);		
		//ComboBox<AMISCodesModelData> c = PagePanelController.addLastYearCombo();
		c = PagePanelController.addLastYearCombo();
		c.addListener(Events.Change, PagePanelController.lastYearFromComboBox(c));
		c.disable();
		commodityListBox.addSelectionChangedListener(commodityComboListener(to, start, radioGroup, formData, c, radio));
		lc.insert(c,8);
		lc.insert(loadButton,9);
//		lc.insert(new Html(),9);
//		lc.insert(new Html(),10);
//		lc.insert(new Html(),11);
//		lc.insert(new Html(),12);
//		lc.insert(new Html(),13);
//		lc.insert(new Html(),14);
//		lc.insert(loadButton,15);

		elementsLinkedWithDataSource(to, start, amisInput);
		elementsLinkedWithCountry(amisInput);
	}
	
	public void elementsLinkedWithDataSource(final Html to, final Html start, final AMISInput amisInput) {		
					String dataSource = amisInput.getDataSourceSelected().getLabel();
					String monthBefore = "";
					commodityListBox.setValue(null);
					monthListBox.setValue(null);
					//to.setText("");
					to.setHtml("");
					for(int i = 0; i< formData.length; i++)
					{
						formData[i] = "";
					}
					formData[0] = dataSource;
				//	lc.remove(lc.getWidget(11));
				//	lc.remove(lc.getWidget(12));
					//start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
					start.setHtml("<div class='ccbs-MYLabel'></div>");
				//	to.setHtml("<div class='ccbs-MYLabel'></div>");
					if(formData[0].equals(CCBS.elementForTraining))
					{
					//	PagePanelController.showInfoPopup();
//						lc.remove(lc.getWidget(11));
//						start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
//						to.setHtml("<div class='ccbs-MYLabel'></div>");
					//	lc.insert(start, 11);
					//	String selectionType = "AMIS_COUNTRIES";
					//	loadDataForListBox(dataSource, selectionType, "", formData[0]);
						//This is the radiogroup to disable if data source is Cbs
						FieldSet fieldset = (FieldSet)lc.getWidget(7);
						int i = 0;
						for(Component component:fieldset.getItems())
						{
							if(i<1)
							{
								RadioGroup radiogroup = (RadioGroup)component;
								radiogroup.get(0).disable();
								radiogroup.get(1).disable();
								break;
							}
							i++;
						}
						//RadioGroup radiogroup = (RadioGroup)fieldset.getItemByItemId(itemId);
						//RadioGroup radiogroup = (RadioGroup)lc.getWidget(13);
			
						ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)lc.getWidget(8);
						combo.disable();
					}
					else
					{
						//lc.remove(lc.getWidget(11));
						//lc.insert(monthListBox,11);
						//String selectionType = "AMIS_COUNTRIES";
						//loadDataForListBox(dataSource, selectionType, "", formData[0]);
						FieldSet fieldset = (FieldSet)lc.getWidget(7);
						int i = 0;
						for(Component component:fieldset.getItems())
						{
							if(i<1)
							{
								RadioGroup radiogroup = (RadioGroup)component;
								radiogroup.get(0).enable();
								radiogroup.get(1).enable();
								break;
							}
							i++;
						}
					}
		}
	
	public void elementsLinkedWithCountry(AMISInput amisInput) {
		formData[1] = amisInput.getCountrySelected().getLabel();
		//To calculate the index
		commodityListBox.setValue(null);
		String countryCode= amisInput.getCountrySelected().getCode();
		
		
		
//		int indexCountryName=0;
//		String countryCode= "";
//		for(String countryName:CCBS.COUNTRY_NAMES)
//		{
//			if(countryName.equalsIgnoreCase(formData[1]))
//			{
//				break;
//			}
//			indexCountryName++;
//		}
//		if(indexCountryName<CCBS.COUNTRY_CODES.size())
//		{
//			countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
//		}
		String selectionType = "AMIS_PRODUCTS";
		loadDataForListBox(formData[1], selectionType, countryCode, formData[0]);
}
	
	public /*static*/ SelectionChangedListener<AMISCodesModelData> countryComboListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				if((se != null)&&(se.getSelectedItem()!=null))
				{
					formData[1] = se.getSelectedItem().getLabel();
					//To calculate the index
					commodityListBox.setValue(null);
					int indexCountryName=0;
					String countryCode= "";
					for(String countryName:CCBS.COUNTRY_NAMES)
					{
						if(countryName.equalsIgnoreCase(formData[1]))
						{
							break;
						}
						indexCountryName++;
					}
					if(indexCountryName<CCBS.COUNTRY_CODES.size())
					{
						countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
					}
					String selectionType = "AMIS_PRODUCTS";
					loadDataForListBox(formData[1], selectionType, countryCode, formData[0]);
					
//					String gaulName = gaulCode;
//					System.out.println("countryListBoxListener gaulCode= "+gaulCode);
//					System.out.println("countryListBoxListener gaulName= "+gaulName);
//					load(gaulCode+"", gaulName);
				}
			//	else unload();
			}
		};
	}
	
//	public CCBSParametersPanel(CCBSWindow window, final String gaulCodes[]/*, final String gaulNames[]*/)
//	{
//		ccbswindow = window;
//		aMISCbsUtility = new AMISCbsUtility();
////		countryListBox.setWidth("230px");
////		dataSourceListBox.setWidth("100px");
////		commodityListBox.setWidth("130px");
////		//varietyListBox.setWidth("90px");
////		monthListBox.setWidth("120px");
//		
//		countryListBox.setWidth("140px");
//		dataSourceListBox.setWidth("100px");
//		commodityListBox.setWidth("180px");
//		//varietyListBox.setWidth("90px");
//		monthListBox.setWidth("100px");
//		//firstElement = "FAO-CBS";
//		//secondElement = "NEW";
//		countryListBox.setDisplayField("label");
//		countryListBox.setStore(countrylistStore);
//		dataSourceListBox.setDisplayField("label");
//		dataSourceListBox.setStore(dataSourcelistStore);
//		commodityListBox.setDisplayField("label");
//		commodityListBox.setStore(commoditylistStore);
//		countryListBox.setTriggerAction(TriggerAction.ALL);
//		dataSourceListBox.setTriggerAction(TriggerAction.ALL);
//		commodityListBox.setTriggerAction(TriggerAction.ALL);
//		monthListBox.setTriggerAction(TriggerAction.ALL);
//		dataSourceListBox.getStore().removeAll();
//		countryListBox.getStore().removeAll();
//		commodityListBox.getStore().removeAll();
//		//monthListBox.getStore().removeAll();
//		dataSourceListBox.setValue(null);
//		countryListBox.setValue(null);
//		commodityListBox.setValue(null);
//		monthListBox.setValue(null);
//		
//		//elementForTraining = "FAO-CBS";
//		//elementEmpty = "NEW";
////		gaulNames = new ArrayList();
////		dataSourceName = new ArrayList();
////		commodityNames = new ArrayList();
//		//Contains the rows selected in the four combo box and in the label
//		for(int iFormData = 0; iFormData<5; iFormData++)
//		{
//			formData[iFormData] = "";
//		}
//		dimension = 10;
////		dataSourceName[0] = "CBS";
////		dataSourceName[1] = "FAOSTAT";
////		dataSourceName[2] = "PSD";
//
//		lc = new LayoutContainer();
//		TableLayout table = new TableLayout(7);
////		table.setCellSpacing(10);  
////		table.setCellPadding(4);
//		table.setCellSpacing(3);  
//		table.setCellPadding(1);
//		lc.setLayout(table);
//		
//	//	System.out.println("Class; CCBSParameterPanel  Function: CCBSParametersPanel Text: Before loadDataSourceNames ");
//		loadDataSourceNames();
//	//	System.out.println("Class; CCBSParameterPanel  Function: CCBSParametersPanel Text: After loadDataSourceNames ");
//		Label labelDatasource = new Label("Data Source");
//		labelDatasource.setStyleName("ccbs-Label");
//		labelDatasource.setWidth("100px");
//		//lc.insert(labelDatasource,0);
//		//lc.insert(dataSourceListBox,5);
//		
//	//	countryListBox.addItem("");
//	//	System.out.println("Class; CCBSParameterPanel  Function: CCBSParametersPanel Text: Tornato ");
//	//	System.out.println("Class; CCBSParameterPanel  Function: CCBSParametersPanel Text: gaulNames.size() "+CCBS.COUNTRY_NAMES.size());
//		//countryListBox.setWidth(""+dimension);
//		
//		Label labelCountries = new Label("Country/Region");
//		labelCountries.setStyleName("ccbs-Label");
//		labelCountries.setWidth("140px");
//
//		//lc.insert(labelCountries,1);
//		//lc.insert(countryListBox,6);
//
//		Label labelCommodity = new Label("Commodity");
//		labelCommodity.setStyleName("ccbs-Label");
//		labelCommodity.setWidth("180px");
//		
//		//lc.insert(labelCommodity,2);	
//	//	commodityListBox.addItem("");
//	//	for (int i = 0; i < CCBS.COUNTRY_NAMES.size(); i++) commodityListBox.addItem(CCBS.COUNTRY_NAMES.get(i));
//		ListStore<AMISCodesModelData> commodityStore = new ListStore<AMISCodesModelData>();  
//		for (int i = 0; i < CCBS.COUNTRY_NAMES.size(); i++)
//		{
//			commodityStore.add(new AMISCodesModelData(CCBS.COUNTRY_NAMES.get(i), CCBS.COUNTRY_NAMES.get(i)));
//		}
//		//lc.insert(commodityListBox,7);
//		
//		Label fromMonth = new Label("Marketing Season");
//		fromMonth.setStyleName("ccbs-Label");
//		fromMonth.setWidth("120px");
//		
////		Label toMonth = new Label("Marketing Season To");
////		toMonth.setStyleName("ccbs-Label");
////		toMonth.setWidth("120px");
//		//lc.add(fromMonth);
//		//lc.insert(fromMonth,3);
//		
//		ListStore<Month> months = new ListStore<Month>();  
//		months.add(new Month(""));  
//	    months.add(TestMonth.getMonth());  
//	    
//		//monthListBox.setWidth("90");
//	//    monthListBox.addItem("");
//	    
//	//	for (int i = 0; i < months.getCount(); i++) monthListBox.addItem(months.getAt(i).getMonth());
//		monthListBox.setStore(months);
//		monthListBox.setDisplayField("month");
//		//lc.insert(monthListBox,8);
//	    
//		
//	   // Label toMonth = new Label("To");
//	  //  toMonth.setStyleName("ccbs-Label");
//	    //lc.insert(toMonth,4);
//	    
//	   // final Label to = new Label("");
//	    final Html to = new Html("");
//	  //  to.setStyleName("ccbs-MYLabel");
////	    final Label start = new Label("");
////	    to.setStyleName("ccbs-StartMonthLabel");
//	    final Html start = new Html(); 
//	    start.setWidth("120px");
//	   // lc.insert(to,9);
//	    Label lastYearToShow = new Label("Last year to show");
//		lastYearToShow.setStyleName("ccbs-Label");
//		lastYearToShow.setWidth("120px");
//		
//	    lc.insert(labelDatasource,0);
//	    lc.insert(labelCountries,1);
//	    lc.insert(labelCommodity,2);	
//	    lc.insert(fromMonth,3);
//	 //   lc.insert(toMonth,4);
//	    Html html = new Html();
//	    html.setWidth(90);
//	 //   lc.insert(new Html(),4);
//	    //lc.insert(toMonth,4);
//	    lc.insert(lastYearToShow,4);
//	   // lc.insert(new Html(),6);
//	   
//			
//	    
////	    hpMarketingTo.add(to);
////	    hpMarketing.add(hpMarketingTo);
////	    fieldSet.add(hpMarketing);  
//		
//		add(lc);
//		
//		
//		monthListBox.addSelectionChangedListener(monthComboListener(to));
////		monthListBox.addChangeListener(new ChangeListener()
////		{
////			public void onChange(Widget widget)
////			{
////				int selIdx = monthListBox.getSelectedIndex();
////				if (selIdx >= 0)
////				{
////					String month = monthListBox.getItemText(selIdx);
////					String monthBefore = "";
////					formData[3] = month;
////					formData[4] = monthBefore;
////					
////					if(selIdx > 0)
////					{
////						monthBefore = getOneMonthBefore(month);
////						formData[4] = monthBefore;
////					//to.setHtml("<b>font-size:15" + monthBefore + " </b>");
////					to.setText("/"+monthBefore);
////					//to.setTitle(monthBefore);
////					//to.setEmptyText(monthBefore);
//////					String gaulCode = gaulCodes[selIdx - 1];
//////					String gaulName = gaulNames[selIdx - 1];
//////					load(gaulCode+"", gaulName);
////					}
////				}
////				else unload();
////			}
////		});
//		dataSourceListBox.addSelectionChangedListener(dataSourceComboListener(to, start));
////		dataSourceListBox.addChangeListener(new ChangeListener()
////		{
////			public void onChange(Widget widget)
////			{
////				int selIdx = dataSourceListBox.getSelectedIndex();
////				if (selIdx >= 0)
////				{
////					String dataSource = dataSourceListBox.getItemText(selIdx);
////					System.out.println("CHANGE!!!!dataSourceListBox.addChangeListener dataSource: "+dataSource);
////					formData[0] = dataSource;
////					if(selIdx > 0)
////					{
////						if(formData[0].equals(elementForTraining))
////						{
////							PagePanelController.showInfoPopup();
////							String selectionType = "AMIS_COUNTRIES";
////							loadDataForListBox(dataSource, selectionType, "", formData[0]);
////							FieldSet fieldset = (FieldSet)lc.getWidget(13);
////							int i = 0;
////							for(Component component:fieldset.getItems())
////							{
////								if(i<1)
////								{
////									RadioGroup radiogroup = (RadioGroup)component;
////									radiogroup.get(0).disable();
////									radiogroup.get(1).disable();
////									break;
////								}
////								i++;
////							}
////							//RadioGroup radiogroup = (RadioGroup)fieldset.getItemByItemId(itemId);
////							//RadioGroup radiogroup = (RadioGroup)lc.getWidget(13);
////				
////							ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)lc.getWidget(14);
////							combo.disable();
////						}
////						else
////						{
////							String selectionType = "AMIS_COUNTRIES";
////							loadDataForListBox(dataSource, selectionType, "", formData[0]);
////							FieldSet fieldset = (FieldSet)lc.getWidget(13);
////							int i = 0;
////							for(Component component:fieldset.getItems())
////							{
////								if(i<1)
////								{
////									RadioGroup radiogroup = (RadioGroup)component;
////									radiogroup.get(0).enable();
////									radiogroup.get(1).enable();
////									break;
////								}
////								i++;
////							}
////							
//////							RadioGroup radiogroup = (RadioGroup)lc.getWidget(13);
//////							radiogroup.get(0).enable();
//////							radiogroup.get(1).enable();
////						}
//////						selectionType = "AMIS_PRODUCTS";
//////						loadDataForListBox(dataSource, selectionType);
////					}
////				}
////				else unload();
////			}
////		});
//		countryListBox.addSelectionChangedListener(countryComboListener());
////		countryListBox.addChangeListener(new ChangeListener()
////		{
////			public void onChange(Widget widget)
////			{
////				int selIdx = countryListBox.getSelectedIndex();
////				if (selIdx >= 0)
////				{
//////					String gaulCode = gaulCodes[selIdx - 1];
//////					//String gaulName = gaulNames[selIdx - 1];
////					formData[1] = countryListBox.getItemText(selIdx);
////					//To calculate the index
////					int indexCountryName=0;
////					String countryCode= "";
////					for(String countryName:CCBS.COUNTRY_NAMES)
////					{
////						if(countryName.equalsIgnoreCase(formData[1]))
////						{
////							break;
////						}
////						indexCountryName++;
////					}
////					if(indexCountryName<CCBS.COUNTRY_CODES.size())
////					{
////						countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
////					}
////					String selectionType = "AMIS_PRODUCTS";
////					loadDataForListBox(formData[1], selectionType, countryCode, formData[0]);
//////					String gaulName = gaulCode;
//////					System.out.println("countryListBoxListener gaulCode= "+gaulCode);
//////					System.out.println("countryListBoxListener gaulName= "+gaulName);
//////					load(gaulCode+"", gaulName);
////				}
////				else unload();
////			}
////		});
//		
//		commodityListBox.addSelectionChangedListener(commodityComboListener(to, start));
////		commodityListBox.addChangeListener(new ChangeListener()
////		{
////			public void onChange(Widget widget)
////			{
////				int selIdx = commodityListBox.getSelectedIndex();
////				if (selIdx >= 0)
////				{
////					formData[2] = commodityListBox.getItemText(selIdx);
////				}
////				else unload();
////			}
////		});
//		
//		loadButton = new Button("Load Data");
//		loadButton.setWidth(100);
//		loadButton.setBorders(true);
//		//loadButton.setIconStyle("sendToExcel");
//		loadButton.addSelectionListener(preloadAction("1","1", formData));
//
////		lc.insert(new Html(),12);
////		lc.insert(new Html(),13);
////		lc.insert(new Html(),14);
////		lc.insert(new Html(),15);
////		lc.insert(new Html(),16);
////		lc.insert(new Html(),17);
////		lc.insert(loadButton,18);
//		
//		//lc.insert(loadButton,6);
//		lc.insert(new Html(),5);
//		lc.insert(new Html(),6);
//		lc.insert(dataSourceListBox,7);
//		lc.insert(countryListBox,8);
//		lc.insert(commodityListBox,9);
//		//lc.insert(monthListBox,11);
//		lc.insert(start,10);
//		//lc.insert(to,12);
//		setWidth(200);	
//		
//		FieldSet fieldset = new FieldSet();
//		fieldset.setWidth(150);
//		fieldset.setBorders(false);  
//	    //fieldSet.setHeading("Select a Data Source:"); 
//	    FormLayout layout = new FormLayout(); 
//	    layout.setLabelSeparator("");
//	    layout.setLabelAlign(LabelAlign.LEFT); 
//	    layout.setLabelWidth(0);
//	    layout.setLabelPad(0);
//	    fieldset.setLayout(layout);  
//		Radio radio = new Radio();  
//		radio.setBoxLabel("2011/12"); 
//		PagePanelController.setLastYearToShow(radio.getBoxLabel());
//		radio.setValue(true);  
//		radio.addListener(Events.Change, PagePanelController.disableDefaultYearComboBox(lc, radio));
//		Radio radio2 = new Radio();  
//		radio2.setBoxLabel("Select");
//		radio2.addListener(Events.Change, PagePanelController.enableDefaultYearComboBox(lc, radio2));
//		
//		RadioGroup radioGroup = new RadioGroup();  
//		
//		//radioGroup.setFieldLabel("Favorite Color");  
//		radioGroup.add(radio);  
//		radioGroup.add(radio2);
//		radio.disable();
//		radio2.disable();
//		fieldset.add(radioGroup);
//		lc.insert(fieldset,11);
//		
//		ComboBox<AMISCodesModelData> c = PagePanelController.addLastYearCombo();
//		c.addListener(Events.Change, PagePanelController.lastYearFromComboBox(c));
//		c.disable();
//		lc.insert(c,12);
//		lc.insert(new Html(),13);
//		lc.insert(new Html(),14);
//		lc.insert(new Html(),15);
//		lc.insert(new Html(),16);
//		lc.insert(new Html(),17);
//		lc.insert(new Html(),18);
//		lc.insert(loadButton,19);
//	}
//	public CCBSParametersPanel(CCBSWindow window, final String gaulCodes[]/*, final String gaulNames[]*/)
//	{
//		ccbswindow = window;
//		
//		countryListBox.setWidth("140px");
//		dataSourceListBox.setWidth("100px");
//		commodityListBox.setWidth("180px");
//		monthListBox.setWidth("100px");
//		countryListBox.setDisplayField("label");
//		countryListBox.setStore(countrylistStore);
//		dataSourceListBox.setDisplayField("label");
//		dataSourceListBox.setStore(dataSourcelistStore);
//		commodityListBox.setDisplayField("label");
//		commodityListBox.setStore(commoditylistStore);
//		countryListBox.setTriggerAction(TriggerAction.ALL);
//		dataSourceListBox.setTriggerAction(TriggerAction.ALL);
//		commodityListBox.setTriggerAction(TriggerAction.ALL);
//		monthListBox.setTriggerAction(TriggerAction.ALL);
//		dataSourceListBox.getStore().removeAll();
//		countryListBox.getStore().removeAll();
//		commodityListBox.getStore().removeAll();
//		dataSourceListBox.setValue(null);
//		countryListBox.setValue(null);
//		commodityListBox.setValue(null);
//		monthListBox.setValue(null);
//		
//		elementForTraining = "FAO-CBS";
//		elementEmpty = "NEW";
//		//Contains the rows selected in the four combo box and in the label
//		for(int iFormData = 0; iFormData<5; iFormData++)
//		{
//			formData[iFormData] = "";
//		}
//		dimension = 10;
//		lc = new LayoutContainer();
//		TableLayout table = new TableLayout(8);
////		table.setCellSpacing(10);  
////		table.setCellPadding(4);
//		table.setCellSpacing(3);  
//		table.setCellPadding(1);
//		lc.setLayout(table);
//
//		loadDataSourceNames();
//		Label labelDatasource = new Label("Data Source");
//		labelDatasource.setStyleName("ccbs-Label");
//		labelDatasource.setWidth("100px");
//		
//		Label labelCountries = new Label("Country/Region");
//		labelCountries.setStyleName("ccbs-Label");
//		labelCountries.setWidth("140px");
//
//		Label labelCommodity = new Label("Commodity");
//		labelCommodity.setStyleName("ccbs-Label");
//		labelCommodity.setWidth("180px");
//
//		ListStore<AMISCodesModelData> commodityStore = new ListStore<AMISCodesModelData>();  
//		for (int i = 0; i < CCBS.COUNTRY_NAMES.size(); i++)
//		{
//			commodityStore.add(new AMISCodesModelData(CCBS.COUNTRY_NAMES.get(i), CCBS.COUNTRY_NAMES.get(i)));
//		}
//	
//		Label fromMonth = new Label("Marketing Season");
//		fromMonth.setStyleName("ccbs-Label");
//		fromMonth.setWidth("120px");
//		
//		ListStore<Month> months = new ListStore<Month>();  
//		months.add(new Month(""));  
//	    months.add(TestMonth.getMonth());  
//	    
//		monthListBox.setStore(months);
//		monthListBox.setDisplayField("month");
//
//	    final Html to = new Html("");
//	    final Html start = new Html(); 
//	    start.setWidth("120px");
//	    Label lastYearToShow = new Label("Last year to show");
//		lastYearToShow.setStyleName("ccbs-Label");
//		lastYearToShow.setWidth("120px");
//		
//	    lc.insert(labelDatasource,0);
//	    lc.insert(labelCountries,1);
//	    lc.insert(labelCommodity,2);	
//	    lc.insert(fromMonth,3);
//	    Html html = new Html();
//	    html.setWidth(90);
//	    lc.insert(new Html(),4);
//	    lc.insert(lastYearToShow,5);
//		add(lc);
//		
//		
//		monthListBox.addSelectionChangedListener(monthComboListener(to));
//		dataSourceListBox.addSelectionChangedListener(dataSourceComboListener(to, start));
//		countryListBox.addSelectionChangedListener(countryComboListener());		
//		commodityListBox.addSelectionChangedListener(commodityComboListener(to, start));
//		loadButton = new Button("Load Data");
//		loadButton.setWidth(100);
//		loadButton.setBorders(true);
//		loadButton.addSelectionListener(preloadAction("1","1", formData));
//		lc.insert(new Html(),6);
//		lc.insert(new Html(),7);
//		lc.insert(dataSourceListBox,8);
//		lc.insert(countryListBox,9);
//		lc.insert(commodityListBox,10);
//		lc.insert(monthListBox,11);
//		lc.insert(to,12);
//		setWidth(200);	
//		
//		FieldSet fieldset = new FieldSet();
//		fieldset.setWidth(150);
//		fieldset.setBorders(false);  
//	    FormLayout layout = new FormLayout(); 
//	    layout.setLabelSeparator("");
//	    layout.setLabelAlign(LabelAlign.LEFT); 
//	    layout.setLabelWidth(0);
//	    layout.setLabelPad(0);
//	    fieldset.setLayout(layout);  
//		Radio radio = new Radio();  
//		radio.setBoxLabel("2011/12"); 
//		PagePanelController.setLastYearToShow(radio.getBoxLabel());
//		radio.setValue(true);  
//		radio.addListener(Events.Change, PagePanelController.disableDefaultYearComboBox(lc, radio));
//		Radio radio2 = new Radio();  
//		radio2.setBoxLabel("Select");
//		radio2.addListener(Events.Change, PagePanelController.enableDefaultYearComboBox(lc, radio2));
//		
//		RadioGroup radioGroup = new RadioGroup();  
//
//		radioGroup.add(radio);  
//		radioGroup.add(radio2);
//		radio.disable();
//		radio2.disable();
//		fieldset.add(radioGroup);
//		lc.insert(fieldset,13);
//		
//		ComboBox<AMISCodesModelData> c = PagePanelController.addLastYearCombo();
//		c.addListener(Events.Change, PagePanelController.lastYearFromComboBox(c));
//		c.disable();
//		lc.insert(c,14);
//		lc.insert(new Html(),15);
//		lc.insert(new Html(),16);
//		lc.insert(new Html(),17);
//		lc.insert(new Html(),18);
//		lc.insert(new Html(),19);
//		lc.insert(new Html(),20);
//		lc.insert(loadButton,21);
//	}
	
	public static SelectionChangedListener<Month> monthComboListener(final Html to) {
		return new SelectionChangedListener<Month>() {
			public void selectionChanged(SelectionChangedEvent<Month> se) {
				if((se != null)&&(se.getSelectedItem()!=null))
				{
					String month = se.getSelectedItem().getMonth();
					String monthBefore = "";
					formData[3] = month;
//					formData[4] = monthBefore;
					monthBefore = getOneMonthBefore(month);
					formData[4] = monthBefore;
					//to.setHtml("<b>font-size:15" + monthBefore + " </b>");
					to.setHtml("<div class='ccbs-MYLabel'>/" + monthBefore + "</div>");
//					to.setText("/"+monthBefore);
				}
			//	else unload();
			}
		};
	}
	
//	public /*static*/ SelectionChangedListener<AMISCodesModelData> dataSourceComboListener(final Html to, final Html start) {
//		return new SelectionChangedListener<AMISCodesModelData>() {
//			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
//				
//				if((se != null)&&(se.getSelectedItem()!=null))
//				{
////					countryListBox.getStore().removeAll();
////					commodityListBox.getStore().removeAll();
////					countryListBox.getStore().removeAll();
////					monthListBox.getStore().removeAll();
////					monthListBox.getStore().add(new Month(""));
////					monthListBox.getStore().add(TestMonth.getMonth());
//					String dataSource = se.getSelectedItem().getLabel();
//					String monthBefore = "";
//					countryListBox.setValue(null);
//					commodityListBox.setValue(null);
//					monthListBox.setValue(null);
//					//to.setText("");
//					to.setHtml("");
//					for(int i = 0; i< formData.length; i++)
//					{
//						formData[i] = "";
//					}
//					formData[0] = dataSource;
//					if(formData[0].equals(elementForTraining))
//					{
//						PagePanelController.showInfoPopup();
//						lc.remove(lc.getWidget(11));
////						start.setText("");
//						start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
//						lc.insert(start, 11);
////					    Html html = new Html("test");
////					    html.setWidth(90);
////					    lc.insert(html,11);
//						String selectionType = "AMIS_COUNTRIES";
//						loadDataForListBox(dataSource, selectionType, "", formData[0]);
//						FieldSet fieldset = (FieldSet)lc.getWidget(13);
//						int i = 0;
//						for(Component component:fieldset.getItems())
//						{
//							if(i<1)
//							{
//								RadioGroup radiogroup = (RadioGroup)component;
//								radiogroup.get(0).disable();
//								radiogroup.get(1).disable();
//								break;
//							}
//							i++;
//						}
//						//RadioGroup radiogroup = (RadioGroup)fieldset.getItemByItemId(itemId);
//						//RadioGroup radiogroup = (RadioGroup)lc.getWidget(13);
//			
//						ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)lc.getWidget(14);
//						combo.disable();
//					}
//					else
//					{
//						lc.remove(lc.getWidget(11));
//						lc.insert(monthListBox,11);
//						String selectionType = "AMIS_COUNTRIES";
//						loadDataForListBox(dataSource, selectionType, "", formData[0]);
//						FieldSet fieldset = (FieldSet)lc.getWidget(13);
//						int i = 0;
//						for(Component component:fieldset.getItems())
//						{
//							if(i<1)
//							{
//								RadioGroup radiogroup = (RadioGroup)component;
//								radiogroup.get(0).enable();
//								radiogroup.get(1).enable();
//								break;
//							}
//							i++;
//						}
//					}
//				}
//				else 
//				{
//					unload();
//				}
//			}
//		};
//	}
	public /*static*/ SelectionChangedListener<AMISCodesModelData> dataSourceComboListener(final Html to, final Html start) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				
				if((se != null)&&(se.getSelectedItem()!=null))
				{
//					countryListBox.getStore().removeAll();
//					commodityListBox.getStore().removeAll();
//					countryListBox.getStore().removeAll();
//					monthListBox.getStore().removeAll();
//					monthListBox.getStore().add(new Month(""));
//					monthListBox.getStore().add(TestMonth.getMonth());
					String dataSource = se.getSelectedItem().getLabel();
					String monthBefore = "";
					countryListBox.setValue(null);
					commodityListBox.setValue(null);
					monthListBox.setValue(null);
					//to.setText("");
					to.setHtml("");
					for(int i = 0; i< formData.length; i++)
					{
						formData[i] = "";
					}
					formData[0] = dataSource;
				//	lc.remove(lc.getWidget(11));
				//	lc.remove(lc.getWidget(12));
					//start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
					start.setHtml("<div class='ccbs-MYLabel'></div>");
				//	to.setHtml("<div class='ccbs-MYLabel'></div>");
					if(formData[0].equals(CCBS.elementForTraining))
					{
						PagePanelController.showInfoPopup();
//						lc.remove(lc.getWidget(11));
//						start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
//						to.setHtml("<div class='ccbs-MYLabel'></div>");
					//	lc.insert(start, 11);
						String selectionType = "AMIS_COUNTRIES";
						loadDataForListBox(dataSource, selectionType, "", formData[0]);
						FieldSet fieldset = (FieldSet)lc.getWidget(11);
						int i = 0;
						for(Component component:fieldset.getItems())
						{
							if(i<1)
							{
								RadioGroup radiogroup = (RadioGroup)component;
								radiogroup.get(0).disable();
								radiogroup.get(1).disable();
								break;
							}
							i++;
						}
						//RadioGroup radiogroup = (RadioGroup)fieldset.getItemByItemId(itemId);
						//RadioGroup radiogroup = (RadioGroup)lc.getWidget(13);
			
						ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)lc.getWidget(12);
						combo.disable();
					}
					else
					{
						//lc.remove(lc.getWidget(11));
						//lc.insert(monthListBox,11);
						String selectionType = "AMIS_COUNTRIES";
						loadDataForListBox(dataSource, selectionType, "", formData[0]);
						FieldSet fieldset = (FieldSet)lc.getWidget(11);
						int i = 0;
						for(Component component:fieldset.getItems())
						{
							if(i<1)
							{
								RadioGroup radiogroup = (RadioGroup)component;
								radiogroup.get(0).enable();
								radiogroup.get(1).enable();
								break;
							}
							i++;
						}
					}
				}
				else 
				{
					unload();
				}
			}
		};
	}
	
//	public /*static*/ SelectionChangedListener<AMISCodesModelData> commodityComboListener(final Html to, final Html start) {
//		return new SelectionChangedListener<AMISCodesModelData>() {
//			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
//				if((se != null)&&(se.getSelectedItem()!=null))
//				{
//					String commodity = se.getSelectedItem().getLabel();
//					formData[2] = commodity;
//					//FAO-CBS case
//					if(formData[0].equals(elementForTraining))
//					{
//						//To calculate the index for the country code
//						int indexCountryName=0;
//						String countryCode= "";
//						for(String countryName:CCBS.COUNTRY_NAMES)
//						{
//							if(countryName.equalsIgnoreCase(formData[1]))
//							{
//								break;
//							}
//							indexCountryName++;
//						}
//						if(indexCountryName<CCBS.COUNTRY_CODES.size())
//						{
//							countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
//						}
//						//To calculate the index for the commodity code
//						int indexCommodityName=0;
//						String commodityCode= "";
//						for(String commodityName:CCBS.COMMODITY_NAMES_CBS)
//						{
//							System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text:commodityName "+commodityName);
//							System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text:formData[2] "+formData[2]);
//							System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries indexCommodityName "+indexCommodityName);
//							System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text:COMMODITY_CODES_CBS.get(indexCommodityName) "+CCBS.COMMODITY_CODES_CBS.get(indexCommodityName));
//							if(commodityName.equalsIgnoreCase(formData[2]))
//							{
//								System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Before break indexCommodityName "+indexCommodityName);
//								break;
//							}
//							indexCommodityName++;
//						}
//						if(indexCommodityName<CCBS.COMMODITY_CODES_CBS.size())
//						{
//							commodityCode = CCBS.COMMODITY_CODES_CBS.get(indexCommodityName);
//							System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries commodityCode= in if "+commodityCode);
//						}		
//						System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: countryCode "+countryCode);
//						System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: commodityCode "+commodityCode);
//						AMISQueryVO qvo =  new AMISQueryVO();
//						qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
//						String selectionType = "AMIS_MARKETING_YEAR";
//						qvo.setTypeOfOutput(selectionType);	
//					
//						qvo.setCountryCode(countryCode);
//						qvo.setCommodityCode(commodityCode);
//						
//						try {
//							AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//								
//								@SuppressWarnings("unchecked")
//								public void onSuccess(AMISResultVO vo) {
//
//									for(AMISCodesModelData topcode : vo.getCodes()) {
//										String startMonth = topcode.getLabel();
//										formData[3] = startMonth;
//										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text start   : startMonth "+startMonth);
////										start.setText(startMonth);
//										start.setHtml("<div class='ccbs-StartMonthLabel' style='text-align: right;'>" + startMonth + "</div>");
////										formData[4] = monthBefore;
//										String endMonth = getOneMonthBefore(startMonth);
//										formData[4] = endMonth;
////										to.setText(endMonth);
//										//to.setHtml("<b>font-size:15" + monthBefore + " </b>");
////										to.setText("/"+endMonth);	
//										to.setHtml("<div class='ccbs-MYLabel'>/"+endMonth + "</div>");
//										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: startMonth "+startMonth);
//										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: endMonth "+endMonth);
//										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: start.getText() "+start.getHtml());
//										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: to.getText() "+to.getHtml());
//									}				
//								}
//								
//								public void onFailure(Throwable arg0) {
//					
//								}
//							});
//							
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//				}
//					else
//					{
//						monthListBox.setValue(null);
//					}
//			}
//			//	else unload();
//		};
//		};
//	}
	
	public /*static*/ SelectionChangedListener<AMISCodesModelData> commodityComboListener(final Html to, final Html start, final RadioGroup radioGroup, final String fomData[], final ComboBox<AMISCodesModelData> c, final Radio radio) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				if((se != null)&&(se.getSelectedItem()!=null))
				{
					CCBS.MARKETING_YEAR_AVAILABLE = false;
					String commodity = se.getSelectedItem().getLabel();
					formData[2] = commodity;
					start.setHtml("<div class='ccbs-MYLabel'></div>");
					to.setHtml("<div class='ccbs-MYLabel'></div>");
					formData[3]="";
					formData[4]="";
					//FAO-CBS case
					//if(formData[0].equals(elementForTraining))
					//{
						//To calculate the index for the country code
						int indexCountryName=0;
						String countryCode= "";
						for(String countryName:CCBS.COUNTRY_NAMES)
						{
							if(countryName.equalsIgnoreCase(formData[1]))
							{
								break;
							}
							indexCountryName++;
						}
						if(indexCountryName<CCBS.COUNTRY_CODES.size())
						{
							countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
						}
						//To calculate the index for the commodity code
						int indexCommodityName=0;
						String commodityCode= "";
						for(String commodityName:CCBS.COMMODITY_NAMES_CBS)
						{
//							System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Text:commodityName "+commodityName);
//							System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Text:formData[2] "+formData[2]);
//							System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener indexCommodityName "+indexCommodityName);
//							System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Text:COMMODITY_CODES_CBS.get(indexCommodityName) "+CCBS.COMMODITY_CODES_CBS.get(indexCommodityName));
							if(commodityName.equalsIgnoreCase(formData[2]))
							{
								//System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Before break indexCommodityName "+indexCommodityName);
								break;
							}
							indexCommodityName++;
						}
						if(indexCommodityName<CCBS.COMMODITY_CODES_CBS.size())
						{
							commodityCode = CCBS.COMMODITY_CODES_CBS.get(indexCommodityName);
							//System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener commodityCode= in if "+commodityCode);
						}
						
						if(!(formData[0].equals(CCBS.elementForTraining)))
						{
						//	System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: if(!(formData[0].equals(CCBS.elementForTraining))) ");
							loadYearsForComboSelection(formData, countryCode, commodityCode, AMISConstants.YEARS.toString(), radioGroup, c, start, to, radio);
						}
						else
						{
							//System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: countryCode "+countryCode);
    						//System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: commodityCode "+commodityCode);
    						AMISQueryVO qvo =  new AMISQueryVO();
    						qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
    						String selectionType = "AMIS_MARKETING_YEAR";
    						qvo.setTypeOfOutput(selectionType);	
    					
    						qvo.setCountryCode(countryCode);
    						qvo.setCommodityCode(commodityCode);
    						
    						try {
    							AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
    								
    								@SuppressWarnings("unchecked")
    								public void onSuccess(AMISResultVO vo) {

    									for(AMISCodesModelData topcode : vo.getCodes()) {
    										String startMonth = topcode.getLabel();
    										formData[3] = startMonth;
    									//	System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text start  1 : startMonth "+startMonth);
//    										start.setText(startMonth);
    										//start.setHtml("<div class='ccbs-StartMonthLabel' style='text-align: right;'>" + startMonth + "</div>");
    									//	start.setHtml("<div class='ccbs-MYLabel'>" + startMonth + "</div>");
//    										formData[4] = monthBefore;
    										String endMonth = getOneMonthBefore(startMonth);
    										formData[4] = endMonth;
//    										to.setText(endMonth);
    										//to.setHtml("<b>font-size:15" + monthBefore + " </b>");
//    										to.setText("/"+endMonth);	
    									//	to.setHtml("<div class='ccbs-MYLabel'>/"+endMonth + "</div>");
    										String startEndMonth = startMonth+ "/"+ endMonth;
    										start.setHtml("<div class='ccbs-MYLabel'>" + startEndMonth + "</div>");
//    										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: startMonth "+startMonth);
//    										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: endMonth "+endMonth);
//    										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: start.getText() "+start.getHtml());
//    										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: to.getText() "+to.getHtml());
    										if((formData[3]==null)||(formData[3].equals("")))
    										{
    											//No Marketing Year Available
    											//System.out.println("***************************|||||||||||||||||||||||||||||||||||Class: CCbsParametersPanel Function: commodityComboListener Text: CCBS.MARKETING_YEAR_AVAILABLE "+CCBS.MARKETING_YEAR_AVAILABLE);
    											CCBS.MARKETING_YEAR_AVAILABLE = true;
    											//System.out.println("***************************UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUClass: CCbsParametersPanel Function: commodityComboListener Text: CCBS.MARKETING_YEAR_AVAILABLE "+CCBS.MARKETING_YEAR_AVAILABLE);
    										}
    										radio.setValue(true);
    										c.setEnabled(false);
    									//	ccbswindow.getNorth().getLayout().layout();
    									}				
    								}
    								
    								public void onFailure(Throwable arg0) {
    					
    								}
    							});
    							
    						} catch (Exception e) {
    							e.printStackTrace();
    						}

						}
						
				//}
//					else
//					{
//						monthListBox.setValue(null);
//					}
			}
		};
		};
	}	
	
	//This function allows to reload the combo of the year that is in the north panel of the grid
	public static void reloadComboYearAfterTheSave(final String dataSource, final String country, final String countryCode, final String commodity, final String commodityCode,final RadioGroup radioGroup, final ComboBox<AMISCodesModelData> c, final Html start, final Html to, final Radio radio)
	{
	//		String commodity = se.getSelectedItem().getLabel();
//		formData[2] = commodity;
//		start.setHtml("<div class='ccbs-MYLabel'></div>");
//		to.setHtml("<div class='ccbs-MYLabel'></div>");
//		formData[3]="";
//		formData[4]="";
			//To calculate the index for the country code
//			int indexCountryName=0;
//			String countryCode= "";
//			for(String countryName:CCBS.COUNTRY_NAMES)
//			{
//				if(countryName.equalsIgnoreCase(formData[1]))
//				{
//					break;
//				}
//				indexCountryName++;
//			}
//			if(indexCountryName<CCBS.COUNTRY_CODES.size())
//			{
//				countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
//			}
			//To calculate the index for the commodity code
//			int indexCommodityName=0;
//			String commodityCode= "";
//			for(String commodityName:CCBS.COMMODITY_NAMES_CBS)
//			{
//				System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Text:commodityName "+commodityName);
//				System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Text:formData[2] "+formData[2]);
//				System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener indexCommodityName "+indexCommodityName);
//				System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Text:COMMODITY_CODES_CBS.get(indexCommodityName) "+CCBS.COMMODITY_CODES_CBS.get(indexCommodityName));
//				if(commodityName.equalsIgnoreCase(formData[2]))
//				{
//					System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener Before break indexCommodityName "+indexCommodityName);
//					break;
//				}
//				indexCommodityName++;
//			}
//			if(indexCommodityName<CCBS.COMMODITY_CODES_CBS.size())
//			{
//				commodityCode = CCBS.COMMODITY_CODES_CBS.get(indexCommodityName);
//				System.out.println("Class; CCBSParameterPanel  Function: commodityComboListener commodityCode= in if "+commodityCode);
//			}
			
//			if(!(formData[0].equals(CCBS.elementForTraining)))
		//It works only for the data to save.... so for the data of the country
			if(!(dataSource.equals(CCBS.elementForTraining)))
			{
				//System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: if(!(formData[0].equals(CCBS.elementForTraining))) ");
				//loadYearsForComboSelection(formData, countryCode, commodityCode, AMISConstants.YEARS.toString(), c);
				loadYearsForComboSelection(formData, countryCode, commodityCode, AMISConstants.YEARS.toString(), radioGroup, c, start, to, radio);
			}
			}
	
	public static SelectionListener<ButtonEvent> preloadAction(final String name, final String code, final String formData[], final AMISInput amisInput) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				CCBS.CHANGES_IN_THE_GRID = false;
				
				//To check all the elements
//				if(!checkSelectedElements())
//				{
//					 //MessageBox.alert("Alert", "Please fill all required fields!", null);
//				//	MessageBox.alert("Alert", "Please wait for Marketing Season loading!", null);
//				}
				
				//To check just the commodity
				if(!checkCommoditySelected())
				{
					MessageBox.alert("Alert", "Please fill all required fields!", null);
				//	MessageBox.alert("Alert", "Please wait for Marketing Season loading!", null);
				}
				else
				{
					//This part is used to fill the hashMap for the years, opening and closing stock
					AMISQueryVO qvoCheckData =  new AMISQueryVO();
					//The name data source to check inside the file
					qvoCheckData.setSelectedDataset(formData[0]);
					qvoCheckData.setTrainingDataset(AMISConstants.CBS.toString());
//					qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
					qvoCheckData.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
					AMISServiceEntry.getInstance().checkCountryData(qvoCheckData, false, new AsyncCallback<AMISResultVO>()
	                        {
	                            public void onSuccess(AMISResultVO result)
	                            {
	                        		AMISQueryVO qvo =  new AMISQueryVO();
	                            	if(result.getResultFromCbs())
	                            	{
	                            		//The dataset of the country is empty
	                            		qvo.setSelectedDataset(AMISConstants.CBS.toString());
	                            		qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
	                            	}
	                            	else
	                            	{
	                            		//The dataset of the country is full	                             
	                            		qvo.setSelectedDataset(formData[0]);
	                            		qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());		
	                            	}
	                            	String countryCode = ccbswindow.getCountryCodeFromName(formData[1]);
	                            	//String countryCode = amisInput.getCountrySelected().getCode();
                            		String commodityCode = ccbswindow.getCommodityCodeFromName(formData[2]);
                            		String elementCode = CCBS.OPENING_STOCKS_ELEMENT_CODE;
                            		qvo.setCountryCode(countryCode);
                            		qvo.setCommodityCode(commodityCode);//16, 18
                            		qvo.setElementCode(elementCode);
                            		qvo.setValueType("TOTAL");
                            	//	System.out.println("Class: CCBSParametersPanel Function: preloadAction Text :qvo.getSelectedDataset "+ qvo.getSelectedDataset()+" qvo.getSelectedDatasetFile "+ qvo.getSelectedDatasetFile()+" countryCode "+ countryCode+" commodityCode "+ commodityCode);
                            		
                            		AMISServiceEntry.getInstance().getOpeningClosingStocksForYear(qvo, CCBS.FIELD_NAMES_WITH_UNIT[5], formData[4], new AsyncCallback<HashMap<String, HashMap<String,String>>>() {
                            			@Override
                            			public void onSuccess(HashMap<String, HashMap<String, String>> ris) {
                            			//	System.out.println("Class: CCBSParametersPanel Function: preloadAction Text : on success");
                            					CCBS.OPENING_CLOSING_STOCKS_HASHMAP = ris;
                            					Set<String> keySet = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.keySet();
                            					Iterator<String> it = keySet.iterator();
                            				//	System.out.println("Class: CCBSParametersPanel Function: preloadAction Text : on success before while");
                            					while(it.hasNext())
                            					{
//                            						HashMap<String,String> hashmap = (HashMap<String,String>)it.next();
                            						String year = (String)it.next();
                            						HashMap<String,String> hashmap = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(year);
                            						if(hashmap!=null)
                            						{
                            							Set<String> keySet2 = hashmap.keySet();
                                    					Iterator<String> it2 = keySet2.iterator();
                                    					while(it2.hasNext())
                                    					{
                                    						String code = it2.next();
                                    					//	System.out.println("Class: CCBSParametersPanel Function: preloadAction Text :CCBS.OPENING_CLOSING_STOCKS_HASHMAP year = "+ year +" code = "+code+" value = "+ hashmap.get(code));
                                    					}
                            						}                            						
                            					}
                            				//	System.out.println("Class: CCBSParametersPanel Function: preloadAction Text : on success after while");
                            					String dataSource = formData[0];
                            					//	if((dataSource.equals(CCBSParametersPanel.elementForTraining))&&(!(formData[2].equalsIgnoreCase(CCBS.COMMODITY_WITHOUT_MARKETING_YEAR[0])))&&(!(formData[2].equalsIgnoreCase(CCBS.COMMODITY_WITHOUT_MARKETING_YEAR[1]))))
                            						
                            						if((!(formData[2].equalsIgnoreCase(CCBS.COMMODITY_WITHOUT_MARKETING_YEAR[0])))&&(!(formData[2].equalsIgnoreCase(CCBS.COMMODITY_WITHOUT_MARKETING_YEAR[1]))))
                            						{
                            							final AMISQueryVO qvo =  new AMISQueryVO();
                            							qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
                            						//	qvo.setTypeOfOutput(selectionType);	
                            							if((dataSource.equals(CCBS.elementForTraining))||dataSource.equals(CCBS.elementEmpty))
                            							{
                            								qvo.setSelectedDataset(AMISConstants.CBS.toString());
                            								qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
                            								qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
                            								qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
                            								qvo.setValueType("TOTAL");
                            								qvo.setSortingOrder("ASC");
                            								CCBS.LAST_MONTH_COMBO = ""+ formData[4];
                            							//	System.out.println("Class: CcbsParametersPanel Function: preloadAction Text: CCBS.LAST_MONTH_COMBO= "+CCBS.LAST_MONTH_COMBO);
                            								String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
                            								if(CCBS.LAST_MONTH_COMBO.equals(""))
                            								{
                            									lastMonthCombo = CCBS.N_A_MONTH;
                            								}
                            								try {
                            								//	System.out.println("Class: CcbsParametersPanel Function: preloadAction Text: before getDataFromAnElement 2 qvo.getSelectedDataset() "+ qvo.getSelectedDataset()+"qvo.getSelectedDatasetFile() "+ qvo.getSelectedDatasetFile());
                            									AMISServiceEntry.getInstance().getDataFromAnElement(qvo, CCBS.FIELD_NAMES_WITH_UNIT[5], lastMonthCombo, new AsyncCallback<List<HashMap<String, String>>>() {
                            										
                            										@SuppressWarnings("unchecked")
                            										public void onSuccess(List<HashMap<String, String>> list) {
                            						
                            											//CCBSParametersPanel.loadOtherYears();
                            											CCBS.MONTH_FORECAST_TO_QUERY = list;
                            											loadYears(formData[0], formData[1], ccbswindow.getCountryCodeFromName(formData[1]), formData[2], ccbswindow.getCommodityCodeFromName(formData[2]), AMISConstants.YEARS.toString(), name, code, formData);
                            										}
                            										
                            										public void onFailure(Throwable arg0) {
                            							
                            										}
                            									});
                            									
                            								} catch (Exception e) {
                            									e.printStackTrace();
                            								}
                            							}
                            							else
                            							{
                            								//if the data are specific for the dataSource of the country
                            								AMISQueryVO qvoCheck =  new AMISQueryVO();
                            								//The name data source to check inside the file
                            								qvoCheck.setSelectedDataset(formData[0]);
                            								qvoCheck.setTrainingDataset(AMISConstants.CBS.toString());
                            								//qvoCheck.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                            								qvoCheck.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
                            								AMISServiceEntry.getInstance().checkCountryData(qvoCheck, false, new AsyncCallback<AMISResultVO>()
                            				                        {
                            				                            public void onSuccess(AMISResultVO result)
                            				                            {
                            				                            	if(result.isResultFromCbs())
                            				                            	{
                            				                            		//The dataset of the country is empty... the data need to be taken from the file of the Cbs
                            				                            		qvo.setSelectedDataset(AMISConstants.CBS.toString());
                            				            						qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
                            				                            	}
                            				                            	else
                            				                            	{
                            				                            		//The dataset of the country is full... the data need to be taken from the file of the Country
                            				                            		qvo.setSelectedDataset(formData[0]);
                            				            						//qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                            				                            		qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
                            				                            	}

                            				                            	qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
                            				            					qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
                            				            					qvo.setValueType("TOTAL");
                            				            					qvo.setSortingOrder("ASC");
                            				            					CCBS.LAST_MONTH_COMBO = ""+ formData[4];
                            				            					String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
                            				            					if(CCBS.LAST_MONTH_COMBO.equals(""))
                            				            					{
                            				            						lastMonthCombo = CCBS.N_A_MONTH;
                            				            					}
                            				            					
                            				            				//	System.out.println("Class: CcbsParametersPanel Function: preloadAction Text: before getDataFromAnElement 1 qvo.getSelectedDataset() "+ qvo.getSelectedDataset()+"qvo.getSelectedDatasetFile() "+ qvo.getSelectedDatasetFile());
                            				                            	AMISServiceEntry.getInstance().getDataFromAnElement(qvo, CCBS.FIELD_NAMES_WITH_UNIT[5], lastMonthCombo, new AsyncCallback<List<HashMap<String, String>>>() {
                            											
                            												@SuppressWarnings("unchecked")
                            												public void onSuccess(List<HashMap<String, String>> list) {
                            								
                            													//CCBSParametersPanel.loadOtherYears();
                            													CCBS.MONTH_FORECAST_TO_QUERY = list;
                            													loadYears(formData[0], formData[1], ccbswindow.getCountryCodeFromName(formData[1]), formData[2], ccbswindow.getCommodityCodeFromName(formData[2]), AMISConstants.YEARS.toString(), name, code, formData);
                            												}
                            												
                            												public void onFailure(Throwable arg0) { 
                            									
                            												}
                            										});
                            							
                            									}
                            									
                            									public void onFailure(Throwable arg0) {
                            						
                            									}
                            								});	
                            							}
                            					}
                            						else
                            						{
                            							//New CASE: in that case the list CCBS.MONTH_FORECAST_TO_QUERY is empty because it doesn't take the year from the database
                            							loadYears(formData[0], formData[1], ccbswindow.getCountryCodeFromName(formData[1]), formData[2], ccbswindow.getCommodityCodeFromName(formData[2]), AMISConstants.YEARS.toString(), name, code, formData);
                            						}
                            			}
                            			@Override
                            			public void onFailure(Throwable arg0) {                         				
                            				
                            			}
									});
	                            }
					public void onFailure(Throwable arg0) {
						}
					});
			}
		}
		};
	}
	
	public static boolean checkSelectedElements()
	{
		//Everything is fine
		boolean res = true;
		for(int i = 0; i< formData.length; i++)
		{
			if((formData[i]!= null)&&(!formData[i].equals("")))
			{
				res = true;
			}
			else
			{
				res = false;
				break;
			}
		}
		//To ignore when the commodity is COARSE GRAINS or TOTAL CEREALS
	//	System.out.println("Class: CCBSParameterPanel Function:checkSelectedElements Text:Before check res = "+res);
		if(!res)
		{
		//	System.out.println("Class: CCBSParameterPanel Function:checkSelectedElements Text:formData[2] "+formData[2]);
			if((formData[2].equalsIgnoreCase(CCBS.COMMODITY_WITHOUT_MARKETING_YEAR[0]))||(formData[2].equalsIgnoreCase(CCBS.COMMODITY_WITHOUT_MARKETING_YEAR[1])))
			{
			//	System.out.println("Class: CCBSParameterPanel Function:checkSelectedElements Text:in if ");
				res = true;
			}
		}
		return res;
	}
	
	public static boolean checkCommoditySelected()
	{
		//Everything is fine
		boolean res = true;
		//formData[2] is the commodity
		if((formData[2]!= null)&&(!formData[2].equals("")))
		{
			res = true;
		}
		else
		{
			res = false;
		}
//		if((formData[3]==null)||(formData[3].equals("")))
//		{
//			//No Marketing Year Available
//			CCBS.MARKETING_YEAR_AVAILABLE = false;
//		}
		return res;
	}
	
	public static void loadYearsForComboSelection(final String formData[], final String countryCode, final String commodityCode, final String selectionType, final RadioGroup radioGroup, final ComboBox<AMISCodesModelData> c, final Html start, final Html to, final Radio radio)
	{//loadYearsForComboSelection(formData, countryCode, commodityCode, selectionType, name, code);
		String dataSource = formData[0];
		String countryName = formData[1];
		String commodityName = formData[2];
//		System.out.println("Class: CCbsParametersPanel Function: loadYearsForComboSelection Text: datasource "+dataSource);
//		System.out.println("Class: CCbsParametersPanel Function: loadYearsForComboSelection Text: formData[0] "+formData[0]);
//		System.out.println("Class: CCbsParametersPanel Function: loadYearsForComboSelection Text: formData[1] "+formData[1]);
//		System.out.println("Class: CCbsParametersPanel Function: loadYearsForComboSelection Text: formData[2] "+formData[2]);
//		System.out.println("Class: CCbsParametersPanel Function: loadYearsForComboSelection Text: formData[3] "+formData[3]);
		//radio.setValue(true);
		//This works only for the country ....
		if(!dataSource.equals(CCBS.elementForTraining))
		{
			AMISQueryVO qvoCheck =  new AMISQueryVO();
			//The name data source to check inside the file
			qvoCheck.setSelectedDataset(formData[0]);
			qvoCheck.setTrainingDataset(AMISConstants.CBS.toString());
			//qvoCheck.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
			qvoCheck.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
			AMISServiceEntry.getInstance().checkCountryData(qvoCheck, false, new AsyncCallback<AMISResultVO>()
                    {
                        public void onSuccess(AMISResultVO result)
                        {
                        	//This is the case of the country data
                			AMISQueryVO qvo =  new AMISQueryVO();
                			qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
                			//String selectionType = "AMIS_COUNTRIES";
                			qvo.setTypeOfOutput(selectionType);	
                			qvo.setCountryCode(countryCode);
                			qvo.setCommodityCode(commodityCode);
                			qvo.setSortingOrder("ASC");
                			
                			if(result.isResultFromCbs())
                			{
                				//The dataset of the country is empty... it's necessary to take the data from the dataset of the training
                				qvo.setSelectedDataset(AMISConstants.CBS.toString());
                				qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
                			}	
                			else
                			{
                				//The dataset is full... it's possible take the data from the dataset of the country
                				qvo.setSelectedDataset(formData[0]);
                				//qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                				qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
                			}
                		//	System.out.println("Class:CCbsParametersPanel Function:loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY size= "+ CCBS.MONTH_FORECAST_TO_QUERY.size());
            				for(int i=0; i<CCBS.MONTH_FORECAST_TO_QUERY.size(); i++) {
            					HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(i);
            					Set<String> keySet = hashMap.keySet();
//            					for(int j=0; j<keySet.size(); j++)
//            					{
//            						System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+ keySet.);
//            					}
            					Iterator it= keySet.iterator();
            					while(it.hasNext())
            					{
            						String label = (String)it.next();
            					//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 1 "+ label+" hashMap.get(label) " +hashMap.get(label));
            					}
            				}
                			
                			try {
                			//	AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {getYearsForComboBox
                				AMISServiceEntry.getInstance().getYearsForComboBox(qvo, new AsyncCallback<AMISResultVO>() {
                					@SuppressWarnings("unchecked")
                					public void onSuccess(AMISResultVO vo) {
                	
                	//					System.out.println("Class CCBSWindow  Function: loadYears Text: vo.getCodes().size():  "+vo.getCodes().size());
                	//					System.out.println("Class CCBSWindow  Function: loadYears Text: Before CCBS.YEARS.size():  "+CCBS.YEARS.size());
                						//int iYear=0;
                						String nextYear="";
                						CCBS.YEARS.clear();
                						CCBS.MONTH_FOR_YEARS.clear();
                						//System.out.println("Class CCBSWindow  Function: loadYears Text: Before 2  CCBS.YEARS.size():  "+CCBS.YEARS.size());
                						for(AMISCodesModelData topcode : vo.getCodes()) {
                							nextYear = followingYear(topcode.getLabel());
                							String year=topcode.getLabel() +"/"+nextYear.substring(2);
                						//	System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 1"+year + " topcode.getCode(): "+topcode.getCode());
                							CCBS.YEARS.add(year);
                						//	CCBS.MONTH_FOR_YEARS.put(year, aMISCbsUtility.convertNumberToLongMonth(topcode.getCode()));
                						}
                						//To store the last year for the combo box of the last column of the grid
                						int yearSize = CCBS.YEARS.size();
                						boolean firstYearComboSetted=false;
                						for(int i=0; i<CCBS.MONTH_FORECAST_TO_QUERY.size(); i++) {
                        					HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(i);
                        					Set<String> keySet = hashMap.keySet();
                        					Iterator it= keySet.iterator();
                        					String lastYearMonth;
                        					while((it.hasNext()&&(!firstYearComboSetted)))
                        					{
                        						String label = (String)it.next();
                        						int slashIndex = CCBS.YEARS.get((yearSize-1)).indexOf("/");
                        						String yearFromCCbsYearsList = CCBS.YEARS.get((yearSize-1)).substring(0, slashIndex);
                        						if(label.equalsIgnoreCase(yearFromCCbsYearsList))
                        						{
                        							//For the last year... that is in the database
                        							lastYearMonth = hashMap.get(label);
                        							if(lastYearMonth.equals("0"))
                        							{
                        								//These data are validated... it's not necassary show the year in the lastColumn of the grid
                        								int year = Integer.parseInt(label);
                        								int yearPlusOne = year+1;
                        								int yearPlusTwo = year+2;
                        								String yearPlusTwoS = ""+yearPlusTwo;
                        								yearPlusTwoS = yearPlusTwoS.substring(2);	
                        								CCBS.FIRST_YEAR_COMBO = yearPlusOne+"/"+yearPlusTwo;
                        								System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 1"+year + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
                        								CCBS.FIRST_MONTH_COMBO = "January";
                        								CCBS.LAST_MONTH_COMBO = "December";
                        							//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 Found ");
                        								firstYearComboSetted=true;
                        								break;
                        							}
                        						}
                        					//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 "+ label+" hashMap.get(label) " +hashMap.get(label));
                        					}
                        				}
                						
                					//	System.out.println("Class CCbsParametersPanel  Function: onSuccess Text: CCBS.YEARS.size: "+CCBS.YEARS.size() + " CCBS.MONTH_FOR_YEARS: "+CCBS.MONTH_FOR_YEARS.size()+" CCBS.MONTH_FORECAST_TO_QUERY.size()"+CCBS.MONTH_FORECAST_TO_QUERY.size());
                						Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
                						for(String key:keySet)
                						{
                					//		System.out.println("Class: CCbsParametersPanel Function: onSuccess key= "+key);
                					//		System.out.println("Class: CCbsParametersPanel Function: onSuccess CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
                						}
                					//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.FIRST_YEAR_COMBO label= 5 "+ CCBS.FIRST_YEAR_COMBO);
//                						System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO) label= 5 "+ CCBS.MONTH_FORECAST_TO_QUERY.get(CCBS.FIRST_YEAR_COMBO));
                					//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO) label= 5 "+ CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO));
                						if(!firstYearComboSetted)
                						{
                							CCBS.FIRST_YEAR_COMBO = CCBS.YEARS.get((yearSize-1));
                							System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 2 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
                							CCBS.FIRST_MONTH_COMBO = CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO);
                							//CCBS.FIRST_MONTH_COMBO = getOneMonthAfter(CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO));
                							CCBS.LAST_MONTH_COMBO = ""+ formData[4];
                							
                						}                				
                					//	System.out.println("Class CCbsParametersPanel  Function: onSuccess Text: CCBS.YEARS.size: "+CCBS.YEARS.size() + " CCBS.MONTH_FOR_YEARS: "+CCBS.MONTH_FOR_YEARS.size());
//                						Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
//                						for(String key:keySet)
//                						{
//                							System.out.println("Class: CCbsParametersPanel Function: onSuccess key= "+key);
//                							System.out.println("Class: CCbsParametersPanel Function: onSuccess CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
//                						}
                	//					for(String year : CCBS.YEARS) {
                	//						System.out.println("Class CCBSWindow  Function: loadYears Text: YEAR:  "+year);
                	//					}
                					//	System.out.println("Class CCBSWindow  Function: loadYears Text: After CCBS.YEARS.size():  "+CCBS.YEARS.size());
                					//	ccbswindow.getCenter().removeAll();
                						CCBSParametersPanel.loadOtherYears();
                						
                						//continueLoad(dataSource, countryName, commodityName, name, code, formData, countryCode, commodityCode);               	
//                						if(c.getStore()!=null)
//                						{
//                							System.out.println("Class: CCBSParametersPanel ************************************ c.getStore()getCount() "+ c.getStore().getCount());
//                							for(int i=0; i<c.getStore().getCount();i++)
//                							{
//                								System.out.println("Class: CCBSParametersPanel ************************************ c.getStore()getAt(i) "+ c.getStore().getAt(i).getLabel());
//                							}	
//                						}
                						c.getStore().removeAll();
//                						if(c.getStore()!=null)
//                						{
//                							System.out.println("Class: CCBSParametersPanel after remove all************************************ c.getStore()getCount() "+ c.getStore().getCount());
//                							for(int i=0; i<c.getStore().getCount();i++)
//                							{
//                								System.out.println("Class: CCBSParametersPanel after remove all************************************ c.getStore()getAt(i) "+ c.getStore().getAt(i).getLabel());
//                							}	
//                						}
                						PagePanelController.fillLastYearStore();
                						
                						//c.setStore(CCBS.LAST_YEAR);
                						for(int i=0; i< CCBS.LAST_YEAR.getCount(); i++)
                						{
                							c.getStore().add(CCBS.LAST_YEAR.getAt(i));
                						}
//                						if(c.getStore()!=null)
//                						{
//                							System.out.println("Class: CCBSParametersPanel ************************************ c.getStore()getCount() "+ c.getStore().getCount());
//                							for(int i=0; i<c.getStore().getCount();i++)
//                							{
//                								System.out.println("Class: CCBSParametersPanel ************************************ c.getStore()getAt(i) "+ c.getStore().getAt(i).getLabel());
//                							}	
//                						}
                						Radio r = (Radio)radioGroup.get(0);
                						if(r!=null)
                						{
                							int dim = CCBS.LAST_YEAR.getCount();
                							r.setBoxLabel(CCBS.LAST_YEAR.getAt(dim-1).getLabel());
                						}
                						c.setValue(CCBS.LAST_YEAR.getAt(0));
                					//	PagePanelController.setLastYearToShow(c.getStore().getAt(0).getLabel());
                						PagePanelController.setLastYearToShow(radio.getBoxLabel());
                						radioGroup.enable();
                						//ccbswindow.getCenter().getLayout().layout();
                						//System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: countryCode "+countryCode);
                						//System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: commodityCode "+commodityCode);
                						AMISQueryVO qvo =  new AMISQueryVO();
                						qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
                						String selectionType = "AMIS_MARKETING_YEAR";
                						qvo.setTypeOfOutput(selectionType);	                					
                						qvo.setCountryCode(countryCode);
                						qvo.setCommodityCode(commodityCode);
                						final boolean firstYearComboSettedService = firstYearComboSetted;                	   
                						try {
                							AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
                								
                								@SuppressWarnings("unchecked")
                								public void onSuccess(AMISResultVO vo) {

                									for(AMISCodesModelData topcode : vo.getCodes()) {
                										String startMonth = topcode.getLabel();
                										formData[3] = startMonth;
                									//	System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text start 2  : startMonth "+startMonth);
//                										start.setText(startMonth);
                										//start.setHtml("<div class='ccbs-StartMonthLabel' style='text-align: right;'>" + startMonth + "</div>");
                									//	start.setHtml("<div class='ccbs-MYLabel'>" + startMonth + "</div>");
//                										formData[4] = monthBefore;
                										String endMonth = getOneMonthBefore(startMonth);
                										formData[4] = endMonth;
//                										to.setText(endMonth);
                										//to.setHtml("<b>font-size:15" + monthBefore + " </b>");
//                										to.setText("/"+endMonth);	
                									//	to.setHtml("<div class='ccbs-MYLabel'>/"+endMonth + "</div>");
                										String startEndMonth = startMonth+ "/"+ endMonth;
                										start.setHtml("<div class='ccbs-MYLabel'>" + startEndMonth + "</div>");
                										
//                										if(!firstYearComboSettedService)
//                                						{                                		
//                                							CCBS.FIRST_MONTH_COMBO = getOneMonthAfter(CCBS.FIRST_YEAR_COMBO);                          						
//                                					                                							
//                                						}    
                										if((formData[3]==null)||(formData[3].equals("")))
                										{
                											//No Marketing Year Available
                										//	System.out.println("***************************GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGClass: CCbsParametersPanel Function: commodityComboListener Text:2 CCBS.MARKETING_YEAR_AVAILABLE "+CCBS.MARKETING_YEAR_AVAILABLE);
                											CCBS.MARKETING_YEAR_AVAILABLE = true;
                										//	System.out.println("***************************GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGClass: CCbsParametersPanel Function: commodityComboListener Text:2 CCBS.MARKETING_YEAR_AVAILABLE "+CCBS.MARKETING_YEAR_AVAILABLE);
                										}
                										radio.setValue(true);
                										c.setEnabled(false);
                									//	ccbswindow.getNorth().getLayout().layout();
//                										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: startMonth "+startMonth);
//                										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: endMonth "+endMonth);
//                										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: start.getText() "+start.getHtml());
//                										System.out.println("Class: CCbsParametersPanel Function: commodityComboListener Text: to.getText() "+to.getHtml());
                									}				
                								}
                								
                								public void onFailure(Throwable arg0) {
                					
                								}
                							});
                							
                						} catch (Exception e) {
                							e.printStackTrace();
                						}
                					}
                					
                					public void onFailure(Throwable arg0) {
                		
                					}
                				});
                				
                			} catch (Exception e) {
                				e.printStackTrace();
                			}
                			
                        }
				
				public void onFailure(Throwable arg0) {
	
				}
			});	
		}
	}
	
	public static void loadYears(final String dataSource, final String countryName, final String countryCode, final String commodityName,final String commodityCode, final String selectionType, final String name, final String code, final String formData[])
	{
//		System.out.println("Class: CCbsParametersPanel Function: loadYears Text: datasource "+dataSource);
//		System.out.println("Class: CCbsParametersPanel Function: loadYears Text: formData[0] "+formData[0]);
//		System.out.println("Class: CCbsParametersPanel Function: loadYears Text: formData[1] "+formData[1]);
//		System.out.println("Class: CCbsParametersPanel Function: loadYears Text: formData[2] "+formData[2]);
//		System.out.println("Class: CCbsParametersPanel Function: loadYears Text: formData[3] "+formData[3]);
		if(dataSource.equals(CCBS.elementForTraining))
		{
			//AMISServiceEntry.getInstance().askAMIS(qvo, callback)
			AMISQueryVO qvo =  new AMISQueryVO();
			qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
			//String selectionType = "AMIS_COUNTRIES";
			qvo.setTypeOfOutput(selectionType);	
//			if(dataSource.equals("FAOSTAT"))
//			{
//				qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
//			}
//			else if(dataSource.equals("PSD"))
//			{
//				qvo.setSelectedDataset(AMISConstants.PSD.toString());
//			}
//			else if(dataSource.equals(CCBSParametersPanel.firstElement))
//			{
//				qvo.setSelectedDataset(AMISConstants.CBS.toString());
//			}
			if(dataSource.equals(CCBS.elementForTraining))
			{
				qvo.setSelectedDataset(AMISConstants.CBS.toString());
				qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
			}
			qvo.setCountryCode(countryCode);
			qvo.setCommodityCode(commodityCode);
			qvo.setSortingOrder("ASC");
			
			try {
			//	AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				AMISServiceEntry.getInstance().getYearsWithTheBestMonthForForecast(qvo, CCBS.MONTH_FORECAST_TO_QUERY, new AsyncCallback<AMISResultVO>() {
					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {
	
	//					System.out.println("Class CCBSWindow  Function: loadYears Text: vo.getCodes().size():  "+vo.getCodes().size());
	//					System.out.println("Class CCBSWindow  Function: loadYears Text: Before CCBS.YEARS.size():  "+CCBS.YEARS.size());
						//int iYear=0;
						String nextYear="";
						CCBS.YEARS.clear();
						CCBS.MONTH_FOR_YEARS.clear();
						//System.out.println("Class CCBSWindow  Function: loadYears Text: Before 2  CCBS.YEARS.size():  "+CCBS.YEARS.size());
						for(AMISCodesModelData topcode : vo.getCodes()) {
							nextYear = followingYear(topcode.getLabel());
							String year=topcode.getLabel() +"/"+nextYear.substring(2);
						//	System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 2"+year + " topcode.getCode(): "+topcode.getCode());
							CCBS.YEARS.add(year);
							CCBS.MONTH_FOR_YEARS.put(year, aMISCbsUtility.convertNumberToLongMonth(topcode.getCode()));
						}
						//To store the last year for the combo box of the last column of the grid
						int yearSize = CCBS.YEARS.size();
						boolean firstYearComboSetted=false;
						for(int i=0; i<CCBS.MONTH_FORECAST_TO_QUERY.size(); i++) {
        					HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(i);
        					Set<String> keySet = hashMap.keySet();
        					Iterator it= keySet.iterator();
        					String lastYearMonth;
        					while((it.hasNext()&&(!firstYearComboSetted)))
        					{
        						String label = (String)it.next();
        						int slashIndex = CCBS.YEARS.get((yearSize-1)).indexOf("/");
        						String yearFromCCbsYearsList = CCBS.YEARS.get((yearSize-1)).substring(0, slashIndex);
        						if(label.equalsIgnoreCase(yearFromCCbsYearsList))
        						{
        							//For the last year... that is in the database
        							lastYearMonth = hashMap.get(label);
        							if(lastYearMonth.equals("0"))
        							{
        								//These data are validated... it's not necassary show the year in the lastColumn of the grid
        								int year = Integer.parseInt(label);
        								int yearPlusOne = year+1;
        								int yearPlusTwo = year+2;
        								String yearPlusTwoS = ""+yearPlusTwo;
        								yearPlusTwoS = yearPlusTwoS.substring(2);	        							
        								CCBS.FIRST_YEAR_COMBO = yearPlusOne+"/"+yearPlusTwo;
        								System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 3 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
        								CCBS.FIRST_MONTH_COMBO = "January";
        								CCBS.LAST_MONTH_COMBO = "December";
        								//System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 Found ");
        								firstYearComboSetted=true;
        								break;
        							}
        						}
        					//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 "+ label+" hashMap.get(label) " +hashMap.get(label));
        					}
        				}
						if(!firstYearComboSetted)
						{
							CCBS.FIRST_YEAR_COMBO = CCBS.YEARS.get((yearSize-1));
							System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 4 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
							CCBS.FIRST_MONTH_COMBO = CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO);
							//CCBS.FIRST_MONTH_COMBO = getOneMonthAfter(CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO));
							CCBS.LAST_MONTH_COMBO = ""+ formData[4];
						}
					//	System.out.println("Class CCbsParametersPanel  Function: onSuccess Text: CCBS.YEARS.size: "+CCBS.YEARS.size() + " CCBS.MONTH_FOR_YEARS: "+CCBS.MONTH_FOR_YEARS.size());
						Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
//						for(String key:keySet)
//						{
//							System.out.println("Class: CCbsParametersPanel Function: onSuccess key= "+key);
//							System.out.println("Class: CCbsParametersPanel Function: onSuccess CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
//						}
	//					for(String year : CCBS.YEARS) {
	//						System.out.println("Class CCBSWindow  Function: loadYears Text: YEAR:  "+year);
	//					}
					//	System.out.println("Class CCBSWindow  Function: loadYears Text: After CCBS.YEARS.size():  "+CCBS.YEARS.size());
						ccbswindow.getCenter().removeAll();
						CCBSParametersPanel.loadOtherYears();
						continueLoad(dataSource, countryName, commodityName, name, code, formData, countryCode, commodityCode);
	//					bookPanel = ccbswindow.buildCenterPanel(countryName, commodityName);
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel");
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel name " +name);
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel code " +code );
	//					//loadAction(name, code, formData, bookPanel);
	//					loadAction(code, name, formData, bookPanel);
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after loadAction");
	//				
	//					ccbswindow.getCenter().add(bookPanel);
	//					ccbswindow.getSave().setEnabled(true);
	//					ccbswindow.getCancel().setEnabled(true);
	//					ccbswindow.getCenter().getLayout().layout();
	
					}
					
					public void onFailure(Throwable arg0) {
		
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(dataSource.equals(CCBS.elementEmpty))
		{
			String nextYear="";
			CCBS.YEARS.clear();
			CCBS.MONTH_FOR_YEARS.clear();
			String lastYearToShow = PagePanelController.getLastYearToShow().substring(0, 4);
			
			int yearInt = Integer.parseInt(lastYearToShow)-(CCBS.NUMBER_OF_YEARS-1);
			String firstYearToShow = "";
			for(int i=0; i<6; i++)
			{
				firstYearToShow = ""+yearInt;
				nextYear = followingYear(firstYearToShow);
				String year =firstYearToShow +"/"+nextYear.substring(2);
				CCBS.YEARS.add(year);
				//It'll contain the last forecast month to show in the column
				CCBS.MONTH_FOR_YEARS.put(year,"");
				yearInt++;
			}
			
			//Request to fill the last column of the grid (year for forecast)\
			AMISQueryVO qvo =  new AMISQueryVO();
			qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
			qvo.setTypeOfOutput(selectionType);	
			qvo.setSelectedDataset(AMISConstants.CBS.toString());
			qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
			qvo.setCountryCode(countryCode);
			qvo.setCommodityCode(commodityCode);
			qvo.setSortingOrder("ASC");
			
			try {
				AMISServiceEntry.getInstance().getYearsWithTheBestMonthForForecast(qvo, CCBS.MONTH_FORECAST_TO_QUERY, new AsyncCallback<AMISResultVO>() {
			//	AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
					
					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {
						String nextYear="";
						List<String> yearApp = new ArrayList<String>();
						HashMap<String, String> monthForYear= new HashMap<String, String>();
						//CCBS.YEARS.clear();
						//CCBS.MONTH_FOR_YEARS.clear();
						for(AMISCodesModelData topcode : vo.getCodes()) {
							nextYear = followingYear(topcode.getLabel());
							String year=topcode.getLabel() +"/"+nextYear.substring(2);
							//System.out.println("Class CCbsParametersPanel  Function: onSuccess Text: year: "+year + " topcode.getCode(): "+topcode.getCode());
							yearApp.add(year);
							//monthForYear.put(year,topcode.getCode());
							String month = aMISCbsUtility.convertNumberToLongMonth(topcode.getCode());
							monthForYear.put(year,month);
							if(CCBS.MONTH_FOR_YEARS.get(year)!=null)
							{
								CCBS.MONTH_FOR_YEARS.put(year, month);
							}
							
						//	CCBS.YEARS.add(year);
						//	CCBS.MONTH_FOR_YEARS.put(year, aMISCbsUtility.convertNumberToLongMonth(topcode.getCode()));
							//CCBS.YEARS.add(year);
							//CCBS.MONTH_FOR_YEARS.put(year,topcode.getCode());
						}
												
						//To store the last year for the combo box of the last column of the grid
						int yearSize = yearApp.size();
						boolean firstYearComboSetted=false;
						for(int i=0; i<CCBS.MONTH_FORECAST_TO_QUERY.size(); i++) {
        					HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(i);
        					Set<String> keySet = hashMap.keySet();
        					Iterator it= keySet.iterator();
        					String lastYearMonth;
        					while((it.hasNext()&&(!firstYearComboSetted)))
        					{
        						String label = (String)it.next();
        						int slashIndex = CCBS.YEARS.get((yearSize-1)).indexOf("/");
        						String yearFromCCbsYearsList = CCBS.YEARS.get((yearSize-1)).substring(0, slashIndex);
        						if(label.equalsIgnoreCase(yearFromCCbsYearsList))
        						{
        							//For the last year... that is in the database
        							lastYearMonth = hashMap.get(label);
        							if(lastYearMonth.equals("0"))
        							{
        								//These data are validated... it's not necassary show the year in the lastColumn of the grid
        								int year = Integer.parseInt(label);
        								int yearPlusOne = year+1;
        								int yearPlusTwo = year+2;
        								String yearPlusTwoS = ""+yearPlusTwo;
        								yearPlusTwoS = yearPlusTwoS.substring(2);        								
        								CCBS.FIRST_YEAR_COMBO = yearPlusOne+"/"+yearPlusTwo;
        								System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 5 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
        								CCBS.FIRST_MONTH_COMBO = "January";
        								CCBS.LAST_MONTH_COMBO = "December";
        								//System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 Found ");
        								firstYearComboSetted=true;
        								break;
        							}
        						}
        						//System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 "+ label+" hashMap.get(label) " +hashMap.get(label));
        					}
        				}
						if(!firstYearComboSetted)
						{
							CCBS.FIRST_YEAR_COMBO = yearApp.get((yearSize-1));
							System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 6 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
							CCBS.FIRST_MONTH_COMBO = monthForYear.get(CCBS.FIRST_YEAR_COMBO);
							//CCBS.FIRST_MONTH_COMBO = getOneMonthAfter(CCBS.FIRST_YEAR_COMBO);
							CCBS.LAST_MONTH_COMBO = ""+ formData[4];
						}						
						ccbswindow.getCenter().removeAll();
						CCBSParametersPanel.loadOtherYears();
						continueLoad(dataSource, countryName, commodityName, name, code, formData, countryCode, commodityCode);
	//					bookPanel = ccbswindow.buildCenterPanel(countryName, commodityName);
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel");
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel name " +name);
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel code " +code );
	//					//loadAction(name, code, formData, bookPanel);
	//					loadAction(code, name, formData, bookPanel);
	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after loadAction");
	//				
	//					ccbswindow.getCenter().add(bookPanel);
	//					ccbswindow.getSave().setEnabled(true);
	//					ccbswindow.getCancel().setEnabled(true);
	//					ccbswindow.getCenter().getLayout().layout();
	
					}
					
					public void onFailure(Throwable arg0) {
		
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			AMISQueryVO qvoCheck =  new AMISQueryVO();
			//The name data source to check inside the file
			qvoCheck.setSelectedDataset(formData[0]);
			qvoCheck.setTrainingDataset(AMISConstants.CBS.toString());
//			qvoCheck.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
			qvoCheck.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
			AMISServiceEntry.getInstance().checkCountryData(qvoCheck, false, new AsyncCallback<AMISResultVO>()
                    {
                        public void onSuccess(AMISResultVO result)
                        {
                        	//This is the case of the country data
                			AMISQueryVO qvo =  new AMISQueryVO();
                			qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
                			//String selectionType = "AMIS_COUNTRIES";
                			qvo.setTypeOfOutput(selectionType);	
                			qvo.setCountryCode(countryCode);
                			qvo.setCommodityCode(commodityCode);
                			qvo.setSortingOrder("ASC");
                			
                			if(result.isResultFromCbs())
                			{
                				//The dataset of the country is empty... it's necessary to take the data from the dataset of the training
                				qvo.setSelectedDataset(AMISConstants.CBS.toString());
                				qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
                			}	
                			else
                			{
                				//The dataset is full... it's possible take the data from the dataset of the country
                				qvo.setSelectedDataset(formData[0]);
//                				qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                				qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
                			}
             
                			try {
                				//System.out.println("Class:CCbsParametersPanel Function:getYearsWithTheBestMonthForForecast Text: CCBS.MONTH_FORECAST_TO_QUERY size= "+ CCBS.MONTH_FORECAST_TO_QUERY.size());
                				for(int i=0; i<CCBS.MONTH_FORECAST_TO_QUERY.size(); i++) {
                					HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(i);
                					Set<String> keySet = hashMap.keySet();
//                					for(int j=0; j<keySet.size(); j++)
//                					{
//                						System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+ keySet.);
//                					}
                					Iterator it= keySet.iterator();
                					while(it.hasNext())
                					{
                						String label = (String)it.next();
                					//	System.out.println("Class:CCbsParametersPanel Function:getYearsWithTheBestMonthForForecast Text: CCBS.MONTH_FORECAST_TO_QUERY label= "+ label+" hashMap.get(label) " +hashMap.get(label));
                					}
                					
                				}
                			//	AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
                				AMISServiceEntry.getInstance().getYearsWithTheBestMonthForForecast(qvo, CCBS.MONTH_FORECAST_TO_QUERY, new AsyncCallback<AMISResultVO>() {
                					@SuppressWarnings("unchecked")
                					public void onSuccess(AMISResultVO vo) {
                	
                	//					System.out.println("Class CCBSWindow  Function: loadYears Text: vo.getCodes().size():  "+vo.getCodes().size());
                	//					System.out.println("Class CCBSWindow  Function: loadYears Text: Before CCBS.YEARS.size():  "+CCBS.YEARS.size());
                						//int iYear=0;
                						String nextYear="";
                						CCBS.YEARS.clear();
                						CCBS.MONTH_FOR_YEARS.clear();
                						//System.out.println("Class CCBSWindow  Function: loadYears Text: Before 2  CCBS.YEARS.size():  "+CCBS.YEARS.size());
                						if((vo==null)||(vo.getCodes()==null)||(vo.getCodes().size()==0))
                						{//CCBS.MONTH_FORECAST_TO_QUERY size
                							MessageBox.alert("Info", "No data available for this selection! Please try with another selection!", null);
                						}
                						for(AMISCodesModelData topcode : vo.getCodes()) {
                							nextYear = followingYear(topcode.getLabel());
                							String year=topcode.getLabel() +"/"+nextYear.substring(2);
                						//	System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 3"+year + " topcode.getCode(): "+topcode.getCode());
                							CCBS.YEARS.add(year);
                							CCBS.MONTH_FOR_YEARS.put(year, aMISCbsUtility.convertNumberToLongMonth(topcode.getCode()));
                						}
                						for(int z=0; z <CCBS.YEARS.size();z++)
                						{
                							System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: Before 7/8 CCBS.YEAR(Z) = "+CCBS.YEARS.get(z));
                						}
               
                						//To store the last year for the combo box of the last column of the grid
                						int yearSize = CCBS.YEARS.size();
                						boolean firstYearComboSetted=false;
                						System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: Before 7/8 CCBS.MONTH_FORECAST_TO_QUERY.size() "+CCBS.MONTH_FORECAST_TO_QUERY.size());
                						for(int i=0; i<CCBS.MONTH_FORECAST_TO_QUERY.size(); i++) {
                        					HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(i);
                        					Set<String> keySet = hashMap.keySet();
                        					Iterator it= keySet.iterator();
                        					String lastYearMonth;
                        					System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: Before 7/8 Before while((it.hasNext()&&(!firstYearComboSetted))) ");
                        					while((it.hasNext()&&(!firstYearComboSetted)))
                        					{
                        						System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: Before 7/8 Inside while((it.hasNext()&&(!firstYearComboSetted))) ");
                        						String label = (String)it.next();
                        						System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: Before 7/8 Inside label =  "+label);
                        						int slashIndex = CCBS.YEARS.get((yearSize-1)).indexOf("/");
                        						String yearFromCCbsYearsList = CCBS.YEARS.get((yearSize-1)).substring(0, slashIndex);
                        						if(label.equalsIgnoreCase(yearFromCCbsYearsList))
                        						{
                        							//For the last year... that is in the database
                        							lastYearMonth = hashMap.get(label);
                        							if(lastYearMonth.equals("0"))
                        							{
                        								//These data are validated... it's not necassary show the year in the lastColumn of the grid
                        								int year = Integer.parseInt(label);
                        								int yearPlusOne = year+1;
                        								int yearPlusTwo = year+2;
                        								String yearPlusTwoS = ""+yearPlusTwo;
                        								yearPlusTwoS = yearPlusTwoS.substring(2);	
                        								CCBS.FIRST_YEAR_COMBO = yearPlusOne+"/"+yearPlusTwo;
                        								System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 7 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
                        								CCBS.FIRST_MONTH_COMBO = "January";
                        								CCBS.LAST_MONTH_COMBO = "December";
                        							//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 Found ");
                        								firstYearComboSetted=true;
                        								break;
                        							}
                        						}
                        						//System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 5 "+ label+" hashMap.get(label) " +hashMap.get(label));
                        					}
                        				}
                						if(!firstYearComboSetted)
                						{
                							CCBS.FIRST_YEAR_COMBO = CCBS.YEARS.get((yearSize-1));
                							System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 8 yearSize "+yearSize + " CCBS.FIRST_YEAR_COMBO: "+CCBS.FIRST_YEAR_COMBO);
                							CCBS.FIRST_MONTH_COMBO = CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO);
                							System.out.println("Class CCbsParametersPanel  Function: onSuccess of loadYears Text: year: 8 CCBS.FIRST_MONTH_COMBO "+CCBS.FIRST_MONTH_COMBO );
                							//CCBS.FIRST_MONTH_COMBO = getOneMonthAfter(CCBS.MONTH_FOR_YEARS.get(CCBS.FIRST_YEAR_COMBO));
                							CCBS.LAST_MONTH_COMBO = ""+ formData[4];
                						}
                					//	System.out.println("Class:CCbsParametersPanel Function: loadYearsForComboSelection Text: CCBS.MONTH_FORECAST_TO_QUERY label= 1 CCBS.FIRST_YEAR_COMBO "+ CCBS.FIRST_YEAR_COMBO);
                						
                						
                						//System.out.println("Class CCbsParametersPanel  Function: onSuccess Text: CCBS.YEARS.size: "+CCBS.YEARS.size() + " CCBS.MONTH_FOR_YEARS: "+CCBS.MONTH_FOR_YEARS.size());
                						Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
                						for(String key:keySet)
                						{
                							System.out.println("Class: CCbsParametersPanel Function: onSuccess key= "+key);
                							System.out.println("Class: CCbsParametersPanel Function: onSuccess CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
                						}
                	//					for(String year : CCBS.YEARS) {
                	//						System.out.println("Class CCBSWindow  Function: loadYears Text: YEAR:  "+year);
                	//					}
                					//	System.out.println("Class CCBSWindow  Function: loadYears Text: After CCBS.YEARS.size():  "+CCBS.YEARS.size());
                						ccbswindow.getCenter().removeAll();
                						CCBSParametersPanel.loadOtherYears();
                						continueLoad(dataSource, countryName, commodityName, name, code, formData, countryCode, commodityCode);
                	//					bookPanel = ccbswindow.buildCenterPanel(countryName, commodityName);
                	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel");
                	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel name " +name);
                	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel code " +code );
                	//					//loadAction(name, code, formData, bookPanel);
                	//					loadAction(code, name, formData, bookPanel);
                	//					System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after loadAction");
                	//				
                	//					ccbswindow.getCenter().add(bookPanel);
                	//					ccbswindow.getSave().setEnabled(true);
                	//					ccbswindow.getCancel().setEnabled(true);
                	//					ccbswindow.getCenter().getLayout().layout();
                	
                					}
                					
                					public void onFailure(Throwable arg0) {
                		
                					}
                				});
                				
                			} catch (Exception e) {
                				e.printStackTrace();
                			}
                			
                        }
				
				public void onFailure(Throwable arg0) {
	
				}
			});			
		}
	}
	
	public static void continueLoad(final String dataSource, final String countryName, final String commodityName, final String name, final String code, final String formData[], final String countryCode, final String commodityCode)
	{		
//		System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: datasource "+dataSource);
//		System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: formData[0] "+formData[0]);
//		System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: formData[1] "+formData[1]);
//		System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: formData[2] "+formData[2]);
//		System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: formData[3] "+formData[3]);
		
		//The commodity will be loaded based on the crops
	//	System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: dataSource if");
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.AMIS_NUM_CROP_COUNT.toString());			
//		if(dataSource.equals(CCBS.elementForTraining))
//		{
//			qvo.setSelectedDataset(AMISConstants.CBS.toString());
//		}
		
		qvo.setCountryCode(countryCode);
		qvo.setCommodityCode(commodityCode);
		
		try {//Taking the code and label for the real elements in the database
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
					String cropNum = "";
					for(AMISCodesModelData topcode : vo.getCodes()) {
						cropNum = topcode.getLabel();
					//	 System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: topcode.getLabel() ="+topcode.getLabel());
					}
					final String cropNum2 = cropNum;
					//System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: cropNum ="+cropNum);
					if(dataSource.equals(CCBS.elementForTraining))
					{
						//Case element for training... Cbs
						AMISQueryVO qvo =  new AMISQueryVO();
						qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
						//String selectionType = "AMIS_COUNTRIES";
						qvo.setTypeOfOutput(AMISConstants.AMIS_ELEMENTS.toString());
						qvo.setElementsWithoutUnit(true);
						qvo.setSelectedDataset("CBS");
						try {//Taking the real elements form the database
							
						AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
							
							@SuppressWarnings("unchecked")
							public void onSuccess(AMISResultVO vo) {
								//String cropNum = "";
								CCBS.NAMES_CODES_FIELD.clear();
								CCBS.SUB_ELEMENTS_WITH_CODE.clear();
								CCBS.OTHER_NAMES_CODES_FIELD.clear();
							//	System.out.println("Class: *********CCbsParametersPanel onsucces ");
								
								for(AMISCodesModelData topcode : vo.getCodes()) {
								//	System.out.println("Class: *********CCbsParametersPanel topcode.getLabel() "+topcode.getLabel());
								//	System.out.println("Class: *********CCbsParametersPanel topcode.getCode() "+topcode.getCode());
									if(!isSubElement(topcode.getLabel()))
									{
										//System.out.println("Class: *********CCbsParametersPanel topcode.getLabel() "+topcode.getLabel());
										//System.out.println("Class: *********CCbsParametersPanel topcode.getCode() "+topcode.getCode());
										//This element belongs to OTHER_ELEMENTS_NAMES
										if(isOtherElement(otherChangeLabel(topcode.getLabel())))
										{
									//		System.out.println("Class: *********CCbsParametersPanel isOtherElement topcode.getLabel() "+topcode.getLabel());
											HashMap hashMap = new HashMap<String, String>();
											hashMap.put(topcode.getCode(), topcode.getLabel());
											CCBS.OTHER_NAMES_CODES_FIELD.put(otherChangeLabel(topcode.getLabel()), hashMap);
										}
										else
										{			
											//For Element
											HashMap hashMap = new HashMap<String, String>();
											hashMap.put(topcode.getCode(), topcode.getLabel());
											CCBS.NAMES_CODES_FIELD.put(changeLabel(topcode.getLabel()), hashMap);
										}
									}
									else
									{
										//For SubElement: subelement name, subelement code
										//System.out.println("Class: *********CCbsParametersPanel SubElements topcode.getLabel() "+topcode.getLabel());
										//System.out.println("Class: *********CCbsParametersPanel SubElements topcode.getCode() "+topcode.getCode());
										CCBS.SUB_ELEMENTS_WITH_CODE.put(topcode.getLabel(), topcode.getCode());
									}
								}
								
//								System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: CCBS.NAMES_CODES_FIELD element "+ element);
//								Set<String> keySet2 = CCBS.NAMES_CODES_FIELD.get(element).keySet();
//								for(String key: keySet2)
//								{
//									System.out.println("CCBS.NAMES_CODES_FIELD key "+ key);
//									System.out.println("CCBS.NAMES_CODES_FIELD Value "+ CCBS.NAMES_CODES_FIELD.get(key));
//								}
								
								bookPanel = ccbswindow.buildCenterPanel(countryName, countryCode, commodityName, commodityCode, cropNum2, dataSource, formData[3]);
							//	System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel");
							//	System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel name " +name);
							//	System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel code " +code );
								//loadAction(name, code, formData, bookPanel);
								loadAction(code, name, formData, bookPanel);
							//	System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after loadAction");
							
								ccbswindow.getCenter().add(bookPanel);
								ccbswindow.getSave().setEnabled(true);
								ccbswindow.getCancel().setEnabled(true);
								ccbswindow.getSaveAndClose().setEnabled(true);
								ccbswindow.getCenter().getLayout().layout();
							}
								
								public void onFailure(Throwable arg0) {
									
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}	
						
					}
					else
					{
						//Case Country
					//	System.out.println("Class: CCbsParametersPanel Function: continueLoad Text: dataSource else");
						AMISQueryVO qvo =  new AMISQueryVO();
						qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
						//String selectionType = "AMIS_COUNTRIES";
						qvo.setTypeOfOutput(AMISConstants.AMIS_ELEMENTS.toString());
						qvo.setElementsWithoutUnit(true);
						//qvo.setSelectedDataset("CBS");
						try {//Taking the real elements form the database
							
						AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
							
							@SuppressWarnings("unchecked")
							public void onSuccess(AMISResultVO vo) {
								//String cropNum = "";
								CCBS.NAMES_CODES_FIELD.clear();
								CCBS.SUB_ELEMENTS_WITH_CODE.clear();
								CCBS.OTHER_NAMES_CODES_FIELD.clear();
								for(AMISCodesModelData topcode : vo.getCodes()) {

								//	System.out.println("Class: *********CCbsParametersPanel topcode.getLabel() "+topcode.getLabel());
								//	System.out.println("Class: *********CCbsParametersPanel topcode.getCode() "+topcode.getCode());
									if(!isSubElement(topcode.getLabel()))
									{
										//This element belongs to OTHER_ELEMENTS_NAMES
										if(isOtherElement(otherChangeLabel(topcode.getLabel())))
										{
											HashMap hashMap = new HashMap<String, String>();
											hashMap.put(topcode.getCode(), topcode.getLabel());
											CCBS.OTHER_NAMES_CODES_FIELD.put(otherChangeLabel(topcode.getLabel()), hashMap);
										}
										else
										{							
											HashMap hashMap = new HashMap<String, String>();
											hashMap.put(topcode.getCode(), topcode.getLabel());
											CCBS.NAMES_CODES_FIELD.put(changeLabel(topcode.getLabel()), hashMap);
										}
									}
									else
									{
										//For SubElement: subelement name, subelement code
										//System.out.println("Class: *********CCbsParametersPanel SubElements topcode.getLabel() "+topcode.getLabel());
										//System.out.println("Class: *********CCbsParametersPanel SubElements topcode.getCode() "+topcode.getCode());
										CCBS.SUB_ELEMENTS_WITH_CODE.put(topcode.getLabel(), topcode.getCode());
									}
								}
								
							//Case NEW
							bookPanel = ccbswindow.buildCenterPanel(countryName, countryCode, commodityName, commodityCode, cropNum2, dataSource, formData[3]);
					//		System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel");
					//		System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel name " +name);
					//		System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after buildCenterPanel code " +code );
							//loadAction(name, code, formData, bookPanel);
							loadAction(code, name, formData, bookPanel);
					//		System.out.println("Class: CCBSParametersPanel Function:preloadAction Text: after loadAction");
						
							ccbswindow.getCenter().add(bookPanel);
							ccbswindow.getSave().setEnabled(true);
							ccbswindow.getCancel().setEnabled(true);
							ccbswindow.getSaveAndClose().setEnabled(true);
							ccbswindow.getCenter().getLayout().layout();
						
							}
							
							public void onFailure(Throwable arg0) {
								
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}	
					}
				}
					
					public void onFailure(Throwable arg0) {
						
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
	}	
	
	private static boolean isSubElement(String element)
	{
		boolean subElement = false;
		for(String name :CCBS.SUB_ELEMENTS_NAMES)
		{
			if(element.equals(name))
			{
				subElement = true;
				break;
			}
		}
		return subElement;
	}
	
	//This method is used to split the elements "Domestic Availability", "Total Utilization", "Domestic Utilization" 
	//and "Per Cap. Food Use" to the list NAMES_CODES_FIELD
	private static boolean isOtherElement(String element)
	{
		boolean subElement = false;
		for(String name :CCBS.OTHER_ELEMENTS_NAMES)
		{
			//System.out.println("Class: *********CCbsParametersPanel isOtherElement element "+element);
			//System.out.println("Class: *********CCbsParametersPanel isOtherElement List name "+name);
			if(element.equals(name))
			{
				subElement = true;
				break;
			}
		}
		return subElement;
	}
	
	//From Database label to cbs sheet label
	private static String changeLabel(String fieldName) {
		String newFieldName = fieldName;
		if(fieldName.equalsIgnoreCase("Supply"))
		{
			newFieldName = "Total Supply";
		}
		else if(fieldName.equalsIgnoreCase("Utilization"))
		{
			newFieldName = "Domestic Utilization";
		}
		else if(fieldName.equalsIgnoreCase("Government Stocks"))
		{
			newFieldName = "Of which Government";
		}
//		else if(fieldName.equalsIgnoreCase("Per Capita Food Use"))
//		{
//			newFieldName = "Per Cap. Food Use";
//		}
		return newFieldName;
	}
	
	//From Database label to cbs sheet label
	private static String otherChangeLabel(String fieldName) {
		String newFieldName = fieldName;
		if(fieldName.equalsIgnoreCase("Utilization"))
		{
			newFieldName = "Domestic Utilization";
		}
		else if(fieldName.equalsIgnoreCase("Per Capita Food Use"))
		{
			newFieldName = "Per Cap. Food Use";
		}
	//	System.out.println("Class: *********CCbsParametersPanel otherChangeLabel topcode.getLabel() "+newFieldName);
		return newFieldName;
	}	
	
//	//To calculate the other year to the forecast: now it's static
//	public static void loadOtherYears()
//	{
//		CCBS.OTHER_YEARS.clear();
//		
//		//To calculate the current year
////		GregorianCalendar gc = new GregorianCalendar();
//	//	System.out.println("Giorno: " + gc.get(Calendar.DAY_OF_MONTH) );
//	//	System.out.println("Mese: " + gc.get(Calendar.MONTH) );
//	//	System.out.println("Anno: " + gc.get(Calendar.YEAR) );
////		int year = gc.get(Calendar.YEAR);
//		
////		Date date = new Date();
////		int year = date.getYear()+ 1900;
////		String nextYear = (year+1)+"";
////		String nextNextYear = (year+2)+"";
////		//CCBS.OTHER_YEARS.add("2011/12");
////		//CCBS.OTHER_YEARS.add("2012/13");
////		CCBS.OTHER_YEARS.add(""+year+"/"+nextYear.substring(2));
////		CCBS.OTHER_YEARS.add(""+nextYear+"/"+nextNextYear.substring(2));
//		int endIndexFirstYear = CCBS.FIRST_YEAR_COMBO.indexOf("/");
//		String firstYearString = CCBS.FIRST_YEAR_COMBO.substring(0, endIndexFirstYear);
//		int endIndexLastYear = CCBS.LAST_YEAR_COMBO.indexOf("/");
//		String lastYearString = CCBS.LAST_YEAR_COMBO.substring(0, endIndexLastYear);
//		int firstYear = Integer.parseInt(firstYearString);		
//		int lastYear = Integer.parseInt(lastYearString);
//		int yearCount = firstYear;
//		int nextYear;
//		String leftSideOfYear= "";
//		String rigthSideOfYear= "";
//		int i = 0//		GregorianCalendar gc = new GregorianCalendar();
//		//	System.out.println("Giorno: " + gc.get(Calendar.DAY_OF_MONTH) );
//		//	System.out.println("Mese: " + gc.get(Calendar.MONTH) );
//		//	System.out.println("Anno: " + gc.get(Calendar.YEAR) );
////			int year = gc.get(Calendar.YEAR);
//			
////			Date date = new Date();
////			int year = date.getYear()+ 1900;
////			String nextYear = (year+1)+"";
////			String nextNextYear = (year+2)+"";
////			//CCBS.OTHER_YEARS.add("2011/12");
////			//CCBS.OTHER_YEARS.add("2012/13");
////			CCBS.OTHER_YEARS.add(""+year+"/"+nextYear.substring(2));
////			CCBS.OTHER_YEARS.add(""+nextYear+"/"+nextNextYear.substring(2));;
//		int iEnd= 0;
//		String monthYearBefore = "";
//		if(CCBS.FIRST_YEAR_COMBO!=null)
//		{
//			System.out.println("loadOtherYears  *********************************CCBS.FIRST_YEAR_COMBO=  *"+ CCBS.FIRST_YEAR_COMBO+"*");
//			String yearFromCombo = CCBS.FIRST_YEAR_COMBO.substring(0, 4);
//			String yearFromCombo2 = "";
//			if(CCBS.FIRST_YEAR_COMBO.length()>8)
//				yearFromCombo2 = CCBS.FIRST_YEAR_COMBO.substring(7, 9);
//			else
//				yearFromCombo2 = CCBS.FIRST_YEAR_COMBO.substring(5, 7);
//			System.out.println("loadOtherYears  *********************************yearFromCombo2=  "+ yearFromCombo2);
//			int yearBeforeYearCombo = Integer.parseInt(yearFromCombo);
//			yearBeforeYearCombo--;
//			int yearBeforeYearCombo2 = Integer.parseInt(yearFromCombo2);
//			yearBeforeYearCombo2--;
//			System.out.println("loadOtherYears  *********************************yearBeforeYearCombo2=  "+ yearBeforeYearCombo2);
//			if(yearBeforeYearCombo2<10)
//			{
//				yearFromCombo2 = "0"+yearBeforeYearCombo2;
//			}
//			else
//			{
//				yearFromCombo2 = ""+yearBeforeYearCombo2;
//			}
//			String yearToSearch = ""+yearBeforeYearCombo+"/"+yearFromCombo2;
//			System.out.println("loadOtherYears  *********************************yearToSearch=  "+ yearToSearch);
//			boolean found =false;
////			for(int imap=0; imap<CCBS.MONTH_FORECAST_TO_QUERY.size(); imap++) {
//			for(int imap=0; imap<CCBS.MONTH_FOR_YEARS.size(); imap++) {
////				HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(imap);
////				Set<String> keySet = hashMap.keySet();
//				Set<String> keySet = CCBS.MONTH_FOR_YEARS.keySet();
//				Iterator it= keySet.iterator();
//				while(it.hasNext())
//				{
//					String year = (String)it.next();
//					if((year!=null)&&(year.equals(yearToSearch)))
//					{
//						monthYearBefore = CCBS.MONTH_FOR_YEARS.get(year);
//						found = true;
//						break;
//					}
//				}
//				if(found)
//				{
//					break;
//				}
//			}
//			System.out.println("loadOtherYears  *********************************monthYearBefore=  "+ monthYearBefore);
//
//			if((monthYearBefore!=null)&&(monthYearBefore.equals(CCBS.VALIDATED_MONTH)))
//			{
//				iEnd = 1;
//			}
//			else
//			{
//				iEnd = 2;
//			}
//			while(i<iEnd)
//			{
//				leftSideOfYear = ""+ yearCount;
//				nextYear = yearCount +1;
//				rigthSideOfYear = ""+ nextYear;
//				rigthSideOfYear= rigthSideOfYear.substring(2);
//				CCBS.OTHER_YEARS.add(leftSideOfYear+"/"+rigthSideOfYear);
//				yearCount++;
//				i++;
//			}
//		}
//		
//	}
	
	//To calculate the other year to the forecast: now it's static
	public static void loadOtherYears()
	{
		CCBS.OTHER_YEARS.clear();
		
		//To calculate the current year
//		GregorianCalendar gc = new GregorianCalendar();
	//	System.out.println("Giorno: " + gc.get(Calendar.DAY_OF_MONTH) );
	//	System.out.println("Mese: " + gc.get(Calendar.MONTH) );
	//	System.out.println("Anno: " + gc.get(Calendar.YEAR) );
//		int year = gc.get(Calendar.YEAR);
		
//		Date date = new Date();
//		int year = date.getYear()+ 1900;
//		String nextYear = (year+1)+"";
//		String nextNextYear = (year+2)+"";
//		//CCBS.OTHER_YEARS.add("2011/12");
//		//CCBS.OTHER_YEARS.add("2012/13");
//		CCBS.OTHER_YEARS.add(""+year+"/"+nextYear.substring(2));
//		CCBS.OTHER_YEARS.add(""+nextYear+"/"+nextNextYear.substring(2));
		int endIndexFirstYear = CCBS.FIRST_YEAR_COMBO.indexOf("/");
		String firstYearString = CCBS.FIRST_YEAR_COMBO.substring(0, endIndexFirstYear);
		int endIndexLastYear = CCBS.LAST_YEAR_COMBO.indexOf("/");
		String lastYearString = CCBS.LAST_YEAR_COMBO.substring(0, endIndexLastYear);
		int firstYear = Integer.parseInt(firstYearString);		
		int lastYear = Integer.parseInt(lastYearString);
		int yearCount = firstYear;
		int nextYear;
		String leftSideOfYear= "";
		String rigthSideOfYear= "";
		//int i = 0//		GregorianCalendar gc = new GregorianCalendar();
		//	System.out.println("Giorno: " + gc.get(Calendar.DAY_OF_MONTH) );
		//	System.out.println("Mese: " + gc.get(Calendar.MONTH) );
		//	System.out.println("Anno: " + gc.get(Calendar.YEAR) );
//			int year = gc.get(Calendar.YEAR);
			
//			Date date = new Date();
//			int year = date.getYear()+ 1900;
//			String nextYear = (year+1)+"";
//			String nextNextYear = (year+2)+"";
//			//CCBS.OTHER_YEARS.add("2011/12");
//			//CCBS.OTHER_YEARS.add("2012/13");
//			CCBS.OTHER_YEARS.add(""+year+"/"+nextYear.substring(2));
//			CCBS.OTHER_YEARS.add(""+nextYear+"/"+nextNextYear.substring(2));;
		int iEnd= 1;
		if(CCBS.FIRST_YEAR_COMBO!=null)
		{
		//	System.out.println("loadOtherYears  *********************************CCBS.FIRST_YEAR_COMBO=  *"+ CCBS.FIRST_YEAR_COMBO+"*");
			String firstYearComboSubstring = CCBS.FIRST_YEAR_COMBO.substring(0, 4);
			boolean found =false;
			for(int imap=0; imap<CCBS.MONTH_FORECAST_TO_QUERY.size(); imap++) {
//			for(int imap=0; imap<CCBS.MONTH_FOR_YEARS.size(); imap++) {
				HashMap<String, String> hashMap = CCBS.MONTH_FORECAST_TO_QUERY.get(imap);
				Set<String> keySet = hashMap.keySet();
//				Set<String> keySet = CCBS.MONTH_FOR_YEARS.keySet();
				Iterator it= keySet.iterator();
				while(it.hasNext())
				{
					String year = (String)it.next();
					//System.out.println("loadOtherYears  *********************************CCBS.FIRST_YEAR_COMBO=  *"+ CCBS.FIRST_YEAR_COMBO+"*"+" year *"+year+"*");
					if((year!=null)&&(year.equals(firstYearComboSubstring)))
					{
						//If there are data for that year in the database
						iEnd = 2;
						found = true;
						break;
					}
				}
				if(found)
				{
					break;
				}
			}
		//	System.out.println("loadOtherYears  *********************************iEnd=  *"+iEnd+"*");
		//	System.out.println("loadOtherYears  *********************************monthYearBefore=  "+ monthYearBefore);

//			if((monthYearBefore!=null)&&(monthYearBefore.equals(CCBS.VALIDATED_MONTH)))
//			{
//				iEnd = 1;
//			}
//			else
//			{
//				iEnd = 2;
//			}
			int i=0;
			while(i<iEnd)
			{
				leftSideOfYear = ""+ yearCount;
				nextYear = yearCount +1;
				rigthSideOfYear = ""+ nextYear;
				rigthSideOfYear= rigthSideOfYear.substring(2);
				CCBS.OTHER_YEARS.add(leftSideOfYear+"/"+rigthSideOfYear);
				yearCount++;
				i++;
			}
		}
		
	}
	
	public static ListStore<AMISCodesModelData> fillStoreFromOtherYears()
	{
		 ListStore<AMISCodesModelData> years = new ListStore<AMISCodesModelData>();
		 for(int iYears=0; iYears<CCBS.OTHER_YEARS.size(); iYears++)
		 {
			 years.add(new AMISCodesModelData(CCBS.OTHER_YEARS.get(iYears), CCBS.OTHER_YEARS.get(iYears)));
		 }
		 return years;
	}
	
	private static String followingYear(String year)
	{
		long numYear = Long.parseLong(year);
		numYear++;
		return ""+numYear;
	}
	
	
	private static HorizontalPanel insertButton()
	{
		HorizontalPanel p1 = new HorizontalPanel();
		p1.setTableWidth("100%");
		p1.setHorizontalAlign(HorizontalAlignment.RIGHT);
		p1.setVerticalAlign(VerticalAlignment.MIDDLE);

		HorizontalPanel p2 = new HorizontalPanel();
		Button cancel = new Button("Cancel ");
		cancel.setWidth(80);
		cancel.setBorders(true);
		cancel.setIconStyle("cancel");
		cancel.setIconAlign(IconAlign.RIGHT);
		p2.add(cancel);
		
		Button save = new Button("Save", new SelectionListener<ButtonEvent>() {  
			@Override  
			public void componentSelected(ButtonEvent ce) { 
				MessageBox.info("Action", "The data have been saved", null);
			  }  
			});  
		save.setWidth(80);
		save.setIconStyle("save");
		save.setIconAlign(IconAlign.RIGHT);
		save.setBorders(true);

		p2.add(save);
		p1.add(p2);
		return p1;
	
	}
	
	public static String getOneMonthBefore(String month)
	{		
		String oneMonthBefore = "";
		if(month.equalsIgnoreCase("January"))
		{
			oneMonthBefore = "December";
		}
		else if(month.equalsIgnoreCase("February"))
		{
			oneMonthBefore = "January";
		}
		else if(month.equalsIgnoreCase("March"))
		{
			oneMonthBefore = "February";
		}
		else if(month.equalsIgnoreCase("April"))
		{
			oneMonthBefore = "March";
		}
		else if(month.equalsIgnoreCase("May"))
		{
			oneMonthBefore = "April";
		}
		else if(month.equalsIgnoreCase("June"))
		{
			oneMonthBefore = "May";
		}
		else if(month.equalsIgnoreCase("July"))
		{
			oneMonthBefore = "June";
		}
		else if(month.equalsIgnoreCase("August"))
		{
			oneMonthBefore = "July";
		}
		else if(month.equalsIgnoreCase("September"))
		{
			oneMonthBefore = "August";
		}
		else if(month.equalsIgnoreCase("October"))
		{
			oneMonthBefore = "September";
		}
		else if(month.equalsIgnoreCase("November"))
		{
			oneMonthBefore = "October";
		}
		else if(month.equalsIgnoreCase("December"))
		{
			oneMonthBefore = "November";
		}
		return oneMonthBefore;
	}
	
	public static String getOneMonthAfter(String month)
	{		
		String oneMonthBefore = "";
		if(month.equalsIgnoreCase("January"))
		{
			oneMonthBefore = "February";
		}
		else if(month.equalsIgnoreCase("February"))
		{
			oneMonthBefore = "March";
		}
		else if(month.equalsIgnoreCase("March"))
		{
			oneMonthBefore = "April";
		}
		else if(month.equalsIgnoreCase("April"))
		{
			oneMonthBefore = "May";
		}
		else if(month.equalsIgnoreCase("May"))
		{
			oneMonthBefore = "June";
		}
		else if(month.equalsIgnoreCase("June"))
		{
			oneMonthBefore = "July";
		}
		else if(month.equalsIgnoreCase("July"))
		{
			oneMonthBefore = "August";
		}
		else if(month.equalsIgnoreCase("August"))
		{
			oneMonthBefore = "September";
		}
		else if(month.equalsIgnoreCase("September"))
		{
			oneMonthBefore = "October";
		}
		else if(month.equalsIgnoreCase("October"))
		{
			oneMonthBefore = "November";
		}
		else if(month.equalsIgnoreCase("November"))
		{
			oneMonthBefore = "December";
		}
		else if(month.equalsIgnoreCase("December"))
		{
			oneMonthBefore = "January";
		}
		return oneMonthBefore;
	}
	
//	private static void loadAction(String gaulCode, final String gaulName, String formData[], final BookPanel bookPanel)
//	{
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[0]"+ formData[0]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[1]"+ formData[1]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[2]"+ formData[2]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[3]"+ formData[3]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[4]"+ formData[4]);
//		boolean dataEmpty = false;
//		final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {  
//		public void handleEvent(MessageBoxEvent ce) {  
//		     Button btn = ce.getButtonClicked();  
//		     Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
//		    
//		    }  
//		};  
//		for(int iFormData =0; iFormData<5;iFormData++)
//		{
//			if(formData[iFormData].equals(""))
//			{
//				if(iFormData==0)
//				{
//					MessageBox.alert("Error", "Select field 'Data Source'", l);
//				}
//				if(iFormData==1)
//				{
//					MessageBox.alert("Error", "Select field 'Country/Region'", l);
//				}
//				if(iFormData==2)
//				{
//					MessageBox.alert("Error", "Select field 'Commodity'", l);
//				}
//				if(iFormData==3)
//				{
//					MessageBox.alert("Error", "Select field 'From'", l);
//				}
//				dataEmpty = true;
//			}
//		}
//		if(!dataEmpty)
//		{
//			final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data for " + gaulName, "");
//			loadingWindow.showLoadingBox();
//	
//			AMISServiceEntry.getInstance().getCCBSData(gaulCode, CCBS.YEARS,
//			                                           new AsyncCallback<Book>()
//			                                           {
//				                                           public void onSuccess(Book result)
//				                                           {
//					                                           loadingWindow.destroyLoadingBox();
//					                                           
//					                                           // System.out.println("Book (Client):"); // DEBUG
//					                                           // System.out.println(result);    // DEBUG
//	
//					                                           try
//					                                           {
//					                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess" );
//						                                           Book book = makeClientBook(result);
//						                                           for (int pageNum = 0; pageNum < book.numPages(); pageNum++)
//						                                           {
//							                                           String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
//							                                           bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
//						                                           }
//						                                           bookPanel.load(book);
//					                                           }
//					                                           catch (Exception e)
//					                                           {
//						                                           e.printStackTrace();
//					                                           }
//				                                           }
//	
//				                                           public void onFailure(Throwable caught)
//				                                           {
//					                                           loadingWindow.destroyLoadingBox();
//	
//					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
//				                                           }
//			                                           });
//		}
//	}
//	
	
	public static void loadAction(final String formData[], final BookPanel bookPanel)
	{
		loadAction("","", formData, bookPanel);
	}
	
	private static void loadAction(String gaulCode, final String gaulName, final String formData[], final BookPanel bookPanel)
	{
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[0]"+ formData[0]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[1]"+ formData[1]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[2]"+ formData[2]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[3]"+ formData[3]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[4]"+ formData[4]);
		boolean dataEmpty = false;
		final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {  
		public void handleEvent(MessageBoxEvent ce) {  
		     Button btn = ce.getButtonClicked();  
		     Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
		    
		    }  
		};  
		for(int iFormData =0; iFormData<5;iFormData++)
		{
			if(formData[iFormData].equals(""))
			{
				if(iFormData==0)
				{
					dataEmpty = true;
					MessageBox.alert("Error", "Select field 'Data Source'", l);
				}
				if(iFormData==1)
				{
					dataEmpty = true;
					MessageBox.alert("Error", "Select field 'Country/Region'", l);
				}
				if(iFormData==2)
				{
					dataEmpty = true;
					MessageBox.alert("Error", "Select field 'Commodity'", l);
				}
				
//				if(iFormData==3)
//				{					
//					//To ignore when the commodity is COARSE GRAINS or TOTAL CEREALS
//					if((!formData[2].equals("COARSE GRAINS"))&&(!formData[2].equals("TOTAL CEREALS")))
//					{
//						dataEmpty = true;
//						MessageBox.alert("Info", "No data available for this selection! Please try with another selection!", null);
//						//MessageBox.alert("Error", "Select field 'From'", l);
//					}					
//				}
				//dataEmpty = true;
			}
		}
		if(!dataEmpty)
		{
			//final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data for " + gaulName, "");
		//	final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data... ", "");
			//final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "", "");
			//loadingWindow.showLoadingBox();
			if(formData[0].equals(CCBS.elementForTraining))
			{
				final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data... ", "");
				loadingWindow.showLoadingBox();
			//	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Class: CCCBSParametersPanel  Function: before getccbsData Text if(formData[0].equals(CCBS.elementForTraining))");
				AMISQueryVO qvo =  new AMISQueryVO();
				//qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
				//String selectionType = "AMIS_COUNTRIES";
				
				//qvo.setTypeOfOutput(selectionType);	
//				if(formData[0].equals("FAOSTAT"))
//				{
//					qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
//				}
//				else if(formData[0].equals("PSD"))
//				{
//					qvo.setSelectedDataset(AMISConstants.PSD.toString());
//				}
//				else if(formData[0].equals(CCBSParametersPanel.firstElement))
//				{
//					qvo.setSelectedDataset(AMISConstants.CBS.toString());
//				}
				if(formData[0].equals(CCBS.elementForTraining))
				{
					qvo.setSelectedDataset(AMISConstants.CBS.toString());
				}
				qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
				qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
				qvo.setSortingOrder("ASC");
				qvo.setValueType("TOTAL");
				qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
//	
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData CCBS.NUMBER_OF_YEARS " + CCBS.NUMBER_OF_YEARS);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData CCBS.NAME_FIRST_YEARS_TO_SHOW " + CCBS.NAME_FIRST_YEARS_TO_SHOW);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData CCBS.NUMBER_FIRST_YEARS_TO_SHOW " + CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
			
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData gaulCode " +gaulCode);
				String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
				if(CCBS.LAST_MONTH_COMBO.equals(""))
				{
					lastMonthCombo = CCBS.N_A_MONTH;
				}
			//	System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) start YEARS" );
//				 for(int i =0; i< CCBS.YEARS.size(); i++)
//				 {
//					 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) YEARS"+ CCBS.YEARS.get(i));
//				 }
//				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) end YEARS" );
//				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) CCBS.NUMBER_FIRST_YEARS_TO_SHOW"+ CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
//				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) lastMonthCombo"+ lastMonthCombo);
//				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) CCBS.FIELD_NAMES_WITH_UNIT[5]"+ CCBS.FIELD_NAMES_WITH_UNIT[5]);
//				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) CCBS.MONTH_FORECAST_TO_QUERY Start");
//				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) CCBS.MONTH_FORECAST_TO_QUERY.size()"+ CCBS.MONTH_FORECAST_TO_QUERY.size());
				 for(int i =0; i< CCBS.MONTH_FORECAST_TO_QUERY.size(); i++)
				 {
					 Set<String> keySet = CCBS.MONTH_FORECAST_TO_QUERY.get(i).keySet();
					 for(String key:keySet)
					 {
						 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) key "+key+" CCBS.MONTH_FORECAST_TO_QUERY.get(i).get(key) "+ CCBS.MONTH_FORECAST_TO_QUERY.get(i).get(key));
				 
					 }
				 }
				// System.out.println("Class: CCBSParametersPanel Function: loadAction Text:if (formData[0].equals(CCBS.elementForTraining)) CCBS.MONTH_FORECAST_TO_QUERY End");
				AMISServiceEntry.getInstance().getCCBSData(qvo, CCBS.YEARS, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, lastMonthCombo, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
				                                           new AsyncCallback<Book>()
				                                           {
					                                           public void onSuccess(Book result)
					                                           {
						                                          // loadingWindow.destroyLoadingBox();
						                                           
						                                           
						                                           // System.out.println("Book (Client):"); // DEBUG
						                                           // System.out.println(result);    // DEBUG
					                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess Start if(formData[0].equals(CCBS.elementForTraining))" );
						                                           try
						                                           {
						                                        	   
						                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess" );
							                                           Book book = makeClientBook(result);
							                                        //   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 1 if(formData[0].equals(CCBS.elementForTraining))" );
							                                           for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++)
							                                           {
							                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ciclo" );
								                                           //String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
								                                        //   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:title " +title);
								                                          // bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
								                                         //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess fine ciclo" );
							                                           }
							                                      //    System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 2" );
							                                           bookPanel.load(book);
							                                           //INIZIO DA ELIMINARE
							                                           Page p = book.getPage(0);
							                                           for(int i=0; i< p.numCols(); i++)
							                                           {
							                                        	   for(int j=0; j< p.numRows(); j++)
								                                           {
								                                        	   String text = p.getCell(j, i).getText();
								                                        	   Double value = p.getCell(j, i).getValue();
								                                       // 	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ROW= "+j+" COL= "+i+ " text= "+ text+" value= "+value );
								                                           }
							                                           }
							                                           //FINE DA ELIMINARE
							                                      //     System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess end" );
	//						                                       	ccbswindow.getCenter().add(bookPanel);
	//						                    					ccbswindow.getSave().setEnabled(true);
	//						                    					ccbswindow.getCancel().setEnabled(true);
							                    					//ccbswindow.getCenter().show();
							                    					//ccbswindow.getCenter().getLayout().layout();  
							                                    //   	System.out.println("Class: CCBSParametersPanel Function: loadAction Text: Before Fill subelements data CBS case if(formData[0].equals(CCBS.elementForTraining))");
							                            			//To fill the list of the sub elements (Production and Other Uses) that are in the database 
							                            			AMISQueryVO qvo =  new AMISQueryVO();
							                            			qvo.setSelectedDataset(AMISConstants.CBS.toString());
							                            			qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
							                            			qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
							                            			qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
							                            			qvo.setSortingOrder("ASC");
							                            			qvo.setValueType("TOTAL");
							                            		

							                            			AMISServiceEntry.getInstance().getCBSSubElementsData(qvo, CCBS.YEARS, CCBS.LAST_MONTH_COMBO, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
							                            			                                           new AsyncCallback<List<ClientCbsDatasetResult>>()
							                            			                                           {
							                            				                                           public void onSuccess(List<ClientCbsDatasetResult> result)
							                            				                                           {
							                            				                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list if(formData[0].equals(CCBS.elementForTraining))");
							                            					                                          loadingWindow.destroyLoadingBox();
							                            					                                          ccbswindow.getShowHideFlags().enable();
							                            				                                        	   for(ClientCbsDatasetResult clientCbsDatasetResult: result)
							                            				                                        	   {
							                            				                                        		   CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(clientCbsDatasetResult);
							                            				                                        	   }
//							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...after add in the list if(formData[0].equals(CCBS.elementForTraining))");
//							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size = "+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size() + " if(formData[0].equals(CCBS.elementForTraining))");
							                            				                                           }
							                            				                                           
							                            				                                           public void onFailure(Throwable caught)
							                            				                                           {
							                            					                                           loadingWindow.destroyLoadingBox();
							                            	
							                            					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCBSSubElementsData()");
							                            				                                           }
							                            			                                           });
							                                           
						                                           }
						                                           catch (Exception e)
						                                           {
							                                           e.printStackTrace();
						                                           }
					                                           }
		
					                                           public void onFailure(Throwable caught)
					                                           {
						                                           loadingWindow.destroyLoadingBox();
		
						                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
					                                           }
				                                           });
			//	ccbswindow.getCenter().getLayout().layout(); 
		}
			else if(formData[0].equals(CCBS.elementEmpty))
			{
				//Case NEW
				final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data... ", "");
				loadingWindow.showLoadingBox();
				//System.out.println("Class: CCCBSParametersPanel  Function: before getccbsData Text else (formData[0].equals(CCBS.elementEmpty))");
				// loadingWindow.destroyLoadingBox();
				 try
                 {
              	   
              	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess" );
                     Book book = makeClientBookForNew();
                //     System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 1" );
                     for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++)
                     {
                //  	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ciclo" );
                         //String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
                      //   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:title " +title);
                        // bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
                  //       System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess fine ciclo" );
                     }
             //        System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 2" );
                     bookPanel.load(book);
             //       System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess end" );
//						                                       	ccbswindow.getCenter().add(bookPanel);
//						                    					ccbswindow.getSave().setEnabled(true);
//						                    					ccbswindow.getCancel().setEnabled(true);
					//ccbswindow.getCenter().show();
					//ccbswindow.getCenter().getLayout().layout();  
                    //	System.out.println("Class: CCBSParametersPanel Function: loadAction Text: Before Fill subelements data NEW case");
            			//To fill the list of the sub elements (Production and Other Uses) that are in the database 
            			AMISQueryVO qvo =  new AMISQueryVO();
            			qvo.setSelectedDataset(AMISConstants.CBS.toString());
            			qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
            			qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
            			qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
            			qvo.setSortingOrder("ASC");
            			qvo.setValueType("TOTAL");

            			String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
        				if(CCBS.LAST_MONTH_COMBO.equals(""))
        				{
        					lastMonthCombo = CCBS.N_A_MONTH;
        				}
        				
            			AMISServiceEntry.getInstance().getCBSSubElementsData(qvo, CCBS.YEARS, lastMonthCombo, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
            			                                           new AsyncCallback<List<ClientCbsDatasetResult>>()
            			                                           {
            				                                           public void onSuccess(List<ClientCbsDatasetResult> result)
            				                                           {
            				                                        //	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list");
            					                                           loadingWindow.destroyLoadingBox();
            					                                           ccbswindow.getShowHideFlags().enable();
            				                                        	   for(ClientCbsDatasetResult clientCbsDatasetResult: result)
            				                                        	   {
            				                                        		   CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(clientCbsDatasetResult);
            				                                        	   }
//            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...after add in the list");
//            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size = "+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
            				                                           }
            				                                           
            				                                           public void onFailure(Throwable caught)
            				                                           {
            					                                           loadingWindow.destroyLoadingBox();
            	
            					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCBSSubElementsData()");
            				                                           }
            			                                           });
                 }
                 catch (Exception e)
                 {
                     e.printStackTrace();
                 }
				}
			else
			{
				//Case Data source of the country
				//Check wich type of "loading window" to show..
				//If the dataset is empty... it's necessary to wait for loading from Cbs Dataset ----> load: "The data is accessed for the first time; database is being created. Please wait... "
				//Otherwise operation is fast... it doesn't need of the copy of the data -----> load: "Loading data... "
				AMISQueryVO qvo =  new AMISQueryVO();
				//The name data source to check inside the file
				qvo.setSelectedDataset(formData[0]);
				qvo.setTrainingDataset(AMISConstants.CBS.toString());
//				qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
				qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
				
				AMISServiceEntry.getInstance().checkCountryDataForLoadingWindow(qvo, new AsyncCallback<AMISResultVO>()
                        {
                            public void onSuccess(AMISResultVO result)
                            {
				//System.out.println("Class: CCCBSParametersPanel  Function: before getccbsData Text else (formData[0].equals(CCBS.elementForTraining))");
				final LoadingWindow loadingWindow;
				if(result.getResultFromCbs())
				{
					loadingWindow = new LoadingWindow("Loading...", "The data is accessed for the first time; database is being created. Please wait... ", "");
				}
				else
				{
					loadingWindow = new LoadingWindow("Loading...", "Loading data... ", "");
				}
			//	final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "", "");
				loadingWindow.showLoadingBox();
				//Check if the dataset of the country is empty... this is the first time... it's necessary to load Cbs Data
				
				//If the data come from Cbs ... copy all this data into the dataset of the country... This is the FIRST time for that dataset
				
				//Else take the value from the dataset of the country making the Book to show
				
				AMISQueryVO qvo =  new AMISQueryVO();
				//The name data source to check inside the file
				qvo.setSelectedDataset(formData[0]);
				qvo.setTrainingDataset(AMISConstants.CBS.toString());
//				qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
				qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
				AMISServiceEntry.getInstance().checkCountryData(qvo, true, new AsyncCallback<AMISResultVO>()
                        {
                            public void onSuccess(AMISResultVO result)
                            {
//                            	System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:COMEEEEEEEEEEEEEEEEEEEEEEEEEEEE BACKKKKKKKKKKKKKKKKKKKKKKKKKKKKK!!!!" );
//                            	System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:COMEEEEEEEEEEEEEEEEEEEEEEEEEEEE BACKKKKKKKKKKKKKKKKKKKKKKKKKKKKK!!!!" );
//                            	System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:COMEEEEEEEEEEEEEEEEEEEEEEEEEEEE BACKKKKKKKKKKKKKKKKKKKKKKKKKKKKK!!!!" );
//                         	   	System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:onsuccess Start else (formData[0].equals(CCBS.elementForTraining))" );
                            	try
                                {
                            		//Else take the value from the dataset of the country making the Book to show
                            		
                         	   		//Fill the book to show in the grid                                 	   	
                            	   AMISQueryVO qvo =  new AMISQueryVO();
                            	   qvo.setSelectedDataset(formData[0]);
                    				qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
                    				qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
                    				qvo.setSortingOrder("ASC");
                    				qvo.setValueType("TOTAL");
//                    				qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                    				qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
                    				String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
                    				if(CCBS.LAST_MONTH_COMBO.equals(""))
                    				{
                    					lastMonthCombo = CCBS.N_A_MONTH;
                    				}                 
                    				// System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) start YEARS" );
//                    				 for(int i =0; i< CCBS.YEARS.size(); i++)
//                    				 {
//                    					 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) YEARS"+ CCBS.YEARS.get(i));
//                    				 }
//                    				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) end YEARS" );
//                    				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) CCBS.NUMBER_FIRST_YEARS_TO_SHOW"+ CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
//                    				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) lastMonthCombo"+ lastMonthCombo);
//                    				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) CCBS.FIELD_NAMES_WITH_UNIT[5]"+ CCBS.FIELD_NAMES_WITH_UNIT[5]);
//                    				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) CCBS.MONTH_FORECAST_TO_QUERY Start");
//                    				 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) CCBS.MONTH_FORECAST_TO_QUERY.size()"+ CCBS.MONTH_FORECAST_TO_QUERY.size());
                    				 for(int i =0; i< CCBS.MONTH_FORECAST_TO_QUERY.size(); i++)
                    				 {
                    					 Set<String> keySet = CCBS.MONTH_FORECAST_TO_QUERY.get(i).keySet();
                    					 for(String key:keySet)
                    					 {
                    					//	 System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) key "+key+" CCBS.MONTH_FORECAST_TO_QUERY.get(i).get(key) "+ CCBS.MONTH_FORECAST_TO_QUERY.get(i).get(key));
                    				 
                    					 }
                    				 }
                    				// System.out.println("Class: CCBSParametersPanel Function: loadAction Text:else (formData[0].equals(CCBS.elementForTraining)) CCBS.MONTH_FORECAST_TO_QUERY End");
                    				 AMISServiceEntry.getInstance().getCCBSData(qvo, CCBS.YEARS, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, lastMonthCombo, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
                    				                                           new AsyncCallback<Book>()
                    				                                           {
                    					                                           public void onSuccess(Book result)
                    					                                           {
                    					                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess Start else (formData[0].equals(CCBS.elementForTraining))" );
                    						                                           try
                    						                                           {
                    						                                        	 //  loadingWindow.destroyLoadingBox();
                    						                                        	   Book book = makeClientBook(result);
                    							                                         //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 1 else (formData[0].equals(CCBS.elementForTraining))" );
                    							                                           for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++)
                    							                                           {
                    							                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ciclo" );
                    								                                           //String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
                    								                                        //   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:title " +title);
                    								                                          // bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
                    								                                         //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess fine ciclo" );
                    							                                           }
                    							                                      //    System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 2" );
                    							                                           bookPanel.load(book);
                    							                                           //INIZIO DA ELIMINARE
                    							                                           Page p = book.getPage(0);
                    							                                           for(int i=0; i< p.numCols(); i++)
                    							                                           {
                    							                                        	   for(int j=0; j< p.numRows(); j++)
                    								                                           {
                    								                                        	   String text = p.getCell(j, i).getText();
                    								                                        	   Double value = p.getCell(j, i).getValue();
                    								                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ROW= "+j+" COL= "+i+ " text= "+ text+" value= "+value );
                    								                                           }
                    							                                           }
                    							                                          
                    							                                           
                    							                                           //FINE DA ELIMINARE
                    							                                      // 	System.out.println("Class: CCBSParametersPanel Function: loadAction Text: Before else (formData[0].equals(CCBS.elementForTraining))");
                    							                            			//To fill the list of the sub elements (Production and Other Uses) that are in the database 
                    							                            			AMISQueryVO qvo =  new AMISQueryVO();
                    							                            			qvo.setSelectedDataset(formData[0]);
//                    							                            			qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                    							                            			qvo.setSelectedDatasetFile(ccbswindow.amisForCountryDataset());
                    							                            			qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
                    							                            			qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
                    							                            			qvo.setSortingOrder("ASC");
                    							                            			qvo.setValueType("TOTAL");
                    							                            		//	System.out.println("Class: CCBSParametersPanel Function: loadAction Text: Before else (formData[0].equals(CCBS.elementForTraining)) qvo.getSelectedDatasetFile"+qvo.getSelectedDatasetFile());
                    							                            			AMISServiceEntry.getInstance().getCBSSubElementsData(qvo, CCBS.YEARS, CCBS.LAST_MONTH_COMBO, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
                    							                            			                                           new AsyncCallback<List<ClientCbsDatasetResult>>()
                    							                            			                                           {
                    							                            				                                           public void onSuccess(List<ClientCbsDatasetResult> result)
                    							                            				                                           {
                    							                            				                                        	  // System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list else (formData[0].equals(CCBS.elementForTraining))");
                    							                            					                                           loadingWindow.destroyLoadingBox();
                    							                            					                                           ccbswindow.getShowHideFlags().enable();
                    							                            					                                           for(ClientCbsDatasetResult clientCbsDatasetResult: result)
                    							                            				                                        	   {
                    							                            				                                        		 //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...after add in the list else (formData[0].equals(CCBS.elementForTraining)) clientCbsDatasetResult.getValue() "+clientCbsDatasetResult.getValue()+" clientCbsDatasetResult.getAnnotation() " + clientCbsDatasetResult.getAnnotation());
                    							                            				                                        		   CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(clientCbsDatasetResult);
                    							                            				                                        	   }
//                    							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...after add in the list else (formData[0].equals(CCBS.elementForTraining))");
//                    							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size = "+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size()+ " else (formData[0].equals(CCBS.elementForTraining))");
                    							                            				                                           }
                    							                            				                                           
                    							                            				                                           public void onFailure(Throwable caught)
                    							                            				                                           {
                    							                            					                                           loadingWindow.destroyLoadingBox();
                    							                            	
                    							                            					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCBSSubElementsData()");
                    							                            				                                           }
                    							                            			                                           });                                       							                                           
                    						                                           }
                    						                                           catch (Exception e)
                    						                                           {
                    							                                           e.printStackTrace();
                    						                                           }
                    					                                           }
                    		
                    					                                           public void onFailure(Throwable caught)
                    					                                           {
                    						                                           loadingWindow.destroyLoadingBox();
                    		
                    						                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
                    					                                           }
                    				                                           });
                                	
                                	
                                	
                                	
                                	
                                	
                                	
                                	
                                	
                                	
                                	
//                                	System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:onsuccess Start else (formData[0].equals(CCBS.elementForTraining)) "+ result.isResultFromCbs());
//                                	if(result.isResultFromCbs())
//                                	{
////                                		//Copy all the result to new dataset
////                                		AMISQueryVO qvo =  new AMISQueryVO();
////                        				//It's necessary to change the column of the Dataset because the values are taken by Cbs
////                        				qvo.setSelectedDataset(formData[0]);
////                        				qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
////                        				System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:onsuccess result.getCbsResult() size = "+ result.getCbsResult().size());
////                        				if(result.getCbsResult().size()!=0)
////                        				{
////                        					System.out.println("Class: CCBSParametersPanel Function: checkCountryData Text:onsuccess result.getCbsResult().get(0)[0] = "+ result.getCbsResult().get(0)[0]);
////                        				}
////                        				qvo.setCbsResult(result.getCbsResult());
////                        				
////                        				AMISServiceEntry.getInstance().fillCountryData(qvo, new AsyncCallback<Boolean>()
////                                                {
////                                                    public void onSuccess(Boolean result)
////                                                    {
////                                                 	   	System.out.println("Class: CCBSParametersPanel Function: fillCountryData Text:onsuccess Start Result boolean = " + result);
////                                                 	   	//Fill the book to show in the grid
//                                                 	   	
//                                               	   AMISQueryVO qvo =  new AMISQueryVO();
//                                               	   qvo.setSelectedDataset(AMISConstants.CBS.toString());
//                                       				qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
//                                       				qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
//                                       				qvo.setSortingOrder("ASC");
//                                       				qvo.setValueType("TOTAL");
//                                       				qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
//                                       				String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
//                                       				if(CCBS.LAST_MONTH_COMBO.equals(""))
//                                       				{
//                                       					lastMonthCombo = CCBS.N_A_MONTH;
//                                       				}                                       				
//                                       				AMISServiceEntry.getInstance().getCCBSData(qvo, CCBS.YEARS, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, lastMonthCombo, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
//                                       				                                           new AsyncCallback<Book>()
//                                       				                                           {
//                                       					                                           public void onSuccess(Book result)
//                                       					                                           {
//                                       					                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess Start else (formData[0].equals(CCBS.elementForTraining))" );
//                                       						                                           try
//                                       						                                           {
//                                       						                                        	loadingWindow.destroyLoadingBox();
//                                       						                                        	   Book book = makeClientBook(result);
//                                       							                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 1 else (formData[0].equals(CCBS.elementForTraining))" );
//                                       							                                           for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++)
//                                       							                                           {
//                                       							                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ciclo" );
//                                       								                                           //String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
//                                       								                                        //   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:title " +title);
//                                       								                                          // bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
//                                       								                                         //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess fine ciclo" );
//                                       							                                           }
//                                       							                                      //    System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 2" );
//                                       							                                           bookPanel.load(book);
//                                       							                                           //INIZIO DA ELIMINARE
//                                       							                                           Page p = book.getPage(0);
//                                       							                                           for(int i=0; i< p.numCols(); i++)
//                                       							                                           {
//                                       							                                        	   for(int j=0; j< p.numRows(); j++)
//                                       								                                           {
//                                       								                                        	   String text = p.getCell(j, i).getText();
//                                       								                                        	   Double value = p.getCell(j, i).getValue();
//                                       								                                        	 //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ROW= "+j+" COL= "+i+ " text= "+ text+" value= "+value );
//                                       								                                           }
//                                       							                                           }
//                                       							                                           //FINE DA ELIMINARE
//                                       							                                       	System.out.println("Class: CCBSParametersPanel Function: loadAction Text: else (formData[0].equals(CCBS.elementForTraining))");
//                                       							                            			//To fill the list of the sub elements (Production and Other Uses) that are in the database 
//                                       							                            			AMISQueryVO qvo =  new AMISQueryVO();
//                                       							                            			qvo.setSelectedDataset(AMISConstants.CBS.toString());
//                                       							                            			qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
//                                       							                            			qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
//                                       							                            			qvo.setSortingOrder("ASC");
//                                       							                            			qvo.setValueType("TOTAL");
//                                       							                            			qvo.setSelectedDatasetFile(AMISConstants.AMIS.toString());
//
//                                       							                            			AMISServiceEntry.getInstance().getCBSSubElementsData(qvo, CCBS.YEARS, CCBS.LAST_MONTH_COMBO, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
//                                       							                            			                                           new AsyncCallback<List<ClientCbsDatasetResult>>()
//                                       							                            			                                           {
//                                       							                            				                                           public void onSuccess(List<ClientCbsDatasetResult> result)
//                                       							                            				                                           {
//                                       							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list else (formData[0].equals(CCBS.elementForTraining))");
//                                       							                            					                                           loadingWindow.destroyLoadingBox();
//                                       							                            				                                        	   for(ClientCbsDatasetResult clientCbsDatasetResult: result)
//                                       							                            				                                        	   {
//                                       							                            				                                        		   CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(clientCbsDatasetResult);
//                                       							                            				                                        	   }
//                                       							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...after add in the list else (formData[0].equals(CCBS.elementForTraining))");
//                                       							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size = "+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size() +" else (formData[0].equals(CCBS.elementForTraining))");
//                                       							                            				                                           }
//                                       							                            				                                           
//                                       							                            				                                           public void onFailure(Throwable caught)
//                                       							                            				                                           {
//                                       							                            					                                           loadingWindow.destroyLoadingBox();
//                                       							                            	
//                                       							                            					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCBSSubElementsData()");
//                                       							                            				                                           }
//                                       							                            			                                           });                                       							                                           
//                                       						                                           }
//                                       						                                           catch (Exception e)
//                                       						                                           {
//                                       							                                           e.printStackTrace();
//                                       						                                           }
//                                       					                                           }
//                                       		
//                                       					                                           public void onFailure(Throwable caught)
//                                       					                                           {
//                                       						                                           loadingWindow.destroyLoadingBox();
//                                       		
//                                       						                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
//                                       					                                           }
//                                       				                                           });
//                                                 	   	
////                                                    }
////                                                public void onFailure(Throwable caught)
////                                                {
////                                                    loadingWindow.destroyLoadingBox();
////
////                                                    FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
////                                                }
////                                            });
//                                	}
//                                	else
//                                	{
//                                		//Else take the value from the dataset of the country making the Book to show
//                                		
//                                 	   		//Fill the book to show in the grid                                 	   	
//                                    	   AMISQueryVO qvo =  new AMISQueryVO();
//                                    	   qvo.setSelectedDataset(formData[0]);
//                            				qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
//                            				qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
//                            				qvo.setSortingOrder("ASC");
//                            				qvo.setValueType("TOTAL");
//                            				qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
//                            				String lastMonthCombo = CCBS.LAST_MONTH_COMBO;
//                            				if(CCBS.LAST_MONTH_COMBO.equals(""))
//                            				{
//                            					lastMonthCombo = CCBS.N_A_MONTH;
//                            				}                                       				
//                            				AMISServiceEntry.getInstance().getCCBSData(qvo, CCBS.YEARS, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, lastMonthCombo, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
//                            				                                           new AsyncCallback<Book>()
//                            				                                           {
//                            					                                           public void onSuccess(Book result)
//                            					                                           {
//                            					                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess Start" );
//                            						                                           try
//                            						                                           {
//                            						                                        	   loadingWindow.destroyLoadingBox();
//                            						                                        	   Book book = makeClientBook(result);
//                            							                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 1" );
//                            							                                           for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++)
//                            							                                           {
//                            							                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ciclo" );
//                            								                                           //String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
//                            								                                        //   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:title " +title);
//                            								                                          // bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
//                            								                                         //  System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess fine ciclo" );
//                            							                                           }
//                            							                                      //    System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 2" );
//                            							                                           bookPanel.load(book);
//                            							                                           //INIZIO DA ELIMINARE
//                            							                                           Page p = book.getPage(0);
//                            							                                           for(int i=0; i< p.numCols(); i++)
//                            							                                           {
//                            							                                        	   for(int j=0; j< p.numRows(); j++)
//                            								                                           {
//                            								                                        	   String text = p.getCell(j, i).getText();
//                            								                                        	   Double value = p.getCell(j, i).getValue();
//                            								                                        	//   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ROW= "+j+" COL= "+i+ " text= "+ text+" value= "+value );
//                            								                                           }
//                            							                                           }
//                            							                                           //FINE DA ELIMINARE
//                            							                                       	System.out.println("Class: CCBSParametersPanel Function: loadAction Text: Before Fill subelements data CBS case");
//                            							                            			//To fill the list of the sub elements (Production and Other Uses) that are in the database 
//                            							                            			AMISQueryVO qvo =  new AMISQueryVO();
//                            							                            			qvo.setSelectedDataset(AMISConstants.CBS.toString());
//                            							                            			qvo.setCountryCode(ccbswindow.getCountryCodeFromName(formData[1]));
//                            							                            			qvo.setCommodityCode(ccbswindow.getCommodityCodeFromName(formData[2]));
//                            							                            			qvo.setSortingOrder("ASC");
//                            							                            			qvo.setValueType("TOTAL");
//                            							                            			qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
//
//                            							                            			AMISServiceEntry.getInstance().getCBSSubElementsData(qvo, CCBS.YEARS, CCBS.LAST_MONTH_COMBO, CCBS.NUMBER_FIRST_YEARS_TO_SHOW, CCBS.FIELD_NAMES_WITH_UNIT[5], CCBS.MONTH_FORECAST_TO_QUERY,
//                            							                            			                                           new AsyncCallback<List<ClientCbsDatasetResult>>()
//                            							                            			                                           {
//                            							                            				                                           public void onSuccess(List<ClientCbsDatasetResult> result)
//                            							                            				                                           {
//                            							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list");
//                            							                            					                                           loadingWindow.destroyLoadingBox();
//                            							                            				                                        	   for(ClientCbsDatasetResult clientCbsDatasetResult: result)
//                            							                            				                                        	   {
//                            							                            				                                        		   CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(clientCbsDatasetResult);
//                            							                            				                                        	   }
//                            							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...after add in the list");
//                            							                            				                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text: After Fill subelements data onSuccess...before add in the list CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size = "+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
//                            							                            				                                           }
//                            							                            				                                           
//                            							                            				                                           public void onFailure(Throwable caught)
//                            							                            				                                           {
//                            							                            					                                           loadingWindow.destroyLoadingBox();
//                            							                            	
//                            							                            					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCBSSubElementsData()");
//                            							                            				                                           }
//                            							                            			                                           });                                       							                                           
//                            						                                           }
//                            						                                           catch (Exception e)
//                            						                                           {
//                            							                                           e.printStackTrace();
//                            						                                           }
//                            					                                           }
//                            		
//                            					                                           public void onFailure(Throwable caught)
//                            					                                           {
//                            						                                           loadingWindow.destroyLoadingBox();
//                            		
//                            						                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
//                            					                                           }
//                            				                                           });
//                                	}
                                }	
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }

                        public void onFailure(Throwable caught)
                        {
                            loadingWindow.destroyLoadingBox();

                            FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
                        }
                    });
                        }

                        public void onFailure(Throwable caught)
                        {
                        //    loadingWindow.destroyLoadingBox();

                            FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
                        }
                    });		
				}
		} //if(dataEmpty)
	}
	
//	private static void loadAction(String gaulCode, final String gaulName, String formData[], final BookPanel bookPanel)
//	{
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[0]"+ formData[0]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[1]"+ formData[1]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[2]"+ formData[2]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[3]"+ formData[3]);
//		System.out.println("Class: CCBSParametersPanel Function: loadAction Text: formData[4]"+ formData[4]);
//		boolean dataEmpty = false;
//		final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {  
//		public void handleEvent(MessageBoxEvent ce) {  
//		     Button btn = ce.getButtonClicked();  
//		     Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
//		    
//		    }  
//		};  
//		for(int iFormData =0; iFormData<5;iFormData++)
//		{
//			if(formData[iFormData].equals(""))
//			{
//				if(iFormData==0)
//				{
//					MessageBox.alert("Error", "Select field 'Data Source'", l);
//				}
//				if(iFormData==1)
//				{
//					MessageBox.alert("Error", "Select field 'Country/Region'", l);
//				}
//				if(iFormData==2)
//				{
//					MessageBox.alert("Error", "Select field 'Commodity'", l);
//				}
//				if(iFormData==3)
//				{
//					MessageBox.alert("Error", "Select field 'From'", l);
//				}
//				dataEmpty = true;
//			}
//		}
//		if(!dataEmpty)
//		{
//			//final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data for " + gaulName, "");
//			final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data ", "");
//			loadingWindow.showLoadingBox();
//
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData CCBS.NUMBER_OF_YEARS " + CCBS.NUMBER_OF_YEARS);
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData CCBS.NAME_FIRST_YEARS_TO_SHOW " + CCBS.NAME_FIRST_YEARS_TO_SHOW);
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData CCBS.NUMBER_FIRST_YEARS_TO_SHOW " + CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
//			
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Before getCCBSData gaulCode " +gaulCode);
//			
//			AMISServiceEntry.getInstance().getCCBSData(gaulCode, CCBS.YEARS,CCBS.NUMBER_FIRST_YEARS_TO_SHOW,
//			                                           new AsyncCallback<Book>()
//			                                           {
//				                                           public void onSuccess(Book result)
//				                                           {
//					                                           loadingWindow.destroyLoadingBox();
//					                                           
//					                                           // System.out.println("Book (Client):"); // DEBUG
//					                                           // System.out.println(result);    // DEBUG
//	
//					                                           try
//					                                           {
//					                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess" );
//						                                           Book book = makeClientBook(result);
//						                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 1" );
//						                                           for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++)
//						                                           {
//						                                        	   System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess ciclo" );
//							                                           String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
//							                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:title " +title);
//							                                           bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
//							                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess fine ciclo" );
//						                                           }
//						                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess 2" );
//						                                           bookPanel.load(book);
//						                                           System.out.println("Class: CCBSParametersPanel Function: loadAction Text:onsuccess end" );
//					                                           }
//					                                           catch (Exception e)
//					                                           {
//						                                           e.printStackTrace();
//					                                           }
//				                                           }
//	
//				                                           public void onFailure(Throwable caught)
//				                                           {
//					                                           loadingWindow.destroyLoadingBox();
//	
//					                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
//				                                           }
//			                                           });
//		}
//	}
	
//	private void load(String gaulCode, final String gaulName)
//	{
//		final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading CCBS data for " + gaulName, "");
//		loadingWindow.showLoadingBox();
//
//		AMISServiceEntry.getInstance().getCCBSData(gaulCode, CCBS.YEARS, CCBS.NUMBER_FIRST_YEARS_TO_SHOW,
//		                                           new AsyncCallback<Book>()
//		                                           {
//			                                           public void onSuccess(Book result)
//			                                           {
//				                                           loadingWindow.destroyLoadingBox();
//				                                           
//				                                           // System.out.println("Book (Client):"); // DEBUG
//				                                           // System.out.println(result);    // DEBUG
//
//				                                           try
//				                                           {
//
//					                                           Book book = makeClientBook(result);
//					                                           for (int pageNum = 0; pageNum < book.numPages(); pageNum++)
//					                                           {
//						                                           String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
//						                                           bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
//					                                           }
//					                                           bookPanel.load(book);
//				                                           }
//				                                           catch (Exception e)
//				                                           {
//					                                           e.printStackTrace();
//				                                           }
//			                                           }
//
//			                                           public void onFailure(Throwable caught)
//			                                           {
//				                                           loadingWindow.destroyLoadingBox();
//
//				                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
//			                                           }
//		                                           });
//	}

	private static void unload()
	{
		try {
			bookPanel.unload();
		} catch (Exception e) {
			System.out.println("book panel null");
		}
	}

	public static Book makeClientBook(Book dbBook) throws Exception
	{
		// copy pages
		//System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:1" );
		int numRows = CCBS.FIELD_NAMES.length;
		int numCols = dbBook.getPage(0).numCols();
		//System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:numRows"+numRows+" numCols "+numCols );

		//Book guiBook = new Book(dbBook.numPages() + 2);
		Book guiBook = new Book(dbBook.numPages());
		//System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:2 dbBook.numPages()"+dbBook.numPages() );
		for (int pageNum = 0; pageNum < dbBook.numPages(); pageNum++)
		{
		//	System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text: loop 1 pageNum "+pageNum );
			Page page    = dbBook.getPage(pageNum);
		//	System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text: loop 2 " );
			Page guiPage = new Page(numRows, numCols);
		//	System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text: loop 3 " );
			guiBook.setPage(pageNum, guiPage);
		//	System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text: loop 4 " );
			fillCropPage(guiPage, page);
		//	System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text: loop 5 " );
		//	System.out.println("" );
		}
		//System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:3" );
//		// add coarse grains page
//		Page coarseGrainsPage = new Page(numRows, numCols);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:4" );
//		guiBook.setPage(dbBook.numPages(), coarseGrainsPage);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:5" );
//		fillCoarseGrainsPage(coarseGrainsPage);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:6" );
//
//		// add total cereals page
//		Page totalCerealsPage = new Page(numRows, numCols);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:7" );
//		guiBook.setPage(dbBook.numPages() + 1, totalCerealsPage);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:8" );
//		fillTotalCerealsPage(totalCerealsPage);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:9" );

		// update cells
	
		
		guiBook.update();
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBook Text:10" );
		return guiBook;
	}

	public static Book makeClientBookForNew() throws Exception
	{
		//Case NEW
		// copy pages
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text:1" );
		int numRows = CCBS.FIELD_NAMES.length;
		int numCols = (CCBS.NUMBER_OF_YEARS+1) * 2;//14
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text:numRows"+numRows+" numCols "+numCols );

		//Book guiBook = new Book(dbBook.numPages() + 2);
		Book guiBook = new Book(1);
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text:2 dbBook.numPages()" );
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text: loop 1 pageNum ");
		//Page page    = dbBook.getPage(0);
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text: loop 2 " );
		Page guiPage = new Page(numRows, numCols);
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text: loop 3 " );
		guiBook.setPage(0, guiPage);
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text: loop 4 " );
		fillNewCropPage(guiPage);
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text: loop 5 " );
//		System.out.println("" );
//		System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text:3" );
		
//		for(int iRow=0; iRow<numRows; iRow++)
//		{
//			int iCol=0;
//			while(iCol<(numCols-2))
//			{
//				guiPage.setCell(iRow, iCol, "1");
//				//page.setCell(rowNum, colNum + 1, el.getNote());
//				guiPage.setCell(iRow, iCol + 1, "r");
//				iCol+=2;
//			}
//		}
		
		guiBook.update();
	//	System.out.println("Class: CCBSParametersPanel Function: makeClientBookForNew Text:10" );
		return guiBook;
	}
	
//	private static void fillCropPage(Page guiPage, Page dbPage) throws Exception
//	{
//		// copy available data
//		copyRow(guiPage,  0, dbPage,  0); // Population
//		copyRow(guiPage,  5, dbPage,  1); // Production
//		copyRow(guiPage,  6, dbPage,  2); // J/J Imports
//		copyRow(guiPage,  7, dbPage,  3); // Imports
//		copyRow(guiPage,  8, dbPage,  4); // Commercial Imports
//		copyRow(guiPage,  9, dbPage,  5); // Food Aid
//		copyRow(guiPage, 13, dbPage,  6); // Food Use
//		copyRow(guiPage, 14, dbPage,  7); // Feed Use
//		copyRow(guiPage, 15, dbPage,  8); // Other Uses
//		copyRow(guiPage, 16, dbPage,  9); // J/J Exports
//		copyRow(guiPage, 17, dbPage, 10); // Exports
//		copyRow(guiPage, 18, dbPage, 11); // Closing Stocks
//		copyRow(guiPage, 19, dbPage, 12); // Of which Government
//		copyRow(guiPage, 22, dbPage, 13); // Area Harvested
//
//		// TODO this should be computed for all available years, not only the displayed ones
//
//		// opening stocks = closing stocks of previous year (4 = 18[-2])
//		for (int colNum = 2; colNum < dbPage.numCols(); colNum += 2)
//			guiPage.getCell(4, colNum)
//					.addRef(18, colNum - 2)
//					.setFormula(new CellFormula() { public double eval() { return v(0); }})
//					.setValid(false);
//
//		for (int colNum = 0; colNum < dbPage.numCols(); colNum += 2)
//		{
//			// domestic availability = opening stocks + production (3 = 4 + 5)
//			guiPage.getCell(3, colNum)
//					.addRef(4)
//					.addRef(5)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
//					.setValid(false);
//
//			// total supply = domestic availability + imports (2 = 3 + 7)
//			guiPage.getCell(2, colNum)
//					.addRef(3)
//					.addRef(7)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
//					.setValid(false);
//
//			// domestic utilization = food use + feed use + other uses (12 = 13 + 14 + 15)
//			guiPage.getCell(12, colNum)
//					.addRef(13)
//					.addRef(14)
//					.addRef(15)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
//
//			// total utilization = domestic utilization + exports + closing stocks (11 = 12 + 17 + 18)
//			guiPage.getCell(11, colNum)
//					.addRef(12)
//					.addRef(17)
//					.addRef(18)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
//
//			// per cap. food use = food use / population * 1000 (20 = 13 / 0)
//			guiPage.getCell(20, colNum)
//					.addRef(13)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//
//			// per cap. feed use = feed use / population * 1000 (21 = 14 / 0)
//			guiPage.getCell(21, colNum)
//					.addRef(14)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//
//			// yield = production / area (23 = 5 / 22)
//			guiPage.getCell(23, colNum)
//					.addRef(5)
//					.addRef(22)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//		}
//	}
	//Before moving Area Harvested and Yield below Production
//	private static void fillCropPage(Page guiPage, Page dbPage) throws Exception
//	{
//		// copy available data
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: Start ");
//		copyRow(guiPage,  0, dbPage,  0); // Population
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 1 ");
//		copyRow(guiPage,  5, dbPage,  1); // Production
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 2 ");
//		copyRow(guiPage,  6, dbPage,  2); // J/J Imports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 3 ");
//		copyRow(guiPage,  7, dbPage,  3); // Imports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 4 ");
//		//copyRow(guiPage,  8, dbPage,  4); // Commercial Imports
//		//copyRow(guiPage,  9, dbPage,  5); // Food Aid
//		copyRow(guiPage, 11, dbPage,  4); // Food Use
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 5 ");
//		copyRow(guiPage, 12, dbPage,  5); // Feed Use
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 6 ");
//		copyRow(guiPage, 13, dbPage,  6); // Other Uses
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 7 ");
//		copyRow(guiPage, 14, dbPage,  7); // J/J Exports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 8 ");
//		copyRow(guiPage, 15, dbPage, 8); // Exports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 9 ");
//		copyRow(guiPage, 16, dbPage, 9); // Closing Stocks
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 10 ");
//		copyRow(guiPage, 17, dbPage, 10); // Of which Government
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 11 ");
//		copyRow(guiPage, 20, dbPage, 11); // Area Harvested
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 12 ");
//		
//		// TODO this should be computed for all available years, not only the displayed ones
//
//		// opening stocks = closing stocks of previous year (4 = 16[-2])
//		for (int colNum = 2; colNum < dbPage.numCols(); colNum += 2)
//			guiPage.getCell(4, colNum)
//					.addRef(16, colNum - 2)
//					.setFormula(new CellFormula() { public double eval() { return v(0); }})
//					.setValid(false);
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 13 ");
//		for (int colNum = 0; colNum < dbPage.numCols(); colNum += 2)
//		{
//			System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 14 ");
//			// domestic availability = opening stocks + production (3 = 4 + 5)
//			guiPage.getCell(3, colNum)
//					.addRef(4)
//					.addRef(5)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
//					.setValid(false);
//
//			// total supply = domestic availability + imports (2 = 3 + 7)
//			guiPage.getCell(2, colNum)
//					.addRef(3)
//					.addRef(7)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
//					.setValid(false);
//
//			// domestic utilization = food use + feed use + other uses (10 = 11 + 12 + 13)
//			guiPage.getCell(10, colNum)
//					.addRef(11)
//					.addRef(12)
//					.addRef(13)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
//
//			// total utilization = domestic utilization + exports + closing stocks (9 = 10 + 15 + 16)
//			guiPage.getCell(9, colNum)
//					.addRef(10)
//					.addRef(15)
//					.addRef(16)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
//			System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 15 ");
//			// per cap. food use = food use / population * 1000 (18 = 11 / 0)
//			guiPage.getCell(18, colNum)
//					.addRef(11)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//
//			// per cap. feed use = feed use / population * 1000 (19 = 12 / 0)
//			guiPage.getCell(19, colNum)
//					.addRef(12)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//
//			// yield = production / area (21 = 5 / 20)
//			guiPage.getCell(21, colNum)
//					.addRef(5)
//					.addRef(20)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//			System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 16 ");
//		}
//	}
	
//	private static void fillCropPage(Page guiPage, Page dbPage) throws Exception
//	{
//		// copy available data
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: Start ");
//		copyRow(guiPage,  0, dbPage,  0); // Population
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 1 ");
//		copyRow(guiPage,  4, dbPage,  1); // Opening Stocks
//		copyRow(guiPage,  5, dbPage,  2); // Production
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 2 ");
//		copyRow(guiPage, 6, dbPage, 3); // Area Harvested
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 12 ");
//		copyRow(guiPage,  8, dbPage,  4); // J/J Imports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 3 ");
//		copyRow(guiPage,  9, dbPage,  5); // Imports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 4 ");
//		//copyRow(guiPage,  8, dbPage,  4); // Commercial Imports
//		//copyRow(guiPage,  9, dbPage,  5); // Food Aid
//		copyRow(guiPage, 12, dbPage,  6); // Food Use
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 5 ");
//		copyRow(guiPage, 13, dbPage,  7); // Feed Use
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 6 ");
//		copyRow(guiPage, 14, dbPage,  8); // Other Uses
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 7 ");
//		copyRow(guiPage, 15, dbPage,  9); // J/J Exports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 8 ");
//		copyRow(guiPage, 16, dbPage, 10); // Exports
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 9 ");
//		copyRow(guiPage, 17, dbPage, 11); // Closing Stocks
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 10 ");
//		copyRow(guiPage, 18, dbPage, 12); // Of which Government
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 11 ");
//		
//		
//		// TODO this should be computed for all available years, not only the displayed ones
//
//		// opening stocks = closing stocks of previous year (4 = 17[-2])
//		for (int colNum = 2; colNum < dbPage.numCols(); colNum += 2)
//		{
//			guiPage.getCell(4, colNum)
//					.addRef(17, colNum - 2)
//					.setFormula(new CellFormula() { public double eval() { return v(0); }})
//					.setValid(false);
//		}
//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 13 ");
//		for (int colNum = 0; colNum < dbPage.numCols(); colNum += 2)
//		{
//			System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 14 ");
//			// unbalanced = total supply - total utilization (1 = 2 + 10)
//				guiPage.getCell(1, colNum)
//						.addRef(2)
//						.addRef(10)
//						.setFormula(new CellFormula() { public double eval() { return v(0) - v(1); }})
//						.setValid(false);
//			// domestic availability = opening stocks + production (3 = 4 + 5)
//			guiPage.getCell(3, colNum)
//					.addRef(4)
//					.addRef(5)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
//					.setValid(false);
//			// total supply = domestic availability + imports (2 = 3 + 9)
//			guiPage.getCell(2, colNum)
//					.addRef(3)
//					.addRef(9)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
//					.setValid(false);
//			// yield = production / area (7 = 5 / 6)
//			guiPage.getCell(7, colNum)
//					.addRef(5)
//					.addRef(6)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//			// domestic utilization = food use + feed use + other uses (11 = 12 + 13 + 14)
//			guiPage.getCell(11, colNum)
//					.addRef(12)
//					.addRef(13)
//					.addRef(14)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
//			// total utilization = domestic utilization + exports + closing stocks (10 = 11 + 16 + 17)
//			guiPage.getCell(10, colNum)
//					.addRef(11)
//					.addRef(16)
//					.addRef(17)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
//			System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 15 ");
//			// per cap. food use = (food use*1000000 / population) (19 = 12 / 0)
//			guiPage.getCell(19, colNum)
//					.addRef(12)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? (v(0)*1000000) / v(1) : 0; }})
//					.setValid(false);
//
////			// per cap. feed use = feed use / population * 1000 (21 = 13 / 0)
////			guiPage.getCell(21, colNum)
////					.addRef(13)
////					.addRef(0)
////					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
////					.setValid(false);
//			
//			System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 16 ");
//		}
//	}

	private static void fillNewCropPage(Page guiPage) throws Exception
	{
		// opening stocks = closing stocks of previous year (4 = 17[-2])
		for (int colNum = 2; colNum < guiPage.numCols(); colNum += 2)
		{
			guiPage.getCell(4, colNum)
					.addRef(17, colNum - 2)
					//.setFormula(new CellFormula() { public double eval() { return v(0); }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				else { return ""+v(0);}
				}})
					.setValid(false);
		}
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 13 ");
		for (int colNum = 0; colNum < guiPage.numCols(); colNum += 2)
		{
		//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 14 ");
			// unbalanced = total supply - total utilization (1 = 2 - 10)
				guiPage.getCell(1, colNum)
						.addRef(2)
						.addRef(10)
						//.setFormula(new CellFormula() { public double eval() { return v(0) - v(1); }})
						.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) - v(1);
					return ""+ris;}
				}})
				.setValid(false);
			// domestic availability = opening stocks + production (3 = 4 + 5)
			guiPage.getCell(3, colNum)
					.addRef(4)
					.addRef(5)
					//.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) + v(1);
				return ""+ris;}
				}})
					.setValid(false);
			// total supply = domestic availability + imports (2 = 3 + 9)
			guiPage.getCell(2, colNum)
					.addRef(3)
					.addRef(9)
					//.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) + v(1);
				return ""+ris;}
				}})
					.setValid(false);
			// yield = production / area (7 = 5 / 6)
			guiPage.getCell(7, colNum)
					.addRef(5)
					.addRef(6)
					//.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) / v(1);
					return v(1) > 0 ? (""+ris) : ""+0;}
				}})
					.setValid(false);
			// domestic utilization = food use + feed use + other uses (11 = 12 + 13 + 14)
//			guiPage.getCell(11, colNum)
//					.addRef(12)
//					.addRef(13)
//					.addRef(14)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
			guiPage.getCell(11, colNum)
			.addRef(12)
			.addRef(13)
			.addRef(14)
			//.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
			.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				if(vNull(2).equals("")) return "";
				else { double ris = v(0) + v(1) + v(2);
				return ""+ris;}
				}})
			.setValid(false);
			// total utilization = domestic utilization + exports + closing stocks (10 = 11 + 16 + 17)
//			guiPage.getCell(10, colNum)
//					.addRef(11)
//					.addRef(16)
//					.addRef(17)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
			
			guiPage.getCell(10, colNum)
			.addRef(11)
			.addRef(16)
			.addRef(17)
			.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				if(vNull(2).equals("")) return "";
				else { double ris = v(0) + v(1) + v(2);
				return ""+ris;}
				}})
				//public double eval() { return v(0) + v(1) + v(2); }})
			.setValid(false);
		//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 15 ");
			// per cap. food use = (food use*1000000 / population) (19 = 12 / 0)
//			guiPage.getCell(19, colNum)
//					.addRef(12)
//					.addRef(0)
//					//.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? (v(0)*1000000) / v(1) : 0; }})
//					.setFormula(new CellFormula() { public String evalString() { if(nullData(1)) return "";
//					if(nullData(0)) return "";
//					else return v(1) > 0 ? ""+((v(0)*1000000) / v(1)) : ""+0; }})
//					.setValid(false);
			
			guiPage.getCell(19, colNum)
			.addRef(12)
			.addRef(0)
			//.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? (v(0)*1000000) / v(1) : 0; }})
			.setFormula(new CellFormula() { public String evalStringNull() { if(vNull(1).equals("")) return "";
			if(vNull(0).equals("")) return "";
			else 
			{double ris = (v(0)*1000000) / v(1);
				return v(1) > 0 ? ""+ris : ""+0;}
			}})
			.setValid(false);
		}
	}
	
	private static void fillCropPage(Page guiPage, Page dbPage) throws Exception
	{
		// copy available data
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: Start ");
		copyRow(guiPage,  0, dbPage,  0); // Population
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 1 ");
		copyRow(guiPage,  4, dbPage,  1); // Opening Stocks
		copyRow(guiPage,  5, dbPage,  2); // Production
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 2 ");
		copyRow(guiPage, 6, dbPage, 3); // Area Harvested
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 12 ");
		copyRow(guiPage,  8, dbPage,  4); // J/J Imports
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 3 ");
		copyRow(guiPage,  9, dbPage,  5); // Imports
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 4 ");
		//copyRow(guiPage,  8, dbPage,  4); // Commercial Imports
		//copyRow(guiPage,  9, dbPage,  5); // Food Aid
		copyRow(guiPage, 12, dbPage,  6); // Food Use
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 5 ");
		copyRow(guiPage, 13, dbPage,  7); // Feed Use
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 6 ");
		copyRow(guiPage, 14, dbPage,  8); // Other Uses
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 7 ");
		copyRow(guiPage, 15, dbPage,  9); // J/J Exports
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 8 ");
		copyRow(guiPage, 16, dbPage, 10); // Exports
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 9 ");
		copyRow(guiPage, 17, dbPage, 11); // Closing Stocks
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 10 ");
		copyRow(guiPage, 18, dbPage, 12); // Of which Government
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 11 ");
		
		
		// TODO this should be computed for all available years, not only the displayed ones

		// opening stocks = closing stocks of previous year (4 = 17[-2])
		for (int colNum = 2; colNum < dbPage.numCols(); colNum += 2)
		//It's not necessary for the last three columns because they are a particular case 
		//for (int colNum = 2; colNum < (dbPage.numCols()-5); colNum += 2)
		{
			guiPage.getCell(4, colNum)
					.addRef(17, colNum - 2)
					//.setFormula(new CellFormula() { public double eval() { return v(0); }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				else { return ""+v(0);}
				}})
					.setValid(false);
		}
	//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 13 ");
		for (int colNum = 0; colNum < dbPage.numCols(); colNum += 2)
		{
	//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 14 ");
			// unbalanced = total supply - total utilization (1 = 2 - 10)
				guiPage.getCell(1, colNum)
						.addRef(2)
						.addRef(10)
						//.setFormula(new CellFormula() { public double eval() { return v(0) - v(1); }})
						.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) - v(1);
					return ""+ris;}
				}})
				.setValid(false);
			// domestic availability = opening stocks + production (3 = 4 + 5)
			guiPage.getCell(3, colNum)
					.addRef(4)
					.addRef(5)
					//.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) + v(1);
				return ""+ris;}
				}})
					.setValid(false);
			// total supply = domestic availability + imports (2 = 3 + 9)
			guiPage.getCell(2, colNum)
					.addRef(3)
					.addRef(9)
					//.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) + v(1);
				return ""+ris;}
				}})
					.setValid(false);
			// yield = production / area (7 = 5 / 6)
			guiPage.getCell(7, colNum)
					.addRef(5)
					.addRef(6)
					//.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				else { double ris = v(0) / v(1);
					return v(1) > 0 ? (""+ris) : ""+0;}
				}})
					.setValid(false);
			// domestic utilization = food use + feed use + other uses (11 = 12 + 13 + 14)
//			guiPage.getCell(11, colNum)
//					.addRef(12)
//					.addRef(13)
//					.addRef(14)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
			guiPage.getCell(11, colNum)
			.addRef(12)
			.addRef(13)
			.addRef(14)
			//.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
			.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				if(vNull(2).equals("")) return "";
				else { double ris = v(0) + v(1) + v(2);
				return ""+ris;}
				}})
			.setValid(false);
			// total utilization = domestic utilization + exports + closing stocks (10 = 11 + 16 + 17)
//			guiPage.getCell(10, colNum)
//					.addRef(11)
//					.addRef(16)
//					.addRef(17)
//					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
//					.setValid(false);
			
			guiPage.getCell(10, colNum)
			.addRef(11)
			.addRef(16)
			.addRef(17)
			.setFormula(new CellFormula() {
				public String evalStringNull() { if(vNull(0).equals("")) return "";
				if(vNull(1).equals("")) return "";
				if(vNull(2).equals("")) return "";
				else { double ris = v(0) + v(1) + v(2);
				return ""+ris;}
				}})
				//public double eval() { return v(0) + v(1) + v(2); }})
			.setValid(false);
	//		System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 15 ");
			// per cap. food use = (food use*1000000 / population) (19 = 12 / 0)
//			guiPage.getCell(19, colNum)
//					.addRef(12)
//					.addRef(0)
//					//.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? (v(0)*1000000) / v(1) : 0; }})
//					.setFormula(new CellFormula() { public String evalString() { if(nullData(1)) return "";
//					if(nullData(0)) return "";
//					else return v(1) > 0 ? ""+((v(0)*1000000) / v(1)) : ""+0; }})
//					.setValid(false);
			
			guiPage.getCell(19, colNum)
			.addRef(12)
			.addRef(0)
			//.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? (v(0)*1000000) / v(1) : 0; }})
			.setFormula(new CellFormula() { public String evalStringNull() { if(vNull(1).equals("")) return "";
			if(vNull(0).equals("")) return "";
			else 
			{double ris = (v(0)*1000000) / v(1);
				return v(1) > 0 ? ""+ris : ""+0;}
			}})
			.setValid(false);

//			// per cap. feed use = feed use / population * 1000 (21 = 13 / 0)
//			guiPage.getCell(21, colNum)
//					.addRef(13)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
			
		//	System.out.println("Class; CCBSParameterPanel  Function: fillCropPage Text: 16 ");
		}
	}
	
//	private static void fillCoarseGrainsPage(Page page)
//	{
//		for (int colNum = 0; colNum < page.numCols(); colNum += 2)
//		{
//			page.getCell(0, colNum)         // = Population
//				.addRef(0, 0, colNum)
//				.setFormula(new CellFormula() { public double eval() { return v(0); }})
//				.setValid(false);
//			                                // 1 Unbalanced
//			addCoarseGrainsTotalRow(page,  2); // + Total Supply
//			addCoarseGrainsTotalRow(page,  3); // + Domestic Availability
//			addCoarseGrainsTotalRow(page,  4); // + Opening Stocks
//			addCoarseGrainsTotalRow(page,  5); // + Production
//			addCoarseGrainsTotalRow(page,  6); // + J/J Imports
//			addCoarseGrainsTotalRow(page,  7); // + Imports
//			addCoarseGrainsTotalRow(page,  8); // + Commercial Imports
//			addCoarseGrainsTotalRow(page,  9); // + Food Aid
//			addCoarseGrainsTotalRow(page, 10); // + Unbalanced Imports
//			addCoarseGrainsTotalRow(page, 11); // + Total Utilization
//			addCoarseGrainsTotalRow(page, 12); // + Domestic Utilization
//			addCoarseGrainsTotalRow(page, 13); // + Food Use
//			addCoarseGrainsTotalRow(page, 14); // + Feed Use
//			addCoarseGrainsTotalRow(page, 15); // + Other Uses
//			addCoarseGrainsTotalRow(page, 16); // + J/J Exports
//			addCoarseGrainsTotalRow(page, 17); // + Exports
//			addCoarseGrainsTotalRow(page, 18); // + Closing Stocks
//			addCoarseGrainsTotalRow(page, 19); // + Of which Government
//			page.getCell(20, colNum) // / Per Cap. Food Use
//					.addRef(13)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//
//			page.getCell(21, colNum) // / Per Cap. Feed Use
//					.addRef(14)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//			addTotalRow(page, 22); // + Area Harvested
//			page.getCell(23, colNum) // / Yield
//					.addRef(5)
//					.addRef(22)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//		}
//	}

	private static void fillCoarseGrainsPage(Page page)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum += 2)
		{
			page.getCell(0, colNum)         // = Population
				.addRef(0, 0, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0); }})
				.setValid(false);
			                                // 1 Unbalanced
			addCoarseGrainsTotalRow(page,  2); // + Total Supply
			addCoarseGrainsTotalRow(page,  3); // + Domestic Availability
			addCoarseGrainsTotalRow(page,  4); // + Opening Stocks
			addCoarseGrainsTotalRow(page,  5); // + Production
			addCoarseGrainsTotalRow(page,  6); // + J/J Imports
			addCoarseGrainsTotalRow(page,  7); // + Imports
			//addCoarseGrainsTotalRow(page,  8); // + Commercial Imports
			//addCoarseGrainsTotalRow(page,  9); // + Food Aid
			addCoarseGrainsTotalRow(page, 8); // + Unbalanced Imports
			addCoarseGrainsTotalRow(page, 9); // + Total Utilization
			addCoarseGrainsTotalRow(page, 10); // + Domestic Utilization
			addCoarseGrainsTotalRow(page, 11); // + Food Use
			addCoarseGrainsTotalRow(page, 12); // + Feed Use
			addCoarseGrainsTotalRow(page, 13); // + Other Uses
			addCoarseGrainsTotalRow(page, 14); // + J/J Exports
			addCoarseGrainsTotalRow(page, 15); // + Exports
			addCoarseGrainsTotalRow(page, 16); // + Closing Stocks
			addCoarseGrainsTotalRow(page, 17); // + Of which Government
			page.getCell(18, colNum) // / Per Cap. Food Use
					.addRef(11)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);

			page.getCell(19, colNum) // / Per Cap. Feed Use
					.addRef(12)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
			addTotalRow(page, 20); // + Area Harvested
			page.getCell(21, colNum) // / Yield
					.addRef(5)
					.addRef(20)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
		}
	}
	// TODO: check if this classification is correct
	//   0 wheat
	// x 1 barley
	// x 2 maize
	//   3 rice (paddy)
	//   4 rice (milled)
	// x 5 millet
	// x 6 other cereals
	// x 7 grain sorghum
	// x 8 rye
	// x 9 oats
//	private static void addCoarseGrainsTotalRow(Page page, int rowNum)
//	{
//		for (int colNum = 0; colNum < page.numCols(); colNum+=2)
//			page.getCell(rowNum, colNum)
//				.addRef(1, rowNum, colNum)
//				.addRef(2, rowNum, colNum)
//				.addRef(5, rowNum, colNum)
//				.addRef(6, rowNum, colNum)
//				.addRef(7, rowNum, colNum)
//				.addRef(8, rowNum, colNum)
//				.addRef(9, rowNum, colNum)
//				.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2) + v(3) + v(4) + v(5) + v(6); }})
//				.setValid(false);
//	}

	private static void addCoarseGrainsTotalRow(Page page, int rowNum)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum+=2)
			page.getCell(rowNum, colNum)
				.addRef(1, rowNum, colNum)
				.addRef(2, rowNum, colNum)
				.addRef(5, rowNum, colNum)
				.addRef(6, rowNum, colNum)
				.addRef(7, rowNum, colNum)
				.addRef(8, rowNum, colNum)
				.addRef(9, rowNum, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2) + v(3) + v(4); }})
				.setValid(false);
	}
	
//	private static void fillTotalCerealsPage(Page page)
//	{
//		for (int colNum = 0; colNum < page.numCols(); colNum += 2)
//		{
//			page.getCell(0, colNum)         // = Population
//				.addRef(0, 0, colNum)
//				.setFormula(new CellFormula() { public double eval() { return v(0); }})
//				.setValid(false);
//			                                // 1 Unbalanced
//			addTotalRow(page,  2); // + Total Supply
//			addTotalRow(page,  3); // + Domestic Availability
//			addTotalRow(page,  4); // + Opening Stocks
//			addTotalRow(page,  5); // + Production
//			addTotalRow(page,  6); // + J/J Imports
//			addTotalRow(page,  7); // + Imports
//			addTotalRow(page,  8); // + Commercial Imports
//			addTotalRow(page,  9); // + Food Aid
//			addTotalRow(page, 10); // + Unbalanced Imports
//			addTotalRow(page, 11); // + Total Utilization
//			addTotalRow(page, 12); // + Domestic Utilization
//			addTotalRow(page, 13); // + Food Use
//			addTotalRow(page, 14); // + Feed Use
//			addTotalRow(page, 15); // + Other Uses
//			addTotalRow(page, 16); // + J/J Exports
//			addTotalRow(page, 17); // + Exports
//			addTotalRow(page, 18); // + Closing Stocks
//			addTotalRow(page, 19); // + Of which Government
//			page.getCell(20, colNum) // / Per Cap. Food Use
//					.addRef(13)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//
//			page.getCell(21, colNum) // / Per Cap. Feed Use
//					.addRef(14)
//					.addRef(0)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//			addTotalRow(page, 22); // + Area Harvested
//			page.getCell(23, colNum) // / Yield
//					.addRef(5)
//					.addRef(22)
//					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
//					.setValid(false);
//		}
//	}
	
	private static void fillTotalCerealsPage(Page page)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum += 2)
		{
			page.getCell(0, colNum)         // = Population
				.addRef(0, 0, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0); }})
				.setValid(false);
			                                // 1 Unbalanced
			addTotalRow(page,  2); // + Total Supply
			addTotalRow(page,  3); // + Domestic Availability
			addTotalRow(page,  4); // + Opening Stocks
			addTotalRow(page,  5); // + Production
			addTotalRow(page,  6); // + J/J Imports
			addTotalRow(page,  7); // + Imports
			//addTotalRow(page,  8); // + Commercial Imports
			//addTotalRow(page,  9); // + Food Aid
			addTotalRow(page, 8); // + Unbalanced Imports
			addTotalRow(page, 9); // + Total Utilization
			addTotalRow(page, 10); // + Domestic Utilization
			addTotalRow(page, 11); // + Food Use
			addTotalRow(page, 12); // + Feed Use
			addTotalRow(page, 13); // + Other Uses
			addTotalRow(page, 14); // + J/J Exports
			addTotalRow(page, 15); // + Exports
			addTotalRow(page, 16); // + Closing Stocks
			addTotalRow(page, 17); // + Of which Government
			page.getCell(18, colNum) // / Per Cap. Food Use
					.addRef(11)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);

			page.getCell(19, colNum) // / Per Cap. Feed Use
					.addRef(12)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
			addTotalRow(page, 20); // + Area Harvested
			page.getCell(21, colNum) // / Yield
					.addRef(5)
					.addRef(20)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
		}
	}

	private static void addTotalRow(Page page, int rowNum)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum+=2)
			page.getCell(rowNum, colNum)
				.addRef(0, rowNum, colNum)
				.addRef(1, rowNum, colNum)
				.addRef(2, rowNum, colNum)
				.addRef(3, rowNum, colNum)
				.addRef(4, rowNum, colNum)
				.addRef(5, rowNum, colNum)
				.addRef(6, rowNum, colNum)
				.addRef(7, rowNum, colNum)
				.addRef(8, rowNum, colNum)
				.addRef(9, rowNum, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2) + v(3) + v(4) + v(5) + v(6) + v(7) + v(8) + v(9); }})
				.setValid(false);
	}

	private static void copyRow(Page toPage, int toRow, Page fromPage, int fromRow)
	{
		//Opening Stocks
		if(toRow == 4)
		{
			int colNum = 0;
			Cell fromCell = fromPage.getCell(fromRow, colNum);
			if (fromCell.isNumeric()) toPage.setCell(toRow, colNum, fromCell.getValue());
			else                      toPage.setCell(toRow, colNum, fromCell.getText());
			//toPage.setCell(toRow, colNum + 1, fromPage.getCell(fromRow, colNum + 1).getText());
		}
		else
		{
			for (int colNum = 0; colNum < fromPage.numCols(); colNum += 2)
			{
				Cell fromCell = fromPage.getCell(fromRow, colNum);
				if (fromCell.isNumeric()) toPage.setCell(toRow, colNum, fromCell.getValue());
				else                      toPage.setCell(toRow, colNum, fromCell.getText());
				toPage.setCell(toRow, colNum + 1, fromPage.getCell(fromRow, colNum + 1).getText());
			}
		}
	}
	
	private /*static*/ void loadDataForListBox(String dataSource, final String selectionType, String countryCode, String datasource)
	{
		//AMISServiceEntry.getInstance().askAMIS(qvo, callback)
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		if(datasource.equals(CCBS.elementForTraining))
		{
			//String selectionType = "AMIS_COUNTRIES";
			if(selectionType.equals(AMISConstants.AMIS_COUNTRIES.toString()))
			{
				qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRY_CROP_COUNT.toString());	
			}
			else if(selectionType.equals(AMISConstants.AMIS_PRODUCTS.toString()))
			{
				qvo.setTypeOfOutput(AMISConstants.AMIS_PRODUCT_CROP_COUNT.toString());	
				qvo.setCountryCode(countryCode);
			}
			else
			{
				qvo.setTypeOfOutput(selectionType);	
			}
			//qvo.setTypeOfOutput(selectionType);
			qvo.setSelectedDataset(AMISConstants.CBS.toString());
		}
		else
		{
			//Case NEW
			qvo.setTotalFlag(true);
			qvo.setTypeOfOutput(selectionType);	
		}
//		if(dataSource.equals("FAOSTAT"))
//		{
//			qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
//		}
//		else if(dataSource.equals("PSD"))
//		{
//			qvo.setSelectedDataset(AMISConstants.PSD.toString());
//		}
//		else if(dataSource.equals("CBS"))
//		{
//			qvo.setSelectedDataset(AMISConstants.CBS.toString());
//		}
		
//		ListStore<AMISCodesModelData> country2 = new ListStore<AMISCodesModelData>();
//		country2.add(new AMISCodesModelData("test","test"));
//		countryListBox.setStore(country2);
		
		
		
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
					int iData=0;
					
					if(selectionType.equals(AMISConstants.AMIS_COUNTRIES.toString()))
					{
						CCBS.COUNTRY_NAMES.clear();
						CCBS.COUNTRY_CODES.clear();
						//gaulNames.clear();
						//countryListBox.clear();
						//countryListBox.setWidth("90");
						//countryListBox.addItem("");
					}
					else if(selectionType.equals(AMISConstants.AMIS_PRODUCTS.toString()))
					{
						CCBS.COMMODITY_NAMES_CBS.clear();
						CCBS.COMMODITY_CODES_CBS.clear();
						//commodityNames.clear();
						//commodityListBox.clear();
						//commodityListBox.setWidth("90");
						//commodityListBox.addItem("");
					}
					int J=0;
					for(AMISCodesModelData topcode : vo.getCodes()) {
					//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: topcode.getLabel() "+topcode.getLabel());
						if(selectionType.equals(AMISConstants.AMIS_COUNTRIES.toString()))
						{
							CCBS.COUNTRY_NAMES.add(topcode.getLabel());
							CCBS.COUNTRY_CODES.add(topcode.getCode());
						}
						else if(selectionType.equals(AMISConstants.AMIS_PRODUCTS.toString()))
						{
							//To eliminate same commodities
							boolean toInsert = true;
							String code = topcode.getCode();
							for(int i=0; i< CCBS.COMMODITY_CODE_TO_ELIMINATE.length; i++)
							{
								if(code.equals(CCBS.COMMODITY_CODE_TO_ELIMINATE[i]))
								{
									toInsert = false;
									break;
								}
							}
							if(toInsert)
							{
								CCBS.COMMODITY_NAMES_CBS.add(topcode.getLabel());
								CCBS.COMMODITY_CODES_CBS.add(topcode.getCode());
							}
							
						//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text:CCBS.COMMODITY_NAMES_CBS.get(i) "+CCBS.COMMODITY_NAMES_CBS.get(J));
						//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text:COMMODITY_CODES_CBS.get(i) "+CCBS.COMMODITY_CODES_CBS.get(J));
						}
						
						iData++;
						//store.add(topcode);
						J++;
					}
					
					if(selectionType.equals(AMISConstants.AMIS_COUNTRIES.toString()))
					{
					//	ListStore<AMISCodesModelData> country = new ListStore<AMISCodesModelData>();
						//for (int iCountry = 0; iCountry < CCBS.COUNTRY_NAMES.size(); iCountry++) countryListBox.addItem(CCBS.COUNTRY_NAMES.get(iCountry));
						countryListBox.getStore().removeAll();
						countryListBox.getStore().add(new AMISCodesModelData("", ""));
						for (int iCountry = 0; iCountry < CCBS.COUNTRY_NAMES.size(); iCountry++)
							{
								//countryListBox.addItem(CCBS.COUNTRY_NAMES.get(iCountry));
								countryListBox.getStore().add(new AMISCodesModelData(CCBS.COUNTRY_NAMES.get(iCountry), CCBS.COUNTRY_NAMES.get(iCountry)));
							}
					//	countryListBox.setValue(null);
						
						
						//countryListBox.setStore(country);
						
					//	for(int i=0; i< countryListBox.getStore().getCount();i++)
						//System.out.println("Country label "+countryListBox.getStore().getAt(i).getLabel());
					}
					else if(selectionType.equals(AMISConstants.AMIS_PRODUCTS.toString()))
					{
						//ListStore<AMISCodesModelData> product = new ListStore<AMISCodesModelData>();
						//for (int iProduct = 0; iProduct < CCBS.COMMODITY_NAMES_CBS.size(); iProduct++) commodityListBox.addItem(CCBS.COMMODITY_NAMES_CBS.get(iProduct));
						commodityListBox.getStore().removeAll();
						commodityListBox.getStore().add(new AMISCodesModelData("", ""));
						for (int iProduct = 0; iProduct < CCBS.COMMODITY_NAMES_CBS.size(); iProduct++) 
							{
								//commodityListBox.addItem(CCBS.COMMODITY_NAMES_CBS.get(iProduct));
								if(!CCBS.COMMODITY_NAMES_CBS.get(iProduct).equals("Population"))
								{
									commodityListBox.getStore().add(new AMISCodesModelData(CCBS.COMMODITY_NAMES_CBS.get(iProduct), CCBS.COMMODITY_NAMES_CBS.get(iProduct)));
								}
							}
						//commodityListBox.setStore(product);
						//commodityListBox.setDisplayField("label");
					//	for(int i=0; i< commodityListBox.getStore().getCount();i++)
						//	System.out.println("Commodity label "+commodityListBox.getStore().getAt(i).getLabel());
					//	commodityListBox.setValue(null);
					}
					
					lc.getLayout().layout();
					
					
				//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: gaulNames.size() "+CCBS.COUNTRY_NAMES.size());

				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void loadGaulCountries()
//	{
//		//AMISServiceEntry.getInstance().askAMIS(qvo, callback)
//		AMISQueryVO qvo =  new AMISQueryVO();
//		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
//		String selectionType = "AMIS_COUNTRIES";
//		qvo.setTypeOfOutput(selectionType);	
////		if(domainFilter.getLabel().equals("FAOSTAT"))
////		{
////			qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.FAOSTAT);
////		}
////		else if(domainFilter.getLabel().equals("PSD"))
////		{
////			qvo.setSelectedDataset(AMISConstants.PSD.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.PSD);
////		}
////		else if(domainFilter.getLabel().equals("CBS"))
////		{
////			qvo.setSelectedDataset(AMISConstants.CBS.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.CBS);
////		}
//		qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
//		
//		try {
//			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//				
//				@SuppressWarnings("unchecked")
//				public void onSuccess(AMISResultVO vo) {
////					panel.getPanel().removeAll();
////					ListStore store = panel.getStore();
////					store.removeAll();
//					
//					int iCountries=0;
//					for(AMISCodesModelData topcode : vo.getCodes()) {
//						System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: topcode.getLabel() "+topcode.getLabel());
//						
//						CCBS.COUNTRY_NAMES.add(topcode.getLabel());
//						System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: gaulNames.get(iCountries) "+CCBS.COUNTRY_NAMES.get(iCountries));
//						iCountries++;
//						//store.add(topcode);
//
//					}
//					for (int i = 0; i < CCBS.COUNTRY_NAMES.size(); i++) countryListBox.addItem(CCBS.COUNTRY_NAMES.get(i));
//					System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: gaulNames.size() "+CCBS.COUNTRY_NAMES.size());
//					
//					/** TODO: make a unique builder **/
////					if ( panel.getTitle() != null )
////						panel.getPanel().add(panel.addTitle(panel.getTitle()));
////					
////					panel.addList();
////					panel.getPanel().layout();
////					
////					if ( addSelectAll ) {
////						panel.getPanel().add(FormattingUtils.addVSpace(5));
////						panel.getPanel().add(panel.buildSelectAllPanel());
////					}
////					
////					panel.getPanel().layout();
//				}
//				
//				public void onFailure(Throwable arg0) {
//	
//				}
//			});
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		AMISServiceEntry.getInstance().getGaulNames(gaulCodes, new AsyncCallback<String[]>()
////		                                           {
////			                                           public void onSuccess(String result[])
////			                                           {
////				                                           gaulNames = result;
////				                                           continueBuild();
////			                                           }
////
////			                                           public void onFailure(Throwable caught)
////			                                           {
////				                                           FenixAlert.alert("RPC Failed", "CCBSService.getGaulNames()");
////			                                           }
////		                                           });
//
//	}
	
//	private void loadDataSourceNames()
//	{
//		System.out.println("Class; CCBSParameterPanel  Function: loadDataSourceNames Text: Start ");
//		//AMISServiceEntry.getInstance().askAMIS(qvo, callback)
//		AMISQueryVO qvo =  new AMISQueryVO();
//		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
//		String selectionType = "AMIS";
//		qvo.setTypeOfOutput(selectionType);	
////		if(domainFilter.getLabel().equals("FAOSTAT"))
////		{
////			qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.FAOSTAT);
////		}
////		else if(domainFilter.getLabel().equals("PSD"))
////		{
////			qvo.setSelectedDataset(AMISConstants.PSD.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.PSD);
////		}
////		else if(domainFilter.getLabel().equals("CBS"))
////		{
////			qvo.setSelectedDataset(AMISConstants.CBS.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.CBS);
////		}
////		qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
//		
//		try {
//			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//				
//				@SuppressWarnings("unchecked")
//				public void onSuccess(AMISResultVO vo) {
////					panel.getPanel().removeAll();
////					ListStore store = panel.getStore();
////					store.removeAll();
//					//dataSourceName.clear();
//					CCBS.DATA_SOURCE_NAMES.clear();
//					dataSourceListBox.clear();
//					dataSourceListBox.setWidth("90");
//					int iDataSourceName=0;
//					dataSourceListBox.addItem("");
//					for(AMISCodesModelData topcode : vo.getCodes()) {
//						System.out.println("Class; CCBSParameterPanel  Function: loadDataSourceNames Text: topcode.getLabel() "+topcode.getLabel());
//						
//						CCBS.DATA_SOURCE_NAMES.add(topcode.getLabel());
//						System.out.println("Class; CCBSParameterPanel  Function: loadDataSourceNames Text: gaulNames.get(iCountries) "+CCBS.DATA_SOURCE_NAMES.get(iDataSourceName));
//						iDataSourceName++;
//						//store.add(topcode);
//					}
//					for (int iDataSource = 0; iDataSource < CCBS.DATA_SOURCE_NAMES.size(); iDataSource++) dataSourceListBox.addItem(CCBS.DATA_SOURCE_NAMES.get(iDataSource));
//					System.out.println("Class; CCBSParameterPanel  Function: loadDataSourceNames Text: dataSourceName.size() "+CCBS.DATA_SOURCE_NAMES.size());
//					
//					/** TODO: make a unique builder **/
////					if ( panel.getTitle() != null )
////						panel.getPanel().add(panel.addTitle(panel.getTitle()));
////					
////					panel.addList();
////					panel.getPanel().layout();
////					
////					if ( addSelectAll ) {
////						panel.getPanel().add(FormattingUtils.addVSpace(5));
////						panel.getPanel().add(panel.buildSelectAllPanel());
////					}
////					
////					panel.getPanel().layout();
//				}
//				
//				public void onFailure(Throwable arg0) {
//	
//				}
//			});
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	private void loadDataSourceNames()
	{
		CCBS.DATA_SOURCE_NAMES.clear();
		//dataSourceListBox.clear();
		//dataSourceListBox.setWidth("90");
		//dataSourceListBox.addItem("");
		//CCBS.DATA_SOURCE_NAMES.add(new String("CBS"));
		//CCBS.DATA_SOURCE_NAMES.add(new String("FAO-CBS"));
		//CCBS.DATA_SOURCE_NAMES.add(new String("NEW"));
		CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining));
		CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementEmpty));
		
//		for (int iDataSource = 0; iDataSource < CCBS.DATA_SOURCE_NAMES.size(); iDataSource++) 
//		{
//			dataSourceListBox.addItem(CCBS.DATA_SOURCE_NAMES.get(iDataSource));
//		}
		//ListStore<AMISCodesModelData> dataSourceStore = new ListStore<AMISCodesModelData>();
		dataSourceListBox.getStore().removeAll();
		for (int iDataSource = 0; iDataSource < CCBS.DATA_SOURCE_NAMES.size(); iDataSource++) 
		{
			dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.DATA_SOURCE_NAMES.get(iDataSource),CCBS.DATA_SOURCE_NAMES.get(iDataSource)));
			//dataSourceListBox.addItem(CCBS.DATA_SOURCE_NAMES.get(iDataSource));
		}
		//dataSourceListBox.setStore(dataSourceStore);
		//dataSourceListBox.setDisplayField("label");
	}

	public ComboBox<AMISCodesModelData> getC() {
		return c;
	}

	public void setC(ComboBox<AMISCodesModelData> c) {
		this.c = c;
	}

	public Radio getRadio() {
		return radio;
	}

	public void setRadio(Radio radio) {
		this.radio = radio;
	}

	public RadioGroup getRadioGroup() {
		return radioGroup;
	}

	public void setRadioGroup(RadioGroup radioGroup) {
		this.radioGroup = radioGroup;
	}

	public Html getTo() {
		return to;
	}

	public Html getStart() {
		return start;
	}

	public static String[] getFormData() {
		return formData;
	}

	public static void setFormData(String[] formData) {
		CCBSParametersPanel.formData = formData;
	}
	
//	private void loadProductNames()
//	{
//		System.out.println("Class; CCBSParameterPanel  Function: loadProductNames Text: Start ");
//		//AMISServiceEntry.getInstance().askAMIS(qvo, callback)
//		AMISQueryVO qvo =  new AMISQueryVO();
//		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
//		String selectionType = "AMIS_PRODUCTS";
//		qvo.setTypeOfOutput(selectionType);	
////		if(domainFilter.getLabel().equals("FAOSTAT"))
////		{
////			qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.FAOSTAT);
////		}
////		else if(domainFilter.getLabel().equals("PSD"))
////		{
////			qvo.setSelectedDataset(AMISConstants.PSD.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.PSD);
////		}
////		else if(domainFilter.getLabel().equals("CBS"))
////		{
////			qvo.setSelectedDataset(AMISConstants.CBS.toString());
////			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.CBS);
////		}
//		qvo.setSelectedDataset(AMISConstants.FAOSTAT.toString());
//		
//		try {
//			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//				
//				@SuppressWarnings("unchecked")
//				public void onSuccess(AMISResultVO vo) {
////					panel.getPanel().removeAll();
////					ListStore store = panel.getStore();
////					store.removeAll();
//					
//					int iCountries=0;
//					for(AMISCodesModelData topcode : vo.getCodes()) {
//						System.out.println("Class; CCBSParameterPanel  Function: loadProductNames Text: topcode.getLabel() "+topcode.getLabel());
//						
//						CCBS.COMMODITY_NAMES_CBS.add(topcode.getLabel());
//						System.out.println("Class; CCBSParameterPanel  Function: loadProductNames Text: commodityNames.get(iCountries) "+CCBS.COMMODITY_NAMES_CBS.get(iCountries));
//						iCountries++;
//						//store.add(topcode);
//
//					}
//					for (int iDataSource = 0; iDataSource < CCBS.COMMODITY_NAMES_CBS.size(); iDataSource++) commodityListBox.addItem(CCBS.COMMODITY_NAMES_CBS.get(iDataSource));
//					System.out.println("Class; CCBSParameterPanel  Function: loadProductNames Text: commodityNames.size() "+CCBS.COMMODITY_NAMES_CBS.size());
//					
//					/** TODO: make a unique builder **/
////					if ( panel.getTitle() != null )
////						panel.getPanel().add(panel.addTitle(panel.getTitle()));
////					
////					panel.addList();
////					panel.getPanel().layout();
////					
////					if ( addSelectAll ) {
////						panel.getPanel().add(FormattingUtils.addVSpace(5));
////						panel.getPanel().add(panel.buildSelectAllPanel());
////					}
////					
////					panel.getPanel().layout();
//				}
//				
//				public void onFailure(Throwable arg0) {
//	
//				}
//			});
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	public static String[] getOpeningClosingStocksValues(String yearToCheck)
	{
		String openingClosingStocksValues[]= new String[2];
		openingClosingStocksValues[0] = "";
		openingClosingStocksValues[1] = "";
		Set<String> keySet = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.keySet();
		Iterator<String> it = keySet.iterator();
		System.out.println("Class: CCBSParametersPanel Function: preloadAction Text : on success before while");
		while(it.hasNext())
		{
//			HashMap<String,String> hashmap = (HashMap<String,String>)it.next();
			String year = (String)it.next();
			if(year.equals(yearToCheck))
			{
				HashMap<String,String> hashmap = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(year);
				if(hashmap!=null)
				{
					Set<String> keySet2 = hashmap.keySet();
					Iterator<String> it2 = keySet2.iterator();
					while(it2.hasNext())
					{
						String code = it2.next();
						if(code.equals(CCBS.OPENING_STOCKS_ELEMENT_CODE))
						{
							//First Element
							openingClosingStocksValues[0] = hashmap.get(code);
						}
						else if(code.equals(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
						{
							//Second Element
							openingClosingStocksValues[1] = hashmap.get(code);
						}
							
					//	System.out.println("Class: CCBSParametersPanel Function: preloadAction Text :CCBS.OPENING_CLOSING_STOCKS_HASHMAP year = "+ year +" code = "+code+" value = "+ hashmap.get(code));
					}
				}
			}
		}
		
		if((openingClosingStocksValues[0]==null)||(openingClosingStocksValues[0].equals("")))
		{
			//Take the closing stocks of the previous year
			int yearBefore = Integer.parseInt(yearToCheck) -1;
			HashMap<String,String> hashmap = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(""+yearBefore);
			if(hashmap!=null)
			{
				HashMap<String, String> hashMapYearToCheck = new HashMap<String, String>();
				String closingStocksBeforeYear = hashmap.get(CCBS.CLOSING_STOCKS_ELEMENT_CODE);
				if(closingStocksBeforeYear!=null)
				{
					openingClosingStocksValues[0]= closingStocksBeforeYear;
					if((CCBS.OPENING_CLOSING_STOCKS_HASHMAP!=null)&&(CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(yearToCheck)==null))
					{
						hashMapYearToCheck.put(CCBS.OPENING_STOCKS_ELEMENT_CODE, closingStocksBeforeYear);
						CCBS.OPENING_CLOSING_STOCKS_HASHMAP.put(yearToCheck, hashMapYearToCheck);
					}
					else
					{
						CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(yearToCheck).put(CCBS.OPENING_STOCKS_ELEMENT_CODE, closingStocksBeforeYear);
					}
				}
			}
		}
		return openingClosingStocksValues;
	}
	
	public static void setOpeningClosingStocksValues(String headerYear, String nextYear, String closingStocksValue)
	{
		Set<String> externalHMkeySet = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.keySet();
		Iterator it = externalHMkeySet.iterator();
		HashMap<String,String> hm;
		boolean found1 = false;
		boolean found2 = false;
		String element = "";
		while(it.hasNext())
		{
			element = (String)it.next();
			if(element.equals(headerYear))
			{
				hm = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(element);
				hm.put(CCBS.CLOSING_STOCKS_ELEMENT_CODE, closingStocksValue);
				found1 = true;
				//System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: found1 = true closingStocksValue "+ closingStocksValue);
//				Set<String> internalHMkeySet = hm.keySet();
//				Iterator it2 = internalHMkeySet.iterator();
//				//Update the closing stocks value for that year
//				while(it2.hasNext())
//				{
//					String code = (String)it2.next();
//					if(code.equals(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
//					{
//						//Second Element
//						openingClosingStocksValues[1] = hashmap.get(code);
//					}
//				}
				
			}
			if(element.equals(nextYear))
			{
				hm = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(element);
				hm.put(CCBS.OPENING_STOCKS_ELEMENT_CODE, closingStocksValue);
				found2 = true;
			//	System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: found1 = true closingStocksValue "+ closingStocksValue);
//				Set<String> internalHMkeySet = hm.keySet();
//				Iterator it2 = internalHMkeySet.iterator();
//				//Update the closing stocks value for that year
//				while(it2.hasNext())
//				{
//					String code = (String)it2.next();
//					if(code.equals(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
//					{
//						//Second Element
//						openingClosingStocksValues[1] = hashmap.get(code);
//					}
//				}
			}
			if((found1)&&(found2))
			{
			//	System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: found1 = true and found2 = true  before break");
				break;
			}
		}
		if(!found1)
		{
		//	System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: if(!found1)");
			//This is a new year... setting the closing stocks value
			HashMap<String, String> newHm = new HashMap<String, String>();
			newHm.put(CCBS.CLOSING_STOCKS_ELEMENT_CODE, closingStocksValue);
			newHm.put(CCBS.OPENING_STOCKS_ELEMENT_CODE, "");
			CCBS.OPENING_CLOSING_STOCKS_HASHMAP.put(headerYear, newHm);
		}
		if(!found2)
		{
		//	System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: if(!found2)");
			//This is a new year... setting the closing stocks value
			HashMap<String, String> newHm = new HashMap<String, String>();
			newHm.put(CCBS.OPENING_STOCKS_ELEMENT_CODE, closingStocksValue);
			newHm.put(CCBS.CLOSING_STOCKS_ELEMENT_CODE, "");
			CCBS.OPENING_CLOSING_STOCKS_HASHMAP.put(nextYear, newHm);
		}
		
//		System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: headerYear "+headerYear +" Closing Stocks "+CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(headerYear).get(CCBS.CLOSING_STOCKS_ELEMENT_CODE));
//		System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: headerYear "+headerYear +" Opening Stocks "+CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(headerYear).get(CCBS.OPENING_STOCKS_ELEMENT_CODE));
//		System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: nextYear "+nextYear +" Closing Stocks "+CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(nextYear).get(CCBS.CLOSING_STOCKS_ELEMENT_CODE));
//		System.out.println("Class: CCbsParametersPanel  Function: handleEvent Text: nextYear "+nextYear +" Opening Stocks "+CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(nextYear).get(CCBS.OPENING_STOCKS_ELEMENT_CODE));
	}
}
