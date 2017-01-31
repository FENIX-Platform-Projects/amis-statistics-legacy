package org.fao.fenix.web.modules.r.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.view.RFunctionPanel;
import org.fao.fenix.web.modules.r.client.view.RFunctionWindow;
import org.fao.fenix.web.modules.r.client.view.RMainVariableFieldSet;
import org.fao.fenix.web.modules.r.client.view.RMenu;
import org.fao.fenix.web.modules.r.client.view.RResultWindow;
import org.fao.fenix.web.modules.r.common.services.RServiceEntry;
import org.fao.fenix.web.modules.r.common.vo.RException;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.r.common.vo.RResultVO;
import org.fao.fenix.web.modules.r.common.vo.RUserSettingsVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class RController {
	
	public static SelectionListener<ButtonEvent> calculate(final RFunctionWindow w, final TableVO tvo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				try {
					final RUserSettingsVO usvo = collectParameters(w, tvo);
					final LoadingWindow l = new LoadingWindow(BabelFish.print().load(), BabelFish.print().loadingMessage(), BabelFish.print().loading());
					RServiceEntry.getInstance().calculate(usvo, new AsyncCallback<RResultVO>() {
						public void onSuccess(RResultVO rvo) {
							l.destroyLoadingBox();
							RResultWindow rrw = new RResultWindow();
							rrw.build(rvo, usvo);
							l.destroyLoadingBox();
						};
						public void onFailure(Throwable e) {
							l.destroyLoadingBox();
							FenixAlert.error("ERROR", e.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				} catch (RException e) {
					FenixAlert.info(BabelFish.print().information(), e.getMessage());
				}
			}
		};
	}
	
	public static String matrix2htmlTable(Object[][] matrix, Map<String, String> mainDimension) {
		System.out.println(matrix.length);
		System.out.println(matrix[0].length);
		String html = "<table border='1' cellpadding='10'>";
		html += "<tr>";
		for (String header : mainDimension.values())
			html += "<td align='center'><b>" + header + "</b></td>";
		html += "</tr>";
		for (int i = 0 ; i < matrix.length ; i++) {
			html += "<tr>";
			for (int j = 0 ; j < matrix[i].length ; j++)  {
				if ((matrix[i][j] != null) && (!matrix[i][j].toString().equals("")))
					html += "<td align='center'>" + matrix[i][j].toString() + "</td>";
				else
					html += "<td align='center'>&nbsp;</td>";
			}
			html += "</tr>";
		}
		html += "</table>";
		return html;
	}
	
	public static String matrix2htmlList(Object[][] matrix, Map<String, String> mainDimension) {
		
//		System.out.println("==================================================================");
//		for (int i = 0 ; i < matrix.length ; i++) {
//			for (int j = 0 ; j < matrix[i].length ; j++)
//				System.out.print("["+i+"]["+j+"]" + matrix[i][j].toString() + " | ");
//			System.out.println();
//		}
//		System.out.println("==================================================================");
		
		String html = "<table border='1' cellpadding='10px'>";
		html += "<tr>";
		for (String header : mainDimension.values())
			html += "<td align='center'><b>" + header + "</b></td>";
		html += "</tr>";
		html += "<tr>";
		for (int j = 0 ; j < matrix[0].length ; j++) {
			if ((matrix[0][j] != null) && (!matrix[0][j].toString().equals("")))
				html += "<td align='center'>" + matrix[0][j].toString() + "</td>";
			else
				html += "<td align='center'>&nbsp;</td>";
		}
		html += "</tr>";
		html += "</table>";
		return html;
	}
	
	public static String matrix2htmlLabel(Object[][] matrix) {
		String html = "<table cellpadding='10'>";
		html += "<tr>";
		html += "<td align='center'>" + matrix[0][0].toString() + "</td>";
		html += "</tr>";
		html += "</table>";
		return html;
	}
	
	public static SelectionListener<ButtonEvent> close(final RFunctionWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().close();
			}
		};
	}

	public static void buildRMenu(final TableWindow tw, final RMenu rMenu) {
		try {
			RServiceEntry.getInstance().parseMenu(new AsyncCallback<List<FenixMenuItemVo>>() {
				public void onSuccess(List<FenixMenuItemVo> vos) {
					for (FenixMenuItemVo vo : vos) {
						if (vo.getParent().equals("")) {
							MenuItem mi = new MenuItem(BabelFish.print().getString(vo.getName()));
							mi.setIconStyle("R");
							mi.setSubMenu(createSubMenu(tw, vos, vo.getName()));
							if (!vo.getCommand().equals(""))
								mi.addSelectionListener(executeCommand(tw, vo.getName(), vo.getCommand()));
							rMenu.getMenu().add(mi);
						}
					}
					
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	public static void buildRMenu(final TableViewWindow tw, final RMenu rMenu) {
		try {
			RServiceEntry.getInstance().parseMenu(new AsyncCallback<List<FenixMenuItemVo>>() {
				public void onSuccess(List<FenixMenuItemVo> vos) {
					for (FenixMenuItemVo vo : vos) {
						if (vo.getParent().equals("")) {
							MenuItem mi = new MenuItem(BabelFish.print().getString(vo.getName()));
							mi.setIconStyle("R");
							mi.setSubMenu(createSubMenu(tw, vos, vo.getName()));
							if (!vo.getCommand().equals(""))
								mi.addSelectionListener(executeCommand(tw, vo.getName(), vo.getCommand()));
							rMenu.getMenu().add(mi);
						}
					}
					
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	private static Menu createSubMenu(TableWindow tw, List<FenixMenuItemVo> vos, String parent) {
		Menu subMenu = new Menu();
		for (FenixMenuItemVo vo : vos) {
			if (vo.getParent().equals(parent)) {
				MenuItem mi = new MenuItem(BabelFish.print().getString(vo.getName()));
				mi.setIconStyle("R");
				if (!vo.getCommand().equals(""))
					mi.addSelectionListener(executeCommand(tw, vo.getName(), vo.getCommand()));
				subMenu.add(mi);
			}
		}
		return subMenu;
	}
	
	private static Menu createSubMenu(TableViewWindow tw, List<FenixMenuItemVo> vos, String parent) {
		Menu subMenu = new Menu();
		for (FenixMenuItemVo vo : vos) {
			if (vo.getParent().equals(parent)) {
				MenuItem mi = new MenuItem(BabelFish.print().getString(vo.getName()));
				mi.setIconStyle("R");
				if (!vo.getCommand().equals(""))
					mi.addSelectionListener(executeCommand(tw, vo.getName(), vo.getCommand()));
				subMenu.add(mi);
			}
		}
		return subMenu;
	}
	
	private static SelectionListener<MenuEvent> executeCommand(final TableWindow tw, final String name, final String command) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				try {
					RServiceEntry.getInstance().parseFunction(command, new AsyncCallback<RFunctionVO>() {
						public void onSuccess(RFunctionVO vo) {
							new RFunctionWindow().build(tw, vo);
						};
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}
	
	private static SelectionListener<MenuEvent> executeCommand(final TableViewWindow tw, final String name, final String command) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				try {
					RServiceEntry.getInstance().parseFunction(command, new AsyncCallback<RFunctionVO>() {
						public void onSuccess(RFunctionVO vo) {
							new RFunctionWindow().build(tw, vo);
						};
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}
	
	public static Listener<BaseEvent> enableOtherVariablesList(final CheckBox cd, final ListBox lb) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (cd.getValue()) {
					lb.setEnabled(true);
					for (int i = 0 ; i < lb.getItemCount() ; i++)
						lb.setItemSelected(i, true);
				} else {
					lb.setEnabled(false);
					for (int i = 0 ; i < lb.getItemCount() ; i++)
						lb.setItemSelected(i, false);
				}
			};
		};
	}
	
	public static ChangeHandler mainVariableChangeListener(final TableVO tvo, final ListBox variableList, final ListBox leftList, final ListBox rightList) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent e) {
				if (variableList.getSelectedIndex() > 0) {
					for (int i = leftList.getItemCount() - 1 ; i >= 0 ; i--)
						leftList.removeItem(i);
					for (int i = rightList.getItemCount() - 1 ; i >= 0 ; i--)
						rightList.removeItem(i);
					DimensionBeanVO dbvo = tvo.getDimensionDescriptorMap().get(variableList.getValue(variableList.getSelectedIndex()));
					for (String code : dbvo.getDistinctDimensionValues().keySet()) 
						leftList.addItem(dbvo.getDistinctDimensionValues().get(code), code);
					for (int i = 0 ; i < leftList.getItemCount() ; i++)
						leftList.setItemSelected(i, true);
				}
			}
		};
	}
	
	public static void createOtherDimensions(TableVO tvo, RFunctionPanel p) {
		for (DimensionBeanVO dbvo : tvo.getDimensionDescriptorMap().values())
			if (!dbvo.getColumnDataType().equalsIgnoreCase("quantity") && (dbvo.getDistinctDimensionValues().size() > 1))
				p.getOtherVariablesWrapper().add(p.buildGroupDimensionPanel(dbvo.getHeader(), dbvo.getColumnDataType(), dbvo.getDistinctDimensionValues()));
	}
	
	public static SelectionListener<ButtonEvent> add(final ListBox left, final ListBox right) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = right.getItemCount() - 1 ; i >= 0 ; i--)
					right.removeItem(i);
				for (int i = 0 ; i < left.getItemCount() ; i++)
					if (left.isItemSelected(i))
						right.addItem(left.getItemText(i), left.getValue(i));
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addAll(final ListBox left, final ListBox right) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = right.getItemCount() - 1 ; i >= 0 ; i--)
					right.removeItem(i);
				for (int i = 0 ; i < left.getItemCount() ; i++)
					right.addItem(left.getItemText(i), left.getValue(i));
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> remove(final ListBox right) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = right.getItemCount() - 1 ; i >= 0 ; i--)
					if (right.isItemSelected(i))
						right.removeItem(i);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> removeAll(final ListBox right) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = right.getItemCount() - 1 ; i >= 0 ; i--)
					right.removeItem(i);
			}
		};
	}
	
	public static RUserSettingsVO collectParameters(RFunctionWindow w, TableVO tvo) throws RException {
		
		RUserSettingsVO vo = new RUserSettingsVO();
		RFunctionPanel rFunctionPanel = w.getrFunctionPanel();
		RMainVariableFieldSet rMainVariableFieldSet = rFunctionPanel.getrMainVariableFieldSet();
		
		vo.setDatasetID(tvo.getDatasetID());
		
		Map<String, String> mainDimension = new HashMap<String, String>();
		ListBox variableList = rMainVariableFieldSet.getVariableList();
		String datatype = tvo.getDimensionDescriptorMap().get(variableList.getValue(variableList.getSelectedIndex())).getColumnDataType();
		mainDimension.put(variableList.getItemText(variableList.getSelectedIndex()), datatype);
		vo.setMainDimension(mainDimension);
		
		Map<String, String> mainDimensionValues = new HashMap<String, String>();
		ListBox rightList = rMainVariableFieldSet.getRightList();
		if (rightList.getItemCount() == 0)
			throw new RException(BabelFish.print().pleaseSelectAtLeastOneVariableOfInterest());
		for (int i = 0 ; i < rightList.getItemCount() ; i++)
			mainDimensionValues.put(rightList.getValue(i), rightList.getItemText(i));
		vo.setMainDimensionValues(mainDimensionValues);
		
		Map<String, Map<String, String>> otherDimensionsValues = new HashMap<String, Map<String,String>>();
		Map<String, String> otherDimensions = new HashMap<String, String>();
		List<VerticalPanel> otherDimensionsList = rFunctionPanel.getOtherDimensions();
		for (VerticalPanel vp : otherDimensionsList) {
			CheckBox cb = (CheckBox)vp.getData("CHECKBOX");
			if (cb.getValue()) {
				String header = (String)vp.getData("HEADER");
				String dataType = (String)vp.getData("DATATYPE");
				otherDimensions.put(header, dataType);
				ListBox valuesList = (ListBox)vp.getData("LISTBOX");
				Map<String, String> valuesMap = new HashMap<String, String>();
				for (int i = 0 ; i < valuesList.getItemCount() ; i++) 
					if (valuesList.isItemSelected(i)) 
						valuesMap.put(valuesList.getValue(i), valuesList.getItemText(i));
				otherDimensionsValues.put(header, valuesMap);
			}
		}
		vo.setOtherDimensions(otherDimensions);
		vo.setOtherDimensionsValues(otherDimensionsValues);
		
		Map<String, String> options = new HashMap<String, String>();
		List<HorizontalPanel> optionsList = rFunctionPanel.getOptions();
		for (HorizontalPanel hp : optionsList) {
			CheckBox cb = (CheckBox)hp.getData("CHECKBOX");
			if (cb.getValue()) {
				String optionName = (String)hp.getData("OPTIONNAME");
				String optionValue = "";
				Boolean isList = (Boolean)hp.getData("ISLIST");
				if (isList) {
					ListBox values = (ListBox)hp.getData("LISTBOX");
					optionValue = values.getItemText(values.getSelectedIndex());
				} else {
					TextField<String> textfield = (TextField<String>)hp.getData("TEXTFIELD");
					optionValue = textfield.getValue();
				}
				options.put(optionName, optionValue);
			}
		}
		vo.setOptions(options);
		
		vo.setrFunctionVO(w.getrFunctionVO());
		
		return vo;
	}
	
}
