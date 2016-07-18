package org.fao.fenix.web.modules.amis.client.view.compare;

import org.fao.fenix.web.modules.amis.client.control.compare.AMISCompareController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.RootPanel;

public class AMISCompare {

	ContentPanel panel;

	ContentPanel selectionPanel;

	ContentPanel outputPanel;

	ContentPanel tablePanel;

	AMISCompareSelectorPanel compare;

	Button showComparisonButton;

	Html message;
	
	//This indicates the first time that not elements for olap iframe is added to the div
	static boolean noElementsOlapIsNull;
	//This indicates the first time that the compare notes is created
	static boolean compareNotesIsCreated;

	Button compareButton;

	FormPanel formPanel;
	FormData formData;
	LayoutContainer column1;
	LayoutContainer column2;
	LayoutContainer column3;
	LayoutContainer column4;
	LayoutContainer column5;
	
	
	public AMISCompare() {

		panel = new ContentPanel();
		panel.setBorders(false);
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);

		selectionPanel = new ContentPanel();
		selectionPanel.setBorders(false);
		selectionPanel.setAutoWidth(true);
		selectionPanel.setAutoHeight(true);
		selectionPanel.setBodyBorder(false);
		selectionPanel.setHeaderVisible(false);


		outputPanel = new ContentPanel();
		outputPanel.setBorders(false);
		outputPanel.setAutoWidth(true);
		outputPanel.setAutoHeight(true);
		outputPanel.setBodyBorder(false);
		outputPanel.setHeaderVisible(false);

		formPanel = new FormPanel();
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		formData = new FormData("100%");
		formPanel.setFrame(false);
		formPanel.setHeaderVisible(false);
		formPanel.setSize(1080, -1);
		formPanel.setFieldWidth(200);
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);
	
			
		LayoutContainer mainContainer = new LayoutContainer();
		mainContainer.setBorders(true);
		mainContainer.setStyleAttribute("padding", "10px");
	
		LayoutContainer main = new LayoutContainer();
		main.setBorders(false);
		main.setLayout(new ColumnLayout());



		//countries/areas
		column1 = new LayoutContainer();
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);
		column1.setStyleAttribute("paddingRight", "10px");
		column1.setLayout(layout);

		//products
		column2 = new LayoutContainer();
		FormLayout layout2 = new FormLayout();
		layout2.setLabelAlign(LabelAlign.TOP);
		column2.setStyleAttribute("paddingLeft", "10px");
		column2.setLayout(layout2);

		//elements
		column3 = new LayoutContainer();
		column3.setStyleAttribute("paddingLeft", "20px");
		FormLayout lay = new FormLayout();
		lay.setLabelAlign(LabelAlign.TOP);
		column3.setLayout(lay);

		//dates
		column4 = new LayoutContainer();
		column4.setStyleAttribute("paddingLeft", "10px");
		FormLayout lay2 = new FormLayout();
		lay2.setLabelAlign(LabelAlign.TOP);
		column4.setLayout(lay2);

		//show comparison button
		column5 = new LayoutContainer();
		column5.setStyleAttribute("paddingLeft", "5px");
		FormLayout lay5 = new FormLayout();
		lay5.setLabelAlign(LabelAlign.TOP);
		column5.setLayout(lay5);


		showComparisonButton = new Button("<b><font color='#33528C'>Show Comparison</font></b>");
		showComparisonButton.setIconStyle("showComparisonChartIcon");
		showComparisonButton.setHeight(30);
		showComparisonButton.addSelectionListener(AMISCompareController.compare(this, outputPanel, formPanel, column1, column2, column3, column4));
	
		main.add(column1, new ColumnData(203));
		main.add(column2, new ColumnData(205));
		main.add(column3, new ColumnData(380));
		main.add(column4, new ColumnData(240));
		//main.add(column4, new ColumnData(230));
		//main.add(column5, new ColumnData(230));

		mainContainer.add(main);
		
		formPanel.add(mainContainer, new FormData("100%"));
		formPanel.add(FormattingUtils.addVSpace(5));
		
		if(!AMISCompare.isCompareNotesIsCreated())
		{
			RootPanel.get("COMPARE_NOTES").add(buildNotesWithSpace(true));
			AMISCompare.setCompareNotesIsCreated(true);
		}
		RootPanel.get("COMPARE_NOTES").setVisible(true);
		FormButtonBinding binding = new FormButtonBinding(formPanel);
		binding.addButton(showComparisonButton);

		//formPanel.setButtonAlign(HorizontalAlignment.RIGHT);
		//formPanel.addButton(showComparisonButton);


		compare = new AMISCompareSelectorPanel();



	}

	public ContentPanel build() {

		panel.add(buildMainPanel());
		panel.add(outputPanel);
		
		return panel;
	}



	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		//p.setSpacing(10);
		p.setSpacing(1);
		compare.build(this);


		p.add(selectionPanel);
		

		return p;
	}

	
	public static VerticalPanel buildNotesWithSpace(boolean showLine) {
		VerticalPanel notesPanelOut = new VerticalPanel();
		notesPanelOut.setBorders(false);
		notesPanelOut.setTitle("Notes");
		ContentPanel emptyPanels = new ContentPanel();
		emptyPanels.setHeaderVisible(false);
		emptyPanels.setBodyBorder(false);
		emptyPanels.setBorders(showLine);
		notesPanelOut.add(FormattingUtils.addVSpace(6));
		notesPanelOut.add(emptyPanels);
		notesPanelOut.add(FormattingUtils.addVSpace(8));
		notesPanelOut.setStyleAttribute("top-padding", "5px");
		VerticalPanel notesPanel = new VerticalPanel();
		notesPanel.setBorders(true);
		//notesPanel.setTitle("Notes");
		//notesPanel.setWidth(790);
		notesPanel.setWidth("1030px");
		//notesPanel.setId("NOTES_PANEL");
		notesPanel.setStyleAttribute("border-color", "#B5B8C8");
	
		notesPanel.setStyleAttribute("padding", "5px");
		notesPanel.setStyleName("amis_text");
		
		Html elementNote =  new Html("<u>Notes</u>");
		notesPanel.add(elementNote);		
		notesPanel.add(FormattingUtils.addVSpace(1));
//		elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> *Data Availability by source (e.g. [C,P] means data is available from FAO-CBS and USDA-PSD, but not from IGC).</div>");
        elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> *Data Availability by source (e.g. [C,P] means data is available from FAO-AMIS and USDA-PSD, but not from IGC).</div>");
       // notesPanel.add(elementNote);
		
        notesPanel.add(FormattingUtils.addVSpace(1));
		elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> NMY = National Marketing Year </div>");		
		notesPanel.add(elementNote);
		
		notesPanel.add(FormattingUtils.addVSpace(1));
		elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> ITY = International Trade Year </div>");		
		notesPanel.add(elementNote);
		
		notesPanel.add(FormattingUtils.addVSpace(1));
		elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> Aggregated data are in <b>bold</b>.</div>");		
		notesPanel.add(elementNote);

        notesPanel.add(FormattingUtils.addVSpace(1));
        elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> If a selection does not appear in a chart then no data is available.</div>");
        notesPanel.add(elementNote);
		//notesPanel.add(FormattingUtils.addVSpace(1));
		//elementNote = new Html("<div style='font-size: 11px;'><img src='amis-images/grey_arrow.png' border='0'/> FAOSTAT production always refers to the calendar year (year n) whereas FAO-CBS production refers to specific crop seasons or split years (season n/n+1) which, however, are referenced as year n. For soybeans, in the case of Argentina, Australia, Brazil,  Philippines, South-Africa, Thailand and Vietnam, FAO-CBS production refers to crops harvested in the second half of the split year (corresponding to year n+1) and therefore is different from FAOSTAT production which always refers to year n. For all other AMIS countries, FAO-CBS soybean production refers to crops harvested in the first half of the split year (year n) and therefore is comparable to FAOSTAT.</div>");		
		//notesPanel.add(elementNote);
		notesPanelOut.add(notesPanel);
		notesPanelOut.add(FormattingUtils.addVSpace(10));
		return notesPanelOut;
	}

	
	public Html getMessage(){
		return message;
	}


	public ContentPanel getPanel() {
		return panel;
	}

	public FormPanel getForm() {
		return formPanel;
	}

	public FormData getFormData() {
		return formData;
	}



	public ContentPanel getSelectionPanel() {
		return selectionPanel;
	}

	public AMISCompareSelectorPanel getSelectors() {
		return compare;
	}

	public LayoutContainer getColumn1() {
		return column1;
	}

	public LayoutContainer getColumn2() {
		return column2;
	}

	public LayoutContainer getColumn3() {
		return column3;
	}

	public LayoutContainer getColumn4() {
		return column4;
	}

	public LayoutContainer getColumn5() {
		return column5;
	}

	public ContentPanel getOutputPanel() {
		return outputPanel;
	}

	@SuppressWarnings("unchecked")
	public ComboBox<AMISCodesModelData> getColumn2ComboBoxById(String id){
		Component component = column2.getItemByItemId(id);
		if(component==null)
			return null;
		else
			return (ComboBox<AMISCodesModelData>) component;
	}

	public void removeColumn2ComboBoxById(String id){
		ComboBox<AMISCodesModelData> combo = getColumn2ComboBoxById(id);
		if(combo!=null)
			column2.remove(combo);
	}

	public Button getShowComparisonButton() {
		return showComparisonButton;
	}

	public void setShowComparisonButton(Button showComparisonButton) {
		this.showComparisonButton = showComparisonButton;
	}

	public static boolean isNoElementsOlapIsNull() {
		return noElementsOlapIsNull;
	}

	public static void setNoElementsOlapIsNull(boolean noElementsOlapIsNull) {
		AMISCompare.noElementsOlapIsNull = noElementsOlapIsNull;
	}

	public static boolean isCompareNotesIsCreated() {
		return compareNotesIsCreated;
	}

	public static void setCompareNotesIsCreated(boolean compareNotesIsCreated) {
		AMISCompare.compareNotesIsCreated = compareNotesIsCreated;
	}



}
