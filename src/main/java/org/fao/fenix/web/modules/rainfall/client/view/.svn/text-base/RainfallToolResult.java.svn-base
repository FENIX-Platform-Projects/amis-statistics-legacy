package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class RainfallToolResult {

	private ContentPanel resultPanel;

	private HTML result;
	
	public RainfallToolResult() {
		createResultPanel();
		if (RootPanel.get("result").getWidgetCount() > 0)
			RootPanel.get("result").remove(0);
		RootPanel.get("result").add(resultPanel);
	}

	private void createResultPanel() {

		resultPanel = new ContentPanel();
		resultPanel.setHeaderVisible(false);
		resultPanel.setLayout(new FitLayout());
		resultPanel.setHeight("400px");
		resultPanel.setScrollMode(Scroll.AUTO);
//		resultPanel.setWidth("79%");

		result = new HTML("");
		result.setHTML(BabelFish.print().quickStart());
		resultPanel.add(result);
	}
	
	public void setResult(String content) {
		this.result.setHTML(content);
	}

	public HTML getResult() {
		return result;
	}

}