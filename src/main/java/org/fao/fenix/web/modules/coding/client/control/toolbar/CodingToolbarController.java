package org.fao.fenix.web.modules.coding.client.control.toolbar;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CodingToolbarController {

	
	public static SelectionListener<IconButtonEvent> buildExportToSelectionListener(final CodingSearchResults codingSearchResults, final String typeExport) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
		
			
				List<CodeVo> codesVo = codingSearchResults.getCodingPager().getCodesVo();
				List<List<String>> data = new ArrayList<List<String>>();
				List<String> headers = new ArrayList<String>();
				List<String> row = new ArrayList<String>(); 
				
				
				for(int i=0; i< codingSearchResults.getTable().getColumnCount(); i++) {
					try {
						headers.add(codingSearchResults.getTable().getColumn(i).getText());
						System.out.println(codingSearchResults.getTable().getColumn(i).getText());
					}
					catch (NullPointerException e) {
						System.out.println("null pointer");
					}
					
				}
				data.add(headers);
			
			
				
				System.out.println("START CODES");
				for(int i=0; i < codesVo.size(); i++){
					if (codingSearchResults.getCodingPager().getType().equals("ALL")) { 
						row.add(codesVo.get(i).getTitle() + ", " + codesVo.get(i).getRegion()); 
						System.out.println(row.get(0));
					}
					row.add(codesVo.get(i).getCode());
					row.add(codesVo.get(i).getLabel());
					row.add(codesVo.get(i).getDescription());
					
					data.add(row);
					row = new ArrayList<String>(); 
				}

				System.out.println("END");
				final LoadingWindow loadingWindow = new LoadingWindow();
				loadingWindow.create();
				BirtServiceEntry.getInstance().exportTable(data, typeExport, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						Window.open(result, "_blank", "status=no");
						loadingWindow.destroy();
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "exportTable @ CodingToolbarController");
					}
				});
							
			}
		};
	}
}
