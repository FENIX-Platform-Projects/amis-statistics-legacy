/*
 */

package org.fao.fenix.web.modules.map.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

/**
 * 
 * @author etj
 */
public class ClientMapView extends FenixResourceVo {
    
	private ClientBBox bbox;
	private List<ClientGeoView> layerList = new ArrayList();

	public ClientMapView() {
	}

	public ClientMapView(String id, String project, String name, String type, String url) {
		super(id, project, name, type, url);
	}

	public ClientMapView(FenixResourceVo r) {
		FenixResourceVo.copy(r, this);
	}

	public void addLayer(ClientGeoView r) {
		layerList.add(r);
	}

	public List<ClientGeoView> getLayerList() {
		return layerList;
	}

	public String getType() {
		return ResourceType.MAPVIEW;
	}

	public ClientBBox getBbox() {
		return bbox;
	}

	public void setBbox(ClientBBox bbox) {
		this.bbox = bbox;
	}

	public String toString() {
		return "ClientMapView[id:"+ getId() + " "+ bbox + " layers: "+ layerList.size()+ "]";
	}

}
