package org.fao.fenix.web.modules.coding.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingHiearachyMD;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.coding.common.vo.MappingVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;



public interface CodingService extends RemoteService {

	
	/**
	 * SERVICES OF THE SEARCHING INTERFACE
	 */
	// initialization of available coding systems
	public String loadReleaseData();
	
	public List<String> findAllCodingSystems(String category);
	
	public List<String> findOfficialLangCodesFao();
	
	public List<String> tokenizeCodingSystem(String codingSystem);
	
	
	/*
	 * per il searching potrei utilizzare i CodeVo, mappingVo etc
	 */
	/***
	 * searching with some criteria
	 */
	
	public List<CodeVo> searchBy(String criteria, String search, String codingSystem, String langCode);
	
	public List<CodeVo> searchByCreator(String criteria, String search, String codingSystem, String langCode, int startIndex, int endIndex);
	
	public Integer getQuerySize(String criteria, String search, String codingSystem, String langCode);
	
	public List<List<CodeVo>> searchAllBy(String criteria, String search, List<String> codingSystem, String langCode);
	
	public List<CodeVo> searchByCreatorOrder(String criteria, String search, String codingSystem, String langCode, int startIndex, int endIndex, String sortingType, String columnName);
	
	public List<CodeVo> showCodingSystem(Long resourceId);
	
	
	/***
	 * convert with some criteria
	 */
	
	public  List<CodeVo> convertFromDcmt(String criteria, String search, String destinationCodeSystem, String langCode);
	
	public  List<CodeVo> convertInto(String criteria, String search, String sourceCodeSystem, String destinationCodeSystem, String langCode);
	
	/**
	 * translator
	 */
	
	public List<CodeVo> searchDcmtCodes(String code, String label, String langCode, String dcmtCode, CodingSystemVo cs);
	
	public String updateInsertDcmtCode(List<String> allElements, List<String> allAttributes, Boolean excluding);
	
	public String updateRemovedAttrDcmtCode(String element, String attribute);
	
	public String updateRemovedElemDcmtCode(String element);
	
	public List<CodeVo> findFathers(String item, String langCode);
	
	public List<CodeVo> findChildren(String father, String langCode);
	
	public List<MappingVo> getMappedCodes(String cs);
	
	public String getDcmtLabel(String dcmtCode, String langCode);
	
	public String testCode(String dcmtCode);
	
	public String testHierarchicStructure();
	
	public HashMap<String, List<List<String>>> getHashMapElements();
	
	public List<List<String>> getElementsAttributes(String langCode);
	
	public List<List<String>> getElementsNotAttributes(String langCode);
	
	public List<String> getNotElements(String langCode);
	
	/**** upload codes from grid ***/

	public String uploadModifiedCodes(List<DcmtCodingCreatorGridMD> md, CodingSystemVo codingSystem) throws FenixGWTException;
	
	public void saveNewItem(String label, String langCode, String description, String father, String item);
	
	/*** tokenize output ***/
	
	public String tokenizeOutput(String response);
	
	public String exportAsCSV(String system, String region, List<DcmtCodingCreatorGridMD>  modifiedCodes);
	
	/*** coding hierarchy ***/
	
	public List<CodingSystemVo> findCsWithHierarchy();
	
	public List<List<String>> getCsDescriptions(Long cs_id, String code);
	
	public List<CodeVo> initializeHierarchyGrid(Long cs_id, String code);
	
	public List<List<CodeVo>> getCodes(Long cs_id, List<CodingHiearachyMD> code, Integer codeLevel, Integer level);
	
	/*** import default coding lists from Main Menu ***/
	
	@Secured( { "ROLE_USER" })
	public void importDefaultCodeLists() throws FenixGWTException;
	
	public List<CodeVo> getCodes(List<String> codes, String system, String langCode);
}
