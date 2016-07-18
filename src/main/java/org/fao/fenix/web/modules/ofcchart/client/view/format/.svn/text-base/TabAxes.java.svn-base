package org.fao.fenix.web.modules.ofcchart.client.view.format;

import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.utils.ColorPickerCaller;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class TabAxes extends TabItem {
	
	ContentPanel mainCont;
	ContentPanel xAxis;
	ContentPanel yAxis;
	ContentPanel yAxis2;
	CheckBox flip;
	CheckBox showValue;
	
	// -------------------- X axis -----------------------------------
	
	TextField<String> textXAxisTitle;
	CheckBox visibleXAxisTitle;
	ListBox sizeXAxisTitle;
	IconButton colorXAxisTitle;
	CheckBox visibleXAxisLabel;
	ListBox sizeXAxisLabel;
	IconButton colorXAxisLabel;
	CheckBox xGrid;
	
	public CheckBox getXGrid() {
		return xGrid;
	}

	public void setXGrid(CheckBox grid) {
		xGrid = grid;
	}

	public CheckBox getFlip() {
		return flip;
	}

	public IconButton getColorXAxisTitle() {
		return colorXAxisTitle;
	}

	public CheckBox getShowValue() {
		return showValue;
	}

	public void setShowValue(CheckBox showValue) {
		this.showValue = showValue;
	}

	public void setColorXAxisTitle(IconButton colorXAxisTitle) {
		this.colorXAxisTitle = colorXAxisTitle;
	}

	public IconButton getColorXAxisLabel() {
		return colorXAxisLabel;
	}

	public void setColorXAxisLabel(IconButton colorXAxisLabel) {
		this.colorXAxisLabel = colorXAxisLabel;
	}

	public TextField<String> getTextXAxisTitle() {
		return textXAxisTitle;
	}

	public void setTextXAxisTitle(TextField<String> textXAxisTitle) {
		this.textXAxisTitle = textXAxisTitle;
	}

	public CheckBox getVisibleXAxisTitle() {
		return visibleXAxisTitle;
	}

	public void setVisibleXAxisTitle(CheckBox visibleXAxisTitle) {
		this.visibleXAxisTitle = visibleXAxisTitle;
	}

	public ListBox getSizeXAxisTitle() {
		return sizeXAxisTitle;
	}

	public void setSizeXAxisTitle(ListBox sizeXAxisTitle) {
		this.sizeXAxisTitle = sizeXAxisTitle;
	}

	public ListBox getSizeXAxisLabel() {
		return sizeXAxisLabel;
	}

	public void setSizeXAxisLabel(ListBox sizeXAxisLabel) {
		this.sizeXAxisLabel = sizeXAxisLabel;
	}

	public CheckBox getVisibleXAxisLabel() {
		return visibleXAxisLabel;
	}

	public void setVisibleXAxisLabel(CheckBox visibleXAxisLabel) {
		this.visibleXAxisLabel = visibleXAxisLabel;
	}

	private FieldSet createXAxisTitleWidgets(){
			
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Title Label");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrXAxisTitle = new HorizontalPanel();
		hrXAxisTitle.setSpacing(5);
		hrXAxisTitle.add(new HTML("Text&nbsp;&nbsp;&nbsp;"));
		textXAxisTitle = new TextField<String>();
		hrXAxisTitle.add(textXAxisTitle);
		fieldSet.add(hrXAxisTitle);
		
		HorizontalPanel hrSizeVisibleXAxisTitle = new HorizontalPanel();
		hrSizeVisibleXAxisTitle.setSpacing(5);
		String t = BabelFish.print().size() + "&nbsp;&nbsp;&nbsp;"; 
		hrSizeVisibleXAxisTitle.add(new HTML(t));
		sizeXAxisTitle = new ListBox();
		populateSizeList(sizeXAxisTitle);
		sizeXAxisTitle.setWidth("45px");
		hrSizeVisibleXAxisTitle.add(sizeXAxisTitle);
		fieldSet.add(hrSizeVisibleXAxisTitle);
		
		HorizontalPanel hrVisibleXAxisTitle = new HorizontalPanel();
		hrVisibleXAxisTitle.setSpacing(5);
		hrVisibleXAxisTitle.add(new HTML(BabelFish.print().visible()));
		visibleXAxisTitle = new CheckBox();
		hrVisibleXAxisTitle.add(visibleXAxisTitle);
		fieldSet.add(hrVisibleXAxisTitle);
		
		HorizontalPanel hrcolorXAxisTitle = new HorizontalPanel();
		hrcolorXAxisTitle.setSpacing(5);
		hrcolorXAxisTitle.add(new HTML("Color"));
		colorXAxisTitle = new IconButton();
		colorXAxisTitle.setSize(60, 10);
		colorXAxisTitle.setStyleAttribute("border", "1px solid black");
		colorXAxisTitle.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrcolorXAxisTitle.add(colorXAxisTitle);
		fieldSet.add(hrcolorXAxisTitle);
		
		
		
		return fieldSet;
	}
	
	private FieldSet createXAxisLabelWidgets(){
	
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("X Labels");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrSizeVisibleXAxisLabel = new HorizontalPanel();
		hrSizeVisibleXAxisLabel.setSpacing(5);
		String t = BabelFish.print().size() + "&nbsp;&nbsp;&nbsp;"; 
		hrSizeVisibleXAxisLabel.add(new HTML(t));
		sizeXAxisLabel = new ListBox();
		populateSizeList(sizeXAxisLabel);
		sizeXAxisLabel.setWidth("45px");
		hrSizeVisibleXAxisLabel.add(sizeXAxisLabel);
		fieldSet.add(hrSizeVisibleXAxisLabel);
		
		
		HorizontalPanel hrVisibleXAxisLabel = new HorizontalPanel();
		hrVisibleXAxisLabel.setSpacing(5);
		hrVisibleXAxisLabel.add(new HTML(BabelFish.print().visible()));
		visibleXAxisLabel = new CheckBox();
		hrVisibleXAxisLabel.add(visibleXAxisLabel);
		fieldSet.add(hrVisibleXAxisLabel);
		
		HorizontalPanel hrcolorXAxisLabel = new HorizontalPanel();
		hrcolorXAxisLabel.setSpacing(5);
		hrcolorXAxisLabel.add(new HTML("Color"));
		colorXAxisLabel = new IconButton();
		colorXAxisLabel.setSize(60, 10);
		colorXAxisLabel.setStyleAttribute("border", "1px solid black");
		colorXAxisLabel.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrcolorXAxisLabel.add(colorXAxisLabel);
		fieldSet.add(hrcolorXAxisLabel);
		
		//grid
		HorizontalPanel hrXGrid = new HorizontalPanel();
		hrXGrid.setSpacing(5);
		hrXGrid.add(new HTML("Grid"));
		xGrid = new CheckBox();
		hrXGrid.add(xGrid);
		fieldSet.add(hrXGrid);
		
		return fieldSet;
		
	}
	
// -------------------- Y axis -----------------------------------
	
	TextField<String> textYAxisTitle;
	CheckBox visibleYAxisTitle;
	ListBox sizeYAxisTitle;
	IconButton colorYAxisTitle;
	CheckBox visibleYAxisLabel;
	ListBox sizeYAxisLabel;
	IconButton colorYAxisLabel;
	CheckBox yGrid;
	
	RadioButton autoStep;
	RadioButton numberStep;
	TextField<String> stepNumber;
	TextField<String> fractionDigits;
	TextField<String> minScale;
	TextField<String> maxScale;
	
		
	public CheckBox getYGrid() {
		return yGrid;
	}

	public void setYGrid(CheckBox grid) {
		yGrid = grid;
	}

	public IconButton getColorYAxisTitle() {
		return colorYAxisTitle;
	}

	public void setColorYAxisTitle(IconButton colorYAxisTitle) {
		this.colorYAxisTitle = colorYAxisTitle;
	}

	public IconButton getColorYAxisLabel() {
		return colorYAxisLabel;
	}

	public void setColorYAxisLabel(IconButton colorYAxisLabel) {
		this.colorYAxisLabel = colorYAxisLabel;
	}

	public TextField<String> getTextYAxisTitle() {
		return textYAxisTitle;
	}

	public void setTextYAxisTitle(TextField<String> textYAxisTitle) {
		this.textYAxisTitle = textYAxisTitle;
	}

	public CheckBox getVisibleYAxisTitle() {
		return visibleYAxisTitle;
	}

	public void setVisibleYAxisTitle(CheckBox visibleYAxisTitle) {
		this.visibleYAxisTitle = visibleYAxisTitle;
	}

	public ListBox getSizeYAxisTitle() {
		return sizeYAxisTitle;
	}

	public void setSizeYAxisTitle(ListBox sizeYAxisTitle) {
		this.sizeYAxisTitle = sizeYAxisTitle;
	}

	public ListBox getSizeYAxisLabel() {
		return sizeYAxisLabel;
	}

	public void setSizeYAxisLabel(ListBox sizeYAxisLabel) {
		this.sizeYAxisLabel = sizeYAxisLabel;
	}

	public CheckBox getVisibleYAxisLabel() {
		return visibleYAxisLabel;
	}

	public void setVisibleYAxisLabel(CheckBox visibleYAxisLabel) {
		this.visibleYAxisLabel = visibleYAxisLabel;
	}

	public RadioButton getAutoStep() {
		return autoStep;
	}

	public void setAutoStep(RadioButton autoStep) {
		this.autoStep = autoStep;
	}

	public RadioButton getNumberStep() {
		return numberStep;
	}

	public void setNumberStep(RadioButton numberStep) {
		this.numberStep = numberStep;
	}

	public TextField<String> getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(TextField<String> stepNumber) {
		this.stepNumber = stepNumber;
	}

	public TextField<String> getFractionDigits() {
		return fractionDigits;
	}

	public void setFractionDigits(TextField<String> fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	public TextField<String> getMinScale() {
		return minScale;
	}

	public void setMinScale(TextField<String> minScale) {
		this.minScale = minScale;
	}

	public TextField<String> getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(TextField<String> maxScale) {
		this.maxScale = maxScale;
	}

	private void populateSizeList(ListBox l){
		for (int i=5; i <= 50; i+=5){
			l.addItem(String.valueOf(i));
		}
	}
	
	private FieldSet createYScaleWidgets(){
	
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Scale");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		autoStep = new RadioButton("group");
		numberStep = new RadioButton("group");
		stepNumber = new TextField<String>();
		stepNumber.setToolTip("Between 1 and 99");
		fractionDigits = new TextField<String>();
		minScale = new TextField<String>();
		maxScale = new TextField<String>();
		
		HorizontalPanel hrFirst = new HorizontalPanel();
		hrFirst.setSpacing(5);
		HTML labelStep = new HTML("Step Auto");
		hrFirst.add(autoStep);
		hrFirst.add(labelStep);
		fieldSet.add(hrFirst);
		
		autoStep.addClickListener(new ClickListener(){
			
			public void onClick(Widget widget){
				stepNumber.setEnabled(false);
			}
			
		});
		
		HorizontalPanel hrSecond = new HorizontalPanel();
		hrSecond.setSpacing(5);
		HTML labelStepNumber = new HTML("Step Number");
		hrSecond.add(numberStep);
		hrSecond.add(labelStepNumber);
		hrSecond.add(stepNumber);
		fieldSet.add(hrSecond);
		
		numberStep.addClickListener(new ClickListener(){
			
			public void onClick(Widget widget){
				stepNumber.setEnabled(true);
			}
			
		});
		
		HorizontalPanel hrThird = new HorizontalPanel();
		hrThird.setSpacing(6);
		hrThird.add(new HTML("Min"));
		hrThird.add(minScale);
		fieldSet.add(hrThird);
		
		HorizontalPanel hrFourth = new HorizontalPanel();
		hrFourth.setSpacing(5);
		hrFourth.add(new HTML("Max"));
		hrFourth.add(maxScale);
		fieldSet.add(hrFourth);
		
		HorizontalPanel hrFifth = new HorizontalPanel();
		hrFifth.setSpacing(5);
		hrFifth.add(new HTML("Fraction Digits"));
		hrFifth.add(fractionDigits);
		fieldSet.add(hrFifth);
		
		return fieldSet;
		
	}
	
	private FieldSet createYAxisTitleWidgets(){
			
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Title Label");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrYAxisTitle = new HorizontalPanel();
		hrYAxisTitle.setSpacing(5);
		hrYAxisTitle.add(new HTML("Text&nbsp;&nbsp;&nbsp;"));
		textYAxisTitle = new TextField<String>();
		hrYAxisTitle.add(textYAxisTitle);
		fieldSet.add(hrYAxisTitle);
		
		HorizontalPanel hrSizeVisibleYAxisTitle = new HorizontalPanel();
		hrSizeVisibleYAxisTitle.setSpacing(5);
		String t = BabelFish.print().size() + "&nbsp;&nbsp;&nbsp;"; 
		hrSizeVisibleYAxisTitle.add(new HTML(t));
		sizeYAxisTitle = new ListBox();
		populateSizeList(sizeYAxisTitle);
		sizeYAxisTitle.setWidth("45px");
		hrSizeVisibleYAxisTitle.add(sizeYAxisTitle);
		fieldSet.add(hrSizeVisibleYAxisTitle);
		
		HorizontalPanel hrVisibleYAxisTitle = new HorizontalPanel();
		hrVisibleYAxisTitle.setSpacing(5);
		hrVisibleYAxisTitle.add(new HTML(BabelFish.print().visible()));
		visibleYAxisTitle = new CheckBox();
		hrVisibleYAxisTitle.add(visibleYAxisTitle);
		fieldSet.add(hrVisibleYAxisTitle);
		
		HorizontalPanel hrcolorYAxisTitle = new HorizontalPanel();
		hrcolorYAxisTitle.setSpacing(5);
		hrcolorYAxisTitle.add(new HTML("Color"));
		colorYAxisTitle = new IconButton();
		colorYAxisTitle.setSize(60, 10);
		colorYAxisTitle.setStyleAttribute("border", "1px solid black");
		colorYAxisTitle.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrcolorYAxisTitle.add(colorYAxisTitle);
		fieldSet.add(hrcolorYAxisTitle);
		
		return fieldSet;
	}
	
	private FieldSet createYAxisLabelWidgets(){
	
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Y Labels");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrSizeVisibleYAxisLabel = new HorizontalPanel();
		hrSizeVisibleYAxisLabel.setSpacing(5);
		String t = BabelFish.print().size() + "&nbsp;&nbsp;&nbsp;"; 
		hrSizeVisibleYAxisLabel.add(new HTML(t));
		sizeYAxisLabel = new ListBox();
		populateSizeList(sizeYAxisLabel);
		sizeYAxisLabel.setWidth("45px");
		hrSizeVisibleYAxisLabel.add(sizeYAxisLabel);
		fieldSet.add(hrSizeVisibleYAxisLabel);
				
		HorizontalPanel hrVisibleYAxisLabel = new HorizontalPanel();
		hrVisibleYAxisLabel.setSpacing(5);
		hrVisibleYAxisLabel.add(new HTML(BabelFish.print().visible()));
		visibleYAxisLabel = new CheckBox();
		hrVisibleYAxisLabel.add(visibleYAxisLabel);
		fieldSet.add(hrVisibleYAxisLabel);
		
		HorizontalPanel hrcolorYAxisLabel = new HorizontalPanel();
		hrcolorYAxisLabel.setSpacing(5);
		hrcolorYAxisLabel.add(new HTML("Color"));
		colorYAxisLabel = new IconButton();
		colorYAxisLabel.setSize(60, 10);
		colorYAxisLabel.setStyleAttribute("border", "1px solid black");
		colorYAxisLabel.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrcolorYAxisLabel.add(colorYAxisLabel);
		fieldSet.add(hrcolorYAxisLabel);
		
		//grid
		HorizontalPanel hrYGrid = new HorizontalPanel();
		hrYGrid.setSpacing(5);
		hrYGrid.add(new HTML("Grid"));
		yGrid = new CheckBox();
		hrYGrid.add(yGrid);
		fieldSet.add(hrYGrid);
		
		return fieldSet;
		
	}
	
// -------------------- Y axis  (Second Scale) -----------------------------------
	
	TextField<String> textY2AxisTitle;
	CheckBox visibleY2AxisTitle;
	ListBox sizeY2AxisTitle;
	IconButton colorY2AxisTitle;
	CheckBox visibleY2AxisLabel;
	ListBox sizeY2AxisLabel;
	IconButton colorY2AxisLabel;
	
	RadioButton autoStep2;
	RadioButton numberStep2;
	TextField<String> stepNumber2;
	TextField<String> fractionDigits2;
	TextField<String> minScale2;
	TextField<String> maxScale2;
	
	public IconButton getColorY2AxisTitle() {
		return colorY2AxisTitle;
	}

	public void setColorY2AxisTitle(IconButton colorY2AxisTitle) {
		this.colorY2AxisTitle = colorY2AxisTitle;
	}

	public IconButton getColorY2AxisLabel() {
		return colorY2AxisLabel;
	}

	public void setColorY2AxisLabel(IconButton colorY2AxisLabel) {
		this.colorY2AxisLabel = colorY2AxisLabel;
	}

	public TextField<String> getTextY2AxisTitle() {
		return textY2AxisTitle;
	}

	public void setTextY2AxisTitle(TextField<String> textY2AxisTitle) {
		this.textY2AxisTitle = textY2AxisTitle;
	}

	public CheckBox getVisibleY2AxisTitle() {
		return visibleY2AxisTitle;
	}

	public void setVisibleY2AxisTitle(CheckBox visibleY2AxisTitle) {
		this.visibleY2AxisTitle = visibleY2AxisTitle;
	}

	public ListBox getSizeY2AxisTitle() {
		return sizeY2AxisTitle;
	}

	public void setSizeY2AxisTitle(ListBox sizeY2AxisTitle) {
		this.sizeY2AxisTitle = sizeY2AxisTitle;
	}

	public CheckBox getVisibleY2AxisLabel() {
		return visibleY2AxisLabel;
	}

	public void setVisibleY2AxisLabel(CheckBox visibleY2AxisLabel) {
		this.visibleY2AxisLabel = visibleY2AxisLabel;
	}

	public ListBox getSizeY2AxisLabel() {
		return sizeY2AxisLabel;
	}

	public void setSizeY2AxisLabel(ListBox sizeY2AxisLabel) {
		this.sizeY2AxisLabel = sizeY2AxisLabel;
	}

	public RadioButton getAutoStep2() {
		return autoStep2;
	}

	public void setAutoStep2(RadioButton autoStep2) {
		this.autoStep2 = autoStep2;
	}

	public RadioButton getNumberStep2() {
		return numberStep2;
	}

	public void setNumberStep2(RadioButton numberStep2) {
		this.numberStep2 = numberStep2;
	}

	public TextField<String> getStepNumber2() {
		return stepNumber2;
	}

	public void setStepNumber2(TextField<String> stepNumber2) {
		this.stepNumber2 = stepNumber2;
	}

	public TextField<String> getFractionDigits2() {
		return fractionDigits2;
	}

	public void setFractionDigits2(TextField<String> fractionDigits2) {
		this.fractionDigits2 = fractionDigits2;
	}

	public TextField<String> getMinScale2() {
		return minScale2;
	}

	public void setMinScale2(TextField<String> minScale2) {
		this.minScale2 = minScale2;
	}

	public TextField<String> getMaxScale2() {
		return maxScale2;
	}

	public void setMaxScale2(TextField<String> maxScale2) {
		this.maxScale2 = maxScale2;
	}

	private FieldSet createY2ScaleWidgets(){
		
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Scale");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		autoStep2 = new RadioButton("group2");
		numberStep2 = new RadioButton("group2");
		stepNumber2 = new TextField<String>();
		stepNumber2.setToolTip("Between 1 and 99");
		fractionDigits2 = new TextField<String>();
		minScale2 = new TextField<String>();
		maxScale2 = new TextField<String>();
		
		HorizontalPanel hrFirst = new HorizontalPanel();
		hrFirst.setSpacing(5);
		HTML labelStep = new HTML("Step Auto");
		hrFirst.add(autoStep2);
		hrFirst.add(labelStep);
		fieldSet.add(hrFirst);
		
		autoStep2.addClickListener(new ClickListener(){
			
			public void onClick(Widget widget){
				stepNumber2.setEnabled(false);
			}
			
		});
		
		HorizontalPanel hrSecond = new HorizontalPanel();
		hrSecond.setSpacing(5);
		HTML labelStepNumber = new HTML("Step Number");
		hrSecond.add(numberStep2);
		hrSecond.add(labelStepNumber);
		hrSecond.add(stepNumber2);
		fieldSet.add(hrSecond);
		
		numberStep2.addClickListener(new ClickListener(){
			
			public void onClick(Widget widget){
				stepNumber2.setEnabled(true);
			}
			
		});
		
		HorizontalPanel hrThird = new HorizontalPanel();
		hrThird.setSpacing(6);
		hrThird.add(new HTML("Min"));
		hrThird.add(minScale2);
		fieldSet.add(hrThird);
		
		HorizontalPanel hrFourth = new HorizontalPanel();
		hrFourth.setSpacing(5);
		hrFourth.add(new HTML("Max"));
		hrFourth.add(maxScale2);
		fieldSet.add(hrFourth);
		
		HorizontalPanel hrFifth = new HorizontalPanel();
		hrFifth.setSpacing(5);
		hrFifth.add(new HTML("Fraction Digits"));
		hrFifth.add(fractionDigits2);
		fieldSet.add(hrFifth);
		
		return fieldSet;
		
	}
	
	private FieldSet createY2AxisTitleWidgets(){
			
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Title Label");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrYAxisTitle = new HorizontalPanel();
		hrYAxisTitle.setSpacing(5);
		hrYAxisTitle.add(new HTML("Text&nbsp;&nbsp;&nbsp;"));
		textY2AxisTitle = new TextField<String>();
		hrYAxisTitle.add(textY2AxisTitle);
		fieldSet.add(hrYAxisTitle);
		
		HorizontalPanel hrSizeVisibleYAxisTitle = new HorizontalPanel();
		hrSizeVisibleYAxisTitle.setSpacing(5);
		String t = BabelFish.print().size() + "&nbsp;&nbsp;&nbsp;"; 
		hrSizeVisibleYAxisTitle.add(new HTML(t));
		sizeY2AxisTitle = new ListBox();
		populateSizeList(sizeY2AxisTitle);
		sizeY2AxisTitle.setWidth("45px");
		hrSizeVisibleYAxisTitle.add(sizeY2AxisTitle);
		fieldSet.add(hrSizeVisibleYAxisTitle);
				
		HorizontalPanel hrVisibleYAxisTitle = new HorizontalPanel();
		hrVisibleYAxisTitle.setSpacing(5);
		hrVisibleYAxisTitle.add(new HTML(BabelFish.print().visible()));
		visibleY2AxisTitle = new CheckBox();
		hrVisibleYAxisTitle.add(visibleY2AxisTitle);
		fieldSet.add(hrVisibleYAxisTitle);
		
		HorizontalPanel hrcolorY2AxisTitle = new HorizontalPanel();
		hrcolorY2AxisTitle.setSpacing(5);
		hrcolorY2AxisTitle.add(new HTML("Color"));
		colorY2AxisTitle = new IconButton();
		colorY2AxisTitle.setSize(60, 10);
		colorY2AxisTitle.setStyleAttribute("border", "1px solid black");
		colorY2AxisTitle.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrcolorY2AxisTitle.add(colorY2AxisTitle);
		fieldSet.add(hrcolorY2AxisTitle);
		
		return fieldSet;
	}
	
	private FieldSet createY2AxisLabelWidgets(){
	
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Y Labels");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
//		layout.setPadding(4);  
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrSizeVisibleYAxisLabel = new HorizontalPanel();
		hrSizeVisibleYAxisLabel.setSpacing(5);
		String t = BabelFish.print().size() + "&nbsp;&nbsp;&nbsp;"; 
		hrSizeVisibleYAxisLabel.add(new HTML(t));
		sizeY2AxisLabel = new ListBox();
		populateSizeList(sizeY2AxisLabel);
		sizeY2AxisLabel.setWidth("45px");
		hrSizeVisibleYAxisLabel.add(sizeY2AxisLabel);
		fieldSet.add(hrSizeVisibleYAxisLabel);
				
		HorizontalPanel hrVisibleYAxisLabel = new HorizontalPanel();
		hrVisibleYAxisLabel.setSpacing(5);
		hrVisibleYAxisLabel.add(new HTML(BabelFish.print().visible()));
		visibleY2AxisLabel = new CheckBox();
		hrVisibleYAxisLabel.add(visibleY2AxisLabel);
		fieldSet.add(hrVisibleYAxisLabel);
		
		HorizontalPanel hrcolorY2AxisLabel = new HorizontalPanel();
		hrcolorY2AxisLabel.setSpacing(5);
		hrcolorY2AxisLabel.add(new HTML("Color"));
		colorY2AxisLabel = new IconButton();
		colorY2AxisLabel.setSize(60, 10);
		colorY2AxisLabel.setStyleAttribute("border", "1px solid black");
		colorY2AxisLabel.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrcolorY2AxisLabel.add(colorY2AxisLabel);
		fieldSet.add(hrcolorY2AxisLabel);
		
		return fieldSet;
		
	}
	
	public void addWidgetsForSecondScale(){
		
		yAxis2 = new ContentPanel();
		yAxis2.setHeading("Y Axis (Second Scale)");
		yAxis2.setScrollMode(Scroll.AUTO);
		yAxis2.setAutoWidth(true);
		yAxis2.setExpanded(true);
		yAxis2.setBodyBorder(false);
		VerticalPanel vpY2Axis = new VerticalPanel();
		vpY2Axis.setSpacing(10);
		vpY2Axis.add(createY2AxisTitleWidgets());
		vpY2Axis.add(createY2AxisLabelWidgets());
		vpY2Axis.add(createY2ScaleWidgets());
		yAxis2.add(vpY2Axis);
		mainCont.add(yAxis2);
		
	}
	
	public void addFlip(){
		
		HorizontalPanel hrFlip = new HorizontalPanel();
		hrFlip.setSpacing(5);
		flip = new CheckBox();
		
		hrFlip.add(new HTML("Flip"));
		hrFlip.add(flip);
		
		showValue = new CheckBox();
		hrFlip.add(new HTML("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Show Series Values"));
		hrFlip.add(showValue);
		
		mainCont.setTopComponent(hrFlip);
		
	}
	
	public TabAxes(){
		setText(BabelFish.print().axes());
		mainCont = new ContentPanel();
		mainCont.setBodyBorder(false);
		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(585, 420);
		
		xAxis = new ContentPanel();
		xAxis.setHeading("X Axis");
		xAxis.setScrollMode(Scroll.AUTO);
		xAxis.setAutoWidth(true);
		xAxis.setExpanded(true);
		xAxis.setBodyBorder(false);
		VerticalPanel vpXAxis = new VerticalPanel();
		vpXAxis.setSpacing(10);
		vpXAxis.add(createXAxisTitleWidgets());
		vpXAxis.add(createXAxisLabelWidgets());
		xAxis.add(vpXAxis);
		mainCont.add(xAxis);
		
		yAxis = new ContentPanel();
		yAxis.setHeading("Y Axis");
		yAxis.setScrollMode(Scroll.AUTO);
		yAxis.setAutoWidth(true);
		yAxis.setExpanded(true);
		yAxis.setBodyBorder(false);
		VerticalPanel vpYAxis = new VerticalPanel();
		vpYAxis.setSpacing(10);
		vpYAxis.add(createYAxisTitleWidgets());
		vpYAxis.add(createYAxisLabelWidgets());
		vpYAxis.add(createYScaleWidgets());
		yAxis.add(vpYAxis);
		mainCont.add(yAxis);
		
		add(mainCont);
		
	}

}
