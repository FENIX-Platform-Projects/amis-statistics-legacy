package org.fao.fenix.web.modules.ipc.server.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.ipc.common.vo.BulletPointVO;
import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;

public class IPCXMLWriter {

	private static final Logger LOGGER = Logger.getLogger(IPCXMLWriter.class);
	
	private String path = "/home/kalimaha/Scrivania"; // TODO change this, add the path in the application context
	
	public static List<ModuleVO> test() {
		List<ModuleVO> modules = new ArrayList<ModuleVO>();
		
		
		ModuleVO module = new ModuleVO();
		module.setLevel(1);
		
		///
		List<FreeTextVO> freeTextsVO = new ArrayList<FreeTextVO>();
		FreeTextVO freeTextVO = new FreeTextVO();
		freeTextVO.setName("Crude Mortality Rate");
		freeTextVO.setCode("FT-00");
		freeTextVO.setRangeCode("00");
		freeTextVO.setRangeLabel("< 0.5 / 10,000 / day");
		freeTextVO.setExactValue(new Double(365));
		freeTextVO.setLevelCode("00");
		freeTextVO.setLevelLabel("Generally Food Secure 1A");
		List<BulletPointVO> bulletPoints = new ArrayList<BulletPointVO>();
		BulletPointVO bulletPoint = new BulletPointVO();
		bulletPoint.setText("asdsad");
		bulletPoint.setDirectEvidence(false);
		bulletPoints.add(bulletPoint);
		freeTextsVO.add(freeTextVO);
		freeTextsVO.add(freeTextVO);
	
		freeTextsVO.add(freeTextVO);
		
	
	
		
		////
		List<DropDownVO> dropDownsVO = new ArrayList<DropDownVO>();
		DropDownVO dropDownVO = new DropDownVO();
		dropDownVO.setCode("23");
		dropDownVO.setName("name");
		dropDownVO.setDropDownCode("1212");
		dropDownVO.setDropDownLabel("label");
		dropDownsVO.add(dropDownVO);
		dropDownsVO.add(dropDownVO);
		dropDownsVO.add(dropDownVO);
		dropDownsVO.add(dropDownVO);
		
		
		///
		module.setDropDowns(dropDownsVO);
		module.setFreeTexts(freeTextsVO);
		
		
		modules.add(module);
		return modules;
	}
	
	/** CREATING THE XML **/
	
	public static String createIPCXmlFile(List<ModuleVO> modulesVo) {
		StringBuilder ipcXml = new StringBuilder();
		ipcXml.append("<ipc>\n");
		System.out.println("modules: " + modulesVo.size() );
		for(ModuleVO moduleVo : modulesVo ) {
			ipcXml.append(createModule(moduleVo));
		}
		ipcXml.append("</ipc>");
//		try {
//			File file = new File("/home/vortex/Scrivania/test.xml");
//			FileOutputStream stream = new FileOutputStream(file);
//			stream.write(ipcXml.toString().getBytes());
//			stream.close();
//			LOGGER.info("IPC xml complete!");
//			return file;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		String xml = ipcXml.toString();
		return xml;
	}
	
	private static StringBuffer createModule(ModuleVO moduleVo) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<module level=\""+  moduleVo.getLevel() + "\">\n");
		if ( moduleVo.getFreeTexts() != null) {
			for(FreeTextVO freeTextVO : moduleVo.getFreeTexts())
				buffer.append(createFreeText(freeTextVO));
		}
		if ( moduleVo.getDropDowns() != null) {
			for(DropDownVO dropDownVo : moduleVo.getDropDowns())
				buffer.append(createDropDown(dropDownVo));
		}
		buffer.append("</module>\n");
		return buffer;
	}
	
	private static StringBuffer createDropDown(DropDownVO dropDownVo) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<dropDown name=\""+  dropDownVo.getName() + "\" code=\"" + dropDownVo.getCode() +"\">\n");
		buffer.append("<dropDownCode>");
		if ( dropDownVo.getDropDownCode() != null)
			buffer.append(dropDownVo.getDropDownCode());
		buffer.append("</dropDownCode>\n");
		buffer.append("<dropDownLabel>");
		if ( dropDownVo.getDropDownLabel() != null)
			buffer.append(dropDownVo.getDropDownLabel());
		buffer.append("</dropDownLabel>\n");	
		buffer.append("</dropDown>\n");
		return buffer;
	}
	
	private static StringBuffer createFreeText(FreeTextVO freeTextVO) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<freeText name=\""+  freeTextVO.getName() + "\" code=\"" + freeTextVO.getCode() +"\">\n");
		if ( freeTextVO.getRangeCode() != null)
			buffer.append(createRangeCode(freeTextVO));
		if ( freeTextVO.getRangeLabel() != null)
			buffer.append(createRangeLabel(freeTextVO));
		if ( freeTextVO.getExactValue() != null)
			buffer.append(createExactValue(freeTextVO));
		if ( freeTextVO.getLevelCode() != null)
			buffer.append(createLevelCode(freeTextVO));
		if ( freeTextVO.getRangeLabel() != null)
			buffer.append(createLevelLabel(freeTextVO));
		if ( freeTextVO.getBulletPoints() != null)
			buffer.append(createBulletPoints(freeTextVO.getBulletPoints()));
		buffer.append("</freeText>\n");
		return buffer;
	}
	
	private static StringBuffer createRangeCode(FreeTextVO freeTextVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<rangeCode>");
		buffer.append(freeTextVO.getRangeCode());		
		buffer.append("</rangeCode>\n");
		return buffer;
	}
	
	private static StringBuffer createRangeLabel(FreeTextVO freeTextVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<rangeLabel>");
		buffer.append(getRangeLabel(freeTextVO));		
		buffer.append("</rangeLabel>\n");
		return buffer;
	}
	
	private static StringBuffer createExactValue(FreeTextVO freeTextVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<exactValue>");
		buffer.append(freeTextVO.getExactValue());		
		buffer.append("</exactValue>\n");
		return buffer;
	}
	
	private static StringBuffer createLevelCode(FreeTextVO freeTextVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<levelCode>");
		buffer.append(freeTextVO.getLevelCode());		
		buffer.append("</levelCode>\n");
		return buffer;
	}
	
	private static StringBuffer createLevelLabel(FreeTextVO freeTextVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<levelLabel>");
		buffer.append(freeTextVO.getLevelLabel());		
		buffer.append("</levelLabel>\n");
		return buffer;
	}
	
	private static StringBuffer createBulletPoints(List<BulletPointVO> bulletPointsVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<bulletPoints>\n");
		for(BulletPointVO bulletPointVO : bulletPointsVO)
			buffer.append(createBulletPoint(bulletPointVO));
		buffer.append("</bulletPoints>\n");
		return buffer;
	}
	
	private static StringBuffer createBulletPoint(BulletPointVO bulletPointVO){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<bulletPoint>\n");
		buffer.append("<text>");
//		buffer.append(bulletPointVO.getText());
		buffer.append(getTextArea(bulletPointVO.getText()));
		buffer.append("</text>");
		buffer.append("<directEvidence>");
		buffer.append(bulletPointVO.getDirectEvidence());
		buffer.append("</directEvidence>");	
		buffer.append("</bulletPoint>\n");
		return buffer;
	}
	
	private static StringBuffer getRangeLabel(FreeTextVO freeTextVO) {
		StringBuffer buffer = new StringBuffer();	
		freeTextVO.setRangeLabel(freeTextVO.getRangeLabel().replace("<", "&lt;"));
		freeTextVO.setRangeLabel(freeTextVO.getRangeLabel().replace(">", "&gt;"));
		buffer.append(freeTextVO.getRangeLabel());
		return buffer;
	}	
	
	private static String getTextArea(String textArea) {
		String buffer = new String();
		LOGGER.info("BulletPoint: " + textArea);
		buffer = textArea.replace("<", "&lt;");
		buffer = buffer.replace(">", "&gt;");
		System.out.println(buffer);
		return buffer;
	}	
	
	private static void print(ModuleVO vo) {
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
	
}