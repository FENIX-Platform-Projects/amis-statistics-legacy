package org.fao.fenix.web.modules.metadataeditor.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class ExtraInfoPanel extends LayoutContainer {
	
	private VerticalPanel wrapper;
	
	private TextField<String> providerName;
	
	private TextField<String> providerContact;
	
	private ListStore<GaulModelData> providerGaulStore;
	
	private ComboBox<GaulModelData> providerGaulList;
	
	private Html tooltip;
	
	private DateField startDate;
	
	private DateField endDate;
	
	private DateField dateLastUpdate;
	
	private boolean isEditable;
	
	private final static int SINGLE_SPACING = 2;
	
	private final static int TRIPLE_SPACING = 6;
	
	private final static String TOOLTIP_WIDTH = "750px";
	
	private final static String TRIPLE_OBJECT_WIDTH = "244px";
	
	public ExtraInfoPanel(boolean isEditable) {
		this.setEditable(isEditable);
		wrapper = new VerticalPanel();
		wrapper.setSpacing(SINGLE_SPACING);
		wrapper.setLayout(new FillLayout());
		tooltip = new Html("<div style='color: #15428B;'>" + BabelFish.print().stepThree() + "</div>");
		tooltip.setWidth(TOOLTIP_WIDTH);
		providerName = new TextField<String>();
		providerName.setWidth(TRIPLE_OBJECT_WIDTH);
		providerName.setEmptyText(BabelFish.print().tooltipprovider());
		providerContact = new TextField<String>();
		providerContact.setWidth(TRIPLE_OBJECT_WIDTH);
		providerContact.setEmptyText(BabelFish.print().tooltipcontact());
		providerGaulStore = new ListStore<GaulModelData>(); 
		providerGaulList = new ComboBox<GaulModelData>();
		providerGaulList.setTriggerAction(TriggerAction.ALL);
		providerGaulList.setStore(providerGaulStore);
		providerGaulList.setDisplayField("gaulLabel");
		providerGaulList.setWidth(TRIPLE_OBJECT_WIDTH);
		providerGaulList.setEmptyText(BabelFish.print().tooltipSourceRegion());
		startDate = new DateField();
		startDate.setWidth(TRIPLE_OBJECT_WIDTH);
		startDate.setEmptyText(BabelFish.print().tooltipstartDate());
		endDate = new DateField();
		endDate.setWidth(TRIPLE_OBJECT_WIDTH);
		endDate.setEmptyText(BabelFish.print().tooltipendDate());
		dateLastUpdate = new DateField();
		dateLastUpdate.setWidth(TRIPLE_OBJECT_WIDTH);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		wrapper.add(buildTooltipPanel(tooltip));
		wrapper.add(buildProviderPanel());
		wrapper.add(buildStartEndDatePanel());
		add(wrapper);
	}
	
	private HorizontalPanel buildStartEndDatePanel() {
		List<String> labels = new ArrayList<String>();
		labels.add(BabelFish.print().startDate());
		labels.add(BabelFish.print().endDate());
		labels.add(BabelFish.print().dateLastUpdate());
		List<Widget> objects = new ArrayList<Widget>();
		objects.add(startDate);
		objects.add(endDate);
		objects.add(dateLastUpdate);
		return buildObjectPanel(labels, objects, SINGLE_SPACING);
	}
	
	private HorizontalPanel buildProviderPanel() {
		List<String> labels = new ArrayList<String>();
		labels.add(BabelFish.print().provider());
		labels.add("Provider's Region");
		labels.add(BabelFish.print().contact());
		List<Widget> objects = new ArrayList<Widget>();
		objects.add(providerName);
		objects.add(providerGaulList);
		objects.add(providerContact);
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
		lbl = new Html("<div style='color: #15428B; font-weight: bold;'>" + label + "</div>");
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

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public TextField<String> getProviderName() {
		return providerName;
	}

	public TextField<String> getProviderContact() {
		return providerContact;
	}

	public ComboBox<GaulModelData> getProviderGaulList() {
		return providerGaulList;
	}

	public DateField getStartDate() {
		return startDate;
	}

	public DateField getEndDate() {
		return endDate;
	}

	public DateField getDateLastUpdate() {
		return dateLastUpdate;
	}
	
}