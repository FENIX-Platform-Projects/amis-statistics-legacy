package org.fao.fenix.web.modules.adam.client.view.dataentry;

import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


public class DonorProfileDataEntryPanel  {

	private TabPanel tabPanel;
	
	private DonorProfileForm donorProfileForm;
	
	private DonorProcessesForm donorProcessesForm;
	
	public Boolean windowIsOpen = false;
	
	private String donorLabel;
	
	private ADAMDonorProfileVO donorProfileVO;
	
	private ToolBar toolbar;
	
	public DonorProfileDataEntryPanel() {
		
	}
	
   public VerticalPanel build(ADAMDonorProfileVO vo) {
		
	    VerticalPanel vp = new VerticalPanel();     
	    vp.add(buildForm(vo));
		
		return vp;
	}
	
private FormPanel buildForm(ADAMDonorProfileVO vo) {
		
		FormPanel form = new FormPanel();  
		form.setPadding(0);  
		form.setFrame(false);  
		form.setHeaderVisible(false);
		form.setBodyBorder(false);  
		
		form.addStyleName("profile-edit-form");
		form.setLabelWidth(150);
		form.setLabelAlign(LabelAlign.TOP);  
		form.setButtonAlign(HorizontalAlignment.CENTER);  
		form.setLayout(new FitLayout());  
		
			    
		tabPanel = new TabPanel();
		tabPanel.add(new DonorProfileForm().build(vo));  
		tabPanel.add(new DonorProcessesForm().build(vo));  
		 
		form.add(tabPanel);  
		
		form.addButton(new Button(BabelFish.print().saveChanges()));  
		
		
		form.setSize(750, 1000);  
		
		
		return form;
	}

	
	public ToolBar buildToolBar(){
		toolbar = new ToolBar();
		Button save = new Button("Save");  
		save.setIconStyle("save");  
		toolbar.add(save);
		return toolbar;
		
	}
	
	public TabPanel getTabPanel() {
		return tabPanel;
	}

	
	public DonorProfileForm getDonorProfileForm() {
		return donorProfileForm;
	}

	public DonorProcessesForm getDonorProcessesModule() {
		return donorProcessesForm;
	}

	
	public String getDonorLabel() {
		return donorLabel;
	}

	public void setDonorLabel(String donorLabel) {
		this.donorLabel = donorLabel;
	}

	public ADAMDonorProfileVO getDonorProfileVO() {
		return donorProfileVO;
	}

	
	
}