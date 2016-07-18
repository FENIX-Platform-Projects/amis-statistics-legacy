package org.fao.fenix.web.modules.cpf.server;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.cpf.CPFDao;
import org.fao.fenix.core.persistence.file.FileFactory;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.cpf.common.constants.CPFConstants;
import org.fao.fenix.web.modules.cpf.common.services.CPFService;
import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;
import org.fao.fenix.web.modules.cpf.common.vo.CPFResultVO;
import org.fao.fenix.web.modules.table.server.TableExcel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CPFServiceImpl extends RemoteServiceServlet implements
		CPFService {

	FileFactory fileFactory;
	private CPFDao cpfDao;
	
	private final static Logger LOGGER = Logger.getLogger(CPFServiceImpl.class);
	
	private TableExcel tableExcel;

	
	public CPFServiceImpl() {

	}


	public CPFResultVO askCPF(CPFQueryVO qvo) throws FenixGWTException {

		LOGGER.info("askCPF START");
		// outputs
		CPFResultVO rvo = new CPFResultVO();
		rvo.setOutput(qvo.getOutput());
		
		// create the appropriate object
		CPFConstants o = CPFConstants.valueOf(qvo.getOutput());
		CPFConstants ot = CPFConstants.valueOf(qvo.getTypeOfOutput());
	
		switch (o) {
		case CODING_SYSTEM:
			switch (ot) {
			case CPF_NAMES:
				rvo = CPFGetCodingSystem.getAllCPFNames(qvo, rvo, cpfDao);
				break;
			case CPF_CODES:
				rvo = CPFGetCodingSystem.getAllCPFCodes(qvo, rvo, cpfDao);
				break;	
			case COUNTRIES:
				rvo = CPFGetCodingSystem.getAllCountries(qvo, rvo, cpfDao);
				break;
			case FAO_FRAMEWORK:
				rvo = CPFGetCodingSystem.getAllFAOFramework(qvo, rvo, cpfDao);
				break;	
			}
		}

		// return the result bean
		rvo.setTitle(qvo.getTitle());
		rvo.setImageWidth(qvo.getWidth() + "px");
		rvo.setImageHeight(qvo.getHeight() + "px");
		rvo.setOutput(qvo.getOutput());
		rvo.setTypeOfOutput(qvo.getTypeOfOutput());
	

		LOGGER.info("askCPF END");
		return rvo;
	}

	
	public String export(CPFResultVO rvo) {

		// TODO: change it...
		String filename = tableExcel.createExcel(rvo.getTitle(), rvo.getTableHeaders(), rvo
				.getTableContents(), rvo.getTableHeaders().size()-1);

		return filename;
	}

	/**public CPFDao getCPFDao() {
		return CPFDao;
	}

	public void setCPFDao(CPFDao CPFDao) {
		this.CPFDao = CPFDao;
	}
**/
	public void setTableExcel(TableExcel tableExcel) {
		this.tableExcel = tableExcel;
	}

	public void setFileFactory(FileFactory fileFactory) {
		this.fileFactory = fileFactory;
	}
	
	public CPFDao getCpfDao() {
		return cpfDao;
	}

	public void setCpfDao(CPFDao cpfDao) {
		this.cpfDao = cpfDao;
	}

}