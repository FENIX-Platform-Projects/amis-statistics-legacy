package org.fao.fenix.web.modules.amis.client.view.download;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.control.download.AMISFoodBalanceDownloadController;
import org.fao.fenix.web.modules.amis.client.view.download.foodbalance.AMISDownloadFoodBalance;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class AMISDownloadDomainPanel {
		
	ContentPanel panel;
	  
    RadioGroup radioGroup;
    
    Radio radioFaostat;
    
    Radio radioPsd;
        
    Radio radioCcbs;
    
    Radio radioIgc;
    
    Radio radioAmis;
    
    Radio radioNational;
    
    FieldSet fieldSet = new FieldSet();
     

    
    public AMISDownloadDomainPanel() {
		panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		panel.setStyleAttribute("padding-left", "20px");
		
		radioFaostat = new Radio();
		radioPsd = new Radio();
		radioIgc = new Radio();
		radioCcbs = new Radio();
		radioCcbs.setValue(true); 
		radioAmis = new Radio();
		radioNational= new Radio();
		
		radioGroup = new RadioGroup();
		radioGroup.setBorders(false);
		radioGroup.setOrientation(Orientation.VERTICAL); 
		radioGroup.add(radioCcbs);
		//radioGroup.add(radioFaostat);
		radioGroup.add(radioPsd);
		radioGroup.add(radioIgc);
		//radioGroup.add(radioAmis);
		radioGroup.add(radioNational);
	      
	      fieldSet.setBorders(false);  
	      fieldSet.setHeading("Select a Data Source:"); 
	      FormLayout layout = new FormLayout(); 
	      layout.setLabelSeparator("");
	      layout.setLabelAlign(LabelAlign.LEFT); 
	      layout.setLabelWidth(0);
	      layout.setLabelPad(0);
	      fieldSet.setLayout(layout);  
	      
	      fieldSet.add(radioGroup);

//         fieldSet.add(new Html("<div style='font:12px tahoma,arial,helvetica,sans-serif'>National data updated as of end of November 2014</div>"));
//        fieldSet.add(new Html("<div style='font:12px tahoma,arial,helvetica,sans-serif'>National data updated as of mid-May 2014</div>"));
	
	}
	
	public ContentPanel build(AMISDownload download) {
		panel.add(FormattingUtils.addVSpace(5));
		
		addFieldSet();
		
//		AMISDownloadController.getDomainsAgentRadio(download, "FAO-CBS", radioFaostat, radioPsd, radioCcbs, radioIgc, radioAmis, radioNational);
        AMISDownloadController.getDomainsAgentRadio(download, "FAO-AMIS", radioFaostat, radioPsd, radioCcbs, radioIgc, radioAmis, radioNational);
				
		addRadioListeners(download);
		return panel;
	}
	
	
	private void addRadioListeners(final AMISDownload download) {
		System.out.println("1 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
		radioFaostat.addListener(Events.OnClick, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("2 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
				
				System.out.println("3 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: " );
				for(AMISCodesModelData selectedCode : AMISDownloadController.radioList)
				{
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: radioFaostat.getFieldLabel() "+radioFaostat.getBoxLabel());
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: selectedCode.getCode() "+selectedCode.getCode());
					if(selectedCode.getCode().equals(radioFaostat.getBoxLabel()))
					{
                        download.buildSelectors(selectedCode);

                        download.getTablePanel().getTablesPanel().removeAll();
					
						download.getTitlePanel().getPanel().layout();
					}
				}				
			}
		});
		
		radioPsd.addListener(Events.OnClick, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("2 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
				
				System.out.println("3 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: " );
				
				for(AMISCodesModelData selectedCode : AMISDownloadController.radioList)
				{
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: radioPsd.getFieldLabel() "+radioPsd.getBoxLabel());
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: selectedCode.getCode() "+selectedCode.getCode());
					if(selectedCode.getCode().equals(radioPsd.getBoxLabel()))
					{
                        if(AMISDownloadController.isFoodBalance)
                            ((AMISDownloadFoodBalance)download).buildSelectors( AMISFoodBalanceDownloadController.selectedByOptionModel);
                        else
                            download.buildSelectors(selectedCode);

						download.getTablePanel().getTablesPanel().removeAll();
						
						download.getTitlePanel().getPanel().layout();
					}
				}				
			}
		});
		
		radioCcbs.addListener(Events.OnClick, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("2 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
				
				System.out.println("3 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: " );
				
				for(AMISCodesModelData selectedCode : AMISDownloadController.radioList)
				{
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: radioCcbs.getFieldLabel() "+radioCcbs.getBoxLabel());
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: selectedCode.getCode() "+selectedCode.getCode());
					if(selectedCode.getCode().equals(radioCcbs.getBoxLabel()))
					{
                        if(AMISDownloadController.isFoodBalance)
                            ((AMISDownloadFoodBalance)download).buildSelectors( AMISFoodBalanceDownloadController.selectedByOptionModel);
                        else
                            download.buildSelectors(selectedCode);

						download.getTablePanel().getTablesPanel().removeAll();
						
						download.getTitlePanel().getPanel().layout();
					}
				}				
			}
		});
        
		radioAmis.addListener(Events.OnClick, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("2 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
				
				System.out.println("3 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: " );
				
				for(AMISCodesModelData selectedCode : AMISDownloadController.radioList)
				{
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: radioAmis.getFieldLabel() "+radioAmis.getBoxLabel());
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: selectedCode.getCode() "+selectedCode.getCode());
					if(selectedCode.getCode().equals(radioAmis.getBoxLabel()))
					{
                        if(AMISDownloadController.isFoodBalance)
                            ((AMISDownloadFoodBalance)download).buildSelectors( AMISFoodBalanceDownloadController.selectedByOptionModel);
                        else
                            download.buildSelectors(selectedCode);

						download.getTablePanel().getTablesPanel().removeAll();
						
					
						download.getTitlePanel().getPanel().layout();
					}
				}				
			}
		});
		
		radioIgc.addListener(Events.OnClick, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("2 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
					
				for(AMISCodesModelData selectedCode : AMISDownloadController.radioList)
				{
					System.out.println("3 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: radioIgc.getFieldLabel() "+radioIgc.getBoxLabel());
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: selectedCode.getCode() "+selectedCode.getCode());
					if(selectedCode.getCode().equals(radioIgc.getBoxLabel()))
					{
                        if(AMISDownloadController.isFoodBalance)
                            ((AMISDownloadFoodBalance)download).buildSelectors( AMISFoodBalanceDownloadController.selectedByOptionModel);
                        else
                            download.buildSelectors(selectedCode);

						download.getTablePanel().getTablesPanel().removeAll();
						
					
						download.getTitlePanel().getPanel().layout();
					}
				}				
			}
		});
		
		radioNational.addListener(Events.OnClick, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				System.out.println("2 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: ");
				
				System.out.println("3 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: " );
				
				for(AMISCodesModelData selectedCode : AMISDownloadController.radioList)
				{
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: radioNational.getFieldLabel() "+radioNational.getBoxLabel());
					System.out.println("4 Class: AmisDownloadDomainPanel Function: addRadioListeners Text: HandleEvent selected: selectedCode.getCode() "+selectedCode.getCode());
					if(selectedCode.getCode().equals(radioNational.getBoxLabel()))
					{
                        if(AMISDownloadController.isFoodBalance)
                            ((AMISDownloadFoodBalance)download).buildSelectors( AMISFoodBalanceDownloadController.selectedByOptionModel);
                        else
                            download.buildSelectors(selectedCode);

						download.getTablePanel().getTablesPanel().removeAll();
						
						download.getTitlePanel().getPanel().layout();
					}
				}				
			}
		});
	}

	
	public Html addTitle(String title) {
		return new Html("<div class='download_domain_title'> " + title + "</div>");
	}
	
	
	public void addFieldSet() {
	      panel.add(fieldSet);
	}

}
