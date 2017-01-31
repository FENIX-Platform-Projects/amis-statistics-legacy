package org.fao.fenix.web.modules.birt.client.view.chart.viewer.format;



import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;


public class StylePanel {
	
	
	
	CheckBox bold;
	
	CheckBox italic;
	
	CheckBox underline;
	
	ListBox fontType;
	
	public StylePanel() {
		bold = new CheckBox();
		italic = new CheckBox();
		underline = new CheckBox();
	}
	
	
	public HorizontalPanel buildHorizontalPanel(Boolean boldChecked, Boolean italicChecked, Boolean underlineChecked, String fontType) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		panel.add(buildCheckBoxPanel(bold, "Bold", boldChecked));
		panel.add(buildCheckBoxPanel(italic, "Italic", italicChecked));
		panel.add(buildCheckBoxPanel(underline, "Underline", underlineChecked));
		panel.add(buildFontType("font style", fontType));
		
		
		return panel;
	}
	
	public VerticalPanel buildVerticalPanel(Boolean boldChecked, Boolean italicChecked, Boolean underlineChecked, String selected) {
		VerticalPanel panel = new VerticalPanel();
//		panel.setSpacing(5);
		
		panel.add(buildCheckBoxPanel(bold, "Bold", boldChecked));
		panel.add(buildCheckBoxPanel(italic, "Italic", italicChecked));
		panel.add(buildCheckBoxPanel(underline, "Underline", underlineChecked));
		panel.add(buildFontType("Font Style", selected));
		
		return panel;
	}
	
	private HorizontalPanel buildCheckBoxPanel(CheckBox checkBox, String title, Boolean checked) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		HTML html = new HTML(title);
		html.setWidth("45px");
		panel.add(html);
		
		checkBox.setValue(checked);
		panel.add(checkBox);
		
		return panel;
	}
	
	private HorizontalPanel buildFontType(String title, String selected) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		HTML html = new HTML(title);
		html.setWidth("45px");
		panel.add(html);
		
		fontType = new ListBox();

		fontType.addItem("Sans-Serif", "sans-serif");
		fontType.addItem("Serif", "serif");
		
		
		panel.add(fontType);
		
		return panel;
	}
	
	public void setFontStyle(String code) {
		for(int i=0; i < fontType.getItemCount(); i++) {
			if ( fontType.getValue(i).equalsIgnoreCase(code)) {
				fontType.setSelectedIndex(i);
				break;
			}
		}
		
	}


	public CheckBox getBold() {
		return bold;
	}


	public void setBold(CheckBox bold) {
		this.bold = bold;
	}


	public CheckBox getItalic() {
		return italic;
	}


	public void setItalic(CheckBox italic) {
		this.italic = italic;
	}


	public CheckBox getUnderline() {
		return underline;
	}


	public void setUnderline(CheckBox underline) {
		this.underline = underline;
	}


	public ListBox getFontType() {
		return fontType;
	}
	
	
	
	
}
