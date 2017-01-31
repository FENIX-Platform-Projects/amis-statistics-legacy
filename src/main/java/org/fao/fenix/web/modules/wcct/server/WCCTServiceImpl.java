package org.fao.fenix.web.modules.wcct.server;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.dataset.IndexCoreContent;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.wcct.WCCTDao;
import org.fao.fenix.map.custom.CropCalendarMapBuilder;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.wcct.common.services.WCCTService;
import org.fao.fenix.web.modules.wcct.common.vo.WCCTVo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class WCCTServiceImpl extends RemoteServiceServlet implements WCCTService {

	WCCTDao wcctDao;
	CodecDao codecDao;
	CropCalendarMapBuilder cropCalendarMapBuilder;
	
	
	
	
	public List<CodeVo> getCountries() {
		List<String> codes = wcctDao.getCountries();
		List<CodeVo> countries = new ArrayList<CodeVo>(); 
		for(String code : codes) {
			CodeVo c = new CodeVo();
			Code gaul0 = codecDao.getCodeFromCodeSystemRegion(code, "GAUL0", "0", "EN");
			c.setLabel(gaul0.getLabel());
			c.setCode(gaul0.getCode());
			countries.add(c);
		}
		return countries;
	}
	
	public List<CodeVo> getCommodities() {
		List<String> codes = wcctDao.getCommodities();
		List<CodeVo> commodities = new ArrayList<CodeVo>();
		for(String code : codes) {
			CodeVo c = new CodeVo();
			c.setCode(code);
//			System.out.println("code");
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(code, "HS", "0", "EN"));
			commodities.add(c);
		}
		return commodities;
	}
	
	
	public List<List<CodeVo>> getDimensions(String commodity){
		List<List<CodeVo>> dimensions = new ArrayList<List<CodeVo>>();
		List<String> codes = wcctDao.getDimensions(commodity);
		for(String code : codes) {
			List<String> dimension = tokenizeDimensions(code); 
			/** get season ***/
			CodeVo c = new CodeVo();
			List<CodeVo> clist = new ArrayList<CodeVo>();
			c.setCode(code);
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(dimension.get(0), "SeasonCode", "0", "EN"));
			clist.add(c);
		
			/** get other ***/
			c = new CodeVo();
			c.setCode(code);
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(dimension.get(1), "OthersWcctCode", "0", "EN"));
			clist.add(c);
			
			/** get location ***/
			c = new CodeVo();
			c.setCode(code);
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(dimension.get(2), "LocationCode", "0", "EN"));
			clist.add(c);
			dimensions.add(clist);
		}
		return dimensions;
	}
	
	public List<String> tokenizeDimensions(String dimensions){
		StringTokenizer tokenizer = new StringTokenizer(dimensions, "_");
		List<String> tokens = new ArrayList<String>();
		while( tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken().trim());
		}
		return tokens;
	}
	
	public List<CodeVo> getSeason(String commodity) {
		List<CodeVo> dimensions = new ArrayList<CodeVo>();
		List<String> codes = wcctDao.getSeason(commodity);
		for(String code : codes) {
			CodeVo c = new CodeVo();
			c.setCode(code);
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(code, "SeasonCode", "0", "EN"));
			dimensions.add(c);
		}
		return dimensions;
	}
	
	public List<CodeVo> getLocations(String commodity) {
		List<CodeVo> dimensions = new ArrayList<CodeVo>();
		List<String> codes = wcctDao.getLocations(commodity);
		for(String code : codes) {
			CodeVo c = new CodeVo();
			c.setCode(code);
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(code, "LocationCode", "0", "EN"));
			dimensions.add(c);
		}
		return dimensions;
	}	
	public List<CodeVo> getOthers(String commodity) {
		List<CodeVo> dimensions = new ArrayList<CodeVo>();
		List<String> codes = wcctDao.getOthers(commodity);
		for(String code : codes) {
			CodeVo c = new CodeVo();
			c.setCode(code);
			c.setLabel(codecDao.getLabelFromCodeCodingSystem(code, "OthersWcctCode", "0", "EN"));
			dimensions.add(c);
		}
		return dimensions;
	}
	
	
	public List<CodeVo> getCommoditiesByCountry(List<String> countries) {
		List<CodeVo> commodities = new ArrayList<CodeVo>();

			for(String country : countries) {
//				System.out.println("getCommoditiesByCountry:" + country);
				List<IndexCoreContent> codes = wcctDao.findCropsBy(country);
				if ( codes.isEmpty()) {
//					System.out.println("codes null");
				}
				for(IndexCoreContent code : codes) {
					/*** if non e' contenuto il codice getCommodityCode allora aggiungilo ***/
					Boolean check = false;
					for(CodeVo commodity : commodities) {
						if ( commodity.getCode().equals(code.getCommodityCode())) {
							check = true;
							break;
						}
					}
					if ( !check) {
//						System.out.println("->");
						CodeVo c = new CodeVo();
						c.setCode(code.getCommodityCode());
						c.setLabel(codecDao.getLabelFromCodeCodingSystem(code.getCommodityCode(), "HS", "0", "EN"));
						commodities.add(c);
					}		
				}
			
		}
		return commodities;
	}
	
	

	public List<WCCTVo> fillTable(List<String> countries, List<String> commodities) {
		List<WCCTVo> results = new ArrayList<WCCTVo>();
//		System.out.println("searching wcct");
		for(String country : countries) {
			for(String commodity : commodities) {

				/*** get wcctCode ***/
//				System.out.println("-> " + country + " | " + commodity);
				List<IndexCoreContent> wcct = wcctDao.findCropForTable(country, commodity);
			
				
				if ( wcct != null ) {
					for(IndexCoreContent code : wcct) {
						WCCTVo result = new WCCTVo();
						result.setCountry(codecDao.getLabelFromCodeCodingSystem(code.getFeatureCode(), "GAUL0", "0", "EN"));
						result.setCrop(codecDao.getLabelFromCodeCodingSystem(code.getCommodityCode(), "HS", "0", "EN"));
						result.setCropStage(codecDao.getLabelFromCodeCodingSystem(Integer.toString(code.getQuantity().intValue()), "CropStage", "0", "EN"));
						result.setFurther(codecDao.getLabelFromCodeCodingSystem(code.getFirstIndicator(), "FurtherWcctCodes", "0", "EN"));
						result.setStartMonth(Integer.toString(code.getBaseDateFrom().getMonth() + 1));
						result.setStartDek(Integer.toString(code.getBaseDateFrom().getDate()));
						result.setEndMonth(Integer.toString(code.getBaseDateTo().getMonth() + 1));
						result.setEndDek(Integer.toString(code.getBaseDateTo().getDate()));
						results.add(result);
					}
				}
			}
		}
		return results;
	}
	
	public Boolean createMap() {
//		System.out.println("creating map");
		return cropCalendarMapBuilder.createMap();
	}
	
	public String getMap(Boolean dekade, String month, String day, String crop, String season, String others) {
		/*** create date ***/
		Integer m = new Integer(month);
		Integer d = new Integer(day);
		Date date = new Date(0, m, d);
		BufferedImage image = cropCalendarMapBuilder.getCropCalendarImage(dekade, date, crop, season, others);
		
		String filename = crop.concat(date.toString()).concat(season);
		

		File file = new File( Setting.getSystemPath() + "/exportObject/" + filename + ".png");
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "<div align='center'> <img src='../exportObject"  + File.separator + filename + ".png'" + "> </div>";
	}
	
	
	public void setWcctDao(WCCTDao wcctDao) {
		this.wcctDao = wcctDao;
	}
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setCropCalendarMapBuilder(CropCalendarMapBuilder cropCalendarMapBuilder) {
		this.cropCalendarMapBuilder = cropCalendarMapBuilder;
	}

	
	public String getImage() {
		return null;
	}
	

	


}
