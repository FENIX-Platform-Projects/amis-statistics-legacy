package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ADAMQuantityColumnController extends ADAMController {

	public static SelectionChangedListener<GaulModelData> updateAggregationColumnADAMView(final ComboBox<GaulModelData> aggregationColumnList) {
			return new SelectionChangedListener<GaulModelData>() {
				public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
		
					crs_aggregationColumn = aggregationColumnList.getSelection().get(0);

					callViewAgent(currentVIEW, "updateAggregationColumnADAMView", selectedTab);
				}
			};
		}
	
	
	public static SelectionListener<IconButtonEvent> showOECDQuantityInfo() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				
				StringBuilder sb = new StringBuilder();
				sb.append("<font style='text-align:justify;'><br/><font color='#1B65A4'><b>Development Assistance Flow Type</b></font><br/><br/> ");
				sb.append("The OECD-CRS reports outflows from the resource partner to the recipient country. From ADAM "+
						"you can select commitments or disbursements and all amounts are expressed in USD millions (unless otherwise specified) in either constant or current prices. "+
						"The OECD definitions are listed below:<br/><br/>");
				sb.append("<b>Disbursements</b> - The transactions of providing financial resources. The two counterparties must record the transaction simultaneously.<br/>"+
						"In practice, disbursements are recorded at one of several stages: provision of goods and services (where trade credit is involved); placing of funds at the disposal of the recipient in an earmarked fund or account; withdrawal of funds by the recipient from an earmarked fund or account; or payment by the lender of invoices on behalf of the borrower.<br/><br/>");
				sb.append("<b>Commitments</b> - A firm obligation, expressed in writing and backed by the necessary funds, undertaken by an official donor to provide specified assistance to a recipient country or a multilateral organisation.<br/>"+
				"Bilateral commitments are recorded in the full amount of expected transfer, irrespective of the time required for the completion of disbursements.<br/><br/>"+
				"Commitments to multilateral organisations are reported as the sum of (i) any disbursements in the year reported on which have not previously been notified as commitments and (ii) expected disbursements in the following year.<br/><br/>");
				sb.append("<b>Constant prices (deflated amounts)</b> - Constant prices are obtained by directly factoring changes over time in the values of flows or stocks of goods and services into two components reflecting changes in the prices of the goods and services concerned and changes in their volumes (i.e. changes in 'constant price terms'); the term 'at constant prices' commonly refers to series which use a fixed-base Laspeyres formula.<br/>"+
						"(Constant prices (deflated amounts) include adjustments to allow for inflation rate changes in resource partner countries as well as changes in exchange rates between the resource partner currency and the US dollar over the same period)<br/><br/>");
				sb.append("</font>");		
				Text label = new Text(sb.toString());
				label.addStyleName("pad-text");
				label.setStyleAttribute("backgroundcolor", "white");

				LayoutContainer container = new LayoutContainer();
				container.setLayout(new FitLayout());
				
				ContentPanel panel = new ContentPanel();				
				panel.setHeaderVisible(false);
				panel.add(label);
				container.add(panel);

				Window window = new Window();
				window.setHeight(500);
				window.setWidth(650);
				window.setLayout(new FitLayout());

				window.add(container);
				window.setHeaderVisible(true);
				window.setHeading("ADAM OECD Development Assistance Flow Type");

				window.show();
			}
		};
	}
	
	
	public static SelectionListener<IconButtonEvent> showFPMISQuantityInfo() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				
				StringBuilder sb = new StringBuilder();
				sb.append("<font style='text-align:justify;'><br/><font color='#1B65A4'><b>Approvals</b></font><br/><br/> ");
				sb.append("The total budget of all projects newly approved and budget revisions during the reported period, as reported by Data Warehouse at the time of data retrieval. This figure does not include commitments from donors for projects that have not been entered in the Data Warehouse.<br/><br/>");
				sb.append("</font>");		
				Text label = new Text(sb.toString());
				label.addStyleName("pad-text");
				label.setStyleAttribute("backgroundcolor", "white");

				LayoutContainer container = new LayoutContainer();
				container.setLayout(new FitLayout());
				
				ContentPanel panel = new ContentPanel();				
				panel.setHeaderVisible(false);
				panel.add(label);
				container.add(panel);

				Window window = new Window();
				window.setHeight(300);
				window.setWidth(400);
				window.setLayout(new FitLayout());

				window.add(container);
				window.setHeaderVisible(true);
				window.setHeading("ADAM FAO FPMIS Approvals");

				window.show();
			}
		};
	}
	

}