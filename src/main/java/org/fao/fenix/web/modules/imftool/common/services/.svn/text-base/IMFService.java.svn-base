package org.fao.fenix.web.modules.imftool.common.services;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.imftool.common.vo.IMFToolVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface IMFService extends RemoteService {

	public IMFToolVO getLastDataInfo() throws FenixGWTException;

	/**
	 * Send the data to the <code>email</code> address and return the ID of the
	 * saved <code>IMFStats</code>.
	 * 
	 * @param vo
	 *            Contains the recipient's email address and the URLs of the
	 *            IFM's XMLs.
	 * @param hasCPI
	 * @param hasExchangeRates
	 * @return ID of the saved <code>IMFStats</code>.
	 * @throws FenixGWTException
	 */
	public Long sendData(IMFToolVO vo, boolean hasCPI, boolean hasExchangeRates) throws FenixGWTException;

}