package org.fao.fenix.web.modules.imftool.server;

import java.util.Date;

import org.apache.log4j.Logger;
import org.fao.fenix.core.utils.IMFUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.imftool.common.services.IMFService;
import org.fao.fenix.web.modules.imftool.common.vo.IMFToolVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class IMFServiceImpl extends RemoteServiceServlet implements IMFService {

	private static final Logger LOGGER = Logger.getLogger(IMFServiceImpl.class);
	
	private IMFUtils imfUtils;
	
	public IMFToolVO getLastDataInfo() throws FenixGWTException {
		IMFToolVO vo = new IMFToolVO();
		Date d = new Date();
		d = imfUtils.getLastUpdate(d);
		String exchangeRates = imfUtils.getExchangeRatesFilenameFromDate(d);
		String cpi = imfUtils.getCPIFilename(exchangeRates);
		vo.setCpiURL(cpi);
		vo.setExchangeRatesURL(exchangeRates);
		vo.setLastUpdate(imfUtils.formatDate(d));
		return vo;
	}
	
	public Long sendData(IMFToolVO vo, boolean hasCPI, boolean hasExchangeRates) throws FenixGWTException {
		Long statsID = imfUtils.sendData(vo.getEmail(), vo.getExchangeRatesURL(), vo.getCpiURL());
		LOGGER.info("IMF Data sent to " + vo.getEmail() + ". Stats stored with ID: " + statsID);
		return statsID;
	}

	public void setImfUtils(IMFUtils imfUtils) {
		this.imfUtils = imfUtils;
	}
		
}