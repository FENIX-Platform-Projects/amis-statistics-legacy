package org.fao.fenix.web.modules.table.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class TablePager {

	private HorizontalPanel panel;

	public Button start;

	public Button end;

	public Button back;

	public Button forward;

	public Button goToPage;

	public Button refresh;

	public HTML pageInfo;
	public HTML totalPagesInfo;

	private TextBox pageBox;

	private Integer actualPage;

	private Integer totalRows;

	public HorizontalPanel build(Long resourceId) {
		panel = new HorizontalPanel();
		buildInterface();
		format();
		return panel;
	}
	
	public void buildInterface() {
		start = new Button("|<");
		end = new Button(">|");
		back = new Button("<");
		forward = new Button(">");
		goToPage = new Button(BabelFish.print().goToPage());
		pageInfo = new HTML(BabelFish.print().page() + " x / X ");
		pageBox = new TextBox();
		pageBox.setText("1");
		panel.add(start);
		panel.add(back);
		panel.add(pageInfo);
		panel.add(goToPage);
		panel.add(pageBox);
		panel.add(forward);
		panel.add(end);
	}
	
	public void buildNewInterface() {
		start = new Button("|<");
		end = new Button(">|");
		back = new Button("<");
		forward = new Button(">");
		goToPage = new Button(BabelFish.print().goToPage());
		refresh = new Button(BabelFish.print().refresh());
		totalPagesInfo = new HTML(" of Z");
		pageInfo = new HTML("Displaying x - X of X");
		pageBox = new TextBox();
		pageBox.setText("1");
		panel.add(start);
		panel.add(back);
		panel.add(goToPage);
		panel.add(pageBox);
		panel.add(totalPagesInfo);
		panel.add(forward);
		panel.add(end);
		panel.add(refresh);
		panel.add(pageInfo);

	}

	public void format() {
		start.setWidth("15px");
		end.setWidth("15px");
		pageInfo.setWidth("75px");
		goToPage.setWidth("75px");
		pageBox.setWidth("25px");
		forward.setWidth("15px");
		end.setWidth("15px");
		panel.setSpacing(10);
		//panel.setSize(TableConstants.PAGER_WIDTH, TableConstants.PAGER_HEIGHT);
	}


	public void newInterfaceFormat() {
		start.setWidth("15px");
		end.setWidth("15px");
		pageInfo.setWidth("300px");
		goToPage.setWidth("75px");
		totalPagesInfo.setWidth("25px");
		pageBox.setWidth("25px");
		forward.setWidth("15px");
		end.setWidth("15px");
		panel.setSpacing(10);
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
	}


	public HorizontalPanel getPanel() {
		return panel;
	}

	public void setPanel(HorizontalPanel panel) {
		this.panel = panel;
	}

	public Button getStart() {
		return start;
	}

	public void setStart(Button start) {
		this.start = start;
	}

	public Button getEnd() {
		return end;
	}

	public void setEnd(Button end) {
		this.end = end;
	}

	public Button getBack() {
		return back;
	}

	public void setBack(Button back) {
		this.back = back;
	}

	public Button getForward() {
		return forward;
	}

	public void setForward(Button forward) {
		this.forward = forward;
	}

	public void setRefresh(Button refresh) {
		this.refresh = refresh;
	}

	public Button getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(Button goToPage) {
		this.goToPage = goToPage;
	}

	public HTML getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(HTML pageInfo) {
		this.pageInfo = pageInfo;
	}

	public TextBox getPageBox() {
		return pageBox;
	}

	public void setPageBox(TextBox pageBox) {
		this.pageBox = pageBox;
	}

	public Integer getActualPage() {
		return actualPage;
	}

	public void setActualPage(Integer actualPage) {
		this.actualPage = actualPage;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Button getRefresh() {
		return refresh;
	}

}
