package org.fao.fenix.web.modules.adam.client.view.dataentry;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.ScrollPanel;


public class DonorProfileForm  {

	public Map<String, TextField<String>> exactValueMap;
	public final DateTimeFormat dateFormat = DateTimeFormat.getFormat("MMM-yyyy");
	
	

	public DonorProfileForm() {
	}

	public TabItem build(ADAMDonorProfileVO donorProfileVO) {
		
		FormData formData = new FormData("98%");  
		
	    TabItem tab = new TabItem();
		tab.setStyleAttribute("padding", "10px");  
		tab.setText("Profile");  
		tab.setLayout(new FormLayout());  
		//tab.setScrollMode(Scroll.AUTO);
		
					    
		DateField refDate = new DateField();  
		refDate.getPropertyEditor().setFormat(dateFormat);
		refDate.setFieldLabel(BabelFish.print().setReferenceDate()); 
		refDate.setWidth(100);
		
		if(!donorProfileVO.getProfileReferenceDate().equals(""))
			refDate.setValue(dateFormat.parse(donorProfileVO.getProfileReferenceDate()));
		else 			
			refDate.setValue(new Date());
	
			
		tab.add(refDate, formData);  
		 		
		FormLayout layout = new FormLayout();  
		layout.setLabelAlign(LabelAlign.LEFT);  
		layout.setLabelWidth(100);  
		   		
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setLayout(layout);  
		fieldSet.setHeading(BabelFish.print().resourceMobilizationOfficerInformation());  
		
		
		TextField<String> first = new TextField<String>();  
		first.setFieldLabel(BabelFish.print().name());  
		first.setValue(donorProfileVO.getResponsibleMobilizationOfficer());
		fieldSet.add(first, formData);  
		  
	   
		TextField<String> email = new TextField<String>();  
		email.setFieldLabel(donorProfileVO.getEmailTitle());  
		email.setValue(donorProfileVO.getResourceMobilizationOfficerEmail());
		
		fieldSet.add(email, formData);  
		
		tab.add(fieldSet, formData);  
		
		HtmlEditor themes = new HtmlEditor();  
		themes.setFieldLabel(donorProfileVO.getPriorityThemesTitle());  
		themes.setValue(donorProfileVO.getPriorityThemes());
		
		themes.setHeight(200);  
		tab.add(themes, formData);  
		 
		HtmlEditor areas = new HtmlEditor();  
		areas.setFieldLabel(donorProfileVO.getPriorityGeographicalAreasTitle());  
		areas.setValue(donorProfileVO.getPriorityGeographicalAreas());
		areas.setHeight(200);  
	
		tab.add(areas, formData);  
			    
        TextField<String> funding = new TextField<String>();  
        funding.setFieldLabel(donorProfileVO.getFavouredFundingArrangementsTitle()); 
        funding.setHeight(40);  
        
        StringBuilder fundingSB = new StringBuilder();

	  /**  if(donorProfileVO.getFavouredFundingArrangements()!=null){
	    	int i= 0;
	    	for(String type:  donorProfileVO.getFavouredFundingArrangements().keySet()){
	    		fundingSB.append(type);  
	    		if (i <  donorProfileVO.getFavouredFundingArrangements().size() - 1)
	    			fundingSB.append(", ");

	    		i++;
	    	}
	    	 funding.setValue(fundingSB.toString());
	    }
		
	    tab.add(funding, formData);  
			**/
				    
		HtmlEditor webLinks = new HtmlEditor();  
		webLinks.setFieldLabel(donorProfileVO.getExternalLinksTitle());  
		webLinks.setHeight(100);  
		
		 List<String> links = donorProfileVO.getExternalLinks();
	     StringBuilder linksSB = new StringBuilder();

		 if(links!=null){
		    	for(String link: links)
		    		linksSB.append("<a href='"+link+"'>"+link+"</a><br/>");
				
		    		webLinks.setValue(linksSB.toString());  
	       }
		    
					
		 tab.add(webLinks,formData);  
		    
	    return tab;
		
	}
	
}