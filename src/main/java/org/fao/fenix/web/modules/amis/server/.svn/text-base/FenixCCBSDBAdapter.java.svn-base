package org.fao.fenix.web.modules.amis.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.CoreContent;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;


public class FenixCCBSDBAdapter implements DBAdapter
{
	private static final Logger LOGGER = Logger.getLogger(FenixCCBSDBAdapter.class);
	
	private DatasetDao datasetDao; // will be injected by spring
	
	public TimeSeries select(CCBS.Codes code, String featureCode, String commodityCode)
	{
		Dataset dataset = datasetDao.findByCode(code.getDatasetCode());
		if(dataset == null) {
			LOGGER.error("Missing CCBS dataset '"+code.getDatasetCode()+"'");
			throw new NullPointerException("Missing CCBS dataset '"+code.getDatasetCode()+"'");
		}

//		long datasetId = dataset.getResourceId();
		
		List<DatasetDao.CoreFilter> filters = new ArrayList<DatasetDao.CoreFilter>();
		filters.add(new DatasetDao.CoreFilter(DataType.featureCode, featureCode));		
		if (! code.equals(CCBS.Codes.CCBS_POPULATION))
			filters.add(new DatasetDao.CoreFilter(DataType.commodityCode, commodityCode));
				
		List<CoreContent> result = datasetDao.getCoreDao().getFilteredRecords((CoreDataset)dataset, filters);
				
		FenixCCBSTimeSeries sourceTS = new FenixCCBSTimeSeries(result.size());
		for(CoreContent coreContent : result) {
			sourceTS.add(new FenixCCBSTimeSeries.LocalElement((QuantitativeCoreContent)coreContent));
		}
		
		return sourceTS;
				
//		if (code.equals(CCBS.Codes.CCBS_POPULATION)) return sourceTS.select(featureCode);
//		else                                         return sourceTS.select(featureCode, commodityCode);
	}

	
	private List<Object[]> cachedGaulpairlist = null;
	public synchronized String[] getCCBSGaulCodes()
	{		
		if(cachedGaulpairlist == null)
			retrieveGaul();
		String[] ret = new String[cachedGaulpairlist.size()];
		for(int i = 0; i < cachedGaulpairlist.size(); i++) {
			Object[] objects = cachedGaulpairlist.get(i);
			ret[i] = (String)objects[0];
		}
		return ret;
	}

	public synchronized String[] getGaulNames(String gaulCodes[])
	{
		if(cachedGaulpairlist == null)
			retrieveGaul();
		String[] ret = new String[cachedGaulpairlist.size()];
		for(int i = 0; i < cachedGaulpairlist.size(); i++) {
			Object[] objects = cachedGaulpairlist.get(i);
			ret[i] = (String)objects[1];
		}
		return ret;
		
	}
	
	private void retrieveGaul() {
		// all CCBS datasets reflect more or less the same data.
		// just take one of the datasets, and extract the needed values.
		Dataset dataset = datasetDao.findByCode( CCBS.Codes.CCBS_PRODUCTION.getDatasetCode());
		long datasetId = dataset.getResourceId();		
		
		cachedGaulpairlist = (List<Object[]>)datasetDao.getDistinctValues(datasetId, "Country");
	}


	//==========================================================================
	
	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

//	@Override
//	public void setAmisDao(AMISDao amisdao) {
//		// TODO Auto-generated method stub
//		
//	}
		
}
