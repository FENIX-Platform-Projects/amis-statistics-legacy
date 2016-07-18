package org.fao.fenix.web.modules.ccbs.common.services;

import org.fao.fenix.web.modules.ccbs.common.vo.Book;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCBSServiceAsync
{

	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER"})
	void populateCCBS(AsyncCallback<Integer> async);

	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER"})
	void getCCBSData(String featureCode, int years[], AsyncCallback<Book> async);

	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER"})
	void getCCBSGaulCodes(AsyncCallback<String[]> async);

	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER"})
	void getGaulNames(String gaulCodes[], AsyncCallback<String[]> async);
}
