package org.fao.fenix.web.modules.cpf.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.cpf.CPFDao;
import org.fao.fenix.web.modules.cpf.common.model.CPFCodesModelData;
import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;
import org.fao.fenix.web.modules.cpf.common.vo.CPFResultVO;

public class CPFGetCodingSystem{

	private final static Logger LOGGER = Logger.getLogger(CPFGetCodingSystem.class);

	public static CPFResultVO getAllCPFNames(CPFQueryVO qvo, CPFResultVO rvo, CPFDao cpfDao){
		qvo.setSql(CPFCodingSystemQueryBuilder.getAllCPFNamesQuery(qvo));
		rvo.setCodes(getCodingSystem(qvo, cpfDao));

		LOGGER.info("getAllCPFNames rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static CPFResultVO getAllCPFCodes(CPFQueryVO qvo, CPFResultVO rvo, CPFDao cpfDao){
		qvo.setSql(CPFCodingSystemQueryBuilder.getAllCPFCodesQuery(qvo));
		rvo.setCodes(getCodingSystem(qvo, cpfDao));

		LOGGER.info("getAllCPFCodes rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static CPFResultVO getAllCountries(CPFQueryVO qvo, CPFResultVO rvo, CPFDao cpfDao){
		qvo.setSql(CPFCodingSystemQueryBuilder.getAllCountriesQuery(qvo));
		rvo.setCodes(getCodingSystem(qvo, cpfDao));

		LOGGER.info("getAllCountries rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static CPFResultVO getAllFAOFramework(CPFQueryVO qvo, CPFResultVO rvo, CPFDao cpfDao){
		qvo.setSql(CPFCodingSystemQueryBuilder.getAllFAOFrameworkQuery(qvo));
		rvo.setCodes(getCodingSystem(qvo, cpfDao));

		LOGGER.info("AllFAOFramework rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	
	private static List<CPFCodesModelData> getCodingSystem(CPFQueryVO qvo, CPFDao cpfDao) {

		List<CPFCodesModelData> codes = new ArrayList<CPFCodesModelData>();
		List<Object[]> sqlResult = cpfDao.getResultList(qvo.getSql());

		for(int i=0; i < sqlResult.size(); i++) {
			codes.add(new CPFCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString()));
		}

		return codes;
	}



}
