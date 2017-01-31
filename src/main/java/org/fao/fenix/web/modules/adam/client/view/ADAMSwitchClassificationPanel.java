package org.fao.fenix.web.modules.adam.client.view;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMSwitchClassificationController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;

/*The selection is between OECD-DAC Sector VIEW (ADAM VIEW) or FAO Strategic Framework (FAOVIEW)*/

public class ADAMSwitchClassificationPanel {

	static VerticalPanel panel;
	public static CheckBox classificationCheckBox;
	
	public static Boolean isBuild = false;
	

    public static VerticalPanel build(ADAMCurrentVIEW currentVIEW) {
    	 isBuild = true;
    	// currentSelection = currentVIEW;

    	 VerticalPanel vp = new VerticalPanel();
    	 vp.setHorizontalAlign(HorizontalAlignment.LEFT);
    	 vp.setVerticalAlign(VerticalAlignment.BOTTOM);


    	/* HorizontalPanel p = new HorizontalPanel();
    	 p.setHorizontalAlign(HorizontalAlignment.LEFT);
    	 p.setVerticalAlign(VerticalAlignment.BOTTOM);*/
    	 HorizontalPanel p = new HorizontalPanel();
 		 p.setHorizontalAlign(HorizontalAlignment.LEFT);
 		 p.setVerticalAlign(VerticalAlignment.TOP);
 		 p.setSpacing(5);

   	
    	 IconButton infoIcon = new IconButton("adam_info");
    	 infoIcon.setToolTip("Click for more information on the FAO Strategic Framework");
    	 infoIcon.setHeight(20);
    	 infoIcon.addSelectionListener(ADAMSwitchClassificationController.showClassificationInfo());
    	 
    	 classificationCheckBox = ADAMBoxMaker.createToolbarCheckBox(null);
 		
    	 
    	 if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
    		 classificationCheckBox.setBoxLabel("<font color='white'>Clear FAO Strategic Framework</font>");  
    		 classificationCheckBox.setValue(true);
    	 }
    	 else if (ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
    		 classificationCheckBox.setBoxLabel("<font color='white'>Switch to FAO Strategic Framework</font>");  
    		 classificationCheckBox.setValue(false);
    	 }
    	 
    	 classificationCheckBox.addListener(Events.OnClick, ADAMSwitchClassificationController.addSwitchClassificationListener(p, vp));

    	 p.add(infoIcon);
    	 p.add(classificationCheckBox);
    	
    	 vp.add(p);

    	 return vp;	
     }
     
    
     
	public VerticalPanel getPanel() {
		return panel;
	}
	
	public static Boolean getIsBuild() {
		return isBuild;
	}
	
}