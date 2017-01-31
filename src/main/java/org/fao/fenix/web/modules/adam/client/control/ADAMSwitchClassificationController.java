package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ADAMSwitchClassificationController extends ADAMController {

	public static Listener<ComponentEvent> addSwitchClassificationListener(final HorizontalPanel holder, final VerticalPanel mainContainer) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				CheckBox box = ((CheckBox)ce.getComponent());	
				if(box.getValue()) {
					box.setBoxLabel("<font color='white' >Clear FAO Strategic Framework</font>");  	
					box.recalculate();
					
					//GOOGLE Analytics
				//	GoogleAnalyticsController.trackEvent(ADAMCurrentVIEW.FAOVIEW.toString(), "Switch to FAO Strategic Framework", "");	
					ADAMController.callViewAgent(ADAMCurrentVIEW.FAOVIEW, "classificationListListener",ADAMController.selectedTab);	
					
				} else {
					box.setBoxLabel("<font color='white'>Switch to FAO Strategic Framework</font>");  
					box.recalculate();
					//GOOGLE Analytics
				//	GoogleAnalyticsController.trackEvent(ADAMCurrentVIEW.ADAMVIEW.toString(), "Switch to OECD DAC Sectors", "");
					ADAMController.callViewAgent(ADAMCurrentVIEW.ADAMVIEW, "classificationListListener", ADAMController.selectedTab);
				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> showClassificationInfo() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {

				StringBuilder sb = new StringBuilder();
			    sb.append("<font style='text-align:justify;'><br/><font color='#1B65A4'><b>FAO Strategic Framework</b></font><br/><br/> ");
				sb.append("Switching to the Strategic Framework replaces the OECD-CRS Sector and Sub-Sector lists with the FAO SOs and ORs lists. <br/><br/>");
				sb.append("The displayed charts are the result of a conversion from the OECD-DAC Classification to the FAO Strategic Framework and consequently the budget may be split among different ORs.<br/><br/>");
				sb.append("For more information on how the OECD-DAC Classification has been mapped to the FAO Strategic Framework, please click <a href='#' target='_blank'> here</a>.<br/><br/>") ;
				sb.append("</font>");
				
				Text label = new Text(sb.toString()) ;
				label.addStyleName("pad-text");
				label.setStyleAttribute("backgroundcolor", "white");

				LayoutContainer container = new LayoutContainer();
				container.setLayout(new FitLayout());
				
				ContentPanel panel = new ContentPanel();				
				panel.setHeaderVisible(false);
				panel.add(label);
				container.add(panel);

				Window window = new Window();
				window.setHeight(255);
				window.setWidth(465);
				window.setLayout(new FitLayout());

				window.add(container);
				window.setHeaderVisible(true);
				window.setHeading("ADAM FAO Strategic Framework Information");

				window.show();
			}
		};
	}

}