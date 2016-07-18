package org.fao.fenix.web.modules.olap.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerOpener;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerYAxisParametersVO;
import org.fao.fenix.web.modules.olap.client.view.MT2ChartDesignerWindow;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class MT2ChartDesign {

	public static SelectionListener<ButtonEvent> mt2chartDesignerWindow(final MT2ChartDesignerWindow tmp, final OLAPParametersVo pvo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				mt2chartDesignerWindowAgent(tmp, pvo);
			}
		};
	}
	
	private static void mt2chartDesignerWindowAgent(MT2ChartDesignerWindow tmp, OLAPParametersVo pvo) {
		
		// convert OLAP parameters in Chart Designer ones
		List<ChartDesignerParametersVO> parameters = olapParameters2ChartDesignerParameters(tmp, pvo);
		
		// create the window
		ChartDesignerWindow w = new ChartDesignerWindow();
		w.build();
		ResourceChildModel m = new ResourceChildModel();
		m.setId(String.valueOf(pvo.getDataSourceId()));
		w.getChartDesignerPanel().getDatasourcePanel().getModels().add(m);
		
		// open the chart
		ChartDesignerOpener.loadParameters(w, parameters, true);
		List<Long> datasetIDs = new ArrayList<Long>();
		datasetIDs.add(pvo.getDataSourceId());
		
		// visualize the chart
		w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getChartPanel().getLayoutContainer());
		tmp.getWindow().close();
	}
	
	private static Map<String, String> getSeriesColorMap(String contentDescriptor, OLAPParametersVo pvo) {
		Map<String, String> m = new HashMap<String, String>();
		if (pvo.getX().equals(contentDescriptor)) {
			for (String code : pvo.getXLabels().keySet())
				m.put(pvo.getXLabels().get(code), ChartDesignerController.createRandomHex(pvo.getDataSourceId()));
		} else if (pvo.getZ().equals(contentDescriptor)) {
			for (String code : pvo.getZLabels().keySet())
				m.put(pvo.getZLabels().get(code), ChartDesignerController.createRandomHex(pvo.getDataSourceId()));
		} else if (pvo.getY().equals(contentDescriptor)) {
			for (String code : pvo.getYLabels().keySet())
				m.put(pvo.getYLabels().get(code), ChartDesignerController.createRandomHex(pvo.getDataSourceId()));
		} else if (pvo.getW().equals(contentDescriptor)) {
			for (String code : pvo.getWLabels().keySet())
				m.put(pvo.getWLabels().get(code), ChartDesignerController.createRandomHex(pvo.getDataSourceId()));
		}
		return m;
	}
	
	private static List<Long> getDatasourceIDs(OLAPParametersVo pvo) {
		List<Long> l = new ArrayList<Long>();
		l.add(pvo.getDataSourceId());
		return l;
	}
	
	private static List<String> getXContentDescriptors(MT2ChartDesignerWindow tmp) {
		List<String> l = new ArrayList<String>();
		l.add(tmp.getxAxis().getValue(tmp.getxAxis().getSelectedIndex()));
		return l;
	}
	
	private static List<String> getXCodes(String contentDescriptor, OLAPParametersVo pvo) {
		List<String> l = new ArrayList<String>();
		if (pvo.getX().equals(contentDescriptor)) {
			for (String code : pvo.getXLabels().keySet())
				l.add(code);
		} else if (pvo.getZ().equals(contentDescriptor)) {
			for (String code : pvo.getZLabels().keySet())
				l.add(code);
		} else if (pvo.getY().equals(contentDescriptor)) {
			for (String code : pvo.getYLabels().keySet())
				l.add(code);
		} else if (pvo.getW().equals(contentDescriptor)) {
			for (String code : pvo.getWLabels().keySet())
				l.add(code);
		}
		return l;
	}
	
	private static ChartDesignerYAxisParametersVO createYAxis(MT2ChartDesignerWindow tmp, OLAPParametersVo pvo) {
		ChartDesignerYAxisParametersVO yap = new ChartDesignerYAxisParametersVO();
		String contentDescriptor = tmp.getyAxis().getValue(tmp.getyAxis().getSelectedIndex()); 
		yap.setContentDescriptor(contentDescriptor);
		yap.setyCodes(getYCodes(contentDescriptor, pvo));
		yap.setSeriesColorsMap(getSeriesColorMap(contentDescriptor, pvo));
		yap.setDatasetID(pvo.getDataSourceId());
		yap.setStep(10);
		yap.setyGrid(true);
		yap.setyGridColor("#D3D3D3");
		yap.setMostRecentData(useMostRecentData(contentDescriptor, pvo));
		yap.setMostRecentData(useMostRecentData(contentDescriptor, pvo));
		if (yap.isFromDateToDate()) {
			yap.setFromDate(getFromDate(contentDescriptor, pvo));
			yap.setToDate(getToDate(contentDescriptor, pvo));
		}
		if (yap.isMostRecentData()) {
			yap.setLatestYears(getLatestYears(contentDescriptor, pvo));
			yap.setLatestMonths(getLatestMonths(contentDescriptor, pvo));
			yap.setLatestDays(getLatestDays(contentDescriptor, pvo));
		}
		return yap;
	}
	
	private static Map<String, String> getYCodes(String contentDescriptor, OLAPParametersVo pvo) {
		Map<String, String> m = new HashMap<String, String>();
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.getXLabels();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.getZLabels();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.getYLabels();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.getWLabels();
		}
		return m;
	}
	
	private static List<ChartDesignerParametersVO> olapParameters2ChartDesignerParameters(MT2ChartDesignerWindow tmp, OLAPParametersVo pvo) {
		List<ChartDesignerParametersVO> list = new ArrayList<ChartDesignerParametersVO>();
		ChartDesignerParametersVO cp = new ChartDesignerParametersVO();
		String contentDescriptor = getXContentDescriptors(tmp).get(0);
		cp.addYAxis(createYAxis(tmp, pvo));
		cp.setxContentDescriptors(getXContentDescriptors(tmp));
		cp.setxCodes(getXCodes(contentDescriptor, pvo));
		cp.setDatasourceIDs(getDatasourceIDs(pvo));
		cp.setChartType("LINE");
		cp.setAggregationFunction(pvo.getFunction());
		cp.setImageWidth(460);
		cp.setImageHeight(250);
		cp.setxAxisEquidistantDates(true);
		cp.setLabelsDistanceFromAxis(-1);
		cp.setxAxisNumberOfIntervals(5);
		cp.setxGrid(true);
		cp.setLegendHorizontal(false);
		cp.setxGridColor("#D3D3D3");
		cp.setxUseFromDateToDate(useFromDateToDate(contentDescriptor, pvo));
		if (cp.isxUseFromDateToDate()) {
			cp.setxFromDate(getFromDate(contentDescriptor, pvo));
			cp.setxToDate(getToDate(contentDescriptor, pvo));
		}
		cp.setxUseMostRecentData(useMostRecentData(contentDescriptor, pvo));
		if (cp.isxUseMostRecentData()) {
			cp.setxLatestYears(getLatestYears(contentDescriptor, pvo));
			cp.setxLatestMonths(getLatestMonths(contentDescriptor, pvo));
			cp.setxLatestDays(getLatestDays(contentDescriptor, pvo));
		}
		list.add(cp);
		return list;
	}
	
	private static Integer getLatestDays(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.getxLatestDays();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.getzLatestDays();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.getyLatestDays();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.getwLatestDays();
		}
		return null;
	}
	
	private static Integer getLatestMonths(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.getxLatestMonths();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.getzLatestMonths();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.getyLatestMonths();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.getwLatestMonths();
		}
		return null;
	}
	
	private static Integer getLatestYears(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.getxLatestYears();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.getzLatestYears();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.getyLatestYears();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.getwLatestYears();
		}
		return null;
	}
	
	private static Date getFromDate(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.getxFromDate();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.getzFromDate();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.getyFromDate();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.getwFromDate();
		}
		return null;
	}
	
	private static Date getToDate(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.getxToDate();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.getzToDate();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.getyToDate();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.getwToDate();
		}
		return null;
	}
	
	private static boolean useFromDateToDate(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.isxUseFromDateToDate();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.iszUseFromDateToDate();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.isyUseFromDateToDate();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.iswUseFromDateToDate();
		}
		return false;
	}
	
	private static boolean useMostRecentData(String contentDescriptor, OLAPParametersVo pvo) {
		if (pvo.getX().equals(contentDescriptor)) {
			return pvo.isxUseMostRecentData();
		} else if (pvo.getZ().equals(contentDescriptor)) {
			return pvo.iszUseMostRecentData();
		} else if (pvo.getY().equals(contentDescriptor)) {
			return pvo.isyUseMostRecentData();
		} else if (pvo.getW().equals(contentDescriptor)) {
			return pvo.iswUseMostRecentData();
		}
		return false;
	}
	
}