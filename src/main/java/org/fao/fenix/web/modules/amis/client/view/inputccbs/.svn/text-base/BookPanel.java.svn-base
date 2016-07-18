package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import org.fao.fenix.web.modules.amis.common.vo.Book;
import org.fao.fenix.web.modules.amis.common.vo.Page;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class BookPanel extends TabPanel implements Listener<TabPanelEvent>
{
	private PagePanel pagePanels[];

//	public BookPanel(String pageNames[], PageDecoration pageDecorations[])
//	{
//		pagePanels = new PagePanel[pageNames.length];
//
//		setResizeTabs(true);
//		setAnimScroll(true);
//		setTabPosition(TabPosition.BOTTOM);
//
//		for (int pageNum = 0; pageNum < pageNames.length; pageNum++)
//		{
//			TabItem item = new TabItem();
//			item.setText(pageNames[pageNum]);
//			PagePanel pagePanel = new PagePanel(pageDecorations[pageNum]);
//			pagePanels[pageNum] = pagePanel;
//			item.setLayout(new FitLayout());
//			item.add(pagePanel);
//			add(item);
//		}
//		addListener(Events.Select, this);
//	}
	
	//public BookPanel(String pageNames[], PageDecoration pageDecorations[])
	public BookPanel(PageDecoration pageDecorations[])
	{
		pagePanels = new PagePanel[1];

		setResizeTabs(true);
		//setAnimScroll(true);
		setAnimScroll(false);
		setTabPosition(TabPosition.BOTTOM);

		for (int pageNum = 0; pageNum < 1; pageNum++)
		{
			TabItem item = new TabItem();
			//item.setText(pageNames[pageNum]);
			item.setText(pageDecorations[pageNum].getCommodity());
			PagePanel pagePanel = new PagePanel(pageDecorations[pageNum]);
			pagePanels[pageNum] = pagePanel;
			item.setLayout(new FitLayout());
			item.add(pagePanel);
			add(item);
		}
		
		addListener(Events.Select, this);
	}
	
//	public BookPanel(PageDecoration pageDecorations[])
//	{
//		pagePanels = new PagePanel[1];
//
//		//setResizeTabs(true);
//		//setAnimScroll(true);
//		//setTabPosition(TabPosition.BOTTOM);
//
//		for (int pageNum = 0; pageNum < 1; pageNum++)
//		{
//			//TabItem item = new TabItem();
//			//item.setText(pageNames[pageNum]);
//			PagePanel pagePanel = new PagePanel(pageDecorations[pageNum]);
//			pagePanels[pageNum] = pagePanel;
//			//item.setLayout(new FitLayout());
//			//item.add(pagePanel);
//			add(pagePanel);
//			pagePanel.updateView();
//			//getLayout().layout();
//		}
//	//	addListener(Events.Select, this);
//		//addListener(Events.Select, this);
//	}

//	public void load(Book book) throws Exception
//	{
//		for (int pageNum = 0; pageNum < book.numPages(); pageNum++)
//		{
//			Page page = book.getPage(pageNum);
//			pagePanels[pageNum].load(page);
//		}
//	}

	public void load(Book book) throws Exception
	{
		for (int pageNum = 0; pageNum < 1; pageNum++)
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
//	public void handleEvent(TabPanelEvent event)
//	{
//		TabItem item = event.getItem();
//		PagePanel pagePanel = (PagePanel)item.getItem(0);
//		pagePanel.updateView();
//	}
}
