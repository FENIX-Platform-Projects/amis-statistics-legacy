package org.fao.fenix.web.modules.fpi.client.control;

import java.util.List;

import org.fao.fenix.web.modules.fpi.client.view.GAULCodeSelector;
import org.fao.fenix.web.modules.fpi.client.view.HSCodeSelector;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FPIController {

	public static SelectionListener<ButtonEvent> buildSelectParameterListener(final String parameter, final HorizontalPanel parameterPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if (parameter.equals(BabelFish.print().gaulCode()))
					selectGaulCode(parameterPanel);
				if (parameter.equals(BabelFish.print().commodityLabel()))
					selectCommodityCode(parameterPanel);
			}
		};
	}
	
	protected static void selectGaulCode(HorizontalPanel parameterPanel) {
		GAULCodeSelector selector = new GAULCodeSelector();
		selector.build(parameterPanel);
	}
	
	protected static void selectCommodityCode(HorizontalPanel parameterPanel) {
		HSCodeSelector selector = new HSCodeSelector();
		selector.build(parameterPanel);
	}
	
	public static void buildAverageValueChangeListener(final HorizontalPanel monthPanel, final List<HorizontalPanel> weekList) {
		for (int i = 0; i < weekList.size(); i++) {
			final TextBox text = (TextBox) weekList.get(i).getData("value");
			text.addChangeListener(new ChangeListener() {
				public void onChange(Widget sender) {
					double total = 0;
					for (int i = 0; i < weekList.size(); i++) {
						final TextBox text = (TextBox) weekList.get(i).getData("value");
						if (text.getText() != null && !text.getText().equals(""))
							total += Double.parseDouble(text.getText());
					}
					double average = total / weekList.size();
					((TextBox)monthPanel.getData("average")).setText(String.valueOf(average));
				}
			});
		}
	}

}