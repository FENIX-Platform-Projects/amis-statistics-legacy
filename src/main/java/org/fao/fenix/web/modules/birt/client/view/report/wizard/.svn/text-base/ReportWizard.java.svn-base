package org.fao.fenix.web.modules.birt.client.view.report.wizard;

import org.fao.fenix.web.modules.birt.client.control.report.ReportWizardController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportWizard {

	private VerticalPanel mainCont;
	private HorizontalPanel templateActivated;
	private Button ok;
	private Window window;
	
	public Window getWindow() {
		return window;
	}

	public HorizontalPanel getTemplateActivated() {
		return templateActivated;
	}

	public void setTemplateActivated(HorizontalPanel templateActivated) {
		this.templateActivated = templateActivated;
	}

	private HorizontalPanel buildPreview(String color){
		HorizontalPanel preview = new HorizontalPanel();
		preview.setSize("105px", "155px");
		DOM.setStyleAttribute(preview.getElement(), "backgroundColor", color);
		preview.setSpacing(5);
		
		return preview;
	}
	
	public ReportWizard(){
		window = new Window();
		window.setLayout(new BorderLayout());
		window.setHeading(BabelFish.print().reportWizard());
		window.setSize(400, 300);
		mainCont = new VerticalPanel();
		mainCont.setSpacing(10);
		
		// *************** FIRST ROW *******************
		
		HorizontalPanel firstRow=new HorizontalPanel();
		firstRow.setSpacing(10);
		
		Image tBlank=new Image("birt-images/template/blankTemplate.png");
		DOM.setStyleAttribute(tBlank.getElement(), "cursor", "pointer");
		VerticalPanel vpTBlank = new VerticalPanel();
		HorizontalPanel contTBlank=buildPreview("#FF0000");
		//default chart
		setTemplateActivated(contTBlank);
		tBlank.addClickListener(ReportWizardController.activeDisactive(contTBlank, this));
		contTBlank.add(tBlank);
		vpTBlank.add(contTBlank);
		vpTBlank.add(new HTML("<div style='font-weight:bold;'>" + BabelFish.print().blankReport() + "</div>"));
		//vpTBlank.setHorizontalAlign(HorizontalAlignment.CENTER);
		firstRow.add(vpTBlank);
		
		Image t1=new Image("birt-images/template/template1.png");
		DOM.setStyleAttribute(t1.getElement(), "cursor", "pointer");
		VerticalPanel vpT1 = new VerticalPanel();
		HorizontalPanel contT1=buildPreview("#cbc4c4");
		//default chart
		t1.addClickListener(ReportWizardController.activeDisactive(contT1, this));
		contT1.add(t1);
		vpT1.add(contT1);
		vpT1.add(new HTML("<div style='font-weight:bold;'>" + BabelFish.print().template() + "1</div>"));
		//vpT1.setHorizontalAlign(HorizontalAlignment.CENTER);
		firstRow.add(vpT1);
		
		Image t2=new Image("birt-images/template/template2.png");
		DOM.setStyleAttribute(t2.getElement(), "cursor", "pointer");
		VerticalPanel vpT2 = new VerticalPanel();
		HorizontalPanel contT2=buildPreview("#cbc4c4");
		//default chart
		t2.addClickListener(ReportWizardController.activeDisactive(contT2, this));
		contT2.add(t2);
		vpT2.add(contT2);
		vpT2.add(new HTML("<div style='font-weight:bold;'>" + BabelFish.print().template() + "2</div>"));
		//vpT2.setHorizontalAlign(HorizontalAlignment.CENTER);
		firstRow.add(vpT2);
		
		mainCont.add(firstRow);
		
		HorizontalPanel contOk = new HorizontalPanel();
		contOk.setSpacing(10);
		ok = new Button(BabelFish.print().ok());
		ok.addSelectionListener(ReportWizardController.openReportViewer(this));		
		contOk.add(ok);
		
		mainCont.add(contOk);
		
		mainCont.setCellHorizontalAlignment(contOk, HorizontalPanel.ALIGN_CENTER);
		
		window.add(mainCont, new BorderLayoutData(LayoutRegion.CENTER));
		window.show();
	}
	
}
