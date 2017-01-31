package org.fao.fenix.web.modules.adam.client.view;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class ADAMLayersHandle {

	private LinkedHashMap<Long, LayerVO> layersMap;
	
	public ADAMLayersHandle() {
		layersMap = new LinkedHashMap<Long, LayerVO>();
	}

	public void addLayer(LayerVO vo) {
		layersMap.put(vo.getOlID(), vo);
	}
	
	public LinkedHashMap<Long, LayerVO> getLayersMap() {
		return layersMap;
	}
	
	
	
}