package org.fao.fenix.web.modules.birt.client.control.report;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.client.view.report.wizard.ReportWizard;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ReportWizardController {

	
	public static ClickListener activeDisactive(final HorizontalPanel cont, final ReportWizard reportWizard) {
		return new ClickListener() {
			public void onClick(Widget wid) {
				// if the user doesn't click on the chart type already selected
				// I change the chart type
				if (cont != reportWizard.getTemplateActivated()) {
					DOM.setStyleAttribute(reportWizard.getTemplateActivated().getElement(), "backgroundColor", "#cbc4c4");
					DOM.setStyleAttribute(cont.getElement(), "backgroundColor", "#FF0000");
					reportWizard.setTemplateActivated(cont);
					
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> openReportViewer(final ReportWizard reportWizard) {
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				String URLTemplateImage = ((Image) reportWizard.getTemplateActivated().getWidget(0)).getUrl();
				int index=0;
				for (int i = (URLTemplateImage.length()-1); i > 0; i--){
					if (URLTemplateImage.charAt(i)=='/'){
						index = i;
						break;
					}
				}
				index++;
				
				String template = URLTemplateImage.substring(index, (URLTemplateImage.length()-4)); 
				reportWizard.getWindow().close();
				new ReportViewer(template);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addToReport(final ReportViewer reportViewer, final TableWindow tableWindow, final String datasetTitle, final String datasetId) {
		
		return new SelectionListener<ButtonEvent>() {
			
			public void componentSelected(ButtonEvent ce) {
				
				String tableHTML = createTableHTML(tableWindow);
				
				final String datasetCode = datasetId + String.valueOf((int)(Math.random() * 100));
				
				BirtServiceEntry.getInstance().addTableToReport(datasetCode, datasetId, reportViewer.getRptDesign(), reportViewer.getObjectPosition(), reportViewer.getKeyTemplate(), tableHTML, new AsyncCallback<String>(){
					
					public void onSuccess(String s) {
				
//						if ((reportViewer.getKeyTemplate().equals("template1") || reportViewer.getKeyTemplate().equals("template2")) && !reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText().equals("( " + I18N.print().empty() + ")")){
//							reportViewer.getSideBar().deleteResource("table", reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getItemId());
//						}
						
						// TODO add table ID and Name to the side bar
						reportViewer.getSideBar().addElementToListResource(datasetCode, datasetTitle, "table", reportViewer.getObjectPosition());
						
						reportViewer.getReport().setHTML(s);
						tableWindow.getWindow().close();
					}
					
					public void onFailure(Throwable caught) {
						Info.display("Service call failed!", "Service call to {0} failed", "createReport()");
					}
					
				});
				
			}
		};
	}
	
	private static String createTableHTML(TableWindow tableWindow) {
		
		// initiate variables
		StringBuilder sb = new StringBuilder();
		EditorGrid datasetGrid = tableWindow.getDatasetGrid().getEditorGrid();
		ListStore<DatasetRowModel> store = datasetGrid.getStore();
		
		// styles
		String div_pair = "<div style='font-size: 8pt; font-family: sans-serif; background-color: #D0DDED;'>";
		String div_odds = "<div style='font-size: 8pt; font-family: sans-serif; background-color: #C0C0C0;'>";
		String div_head = "<div style='font-size: 10pt; font-family: sans-serif; background-color: #D0DDED; font-weight: bold;'>"; // #1D4589
		
		sb.append("<table width='100%' border='0' cellspacing='0'>");
		
		// add column headers
		sb.append("<tr>");
		for (int i = 2 ; i < datasetGrid.getColumnModel().getColumnCount() ; i++)
			if (!datasetGrid.getColumnModel().getColumn(i).isHidden())
				sb.append("<td>").append(div_head).append(datasetGrid.getColumnModel().getColumnHeader(i)).append("</div></td>");
		sb.append("</tr>");
		
		for (int i = 0 ; i < store.getCount() ; i++) {
			DatasetRowModel model = store.getAt(i);
			sb.append("<tr>");
			for (int j = 1 ; j < model.getProperties().size() ; j++) {
				if (!datasetGrid.getColumnModel().getColumn(j + 1).isHidden()) {
					String code = "column" + j;
					if (i % 2 != 0)
						sb.append("<td>").append(div_pair).append(model.getProperties().get(code)).append("</div></td>");
					else
						sb.append("<td>").append(div_odds).append(model.getProperties().get(code)).append("</div></td>");
				}
			}
			sb.append("</tr>");
		}
		
		
		// return result
		sb.append("</table>");
		return sb.toString();
	}

}
