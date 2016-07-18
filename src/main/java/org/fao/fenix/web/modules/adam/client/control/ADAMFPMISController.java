package org.fao.fenix.web.modules.adam.client.control;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.fpmis.ADAMFPMISProjects;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;

import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;


public class ADAMFPMISController extends ADAMController {
	
	public static String urlFPMISProjectsBase = "https://extranet.fao.org/fpmis/FPMISReportServlet.jsp?APD=&div=&type=countryprofileopen&lng=EN&qlfrs=&UF=N&typeUF=&colorder=2345&pwb=&sorttype=1&";
	
//	public static String url = "https://extranet.fao.org/fpmis/FPMISReportServlet.jsp?APD=&countryId=ID&div=&fundG=&type=countryprofileopen&lng=EN&qlfrs=&UF=N&typeUF=&colorder=2345&pwb=&sorttype=1";
//	public static String url = "https://extranet.fao.org/fpmis/FPMISReportServlet.jsp?APD=&countryId=YE&div=&fundG=&type=countryprofileopen&lng=EN&qlfrs=&UF=N&typeUF=&colorder=2345&pwb=&sorttype=1";
	
	

	public static SelectionListener<ButtonEvent> showFPMIS(final Button button) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				showFPMISAgentBig("FPMIS Projects", "CENTER", "red", ADAMConstants.BIG_BOX_WIDTH, ADAMConstants.BIG_BOX_HEIGHT);

			}
		};
	}
	
	public static SelectionListener<MenuEvent> showFPMIS(final MenuItem button) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				showFPMISAgentBig("FPMIS Projects", "CENTER", "red", ADAMConstants.BIG_BOX_WIDTH, ADAMConstants.BIG_BOX_HEIGHT);
			}
		};
	}
	
	/**
	 * 
	 * THIS IS FOR THE COUNTRIES REQUEST
	 */
	private static void showFPMISAgentBig(final String title, final String position, final String color,final String width,final String height) {
		
		/** TODO call to made the ID country name dinamic **/

		Map<String, String> recipients = ADAMBoxMaker.countriesSelected;
		
		ADAMQueryVO qvo = ADAMQueryVOBuilder.getFRMIPCountryID(title, recipients);
		
	
		
		/** TODO, all selected check **/
		if ( recipients.size() == 1 ) {
			ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
			

			public void onSuccess(ADAMResultVO rvo) {
				
				// should be just one country
				if ( rvo.getGaulMap().isEmpty() ) {
					FenixAlert.info("FPMIS Projects", "FPMIS doesn't have data for this recipient");
				} 
				else {
					String url = "";
					for (String recipientID : rvo.getGaulMap().keySet() ) {
						url = getFPMISProjectURL(recipientID);
					}
	
					RootPanel.get(position).setStyleName("small-box content");
					
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					
					RootPanel.get(position).add(new ADAMFPMISProjects().buildBigProjectList(rvo, url, width, height, position, color));
				}
			}
			
			public void onFailure(Throwable arg0) {

			}
		});
			
		}
		else {
			FenixAlert.info("Too many selected recipients countries", "The FPMIS project list is available only for single countries");
		}
		
		

		
		
		
		
//		ADAMQueryVO qvo = ADAMQueryVOBuilder.createFRPMISRequest(ADAMBoxContent.FPMIS_REQUEST.toString(), ADAMBoxContent.FPMIS_REQUEST.toString());
//		
//		
//		ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
//			
//
//			public void onSuccess(ADAMResultVO result) {
//				// TODO Auto-generated method stub
//				System.out.println("ok received");
//				
//				
//				
//			}
//			
//
//			public void onFailure(Throwable arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}
	
	
	
	
	private static String getFPMISProjectURL(String countryId){
		String url = urlFPMISProjectsBase + "countryId=" + countryId;
		return url;
	}
	

	
}
