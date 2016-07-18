package org.fao.fenix.web.modules.metadataeditor.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.fpi.common.vo.PeriodTypeCodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.PeriodTypeCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.SharingCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class MetadataPanel extends LayoutContainer {
	
	private VerticalPanel wrapper;
	
	private Html tooltip;
	
	private TextField<String> title;
	
	private TextField<String> keywords;
	
	private TextField<String> code;
	
	private TextField<String> sourceName;
	
	private TextField<String> sourceContact;
	
	private ComboBox<PeriodTypeCodeModelData> periodList;
	
	private ListStore<PeriodTypeCodeModelData> periodStore; 
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<GaulModelData> sourceGaulStore;
	
	private ComboBox<GaulModelData> sourceGaulList;
	
	private ListStore<CategoryModelData> categoryStore;
	
	private ComboBox<CategoryModelData> categoryList;
	
	private ListStore<SharingCodeModelData> sharingStore;
	
	private ComboBox<SharingCodeModelData> sharingList;
	
	private TextArea abstractAbstract;
	
	private List<String> mandatoryFields;
	
	private boolean isEditable;
	
	private final static int SINGLE_SPACING = 2;
	
	private final static int DOUBLE_SPACING = 4;
	
	private final static int TRIPLE_SPACING = 6;
	
	private final static String TOOLTIP_WIDTH = "750px";
	
	private final static String SINGLE_OBJECT_WIDTH = "765px";
	
	private final static String DOUBLE_OBJECT_WIDTH = "373px";
	
	private final static String TRIPLE_OBJECT_WIDTH = "244px";
	
	private final static String TEXT_AREA_HEIGHT = "110px";
	
	public MetadataPanel(boolean isEditable) {
		this.setEditable(isEditable);
		fillMandatoryFields();
		wrapper = new VerticalPanel();
		wrapper.setSpacing(SINGLE_SPACING);
		wrapper.setLayout(new FillLayout());
		tooltip = new Html("<div style='color: #15428B;'>" + BabelFish.print().stepThree() + "</div>");
		tooltip.setWidth(TOOLTIP_WIDTH);
		title = new TextField<String>();
		title.setWidth(SINGLE_OBJECT_WIDTH);
		title.setEmptyText(BabelFish.print().tooltiptitle());
		keywords = new TextField<String>();
		keywords.setWidth(DOUBLE_OBJECT_WIDTH);
		keywords.setEmptyText(BabelFish.print().tooltipkeywords());
		periodList = new ComboBox<PeriodTypeCodeModelData>();
		periodList.setTriggerAction(TriggerAction.ALL);
		periodList.setEmptyText(BabelFish.print().tooltipPeriodicity());
		periodStore = createPeriodTypeStore();
		periodList.setStore(periodStore);
		periodList.setAllowBlank(false);
		periodList.setDisplayField("periodTypeCode");
		periodList.setWidth(DOUBLE_OBJECT_WIDTH);
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth(DOUBLE_OBJECT_WIDTH);
		gaulList.setEmptyText(BabelFish.print().tooltipregion());
		categoryStore = new ListStore<CategoryModelData>(); 
		categoryList = new ComboBox<CategoryModelData>();
		categoryList.setTriggerAction(TriggerAction.ALL);
		categoryList.setStore(categoryStore);
		categoryList.setDisplayField("categoryName");
		categoryList.setWidth(DOUBLE_OBJECT_WIDTH);
		categoryList.setEmptyText(BabelFish.print().tooltipcategory());
		sharingStore = new ListStore<SharingCodeModelData>(); 
		fillSharingStore();
		sharingList = new ComboBox<SharingCodeModelData>();
		sharingList.setTriggerAction(TriggerAction.ALL);
		sharingList.setStore(sharingStore);
		sharingList.setDisplayField("sharingCode");
		sharingList.setWidth(DOUBLE_OBJECT_WIDTH);
		sharingList.setEmptyText(BabelFish.print().tooltipsharingCode());
		code = new TextField<String>();
		code.setWidth(DOUBLE_OBJECT_WIDTH);
		code.setEmptyText(BabelFish.print().tooltipcode());
		sourceName = new TextField<String>();
		sourceName.setWidth(TRIPLE_OBJECT_WIDTH);
		sourceName.setEmptyText(BabelFish.print().tooltipsource());
		sourceContact = new TextField<String>();
		sourceContact.setWidth(TRIPLE_OBJECT_WIDTH);
		sourceContact.setEmptyText(BabelFish.print().tooltipcontact());
		sourceGaulStore = new ListStore<GaulModelData>(); 
		sourceGaulList = new ComboBox<GaulModelData>();
		sourceGaulList.setTriggerAction(TriggerAction.ALL);
		sourceGaulList.setStore(sourceGaulStore);
		sourceGaulList.setDisplayField("gaulLabel");
		sourceGaulList.setWidth(TRIPLE_OBJECT_WIDTH);
		sourceGaulList.setEmptyText(BabelFish.print().tooltipSourceRegion());
		abstractAbstract = new TextArea();
		abstractAbstract.setSize(SINGLE_OBJECT_WIDTH, TEXT_AREA_HEIGHT);
		abstractAbstract.setEmptyText(BabelFish.print().tooltipabstractAbstract());
		MEController.fillCategoryPanel(categoryStore);
		MEController.fillGaulStore(gaulStore);
		MEController.fillGaulStore(sourceGaulStore);
		setPrivileges();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		wrapper.add(buildTooltipPanel(tooltip));
		wrapper.add(buildSingleObjectPanel(BabelFish.print().title(), title));
		wrapper.add(buildKeywordsPeriodicityPanel());
		wrapper.add(buildRegionCategoryPanel());
		wrapper.add(buildSharingPolicyCodePanel());
		wrapper.add(buildSourcePanel());
		wrapper.add(buildSingleObjectPanel(BabelFish.print().abstractAbstract(), abstractAbstract));
		add(wrapper);
	}
	
	private HorizontalPanel buildSourcePanel() {
		List<String> labels = new ArrayList<String>();
		labels.add(BabelFish.print().source());
		labels.add(BabelFish.print().sourceRegion());
		labels.add(BabelFish.print().contact());
		List<Widget> objects = new ArrayList<Widget>();
		objects.add(sourceName);
		objects.add(sourceGaulList);
		objects.add(sourceContact);
		return buildObjectPanel(labels, objects, SINGLE_SPACING);
	}
	
	private HorizontalPanel buildSharingPolicyCodePanel() {
		List<String> labels = new ArrayList<String>();
		labels.add(BabelFish.print().sharingCode());
		labels.add(BabelFish.print().code());
		List<Widget> objects = new ArrayList<Widget>();
		objects.add(sharingList);
		objects.add(code);
		return buildObjectPanel(labels, objects, SINGLE_SPACING);
	}
	
	private HorizontalPanel buildRegionCategoryPanel() {
		List<String> labels = new ArrayList<String>();
		labels.add(BabelFish.print().region());
		labels.add(BabelFish.print().category());
		List<Widget> objects = new ArrayList<Widget>();
		objects.add(gaulList);
		objects.add(categoryList);
		return buildObjectPanel(labels, objects, SINGLE_SPACING);
	}
	
	private HorizontalPanel buildKeywordsPeriodicityPanel() {
		List<String> labels = new ArrayList<String>();
		labels.add(BabelFish.print().keywords());
		labels.add(BabelFish.print().periodicity());
		List<Widget> objects = new ArrayList<Widget>();
		objects.add(keywords);
		objects.add(periodList);
		return buildObjectPanel(labels, objects, SINGLE_SPACING);
	}
	
	private HorizontalPanel buildObjectPanel(List<String> labels, List<Widget> objects, int spacing) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(spacing);
		for (int i = 0 ; i < labels.size() ; i++)
			p.add(buildSingleObjectPanel(labels.get(i), objects.get(i)));
		return p;
	}
	
	private VerticalPanel buildSingleObjectPanel(String label, Widget object) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(TRIPLE_SPACING);
		Html lbl = null;
		if (mandatoryFields.contains(label)) {
			lbl = new Html("<div style='color: #CA1616; font-weight: bold;'>" + label + "</div>");
		} else {
			lbl = new Html("<div style='color: #15428B; font-weight: bold;'>" + label + "</div>");
		}
		p.add(lbl);
		p.add(object);
		return p;
	}
	
	private VerticalPanel buildTooltipPanel(Widget object) {
		VerticalPanel p = new VerticalPanel();
		VerticalPanel p2 = new VerticalPanel();
		p.setSpacing(TRIPLE_SPACING);
		p2.setSpacing(TRIPLE_SPACING);
		p2.setBorders(true);
		p2.add(object);
		p.add(p2);
		return p;
	}
	
	private ListStore<PeriodTypeCodeModelData> createPeriodTypeStore() {
		ListStore<PeriodTypeCodeModelData> periodStore = new ListStore<PeriodTypeCodeModelData>();
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.daily.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.weekly.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.decade.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.monthly.name()));
		periodStore.add(new PeriodTypeCodeModelData(PeriodTypeCodeVo.yearly.name()));
		return periodStore;
	}
	
	private void fillSharingStore() {
		sharingStore.add(new SharingCodeModelData("Public"));
		sharingStore.add(new SharingCodeModelData("PublicAndDownload"));
	}
	
	public void fillMandatoryFields() {
		mandatoryFields = new ArrayList<String>();
		mandatoryFields.add(BabelFish.print().title()); 
		mandatoryFields.add(BabelFish.print().keywords()); 
		mandatoryFields.add(BabelFish.print().sharingCode()); 
		mandatoryFields.add(BabelFish.print().region()); 
		mandatoryFields.add(BabelFish.print().category());
		mandatoryFields.add(BabelFish.print().periodicity());
		mandatoryFields.add(BabelFish.print().source());
		mandatoryFields.add(BabelFish.print().sourceRegion());
	}
	
	public void setPrivileges(boolean isEditable) {
		this.setEditable(isEditable);
		setPrivileges();
	}
	
	public void setPrivileges() {
		title.setAllowBlank(!this.isEditable());
		title.setReadOnly(!this.isEditable());
		keywords.setAllowBlank(!this.isEditable());
		keywords.setReadOnly(!this.isEditable());
		sourceName.setAllowBlank(!this.isEditable());
		sourceName.setReadOnly(!this.isEditable());
		gaulList.setAllowBlank(!this.isEditable());
		gaulList.setReadOnly(!this.isEditable());
		sourceGaulList.setAllowBlank(!this.isEditable());
		sourceGaulList.setReadOnly(!this.isEditable());
		categoryList.setAllowBlank(!this.isEditable());
		categoryList.setReadOnly(!this.isEditable());
		periodList.setAllowBlank(!this.isEditable());
		periodList.setReadOnly(!this.isEditable());
		sharingList.setAllowBlank(!this.isEditable());
		sharingList.setReadOnly(!this.isEditable());
	}
	
	public boolean validate() throws FenixGWTException {
		if (title.getValue() == null || title.getValue().isEmpty())
			throw new FenixGWTException("Please insert a title for the resource.");
		if (keywords.getValue() == null || keywords.getValue().isEmpty())
			throw new FenixGWTException("Please insert few keywords for the resource.");
		if (sourceName.getValue() == null || sourceName.getValue().isEmpty())
			throw new FenixGWTException("Please quote the source of the resource.");
		if (gaulList.getValue() == null || gaulList.getSelection().isEmpty())
			throw new FenixGWTException("Please select a region for the resource.");
		if (sourceGaulList.getValue() == null || sourceGaulList.getSelection().isEmpty())
			throw new FenixGWTException("Please select a region for the source.");
		if (categoryList.getValue() == null || categoryList.getSelection().isEmpty())
			throw new FenixGWTException("Please select a category for the resource.");
		if (periodList.getValue() == null || periodList.getSelection().isEmpty())
			throw new FenixGWTException("Please select a periodicity for the resource.");
		if (sharingList.getValue() == null || sharingList.getSelection().isEmpty())
			throw new FenixGWTException("Please select a sharing mode for the resource.");
		return true;
	}

	/** Naming problems with UIObject.getTitle() */
	public TextField<String> getTITLE() {
		return title;
	}

	public TextField<String> getKeywords() {
		return keywords;
	}

	public TextField<String> getCode() {
		return code;
	}

	public TextField<String> getSourceName() {
		return sourceName;
	}

	public TextField<String> getSourceContact() {
		return sourceContact;
	}

	public ComboBox<PeriodTypeCodeModelData> getPeriodList() {
		return periodList;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public ComboBox<GaulModelData> getSourceGaulList() {
		return sourceGaulList;
	}

	public ComboBox<CategoryModelData> getCategoryList() {
		return categoryList;
	}

	public ComboBox<SharingCodeModelData> getSharingList() {
		return sharingList;
	}

	public TextArea getAbstractAbstract() {
		return abstractAbstract;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
	
}