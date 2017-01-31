package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.model.FAOSectorMatrixModel;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.grid.AggregationRenderer;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.google.gwt.i18n.client.NumberFormat;

public class ADAMFAOSectorMatrix {

	private static GridCellRenderer<FAOSectorMatrixModel> oRsRenderer;
	private static GridCellRenderer<FAOSectorMatrixModel> commitmentRenderer;
	private static GridCellRenderer<FAOSectorMatrixModel> disbursementRenderer;
//	private static GridCellRenderer<FAOSectorMatrixModel> percentCommitmentRenderer;
//	private static GridCellRenderer<FAOSectorMatrixModel> percentDisbursementRenderer;
	private static final NumberFormat number = NumberFormat.getFormat("0.000");


	public static Grid<FAOSectorMatrixModel> createFAOSectorMatrix(OLAPParametersVo params, List<FAOSectorMatrixModel> models, final String recipientCode) {

		//set Grid renderers

	    
		   oRsRenderer = new GridCellRenderer<FAOSectorMatrixModel>() {

		        public Object render(final FAOSectorMatrixModel model, String property, ColumnData config, final int rowIndex,
		            final int colIndex, ListStore<FAOSectorMatrixModel> store, Grid<FAOSectorMatrixModel> grid) {
		        	

                     ClickHtml ors = new ClickHtml();
                     StringBuilder link = new StringBuilder();
		        	 
		        	 if((String) model.get(property)!=null && (String) model.get(property)!=""){
		        		 
		        		 if(!model.get(property).toString().contains("available")) {
			        			MessageBox box = new MessageBox();
			        			box.setMinWidth(550);
			        			box.setModal(false);
					        	box.setIcon(MessageBox.INFO);
					        	box.setTitle("FAO Organizational Results (ORs) Descriptions");
					        	box.setMessage(model.getMappedORsDescription());
					        	link.append("<span style='text-decoration: underline; cursor: pointer;'><img src='images/information.png'/></span> ");
					        	ors.setTitle("Click to view descriptions");
					        	ors.addListener(Events.OnClick, ADAMDonorMatrixController.showORDescriptions(box));
			        	  }
		        		 
		        		  link.append((String) model.get(property));
		  
			         }
		         
		        	 ors.setHtml(link.toString());
		        	
		        	
						return ors; 
				
		        }
		      };
		      
		
		commitmentRenderer = new GridCellRenderer<FAOSectorMatrixModel>() {
			public Object render(FAOSectorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<FAOSectorMatrixModel> store, Grid<FAOSectorMatrixModel> grid) {


				if(model.get(property)!=null){
					double val = (Double)model.get(property);
					String style = val > 0 ? "#3668C2" : "";   //blue backgound if  > 0
					String color = val > 0 ? "white" : "#1D4589";   //text color

					String v = number.format(val);  
					Html value = new Html("<span>"+ v+"</span>");
					
					/*ClickHtml value = new ClickHtml();
					value.setHtml("<span "  
							+ " style='cursor: pointer; font-weight: bold;background:" + style + ";color:"+color+"'>"
							+ v+"</span>");
					value.setTitle("Click to view Projects");

					//Note: (String)model.get(property+"xKey") = donorCode

					value.addListener(Events.OnClick, ADAMDonorMatrixController.showProjects((String)model.get(property+"xKey"), recipientCode, model.getFAOSectorCode()));
*/
					return value; 
				}

				/**if(model.get(property)!=null){
			        double val = (Double)model.get(property);

			         ClickHtml value = new ClickHtml();
			         value.setHtml("<span>" + number.format(val) + "</span>");
			         value.setTitle("Click to view Projects");


			        value.addListener(Events.OnClick, DonorMatrixController. showProjects(donorCode, recipientCode, model.getFAOSectorCode()));

			         // return "<span>" + number.format(val) + "</span>";
			          return value;
			    	}**/
				else
					return "";
			}
		};

		disbursementRenderer = new GridCellRenderer<FAOSectorMatrixModel>() {
			public Object render(FAOSectorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<FAOSectorMatrixModel> store, Grid<FAOSectorMatrixModel> grid) {


				if(model.get(property)!=null){
					double val = (Double)model.get(property);
					String style = val > 0 ? "#3668C2" : "";   //blue backgound if  > 0
					String color = val > 0 ? "white" : "#1D4589";   //text color

					String v = number.format(val);  

					Html value = new Html("<span>"+ v+"</span>");
					/*ClickHtml value = new ClickHtml();
					value.setHtml("<span "  
							+ " style='cursor: pointer; font-weight: bold;background:" + style + ";color:"+color+"'>"
							+ v+"</span>");
					value.setTitle("Click to view Projects");
*/
					//Note: (String)model.get(property+"xKey") = donorCode

					//value.addListener(Events.OnClick, ADAMDonorMatrixController.showProjects((String)model.get(property+"xKey"), recipientCode, model.getFAOSectorCode()));
					return value; 
				}
				else
					return "";
			}
		};

	/***	percentCommitmentRenderer = new GridCellRenderer<FAOSectorMatrixModel>() {
			public Object render(FAOSectorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<FAOSectorMatrixModel> store, Grid<FAOSectorMatrixModel> grid) {

				if(model.get(property)!=null){
					double val = (Double)model.get(property);
					String v = number.format(val);  
					return v; 
				}
				else
					return "";
			}
		};

		percentDisbursementRenderer = new GridCellRenderer<FAOSectorMatrixModel>() {
			public Object render(FAOSectorMatrixModel model, String property, ColumnData config, int rowIndex, int colIndex,
					ListStore<FAOSectorMatrixModel> store, Grid<FAOSectorMatrixModel> grid) {

				if(model.get(property)!=null){
					double val = (Double)model.get(property);
					String v = number.format(val);  
					return v; 
				}
				else
					return "";
			}
		};

**/
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		configs = createColumns(params, configs);
		configs = createSumFaoSectorsColumn(params, configs);
		configs = createSumTotalODAColumn(params, configs);

		final ColumnModel cm = new ColumnModel(configs);
		createXHeaderRows(params, cm);
		
		if(models.size() > 1)
			createAggregationSummaryRow(params,cm);


		final  ListStore<FAOSectorMatrixModel> store = new ListStore<FAOSectorMatrixModel>();   
		store.add(models);
		// GWT.log("store count = "+String.valueOf(store.getCount()), null);
		// store.groupBy("name");   


		final Grid<FAOSectorMatrixModel> grid = new Grid<FAOSectorMatrixModel>(store, cm);
		grid.setBorders(false);
		if(store.getCount() < 2)
			grid.setHeight(300);
		else
		  grid.setAutoHeight(true);



		// final Grid<FAOSectorMatrixModel> grid = new Grid<FAOSectorMatrixModel>(store, cm);
		// grid.setView(view);   

		//grid.setSelectionModel(sm);   
		// grid.addPlugin(sm);   
		//grid.addPlugin(expander);


		//  grid.setStyleAttribute("borderTop", "none");  
		//  grid.getView().setForceFit(true);  


		// grid.setBorders(false);  
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


		/*  grid.addListener(Events.ViewReady, new Listener<ComponentEvent>() {
	        public void handleEvent(ComponentEvent be) {
	          grid.getStore().addListener(Store.Add, new Listener<StoreEvent<FAOSectorMatrixModel>>() {
	            public void handleEvent(StoreEvent<FAOSectorMatrixModel> be) {
	            	 doAutoHeight(cp, grid);
	            }
	          });
	          doAutoHeight(cp, grid);
	        }
	      });
	      grid.addListener(Events.ColumnResize, new Listener<ComponentEvent>() {
	        public void handleEvent(ComponentEvent be) {
	        	 doAutoHeight(cp, grid);
	        }
	      });
	      grid.getColumnModel().addListener(Events.HiddenChange, new Listener<ColumnModelEvent>() {
	        public void handleEvent(ColumnModelEvent be) {
	          doAutoHeight(cp, grid);
	        }
	      });*/

		return grid;
	}


	protected static void doAutoHeight(ContentPanel cp, Grid<FAOSectorMatrixModel> grid) {
		if (grid.isViewReady()) {
			cp.setHeight((grid.getView().getBody().isScrollableX() ? 19 : 0) + grid.el().getFrameWidth("tb")
					+ grid.getView().getHeader().getHeight() + cp.getFrameHeight()
					+ grid.getView().getBody().firstChild().getHeight());
		}
	}

	private static List<ColumnConfig> createColumns(OLAPParametersVo params, List<ColumnConfig> configs){

		String zLabel = params.getZLabel();
		String wLabel = params.getWLabel();

		//Column config for Z
		ColumnConfig column = new ColumnConfig();
		column.setId("faoSector");
		column.setHeader("FAO Related Sub-Sector");   //"FAO Sector" = z label
		column.setWidth(200);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("mappedORs");
		column.setHeader("FAO Organizational Results (ORs)");  
		column.setRenderer(oRsRenderer);	
		column.setWidth(200);
		column.setHidden(true);
		configs.add(column);


		configs = createYColumns(params, configs);


		return configs;
	}


	private static List<ColumnConfig> createSumFaoSectorsColumn(OLAPParametersVo params, List<ColumnConfig> configs){

		ColumnConfig column = new ColumnConfig("commitmentOdaSum", "Sum of ODA Commitments", 80);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setNumberFormat(number);
		configs.add(column);

		return configs;
	}


	private static List<ColumnConfig> createSumTotalODAColumn(OLAPParametersVo params, List<ColumnConfig> configs){

		ColumnConfig column = new ColumnConfig("disbursementOdaSum", "Sum of ODA Disbursement", 80);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setNumberFormat(number);
		configs.add(column);

		return configs;
	}


	private static ColumnModel createAggregationSummaryRow(OLAPParametersVo params, ColumnModel cm){

		final AggregationRowConfig<DonorMatrixModel> sum = new AggregationRowConfig<DonorMatrixModel>();
		sum.setHtml("faoSector", "Total");

		final AggregationRowConfig<DonorMatrixModel> average = new AggregationRowConfig<DonorMatrixModel>();
		average.setHtml("faoSector", "Average");

		final AggregationRowConfig<DonorMatrixModel> maximum = new AggregationRowConfig<DonorMatrixModel>();
		maximum.setHtml("faoSector", "Maximum");


		final AggregationRowConfig<DonorMatrixModel> minimum = new AggregationRowConfig<DonorMatrixModel>();
		minimum.setHtml("faoSector", "Minimum");


		/// needs to be done for each totalODE
		if (params.getYLabels().size() > 0) {

			for (int i = 0; i < params.getXLabels().size(); i++) {

				sum.setSummaryType("commitmentOda"+i, SummaryType.SUM);
				sum.setRenderer("commitmentOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {

						return "<font color='blue'>"+number.format(value.doubleValue())+"</font>";
					}
				});

				sum.setSummaryType("disbursementOda"+i, SummaryType.SUM);
				sum.setRenderer("disbursementOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {

						//  return "<font size='1px'>"+number.format(value.doubleValue())+"</font>";
						return "<font color='green'>"+number.format(value.doubleValue())+"</font>";
					}
				});



				average.setSummaryType("commitmentOda"+i, SummaryType.AVG);
				average.setRenderer("commitmentOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
						return number.format(value.doubleValue());
					}
				});

				average.setSummaryType("disbursementOda"+i, SummaryType.AVG);
				average.setRenderer("disbursementOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
						return number.format(value.doubleValue());
					}
				});


				maximum.setSummaryType("commitmentOda"+i, SummaryType.MAX);
				maximum.setRenderer("commitmentOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
						return number.format(value.doubleValue());
					}
				});

				maximum.setSummaryType("disbursementOda"+i, SummaryType.MAX);
				maximum.setRenderer("disbursementOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
						return number.format(value.doubleValue());
					}
				});

				minimum.setSummaryType("commitmentOda"+i, SummaryType.MIN);
				minimum.setRenderer("commitmentOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
						return number.format(value.doubleValue());
					}
				});

				minimum.setSummaryType("disbursementOda"+i, SummaryType.MIN);
				minimum.setRenderer("disbursementOda"+i, new AggregationRenderer<DonorMatrixModel>() {
					public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {
						return number.format(value.doubleValue());
					}
				});
			}

			sum.setSummaryType("commitmentOdaSum", SummaryType.SUM);
			sum.setRenderer("commitmentOdaSum", new AggregationRenderer<DonorMatrixModel>() {
				public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {

					//  return "<font size='1px'>"+number.format(value.doubleValue())+"</font>";
					return "<font color='green'>"+number.format(value.doubleValue())+"</font>";
				}
			});


			sum.setSummaryType("disbursementOdaSum", SummaryType.SUM);
			sum.setRenderer("disbursementOdaSum", new AggregationRenderer<DonorMatrixModel>() {
				public Object render(Number value, int colIndex, Grid<DonorMatrixModel> grid, ListStore<DonorMatrixModel> store) {

					//  return "<font size='1px'>"+number.format(value.doubleValue())+"</font>";
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

					if(secondaryHeader.contains("Commitment")) {

						ColumnConfig column = new ColumnConfig("commitmentOda"+i, secondaryHeader, 70);
						column.setAlignment(HorizontalAlignment.RIGHT);
						column.setRenderer(commitmentRenderer);
						configs.add(column);
					}
					else if(secondaryHeader.contains("Disbursement")){
						ColumnConfig column = new ColumnConfig("disbursementOda"+i, secondaryHeader, 80);
						column.setAlignment(HorizontalAlignment.RIGHT);
						column.setRenderer(disbursementRenderer);   
						configs.add(column);
					}/** else if(secondaryHeader.contains("% Commitment")){
						ColumnConfig column = new ColumnConfig("percentCommitment"+i, secondaryHeader, 35);
					    column.setAlignment(HorizontalAlignment.RIGHT);
					    column.setRenderer(percentCommitmentRenderer);   
						configs.add(column);
					   // column.setHidden(true); //hidden by default

					}else if(secondaryHeader.contains("% Disbursement")){
						ColumnConfig column = new ColumnConfig("percentDisbursement"+i, secondaryHeader, 50);
					    column.setAlignment(HorizontalAlignment.RIGHT);
					    column.setRenderer(percentDisbursementRenderer);   
					    configs.add(column);
					}**/

				}


			}
		}

		return configs;
	}


	private static ColumnModel  createXHeaderRows(OLAPParametersVo params, ColumnModel cm) {

		// 4 dimensions
		//   ROW 1 = TITLE
		// X = row 1 e.g. donors
		// Y = row 2 e.g. commitment/disbursement
		// Z = column 1	e.g sectors	= COLUMN 1
		// W = column 2 e.g. Key SOS = COLUMN 2

		// Totals ODA = last column

		// X Parameter label
		int xLabelSpan = params.getXLabels().keySet().size(); //colspan
		int colIndex = 1;
		if (params.getWLabels().isEmpty()) {
			colIndex = 2;
		} else if (!params.getWLabels().isEmpty()) {
			colIndex = 3;
		}


		//Y Labels
		int yLabelSpan = params.getYLabels().keySet().size();

		int yLabelSize = params.getYLabels().size();
		int xLabelSize = params.getXLabels().size();


		//int xLabelsHeaderColSpan = (yLabelSize -1) * xLabelSize; //* 2; // hiding production
		int xLabelsHeaderColSpan = (yLabelSize) * xLabelSize; //* 2; // showing production

		//int xLabelColSpan = 3;//(yLabelSpan - params.getYLabels().size())/2;
		int xLabelColSpan = 2; //int xLabelColSpan = 4; //params.getYLabels().size() linked to changing Y items
		// Info.display(" y label size = "+yLabelSize, "col span x = "+xLabelsHeaderColSpan);

		// X Label Header (Row 0)

		String years = "";

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
			return buildHeader(colIndex, iterator, params.getxCodes(), xLabelColSpan, cm);
		}	
		else {
			//Global
			return buildHeaderMap(colIndex, params.getXLabels(), params.getxCodes(), xLabelColSpan, cm);
		}	

	}


	private static ColumnModel buildHeaderMap(int colIndex, Map<String, String> xlabels, Map<String, String> xcodes, int xLabelColSpan, ColumnModel cm){

		int c = colIndex;

		int t = 0;
		
		//int j = 0;

		Iterator<Map.Entry<String, String>> iterator = xlabels.entrySet().iterator();
		for (int i = 0; i < xlabels.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();			
			final String code = entry.getKey();
			final String header = entry.getValue();

			Html donor = new Html(header);


			/*	Button donor = new Button(header, new SelectionListener<ButtonEvent>() {
   		            @Override
   		            public void componentSelected(ButtonEvent ce) {
   		              DonorMatrixController.openDonorProfileView(code, header);
   			        }
   		          });
   		          donor.setToolTip("Click to view <b>"+header +" profile</b>");*/

			//cm.addHeaderGroup(0, i+t+c+j, new HeaderGroupConfig(donor, 1, xLabelColSpan)); //4 y items	
			cm.addHeaderGroup(0, c+i, new HeaderGroupConfig(donor, 1, xLabelColSpan)); //2 y items

			c++;

			t++;
			
			//j++;

		}

		return cm;
	}

	private static ColumnModel buildHeader(int colIndex, Iterator<String> treeIterator, Map<String, String> xcodes, int xLabelColSpan, ColumnModel cm){

		int c = colIndex;

		int t = 0;

		for (int i = 0; i < xcodes.size(); i++) {
			final String header = treeIterator.next();

			//int t = i+;

			final String code = xcodes.get(header);

			Html donor = new Html(header);

			/*	Button donor = new Button(header, new SelectionListener<ButtonEvent>() {
   		            @Override
   		            public void componentSelected(ButtonEvent ce) {
   		              DonorMatrixController.openDonorProfileView(code, header);
   			        }
   		          });
   		          donor.setToolTip("Click to view <b>"+header +" profile</b>");
   		        //  donor.setContextMenu(new Menu());
			 */

			// System.out.println("row = 0"+ "col = "+(i+t+c) + " donor "+header + "c :"+c+" :t"+t+" :i "+i);
			// System.out.println("row = 0"+ "col = "+(c+i) + " donor "+header + "c :"+c+" :params.getYLabels().size()"+params.getYLabels().size());


			//  cm.addHeaderGroup(0, i+t+c, new HeaderGroupConfig(donor, 1, xLabelColSpan)); // 3 y items

			//GWT.log("column grp index: "+c+": i "+i + "("+c+i+")", null);

			cm.addHeaderGroup(0, c+i, new HeaderGroupConfig(donor, 1, xLabelColSpan)); //2 y items	


			//  for (int i = colIndex; i< params.getXLabels().keySet().size(); i++) {

			//final String header = params.getXLabels()..iterator().next();


			//	if (params.getYLabels().keySet().isEmpty() || params.getY().equals(""))
			//	yLabelSpan = 1;


			c++;

			t++;

		}
		return cm;
	}


}
