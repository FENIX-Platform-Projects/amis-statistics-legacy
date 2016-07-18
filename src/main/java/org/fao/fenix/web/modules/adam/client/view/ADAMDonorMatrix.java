package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.DonorProfileController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.AggregationRenderer;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ADAMDonorMatrix {

	private static GridCellRenderer<DonorMatrixModel> totalODARenderer;
	private static GridCellRenderer<DonorMatrixModel> percentODARenderer;

	private static GridCellRenderer<DonorMatrixModel> htmlRenderer;
	private static final NumberFormat number = NumberFormat.getFormat("0.00");
	
	private static GridCellRenderer<DonorMatrixModel> recipientRenderer;
	
	private static ADAMSelectedDataset selectedDataset;

	
	private static String darkRed = "#96302E";
	
	public static Grid<DonorMatrixModel> createDonorMatrix(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> models) {

		final OLAPParametersVo params = vo.getOlapParametersVO();
		
		selectedDataset = vo.getSelectedDataset();
		
		//set Grid renderers
		
		 recipientRenderer = new GridCellRenderer<DonorMatrixModel>() {   
			  
		      private boolean init;   
		  
		      public Object render(final DonorMatrixModel model, String property, ColumnData config, final int rowIndex,   
		          final int colIndex, ListStore<DonorMatrixModel> store, Grid<DonorMatrixModel> grid) {   
		        if (!init) {   
		          init = true;   
		       /*   grid.addListener(Events.ColumnResize, new Listener<GridEvent<DonorMatrixModel>>() {   
		  
		            public void handleEvent(GridEvent<DonorMatrixModel> be) {   
		              for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {   
		                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null  
		                    && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {   
		                  ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);   
		                }   
		              }   
		            }   
		          });   */ 
		        }  
		  
		        HorizontalPanel panel = new HorizontalPanel();
		        panel.setSpacing(2);
		        //panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		       // panel.setWidth(100);
		        
		        /* ClickHtml recipientName = new ClickHtml();
		        recipientName.setHtml("<span style='text-decoration: underline; cursor: pointer;'>"+model.getRecipient()+"</span>");
		        recipientName.setTitle(BabelFish.print().clickToOpenADAMView()+ " " + model.getRecipient());
		        recipientName.addListener(Events.OnClick, ADAMDonorMatrixController.openADAMCountryView(model.getRecipientCode(), model.getRecipient()));
		        panel.add(recipientName);*/
		        panel.add(new Html(model.getRecipient()));
		        List<Double> faoSectorValues = new ArrayList<Double>();
		    	
		        for (int i = 0; i < params.getXLabels().size(); i++) {
		        	if(model.get("totalOda"+i+"fao")!=null){
		        		double val = (Double)model.get("totalOda"+i+"fao");
		        		if(val!=0.00) {
		        			faoSectorValues.add(val);
		        			break; // just need to know that there is at least 1 value for the faoSectorValues.isEmpty() test
		        		}
		        	}
		        }
		    	if(!faoSectorValues.isEmpty()){
		    		ClickHtml moreInfo = new ClickHtml();
		    		moreInfo.setHtml("<span style='text-decoration: underline; cursor: pointer; color:blue;'>[view sub-sectors] </span>");
		    		moreInfo.setTitle("Click to view FAO Sectors breakdown for "+ model.getRecipient());
		    		moreInfo.addListener(Events.OnClick, ADAMDonorMatrixController.showFaoSectorView(vo, model.getRecipientCode(), model.getRecipient(), models));
		    		
		    		ClickHtml disclaimer = new ClickHtml();
		    		disclaimer.setHtml("<a class='general-link' href='adam-docs/adam_fao_sector_breakdown.pdf' target='_blank'><img src='adam-images/info.gif' border='0'></a>");
		    		
		    		panel.add(moreInfo);
		    		panel.add(disclaimer);
		    		
		    		
		    	}
		        
		        /*Button b = new Button((String) model.get(property), new SelectionListener<ButtonEvent>() {   
		          @Override  
		          public void componentSelected(ButtonEvent ce) {   
		        	//  DonorMatrixController.openADAMView(model.getRecipientCode(), model.getRecipient());
		        	  
		             //Info.display(model.getRecipient(), "<ul><li>" + model.getRecipientCode() + "</li></ul>");   
		          }   
		        });   
		        b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);   
		       // b.setWidth(200);   
		        b.setToolTip(BabelFish.print().clickForMoreInformation());   
		       */
		        return panel;   
		      }   
		    };  
		   
		   /*** percentODARenderer = new GridCellRenderer<DonorMatrixModel>() {
			      public String render(DonorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
			          ListStore<DonorMatrixModel> store, Grid<DonorMatrixModel> grid) {

			    	if(model.get(property)!=null){
			        double val = (Double)model.get(property);
			          return number.format(val);
			    	}
			    	else
			    		return "";
			      }
			    };***/

		/*totalODARenderer = new GridCellRenderer<DonorMatrixModel>() {
		      public String render(DonorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
		          ListStore<DonorMatrixModel> store, Grid<DonorMatrixModel> grid) {

		    	if(model.get(property)!=null){
		        double val = (Double)model.get(property);
		        String percentProperty = property+"percent";

		    	double percent = (Double)model.get(percentProperty);
		        String style = "white";

		        if(percent == 100.00 || percent == 100) {
		        	style = "yellow";
		        }	
		        else if(percent >  50.00) {
		        	style = "#FFA500"; //orange
		        }	
		        	return "<span style='background-color:" + style + ";bgcolor:' title='"+percent+"% of ODA on Production Sectors'>" + number.format(val) + "</span>";
		    	}
		    	else
		    		return "";
		      }
		    };*/

		    totalODARenderer = new GridCellRenderer<DonorMatrixModel>() {
			      public String render(DonorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
			          ListStore<DonorMatrixModel> store, Grid<DonorMatrixModel> grid) {

			    	if(model.get(property)!=null){
			        double val = (Double)model.get(property);
			        
			          return "<span>" + number.format(val) + "</span>";
			         // return "<span>" + val + "</span>";
			    	}
			    	else
			    		return "";
			      }
			    };
			    
		/**   htmlRenderer = new GridCellRenderer<DonorMatrixModel>() {

		        private boolean init;

		        public Object render(final DonorMatrixModel model, String property, ColumnData config, final int rowIndex,
		            final int colIndex, ListStore<DonorMatrixModel> store, Grid<DonorMatrixModel> grid) {
		          if (!init) {
		            init = true;
		            grid.addListener(Events.ColumnResize, new Listener<GridEvent<DonorMatrixModel>>() {

		              public void handleEvent(GridEvent<DonorMatrixModel> be) {
		                for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
		                  if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
		                      && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
		                    ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);
		                  }
		                }
		              }
		            });
		          }

		          Html url = new Html("");
		          if((String) model.get(property)!=null && (String) model.get(property)!=""){
		        	  url.setHtml("<a href='"+(String) model.get(property)+"' target='_blank' title='"+BabelFish.print().clickToOpen()+" "+(String) model.get(property)+"'><img src='images/geoIcon.png'/></a>");
		          }

		          return url;
		        }
		      };**/
		      
		     


		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		//final CheckBoxSelectionModel<DonorMatrixModel> sm = new CheckBoxSelectionModel<DonorMatrixModel>();   
		//configs.add(sm.getColumn());   

		
			
		/**XTemplate template = XTemplate.create(getTemplate());
		 
		RowExpander expander = new RowExpander();
		expander.setTemplate(template);
		configs.add(expander);   **/

		
		configs = createColumns(params, configs);
		configs = createSumFaoSectorsColumn(params, configs);
		configs = createSumTotalODAColumn(params, configs);
		
		//Info.display("configs size", String.valueOf(configs.size()));  
		ColumnModel cm = new ColumnModel(configs);

		cm = createXHeaderRows(vo, models, params, cm);

		if(models.size() > 1)
			cm = createAggregationSummaryRow(params,cm);


	    final ListStore<DonorMatrixModel> store = new ListStore<DonorMatrixModel>();
	    store.add(models);

	   

	    final Grid<DonorMatrixModel> grid = new Grid<DonorMatrixModel>(store, cm);
	    
	    if(store.getCount() < 2)
			grid.setHeight(300);
		else
		  grid.setAutoHeight(true);
	    
	   // grid.addPlugin(expander);
	  
		
	 //  grid.setStyleAttribute("borderTop", "none");  
	 //  grid.getView().setForceFit(true);  
	   
		
	   grid.setBorders(false);  
	   //grid.setAutoExpandColumn("name");
	  // grid.getColumnModel().setColumnWidth(2, 100);
	  // grid.getColumnModel().setColumnWidth(2, 150);
	    
	   // grid.getView().setForceFit(true);
	   // grid.setStyleAttribute("borderTop", "none");
	    //grid.setAutoExpandColumn("name");
	   // grid.setBorders(false);
	   // grid.setColumnReordering(true);   
	   // grid.setStripeRows(true);
	   // grid.setHeight(400);
	    //grid.setColumnLines(true);
	   // grid.addPlugin(filters);
	  
	    


		return grid;
	}

	private static List<ColumnConfig> createColumns(OLAPParametersVo params, List<ColumnConfig> configs){

		 String zLabel = params.getZLabel();
		 String wLabel = params.getWLabel();

		 //Column config for Z
		 ColumnConfig column = new ColumnConfig();
		 column.setId("name");
		 column.setHeader("Recipient Country");   //"Recipient Country" = z label
		 column.setWidth(190);
		 column.setRenderer(recipientRenderer);   
		 configs.add(column);
		

		 //Column Config for W
		/** if (wLabel!=null && !params.getWLabels().isEmpty()) {
			 column = new ColumnConfig();
			 column.setId("KeySOs");
			 column.setHeader(wLabel);   //Key SOs and ORs = w label
			 column.setWidth(100);
			 configs.add(column);
	     }**/

		 configs = createYColumns(params, configs);

		 
		 return configs;
	}


	private static List<ColumnConfig> createSumFaoSectorsColumn(OLAPParametersVo params, List<ColumnConfig> configs){

		ColumnConfig column = new ColumnConfig("totalFAOSum", "Sum of FAO Sectors", 80);
	    column.setAlignment(HorizontalAlignment.RIGHT);
	    column.setNumberFormat(number);
	    configs.add(column);

	    return configs;
	}
	

	private static List<ColumnConfig> createSumTotalODAColumn(OLAPParametersVo params, List<ColumnConfig> configs){

		ColumnConfig column = new ColumnConfig("totalOdaSum", "Sum of Total ODA", 80);
	    column.setAlignment(HorizontalAlignment.RIGHT);
	    column.setNumberFormat(number);
	    configs.add(column);

	    return configs;
	}


	private static ColumnModel createAggregationSummaryRow(OLAPParametersVo params, ColumnModel cm){

		final AggregationRowConfig<DonorMatrixModel> sum = new AggregationRowConfig<DonorMatrixModel>();
	    sum.setHtml("name", "Total");
	   
		final AggregationRowConfig<DonorMatrixModel> average = new AggregationRowConfig<DonorMatrixModel>();
		average.setHtml("name", "Average");

		final AggregationRowConfig<DonorMatrixModel> maximum = new AggregationRowConfig<DonorMatrixModel>();
		maximum.setHtml("name", "Maximum");


	    final AggregationRowConfig<DonorMatrixModel> minimum = new AggregationRowConfig<DonorMatrixModel>();
	    minimum.setHtml("name", "Minimum");


	    /// needs to be done for each totalODE
	    if (params.getYLabels().size() > 0) {

	    	for (int i = 0; i < params.getXLabels().size(); i++) {

	    		sum.setSummaryType("totalOda"+i+"fao", SummaryType.SUM);
	    		sum.setRenderer("totalOda"+i+"fao", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	  return "<font color='blue'>"+number.format(value.doubleValue())+"</font>"; 
				    	 
					      }
					    });

	    		sum.setSummaryType("totalOda"+i+"all", SummaryType.SUM);
	    		sum.setRenderer("totalOda"+i+"all", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
					        
				    	  return "<font color='green'>"+number.format(value.doubleValue())+"</font>";
				    	 
					      }
					    });
	    		
	    		
	    		
				average.setSummaryType("totalOda"+i+"fao", SummaryType.AVG);
				average.setRenderer("totalOda"+i+"fao", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	    return number.format(value.doubleValue());
					       
					      }
					    });
				
				average.setSummaryType("totalOda"+i+"all", SummaryType.AVG);
				average.setRenderer("totalOda"+i+"all", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	  return number.format(value.doubleValue());
					        
					      }
					    });


				maximum.setSummaryType("totalOda"+i+"fao", SummaryType.MAX);
				maximum.setRenderer("totalOda"+i+"fao", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	  return number.format(value.doubleValue());
					        
					      }
					    });
				
				maximum.setSummaryType("totalOda"+i+"all", SummaryType.MAX);
				maximum.setRenderer("totalOda"+i+"all", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	  return number.format(value.doubleValue());
					       
					      }
					    });

				minimum.setSummaryType("totalOda"+i+"fao", SummaryType.MIN);
				minimum.setRenderer("totalOda"+i+"fao", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	  return number.format(value.doubleValue());
					       
					      }
					    });
				
				minimum.setSummaryType("totalOda"+i+"all", SummaryType.MIN);
				minimum.setRenderer("totalOda"+i+"all", new AggregationRenderer<DonorMatrixModel>() {
				      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
				    	  return number.format(value.doubleValue());
					       
					      }
					    });
	      }
	    	
	    	  sum.setSummaryType("totalOdaSum", SummaryType.SUM);
	  		  sum.setRenderer("totalOdaSum", new AggregationRenderer<DonorMatrixModel>() {
	  		      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
	  		    	return "<font color='green'>"+number.format(value.doubleValue())+"</font>";    
	  		    	
	  			      }
	  			    });
	  		  
	  		  
	  		  sum.setSummaryType("totalFAOSum", SummaryType.SUM);
	  		  sum.setRenderer("totalFAOSum", new AggregationRenderer<DonorMatrixModel>() {
	  		      public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
	  			        
	  		    	return "<font color='blue'>"+number.format(value.doubleValue())+"</font>";
	  		    	
	  			      }
	  			    });
	    	
	   }

	  
		
		
	    cm.addAggregationRow(sum);
	    cm.addAggregationRow(average);
	    cm.addAggregationRow(maximum);
	 //   cm.addAggregationRow(minimum);

	    return cm;
	}

	private static List<ColumnConfig> createYColumns(OLAPParametersVo params, List<ColumnConfig> configs){

		//DONT SHOW FOR DONOR MATRIX Y-LABEL
		/*if (params.getYLabels().size() > 0) {
			int yLabelSpan = params.getXLabels().keySet().size() * params.getYLabels().keySet().size();
			int colIndex = 0;
			if (params.getWLabels().isEmpty()) {
				colIndex = 1;
			} else if (!params.getWLabels().isEmpty()) {
				colIndex = 2;
			}

			// Y Label Header (Row2)
			String yLabel = params.getYLabel();
			cm.addHeaderGroup(2, colIndex, new HeaderGroupConfig(yLabel, 1, yLabelSpan));
		}*/

		// add Y values (if any)



       
		

		if (params.getYLabels().size() > 0) {
		
			Iterator<Map.Entry<String, String>> yiterator = params.getYLabels().entrySet().iterator();
			
			   for (int i = 0; i < params.getXLabels().size(); i++) {

				   //for (int j = 0; j < params.getYLabels().size(); j++) {
				   for (String secondaryHeader: params.getYLabels().keySet()) {
					   
				    //map.Entry<String, String> item	= yiterator.next();
				    //final String secondaryHeader = item.getValue();
				
					if(secondaryHeader.contains("FAO")) {

					ColumnConfig column = new ColumnConfig("totalOda"+i+"fao", secondaryHeader, 70);
					column.setAlignment(HorizontalAlignment.RIGHT);
				    column.setRenderer(totalODARenderer);
				    configs.add(column);
					}
				    else if(secondaryHeader.contains("Total")){
						ColumnConfig column = new ColumnConfig("totalOda"+i+"all", secondaryHeader, 70);
					   column.setAlignment(HorizontalAlignment.RIGHT);
					    column.setRenderer(totalODARenderer);   
					    configs.add(column);
					} /**else if(secondaryHeader.contains("Web")){
						ColumnConfig column = new ColumnConfig("webPage"+i, secondaryHeader, 35);
						column.setToolTip("Link to relevant resource partner information in the country");
					    column.setAlignment(HorizontalAlignment.RIGHT);
					    column.setRenderer(htmlRenderer);
					    column.setHidden(true); //hidden by default
					    configs.add(column);
					   
					}**/
					/*else if(secondaryHeader.contains("Production")){
						ColumnConfig column = new ColumnConfig("totalOda"+i+"percent", "%", 50);
					    column.setAlignment(HorizontalAlignment.RIGHT);
					    column.setRenderer(percentODARenderer);   
					    configs.add(column);
					}*/

			}


		}
		}

		return configs;
	}


	private static ColumnModel  createXHeaderRows(ADAMDonorMatrixVO vo, List<DonorMatrixModel> matrixModels, OLAPParametersVo params, ColumnModel cm) {

		// 4 dimensions
		//   ROW 1 = TITLE
		// X = row 1 e.g. donors
		// Y = row 2 e.g. assistancetype
		// Z = column 1	e.g recipient	= COLUMN 1
		// W = column 2 e.g. Key SOS = COLUMN 2

		// Totals ODA = last column

		// X Parameter label
		int xLabelSpan = params.getXLabels().keySet().size(); //colspan
		int colIndex = 0;//1;
		if (params.getWLabels().isEmpty()) {
			colIndex = 1;//2;
		} else if (!params.getWLabels().isEmpty()) {
			colIndex = 2;//3;
		}


		//Y Labels
		int yLabelSpan = params.getYLabels().keySet().size();

		int yLabelSize = params.getYLabels().size();
		int xLabelSize = params.getXLabels().size();


		//int xLabelsHeaderColSpan = (yLabelSize -1) * xLabelSize; //* 2; // hiding production
		int xLabelsHeaderColSpan = (yLabelSize) * xLabelSize; //* 2; // showing production

		//int xLabelColSpan = 3;//(yLabelSpan - params.getYLabels().size())/2;
		int xLabelColSpan = 2; //3 params.getYLabels().size() linked to changing Y items
		// Info.display(" y label size = "+yLabelSize, "col span x = "+xLabelsHeaderColSpan);

		// X Label Header (Row 0)
		 
		 String years = "";
		/* for(OLAPFilterVo filter: params.getFilters()) {
			 for (String value: filter.getDimensionMap().keySet()) {
				 years += value.substring(0,4);
			 }
		 }*/
		 
		 OLAPFilterVo filter =  params.getFilters().get(0);
		 
			// for(OLAPFilterVo filter: params.getFilters().get(0)) {
				 for (String value: filter.getDimensionMap().keySet()) {
					 years += value.substring(0,4);
				 }
			// }
			
				 
		//String xLabel = "ODA Commitments to Production Sectors  -  Food Security assistance  - Humanitarian Aid in "+years + " (USD Milions)*"; //params.getXLabel();
		//cm.addHeaderGroup(0, colIndex, new HeaderGroupConfig(xLabel, 1, xLabelsHeaderColSpan));

		 
		//Build Headers (not sorted labels for Global view)
		if(params.getSortedXLabels()!= null && params.getSortedXLabels().size() > 0) {
			Iterator<String> iterator = params.getSortedXLabels().iterator();			
			return buildHeaderTree(vo, matrixModels, colIndex, iterator, params.getxCodes(), xLabelColSpan, cm);
		}	
		else {
			//Global
			return buildHeaderMap(vo, matrixModels, colIndex, params.getXLabels(), params.getxCodes(), xLabelColSpan, cm);
		}	
		
		//return cm;
	}


	private static ColumnModel buildHeaderMap(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, int colIndex, Map<String, String> xlabels, Map<String, String> xcodes, int xLabelColSpan, ColumnModel cm){
		
		int c = colIndex;
		
		int t = 0;
		  
		Iterator<Map.Entry<String, String>> iterator = xlabels.entrySet().iterator();
		for (int i = 0; i < xlabels.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();
			
			//final String code = entry.getKey();
			final String header = entry.getValue();
			String formattedHeader = "<font color='"+darkRed+"'>"+header+"</font>";//formatDonorHeader(vo.getDonorsDacMembership(),header,code);
			
			/**String formattedHeader ="";
			
			if(vo.getDonorsDacMembership()!=null && !vo.getDonorsDacMembership().isEmpty()){   
				Iterator<Map.Entry<String, String>> iteratorp = vo.getDonorsDacMembership().entrySet().iterator();
				
				for (int p = 0; p < vo.getDonorsDacMembership().size();p++) {
					Map.Entry<String, String> entryp =iteratorp.next();
					final String codep = entryp.getKey();
					final String is_dac = entryp.getValue();
					//System.out.println("codep = " + codep +": is_dac "+is_dac);
				}
				 if (vo.getDonorsDacMembership().get(code)!=null) {
				   if(vo.getDonorsDacMembership().get(code).equalsIgnoreCase("true"))
					 formattedHeader = "<font color='blue'>"+header+" [DAC]</font>";
	               else
	            	 formattedHeader = "<font color='green'>"+header+" [NON-DAC]</font></span>";	
			     } else
			    	 formattedHeader = header; 
			}**/
		
				//int t = i+;
				
				
				/*Button donor = new Button(formattedHeader, new SelectionListener<ButtonEvent>() {
   		            @Override
   		            public void componentSelected(ButtonEvent ce) {
   		             //Info.display(header, "Open Donor Profile for "+header);
   		              ADAMDonorMatrixController.openDonorProfileView(vo, matrixModels, code, header);
   			        	 
   		            // com.google.gwt.user.client.Window.open("adamObjects/ADAM_DONOR_PROFILE_"+code+".html", "_blank","");
   		             
   		            }
   		          });
   		          donor.setToolTip("Click to view <b>"+formattedHeader +"</b> profile");
   		          donor.setWidth(110);*/
   		         // donor.setStylePrimaryName("matrix-x-btn-text");
   		        //  donor.setContextMenu(new Menu());
   		     
   		          
   		    // System.out.println("row = 0"+ "col = "+(i+t+c) + " donor "+header + "c :"+c+" :t"+t+" :i "+i);
   		   //  System.out.println("row = 0"+ "col = "+(c+i) + " donor "+header + "c :"+c);
      		  
   		     
                //cm.addHeaderGroup(0, i+t+c, new HeaderGroupConfig(donor, 1, xLabelColSpan)); // 3 y items
   		     
   		    cm.addHeaderGroup(0, c+i, new HeaderGroupConfig(formattedHeader, 1, xLabelColSpan)); //2 y items

		//  for (int i = colIndex; i< params.getXLabels().keySet().size(); i++) {

	    	//final String header = params.getXLabels()..iterator().next();


		//	if (params.getYLabels().keySet().isEmpty() || params.getY().equals(""))
			//	yLabelSpan = 1;



			

		  c++;
		  
		 t++;
		  
		}

			return cm;
	}
	
	private static ColumnModel buildHeaderTree(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, int colIndex, Iterator<String> treeIterator, Map<String, String> xcodes, int xLabelColSpan, ColumnModel cm){
	    			
		int c = colIndex;
		
		int t = 0;
		  
			for (int i = 0; i < xcodes.size(); i++) {
				final String header = treeIterator.next();
				//String formattedHeader="";
				//int t = i+;
				
				//final String code = xcodes.get(header);
				String formattedHeader = "<font color='"+darkRed+"'>"+header+"</font>";;//formatDonorHeader(vo.getDonorsDacMembership(),header,code);
				
				
				/*if(vo.getDonorsDacMembership()!=null && !vo.getDonorsDacMembership().isEmpty()){   
					
					Iterator<Map.Entry<String, String>> iteratorp = vo.getDonorsDacMembership().entrySet().iterator();
					
					for (int p = 0; p < vo.getDonorsDacMembership().size();p++) {
						Map.Entry<String, String> entryp =iteratorp.next();
						final String codep = entryp.getKey();
						final String is_dac = entryp.getValue();
						System.out.println("codep = " + codep +": is_dac "+is_dac);
					}
					
					 if (vo.getDonorsDacMembership().get(code)!=null) {
					   if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS) && vo.getDonorsDacMembership().get(code).equalsIgnoreCase("true"))
						   formattedHeader = "<font color='blue'>"+header+"</font>";
				       else if (ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.AID_DATA) && vo.getDonorsDacMembership().get(code).equalsIgnoreCase("false"))
				    	   formattedHeader = "<font color='green'>"+header+"</font></span>";	
				       else if (ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS_AID_DATA) && vo.getDonorsDacMembership().get(code).equalsIgnoreCase("false"))
				    	   formattedHeader = "<font color='blue'>"+header+" [Dac]</font>";
				       else if (ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS_AID_DATA) && vo.getDonorsDacMembership().get(code).equalsIgnoreCase("false"))
				    	   formattedHeader = "<font color='green'>"+header+" [Non-Dac]</font>";
				     } else
				    	 formattedHeader = header; 
				}*/
				
				/*
				Button donor = new Button(formattedHeader, new SelectionListener<ButtonEvent>() {
   		            @Override
   		            public void componentSelected(ButtonEvent ce) {
   		             // Info.display(header, "Open Donor Profile for "+header + " code = "+xItem.getKey());
   		            	
   		              ADAMDonorMatrixController.openDonorProfileView(vo, matrixModels, code, header);
   			        	 
   		            // com.google.gwt.user.client.Window.open("adamObjects/ADAM_DONOR_PROFILE_"+code+".html", "_blank","");
   		             
   		            }
   		          });
   		          donor.setToolTip("Click to view <b>"+formattedHeader +"</b> profile");
   		          donor.setWidth(110);*/
   		        //  donor.setContextMenu(new Menu());
   		     
   		          
   		    // System.out.println("row = 0"+ "col = "+(i+t+c) + " donor "+header + "c :"+c+" :t"+t+" :i "+i);
   		    // System.out.println("row = 0"+ "col = "+(c+i) + " donor "+header + "c :"+c+" :params.getYLabels().size()"+params.getYLabels().size());
      		  
   		     
              //  cm.addHeaderGroup(0, i+t+c, new HeaderGroupConfig(donor, 1, xLabelColSpan)); // 3 y items
   		     
   		     cm.addHeaderGroup(0, c+i, new HeaderGroupConfig(formattedHeader, 1, xLabelColSpan)); //2 y items

		//  for (int i = colIndex; i< params.getXLabels().keySet().size(); i++) {

	    	//final String header = params.getXLabels()..iterator().next();


		//	if (params.getYLabels().keySet().isEmpty() || params.getY().equals(""))
			//	yLabelSpan = 1;



			

		  c++;
		  
		 t++;
		  
		}
  return cm;
	}
	private static ColumnModel  createXHeaderRowsOriginal(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, OLAPParametersVo params, ColumnModel cm) {

		// 4 dimensions
		//   ROW 1 = TITLE
		// X = row 1 e.g. donors
		// Y = row 2 e.g. assistancetype
		// Z = column 1	e.g recipient	= COLUMN 1
		// W = column 2 e.g. Key SOS = COLUMN 2

		// Totals ODA = last column

		// X Parameter label
		int xLabelSpan = params.getXLabels().keySet().size(); //colspan
		int colIndex = 2;
		if (params.getWLabels().isEmpty()) {
			colIndex = 3;
		} else if (!params.getWLabels().isEmpty()) {
			colIndex = 4;
		}


		//Y Labels
		int yLabelSpan = params.getYLabels().keySet().size();

		int yLabelSize = params.getYLabels().size();
		int xLabelSize = params.getXLabels().size();


		//int xLabelsHeaderColSpan = (yLabelSize -1) * xLabelSize; //* 2; // hiding production
		int xLabelsHeaderColSpan = (yLabelSize) * xLabelSize; //* 2; // showing production

		//int xLabelColSpan = 3;//(yLabelSpan - params.getYLabels().size())/2;
		int xLabelColSpan = 3; //params.getYLabels().size() linked to changing Y items
		// Info.display(" y label size = "+yLabelSize, "col span x = "+xLabelsHeaderColSpan);

		// X Label Header (Row 0)
		 
		 String years = "";
		 for(OLAPFilterVo filter: params.getFilters()) {
			 for (String value: filter.getDimensionMap().keySet()) {
				 years += value.substring(0,4);
			 }
		 }
		 
		//String xLabel = "ODA Commitments to Production Sectors  -  Food Security assistance  - Humanitarian Aid in "+years + " (USD Milions)*"; //params.getXLabel();
		//cm.addHeaderGroup(0, colIndex, new HeaderGroupConfig(xLabel, 1, xLabelsHeaderColSpan));



		 //In Loop
		// X values	(Row 1)
		// int c = colIndex;

		//Iterator<Map.Entry<String, String>> iterator2 = params.getXLabels().entrySet().iterator();

		Iterator<String> iterator = params.getSortedXLabels().iterator();
		
	    	
	    //Iterator<String> iterator = params.getSortedXLabels().iterator();
		
		int c = colIndex;
		
		int t = 0;
		  
			for (int i = 0; i < params.getSortedXLabels().size(); i++) {
				final String header = iterator.next();
				
				//int t = i+;
				
				final String code = params.getxCodes().get(header);
				
				Button donor = new Button(header, new SelectionListener<ButtonEvent>() {
    		            @Override
    		            public void componentSelected(ButtonEvent ce) {
    		             // Info.display(header, "Open Donor Profile for "+header + " code = "+xItem.getKey());
    		              ADAMDonorMatrixController.openDonorProfileView(vo, matrixModels, code, header);
    			        	 
    		            // com.google.gwt.user.client.Window.open("adamObjects/ADAM_DONOR_PROFILE_"+code+".html", "_blank","");
    		             
    		            }
    		          });
    		          donor.setToolTip("Click to view <b>"+header +"</b> profile");
    		        //  donor.setContextMenu(new Menu());
    		     
    		          
    		    // System.out.println("row = 0"+ "col = "+(i+t+c) + " donor "+header + "c :"+c+" :t"+t+" :i "+i);
    		    // System.out.println("row = 0"+ "col = "+(c+i) + " donor "+header + "c :"+c+" :params.getYLabels().size()"+params.getYLabels().size());
       		  
    		     
                 cm.addHeaderGroup(0, i+t+c, new HeaderGroupConfig(donor, 1, xLabelColSpan)); // 3 y items
    		     
    		    // cm.addHeaderGroup(0, c+i, new HeaderGroupConfig(donor, 1, xLabelColSpan)); //2 y items

		//  for (int i = colIndex; i< params.getXLabels().keySet().size(); i++) {

	    	//final String header = params.getXLabels()..iterator().next();


		//	if (params.getYLabels().keySet().isEmpty() || params.getY().equals(""))
			//	yLabelSpan = 1;



			

		  c++;
		  
		 t++;
		  
		}


	   /*  for (int i = colIndex; i< params.getXLabels().keySet().size(); i++) {

	    	final String header = params.getXLabels().keySet().iterator().next();


		//	if (params.getYLabels().keySet().isEmpty() || params.getY().equals(""))
			//	yLabelSpan = 1;

			 Info.display(header, "<ul><li> Open Profile</li></ul>"+i);


			Button donor = new Button(header, new SelectionListener<ButtonEvent>() {
		            @Override
		            public void componentSelected(ButtonEvent ce) {
		              Info.display(header, "<ul><li> Open Profile</li></ul>");
		            }
		          });
		          donor.setToolTip("Click to view profile");

		   cm.addHeaderGroup(1, i, new HeaderGroupConfig(donor, 1, xLabelColSpan));

		   i+=2;
		}*/

	      //cm.addHeaderGroup(1,1, new HeaderGroupConfig(new Button("1"), 1, xLabelColSpan));
	      //cm.addHeaderGroup(1,3, new HeaderGroupConfig(new Button("2"), 1, xLabelColSpan));
	      //cm.addHeaderGroup(1,5, new HeaderGroupConfig(new Button("3"), 1, xLabelColSpan));
	      //cm.addHeaderGroup(1,7, new HeaderGroupConfig(new Button("4"), 1, xLabelColSpan));



			 //XTemplate template = XTemplate.create("<br/><p><b>FAO Key SOs (Strategic Objectives) and ORs (Organizational Results) as identified in CPF ({refCPFDate}):</b><br/> <br/> <b>Key SOs:<b> <a href='#' title='{keySOsDesc}'>{keySOs}</a> <br/> <b>Key ORs:<b> <a href='#' title='{keyORsDesc}'>{keyORs}</a> <br/> <b>Comments:<b> {keyComment}</p>");
			
		// return result
		return cm;
	}

	private static String formatDonorHeader(Map<String, String> donorDacMembershipMap, String donorName, String donorCode){
     
		String formattedHeader = "";
		
		// Dark Red = Dac
		// Orange = Non-Dac
		
		String darkRed = "#96302E";
		String grey = "#433F40";

		if(donorDacMembershipMap!=null && !donorDacMembershipMap.isEmpty()){   
			
			String isDonorDac = donorDacMembershipMap.get(donorCode);
			
			 if (isDonorDac!=null) {
			   if(selectedDataset.equals(ADAMSelectedDataset.ADAM_CRS) && isDonorDac.equalsIgnoreCase("true"))
				   formattedHeader = "<font color='"+darkRed+"'>"+donorName+"</font>";
		       else if (selectedDataset.equals(ADAMSelectedDataset.AID_DATA) && isDonorDac.equalsIgnoreCase("false"))
		    	   formattedHeader = "<font color='"+grey+"'>"+donorName+"</font></span>";	
		       else if (selectedDataset.equals(ADAMSelectedDataset.ADAM_CRS_AID_DATA) && isDonorDac.equalsIgnoreCase("true"))
		    	   formattedHeader = "<font color='"+darkRed+"'>"+donorName+" [DAC]</font>";
		       else if (selectedDataset.equals(ADAMSelectedDataset.ADAM_CRS_AID_DATA) && isDonorDac.equalsIgnoreCase("false"))
		    	   formattedHeader = "<font color='"+grey+"'>"+donorName+" [Non-DAC]</font>";
		       else
		    	   formattedHeader = donorName;   
			 } else
		    	 formattedHeader = donorName; 
		}
		return formattedHeader;
	}
	
	 private static native String getTemplate() /*-{	
	 var html = [
	 '<br/><p><b>FAO Key SOs (Strategic Objectives) and ORs (Organizational Results) as matched with the country priorities</b>',
     '<tpl if="keyComment==\'\'">',
     ' ({refCPFDate}) </p><br/>',	
     '<table width=\'600px\' style=\'border-collapse: collapse; border: 1px solid #FFFFFF; background-color:#CCCCCC;\'><tr><td style=\'border: 1px solid #FFFFFF;\' valign=\'top\' align=\'center\'><b>Key SOs</b><br/> ({keySOs})<br/></td><td style=\'border: 1px solid #FFFFFF;\' valign=\'top\' align=\'center\'><b>Key ORs</b> <br/>({keyORs})<br/></td></tr><tr><td style=\'border: 1px solid #FFFFFF;\' valign=\'top\'><br/>{keySOsDesc}</td><td style=\'border: 1px solid #FFFFFF;\' valign=\'top\'><br/>{keyORsDesc}</td></tr></table>',
     '</tpl>',
	  '<tpl if="keyComment!=\'\'">',
     '</p><br/><p><b>{keyComment}</b></p>',
	 '</tpl>'     
    ];
    return html.join("");
  }-*/;
}
