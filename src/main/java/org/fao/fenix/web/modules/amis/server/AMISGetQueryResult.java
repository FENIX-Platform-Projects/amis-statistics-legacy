package org.fao.fenix.web.modules.amis.server;

import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;


public class AMISGetQueryResult {
	
	private final static Logger LOGGER = Logger.getLogger(AMISGetQueryResult.class);
	
	public static List<AMISResultVO> getFenixDatasetProperties(AMISQueryVO qvo, List<AMISResultVO> rvo, AMISDao amisDao){

		qvo.setSql(FENIXQueryBuilder.getDatasetsQuery(qvo));	
		
        List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
	   
		for(int i=0; i < sqlResult.size(); i++) {
			AMISResultVO vo = new AMISResultVO();
			vo.setDatabase(AMISConstants.FENIX_DATABASE.name());
			vo.setFenixDatasetCode(sqlResult.get(i)[0].toString());
			vo.setFenixDatasetTableName(sqlResult.get(i)[1].toString());
			rvo.add(vo);
		}
		
		LOGGER.info("getFenixDatasetProperties rvo size = " + rvo.size());
		return rvo;
	}
	
}
