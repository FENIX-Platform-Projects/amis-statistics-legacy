package org.fao.fenix.web.modules.esoko.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.fao.fenix.core.esoko.EsokoImporter;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.esoko.common.services.EsokoService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings({"serial", "deprecation"})
public class EsokoServiceImpl extends RemoteServiceServlet implements EsokoService {

	private EsokoImporter esokoImporter;
	
	private UrlFinder urlFinder;
	
	private static final Logger LOGGER = Logger.getLogger(EsokoServiceImpl.class);
	
	public Long synchronize(String locationID, String timePeriod) {
		Long datasetID = null;
		Date today = new Date();
		int startYear = Integer.valueOf(String.valueOf(timePeriod.subSequence(0, 4)));
		int actualYear = 1900 + today.getYear();
		LOGGER.info(startYear + " to " + actualYear + " for code: " + locationID);
		if (startYear < actualYear) {
			for (int i = startYear ; i <= actualYear ; i++) {
				LOGGER.info("Import for [" + timePeriod + "] and [" + locationID + "]");
				datasetID = esokoImporter.importEsokoPrices(locationID, timePeriod);
				LOGGER.info("Done with ID " + datasetID);
//				if (i == actualYear) {
//					LOGGER.info("Import for [" + timePeriod + "] and [" + locationID + "]");
//					datasetID = esokoImporter.importEsokoPrices(locationID, timePeriod);
//					LOGGER.info("Done with ID " + datasetID);
//				} else {
//					LOGGER.info("Import for [" + timePeriod + "] and [" + locationID + "]");
//					datasetID = esokoImporter.importEsokoPrices(locationID, timePeriod);
//					LOGGER.info("Done with ID " + datasetID);
//				}
			}
		} else if (startYear == actualYear) {
			LOGGER.info("Import for [" + timePeriod + "] and [" + locationID + "]");
			datasetID = esokoImporter.importEsokoPrices(locationID, timePeriod);
			LOGGER.info("Done with ID " + datasetID);
		}
		updateCharts(datasetID);
		return datasetID;
	}
	
	@SuppressWarnings("static-access")
	private void updateCharts(Long datasetID) {
		try {
			String fenixWebURL = urlFinder.fenixWebUrl;
			int idx = fenixWebURL.indexOf("/fenix/Fenix.html");
			URL url = new URL(fenixWebURL.substring(0, idx) + "/UpdateChartServlet?datasetId=" + datasetID);
			System.out.println("UPDATE CHART: " + url);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void setEsokoImporter(EsokoImporter esokoImporter) {
		this.esokoImporter = esokoImporter;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}
	
}