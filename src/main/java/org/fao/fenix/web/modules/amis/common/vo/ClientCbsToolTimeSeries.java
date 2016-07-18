package org.fao.fenix.web.modules.amis.common.vo;

import java.util.ArrayList;
import java.util.List;

public class ClientCbsToolTimeSeries {

	private List<List<ClientCbsDatasetResult>> element;
	
	public ClientCbsToolTimeSeries() {
	
		element = new ArrayList<List<ClientCbsDatasetResult>>();
	}

	public List<List<ClientCbsDatasetResult>> getElement() {
		return element;
	}

	public void setElement(List<List<ClientCbsDatasetResult>> element) {
		this.element = element;
	}
}
