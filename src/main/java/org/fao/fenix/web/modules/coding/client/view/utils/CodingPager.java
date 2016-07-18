package org.fao.fenix.web.modules.coding.client.view.utils;

import java.util.List;

import org.fao.fenix.web.modules.coding.client.control.search.CodingPagerController;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorMenu;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class CodingPager {

	private HorizontalPanel panel;

	private Button start;
	private Button end;
	private Button back;
	private Button forward;
	private Button goToPage;
	private HTML pageInfo;
	private TextBox pageBox;
	private Integer actualPage;
	private Integer totalRows;
	private Integer totalPages;
	private List<CodeVo> codesVo;
	private String type;

	public HorizontalPanel build(CodingSearchResults window) {
		panel = new HorizontalPanel();
		buildInterface();
		addListeners(window);
		format();
		return panel;
	}
	
	public HorizontalPanel build(DcmtCodingCreatorMenu window) {
		panel = new HorizontalPanel();
		buildInterface();
		addListeners(window);
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
	
	public void addListeners(CodingSearchResults window) {
		start.addSelectionListener(CodingPagerController.start(window));
		forward.addSelectionListener(CodingPagerController.next(window));
		back.addSelectionListener(CodingPagerController.previous(window));
		end.addSelectionListener(CodingPagerController.end(window));
		goToPage.addSelectionListener(CodingPagerController.goToPage(window));
	}
	
	public void addListeners(DcmtCodingCreatorMenu window) {
	//	start.addSelectionListener(CodingPagerController.start(window));
	//	forward.addSelectionListener(CodingPagerController.next(window));
	//	back.addSelectionListener(CodingPagerController.previous(window));
	//	end.addSelectionListener(CodingPagerController.end(window));
	//	goToPage.addSelectionListener(CodingPagerController.goToPage(window));
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
		setTotalPages();
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		this.totalPages = (getTotalRows() / TableConstants.ITEMS_PER_PAGE )+ 1;
	}

	public List<CodeVo> getCodesVo() {
		return codesVo;
	}

	public void setCodesVo(List<CodeVo> codesVo) {
		this.codesVo = codesVo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
