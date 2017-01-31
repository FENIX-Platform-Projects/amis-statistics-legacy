package org.fao.fenix.web.modules.x.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.user.client.ui.HTML;

public class XExplorerPager {

	private HorizontalPanel pagerPanel;
	
	private IconButton firstButton;
	
	private IconButton lastButton;
	
	private IconButton prevButton;
	
	private IconButton nextButton;
	
	private Button goToPageButton;
	
	private TextField<String> pageNumber;
	
	private HTML totalPagesLabel;
	
	private int firstResultIndex = 0;
	
	private int totalResults = 1000;
	
	private int maxResults = 10;
	
	public XExplorerPager() {
		pagerPanel = new HorizontalPanel();
		pagerPanel.setSpacing(10);
		firstButton = new IconButton("x-btn-text x-tbar-page-first");
		lastButton = new IconButton("x-btn-text x-tbar-page-last");
		prevButton = new IconButton("x-btn-text x-tbar-page-prev");
		nextButton = new IconButton("x-btn-text x-tbar-page-next");
		goToPageButton = new Button("Go To Page");
		pageNumber = new TextField<String>();
		pageNumber.setWidth("20px");
		pageNumber.setValue("1");
		totalPagesLabel = new HTML("");
	}
	
	public HorizontalPanel build() {
		pagerPanel.add(firstButton);
		pagerPanel.add(new SeparatorToolItem());
		pagerPanel.add(prevButton);
		pagerPanel.add(new SeparatorToolItem());
		pagerPanel.add(new HTML("Page"));
		pagerPanel.add(pageNumber);
		pagerPanel.add(totalPagesLabel);
		pagerPanel.add(goToPageButton);
		pagerPanel.add(new SeparatorToolItem());
		pagerPanel.add(nextButton);
		pagerPanel.add(new SeparatorToolItem());
		pagerPanel.add(lastButton);
		return pagerPanel;
	}

	public int getFirstResultIndex() {
		return firstResultIndex;
	}

	public void setFirstResultIndex(int firstResultIndex) {
		this.firstResultIndex = firstResultIndex;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public HorizontalPanel getPagerPanel() {
		return pagerPanel;
	}

	public IconButton getFirstButton() {
		return firstButton;
	}

	public IconButton getLastButton() {
		return lastButton;
	}

	public IconButton getPrevButton() {
		return prevButton;
	}

	public IconButton getNextButton() {
		return nextButton;
	}

	public Button getGoToPageButton() {
		return goToPageButton;
	}

	public TextField<String> getPageNumber() {
		return pageNumber;
	}

	public HTML getTotalPages() {
		return totalPagesLabel;
	}

	public void setPageNumber(TextField<String> pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	
}
