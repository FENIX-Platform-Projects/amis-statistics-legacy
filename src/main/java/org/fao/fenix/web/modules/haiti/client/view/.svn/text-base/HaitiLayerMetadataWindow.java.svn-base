package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;

public class HaitiLayerMetadataWindow extends FenixWindow {

	private final String WIDTH = "275px";
	
	private final String HEIGHT = "400px";
	
	public HaitiLayerMetadataWindow(LayerVO vo, String language) {
		buildCenterPanel(vo, language);
		getWindow().setBorders(false);
		getWindow().setBodyBorder(false);
		getWindow().setSize(WIDTH, HEIGHT);
		getWindow().setHeading(HaitiLangEntry.getInstance(language).metadata());
		show();
	}
	
	private void buildCenterPanel(LayerVO vo, String language) {
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setSpacing(10);
		wrapper.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).title() + "</b>"));
		if (!vo.getLayerTitle().equals(""))
			wrapper.add(new HTML(vo.getLayerTitle()));
		else
			wrapper.add(new HTML("n.a."));
		wrapper.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).source() + "</b>"));
		if (vo.getSource() != null && !vo.getSource().equals(""))
			wrapper.add(new HTML(vo.getSource()));
		else
			wrapper.add(new HTML("n.a."));
		wrapper.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).contact() + "</b>"));
		if (vo.getSourceContact() != null && !vo.getSourceContact().equals(""))
			wrapper.add(new HTML(vo.getSourceContact()));
		else
			wrapper.add(new HTML("n.a."));
		wrapper.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).provider() + "</b>"));
		
		if (vo.getProvider() != null && !vo.getProvider().equals(""))
			wrapper.add(new HTML(String.valueOf(vo.getProvider())));
		else
			wrapper.add(new HTML("n.a."));
		wrapper.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).contact() + "</b>"));
		
		if(vo.getProviderContact() != null && !vo.getProviderContact().equals("")) 
			wrapper.add(new HTML(vo.getProviderContact()));
		else
			wrapper.add(new HTML("n.a."));
		
		wrapper.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).abstractAbstract() + "</b>"));
		if (vo.getAbstractAbstract() != null && !vo.getAbstractAbstract().equals(""))
			wrapper.add(new HTML(vo.getAbstractAbstract()));
		else
			wrapper.add(new HTML("n.a."));
		setCenterProperties();
		getCenter().add(wrapper);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
}
