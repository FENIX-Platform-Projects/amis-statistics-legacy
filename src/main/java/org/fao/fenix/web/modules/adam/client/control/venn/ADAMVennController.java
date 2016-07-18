package org.fao.fenix.web.modules.adam.client.control.venn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennRecipientMatrixController;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMVennMaker;
import org.fao.fenix.web.modules.adam.client.view.venn.ADAMVennPriorities;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennMatrix;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennRecipientsVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.venn.common.services.VennServiceEntry;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMVennController extends ADAMController {
	
	public static void changeViewOnPriorities(final Map<String, String> donorList, final Map<String, String> gaulList, final Map<String, String> priorities, final String intersectionValue, final String intersection) {
		System.out.println("changing the view based on priorities");

		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		Boolean countryAdded = addCountryFilter(filters, gaulList);
		System.out.println("countryAdded: " + countryAdded);
		Boolean donorAdded = addDonorFilter(filters, donorList);
		System.out.println("donorAdded: " + donorAdded);
//		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
//		System.out.println("sectorAdded: " + sectorAdded);
		List<String> dacCodes = new ArrayList<String>();
		
		if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){
			for(String dac: priorities.keySet()){
				dacCodes.add(dac);
			}
		}
		
		 if( countryAdded && !donorAdded){
			 if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
				 createCountryORview(filters, priorities, intersectionValue, intersection);
			 else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){
				 createCountryDACview(filters, priorities, intersectionValue, dacCodes, intersection);
			 }
		 }
		 else if( countryAdded && donorAdded) {
			 if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
				 createCountryDonorORview(filters, priorities, intersectionValue, intersection);
			 else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){
				 createCountryDonorDACview(filters, priorities, intersectionValue, dacCodes, intersection);
			 }
				 
		 }
		
	
	};
	
	private static void createCountryDACview(HashMap<String, List<String>> filters, final Map<String, String> priorities, final String intersectionValue,  final List<String> dacCodes, final String intersection) {
		String type = "";
		String xAxisLabel = "";
		
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)) {
			type = "Priority ORs";
			xAxisLabel = "FAO Organizational Results (hover over an OR for the label)";
		}	
		else
			type = "Priority OECD-DAC Sub-Sectors"; {
			xAxisLabel = "OECD-DAC Sub-Sector Codes (hover over a code for the label)";
		 }
		
		//ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners for the "+ intersectionValue+ " " + type, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners for the " + type, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		
		setRecipientIntersectionsDescription(intersection, topDonorsChart);
		topDonorsChart.getWheres().put("purposecode", dacCodes);
		//ADAMTableController.addChart("ANALYSE_BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
		ADAMTableController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
		
		
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR_STACK_DAC_DONOR.name(), "Financing of "+ type, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false);
		toORsChart.setxLabel(xAxisLabel);
		setRecipientIntersectionsDescription(intersection, toORsChart);
		toORsChart.getWheres().put("purposecode", dacCodes);
		//ADAMController.addChart("ANALYSE_BOTTOM_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		
	}
	
	private static void createCountryDonorDACview(HashMap<String, List<String>> filters, final Map<String, String> priorities, final String intersectionValue, final List<String> dacCodes, final String intersection) {
        String type = "";
		
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			type = "Priority ORs";
		else
		  type = "Priority OECD-DAC Sub-Sectors";
		
		ADAMQueryVO channelORChart = ADAMQueryVOBuilder.topChannelsChart("Channels of Delivery for the "+ intersectionValue +" " + type, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false); 	
		channelORChart.getWheres().put("purposecode", dacCodes);
		//ADAMController.addChart("ANALYSE_BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), channelORChart, objectWindowId); 
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), channelORChart, objectWindowId); 
		
		
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR.name(), "Financing of "+ type, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false);
		toORsChart.getWheres().put("purposecode", dacCodes);
		//ADAMController.addChart("ANALYSE_BOTTOM_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		
	}
	
	private static void createCountryORview(HashMap<String, List<String>> filters, final Map<String, String> priorities, final String intersectionValue, final String intersection) {
		//ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners for the "+ intersectionValue+ " ORs", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, false, 10);	
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners for the Priority ORs", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, false, 10);	
		
		setRecipientIntersectionsDescription(intersection, topDonorsChart);
		topDonorsChart.setPriorities(priorities);	
		//ADAMTableController.addChart("ANALYSE_BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
		ADAMTableController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
		
		
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR_STACK_OR_DONOR.name(), "Financing of Priotity ORs", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, false);
		toORsChart.setxLabel("FAO Organizational Results (hover over an OR for the label)");
		setRecipientIntersectionsDescription(intersection, toORsChart);
		toORsChart.setPriorities(priorities);
		//ADAMController.addChart("ANALYSE_BOTTOM_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		
	}
	
	private static void createCountryDonorORview(HashMap<String, List<String>> filters, final Map<String, String> priorities, final String intersectionValue, final String intersection) {
		ADAMQueryVO channelORChart = ADAMQueryVOBuilder.topChannelsChart("Channels of Delivery for the "+ intersectionValue +" ORs", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, false); 	
		channelORChart.setPriorities(priorities);	
		//ADAMController.addChart("ANALYSE_BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), channelORChart, objectWindowId); 
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), channelORChart, objectWindowId); 
		
		
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR.name(), "Financing of Priority ORs", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, false);
		toORsChart.setPriorities(priorities);
		//ADAMController.addChart("ANALYSE_BOTTOM_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		
	}
	
	
	/** VENN **/
	public static void addPrioritiesVenn(final String titleA, final String titleB, final String titleC, final String hoverTitleA, final String hoverTitleB, final String position, final String color, final HashMap<String, String> setA, final HashMap<String, String> setB, final HashMap<String, String> setC, final String language, final long objID) {
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());


		final List<String> countryCodes = new ArrayList<String>();


		// in this case
		// setA = recipients
		// setB = channels
		// setC = donors

		/** TODO: the strategic bojective should be takeing with a list of string, and not just a code **/
		ADAMQueryVO qrvo = ADAMQueryVOBuilder.getPriorities(ADAMBoxContent.VENN_RECIPIENT_PRIORITIES, "RECIPIENT_KEYSOS", setA, null, null);
		qrvo.setCurrentView(ADAMController.currentVIEW);
		
		ADAMServiceEntry.getInstance().askADAM(qrvo, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO recipients) {
				final HashMap<String, String> aCodes = (HashMap<String, String>) recipients.getPriorities();
				final Map<String, Map<String, String>> countriesPriorities = recipients.getCountriesPriorities();

//				System.out.println("aCodes :" +aCodes);
				/*for( String key: aCodes.keySet()) {
					System.out.println("A key: " + key);
				}*/
				final ADAMQueryVO qcvo = ADAMQueryVOBuilder.getPriorities(ADAMBoxContent.VENN_CHANNEL_PRIORITIES, "CHANNEL_KEYSOS", setA, setB, null);		
				qcvo.setCurrentView(ADAMController.currentVIEW);
				
				ADAMServiceEntry.getInstance().askADAM(qcvo, new AsyncCallback<ADAMResultVO>() {
					public void onSuccess(ADAMResultVO channels) {
						final HashMap<String, String> bCodes = (HashMap<String, String>) channels.getPriorities();

//						System.out.println("bCodes :" +bCodes);
						/*for( String key: bCodes.keySet()) {
							System.out.println("B key: " + key);
						}*/
					
						String donorPrioritiesDatasetCode = "";
						if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
							donorPrioritiesDatasetCode = "FPMIS_DONOR_KEYSOS";
						} else {
							donorPrioritiesDatasetCode = "DONOR_KEYSOS";
						}
						
						ADAMQueryVO qdvo = ADAMQueryVOBuilder.getPriorities(ADAMBoxContent.VENN_DONOR_PRIORITIES, donorPrioritiesDatasetCode, setA, setB, setC);		
						qdvo.setCurrentView(ADAMController.currentVIEW);
						
						ADAMServiceEntry.getInstance().askADAM(qdvo, new AsyncCallback<ADAMResultVO>() {
							public void onSuccess(ADAMResultVO donors) {
								final HashMap<String, String> cCodes = (HashMap<String, String>) donors.getPriorities();
								
								final Map<String, Map<String, String>> donorsPriorities = donors.getDonorsPriorities();

//								System.out.println("cCodes :" +cCodes);
	//							for( String key: cCodes.keySet()) {
									//System.out.println("B key: " + key);
					//			}
								
								
								/*for( String key: donorsPriorities.keySet()) {
									System.out.println("donorsPriorities key: " + key);
								}
								
								for( Map<String, String> values: donorsPriorities.values()) {
									for( String label: values.values()) {
										System.out.println("donorsPriorities value: label: " + label);
									}
								}*/
								
								final VennResultsVO results = new VennResultsVO();

								results.setA_dacCodes(aCodes);
								results.setB_dacCodes(bCodes);
								results.setC_dacCodes(cCodes);
								results.setAggregationLevel(0);

								VennServiceEntry.getInstance().calculateIntersections(results, countryCodes, language, new AsyncCallback<VennBeanVO>() {
									public void onSuccess(VennBeanVO bean) {

										String aLabel = titleA;
										String bLabel = titleB;
										String cLabel = titleC;
										
										String aHoverLabel = hoverTitleA;
										String bHoverLabel = hoverTitleB;
										String cHoverLabel = titleC;
										
										System.out.println("bean dac: " + bean.getVennGraphBeanVO().getDacCodes() );
										
										
										
										
//										bean.getVennGraphBeanVO().setDacCodes(results.getMergedDacCodes());
										bean.getVennGraphBeanVO().setSo_dacCodes(results.getSo_dacCorrispondence());
										bean.getVennGraphBeanVO().getA().setLabel(aLabel);
										bean.getVennGraphBeanVO().getB().setLabel(bLabel);
										bean.getVennGraphBeanVO().getC().setLabel(cLabel);
										
										bean.getVennGraphBeanVO().getA().setHoverLabel(aHoverLabel);
										bean.getVennGraphBeanVO().getB().setHoverLabel(bHoverLabel);
										bean.getVennGraphBeanVO().getC().setHoverLabel(cHoverLabel);
										
										
										bean.getVennGraphBeanVO().getAb().setLabel(bean.getVennGraphBeanVO().getA().getHoverLabel() + "/" + bean.getVennGraphBeanVO().getB().getHoverLabel()+" Overlap");
										bean.getVennGraphBeanVO().getBc().setLabel(bean.getVennGraphBeanVO().getB().getHoverLabel() + "/" + bean.getVennGraphBeanVO().getC().getHoverLabel()+" Overlap");
										bean.getVennGraphBeanVO().getAc().setLabel(bean.getVennGraphBeanVO().getA().getHoverLabel() + "/" + bean.getVennGraphBeanVO().getC().getHoverLabel()+" Overlap");
										bean.getVennGraphBeanVO().getAbc().setLabel(bean.getVennGraphBeanVO().getA().getHoverLabel() + "/" + bean.getVennGraphBeanVO().getB().getHoverLabel() + "/" + bean.getVennGraphBeanVO().getC().getHoverLabel() +" Overlap");

										System.out.println("bean A hover: " + bean.getVennGraphBeanVO().getA().getHoverLabel());
										System.out.println("bean B hover: " + bean.getVennGraphBeanVO().getB().getHoverLabel());
										System.out.println("bean C hover: " +bean.getVennGraphBeanVO().getC().getHoverLabel());
										
										System.out.println("bean A: " + bean.getVennGraphBeanVO().getA().getLabel());
										System.out.println("bean B: " + bean.getVennGraphBeanVO().getB().getLabel());
										System.out.println("bean C: " +bean.getVennGraphBeanVO().getC().getLabel());
										System.out.println("bean getAb: " + bean.getVennGraphBeanVO().getAb().getLabel());
										System.out.println("bean getBc: " + bean.getVennGraphBeanVO().getBc().getLabel());
										System.out.println("bean getAc: " +bean.getVennGraphBeanVO().getAc().getLabel());
										System.out.println("bean getAbc: " +bean.getVennGraphBeanVO().getAbc().getLabel());
										
										ADAMResultVO adamResultVO = new ADAMResultVO();
										HashMap<String, String> priorities = new HashMap<String, String>();
										
										if(aCodes!=null)
											priorities.putAll(aCodes);
										if(bCodes!=null)
											priorities.putAll(bCodes);
										if(cCodes!=null)
											priorities.putAll(cCodes);
										adamResultVO.setPriorities(priorities);
										adamResultVO.setDonorsPriorities(donorsPriorities);
										adamResultVO.setCountriesPriorities(countriesPriorities);
										
										// setting the RESULT VO
										// the output is setted for both as the outputtype
										adamResultVO.setOutputType(qcvo.getOutputType());
										adamResultVO.setResourceType(qcvo.getOutputType());
										

										/** venn **/
										putVenn(adamResultVO, bean, position, color, objID);

									}

									public void onFailure(Throwable caught) {

									}

								});
							}
							public void onFailure(Throwable arg0) {
							}
						});

					}

					public void onFailure(Throwable arg0) {
					}
				});

			}
			public void onFailure(Throwable arg0) {
			}
		});

	}


	public static void putHighlightedVenn(final String imageMapId, final VerticalPanel vennPanelBig, final VerticalPanel vennPanelSmall, final ADAMResultVO adamResultVO, final VennBeanVO vennBean, final String intersection, final LinkedHashMap<String, String> codesHM, final String intersectionValue, final VerticalPanel smallInfoHolder, final VerticalPanel bigInfoHolder) {

		VennServiceEntry.getInstance().createHighlightedVenn(vennBean, "aggregationDAC", intersection, new AsyncCallback<List<VennIframeVO>>() {
			public void onSuccess(List<VennIframeVO> results) {

				for(VennIframeVO result : results) {
				/** TODO: for now it's just one **/
						String urlBig = "../venn/temp/" + result.getIframeCentralPanel();
						adamResultVO.setBigImagePath(urlBig);
						
						String urlSmall = "../venn/temp/" + result.getIframePortlet();
						adamResultVO.setSmallImagePath(urlSmall);						
			    }
				
				//Synchronize the big and small venn images, so that any selection from the small venn is also matched in the big venn image and vice versa
				if(imageMapId.contains("small")) {
					vennPanelSmall.removeAll();
					vennPanelSmall.add(ADAMVennPriorities.buildSmallVenn(adamResultVO.getSmallImagePath()));
				    vennPanelSmall.layout();
				    smallInfoHolder.layout();
				    
				   if(vennPanelBig!=null){
				    	vennPanelBig.removeAll();
				    	vennPanelBig.add(ADAMVennPriorities.buildBigVenn(adamResultVO.getBigImagePath()));
				    	vennPanelBig.layout();				   
					}
				    if(bigInfoHolder!=null){
				    	bigInfoHolder.layout();
					}	
				} else if(imageMapId.contains("big")) {
					vennPanelBig.removeAll(); 
					vennPanelBig.add(ADAMVennPriorities.buildBigVenn(adamResultVO.getBigImagePath()));
				    vennPanelBig.layout();
				    bigInfoHolder.layout();
				    
				  if(vennPanelSmall!=null){
				    	vennPanelSmall.removeAll();
						vennPanelSmall.add(ADAMVennPriorities.buildSmallVenn(adamResultVO.getSmallImagePath()));
					    vennPanelSmall.layout();				   
					}
				    if(smallInfoHolder!=null){
				    	smallInfoHolder.layout();
					}
				}

				ADAMVennController.changeViewOnPriorities(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, codesHM, intersectionValue, intersection);
				
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "ADAMVennController @ putHighlightedVenn");
			}
		});
	}
	
	
	public static void putVenn(final ADAMResultVO adamResultVO, final VennBeanVO vennBean, final String position, final String color, final long objID) {


		VennServiceEntry.getInstance().createVenn(vennBean, "aggregationDAC", new AsyncCallback<List<VennIframeVO>>() {
			public void onSuccess(List<VennIframeVO> results) {

				System.out.println("VENN objID: " + objID + " | objectWindowId: " + objectWindowId);

				/** TODO: for now it's just one **/
				for(VennIframeVO result : results) {

					if ( objID == objectWindowId ) {
						if (RootPanel.get(position).getWidgetCount() > 0)
							RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				
						String title = "Common Priorities Venn Diagram";
						
						if(ADAMController.currentVIEW.equals(ADAMController.currentVIEW.ADAMVIEW) && !ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
							title += " (expressed in OECD-DAC Sub-Sectors)";
						else
							title += " (expressed in Organizational Results - ORs)";
						
						
						
						adamResultVO.setTitle(title);
						adamResultVO.setVennBean(vennBean);
						adamResultVO.setBoxColor(color);
						adamResultVO.setDescription(getVennExplanation());

						String urlBig = "../venn/temp/" + result.getIframeCentralPanel();
						adamResultVO.setBigImagePath(urlBig);
						adamResultVO.setBigBaseImagePath(urlBig);
						

						String urlSmall = "../venn/temp/" + result.getIframePortlet();
						adamResultVO.setSmallImagePath(urlSmall);
						adamResultVO.setSmallBaseImagePath(urlSmall);
						


						RootPanel.get(position).setStyleName("big-box content");


						RootPanel.get(position).add(ADAMVennMaker.buildSmallPriorityVenn(adamResultVO.getTitle(), urlSmall, ADAMConstants.BIG_BOX_WIDTH, ADAMConstants.SMALL_HEIGHT, adamResultVO, position, color, fullScreenVenn(position, color, "Common priorities")));

						RootPanel.get(position).getWidget(0).setTitle("");
						RootPanel.get(position).setTitle("");

						vennMap.put(position, adamResultVO);
						currentUsedObjects.put(position, ADAMBoxContent.VENN_PRIORITIES);

						checkBoxes();
					}
				}




			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "TableController @ getFilters");
			}
		});
	}
	
	
	
	private static HashMap<String, String> getStrategyCodes(HashMap<String, HashMap<String, String>> strategies) {
		HashMap<String, String> codes = new HashMap<String, String>();


		for (String strategy : strategies.keySet())
			codes.putAll(strategies.get(strategy));


		return codes;
	}




	/*public static SelectionListener<ButtonEvent> fullScreenVenn(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVisibilityController.styleVisibilityDisplay("CENTER");
				
				removeBoxes();
				ADAMResultVO vo = vennMap.get(position);
				RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
				RootPanel.get("CENTER").add(ADAMVennMaker.buildBigPriorityVenn(vo.getTitle(), vo.getBigImagePath(),ADAMConstants.BIG_WIDTH, ADAMConstants.BIG_HEIGHT, vo.getVennBean(), smallScreenVenn(position, color, title)));
				RootPanel.get("CENTER").getWidget(0).setTitle("");
				
			}
		};
	}*/

	public static SelectionListener<ButtonEvent> fullScreenVenn(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeCenter();
				ADAMVisibilityController.styleVisibilityDisplay("CENTER");
				ADAMResultVO vo = vennMap.get(position);
				//RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				//RootPanel.get("CENTER").add(ADAMVennMaker.buildBigPriorityVenn(vo.getTitle(), vo.getBigImagePath(),ADAMConstants.BIG_WIDTH, ADAMConstants.BIG_HEIGHT, vo.getVennBean(), smallScreenVenn(position, color, title)));
				RootPanel.get("CENTER").add(ADAMVennMaker.buildBigPriorityVenn(vo.getTitle(), vo.getBigImagePath(),ADAMConstants.BIG_WIDTH, ADAMConstants.BIG_HEIGHT, vo, position, color, smallScreenVenn(position, color, title)));
				
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> smallScreenVenn(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeCenter();
			}
		};
	}
	
	/*public static SelectionListener<ButtonEvent> smallScreenVenn(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeBoxes();
				RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES) + "-box border content");
				restoreBoxesContent("Venn");
			}
		};
	}*/
	
	/*public static SelectionListener<ButtonEvent> clearVennResults() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				//ADAMViewController.callADAMView();
				 if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){
					 ADAMController.callViewAgent(ADAMCurrentVIEW.ADAMVIEW, "clearVennResults");
					
				 } 
				 else if (currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
					 ADAMController.callViewAgent(ADAMCurrentVIEW.FAOVIEW, "clearVennResults");
				 }
				
			}
		};
	}*/
	
	
	// VENN RECIPIENT Matrix:
	public static SelectionListener<ButtonEvent> buildVennRecipientMatrix() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVennRecipientMatrixController.buildVennRecipientMatrixAgent(null, true, "ANALYSE_TOP", "ANALYSE_MIDDLE", "ANALYSE_BOTTOM");
			}
		};
	}


	
	public static SelectionListener<ButtonEvent> buildVennRecipientMatrixMenu(final ADAMVennMatrix adamVennMatrix, final ADAMResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVennRecipientMatrixController.buildVennRecipientMatrixAgent(adamVennMatrix, false, "ANALYSE_TOP", "ANALYSE_MIDDLE", "ANALYSE_BOTTOM");
			}
		};
	}

	public static SelectionListener<ButtonEvent> buildVennRecipientMatrixMenuListener(final ADAMVennMatrix adamVennMatrix, final ADAMResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVennRecipientMatrixController.buildVennRecipientMatrixAgent(adamVennMatrix, false, "ANALYSE_TOP", "ANALYSE_MIDDLE", "ANALYSE_BOTTOM");
			}
		};
	}
	
	 protected static List<String> createVennMatrixSourceRow(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities, LinkedHashMap<String, String> recipients, HashMap<String, ADAMVennRecipientsVO> recipientsVOHM) { 
			
			List<String> sourceRow = new ArrayList<String>();	
			
			boolean hasSource = false;
			
			sourceRow.add("Source");
			if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) || currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
				sourceRow.add("");
			
			for(String recipient : recipients.keySet()) {
					if ( recipientsVOHM.containsKey(recipient)) {
						ADAMVennRecipientsVO vo = recipientsVOHM.get(recipient);
						
						if ( recipientsVOHM.get(recipient).getIsCalculated()!=null && recipientsVOHM.get(recipient).getIsCalculated() ) {
							if ( !showOnlyCPF ) {
								sourceRow.add("");
								
								if (showStatedPriorities && ADAMBoxMaker.countriesSelected.size() == 1) {						
									if(vo.getCpfSourceDocument()!=null) {
										sourceRow.add(vo.getCpfSourceDocument());
										hasSource = true;
									}	
									else {
										sourceRow.add("");
									}	
							    }
							}	
						}
						else {
							if(vo.getSourceDocument()!=null) {
								if(vo.getSourceDocument().contains("NMTPF"))
									sourceRow.add("<span title='National Medium-Term Priority Framework (NMTPF)'>"+vo.getSourceDocument()+"</span>");
								else
									sourceRow.add(vo.getSourceDocument());
								
								hasSource = true;
							}	
							else {
								sourceRow.add("");
							}
							if (showStatedPriorities && ADAMBoxMaker.countriesSelected.size() == 1) {						
								if(vo.getCpfSourceDocument()!=null) {
									if(vo.getCpfSourceDocument().contains("NMTPF"))
										sourceRow.add("<span title='National Medium-Term Priority Framework (NMTPF)'>"+vo.getCpfSourceDocument()+"</span>");	
									else
										sourceRow.add(vo.getCpfSourceDocument());
									
									hasSource = true;
								}	
								else {
									sourceRow.add("");
								}	
						    }
						}	
					}
					else {
						sourceRow.add("");
					}
						
			} 
			
			if(hasSource)
				return sourceRow;
			
			else
				return null;
		}
	
	
	 
	 
	 protected static void setVennMatrixORDACTableHeaders(List<String> tableHeaders){
		 if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			 tableHeaders.add("Organizational Result (OR)");
			 tableHeaders.add("Short OR Description");
		 } else {
			 if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
				 tableHeaders.add("Organizational Result (OR)");
				 tableHeaders.add("Short OR Description");
			 }
			 else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
				 //tableHeaders.add("OECD-DAC Sector Code");
				 tableHeaders.add("OECD-DAC Sector");
			 }
		 }
	 }
	 
	 protected static void setVennMatrixORDACTableHeadersWithDAC(List<String> tableHeaders){
		 if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			 tableHeaders.add("Organizational Result (OR)");
			 tableHeaders.add("Short OR Description");
		 } else {
			 if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
				 tableHeaders.add("Organizational Result (OR)");
				 tableHeaders.add("Short OR Description");
			 }
			 else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
				// tableHeaders.add("OECD-DAC Sector Code");
				 tableHeaders.add("OECD-DAC Sector");
			 }
		 }
	 }
	 
	 
	 public static Boolean removeDACSectorCode() {

			Boolean remove = false;
			
			 if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
					remove = false;
				 } else {
					 if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
						 remove = false;
					 }
					 else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
						 remove = true;
					 }
				 }
		
			return remove;
		}
	 
	 
	 private static String getVennExplanation(){
		 String description = "" +
			"<div style='padding:20px'>" +
			"<p><img src='adam-docs/venn_explained.jpg'/></p>" +
			"</div>";
		 
		 return description;
	 }
	 
	 //when the Recipient Venn circle is clicked on, add the description for the i icon to the two displaying charts
	 private static void setRecipientIntersectionsDescription(String intersection, ADAMQueryVO qvo){
		
		 if(intersection.equalsIgnoreCase("a") || intersection.equalsIgnoreCase("ab") || intersection.equalsIgnoreCase("ac")
				 || intersection.equalsIgnoreCase("abc")) { // Recipients
			 System.out.println("ADAMVennController: setRecipientIntersectionsDisclaimer intersection = "+intersection);
			 //Description Here
			//qvo.setDescription("<div style='padding:10px;'>Resource partners displayed have a history of spending but the spending is mimimal compared to their priorities.</div>");
		 }
	 }
	 
	 
	 public static String vennMatrixOnHoverTitle(Map<String, String> recipientCountries, String country, String sectorCode, String sector){
			String title = "";
			String sectorLabel = sector;
			
	        if(!removeDACSectorCode()){
	        	sectorLabel = sectorCode + " ("+sector+")";
			}
	        
			if(recipientCountries.size() > 1)
			    title = country+": "+sectorLabel;
			 else
			    title = sectorLabel;
			  
			return title;
		}
}
