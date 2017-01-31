package org.fao.fenix.web.modules.birt.client.view.report.viewer;


import org.fao.fenix.web.modules.birt.client.control.report.ReportViewerController;
import org.fao.fenix.web.modules.birt.client.view.report.util.SelectTemplate;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SideBar extends ContentPanel{
	
	
	private VerticalPanel mainCont;
	private ReportViewer reportViewer;
	private DataList listResource;
	
	public DataList getListResource() {
		return listResource;
	}

	public void deleteResource(String resourceType, String resourceId){
		if (resourceType.equals("map")){
			reportViewer.getReportVo().getMapIdList().remove(Long.valueOf(resourceId));
		} else if (resourceType.equals("chart")){
			reportViewer.getReportVo().getChartIdList().remove(Long.valueOf(resourceId));
		} else if (resourceType.equals("table")){
			reportViewer.getReportVo().getTableIdList().remove(Long.valueOf(resourceId));
		} else if (resourceType.equals("text")){
			reportViewer.getReportVo().getTextIdList().remove(Long.valueOf(resourceId));
		}
	}
	
	@SuppressWarnings("deprecation")
	private void initializeTemplate1(DataList listResource){
		DataListItem newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("mapIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("chartIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("tableIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("textResourceIcon");
		listResource.add(newItem);
	}
	
	@SuppressWarnings("deprecation")
	private void initializeTemplate2(DataList listResource){
		DataListItem newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("mapIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("chartIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("tableIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("mapIcon");
		listResource.add(newItem);
		newItem = new DataListItem();
		newItem.setText("( " + BabelFish.print().empty() + ")");
		newItem.setIconStyle("textResourceIcon");
		listResource.add(newItem);
	}
	@SuppressWarnings("deprecation")
	public void addElementToListResource(String id, String name, String typeResource, int objectPosition) {
		DataListItem newItem = new DataListItem();  
		System.out.println("id: " + id + " | name: " + name + " | typeResource: " + typeResource);
		newItem.setText(name);
		newItem.setItemId(id);
		if (typeResource.equals("map")){
			newItem.setIconStyle("mapIcon");
			reportViewer.getReportVo().addsetMapIdList(Long.valueOf(id));
		} else if (typeResource.equals("chart")){
			newItem.setIconStyle("chartIcon");
			reportViewer.getReportVo().addsetChartIdList(Long.valueOf(id));
		} else if (typeResource.equals("text")){
			newItem.setIconStyle("textResourceIcon");
			reportViewer.getReportVo().addsetTextIdList(Long.valueOf(id));
		} else if (typeResource.equals("table")){
			newItem.setIconStyle("tableIcon");
			reportViewer.getReportVo().addsetTableIdList(Long.valueOf(id));
		} else if (typeResource.equals("comment")){
			newItem.setIconStyle("noteIcon");
		}
		if (objectPosition == -1){
			listResource.add(newItem);
		} else{
			listResource.getItem((objectPosition-1)).setText(name);
			listResource.getItem((objectPosition-1)).setItemId(id);
		}
		
	}

	private Menu buildMenu(){
		Menu menu = new Menu();
		
		MenuItem delete = new MenuItem();  
		delete.setText(BabelFish.print().delete());  
		delete.setIconStyle("deleteObj");  
		delete.addSelectionListener(ReportViewerController.deleteObject(reportViewer));
		menu.add(delete);
		
		MenuItem moveUp = new MenuItem();  
		moveUp.setText(BabelFish.print().moveUp());  
		moveUp.setIconStyle("arrow-up");  
		moveUp.addSelectionListener(ReportViewerController.moveObject(reportViewer, "up"));
		menu.add(moveUp);
		
		MenuItem moveDown = new MenuItem();  
		moveDown.setText(BabelFish.print().moveDown());  
		moveDown.setIconStyle("arrow-down");  
		moveDown.addSelectionListener(ReportViewerController.moveObject(reportViewer, "down"));
		menu.add(moveDown);
		
		MenuItem space = new MenuItem();  
		space.setText(BabelFish.print().space());  
		space.addSelectionListener(ReportViewerController.addSeparator(reportViewer, "br"));
		menu.add(space);
		
		MenuItem hr = new MenuItem();  
		hr.setText(BabelFish.print().horizontalBar());  
		hr.addSelectionListener(ReportViewerController.addSeparator(reportViewer, "hr"));
		menu.add(hr);
		
		MenuItem modifyComment = new MenuItem();  
		modifyComment.setText("modify comment");  
		modifyComment.addSelectionListener(ReportViewerController.modifyComment(reportViewer));
		menu.add(modifyComment);
		
		return menu;
	}
	

	public SideBar(ReportViewer reportViewer){
		listResource = new DataList();
		listResource.setBorders(false);
		listResource.setWidth(200);
		if (reportViewer.getReportViewid() == 0L){
			if (reportViewer.getKeyTemplate().equals("template1")){
				initializeTemplate1(listResource);
			} else if (reportViewer.getKeyTemplate().equals("template2")){
				initializeTemplate2(listResource);
			}
		}
		setBodyBorder(false);
		setHeaderVisible(false);
		//setAutoHeight(true);
		setAutoWidth(true);
		this.reportViewer=reportViewer;
	}

	
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		mainCont = new VerticalPanel();
		mainCont.setSpacing(10);
		
		final VerticalPanel template = new VerticalPanel();
		template.setSpacing(5);
		template.add(new HTML("<div style='color:#FF0000; font-weight:bold;'>" + BabelFish.print().template()+ ":</div>"));
		
		if (reportViewer.getReportViewid() == 0L){
			template.add(SelectTemplate.getClassTemplate(reportViewer.getKeyTemplate(), reportViewer));
		} else {
			BirtServiceEntry.getInstance().getClassTemplate(reportViewer.getReportViewid(), new AsyncCallback<String>(){
				
				public void onSuccess(String keyTemplate){
					reportViewer.setKeyTemplate(keyTemplate);
					template.add(SelectTemplate.getClassTemplate(reportViewer.getKeyTemplate(), reportViewer));
					if (reportViewer.getKeyTemplate().equals("blankTemplate")){
						listResource.setContextMenu(buildMenu());
					}
				}
				
				public void onFailure(Throwable caught) {
					Info.display("getClassTemplate", caught.getLocalizedMessage());
				}
				
			});
		}
		
		mainCont.add(template);
		mainCont.add(new HTML("<hr>"));
		
		VerticalPanel objectCont = new VerticalPanel();
		objectCont.setSpacing(5);
		objectCont.add(new HTML("<div style='color:#FF0000; font-weight:bold;'>" + BabelFish.print().resourceList() + ":</div>"));
		objectCont.add(listResource);
		mainCont.add(objectCont);
		
		if (reportViewer.getKeyTemplate() != null){
			if (reportViewer.getKeyTemplate().equals("blankTemplate")){
				listResource.setContextMenu(buildMenu());
			}
		}
		
		
		add(mainCont);
		//setSize(200, 325);
	}
	
	

}
