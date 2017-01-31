package org.fao.fenix.web.modules.fieldclimate.server;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.fieldclimate.FieldClimateConnector;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.fieldclimate.common.services.FCService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class FCServiceImpl extends RemoteServiceServlet implements FCService {

	private FieldClimateConnector fieldClimateConnector;

	private UrlFinder urlFinder;
	
	private final static String USERNAME = "GIEWS";
	
	private final static String PASSWORD = "sudan";

	private static final Logger LOGGER = Logger.getLogger(FCServiceImpl.class);

	public void synchronize(String station, int entries) {

		LOGGER.info("FieldClimate Synchronization: START...");

		// get stations list
		List<String> stations = new ArrayList<String>();
		if (station.equalsIgnoreCase("all"))
			stations = fieldClimateConnector.getStationList(USERNAME, PASSWORD);
		else
			stations.add(station);
		LOGGER.info(stations.size() + " stations to synchronize");

		try {

			// dataset ID's
			Long dailyDatasetID = null;
			Long monthlyDatasetID = null;

			// synch daily data
			LOGGER.info("Synchronize FieldClimate DAILY Data: START...");
			dailyDatasetID = fieldClimateConnector.synchronize(USERNAME, PASSWORD, stations, entries, 2, false);
			LOGGER.info("Synchronize FieldClimate DAILY Data: DONE With ID " + dailyDatasetID);

			// synch monthly data
			LOGGER.info("Synchronize FieldClimate MONTHLY Data: START...");
			monthlyDatasetID = fieldClimateConnector.synchronize(USERNAME, PASSWORD, stations, (1 + (int) entries / 30), 3, false);
			LOGGER.info("Synchronize FieldClimate DAILY Data: DONE With ID " + monthlyDatasetID);

			// update charts
			LOGGER.info("Synchronize FieldClimate CHARTS: START...");
			updateCharts(dailyDatasetID);
			updateCharts(monthlyDatasetID);
			LOGGER.info("Synchronize FieldClimate CHARTS: DONE");

		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		}

		LOGGER.info("FieldClimate Synchronization: DONE");
	}

	@SuppressWarnings( { "static-access", "unused" })
	private void updateCharts(Long datasetID) {
		try {
			String fenixWebURL = urlFinder.fenixWebUrl;
			int idx = fenixWebURL.indexOf("/fenix/Fenix.html");
			URL url = new URL(fenixWebURL.substring(0, idx) + "/UpdateChartServlet?datasetId=" + datasetID);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void setFieldClimateConnector(FieldClimateConnector fieldClimateConnector) {
		this.fieldClimateConnector = fieldClimateConnector;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

}
