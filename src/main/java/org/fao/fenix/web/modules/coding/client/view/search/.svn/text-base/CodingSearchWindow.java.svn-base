package org.fao.fenix.web.modules.coding.client.view.search;

import org.fao.fenix.web.modules.coding.client.view.toolbar.CodingToolbar;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class CodingSearchWindow extends FenixWindow {
	
	CodingSearchMenu codingSearchMenu;
	CodingSearchResults codingSearchResults;
	CodingToolbar codingToolbar;
		
	public void build() {
		setCenterProperties();
		getCenter().add(buildMenuPanel());
		addCenterPartToWindow();
		format();
		show();
	}
	
	public void build(ListStore<DimensionItemModel> listStore, ComboBox<DimensionItemModel> combo,  CodingSystemVo codingSystemVo) {
		setCenterProperties();
		getCenter().add(buildMenuPanel(listStore, combo, codingSystemVo));
		addCenterPartToWindow();
		format();
		show();
	}
	
	private VerticalPanel buildMenuPanel() {
		codingSearchMenu = new CodingSearchMenu();
		VerticalPanel vPanel = codingSearchMenu.build(this);
		return vPanel;
	}
	
	private VerticalPanel buildMenuPanel(ListStore<DimensionItemModel> listStore, ComboBox<DimensionItemModel> combo,  CodingSystemVo codingSystemVo) {
		codingSearchMenu = new CodingSearchMenu();
		VerticalPanel vPanel = codingSearchMenu.build(this,listStore, combo, codingSystemVo);
		return vPanel;
	}
	

	protected void format() {
		setSize(460, 260);
		getCenter().setHeaderVisible(false);
		setTitle("<b> " + BabelFish.print().codingFindConvert() + " </b>");

	}
}