/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.fao.fenix.web.modules.text.client.view.editor;

import java.util.Date;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.text.client.control.TextEditorToolbarController;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * Rich Text Area Toolbar.
 */
public class TextEditorToolbar extends Composite {

	public final RichTextArea.FontSize[] fontSizesConstants = new RichTextArea.FontSize[] {
			RichTextArea.FontSize.XX_SMALL, RichTextArea.FontSize.X_SMALL, RichTextArea.FontSize.SMALL,
			RichTextArea.FontSize.MEDIUM, RichTextArea.FontSize.LARGE, RichTextArea.FontSize.X_LARGE,
			RichTextArea.FontSize.XX_LARGE };

	public RichTextArea richText;
	public RichTextArea.BasicFormatter basic;
	public RichTextArea.ExtendedFormatter extended;
	public String textContent;
	public String referenceDate;

	private VerticalPanel outer = new VerticalPanel();
	private HorizontalPanel topPanel = new HorizontalPanel();
	private HorizontalPanel bottomPanel = new HorizontalPanel();
	public ToggleButton bold;
	public ToggleButton italic;
	public ToggleButton underline;
	public ToggleButton subscript;
	public ToggleButton superscript;
	public ToggleButton strikethrough;
	public PushButton indent;
	public PushButton outdent;
	public PushButton justifyLeft;
	public PushButton justifyCenter;
	public PushButton justifyRight;
	public PushButton hr;
	public PushButton ol;
	public PushButton ul;
	public PushButton insertImage;
	public PushButton createLink;
	public PushButton removeLink;
	public PushButton removeFormat;
	public PushButton save;
	public PushButton exportPDF;
	public PushButton print;
	//public PushButton backColors;
	//public PushButton foreColors;
	// public Button setBackColor;
	// public Button setForeColor;

	public ListBox backColors;
	public ListBox foreColors;
	public ListBox fonts;
	public ListBox fontSizes;

	public final DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	public DateField dateField;
	/**
	 * Creates a new toolbar that drives the given rich text area.
	 *
	 * @param richText
	 *            the rich text area to be controlled
	 */

	public TextEditorToolbar(RichTextArea richText, String textContent, TextEditor editor) {
		this.richText = richText;
		this.basic = richText.getBasicFormatter();
		this.extended = richText.getExtendedFormatter();
		this.textContent = textContent;
		this.referenceDate = editor.getReferenceDate();
		this.dateField = new DateField();
		this.dateField.setValue(new Date());
	}

	public VerticalPanel getToolbar() {
		outer.add(topPanel);
		outer.add(bottomPanel);
		topPanel.setWidth("100%");
		bottomPanel.setWidth("100%");
	
		initWidget(outer);

		Image boldImage = new Image("images/text/bold.gif");
		Image createLinkImage = new Image("images/text/createLink.gif");
		Image hrImage = new Image("images/text/hr.gif");
		Image indentImage = new Image("images/text/indent.gif");
		Image italicImage = new Image("images/text/italic.gif");
		Image justifyCenterImage = new Image("images/text/justifyCenter.gif");
		Image justifyRightImage = new Image("images/text/justifyRight.gif");
		Image justifyLeftImage = new Image("images/text/justifyLeft.gif");
		Image olImage = new Image("images/text/ol.gif");
		Image outdentImage = new Image("images/text/outdent.gif");
		Image removeFormatImage = new Image("images/text/removeFormat.gif");
		Image removeLinkImage = new Image("images/text/removeLink.gif");
		Image strikeThroughImage = new Image("images/text/strikeThrough.gif");
		Image subscriptImage = new Image("images/text/subscript.gif");
		Image superscriptImage = new Image("images/text/superscript.gif");
		Image ulImage = new Image("images/text/ul.gif");
		Image underlineImage = new Image("images/text/underline.gif");
		Image insertImageImage = new Image("images/text/insertImage.gif");
		Image backColorsImage = new Image("images/text/backColors.gif");
		Image foreColorsImage = new Image("images/text/foreColors.gif");
		Image fontsImage = new Image("images/text/fonts.gif");
		Image fontSizesImage = new Image("images/text/fontSizes.gif");
		
		
		/*Image saveImage = new Image("save.gif");
		Image exportPDFImage = new Image("pdfIcon.gif");
		Image printImage = new Image("print.gif");*/

		// if((rptDesign.equals("NoBirt"))){
		// topPanel.add(save = createPushButton(saveImage, strings.save()));
		// topPanel.add(exportPDF = createPushButton(exportPDFImage,
		// strings.exportPDF()));
		// topPanel.add(print = createPushButton(printImage, strings.print()));
		// }

		if (basic != null) {
			topPanel.add(bold = createToggleButton(boldImage, BabelFish.print().bold()));
			topPanel.add(italic = createToggleButton(italicImage, BabelFish.print().italic()));
			topPanel.add(underline = createToggleButton(underlineImage, BabelFish.print().underline()));
			topPanel.add(subscript = createToggleButton(subscriptImage, BabelFish.print().subscript()));
			topPanel.add(superscript = createToggleButton(superscriptImage, BabelFish.print().superscript()));
			topPanel.add(justifyLeft = createPushButton(justifyLeftImage, BabelFish.print().justifyLeft()));
			topPanel.add(justifyCenter = createPushButton(justifyCenterImage, BabelFish.print().justifyCenter()));
			topPanel.add(justifyRight = createPushButton(justifyRightImage, BabelFish.print().justifyRight()));
		}

		if (extended != null) {
			topPanel.add(strikethrough = createToggleButton(strikeThroughImage, BabelFish.print().strikeThrough()));
			topPanel.add(indent = createPushButton(indentImage, BabelFish.print().indent()));
			topPanel.add(outdent = createPushButton(outdentImage, BabelFish.print().outdent()));
			topPanel.add(hr = createPushButton(hrImage, BabelFish.print().hr()));
			topPanel.add(ol = createPushButton(olImage, BabelFish.print().ol()));
			topPanel.add(ul = createPushButton(ulImage, BabelFish.print().ul()));
			topPanel.add(insertImage = createPushButton(insertImageImage, BabelFish.print().insertImage()));
			topPanel.add(createLink = createPushButton(createLinkImage, BabelFish.print().createLink()));
			topPanel.add(removeLink = createPushButton(removeLinkImage, BabelFish.print().removeLink()));
			topPanel.add(removeFormat = createPushButton(removeFormatImage, BabelFish.print().removeFormat()));
		}

		if (basic != null) {
			backColorsImage.setTitle(BabelFish.print().backColors());
			bottomPanel.add(backColorsImage);
			bottomPanel.add(backColors = createColorList(BabelFish.print().highlightColor()));
			foreColorsImage.setTitle(BabelFish.print().foreColors());
			bottomPanel.add(foreColorsImage);
			bottomPanel.add(foreColors = createColorList(BabelFish.print().fontColor()));
			// bottomPanel.add(backColors = createPushButton(backColorsImage,
			// I18N.print().insertImage()));
			// bottomPanel.add(setBackColor = new Button());
			// bottomPanel.add(foreColors = createPushButton(foreColorsImage,
			// I18N.print().insertImage()));
			// bottomPanel.add(setForeColor = new Button());
			fontsImage.setTitle(BabelFish.print().font());
			bottomPanel.add(fontsImage);
			bottomPanel.add(fonts = createFontList());
			
			fontSizesImage.setTitle(BabelFish.print().fontSize());
			bottomPanel.add(fontSizesImage);
			bottomPanel.add(fontSizes = createFontSizes());

			// These listeners are only used for updating status, so don't hook
			// them up
			// unless at least basic editing is supported.
			// richText.addKeyboardListener(listener);
			// richText.addClickListener(listener);
			richText.addKeyboardListener(TextEditorToolbarController.onKey(this));
			richText.addClickListener(TextEditorToolbarController.onClick(this));
		}

		//Add Date Field to the top panel
		dateField = new DateField();
		dateField.getPropertyEditor().setFormat(dateFormat);
		dateField.setTitle(BabelFish.print().setReferenceDate()); //tooltip
		
		if(referenceDate!=null && !referenceDate.equals("null"))
			dateField.setValue(dateFormat.parse(referenceDate));
		else 			
			dateField.setValue(new Date());
//			dateField.setEmptyText(I18N.print().setReferenceDate());
		
		
		dateField.setWidth(150);
		dateField.setAllowBlank(true);
		dateField.setId("refDate");
		
		topPanel.add(new HTML ("&nbsp;&nbsp;")); //spacer
		topPanel.add(dateField);
		
		return outer;
	}

	private ListBox createColorList(String caption) {
		ListBox lb = new ListBox();
		lb.setWidth("120px");
		// lb.addChangeListener(listener);
		lb.addChangeListener(TextEditorToolbarController.onChange(this));
		lb.setVisibleItemCount(1);

		lb.addItem(caption);
		lb.addItem(BabelFish.print().white(), "white");
		lb.addItem(BabelFish.print().black(), "black");
		lb.addItem(BabelFish.print().red(), "red");
		lb.addItem(BabelFish.print().green(), "green");
		lb.addItem(BabelFish.print().yellow(), "yellow");
		lb.addItem(BabelFish.print().blue(), "blue");
		return lb;
	}

	private ListBox createFontList() {
		ListBox lb = new ListBox();
		lb.setWidth("120px");
		// lb.addChangeListener(listener);
		lb.addChangeListener(TextEditorToolbarController.onChange(this));
		lb.setVisibleItemCount(1);

		lb.addItem(BabelFish.print().font(), "");
		lb.addItem(BabelFish.print().normal(), "");
		lb.addItem("Times New Roman", "Times New Roman");
		lb.addItem("Arial", "Arial");
		lb.addItem("Courier New", "Courier New");
		lb.addItem("Georgia", "Georgia");
		lb.addItem("Trebuchet", "Trebuchet");
		lb.addItem("Verdana", "Verdana");
		return lb;
	}

	private ListBox createFontSizes() {
		ListBox lb = new ListBox();
		lb.setWidth("120px");
		// lb.addChangeListener(listener);
		lb.addChangeListener(TextEditorToolbarController.onChange(this));
		lb.setVisibleItemCount(1);

		lb.addItem(BabelFish.print().fontSize());
		lb.addItem(BabelFish.print().xxsmall());
		lb.addItem(BabelFish.print().xsmall());
		lb.addItem(BabelFish.print().small());
		lb.addItem(BabelFish.print().medium());
		lb.addItem(BabelFish.print().large());
		lb.addItem(BabelFish.print().xlarge());
		lb.addItem(BabelFish.print().xxlarge());
		return lb;
	}

	private PushButton createPushButton(Image img, String tip) {
		PushButton pb = new PushButton(img);
		// pb.addClickListener(listener);
		pb.addClickListener(TextEditorToolbarController.onClick(this));

		pb.setTitle(tip);
		return pb;
	}

	private ToggleButton createToggleButton(Image img, String tip) {
		ToggleButton tb = new ToggleButton(img);
		// tb.addClickListener(listener);
		tb.addClickListener(TextEditorToolbarController.onClick(this));
		tb.setTitle(tip);
		return tb;
	}

}
