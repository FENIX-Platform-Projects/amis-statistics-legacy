package org.fao.fenix.web.modules.chartdesigner.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerPanel;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;

import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class ChartDesignerDimensionsComparator {
	
	private Map<String, List<DescriptorViewVO>> map;
	
	public ChartDesignerDimensionsComparator() {
		map = new HashMap<String, List<DescriptorViewVO>>();
		map.put("date", new ArrayList<DescriptorViewVO>());
		map.put("baseDateFrom", new ArrayList<DescriptorViewVO>());
		map.put("baseDateTo", new ArrayList<DescriptorViewVO>());
		map.put("firstIndicator", new ArrayList<DescriptorViewVO>());
		map.put("secondIndicator", new ArrayList<DescriptorViewVO>());
		map.put("commodityCode", new ArrayList<DescriptorViewVO>());
		map.put("featureCode", new ArrayList<DescriptorViewVO>());
		map.put("measurementUnit", new ArrayList<DescriptorViewVO>());
		map.put("text", new ArrayList<DescriptorViewVO>());
	}

	public List<DescriptorViewVO> findCommonDimensions(ChartDesignerPanel chartDesignerPanel, List<DatasetVO> datasets) {
		List<DescriptorViewVO> vos = new ArrayList<DescriptorViewVO>();
		List<Long> selectedDatasetIDs = selectedDatasets(chartDesignerPanel);
		for (int i = 0 ; i < datasets.size() ; i++) {
			DatasetVO d = datasets.get(i);
			if (selectedDatasetIDs.contains(Long.valueOf(d.getDsId()))) {
				DescriptorViewVO fake = new DescriptorViewVO();
				fake.setHeader("********** " + d.getDatasetName() + " **********");
				fake.setContentDescriptor("");
				vos.add(fake);
				for (DescriptorViewVO vo : d.getDescriptorViews()) {
					vos.add(vo);
				}
			}
		}
		return vos;
	}
	
	public List<Long> selectedDatasets(ChartDesignerPanel chartDesignerPanel) {
		List<Long> ids = new ArrayList<Long>();
		for (ChartDesignerDatasourceFieldSet fs : chartDesignerPanel.getDatasourcePanel().getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue())
				ids.add(Long.valueOf((String)cb.getData("DATASET_ID")));
		}
		return ids;
	}
	
}
