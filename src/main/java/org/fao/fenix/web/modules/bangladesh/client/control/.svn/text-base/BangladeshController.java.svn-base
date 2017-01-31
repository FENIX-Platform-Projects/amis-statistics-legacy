package org.fao.fenix.web.modules.bangladesh.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.bangladesh.client.view.BangladeshWindow;
import org.fao.fenix.web.modules.bangladesh.common.services.BangladeshServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.rednels.ofcgwt.client.ChartWidget;

public class BangladeshController {
	
	private static Timer timer;

//	private static LoadingWindow loading;
	
	public static SelectionListener<ButtonEvent> createReport(final BangladeshWindow bangladeshWindow, final TextField<Integer> issue, final DateField date) {
		return new SelectionListener<ButtonEvent>() {		
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow(BabelFish.print().creatingReport(), BabelFish.print().retrievingData(), BabelFish.print().loading());
				loading.showLoadingBox();
				String stringIssue = "Issue No. " + String.valueOf(issue.getValue());
//				createChartImages(bangladeshWindow, stringIssue, date.getValue());
				List<String> chartsFileNames = new ArrayList<String>();
				BangladeshServiceEntry.getInstance().createReport(chartsFileNames, stringIssue, date.getValue(), new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loading.destroyLoadingBox();
//						Window.open("../bangladeshReports/"+ result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
						
						Window window = new Window();
						ContentPanel cont = new ContentPanel();
						cont.setHeaderVisible(false);
						cont.setLayout(new FitLayout());
						window.setLayout(new FitLayout());
						window.setSize(800, 600);
						HTML content = new HTML(result);
						cont.add(content);
						window.add(cont);
						window.show();
						

					}
					public void onFailure(Throwable arg0) {	
						loading.destroyLoadingBox();
					}
				});
				
			}
		};
	}
	
	
	public static void destroy() {
		if (timer != null)
		timer.cancel();
	}
//	}
}
