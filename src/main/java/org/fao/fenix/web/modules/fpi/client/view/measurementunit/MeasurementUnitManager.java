package org.fao.fenix.web.modules.fpi.client.view.measurementunit;

import java.util.List;

import org.fao.fenix.web.modules.fpi.client.control.measurementunit.MeasurementUnitManagerController;
import org.fao.fenix.web.modules.fpi.common.vo.MeasurementUnitVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.ui.HTML;

public class MeasurementUnitManager extends FenixWindow {

	public VerticalPanel panel;
	public VerticalPanel muPanel;
	public Button button;
	
	public MeasurementUnitManager() {
		
		panel = new VerticalPanel();
		muPanel = new VerticalPanel();
		button = new Button(BabelFish.print().save());
		
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		
		format();
	}
	
	public void format() {
		panel.add(muPanel);
		panel.add(button);
		muPanel.setSize("575px", "165px");
		muPanel.setScrollMode(Scroll.AUTO);
		muPanel.setSpacing(10);
		getWindow().setSize("600px", "225px");
		getWindow().setHeading(BabelFish.print().measurementUnit());
		getCenter().setHeaderVisible(false);
	}
	
	public void build(List<MeasurementUnitVo> list) {
		for (int i = 0 ; i < list.size() ; i++) {
			HorizontalPanel mu = muPanel(list.get(i)); 
			muPanel.add(mu);
			muPanel.setData(String.valueOf(list.get(i).getId()), mu);
		}
		button.addSelectionListener(MeasurementUnitManagerController.update(list, muPanel));
		show();
	}
	
	public HorizontalPanel muPanel(MeasurementUnitVo vo) {
		HorizontalPanel muPanel = new HorizontalPanel();
		HTML label = new HTML(vo.getSymbol());
		label.setWidth("75px");
		CheckBox currency = new CheckBox();
		CheckBox visible = new CheckBox();
		currency.setValue(vo.isCurrency());
		currency.setBoxLabel(BabelFish.print().isCurrency());
		currency.setWidth("150px");
		visible.setValue(vo.isVisible());
		visible.setBoxLabel(BabelFish.print().isVisible());
		visible.setWidth("150px");
		muPanel.add(label);
		muPanel.add(currency);
		muPanel.add(visible);
		muPanel.setSpacing(5);
		muPanel.setWidth("400px");
		muPanel.setData("isVisible", visible);
		muPanel.setData("isCurrency", currency);
		return muPanel;
	}
	
}
