package org.fao.fenix.web.modules.amis.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.TreeSet;

import org.fao.fenix.web.modules.amis.client.control.history.AMISHistoryController;
import org.fao.fenix.web.modules.amis.client.control.input.AMISInputController;
import org.fao.fenix.web.modules.amis.client.control.menu.AMISMenuController;
import org.fao.fenix.web.modules.amis.client.control.outputs.AMISChartController;
import org.fao.fenix.web.modules.amis.client.control.outputs.AMISTableController;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompare;
import org.fao.fenix.web.modules.amis.client.view.home.AMISCcbs;
import org.fao.fenix.web.modules.amis.client.view.home.AMISDatasourceSelectorPanel;
import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;
import org.fao.fenix.web.modules.amis.client.view.home.AMISPsd;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.BookPanel;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginPanel;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginRegisterPanel;
import org.fao.fenix.web.modules.amis.client.view.login.AMISRegistrationForm;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.statisticalnotes.AMISStatisticalNotes;
import org.fao.fenix.web.modules.amis.client.view.utils.AMISHomeUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.EmptyValuesPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker.AMISBoxMakerFlatTable;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.constants.AMISInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVOBuilder;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;



import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;




public class AMISController {
	
	
	// CCBSVO
	// PSDVO
	// FAOSTATVO
	
	//Databases:
	//FENIX
	//FAOSTAT
	
	//CCBS, PSD - datasetCode, 
	//tableName

	public static AMISCurrentDatasetView currentDatasetView;
	
	public static AMISCurrentView currentSelectedView;
	
	
	public final static Map<String, AMISResultVO> amisDatasetMap;
	
	static {
		amisDatasetMap = new HashMap<String, AMISResultVO>(); //store: database, dataset Code, dataset tablename
	}	

	/* not used
	public static ContentPanel addMap(AMISQueryVO qvo, String width, String height) {
		ContentPanel mapPanel = new ContentPanel();
		mapPanel.setHeaderVisible(false);
		mapPanel.setBodyBorder(false);
		AMISMapController.addMap(mapPanel, qvo, width, height);
	
		return mapPanel;
	}*/

	public static ContentPanel addTable(AMISQueryVO qvo, String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setBorders(false);
		AMISTableController.addTable(panel, qvo, width, height);
		return panel;
	}
	
	
	 public static HorizontalPanel addChart(VerticalPanel vp, ContentPanel emptyPanelHolder, AMISQueryVO qvo, String width, String height, LoadingWindow loadingWindow) {
	//public static HorizontalPanel addChart(VerticalPanel vp, ContentPanel emptyPanelHolder, AMISQueryVO qvo, String width, String height) {
			HorizontalPanel panel = new HorizontalPanel();
			ContentPanel c1 = new ContentPanel();
			c1.setHeaderVisible(false);
			c1.setBorders(false);
			c1.setBodyBorder(false);
			AMISChartController.addChart(vp, emptyPanelHolder, c1, qvo, width, height, loadingWindow);
			//AMISChartController.addChart(vp, emptyPanelHolder, c1, qvo, width, height);
			panel.add(c1);
			return panel;
		}
	 
	


     public static HorizontalPanel addChart(AMISQueryVO qvo, String width, String height) {
		
		HorizontalPanel panel = new HorizontalPanel();
		
		ContentPanel c1 = new ContentPanel();
		c1.setHeaderVisible(false);
		c1.setBodyBorder(false);
		AMISChartController.addChart(c1, qvo, width, height);
		
		panel.add(c1);
		return panel;
	}

   
     public static SelectionListener<ButtonEvent> exportChartExcel(final AMISResultVO vo) {
	 		return new SelectionListener<ButtonEvent>() {
	 			public void componentSelected(ButtonEvent ce) {
	 				exportChartExcelAgent(vo);
	 			}
	 		};
	 	}
 	
 	 
 	
 	private static void exportChartExcelAgent(final AMISResultVO rvo) {
 		List<List<String>> table = new ArrayList<List<String>>();
 		LinkedHashMap<String, Map<String, Double>> chartValues = rvo.getChartValues();
 		
 		//System.out.println("chartValues" + chartValues);
 		
 		// TODO: quick fix for the stack (DO A SERIE switcher)
 		//System.out.println("vo.getOutputType(): " + rvo.getTypeOfOutput() +" "+ );
 		
 		
 		System.out.println("COUNT: " + chartValues.size());
 		
 		//for(String serie : rvo.getSerieMeasurementUnits().keySet()) {
 			//System.out.println("serie" + serie + " | "+rvo.getSerieMeasurementUnits().get(serie)); 			
 		//}
 		
 		for(String key : chartValues.keySet()) {
 			int indexFcast=0;
 			for(String chartKey : chartValues.get(key).keySet()) {
 				List<String> row = new ArrayList<String>();
 				row.add(key);
 				if((chartKey!=null)&&(chartKey.contains("f'cast")))
 				{
 					indexFcast = chartKey.indexOf("f'cast");
 					//To eliminate the space between the year and f'cast 
 					indexFcast--;
 					row.add(chartKey.substring(0, indexFcast));
 				}
 				else
 				{
 					row.add(chartKey);
 				}
 				
 				if(chartValues.get(key).get(chartKey)!=null)
 				{
 					//System.out.println("Not Null *"+chartValues.get(key).get(chartKey).toString()+"*");
 					row.add(chartValues.get(key).get(chartKey).toString());
 					System.out.println("key "+key+" chartKey*" + chartKey + "* | chartValues.get(key).get(chartKey).toString() "+chartValues.get(key).get(chartKey).toString());
 				}
 				else{
 					//System.out.println("Null");
 					//Ignore the row
 					continue;
 					//row.add("");
 				}
 				//System.out.println("After");
 				
 				if(rvo.getSerieMeasurementUnits().containsKey(key))
 				{
 					row.add(rvo.getSerieMeasurementUnits().get(key));
 				}
 				else
 				{
 					if((key!=null)&&(key.equals("Domestic Utilization")))
 					{
 						if(rvo.getSerieMeasurementUnits().containsKey("Utilization"))
 		 				{
 		 					row.add(rvo.getSerieMeasurementUnits().get("Utilization"));
 		 				}
 					}
 				}
 				table.add(row);
 			}
 		}
 		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! ");
 		if(rvo.getTableHeaders()!= null){
 			//System.out.println("rvo.getTableHeaders()!= null ");
 			if(rvo.getTableHeaders().size() > 0) {
 				//System.out.println("if rvo.getTableHeaders().size()= "+rvo.getTableHeaders().size());
// 				for(int z= 0; z< rvo.getTableHeaders().size(); z++)
// 				{
// 					System.out.println("z = "+z+" rvo.getTableHeaders().get(z)= "+rvo.getTableHeaders().get(z));
// 				}
 			
 			rvo.setExportTableHeaders(rvo.getTableHeaders());
 			if(!rvo.getTableHeaders().contains("Unit"))
 			{
 				rvo.getTableHeaders().add("Unit");
 				rvo.setNumericColumnIndex(rvo.getTableHeaders().size()-2); //lastcolumn
 			}
 	 		//rvo.setNumericColumnIndex(rvo.getTableHeaders().size()-1); //lastcolumn
 			} else {
 				//System.out.println("else rvo.getTableHeaders().size()= "+rvo.getTableHeaders().size());
 				//rvo.setExportTableHeaders(null);
 				rvo.getTableHeaders().add("Elements");
 				rvo.getTableHeaders().add("Year");
 				rvo.getTableHeaders().add("Value");
 				rvo.getTableHeaders().add("Unit");
 				rvo.setExportTableHeaders(rvo.getTableHeaders());
 	 			rvo.setNumericColumnIndex(chartValues.keySet().size()); //lastcolumn
 			}
 		} else {
 			//System.out.println("rvo.getTableHeaders()== null ");
 			rvo.setExportTableHeaders(null);
 			rvo.setNumericColumnIndex(chartValues.keySet().size()); //lastcolumn
 		}
 		
 		if(rvo.getExportTitle()==null) {
 			rvo.setExportTitle(rvo.getTitle());
 		}
 		//System.out.println("rvo.getExportTitle== "+ rvo.getExportTitle());
 		rvo.setTableContents(table);
 	
 		//System.out.println("exportChartExcelAgent: rvo.getNumericColumnIndex " + rvo.getNumericColumnIndex());
 		
 		AMISServiceEntry.getInstance().export(rvo, new AsyncCallback<String>() {

 			public void onSuccess(String result) {
 			//	com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
 				//com.google.gwt.user.client.Window.open("../exportObject/" + result, "",  "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
 				com.google.gwt.user.client.Window.open("../exportObject/" + result, "",  "");
 				//com.google.gwt.user.client.Window.open("../cbsTool/exportObject/" + filename, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
 			}

 			public void onFailure(Throwable arg0) {

 			}
 		});
 	}
 	
 	public static SelectionListener<ButtonEvent> exportTableExcel(final AMISResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("Class: AMISController Function: exportTableExcel Text: Export ");
				firstColumnParser(vo);
				
				exportTableExcelAgent(vo);
			}

			private void firstColumnParser(AMISResultVO vo) {
				if(vo!=null)
				{
					List<List<String>> tableContents = vo.getTableContents();
					if(tableContents!=null)
					{int i=0;int j= 0;
						for(List<String> extern: tableContents)
						{
							System.out.println("Class: AMISController Function: exportTableExcel Text: extern i= "+i);
							j=0;
							for(String intern: extern)
							{
								System.out.println("Class: AMISController Function: exportTableExcel Text: Before intern "+intern);
								if((j==0)&&(intern!=null))
								{
									//If it's the first column
									int indexStart = intern.indexOf("\">");
									int indexEnd = intern.indexOf("<sup>");
									if((indexStart!=-1)&&(indexStart!=-1))
									{
										extern.set(0, intern.substring(indexStart+2, indexEnd));
									}
								}
								System.out.println("Class: AMISController Function: exportTableExcel Text: After intern "+intern + "\n");
								j++;
							}
							i++;
						}
					}
				}
				
			}
		};
	}
 	
 	public static ContentPanel createFootnoteTable(final AMISQueryVO qvo, final String tableType, final String areas)
 	{
 		System.out.println("NEW!!!!!!!!!!!!!!!!!!!!!!!!");
 		final ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
 		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {


				public void onSuccess(AMISResultVO rvo) {
					panel.removeAll();
					
					if ( rvo.getRows() == null ) {
						//tablePanel.add(new Html("<div class=''> " + rvo.getTableHTML() + "</div>"));
//						ContentPanel panel = (ContentPanel)widgetsPanel.getItemByItemId(AMISConstants.FOOTNOTE_TABLE.toString());
//						if(panel!=null)
//						{
//							System.out.println("REMOVE !!!!!!!!!!!!!!!!!!!! 1");
//							widgetsPanel.remove(panel);
//						}
//						widgetsPanel.add(AMISHomeUtils.getHtmlFootnoteTable(tableType, areas));
//						widgetsPanel.layout();
						
						panel.add(AMISHomeUtils.getHtmlFootnoteTable(tableType, areas));

						panel.layout();

					}
					else {
						if ( rvo.getRows() > 0 ) {
								//tablePanel.add(new Html("<div style='background:#FFFFFF;'> " + rvo.getTableHTML() + "</div>"));
//							ContentPanel panel = (ContentPanel)widgetsPanel.getItemByItemId(AMISConstants.FOOTNOTE_TABLE.toString());
//							if(panel!=null)
//							{
//								System.out.println("REMOVE !!!!!!!!!!!!!!!!!!!! 2");
//								widgetsPanel.remove(panel);
//							}
//							widgetsPanel.add(AMISHomeUtils.getHtmlFootnoteTable(tableType, areas));
//							widgetsPanel.layout();
							
							panel.add(AMISHomeUtils.getHtmlFootnoteTable(tableType, areas));

							panel.layout();
							}
						else {
							//tablePanel.add(new Html("<div style='background:#FFFFFF;'> No data available for the current selection. </div>"));
						}
					}
					
				}

				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		//AMISHomeUtils.getHtmlFootnoteTable("cereal_table", areas, panel);
 	return panel;
 	}

 	public static SelectionListener<ButtonEvent> openRegisterPanelForm(final AMISRegistrationForm amisRegistrationForm) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("Class: AMISController Function: openRegisterPanelForm Text: Before call build ");
				amisRegistrationForm.build();
				System.out.println("Class: AMISController Function: openRegisterPanelForm Text: After build ");
			}
			};
	}
 	
 	public static SelectionListener<ButtonEvent> cancelPanelRegistrationForm(final AMISRegistrationForm amisRegistrationForm) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				amisRegistrationForm.getWindow().hide();
			}
			};
	}
 	
 	public static SelectionListener<ButtonEvent> requestAccessPanelRegistrationForm(final AMISRegistrationForm amisRegistrationForm) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				//AMIS-Secretariat@fao.org
				String errorText = parametersCheck(amisRegistrationForm);
				if(!errorText.equals(""))
				{
					System.out.println("Error come back in if text : "+errorText);
					//Error 
					FenixAlert.error("Error", errorText);
				}
				else if(!emailParameterCheck(amisRegistrationForm))
				{
					//Error 
					FenixAlert.error("Error", "E-mail address is not valid!");
				}
				else
				{
					Map<String, String> m = new HashMap<String, String>();
					m.put("Name", amisRegistrationForm.getNameTextField().getValue());
					m.put("Surname", amisRegistrationForm.getSurnameTextField().getValue());
					m.put("Country", amisRegistrationForm.getCountryTextField().getValue());
					m.put("Institution", amisRegistrationForm.getInstitutionTextField().getValue());
					m.put("E-Mail", amisRegistrationForm.getEmailTextField().getValue());
					final LoadingWindow loading = new LoadingWindow("Loading", "The system is sending your e-mail to the AMIS Secretariat.", "Please wait...");
					try {
						AMISServiceEntry.getInstance().sendRegistrationEMail(m, new AsyncCallback<String>() {
							public void onSuccess(String arg0) {
								String text = "Thank you for requesting access AMIS Statistics. Your request will be processed as soon as possible and, if approved, you will receive an email confirming the activation of the account with username and password.<br><br>Best regards,<br>The AMIS Secretariat\n";
							//	FenixAlert.info("Success", arg0);
								loading.destroyLoadingBox();
								FenixAlert.infoAmisRegistration(arg0, text);
								amisRegistrationForm.getWindow().hide();
								loading.destroyLoadingBox();
								
							}
							public void onFailure(Throwable arg0) {
								loading.destroyLoadingBox();
								FenixAlert.error("Error", arg0.getMessage());
								loading.destroyLoadingBox();
							}
						});
					} catch (FenixGWTException e) {
						loading.destroyLoadingBox();
						FenixAlert.error("Error", e.getMessage());
						loading.destroyLoadingBox();
					}
				}			
			}
			};
	}
 	 
 	public static String parametersCheck(AMISRegistrationForm amisRegistrationForm)
 	{
 		System.out.println("Name 1 "+ amisRegistrationForm.getNameTextField());
 		System.out.println("Surname 1 "+ amisRegistrationForm.getSurnameTextField());
 		System.out.println("Country 1 "+amisRegistrationForm.getCountryTextField());
 		System.out.println("Institution 1 "+ amisRegistrationForm.getInstitutionTextField());
 		System.out.println("E-Mail 1 "+ amisRegistrationForm.getEmailTextField());
 		System.out.println("Name "+ amisRegistrationForm.getNameTextField().getValue());
 		System.out.println("Surname "+ amisRegistrationForm.getSurnameTextField().getValue());
 		System.out.println("Country "+amisRegistrationForm.getCountryTextField().getValue());
 		System.out.println("Institution "+ amisRegistrationForm.getInstitutionTextField().getValue());
 		System.out.println("E-Mail "+ amisRegistrationForm.getEmailTextField().getValue());
 		String errorText = "";
 		int count=0;
		if((amisRegistrationForm.getNameTextField()==null)||(amisRegistrationForm.getNameTextField().getValue()==null)||(amisRegistrationForm.getNameTextField().getValue().equals("")))
		{
			errorText = "Name, ";
			count++;
		}
		if((amisRegistrationForm.getSurnameTextField()==null)||(amisRegistrationForm.getSurnameTextField().getValue()==null)||(amisRegistrationForm.getSurnameTextField().getValue().equals("")))
		{
			errorText = errorText+"Surname, ";
			count++;
		}
		if((amisRegistrationForm.getCountryTextField()==null)||(amisRegistrationForm.getCountryTextField().getValue()==null)||(amisRegistrationForm.getCountryTextField().getValue().equals("")))
		{
			errorText = errorText+"Country, ";
			count++;
		}
		if((amisRegistrationForm.getInstitutionTextField()==null)||(amisRegistrationForm.getInstitutionTextField().getValue()==null)||(amisRegistrationForm.getInstitutionTextField().getValue().equals("")))
		{
			errorText = errorText+"Institution, ";
			count++;
		}
		if((amisRegistrationForm.getEmailTextField()==null)||(amisRegistrationForm.getEmailTextField().getValue()==null)||(amisRegistrationForm.getEmailTextField().getValue().equals("")))
		{
			errorText = errorText+"E-Mail, ";
			count++;
		}
		if(count!=0)
		{
			//Remove the last comma
			errorText= errorText.substring(0, errorText.length()-2);
			if(count>1)
			{
				errorText = "Please insert the fields: " + errorText+".";
			}
			else
			{
				errorText = "Please insert the field: " + errorText+".";
			}
		}		
		System.out.println("Error text : "+errorText);
 		return errorText;
 	}
 	
 	public static boolean emailParameterCheck(AMISRegistrationForm amisRegistrationForm)
 	{
 		String email = amisRegistrationForm.getEmailTextField().getValue();
 		boolean ris = false;
 		if (email.matches(".+@.+\\.[a-z]+"))
 		{
 			ris = true;
 		}
 		return ris;
 	}
 	
 	private static void exportTableExcelAgent(final AMISResultVO vo) {
 		if(vo.getExportTitle()!=null && vo.getMeasurementUnit()!=null){
 			vo.setExportTitle(vo.getExportTitle() + " " + vo.getMeasurementUnit());		
		}
 		
 		AMISServiceEntry.getInstance().export(vo, new AsyncCallback<String>() {

 			
 			public void onSuccess(String result) {
 			//	com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
 				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "");
 			}

 			public void onFailure(Throwable arg0) {

 			}
 		});
 	}
 	
 	public static LinkedHashMap<String, String> sortByValuesASC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	public static LinkedHashMap<String, String> sortByValuesDESC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = size -1; i>= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	public static SelectionListener<ButtonEvent> selectAll(final ListView<AMISCodesModelData> list) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				System.out.println("selectAll");
				selectAllAgent(list);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> deselectAll(final ListView<AMISCodesModelData> list) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("deselectAll");
				deselectAllAgent(list);
			}
		};
	}
	
	public static void selectAllAgent(ListView<AMISCodesModelData> list) {
		System.out.println("selectAll");
		list.getSelectionModel().selectAll();
	}
	
	public static void deselectAllAgent(ListView<AMISCodesModelData> list) {
		System.out.println("deselectAll");
		list.getSelectionModel().deselectAll();
	}
	
////JUST ADDED 27-07-2011
	/**
	 * 
	 * The visualization param, has to be takes for the asynccall for the aggregated countries 
	 * 
	 * TODO: they could be cached somewhere?
	 * 
	 * @param visualization
	 * @param qvo
	 * @param values
	 * @param filterType
	 */
	public static void addToFilters(AMISQueryVO qvo, Map<String, String> values, String filterType, String domain) {
	
		
		// this is a check if the filter is applicable to the QVO
		if (checkIfAddFilter(qvo, filterType)){
			
			// TODO: should be a list of domains(List<DWCOdes>
			// WORKAROUND
			System.out.println("DOMAIN: " + domain);
			if ( domain != null ) {
				Map<String, String> v = new HashMap<String, String>();
				v.put(domain, domain);
				addValuesFilter(qvo.getDomains(), v);
			}
			
			AMISConstants c = AMISConstants.valueOf(filterType);
			switch(c) {
				/** TODO: switch the different cases **/
				case FAOSTAT_GROUPS:  addValuesFilter(qvo.getDomains(), values); break;
			    case FAOSTAT_DOMAINS: addValuesFilter(qvo.getDomains(), values); break;
			    case FAOSTAT_DOMAINS_FOR_GROUP: addValuesFilter(qvo.getDomains(), values); break;
			    case FAOSTAT_COUNTRIES: addValuesFilter(qvo.getAreas(), values); break;
		 
				/** TODO: To be changed **/
				case FAOSTAT_AREAS_WORLD: addValuesFilter(qvo.getAreas(), values); break;
				case FAOSTAT_AREAS_FAO:  addValuesFilter(qvo.getAreas(), values); break;
	
				
				case FAOSTAT_ITEMS: addValuesFilter(qvo.getItems(), values); break;
				case FAOSTAT_ELEMENTS: addValuesFilter(qvo.getElements(), values); break;
				case FAOSTAT_ELEMENTS_FOR_ITEM: addValuesFilter(qvo.getElements(), values); break;
		
				case FAOSTAT_YEARS: 
							   qvo.setRunMaxDateQuery(false);  
							   addValuesFilter(qvo.getYears(), values); break;
							   
				/** TODO: Implement the between **/
				case FAOSTAT_TIMERANGE: {
							qvo.setRunMaxDateQuery(false); 
							addValuesFilter(qvo.getYears(), values); 
							break;
				}
				
				case AGGREGATION_TYPE:  
							addAggregationValue(qvo, values); break;
			}
		}
		else{
			System.out.println("FILTER: " + filterType + " not added");
		}
	}
	
	public static void addValuesFilter(Map<String, String> filter, Map<String, String> values) {	
		filter.clear();
		filter.putAll(values);
	}
	
	private static Boolean checkIfAddFilter(AMISQueryVO qvo, String filterType) {		
		Boolean check = true;
		
		AMISConstants c = AMISConstants.valueOf(filterType);

		System.out.println("qvo.getIsFixedAvoidingYears(): " + qvo.getApplyOnlyYearFilter());
		// this is for the years if the current filter is YEARS or TIMERANGE it's applicable otherwise no
		System.out.println("true AMISConstants: " + c.toString());

		// this means that any filter can be applied
		if ( qvo.getNotApplyFilters() ) {
			return false;
		}
		
		if ( qvo.getApplyOnlyYearFilter() && (c.toString().equalsIgnoreCase(AMISConstants.FAOSTAT_YEARS.toString()) || c.toString().equalsIgnoreCase(AMISConstants.FAOSTAT_TIMERANGE.toString()))) {
			System.out.println("true AMISConstants: " + c.toString());
			return true;
		}
		else if ( qvo.getApplyOnlyYearFilter() && (!c.toString().equalsIgnoreCase(AMISConstants.FAOSTAT_YEARS.toString()) || !c.toString().equalsIgnoreCase(AMISConstants.FAOSTAT_TIMERANGE.toString()))) {
			System.out.println("false AMISConstants: " + c.toString());
			return false;
		}
		
		
		switch(c) {
			/** TODO: switch the different cases **/
			case FAOSTAT_GROUPS: break;
			case FAOSTAT_DOMAINS: break;
			case FAOSTAT_DOMAINS_FOR_GROUP: break;
			case FAOSTAT_COUNTRIES:  
							System.out.println("APPLY_ALL_FILTERS_EXCEPT_AREAS: " + qvo.getApplyAllFiltersExceptAreas());
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			
			case FAOSTAT_AREAS_WORLD: 
							System.out.println("APPLY_ALL_FILTERS_EXCEPT_AREAS: " + qvo.getApplyAllFiltersExceptAreas());
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			case FAOSTAT_AREAS_FAO: 
							System.out.println("APPLY_ALL_FILTERS_EXCEPT_AREAS: " + qvo.getApplyAllFiltersExceptAreas());
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			case FAOSTAT_ITEMS: break;
			case FAOSTAT_ELEMENTS:  break;
			case FAOSTAT_ELEMENTS_FOR_ITEM: break;
			case FAOSTAT_YEARS: 
						System.out.println("APPLY_ALL_FILTERS_EXCEPT_YEARS: " + qvo.getApplyAllFiltersExceptYears());
						// this is to avoid to add the filter
						if ( !qvo.getApplyAllFiltersExceptYears() ) {
							check = true;
						}
						else if ( qvo.getApplyAllFiltersExceptYears() )  { 
							check = false;
						}
				break;
				
			/** TODO: Implement the between **/
			case FAOSTAT_TIMERANGE: {
					System.out.println("APPLY_ALL_FILTERS_EXCEPT_YEARS: " + qvo.getApplyAllFiltersExceptYears());
					// this is to avoid to add the filter
					if ( !qvo.getApplyAllFiltersExceptYears() ) {
						check = true;
					}
					else if ( qvo.getApplyAllFiltersExceptYears() ) {
						check = false;
					}
					break;
			}
			
			case AGGREGATION_TYPE:  
					System.out.println("APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE: " + qvo.getApplyAllFiltersExceptAggregrationType());
					// this is to avoid to add the filter
					if ( !qvo.getApplyAllFiltersExceptAggregrationType() ) {
						check = true;
					}
					else if ( qvo.getApplyAllFiltersExceptAggregrationType() ) {
						check = false;
					}
	
					break;
		}
		
		return check;
	}
	
	/** TODO: Change it... **/
	public static void addAggregationValue(AMISQueryVO qvo, Map<String, String> values) {	
		for( String key : values.keySet()) {
			qvo.setAggregationType(key);
		}
	}
	
	public static void cacheAMISDatasets(){
		
		List<AMISQueryVO> qvos = new ArrayList<AMISQueryVO>();
		qvos.add(AMISQueryVOBuilder.setFENIXDatasetParameters(new AMISQueryVO()));
		
		
		if(amisDatasetMap.isEmpty()) {
			System.out.println("cacheAMISDatasets START ");
			
			//amisDatasetMap.put(AMISConstants.FAOSTAT.toString(), AMISQueryVOBuilder.setFAOSTATParameters(new AMISResultVO()));
			
			for(int i=0; i < qvos.size(); i++) {
				
				final AMISQueryVO qvo = qvos.get(i);
				
				
				try{
			    	 AMISServiceEntry.getInstance().getDatasetProperties(qvo, new AsyncCallback<List<AMISResultVO>>() {
			    		 
			    		// code, tablename from customdataset WHERE code IN ('AMIS', 'AMIS_ELEMENTS', 'AMIS_COUNTRIES', 'AMIS_PRODUCTS') 
			    		 
							public void onSuccess(List<AMISResultVO> rvo) {
								for(AMISResultVO ro: rvo){
									for(String key: qvo.getDatasetCodes().keySet()) {
										String datasetCode = qvo.getDatasetCodes().get(key);
										if(datasetCode.equalsIgnoreCase(ro.getFenixDatasetCode())){
											amisDatasetMap.put(key, ro);
										}
										
									}
									
								}
								System.out.println("cacheAMISDatasets amisDatasetMap  "+amisDatasetMap);
								
								//set the default dataset
								setCurrentDatasetView(AMISCurrentDatasetView.CBS);
								
							}
							public void onFailure(Throwable arg0) {
								
							}
						});			    	 
			     	 }
			    	 catch (Exception e) {
			 			e.printStackTrace();
			 		}
			}	
			
			// checks the asyn calls
			//checkIfAllTheAsyncCallsAreReturned(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());
			
		}
			
	}

//Back up: with Combo box for Country and not label
//	public static Listener<ComponentEvent> loginListenerClick(final TextField<String> username, final TextField<String> password, final AMISInput amisInput) {
//		return new Listener<ComponentEvent>() {
//
//			public void handleEvent(ComponentEvent ce) {
//				System.out.println("Class: AMISController Function: loginListenerClick Text: username= "+username.getValue() + " password "+password.getValue());
//				
//				UserServiceEntry.getInstance().login(username.getValue(), password.getValue(), new AsyncCallback<LoginResponseVo>() {
//					public void onSuccess(LoginResponseVo vo) {
//						if (vo.isSucceeded()) {
//							String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
//							String welcomeMessage = "  " + BabelFish.print().welcome() + " <b>" + firstAndLastName + "</b>";
//							FenixUser.populateRoles(vo.getFenixUserVo());
//							amisInput.getPanel().removeAll();
//							amisInput.getPanel().add(FormattingUtils.addVSpace(20));
//							amisInput.getPanel().add(amisInput.buildLoginMessage(welcomeMessage));
//							amisInput.getPanel().add(amisInput.buildInputButtons());
//							//Create two combo in amisInput: country and data source
//							//Set liststore....							
//							
//						//	amisInput.getPanel().add(amisInput.buildCountryListBox());
//
//						//	amisInput.getPanel().getLayout().layout();
//
//							//Find Groups the user belongs to
//							//AMISServiceImpl findGroupsOfUser should be updated so that the user groups are translated to the appropriate
//							//country code and label to fill the country combo 
//							AMISServiceEntry.getInstance().findGroupsOfUser(username.getValue(), new AsyncCallback<List<AMISCodesModelData>>() {
//								public void onSuccess(List<AMISCodesModelData> userGroups) {
//
//									amisInput.getCountryListBox().getStore().removeAll();
//									amisInput.getCountryListBox().getStore().add(new AMISCodesModelData("", ""));
//									for(final AMISCodesModelData userGroupModel: userGroups)
//									{
//										if(!userGroupModel.getLabel().equalsIgnoreCase("ALL_USER_GROUP"))
//										{
//											System.out.println("Class: AMISController findGroupsOfUser : userGroup = "+userGroupModel.getLabel());
//											System.out.println("Class: AMISController findGroupsOfUser : userGroup = "+userGroupModel.getCode());
//
//											
////											amisInput.getCountryListBox().getStore().add(userGroupModel);
////											amisInput.getPanel().getLayout().layout();
//											
//											AMISQueryVO qvo = new AMISQueryVO();
//											qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
//											qvo.setCountryName(userGroupModel.getLabel());
//											qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRY_CROP_COUNT.toString());
//											
//											//1. amisInput.getCountryCombo.getStore.clear();(or removeaLL)
//											//2. amisInput.getCountryCombo.getStore.add(userGroupModel)
//											//3. amisInput.getPanel().getLayout().layout(); and remove from line 515
//
//											try {
//												AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//													
//													@SuppressWarnings("unchecked")
//													public void onSuccess(AMISResultVO vo) {
//														int iData=0;
//															CCBS.COUNTRY_NAMES.clear();
//															CCBS.COUNTRY_CODES.clear();
//														
//														int J=0;
//														for(AMISCodesModelData topcode : vo.getCodes()) {
//														//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: topcode.getLabel() "+topcode.getLabel());
//															
//															CCBS.COUNTRY_NAMES.add(topcode.getLabel());
//															CCBS.COUNTRY_CODES.add(topcode.getCode());															
//															iData++;
//															//store.add(topcode);
//															J++;
//														}
//														
//													//	amisInput.getCountryListBox().getStore().removeAll();
//													
//														for (int iCountry = 0; iCountry < CCBS.COUNTRY_NAMES.size(); iCountry++)
//														{
//															//countryListBox.addItem(CCBS.COUNTRY_NAMES.get(iCountry));
//															amisInput.getCountryListBox().getStore().add(new AMISCodesModelData(CCBS.COUNTRY_CODES.get(iCountry), CCBS.COUNTRY_NAMES.get(iCountry)));
//														}
//														for(int i=0; i< amisInput.getCountryListBox().getStore().getCount();i++)
//														{
//															System.out.println("********Country label "+amisInput.getCountryListBox().getStore().getAt(i).getLabel());
//															System.out.println("********Country code "+amisInput.getCountryListBox().getStore().getAt(i).getCode());
//														}
//														
//														for(int i=0; i< CCBS.COUNTRY_NAMES.size();i++)
//														{
//															System.out.println("********CCBS.COUNTRY_NAMES "+CCBS.COUNTRY_NAMES.get(i));
//														}
//														for(int i=0; i< CCBS.COUNTRY_CODES.size();i++)
//														{
//															System.out.println("********CCBS.COUNTRY_CODES "+CCBS.COUNTRY_CODES.get(i));
//														}
//													
//																											}
//													
//													public void onFailure(Throwable arg0) {
//										
//													}
//												});
//												
//											} catch (Exception e) {
//												e.printStackTrace();
//											}
//
//										}
//									}
//									amisInput.getPanel().getLayout().layout();
//								}
//
//								public void onFailure(Throwable e) {
//									FenixAlert.error(BabelFish.print().error(), e.getMessage());
//								}
//							});
//						} else {
//							String message = vo.getResponseMessage();
//							if (vo.getResponseMessage().equals("UsernameNotFoundException"))
//								message = BabelFish.print().UsernameNotFoundException();
//							if (vo.getResponseMessage().equals("BadCredentialsException"))
//								message = BabelFish.print().BadCredentialsException();
//							if (vo.getResponseMessage().equals("AuthenticationException"))
//								message = BabelFish.print().AuthenticationException();
//							if (vo.getResponseMessage().equals("DisabledException"))
//								message =  BabelFish.print().UserGroupDisabledException();;
//								
//								ClickHtml html = new ClickHtml();
//								html.setId("message");
//								html.setHtml("<div class='login_welcome'>"+message + " <b>[click to cancel]</b></div>");
//								html.setStyleAttribute("cursor", "pointer");
//								html.addListener(Events.OnClick, removeLogInMessage(amisInput));
//							
//								amisInput.getPanel().add(html);
//								amisInput.getPanel().getLayout().layout();
//
//						}						
//					}
//					public void onFailure(Throwable e) {
//						FenixAlert.error(BabelFish.print().error(), e.getMessage());
//					}
//				});
//
//			}
//		};
//	}

	/*public static Listener<ComponentEvent> loginListenerClick(final TextField<String> username, final TextField<String> password, final ComboBox<AMISCodesModelData> dataSourceListBox, final AMISInput amisInput) {
	return new Listener<ComponentEvent>() {

			public void handleEvent(ComponentEvent ce) {
				System.out.println("Class: AMISController Function: loginListenerClick Text: username= "+username.getValue() + " password "+password.getValue());
				final String usernameValue = username.getValue();
				AMISLoginPanel.getAmisUserParameters().setUsername(username.getValue());
				AMISLoginPanel.getAmisUserParameters().setPwd(password.getValue());
				UserServiceEntry.getInstance().login(username.getValue(), password.getValue(), new AsyncCallback<LoginResponseVo>() {
					public void onSuccess(LoginResponseVo vo) {
						if (vo.isSucceeded()) {
							String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
							String welcomeMessage = "  " + BabelFish.print().welcome() + " <b>" + firstAndLastName + "</b>";
							FenixUser.populateRoles(vo.getFenixUserVo());
							amisInput.getPanel().removeAll();
							//amisInput.getPanel().add(FormattingUtils.addVSpace(20));
							amisInput.setUsername(usernameValue);
							amisInput.getPanel().add(amisInput.buildInputButtons());
							//amisInput.getPanel().add(amisInput.buildLoginMessage(welcomeMessage));
							if (RootPanel.get("USER_LOGIN").getWidgetCount() > 0)
								RootPanel.get("USER_LOGIN").remove(RootPanel.get("USER_LOGIN").getWidget(0));
							
							RootPanel.get("USER_LOGIN").add(amisInput.buildLoginMessage(welcomeMessage));
						//	amisInput.getPanel().add(amisInput.buildInputButtons());
							//Create two combo in amisInput: country and data source
							//Set liststore....							
							
						//	amisInput.getPanel().add(amisInput.buildCountryListBox());

						//	amisInput.getPanel().getLayout().layout();

							//Find Groups the user belongs to
							//AMISServiceImpl findGroupsOfUser should be updated so that the user groups are translated to the appropriate
							//country code and label to fill the country combo 
							AMISServiceEntry.getInstance().findGroupsOfUser(username.getValue(), new AsyncCallback<List<AMISCodesModelData>>() {
								public void onSuccess(List<AMISCodesModelData> userGroups) {
									CCBS.COUNTRY_NAMES.clear();
									CCBS.COUNTRY_CODES.clear();
									if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
									{
										amisInput.getCountryListBox().getStore().removeAll();
									}
									final int iData=0;
	//								amisInput.getCountryListBox().getStore().add(new AMISCodesModelData("", ""));
									for(final AMISCodesModelData userGroupModel: userGroups)
									{
										if(!userGroupModel.getLabel().equalsIgnoreCase("ALL_USER_GROUP"))
										{
											System.out.println("Class: AMISController findGroupsOfUser : userGroup Label = "+userGroupModel.getLabel());
											System.out.println("Class: AMISController findGroupsOfUser : userGroup Code = "+userGroupModel.getCode());
											
//											amisInput.getCountryListBox().getStore().add(userGroupModel);
//											amisInput.getPanel().getLayout().layout();
											
											AMISQueryVO qvo = new AMISQueryVO();
											qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
											qvo.setCountryName(userGroupModel.getLabel());
											qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRY_CROP_COUNT.toString());
											
											//1. amisInput.getCountryCombo.getStore.clear();(or removeaLL)
											//2. amisInput.getCountryCombo.getStore.add(userGroupModel)
											//3. amisInput.getPanel().getLayout().layout(); and remove from line 515

											try {
												AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
													
													@SuppressWarnings("unchecked")
													public void onSuccess(AMISResultVO vo) {
														
//															CCBS.COUNTRY_NAMES.clear();
//															CCBS.COUNTRY_CODES.clear();
														
														int J=0;
														for(AMISCodesModelData topcode : vo.getCodes()) {
														//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: topcode.getLabel() "+topcode.getLabel());
															
															CCBS.COUNTRY_NAMES.add(topcode.getLabel());
															CCBS.COUNTRY_CODES.add(topcode.getCode());	
															if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
															{
																amisInput.getCountryListBox().getStore().add(new AMISCodesModelData(topcode.getCode(), topcode.getLabel()));
															}
															//iData++;
															//store.add(topcode);
															J++;
														}
														
														System.out.println("Class: AMISController findGroupsOfUser : username = "+username);
													//	amisInput.getCountryListBox().getStore().removeAll();
													if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
													{
														System.out.println("Class: AMISController findGroupsOfUser : if((usernameValue!= null)&&(username.equals(CCBS.AMIS_SECRETARIAT)))");
													
//														for (int iCountry = 0; iCountry < CCBS.COUNTRY_NAMES.size(); iCountry++)
//														{
//															//countryListBox.addItem(CCBS.COUNTRY_NAMES.get(iCountry));
//															amisInput.getCountryListBox().getStore().add(new AMISCodesModelData(CCBS.COUNTRY_CODES.get(iCountry), CCBS.COUNTRY_NAMES.get(iCountry)));
//														}
//														for(int i=0; i< CCBS.COUNTRY_NAMES.size();i++)
//														{
//															System.out.println("********CCBS.COUNTRY_NAMES "+CCBS.COUNTRY_NAMES.get(i));
//														}
//														for(int i=0; i< CCBS.COUNTRY_CODES.size();i++)
//														{
//															System.out.println("********CCBS.COUNTRY_CODES "+CCBS.COUNTRY_CODES.get(i));
//														}
														//iData++;
														amisInput.setSelectCountryAndDatasourceInputPage();
													}
													else
													{
														final AmisUtilsController auc = new AmisUtilsController();
														System.out.println("Class: AMISController findGroupsOfUser : else((usernameValue!= null)&&(username.equals(CCBS.AMIS_SECRETARIAT)))");
														amisInput.getCountryLabel().setHtml("<div class='input_panel_text'><b>"+CCBS.COUNTRY_NAMES.get(0) +"</b></div>");
														for(int i=0; i< CCBS.COUNTRY_NAMES.size();i++)
														{
															System.out.println("********CCBS.COUNTRY_NAMES "+CCBS.COUNTRY_NAMES.get(i));
														}
														for(int i=0; i< CCBS.COUNTRY_CODES.size();i++)
														{
															System.out.println("********CCBS.COUNTRY_CODES "+CCBS.COUNTRY_CODES.get(i));
														}
														//Variable used in the Cbs Tool to know which country is used
														final String country =  CCBS.COUNTRY_NAMES.get(0);
														//Variable used in the Cbs Tool to know which country is used
														amisInput.getCountrySelected().setLabel(country);
														int i=0;
														for(i=0; i<CCBS.COUNTRY_NAMES.size(); i++)
														{
															if(CCBS.COUNTRY_NAMES.get(i).equals(country))
															{
																break;
															}
														}
														amisInput.getCountrySelected().setCode(CCBS.COUNTRY_CODES.get(i));
														amisInput.setSelectCountryTextInputPage(country);
														//Fill the data source listbox
														
														CCBS.DATA_SOURCE_NAMES.clear();
														//CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining + " (Training Set)"));
														dataSourceListBox.getStore().removeAll();
														//dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
													//4. The following (getAllCustomDatasets and getGroupPermission4Resource) should be in a new function and referenced when a country (i.e. a user group) has been clicked on from the getCountryCombo (user group),
													//Find All Custom Datasets
													AMISServiceEntry.getInstance().getAllCustomDatasets(new AsyncCallback<Map<Long, AMISCodesModelData>>() {
														public void onSuccess(Map<Long, AMISCodesModelData> customDatasets) {
															for(final Map.Entry<Long, AMISCodesModelData> customDatasetEntry: customDatasets.entrySet())
															{
																boolean ris = auc.isDatasourceForCountry();
																if(!ris)
																{
																	final long resourceId = customDatasetEntry.getKey();
																	final AMISCodesModelData dataSourceModel = customDatasetEntry.getValue();
																	final String dataSourceCode = dataSourceModel.getCode();

																	System.out.println("Class:AmisController Function: getAllCustomDatasets********resourceId "+resourceId);
																	System.out.println("Class:AmisController Function: getAllCustomDatasets********dataSourceModel.getCode() "+dataSourceModel.getCode()+ " dataSourceModel.getLabel()"+ dataSourceModel.getLabel());
																	System.out.println("Class:AmisController Function: getAllCustomDatasets********country "+country);
																	//Check the group permissions for each custom dataset resource			
																	if((dataSourceCode!=null)&&(dataSourceCode.contains(country)))
																	{
																	//Check the group permissions for each custom dataset resource			
																	UserServiceEntry.getInstance().getGroupPermission4Resource(country, resourceId, new AsyncCallback<Map<String, Boolean>>() {
																		public void onSuccess(Map<String, Boolean> permissionMap) {

																			//Check if all the 4 permission types (DELETE, READ, WRITE, DOWNLOAD) are set to true, if so then this dataset belongs to the group
																			if(permissionMap.get("DELETE") && permissionMap.get("READ") && permissionMap.get("WRITE") && permissionMap.get("DOWNLOAD")) {
																				System.out.println("Group: "+country+" : permission values for dataset code '"+dataSourceCode+"' = [DELETE:"+ permissionMap.get("DELETE") +" | READ:" + permissionMap.get("READ") +" | WRITE:" + permissionMap.get("WRITE") +" | DOWNLOAD:" + permissionMap.get("DOWNLOAD")+ "]");
																				//The AMISCodesModelData (dataset code and dataset title) i.e. dataSourceModel would then be added to the DATA SOURCE Combo
																				//e.g. amisInput.getDataSourceCombo.getStore.add(model)
																				//amisInput.getPanel().getLayout().layout();
																				CCBS.DATA_SOURCE_NAMES.add(new String(dataSourceModel.getLabel()));
																				CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining));
																				dataSourceListBox.getStore().add(dataSourceModel);
																				auc.setDatasourceForCountry(true);
																			}
																		}

																		public void onFailure(Throwable caught) {
																			Info.display(BabelFish.print().generalServerProblem(), caught
																					.getLocalizedMessage());
																		}
																	});
																	}
																}																
															}
															dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
															amisInput.getPanel().getLayout().layout();
														}

														public void onFailure(Throwable caught) {
															Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
														}
													});
													}														
														
													//	Html selectFromComboBox = new Html("<div class='input_panel_text'><b>"+CCBS.COUNTRY_NAMES.get(0) +"</b></div>");
//														for(int i=0; i< amisInput.getCountryListBox().getStore().getCount();i++)
//														{
//															System.out.println("********Country label "+amisInput.getCountryListBox().getStore().getAt(i).getLabel());
//															System.out.println("********Country code "+amisInput.getCountryListBox().getStore().getAt(i).getCode());
//														}
														
																										}
																									
													public void onFailure(Throwable arg0) {
										
													}
												});
												
											} catch (Exception e) {
												e.printStackTrace();
											}

										}
									}
									amisInput.getPanel().getLayout().layout();
								}

								public void onFailure(Throwable e) {
									FenixAlert.error(BabelFish.print().error(), e.getMessage());
								}
							});
						} else {
							String message = vo.getResponseMessage();
							if (vo.getResponseMessage().equals("UsernameNotFoundException"))
								message = BabelFish.print().UsernameNotFoundException();
							if (vo.getResponseMessage().equals("BadCredentialsException"))
								message = BabelFish.print().BadCredentialsException();
							if (vo.getResponseMessage().equals("AuthenticationException"))
								message = BabelFish.print().AuthenticationException();
							if (vo.getResponseMessage().equals("DisabledException"))
								message =  BabelFish.print().UserGroupDisabledException();;
								
								ClickHtml html = new ClickHtml();
								html.setId("message");
								html.setHtml("<div class='login_welcome'>"+message + " <b>[click to cancel]</b></div>");
								html.setStyleAttribute("cursor", "pointer");
								html.addListener(Events.OnClick, removeLogInMessage(amisInput));
							
								
								if (RootPanel.get("USER_LOGIN").getWidgetCount() > 0)
									RootPanel.get("USER_LOGIN").remove(RootPanel.get("USER_LOGIN").getWidget(0));
								
								RootPanel.get("USER_LOGIN").add(html);
								
								//amisInput.getPanel().add(html);
								amisInput.getPanel().getLayout().layout();

						}						
					}
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().error(), e.getMessage());
					}
				});

			}
		};
	}
	
	
	public static Listener<ComponentEvent> basicLoginListenerClick(final TextField<String> username, final TextField<String> password, final AMISMainMenu mainMenu) {
		return new Listener<ComponentEvent>() {

				public void handleEvent(ComponentEvent ce) {
					System.out.println("Class: AMISController Function: loginListenerClick Text: username= "+username.getValue() + " password "+password.getValue());
					AMISLoginPanel.getAmisUserParameters().setUsername(username.getValue());
					AMISLoginPanel.getAmisUserParameters().setPwd(password.getValue());
					UserServiceEntry.getInstance().login(username.getValue(), password.getValue(), new AsyncCallback<LoginResponseVo>() {
						public void onSuccess(LoginResponseVo vo) {
							if (vo.isSucceeded()) {
								String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
								String welcomeMessage = "  " + BabelFish.print().welcome() + " <b>" + firstAndLastName + "</b>";
								FenixUser.populateRoles(vo.getFenixUserVo());
							
								//Welcome message
								if (RootPanel.get("USER_LOGIN").getWidgetCount() > 0)
									RootPanel.get("USER_LOGIN").remove(RootPanel.get("USER_LOGIN").getWidget(0));
								
								//AMISHome.buildAndReplaceElement("AMIS-LOGIN", logOut(mainMenu), "login");
								
								buildLogMessage(mainMenu, logOut(mainMenu), "Log Out");
								RootPanel.get("USER_LOGIN").add(buildWelcomeMessage(welcomeMessage));
							
							} else {
								String message = vo.getResponseMessage();
								if (vo.getResponseMessage().equals("UsernameNotFoundException"))
									message = BabelFish.print().UsernameNotFoundException();
								if (vo.getResponseMessage().equals("BadCredentialsException"))
									message = BabelFish.print().BadCredentialsException();
								if (vo.getResponseMessage().equals("AuthenticationException"))
									message = BabelFish.print().AuthenticationException();
								if (vo.getResponseMessage().equals("DisabledException"))
									message =  BabelFish.print().UserGroupDisabledException();;
									
									ClickHtml html = new ClickHtml();
									html.setId("message");
									html.setHtml("<div class='login_welcome'>"+message + " <b>[click to cancel]</b></div>");
									html.setStyleAttribute("cursor", "pointer");
									html.addListener(Events.OnClick, removeLogInMessage());
								
									
									if (RootPanel.get("USER_LOGIN").getWidgetCount() > 0)
										RootPanel.get("USER_LOGIN").remove(RootPanel.get("USER_LOGIN").getWidget(0));
									
									RootPanel.get("USER_LOGIN").add(html);
									
									//amisInput.getPanel().add(html);
									//amisInput.getPanel().getLayout().layout();

							}						
						}
						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().error(), e.getMessage());
						}
					});

				}
			};
		}
	
	public static void populateComboBox(final String usernameValue, final AMISInput amisInput)
	{
		//Find Groups the user belongs to
		//AMISServiceImpl findGroupsOfUser should be updated so that the user groups are translated to the appropriate
		//country code and label to fill the country combo 
		AMISServiceEntry.getInstance().findGroupsOfUser(usernameValue, new AsyncCallback<List<AMISCodesModelData>>() {
			public void onSuccess(List<AMISCodesModelData> userGroups) {
				CCBS.COUNTRY_NAMES.clear();
				CCBS.COUNTRY_CODES.clear();
				if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
				{
					amisInput.getCountryListBox().getStore().removeAll();
					//amisInput.getDataSourceListBox().getStore().removeAll();
				}
				final int iData=0;
//								amisInput.getCountryListBox().getStore().add(new AMISCodesModelData("", ""));
				for(final AMISCodesModelData userGroupModel: userGroups)
				{
					if(!userGroupModel.getLabel().equalsIgnoreCase("ALL_USER_GROUP"))
					{
						System.out.println("Class: AMISController findGroupsOfUser : userGroup Label = "+userGroupModel.getLabel());
						System.out.println("Class: AMISController findGroupsOfUser : userGroup Code = "+userGroupModel.getCode());
						
//						amisInput.getCountryListBox().getStore().add(userGroupModel);
//						amisInput.getPanel().getLayout().layout();
						
						AMISQueryVO qvo = new AMISQueryVO();
						qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
						qvo.setCountryName(userGroupModel.getLabel());
						qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRY_CROP_COUNT.toString());
						
						//1. amisInput.getCountryCombo.getStore.clear();(or removeaLL)
						//2. amisInput.getCountryCombo.getStore.add(userGroupModel)
						//3. amisInput.getPanel().getLayout().layout(); and remove from line 515

						try {
							AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
								
								@SuppressWarnings("unchecked")
								public void onSuccess(AMISResultVO vo) {
									
//										CCBS.COUNTRY_NAMES.clear();
//										CCBS.COUNTRY_CODES.clear();
									
									int J=0;
									for(AMISCodesModelData topcode : vo.getCodes()) {
									//	System.out.println("Class; CCBSParameterPanel  Function: loadGaulCountries Text: topcode.getLabel() "+topcode.getLabel());
										
										CCBS.COUNTRY_NAMES.add(topcode.getLabel());
										CCBS.COUNTRY_CODES.add(topcode.getCode());	
										if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
										{
											amisInput.getCountryListBox().getStore().add(new AMISCodesModelData(topcode.getCode(), topcode.getLabel()));
										}
										//iData++;
										//store.add(topcode);
										J++;
									}
									
									System.out.println("Class: AMISController findGroupsOfUser : username = "+usernameValue);
								//	amisInput.getCountryListBox().getStore().removeAll();
								if((usernameValue!= null)&&(usernameValue.equals(CCBS.AMIS_SECRETARIAT)))
								{
									System.out.println("Class: AMISController findGroupsOfUser : if((usernameValue!= null)&&(username.equals(CCBS.AMIS_SECRETARIAT)))");
								
//									for (int iCountry = 0; iCountry < CCBS.COUNTRY_NAMES.size(); iCountry++)
//									{
//										//countryListBox.addItem(CCBS.COUNTRY_NAMES.get(iCountry));
//										amisInput.getCountryListBox().getStore().add(new AMISCodesModelData(CCBS.COUNTRY_CODES.get(iCountry), CCBS.COUNTRY_NAMES.get(iCountry)));
//									}
//									for(int i=0; i< CCBS.COUNTRY_NAMES.size();i++)
//									{
//										System.out.println("********CCBS.COUNTRY_NAMES "+CCBS.COUNTRY_NAMES.get(i));
//									}
//									for(int i=0; i< CCBS.COUNTRY_CODES.size();i++)
//									{
//										System.out.println("********CCBS.COUNTRY_CODES "+CCBS.COUNTRY_CODES.get(i));
//									}
									//iData++;
									amisInput.setSelectCountryAndDatasourceInputPage();
								}
								else
								{
									final AmisUtilsController auc = new AmisUtilsController();
									System.out.println("Class: AMISController findGroupsOfUser : else((usernameValue!= null)&&(username.equals(CCBS.AMIS_SECRETARIAT)))");
									amisInput.getCountryLabel().setHtml("<div class='input_panel_text'><b>"+CCBS.COUNTRY_NAMES.get(0) +"</b></div>");
									for(int i=0; i< CCBS.COUNTRY_NAMES.size();i++)
									{
										System.out.println("********CCBS.COUNTRY_NAMES "+CCBS.COUNTRY_NAMES.get(i));
									}
									for(int i=0; i< CCBS.COUNTRY_CODES.size();i++)
									{
										System.out.println("********CCBS.COUNTRY_CODES "+CCBS.COUNTRY_CODES.get(i));
									}
									//Variable used in the Cbs Tool to know which country is used
									final String country =  CCBS.COUNTRY_NAMES.get(0);
									//Variable used in the Cbs Tool to know which country is used
									amisInput.getCountrySelected().setLabel(country);
									int i=0;
									for(i=0; i<CCBS.COUNTRY_NAMES.size(); i++)
									{
										if(CCBS.COUNTRY_NAMES.get(i).equals(country))
										{
											break;
										}
									}
									amisInput.getCountrySelected().setCode(CCBS.COUNTRY_CODES.get(i));
									amisInput.setSelectCountryTextInputPage(country);
									//Fill the data source listbox
									
									CCBS.DATA_SOURCE_NAMES.clear();
									//CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining + " (Training Set)"));
									amisInput.getDataSourceListBox().getStore().removeAll();
									//dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
								//4. The following (getAllCustomDatasets and getGroupPermission4Resource) should be in a new function and referenced when a country (i.e. a user group) has been clicked on from the getCountryCombo (user group),
								//Find All Custom Datasets
								AMISServiceEntry.getInstance().getAllCustomDatasets(new AsyncCallback<Map<Long, AMISCodesModelData>>() {
									public void onSuccess(Map<Long, AMISCodesModelData> customDatasets) {
										for(final Map.Entry<Long, AMISCodesModelData> customDatasetEntry: customDatasets.entrySet())
										{
											boolean ris = auc.isDatasourceForCountry();
											if(!ris)
											{
												final long resourceId = customDatasetEntry.getKey();
												final AMISCodesModelData dataSourceModel = customDatasetEntry.getValue();
												final String dataSourceCode = dataSourceModel.getCode();

												System.out.println("Class:AmisController Function: getAllCustomDatasets********resourceId "+resourceId);
												System.out.println("Class:AmisController Function: getAllCustomDatasets********dataSourceModel.getCode() "+dataSourceModel.getCode()+ " dataSourceModel.getLabel()"+ dataSourceModel.getLabel());
												System.out.println("Class:AmisController Function: getAllCustomDatasets********country "+country);
												//Check the group permissions for each custom dataset resource			
												if((dataSourceCode!=null)&&(dataSourceCode.contains(country)))
												{
												//Check the group permissions for each custom dataset resource			
												UserServiceEntry.getInstance().getGroupPermission4Resource(country, resourceId, new AsyncCallback<Map<String, Boolean>>() {
													public void onSuccess(Map<String, Boolean> permissionMap) {

														//Check if all the 4 permission types (DELETE, READ, WRITE, DOWNLOAD) are set to true, if so then this dataset belongs to the group
														if(permissionMap.get("DELETE") && permissionMap.get("READ") && permissionMap.get("WRITE") && permissionMap.get("DOWNLOAD")) {
															System.out.println("Group: "+country+" : permission values for dataset code '"+dataSourceCode+"' = [DELETE:"+ permissionMap.get("DELETE") +" | READ:" + permissionMap.get("READ") +" | WRITE:" + permissionMap.get("WRITE") +" | DOWNLOAD:" + permissionMap.get("DOWNLOAD")+ "]");
															//The AMISCodesModelData (dataset code and dataset title) i.e. dataSourceModel would then be added to the DATA SOURCE Combo
															//e.g. amisInput.getDataSourceCombo.getStore.add(model)
															//amisInput.getPanel().getLayout().layout();
															CCBS.DATA_SOURCE_NAMES.add(new String(dataSourceModel.getLabel()));
															CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining));
															amisInput.getDataSourceListBox().getStore().add(dataSourceModel);
															auc.setDatasourceForCountry(true);
														}
													}

													public void onFailure(Throwable caught) {
														Info.display(BabelFish.print().generalServerProblem(), caught
																.getLocalizedMessage());
													}
												});
												}
											}											
										}
										amisInput.getDataSourceListBox().getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
										amisInput.getPanel().getLayout().layout();
									}

									public void onFailure(Throwable caught) {
										Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
									}
								});
								}														
									
								//	Html selectFromComboBox = new Html("<div class='input_panel_text'><b>"+CCBS.COUNTRY_NAMES.get(0) +"</b></div>");
//									for(int i=0; i< amisInput.getCountryListBox().getStore().getCount();i++)
//									{
//										System.out.println("********Country label "+amisInput.getCountryListBox().getStore().getAt(i).getLabel());
//										System.out.println("********Country code "+amisInput.getCountryListBox().getStore().getAt(i).getCode());
//									}
									
																					}
																				
								public void onFailure(Throwable arg0) {
					
								}
							});
							
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
				amisInput.getPanel().getLayout().layout();
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}*/
	
	/*
	 * 		final String country =  se.getSelectedItem().getLabel();
					//Variable used in the Cbs Tool to know which country is used
					amisInput.getCountrySelected().setLabel(country);
					int i=0;
					for(i=0; i<CCBS.COUNTRY_NAMES.size(); i++)
					{
						if(CCBS.COUNTRY_NAMES.get(i).equals(country))
						{
							break;
						}
					}
					amisInput.getCountrySelected().setCode(CCBS.COUNTRY_CODES.get(i));
					
					CCBS.DATA_SOURCE_NAMES.clear();
					CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining));
					dataSourceListBox.getStore().removeAll();
					dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
				//4. The following (getAllCustomDatasets and getGroupPermission4Resource) should be in a new function and referenced when a country (i.e. a user group) has been clicked on from the getCountryCombo (user group),
				//Find All Custom Datasets
				AMISServiceEntry.getInstance().getAllCustomDatasets(new AsyncCallback<Map<Long, AMISCodesModelData>>() {
					public void onSuccess(Map<Long, AMISCodesModelData> customDatasets) {
						for(final Map.Entry<Long, AMISCodesModelData> customDatasetEntry: customDatasets.entrySet())
						{
							final long resourceId = customDatasetEntry.getKey();
							final AMISCodesModelData dataSourceModel = customDatasetEntry.getValue();
							final String dataSourceCode = dataSourceModel.getCode();

							//Check the group permissions for each custom dataset resource			
							UserServiceEntry.getInstance().getGroupPermission4Resource(country, resourceId, new AsyncCallback<Map<String, Boolean>>() {
								public void onSuccess(Map<String, Boolean> permissionMap) {

									//Check if all the 4 permission types (DELETE, READ, WRITE, DOWNLOAD) are set to true, if so then this dataset belongs to the group
									if(permissionMap.get("DELETE") && permissionMap.get("READ") && permissionMap.get("WRITE") && permissionMap.get("DOWNLOAD")) {
										System.out.println("Group: "+country+" : permission values for dataset code '"+dataSourceCode+"' = [DELETE:"+ permissionMap.get("DELETE") +" | READ:" + permissionMap.get("READ") +" | WRITE:" + permissionMap.get("WRITE") +" | DOWNLOAD:" + permissionMap.get("DOWNLOAD")+ "]");
										//The AMISCodesModelData (dataset code and dataset title) i.e. dataSourceModel would then be added to the DATA SOURCE Combo
										//e.g. amisInput.getDataSourceCombo.getStore.add(model)
										//amisInput.getPanel().getLayout().layout();
										CCBS.DATA_SOURCE_NAMES.add(new String(dataSourceModel.getLabel()));
										dataSourceListBox.getStore().add(dataSourceModel);
									}
								}

								public void onFailure(Throwable caught) {
									Info.display(BabelFish.print().generalServerProblem(), caught
											.getLocalizedMessage());
								}
							});
							
						}
						amisInput.getPanel().getLayout().layout();
					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
					}
				});
	 */
	
	/*
	public static SelectionChangedListener<AMISCodesModelData> countryComboListener(final ComboBox<AMISCodesModelData> countryListBox, final ComboBox<AMISCodesModelData> dataSourceListBox, final AMISInput amisInput) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				if((se != null)&&(se.getSelectedItem()!=null))
				{
					final AmisUtilsController auc = new AmisUtilsController();
					dataSourceListBox.setValue(null);
					final String country =  se.getSelectedItem().getLabel();
					//Variable used in the Cbs Tool to know which country is used
					amisInput.getCountrySelected().setLabel(country);
					int i=0;
					for(i=0; i<CCBS.COUNTRY_NAMES.size(); i++)
					{
						if(CCBS.COUNTRY_NAMES.get(i).equals(country))
						{
							break;
						}
					}
					System.out.println("Class:AmisController Function: countryComboListener********CCBS.COUNTRY_NAMES.size()"+CCBS.COUNTRY_NAMES.size());
					System.out.println("Class:AmisController Function: countryComboListener********CCBS.COUNTRY_CODES.size()"+CCBS.COUNTRY_CODES.size());
					for(int y=0; y< CCBS.COUNTRY_CODES.size();y++)
					{
						System.out.println("******** y= "+y+" CCBS.COUNTRY_CODES "+CCBS.COUNTRY_CODES.get(y) + " CCBS.COUNTRY_NAMES "+ CCBS.COUNTRY_NAMES.get(y));
					}
					System.out.println("Class:AmisController Function: countryComboListener********i "+i+" CCBS.COUNTRY_CODES.get(i) "+CCBS.COUNTRY_CODES.get(i));
					amisInput.getCountrySelected().setCode(CCBS.COUNTRY_CODES.get(i));
					
					CCBS.DATA_SOURCE_NAMES.clear();
					CCBS.DATA_SOURCE_NAMES.add(new String(CCBS.elementForTraining));
					dataSourceListBox.getStore().removeAll();
					dataSourceListBox.getStore().add(new AMISCodesModelData(CCBS.elementForTraining, CCBS.elementForTraining));
				//4. The following (getAllCustomDatasets and getGroupPermission4Resource) should be in a new function and referenced when a country (i.e. a user group) has been clicked on from the getCountryCombo (user group),
				//Find All Custom Datasets
				AMISServiceEntry.getInstance().getAllCustomDatasets(new AsyncCallback<Map<Long, AMISCodesModelData>>() {
					public void onSuccess(Map<Long, AMISCodesModelData> customDatasets) {
						for(final Map.Entry<Long, AMISCodesModelData> customDatasetEntry: customDatasets.entrySet())
					//	for(final Map.Entry<Long, AMISCodesModelData> customDatasetEntry: customDatasets.entrySet())
						{							
							boolean ris = auc.isDatasourceForCountry();
							if(!ris)
							{
								final long resourceId = customDatasetEntry.getKey();
								final AMISCodesModelData dataSourceModel = customDatasetEntry.getValue();
								final String dataSourceCode = dataSourceModel.getCode();
								System.out.println("Class:AmisController Function: getAllCustomDatasets********resourceId "+resourceId);
								System.out.println("Class:AmisController Function: getAllCustomDatasets********dataSourceModel.getCode() "+dataSourceModel.getCode()+ " dataSourceModel.getLabel()"+ dataSourceModel.getLabel());
								System.out.println("Class:AmisController Function: getAllCustomDatasets********country "+country);
								//Check the group permissions for each custom dataset resource			
								if((dataSourceCode!=null)&&(dataSourceCode.contains(country)))
								{
									UserServiceEntry.getInstance().getGroupPermission4Resource(country, resourceId, new AsyncCallback<Map<String, Boolean>>() {
										public void onSuccess(Map<String, Boolean> permissionMap) {

											//Check if all the 4 permission types (DELETE, READ, WRITE, DOWNLOAD) are set to true, if so then this dataset belongs to the group
											if(permissionMap.get("DELETE") && permissionMap.get("READ") && permissionMap.get("WRITE") && permissionMap.get("DOWNLOAD")) {
												System.out.println("Group: "+country+" : permission values for dataset code '"+dataSourceCode+"' = [DELETE:"+ permissionMap.get("DELETE") +" | READ:" + permissionMap.get("READ") +" | WRITE:" + permissionMap.get("WRITE") +" | DOWNLOAD:" + permissionMap.get("DOWNLOAD")+ "]");
												//The AMISCodesModelData (dataset code and dataset title) i.e. dataSourceModel would then be added to the DATA SOURCE Combo
												//e.g. amisInput.getDataSourceCombo.getStore.add(model)
												//amisInput.getPanel().getLayout().layout();
												CCBS.DATA_SOURCE_NAMES.add(new String(dataSourceModel.getLabel()));
												dataSourceListBox.getStore().add(dataSourceModel);
												auc.setDatasourceForCountry(true);
											}
										}

										public void onFailure(Throwable caught) {
											Info.display(BabelFish.print().generalServerProblem(), caught
													.getLocalizedMessage());
										}
									});
								}	
							}																		
						}
						amisInput.getPanel().getLayout().layout();
					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
					}
				});
				}

				
				
				
				
				
				
				
				
				
//				if((se != null)&&(se.getSelectedItem()!=null))
//				{
//					formData[1] = se.getSelectedItem().getLabel();
//					//To calculate the index
//					commodityListBox.setValue(null);
//					int indexCountryName=0;
//					String countryCode= "";
//					for(String countryName:CCBS.COUNTRY_NAMES)
//					{
//						if(countryName.equalsIgnoreCase(formData[1]))
//						{
//							break;
//						}
//						indexCountryName++;
//					}
//					if(indexCountryName<CCBS.COUNTRY_CODES.size())
//					{
//						countryCode = CCBS.COUNTRY_CODES.get(indexCountryName);
//					}
//					String selectionType = "AMIS_PRODUCTS";
//					loadDataForListBox(formData[1], selectionType, countryCode, formData[0]);
//					
////					String gaulName = gaulCode;
////					System.out.println("countryListBoxListener gaulCode= "+gaulCode);
////					System.out.println("countryListBoxListener gaulName= "+gaulName);
////					load(gaulCode+"", gaulName);
//				}
			//	else unload();
			}
		};
	}

	public static SelectionChangedListener<AMISCodesModelData> dataSourceComboListener(final ComboBox<AMISCodesModelData> dataSourceListBox, final HorizontalPanel hpGo, final AMISInput amisInputfinal Html to, final Html start) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				if((se != null)&&(se.getSelectedItem()!=null))
					{
					//This variable is used by the Cbs Tool to know which datasource is used.
						amisInput.getDataSourceSelected().setLabel(se.getSelectedItem().getLabel());
						amisInput.getDataSourceSelected().setCode(se.getSelectedItem().getLabel());
						Component component = hpGo.getItem(0);
						if(component != null)
						{
							ClickHtml go = (ClickHtml)component;
							if(go!= null)
							{
								go.enable();
							}
						}
						
//						List<Component> componentList =hpGo.getItems();
//						for(Component component:componentList)
//						{
//							if(component != null)
//							{
//								ClickHtml go = (ClickHtml)component;
//								if(go!= null)
//								{
//									go.enable();
//								}
//							}
//						}
					}
				
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
//				//	lc.remove(lc.getWidget(11));
//				//	lc.remove(lc.getWidget(12));
//					//start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
//					start.setHtml("<div class='ccbs-MYLabel'></div>");
//				//	to.setHtml("<div class='ccbs-MYLabel'></div>");
//					if(formData[0].equals(elementForTraining))
//					{
//						PagePanelController.showInfoPopup();
////						lc.remove(lc.getWidget(11));
////						start.setHtml("<div class='ccbs-StartMonthLabel'></div>");
////						to.setHtml("<div class='ccbs-MYLabel'></div>");
//					//	lc.insert(start, 11);
//						String selectionType = "AMIS_COUNTRIES";
//						loadDataForListBox(dataSource, selectionType, "", formData[0]);
//						FieldSet fieldset = (FieldSet)lc.getWidget(11);
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
//						ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)lc.getWidget(12);
//						combo.disable();
//					}
//					else
//					{
//						//lc.remove(lc.getWidget(11));
//						//lc.insert(monthListBox,11);
//						String selectionType = "AMIS_COUNTRIES";
//						loadDataForListBox(dataSource, selectionType, "", formData[0]);
//						FieldSet fieldset = (FieldSet)lc.getWidget(11);
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
			}
		};
	}

	
	
	private static Listener<BaseEvent> removeLogInMessage(final AMISInput amisInput) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
//				Component component = amisInput.getPanel().getItemByItemId("message");
//				if(component!=null) {
//					ClickHtml message = (ClickHtml)component;
//					amisInput.getPanel().remove(message);
//					amisInput.getPanel().getLayout().layout();
//				}	
				
				int i = 0;
				while(i<RootPanel.get("USER_LOGIN").getWidgetCount())		
				{
					Widget w = RootPanel.get("USER_LOGIN").getWidget(i);
					ClickHtml html = (ClickHtml)w;
					if((html != null)&&(html.getId()!=null)&&(html.getId().equals("message")))
					{
						RootPanel.get("USER_LOGIN").getWidget(i).removeFromParent();
						break;
					}
					i++;
				}				
			}
		};
	}
*/

	@SuppressWarnings("unchecked")
/**	public static SelectionListener<IconButtonEvent> loginListener(final TextField<String> username, final TextField<String> password) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				//<String> username = (TextField<String>)secondaryMenuBar.getData("USERNAME");
				//TextField<String> password = (TextField<String>)secondaryMenuBar.getData("PASSWORD");
				System.out.println("Class: AMISController Function: loginListener Text: username= "+username.getValue());
				System.out.println("Class: AMISController Function: loginListener Text: password= "+password.getValue());
				UserServiceEntry.getInstance().login(username.getValue(), password.getValue(), new AsyncCallback<LoginResponseVo>() {
					public void onSuccess(LoginResponseVo vo) {
						//removeSecondaryMenu();
						if (vo.isSucceeded()) {
							System.out.println("Class: AMISController Function: loginListener Text: Success!!!!");
							String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
							String welcomeMessage = "  " + BabelFish.print().welcome() + " <b>" + firstAndLastName + "</b>";
							FenixUser.populateRoles(vo.getFenixUserVo());
							RootPanel.get("MAIN_CONTENT").add(new HTML(welcomeMessage));
							System.out.println("Class: AMISController Function: loginListener Text: welcomeMessage= "+welcomeMessage);
							
							//t.addLoginItems();
							//t.getMainMenuBar().getLayout().layout();

						//	RootPanel.get("loginMessage").setStyleName("link");
						} else {
							String message = vo.getResponseMessage();
							if (vo.getResponseMessage().equals("UsernameNotFoundException"))
								message = BabelFish.print().UsernameNotFoundException();
							if (vo.getResponseMessage().equals("BadCredentialsException"))
								message = BabelFish.print().BadCredentialsException();
							if (vo.getResponseMessage().equals("AuthenticationException"))
								message = BabelFish.print().AuthenticationException();
							if (vo.getResponseMessage().equals("DisabledException"))
								message =  BabelFish.print().UserGroupDisabledException();;
							//RootPanel.get("loginMessage").add(new HTML(message));
							//RootPanel.get("loginMessage").setStyleName("link");
								
							ClickHtml html = new ClickHtml();
							html.setHtml(message + " <b>[click to cancel]</b>");
							html.setStyleAttribute("cursor", "pointer");
							html.addListener(Events.OnClick, removeLogInMessage());
							
							//RootPanel.get("SECONDARY_MENU").add(html);
						}
						

						
					}
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().error(), e.getMessage());
					}
				});
			}
		};
	}***/

	public static AMISCurrentDatasetView getCurrentDatasetView() {
		return currentDatasetView;
	}

	public static String getTableNameForDataset(AMISCurrentDatasetView currentDatasetView) {
		AMISResultVO rvo = amisDatasetMap.get(currentDatasetView.toString());
		
		System.out.println("getTableNameForDataset: amisDatasetMap"+amisDatasetMap);
		
		if(rvo==null){
			System.out.println("getTableNameForDataset: amisDatasetMap RVO for "+currentDatasetView.toString() + " IS NULL" );
		} else {
			System.out.println("getTableNameForDataset: amisDatasetMap RVO for "+currentDatasetView.toString() + "  not  NULL" );
		}
		
		return rvo.getFenixDatasetTableName();
	}
	
	public static String getTableNameForCodingSystem(String codingSystemCode) {
		AMISResultVO rvo = amisDatasetMap.get(codingSystemCode);
		
		return rvo.getFenixDatasetTableName();
	}
	
	
	public static void setCurrentDatasetView(AMISCurrentDatasetView currentDatasetView) {
		AMISController.currentDatasetView = currentDatasetView;
	}

	public static void addChart(ContentPanel panel, AMISQueryVO params,
			String string, String string2) {
		// TODO Auto-generated method stub
		
	}
	
	public static void openDatasetView(AMISCurrentDatasetView dataset, ContentPanel panel, final AMISDatasourceSelectorPanel dsp) {		
		System.out.println("Current View  = "+AMISController.currentDatasetView);
	
		AMISCurrentDatasetView currentView  = AMISController.currentDatasetView;
		
		AMISCodesModelData selectedProduct = null;
		//Get currently selected product
		switch(currentView){
		   case CBS:
			   List<AMISFilter> cbs = dsp.getAmisCcbs().getFiltersPanel().getFilters();
			   for(AMISFilter filt: cbs){
				   if(filt.getCombo().getValue() != null) {
					   selectedProduct =  filt.getCombo().getValue();
					   System.out.println("CCBS Product  = "+selectedProduct.getLabel() + "|" + selectedProduct.getCode());
				   }
			   }
			   break;
		   case FAOSTAT: break;
		   case PSD: 
			   List<AMISFilter> psd = dsp.getAmisPsd().getFiltersPanel().getFilters();			   
			   if(psd!=null){
				   for(AMISFilter filt: psd){
					   if(filt.getCombo().getValue() != null){
						   selectedProduct =  filt.getCombo().getValue();
						   System.out.println("PSD Product  = "+selectedProduct.getLabel() + "|" + selectedProduct.getCode());
					   }
				   }
			   }
	       break;
		}
		
		setCurrentDatasetView(dataset);
		
		panel.removeAll();
		
		switch(dataset){
		   case CBS:
			   AMISCcbs amisCcbs = new AMISCcbs();
			   panel.add(amisCcbs.build(dsp,selectedProduct));	
			   dsp.setAmisCcbs(amisCcbs);
			   break;
		   case FAOSTAT: break;
		   case PSD: 
			   AMISPsd amisPsd = new AMISPsd();
			   panel.add(amisPsd.build(dsp,selectedProduct));
			   dsp.setAmisPsd(amisPsd);
	       break;
		}
		
		 panel.getLayout().layout();		   
	}
	
	 public static void buildViews(AMISCompare compare, ContentPanel panel, List<AMISQueryVO> qvos, String title, LoadingWindow loadingWindow) {
//	public static void buildViews(AMISCompare compare, ContentPanel panel, List<AMISQueryVO> qvos, String title) {
		//panel.removeAll();
		FormPanel formPanel = compare.getForm();
		System.out.println("Class: AMISController Function: buildViews Text: 1 formPanel.getItemCount()= "+formPanel.getItemCount());

			//panel.getLayout().layout();
			ContentPanel emptyPanels = new ContentPanel();
			emptyPanels.setHeaderVisible(false);
			emptyPanels.setBodyBorder(false);
			//emptyPanels.setBorders(true);
			emptyPanels.setBorders(false);
			
//			ContentPanel cp = new EmptyValuesPanel().build(AMISInterfaceConstants.CHART_WIDTH, AMISInterfaceConstants.CHART_HEIGHT ,null);
//			cp.setBorders(true);
//			cp.setWidth("1030px");
//			cp.setStyleAttribute("padding", "10px");			
		
			VerticalPanel v = new VerticalPanel();			
			v.setHorizontalAlign(HorizontalAlignment.LEFT);		
			v.setScrollMode(Scroll.AUTOX);
			
			System.out.println("qvos.size(): " + qvos.size());
		/**	
			Integer currentSize = 0;
			
			HorizontalPanel widgetsPanel = new HorizontalPanel();
			widgetsPanel.setBorders(true);
			
			Boolean addHSpacing = false;
			
			List<Widget> charts = new ArrayList<Widget>();**/
			for(int i=0; i < qvos.size(); i++) {
				AMISQueryVO qvo = qvos.get(i);
				AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
				String width = getWidth(content, qvo);
				String height = getHeight(content, qvo);
				qvo.setChartForAmisHomePage(false);
				
			/**	AMISQueryVO qvo = qvos.get(i);
				
				//v.add(FormattingUtils.addVSpace(10));
				
				AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
				
				// Object Size (Width and Height)
				String width = getWidth(content, qvo);
				String height = getHeight(content, qvo);
				
				
				
				// Calculate width
				currentSize = currentSize + Integer.valueOf(width);
				
				System.out.println(i + ") content: " + content.name() + " | w:" + width + " | h:" + height + " (currentsize: " + currentSize + ")" + "  addHSpacing: " + addHSpacing);

				if ( currentSize < ((AMISInterfaceConstants.CENTER_PANEL_WIDTH) - AMISInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING) ) {
					if ( addHSpacing )
						widgetsPanel.add(FormattingUtils.addHSpace(AMISInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING));
					addHSpacing = true;
				}
				else {
					v.add(widgetsPanel);
					v.add(FormattingUtils.addVSpace(AMISInterfaceConstants.CENTER_PANEL_VERTICAL_SPACING));
					//widgetsPanel = new HorizontalPanel();
					//widgetsPanel.setBorders(true);
					currentSize = Integer.valueOf(width);
					addHSpacing = true;
				}

				
**/
		
				switch (content) {
				case TABLE:
					//charts.add(addTable(qvo, width, height));
					//widgetsPanel.add(addTable(qvo, width, height));
					addTable(qvo, width, height);
					break;
					
				case CHART:
					
					//charts.add(addChart(emptyPanels, qvo, width, height, qvos.size()));
				  addChart(v, emptyPanels, qvo, width, height, loadingWindow);
				//  addChart(v, emptyPanels, qvo, width, height);
					break;

				}
			}
			
			 System.out.println("Class: AMISController Function: buildViews Text: 2 panel.getItemCount() = "+panel.getItemCount());

			 	for(int i=panel.getItemCount()-1; i>=0; i--)
				{
//					 System.out.println("Class: AMISController Function: buildViews Text: id= "+panel.getWidget(i).getElement().getId());
//					 System.out.println("Class: AMISController Function: buildViews Text: title = "+panel.getWidget(i).getElement().getTitle());
//					if((panel.getWidget(i).getElement().getId()!=null)&&(panel.getWidget(i).getElement().getId().equals("amisOLAP")))
//					{
//						//Olap Iframe.... do not remove
//						 System.out.println("Class: AMISController Function: buildViews Text: if  title = "+panel.getWidget(i).getElement().getTitle());
//					}
//					else
//					{
//						System.out.println("Class: AMISController Function: buildViews Text: else title = "+panel.getWidget(i).getElement().getTitle()+" ... Removing");
//						panel.getWidget(i).removeFromParent();
//					}
					panel.getWidget(i).removeFromParent();
				}
			 
//				for(int i=formPanel.getItemCount()-1; i>=0; i--)
//				{
//					System.out.println("Class: AMISController Function: buildViews Text: 3 formPanel.getWidget(i).getElement().getTitle= "+formPanel.getWidget(i).getElement().getTitle());
//					if((formPanel.getWidget(i).getElement().getTitle()!=null)&&(formPanel.getWidget(i).getElement().getTitle().equals("Notes")))
//					{
//						System.out.println("Class: AMISController Function: buildViews Text: i= "+i + " Removed");
//						//NOTES_PANEL.... remove
//						formPanel.getWidget(i).removeFromParent();
//					}
//				}
		    //v.add(widgetsPanel);
				
				for(int i=0; i< panel.getItemCount(); i++)
				{
					 System.out.println("Class: AMISController Function: buildViews Text: Before build notes panel.getWidget(i).getElement().getTitle()= "+panel.getWidget(i).getElement().getTitle());
				}
				for(int i=0; i< formPanel.getItemCount(); i++)
				{
					System.out.println("Class: AMISController Function: buildViews Text: Before build notes formPanel.getWidget(i).getElement().getTitle= "+formPanel.getWidget(i).getElement().getTitle());
				}
				
			panel.add(v);
			panel.add(emptyPanels);
//			for(int i=0; i< emptyPanels.getItemCount(); i++)
//			{
//				System.out.println("Class: AMISController Function: buildViews Text: 2 formPanel.getWidget(i).getElement().getId()= "+formPanel.getWidget(i).getElement().getId());
//				if((emptyPanels.getWidget(i).getElement().getId()!=null)&&(emptyPanels.getWidget(i).getElement().getId().equals("NOTES_PANEL")))
//				{
//					System.out.println("Class: AMISController Function: buildViews Text: emptyPanels i= "+i + " Removed");
//					//NOTES_PANEL.... remove
//					emptyPanels.getWidget(i).getElement().removeFromParent();
//				}
//			}
			//panel.add(FormattingUtils.addVSpace(8));
			//Eliminate the notes from the page
			
		//	formPanel.getLayout().layout();
			//compare.getForm().getWidget(3).getElement().removeFromParent();
			
			//panel.add(AMISCompare.buildNotes());
			
			//panel.add(AMISCompare.buildNotesWithSpace(false));
			//formPanel.add(AMISCompare.buildNotes());
			//panel.add(v);
	     	panel.layout();
		}
	 
	 
	 public static void buildNoMessage(ContentPanel panel, List<AMISQueryVO> qvos) {
			
			panel.removeAll();
			
			ContentPanel emptyPanels = new ContentPanel();
			emptyPanels.setHeaderVisible(false);
			emptyPanels.setBodyBorder(false);
			emptyPanels.setBorders(true);
		
			AMISConstants content = AMISConstants.valueOf(qvos.get(0).getOutput());
			
			String width = getWidth(content, qvos.get(0));
			String height = getHeight(content, qvos.get(0));
			
			emptyPanels.add(EmptyValuesPanel.build(width, height, qvos.get(0))); // no data available message
			
			panel.add(emptyPanels);
	     	panel.layout();
		}
	 
	 
	 public static void buildViewsOriginal(ContentPanel panel, List<AMISQueryVO> qvos, String title) {
			
			panel.removeAll();
			VerticalPanel v = new VerticalPanel();
			
			ContentPanel emptyPanels = new ContentPanel();
			emptyPanels.removeAll();
			
			v.setHorizontalAlign(HorizontalAlignment.LEFT);
			
			v.setScrollMode(Scroll.AUTOX);
			
			System.out.println("qvos.size(): " + qvos.size());
			
			Integer currentSize = 0;
			
			HorizontalPanel widgetsPanel = new HorizontalPanel();
			
			Boolean addHSpacing = false;
			
			for(int i=0; i < qvos.size(); i++) {
				
				AMISQueryVO qvo = qvos.get(i);
				
				//v.add(FormattingUtils.addVSpace(10));
				
				AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
				
				// Object Size (Width and Height)
				String width = getWidth(content, qvo);
				String height = getHeight(content, qvo);
				
				
				
				// Calculate width
				currentSize = currentSize + Integer.valueOf(width);
				
				System.out.println(i + ") content: " + content.name() + " | w:" + width + " | h:" + height + " (currentsize: " + currentSize + ")" + "  addHSpacing: " + addHSpacing);

				if ( currentSize < ((AMISInterfaceConstants.CENTER_PANEL_WIDTH) - AMISInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING) ) {
					if ( addHSpacing )
						widgetsPanel.add(FormattingUtils.addHSpace(AMISInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING));
					addHSpacing = true;
				}
				else {
					v.add(widgetsPanel);
					v.add(FormattingUtils.addVSpace(AMISInterfaceConstants.CENTER_PANEL_VERTICAL_SPACING));
					widgetsPanel = new HorizontalPanel();
					currentSize = Integer.valueOf(width);
					addHSpacing = true;
				}

				

				
				switch (content) {
				case TABLE:
					widgetsPanel.add(addTable(qvo, width, height));
					break;
					
				case CHART:
					//ORIGINAL widgetsPanel.add(addChart(emptyPanels, qvo, width, height, qvos.size()));
					//widgetsPanel.add(addChart(v, widgetsPanel, emptyPanels, qvo, width, height, qvos.size()));

					break;

				}
			}
			v.add(widgetsPanel);
			
			/**if(!AMISCompareController.getEmptyPanels().isEmpty()) {
				for(Widget widget: AMISCompareController.getEmptyPanels()){
					v.add(widget);
				}
			} else
				System.out.println("@@@ ###vb No empty Panels!!");**/
			
			panel.add(v);
	     	panel.layout();
		}
		
	 private static String getWidth(AMISConstants content, AMISQueryVO qvo) {
			
			if ( qvo.getWidth() != null )
				return qvo.getWidth();
			
			switch (content) {
				case TABLE:
					return AMISInterfaceConstants.TABLE_WIDTH;
				case CHART:
					return AMISInterfaceConstants.CHART_WIDTH;
			}
			
			return null;
		}
		
		private static String getHeight(AMISConstants content, AMISQueryVO qvo) {
			if ( qvo.getHeight() != null )
				return qvo.getHeight();
			
			switch (content) {
				case TABLE:
					return AMISInterfaceConstants.TABLE_HEIGHT;
				case CHART:
					return AMISInterfaceConstants.CHART_HEIGHT;
			}
			
			return null;
		}


		public static void cleanUpSelects(List<String> selects) {
			for(int j = 0; j < selects.size(); j++ ){
				String select = selects.get(j);
				
				if(select.contains("AMIS_DATA_TABLE")){
					String newSelect = select.replace("AMIS_DATA_TABLE", AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS));	
					selects.remove(j);
					selects.add(j, newSelect);
				}
			}
			
		}


		public static AMISQueryVO setFroms(AMISQueryVO qvo) {
			List<String> froms = new ArrayList<String>();
			froms.add(AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS) +" D ");	
			//froms.add(AMISConstants.amis_all_datasources.toString() +" D ");
			froms.add(AMISController.getTableNameForCodingSystem(AMISConstants.AMIS.toString()+"_ELEMENTS") + " E ");	
			qvo.setFroms(froms);
			
			return qvo;
		}
		
		//re-set how the years are viewed for Rice imports and exports chart
		public static void handleRiceTradeYears(AMISQueryVO qvo, String selectedProduct){
			AMISResultVO amis_dataset_vo = AMISController.amisDatasetMap.get(AMISConstants.AMIS.toString());
			for(int i = 0; i < qvo.getSelects().size(); i++){
				String select = qvo.getSelects().get(i);
				if(select.contains("year") && amis_dataset_vo!=null) {
					qvo.getSelects().remove(select);
					if(selectedProduct.contains("Rice"))	{									
						String rice_year_select = "CASE WHEN year = (SELECT MAX(year) FROM "+amis_dataset_vo.getFenixDatasetTableName()+") THEN substring(CAST(ROUND(CAST(extract(year from year + interval '1 year') AS NUMERIC), 0) AS TEXT), 0) || ' f''cast'"+
						"ELSE substring(CAST(ROUND(CAST(extract(year from year + interval '1 year') AS NUMERIC), 0) AS TEXT), 0) END AS year_rice";										
						qvo.getSelects().add(i, rice_year_select);
						break;
					}
					else {
						String rice_year_select = "CASE WHEN year = (SELECT MAX(year) FROM "+amis_dataset_vo.getFenixDatasetTableName()+") THEN ROUND(CAST(extract(year from D.year) AS NUMERIC), 0) || '/' || substring(CAST(ROUND(CAST(extract(year from year + interval '1 year') AS NUMERIC), 0) AS TEXT), 3) || ' f''cast'"+
						"ELSE ROUND(CAST(extract(year from D.year) AS NUMERIC), 0) || '/' || substring(CAST(ROUND(CAST(extract(year from year + interval '1 year') AS NUMERIC), 0) AS TEXT), 3)"+
						"END AS year";										
						qvo.getSelects().add(i, rice_year_select);
						break;								
					} 
				} 
			}
		}
		
		//re-set which Element is displayed for the World and Countries
		public static void handleTradeElements(AMISQueryVO qvo, AMISCodesModelData selectedCountryModel){
			
			String selectedAreaCode = null;
				
			if(selectedCountryModel!=null ) {
				selectedAreaCode = selectedCountryModel.getCode();
			} 
			  	
             for(String key: qvo.getElements().keySet()){
            	String element = qvo.getElements().get(key);
            	if(element.contains("Imports") && selectedAreaCode!=null) {
            		qvo.getElements().remove(key);
            		
            		if(selectedAreaCode.equals("999000"))	{	
            			qvo.getElements().put("8", "Imports (ITY)"); // <ELEMENT label='Exports (NMY)'>10</ELEMENT> <ELEMENT label='Imports (NMY)'>7</ELEMENT>
            		}
            		else{
            			qvo.getElements().put("7", "Imports (NMY)");
            		}	
            		break;
            	}           	
            	
            }
             
             for(String key: qvo.getElements().keySet()){
             	String element = qvo.getElements().get(key);
              	if(element.contains("Exports") && selectedAreaCode!=null) {
             		qvo.getElements().remove(key);
             		if(selectedAreaCode.equals("999000"))	{	
             			qvo.getElements().put("12", "Exports (ITY)"); // <ELEMENT label='Exports (NMY)'>10</ELEMENT> <ELEMENT label='Imports (NMY)'>7</ELEMENT>
             		}
             		else{
             			qvo.getElements().put("10", "Exports (NMY)");
             		}
             		break;
             	}

             	
             } 
			
	
		}


		public static Listener<ComponentEvent> statisticalNoteLinkAction(final AMISMainMenu mainMenu) {
			return new Listener<ComponentEvent>() {
				@Override
				public void handleEvent(ComponentEvent be) {
					System.out.println("Class: AmisController Function: statisticalNoteLinkAction Text:Before call openView ");
					AMISMenuController.openViewAgent(AMISCurrentView.STATISTICALNOTES, mainMenu);
					AMISHistoryController.setHistoryAnchor(AMISCurrentView.STATISTICALNOTES);
					System.out.println("Class: AmisController Function: statisticalNoteLinkAction Text:After call openView ");
				}
				
			};
		}

/**
 * Tracking Google Analytics Events for Registered User (not AMIS Secretariat)
 * @param action
 */
		public static void trackUserEvent(String action) {
			System.out.println("XXXX 1 trackUserEvent " + FenixUser.hasAdminRole());
			
			if(FenixUser.hasAdminRole())
			{
				
				System.out.println("XXXX 2 trackUserEvent " + AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT));
				// if the Logged In User is Not AMIS Secretariat track event
				if(!AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT))
				{ 
					
					System.out.println("XXXX 3 trackUserEvent " +CCBS.COUNTRY_NAMES.get(0) + " | " + action);
					
					// CCBS.COUNTRY_NAMES.get(0) = User's associated country
					GoogleAnalyticsController.trackEvent(CCBS.COUNTRY_NAMES.get(0), action, "");  
				}
			}
				
		}
		
	/*	public static Listener<ComponentEvent> buildLoginPanel(final AMISMainMenu mainMenu) {
			return new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent ce) {
					if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
						RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
					
					if(currentSelectedView.equals(AMISCurrentView.HOME))
						AMISHome.hideHomeVisibility();						
					
					RootPanel.get("MAIN_CONTENT").add(new AMISLoginRegisterPanel(mainMenu).build());
					
				}
			};
		}
		
		public static Listener<ComponentEvent> logOut(final AMISMainMenu mainMenu) {
			return new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent ce) {
					if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
						RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
					
					//Remove Welcome
					if (RootPanel.get("USER_LOGIN").getWidgetCount() > 0)
						RootPanel.get("USER_LOGIN").remove(RootPanel.get("USER_LOGIN").getWidget(0));
					
					//Re-Set Log Out to Log In
					buildLogMessage(mainMenu, buildLoginPanel(mainMenu), "Log In");
					
					AMISMenuController.openViewAgent(currentSelectedView, mainMenu);
					
				}
			};
		}
		
		
		
		
		
		public static void buildLogMessage(final AMISMainMenu mainMenu,  Listener<ComponentEvent> listener, String text) {
			
			Element p = RootPanel.get("AMIS-LOGIN").getElement();
			 ClickHtml clickHtml = new ClickHtml();
			 clickHtml.setId("AMIS-LOGIN");
			 clickHtml.setHtml("<a href='#'>"+text+"</a>");
			 clickHtml.setStyleName("login");
			 clickHtml.addListener(Events.OnClick, listener);
			 p.getParentElement().replaceChild(clickHtml.getElement(), p);
			 clickHtml.attach();
			 
			AMISMenuController.openViewAgent(currentSelectedView, mainMenu);
			 
			
		} 
		
		public static HorizontalPanel buildWelcomeMessage(String text) {
			HorizontalPanel vp = new HorizontalPanel();
			HorizontalPanel panel = new HorizontalPanel();
			panel.addStyleName("login_welcome_panel");
			panel.setStyleAttribute("backgroundColor", "#FFFFFF");
			panel.setSpacing(10);
			vp.setStyleAttribute("backgroundColor", "#FFFFFF");
			HTML html = new HTML();
			html.setHTML("<div class='login_welcome'>" + text + "</div>");
			panel.add(html);
			vp.setHorizontalAlign(HorizontalAlignment.RIGHT);
			vp.setTableWidth("100%");
			vp.add(panel);
			return vp;
		} 
		
		private static Listener<BaseEvent> removeLogInMessage() {
			return new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent ce) {
					
					int i = 0;
					while(i<RootPanel.get("USER_LOGIN").getWidgetCount())		
					{
						Widget w = RootPanel.get("USER_LOGIN").getWidget(i);
						ClickHtml html = (ClickHtml)w;
						if((html != null)&&(html.getId()!=null)&&(html.getId().equals("message")))
						{
							RootPanel.get("USER_LOGIN").getWidget(i).removeFromParent();
							break;
						}
						i++;
					}				
				}
			};
		}*/
}


