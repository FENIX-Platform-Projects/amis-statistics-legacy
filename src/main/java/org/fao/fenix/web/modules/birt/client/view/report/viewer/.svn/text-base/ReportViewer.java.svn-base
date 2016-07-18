package org.fao.fenix.web.modules.birt.client.view.report.viewer;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.util.SelectTemplate;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.birt.common.vo.ReportVo;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class ReportViewer extends FenixWindow {

	private String keyTemplate;
	private HTML report;
	private String rptDesign;
	private Long reportViewid=0L;
	private String nameReport="";
	private SideBar sideBar;
	private ReportVo reportVo = new ReportVo();
	private ReportToolbar reportTB;
	private int objectPosition = -1;
	
	

	public ReportViewer(Long repViewId){
		this.setReportViewid(repViewId);
		build();
	}
	
	public ReportViewer(String keyTemplate){
		reportVo.setTemplate(keyTemplate);
		setKeyTemplate(keyTemplate);
		
		//kind of report
		/*
		if (SelectTemplate.getNameTemplate(keyTemplate).equals("blank")){
			setReportType(TemplateType.BLANK_TEMPLATE);
		} else {
			setReportType(TemplateType.FULL_TEMPLATE);
		}
		*/
		build();
	}
	
	private void openReport(String rptDesign){
		int index=0;
		for (int i=(rptDesign.length()-1); i > 0; i--){
			if (rptDesign.charAt(i)=='.'){
				index = i;
				break;
			}
		}
		BirtServiceEntry.getInstance().openReport(rptDesign.substring(0, index), "frameset", new AsyncCallback<String>(){
			public void onSuccess(String iFrame){
				report.setHTML(iFrame);
			}
		
			public void onFailure(Throwable caught) {
				Info.display("Service call failed!", "Service call to {0} failed", "openReport()");
			}
			
		});
	}
	
	public void showReport(){
		BirtServiceEntry.getInstance().nameFileById(this.getReportViewid(), new AsyncCallback<String>(){
			
			public void onSuccess(final String rptdesign){
				System.out.println("showing report");
				/** this one should be done afterwards **/
//				setRptDesign(rptdesign);	
				
				BirtServiceEntry.getInstance().getReport(Long.valueOf(getReportViewid()), getReportVo(), rptdesign, new AsyncCallback<List<List<String>>>(){					
					public void onSuccess(List<List<String>> reportInfo){
						for (int i = 0 ; i <=  reportInfo.size() -2 ; i++)
							getSideBar().addElementToListResource(reportInfo.get(i).get(1), reportInfo.get(i).get(2), reportInfo.get(i).get(0), -1);
						
						setRptDesign(reportInfo.get( reportInfo.size() -1).get(0));
						openReport(rptdesign);
					}
					
					public void onFailure(Throwable caught) {
						Info.display("getReport", caught.getLocalizedMessage());
					}
				});
				
			
			}
			
			public void onFailure(Throwable caught) {
				Info.display("nameFileById", caught.getLocalizedMessage());
			}
			
		});
	}
	
	public void showTemplate(){
		BirtServiceEntry.getInstance().getTemplate(SelectTemplate.getNameTemplate(getKeyTemplate()), new AsyncCallback<String>(){
			
			public void onSuccess(String rptdesign){
				setRptDesign(rptdesign);
				openReport(rptdesign);
			}
			
			public void onFailure(Throwable caught) {
				Info.display("Service call failed!", "Service call to {0} failed", "getTemplate()");
			}
			
		});
	}
	
	public void build(){
		report=new HTML();
		sideBar = new SideBar(this);
		
		setTitle("Reports");
		setSize(700, 500);
		setMaximizable(true);
		setCollapsible(true);
		
		//getWestData().setSize(100);
		fillWestPart(sideBar);
		setCenterProperties();
		getCenter().setHeaderVisible(false);
		
		if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()){
			reportTB=new ReportToolbar(this);
			getCenter().setTopComponent(reportTB.getToolBar());
		}
		
		getCenter().add(getReport());
		addCenterPartToWindow();
		
		if (this.getReportViewid() == 0L){
			showTemplate();
		} else {
			showReport();
		}
		
				
		show();
	
	}
	
	
	
	public int getObjectPosition() {
		return objectPosition;
	}

	public void setObjectPosition(int objectPosition) {
		this.objectPosition = objectPosition;
	}

	public ReportVo getReportVo() {
		return reportVo;
	}

	public void setReportVo(ReportVo reportVo) {
		this.reportVo = reportVo;
	}

	public SideBar getSideBar() {
		return sideBar;
	}

	public String getKeyTemplate() {
		return keyTemplate;
	}

	public void setKeyTemplate(String keyTemplate) {
		this.keyTemplate = keyTemplate;
	}

	public HTML getReport() {
		return report;
	}

	public void setReport(HTML report) {
		this.report = report;
	}
	
	public String getRptDesign() {
		return rptDesign;
	}

	public void setRptDesign(String rptDesign) {
		this.rptDesign = rptDesign;
	}

	public Long getReportViewid() {
		return reportViewid;
	}

	public void setReportViewid(Long reportViewid) {
		this.reportViewid = reportViewid;
	}

	public String getNameReport() {
		return nameReport;
	}

	public void setNameReport(String nameReport) {
		this.nameReport = nameReport;
	}
}
