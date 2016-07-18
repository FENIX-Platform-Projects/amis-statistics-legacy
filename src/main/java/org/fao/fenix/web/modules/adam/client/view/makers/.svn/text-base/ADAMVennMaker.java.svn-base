package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.List;

import org.fao.fenix.web.modules.adam.client.view.venn.ADAMVennPriorities;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;


public class ADAMVennMaker extends ADAMBoxMaker {

	public static VerticalPanel buildSmallPriorityVenn(String title, String image, String width, String height, ADAMResultVO rvo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener) {
		
		ADAMVennPriorities vennPriorities = new ADAMVennPriorities(rvo, true);
		
		return vennPriorities.buildSmallPriorityVenn(title, image, width, height, rvo, position, color, objectSizeListener);
	}
	
	public static VerticalPanel buildBigPriorityVenn(String title, String image, String width, String height, ADAMResultVO rvo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener) {
		
		ADAMVennPriorities vennPriorities = new ADAMVennPriorities(rvo, false);
		
		//return ADAMVennPriorities.buildBigPriorityVenn(title, image, width, height, vennBean, objectSizeListener);
		//return ADAMVennPriorities.buildBigPriorityVenn(title, image, width, height, rvo, position, color, objectSizeListener);
		return vennPriorities.buildBigPriorityVenn(title, image, width, height, rvo, position, color, objectSizeListener);
	}
	
	
	
//	private static void createDACPriorities(List<VennIntersectionsBean> intersactions, VennBeanVO vennBean, VerticalPanel panel) {
////		panel.add(new Html("<div style='font-size:12px;font-weight:bold'><u> Common Priorities <u></div><br>"));
//		
//		for (VennIntersectionsBean intersaction : intersactions) { 
//			if (!intersaction.getAggregatedDacCodes().isEmpty()) {					
//				String htmlCentralString = new String();
//				Hyperlink labelCentral = new Hyperlink();
//				String str = "000000" + Integer.toHexString(intersaction.getColor());
//				String color = "#" + str.substring(str.length() - 6);
//				if ( color.equals("#ffffff")) 
//					color = "#000000";
//		
////				labelCentral.setHTML("<div class='link-title'>" + intersaction.getLabel() + "</u></div>");
//
//				labelCentral.setHTML("<div style='font-size:10px; color:" + color + "'><u>" + intersaction.getLabel() + "</u></div>");
//				panel.add(labelCentral);
//				com.extjs.gxt.ui.client.widget.Window windowCentral = new com.extjs.gxt.ui.client.widget.Window();
//				windowCentral.setHeading(intersaction.getLabel());
//				for(String dacCode : intersaction.getAggregatedDacCodes()) {
//					htmlCentralString +=  /**dacCode + " - " + **/ vennBean.getVennGraphBeanVO().getDacCodes().get(dacCode);
//					htmlCentralString += "<br>";
//				}
//				HTML htmlCentral = new HTML(htmlCentralString);
//				windowCentral.add(htmlCentral);
//				labelCentral.addClickHandler(VennController.selectIntersaction(windowCentral));
//			}
//		}
//	}
	
	
	

}