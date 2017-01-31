package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.amis.client.control.input.Month;
import org.fao.fenix.web.modules.amis.client.control.input.Year;
import org.fao.fenix.web.modules.amis.client.control.inputccbs.PagePanelController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.Book;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.amis.common.vo.Cell;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsToolTimeSeries;
import org.fao.fenix.web.modules.amis.common.vo.Page;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;

public class PagePanel extends Composite implements Book.UpdateListener {
	private PageDecoration decoration;
	private Page page;
	private CCBSStore store;
	private ColumnModel cm;
	private Grid grid;
	final boolean fieldNotNull[] = new boolean[3];
	boolean totalOtherUses = false;
	ComboBox<AMISCodesModelData> combo;
	Button mainSave;

	private static final NumberFormat numberFormat = NumberFormat
			.getFormat("#,##0.00");

	public PagePanel(PageDecoration decoration) {
		this.decoration = decoration;

		store = new CCBSStore(decoration);
		cm = makeColumnModel();
		grid = makeGrid();

		initComponent(grid);
		grid.mask();

	}

	public void load(Page page) {
		this.page = page;

		store.loadPage(page);
		page.getBook().addBookUpdateListener(this);
		grid.unmask();
	}

	public void unload() {
		store.unloadPage();
		grid.mask();
	}

	public void updateView() {
		store.updatePage();
	}

	public PageDecoration getDecoration() {
		return decoration;
	}

	public ComboBox<AMISCodesModelData> addFlagCombo() {
		ComboBox<AMISCodesModelData> comboFlag = new ComboBox<AMISCodesModelData>();
		comboFlag.setDisplayField("code");
		comboFlag.setWidth(70);
		comboFlag.setStore(CCBS.FLAGS);
		comboFlag.setTypeAhead(true);
		comboFlag.setTriggerAction(TriggerAction.ALL);
		// comboFlag.setValue(CCBS.FLAGS.getAt(0));
		return comboFlag;
	}

	// public void updateSaveButton(Button localSave)
	// {
	// int i= 0;
	// System.out.println("Before button save totalOtherUses: " +
	// totalOtherUses);
	// if(totalOtherUses)
	// {
	// System.out.println("Before button save totalOtherUses: if");
	// localSave.enable();
	// }
	// else
	// {
	// System.out.println("Before button save totalOtherUses: else");
	// for(i=0; i< fieldNotNull.length; i++)
	// {
	// if(!fieldNotNull[i])
	// {
	// break;
	// }
	// }
	// if(i==fieldNotNull.length)
	// {
	// localSave.enable();
	// }
	// else
	// {
	// localSave.disable();
	// }
	// }
	// }

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public static void showHideSelectionChangedListener(BookPanel bookPanel,
			int pagePanelNum, CCBSWindow ccbsWindow) {
		System.out
				.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 1!!!");
		Grid grid = bookPanel.getPagePanel(pagePanelNum).getGrid();
		// ColumnConfig colConf = grid.getColumnModel().getColumn(numCols+1);
		System.out
				.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 2!!!");
		ColumnConfig colConf;
		ColumnModel colMod = grid.getColumnModel();

		for (int i = 1; i < grid.getColumnModel().getColumnCount(); i++) {
			System.out
					.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: colMod.getColumnId(i) "
							+ colMod.getColumnId(i));
			// grid.getColumnModel().getColumn(i).setHidden(true);
			if ((i % 2) == 0) {
				if (!CCBS.isHide) {
					System.out
							.println("Class: PagePanel Function: showHideSelectionChangedListener Text: if(!CCBS.isHide)");
					// If the columns of the flags are not hidden... it must be
					// hidden
					colConf = grid.getColumnModel().getColumn(i);
					// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 3!!! Header "+
					// colConf.getHeader());
					// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 3!!! Width Before "+
					// colConf.getWidth());

					// colConf.setWidth(colConf.getWidth()-2);
					colConf.setRenderer(colConf.getRenderer());
					colConf.setWidth(2);
					// colConf.getRenderer().render(model, property, config,
					// rowIndex, colIndex, store, grid)er(model, property,
					// config, rowIndex, colIndex, store, grid);
					// colConf.setRenderer(new PageCellRenderer());
					// colConf.setHidden(true);
					// colMod.setColumnWidth(i, 0);
					// grid.getColumnModel().getColumnById("col_" +
					// i).setHidden(true);

					colMod.getColumn(i).setHidden(true);
					// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 3!!! Width After "+
					// colConf.getWidth());

				} else {
					// If the columns of the flags are hidden... it must be
					// shown
					colConf = grid.getColumnModel().getColumn(i);
					// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 3!!! Header "+
					// colConf.getHeader());
					// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 3!!! Width Before "+
					// colConf.getWidth());

					// colConf.setWidth(colConf.getWidth()-2);
					colConf.setRenderer(colConf.getRenderer());
					colConf.setWidth(20);
					// colConf.getRenderer().render(model, property, config,
					// rowIndex, colIndex, store, grid)er(model, property,
					// config, rowIndex, colIndex, store, grid);
					// colConf.setRenderer(new PageCellRenderer());
					// colConf.setHidden(true);
					// colMod.setColumnWidth(i, 0);
					// grid.getColumnModel().getColumnById("col_" +
					// i).setHidden(true);

					colMod.getColumn(i).setHidden(false);
					// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 3!!! Width After "+
					// colConf.getWidth());

				}
			}
		}
		if (!CCBS.isHide) {
			CCBS.isHide = true;
		} else {
			CCBS.isHide = false;
		}
		// ccbsWindow.getCenter().removeAll();
		// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 4!!!");
		ccbsWindow.getCenter().getLayout().layout();
		// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 5!!!");

		grid.reconfigure(grid.getStore(), grid.getColumnModel());
		// System.out.println("Class: PagePAnel Function: showHideSelectionChangedListener Text: Start 6!!!");
		// matrixGrid.reconfigure(matrixGrid.getStore(),
		// matrixGrid.getColumnModel())
		// this.grid.getColumnModel().getColumn(0).setWidth(0);
		//		
		// //
		// System.out.println("????????????????????Listener decoration.numCols() "+
		// decoration.numCols());
		// int numCol = decoration.numCols();
		// int numColToChangeValue = numCol-2;
		// int numColToChangeFlag = numCol-1;
		// String selected =
		// PagePanelController.changeMonthString(se.getSelectedItem().getMonthForecast());
		// if(selected!=null){
		// if((panel!=null)&&(panel.grid!=null))
		// {
		// CCBSStore store = (CCBSStore)panel.grid.getStore();
		// List<CCBSModel> modelList = (List<CCBSModel>)store.getModels();
		// for(CCBSModel model: modelList)
		// {
		// //System.out.println("Listener decoration.numCols() "+
		// decoration.numCols());
		// //
		// System.out.println("Listener model.get(col_+ numColToChangeValue+ FORECAST) before"+
		// model.get("col_"+ numColToChangeValue+ "_FORECAST"));
		// model.set("col_"+ numColToChangeValue+ "_FORECAST", selected);
		// //
		// System.out.println("Listener model.get(col_+ numColToChangeValue+ FORECAST) after"+
		// model.get("col_"+ numColToChangeValue+ "_FORECAST"));
		//						
		// //
		// System.out.println("Listener model.get(col_+ numColToChangeFlag+ FORECAST) before"+
		// model.get("col_"+ numColToChangeFlag+ "_FORECAST"));
		// model.set("col_"+ numColToChangeFlag+ "_FORECAST", selected);
		// //
		// System.out.println("Listener model.get(col_+ numColToChangeFlag+ FORECAST) after"+
		// model.get("col_"+ numColToChangeFlag+ "_FORECAST"));
		// // for(int colNum=0; colNum< decoration.numCols(); colNum++)
		// // {
		// // model.set("col_"+ colNum+ "_FORECAST", selected);
		// // }
		// }
		// }
		// //set("col_"+ colNum+ "_FORECAST",
		// CCBS.MONTH_FOR_YEARS.get(PagePanelController.getLastYearToShow()));
		// // set("col_"+ colNum+ "_YEAR",
		// PagePanelController.getLastYearToShow());
		// }
	}

	public static SelectionListener<ButtonEvent> saveAndCloseGrid(
			final CCBSWindow window, final BookPanel bookpanel,
			final int pagePanelNum, final String dataSource,
			final String country, final String countryCode,
			final String commodity, final String commodityCode,
			final RadioGroup radioGroup, final ComboBox<AMISCodesModelData> c,
			final Html start, final Html to, final Radio radio) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				 CCBS.CHANGES_IN_THE_GRID = false;
				System.out.println("Class: PagePanel Function: saveAndCloseGrid Text: Before call if saveInformationForm dataSource "+ dataSource);
				//window.getWindow().hide();
				boolean isSaveAndClose = true;
				saveInformationForm(window, bookpanel, pagePanelNum,
						dataSource, country, countryCode, commodity,
						commodityCode, radioGroup, c, start, to, radio, isSaveAndClose);
				System.out
						.println("Class: PagePanel Function: saveAndCloseGrid Text: After saveInformationForm ");
			}
		};
	}

	public static SelectionListener<ButtonEvent> saveGrid(
			final CCBSWindow window, final BookPanel bookpanel,
			final int pagePanelNum, final String dataSource,
			final String country, final String countryCode,
			final String commodity, final String commodityCode,
			final RadioGroup radioGroup, final ComboBox<AMISCodesModelData> c,
			final Html start, final Html to, final Radio radio) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				 CCBS.CHANGES_IN_THE_GRID = false;
				System.out.println("Class: PagePanel Function: saveGrid Text: Before call if saveInformationForm dataSource "+ dataSource);
				boolean isSaveAndClose = false;
				saveInformationForm(window, bookpanel, pagePanelNum,
						dataSource, country, countryCode, commodity,
						commodityCode, radioGroup, c, start, to, radio, isSaveAndClose);
				System.out.println("Class: PagePanel Function: saveGrid Text: After saveInformationForm ");
				// Window w = new Window();
				// w.setWidth(300);
				// w.setHeading("Information Form");
				// //w.setTitle("Information Form");
				// FormPanel simple = new FormPanel();
				// //simple.setHeading("Information Form");
				// simple.setHeaderVisible(false);
				// simple.setFrame(true);
				// simple.setWidth(290);
				//				
				// VerticalPanel vp = new VerticalPanel();
				// vp.setSpacing(3);
				// Label label = new Label("Full Data Source Name");
				// label.setStyleName("ccbs-Label");
				// label.setWidth("200px");
				// TextField<String> textField = new TextField<String>();
				// textField.setAllowBlank(false);
				// textField.setWidth(200);
				// vp.add(label);
				// vp.add(textField);
				// // TextField<String> fullName = new TextField<String>();
				// // fullName.setFieldLabel("Full Data Source Name");
				// // fullName.setAllowBlank(false);
				// //
				// //firstName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());
				// // simple.add(fullName);
				//				
				// label = new Label("Acronym Data Source Name");
				// label.setStyleName("ccbs-Label");
				// label.setWidth("200px");
				// textField = new TextField<String>();
				// textField.setAllowBlank(false);
				// textField.setWidth(200);
				// vp.add(label);
				// vp.add(textField);
				//				
				// // TextField<String> email = new TextField<String>();
				// // email.setFieldLabel("Acronym Data Source Name");
				// // email.setAllowBlank(false);
				// Button b = new Button("Submit");
				// b.addSelectionListener(saveInformationForm(bookpanel,
				// pagePanelNum, dataSource, country, countryCode,
				// commodity,commodityCode, w));
				// simple.addButton(b);
				// Button c = new Button("Cancel");
				// c.addSelectionListener(cancelInformationForm(w));
				// simple.addButton(c);
				// simple.setButtonAlign(HorizontalAlignment.CENTER);
				// simple.add(vp);
				//				
				// FormButtonBinding binding = new FormButtonBinding(simple);
				// binding.addButton(b);
				//				
				// //simple.add(email);
				// w.add(simple);
				// w.show();
			}
		};
	}

	public static SelectionListener<ButtonEvent> cancelInformationForm(
			final Window w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.hide();
			}
		};
	}

	public static void checkLastColumnYear(Grid grid, int numCols, int numRows) {
		// NumCols indicates the last column
		// NumRows indicates the number of the rows of the grid
		// System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: numCols= "+numCols);
		ColumnConfig colConf = grid.getColumnModel().getColumn(numCols + 1);
		System.out.println("Class: PagePanel Function:checkLastColumnYear Text: numCols= "+numCols+"  numRows "+numRows);
		 System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: colConf.getWidget()= "+colConf.getWidget());
		if (colConf.getWidget() != null) {
			System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: colConf.getWidget() !=null ");
			HorizontalPanel hp = (HorizontalPanel) colConf.getWidget();
			ComboBox<AMISCodesModelData> comboYear = (ComboBox<AMISCodesModelData>) hp.getItemByItemId("ComboYear");
			ComboBox<MonthForecast> comboMonth = (ComboBox<MonthForecast>) hp.getItemByItemId("ComboMonth");
			String yearValue = comboYear.getValue().getLabel();
			String monthValue = comboMonth.getValue().getMonthForecast();
			System.out.println("Class: PagePanel Function:checkLastColumnYear Text: yearValue= "+yearValue+" monthValue= "+monthValue);
			// Update value in the list of data results for Production and Other
			// Uses Form
			for (ClientCbsDatasetResult clientCbsDatasetResult : CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM) {
				// //Check elements and take the elements of the last column to
				// change them value
				// for(int row = 0; row<numRows; row++)
				// {
				// System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: row= "+row+" numCols= "+numCols);
				// System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: clientCbsDatasetResult.getId()= "+clientCbsDatasetResult.getId());
				// if(clientCbsDatasetResult.getId().contains("row_"+row+
				// "col_"+ numCols));
				// {
				// //For the elements of the last column
				// clientCbsDatasetResult.setYear(yearValue);
				// clientCbsDatasetResult.setMonth(PagePanelController.changeMonthString(monthValue));
				// }
				// }

				String id = clientCbsDatasetResult.getId();
				String numColString = "col_" + numCols;
				//System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: id "	+ id);
				//System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: numColString "+ numColString);
				// if(id.contains(numColString));
				if (id.indexOf(numColString) > 0) {
					//System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: if numCols= "+ numCols);
					//System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: clientCbsDatasetResult.getId()= "+ clientCbsDatasetResult.getId());
					// For the elements of the last column
					clientCbsDatasetResult.setYear(yearValue);
					// clientCbsDatasetResult.setMonth(PagePanelController.changeMonthString(monthValue));
					//System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: if monthValue= "+ monthValue);
					clientCbsDatasetResult.setMonth(PagePanelController.convertMonthToNumber(monthValue));
				}
			}
		}
	}

	public static void saveInformationForm(final CCBSWindow ccbswindow,
			final BookPanel bookpanel, final int pagePanelNum,
			final String dataSource, final String country,
			final String countryCode, final String commodity,
			final String commodityCode, final RadioGroup radioGroup,
			final ComboBox<AMISCodesModelData> c, final Html start,
			final Html to, final Radio radio, final boolean isSaveAndClose) /* , final Window w */{
		System.out.println("Class: PagePanel Function: saveInformationForm Text: Start");
		if (bookpanel == null) {
			System.out.println("******************************BOOKPANEL NULL ");
		}
		PagePanel pagepanel = bookpanel.getPagePanel(pagePanelNum);
		//System.out.println("Class: PagePanel Function: saveInformationForm Text: Start 2");
		if (pagepanel == null) {
			System.out.println("******************************bookpanel.getPagePanel(pagePanelNum) NULL ");
		}
		Grid grid = bookpanel.getPagePanel(pagePanelNum).getGrid();
	//	System.out.println("******************************saveInformationForm before  checkLastColumnYear");
		checkLastColumnYear(grid, (pagepanel.getDecoration().numCols() - 2),
				pagepanel.getDecoration().numRows());
		//System.out.println("******************************saveInformationForm after  checkLastColumnYear");
		// ColumnConfig colConf = grid.getColumnModel().getColumn(6);
		// HorizontalPanel hp = (HorizontalPanel)colConf.getWidget();
		// hp.getItemByItemId(itemId);
		if (grid == null) {
			System.out.println("******************************grid NULL ");
		}
		// System.out.println("******************************firstElement " +
		// CCBSParametersPanel.firstElement +" dataSource " + dataSource);
		boolean foundElement = false;
		if (!dataSource.equals(CCBS.elementForTraining)) {
			// New Case or Country Case
			CCBSStore store = (CCBSStore) grid.getStore();
			// List<CCBSModel> model = grid.getStore().getModifiedRecords();

			List<CCBSModel> model = (List<CCBSModel>) store.getModels();
			int totalNumCol = pagepanel.getDecoration().numCols();
			int i = 0;
			ClientCbsToolTimeSeries timeSeries = new ClientCbsToolTimeSeries();
			String value = "";
			String element = "";
			String elementUnit = "";
			String forecast = "";
			String year = "";
			String flag = "";
			// Name in the real table im the db
			String elementName = "";
			// Code in the real table im the db
			String elementCode = "";
			String valueType = "";
			System.out.println("******************* GRID StartProperties");

			boolean nullValueFound= false;
		//	System.out.println("******************************saveInformationForm Start print name of elements ");
			Set<String> keySet2 = CCBS.NAMES_CODES_FIELD.keySet();
			// There is just an element in this list: code, name of the element
			// in the database
			for (String key2 : keySet2) {
				Set<String> keySet3 = CCBS.NAMES_CODES_FIELD.get(key2).keySet();
				for (String key : keySet3) {
					elementCode = key;
					elementName = CCBS.NAMES_CODES_FIELD.get(key2).get(elementCode);
					System.out.println("******************************saveInformationForm  NAMES_CODES_FIELD element "+ elementName+ " elementCode "+ elementCode);
				}
			}
			keySet2 = CCBS.OTHER_NAMES_CODES_FIELD.keySet();
			for (String key2 : keySet2) {
				Set<String> keySet3 = CCBS.OTHER_NAMES_CODES_FIELD.get(key2)
						.keySet();
				for (String key : keySet3) {
					elementCode = key;
					elementName = CCBS.OTHER_NAMES_CODES_FIELD.get(key2).get(elementCode);
					System.out.println("******************************saveInformationForm  OTHER_NAMES_CODES_FIELD element "+ elementName+ " elementCode "+ elementCode);
				}
			}
			//System.out.println("******************************saveInformationForm End print name of elements ");
			for (CCBSModel ccbsRow : model)
			// for(Record ccbsRow : model)
			{
				foundElement = false;
				List<ClientCbsDatasetResult> datasetResultList = new ArrayList<ClientCbsDatasetResult>();
				// System.out.println("MODEL "+i+" ");
				for (int colNum = 0; colNum < totalNumCol; colNum += 2) {
					value = ccbsRow.get("col_" + colNum);
					element = ccbsRow.get("col_" + colNum + "_ELEMENT");
					System.out.println("******************* GRID Reading properties...element "+ element);
					elementUnit = ccbsRow.get("col_" + colNum + "_ELEMENT_UNIT");
					forecast = ccbsRow.get("col_" + colNum + "_FORECAST");
					// System.out.println("******************* Before convertMonthToNumber before forecast"+forecast);
					forecast = PagePanelController.convertMonthToNumber(forecast);
					// System.out.println("******************* Before convertMonthToNumber after forecast"+forecast);
					year = ccbsRow.get("col_" + colNum + "_YEAR");
					// flag = ccbsRow.get("col_"+ colNum+ "_FLAG");
					flag = ccbsRow.get("col_" + (colNum + 1));
					valueType = ccbsRow.get("col_" + colNum + "_VALUE_TYPE");
					String calculatedFieldName = "";
					int j=0;
					for(j=0; j< CCBS.CALCULATED_FIELD_NAMES.length; j++)
					{
						if(element.equalsIgnoreCase(CCBS.CALCULATED_FIELD_NAMES[j]))
						{
								break;
						}
					}
					//If the element is not calculated.... check if it contains a null value
					if(j==CCBS.CALCULATED_FIELD_NAMES.length)
					{
						if((value== null)||(value.equals(""))||(value.equals("null"))||(value.equals("NULL")))
						{
							System.out.println("col "+ colNum+ " element= "+element+" year= "+year+" value *"+ value+"*");
							nullValueFound = true;
							break;
						}
					}
					
					// System.out.println("******************* GRID StartProperties element"+element);
					// if((element.equals("Domestic Availability"))||(element.equals("Total Utilization"))||(element.equals("Domestic Utilization"))||(element.equals("Per Cap. Food Use")))
					// {
					// System.out.println("Col "+colNum+" StartProperties");
					// // System.out.println("Col "+colNum+" Value: "+
					// ccbsRow.get("col_"+ colNum));
					// // System.out.println("Col "+colNum+" Element: "+
					// ccbsRow.get("col_"+ colNum+ "_ELEMENT"));
					// // System.out.println("Col "+colNum+" Element Unit : "+
					// ccbsRow.get("col_"+ colNum+ "_ELEMENT_UNIT"));
					// // System.out.println("Col "+colNum+" Forecast : "+
					// ccbsRow.get("col_"+ colNum+ "_FORECAST"));
					// // System.out.println("Col "+colNum+" Year : "+
					// ccbsRow.get("col_"+ colNum+ "_YEAR"));
					// // System.out.println("Col "+colNum+" FLAG : "+
					// ccbsRow.get("col_"+ colNum+ "_FLAG"));
					//								
					// System.out.println("Col "+colNum+" Value: "+ value);
					// System.out.println("Col "+colNum+" Element: "+ element);
					// System.out.println("Col "+colNum+" Element Unit : "+
					// elementUnit);
					// System.out.println("Col "+colNum+" Forecast : "+
					// forecast);
					// System.out.println("Col "+colNum+" Year : "+ year);
					// System.out.println("Col "+colNum+" FLAG : "+ flag);
					// System.out.println("Col "+colNum+" valueType : "+
					// valueType);
					// }

					// System.out.println("EndProperties");
					// System.out.println("CCBS.NAMES_CODES_FIELD element "+
					// element);
					// Set<String> keySet2 = CCBS.NAMES_CODES_FIELD.keySet();
					// for(String key: keySet2)
					// {
					// System.out.println("CCBS.NAMES_CODES_FIELD key "+ key);
					// System.out.println("CCBS.NAMES_CODES_FIELD Value "+
					// CCBS.NAMES_CODES_FIELD.get(key));
					// }
					//System.out.println(" If element: " + element);
					if (CCBS.NAMES_CODES_FIELD.get(element) != null) {
					//	System.out.println(" If CCBS.NAMES_CODES_FIELD.get(element): "+ CCBS.NAMES_CODES_FIELD.get(element));
						Set<String> keySet = CCBS.NAMES_CODES_FIELD.get(element).keySet();
						// There is just an element in this list: code, name of
						// the element in the database
						for (String key : keySet) {
							elementCode = key;
							elementName = CCBS.NAMES_CODES_FIELD.get(element).get(elementCode);
							break;
						}
						ClientCbsDatasetResult datasetResult = new ClientCbsDatasetResult(
								dataSource, countryCode, country,
								commodityCode, commodity, elementCode,
								elementName, elementUnit, year, value, flag,
								forecast, valueType);
						// Insert the result
						foundElement = true;
						datasetResultList.add(datasetResult);
					} else if (CCBS.OTHER_NAMES_CODES_FIELD.get(element) != null) {
					//	System.out.println(" If OTHER_NAMES_CODES_FIELD.get(element): "+ CCBS.OTHER_NAMES_CODES_FIELD.get(element));
						// System.out.println(" Else CCBS.OTHER_NAMES_CODES_FIELD.get(element): "+
						// CCBS.OTHER_NAMES_CODES_FIELD.get(element));
						Set<String> keySet = CCBS.OTHER_NAMES_CODES_FIELD.get(
								element).keySet();
						// There is just an element in this list: code, name of
						// the element in the database
						for (String key : keySet) {
							elementCode = key;
							elementName = CCBS.OTHER_NAMES_CODES_FIELD.get(element).get(elementCode);
							break;
						}
						ClientCbsDatasetResult datasetResult = new ClientCbsDatasetResult(
								dataSource, countryCode, country,
								commodityCode, commodity, elementCode,
								elementName, elementUnit, year, value, flag,
								forecast, valueType);
						// Insert the result
						foundElement = true;
						datasetResultList.add(datasetResult);
						// System.out.println(" DataSource: "+
						// datasetResult.getDatabase());
						// System.out.println(" CountryCode: "+
						// datasetResult.getRegion_code());
						// System.out.println(" CountryName: "+
						// datasetResult.getRegion_name());
						// System.out.println(" CommodityCode: "+
						// datasetResult.getProduct_code());
						// System.out.println(" CommodityName: "+
						// datasetResult.getProduct_name());
						// System.out.println(" Value: "+
						// datasetResult.getValue());
						// System.out.println(" ElementCode: "+
						// datasetResult.getElement_code());
						// System.out.println(" ElementName: "+
						// datasetResult.getElement_name());
						// System.out.println(" ElementUnit : "+
						// datasetResult.getUnits());
						// System.out.println(" Forecast : "+
						// datasetResult.getMonth());
						// System.out.println(" Year : "+
						// datasetResult.getYear());
						// System.out.println(" Flag : "+
						// datasetResult.getAnnotation());
						// System.out.println(" ValueType : "+
						// datasetResult.getValueType());
					} else {
						// System.out.println(" Else NO!!!!!! ");
					}
				}
				if(nullValueFound)
				{
					break;
				}
				// Insert the list of the results
				if (foundElement) {
					timeSeries.getElement().add(datasetResultList);
				}
				// System.out.println("");
				// System.out.println("");
				i++;
			}
			if(nullValueFound)
			{
				System.out.println("Null value!!!!! ");
				MessageBox.alert("Info","Please fill blank data before saving.",null);
			}
			else
			{
				List<ClientCbsDatasetResult> listToSaveOnDb = new ArrayList<ClientCbsDatasetResult>();
				System.out.println("******************* GRID StartProperties");
				int iRow = 0;
				int iElement = 0;
				int iColumn = 0;
				int timeSeriesDim = 0;
				for (List<ClientCbsDatasetResult> list : timeSeries.getElement()) {
					timeSeriesDim += list.size();
					// For Each Row
					iElement = 0;
					iColumn = 0;
					System.out.println("******** Row " + iRow);
					for (ClientCbsDatasetResult dataResult : list) {
						System.out.println("******** Row " + iRow + " Element "	+ iElement);
						// Set the id for the element
						// Ex.row_14col_8SubElement_IndustrialUse_Others
						dataResult.setId("row_" + iRow + "col_" + iColumn+ "Element_" + dataResult.getElement_name());
						// For Each Element
						System.out.println(" Id: " + dataResult.getId());
						System.out.println(" DataSource: "+ dataResult.getDatabase());
						System.out.println(" CountryCode: "+ dataResult.getRegion_code());
						System.out.println(" CountryName: "+ dataResult.getRegion_name());
						System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
						System.out.println(" CommodityName: "+ dataResult.getProduct_name());
						System.out.println(" Value: " + dataResult.getValue());
						System.out.println(" ElementCode: "+ dataResult.getElement_code());
						System.out.println(" ElementName: "+ dataResult.getElement_name());
						System.out.println(" ElementUnit : "+ dataResult.getUnits());
						System.out.println(" Forecast : " + dataResult.getMonth());
						System.out.println(" Year : " + dataResult.getYear());
						System.out.println(" Flag : " + dataResult.getAnnotation());
						System.out.println(" ValueType : "+ dataResult.getValueType());
						iElement++;
						listToSaveOnDb.add(dataResult);

						iColumn += 2;
					}
					iRow++;
				}
				System.out.println("******************* GRID EndProperties");

				System.out.println("******************* SMALL FORM StartProperties size = "+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
				int iCell = 0;
				for (ClientCbsDatasetResult dataResult : CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM) {
					System.out.println("******** iCell " + iCell);
					// For Each Element
					System.out.println(" Id: " + dataResult.getId());
					System.out.println(" DataSource: " + dataResult.getDatabase());
					System.out.println(" CountryCode: "+ dataResult.getRegion_code());
					System.out.println(" CountryName: "+ dataResult.getRegion_name());
					System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
					System.out.println(" CommodityName: "+ dataResult.getProduct_name());
					System.out.println(" Value: " + dataResult.getValue());
					System.out.println(" ElementCode: "+ dataResult.getElement_code());
					System.out.println(" ElementName: "+ dataResult.getElement_name());
					System.out.println(" ElementUnit : " + dataResult.getUnits());
					System.out.println(" Forecast : " + dataResult.getMonth());
					System.out.println(" Year : " + dataResult.getYear());
					System.out.println(" Flag : " + dataResult.getAnnotation());
					System.out.println(" ValueType : " + dataResult.getValueType());
					iCell++;
					listToSaveOnDb.add(dataResult);
				}
				System.out.println("******************* SMALL FORM EndProperties");
				try {
					if(isSaveAndClose)
					{
						//If the button is save and close
						//It's necessary close the window of the Ccbs tool
						ccbswindow.getWindow().hide();
					}
					int dim = timeSeriesDim	+ CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size();
					// ClientCbsDatasetResult listToSave[] = new
					// ClientCbsDatasetResult[dim];
					List<HashMap<String, String>> listToSave = new ArrayList<HashMap<String, String>>();
					for (ClientCbsDatasetResult client : listToSaveOnDb) {
						HashMap<String, String> hashMap = new HashMap<String, String>();
						hashMap.put("id", client.getId());
						hashMap.put("datasource", client.getDatabase());
						hashMap.put("countrycode", client.getRegion_code());
						hashMap.put("countryname", client.getRegion_name());
						hashMap.put("commoditycode", client.getProduct_code());
						hashMap.put("commodityname", client.getProduct_name());
						hashMap.put("value", client.getValue());
						hashMap.put("elementcode", client.getElement_code());
						hashMap.put("elementname", client.getElement_name());
						hashMap.put("elementunit", client.getUnits());
						hashMap.put("forecast", client.getMonth());
						hashMap.put("year", client.getYear());
						hashMap.put("flag", client.getAnnotation());
						hashMap.put("valuetype", client.getValueType());
						listToSave.add(hashMap);
					}

					AMISServiceEntry.getInstance().saveGridCcbsTool(
							ccbswindow.amisForCountryDataset(), listToSave,
							pagepanel.getDecoration().numCols(),
							new AsyncCallback<Boolean>() {
								@SuppressWarnings("unchecked")
								public void onSuccess(Boolean result) {
									System.out.println("Class: PagePanel Function:saveInformationForm Text: result "+ result);
									// w.hide();
									MessageBox.info("Action",
											"The data have been saved", null);
									CCBSParametersPanel.reloadComboYearAfterTheSave(
													dataSource, country,
													countryCode, commodity,
													commodityCode, radioGroup, c,
													start, to, radio);
								}

								// SELECT * FROM
								// AMIS_HISTORICAL_DATA_COUNTRY_DATASOURCE_26e1a38a
								// WHERE database='AMIS Data Argentina DataSource'
								// AND region_code='12' AND region_name= 'Argentina'
								// AND product_code= '1' AND product_name='Wheat
								// (Total)' AND element_code='19' AND
								// element_name='Supply' AND units= 'Million tonnes'
								// AND year= '2006-01-01' AND month= '0' AND value=
								// '18.24' AND flag='E' AND value_type='TOTAL' AND
								// last_update = CURRENT_DATE)
								public void onFailure(Throwable arg0) {

								}
							});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		} else {
			MessageBox.alert("Info",
					"This is a training version! The data can not be saved!",
					null);
			// Look saveAllChanges method in TableToolbarController in
			// org.fao.fenix.web.modules.table.client.control
			// List<Record> store = grid.getStore().getModifiedRecords();
			// for(int i=0; i<store.size(); i++)
			// {
			// Record record = store.get(i);
			// // store..
			// // record.getModel();
			// // CCBSModel c = store.get(i);
			// System.out.println("******************************STORE i = "+i+" value "+store.get(i).toString());
			// }
			//					
		}

	}

	// public static SelectionListener<ButtonEvent> saveInformationForm(final
	// BookPanel bookpanel, final int pagePanelNum, final String dataSource,
	// final String country, final String countryCode, final String commodity,
	// final String commodityCode /*, final Window w*/) {
	// return new SelectionListener<ButtonEvent>() {
	// public void componentSelected(ButtonEvent ce) {
	// System.out.println("Class: PagePanel Function: saveInformationForm Text: Start");
	// if(bookpanel == null)
	// {
	// System.out.println("******************************BOOKPANEL NULL ");
	// }
	// PagePanel pagepanel = bookpanel.getPagePanel(pagePanelNum);
	// System.out.println("Class: PagePanel Function: saveInformationForm Text: Start 2");
	// if(pagepanel == null)
	// {
	// System.out.println("******************************bookpanel.getPagePanel(pagePanelNum) NULL ");
	// }
	// Grid grid = bookpanel.getPagePanel(pagePanelNum).getGrid();
	// System.out.println("******************************saveInformationForm before  checkLastColumnYear");
	// checkLastColumnYear(grid, (pagepanel.getDecoration().numCols()-2),
	// pagepanel.getDecoration().numRows());
	// System.out.println("******************************saveInformationForm after  checkLastColumnYear");
	// // ColumnConfig colConf = grid.getColumnModel().getColumn(6);
	// // HorizontalPanel hp = (HorizontalPanel)colConf.getWidget();
	// // hp.getItemByItemId(itemId);
	// if(grid == null)
	// {
	// System.out.println("******************************grid NULL ");
	// }
	// // System.out.println("******************************firstElement " +
	// CCBSParametersPanel.firstElement +" dataSource " + dataSource);
	// boolean foundElement = false;
	// if(!dataSource.equals(CCBS.elementForTraining))
	// {
	// //New Case or Country Case
	// CCBSStore store = (CCBSStore)grid.getStore();
	// //List<CCBSModel> model = grid.getStore().getModifiedRecords();
	//					
	// List<CCBSModel> model = (List<CCBSModel>)store.getModels();
	// int totalNumCol = pagepanel.getDecoration().numCols();
	// int i = 0;
	// ClientCbsToolTimeSeries timeSeries = new ClientCbsToolTimeSeries();
	// String value = "";
	// String element = "";
	// String elementUnit = "";
	// String forecast = "";
	// String year = "";
	// String flag = "";
	// //Name in the real table im the db
	// String elementName = "";
	// //Code in the real table im the db
	// String elementCode = "";
	// String valueType = "";
	// System.out.println("******************* GRID StartProperties");
	// for(CCBSModel ccbsRow : model)
	// //for(Record ccbsRow : model)
	// {
	// foundElement = false;
	// List<ClientCbsDatasetResult> datasetResultList = new
	// ArrayList<ClientCbsDatasetResult>();
	// // System.out.println("MODEL "+i+" ");
	// for(int colNum= 0; colNum < totalNumCol; colNum+=2)
	// {
	// value = ccbsRow.get("col_"+ colNum);
	// element = ccbsRow.get("col_"+ colNum+ "_ELEMENT");
	// elementUnit = ccbsRow.get("col_"+ colNum+ "_ELEMENT_UNIT");
	// forecast = ccbsRow.get("col_"+ colNum+ "_FORECAST");
	// System.out.println("******************* Before convertMonthToNumber before forecast"+forecast);
	// forecast = PagePanelController.convertMonthToNumber(forecast);
	// System.out.println("******************* Before convertMonthToNumber after forecast"+forecast);
	// year = ccbsRow.get("col_"+ colNum+ "_YEAR");
	// //flag = ccbsRow.get("col_"+ colNum+ "_FLAG");
	// flag = ccbsRow.get("col_"+ (colNum+1));
	// valueType = ccbsRow.get("col_"+ colNum+ "_VALUE_TYPE");
	// System.out.println("******************* GRID StartProperties element"+element);
	// //
	// if((element.equals("Domestic Availability"))||(element.equals("Total Utilization"))||(element.equals("Domestic Utilization"))||(element.equals("Per Cap. Food Use")))
	// // {
	// // System.out.println("Col "+colNum+" StartProperties");
	// //// System.out.println("Col "+colNum+" Value: "+ ccbsRow.get("col_"+
	// colNum));
	// //// System.out.println("Col "+colNum+" Element: "+ ccbsRow.get("col_"+
	// colNum+ "_ELEMENT"));
	// //// System.out.println("Col "+colNum+" Element Unit : "+
	// ccbsRow.get("col_"+ colNum+ "_ELEMENT_UNIT"));
	// //// System.out.println("Col "+colNum+" Forecast : "+ ccbsRow.get("col_"+
	// colNum+ "_FORECAST"));
	// //// System.out.println("Col "+colNum+" Year : "+ ccbsRow.get("col_"+
	// colNum+ "_YEAR"));
	// //// System.out.println("Col "+colNum+" FLAG : "+ ccbsRow.get("col_"+
	// colNum+ "_FLAG"));
	// //
	// // System.out.println("Col "+colNum+" Value: "+ value);
	// // System.out.println("Col "+colNum+" Element: "+ element);
	// // System.out.println("Col "+colNum+" Element Unit : "+ elementUnit);
	// // System.out.println("Col "+colNum+" Forecast : "+ forecast);
	// // System.out.println("Col "+colNum+" Year : "+ year);
	// // System.out.println("Col "+colNum+" FLAG : "+ flag);
	// // System.out.println("Col "+colNum+" valueType : "+ valueType);
	// // }
	//	
	//							
	// // System.out.println("EndProperties");
	// // System.out.println("CCBS.NAMES_CODES_FIELD element "+ element);
	// // Set<String> keySet2 = CCBS.NAMES_CODES_FIELD.keySet();
	// // for(String key: keySet2)
	// // {
	// // System.out.println("CCBS.NAMES_CODES_FIELD key "+ key);
	// // System.out.println("CCBS.NAMES_CODES_FIELD Value "+
	// CCBS.NAMES_CODES_FIELD.get(key));
	// // }
	// if(CCBS.NAMES_CODES_FIELD.get(element)!=null)
	// {
	// System.out.println(" If CCBS.NAMES_CODES_FIELD.get(element): "+
	// CCBS.NAMES_CODES_FIELD.get(element));
	// Set<String> keySet = CCBS.NAMES_CODES_FIELD.get(element).keySet();
	// //There is just an element in this list: code, name of the element in the
	// database
	// for(String key: keySet)
	// {
	// elementCode = key;
	// elementName = CCBS.NAMES_CODES_FIELD.get(element).get(elementCode);
	// break;
	// }
	// ClientCbsDatasetResult datasetResult = new
	// ClientCbsDatasetResult(dataSource, countryCode, country, commodityCode,
	// commodity, elementCode, elementName, elementUnit, year, value, flag,
	// forecast, valueType);
	// //Insert the result
	// foundElement = true;
	// datasetResultList.add(datasetResult);
	// }
	// else if(CCBS.OTHER_NAMES_CODES_FIELD.get(element)!=null)
	// {
	// System.out.println(" Else CCBS.OTHER_NAMES_CODES_FIELD.get(element): "+
	// CCBS.OTHER_NAMES_CODES_FIELD.get(element));
	// Set<String> keySet = CCBS.OTHER_NAMES_CODES_FIELD.get(element).keySet();
	// //There is just an element in this list: code, name of the element in the
	// database
	// for(String key: keySet)
	// {
	// elementCode = key;
	// elementName = CCBS.OTHER_NAMES_CODES_FIELD.get(element).get(elementCode);
	// break;
	// }
	// ClientCbsDatasetResult datasetResult = new
	// ClientCbsDatasetResult(dataSource, countryCode, country, commodityCode,
	// commodity, elementCode, elementName, elementUnit, year, value, flag,
	// forecast, valueType);
	// //Insert the result
	// foundElement = true;
	// datasetResultList.add(datasetResult);
	// // System.out.println(" DataSource: "+ datasetResult.getDatabase());
	// // System.out.println(" CountryCode: "+ datasetResult.getRegion_code());
	// // System.out.println(" CountryName: "+ datasetResult.getRegion_name());
	// // System.out.println(" CommodityCode: "+
	// datasetResult.getProduct_code());
	// // System.out.println(" CommodityName: "+
	// datasetResult.getProduct_name());
	// // System.out.println(" Value: "+ datasetResult.getValue());
	// // System.out.println(" ElementCode: "+ datasetResult.getElement_code());
	// // System.out.println(" ElementName: "+ datasetResult.getElement_name());
	// // System.out.println(" ElementUnit : "+ datasetResult.getUnits());
	// // System.out.println(" Forecast : "+ datasetResult.getMonth());
	// // System.out.println(" Year : "+ datasetResult.getYear());
	// // System.out.println(" Flag : "+ datasetResult.getAnnotation());
	// // System.out.println(" ValueType : "+ datasetResult.getValueType());
	// }
	// else
	// {
	// System.out.println(" Else NO!!!!!! ");
	// }
	// }
	// //Insert the list of the results
	// if(foundElement)
	// {
	// timeSeries.getElement().add(datasetResultList);
	// }
	// // System.out.println("");
	// // System.out.println("");
	// i++;
	// }
	// List<ClientCbsDatasetResult> listToSaveOnDb = new
	// ArrayList<ClientCbsDatasetResult>();
	// System.out.println("******************* GRID StartProperties");
	// int iRow =0;
	// int iElement =0;
	// int iColumn = 0;
	// int timeSeriesDim =0;
	// for(List<ClientCbsDatasetResult> list:timeSeries.getElement())
	// {
	// timeSeriesDim += list.size();
	// //For Each Row
	// iElement =0;
	// iColumn=0;
	// System.out.println("******** Row "+ iRow);
	// for(ClientCbsDatasetResult dataResult: list)
	// {
	// System.out.println("******** Row "+ iRow + " Element "+ iElement);
	// //Set the id for the element
	// //Ex.row_14col_8SubElement_IndustrialUse_Others
	// dataResult.setId("row_"+iRow+"col_"+iColumn+"Element_"+dataResult.getElement_name());
	// //For Each Element
	// System.out.println(" Id: "+ dataResult.getId());
	// System.out.println(" DataSource: "+ dataResult.getDatabase());
	// System.out.println(" CountryCode: "+ dataResult.getRegion_code());
	// System.out.println(" CountryName: "+ dataResult.getRegion_name());
	// System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
	// System.out.println(" CommodityName: "+ dataResult.getProduct_name());
	// System.out.println(" Value: "+ dataResult.getValue());
	// System.out.println(" ElementCode: "+ dataResult.getElement_code());
	// System.out.println(" ElementName: "+ dataResult.getElement_name());
	// System.out.println(" ElementUnit : "+ dataResult.getUnits());
	// System.out.println(" Forecast : "+ dataResult.getMonth());
	// System.out.println(" Year : "+ dataResult.getYear());
	// System.out.println(" Flag : "+ dataResult.getAnnotation());
	// System.out.println(" ValueType : "+ dataResult.getValueType());
	// iElement++;
	// listToSaveOnDb.add(dataResult);
	//							
	// iColumn+=2;
	// }
	// iRow++;
	// }
	// System.out.println("******************* GRID EndProperties");
	//					
	// System.out.println("******************* SMALL FORM StartProperties size = "+
	// CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
	// int iCell= 0;
	// for(ClientCbsDatasetResult dataResult:
	// CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
	// {
	// System.out.println("******** iCell "+ iCell);
	// //For Each Element
	// System.out.println(" Id: "+ dataResult.getId());
	// System.out.println(" DataSource: "+ dataResult.getDatabase());
	// System.out.println(" CountryCode: "+ dataResult.getRegion_code());
	// System.out.println(" CountryName: "+ dataResult.getRegion_name());
	// System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
	// System.out.println(" CommodityName: "+ dataResult.getProduct_name());
	// System.out.println(" Value: "+ dataResult.getValue());
	// System.out.println(" ElementCode: "+ dataResult.getElement_code());
	// System.out.println(" ElementName: "+ dataResult.getElement_name());
	// System.out.println(" ElementUnit : "+ dataResult.getUnits());
	// System.out.println(" Forecast : "+ dataResult.getMonth());
	// System.out.println(" Year : "+ dataResult.getYear());
	// System.out.println(" Flag : "+ dataResult.getAnnotation());
	// System.out.println(" ValueType : "+ dataResult.getValueType());
	// iCell++;
	// listToSaveOnDb.add(dataResult);
	// }
	// System.out.println("******************* SMALL FORM EndProperties");
	// try {
	// int dim = timeSeriesDim+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size();
	// //ClientCbsDatasetResult listToSave[] = new ClientCbsDatasetResult[dim];
	// List<HashMap<String, String>> listToSave = new
	// ArrayList<HashMap<String,String>>();
	// for(ClientCbsDatasetResult client : listToSaveOnDb)
	// {
	// HashMap<String, String> hashMap = new HashMap<String, String>();
	// hashMap.put("id", client.getId());
	// hashMap.put("datasource", client.getDatabase());
	// hashMap.put("countrycode", client.getRegion_code());
	// hashMap.put("countryname", client.getRegion_name());
	// hashMap.put("commoditycode", client.getProduct_code());
	// hashMap.put("commodityname", client.getProduct_name());
	// hashMap.put("value", client.getValue());
	// hashMap.put("elementcode", client.getElement_code());
	// hashMap.put("elementname", client.getElement_name());
	// hashMap.put("elementunit", client.getUnits());
	// hashMap.put("forecast", client.getMonth());
	// hashMap.put("year", client.getYear());
	// hashMap.put("flag", client.getAnnotation());
	// hashMap.put("valuetype", client.getValueType());
	// listToSave.add(hashMap);
	// }
	// AMISServiceEntry.getInstance().saveGridCcbsTool(listToSave,
	// pagepanel.getDecoration().numCols(), new AsyncCallback<Boolean>() {
	// @SuppressWarnings("unchecked")
	// public void onSuccess(Boolean result) {
	// System.out.println("Class: PagePanel Function:saveInformationForm Text: result "+
	// result);
	// // w.hide();
	// MessageBox.info("Action", "The data have been saved", null);
	// }
	//							
	// public void onFailure(Throwable arg0) {
	//				
	// }
	// });
	//						
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// else
	// {
	// MessageBox.alert("Info",
	// "This is a training version! The data can not be saved!", null);
	// //Look saveAllChanges method in TableToolbarController in
	// org.fao.fenix.web.modules.table.client.control
	// // List<Record> store = grid.getStore().getModifiedRecords();
	// // for(int i=0; i<store.size(); i++)
	// // {
	// // Record record = store.get(i);
	// //// store..
	// //// record.getModel();
	// //// CCBSModel c = store.get(i);
	// //
	// System.out.println("******************************STORE i = "+i+" value "+store.get(i).toString());
	// // }
	// //
	// }
	//				
	// }
	// };
	// }

	// public static SelectionListener<ButtonEvent> saveInformationForm(final
	// BookPanel bookpanel, final int pagePanelNum, final String dataSource,
	// final String country, final String countryCode, final String commodity,
	// final String commodityCode, final Window w) {
	// return new SelectionListener<ButtonEvent>() {
	// public void componentSelected(ButtonEvent ce) {
	//				
	// if(bookpanel == null)
	// {
	// System.out.println("******************************BOOKPANEL NULL ");
	// }
	// PagePanel pagepanel = bookpanel.getPagePanel(pagePanelNum);
	// if(pagepanel == null)
	// {
	// System.out.println("******************************bookpanel.getPagePanel(pagePanelNum) NULL ");
	// }
	// Grid grid = bookpanel.getPagePanel(pagePanelNum).getGrid();
	// System.out.println("******************************saveInformationForm before  checkLastColumnYear");
	// checkLastColumnYear(grid, (pagepanel.getDecoration().numCols()-2),
	// pagepanel.getDecoration().numRows());
	// System.out.println("******************************saveInformationForm after  checkLastColumnYear");
	// // ColumnConfig colConf = grid.getColumnModel().getColumn(6);
	// // HorizontalPanel hp = (HorizontalPanel)colConf.getWidget();
	// // hp.getItemByItemId(itemId);
	// if(grid == null)
	// {
	// System.out.println("******************************grid NULL ");
	// }
	// // System.out.println("******************************firstElement " +
	// CCBSParametersPanel.firstElement +" dataSource " + dataSource);
	// boolean foundElement = false;
	// if(!dataSource.equals(CCBS.elementForTraining))
	// {
	// //New Case or Country Case
	// CCBSStore store = (CCBSStore)grid.getStore();
	// List<Record> model = grid.getStore().getModifiedRecords();
	// List<Integer> modifiedColumnId;
	// int underscoreIndex;
	// int idInt;
	// int colNum;
	// //List<CCBSModel> model = (List<CCBSModel>)store.getModels();
	// int totalNumCol = pagepanel.getDecoration().numCols();
	// int i = 0;
	// ClientCbsToolTimeSeries timeSeries = new ClientCbsToolTimeSeries();
	// String value = "";
	// String element = "";
	// String elementUnit = "";
	// String forecast = "";
	// String year = "";
	// String flag = "";
	// //Name in the real table im the db
	// String elementName = "";
	// //Code in the real table im the db
	// String elementCode = "";
	// String valueType = "";
	// List<ClientCbsDatasetResult> datasetResultList = new
	// ArrayList<ClientCbsDatasetResult>();
	// System.out.println("******************* GRID StartProperties");
	// //for(CCBSModel ccbsRow : model)
	// //Loop on the modified records
	// for(Record ccbsRowRecord : model)
	// {
	// //Take the single row
	// CCBSModel ccbsRow = (CCBSModel)ccbsRowRecord.getModel();
	// // Map<String, Object> property = ccbsRow.getProperties();
	// //Take the modified columns
	// Map<String, Object> property = ccbsRowRecord.getChanges();
	// Set<String> keySet2 = property.keySet();
	// Iterator it= keySet2.iterator();
	// // for(int z=0; z<property.size(); z++)
	// // {
	// // String p = (String)it.next();
	// // System.out.println("Property Key = "+ p
	// +" Property "+property.get(p));
	// // System.out.println("Property Key = "+ p
	// +" ccbsRow.get(+property.get(p)) "+ccbsRow.get(""+property.get(p)));
	// // }
	// modifiedColumnId = new ArrayList<Integer>();
	// //Loop on the modified columns to save the id in a list
	// for(int z=0; z<property.size(); z++)
	// {
	// String p = (String)it.next();
	// //Take the number of the column parsing the id
	// underscoreIndex = p.indexOf("_");
	// String id = p.substring(underscoreIndex+1);
	// idInt = Integer.parseInt(id);
	// //Take only the peer column because that contain all the properties
	// if(idInt%2==0)
	// {
	// modifiedColumnId.add(new Integer(idInt));
	// }
	// System.out.println("Property Key = "+ p +" Property "+property.get(p));
	// System.out.println("Property Key = "+ p
	// +" ccbsRow.get(+property.get(p)) "+ccbsRow.get(""+property.get(p)));
	// }
	//						
	// foundElement = false;
	//						
	// // System.out.println("MODEL "+i+" ");
	//						
	// for(Integer idModifiedColumn: modifiedColumnId)
	// {
	// colNum = idModifiedColumn;
	// // for(int colNum= 0; colNum < totalNumCol; colNum+=2)
	// // {
	// value = ccbsRow.get("col_"+ colNum);
	// element = ccbsRow.get("col_"+ colNum+ "_ELEMENT");
	// elementUnit = ccbsRow.get("col_"+ colNum+ "_ELEMENT_UNIT");
	// forecast = ccbsRow.get("col_"+ colNum+ "_FORECAST");
	// System.out.println("******************* Before convertMonthToNumber before forecast"+forecast);
	// forecast = PagePanelController.convertMonthToNumber(forecast);
	// System.out.println("******************* Before convertMonthToNumber after forecast"+forecast);
	// year = ccbsRow.get("col_"+ colNum+ "_YEAR");
	// //flag = ccbsRow.get("col_"+ colNum+ "_FLAG");
	// flag = ccbsRow.get("col_"+ (colNum+1));
	// valueType = ccbsRow.get("col_"+ colNum+ "_VALUE_TYPE");
	// System.out.println("******************* GRID StartProperties element"+element);
	// //
	// if((element.equals("Domestic Availability"))||(element.equals("Total Utilization"))||(element.equals("Domestic Utilization"))||(element.equals("Per Cap. Food Use")))
	// // {
	// // System.out.println("Col "+colNum+" StartProperties");
	// //// System.out.println("Col "+colNum+" Value: "+ ccbsRow.get("col_"+
	// colNum));
	// //// System.out.println("Col "+colNum+" Element: "+ ccbsRow.get("col_"+
	// colNum+ "_ELEMENT"));
	// //// System.out.println("Col "+colNum+" Element Unit : "+
	// ccbsRow.get("col_"+ colNum+ "_ELEMENT_UNIT"));
	// //// System.out.println("Col "+colNum+" Forecast : "+ ccbsRow.get("col_"+
	// colNum+ "_FORECAST"));
	// //// System.out.println("Col "+colNum+" Year : "+ ccbsRow.get("col_"+
	// colNum+ "_YEAR"));
	// //// System.out.println("Col "+colNum+" FLAG : "+ ccbsRow.get("col_"+
	// colNum+ "_FLAG"));
	// //
	// // System.out.println("Col "+colNum+" Value: "+ value);
	// // System.out.println("Col "+colNum+" Element: "+ element);
	// // System.out.println("Col "+colNum+" Element Unit : "+ elementUnit);
	// // System.out.println("Col "+colNum+" Forecast : "+ forecast);
	// // System.out.println("Col "+colNum+" Year : "+ year);
	// // System.out.println("Col "+colNum+" FLAG : "+ flag);
	// // System.out.println("Col "+colNum+" valueType : "+ valueType);
	// // }
	//	
	//							
	// // System.out.println("EndProperties");
	// // System.out.println("CCBS.NAMES_CODES_FIELD element "+ element);
	// // Set<String> keySet2 = CCBS.NAMES_CODES_FIELD.keySet();
	// // for(String key: keySet2)
	// // {
	// // System.out.println("CCBS.NAMES_CODES_FIELD key "+ key);
	// // System.out.println("CCBS.NAMES_CODES_FIELD Value "+
	// CCBS.NAMES_CODES_FIELD.get(key));
	// // }
	// if(CCBS.NAMES_CODES_FIELD.get(element)!=null)
	// {
	// // System.out.println(" If CCBS.NAMES_CODES_FIELD.get(element): "+
	// CCBS.NAMES_CODES_FIELD.get(element));
	// Set<String> keySet = CCBS.NAMES_CODES_FIELD.get(element).keySet();
	// //There is just an element in this list: code, name of the element in the
	// database
	// for(String key: keySet)
	// {
	// elementCode = key;
	// elementName = CCBS.NAMES_CODES_FIELD.get(element).get(elementCode);
	// break;
	// }
	// ClientCbsDatasetResult datasetResult = new
	// ClientCbsDatasetResult(dataSource, countryCode, country, commodityCode,
	// commodity, elementCode, elementName, elementUnit, year, value, flag,
	// forecast, valueType);
	// //Insert the result
	// foundElement = true;
	// datasetResultList.add(datasetResult);
	// }
	// else if(CCBS.OTHER_NAMES_CODES_FIELD.get(element)!=null)
	// {
	// //
	// System.out.println(" Else CCBS.OTHER_NAMES_CODES_FIELD.get(element): "+
	// CCBS.OTHER_NAMES_CODES_FIELD.get(element));
	// Set<String> keySet = CCBS.OTHER_NAMES_CODES_FIELD.get(element).keySet();
	// //There is just an element in this list: code, name of the element in the
	// database
	// for(String key: keySet)
	// {
	// elementCode = key;
	// elementName = CCBS.OTHER_NAMES_CODES_FIELD.get(element).get(elementCode);
	// break;
	// }
	// ClientCbsDatasetResult datasetResult = new
	// ClientCbsDatasetResult(dataSource, countryCode, country, commodityCode,
	// commodity, elementCode, elementName, elementUnit, year, value, flag,
	// forecast, valueType);
	// //Insert the result
	// foundElement = true;
	// datasetResultList.add(datasetResult);
	// // System.out.println(" DataSource: "+ datasetResult.getDatabase());
	// // System.out.println(" CountryCode: "+ datasetResult.getRegion_code());
	// // System.out.println(" CountryName: "+ datasetResult.getRegion_name());
	// // System.out.println(" CommodityCode: "+
	// datasetResult.getProduct_code());
	// // System.out.println(" CommodityName: "+
	// datasetResult.getProduct_name());
	// // System.out.println(" Value: "+ datasetResult.getValue());
	// // System.out.println(" ElementCode: "+ datasetResult.getElement_code());
	// // System.out.println(" ElementName: "+ datasetResult.getElement_name());
	// // System.out.println(" ElementUnit : "+ datasetResult.getUnits());
	// // System.out.println(" Forecast : "+ datasetResult.getMonth());
	// // System.out.println(" Year : "+ datasetResult.getYear());
	// // System.out.println(" Flag : "+ datasetResult.getAnnotation());
	// // System.out.println(" ValueType : "+ datasetResult.getValueType());
	// }
	// else
	// {
	// System.out.println(" Else NO!!!!!! ");
	// }
	// }
	// //Insert the list of the results
	// if(foundElement)
	// {
	// timeSeries.getElement().add(datasetResultList);
	// }
	// // System.out.println("");
	// // System.out.println("");
	// i++;
	// }
	// System.out.println("******************* GRID StartProperties");
	// int iRow =0;
	// int iElement =0;
	// for(List<ClientCbsDatasetResult> list:timeSeries.getElement())
	// {
	// //For Each Row
	// iElement =0;
	// System.out.println("******** Row "+ iRow);
	// for(ClientCbsDatasetResult dataResult: list)
	// {
	// System.out.println("******** Row "+ iRow + " Element "+ iElement);
	// //For Each Element
	// System.out.println(" Id: "+ dataResult.getId());
	// System.out.println(" DataSource: "+ dataResult.getDatabase());
	// System.out.println(" CountryCode: "+ dataResult.getRegion_code());
	// System.out.println(" CountryName: "+ dataResult.getRegion_name());
	// System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
	// System.out.println(" CommodityName: "+ dataResult.getProduct_name());
	// System.out.println(" Value: "+ dataResult.getValue());
	// System.out.println(" ElementCode: "+ dataResult.getElement_code());
	// System.out.println(" ElementName: "+ dataResult.getElement_name());
	// System.out.println(" ElementUnit : "+ dataResult.getUnits());
	// System.out.println(" Forecast : "+ dataResult.getMonth());
	// System.out.println(" Year : "+ dataResult.getYear());
	// System.out.println(" Flag : "+ dataResult.getAnnotation());
	// System.out.println(" ValueType : "+ dataResult.getValueType());
	// iElement++;
	// }
	// iRow++;
	// }
	// System.out.println("******************* GRID EndProperties");
	//					
	// System.out.println("******************* SMALL FORM StartProperties size = "+
	// CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
	// int iCell= 0;
	// for(ClientCbsDatasetResult dataResult:
	// CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
	// {
	// System.out.println("******** iCell "+ iCell);
	// //For Each Element
	// System.out.println(" Id: "+ dataResult.getId());
	// System.out.println(" DataSource: "+ dataResult.getDatabase());
	// System.out.println(" CountryCode: "+ dataResult.getRegion_code());
	// System.out.println(" CountryName: "+ dataResult.getRegion_name());
	// System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
	// System.out.println(" CommodityName: "+ dataResult.getProduct_name());
	// System.out.println(" Value: "+ dataResult.getValue());
	// System.out.println(" ElementCode: "+ dataResult.getElement_code());
	// System.out.println(" ElementName: "+ dataResult.getElement_name());
	// System.out.println(" ElementUnit : "+ dataResult.getUnits());
	// System.out.println(" Forecast : "+ dataResult.getMonth());
	// System.out.println(" Year : "+ dataResult.getYear());
	// System.out.println(" Flag : "+ dataResult.getAnnotation());
	// System.out.println(" ValueType : "+ dataResult.getValueType());
	// iCell++;
	// }
	// System.out.println("******************* SMALL FORM EndProperties");
	// w.hide();
	// MessageBox.info("Action", "The data have been saved", null);
	// }
	// else
	// {
	// MessageBox.alert("Info",
	// "This is a training version! The data can not be saved!", null);
	// //Look saveAllChanges method in TableToolbarController in
	// org.fao.fenix.web.modules.table.client.control
	// // List<Record> store = grid.getStore().getModifiedRecords();
	// // for(int i=0; i<store.size(); i++)
	// // {
	// // Record record = store.get(i);
	// //// store..
	// //// record.getModel();
	// //// CCBSModel c = store.get(i);
	// //
	// System.out.println("******************************STORE i = "+i+" value "+store.get(i).toString());
	// // }
	// //
	// }
	//				
	// }
	// };
	// }

	public Grid makeGrid() {
		final EditorGrid grid = new EditorGrid<CCBSModel>(store, cm);

		grid.addListener(Events.BeforeEdit, new Listener<GridEvent>() {
			public void handleEvent(GridEvent gridEvent) {
				if (!isCellEditable(gridEvent.getRowIndex(), gridEvent
						.getColIndex()))
					gridEvent.setCancelled(true);
			}
		});
		grid.addListener(Events.AfterEdit, new Listener<GridEvent>() {
			public void handleEvent(GridEvent gridEvent) {
				if (gridEvent != null) {
					CCBS.CHANGES_IN_THE_GRID = true;
					int rowNum = gridEvent.getRowIndex();
					int colNum = gridEvent.getColIndex() - 1;

					Cell cell = page.getCell(rowNum, colNum);
					boolean correctFlag = false;
					boolean toUpdate = false;
					if (gridEvent.getValue() != null) {
						String value = gridEvent.getValue().toString();
						if ((colNum % 2) != 0) {
							// Case Flag
							correctFlag = checkIfCorrectFlag(value);
							if (!correctFlag) {
								String allFlagToShow = stringWidthAllFlag();
								MessageBox.alert("Alert",
										"Wrong flag. Availbale flags are: "
												+ allFlagToShow, null);
							}
						}

						if (((colNum % 2) == 0) || (correctFlag)) {
							cell.setText(value);
							System.out
									.println("Class: !!!!PagePanel  Function: handleEvent Text: value "
											+ value
											+ "row "
											+ rowNum
											+ " col "
											+ colNum);
							cell.setValid(false);
							toUpdate = true;
						}
						if(rowNum == CCBS.CLOSING_STOCKS_ROW_NUM)
						{
							System.out.println("Class: PagePanel  Function: handleEvent Text: if(rowNum == CCBS.CLOSING_STOCKS_ROW_NUM) ");
							String header = "";
							if(colNum == CCBS.FORECAST_COL_NUM)
							{
								//Last column
								System.out.println("Class: PagePanel  Function: handleEvent Text: last column ");
								ColumnConfig colConf = grid.getColumnModel().getColumn(colNum+1);
								if (colConf.getWidget() != null) {
									
									System.out.println("Class: PagePanel  Function: handleEvent Text: if (colConf.getWidget() != null) { ");
									HorizontalPanel hp = (HorizontalPanel) colConf.getWidget();
									ComboBox<AMISCodesModelData> comboYear = (ComboBox<AMISCodesModelData>) hp.getItemByItemId("ComboYear");
									ComboBox<MonthForecast> comboMonth = (ComboBox<MonthForecast>) hp.getItemByItemId("ComboMonth");
									header = comboYear.getValue().getLabel();
									if((header!=null)&&(header!="")&&(header.length()>4))
									{
										header = header.substring(0, 4);
									}
								}
							}
							else
							{
								//Not last column
								System.out.println("Class: PagePanel  Function: handleEvent Text: not last column ");
								header = decoration.getColHeader(colNum).substring(0,4);
							}
							System.out.println("Class: PagePanel  Function: handleEvent Text: header "+ header);
							//Storing the next year
							int nextYear = Integer.parseInt(header)+1;
							String nextYearString = ""+ nextYear;
							System.out.println("Class: PagePanel  Function: handleEvent Text: header "+ header);
							System.out.println("Class: PagePanel  Function: handleEvent Text: nextYearString "+ nextYearString);
							System.out.println("Class: PagePanel  Function: handleEvent Text: value "+ value+ " setOpeningClosingStocksValues ");
							//Update the closing stocks value for that year
							CCBSParametersPanel.setOpeningClosingStocksValues(header, nextYearString, value);
							System.out.println("Class: PagePanel  Function: handleEvent Text: After setOpeningClosingStocksValues colNum " + colNum);
							if((colNum == CCBS.BEFORE_FORECAST_COL_NUM)||(colNum == (CCBS.BEFORE_FORECAST_COL_NUM-2)))
							{
								System.out.println("Class: PagePanel  Function: handleEvent Text: After setOpeningClosingStocksValues CCBS.BEFORE_FORECAST_COL_NUM " + CCBS.BEFORE_FORECAST_COL_NUM);
								//CCBSParametersPanel.setOpeningClosingStocksValues(header, nextYearString, value);
								//Listener for the Closing stocks of the last column before the Forecast column
								
								//Change the value of the opening stocks or closing stocks of the last column: two cases
								
								String yearValue = "";
								ColumnConfig colConf;
								if(colNum == CCBS.BEFORE_FORECAST_COL_NUM)
								{
									colConf = grid.getColumnModel().getColumn(colNum+3);
								}								
								else
								{
									colConf = grid.getColumnModel().getColumn(colNum+5);
								}
								// System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: colConf.getWidget()= "+colConf.getWidget());
								if (colConf.getWidget() != null) {
									HorizontalPanel hp = (HorizontalPanel) colConf.getWidget();
									ComboBox<AMISCodesModelData> comboYear = (ComboBox<AMISCodesModelData>) hp.getItemByItemId("ComboYear");
									ComboBox<MonthForecast> comboMonth = (ComboBox<MonthForecast>) hp.getItemByItemId("ComboMonth");
									yearValue = comboYear.getValue().getLabel();
								}
								System.out.println("Class: PagePanel  Function: handleEvent Text: After setOpeningClosingStocksValues yearValue before if " + yearValue);
								if((yearValue!=null)&&(yearValue!="")&&(yearValue.length()>4))
								{
									yearValue = yearValue.substring(0, 4);
									System.out.println("Class: PagePanel  Function: handleEvent Text: After setOpeningClosingStocksValues yearValue in if " + yearValue);
									CCBSStore ccbsStore = (CCBSStore)grid.getStore();								
									int lastColNum;
									if(colNum == CCBS.BEFORE_FORECAST_COL_NUM)
									{
										lastColNum = colNum +2;
									}	
									else
									{
										lastColNum = colNum +4;
									}
									//Take the value for Closing Stocks from the hashmap OPENING_CLOSING_STOCKS_HASHMAP
									String openingClosingStocksValues[]= CCBSParametersPanel.getOpeningClosingStocksValues(yearValue);
									//CASE one: the year of the last column is the same of the previous one (same Closing Stocks value)
									//To need to change the value of the closing stocks cell
									System.out.println("Class: PagePanel  Function: handleEvent Text: After setOpeningClosingStocksValues before if(yearValue.equals(header)) header= "+ header+" yearValue= " +yearValue + " openingClosingStocksValues[0] "+ openingClosingStocksValues[0]+" openingClosingStocksValues[1] "+ openingClosingStocksValues[1]);
								//	if(yearValue.equals(header))
								//	{
										System.out.println("Class: PagePanel  Function: handleEvent Text: After setOpeningClosingStocksValues if(yearValue.equals(header))");
										CCBSModel ccbsModel = (CCBSModel)grid.getStore().getAt(CCBS.CLOSING_STOCKS_ROW_NUM);
										ccbsModel.set("col_" + lastColNum, openingClosingStocksValues[1]);
										System.out.println("Class: PagePanel  Function: handleEvent Text: if(yearValue.equals(header)) Before update Page ");
										ccbsStore.updatePage();
										System.out.println("Class: PagePanel  Function: handleEvent Text: if(yearValue.equals(header)) After update Page ");
										Cell cell1 = page.getCell(CCBS.CLOSING_STOCKS_ROW_NUM, lastColNum);
										cell1.setText(openingClosingStocksValues[1]);
										cell1.setValid(false);									
										try 
										{									
											//page.getBook().update(openingClosingStocksValues[1]);
											page.getBook().update();
											System.out.println("Class: PagePanel  Function: handleEvent Text: if(yearValue.equals(header)) After page.getBook().update(); ");
										} catch (Exception e) {
											
											e.printStackTrace();
										}
										//Update the Opening stocks value of the last column(forecast)
										ccbsModel = (CCBSModel)grid.getStore().getAt(CCBS.OPENING_STOCKS_ROW_NUM);
										ccbsModel.set("col_" + lastColNum, openingClosingStocksValues[0]);
										ccbsStore.updatePage();
										cell1 = page.getCell(CCBS.OPENING_STOCKS_ROW_NUM, lastColNum);
										cell1.setText(openingClosingStocksValues[0]);
										cell1.setValid(false);									
										try 
										{									
											page.getBook().update(openingClosingStocksValues[0]);
											//page.getBook().update();
											System.out.println("Class: PagePanel  Function: handleEvent Text: if(yearValue.equals(header)) After page.getBook().update(); ");
										} catch (Exception e) {
											
											e.printStackTrace();
										}
								//	}
//									else
//									{
//										//CASE two: the year of the last column is the next of the second previous column 
//										//(Closing Stocks value of the previus year is equal of the Opening Stocks of the last column)
//										//To need to change the value of the opening stocks cell
//										CCBSModel ccbsModel = (CCBSModel)grid.getStore().getAt(CCBS.OPENING_STOCKS_ROW_NUM);
//										ccbsModel.set("col_" + lastColNum, openingClosingStocksValues[1]);
//										ccbsStore.updatePage();
//										Cell cell1 = page.getCell(CCBS.OPENING_STOCKS_ROW_NUM, lastColNum);
//										cell1.setText(openingClosingStocksValues[1]);
//										cell1.setValid(false);									
//										try 
//										{									
//											page.getBook().update(openingClosingStocksValues[1]);
//										} catch (Exception e) {
//											
//											e.printStackTrace();
//										}
//									}
								}
							}
						}
					
					} else {
						cell.setText("");
						cell.setValid(false);
						toUpdate = true;
						// System.out.println("Class: PagePanel  Function: handleEvent Text:ELSE value "+gridEvent.getValue());
					}
					try {
						if (toUpdate) {
							System.out
									.println("Class: PagePanel  Function: handleEvent Text: Before page.getBook().update(); in if correctFlag "
											+ correctFlag);
							page.getBook().update();
						} else {
							System.out
									.println("Class: PagePanel  Function: handleEvent Text: Before page.getBook().update(); in else correctFlag "
											+ correctFlag);
						}
					} catch (Exception e) {
						FenixAlert.error("Error", "Recalculation failed");

						e.printStackTrace();
					}
					
				}
			}

			private String stringWidthAllFlag() {
				String allFlag = "";
				int dimFlagList = CCBS.FLAGS.getCount();
				for (int i = 0; i < dimFlagList; i++) {
					if (CCBS.FLAGS.getAt(i).getCode().equals("")) {
						allFlag = allFlag.concat("' ");
					} else {
						allFlag = allFlag.concat("'"
								+ CCBS.FLAGS.getAt(i).getCode());
					}
					if (i != (dimFlagList - 1)) {
						allFlag = allFlag.concat("', ");
					} else {
						allFlag = allFlag.concat("'.");
					}
				}
				return allFlag;
			}

			private boolean checkIfCorrectFlag(String flagToCheck) {
				boolean correctFlag = false;
				for (int i = 0; i < CCBS.FLAGS.getCount(); i++) {
					System.out
							.println("Class: PagePanel  Function: checkIfCorrectFlag Text: CCBS.FLAGS.getAt(i) "
									+ CCBS.FLAGS.getAt(i).getCode());
					System.out
							.println("Class: PagePanel  Function: checkIfCorrectFlag Text: flagToCheck "
									+ flagToCheck);
					if (flagToCheck.equals(CCBS.FLAGS.getAt(i).getCode())) {
						System.out
								.println("Class: PagePanel  Function: checkIfCorrectFlag Text: equal ");
						correctFlag = true;
						break;
					}
				}
				return correctFlag;
			}
		});

		grid.addListener(Events.OnClick, new Listener<GridEvent>() {
			public void handleEvent(GridEvent gridEvent) {
				// System.out.println("One click!!!");

				// String value = gridEvent.getValue().toString();
				final int rowNum = gridEvent.getRowIndex();
				final int colNum = gridEvent.getColIndex() - 1;
				// System.out.println("out rowNum "+rowNum+" colNum "+colNum);
				if ((rowNum >= 0) || (colNum >= 0)) {
					// System.out.println("rowNum "+rowNum+" colNum "+colNum);

					if ((colNum >= 0) && (colNum % 2 == 0)) {
						if ((rowNum == CCBS.PRODUCTION_ROW_NUM)&&(Integer.parseInt(decoration.getCropNum())!=0)) {
							final SpecificRowWindow otherUses = new SpecificRowWindow();
							PagePanelVariables ppv = new PagePanelVariables();
							otherUses.setSize(410, 640);
							otherUses.setTitle("Production Form");
							otherUses.getWindow().setClosable(false);
							FormPanel form2 = new FormPanel();
							form2.setHeaderVisible(false);
							form2.setWidth(350);
							form2.setLabelWidth(100);
							form2.setLayout(new FlowLayout());
							form2.removeAll();
							System.out
									.println("**************decoration.getCommodity() "
											+ decoration.getCommodity());
							System.out
									.println("**************decoration.getCountryName() "
											+ decoration.getCountryName());
							System.out
									.println("**************decoration.getCropNum() "
											+ decoration.getCropNum());

							// Check if it's the first time that the user click
							// on this cell or not
							otherUses.cropInTheGlobalList(rowNum, colNum, ppv);
							CCBSStore ccbsStore = (CCBSStore) grid.getStore();
							String totalProductionValue = ccbsStore.getAt(
									rowNum).get("col_" + colNum);
							String totalProductionFlag = ccbsStore
									.getAt(rowNum).get("col_" + (colNum + 1));
							String totalAreaHarvestedValue = ccbsStore.getAt(
									rowNum + 1).get("col_" + colNum);
							String totalAreaHarvestedFlag = ccbsStore.getAt(
									rowNum + 1).get("col_" + (colNum + 1));
							String totalYieldValue = ccbsStore
									.getAt(rowNum + 2).get("col_" + colNum);
							String totalYieldFlag = ccbsStore.getAt(rowNum + 2)
									.get("col_" + (colNum + 1));
							ppv.totalProductionFound = true;
							ppv.totalAreaHarvestedFound = true;
							ppv.totalYieldFound = true;
							if ((totalProductionValue != null)
									&& (totalProductionValue != "")) {
								ppv.totalNumberFieldProdValueFound = Double
										.parseDouble(totalProductionValue);
							}
							if ((totalAreaHarvestedValue != null)
									&& (totalAreaHarvestedValue != "")) {
								ppv.totalNumberFieldAreaValueFound = Double
										.parseDouble(totalAreaHarvestedValue);
							}
							if ((totalYieldValue != null)
									&& (totalYieldValue != "")) {
								ppv.totalNumberFieldYieldValueFound = Double
										.parseDouble(totalYieldValue);
							}
							ppv.totalComboProdValueFound = totalProductionFlag;
							ppv.totalComboAreaValueFound = totalAreaHarvestedFlag;
							ppv.totalComboYieldValueFound = totalYieldFlag;
							// Check if I can take this information from the
							// database

							ProductionFormField productionFormField = new ProductionFormField(
									"First Crop", 130, 5);
							productionFormField
									.setLabelProperty("Production:",
											"Area Harvested:", "Yield:",
											"ccbs-FlagLabel", "130px", "130px",
											"130px");
							productionFormField.setWithnumberfield(true);
							productionFormField.setNumberFieldProperty(
									"Production", "Area Harvested", "Yield",
									"FirstProduction", "FirstAreaHarvested",
									"FirstYield", false, 120);
							productionFormField.setComboProperty(false,
									"firstProductionListener",
									"firstAreaHarvestedListener",
									"firstYieldListener");
							final FieldSet fieldSetOne = otherUses
									.createFieldSetForProduction(
											productionFormField, ppv);
							fieldSetOne.disable();
							form2.add(fieldSetOne);

							productionFormField = new ProductionFormField(
									"Second Crop", 130, 5);
							productionFormField
									.setLabelProperty("Production:",
											"Area Harvested:", "Yield:",
											"ccbs-FlagLabel", "130px", "130px",
											"130px");
							productionFormField.setWithnumberfield(true);
							productionFormField.setNumberFieldProperty(
									"Production", "Area Harvested", "Yield",
									"SecondProduction", "SecondAreaHarvested",
									"SecondYield", false, 120);
							productionFormField.setComboProperty(false,
									"secondProductionListener",
									"secondAreaHarvestedListener",
									"secondYieldListener");
							final FieldSet fieldSetTwo = otherUses
									.createFieldSetForProduction(
											productionFormField, ppv);
							fieldSetTwo.disable();
							form2.add(fieldSetTwo);

							productionFormField = new ProductionFormField(
									"Third Crop", 130, 5);
							productionFormField
									.setLabelProperty("Production:",
											"Area Harvested:", "Yield:",
											"ccbs-FlagLabel", "130px", "130px",
											"130px");
							productionFormField.setWithnumberfield(true);
							productionFormField.setNumberFieldProperty(
									"Production", "Area Harvested", "Yield",
									"ThirdProduction", "ThirdAreaHarvested",
									"ThirdYield", false, 120);
							productionFormField.setComboProperty(false,
									"thirdProductionListener",
									"thirdAreaHarvestedListener",
									"thirdYieldListener");
							final FieldSet fieldSetThree = otherUses
									.createFieldSetForProduction(
											productionFormField, ppv);
							fieldSetThree.disable();
							form2.add(fieldSetThree);

							System.out
									.println("Class: PagePanel  Text : decoration.getCropNum() *"
											+ decoration.getCropNum() + "*");
							if (Integer.parseInt(decoration.getCropNum()) > 0) {
								fieldSetOne.enable();
							}
							if (Integer.parseInt(decoration.getCropNum()) > 1) {
								fieldSetTwo.enable();
							}
							if (Integer.parseInt(decoration.getCropNum()) > 2) {
								fieldSetThree.enable();
							}
							productionFormField = new ProductionFormField(
									"Total Annotations", 130, 5);
							productionFormField.setLabelProperty(
									"Production Flag:", "Area Harvested Flag:",
									"Yield Flag:", "ccbs-FlagLabel", "170px",
									"170px", "170px");
							productionFormField.setWithnumberfield(false);
							productionFormField.setComboProperty(false,
									"totalProductionListener",
									"totalAreaHarvestedListener",
									"totalYieldListener");
							final FieldSet fieldSetFour = otherUses
									.createFieldSetForProduction(
											productionFormField, ppv);
							form2.add(fieldSetFour);

							HorizontalPanel hpMainSave = new HorizontalPanel();

							HorizontalPanel vpMainSave = new HorizontalPanel();
							vpMainSave
									.setHorizontalAlign(HorizontalAlignment.RIGHT);
							vpMainSave
									.setVerticalAlign(VerticalAlignment.MIDDLE);
							vpMainSave.setTableWidth("100%");

							Button mainCancel = new Button("Cancel",
									new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											otherUses.getWindow().hide();
										}
									});
							Button mainSave = new Button("Save",
									new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											CCBS.CHANGES_IN_THE_GRID = true;
											// ProductionFormField
											// productionFormResult =
											// otherUses.getFieldValueForProduction(fieldSetOne,
											// fieldSetTwo, fieldSetThree,
											// decoration.getCropNum(), rowNum,
											// colNum,
											// decoration.getDataSource(), dec);
											ProductionFormField productionFormResult = otherUses
													.getFieldValueForProduction(
															fieldSetOne,
															fieldSetTwo,
															fieldSetThree,
															rowNum, colNum,
															decoration);
											MessageBox
													.info(
															"Action",
															"The \"Production\" field has been updated",
															null);

											CCBSStore ccbsStore = (CCBSStore) grid
													.getStore();
											int areaHarvestedRow = 0;
											int yieldRow = 0;
											String productionString = "";
											boolean productionNotDefined = false;
											boolean areaHarvestedNotDefined = false;
											boolean yieldNotDefined = false;
											for (int rowNum = 0; rowNum < decoration
													.numRows(); rowNum++) {
												if (decoration
														.getRowHeader(rowNum)
														.equalsIgnoreCase(
																"Area Harvested")) {
													areaHarvestedRow = rowNum;
												}
												if (decoration.getRowHeader(
														rowNum)
														.equalsIgnoreCase(
																"Yield")) {
													yieldRow = rowNum;
												}
											}
											int index = 0;
											for (index = 0; index < productionFormResult
													.getProductionFlag().length; index++) {
												if (productionFormResult
														.getProductionFlag()[index]) {
													break;
												}
											}
											if (index == productionFormResult
													.getProductionFlag().length) {
												ccbsStore.getAt(rowNum).set(
														"col_" + colNum, "");
												ccbsStore.getAt(rowNum).set(
														"col_" + (colNum + 1),
														"");
												productionNotDefined = true;
											} else {
												System.out
														.println("ELSE totalProductionFlag "
																+ PagePanelVariables.totalProductionFlag);
												ccbsStore
														.getAt(rowNum)
														.set(
																"col_" + colNum,
																""
																		+ productionFormResult
																				.getProduction());
												if (PagePanelVariables.totalProductionFlag
														.equals("No Flag")) {
													PagePanelVariables.totalProductionFlag = "";
												}
												ccbsStore
														.getAt(rowNum)
														.set(
																"col_"
																		+ (colNum + 1),
																""
																		+ PagePanelVariables.totalProductionFlag);
												productionString = ""
														+ productionFormResult
																.getProduction();
											}
											for (index = 0; index < productionFormResult
													.getAreaHarvestedFlag().length; index++) {
												if (productionFormResult
														.getAreaHarvestedFlag()[index]) {
													break;
												}
											}
											if (index == productionFormResult
													.getAreaHarvestedFlag().length) {
												ccbsStore.getAt(
														areaHarvestedRow).set(
														"col_" + colNum, "");
												ccbsStore.getAt(
														areaHarvestedRow).set(
														"col_" + (colNum + 1),
														"");
												areaHarvestedNotDefined = true;
											} else {
												System.out
														.println("ELSE totalAreaHarvestedFlag "
																+ PagePanelVariables.totalAreaHarvestedFlag);
												ccbsStore
														.getAt(areaHarvestedRow)
														.set(
																"col_" + colNum,
																""
																		+ productionFormResult
																				.getAreaHarvested());
												if (PagePanelVariables.totalAreaHarvestedFlag
														.equals("No Flag")) {
													PagePanelVariables.totalAreaHarvestedFlag = "";
												}
												ccbsStore
														.getAt(areaHarvestedRow)
														.set(
																"col_"
																		+ (colNum + 1),
																""
																		+ PagePanelVariables.totalAreaHarvestedFlag);
											}
											for (index = 0; index < productionFormResult
													.getYieldFlag().length; index++) {
												if (productionFormResult
														.getYieldFlag()[index]) {
													break;
												}
											}
											if (index == productionFormResult
													.getYieldFlag().length) {
												ccbsStore.getAt(yieldRow).set(
														"col_" + colNum, "");
												ccbsStore.getAt(yieldRow).set(
														"col_" + (colNum + 1),
														"");
												yieldNotDefined = true;
											} else {
												System.out
														.println("ELSE totalYieldFlag "
																+ PagePanelVariables.totalYieldFlag);
												ccbsStore
														.getAt(yieldRow)
														.set(
																"col_" + colNum,
																""
																		+ productionFormResult
																				.getAverage());
												if (PagePanelVariables.totalYieldFlag
														.equals("No Flag")) {
													PagePanelVariables.totalYieldFlag = "";
												}
												ccbsStore
														.getAt(yieldRow)
														.set(
																"col_"
																		+ (colNum + 1),
																""
																		+ PagePanelVariables.totalYieldFlag);
											}
											ccbsStore.updatePage();

											Cell cell = page.getCell(rowNum,
													colNum);
											cell.setText(productionString);
											cell.setValid(false);
											cell = page.getCell(rowNum,
													(colNum + 1));
											cell
													.setText(PagePanelVariables.totalProductionFlag);
											cell.setValid(false);

											cell = page.getCell(
													areaHarvestedRow, colNum);
											cell
													.setText(""
															+ productionFormResult
																	.getAreaHarvested());
											cell.setValid(false);
											cell = page.getCell(
													areaHarvestedRow,
													(colNum + 1));
											cell
													.setText(PagePanelVariables.totalAreaHarvestedFlag);
											cell.setValid(false);

											cell = page.getCell(yieldRow,
													colNum);
											cell.setText(""
													+ productionFormResult
															.getAverage());
											cell.setValid(false);
											cell = page.getCell(yieldRow,
													(colNum + 1));
											cell
													.setText(PagePanelVariables.totalYieldFlag);
											cell.setValid(false);

											try {
												System.out
														.println("Class: PagePanel Function: ComponentSelected Text: Before page.getBook().update() ");
												page.getBook().update();

												if (productionNotDefined) {
													ccbsStore
															.getAt(rowNum)
															.set(
																	"col_"
																			+ colNum,
																	"");
													ccbsStore
															.getAt(rowNum)
															.set(
																	"col_"
																			+ (colNum + 1),
																	"");
												} else {
													ccbsStore
															.getAt(rowNum)
															.set(
																	"col_"
																			+ colNum,
																	""
																			+ productionFormResult
																					.getProduction());
													ccbsStore
															.getAt(rowNum)
															.set(
																	"col_"
																			+ (colNum + 1),
																	""
																			+ PagePanelVariables.totalProductionFlag);
												}

												if (areaHarvestedNotDefined) {
													ccbsStore
															.getAt(
																	areaHarvestedRow)
															.set(
																	"col_"
																			+ colNum,
																	"");
													ccbsStore
															.getAt(
																	areaHarvestedRow)
															.set(
																	"col_"
																			+ (colNum + 1),
																	"");
												} else {
													ccbsStore
															.getAt(
																	areaHarvestedRow)
															.set(
																	"col_"
																			+ colNum,
																	""
																			+ productionFormResult
																					.getAreaHarvested());
													ccbsStore
															.getAt(
																	areaHarvestedRow)
															.set(
																	"col_"
																			+ (colNum + 1),
																	""
																			+ PagePanelVariables.totalAreaHarvestedFlag);
												}

												if (yieldNotDefined) {
													ccbsStore
															.getAt(yieldRow)
															.set(
																	"col_"
																			+ colNum,
																	"");
													ccbsStore
															.getAt(yieldRow)
															.set(
																	"col_"
																			+ (colNum + 1),
																	"");
												} else {
													ccbsStore
															.getAt(yieldRow)
															.set(
																	"col_"
																			+ colNum,
																	""
																			+ productionFormResult
																					.getAverage());
													ccbsStore
															.getAt(yieldRow)
															.set(
																	"col_"
																			+ (colNum + 1),
																	""
																			+ PagePanelVariables.totalYieldFlag);
												}
												ccbsStore.updatePage();
											} catch (Exception e) {
												FenixAlert.error("Error",
														"Recalculation failed");

												e.printStackTrace();
											}

											System.out
													.println("Class: PagePanel Function: ComponentSelected ->Text: decoration.getRowHeader(rowNum) "
															+ decoration
																	.getRowHeader(rowNum));
											Map<String, Object> map = ccbsStore
													.getAt(rowNum)
													.getProperties();
											Set<String> key = map.keySet();
											System.out
													.println("Class: PagePanel Function: ComponentSelected ->Text: key "
															+ key);
											// for(String elem:key)
											// {
											// System.out.println("Class: PagePanel Function: ComponentSelected ->Text: map.get(key) "+map.get(key));
											// }

											otherUses.getWindow().hide();
										}
									});
							FormButtonBinding binding = new FormButtonBinding(
									form2);
							binding.addButton(mainSave);
							hpMainSave.add(mainCancel);
							hpMainSave.add(mainSave);
							vpMainSave.add(hpMainSave);

							otherUses.fillCenterPart(form2);
							otherUses.getCenter()
									.setBottomComponent(vpMainSave);
							otherUses.getCenter().setHeaderVisible(false);
							otherUses.getCenter().layout();
							otherUses.show();
						}
						// if(rowNum == 14)
						// {
						// for(int i= 0; i<
						// PagePanelVariables.otherUsesFormArray.length; i++)
						// {
						// PagePanelVariables.otherUsesFormArray[i] = false;
						// }
						// mainSave = new Button("Save");
						// final Button save;
						// final SpecificRowWindow otherUses = new
						// SpecificRowWindow();
						// otherUses.build();
						// otherUses.setTitle("Other Uses Form");
						// otherUses.setSize(500, 650);
						//								
						// final OtherUsesFormField otherUsesFormField = new
						// OtherUsesFormField();
						// otherUsesFormField.setHtmlProperty();
						// Html htmlField = otherUsesFormField.getHtmlField();
						// //Other Uses by field
						// final FieldSet mainFieldSet =
						// otherUses.createMainFieldSetForOtherUses(otherUsesFormField,
						// mainSave);
						// //Industrial Use by field
						// final FieldSet fieldSet =
						// otherUses.createIndustrialUseFieldSetForOtherUses();
						//								
						// FormPanel formSmallFieldset = new FormPanel();
						// formSmallFieldset.setBodyBorder(false);
						// formSmallFieldset.setBorders(false);
						// formSmallFieldset.setHeaderVisible(false);
						// formSmallFieldset.setWidth(600);
						// formSmallFieldset.setLayout(new FlowLayout());
						// formSmallFieldset.removeAll();
						// formSmallFieldset.add(fieldSet);
						// //Other Uses Total
						// otherUses.resizeNorth(145);
						// final FieldSet fieldSetNorth =
						// otherUses.createTotalFieldSetForOtherUses(otherUsesFormField,
						// mainSave);
						// HorizontalPanel hpSave = new HorizontalPanel();
						//								
						// HorizontalPanel vpSave = new HorizontalPanel();
						// vpSave.setHorizontalAlign(HorizontalAlignment.RIGHT);
						// vpSave.setVerticalAlign(VerticalAlignment.MIDDLE);
						// vpSave.setTableWidth("100%");
						// save = new Button("Save", new
						// SelectionListener<ButtonEvent>() {
						// @Override
						// public void componentSelected(ButtonEvent ce) {
						// fieldSet.collapse();
						// double industrialUse = 0;
						// int indexItem=0;
						// String appValue;
						// String industrialUseString = "";
						// boolean fullValue[] = new boolean[5];
						// for(int i=0; i<fullValue.length; i++)
						// {
						// fullValue[i] = false;
						// }
						// for(Component component :fieldSet.getItems())
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: for(Component component :fieldSet.getItems()) indexItem = "+indexItem);
						// if(indexItem<5)
						// {
						// HorizontalPanel hp = (HorizontalPanel)component;
						// if(hp != null)
						// {
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Malt");
						// if(element!= null)
						// {
						// appValue = element.getValue().toString();
						// if((appValue != null)&&(!(appValue.equals(""))))
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: value = "+appValue);
						// industrialUse += Double.parseDouble(appValue);
						// fullValue[indexItem]= true;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "+industrialUse);
						// }
						// }
						// }
						// if(hp != null)
						// {
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Biofuel");
						// if(element!= null)
						// {
						// appValue = element.getValue().toString();
						// if((appValue != null)&&(!(appValue.equals(""))))
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: value = "+appValue);
						// industrialUse += Double.parseDouble(appValue);
						// fullValue[indexItem]= true;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "+industrialUse);
						// }
						// }
						// }
						// if(hp != null)
						// {
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Sweeteners");
						// if(element!= null)
						// {
						// appValue = element.getValue().toString();
						// if((appValue != null)&&(!(appValue.equals(""))))
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: value = "+appValue);
						// industrialUse += Double.parseDouble(appValue);
						// fullValue[indexItem]= true;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "+industrialUse);
						// }
						// }
						// }
						// if(hp != null)
						// {
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Starch");
						// if(element!= null)
						// {
						// appValue = element.getValue().toString();
						// if((appValue != null)&&(!(appValue.equals(""))))
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: value = "+appValue);
						// industrialUse += Double.parseDouble(appValue);
						// fullValue[indexItem]= true;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "+industrialUse);
						// }
						// }
						// }
						// if(hp != null)
						// {
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Others");
						// if(element!= null)
						// {
						// appValue = element.getValue().toString();
						// if((appValue != null)&&(!(appValue.equals(""))))
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: value = "+appValue);
						// industrialUse += Double.parseDouble(appValue);
						// fullValue[indexItem]= true;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "+industrialUse);
						// }
						// }
						// }
						// indexItem++;
						// }
						// else
						// {
						// break;
						// }
						// }
						// int index;
						// for(index=0; index<fullValue.length; index++)
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: index = "+index);
						// if(fullValue[index])
						// {
						// break;
						// }
						// }
						// if(index==fullValue.length)
						// {
						// industrialUseString = "";
						// }
						// else
						// {
						// industrialUseString = ""+industrialUse;
						// }
						//										
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() "+mainFieldSet.getItemCount());
						// for(Component component :mainFieldSet.getItems())
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 1111 ");
						// HorizontalPanel hp = (HorizontalPanel)component;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 2222 ");
						// if(hp != null)
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 3333 ");
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Industrial Use");
						// //System.out.println("Class: PagePanel Function: ComponentSelected Text: element.getFieldLabel() "+element.getFieldLabel());
						// if(element!=null)
						// {
						// element.setValue(industrialUse);
						// PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()]
						// = true;
						// PagePanelController.updateSaveButton(mainSave,
						// otherUsesFormField.getNorthDim(),
						// PagePanelVariables.otherUsesFormArray);
						//													
						// // fieldNotNull[2] = true;
						// // updateSaveButton(mainSave);
						// }
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse "+industrialUseString);
						// }
						// }
						// industrialUse=0;
						// }
						// });
						// //mainSave.disable();
						// FormButtonBinding binding = new
						// FormButtonBinding(formSmallFieldset);
						// binding.addButton(save);
						// hpSave.add(save);
						// vpSave.add(hpSave);
						// fieldSet.add(vpSave);
						//							
						// mainFieldSet.add(formSmallFieldset);
						//								
						// HorizontalPanel hpMainSave = new HorizontalPanel();
						//								
						// HorizontalPanel vpMainSave = new HorizontalPanel();
						// vpMainSave.setHorizontalAlign(HorizontalAlignment.RIGHT);
						// vpMainSave.setVerticalAlign(VerticalAlignment.MIDDLE);
						// vpMainSave.setTableWidth("100%");
						//								
						// final FormPanel form2 = new FormPanel();
						// form2.setHeaderVisible(false);
						// form2.setWidth(350);
						// form2.setLayout(new FlowLayout());
						// form2.removeAll();
						// form2.add(mainFieldSet);
						//								
						// final FormPanel form3 = new FormPanel();
						// form3.setHeaderVisible(false);
						// form3.setWidth(350);
						// form3.setLayout(new FlowLayout());
						// form3.removeAll();
						// form3.add(htmlField);
						// form3.add(FormattingUtils.addVSpace(5));
						// form3.add(fieldSetNorth);
						//								
						// Button mainCancel = new Button("Cancel", new
						// SelectionListener<ButtonEvent>() {
						// @Override
						// public void componentSelected(ButtonEvent ce) {
						// otherUses.getWindow().hide();
						// }
						// });
						// mainSave.setId("mainSaveButton");
						// mainSave.addSelectionListener(new
						// SelectionListener<ButtonEvent>() {
						// @Override
						// public void componentSelected(ButtonEvent ce) {
						// System.out.println("MainSave ON click!!!");
						// fieldSet.collapse();
						// double otherUsesTot = 0;
						// String appValue = "";
						// boolean isValidArray[] = new boolean[7];
						// String otherUsesFlag = "";
						// String otherUsesTotString = "";
						// boolean formFieldIsValid = false;
						// for(int i= 0; i< isValidArray.length; i++)
						// {
						// isValidArray[i] = false;
						// }
						//										
						// if(form3.isValid())
						// {
						// //Other Uses from Total Value
						// System.out.println("FORM TOTAL IS VALID");
						// totalOtherUses = true;
						// }
						// else
						// {
						// OtherUsesFormField otherUsesFormFieldResult =
						// otherUses.getOtherUseByFieldValue(mainFieldSet);
						// isValidArray =
						// otherUsesFormFieldResult.getIsValidArray();
						// otherUsesTot =
						// otherUsesFormFieldResult.getOtherUsesTot();
						// otherUsesFlag =
						// otherUsesFormFieldResult.getOtherUsesFlag();
						// }
						//										
						// int index =0;
						// for(index=0; index<isValidArray.length; index++)
						// {
						// if(!isValidArray[index])
						// {
						// break;
						// }
						// }
						// if(index==isValidArray.length)
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: if(index==isValidArray.length) :otherUsesTotString "+
						// otherUsesTotString);
						// //The form is valid
						// formFieldIsValid = true;
						// otherUsesTotString = ""+ otherUsesTot;
						// }
						// else
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: else(index==isValidArray.length) :otherUsesTotString "+
						// otherUsesTotString);
						// //The form is not valid
						// //Fill all field!
						// formFieldIsValid = false;
						// }
						// if(totalOtherUses)
						// {
						// HorizontalPanel hp;
						// for(Component component2 :fieldSetNorth.getItems())
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: Loop Component component :fieldSetNorth.getItems()");
						// //if((component!= null) &&
						// ((component.getTitle().equalsIgnoreCase("Other Uses"))))
						// if(component2!= null)
						// {
						// hp = (HorizontalPanel)component2;
						// if(hp != null)
						// {
						// NumberField element =
						// (NumberField)hp.getItemByItemId("Other Uses Num Field");
						// if(element!=null)
						// {
						// if(element.getValue() != null)
						// {
						// appValue = element.getValue().toString();
						// otherUsesTot = Double.parseDouble(appValue);
						// otherUsesTotString = ""+otherUsesTot;
						// System.out.println("Class: PagePanel Function: ComponentSelected Text:if(element.getValue() otherUsesTotString "+otherUsesTotString);
						// }
						// }
						//														
						// ComboBox<AMISCodesModelData> combo =
						// (ComboBox<AMISCodesModelData>)hp.getItemByItemId("Other Uses Combo");
						// if(combo!= null)
						// {
						// if(combo.getValue() != null)
						// {
						// otherUsesFlag = combo.getValue().getLabel();
						// }
						//						
						// }
						//													
						// }
						// }
						// }
						// }
						// if((totalOtherUses)||(formFieldIsValid))
						// {
						// System.out.println("                    otherUsesFlag "+otherUsesFlag);
						// System.out.println("                    otherUsesTotString "+otherUsesTotString);
						// //Setting the calculated cell on the grid
						// CCBSStore ccbsStore = (CCBSStore)grid.getStore();
						// ccbsStore.getAt(rowNum).set("col_" + colNum,
						// otherUsesTotString);
						// ccbsStore.updatePage();
						//											
						// Cell cell = page.getCell(rowNum, colNum);
						// cell.setText(otherUsesTotString);
						// cell.setValid(false);
						//
						// try
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: Before page.getBook().update() ");
						// page.getBook().update();
						//												
						// if(otherUsesTotString.equalsIgnoreCase(""))
						// {
						// ccbsStore.getAt(rowNum).set("col_" + colNum, "");
						// ccbsStore.getAt(rowNum).set("col_" + (colNum+1), "");
						// }
						// else
						// {
						// ccbsStore.getAt(rowNum).set("col_" + colNum,
						// ""+otherUsesTotString);
						// ccbsStore.getAt(rowNum).set("col_" + (colNum+1),
						// ""+otherUsesFlag);
						// }
						// ccbsStore.updatePage();
						// }
						// catch (Exception e)
						// {
						// FenixAlert.error("Error", "Recalculation failed");
						//	
						// e.printStackTrace();
						// }
						//											
						// Map<String, Object> map =
						// ccbsStore.getAt(rowNum).getProperties();
						// Set<String> key = map.keySet();
						// for(String elem:key)
						// {
						// System.out.println("Class: PagePanel Function: ComponentSelected Text: map.get(key) "+map.get(key));
						// }
						// MessageBox.info("Action",
						// "The \"Other Uses\" field has been updated", null);
						// otherUses.getWindow().hide();
						// }
						// else
						// {
						// MessageBox.info("Action",
						// "Fill in the required fields!", null);
						// }
						// }
						// });
						// PagePanelController.updateSaveButton(mainSave,
						// otherUsesFormField.getNorthDim(),
						// PagePanelVariables.otherUsesFormArray);
						//
						// // FormButtonBinding mainBinding = new
						// FormButtonBinding(form3);
						// // mainBinding.addButton(mainSave);
						//									
						// otherUses.fillNorthPart(form3);
						// otherUses.getNorth().setHeaderVisible(false);
						// otherUses.fillCenterPart(form2);
						// hpMainSave.add(mainCancel);
						// hpMainSave.add(mainSave);
						// vpMainSave.add(hpMainSave);
						// otherUses.getCenter().setBottomComponent(vpMainSave);
						// otherUses.getCenter().setHeaderVisible(false);
						// otherUses.show();
						// }
						if (rowNum == CCBS.OTHER_USES_ROW_NUM) {
							// for(int i= 0; i<
							// PagePanelVariables.otherUsesFormArray.length;
							// i++)
							// {
							// PagePanelVariables.otherUsesFormArray[i] = false;
							// }

							mainSave = new Button("Save");
							final Button save;
							final SpecificRowWindow otherUses = new SpecificRowWindow();
							otherUses.resizeNorth(145);
							PagePanelVariables ppv = new PagePanelVariables();
							otherUses.build();
							otherUses.setTitle("Other Uses Form");
							otherUses.setSize(500, 650);

							otherUses.getWindow().setClosable(false);
							final OtherUsesFormField otherUsesFormField = new OtherUsesFormField();
							otherUsesFormField.setHtmlProperty();
							Html htmlField = otherUsesFormField.getHtmlField();

							Cell cell = page.getCell(rowNum, colNum);
							String totalValue = cell.getText();
							cell = page.getCell(rowNum, (colNum + 1));
							String flagValue = cell.getText();

							// Check if it's the first time that the user click
							// on this cell or not
							otherUses.otherUsesInTheGlobalList(rowNum, colNum,
									ppv);
							otherUses.industrialUseInTheGlobalList(rowNum,
									colNum, ppv);

							// Other Uses Total
							final FieldSet fieldSetNorth = otherUses
									.createTotalFieldSetForOtherUses(
											otherUsesFormField, totalValue,
											flagValue, mainSave);
							// FormPanel formFieldSetNorth = new FormPanel();
							// formFieldSetNorth.add(fieldSetNorth);
							// FormButtonBinding binding = new
							// FormButtonBinding(formFieldSetNorth);
							// binding.addButton(mainSave);
							HorizontalPanel hpSave = new HorizontalPanel();

							// Other Uses by field
							// final FieldSet mainFieldSet =
							// otherUses.createMainFieldSetForOtherUses(otherUsesFormField,
							// fieldSetNorth, mainFieldSet, mainSave);
							final FieldSet mainFieldSet = otherUses
									.createMainFieldSetForOtherUses(
											otherUsesFormField, fieldSetNorth,
											mainSave, ppv);
							// Industrial Use by field
							final FieldSet fieldSet = otherUses
									.createIndustrialUseFieldSetForOtherUses(ppv);

							FormPanel formSmallFieldset = new FormPanel();
							formSmallFieldset.setBodyBorder(false);
							formSmallFieldset.setBorders(false);
							formSmallFieldset.setHeaderVisible(false);
							formSmallFieldset.setWidth(600);
							formSmallFieldset.setLayout(new FlowLayout());
							formSmallFieldset.removeAll();
							formSmallFieldset.add(fieldSet);

							HorizontalPanel vpReset = new HorizontalPanel();
							vpReset
									.setHorizontalAlign(HorizontalAlignment.RIGHT);
							vpReset.setVerticalAlign(VerticalAlignment.MIDDLE);
							vpReset.setTableWidth("100%");

							Button resetButton = new Button("Reset",
									new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											int indexReset = 0;
											System.out
													.println("Class: PagePanel Function: grid Other uses form Text:Other Uses By filed Reset 1!!!!");
											for (Component component : mainFieldSet
													.getItems()) {
												if (indexReset < 3) {
													HorizontalPanel hp = (HorizontalPanel) component;
													System.out
															.println("Class: PagePanel Function: grid Other uses form Text:Before (hp != null) 1");
													if (hp != null) {
														System.out
																.println("Class: PagePanel Function: grid Other uses form Text:(hp != null) 1");
														NumberField element = (NumberField) hp
																.getItemByItemId("OtherUsesSeedsField");
														if (element != null) {
															System.out
																	.println("Class: PagePanel Function: grid Other uses form Text:Before reset 1");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("OtherUsesSeedsCombo");
														if (combo != null) {
															System.out
																	.println("Class: PagePanel Function: grid Other uses form Text:Before reset combo 1");
															combo.reset();
														}
														otherUsesFormField
																.setSeeds(0.0);
													}
													if (hp != null) {
														System.out
																.println("Class: PagePanel Function: grid Other uses form Text:(hp != null) 2");
														NumberField element = (NumberField) hp
																.getItemByItemId("OtherUsesPostHarvestField");
														if (element != null) {
															System.out
																	.println("Class: PagePanel Function: grid Other uses form Text:Before reset 2");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("OtherUsesPostHarvestCombo");
														if (combo != null) {
															System.out
																	.println("Class: PagePanel Function: grid Other uses form Text:Before reset combo 2");
															combo.reset();
														}
														otherUsesFormField
																.setPostHarvestLosses(0.0);
													}
													if (hp != null) {
														System.out
																.println("Class: PagePanel Function: grid Other uses form Text:(hp != null) 3");
														NumberField element = (NumberField) hp
																.getItemByItemId("Industrial Use");
														if (element != null) {
															System.out
																	.println("Class: PagePanel Function: grid Other uses form Text:Before reset 3");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("OtherUsesIndustrialUseCombo");
														if (combo != null) {
															System.out
																	.println("Class: PagePanel Function: grid Other uses form Text:Before reset combo 3");
															combo.reset();
														}
														otherUsesFormField
																.setIndustrialUse(0.0);
													}
												}
												indexReset++;
											}
											for (Component component : fieldSetNorth
													.getItems()) {
												HorizontalPanel hp = (HorizontalPanel) component;
												if (hp != null) {
													NumberField element = (NumberField) hp
															.getItemByItemId("Other Uses Num Field");
													if (element != null) {
														element.reset();
													}
													ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
															.getItemByItemId("Other Uses Combo");
													if (combo != null) {
														combo.reset();
													}
												}
											}
										}
									});
							vpReset.add(resetButton);
							mainFieldSet.add(vpReset);
							HorizontalPanel vpSave = new HorizontalPanel();
							vpSave
									.setHorizontalAlign(HorizontalAlignment.RIGHT);
							vpSave.setVerticalAlign(VerticalAlignment.MIDDLE);
							vpSave.setTableWidth("100%");

							Button reset = new Button("Reset",
									new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											int indexReset = 0;
											for (Component component : fieldSet
													.getItems()) {
												if (indexReset < 5) {
													// System.out.println("Class: PagePanel Function: Reset Button index"+indexReset);
													HorizontalPanel hp = (HorizontalPanel) component;
													if (hp != null) {
														// System.out.println("Class: PagePanel Function: Reset Button (hp != null) 1");
														NumberField element = (NumberField) hp
																.getItemByItemId("Malt");
														if (element != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before element reset 1");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("MaltCombo");
														if (combo != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before combo reset 1");
															combo.reset();
															// System.out.println("Class: PagePanel Function: Reset Button After combo reset 1");
														}
													}
													if (hp != null) {
														// System.out.println("Class: PagePanel Function: Reset Button (hp != null) 2");
														NumberField element = (NumberField) hp
																.getItemByItemId("Biofuel");
														if (element != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before element reset 2");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("BiofuelCombo");
														if (combo != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before combo reset 2");
															combo.reset();
															// System.out.println("Class: PagePanel Function: Reset Button After combo reset 2");
														}
													}
													if (hp != null) {
														// System.out.println("Class: PagePanel Function: Reset Button (hp != null) 3");
														NumberField element = (NumberField) hp
																.getItemByItemId("Sweeteners");
														if (element != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before element reset 3");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("SweetenersCombo");
														if (combo != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before combo reset 3");
															combo.reset();
															// System.out.println("Class: PagePanel Function: Reset Button After combo reset 3");
														}
													}
													if (hp != null) {
														// System.out.println("Class: PagePanel Function: Reset Button (hp != null) 4");
														NumberField element = (NumberField) hp
																.getItemByItemId("Starch");
														if (element != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before element reset 4");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("StarchCombo");
														if (combo != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before combo reset 4");
															combo.reset();
															// System.out.println("Class: PagePanel Function: Reset Button After combo reset 4");
														}
													}
													if (hp != null) {
														// System.out.println("Class: PagePanel Function: Reset Button (hp != null) 5");
														NumberField element = (NumberField) hp
																.getItemByItemId("Others");
														if (element != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before element reset 5");
															element.reset();
														}
														ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>) hp
																.getItemByItemId("OthersCombo");
														if (combo != null) {
															// System.out.println("Class: PagePanel Function: Reset Button Before combo reset 5");
															combo.reset();
															// System.out.println("Class: PagePanel Function: Reset Button After combo reset 5");
														}
													}
												}
												indexReset++;
											}

											// START With this code WHEN THE
											// USER CLICK RESET ON (Malt,
											// Biofiuwel, Starch,...) fieldset
											// the INDUSTRIAL USE change is
											// value
											// otherUsesFormField.setIndustrialUse(0.0);
											// int i = 0;
											// for(Component component
											// :mainFieldSet.getItems())
											// {
											// if(i<3)
											// {
											// //
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 1111 ");
											// HorizontalPanel hp =
											// (HorizontalPanel)component;
											// //
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 2222 ");
											// if(hp != null)
											// {
											// //
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 3333 ");
											// NumberField element =
											// (NumberField)hp.getItemByItemId("Industrial Use");
											// //System.out.println("Class: PagePanel Function: ComponentSelected Text: element.getFieldLabel() "+element.getFieldLabel());
											// if(element!=null)
											// {
											// element.setValue(0.0);
											// //PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()]
											// = true;
											// //PagePanelController.updateSaveButton(mainSave,
											// otherUsesFormField.getNorthDim(),
											// PagePanelVariables.otherUsesFormArray);
											//													
											// // fieldNotNull[2] = true;
											// // updateSaveButton(mainSave);
											// //
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: Total industrialUse "+industrialUse);
											// double
											// setOtherUsesFromIndustrialUse =
											// otherUsesFormField.getIndustrialUse()
											// +
											// otherUsesFormField.getSeeds()+otherUsesFormField.getPostHarvestLosses();
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: setOtherUsesFromIndustrialUse "+setOtherUsesFromIndustrialUse);
											// for(Component component2 :
											// fieldSetNorth.getItems())
											// {
											// //
											// System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter");
											// HorizontalPanel hp2 =
											// (HorizontalPanel) component2;
											// //
											// System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter hp");
											// if(hp2!=null)
											// {
											// //
											// System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  hp!=null");
											// NumberField nf =
											// (NumberField)hp2.getItemByItemId("Other Uses Num Field");
											// if(nf!=null)
											// {
											// //
											// System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  nf");
											// nf.setValue(setOtherUsesFromIndustrialUse);
											// }
											// }
											// }
											// }
											// //
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: industrialUse "+industrialUseString);
											// }
											// i++;
											// }
											// }
											// END With this code WHEN THE USER
											// CLICK RESET ON (Malt, Biofiuwel,
											// Starch,...) fieldset the
											// INDUSTRIAL USE change is value
										}
									});
							save = new Button("Calculate Industrial Use",
									new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											fieldSet.collapse();
											double industrialUse = 0;
											int indexItem = 0;
											String appValue;
											String industrialUseString = "";
											boolean fullValue[] = new boolean[5];
											for (int i = 0; i < fullValue.length; i++) {
												fullValue[i] = false;
											}
											for (Component component : fieldSet
													.getItems()) {
												System.out
														.println("Class: PagePanel Function: ComponentSelected Text: for(Component component :fieldSet.getItems()) indexItem = "
																+ indexItem);
												if (indexItem < 5) {
													System.out
															.println("Class: PagePanel Function: ComponentSelected Text: for(Component component :in if indexItem = "
																	+ indexItem);
													HorizontalPanel hp = (HorizontalPanel) component;
													if (hp != null) {
														System.out
																.println("Class: PagePanel Function: ComponentSelected Text: Before (NumberField)hp.getItemByItemId(Malt");
														NumberField element = (NumberField) hp
																.getItemByItemId("Malt");
														if (element != null) {
															System.out
																	.println("Class: PagePanel Function: ComponentSelected Text:Malt before to string = ");
															System.out
																	.println("Class: PagePanel Function: ComponentSelected Text:Malt before element.getValue() = "
																			+ element
																					.getValue());

															if (element
																	.getValue() == null) {
																// appValue =
																// element.getEmptyText();
																appValue = "0.0";
															} else {
																appValue = element
																		.getValue()
																		.toString();
															}
															System.out
																	.println("Class: PagePanel Function: ComponentSelected Text:Malt after  to string = ");
															if ((appValue != null)
																	&& (!(appValue
																			.equals("")))) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text:Malt value = "
																				+ appValue);
																industrialUse += Double
																		.parseDouble(appValue);
																fullValue[indexItem] = true;
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "
																				+ industrialUse);
															}
															if (appValue == null) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text:Malt Null value = "
																				+ appValue);
															}
															if (appValue
																	.equals("")) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text:Malt .equals('') value = "
																				+ appValue);
															}

														}
													}
													if (hp != null) {
														NumberField element = (NumberField) hp
																.getItemByItemId("Biofuel");
														if (element != null) {
															if (element
																	.getValue() == null) {
																// appValue =
																// element.getEmptyText();
																appValue = "0.0";
															} else {
																appValue = element
																		.getValue()
																		.toString();
															}
															if ((appValue != null)
																	&& (!(appValue
																			.equals("")))) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: value = "
																				+ appValue);
																industrialUse += Double
																		.parseDouble(appValue);
																fullValue[indexItem] = true;
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "
																				+ industrialUse);
															}
														}
													}
													if (hp != null) {
														NumberField element = (NumberField) hp
																.getItemByItemId("Sweeteners");
														if (element != null) {
															if (element
																	.getValue() == null) {
																// appValue =
																// element.getEmptyText();
																appValue = "0.0";
															} else {
																appValue = element
																		.getValue()
																		.toString();
															}
															if ((appValue != null)
																	&& (!(appValue
																			.equals("")))) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: value = "
																				+ appValue);
																industrialUse += Double
																		.parseDouble(appValue);
																fullValue[indexItem] = true;
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "
																				+ industrialUse);
															}
														}
													}
													if (hp != null) {
														NumberField element = (NumberField) hp
																.getItemByItemId("Starch");
														if (element != null) {
															if (element
																	.getValue() == null) {
																// appValue =
																// element.getEmptyText();
																appValue = "0.0";
															} else {
																appValue = element
																		.getValue()
																		.toString();
															}
															if ((appValue != null)
																	&& (!(appValue
																			.equals("")))) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: value = "
																				+ appValue);
																industrialUse += Double
																		.parseDouble(appValue);
																fullValue[indexItem] = true;
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "
																				+ industrialUse);
															}
														}
													}
													if (hp != null) {
														NumberField element = (NumberField) hp
																.getItemByItemId("Others");
														if (element != null) {
															if (element
																	.getValue() == null) {
																// appValue =
																// element.getEmptyText();
																appValue = "0.0";
															} else {
																appValue = element
																		.getValue()
																		.toString();
															}
															if ((appValue != null)
																	&& (!(appValue
																			.equals("")))) {
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: value = "
																				+ appValue);
																industrialUse += Double
																		.parseDouble(appValue);
																fullValue[indexItem] = true;
																System.out
																		.println("Class: PagePanel Function: ComponentSelected Text: industrialUse = "
																				+ industrialUse);
															}
														}
													}
													indexItem++;
												} else {
													break;
												}
											}
											int index;
											for (index = 0; index < fullValue.length; index++) {
												System.out
														.println("Class: PagePanel Function: ComponentSelected Text: index = "
																+ index);
												if (fullValue[index]) {
													break;
												}
											}
											if (index == fullValue.length) {
												industrialUseString = "";
											} else {
												industrialUseString = ""
														+ industrialUse;
											}
											otherUsesFormField
													.setIndustrialUse(industrialUse);
											System.out
													.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() "
															+ mainFieldSet
																	.getItemCount());
											int i = 0;
											for (Component component : mainFieldSet
													.getItems()) {
												if (i < 3) {
													System.out
															.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 1111 ");
													HorizontalPanel hp = (HorizontalPanel) component;
													System.out
															.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 2222 ");
													if (hp != null) {
														System.out
																.println("Class: PagePanel Function: ComponentSelected Text: mainFieldSet.getItemCount() 3333 ");
														NumberField element = (NumberField) hp
																.getItemByItemId("Industrial Use");
														// System.out.println("Class: PagePanel Function: ComponentSelected Text: element.getFieldLabel() "+element.getFieldLabel());
														if (element != null) {
															element
																	.setValue(industrialUse);
															// PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()]
															// = true;
															// PagePanelController.updateSaveButton(mainSave,
															// otherUsesFormField.getNorthDim(),
															// PagePanelVariables.otherUsesFormArray);

															// fieldNotNull[2] =
															// true;
															// updateSaveButton(mainSave);
															System.out
																	.println("Class: PagePanel Function: ComponentSelected Text: Total industrialUse "
																			+ industrialUse);
															double setOtherUsesFromIndustrialUse = industrialUse
																	+ otherUsesFormField
																			.getSeeds()
																	+ otherUsesFormField
																			.getPostHarvestLosses();
															System.out
																	.println("Class: PagePanel Function: ComponentSelected Text: setOtherUsesFromIndustrialUse "
																			+ setOtherUsesFromIndustrialUse);
															for (Component component2 : fieldSetNorth
																	.getItems()) {
																System.out
																		.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter");
																HorizontalPanel hp2 = (HorizontalPanel) component2;
																System.out
																		.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter hp");
																if (hp2 != null) {
																	System.out
																			.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  hp!=null");
																	NumberField nf = (NumberField) hp2
																			.getItemByItemId("Other Uses Num Field");
																	if (nf != null) {
																		System.out
																				.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  nf");
																		nf
																				.setValue(setOtherUsesFromIndustrialUse);
																	}
																}
															}
														}
														System.out
																.println("Class: PagePanel Function: ComponentSelected Text: industrialUse "
																		+ industrialUseString);
													}
													i++;
												}
											}
											industrialUse = 0;

										}
									});
							// mainSave.disable();
							// FormButtonBinding binding = new
							// FormButtonBinding(formSmallFieldset);
							// binding.addButton(save);
							hpSave.add(reset);
							hpSave.add(save);
							vpSave.add(hpSave);
							fieldSet.add(vpSave);

							mainFieldSet.add(formSmallFieldset);

							HorizontalPanel hpMainSave = new HorizontalPanel();

							HorizontalPanel vpMainSave = new HorizontalPanel();
							vpMainSave
									.setHorizontalAlign(HorizontalAlignment.RIGHT);
							vpMainSave
									.setVerticalAlign(VerticalAlignment.MIDDLE);
							vpMainSave.setTableWidth("100%");

							final FormPanel form2 = new FormPanel();
							form2.setHeaderVisible(false);
							form2.setWidth(350);
							form2.setLayout(new FlowLayout());
							form2.removeAll();
							form2.add(mainFieldSet);

							final FormPanel form3 = new FormPanel();
							form3.setHeaderVisible(false);
							form3.setWidth(350);
							form3.setLayout(new FlowLayout());
							form3.removeAll();
							form3.add(htmlField);
							form3.add(FormattingUtils.addVSpace(5));
							form3.add(fieldSetNorth);

							Button mainCancel = new Button("Cancel",
									new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											otherUses.getWindow().hide();
										}
									});
							mainSave.setId("mainSaveButton");
							mainSave
									.addSelectionListener(new SelectionListener<ButtonEvent>() {
										@Override
										public void componentSelected(
												ButtonEvent ce) {
											CCBS.CHANGES_IN_THE_GRID = true;
											System.out
													.println(" mainSave has been clicked rowNum = "
															+ rowNum
															+ " colNum "
															+ colNum);
											fieldSet.collapse();
											// Take the value and prepare the
											// data set result for Industrial
											// Use Fieldset
											otherUses
													.getIndustrialUseFieldSetForOtherUses(
															fieldSet,
															decoration, rowNum,
															colNum);
											System.out
													.println(" mainSave has been clicked 1");
											// Take the value and prepare the
											// data set result for Other Uses By
											// Field
											otherUses
													.getOtherUsesByFieldFieldSetForOtherUses(
															mainFieldSet,
															decoration, rowNum,
															colNum);
											System.out
													.println(" mainSave has been clicked 2");
											OtherUsesFormField otherUsesFormFieldResult = otherUses
													.getOtherUseTotalValue(fieldSetNorth);
											System.out
													.println(" mainSave has been clicked 3");
											// Setting the calculated cell on
											// the grid
											if (!otherUsesFormFieldResult
													.isTotalValueNull()) {
												System.out
														.println(" otherUsesFormFieldResult.getTotalValue() "
																+ otherUsesFormFieldResult
																		.getTotalValue());
											}
											System.out
													.println(" otherUsesFormFieldResult.getFlagValue() "
															+ otherUsesFormFieldResult
																	.getFlagValue());
											CCBSStore ccbsStore = (CCBSStore) grid
													.getStore();
											if (!otherUsesFormFieldResult
													.isTotalValueNull()) {
												ccbsStore
														.getAt(rowNum)
														.set(
																"col_" + colNum,
																""
																		+ otherUsesFormFieldResult
																				.getTotalValue());
											} else {
												ccbsStore.getAt(rowNum).set(
														"col_" + colNum, "");
											}
											ccbsStore.getAt(rowNum).set(
													"col_" + (colNum + 1),
													otherUsesFormFieldResult
															.getFlagValue());
											ccbsStore.updatePage();

											Cell cell = page.getCell(rowNum,
													colNum);
											cell.setText(""
													+ otherUsesFormFieldResult
															.getTotalValue());
											cell.setValid(false);
											cell = page.getCell(rowNum,
													(colNum + 1));
											cell
													.setText(otherUsesFormFieldResult
															.getFlagValue());
											cell.setValid(false);
											try {
												System.out
														.println("Class: PagePanel Function: ComponentSelected Text: Before page.getBook().update() ");
												page.getBook().update();

												// if(otherUsesTotString.equalsIgnoreCase(""))
												// {
												// ccbsStore.getAt(rowNum).set("col_"
												// + colNum, "");
												// ccbsStore.getAt(rowNum).set("col_"
												// + (colNum+1), "");
												// }
												// else
												// {
												// ccbsStore.getAt(rowNum).set("col_"
												// + colNum,
												// ""+otherUsesTotString);
												// ccbsStore.getAt(rowNum).set("col_"
												// + (colNum+1),
												// ""+otherUsesFlag);
												// }
												// ccbsStore.updatePage();
											} catch (Exception e) {
												FenixAlert.error("Error",
														"Recalculation failed");

												e.printStackTrace();
											}
											for (ClientCbsDatasetResult client : CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM) {
												System.out
														.println("Class:PagePanel Function: mainsave listener Before Hide Text:Other element");
												System.out
														.println("Class:PagePanel Function: mainsave listener Before Hide Text:value= "
																+ client
																		.getValue()
																+ " flag= "
																+ client
																		.getAnnotation()
																+ " year= "
																+ client
																		.getYear()
																+ " forecast= "
																+ client
																		.getMonth());
											}
											otherUses.getWindow().hide();

											// System.out.println("MainSave ON click!!!");
											// fieldSet.collapse();
											// double otherUsesTot = 0;
											// String appValue = "";
											// boolean isValidArray[] = new
											// boolean[7];
											// String otherUsesFlag = "";
											// String otherUsesTotString = "";
											// boolean formFieldIsValid = false;
											// for(int i= 0; i<
											// isValidArray.length; i++)
											// {
											// isValidArray[i] = false;
											// }
											//										
											// if(form3.isValid())
											// {
											// //Other Uses from Total Value
											// System.out.println("FORM TOTAL IS VALID");
											// totalOtherUses = true;
											// }
											// else
											// {
											// OtherUsesFormField
											// otherUsesFormFieldResult =
											// otherUses.getOtherUseByFieldValue(mainFieldSet);
											// isValidArray =
											// otherUsesFormFieldResult.getIsValidArray();
											// otherUsesTot =
											// otherUsesFormFieldResult.getOtherUsesTot();
											// otherUsesFlag =
											// otherUsesFormFieldResult.getOtherUsesFlag();
											// }
											//										
											// int index =0;
											// for(index=0;
											// index<isValidArray.length;
											// index++)
											// {
											// if(!isValidArray[index])
											// {
											// break;
											// }
											// }
											// if(index==isValidArray.length)
											// {
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: if(index==isValidArray.length) :otherUsesTotString "+
											// otherUsesTotString);
											// //The form is valid
											// formFieldIsValid = true;
											// otherUsesTotString = ""+
											// otherUsesTot;
											// }
											// else
											// {
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: else(index==isValidArray.length) :otherUsesTotString "+
											// otherUsesTotString);
											// //The form is not valid
											// //Fill all field!
											// formFieldIsValid = false;
											// }
											// if(totalOtherUses)
											// {
											// HorizontalPanel hp;
											// for(Component component2
											// :fieldSetNorth.getItems())
											// {
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: Loop Component component :fieldSetNorth.getItems()");
											// //if((component!= null) &&
											// ((component.getTitle().equalsIgnoreCase("Other Uses"))))
											// if(component2!= null)
											// {
											// hp = (HorizontalPanel)component2;
											// if(hp != null)
											// {
											// NumberField element =
											// (NumberField)hp.getItemByItemId("Other Uses Num Field");
											// if(element!=null)
											// {
											// if(element.getValue() != null)
											// {
											// appValue =
											// element.getValue().toString();
											// otherUsesTot =
											// Double.parseDouble(appValue);
											// otherUsesTotString =
											// ""+otherUsesTot;
											// System.out.println("Class: PagePanel Function: ComponentSelected Text:if(element.getValue() otherUsesTotString "+otherUsesTotString);
											// }
											// }
											//														
											// ComboBox<AMISCodesModelData>
											// combo =
											// (ComboBox<AMISCodesModelData>)hp.getItemByItemId("Other Uses Combo");
											// if(combo!= null)
											// {
											// if(combo.getValue() != null)
											// {
											// otherUsesFlag =
											// combo.getValue().getLabel();
											// }
											//						
											// }
											//													
											// }
											// }
											// }
											// }
											// if((totalOtherUses)||(formFieldIsValid))
											// {
											// System.out.println("                    otherUsesFlag "+otherUsesFlag);
											// System.out.println("                    otherUsesTotString "+otherUsesTotString);
											// //Setting the calculated cell on
											// the grid
											// CCBSStore ccbsStore =
											// (CCBSStore)grid.getStore();
											// ccbsStore.getAt(rowNum).set("col_"
											// + colNum, otherUsesTotString);
											// ccbsStore.updatePage();
											//											
											// Cell cell = page.getCell(rowNum,
											// colNum);
											// cell.setText(otherUsesTotString);
											// cell.setValid(false);
											//
											// try
											// {
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: Before page.getBook().update() ");
											// page.getBook().update();
											//												
											// if(otherUsesTotString.equalsIgnoreCase(""))
											// {
											// ccbsStore.getAt(rowNum).set("col_"
											// + colNum, "");
											// ccbsStore.getAt(rowNum).set("col_"
											// + (colNum+1), "");
											// }
											// else
											// {
											// ccbsStore.getAt(rowNum).set("col_"
											// + colNum, ""+otherUsesTotString);
											// ccbsStore.getAt(rowNum).set("col_"
											// + (colNum+1), ""+otherUsesFlag);
											// }
											// ccbsStore.updatePage();
											// }
											// catch (Exception e)
											// {
											// FenixAlert.error("Error",
											// "Recalculation failed");
											//	
											// e.printStackTrace();
											// }
											//											
											// Map<String, Object> map =
											// ccbsStore.getAt(rowNum).getProperties();
											// Set<String> key = map.keySet();
											// for(String elem:key)
											// {
											// System.out.println("Class: PagePanel Function: ComponentSelected Text: map.get(key) "+map.get(key));
											// }
											// MessageBox.info("Action",
											// "The \"Other Uses\" field has been updated",
											// null);
											// otherUses.getWindow().hide();
											// }
											// else
											// {
											// MessageBox.info("Action",
											// "Fill in the required fields!",
											// null);
											// }
										}
									});
							// PagePanelController.updateSaveButton(mainSave,
							// otherUsesFormField.getNorthDim(),
							// PagePanelVariables.otherUsesFormArray);

							// FormButtonBinding mainBinding = new
							// FormButtonBinding(form3);
							// mainBinding.addButton(mainSave);

							otherUses.fillNorthPart(form3);
							// HorizontalPanel test = new HorizontalPanel();
							// otherUses.fillNorthPart(fieldSetNorth);
							// //otherUses.getNorth().setHeaderVisible(false);
							otherUses.fillCenterPart(form2);
							hpMainSave.add(mainCancel);
							hpMainSave.add(mainSave);
							vpMainSave.add(hpMainSave);
							otherUses.getCenter()
									.setBottomComponent(vpMainSave);
							otherUses.getCenter().setHeaderVisible(false);
							otherUses.show();
						}
					}

				}
				System.out.println(" END out rowNum " + rowNum + " colNum "
						+ colNum);
			}
			// STOP IF

		});
		return grid;
	}

	private boolean isCellEditable(int rowIndex, int colIndex) {
		return decoration.isRowEditable(rowIndex) && colIndex != 0
				&& decoration.isColEditable(colIndex - 1);
	}

	public void bookUpdated() {
		// maybe faster using this
	}

	public void cellUpdated(int pageNum, int rowNum, int colNum) {
		if (pageNum == page.getPageNum()) {
			
			String value = page.getCell(rowNum, colNum).getText();
			System.out.println(" PagePanel cellUpdated "+ rowNum+" "+colNum + " Value "+ value);
			int rowIndex = rowNum;
			int colIndex = colNum + 1;

			String property = cm.getColumnId(colIndex);
			CCBSModel model = store.getAt(rowIndex);

			model.set(property, value);
			System.out.println(" PagePanel cellUpdated "+ rowNum+" "+colNum + " model.get(col_12) "+ model.get("col_12"));
			store.update(model);
		}
	}

	class PageCellRenderer implements GridCellRenderer<CCBSModel> {
		public String render(CCBSModel model, String property,
				ColumnData config, int rowIndex, int colIndex,
				ListStore<CCBSModel> store) {
			// System.out.println("Class: PageCellRenderer Function: render Text: Start !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Before Style"
			// + config.style);
			String cellProperty = model.get(property, "");
			config.style = "";
			// config.style += "background-color:none";
			int width = (grid.getColumnModel().getColumn(colIndex).getWidth()) - 2;
			String alignment = grid.getColumnModel().getColumn(colIndex)
					.getAlignment().toString();
			config.style += "width:" + width + "px;";
			config.style += "text-align:" + alignment + ";";
			if (colIndex == 0) {
				if (!decoration.isRowEditable(rowIndex)) {
					if ((rowIndex != 5) && (rowIndex != 14)) {
						// System.out.println("Class:PagePanel Function: render Text: IF rowIndex= "+
						// rowIndex + " colIndex= "+colIndex);
						config.style += "background-color:#D3D3D3;";

						// config.style += "background-color:#FF0080;";
					}
				}
			} else if (!isCellEditable(rowIndex, colIndex)) {
				if ((!cellProperty.equals("")) && ((colIndex % 2) != 0)) {
					double value = Double.parseDouble(model.get(property, ""));
					cellProperty = numberFormat.format(value);
				}

				if ((rowIndex != 5) && (rowIndex != 14)) {

					config.style += "background-color:#D3D3D3;";
					// config.style += "background-color:#00FFFF;";
				}
				if (rowIndex == 1) {
					// System.out.println("PageCellRenderer ********************");
					config.style += "background-color: #D3D3D3; color: #960018; font-weight: bold;";

					// config.style += "background-color: 	#008000;";
				}
			}
			// System.out.println("XXXXXX Class:PagePanel Function: render Text: rowIndex= "+
			// rowIndex + " colIndex= "+colIndex);
			// System.out.println("XXXXXX Class:PagePanel Function: render Text: config.style= "+
			// config.style);
			// return numberFormat;
			// return model.get(property, "");
			return cellProperty;
		}

		// public removeAttributes(String )
		// {
		//			
		// }

		public Object render(CCBSModel model, String property,
				ColumnData config, int rowIndex, int colIndex,
				ListStore<CCBSModel> store, Grid<CCBSModel> grid) {
			return render(model, property, config, rowIndex, colIndex, store);
		}
	}

	public static SelectionChangedListener<AMISCodesModelData> yearSelectionChangedListener(
			final PagePanel panel, final PageDecoration decoration,
			final ComboBox<MonthForecast> comboMonth) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(
					SelectionChangedEvent<AMISCodesModelData> se) {
				// System.out.println("?????????????????????????????????Listener decoration.numCols() "+
				// decoration.numCols());
				int numCol = decoration.numCols();
				int numColToChangeValue = numCol - 2;
				int numColToChangeFlag = numCol - 1;
				AMISCodesModelData selected = se.getSelectedItem();
				if (selected != null) {
					if ((panel != null) && (panel.grid != null)) {
						CCBSStore store = (CCBSStore) panel.grid.getStore();
						List<CCBSModel> modelList = (List<CCBSModel>) store.getModels();
						int iModel=0;
						for (CCBSModel model : modelList) {
							 System.out.println("Listener decoration.numCols() iModel "+iModel +" selected.getLabel() "+ selected.getLabel());
							// decoration.numCols());
							// System.out.println("Listener model.get(col_+ numColToChangeValue+ YEAR) before"+
							// model.get("col_"+ numColToChangeValue+ "_YEAR"));
							model.set("col_" + numColToChangeValue + "_YEAR",
									selected.getLabel());
							// System.out.println("Listener model.get(col_+ numColToChangeValue+ YEAR) after"+
							// model.get("col_"+ numColToChangeValue+ "_YEAR"));

							// System.out.println("Listener model.get(col_+ numColToChangeFlag+ YEAR) before"+
							// model.get("col_"+ numColToChangeFlag+ "_YEAR"));
							model.set("col_" + numColToChangeFlag + "_YEAR",
									selected.getLabel());
							// System.out.println("Listener model.get(col_+ numColToChangeFlag+ YEAR) after"+
							// model.get("col_"+ numColToChangeFlag+ "_YEAR"));
							// for(int colNum=0; colNum< decoration.numCols();
							// colNum++)
							// {
							// model.set("col_"+ colNum+ "_YEAR",
							// selected.getLabel());
							// }
							if(iModel== CCBS.OPENING_STOCKS_ROW_NUM)
							{
								System.out.println("Class: PagePanel Function: preloadAction Text : if(iModel== CCBS.OPENING_STOCKS_ROW_NUM) start ");
								String yearValue = "";
								ColumnConfig colConf = panel.grid.getColumnModel().getColumn(numColToChangeFlag);
								// System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: colConf.getWidget()= "+colConf.getWidget());
								if (colConf.getWidget() != null) {
									HorizontalPanel hp = (HorizontalPanel) colConf.getWidget();
									ComboBox<AMISCodesModelData> comboYear = (ComboBox<AMISCodesModelData>) hp.getItemByItemId("ComboYear");
									ComboBox<MonthForecast> comboMonth = (ComboBox<MonthForecast>) hp.getItemByItemId("ComboMonth");
									yearValue = comboYear.getValue().getLabel();
								}
								if((yearValue!=null)&&(yearValue!="")&&(yearValue.length()>4))
								{
									yearValue = yearValue.substring(0, 4);
									System.out.println("Class: PagePanel Function: preloadAction Text : yearValue "+yearValue);
									int year = Integer.parseInt(yearValue);
									year--;
									//Take the value for Opening Stocks from the hashmap OPENING_CLOSING_STOCKS_HASHMAP
									//The value is equal to the Closing Stocks of th previous year
									String openingClosingStocksValues[]= CCBSParametersPanel.getOpeningClosingStocksValues(""+year);										
									//newColumn = colNum-1;
									System.out.println("Class: PagePanel Function: preloadAction Text : model.get(col_12) before "+model.get("col_12"));
									model.set("col_" + 12, openingClosingStocksValues[1]);
//									panel.page.getCell(iModel, 12).setText("33");
//									panel.page.getCell(iModel, 12).setValid(false);
									//model.set("col_12", "33");
									System.out.println("Class: PagePanel Function: preloadAction Text : model.get(col_12 before "+model.get("col_12"));
									store.updatePage();
									System.out.println("Class: PagePanel Function: preloadAction Text : model.get(col_12 after "+model.get("col_12"));
									Cell cell = panel.page.getCell(iModel, 12);
									cell.setText(openingClosingStocksValues[1]);
									cell.setValid(false);
									//cell.getFormula().getCell().setText("33");
									try 
									{
										String value = store.getModels().get(3).get("col_12");
										System.out.println("Class: PagePanel Function: preloadAction Text : value before "+value);
										//panel.page.getBook().update("33");
										panel.page.getBook().update(openingClosingStocksValues[1]);
										value = store.getModels().get(3).get("col_12");
										System.out.println("Class: PagePanel Function: preloadAction Text : value after "+value);
									} catch (Exception e) {
										
										e.printStackTrace();
									}
//									model.set("col_12", "33");
//									store.updatePage();
									
									//store.update(model);
									//panel.page.getCell(iModel, 12).setText(openingClosingStocksValues[1]);
//									panel.page.getCell(iModel, 12).setText("33");
//									panel.page.getCell(iModel, 12).setValid(false);
//									try {
//										panel.page.getBook().update();
//									} catch (Exception e) {
//										
//										e.printStackTrace();
//									}
									System.out.println("Class: PagePanel Function: preloadAction Text : model.get(col_12) after "+model.get("col_12"));
								}
								System.out.println("Class: PagePanel Function: preloadAction Text : if(iModel== CCBS.OPENING_STOCKS_ROW_NUM) end ");
							}
							iModel++;
						}
						comboMonth.getStore().removeAll();
						List<MonthForecast> monthForCombo;
						if (selected.getLabel().equals(
								CCBS.YEARS.get(CCBS.YEARS.size() - 1))) {
							// This is the last year in the db
							boolean firstTime = false;
							monthForCombo = TestMonthForecast
									.getMonthForecastFromCbsDb(firstTime);
						} else {
							// This is a subsequent year
							monthForCombo = TestMonthForecast
									.getShortMonth(true);
						}
						for (int i = 0; i < monthForCombo.size(); i++) {
							comboMonth.getStore().add(monthForCombo.get(i));
						}
						comboMonth.setValue(comboMonth.getStore().getAt(0));
					}
					// set("col_"+ colNum+ "_FORECAST",
					// CCBS.MONTH_FOR_YEARS.get(PagePanelController.getLastYearToShow()));
					// set("col_"+ colNum+ "_YEAR",
					// PagePanelController.getLastYearToShow());

				}
			}
		};
	}

	public static SelectionChangedListener<MonthForecast> monthSelectionChangedListener(
			final PagePanel panel, final PageDecoration decoration) {
		return new SelectionChangedListener<MonthForecast>() {
			public void selectionChanged(SelectionChangedEvent<MonthForecast> se) {
				// System.out.println("????????????????????Listener decoration.numCols() "+
				// decoration.numCols());
				int numCol = decoration.numCols();
				int numColToChangeValue = numCol - 2;
				int numColToChangeFlag = numCol - 1;
				String selected = PagePanelController.changeMonthString(se
						.getSelectedItem().getMonthForecast());
				if (selected != null) {
					if ((panel != null) && (panel.grid != null)) {
						CCBSStore store = (CCBSStore) panel.grid.getStore();
						List<CCBSModel> modelList = (List<CCBSModel>) store
								.getModels();
						for (CCBSModel model : modelList) {
							// System.out.println("Listener decoration.numCols() "+
							// decoration.numCols());
							// System.out.println("Listener model.get(col_+ numColToChangeValue+ FORECAST) before"+
							// model.get("col_"+ numColToChangeValue+
							// "_FORECAST"));
							model.set("col_" + numColToChangeValue
									+ "_FORECAST", selected);
							// System.out.println("Listener model.get(col_+ numColToChangeValue+ FORECAST) after"+
							// model.get("col_"+ numColToChangeValue+
							// "_FORECAST"));

							// System.out.println("Listener model.get(col_+ numColToChangeFlag+ FORECAST) before"+
							// model.get("col_"+ numColToChangeFlag+
							// "_FORECAST"));
							model.set(
									"col_" + numColToChangeFlag + "_FORECAST",
									selected);
							// System.out.println("Listener model.get(col_+ numColToChangeFlag+ FORECAST) after"+
							// model.get("col_"+ numColToChangeFlag+
							// "_FORECAST"));
							// for(int colNum=0; colNum< decoration.numCols();
							// colNum++)
							// {
							// model.set("col_"+ colNum+ "_FORECAST", selected);
							// }
						}
					}
					// set("col_"+ colNum+ "_FORECAST",
					// CCBS.MONTH_FOR_YEARS.get(PagePanelController.getLastYearToShow()));
					// set("col_"+ colNum+ "_YEAR",
					// PagePanelController.getLastYearToShow());
				}
			}
		};
	}

	private ColumnModel makeColumnModel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig headersColumn = new ColumnConfig();
		headersColumn.setRenderer(new PageCellRenderer());
		headersColumn.setId("header");
		// System.out.println("Class: PagePanel Function: makeColumnModel Text: decoration.getTitle() "+decoration.getTitle());
		// System.out.println("Class: PagePanel Function: makeColumnModel Text: decoration.getRowHeadersWidth() "+decoration.getRowHeadersWidth());
		// headersColumn.setHeader(decoration.getTitle());
		// System.out.println("Class: PagePanel Function: makeColumnModel Text: headersColumn.getHeader() "+headersColumn.getHeader());
		headersColumn.setAlignment(Style.HorizontalAlignment.RIGHT);
		headersColumn.setWidth(decoration.getRowHeadersWidth());
		headersColumn.setMenuDisabled(true);
		headersColumn.setSortable(false);
		configs.add(headersColumn);
		int colNum = 0;
		for (colNum = 0; colNum < decoration.numCols(); colNum++) {
			if (colNum == decoration.numCols() - 2) {
				ColumnConfig column = new ColumnConfig();
				column.setRenderer(new PageCellRenderer());
				column.setId("col_" + colNum);
				column.setHeader(decoration.getColHeader(colNum));
				column.setAlignment(Style.HorizontalAlignment.RIGHT);
				// column.setWidth(decoration.getColHeaderWidth(colNum));
				column.setWidth(200);
				column.setMenuDisabled(true);
				column.setSortable(false);
				column.setEditor(new CellEditor(new TextField()));
				HorizontalPanel hpCombo = new HorizontalPanel();
				hpCombo.setSpacing(4);
				ComboBox<AMISCodesModelData> comboYear = new ComboBox<AMISCodesModelData>();

				// CCBSParametersPanel.loadOtherYears();
				ListStore<AMISCodesModelData> years = CCBSParametersPanel
						.fillStoreFromOtherYears();
				comboYear.setDisplayField("label");

				comboYear.setWidth(85);
				comboYear.setStore(years);
				comboYear.setTypeAhead(true);
				comboYear.setTriggerAction(TriggerAction.ALL);
				comboYear.setValue(years.getAt(0));
				comboYear.setId("ComboYear");

				hpCombo.add(comboYear);
				hpCombo.add(FormattingUtils.addHSpace(20));

				ComboBox<MonthForecast> comboMonth = new ComboBox<MonthForecast>();
				ListStore<MonthForecast> monthForecast = new ListStore<MonthForecast>();
				// monthForecast.add(TestMonthForecast.getMonthForecast());
				boolean firstTime = true;
				monthForecast.add(TestMonthForecast
						.getMonthForecastFromCbsDb(firstTime));
				// combo.setEmptyText("Select an year...");
				comboMonth.setDisplayField("monthForecast");
				comboMonth.setWidth(55);
				comboMonth.setId("ComboMonth");
				comboMonth.setStore(monthForecast);
				comboMonth.setTypeAhead(true);
				comboMonth
						.addSelectionChangedListener(monthSelectionChangedListener(
								this, decoration));
				comboMonth.setTriggerAction(TriggerAction.ALL);
				// comboMonth.setValue(new
				// MonthForecast(CCBS.MONTH_WITH_FLAG[0]));
				comboMonth.setValue(new MonthForecast(monthForecast.getAt(0)
						.getMonthForecast()));
				hpCombo.add(comboMonth);
				column.setWidget(hpCombo, "YearsAndMonth");
				configs.add(column);
				comboYear
						.addSelectionChangedListener(yearSelectionChangedListener(
								this, decoration, comboMonth));
				// colNum+=2;
			} else {

				ColumnConfig column = new ColumnConfig();
				column.setRenderer(new PageCellRenderer());
				column.setId("col_" + colNum);
				// if(colNum==decoration.numCols()-4)
				// {
				// String colHeader = decoration.getColHeader(colNum);
				// String month= "";
				// month = decoration.getMonthForYears().get(colHeader);
				// month = "test";
				// column.setHeader(colHeader+ "<BR>"+ month);
				// }
				// else
				// {
				// column.setHeader(decoration.getColHeader(colNum));
				// }
				// Set<String> keySet =decoration.getMonthForYears().keySet();
				// for(String key:keySet)
				// {
				// System.out.println("Class: PagePanel Function: makeColumnModel key= "+key);
				// System.out.println("Class: PagePanel Function: makeColumnModel decoration.getMonthForYears().get(key)= "+decoration.getMonthForYears().get(key));
				// }
				// if((colNum!=decoration.numCols()-1)&&(colNum%2==0))

				Set<String> keySet = decoration.getMonthForYears().keySet();
				// for(String key:keySet)
				// {
				// System.out.println("Class: PagePanel Function: Row 2279 key= "+key);
				// System.out.println("Class: PagePanel Function: Row 2279 decoration.getMonthForYears().get(key)= "+decoration.getMonthForYears().get(key));
				// }
				// System.out.println("Class: PagePanel Function: Row 2285 decoration.numCols()= "+decoration.numCols());
				// for(int i=0; i < decoration.numCols(); i++)
				// {
				// System.out.println("Class: PagePanel Function: Row 2285 i= "+i
				// + " decoration.getColHeader(i) "+
				// decoration.getColHeader(i));
				// }

				// INIZIO DA LEVARE
				if (colNum % 2 == 0) {
					String colHeader = decoration.getColHeader(colNum);
					String month = "";
					String monthLowerCase = "";
					System.out
							.println("Class: PagePanel Function: makeColumnModel Text:decoration.numCols()= "
									+ decoration.numCols()
									+ " decoration.numCols()-4= "
									+ (decoration.numCols() - 4));
					month = decoration.getMonthForYears().get(colHeader);
					// System.out.println("Class: PagePanel Function: makeColumnModel Text:colNum= "+colNum
					// +" colHeader= "+ colHeader + "month= "+month);
					// month = "test";
					if ((month != null) && (!month.equalsIgnoreCase(""))
							&& ((colNum == decoration.numCols() - 4))) {
						// If the data is a Forecast
						// month = "test";
						String year = colHeader;
						monthLowerCase = month.charAt(0)
								+ month.substring(1).toLowerCase();
						column.setHeader(colHeader + "<BR>"
								+ "<div class='ccbs_grid_header_column'>"
								+ monthLowerCase + "</div>");
						// column.setHeader(colHeader+ "<BR>"+ month);
						System.out
								.println("Class: PagePanel Function: makeColumnModel Text:1 colNum= "
										+ colNum
										+ " colHeader= "
										+ colHeader
										+ "month= " + month);
					} else {
						// If the data is validated the month in the database is
						// 0
						// month = "test2";
						// monthLowerCase =month.toLowerCase();
						// monthLowerCase =
						// month.charAt(0)+month.substring(1).toLowerCase();
						column.setHeader(colHeader);
						// System.out.println("Class: PagePanel Function: makeColumnModel Text:2 colNum= "+colNum
						// +" colHeader= "+ colHeader + "month= "+month);
					}
				}
				// FINE DA LEVARE
				// INIZIO DA METTERE
				// if(colNum==decoration.numCols()-4)
				// {
				// String colHeader = decoration.getColHeader(colNum);
				// String month= "";
				// month = decoration.getMonthForYears().get(colHeader);
				// System.out.println("Class: PagePanel Function: makeColumnModel Text:colNum= "+colNum
				// +" colHeader= "+ colHeader + "month= "+month);
				// // month = "test";
				// if(!month.equalsIgnoreCase(""))
				// {
				// //If the data is a Forecast
				// column.setHeader(colHeader+ "<BR>"+ month);
				// }
				// else
				// {
				// //If the data is validated the month in the database is 0
				// column.setHeader(colHeader);
				// }
				// }
				// else
				// {
				// column.setHeader(decoration.getColHeader(colNum));
				// }
				// FINE DA METTERE
				// System.out.println("Class: PagePanel Function: makeColumnModel Text:colNum "+colNum
				// +" decoration.getColHeader(colNum) "+
				// decoration.getColHeader(colNum) +
				// "decoration.getColHeaderWidth(colNum)= "+decoration.getColHeaderWidth(colNum));
				column.setAlignment(Style.HorizontalAlignment.RIGHT);
				column.setWidth(decoration.getColHeaderWidth(colNum));
				column.setMenuDisabled(true);
				column.setSortable(false);
				column.setEditor(new CellEditor(new TextField()));
				configs.add(column);
			}
		}

		ColumnModel columnModel = new ColumnModel(configs);
		columnModel.addHeaderGroup(0, 0, new HeaderGroupConfig(
				"HISTORICAL DATA", 1, 13));
		columnModel.addHeaderGroup(0, 13, new HeaderGroupConfig("FORECAST", 1,
				2));
		// return new ColumnModel(configs);

		return columnModel;
	}

	private class CCBSStore extends ListStore<CCBSModel> {
		private PageDecoration decoration;

		public CCBSStore(PageDecoration decoration) {
			this.decoration = decoration;

			for (int rowNum = 0; rowNum < decoration.numRows(); rowNum++) {
				CCBSModel model = new CCBSModel(decoration, rowNum);
				add(model);
			}
		}

		public void loadPage(Page page) {
			this.rejectChanges(); // FIXME: should possibly save first
			for (int rowNum = 0; rowNum < page.numRows(); rowNum++) {
				CCBSModel model = getAt(rowNum);
				model.loadRow(page, rowNum);
				update(model);
			}
		}

		public void unloadPage() {
			this.rejectChanges(); // FIXME: should possibly save first
			for (int rowNum = 0; rowNum < page.numRows(); rowNum++) {
				CCBSModel model = getAt(rowNum);
				model.unloadRow();
				update(model);
			}
		}

		public void updatePage() {
			for (int rowNum = 0; rowNum < decoration.numRows(); rowNum++) {
				CCBSModel model = getAt(rowNum);
				update(model);
			}
		}
	}

	private class CCBSModel extends BaseModel {
		private PageDecoration decoration;
		private int rowNum;

		private Page page;

		public CCBSModel(PageDecoration decoration, int rowNum) {
			this.decoration = decoration;
			this.rowNum = rowNum;

			if (!decoration.getelementUnit(rowNum).equalsIgnoreCase("")) {
				set("header", decoration.getRowHeader(rowNum) + " ("
						+ decoration.getelementUnit(rowNum) + ")");
			} else {
				set("header", decoration.getRowHeader(rowNum));
			}
			// result[0] is the element, result[1] is the unit of the element
			// System.out.println("Class: PagePanel Function: CCBSModel Text: Before call  parseRowHeader");
			String result[] = parseRowHeader(decoration.getRowHeader(rowNum),
					decoration.getelementUnit(rowNum));
			System.out
					.println("Class: PagePanel Function: CCBSModel Text: After call  parseRowHeader result[0]"
							+ result[0] + " result[1] " + result[1]);
			// System.out.println("Class: Page Panel Function: CCBSModel Text: decoration.getRowHeader(rowNum)"+
			// decoration.getRowHeader(rowNum)+
			// " decoration.getelementUnit(rowNum) "+
			// decoration.getelementUnit(rowNum));
			for (int colNum = 0; colNum < decoration.numCols(); colNum++) {
				// System.out.println(" Test Inside Class: Page Panel Function: CCBSModel Text: decoration.getRowHeader(rowNum)"+
				// decoration.getRowHeader(rowNum));
				set("col_" + colNum + "_ELEMENT", result[0]);
				// System.out.println(" Test Inside Class: Page Panel Function: CCBSModel Text: decoration.getRowHeader(rowNum)"+
				// decoration.getRowHeader(rowNum));
				// System.out.println("Inside Col "+colNum+" result[1]: "+
				// result[1]);

				set("col_" + colNum + "_ELEMENT_UNIT", result[1]);
				// if(!decoration.getelementUnit(rowNum).equalsIgnoreCase(""))
				// {
				// set("col_"+ colNum+ "_ELEMENT_UNIT", result[1]);
				// }
				// else
				// {
				// set("col_"+ colNum+ "_ELEMENT_UNIT", "");
				// }
				// System.out.println("Inside Col "+colNum+" Element Unit : "+
				// get("col_"+ colNum+ "_ELEMENT_UNIT"));

				// System.out.println("Inside Year Start");
				// for(String year2 :CCBS.YEARS_ON_GRID)
				// {
				// System.out.println("YEar " + year2);
				// }
				// System.out.println("Inside Year End");
				// The real value
				// if(colNum%2!=0)
				// {
				// //Flags Column
				// set("col_"+ colNum, "G");
				// }
				// else
				// {
				// //Values Column
				// set("col_"+ colNum, "");
				// }
				set("col_" + colNum, "");

				// For all the columns
				if ((colNum != (decoration.numCols() - 2))
						&& (colNum != (decoration.numCols() - 1))) {
					// set("col_"+ colNum+ "_FORECAST",
					// CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS.get(colNum)));
					// set("col_"+ colNum+ "_YEAR",""+CCBS.YEARS.get(colNum));

					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.YEARS_ON_GRID.get(colNum): "+CCBS.YEARS_ON_GRID.get(colNum));
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS_ON_GRID.get(colNum)): "+CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS_ON_GRID.get(colNum)));
					set("col_" + colNum + "_FORECAST", CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS_ON_GRID.get(colNum)));
					set("col_" + colNum + "_YEAR", ""+ CCBS.YEARS_ON_GRID.get(colNum));
				} else {
					// set("col_"+ colNum+ "_FORECAST",
					// CCBS.MONTH_FOR_YEARS.get(PagePanelController.getLastYearToShow()));
					// set("col_"+ colNum+ "_FORECAST",
					// CCBS.MONTH_WITH_FLAG_LONG[0]);
					// set("col_"+ colNum+ "_FORECAST",
					// decoration.getFirstMonthMarketingYear());
					// set("col_"+ colNum+ "_FORECAST",
					// decoration.getMonthForYears().get(CCBS.OTHER_YEARS.get(0)));
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.OTHER_YEARS.get(0): "+CCBS.OTHER_YEARS.get(0));
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0)): "+CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0)));
					// Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
					// for(String key:keySet)
					// {
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: key= "+key);
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
					// }
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.OTHER_YEARS.get(0): "+CCBS.OTHER_YEARS.get(0));
					// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text: CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0)): "+CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0)));
					if ((CCBS.FIRST_MONTH_COMBO != null)
							&& (!CCBS.FIRST_MONTH_COMBO.equals(""))
							&& ((CCBS.YEARS.get(CCBS.YEARS.size() - 1))
									.equals(CCBS.OTHER_YEARS.get(0)))
							&& (CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS
									.get(0)) != null)) {
						if (CCBS.MONTH_FOR_YEARS.get((CCBS.OTHER_YEARS.get(0)))
								.equals(CCBS.LAST_MONTH_COMBO)) {
							// This is the last month of the Marketing Year
							set("col_" + colNum + "_FORECAST", CCBS.N_A_MONTH);
						} else {
							set(
									"col_" + colNum + "_FORECAST",
									CCBSParametersPanel
											.getOneMonthAfter(CCBS.MONTH_FOR_YEARS
													.get(CCBS.OTHER_YEARS
															.get(0))));
						}
						// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text:1 CCBSParametersPanel.getOneMonthAfter(CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0))): ");
					} else if (CCBS.MONTH_FOR_YEARS
							.get(CCBS.OTHER_YEARS.get(0)) != null) {
						set("col_" + colNum + "_FORECAST", CCBS.MONTH_FOR_YEARS
								.get(CCBS.OTHER_YEARS.get(0)));
						// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text:2 CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0)): "+CCBS.MONTH_FOR_YEARS.get(CCBS.OTHER_YEARS.get(0)));
					} else {
						ListStore<MonthForecast> monthForecast = new ListStore<MonthForecast>();
						// monthForecast.add(TestMonthForecast.getMonthForecast());
						boolean firstTime = true;
						monthForecast.add(TestMonthForecast
								.getMonthForecastFromCbsDb(firstTime));
						String monthForecastString = monthForecast.getAt(0)
								.getMonthForecast();
						// System.out.println("Class PagePanel*******************************  Function: CCBSModel Text:3 monthForecastString: "+monthForecastString);
						set("col_" + colNum + "_FORECAST", monthForecastString);
					}

					// set("col_"+ colNum+ "_YEAR",
					// PagePanelController.getLastYearToShow());
					set("col_" + colNum + "_YEAR", CCBS.OTHER_YEARS.get(0));

				}
				// else
				// {
				// //For the last two columns
				// if(!decoration.getDataSource().equals(CCBSParametersPanel.firstElement))
				// {
				// //set("col_"+ colNum+ "_FORECAST",
				// CCBS.MONTH_FOR_YEARS.get(PagePanelController.getLastYearToShow()));
				// set("col_"+ colNum+ "_FORECAST",
				// CCBS.MONTH_WITH_FLAG_LONG[0]);
				// //set("col_"+ colNum+ "_YEAR",
				// PagePanelController.getLastYearToShow());
				// set("col_"+ colNum+ "_YEAR", CCBS.OTHER_YEARS.get(0));
				// }
				// else
				// {
				// set("col_"+ colNum+ "_FORECAST",
				// CCBS.MONTH_WITH_FLAG_LONG[0]);
				// //set("col_"+ colNum+ "_FORECAST",
				// CCBS.MONTH_FOR_YEARS.get("2011/12"));
				// //set("col_"+ colNum+ "_YEAR", "2011/12");
				// set("col_"+ colNum+ "_YEAR", CCBS.OTHER_YEARS.get(0));
				// }
				// }

				if ((colNum % 2) == 0) {
					set("col_" + colNum + "_FLAG", "False");
				} else {
					set("col_" + colNum + "_FLAG", "True");
				}
				set("col_" + colNum + "_VALUE_TYPE", "TOTAL");
			}
		}

		public void loadRow(Page page, int rowNum)
		{
			this.page = page;
			int newColumn=0;
			boolean isEditable, isRowProductionOrOtherUses, hasWidget;
			//System.out.println("Class: PagePanel Function:loadRow Text: Start");
			for (int colNum = 0; colNum < decoration.numCols() ; colNum++)
			{
				hasWidget = false;
				isEditable = decoration.isRowEditable(rowNum) && colNum != 0 && decoration.isColEditable(colNum - 1);
				isRowProductionOrOtherUses = (rowNum==CCBS.PRODUCTION_ROW_NUM)||(rowNum==CCBS.AREA_HARVESTED_ROW_NUM)||(rowNum==CCBS.OTHER_USES_ROW_NUM);
				if(colNum==6)
				{
//					System.out.println("Class: PagePanel Function:loadRow Text: "+rowNum+""+decoration.getRowHeader(rowNum));
//					System.out.println("Class: PagePanel Function:loadRow Text: row "+rowNum+"decoration.getColHeader(colNum)"+decoration.getColHeader(colNum));
//					System.out.println("Class: PagePanel Function:loadRow Text: rowNum "+rowNum+" isEditable "+isEditable);
//					System.out.println("Class: PagePanel Function:loadRow Text: rowNum "+rowNum+" decoration.isRowEditable(rowNum) "+decoration.isRowEditable(rowNum));
//					System.out.println("Class: PagePanel Function:loadRow Text: page.getCell(rowNum, colNum).getText() *"+page.getCell(rowNum, colNum).getText()+"*");
				}
					
				//isEditable = decoration.isRowEditable(rowNum) && decoration.isColEditable(colNum - 1);
				//if((colNum%2!=0)&&(isEditable)&&((page.getCell(rowNum, colNum).getText()==null)||(page.getCell(rowNum, colNum).getText().equals(""))))
				if((colNum%2!=0)&&(isEditable||isRowProductionOrOtherUses)&&((page.getCell(rowNum, colNum).getText()==null)||(page.getCell(rowNum, colNum).getText().equals(""))||(page.getCell(rowNum, colNum).getText().equals(" "))||(page.getCell(rowNum, colNum).getText().equals("null"))))
				{
					//Flags cell without Value and not calculated
					//System.out.println("Class: PagePanel Function:loadRow Text: IF!!!!!!!!!!!!!!!!!"+rowNum+""+decoration.getRowHeader(rowNum));
					//Default flag is 'G'
					set("col_" + colNum, CCBS.FLAGS.getAt(2).getCode());
				}
				else if((rowNum == CCBS.OPENING_STOCKS_ROW_NUM)&&(colNum>(decoration.numCols()-7)))
				{
					//System.out.println("Class: PagePanel Function:loadRow Text: else if((rowNum == CCBS.OPENING_ROW_NUM)&&(colNum>(decoration.numCols()-7))) colNum = "+colNum+" decoration.numCols() - 2= "+(decoration.numCols() - 2));
					//This is for setting the rigth value for the Opening Stocks Element of the last three columns
					String yearValue = "";
					ColumnConfig colConf = grid.getColumnModel().getColumn(colNum);
					// System.out.println("Clas: PagePanel Function:checkLastColumnYear Text: colConf.getWidget()= "+colConf.getWidget());
					if (colConf.getWidget() != null) {
						HorizontalPanel hp = (HorizontalPanel) colConf.getWidget();
						ComboBox<AMISCodesModelData> comboYear = (ComboBox<AMISCodesModelData>) hp.getItemByItemId("ComboYear");
						ComboBox<MonthForecast> comboMonth = (ComboBox<MonthForecast>) hp.getItemByItemId("ComboMonth");
						yearValue = comboYear.getValue().getLabel();
						hasWidget=true;
					//	System.out.println("Class: PagePanel Function:loadRow Text: IF!!!!!!!!!!!!!!!!! colNum = "+colNum+" yearValue "+yearValue);
					}
					else
					{						
						yearValue = decoration.getColHeader(colNum);
					//	System.out.println("Class: PagePanel Function:loadRow Text: ELSE!!!!!!!!!!!!!!!!! colNum = "+colNum+" yearValue "+yearValue);
					}
					if((yearValue!=null)&&(yearValue!="")&&(yearValue.length()>4))
					{
						yearValue = yearValue.substring(0, 4);
						int year = Integer.parseInt(yearValue);
						year--;
						//Take the value for Opening Stocks from the hashmap OPENING_CLOSING_STOCKS_HASHMAP
						//The value is equal to the Closing Stocks of th previous year
						String openingClosingStocksValues[]= CCBSParametersPanel.getOpeningClosingStocksValues(""+year);										
					//	System.out.println("Class: PagePanel Function:loadRow Text: rowNum "+rowNum+" colNum "+colNum+" "+decoration.getRowHeader(rowNum));
					//	System.out.println("Class: PagePanel Function:loadRow Text: row "+rowNum+" decoration.getColHeader(colNum) "+decoration.getColHeader(colNum));
	
						if(hasWidget)//TO DO:The widget is found in the wrong column(13 and not 12)
						{
							newColumn = colNum-1;
							set("col_" + newColumn, openingClosingStocksValues[1]);
						}
						else
						{
							set("col_" + colNum, openingClosingStocksValues[1]);
						}
						//set("col_" + colNum, openingClosingStocksValues[1]);
					}
					else
					{
						set("col_" + colNum, page.getCell(rowNum, colNum).getText());
					}
				}
				else
				{
					//Value cell
					//System.out.println("Class: PagePanel Function:loadRow Text: ELSE!!!!!!!!!!!!!!!!!"+rowNum+""+decoration.getRowHeader(rowNum));
					set("col_" + colNum, page.getCell(rowNum, colNum).getText());
				}
			//	System.out.println("Class: PagePanel Function:loadRow Text: Row= "+ rowNum +" Col= "+colNum+ " Value: "+ page.getCell(rowNum, colNum).getText());
			//	set("col_" + colNum, page.getCell(rowNum, colNum).getText());
			}
		//	System.out.println("Class: PagePanel Function:loadRow Text: End");
		}

		public void unloadRow() {
			for (int colNum = 0; colNum < decoration.numCols(); colNum++)
				set("col_" + colNum, "");
		}

		public String[] parseRowHeader(String rowHeader, String rowHeaderUnit) {
			// System.out.println("Class: PagePanel Function: parseRowHeader Text: rowHeader "+
			// rowHeader);
			String result[] = new String[2];
			int firstBracket = rowHeader.indexOf("(");
			if ((firstBracket != -1) && (!rowHeader.equals("Imports (NMY)"))
					&& (!rowHeader.equals("Exports (NMY)"))) {
				int secondBracket = rowHeader.indexOf(")");

				// System.out.println("Class: PagePanel Function: parseRowHeader Text: firstBracket "+
				// firstBracket);
				// System.out.println("Class: PagePanel Function: parseRowHeader Text: secondBracket "+
				// secondBracket);
				// result[0] = new String(""+rowHeader.substring(0,
				// (firstBracket-1)));
				result[0] = new String(""
						+ rowHeader.substring(0, (firstBracket - 1)));
				// System.out.println("Class: PagePanel Function: parseRowHeader Text: if rowHeader "+
				// rowHeader);
				// System.out.println("Class: PagePanel Function: parseRowHeader Text: if firstBracket "+
				// (firstBracket+1));
				// System.out.println("Class: PagePanel Function: parseRowHeader Text: if secondBracket "+
				// (secondBracket-1));
				result[1] = new String(""
						+ rowHeader.substring(firstBracket + 1, secondBracket));
				// result[1] = new String(""+rowHeader.substring(firstBracket
				// +1, 20));
				// System.out.println("Class: PagePanel Function: parseRowHeader Text: if result[1] "+
				// result[1]);
			} else {
				result[0] = rowHeader;
				result[1] = rowHeaderUnit;
			}
			return result;
		}
	}

}
