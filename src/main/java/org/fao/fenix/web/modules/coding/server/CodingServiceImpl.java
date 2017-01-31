package org.fao.fenix.web.modules.coding.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.coding.CodingHierarchy;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.coding.DCMTCreatorVo;
import org.fao.fenix.core.domain.coding.DCMTMapping;
import org.fao.fenix.core.domain.coding.DCMTSingleCode;
import org.fao.fenix.core.domain.coding.DCMTStructure;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.FindDao;
import org.fao.fenix.core.persistence.coding.PopulateCoding;
import org.fao.fenix.core.utils.CodeListImporter;
import org.fao.fenix.core.utils.DcmtImporter;
import org.fao.fenix.core.utils.DcmtTranslator;
import org.fao.fenix.web.modules.coding.common.services.CodingService;
import org.fao.fenix.web.modules.coding.common.vo.ClassificationVo;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingHiearachyMD;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.coding.common.vo.MappingVo;
import org.fao.fenix.web.modules.coding.server.utils.CodingVoConverter;
import org.fao.fenix.web.modules.coding.utils.CodingUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CodingServiceImpl extends RemoteServiceServlet implements CodingService {
	
	DcmtImporter dcmtImporter;
	FindDao findDao;
	CodecDao codecDao;
	PopulateCoding populateCoding;
	DcmtTranslator dcmtTranslator;
	CodingUtils codingUtils;

	CodingVoConverter codingVoConverter;
	CodingSystemVo codingSystemVo;
	List<CodeVo> codesVo;
	List<List<CodeVo>> codesVoList;
	List<MappingVo> mappingsVo;
	List<ClassificationVo> classificationVo;
	CodingSystem codingSystem;
	FileUpload fileUpload;

	CodeListImporter codeListImporter;
	
	
	/***************************************************************
	 * 							SEARCHING 
	 ****************************************************************/
	
	
	/***
	 * Method that retrives all commodidy coding system
	 * 
	 * @return the list of coding systems
	 */
	/** TODO: QUICK FIX ABOUT REGIONS
	 *  	  -> dynamically retrieve regions and return CodingSystemVO
	 */
/*	public List<CodingSystemVo> findAllCodingSystems(String category) {
		List<CodingSystemVo> codeSystems = new ArrayList<CodingSystemVo>();
		List<CodingSystem> codingSystem = codecDao.findAllCodingSystems(category);
//		List<CodingSystem> codingSystem = findDao.findAllCodingSystems(category);
		for(CodingSystem cs : codingSystem) {
			CodingSystemVo csvo = new CodingSystemVo();
			csvo.setTitle(cs.getTitle());
			csvo.setRegion(csvo.getRegion());
			codeSystems.add(csvo);
		}
//		for(int i =  0; i < codingSystem.size(); i++) {
//			if ( codingSystem.get(i).getRegion().equals("")) {
//				codeSystems.add(codingSystem.get(i).getTitle());
//			}
//			else {
//				codeSystems.add(codingSystem.get(i).getTitle() + ", " + codingSystem.get(i).getRegion());
//			}
//		}
		return codeSystems;
	}*/
	public List<String> findAllCodingSystems(String category) {
		List<String> codeSystems = new ArrayList<String>();
		List<CodingSystem> codingSystem = codecDao.findAllCodingSystems(category);
		for(int i =  0; i < codingSystem.size(); i++) 
				codeSystems.add(codingSystem.get(i).getTitle());
		return codeSystems;
	}
	
	/***
	 * Method that retrives all the officials FAO langCodes 
	 * 
	 * @return the list of langCodes
	 */
	public List<String> findOfficialLangCodesFao() {
		List<String> langCodes = new ArrayList<String>();
		List<Code> lcodes = codecDao.finalAllOfficialLangCodesFao();
		for(int i = 0; i < lcodes.size(); i++) {
			langCodes.add(lcodes.get(i).getCode());// + ", " + lcodes.get(i).getLabel());
		}
		return langCodes;
	}
	
	
	public List<String> tokenizeCodingSystem(String codingSystem) {
		codingUtils = new CodingUtils();
		CodingSystem codeSystem = codingUtils.tokenizeStringBox(codingSystem);
		List<String> cs = new ArrayList<String>();
		cs.add(codeSystem.getTitle());
		cs.add(codeSystem.getRegion());
		return cs;
	}
	
	/***
	 * Search ALL
	 * 
	 * @return a list of codesVo of the research
	 */
	public List<List<CodeVo>> searchAllBy(String criteria, String search, List<String> codeSystems, String langCode) {
		List<List<CodeVo>> allCodesVo = new ArrayList<List<CodeVo>>();
		for( int i=0; i < codeSystems.size(); i++ ){
			allCodesVo.add(searchBy( criteria,  search, codeSystems.get(i), langCode));
		}	
		return allCodesVo;
	}
	
	
	/***
	 * Search Method
	 * 
	 * @return a list of codesVo of the research
	 */
	/** TODO: quick fix about region
	 *  	  create, a service that retrieve the region dynamically
	 */
	public List<CodeVo> searchBy(String criteria, String search, String codingSystem, String langCode) {
		codesVo = new ArrayList<CodeVo>();
			
//		System.out.println("\n\n\n\n\n\nSEARCHING" + criteria); 
		codingUtils = new CodingUtils();
//		CodingSystem codeSystem = codingUtils.tokenizeStringBox(codingSystem);
		CodingSystem codeSystem = new CodingSystem();
		codeSystem.setTitle(codingSystem);
		codeSystem.setRegion("0");
				
		if ( criteria.equals("Code")){
			if (codeSystem.getTitle().equals("DCMT") || codeSystem.getTitle().equals("dcmt")) {
				List<DCMTSingleCode> codes = codecDao.getDCMTSingleCodeFromCode(search);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}
			else {
				Code code = codecDao.getCodeFromCodeSystemRegion(search, codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (code != null ) {
//					System.out.println("codes empty");
				
//				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(code,codeSystem));
				}
			}
		}
		
		else if ( criteria.equals("Label")) {
			if (codeSystem.getTitle().equals("DCMT")) {
				List<DCMTSingleCode> codes = codecDao.getDCMTSingleCodeFromLabel(search.toLowerCase());
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}
			else {
				List<Code> codes = codecDao.getCodeFromLabelSystemRegion(search.toLowerCase(), codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}
		}
		
		else if ( criteria.equals("Description")) {	
			if (codeSystem.getTitle().equals("DCMT")) {
				List<DCMTSingleCode> codes = codecDao.getDCMTSingleCodeFromDescription(search.toLowerCase());
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}
			else {
				List<Code> codes = codecDao.getCodeFromDescriptionSystemRegion(search.toLowerCase(), codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}
		}
		
		else if ( criteria.equals("Coding System")) {
//			System.out.println("coding system searching");
			if (codeSystem.getTitle().equals("DCMT")) {
				List<DCMTSingleCode> codes = findDao.findAllDCMTSingleCode();
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}
			else {
				List<Code> codes = codecDao.getCodesOfaSystemLangCode(codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					codesVo.add(codingVoConverter.code2Vo(codes.get(i),codeSystem));
				}
			}	
		}
//	System.out.println("---end coding system searching");
		return codesVo;
	}
	
	
	public List<CodeVo> showCodingSystem(Long resourceId) {
		codesVo = new ArrayList<CodeVo>();
		CodingSystem codingSystem = codecDao.getCodingSystemFromId(resourceId);
		
		List<Code> codes = codecDao.getCodesOfaSystem(codingSystem.getTitle(), codingSystem.getRegion());
		for (int i = 0; i < codes.size(); i++) {
			codesVo.add(codingVoConverter.code2Vo(codes.get(i), codingSystem));
		}
		return codesVo;
	}
	
	
	
	
	/***
	 * Search Method for the creator
	 * 
	 * @return a list of codesVo of the research
	 */
	public List<CodeVo> searchByCreator(String criteria, String search, String codingSystem, String langCode, int startIndex, int endIndex) {
		codesVo = new ArrayList<CodeVo>();
//		System.out.println("\n\nSEARCHING" + criteria); 
		codingUtils = new CodingUtils();
		CodingSystem codeSystem = codingUtils.tokenizeStringBox(codingSystem);
				
		if ( criteria.equals("Code")){
				Code code = codecDao.getCodeFromCodeSystemRegion(search, codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (code != null ) {
//					System.out.println("codes empty");
				
//				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(code.getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(code, dcmtCode, codeSystem));
				}
		}
		
		else if ( criteria.equals("label")) {
				List<Code> codes = codecDao.getCodeLimitedFromLabelSystemRegion(search.toLowerCase(), codeSystem.getTitle(), codeSystem.getRegion(), startIndex, endIndex);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(codes.get(i).getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(codes.get(i), dcmtCode, codeSystem));
				}
		}
		
		else if ( criteria.equals("Description")) {	
			/***** TODO: CHANGE QUERY ***/
				Code code = codecDao.getCodeFromCodeSystemRegion(search.toLowerCase(), codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (code != null) {
//					System.out.println("codes empty");
				
//				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(code.getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(code, dcmtCode, codeSystem));
				}
		}
		
		else if ( criteria.equals("Coding System")) {
//			System.out.println("coding system searching");
				List<Code> codes = codecDao.getCodesLimitedOfaSystem(codeSystem.getTitle(), codeSystem.getRegion(), startIndex, endIndex);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(codes.get(i).getCode(), codeSystem.getTitle(), codeSystem.getRegion());
//					System.out.println("-> " + codes.get(i).getCode() + " | " + dcmtCode);
					codesVo.add(codingVoConverter.code2Vo(codes.get(i), dcmtCode, codeSystem));
				}
		}
//	System.out.println("---end coding system searching");
	return codesVo;
	}
	
	
	/***
	 * Search Method for the creator (with a selected order)
	 * 
	 * @return a list of codesVo of the research
	 */
	/****
	 * 
	 * TODO: The order has been made just for the research made by coding system
	 */
	public List<CodeVo> searchByCreatorOrder(String criteria, String search, String codingSystem, String langCode, int startIndex, int endIndex, String sortingType, String columnName) {

		codesVo = new ArrayList<CodeVo>();
		codingUtils = new CodingUtils();
//		CodingSystem codeSystem = codingUtils.tokenizeStringBox(codingSystem);
			/***TODO: implementare listening pure per code, label, description
			 * anche solo prendendo il listsotre e ordinandolo in modo crescente o decrescente
			 * con comparator o con chiamate al db, forse meglio del listener.
			 */
		CodingSystem codeSystem = new CodingSystem();
		codeSystem.setTitle("HS");
		codeSystem.setRegion("0");
		System.out.println("RETRIEVING CODES: " + codeSystem.getTitle() + " | " + criteria);
		if ( criteria.equals("Code")) {
			
			List<Code> codes = null;
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(codes.get(i).getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(codes.get(i), dcmtCode, codeSystem));
				}
		}
		
		else if ( criteria.equals("Label")) {
			List<Code> codes = null;
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(codes.get(i).getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(codes.get(i), dcmtCode, codeSystem));
				}
		}
		
		else if ( criteria.equals("Description")) {	
			/***** TODO: CHANGE QUERY ***/
				Code code = codecDao.getCodeFromCodeSystemRegion(search, codeSystem.getTitle(), codeSystem.getRegion(), langCode);
				if (code != null) {
////					System.out.println("codes empty");
//				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(code.getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(code, dcmtCode, codeSystem));
				}
		}
		else if ( criteria.equals("Coding System")) {
			if ( columnName.equals("dcmtCode")) {
				List<DCMTCreatorVo> dcmtCodeVo = codecDao.getCodesDCMTCodeVo(codeSystem.getTitle(), codeSystem.getRegion(), startIndex, endIndex, sortingType, columnName);
//				System.out.println(dcmtCodeVo.size());
				for (int i = 0; i < dcmtCodeVo.size(); i++) {
					codesVo.add(codingVoConverter.dcmtCreatorCode2Vo(dcmtCodeVo.get(i), codeSystem));
				}
					
			}
			else {
//				System.out.println("coding system searching ordered");
				List<Code> codes = codecDao.getCodesLimitedOfaSystemOrdered(codeSystem.getTitle(), codeSystem.getRegion(), langCode, startIndex, endIndex, sortingType, columnName);
				if (codes.isEmpty()) {
//					System.out.println("codes empty");
				}
				for (int i = 0; i < codes.size(); i++) {
					String dcmtCode = codecDao.getDcmtCodeFromCodeCodingSystem(codes.get(i).getCode(), codeSystem.getTitle(), codeSystem.getRegion());
					codesVo.add(codingVoConverter.code2Vo(codes.get(i), dcmtCode, codeSystem));
//					System.out.println("-> " + codes.get(i).getCode() + " | " + dcmtCode);
				}
			}
		}
	if (codesVo.isEmpty()) {
//		System.out.println("CODESVO IS EMPTY");
	}
//	System.out.println("---end coding system searching");
	return codesVo;
	}
	
	
	/***
	 * upload the modified Codes to the database
	 * 
	 * @param the list of modified codes
	 * @return
	 */
	public String uploadModifiedCodes(List<DcmtCodingCreatorGridMD> md, CodingSystemVo codingSystem) throws FenixGWTException {
		
		try {
			String j = "";
			CodingSystem cs = new CodingSystem();
			cs.setTitle(codingSystem.getTitle());
			cs.setRegion(codingSystem.getRegion());
			for(int i=0; i<md.size(); i++){
				DCMTMapping dcmtMapping = new DCMTMapping();
				dcmtMapping.setDcmtCode(md.get(i).getDcmtCode());
				dcmtMapping.setFromCode(md.get(i).getCode());
				dcmtImporter.saveMappingToDb(dcmtMapping, cs);
			}
			return j;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
		
	}


	// ------------------- RELEASE STUFF POPULATION ----------------
	public String loadReleaseData(){
		String result="";
		try {
			populateCoding.importHS(",");
			populateCoding.importGAUL(",");
			populateCoding.importGSPL(",");
			//populateCoding.importCodes();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	
	/***
	 * Method that return the size of a query
	 * 
	 * @return a list of codesVo of the research
	 */
	public Integer getQuerySize(String criteria, String search, String codingSystem, String langCode) {
		Integer resultSize = new Integer(0);
	
		/*
		 * SEARCH IN DCMTMapping by a criteria
		 */
		
//		System.out.println("\n\nSEARCHING" + criteria); 
		codingUtils = new CodingUtils();
//		CodingSystem codeSystem = codingUtils.tokenizeStringBox(codingSystem);
		CodingSystem codeSystem = new CodingSystem();
//		codeSystem.setTitle(codingSystem);
		codeSystem.setTitle("HS");
		codeSystem.setRegion("0");
		System.out.println("\n\nSEARCHING" + criteria + " | " + codeSystem.getTitle()); 
		
				
		if ( criteria.equals("Code")){
			Code code = codecDao.getCodeFromCodeSystemRegion(search, codeSystem.getTitle(), codeSystem.getRegion(), langCode);
			if ( code != null){
				resultSize = 1;
			}
		}
		
		else if ( criteria.equals("Label")) {
			resultSize = codecDao.getQueryLabelSize(search, codeSystem.getTitle(), codeSystem.getRegion(), langCode);
			//	List<Code> codes = codecDao.getCodeLimitedFromLabelSystemRegion(search, codeSystem.getTitle(), codeSystem.getRegion(), startIndex, endIndex);
		}
		
		else if ( criteria.equals("Description")) {	
			/***** TODO: CHANGE QUERY ***/
			resultSize = codecDao.getQueryDescriptionSize(search, codeSystem.getTitle(), codeSystem.getRegion(), langCode);
		}
		
		else if ( criteria.equals("Coding System")) {
			resultSize = codecDao.getQueryCodingSystemSize(codeSystem.getTitle(), codeSystem.getRegion(), langCode);
		}
	
	return resultSize;
	}
	
	
	
	
	
	
	/****
	 * CONVERT FROM DCMT
	 */
	
	public  List<CodeVo> convertFromDcmt(String criteria, String search, String destinationCodeSystem, String langCode) {
		codesVo = new ArrayList<CodeVo>();
		CodingSystem codingSystem = new CodingSystem();	
		codingUtils = new CodingUtils();
		
		codingSystem = codingUtils.tokenizeStringBox(destinationCodeSystem);
//		System.out.println("destinationCodeSystem " + codingSystem.getTitle() + " " + codingSystem.getRegion());
		if ( criteria.equals("Code")){
			List<DCMTMapping> dcmtMappings = codecDao.getDcmtMappingFromDcmtCodeCodingSystem(search, codingSystem.getTitle(), codingSystem.getRegion());	
			if (!dcmtMappings.isEmpty()) {
				Code code = new Code(); 	
				for (int i = 0; i < dcmtMappings.size(); i++) {
//					System.out.println("-> " + dcmtMappings.get(i).getFromCode());
					code = codecDao.getCodeFromCodeSystemRegion(dcmtMappings.get(i).getFromCode(), codingSystem.getTitle(), codingSystem.getRegion(), langCode);
					if ( code != null ) {
//						for (int j = 0; j <  codes.size(); j++) {
						codesVo.add(codingVoConverter.code2Vo(code, dcmtMappings.get(i).getDcmtCode(), codingSystem));
//						}
					}
				}
			}
		}
		/***
		 * SURE TO BE MODIFIED, AND SEARCHING WITH THE DESTINATION CODINGSYSTEM IN DCMT MAPPING
		 * 
		 */
		else if ( criteria.equals("Label")){
			List<DCMTSingleCode> code = codecDao.getDCMTSingleCodeFromLabel(search);
			if(!code.isEmpty()) {
				for (int i = 0; i < code.size(); i++) {
//					System.out.println("-> " + code.get(i).getCode());
					List<DCMTMapping> dcmtMappings = codecDao.getMappedFromDcmtCodeContainedCodingSystem(code.get(i).getCode(), codingSystem.getTitle(), codingSystem.getRegion());
					if (!dcmtMappings.isEmpty()) {				
					
						Code codes = new Code(); 	
						for (int j = 0; j < dcmtMappings.size(); j++) {
							codes = codecDao.getCodeFromCodeSystemRegion(dcmtMappings.get(j).getFromCode(), codingSystem.getTitle(), codingSystem.getRegion(), langCode);
							if ( codes != null) {
								codesVo.add(codingVoConverter.code2Vo(codes, dcmtMappings.get(j).getDcmtCode(), codingSystem));
							}
						}
					}
				}
			}
		}
		
		else if ( criteria.equals("Description")){
			List<DCMTSingleCode> code = codecDao.getDCMTSingleCodeFromDescription(search);
			if(!code.isEmpty()) {
				for (int i = 0; i < code.size(); i++) {
					List<DCMTMapping> dcmtMappings = codecDao.getMappedFromDcmtCodeContainedCodingSystem(code.get(i).getCode(), codingSystem.getTitle(), codingSystem.getRegion());		
					if (!dcmtMappings.isEmpty()) {
						Code codes = new Code(); 	
						for (int j = 0; j < dcmtMappings.size(); j++) {
							codes = codecDao.getCodeFromCodeSystemRegion(dcmtMappings.get(j).getFromCode(), codingSystem.getTitle(), codingSystem.getRegion(), langCode);
							if ( codes != null) {
								codesVo.add(codingVoConverter.code2Vo(codes, dcmtMappings.get(j).getDcmtCode(), codingSystem));
							}
						}
					}
				}
			}
		}
		
		else if ( criteria.equals("Coding System")){
//			System.out.println("-> "+codingSystem.getTitle()+","+ codingSystem.getRegion());
			List<String> dcmtMappings = codecDao.getMappedCodeFromCodeSystem(codingSystem.getTitle(), codingSystem.getRegion());
			if (!dcmtMappings.isEmpty()) {
				Code codes = new Code(); 	
				for (int i = 0; i < dcmtMappings.size(); i++) {
//					System.out.println("-> "+dcmtMappings.get(i));
//					System.out.println("-> "+dcmtMappings.get(i).toString());
					codes = codecDao.getCodeFromCodeSystemRegion(dcmtMappings.get(i).toString(), codingSystem.getTitle(), codingSystem.getRegion(), langCode);
					if ( codes != null) {
						codesVo.add(codingVoConverter.code2Vo(codes, dcmtMappings.get(i).toString(), codingSystem));
					}
				}
			}
		}
	
		return codesVo;
	} 
	
	
	/****
	 * TODO: PER ORA CONVERTE SOLO PER CODE (no altri mapping)
	 */
	public List<CodeVo> convertInto(String criteria, String search, String sourceCodeSystem, String destinationCodeSystem, String langCode) {
		codesVo = new ArrayList<CodeVo>();
		CodingSystem codingSystem = new CodingSystem();	
		codingUtils = new CodingUtils();
//		System.out.println(destinationCodeSystem);
		CodingSystem codingSystemDst = codingUtils.tokenizeStringBox(destinationCodeSystem);
		CodingSystem codingSystemSrc = codingUtils.tokenizeStringBox(sourceCodeSystem);
		
		if ( criteria.equals("Code")){
//			System.out.println(criteria +" "+search +" "+codingSystemSrc.getTitle() + " "+codingSystemSrc.getRegion());
			List<String> code = codecDao.getMappedDCMTCodeFromCodeSystem(search, codingSystemSrc.getTitle(), codingSystemSrc.getRegion());
			if (!code.isEmpty()) {
				Code codes = new Code();
				// se cercavo come destina zione il dcmt
				if (codingSystemDst.getTitle().equals("DCMT")){
					for (int i = 0; i < code.size(); i++) {
//						System.out.println("-> "+code.get(i));
//						System.out.println("-> "+code.get(i).toString());
						/***
						 * TODO:
								// se cercava il dcmt ritorna (potremmo anche usare il translator, per dare una label di quello inserito)
							// altrimenti cercare nel il codice dcmt nel nuovo code system
						*/  
						Code c = new Code();
						c.setCode(code.get(i));
						codesVo.add(codingVoConverter.code2Vo(c, codingSystem));
					}
				}
				// non cerco il dcmt ma un'altro codingsystem
				else {
					// cerco il mapping col codingsystemdest con il dcmtCode
					for (int i = 0; i < code.size(); i++) {
//						System.out.println("-> "+code.get(i));
//						System.out.println("-> "+code.get(i).toString());
						List<String> codeDest = codecDao.getMappedCodeFromCodeSystem(code.get(i), codingSystemDst.getTitle(), codingSystemDst.getRegion());
						// riprendo tutti i codici in CODE
						for (int j = 0; j < codeDest.size(); j++) {
//							System.out.println("-> "+codeDest.get(j));
							System.out.println("-> "+codeDest.get(j).toString());
							codes = codecDao.getCodeFromCodeSystemRegion(codeDest.get(j).toString(), codingSystemDst.getTitle(), codingSystemDst.getRegion(), langCode);
							if ( codes != null) {
								codesVo.add(codingVoConverter.code2Vo(codes, codingSystem));
							}
						}
					}

					//se non c'e' cerco tramite il GeneralMapping
					/***
					 * TODO: se non ce'' cercare in GeneralMapping
					*/  					
				}
			}	
		}
		/***
		 * TODO: fare anche per le LABEL E DESCRIPTION?
		*/  
		return codesVo;
	}

	
	

	
	public List<CodeVo> searchDcmtCodes(String code, String label, String langCode, String dcmtCode, CodingSystemVo cs) {
		dcmtTranslator.initializeAll();
		/*** get coding system **/
		CodingSystem codingSystem = codecDao.getCodingSystem(cs.getTitle(), cs.getRegion());
		
		
		/*** if a dcmtCode exist put in the the dcmt structure ***/
		if (dcmtCode != null) {
			System.out.println("checking dcmt code2");
			if (!dcmtCode.isEmpty()) {
				System.out.println("checking dcmt code3" + dcmtCode);
				dcmtTranslator.fillDcmtCode(dcmtCode);
			}
		}
//		List<List<DCMTSingleCode>> resultedLabels =  dcmtTranslator.findDcmtCode(label, langCode);
		List<List<DCMTSingleCode>> resultedLabels =  dcmtTranslator.dcmtCodeFromCs(code, label, langCode, codingSystem);
		List<List<Integer>> countFrequency = dcmtTranslator.countFrequencies(resultedLabels);
		
		dcmtTranslator.sortingLabelsCounting();
//		System.out.println("Sorting");
		List<List<DCMTSingleCode>> labels =  dcmtTranslator.getResultedLabels();
		List<List<Integer>> frequency = dcmtTranslator.getResultedCounting();
		List<String> tokens = dcmtTranslator.getTokens();	

		/*** addeed **/
		List<DCMTSingleCode> c = new ArrayList<DCMTSingleCode>();
		c = dcmtTranslator.returnUniqueItems(labels);
		
		List<CodeVo> codesVo = new ArrayList<CodeVo>();
		for (int i=0; i< c.size(); i++) {
			codesVo.add(codingVoConverter.dcmtSingleCode2Vo(c.get(i)));
		}
		return codesVo;
	}
	

	

	public String updateInsertDcmtCode(List<String> allElements, List<String> allAttributes, Boolean excluding) {
		String code = "";
		List<String> elements = tokenize(allElements);
		List<String> attributes = tokenize(allAttributes);
		
		for(int i=0; i< elements.size(); i++) {
//			System.out.println(elements.get(i) + " " + attributes + " " + excluding);
			/** if elements is pressed are pressed without allattributes (excluding true) **/
			if ( excluding == true && attributes.isEmpty()) {
				String e = elements.get(i);
				dcmtTranslator.excludeElement(e);
			} 
			/** if elements are pressed and there are attributes (excluding true) **/
			else if (excluding == true && !attributes.isEmpty()) {
				String e = elements.get(i);
				List<String> a = new ArrayList<String>();
				a = attributes;
//				dcmtTranslator.insertNotAttributesCommodity(e, a);
			}
			else if ( excluding == false ) {
				String e = elements.get(i);
				List<String> a = new ArrayList<String>();
				a = attributes;
				dcmtTranslator.insertAttributesCommodity(e, a);
			} 
		}
		code = dcmtTranslator.createCode();
		return code;
	}
	


	public String updateRemovedAttrDcmtCode(String element, String attribute) {
		String code = new String();
//		System.out.println("updateRemovedAttrDcmtCode: removing");
		StringTokenizer tokenizer = new StringTokenizer(element, "]");
		String e = tokenizer.nextToken().trim().toLowerCase();
		e = e.substring(1);
		tokenizer = new StringTokenizer(attribute, "]");
		String a = tokenizer.nextToken().trim().toLowerCase();
		a = a.substring(1);
//		System.out.println("elem: "+ e + " --- attr: "+ a );
		dcmtTranslator.removeAttributeElement(e, a);
		code = dcmtTranslator.createCode();
		return code;
	}


	public String updateRemovedElemDcmtCode(String element) {
//		System.out.println("updateRemovedElemDcmtCode: removing");
		String code = new String();
		StringTokenizer tokenizer = new StringTokenizer(element, "]");
		String e = tokenizer.nextToken().trim().toLowerCase();
		e = e.substring(1);
		dcmtTranslator.removeElement(e);
		code = dcmtTranslator.createCode();
		return code;
	}

	
	
	
	public List<String> tokenize(List<String> strings){
		List<String> tokStrings = new ArrayList<String>();
		for(int i=0; i< strings.size(); i++) {
			StringTokenizer tokenizer = new StringTokenizer(strings.get(i), "]");
			String s = tokenizer.nextToken().trim().toLowerCase();
			s = s.substring(1);
			tokStrings.add(s);
//			System.out.println(tokStrings.get(i));	
		}
		return tokStrings;	
	}
	
	

	public HashMap<String, List<List<String>>> getHashMapElements() {
		return dcmtTranslator.getElements();
	}

	public List<List<String>> getElementsAttributes(String langCode) {
		List<List<String>> elementsAttributes = new ArrayList<List<String>>();
		List<Integer> elementsList = dcmtTranslator.getElementsList();
		HashMap<String, List<List<String>>> hashMap = dcmtTranslator.getElements();	
		
		/** for each element takes its label and its attributes with the label ***/ 
		for(int i=0; i< elementsList.size(); i++){
			List<String> allElem = new ArrayList<String>();
			String elementCode = elementsList.get(i).toString();
			String elementLabel = codecDao.getDCMTLabelFromCode(elementCode, langCode);
			/** add element **/
			allElem.add("[" + elementCode + "]" + " " + elementLabel);
			/** add attributes **/
			List<List<String>> attributes = hashMap.get(elementCode);
			for(int j=0; j < attributes.get(0).size(); j++) { // FIXME TODO use StringBuilder !!!
				allElem.add("[" + attributes.get(0).get(j) + "]" + " " + codecDao.getDCMTLabelFromCode(attributes.get(0).get(j), langCode));
			}
			elementsAttributes.add(allElem);
		}
		return elementsAttributes;
	}
	
	public List<List<String>> getElementsNotAttributes(String langCode) {
		List<List<String>> elementsNotAttributes = new ArrayList<List<String>>();
		List<Integer> elementsList = dcmtTranslator.getElementsList();
		HashMap<String, List<List<String>>> hashMap = dcmtTranslator.getElements();
		
		/** for each element takes his label and them attributes with the label ***/ 
		for(int i=0; i< elementsList.size(); i++){
			List<String> allElem = new ArrayList<String>();
			String elementCode = elementsList.get(i).toString();
			String elementLabel = codecDao.getDCMTLabelFromCode(elementCode, langCode);
			/** add element **/
			allElem.add("[" + elementCode + "]" + " " + elementLabel);
			/** add attributes **/
			List<List<String>> notAttributes = hashMap.get(elementCode);
			for(int j=0; j < notAttributes.get(1).size(); j++) {
				allElem.add("[" + notAttributes.get(1).get(j) + "]" + " " + codecDao.getDCMTLabelFromCode(notAttributes.get(1).get(j), langCode));
			}
			elementsNotAttributes.add(allElem);
		}
		return elementsNotAttributes;
	}
	
	public List<String> getNotElements(String langCode) {
		List<String> notElements = new ArrayList<String>();
		List<Integer> notElementsInt = dcmtTranslator.getNotElements();
		for(int i=0; i< notElementsInt.size(); i++) {
			notElements.add("[" + notElementsInt.get(i).toString() + "]" + " " + codecDao.getDCMTLabelFromCode(notElementsInt.get(i).toString(), langCode));
		}
		return notElements;
	}
	
	

	public List<CodeVo> findFathers(String item, String langCode) {
		List<CodeVo> codeVo = new ArrayList<CodeVo>();
		List<DCMTSingleCode> code = new ArrayList<DCMTSingleCode>();
		List<DCMTStructure> dcmtStructure = new ArrayList<DCMTStructure>();
		if (item.equals("elements")) 
			dcmtStructure = codecDao.findDCMTStructureBase(false);
		else
			dcmtStructure = codecDao.findDCMTStructureBase(true);
//		System.out.println("langCode " + langCode);
		for(int i=0; i< dcmtStructure.size(); i++) {
			code = codecDao.getDCMTSingleCodeFromCode(dcmtStructure.get(i).getCode(), langCode);
			//System.out.println(dcmtStructure.get(i).getCode() + " " + dcmtStructure.get(i).getFather());
			codeVo.add(codingVoConverter.code2Vo(code.get(0)));
		}
		return codeVo;
	}
	
	


	
	public List<CodeVo> findChildren(String father, String langCode) {
		List<CodeVo> codeVo = new ArrayList<CodeVo>();
		List<DCMTSingleCode> code = new ArrayList<DCMTSingleCode>();
		List<DCMTStructure> dcmtStructure = codecDao.findChildren(father);
//		System.out.println("children langCode =" + langCode);
		for(int i=0; i< dcmtStructure.size(); i++) {
			code = codecDao.getDCMTSingleCodeFromCode(dcmtStructure.get(i).getCode(), langCode);
			codeVo.add(codingVoConverter.code2Vo(code.get(0)));
		}
		return codeVo;
	}


	// FIXME Simone
	public String exportAsCSV(String system, String region, List<DcmtCodingCreatorGridMD>  modifiedCodes){
		return "porco giuda";
	}


	public List<MappingVo> getMappedCodes(String cs) {
		List<MappingVo> mappingVo = new ArrayList<MappingVo>();
		List<DCMTMapping> dcmtMapping = new ArrayList<DCMTMapping>();
		codingUtils = new CodingUtils();
		CodingSystem codingSystem = codingUtils.tokenizeStringBox(cs);
		dcmtMapping = codecDao.getDcmtMappingFromCodeSystem(codingSystem.getTitle(), codingSystem.getRegion());
		for(int i=0; i< dcmtMapping.size(); i++) {
			mappingVo.add(codingVoConverter.mapping2Vo(dcmtMapping.get(i)));
		}
		return mappingVo;
	}
	
	
	
	public String getDcmtLabel(String dcmtCode, String langCode){
		String label = dcmtTranslator.getDcmtLabel(dcmtCode, langCode);
		return label;
	}

	
	/***
	 * checks if the code is wrote in a right way
	 * 
	 * @param dcmtCode
	 */
	public String testCode(String dcmtCode){
		Boolean check = dcmtTranslator.testCode(dcmtCode);
		if ( check) 
			return dcmtTranslator.testStructureDcmtCode(dcmtCode);
		else
			return check.toString();
	}
	
	/***
	 * checks if the hierarchic structure of the code is right
	 * 
	 * @param dcmtCode
	 */
	public String testHierarchicStructure(){
		String hierachic = dcmtTranslator.testHierarchicStructure();
		if ( !hierachic.equals("ok"))
			return hierachic;
		else
			return new String();
	}
	
	
	/*******
	 * Save an item to the db
	 */
	
	public void saveNewItem(String label, String langCode, String description, String father, String item) {
		/*** get a new code **/
//		System.out.println(label + " " + langCode + " " + father + " " + item);
		Boolean isAttribute = false;
		String code = new String();
		if (item.equals("element")) { 
			isAttribute = false;
			code = codecDao.generateElementCode();
		}
		else {
			isAttribute = true;
			code = codecDao.generateAttributeCode();
		}
		/***TODO: check if the label is not already in ***/

//		System.out.println(label + " " + langCode + " " + father + " " + item + " " + code);
		DCMTSingleCode dcmtSingleCode = new DCMTSingleCode();
		dcmtSingleCode.setCode(code);
		dcmtSingleCode.setLabel(label);
		dcmtSingleCode.setLangCode(langCode);
		dcmtSingleCode.setDescription(description);
		dcmtSingleCode.setIsAttribute(isAttribute);
		
		DCMTStructure dcmtStructure = new DCMTStructure();
		dcmtStructure.setCode(code);
		dcmtStructure.setFather(father);
		dcmtStructure.setIsAttribute(isAttribute);
		
		/*** import dcmtSingleCode and dcmtStructure **/
		dcmtImporter.saveNewItem(dcmtSingleCode, dcmtStructure);

	}
	
	
	
	public String tokenizeOutput(String response){
		String error = new String();
		int index1 = response.indexOf("Bad");
		int index2 = response.indexOf("org.fao.fenix.web.modules.core.server.upload"); // FIXME
		if ( index1 >0 && index2 >0)
			error = response.substring(index1, index2);//error.
		return error;
	}
	
	
	
	

	public List<CodingSystemVo> findCsWithHierarchy(){
		List<CodingSystemVo> codingSystems = new ArrayList<CodingSystemVo>();
		List<Long> codingSystemsIds = codecDao.getCodingHierarchyIDs();

		for(Long codingSystemsId : codingSystemsIds) {
			CodingSystem codingSystem = codecDao.getCodingSystemFromId(codingSystemsId);
			CodingSystemVo csvo = new CodingSystemVo();
			csvo.setTitle(codingSystem.getTitle());
			csvo.setRegion(codingSystem.getRegion());
			codingSystems.add(csvo);
		}
		return codingSystems;
	}
	
	
	
	public List<List<String>> getCsDescriptions(Long cs_id, String code) {
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> descriptions = codecDao.getCodingHierarchyDescriptions(cs_id);
		List<String> positions = new ArrayList<String>();
		List<String> codeLevel = new ArrayList<String>();
		for(String description : descriptions) {	
			
			Integer position = codecDao.getCodingHierarchyLevel(cs_id, description);
			positions.add(position.toString());
			System.out.println(description + " | " + position.toString() );
		}
		codeLevel.add(codecDao.getCodingHierarchyCodeLevel(cs_id, code).toString());

		results.add(positions);
		results.add(descriptions);
		results.add(codeLevel);
		return results;
	}
	
	
	
	

	public List<List<CodeVo>> getCodes(Long cs_id, List<CodingHiearachyMD> codeList, Integer codeLevel, Integer level) {
		List<List<CodeVo>> codesVo = new ArrayList<List<CodeVo>>();
		List<CodingHierarchy> ch = new ArrayList<CodingHierarchy>();
		List<CodeVo> chCodes = new ArrayList<CodeVo>();
		List<CodingHierarchy> chUp = new ArrayList<CodingHierarchy>();
		List<CodeVo> chUpCodes = new ArrayList<CodeVo>();
		int i=0;
		
		/***
		 * PROBLEMA SE CI SONO PIU' di un LIVELLo
		 */
		List<String> parents = new ArrayList<String>();
	
		
		for(CodingHiearachyMD code : codeList) {
			ch = new ArrayList<CodingHierarchy>();
			chUp = new ArrayList<CodingHierarchy>();
			
			/*** should be about what is retrieved not the parent but the latest parent ***/
			if (level < codeLevel) {
				/*** search parents ***/
				Boolean check = false;
				
				ch = codecDao.getCodingHierarchyUpNLevels(cs_id, code.getCode(), codeLevel - level);
				for(String parent : parents){
					if(parent.equals(ch.get(0).getCode())) {
						check = true;
						ch = new ArrayList<CodingHierarchy>();
						break;					
					}
				}
				if ( !check) {
					parents.add(ch.get(0).getCode());
				}
				if ( i == 0) {
					i++;
//					System.out.println(" | " + i + " | " + code.getCode());
					chUp = codecDao.getCodingHierarchyLvl(cs_id, ch.get(0).getCode(), ch.get(0).getParent());
				}
			}
			else if (level > codeLevel)
				/*** search children ***/
				ch = codecDao.getCodingHierarchyDownNLevels(cs_id, code.getCode(), level - codeLevel);
			else if (level == codeLevel)
				/*** same level ***/
				ch = codecDao.getCodingHierarchySameLevel(cs_id, code.getCode());
		
			
			/*** clean the double entries ***/
			for (CodingHierarchy c : ch) {
				c.setLabel(codecDao.getLabelFromCodeCodingSystem(c.getCode(), cs_id));
//				System.out.println(c.getLabel());
				chCodes.add(codingVoConverter.code2Vo(c));
			}

			
			for (CodingHierarchy c : chUp) {
				Boolean check = false;
				for(CodingHierarchy ch1 : ch){
					if(c.getCode().equals(ch1.getCode())) {
						check = true;
						break;					
					}
				}
				if ( !check) { 
					c.setLabel(codecDao.getLabelFromCodeCodingSystem(c.getCode(), cs_id));
//					System.out.println(c.getLabel());
					chUpCodes.add(codingVoConverter.code2Vo(c));
				}
			}

		}
		List<String> a = new ArrayList<String>();
		
		
		codesVo.add(chCodes);
		codesVo.add(chUpCodes);
		
		return codesVo;
	}
	
	
	public List<CodeVo> initializeHierarchyGrid(Long cs_id, String code) {
		List<CodeVo> codesVo = new ArrayList<CodeVo>();
		List<CodingHierarchy> ch = codecDao.getCodingHierarchySameLevel(cs_id, code);
		
		for (CodingHierarchy c : ch)
			codesVo.add(codingVoConverter.code2Vo(c));

		
		return codesVo;
	}
	
	
//	@SuppressWarnings("unchecked")
//	public PagingLoadResult<DatasetRowModel> getHierarchicData(final PagingLoadConfig config,) {
//		//if(tableMap==null || !tableMap.containsKey(datasetId)){
//		
//		System.out.println("--- getFilteredData = "+ caller +" ----------------------");
//		if(tableMap==null || caller!=null){
//			System.out.println("--- LOADING NEW FILTERED DATA -----------------");
//		   loadFilteredData(datasetId, headerList, filterCriteria);
//		} 
//
//		if (config.getSortInfo().getSortField() != null) {
//			final String sortField = config.getSortInfo().getSortField();
//			if (sortField != null) {
//				Collections.sort(TableDataCache.getTableData(datasetId), config.getSortInfo().getSortDir().comparator(new Comparator() {
//					public int compare(Object o1, Object o2) {
//						DatasetRowModel p1 = (DatasetRowModel) o1;
//						DatasetRowModel p2 = (DatasetRowModel) o2;
//
//						for (int i = 0; i < headerList.size(); i++) {
//							String columnId = "column"+i;
//
//							if (sortField.equals(columnId)) {
//								return p1.getColumnValue(columnId).compareTo(p2.getColumnValue(columnId));
//							} 
//
//						}
//						return 0;
//					}
//				}));
//			}
//		}
//
//		ArrayList<DatasetRowModel> sublist = new ArrayList<DatasetRowModel>();
//		int start = config.getOffset();
//		int limit = TableDataCache.getTableData(datasetId).size();
//		if (config.getLimit() > 0) {
//			limit = Math.min(start + config.getLimit(), limit);
//		}
//		for (int i = config.getOffset(); i < limit; i++) {
//			sublist.add(TableDataCache.getTableData(datasetId).get(i));
//		}
//		return new BasePagingLoadResult(sublist, config.getOffset(), TableDataCache.getTableData(datasetId).size());
//	}
	
	
	/***************************************************************
	 * 				CODE LIST IMPORTER 
	 ****************************************************************/

	/*
	 * This method is called from the Main Menu and is to be used by country installations
	 *  to allow the import of the default code lists (HS, GAUL etc) which have been made available in the installer
	 */
	public void importDefaultCodeLists() throws FenixGWTException {
		try {
			codeListImporter.importCodeList();
		} catch(FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}


	public void setDcmtImporter(DcmtImporter dcmtImporter) {
		this.dcmtImporter = dcmtImporter;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setPopulateCoding(PopulateCoding populateCoding) {
		this.populateCoding = populateCoding;
	}

	public void setDcmtTranslator(DcmtTranslator dcmtTranslator) {
		this.dcmtTranslator = dcmtTranslator;
	}

	public void setCodeListImporter(CodeListImporter codeListImporter) {
		this.codeListImporter = codeListImporter;
	}


	/** THIS IS USES THE Coding System CODE!!!! **/
	public List<CodeVo> getCodes(List<String> codes, String system, String langCode) {
		List<CodeVo> codesVo = new ArrayList<CodeVo>();
		List<Code> result = codecDao.getCodesFromCode(codes, system, langCode);
		
		
		for (Code c : result) 
			codesVo.add(new CodeVo(c.getCode(), c.getLabel()));			
		
		return codesVo;
	}







	
	
	

	
	
}