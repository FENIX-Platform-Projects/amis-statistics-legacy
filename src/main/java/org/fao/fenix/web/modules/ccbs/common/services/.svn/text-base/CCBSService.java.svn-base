package org.fao.fenix.web.modules.ccbs.common.services;

import org.fao.fenix.web.modules.ccbs.common.vo.Book;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface CCBSService extends RemoteService
{
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER" })
	public Integer populateCCBS();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER" })
	public Book getCCBSData(String featureCode, int years[]);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER" })
	public String[] getCCBSGaulCodes();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER" })
	public String[] getGaulNames(String gaulCodes[]);
}
