package org.fao.fenix.web.modules.welcome.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.core.domain.welcome.QueryPOJO;
import org.fao.fenix.core.domain.welcome.QueryResultPOJO;
import org.fao.fenix.core.welcome.WelcomeUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.welcome.common.services.WelcomeService;
import org.fao.fenix.web.modules.welcome.common.vo.QueryResultVO;
import org.fao.fenix.web.modules.welcome.common.vo.QueryVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class WelcomeServiceImpl extends RemoteServiceServlet implements WelcomeService {
	
	private WelcomeUtils welcomeUtils;

	public List<QueryResultVO> search(QueryVO queryVO) throws FenixGWTException {
		try {
			QueryPOJO pojo = vo2pojo(queryVO);
			List<QueryResultPOJO> pojos = welcomeUtils.search(pojo);
			List<QueryResultVO> vos = new ArrayList<QueryResultVO>();
			for (QueryResultPOJO result : pojos)
				vos.add(pojo2vo(result));
			return vos;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private QueryResultPOJO vo2pojo(QueryResultVO vo) {
		QueryResultPOJO pojo = new QueryResultPOJO();
		pojo.setAbstractabstract(vo.getAbstractabstract());
		pojo.setCategories(vo.getCategories());
		pojo.setCode(vo.getCode());
		pojo.setDatelastupdate(vo.getDatelastupdate());
		pojo.setEnddate(vo.getEnddate());
		pojo.setKeywords(vo.getKeywords());
		pojo.setProvider(vo.getProvider());
		pojo.setRegion(vo.getRegion());
		pojo.setResourceType(vo.getResourceType());
		pojo.setResourceid(vo.getResourceid());
		pojo.setSharingcode(vo.getSharingcode());
		pojo.setSource(vo.getSource());
		pojo.setStartdate(vo.getStartdate());
		pojo.setTitle(vo.getTitle());
		return pojo;
	}
	
	private QueryResultVO pojo2vo(QueryResultPOJO pojo) {
		QueryResultVO vo = new QueryResultVO();
		vo.setAbstractabstract(pojo.getAbstractabstract());
		vo.setCategories(pojo.getCategories());
		vo.setCode(pojo.getCode());
		vo.setDatelastupdate(pojo.getDatelastupdate());
		vo.setEnddate(pojo.getEnddate());
		vo.setKeywords(pojo.getKeywords());
		vo.setProvider(pojo.getProvider());
		vo.setRegion(pojo.getRegion());
		vo.setResourceType(pojo.getResourceType());
		vo.setResourceid(pojo.getResourceid());
		vo.setSharingcode(pojo.getSharingcode());
		vo.setSource(pojo.getSource());
		vo.setStartdate(pojo.getStartdate());
		vo.setTitle(pojo.getTitle());
		return vo;
	}
	
	private QueryPOJO vo2pojo(QueryVO vo) {
		QueryPOJO pojo = new QueryPOJO();
		pojo.setFreeText(vo.getFreeText());
		pojo.setResourceType(vo.getResourceType());
		pojo.setRegion(vo.getRegion());
		pojo.setCategory(vo.getCategory());
		
		pojo.setFromDate(vo.getFromDate());
		pojo.setToDate(vo.getToDate());
		pojo.setSource(vo.getSource());
		
		return pojo;
	}
	
	
	
	
	
	private QueryVO pojo2vo(QueryPOJO pojo) {
		QueryVO vo = new QueryVO();
		vo.setFreeText(pojo.getFreeText());
		vo.setResourceType(pojo.getResourceType());
		vo.setRegion(pojo.getRegion());
		vo.setCategory(pojo.getCategory());
		vo.setFromDate(pojo.getFromDate());
		vo.setToDate(pojo.getToDate());
		vo.setSource(pojo.getSource());
		return vo;
	}

	public void setWelcomeUtils(WelcomeUtils welcomeUtils) {
		this.welcomeUtils = welcomeUtils;
	}
	
}