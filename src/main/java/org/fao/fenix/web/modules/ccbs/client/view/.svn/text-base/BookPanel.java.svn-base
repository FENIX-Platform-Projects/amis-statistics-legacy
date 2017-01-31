package org.fao.fenix.web.modules.ccbs.client.view;

import org.fao.fenix.web.modules.ccbs.common.vo.Book;
import org.fao.fenix.web.modules.ccbs.common.vo.Page;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class BookPanel extends TabPanel implements Listener<TabPanelEvent>
{
	private PagePanel pagePanels[];

	public BookPanel(String pageNames[], PageDecoration pageDecorations[])
	{
		pagePanels = new PagePanel[pageNames.length];

		setResizeTabs(true);
		setAnimScroll(true);
		setTabPosition(TabPosition.BOTTOM);

		for (int pageNum = 0; pageNum < pageNames.length; pageNum++)
		{
			TabItem item = new TabItem();
			item.setText(pageNames[pageNum]);
			PagePanel pagePanel = new PagePanel(pageDecorations[pageNum]);
			pagePanels[pageNum] = pagePanel;
			item.setLayout(new FitLayout());
			item.add(pagePanel);
			add(item);
		}
		addListener(Events.Select, this);
	}

	public void load(Book book) throws Exception
	{
		for (int pageNum = 0; pageNum < book.numPages(); pageNum++)
		{
			Page page = book.getPage(pageNum);
			pagePanels[pageNum].load(page);
		}
	}

	public void unload()
	{
		for (PagePanel pagePanel : pagePanels) pagePanel.unload();
	}

	public PagePanel getPagePanel(int pageNum) { return pagePanels[pageNum]; }

	public void handleEvent(TabPanelEvent event)
	{
		TabItem item = event.getItem();
		PagePanel pagePanel = (PagePanel)item.getItem(0);
		pagePanel.updateView();
	}
}
