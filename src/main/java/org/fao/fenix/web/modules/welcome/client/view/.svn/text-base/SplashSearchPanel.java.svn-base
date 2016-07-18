package org.fao.fenix.web.modules.welcome.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.welcome.client.control.SplashWindowController;
import org.fao.fenix.web.modules.welcome.common.vo.QueryResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HTML;

public class SplashSearchPanel {

	private String FIELD_WIDTH = "120px";

	private String HEIGHT = "490px";

	private String SEARCH_FIELD_WIDTH = "100px";

	private String WIDTH = "885px";

	private int SPACING = 2;

	private Button openMetaDataButton;

	private Button openResourceButton;

	private Button searchButton;

	private CheckBoxSelectionModel<QueryResultVO> sm = null;

	private ComboBox<CategoryModelData> categoryComboBox;

	private ComboBox<GaulModelData> regionComboBox;

	private ComboBox<GaulModelData> resourceTypeComboBox;

	private DateField fromDate = new DateField();

	private DateField toDate = new DateField();

	private Grid<QueryResultVO> grid;

	private HorizontalPanel horizontalPanel = null;

	private ListStore<CategoryModelData> categoryStore;

	private ListStore<GaulModelData> regionStore;

	private ListStore<GaulModelData> resourceTypeStore;

	private ListStore<QueryResultVO> store;

	private TextField<String> source;

	private TextField<String> freeText;

	private VerticalPanel panel;

	//private IconButton bc = new IconButton("projectIcon");
	
	GridCellRenderer<QueryResultVO> buttonRenderer = new GridCellRenderer<QueryResultVO>() {

		private boolean init;

		public Object render(final QueryResultVO model, String property,
				ColumnData config, final int rowIndex, final int colIndex,
				ListStore<QueryResultVO> store, Grid<QueryResultVO> grid) {

			String rt = model.getResourceType().substring(
					1 + model.getResourceType().lastIndexOf('.'));
			System.out.println("model.getResourceType() " + rt);
			if (!init) {
				init = true;
				grid.addListener(Events.ColumnResize,
						new Listener<GridEvent<QueryResultVO>>() {

							public void handleEvent(GridEvent<QueryResultVO> be) {
								for (int i = 0; i < be.getGrid().getStore()
										.getCount(); i++) {
									if (be.getGrid().getView().getWidget(i,
											be.getColIndex()) != null
											&& be.getGrid().getView()
													.getWidget(i,
															be.getColIndex()) instanceof BoxComponent) {
										((BoxComponent) be.getGrid().getView()
												.getWidget(i, be.getColIndex()))
												.setWidth(be.getWidth() - 10);
									}
								}
							}
						});
			}

			IconButton b = new IconButton("");

			try {
				b = new IconButton(SplashWindowController.iconMap.get(rt));
			} catch (Exception e) {
			}

			return b;
		}
	};

	GridCellRenderer<QueryResultVO> buttonToOpen = new GridCellRenderer<QueryResultVO>() {

		private boolean init;

		public Object render(final QueryResultVO model, final String property,
				ColumnData config, final int rowIndex, final int colIndex,
				ListStore<QueryResultVO> store, Grid<QueryResultVO> grid) {

			/*
			 * String rt = model.getResourceType().substring( 1 +
			 * model.getResourceType().lastIndexOf('.'));
			 * System.out.println("model.getResourceType() " + rt);
			 */
			System.out.println("model.getResourceType() " + property);

			if (!init) {
				init = true;
				grid.addListener(Events.ColumnResize,
						new Listener<GridEvent<QueryResultVO>>() {

							public void handleEvent(GridEvent<QueryResultVO> be) {
								for (int i = 0; i < be.getGrid().getStore()
										.getCount(); i++) {
									if (be.getGrid().getView().getWidget(i,
											be.getColIndex()) != null
											&& be.getGrid().getView()
													.getWidget(i,
															be.getColIndex()) instanceof BoxComponent) {
										((BoxComponent) be.getGrid().getView()
												.getWidget(i, be.getColIndex()))
												.setWidth(be.getWidth() - 10);
									}
								}
							}
						});
			}

			IconButton b = new IconButton("projectIcon");

			if (property.equalsIgnoreCase("METADATA")) {
				b = new IconButton("viewMetadata");
				b.setToolTip("Click to Open Metadata.");
				b.addSelectionListener(SplashWindowController.metadata(model));
			} else if (property.equalsIgnoreCase("OPEN")) {
				b = new IconButton("pmOpenResource");
				b.setToolTip("Click to Open Resource.");
				b.addSelectionListener(SplashWindowController.open(model));
			}

			return b;
		}
	};// end of buttonToOpen

	public SplashSearchPanel() {

		panel = new VerticalPanel();
		panel.setLayout(new FitLayout());
		panel.setSize(WIDTH, HEIGHT);
		regionStore = new ListStore<GaulModelData>();
		regionComboBox = new ComboBox<GaulModelData>();
		regionComboBox.setTriggerAction(TriggerAction.ALL);
		regionComboBox.setStore(regionStore);
		regionComboBox.setDisplayField("gaulLabel");
		MEController.fillGaulStore(regionStore);
		regionStore.insert(new GaulModelData(" ", " "), 0);

		resourceTypeStore = new ListStore<GaulModelData>();
		resourceTypeComboBox = new ComboBox<GaulModelData>();
		resourceTypeComboBox.setTriggerAction(TriggerAction.ALL);
		resourceTypeComboBox.setStore(resourceTypeStore);
		resourceTypeComboBox.setDisplayField("gaulLabel");
		resourceTypeStore.insert(new GaulModelData(" ", " "), 0);

		categoryStore = new ListStore<CategoryModelData>();
		categoryComboBox = new ComboBox<CategoryModelData>();
		categoryComboBox.setTriggerAction(TriggerAction.ALL);
		categoryComboBox.setStore(categoryStore);
		categoryComboBox.setDisplayField("categoryName");
		
		MEController.fillCategoryPanel(categoryStore);
		categoryStore.insert(new CategoryModelData(" ", " "), 0);

		store = new ListStore<QueryResultVO>();
	}

	public VerticalPanel build() {
		panel.add(buildSearchPanel());
		horizontalPanel = buildResultPanel(5);
		panel.add(horizontalPanel);
		panel.add(buildPagerPanel());
		return panel;
	}

	private HorizontalPanel buildResultPanel(int lll) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSize(WIDTH, "440px");
		p.setStyleAttribute("backgroundColor", "#9ECC00");

		List<ColumnConfig> configs = createColumnConfigurations();

		store = new ListStore<QueryResultVO>();

		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<QueryResultVO>(store, cm);

		grid.setStyleAttribute("borderTop", "none");
		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setBorders(false);
		grid.setStripeRows(true);
		grid.addPlugin(sm);
		grid.setSelectionModel(sm);
		grid.setSize(WIDTH, "440px");

		p.add(grid);
		return p;
	}

	private List<ColumnConfig> createColumnConfigurations() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		sm = new CheckBoxSelectionModel<QueryResultVO>();
		configs.add(sm.getColumn());

		ColumnConfig column = new ColumnConfig("resourceType", "", 18);
		column.setRenderer(buttonRenderer);
		configs.add(column);

		column = new ColumnConfig("title", "Name", 200);
		configs.add(column);

		column = new ColumnConfig("source", "Source", 150);
		configs.add(column);
		column = new ColumnConfig("region", "Geographic Area", 150);
		configs.add(column);
		column = new ColumnConfig("categories", "Category", 150);
		configs.add(column);
		column = new ColumnConfig("datelastupdate", "Date Last Updated", 150);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
		configs.add(column);

		column = new ColumnConfig("METADATA", "", 30);
		column.setRenderer(buttonToOpen);

		configs.add(column);

		column = new ColumnConfig("OPEN", "", 30);
		column.setRenderer(buttonToOpen);

		configs.add(column);

		return configs;
	}

	private HorizontalPanel buildPagerPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSize(WIDTH, "50px");
		p.setStyleAttribute("backgroundColor", "#51A828");
		/*
		 * p.add(new IconButton("x-btn-text x-tbar-page-first")); p.add(new
		 * IconButton("x-btn-text x-tbar-page-prev")); p.add(newHTML(
		 * "<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
		 * + "Page 1 of 27</div>")); p.add(new
		 * IconButton("x-btn-text x-tbar-page-next")); p.add(new
		 * IconButton("x-btn-text x-tbar-page-last"));
		 */
		HorizontalPanel spacer = new HorizontalPanel();
		spacer.setSize("450px", "50px");
		p.add(spacer);

		openMetaDataButton = new Button("Open Selected Metadata");
		openMetaDataButton.setToolTip("Click to Open Selected Metadata.");
		
		openResourceButton = new Button("Open Selected Resource");
		openResourceButton.setToolTip("Click to Open Selected Resource.");
		
		p.add(openMetaDataButton);
		p.add(openResourceButton);
		return p;
	}

	private HorizontalPanel buildSearchPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSize(WIDTH, "50px");
		p.setStyleAttribute("backgroundColor", "#51A828");
		p.add(buildSearchFieldPanel());
		p.add(buildResourceFieldPanel());
		p.add(buildResourceTypePanel());
		p.add(buildRegionPanel());
		p.add(buildCategoryPanel());
		p.add(buildFromDateFieldPanel());
		p.add(buildToDateFieldPanel());
		// p.add(buildSortByPanel());
		// p.add(buildOrderPanel());
		p.add(buildSearchButtonPanel());
		return p;
	}

	private VerticalPanel buildSearchButtonPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		searchButton = new Button("Search!");
		searchButton.setToolTip("Click to Search.");
		searchButton.setSize("90px", "40px");
		p.add(searchButton);
		return p;
	}

	private VerticalPanel buildToDateFieldPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "To Date</div>");
		label.setWidth(SEARCH_FIELD_WIDTH);
		toDate = new DateField();
		toDate.setWidth(SEARCH_FIELD_WIDTH);
		toDate.setValue(new Date());
		// toDate.setEmptyText("e.g. rice price");
		p.add(label);
		p.add(toDate);
		return p;
	}

	private VerticalPanel buildFromDateFieldPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "From Date</div>");
		label.setWidth(SEARCH_FIELD_WIDTH);
		fromDate = new DateField();
		fromDate.setWidth(SEARCH_FIELD_WIDTH);
		fromDate.setValue(FieldParser.parseDate("1990-01-01"));
		// toDate.setEmptyText("e.g. rice price");
		p.add(label);
		p.add(fromDate);
		return p;
	}

	private VerticalPanel buildSearchFieldPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "Free Text</div>");
		label.setWidth(SEARCH_FIELD_WIDTH);
		freeText = new TextField<String>();
		freeText.setWidth(SEARCH_FIELD_WIDTH);
		freeText.setEmptyText("e.g. rice price");
		p.add(label);
		p.add(freeText);
		return p;
	}

	private VerticalPanel buildResourceFieldPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "Source</div>");
		label.setWidth(SEARCH_FIELD_WIDTH);
		source = new TextField<String>();
		source.setWidth(SEARCH_FIELD_WIDTH);
		source.setEmptyText("e.g. DAM");
		p.add(label);
		p.add(source);
		return p;
	}

	private VerticalPanel buildCategoryPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "Category</div>");
		label.setWidth(FIELD_WIDTH);
		categoryComboBox.setWidth(FIELD_WIDTH);
		categoryComboBox.setEmptyText("e.g. Food Security");
		p.add(label);
		p.add(categoryComboBox);
		return p;
	}

	private VerticalPanel buildRegionPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "Region/Geography</div>");
		label.setWidth(FIELD_WIDTH);
		regionComboBox.setWidth(FIELD_WIDTH);
		regionComboBox.setEmptyText("e.g. Bangladesh");
		p.add(label);
		p.add(regionComboBox);
		return p;
	}

	private VerticalPanel buildResourceTypePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setStyleAttribute("backgroundColor", "#51A828");
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "Resource Type</div>");
		label.setWidth(FIELD_WIDTH);
		resourceTypeComboBox.setWidth(FIELD_WIDTH);
		resourceTypeComboBox.getStore().add(
				new GaulModelData("All", "Resource"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Dataset", "CoreDataset"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Layer", "WMSMapProvider"));
		resourceTypeComboBox.getStore()
				.add(new GaulModelData("Map", "MapView"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Report", "TinyMCEReport"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Table", "TableView"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Multidimensional Table", "MTParameters"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Coding System", "CodingSystem"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Chart", "ChartDesign"));
		resourceTypeComboBox.getStore().add(
				new GaulModelData("Image", "FenixImage"));
		// resourceTypeComboBox.getStore().add(
		// new GaulModelData("Document", "FenixImage"));
		resourceTypeComboBox.setEmptyText("e.g. Dataset");
		resourceTypeComboBox.setValue(new GaulModelData("Dataset",
				"CoreDataset"));
		p.add(label);
		p.add(resourceTypeComboBox);
		return p;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public ComboBox<GaulModelData> getResourceTypeComboBox() {
		return resourceTypeComboBox;
	}

	public ComboBox<GaulModelData> getRegionComboBox() {
		return regionComboBox;
	}

	public ComboBox<CategoryModelData> getCategoryComboBox() {
		return categoryComboBox;
	}

	public TextField<String> getFreeText() {
		return freeText;
	}

	public Button getOpenMetaDataButton() {
		return openMetaDataButton;
	}

	public Button getOpenResourceButton() {
		return openResourceButton;
	}

	public ListStore<QueryResultVO> getStore() {
		return store;
	}

	public void setStore(ListStore<QueryResultVO> store) {
		this.store = store;
	}

	public CheckBoxSelectionModel<QueryResultVO> getSm() {
		return sm;
	}

	public Grid<QueryResultVO> getGrid() {
		return grid;
	}

	public TextField<String> getSource() {
		return source;
	}

	public DateField getFromDate() {
		return fromDate;
	}

	public DateField getToDate() {
		return toDate;
	}
}