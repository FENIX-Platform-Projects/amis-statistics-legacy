package org.fao.fenix.web.modules.dataMapper.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.core.domain.ec.ECConflictsBean;
import org.fao.fenix.core.domain.ec.ECFoodSecurityIssuesBean;
import org.fao.fenix.core.domain.ec.ECFoodSecuritySituationBean;
import org.fao.fenix.core.domain.ec.ECGovernanceBean;
import org.fao.fenix.core.domain.ec.ECKeyMessages;
import org.fao.fenix.core.domain.ec.ECMaps;
import org.fao.fenix.core.domain.ec.ECNaturalDisastersBean;
import org.fao.fenix.core.domain.ec.ECPriceBean;
import org.fao.fenix.core.domain.ec.ECSocialEconomicBean;
import org.fao.fenix.core.persistance.adam.ADAMDao;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.ec.ECDao;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.dataMapper.common.services.DataMapperService;
import org.fao.fenix.web.modules.ec.common.services.ECService;
import org.fao.fenix.web.modules.ec.common.vo.ECBeanVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemConfigurationVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemVO;
import org.fao.fenix.web.modules.ec.server.birt.CreateECReport;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class DataMapperServiceImpl extends RemoteServiceServlet implements DataMapperService {

	Logger LOGGER = Logger.getLogger(DataMapperServiceImpl.class);

	CodecDao codecDao;
	
	ADAMDao adamDao;
	
	public List<CodeVo> getCodesByDescription(String code, String langCode, String codingSystemCode) {

		return null;
	}


	public List<CodeVo> getAllCodes(String codingSystem, String langcode) {
		List<CodeVo> codesVo = new ArrayList<CodeVo>();
		List<Code> codes = codecDao.getCodesOfaSystemLangCodeSorted(codingSystem, "0", langcode);
		
		for( Code code : codes ) {
			codesVo.add(code2vo(code));
		}
		return codesVo;
	}
	
	private CodeVo code2vo(Code c) {
		CodeVo vo = new CodeVo();
		vo.setCode(c.getCode());
		vo.setDescription(c.getDescription());
		vo.setLabel(c.getLabel());
		vo.setLangCode(c.getLangCode());
		return vo;
	}

	
	public void dropTable(String tablename) {
			
			/* TODO: DROP TABLE */
			adamDao.dropTable(tablename);
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}


	public void setAdamDao(ADAMDao adamDao) {
		this.adamDao = adamDao;
	}
	
	
	
	


}
