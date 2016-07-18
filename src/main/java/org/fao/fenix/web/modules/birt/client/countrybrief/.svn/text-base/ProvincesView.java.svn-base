package org.fao.fenix.web.modules.birt.client.countrybrief;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProvincesView {
	
	Window window;
	VerticalPanel mainCont;
	DataList list;
	Button ok;
	ReportView reportView;
	
	public DataList getList() {
		return list;
	}

	public ProvincesView(ReportView reportView){
		window = new Window();
		this.reportView = reportView;
		
		window.setSize(240, 350);
		//window.setScrollMode(Scroll.AUTO);
		window.setHeading("Provinces for " + reportView.getCountryLabel());
		
		mainCont = new VerticalPanel();
		mainCont.setSpacing(10);
		
		list = new DataList();
		list.setSelectionMode(SelectionMode.MULTI);
		list.setBorders(false);  
		list.setWidth(190);
		list.setHeight(250);
		list.setScrollMode(Scroll.AUTO);
		
		HorizontalPanel contOk = new HorizontalPanel();
		ok = new Button(BabelFish.print().ok());
		contOk.add(ok);
		
		RainfallServiceEntry.getInstance().findAllGaul1FromGaul0(reportView.getCountryCode(), new AsyncCallback<List<CodeVo>>(){
			
			public void onSuccess(List<CodeVo> result) {

				DataListItem item;
				for (CodeVo element : result){
					item = new DataListItem();
					item.setText(element.getLabel());
					item.setId(element.getCode());
					item.setIconStyle("mapAddLayer");
					item.setChecked(true);
					list.add(item);
				}
			}

			public void onFailure(Throwable caught) {
				Info.display("findAllGaul1FromGaul0", caught.getLocalizedMessage());
			}
			
		});
		
		
		
		mainCont.add(list);
		mainCont.add(contOk);
		mainCont.setCellHorizontalAlignment(contOk, HorizontalPanel.ALIGN_CENTER);
		window.add(mainCont);
		window.show();
		
	}

}
