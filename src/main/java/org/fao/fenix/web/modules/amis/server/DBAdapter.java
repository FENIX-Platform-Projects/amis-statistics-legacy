package org.fao.fenix.web.modules.amis.server;

import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;

public interface DBAdapter
{
	public TimeSeries select(CCBS.Codes code, String featureCode, String commodityCode);

	public String[] getCCBSGaulCodes();

	public String[] getGaulNames(String gaulCodes[]);
	
//	public void setAmisDao(AMISDao amisdao);
}
