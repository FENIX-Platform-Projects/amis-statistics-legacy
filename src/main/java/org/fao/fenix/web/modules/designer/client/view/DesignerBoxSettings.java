package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.designer.client.control.DesignerController;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class DesignerBoxSettings {

	private ContentPanel panel;

	private CheckBox addCaption;

	private CheckBox addBox;

	private TextField<String> caption;

	private Button okButton;

	private HTML boxLineColor;

	private HTML boxBackgroundColor;

	private HTML captionColor;

	private ListBox captionFontSize;

	private ListBox captionFontFamily;

	private ListBox captionPosition;

	private final static String FIELDSET_WIDTH = "300px";

	private final static String FIELD_WIDTH = "325px";

	private final static int SPACING = 5;

	public DesignerBoxSettings() {
		addCaption = new CheckBox();
		caption = new TextField<String>();
		okButton = new Button("OK");
		okButton.setWidth(FIELD_WIDTH);
		addBox = new CheckBox();
		boxLineColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		boxBackgroundColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		captionColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		captionFontSize = new ListBox();
		captionFontFamily = new ListBox();
		captionPosition = new ListBox();
		captionFontFamily.addItem("Times New Roman", DesignerConstants.TIMES.name());
		captionFontFamily.addItem("Arial", DesignerConstants.ARIAL.name());
		captionFontFamily.addItem("Courier New", DesignerConstants.COURIER.name());
		captionFontFamily.addItem("Serif", DesignerConstants.SERIF.name());
		captionPosition.setSelectedIndex(3);
		captionFontSize.addItem("XX-Small", DesignerConstants.TINY.name());
		captionFontSize.addItem("X-Small", DesignerConstants.SMALL.name());
		captionFontSize.addItem("Normal", DesignerConstants.NORMALSIZE.name());
		captionFontSize.addItem("Medium", DesignerConstants.MEDIUM.name());
		captionFontSize.addItem("Large", DesignerConstants.LARGE.name());
		captionFontSize.addItem("X-Large", DesignerConstants.XLARGE.name());
		captionFontSize.addItem("XX-Large", DesignerConstants.XXLARGE.name());
		captionPosition.addItem("Below", DesignerConstants.BELOW.name());
		captionPosition.addItem("Above", DesignerConstants.ABOVE.name());
		captionPosition.setSelectedIndex(2);
	}

	public ContentPanel build(boolean addCaptionFlag) {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
		panel.setLayout(new FillLayout());
		panel.add(buildWrapper(addCaptionFlag));
		panel.getLayout().layout();
		return panel;
	}

	private VerticalPanel buildWrapper(boolean addCaptionFlag) {
		VerticalPanel w = new VerticalPanel();
		w.add(buildBoxFieldSet());
		w.add(new Html("<hr>"));
		if (addCaptionFlag) {
			w.add(buildCaptionFieldSet());
			w.add(new Html("<hr>"));
		}
		w.add(buildButtonsPanel());
		return w;
	}

	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.add(okButton);
		return p;
	}

	private VerticalPanel buildBoxFieldSet() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		addBox.setBoxLabel("Add Box");
		p.add(new Html("<b><u><font color='#1D4589'>Box Settings<font></u></b>"));
		p.add(addBox);
		p.add(new Html("<b><font color='#1D4589'>Box Border Color<font></b>"));
		boxLineColor.setWidth(FIELD_WIDTH);
		boxLineColor.addClickHandler(DesignerController.colorPicker(boxLineColor));
		p.add(boxLineColor);
		p.add(new Html("<b><font color='#1D4589'>Box Background Color<font></b>"));
		boxBackgroundColor.setWidth(FIELD_WIDTH);
		boxBackgroundColor.addClickHandler(DesignerController.colorPicker(boxBackgroundColor));
		p.add(boxBackgroundColor);
		p.setWidth(FIELDSET_WIDTH);
		return p;
	}

	private VerticalPanel buildCaptionFieldSet() {

		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.add(new Html("<b><u><font color='#1D4589'>Caption Settings<font></u></b>"));

		addCaption.setBoxLabel("Add Caption");
		p.add(addCaption);
		p.add(new Html("<b><font color='#1D4589'>Caption Label<font></b>"));
		caption.setEmptyText("e.g. My Chart");
		caption.setWidth(FIELD_WIDTH);
		p.add(caption);

		p.add(new Html("<b><font color='#1D4589'>Caption Color<font></b>"));
		captionColor.setWidth(FIELD_WIDTH);
		captionColor.addClickHandler(DesignerController.colorPicker(captionColor));
		p.add(captionColor);

		p.add(new Html("<b><font color='#1D4589'>Caption Font Family<font></b>"));
		captionFontFamily.setWidth(FIELD_WIDTH);
		p.add(captionFontFamily);

		p.add(new Html("<b><font color='#1D4589'>Caption Font Size<font></b>"));
		captionFontSize.setWidth(FIELD_WIDTH);
		p.add(captionFontSize);

		p.add(new Html("<b><font color='#1D4589'>Caption Position<font></b>"));
		captionPosition.setWidth(FIELD_WIDTH);
		p.add(captionPosition);

		p.setWidth(FIELDSET_WIDTH);
		return p;
	}

	public CheckBox getAddCaption() {
		return addCaption;
	}

	public TextField<String> getCaption() {
		return caption;
	}

	public Button getOkButton() {
		return okButton;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public CheckBox getAddBox() {
		return addBox;
	}

	public HTML getBoxLineColor() {
		return boxLineColor;
	}

	public HTML getBoxBackgroundColor() {
		return boxBackgroundColor;
	}

	public HTML getCaptionColor() {
		return captionColor;
	}

	public ListBox getCaptionFontSize() {
		return captionFontSize;
	}

	public ListBox getCaptionFontFamily() {
		return captionFontFamily;
	}

	public ListBox getCaptionPosition() {
		return captionPosition;
	}

}
