package org.fao.fenix.web.modules.fpi.client.control.measurementunit;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.fpi.client.view.measurementunit.MeasurementUnitManager;
import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;
import org.fao.fenix.web.modules.fpi.common.vo.MeasurementUnitVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MeasurementUnitManagerController {

	public static void createMeasurementUnitManager() {
		FpiServiceEntry.getInstance().getAllMU(new AsyncCallback<List<MeasurementUnitVo>>() {
			public void onSuccess(List<MeasurementUnitVo> list) {
				new MeasurementUnitManager().build(list);
			}
			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "createMeasurementUnitManager @ MeasurementUnitManagerController");
			}
		});
	}
	
	public static SelectionListener<ButtonEvent> update(final List<MeasurementUnitVo> list, final VerticalPanel muPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				List<MeasurementUnitVo> toBeUpdated = new ArrayList<MeasurementUnitVo>();
				for (int i = 0 ; i < list.size() ; i++) {
					String id = String.valueOf(list.get(i).getId());
					HorizontalPanel panel = muPanel.getData(id);
					MeasurementUnitVo vo = list.get(i);
					CheckBox visible = (CheckBox)panel.getData("isVisible");
					CheckBox currency = (CheckBox)panel.getData("isCurrency");
					vo.setVisible(visible.getValue());
					vo.setCurrency(currency.getValue());
					toBeUpdated.add(vo);
					//System.out.println(vo.getId() + " - " + vo.getLabel() + " is visible? " + vo.isVisible() + " is currency? " + vo.isCurrency());
				}
				FpiServiceEntry.getInstance().updateMU(toBeUpdated, new AsyncCallback() {
					public void onSuccess(Object result) {
						FenixAlert.info(BabelFish.print().info(), "Measurement units have been updated.");
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "update @ MeasurementUnitManagerController");
					}
				});
			}
		};
	}
	
}
