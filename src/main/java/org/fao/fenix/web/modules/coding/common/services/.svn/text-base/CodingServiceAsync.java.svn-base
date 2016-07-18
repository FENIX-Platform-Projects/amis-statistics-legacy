package org.fao.fenix.web.modules.coding.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingHiearachyMD;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.coding.common.vo.MappingVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CodingServiceAsync {	
	
	public void loadReleaseData(AsyncCallback<String> callback);
	
	/** THIS METHOD USES THE Coding System Code **/
	public void getCodes(List<String> codes, String system, String langCode, AsyncCallback<List<CodeVo>> callback);
	
	// search 
	public void findAllCodingSystems(String category, AsyncCallback<List<String>> callback);
	
	public void findOfficialLangCodesFao(AsyncCallback<List<String>> callback);
	
	public void tokenizeCodingSystem(String codingSystem, AsyncCallback<List<String>> callback);
	
	public void searchBy(String criteria, String search, String codingSystem, String langCode, AsyncCallback<List<CodeVo>> callback);
	
	public void searchByCreator(String criteria, String search, String codingSystem, String langCode, int startIndex, int endIndex, AsyncCallback<List<CodeVo>> callback);
	
	public void getQuerySize(String criteria, String search, String codingSystem, String langCode, AsyncCallback<Integer> callback);
	
	public void searchAllBy(String criteria, String search, List<String> codingSystem, String langCode, AsyncCallback<List<List<CodeVo>>> callback);
	
	public void searchByCreatorOrder(String criteria, String search, String codingSystem, String langCode, int startIndex, int endIndex, String sortingType, String columnName, AsyncCallback<List<CodeVo>> callback);

	// convert 
	public void convertFromDcmt(String criteria, String search, String destinationCodeSystem, String langCode, AsyncCallback<List<CodeVo>> callback);
	
	public void convertInto(String criteria, String search, String sourceCodeSystem, String destinationCodeSystem, String langCode, AsyncCallback<List<CodeVo>> callback);
	
	public void testCode(String dcmtCode, AsyncCallback<String> callback);
	
	public void testHierarchicStructure(AsyncCallback<String> callback);
	
	public void getHashMapElements(AsyncCallback<HashMap<String, List<List<String>>>> callback);
	
	public void getElementsAttributes(String langCode, AsyncCallback<List<List<String>>> callback);
	
	public void getElementsNotAttributes(String langCode, AsyncCallback<List<List<String>>> callback);
	
	public void getNotElements(String langCode, AsyncCallback<List<String>> callback);
	
	public void showCodingSystem(Long resourceId, AsyncCallback<List<CodeVo>> callback);

	
	/****
	 *	Dcmt Coding Creator  
	 */
	
	public  void searchDcmtCodes(String code, String label, String langCode, String dcmtCode, CodingSystemVo cs, AsyncCallback<List<CodeVo>> callback);
	
	public void updateInsertDcmtCode(List<String> allElements, List<String> allAttributes, Boolean excluding, AsyncCallback<String> callback);
	
	public void updateRemovedAttrDcmtCode(String element, String attribute,  AsyncCallback<String> callback);
	
	public void updateRemovedElemDcmtCode(String element,  AsyncCallback<String> callback);
	
	public void findFathers(String item, String langCode, AsyncCallback<List<CodeVo>> asyncCallback);
	
	public void findChildren(String father, String langCode, AsyncCallback<List<CodeVo>> asyncCallback);
	
	public void getMappedCodes(String cs, AsyncCallback<List<MappingVo>> asyncCallback);
	
	public void getDcmtLabel(String dcmtCode, String langCode, AsyncCallback<String> asyncCallback);
	
	/**** upload codes from grid ***/

	public void uploadModifiedCodes(List<DcmtCodingCreatorGridMD> md, CodingSystemVo codingSystem, AsyncCallback<String> asyncCallback) throws FenixGWTException; 
	
	public void saveNewItem(String label, String langCode, String description, String father, String item, AsyncCallback asyncCallback);
	
	/*** tokenize output ***/
	
	public void tokenizeOutput(String response, AsyncCallback<String> asyncCallback);
	
	public void exportAsCSV(String system, String region, List<DcmtCodingCreatorGridMD>  modifiedCodes, AsyncCallback<String> callback);
	
	/*** coding hierarchy ***/
	
	public void findCsWithHierarchy(AsyncCallback<List<CodingSystemVo>> callback);
	
	public void getCsDescriptions(Long cs_id, String code, AsyncCallback<List<List<String>>> callback);
	
	public void initializeHierarchyGrid(Long cs_id, String code, AsyncCallback<List<CodeVo>> callback);
	
	public void getCodes(Long cs_id, List<CodingHiearachyMD> code, Integer codeLevel, Integer level, AsyncCallback<List<List<CodeVo>>> callback);
	
	/*** import default coding lists from Main Menu ***/
	@SuppressWarnings("unchecked")
	public void importDefaultCodeLists(AsyncCallback callback);
	
}
