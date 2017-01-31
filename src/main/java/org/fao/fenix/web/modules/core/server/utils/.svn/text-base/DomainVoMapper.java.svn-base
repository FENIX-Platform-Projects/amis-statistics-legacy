package org.fao.fenix.web.modules.core.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.persistence.FenixMetadataDto;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceMetadataVo;

public class DomainVoMapper {
	
	private SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public FenixResourceMetadataVo do2vo(Resource resource) {
		   FenixResourceMetadataVo vo = new FenixResourceMetadataVo();
		    vo.setResourceId(resource.getResourceId());
			vo.setAbstractAbstract(resource.getAbstractAbstract());
//			vo.setContact(resource.getContact());
			vo.setCategories(resource.getCategories());
		
			if(resource.getDateLastUpdate()!=null){
				vo.setDateLastUpdate(resource.getDateLastUpdate().toString());
			} else {
				vo.setDateLastUpdate("");
			}
			
			if(resource.getStartDate()!=null){
				String startDate = dateformatter.format(resource.getStartDate());				
				vo.setStartDate(startDate);
			} else {
				vo.setStartDate("");
			}
			
			if(resource.getEndDate()!=null){
				String endDate = dateformatter.format(resource.getEndDate());
				vo.setEndDate(endDate);
			} else {
				vo.setEndDate("");
			}
			
			//vo.setEndDate(resource.getEndDate().toString());
			//vo.setStartDate(resource.getStartDate().toString());
			vo.setKeywords(resource.getKeywords());
//			vo.setPeriodTypeCode(resource.getPeriodTypeCode());
//			vo.setProvider(resource.getProvider());
			vo.setRegion(resource.getRegion());
			vo.setSharingCode(resource.getSharingCode());
			
			// FIXME Source is an object
			//vo.setSource(resource.getSource());
			
			vo.setTitle(resource.getTitle());
			
			return vo;
		}

	public FenixMetadataDto vo2dto(FenixResourceMetadataVo vo) {
		FenixMetadataDto dto = new FenixMetadataDto();
		dto.setResourceId(vo.getResourceId());
		dto.setAbstractAbstract(vo.getAbstractAbstract());
//		dto.setContact(vo.getContact());
		dto.setCategories(vo.getCategories());		
		dto.setDateLastUpdate(new Date());
		
		if(vo.getEndDate()!=null && !vo.getEndDate().equals("")){
			dto.setEndDate(string2Date(vo.getEndDate()));
		} 
		
		if(vo.getStartDate()!=null && !vo.getStartDate().equals("")){
			dto.setStartDate(string2Date(vo.getStartDate()));
		} 
		
    	dto.setKeywords(vo.getKeywords());
//		dto.setPeriodTypeCode(vo.getPeriodTypeCode());
//		dto.setProvider(vo.getProvider());
		dto.setRegion(vo.getRegion());
		dto.setSharingCode(vo.getSharingCode());
		
		// FIXME Source is an object
		//dto.setSource(vo.getSource());
		
		dto.setTitle(vo.getTitle());
		return dto;
	}

	/**
	 * Hibernate returns a java.sql.Date. GWT want a java.util.Date and thats
	 * what we make here.
	 * 
	 */
	private Date date2Date(Date date) {
		if (date != null) {
			return new Date(date.getTime());
		} else {
			return null;
		}
	}

	public Date string2Date(String date) {		
		if (date != null && date!="") {
			Date d = null;
			try {
				System.out.println("try to parse "+ date);
				d = dateformatter.parse(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return d;
		} else {
			return null;
		}
	}

}
