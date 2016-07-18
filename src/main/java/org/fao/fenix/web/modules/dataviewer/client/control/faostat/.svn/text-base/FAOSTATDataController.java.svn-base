package org.fao.fenix.web.modules.dataviewer.client.control.faostat;

import java.util.List;

import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CONSTANT;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;




import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FAOSTATDataController {
	
	public static void fillGrid(final Grid<DWCodesModelData> g, String cs, String domainCode) {
		try {

			DataViewerServiceEntry.getInstance().getCodes(cs, domainCode, FAOSTATConstants.faostatLanguage, new AsyncCallback<List<DWCodesModelData>>() {
				public void onSuccess(List<DWCodesModelData> ms) {
					g.getStore().removeAll();
					for (DWCodesModelData m : ms) {
						g.getStore().add(m);
					}
				}
				public void onFailure(Throwable E2) {
//					FENIXAlert.error(FAOSTATlanguage.print().error(), E2.getMessage());
				}
			});
		} catch (Exception E1) {
//			FENIXAlert.error(FAOSTATlanguage.print().error(), E1.getMessage());
		}
	}
	
	public static Listener<BaseEvent> gridSelectionChangedListener(final Grid<DWCodesModelData> toBeFilled, final String cs, final Grid<DWCodesModelData> filter) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				GWT.runAsync(new RunAsyncCallback() {
					public void onSuccess() {
						fillGridWithFilter(toBeFilled, cs, filter);
					}
					public void onFailure(Throwable e) {
//						FENIXAlert.error(FAOSTATlanguage.print().error(), e.getMessage());
					}
				});
			}
		};
	}
	
	
	public static Listener<BaseEvent> oracleListener(final Grid<DWCodesModelData> g, final TextField<String> t) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				GWT.runAsync(new RunAsyncCallback() {
					public void onSuccess() {
						g.getStore().filter("label", t.getValue());
					}
					public void onFailure(Throwable e) {
//						FENIXAlert.error(FAOSTATLanguage.print().error(), e.getMessage());
					}
				});
			}
		};
	}
	

	
	public static Listener<BaseEvent> multipleGridSelectionChangedListener(final List<Grid<DWCodesModelData>> toBeFilleds, final String[] css, final Grid<DWCodesModelData> filter) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				GWT.runAsync(new RunAsyncCallback() {
					public void onSuccess() {
						for (int i = 0 ; i < toBeFilleds.size() ; i++) {
							CONSTANT k = CONSTANT.valueOf(css[i].toUpperCase());
							switch (k) {
								case COUNTRY: fillGridWithFilter(toBeFilleds.get(i), css[i], filter); break;
								case ITEM: fillGridWithFilter(toBeFilleds.get(i), css[i], filter); break;
								case ELEMENT: fillGridWithFilter(toBeFilleds.get(i), css[i], filter); break;
							}
						}
					}
					public void onFailure(Throwable e) {
//						FENIXAlert.error(FAOSTATlanguage.print().error(), e.getMessage());
					}
				});
			}
		};
	}
	
	public static void fillGridWithFilter(final Grid<DWCodesModelData> toBeFilled, String cs, Grid<DWCodesModelData> filter) {
		try {
			String domainCode = getSelectedCode(filter);

			DataViewerServiceEntry.getInstance().getCodes(cs, domainCode, FAOSTATConstants.faostatLanguage, new AsyncCallback<List<DWCodesModelData>>() {
				public void onSuccess(List<DWCodesModelData> ms) {
					toBeFilled.getStore().removeAll();
					for (DWCodesModelData m : ms) {
						toBeFilled.getStore().add(m);
					}
				}
				public void onFailure(Throwable E2) {
//					FENIXAlert.error(FAOSTATlanguage.print().error(), E2.getMessage());
				}
			});
		} catch (Exception E1) {
//			FENIXAlert.error(FAOSTATlanguage.print().error(), E1.getMessage());
		}
	}
	
	public static void fillGridWithFilter(final Grid<DWCodesModelData> toBeFilled, String cs, String domainCode) {
		try {

			DataViewerServiceEntry.getInstance().getCodes(cs, domainCode, FAOSTATConstants.faostatLanguage, new AsyncCallback<List<DWCodesModelData>>() {
				public void onSuccess(List<DWCodesModelData> ms) {
					toBeFilled.getStore().removeAll();
					for (DWCodesModelData m : ms) {
						toBeFilled.getStore().add(m);
					}
				}
				public void onFailure(Throwable E2) {
//					FENIXAlert.error(FAOSTATlanguage.print().error(), E2.getMessage());
				}
			});
		} catch (Exception E1) {
//			FENIXAlert.error(FAOSTATlanguage.print().error(), E1.getMessage());
		}
	}
	
	public static String getSelectedCode(ComboBox<DWCodesModelData> l) {
		String c = null;
		for (int i = 0 ; i < l.getSelection().size() ; i++)
			c = l.getSelection().get(i).getCode();
		return c;
	}
	
	
	
	public static String getSelectedCode(Grid<DWCodesModelData> g) {
		String c = null;
		for (int i = 0 ; i < g.getSelectionModel().getSelectedItems().size() ; i++)
			c = g.getSelectionModel().getSelectedItems().get(i).getCode();
		return c;
	}

}
