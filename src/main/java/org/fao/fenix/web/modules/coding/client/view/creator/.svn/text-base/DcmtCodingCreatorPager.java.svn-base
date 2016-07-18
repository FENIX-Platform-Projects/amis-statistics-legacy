package org.fao.fenix.web.modules.coding.client.view.creator;

import org.fao.fenix.web.modules.coding.client.control.creator.DcmtCodingCreatorController;
import org.fao.fenix.web.modules.table.client.view.TablePager;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class DcmtCodingCreatorPager extends TablePager {

	private String sortingType;
	private int columnIndex;
	
	public HorizontalPanel build(DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		this.setPanel(new HorizontalPanel());
		buildInterface();
		initalizeSorting();
		addListeners(dcmtCodingCreatorMenu);
		format();
		
		return this.getPanel();
	}
	
	public void buildInterface() {
		super.buildInterface();
	}
	
	public void initalizeSorting() {
		setSortingType("ASC");
		setColumnIndex(0);
	}
	
	public void addListeners(DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		this.getStart().addSelectionListener(DcmtCodingCreatorController.start(dcmtCodingCreatorMenu));
		this.getForward().addSelectionListener(DcmtCodingCreatorController.next(dcmtCodingCreatorMenu));
		this.getBack().addSelectionListener(DcmtCodingCreatorController.previous(dcmtCodingCreatorMenu));
		this.getEnd().addSelectionListener(DcmtCodingCreatorController.end(dcmtCodingCreatorMenu));
		this.getGoToPage().addSelectionListener(DcmtCodingCreatorController.goToPage(dcmtCodingCreatorMenu));	
	}
	
	public void setSorting(int columnIndex) {
		if (columnIndex ==  getColumnIndex()) {
			if ( getSortingType().equals("ASC")) 
				setSortingType("DESC");
			else
				setSortingType("ASC");
		}
		else {
			setSortingType("ASC");
			setColumnIndex(columnIndex);
		}
	}

	
	public String getSortingType() {
		return sortingType;
	}
	public void setSortingType(String sortingType) {
		this.sortingType = sortingType;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
}
