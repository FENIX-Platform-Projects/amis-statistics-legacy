package org.fao.fenix.web.modules.birt.client.view.report.viewer;

import org.fao.fenix.web.modules.core.client.image.ImageFenixView;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.map.ResourceExplorerMap;
import org.fao.fenix.web.modules.re.client.view.olap.ResourceExplorerOlap;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChooseResourceType {

	Window window;
	VerticalPanel mainCont;
	Button ok;
	//RadioGroup radioGroup;
	RadioButton map;
	RadioButton table;
	RadioButton chart;
	RadioButton text;
	RadioButton comment;
	RadioButton image;
	RadioButton olap;
	
	private SelectionListener<ButtonEvent> openRE(final ReportViewer reportViewer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				if (map.isChecked()){
					new ResourceExplorerMap(reportViewer);
				} else if (table.isChecked()){
//					new ResourceExplorerTable(reportViewer);
					new ResourceExplorerDataset(reportViewer);
				} else if (chart.isChecked()){
					new ResourceExplorerChart(reportViewer);
				} else if (text.isChecked()){
					new ResourceExplorerText(reportViewer);
				} else if (comment.isChecked()){
					new TextEditor(reportViewer, null, null).build();
				} else if (image.isChecked()){
					new ImageFenixView(reportViewer);
				} else if (olap.isChecked()){
					new ResourceExplorerOlap(reportViewer);
//					OlapWindow olapWindow = new OlapWindow();
//					olapWindow.setForReport(true);
//					olapWindow.setReportViewer(reportViewer);
//					olapWindow.build(true);
				}
				
				window.close();
			}
		};
	}
	
	public ChooseResourceType(ReportViewer reportViewer){
		
		window = new Window();
		window.setHeading(BabelFish.print().chooseResource());
		mainCont = new VerticalPanel();
		mainCont.setSpacing(10);
		HTML message = new HTML(BabelFish.print().addResourceToReport());
		HorizontalPanel hr1 = new HorizontalPanel(); 
		hr1.setSpacing(10);
		HorizontalPanel uno = new HorizontalPanel();
		uno.setSpacing(3);
		map = new RadioButton("group");
		map.setChecked(true);
		uno.add(new HTML(BabelFish.print().map() + "&nbsp;&nbsp; :"));
		uno.add(map);
		HorizontalPanel due = new HorizontalPanel();
		due.setSpacing(3);
		table = new RadioButton("group");
		due.add(new HTML(BabelFish.print().table() + " :"));
		due.add(table);
		HorizontalPanel cinque = new HorizontalPanel();
		cinque.setSpacing(3);
		cinque.add(new HTML("Comment :"));
		comment = new RadioButton("group");
		cinque.add(comment);
				
		hr1.add(uno);
		hr1.add(due);
		hr1.add(cinque);
		
		HorizontalPanel hr2 = new HorizontalPanel(); 
		hr2.setSpacing(10);
		HorizontalPanel tre = new HorizontalPanel();
		tre.setSpacing(3);
		tre.add(new HTML(BabelFish.print().chart() + " :"));
		chart = new RadioButton("group");
		tre.add(chart);
		hr2.add(tre);
		HorizontalPanel quattro = new HorizontalPanel();
		quattro.setSpacing(3);
		quattro.add(new HTML("&nbsp;" + BabelFish.print().text() + " :"));
		text = new RadioButton("group");
		quattro.add(text);
		hr2.add(quattro);
		
		HorizontalPanel imageCont = new HorizontalPanel();
		imageCont.setSpacing(3);
		imageCont.add(new HTML("&nbsp;Image :"));
		image = new RadioButton("group");
		imageCont.add(image);
		hr2.add(imageCont);
		
		HorizontalPanel hr3 = new HorizontalPanel(); 
		hr3.setSpacing(10);
		
		HorizontalPanel olapCont = new HorizontalPanel();
		olapCont.setSpacing(3);
		olapCont.add(new HTML("Multidimensional Table: "));
		olap = new RadioButton("group");
		olapCont.add(olap);
		hr3.add(olapCont);
		
		
		
		ok = new Button(BabelFish.print().ok());
		
		ok.addSelectionListener(openRE(reportViewer));
		
		mainCont.add(message);
		mainCont.add(hr1);
		mainCont.add(hr2);
		mainCont.add(hr3);
		HorizontalPanel hrok = new HorizontalPanel();
		hrok.add(ok);
		mainCont.add(hrok);
		mainCont.setCellHorizontalAlignment(hrok, HorizontalPanel.ALIGN_CENTER);
		
		window.add(mainCont);
		window.show();
	}
	
}
