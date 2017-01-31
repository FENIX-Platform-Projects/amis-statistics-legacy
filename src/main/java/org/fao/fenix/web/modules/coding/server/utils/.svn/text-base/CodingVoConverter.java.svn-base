package org.fao.fenix.web.modules.coding.server.utils;

import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.coding.CodingHierarchy;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.coding.DCMTCreatorVo;
import org.fao.fenix.core.domain.coding.DCMTMapping;
import org.fao.fenix.core.domain.coding.DCMTSingleCode;
import org.fao.fenix.core.domain.coding.DCMTStructure;
import org.fao.fenix.web.modules.coding.client.view.vo.DcmtStructureVo;
import org.fao.fenix.web.modules.coding.common.vo.ClassificationVo;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.MappingVo;


public class CodingVoConverter {

	/***
	 * transformation of a codingSystem in a value object
	 * 
	 * @param codingSystem
	 * @return
	 */
	public static CodingSystemVo codingSystem2Vo(CodingSystem codingSystem) {
		CodingSystemVo vo = new CodingSystemVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		return vo;
	}
	
	public static CodeVo dcmtCreatorCode2Vo(DCMTCreatorVo code, CodingSystem codingSystem) {
		CodeVo vo = new CodeVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		vo.setCode(code.getFromCode());
		vo.setDcmtCode(code.getDcmtCode());
		vo.setLabel(code.getLabel());
		return vo;
	}
	
	public static CodeVo code2Vo(Code code, CodingSystem codingSystem) {
		CodeVo vo = new CodeVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		vo.setCode(code.getCode());
		vo.setLabel(code.getLabel());
		vo.setLangCode(code.getLangCode());
		vo.setDescription(code.getDescription());
		return vo;
	}
	
	public static CodeVo code2Vo(Code code) {
		CodeVo vo = new CodeVo();
		vo.setCode(code.getCode());
		vo.setLabel(code.getLabel());
		vo.setLangCode(code.getLangCode());
		vo.setDescription(code.getDescription());
		return vo;
	}
	
	public static CodeVo code2Vo(CodingHierarchy ch) {
		CodeVo vo = new CodeVo();
		vo.setCode(ch.getCode());
		vo.setLabel(ch.getLabel());
		vo.setLevel(ch.getN_level());
		vo.setParent(ch.getParent());
		return vo;
	}
	
	public static CodeVo code2Vo(Code code, String dcmtCode, CodingSystem codingSystem) {
		CodeVo vo = new CodeVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		vo.setCode(code.getCode());
		vo.setLabel(code.getLabel());
		vo.setLangCode(code.getLangCode());
		vo.setDescription(code.getDescription());
		vo.setDcmtCode(dcmtCode);
		return vo;
	}
	
	public static CodeVo dcmtSingleCode2Vo(DCMTSingleCode dcmtSingleCode) {
		CodeVo vo = new CodeVo();
		vo.setCode(dcmtSingleCode.getCode());
		vo.setLabel(dcmtSingleCode.getLabel());
		vo.setDescription(dcmtSingleCode.getDescription());
		return vo;
	}
	
	public static CodeVo code2Vo(DCMTSingleCode code, CodingSystem codingSystem) {
		CodeVo vo = new CodeVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		vo.setCode(code.getCode());
		vo.setLabel(code.getLabel());
		vo.setLangCode(code.getLangCode());
		vo.setDescription(code.getDescription());
		return vo;
	}
	
	public static CodeVo code2Vo(DCMTSingleCode code) {
		CodeVo vo = new CodeVo();
		vo.setCode(code.getCode());
		vo.setLabel(code.getLabel());
		vo.setLangCode(code.getLangCode());
		vo.setDescription(code.getDescription());
		return vo;
	}

	public static MappingVo mapping2Vo(DCMTMapping dcmtMapping, CodingSystem codingSystem) {
		MappingVo vo = new MappingVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		vo.setFromCode(dcmtMapping.getFromCode());
		vo.setDcmtCode(dcmtMapping.getDcmtCode());
		return vo;
	}	
	
	public static MappingVo mapping2Vo(DCMTMapping dcmtMapping) {
		MappingVo vo = new MappingVo();
		vo.setFromCode(dcmtMapping.getFromCode());
		vo.setDcmtCode(dcmtMapping.getDcmtCode());
		return vo;
	}	
	
	public static DcmtStructureVo dcmtStructure2Vo(DCMTStructure dcmtStructure) {
		DcmtStructureVo vo = new DcmtStructureVo();
		vo.setFather(dcmtStructure.getFather());
		vo.setCode(dcmtStructure.getCode());
		return vo;
	}	
	
	
	public static ClassificationVo classification2Vo(Code code, DCMTMapping dcmtMapping, CodingSystem codingSystem) {
		ClassificationVo vo = new ClassificationVo();
		vo.setTitle(codingSystem.getTitle());
		vo.setRegion(codingSystem.getRegion());
		vo.setDcmtCode(dcmtMapping.getDcmtCode());	
		vo.setCode(code.getCode());
		vo.setLabel(code.getLabel());
		vo.setLangCode(code.getLangCode());
		vo.setDescription(code.getDescription());
		return vo;
	}	

}
