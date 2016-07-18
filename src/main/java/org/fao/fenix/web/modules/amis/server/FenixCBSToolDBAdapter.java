package org.fao.fenix.web.modules.amis.server;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.dataset.CoreContent;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.CCBS.Codes;

public class FenixCBSToolDBAdapter implements DBAdapter{

	private static final Logger LOGGER = Logger.getLogger(FenixCBSToolDBAdapter.class);
	
//	private AMISDao amisDao;


	public String[] getCCBSGaulCodes() {
		return null;
	}

	public String[] getGaulNames(String[] gaulCodes) {
		return null;
	}

	public TimeSeries select(Codes code, String featureCode, String commodityCode) {
		return null;
	}
	
//	public TimeSeries select(Codes code, AMISQueryVO qvo) {
//		AMISResultVO rvo = new AMISResultVO();
//		qvo.setElementCode(code.toString());
//		rvo = AMISGetCodingSystem.getTimeSeries(qvo, rvo, amisDao);
//		
//		FenixCCBSTimeSeries sourceTS = new FenixCCBSTimeSeries(rvo.size());
//		for(CoreContent coreContent : result) {
//			sourceTS.add(new FenixCCBSTimeSeries.LocalElement((QuantitativeCoreContent)coreContent));
//		}
//		
//		return sourceTS;
//		return null;
//	}
//
//	public void setAmisDao(AMISDao amisDao) {
//		this.amisDao = amisDao;
//	}
	
	
}
