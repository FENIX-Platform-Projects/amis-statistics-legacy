package org.fao.fenix.web.modules.ccbs.client.view;

import org.fao.fenix.web.modules.ccbs.common.services.CCBSServiceEntry;
import org.fao.fenix.web.modules.ccbs.common.vo.Book;
import org.fao.fenix.web.modules.ccbs.common.vo.CCBS;
import org.fao.fenix.web.modules.ccbs.common.vo.Cell;
import org.fao.fenix.web.modules.ccbs.common.vo.CellFormula;
import org.fao.fenix.web.modules.ccbs.common.vo.Page;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CCBSParametersPanel extends VerticalPanel
{
	private BookPanel bookPanel;
	private ListBox   countryListBox   = new ListBox();

	public CCBSParametersPanel(BookPanel bookPanel, final String gaulCodes[], final String gaulNames[])
	{
		this.bookPanel = bookPanel;

		countryListBox.addItem("");
		for (int i = 0; i < gaulNames.length; i++) countryListBox.addItem(gaulNames[i]);

		LayoutContainer lc = new LayoutContainer();
		lc.setLayout(new TableLayout(2));
		lc.add(new Label("Country")); // TODO: should be read from localized strings
		lc.add(countryListBox);
		add(lc);

		countryListBox.addChangeListener(new ChangeListener()
		{
			public void onChange(Widget widget)
			{
				int selIdx = countryListBox.getSelectedIndex();
				if (selIdx > 0)
				{
					String gaulCode = gaulCodes[selIdx - 1];
					String gaulName = gaulNames[selIdx - 1];
					load(gaulCode+"", gaulName);
				}
				else unload();
			}
		});
		setWidth(200);
	}

	private void load(String gaulCode, final String gaulName)
	{
		final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading CCBS data for " + gaulName, "");
		loadingWindow.showLoadingBox();

		CCBSServiceEntry.getInstance().getCCBSData(gaulCode, CCBS.YEARS,
		                                           new AsyncCallback<Book>()
		                                           {
			                                           public void onSuccess(Book result)
			                                           {
				                                           loadingWindow.destroyLoadingBox();
				                                           
				                                           // System.out.println("Book (Client):"); // DEBUG
				                                           // System.out.println(result);    // DEBUG

				                                           try
				                                           {

					                                           Book book = makeClientBook(result);
					                                           for (int pageNum = 0; pageNum < book.numPages(); pageNum++)
					                                           {
						                                           String title = CCBS.COMMODITY_NAMES[pageNum] + " (" + gaulName + ")";
						                                           bookPanel.getPagePanel(pageNum).getDecoration().setTitle(title);
					                                           }
					                                           bookPanel.load(book);
				                                           }
				                                           catch (Exception e)
				                                           {
					                                           e.printStackTrace();
				                                           }
			                                           }

			                                           public void onFailure(Throwable caught)
			                                           {
				                                           loadingWindow.destroyLoadingBox();

				                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
			                                           }
		                                           });
	}

	private void unload()
	{
		bookPanel.unload();
	}

	public static Book makeClientBook(Book dbBook) throws Exception
	{
		// copy pages
		int numRows = CCBS.FIELD_NAMES.length;
		int numCols = dbBook.getPage(0).numCols();

		Book guiBook = new Book(dbBook.numPages() + 2);
		for (int pageNum = 0; pageNum < dbBook.numPages(); pageNum++)
		{
			Page page    = dbBook.getPage(pageNum);
			Page guiPage = new Page(numRows, numCols);
			guiBook.setPage(pageNum, guiPage);
			fillCropPage(guiPage, page);
		}
		// add coarse grains page
		Page coarseGrainsPage = new Page(numRows, numCols);
		guiBook.setPage(dbBook.numPages(), coarseGrainsPage);
		fillCoarseGrainsPage(coarseGrainsPage);

		// add total cereals page
		Page totalCerealsPage = new Page(numRows, numCols);
		guiBook.setPage(dbBook.numPages() + 1, totalCerealsPage);
		fillTotalCerealsPage(totalCerealsPage);

		// update cells
		guiBook.update();
		return guiBook;
	}

	private static void fillCropPage(Page guiPage, Page dbPage) throws Exception
	{
		// copy available data
		copyRow(guiPage,  0, dbPage,  0); // Population
		copyRow(guiPage,  5, dbPage,  1); // Production
		copyRow(guiPage,  6, dbPage,  2); // J/J Imports
		copyRow(guiPage,  7, dbPage,  3); // Imports
		copyRow(guiPage,  8, dbPage,  4); // Commercial Imports
		copyRow(guiPage,  9, dbPage,  5); // Food Aid
		copyRow(guiPage, 13, dbPage,  6); // Food Use
		copyRow(guiPage, 14, dbPage,  7); // Feed Use
		copyRow(guiPage, 15, dbPage,  8); // Other Uses
		copyRow(guiPage, 16, dbPage,  9); // J/J Exports
		copyRow(guiPage, 17, dbPage, 10); // Exports
		copyRow(guiPage, 18, dbPage, 11); // Closing Stocks
		copyRow(guiPage, 19, dbPage, 12); // Of which Government
		copyRow(guiPage, 22, dbPage, 13); // Area Harvested

		// TODO this should be computed for all available years, not only the displayed ones

		// opening stocks = closing stocks of previous year (4 = 18[-2])
		for (int colNum = 2; colNum < dbPage.numCols(); colNum += 2)
			guiPage.getCell(4, colNum)
					.addRef(18, colNum - 2)
					.setFormula(new CellFormula() { public double eval() { return v(0); }})
					.setValid(false);

		for (int colNum = 0; colNum < dbPage.numCols(); colNum += 2)
		{
			// domestic availability = opening stocks + production (3 = 4 + 5)
			guiPage.getCell(3, colNum)
					.addRef(4)
					.addRef(5)
					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
					.setValid(false);

			// total supply = domestic availability + imports (2 = 3 + 7)
			guiPage.getCell(2, colNum)
					.addRef(3)
					.addRef(7)
					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1); }})
					.setValid(false);

			// domestic utilization = food use + feed use + other uses (12 = 13 + 14 + 15)
			guiPage.getCell(12, colNum)
					.addRef(13)
					.addRef(14)
					.addRef(15)
					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
					.setValid(false);

			// total utilization = domestic utilization + exports + closing stocks (11 = 12 + 17 + 18)
			guiPage.getCell(11, colNum)
					.addRef(12)
					.addRef(17)
					.addRef(18)
					.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2); }})
					.setValid(false);

			// per cap. food use = food use / population * 1000 (20 = 13 / 0)
			guiPage.getCell(20, colNum)
					.addRef(13)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);

			// per cap. feed use = feed use / population * 1000 (21 = 14 / 0)
			guiPage.getCell(21, colNum)
					.addRef(14)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);

			// yield = production / area (23 = 5 / 22)
			guiPage.getCell(23, colNum)
					.addRef(5)
					.addRef(22)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
		}
	}

	private static void fillCoarseGrainsPage(Page page)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum += 2)
		{
			page.getCell(0, colNum)         // = Population
				.addRef(0, 0, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0); }})
				.setValid(false);
			                                // 1 Unbalanced
			addCoarseGrainsTotalRow(page,  2); // + Total Supply
			addCoarseGrainsTotalRow(page,  3); // + Domestic Availability
			addCoarseGrainsTotalRow(page,  4); // + Opening Stocks
			addCoarseGrainsTotalRow(page,  5); // + Production
			addCoarseGrainsTotalRow(page,  6); // + J/J Imports
			addCoarseGrainsTotalRow(page,  7); // + Imports
			addCoarseGrainsTotalRow(page,  8); // + Commercial Imports
			addCoarseGrainsTotalRow(page,  9); // + Food Aid
			addCoarseGrainsTotalRow(page, 10); // + Unbalanced Imports
			addCoarseGrainsTotalRow(page, 11); // + Total Utilization
			addCoarseGrainsTotalRow(page, 12); // + Domestic Utilization
			addCoarseGrainsTotalRow(page, 13); // + Food Use
			addCoarseGrainsTotalRow(page, 14); // + Feed Use
			addCoarseGrainsTotalRow(page, 15); // + Other Uses
			addCoarseGrainsTotalRow(page, 16); // + J/J Exports
			addCoarseGrainsTotalRow(page, 17); // + Exports
			addCoarseGrainsTotalRow(page, 18); // + Closing Stocks
			addCoarseGrainsTotalRow(page, 19); // + Of which Government
			page.getCell(20, colNum) // / Per Cap. Food Use
					.addRef(13)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);

			page.getCell(21, colNum) // / Per Cap. Feed Use
					.addRef(14)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
			addTotalRow(page, 22); // + Area Harvested
			page.getCell(23, colNum) // / Yield
					.addRef(5)
					.addRef(22)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
		}
	}

	// TODO: check if this classification is correct
	//   0 wheat
	// x 1 barley
	// x 2 maize
	//   3 rice (paddy)
	//   4 rice (milled)
	// x 5 millet
	// x 6 other cereals
	// x 7 grain sorghum
	// x 8 rye
	// x 9 oats
	private static void addCoarseGrainsTotalRow(Page page, int rowNum)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum+=2)
			page.getCell(rowNum, colNum)
				.addRef(1, rowNum, colNum)
				.addRef(2, rowNum, colNum)
				.addRef(5, rowNum, colNum)
				.addRef(6, rowNum, colNum)
				.addRef(7, rowNum, colNum)
				.addRef(8, rowNum, colNum)
				.addRef(9, rowNum, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2) + v(3) + v(4) + v(5) + v(6); }})
				.setValid(false);
	}

	private static void fillTotalCerealsPage(Page page)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum += 2)
		{
			page.getCell(0, colNum)         // = Population
				.addRef(0, 0, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0); }})
				.setValid(false);
			                                // 1 Unbalanced
			addTotalRow(page,  2); // + Total Supply
			addTotalRow(page,  3); // + Domestic Availability
			addTotalRow(page,  4); // + Opening Stocks
			addTotalRow(page,  5); // + Production
			addTotalRow(page,  6); // + J/J Imports
			addTotalRow(page,  7); // + Imports
			addTotalRow(page,  8); // + Commercial Imports
			addTotalRow(page,  9); // + Food Aid
			addTotalRow(page, 10); // + Unbalanced Imports
			addTotalRow(page, 11); // + Total Utilization
			addTotalRow(page, 12); // + Domestic Utilization
			addTotalRow(page, 13); // + Food Use
			addTotalRow(page, 14); // + Feed Use
			addTotalRow(page, 15); // + Other Uses
			addTotalRow(page, 16); // + J/J Exports
			addTotalRow(page, 17); // + Exports
			addTotalRow(page, 18); // + Closing Stocks
			addTotalRow(page, 19); // + Of which Government
			page.getCell(20, colNum) // / Per Cap. Food Use
					.addRef(13)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);

			page.getCell(21, colNum) // / Per Cap. Feed Use
					.addRef(14)
					.addRef(0)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
			addTotalRow(page, 22); // + Area Harvested
			page.getCell(23, colNum) // / Yield
					.addRef(5)
					.addRef(22)
					.setFormula(new CellFormula() { public double eval() { return v(1) > 0 ? v(0) / v(1) : 0; }})
					.setValid(false);
		}
	}

	private static void addTotalRow(Page page, int rowNum)
	{
		for (int colNum = 0; colNum < page.numCols(); colNum+=2)
			page.getCell(rowNum, colNum)
				.addRef(0, rowNum, colNum)
				.addRef(1, rowNum, colNum)
				.addRef(2, rowNum, colNum)
				.addRef(3, rowNum, colNum)
				.addRef(4, rowNum, colNum)
				.addRef(5, rowNum, colNum)
				.addRef(6, rowNum, colNum)
				.addRef(7, rowNum, colNum)
				.addRef(8, rowNum, colNum)
				.addRef(9, rowNum, colNum)
				.setFormula(new CellFormula() { public double eval() { return v(0) + v(1) + v(2) + v(3) + v(4) + v(5) + v(6) + v(7) + v(8) + v(9); }})
				.setValid(false);
	}

	private static void copyRow(Page toPage, int toRow, Page fromPage, int fromRow)
	{
		for (int colNum = 0; colNum < fromPage.numCols(); colNum += 2)
		{
			Cell fromCell = fromPage.getCell(fromRow, colNum);
			if (fromCell.isNumeric()) toPage.setCell(toRow, colNum, fromCell.getValue());
			else                      toPage.setCell(toRow, colNum, fromCell.getText());
			toPage.setCell(toRow, colNum + 1, fromPage.getCell(fromRow, colNum + 1).getText());
		}
	}
}
