package org.fao.fenix.web.modules.shared.window.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class Tonictionary extends FenixWindow {

	private HTML html;
	
	private VerticalPanel wrapper;
	
	private String[] catalogue = new String[]{"<b>Triangulization</b><br>When I have three thing than I find you.<br>", 
											  "<b>Randomic</b><br>Something having a random behaviour or Randomic.<br><br>",
											  "<b>Habove</b><br>Please refer to the English word <i>above</i>.<br><br>",
	 										  "<b>Embed</b><br>To add a button.<br><br>",
	 										  "<b>-ional</b><br>Should be appended to the end of any word, to make it sound more interesting<br><br>",
	 										  "<b>Mirrorized</b><br>Please refer to the English word <i>mirror image</i>. But it can be applied to everything.<br>" +
	 										  "<b>Hooligan</b>a violent <i>(or want-to-be)</i> (almost) young troublemaker.",  
	 										  "<b>Lady</b> (polite or formal use) a woman.", 
	 										  "<b>Pyramidization</b> an excuse.", 
	 										  "<b>JVM</b> read above."};
	
	public Tonictionary() {
		html = new HTML("");
		wrapper = new VerticalPanel();
	}
	
	public void build() {
		getWindow().setHeading("Tonictionary!");
		buildCenterPanel();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		String body = "";
		for (String s : catalogue)
			body += s;
		html.setHTML(body);
		wrapper.add(html);
		wrapper.setScrollMode(Scroll.AUTO);
		getCenter().add(wrapper);
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
}
