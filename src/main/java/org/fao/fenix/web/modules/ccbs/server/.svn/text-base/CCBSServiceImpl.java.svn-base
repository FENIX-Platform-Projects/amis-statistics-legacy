package org.fao.fenix.web.modules.ccbs.server;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.ccbs.common.services.CCBSService;
import org.fao.fenix.web.modules.ccbs.common.vo.Book;
import org.fao.fenix.web.modules.ccbs.common.vo.CCBS;
import org.fao.fenix.web.modules.ccbs.common.vo.Page;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CCBSServiceImpl extends RemoteServiceServlet implements CCBSService
{
	//private LocalDBAdapter dbAdapter = new LocalDBAdapter();
	private DBAdapter dbAdapter; // = new LocalDBAdapter();

	public CCBSServiceImpl()
	{
//		System.out.println("CCBSServiceImpl()"); // DEBUG
//
//		try
//		{
//			dbAdapter.init();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}

	public Integer populateCCBS()
	{
		try
		{
			// TODO: replace with real code
			return 0;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

	public Book getCCBSData(String featureCode, int years[])
	{
		// System.out.println("CCBSServiceImpl.getCCBSData(" + featureCode + ", " + years + ")"); // DEBUG

		try
		{
			Book book = new Book(CCBS.COMMODITY_CODES.length);

			// prepare map from years to position
			Map<Integer, Integer> year2Idx = new HashMap<Integer, Integer>();

			// System.out.println("years.length: " + years.length); // DEBUG

			for (int i = 0; i < years.length; i++)
			{
				// System.out.println("year2Idx: " + years[i] + " --> " + i * 2); // DEBUG

				year2Idx.put(years[i], i * 2);
			}
			// fill result book
			CCBS.Codes codes[] = CCBS.Codes.values();
			for (int pageNum = 0; pageNum < CCBS.COMMODITY_CODES.length; pageNum++)
			{
				Page page = new Page(codes.length, years.length * 2);
				book.setPage(pageNum, page);
				String commodityCode = CCBS.COMMODITY_CODES[pageNum];

				for (int rowNum = 0; rowNum < codes.length; rowNum++)
				{
					TimeSeries ts = dbAdapter.select(codes[rowNum], featureCode, commodityCode);
					fillRow(ts, year2Idx, page, rowNum);
				}
			}
			// System.out.println("Book (Server):"); // DEBUG
			// System.out.println(book);    // DEBUG

			return book;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public String[] getCCBSGaulCodes()
	{
		return dbAdapter.getCCBSGaulCodes();
	}

	public String[] getGaulNames(String[] gaulCodes)
	{
		return dbAdapter.getGaulNames(gaulCodes);
	}

	private void fillRow(TimeSeries ts, Map<Integer, Integer> year2Idx, Page page, int rowNum)
	{
		Calendar cal = new GregorianCalendar();
		for (TimeSeries.Element el: ts.elements())
		{
			cal.setTime(el.getDate());
			int year = cal.get(Calendar.YEAR);

			// System.out.println("year = " + year); // DEBUG

			Integer colNum = year2Idx.get(year);

			// System.out.println("colNum = " + colNum); // DEBUG

			if (colNum != null)
			{
				page.setCell(rowNum, colNum,     el.getValue());
				page.setCell(rowNum, colNum + 1, el.getNote());
			}
		}
	}

	public void setDbAdapter(DBAdapter dbAdapter) {
		this.dbAdapter = dbAdapter;
	}
}
