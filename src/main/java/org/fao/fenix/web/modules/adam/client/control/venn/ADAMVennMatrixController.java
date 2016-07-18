package org.fao.fenix.web.modules.adam.client.control.venn;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennChannelMatrixController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennRecipientMatrixController;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennMatrix;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class ADAMVennMatrixController extends ADAMController {
	
	public static Listener<BaseEvent> showGovernmentStatedPriorities(final ADAMVennMatrix vennMatrix, final ADAMResultVO rvo, final CheckBox showCPF, final CheckBox showStatedPriorities) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("showGovernmentStatedPriorities: showCPF value" + showCPF.getValue());
				System.out.println("showGovernmentStatedPriorities: showStatedPriorities value" + showStatedPriorities.getValue());
				ADAMVennRecipientMatrixController.buildVennMatrixRecipientReloadAgent(vennMatrix, rvo, showCPF.getValue(),  showStatedPriorities.getValue());
				vennMatrix.getPanel().layout();
			}
		};
	}
	
	
	public static Listener<BaseEvent> showFAOAgreedStatedPriorities(final ADAMVennMatrix vennMatrix, final ADAMResultVO rvo, final CheckBox showCPF, final CheckBox showStatedPriorities) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("showFAOAgreedStatedPriorities: showCPF value" + showCPF.getValue());
				System.out.println("showFAOAgreedStatedPriorities: showStatedPriorities value" + showStatedPriorities.getValue());
				ADAMVennChannelMatrixController.buildVennMatrixChannelReloadAgent(vennMatrix, rvo, showCPF.getValue(), showStatedPriorities.getValue());
				vennMatrix.getPanel().layout();
			}
		};
	}
	
	
	
	
	
	
}

