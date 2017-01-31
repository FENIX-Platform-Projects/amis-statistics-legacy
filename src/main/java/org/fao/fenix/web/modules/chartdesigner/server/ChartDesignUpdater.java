package org.fao.fenix.web.modules.chartdesigner.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.chartdesigner.ChartDesign;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.chartdesigner.ChartDesignerDao;
import org.fao.fenix.core.utils.ChartDesignerUtils;
import org.springframework.core.io.Resource;

public class ChartDesignUpdater {

	private ChartDesignerDao chartDesignerDao;
	
	private ChartDesignerUtils chartDesignerUtils;
	
	private final static Logger LOGGER = Logger.getLogger(ChartDesignUpdater.class);
	
	private String dir;
	
	public ChartDesignUpdater(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void recreateCharts() throws FenixException  {
		recreateCharts(new ArrayList<Long>());
	}
	
	public void recreateCharts(List<Long> datasetIDs) throws FenixException {
		try {
			LOGGER.info("RE-CREATE CHART IMAGES: START...");
			LOGGER.info("datasetIDs is null? " + (datasetIDs == null));
			if (!datasetIDs.isEmpty()) {
				for (Long datasetID : datasetIDs) {
					List<ChartDesign> chartDesigns = chartDesignerDao.findChartDesignByDatasetID(datasetID);
					LOGGER.info(chartDesigns.size() + " chartDesigns found for dataset " + datasetID);
					int nnn=0;
					int mmm=chartDesigns.size();
					for (ChartDesign chartDesign : chartDesigns){
						
						try {
							System.out.println("manik:::"+nnn+" of "+mmm);
							nnn++;
							recreateCharts(chartDesign);
						} catch (Exception e) {
							LOGGER.info("\tmmm mack FenixException for " + chartDesign.getResourceId() + ": " + e.getMessage());
						}
						
					}
				}
			} else {
				LOGGER.info("chartDesignerDao is null? " + (chartDesignerDao == null));
				List<ChartDesign> chartDesigns = chartDesignerDao.findAllChartDesigns();
				LOGGER.info(chartDesigns.size() + " chartDesigns found");
				
				for (ChartDesign chartDesign : chartDesigns) {
					try {
						recreateCharts(chartDesign);
					} catch (FenixException e) {
						LOGGER.info("\tFenixException for " + chartDesign.getResourceId() + ": " + e.getMessage());
					}
				}
			}
			LOGGER.info("RE-CREATE CHART IMAGES: DONE!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new FenixException(e.getMessage());
		}
	}
	
	private void recreateCharts(ChartDesign chartDesign) throws FenixException  {
		LOGGER.info("\tRE-CREATE CHART IMAGES FOR " + chartDesign.getResourceId() + ": START...");
		String filename = dir + File.separator + chartDesign.getResourceId() + ".png";
		chartDesignerUtils.createChart(filename, chartDesign.getParameters(), true, chartDesign.getResourceId());
		LOGGER.info("\tRE-CREATE CHART IMAGES FOR " + chartDesign.getResourceId() + ": DONE.");
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setChartDesignerDao(ChartDesignerDao chartDesignerDao) {
		this.chartDesignerDao = chartDesignerDao;
	}

	public void setChartDesignerUtils(ChartDesignerUtils chartDesignerUtils) {
		this.chartDesignerUtils = chartDesignerUtils;
	}
	
}
