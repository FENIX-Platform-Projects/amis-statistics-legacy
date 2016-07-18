package org.fao.fenix.web.modules.haiti.client.view;

import java.util.List;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class HaitiHotLinkWindow extends FenixWindow {

	private VerticalPanel metadataPanel;
	
	private ListStore<ResourceVO> gaulStore;
	
	private ListView<ResourceVO> view;
	
	private Button openResourceButton;
	
	public void build(String gaulCode, final String language) {
	
		MetadataServiceEntry.getInstance().findMetadataByGAULCode(gaulCode, new AsyncCallback<List<ResourceVO>>() {
			
			public void onSuccess(List<ResourceVO> vos) {
				if (vos.size() > 0) {
					setSize("425px", "450px");
					buildEastPanel(vos, view, language);
					buildCenterPanel(vos, language);
					getWindow().setHeading(HaitiLangEntry.getInstance(language).availableResources());
					show();
					metadataPanel.add(new HTML("<i>Click on a resource in the list on your left to </i>"));
				}
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
			
		});
		
	}

	private void buildCenterPanel(List<ResourceVO> vos, String language) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildPreviewPanel(vos, language));
		getCenterData().setSize(200);
		addCenterPartToWindow();
	}

	private void buildEastPanel(List<ResourceVO> vos, ListView<ResourceVO> view, String language) {
		setEastProperties();
		fillEastPart(buildMetadataPanel());
		getEastData().setSize(200);
		getEast().setHeaderVisible(false);
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(10);
		
		openResourceButton = new Button(HaitiLangEntry.getInstance(language).openResource()); 
		buttonPanel.add(openResourceButton);
		
		getEast().setBottomComponent(buttonPanel);
	}

	private ContentPanel buildPreviewPanel(List<ResourceVO> vos, String language) {

		ContentPanel previewPanel = new ContentPanel();
		previewPanel.setHeaderVisible(false);
		previewPanel.setFrame(true);
		previewPanel.setBodyBorder(false);
		previewPanel.setWidth("175px");
		previewPanel.setLayout(new FitLayout());

		view = new ListView<ResourceVO>() {
			@Override
			protected ResourceVO prepareData(ResourceVO model) {
				String s = model.get("title");
				model.set("shortName", Format.ellipse(s, 500));
				model.set("imageLink", model.getImageLink()); 
				return model;
			}
		};
		
		gaulStore = new ListStore<ResourceVO>();
		for (ResourceVO vo : vos)
			gaulStore.add(vo);
		
		view.setStore(gaulStore);
		view.setDisplayProperty("title");
		view.setTemplate(getTemplate());
		view.setItemSelector("div.thumb-wrap");
		view.setBorders(false);
		view.addListener(Events.Select, HaitiController.hotLinkMetadata(metadataPanel, view, language));
		openResourceButton.addSelectionListener(HaitiController.openResource(view));
		previewPanel.add(view);

		return previewPanel;
	}

	private native String getTemplate() /*-{ 
										return ['<tpl for=".">', 
										'<div class="thumb-wrap" id="{title}" style="border: 1px solid white">', 
										'<div class="thumb"><img src="{imageLink}"></div>', 
										'<span class="x-editable">{shortName}</span></div>', 
										'</tpl>', 
										'<div class="x-clear"></div>'].join(""); 
										}-*/;

	private VerticalPanel buildMetadataPanel() {
		metadataPanel = new VerticalPanel();
		metadataPanel.setSpacing(3);
		metadataPanel.setLayout(new FitLayout());
		metadataPanel.setScrollMode(Scroll.AUTO);
		metadataPanel.setHeight("350px");
		return metadataPanel;
	}

}
