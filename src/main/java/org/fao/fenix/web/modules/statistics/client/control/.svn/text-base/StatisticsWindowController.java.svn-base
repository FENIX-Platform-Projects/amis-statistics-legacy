package org.fao.fenix.web.modules.statistics.client.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.statistics.client.view.StatisticsWindow;
import org.fao.fenix.web.modules.statistics.common.services.StatisticsServiceEntry;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StatisticsWindowController {

	private static int index = 0;
	private static String syntex = "<font color='#FFFFFF'> "
		+ "Input:<br><br>&nbsp;&nbsp;&nbsp;"
		+ "mac_input"
		+ "<br><br>"
		+ "Output:"+" on mac_date"+"<br><br>&nbsp;&nbsp;&nbsp;"
		+ "mac_output" + "<br><br>"
		+ "<hr>" + "</font>";
	public static List<String> historyList = new Vector<String>();
	public static Map<String,String> consoleMap = new HashMap<String,String>();

	public static Listener<BaseEvent> addSyntax(final StatisticsWindow w) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				try {

					if (!w.getStatisticsPanel().getCmdSyntaxComboBox()
							.getSelection().isEmpty()) {
						String selSyn = w.getStatisticsPanel()
								.getCmdSyntaxComboBox().getSelection().get(0)
								.getGaulCode();
						
						if (selSyn == null || selSyn.trim().length() == 0)
							return;
						
						String old = w.getStatisticsPanel().getInput()
								.getValue();
						if (old == null || old.trim().length() == 0)
							w.getStatisticsPanel().getInput().setValue(selSyn);
						else
							w.getStatisticsPanel().getInput().setValue(
									old + " " + selSyn);
					}

					// String hist = historyList.get(index);
					// w.getStatisticsPanel().getInput().setValue(hist);
				} catch (Exception e) {
					// w.getStatisticsPanel().getInput().setValue(
					// "no history mac!!!");
				}
			};
		};
	}

	public static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(
				new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));
		return out;
	}

	public static SelectionListener<ButtonEvent> execute(
			final StatisticsWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				final String input = collectInput(w);

				if (input == null)
					return;

				final LoadingWindow l = new LoadingWindow(BabelFish.print()
						.info(), "Executing...", BabelFish.print().pleaseWait());

				try {
					StatisticsServiceEntry.getInstance().executeCommandLine(
							input, new AsyncCallback<String>() {
								public void onSuccess(String output) {
									// 3 - refresh table
									// FenixAlert.info(BabelFish.print().info(),
									// "There are c:" + vos.size() + " vos");
									l.destroyLoadingBox();

									String inp = input.trim();
									if (historyList.size() == 0
											|| !historyList.contains(inp))
										historyList.add(inp);

									
									//FenixAlert.error(BabelFish.print().error(), "fdgdf 247:"+syntex+" consoleMap"+consoleMap);

									String print = new String(syntex);
									print = print
									.replaceAll("mac_date", new Date()+"");
									print = print
											.replaceAll("mac_input", input);
									print = print.replaceAll("mac_output",
											output);

									w.getStatisticsPanel().getResult().setHTML(
											w.getStatisticsPanel().getResult()
													.getHTML()
													+ print);

									w.getStatisticsPanel().getInput().setValue(
											"");
									w.getStatisticsPanel().getTopPanel()
											.getLayout().layout();
									l.destroyLoadingBox();
								}

								public void onFailure(Throwable E2) {
									l.destroyLoadingBox();
									FenixAlert.error(BabelFish.print().error(),
											E2.getMessage());
									l.destroyLoadingBox();
								}
							});
				} catch (FenixGWTException E1) {
					l.destroyLoadingBox();
					FenixAlert
							.error(BabelFish.print().error(), E1.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> history(
			final StatisticsWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				try {
					index++;
					if (index >= historyList.size())
						index = 0;

					String hist = historyList.get(index);
					w.getStatisticsPanel().getInput().setValue(hist);
				} catch (Exception e) {
					w.getStatisticsPanel().getInput().setValue(
							"no history mac!!!");
				}
			}
		};
	}

	public static String collectInput(StatisticsWindow w) {
		String vo = w.getStatisticsPanel().getInput().getValue();
		if (vo == null || vo.trim().length() == 0)
			return null;
		return vo;
	}
	
	public static void loadConsoleMapStore(final StatisticsWindow w) {
		try {
			StatisticsServiceEntry.getInstance().getMapConsole(
					new AsyncCallback<Map<String, String>>() {
						public void onSuccess(Map<String, String> output) {
							consoleMap=new HashMap<String, String>(output);
							syntex=consoleMap.get("result_console");
							
						}

						public void onFailure(Throwable E2) {
							FenixAlert.error(BabelFish.print().error(), E2
									.getMessage());
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void fillCmdSyntaxStore(final StatisticsWindow w) {
		try {
			StatisticsServiceEntry.getInstance().getMapManual(
					new AsyncCallback<Map<String, String>>() {
						public void onSuccess(Map<String, String> output) {
							TreeMap<String, String> sorted = sortByKeys(output);
							
							for (String key : sorted.keySet())
								w.getStatisticsPanel().getCmdSyntaxStore()
										.add(
												new GaulModelData(key.trim(), sorted
														.get(key).trim()));

						}

						public void onFailure(Throwable E2) {
							FenixAlert.error(BabelFish.print().error(), E2
									.getMessage());
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}