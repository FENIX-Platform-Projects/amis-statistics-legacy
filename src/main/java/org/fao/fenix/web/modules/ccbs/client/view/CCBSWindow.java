package org.fao.fenix.web.modules.ccbs.client.view;

import org.fao.fenix.web.modules.ccbs.common.services.CCBSServiceEntry;
import org.fao.fenix.web.modules.ccbs.common.vo.CCBS;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class CCBSWindow extends FenixWindow
{
	protected BookPanel           bookPanel;
	protected CCBSParametersPanel ccbsParametersPanel;
	protected String              gaulCodes[], gaulNames[];
	
	public void build()
	{
		System.out.println("Class: CCBSWindow Function:build Text: Build Start");
		setSize(1000, 400);
		setTitle(BabelFish.print().ccbsTool());
		getWindow().setCollapsible(true);
		getWindow().setHeading(BabelFish.print().ccbsTool());

		buildToolbar();
		buildCenterPanel();
		loadGaulCodes();
	}

	private void loadGaulCodes()
	{
		CCBSServiceEntry.getInstance().getCCBSGaulCodes(new AsyncCallback<String[]>()
		                                           {
			                                           public void onSuccess(String result[])
			                                           {
				                                           gaulCodes = result;
				                                           loadGaulNames();
			                                           }

			                                           public void onFailure(Throwable caught)
			                                           {
				                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSGaulCodes()");
			                                           }
		                                           });
	}

	private void loadGaulNames()
	{
		CCBSServiceEntry.getInstance().getGaulNames(gaulCodes, new AsyncCallback<String[]>()
		                                           {
			                                           public void onSuccess(String result[])
			                                           {
				                                           gaulNames = result;
				                                           continueBuild();
			                                           }

			                                           public void onFailure(Throwable caught)
			                                           {
				                                           FenixAlert.alert("RPC Failed", "CCBSService.getGaulNames()");
			                                           }
		                                           });
	}

	private void continueBuild()
	{
		buildWestPanel();
		setSize(1000, 400); // redo now, otherwise it is not rendered correctly
	}

	protected void buildToolbar()
	{
		// TODO CCBSToolbar extending FenixToolbar
	}

	protected void buildWestPanel()
	{
		ccbsParametersPanel = new CCBSParametersPanel(bookPanel, gaulCodes, gaulNames);
		fillWestPart(ccbsParametersPanel);
		getWestData().setSize(250);
		getWest().setHeading(BabelFish.print().parameters()); // TODO: improve title
	}

	protected void buildCenterPanel()
	{
		PageDecoration pageDecorations[] = new PageDecoration[CCBS.COMMODITY_NAMES.length];
		for (int numPage = 0; numPage < CCBS.COMMODITY_NAMES.length; numPage++)
		{
			String  colHeaders[]       = new String[CCBS.YEARS.length * 2];
			int     colHeadersWidths[] = new int[CCBS.YEARS.length * 2];
			boolean editableCols[]     = new boolean[CCBS.YEARS.length * 2];
			for (int i = 0; i < CCBS.YEARS.length; i++)
			{
				int colNum = i * 2;
				colHeaders[colNum]           = CCBS.YEARS[i]+"";
				colHeadersWidths[colNum]     = 80;
				editableCols[colNum]         = numPage < CCBS.COMMODITY_NAMES.length - 2; // skip last 2 pages;
				colHeaders[colNum + 1]       = "";
				colHeadersWidths[colNum + 1] = 20;
				editableCols[colNum + 1]     = false;
			}
			pageDecorations[numPage] = new PageDecoration("",
			                                              CCBS.FIELD_NAMES, 120,              CCBS.FIELD_EDITABLE,
			                                              colHeaders,       colHeadersWidths, editableCols);
		}
		bookPanel = new BookPanel(CCBS.COMMODITY_NAMES, pageDecorations);
		fillCenterPart(bookPanel);

		getCenter().setHeading(BabelFish.print().table()); // TODO: improve title
	}
}
