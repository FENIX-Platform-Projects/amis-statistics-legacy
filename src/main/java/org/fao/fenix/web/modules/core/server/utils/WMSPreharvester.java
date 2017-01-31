/*
 */

package org.fao.fenix.web.modules.core.server.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fao.fenix.core.domain.map.external.WMSHarvestEntry;
import org.fao.fenix.core.persistence.map.external.WMSServerDao;
import org.fao.fenix.map.wms.harvest.HarvestCallback;
import org.fao.fenix.map.wms.harvest.WMSHarvester;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.Resource;

/**
 *
 * @author etj
 */
public class WMSPreharvester {
	private WMSServerDao wmsServerDao;
	private WMSHarvester harvester = new WMSHarvester();		


	public WMSPreharvester(Resource resource) {

		try {
			File file = resource.getFile();
			Document doc = new SAXBuilder().build(file);
			Element root = doc.getRootElement();
			for(Element server : (List<Element>)root.getChildren("server")) {
				harvester.add(new WMSHarvestEntry(server.getChildText("title"), server.getChildText("url")));				
			}
			
			harvester.setCallback(new HarvestCallback() {
				@Override
				public void harvested(WMSHarvestEntry harvesterEntry, boolean successful) {
					wmsServerDao.save(harvesterEntry);
				}
			});

			
		} catch (JDOMException ex) {
			Logger.getLogger(WMSPreharvester.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void harvest() {
		harvester.doHarvest();						
	}
	
	// --
	
	public void setWmsServerDao(WMSServerDao wmsServerDao) {
		this.wmsServerDao = wmsServerDao;
	}	
}
