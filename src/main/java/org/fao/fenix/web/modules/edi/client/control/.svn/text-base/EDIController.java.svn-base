package org.fao.fenix.web.modules.edi.client.control;

import org.fao.fenix.web.modules.edi.client.view.EDISettingsPanel;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EDIController {

	public static ChangeHandler connectionType(final EDISettingsPanel p) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent e) {
				connectionTypeAgent(p);
			}
		};
	}
	
	public static void connectionTypeAgent(final EDISettingsPanel p) {
		String t = p.getConnectionTypeList().getValue(p.getConnectionTypeList().getSelectedIndex());
		if (p.getPanel().getWidget(0) != null) {
			VerticalPanel w = (VerticalPanel) p.getPanel().getWidget(0);
			for (int i = w.getWidgetCount() - 1; i >= 0; i--)
				w.remove(w.getWidget(i));
		}
		if (t.equals(EDISettings.JDBC.name())) {
			p.getPanel().add(p.getDatabaseSettingsPanel().buildDatabaseSettingsPanel());
			p.getPanel().getLayout().layout();
		} else if (t.equals(EDISettings.HTTP.name())) {
			p.getPanel().add(p.getHttpSettingsPanel().buildHTTPSettingsPanel());
			p.getPanel().getLayout().layout();
		}
	}

}