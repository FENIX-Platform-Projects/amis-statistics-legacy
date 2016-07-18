package org.fao.fenix.web.modules.ipc.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.ipc.IPCProvincesInfo;
import org.fao.fenix.core.domain.perspective.FenixDomainUser;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.SaveDao;
import org.fao.fenix.core.persistence.IPCReport.IPCDao;
import org.fao.fenix.core.persistence.perspective.FenixDomainUserDao;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.server.utils.CodingVoConverter;
import org.fao.fenix.web.modules.ipc.common.services.IPCXMLService;
import org.fao.fenix.web.modules.ipc.common.vo.BulletPointVO;
import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.IPCMapValuesVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.ipc.server.xml.IPCXMLParser;
import org.fao.fenix.web.modules.ipc.server.xml.IPCXMLWriter;
import org.springframework.security.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class IPCXMLServiceImpl extends RemoteServiceServlet implements IPCXMLService {

	private static Map<Integer, String[]> freeTextMap;
	
	private static Map<Integer, String[]> dropDownMap;
	
	CodecDao codecDao;
	
	SaveDao saveDao;
	
	FenixDomainUserDao fenixDomainUserDao;
	
	IPCDao ipcDao;
	
	static {
		freeTextMap = new HashMap<Integer, String[]>();
		freeTextMap.put(1, new String[]{"FT-00", "FT-01", "FT-02", "FT-03", "FT-04", "FT-05", "FT-06", "FT-07", "FT-08", "FT-09", "FT-10", "FT-11"});
		freeTextMap.put(2, new String[]{"FT-12"});
		freeTextMap.put(3, new String[]{"FT-13", "FT-14", "FT-15", "FT-16", "FT-17", "FT-18"});
		freeTextMap.put(4, new String[]{"FT-19", "FT-20", "FT-21", "FT-22", "FT-23", "FT-24", "FT-25", "FT-26", "FT-27", "FT-28", "FT-29", "FT-30", "FT-31"});
		dropDownMap = new HashMap<Integer, String[]>();
		dropDownMap.put(2, new String[]{"DD-00", "DD-01"});
		dropDownMap.put(3, new String[]{"DD-02", "DD-03"});
		dropDownMap.put(4, new String[]{"DD-04", "DD-05", "DD-06", "DD-07", "DD-08", "DD-09", "DD-10"});
	}
	
	
	public List<ProvinceVO> getIPCMapValues(List<ProvinceVO> provincesVO) {
		IPCXMLParser parser = new IPCXMLParser();		
		for(ProvinceVO province : provincesVO) {
			DropDownVO pp = parser.findDropDown(province.getXml(), "DD-00", false);
			DropDownVO rl = parser.findDropDown(province.getXml(), "DD-01", false);
			DropDownVO pt = parser.findDropDown(province.getXml(), "DD-03", false);
			province.setPhaseClassification(pp.getDropDownCode());
			province.setRiskLevel(rl.getDropDownCode());
			province.setProjectedTrend(pt.getDropDownCode());
		} 
		return provincesVO;
	}
	
	public IPCMapValuesVO getIPCMapValues(String xml) {
		IPCMapValuesVO vo = new IPCMapValuesVO();
		IPCXMLParser parser = new IPCXMLParser();
		DropDownVO pp = parser.findDropDown(xml, "DD-00", false);
		DropDownVO rl = parser.findDropDown(xml, "DD-01", false);
		DropDownVO pt = parser.findDropDown(xml, "DD-03", false);
		vo.setProjectedPhase(pp.getDropDownCode());
		vo.setRiskLevel(rl.getDropDownCode());
		vo.setProjectedTrend(pt.getDropDownCode());
		return vo;
	}
	
	public List<ModuleVO> getAllModules(String filename, boolean isFromFile) {
		List<ModuleVO> list = new ArrayList<ModuleVO>();
		for (int i = 1 ; i < 5 ; i++)
			list.add(getModule(filename, i, isFromFile));
		return list;
	}
	
	public ModuleVO getModule(String filename, Integer level, boolean isFromFile) {
		IPCXMLParser parser = new IPCXMLParser();
		ModuleVO vo = new ModuleVO();
		vo.setLevel(level);
		String[] freeTextCodes = freeTextMap.get(level);
		if (freeTextCodes != null) 
			for (String freeTextCode : freeTextCodes)
				vo.addFreeTextVO(parser.findFreeText(filename, freeTextCode, isFromFile));
		String[] dropDownCodes = dropDownMap.get(level);
		if (dropDownCodes != null)
			for (String dropDownCode : dropDownCodes)
				vo.addDropDownVO(parser.findDropDown(filename, dropDownCode, isFromFile));
//		print(vo); // TODO remove
		return vo;
	}
	
	private void print(ModuleVO vo) {
		System.out.println("*******************************************************************************************************");
		System.out.println("*******************************************************************************************************");
		System.out.println("************************************************** "+vo.getLevel()+" **************************************************");
		System.out.println("*******************************************************************************************************");
		System.out.println("*******************************************************************************************************");
		if ((vo.getFreeTexts() != null) && !vo.getFreeTexts().isEmpty()) {
			for (FreeTextVO ftvo : vo.getFreeTexts()) {
				System.out.println("\tFree Text: " + ftvo.getCode());
				System.out.println("\tFree Text: " + ftvo.getName());
				System.out.println("\tFree Text: " + ftvo.getRangeCode());
				System.out.println("\tFree Text: " + ftvo.getRangeLabel());
				System.out.println("\tFree Text: " + ftvo.getExactValue());
				System.out.println("\tFree Text: " + ftvo.getLevelCode());
				System.out.println("\tFree Text: " + ftvo.getLevelLabel());
				if ((ftvo.getBulletPoints() != null) && !ftvo.getBulletPoints().isEmpty())
					for (BulletPointVO bvo : ftvo.getBulletPoints()) 
						System.out.println("\t\tBullet Point: " + bvo.getText() + " ["+bvo.getDirectEvidence()+"]");
			}
		}
		if ((vo.getDropDowns() != null) && !vo.getDropDowns().isEmpty()) {
			for (DropDownVO dvo : vo.getDropDowns()) {
				System.out.println("\tDrop Down: " + dvo.getCode());
				System.out.println("\tDrop Down: " + dvo.getName());
				System.out.println("\tDrop Down: " + dvo.getDropDownCode());
				System.out.println("\tDrop Down: " + dvo.getDropDownLabel());
			}
		}
		System.out.println();
	}
	
	public String createXml(List<ModuleVO> modulesVO){
		return IPCXMLWriter.createIPCXmlFile(modulesVO);
	}

	public HashMap<String, List<CodeVo>> getIPCCodeList(String langCode) {
		HashMap<String, List<CodeVo>> result = new HashMap<String, List<CodeVo>>();
		List<CodeVo> r = new ArrayList<CodeVo>();
		String codeList = new String();
		
		/** IPC PHASE CLASSIFICATION code list **/
		codeList = "IPC Phase Classification";
		List<Code> codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));		
		result.put("DD-00", r);
		
		/** IPC Crude Mortality Rate code list **/
		codeList = "IPC Level";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("level", r);
		
		/** IPC Crude Mortality Rate code list **/
		codeList = "IPC Crude Mortality Rate";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-00", r);
		
		/** IPC Acute malnutrition code list **/
		codeList = "IPC Acute Malnutrition";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-01", r);
		
		/** IPC Disease code list **/
		codeList = "IPC Disease";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-02", r);

		/** IPC Food Access Availability **/
		codeList = "IPC Food Access Availability";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-03", r);
		

		/** IPC Dietary diversity **/
		codeList = "IPC Dietary Diversity";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-04", r);
		
		
		/** IPC  Water access availability **/
		codeList = "IPC Water Access Availability";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-05", r);
		

		/** IPC Destitution Displacement **/
		codeList = "IPC Destitution Displacement";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-06", r);
		
		/** IPC Civil Security **/
		codeList = "IPC Civil Security";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-07", r);
		
		/** IPC Coping **/
		codeList = "IPC Coping";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-08", r);
		
		/** IPC Structural Issues **/
		codeList = "IPC Structural Issues";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-09", r);
		
		/** IPC Hazards **/
		codeList = "IPC Hazards";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-10", r);
		

		/** IPC Livelihood Assets **/
		codeList = "IPC Livelihood Assets";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("FT-11", r);
		
			
		/** IPC Risk Level **/
		codeList = "IPC Risk Level";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("DD-01", r);
		
		/** IPC Risk Level **/
		codeList = "IPC Current or Imminent Phase";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		 r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("DD-02", r);
		result.put("DD-04", r);

		
		/** IPC Projected Trend **/
		codeList = "IPC Projected Trend";
		codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
		r = new ArrayList<CodeVo>();
		for(Code code : codes)
			r.add(CodingVoConverter.code2Vo(code));
		result.put("DD-03", r);
		result.put("DD-05", r);
		result.put("DD-06", r);
		result.put("DD-07", r);
		result.put("DD-08", r);
		result.put("DD-09", r);
		result.put("DD-10", r);
		
		return result;
	}
	
	public List<ProvinceVO> getWorkflowProvinces(Long contributor_id, Long workflow_id) {
		List<ProvinceVO> provinces = new ArrayList<ProvinceVO>();
		List<IPCProvincesInfo> ipcWorkflowsInfo = ipcDao.getUserProvinces(contributor_id, workflow_id);
		for(IPCProvincesInfo province : ipcWorkflowsInfo) {
			ProvinceVO p = new ProvinceVO();
			p.setId(province.getId());
			p.setContributor_id(contributor_id);
			p.setPeriod(province.getPeriod());	
			p.setPhaseClassification(province.getPhaseClassification());
			p.setProjectedTrend(province.getProjectedTrend());
			p.setProvinceCode(province.getProvinceCode());
			p.setProvinceLabel(province.getProvinceLabel());
			p.setRiskLevel(province.getRiskLevel());
			p.setXml(province.getXml());
			p.setModules(getAllModules(province.getXml(), false));
			
			provinces.add(p);
		}
		return provinces;
	}

	


	public ProvinceVO saveXml(List<ModuleVO> modulesVO, WorkflowInfoVO workflowInfoVO, ProvinceVO provinceVO) {
		String xml = createXml(modulesVO);
		FenixDomainUser du = fenixDomainUserDao.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		/** delete existing  workflow **/
		IPCProvincesInfo ipcProvinceInfo = new IPCProvincesInfo();
		ipcProvinceInfo.setContributor(du);
		ipcProvinceInfo.setWorkflowID(workflowInfoVO.getWorkflowId());
		ipcProvinceInfo.setIsEmpty(true);
		ipcProvinceInfo.setPeriod(workflowInfoVO.getPeriod());
		ipcProvinceInfo.setProvinceCode(provinceVO.getProvinceCode());
		ipcProvinceInfo.setProvinceLabel(provinceVO.getProvinceLabel());
		ipcProvinceInfo.setXml(xml);
		
		provinceVO.setXml(xml);
		provinceVO.setContributor_id(du.getId());
		
		//System.out.println(xml);
		saveDao.save(ipcProvinceInfo);
		
		return provinceVO;
	}
	
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setSaveDao(SaveDao saveDao) {
		this.saveDao = saveDao;
	}

	public void setFenixDomainUserDao(FenixDomainUserDao fenixDomainUserDao) {
		this.fenixDomainUserDao = fenixDomainUserDao;
	}
	
	public void setIpcDao(IPCDao ipcDao) {
		this.ipcDao = ipcDao;
	}

	
	public List<ProvinceVO> saveAllXml(List<List<ModuleVO>> modulesVO, WorkflowInfoVO workflowInfoVO, List<ProvinceVO> provinceVO) {
		List<ProvinceVO> result = new ArrayList<ProvinceVO>();
		for(int i=0; i <  modulesVO.size(); i++) 
			result.add(saveXml(modulesVO.get(i), workflowInfoVO, provinceVO.get(i)));	
		return result;
	}

	
	
}