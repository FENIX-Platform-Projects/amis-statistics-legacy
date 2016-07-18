package org.fao.fenix.web.modules.cpf.client.view.find;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.cpf.client.view.utils.layout.FormattingUtils;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class CPFSearchResults {

	ContentPanel panel;

	// based on items, elements etc...
	LinkedHashMap<String, CPFSearchResults> clusterResults;



	public CPFSearchResults() {

		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		clusterResults = new LinkedHashMap<String, CPFSearchResults>();

	}

	public void build(TextField<String> searchTextBox) {
//		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
		panel.add(FormattingUtils.addVSpace(10));

		panel.hide();
	}



	public ContentPanel getPanel() {
		return panel;
	}

	public LinkedHashMap<String, CPFSearchResults> getClusterResults() {
		return clusterResults;
	}

}
