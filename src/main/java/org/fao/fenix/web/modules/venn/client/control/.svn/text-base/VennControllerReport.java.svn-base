package org.fao.fenix.web.modules.venn.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.venn.client.view.CenterToolPanel;
import org.fao.fenix.web.modules.venn.client.view.DimensionPortletPanel;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentChart;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentMap;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentOlap;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentVenn;
import org.fao.fenix.web.modules.venn.client.view.VennPortalPanel;
import org.fao.fenix.web.modules.venn.common.services.VennServiceEntry;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennConfigurationBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableValuesBeanVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class VennControllerReport {
	
	
	public static void applyXMLConfiguration(final VennPortalPanel vennPortal, final CenterToolPanel centerPanel, final String language,final String xml) {

		// getting xml configuration settings
		VennServiceEntry.getInstance().getConfiguration(xml, true, new AsyncCallback<VennConfigurationBeanVO>() {
			public void onSuccess(VennConfigurationBeanVO bean) {
				
				// 	apply to venn	
				HashMap<String, String> aCodes = getCodes(bean.getSetA().getStrategies());
				HashMap<String, String> bCodes = getCodes(bean.getSetB().getStrategies());
				HashMap<String, String> cCodes = getCodes(bean.getSetC().getStrategies());
				System.out.println("bean countries= " + bean.getCountries());
				List<String> countryCodes = bean.getCountries();
				vennPortal.setCountryCodes(countryCodes);
				
				createStandardReportEntryPoint(vennPortal, aCodes, bCodes, cCodes, centerPanel, language, countryCodes, bean.getSetA().getOrganization(), bean.getSetB().getOrganization(), bean.getSetC().getOrganization());
				
//				apply(vennPortal, aCodes, bCodes, cCodes, centerPanel, language, countryCodes, bean.getSetA().getOrganization(), bean.getSetB().getOrganization(), bean.getSetC().getOrganization());
			}
			
			public void onFailure(Throwable arg0) {
				FenixAlert.error("Retrieving XML", "Error retrieving configuration");
			}

		
		});
		
	}

	
	/***************** this method create the standard report ***/
	public static void createStandardReportEntryPoint(final VennPortalPanel vennPortal, final HashMap<String, String> aCodes, final HashMap<String, String> bCodes, HashMap<String, String> cCodes, final CenterToolPanel centerPanel, final String language, final List<String> countryCodes, final String aStaticLabel, final String bStaticLabel, final String cStaticLabel) {
		/*** on cascade retrieve all the info needed by the report **/
		
		/** create venn **/
		VennServiceEntry.getInstance().getDACCodes(aCodes, bCodes, cCodes, language, new AsyncCallback<VennResultsVO>() {
			public void onSuccess(final VennResultsVO results) {
				System.out.println("createStandardReport HERE");


				VennServiceEntry.getInstance().calculateIntersections(results, countryCodes, language, new AsyncCallback<VennBeanVO>() {
					public void onSuccess(VennBeanVO bean) {
						System.out.println("createStandardReport HERE");
						
						String aLabel = aStaticLabel;
						String bLabel = bStaticLabel;
						String cLabel = cStaticLabel;
						if ( !results.getA_dacCodes().isEmpty() && aLabel.equals(""))
							aLabel = vennPortal.getControllerPortalPanel().getxPortlet().getComboOrganizations().getValue().getName();
						
						if ( !results.getB_dacCodes().isEmpty() && bLabel.equals(""))
							bLabel = vennPortal.getControllerPortalPanel().getyPortlet().getComboOrganizations().getValue().getName();
						
						if ( !results.getC_dacCodes().isEmpty() && cLabel.equals(""))
							cLabel = (vennPortal.getControllerPortalPanel().getwPortlet().getComboOrganizations().getValue().getName());
							

						bean.getVennGraphBeanVO().setDacCodes(results.getMergedDacCodes());
						bean.getVennGraphBeanVO().setSo_dacCodes(results.getSo_dacCorrispondence());
						bean.getVennGraphBeanVO().getA().setLabel(aLabel);
						bean.getVennGraphBeanVO().getB().setLabel(bLabel);
						bean.getVennGraphBeanVO().getC().setLabel(cLabel);
						bean.getVennGraphBeanVO().getAb().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getB().getLabel());
						bean.getVennGraphBeanVO().getBc().setLabel(bean.getVennGraphBeanVO().getB().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						bean.getVennGraphBeanVO().getAc().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						bean.getVennGraphBeanVO().getAbc().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getB().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						
						
						HashMap<String, String> countries = new HashMap<String, String>();
						for(String country : vennPortal.getCountryCodes()) {
							countries.put(country, country);
						} 
						
						bean.setCountries(countries);

						/*** venn **/
						final VennBeanVO vennBean = bean;

						VennServiceEntry.getInstance().createVenn(bean, "aggregationDAC", new AsyncCallback<List<VennIframeVO>>() {
							public void onSuccess(List<VennIframeVO> results) {
								
								for(VennIframeVO result : results) {
									String urlCP = "../venn/temp/" + result.getIframeCentralPanel();
									String iFrameCP = "<div align='center'> Priorities Matching </div>" + "<div align='center'> <img src='" + urlCP + "'" + "> </div>";
									
									String urlP = "../venn/temp/" + result.getIframePortlet();
									String iFrameP = "<div align='center'> Priorities Matching </div>" + "<div align='center'> <img src='" + urlP + "'" + "> </div>";
									
									System.out.println("result.getIframeCentralPanel():" + result.getIframeCentralPanel() +"|");
										
//									/** Store the venn image path for the report **/
//									vennBean.getVennGraphBeanVO().getVennImages().put("normal", result.getIframeCentralPanel());
									vennBean.getVennGraphBeanVO().getVennImages().put("normal", result.getIframePortlet());
									
									vennPortal.getVennReportBean().getVennGraphReportBean().setVennGraphBeanVO(vennBean.getVennGraphBeanVO());					

									VennController.exportStandardReportMethod(vennPortal);
								}
									
									
									
									
								}

								public void onFailure(Throwable caught) {
									FenixAlert.alert("RPC Failed", "TableController @ getFilters");
								}
							});
						
			
						
					}

					public void onFailure(Throwable caught) {
//						loadingWindow.destroyLoadingBox();
					}

				});
			}

			public void onFailure(Throwable caught) {

			}

		});
		
	}
	
	@SuppressWarnings("deprecation")
	private static HashMap<String, String> getCodes(List<String> strategies) {
		HashMap<String, String> codes = new HashMap<String, String>();
		
		for (String strategy : strategies) 
			codes.put(strategy, strategy);

		return codes;
	}
	

}
