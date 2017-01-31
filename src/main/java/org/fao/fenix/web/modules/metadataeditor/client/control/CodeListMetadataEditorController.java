package org.fao.fenix.web.modules.metadataeditor.client.control;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.view.CodeListMetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DatasetTypeVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CodeListMetadataEditorController {

	public static SelectionListener<ButtonEvent> getXml(final CodeListMetadataEditorWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				boolean nameCondition = ((window.getCodingName().getValue() != null) && (window.getCodingName().getValue().length() > 0));
				boolean typeCondition = (!window.getCodingType().getSelection().isEmpty());
				if (nameCondition && typeCondition) {
					ResourceVO vo = createResourceVO(window);
					DatasetTypeVO dtvo = createDatasetTypeVO(window);
					MetadataServiceEntry.getInstance().createMetadataFile(vo, dtvo, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							Window.open("../exportObject/" + result, "_blank", "status=no");
						}

						public void onFailure(Throwable caught) {
							FenixAlert.error(BabelFish.print().info(), caught.getMessage());
						}
					});
				} else {
					FenixAlert.alert(BabelFish.print().info(), BabelFish.print().fillMandatoryFields());
				}
			}
		};
	}
	
	private static ResourceVO createResourceVO(final CodeListMetadataEditorWindow window) {
		ResourceVO vo = new ResourceVO();
		vo.setTitle(window.getCodingName().getValue());
		if (window.getSource().getValue() != null)
			vo.setSource(window.getSource().getValue());
		// Setting the region to "0" as default
		vo.setRegion("0");
		return vo;
	}
	
	private static DatasetTypeVO createDatasetTypeVO(final CodeListMetadataEditorWindow window) {
		DatasetTypeVO vo = new DatasetTypeVO();
		vo.setTitle(window.getCodingType().getSelection().get(0).getCodingType());
		vo.addDescriptorVO(createDescriptorVO("Code"));
		vo.addDescriptorVO(createDescriptorVO("Label"));
		vo.addDescriptorVO(createDescriptorVO("Langcode"));
		return vo;
	}
	
	private static DescriptorVO createDescriptorVO(String header) {
		DescriptorVO vo = new DescriptorVO();
		vo.setHeader(header);
		vo.setContentDescriptor("text");
		return vo;
	}
	
}
