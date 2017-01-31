package org.fao.fenix.web.modules.ccbs.server.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.fao.fenix.web.modules.ccbs.common.vo.CCBS;
import org.fao.fenix.web.modules.ccbs.server.DBAdapter;
import org.fao.fenix.web.modules.ccbs.server.TimeSeries;

public class LocalDBAdapter implements DBAdapter
{
	private HashMap<CCBS.Codes, LocalTimeSeries> tmMap = new HashMap<CCBS.Codes, LocalTimeSeries>();

	public void init() throws Exception
	{
		for (CCBS.Codes code: CCBS.Codes.values())
			tmMap.put(code, readFile(code));
	}

	public TimeSeries select(CCBS.Codes code, String featureCode, String commodityCode)
	{
		LocalTimeSeries sourceTS = tmMap.get(code);
		if (code.equals(CCBS.Codes.CCBS_POPULATION)) return sourceTS.select(featureCode);
		else                                         return sourceTS.select(featureCode, commodityCode);
	}

	private LocalTimeSeries readFile(CCBS.Codes code) throws Exception
	{
		File file = new File("/Users/rgiaccio/Documents/Programs/CCBS/data/ccbs/" + code + ".csv"); // TODO
		if (code.equals(CCBS.Codes.CCBS_POPULATION)) return readPopulationTS(file);
		else                                         return readCCBSTS(file);
	}

	private LocalTimeSeries readPopulationTS(File file) throws Exception
	{
		LocalTimeSeries ts = new LocalTimeSeries();
		DateFormat      df = new SimpleDateFormat("yyyy");
		boolean first = true;
		for (String line: FileUtils.readLines(file))
		{
			if (first)
			{
				first = false;
				continue;
			}
			// Country,Value,Measurement Unit,Year
			String s[] = line.split(",");
			TimeSeries.Element el = new LocalTimeSeries.LocalElement(df.parse(s[3]), s[0], null, Double.parseDouble(s[1]), null);
			ts.add(el);
		}
		return ts;
	}

	private LocalTimeSeries readCCBSTS(File file) throws Exception
	{
		LocalTimeSeries ts = new LocalTimeSeries();
		DateFormat      df = new SimpleDateFormat("yyyy");
		boolean first = true;
		for (String line: FileUtils.readLines(file))
		{
			if (first)
			{
				first = false;
				continue;
			}
			// FeatureCode,CommodityCode,Value,Annotation,MeasurementUnit,Date
			String s[] = line.split(",");
			TimeSeries.Element el = new LocalTimeSeries.LocalElement(df.parse(s[5]), s[0], s[1], Double.parseDouble(s[2]), s[3]);
			ts.add(el);
		}
		return ts;
	}

	public static final String GAUL_CODES[] = { "1",           "100",        "101",  "103",       "104",    "107",    "108"   };
	public static final String GAUL_NAMES[] = { "Afghanistan", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guyana", "Haiti" };

	public String[] getCCBSGaulCodes()
	{
		return GAUL_CODES;
	}

	public String[] getGaulNames(String gaulCodes[])
	{
		return GAUL_NAMES;
	}
}
