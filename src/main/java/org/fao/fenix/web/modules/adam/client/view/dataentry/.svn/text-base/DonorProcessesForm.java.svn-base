package org.fao.fenix.web.modules.adam.client.view.dataentry;

import java.util.List;

import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class DonorProcessesForm {

	public DonorProcessesForm() {
	}

    public TabItem build(ADAMDonorProfileVO donorProfileVO) {
		
		FormData formData = new FormData("98%");  
		
	    TabItem tab = new TabItem();
		tab.setStyleAttribute("padding", "10px");  
		tab.setText("Processes");  
		tab.setLayout(new FormLayout());  
		tab.setScrollMode(Scroll.AUTO);
					    		
		HtmlEditor funding = new HtmlEditor();  
		funding.setFieldLabel(donorProfileVO.getFundingBodiesTitle());  
		funding.setValue(donorProfileVO.getFundingBodies());
		
		funding.setHeight(200);  
		tab.add(funding, formData);  
		 
		    
	    return tab;
		
	}

	
}