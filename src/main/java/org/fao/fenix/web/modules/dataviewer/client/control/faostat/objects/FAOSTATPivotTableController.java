package org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker.FAOSTATBoxMakerPivotTable;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FAOSTATPivotTableController {
	
public static void addPivotTable(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final String width, final String height) {
		
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));
		panel.layout();

		final String title2 = qvo.getTitle();
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				public void onSuccess(DWFAOSTATResultVO rvo) {
					String title = rvo.getTitle();
					System.out.println("qvo.getTitle() : " + qvo.getTitle() );
					if ( title == null ) {
						title = title2;
					}
					
					System.out.println("rvo.getTitle()" + rvo.getTitle() );
					System.out.println("title : " + title );
					System.out.println("ROWS: " + rvo.getRows());
					System.out.println("qvo.getLimit() : " + qvo.getLimit() );
					Integer rows =  rvo.getRows().intValue();
					if ( qvo.getLimit() != null ) {
						if ( rows >= qvo.getLimit()) {
							title = title + " [" + FAOSTATLanguage.print().tableSampleMaxRows() + " "  + qvo.getLimit() + "]";
						}
					}
					qvo.setTitle(title);
					rvo.setTitle(title);
					
					panel.removeAll();	
	
					panel.setWidth(width);
					panel.add(FAOSTATBoxMakerPivotTable.build(qvo, rvo, width, height));
					panel.layout();
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
