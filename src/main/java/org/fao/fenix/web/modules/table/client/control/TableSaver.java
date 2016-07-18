package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TableSaver {

	public static SelectionListener<IconButtonEvent> saveTableView(final TableVO vo, final List<TableFilterVO> filterList, final Long existingTableViewID, final boolean saveAs) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				ResourceViewVO rvo = collectUserSelections(vo, filterList);
				if (existingTableViewID == null) {
					MetadataEditorWindow w = new MetadataEditorWindow();
					w.build(false, true, false, MESaver.getSaveTableViewListener(w, rvo, existingTableViewID, saveAs));
				} else {
					updateTableView(existingTableViewID, rvo, saveAs);					
				}
			}
		};
	}
	
	public static void updateTableView(Long existingTableViewID, ResourceViewVO rvo, boolean saveAs) {
		try {
			if (rvo.getDatasets().size() == 1 && !rvo.getDatasets().get(0).getDsId().equalsIgnoreCase("null")) {
				final LoadingWindow lw = new LoadingWindow("Update", "Saving new settings for the current TableView.", BabelFish.print().pleaseWait());
				TableServiceEntry.getInstance().saveTableView(existingTableViewID, rvo, saveAs, new AsyncCallback<Long>() {
					public void onSuccess(Long tableViewID) {
						lw.destroyLoadingBox();
						FenixAlert.info(BabelFish.print().info(), BabelFish.print().updateCompleted());
						lw.destroyLoadingBox();
					}
					public void onFailure(Throwable e) {
						lw.destroyLoadingBox();
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
						lw.destroyLoadingBox();
					}
				});
			} else {
				for (DatasetVO d : rvo.getDatasets())
					System.out.println("FAILED! " + d.getDsId());
			}
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static ResourceViewVO collectUserSelections(TableVO vo, List<TableFilterVO> filterList) {
		ResourceViewVO rvo = new ResourceViewVO();
		DatasetVO dvo = createDataset(vo, filterList);
		rvo.addDataset(dvo);
		rvo.setResourceType(ResourceType.TABLE.name());
		return rvo;
	}
	
	private static DatasetVO createDataset(TableVO tvo, List<TableFilterVO> filterList) {
		DatasetVO vo = new DatasetVO();
		vo.setDatasetName(tvo.getDatasetTitle());
		vo.setDsId(String.valueOf(tvo.getDatasetID()));
		List<DescriptorViewVO> descriptors = createDescriptors(vo, tvo, filterList);
		for (DescriptorViewVO dvo : descriptors)
			vo.addDescriptorViewVO(dvo);
		return vo;
	}
	
	private static List<DescriptorViewVO> createDescriptors(DatasetVO dataset, TableVO tvo, List<TableFilterVO> filterList) {
		List<DescriptorViewVO> descriptors = new ArrayList<DescriptorViewVO>();
		String langCode = CheckLanguage.getLanguage();
		for (TableFilterVO tfvo : filterList) {
			DescriptorViewVO dvo = new DescriptorViewVO();
			dvo.setHeader(tfvo.getHeader());
			dvo.setContentDescriptor(tfvo.getDataType());
			dvo.setDimensionVisible(true);
			dvo.setIsSelected(true);
			Map<String, String> selectedValuesMap = tfvo.getSelectedValuesMap();
			if (!selectedValuesMap.isEmpty()) {
				for (String code : selectedValuesMap.keySet()) {
					UniqueValueVO uvvo = createUniqueValue(code, selectedValuesMap.get(code), Long.valueOf(dataset.getDsId()), tfvo.getHeader(), langCode, tfvo.getDataType());
					dvo.addUniqueValue(uvvo);
				}
			}
			descriptors.add(dvo);
		}
		return descriptors;
	}
	
	private static UniqueValueVO createUniqueValue(String code, String label, Long datasetID, String header, String langCode, String datatype) {
		UniqueValueVO uvvo = new UniqueValueVO();
		uvvo.setCode(code);
		uvvo.setLabel(label);
		uvvo.setDs_id(datasetID);
		uvvo.setHeader(header);
		uvvo.setLangCode(langCode);
		uvvo.setDatatype(datatype);
		return uvvo;
	}
	
}