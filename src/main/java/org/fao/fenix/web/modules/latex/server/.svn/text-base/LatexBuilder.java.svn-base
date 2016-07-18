package org.fao.fenix.web.modules.latex.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.designer.Box;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.image.FenixImage;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.resourceview.DescriptorView;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.olap.OLAPLaTeX;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.FindDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.olap.OLAPJDBC;
import org.fao.fenix.core.persistence.resourceview.ResourceViewDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.web.modules.core.server.utils.EmptyDatumLabel;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.designer.common.vo.BoxVO;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;
import org.fao.fenix.web.modules.designer.common.vo.PageVO;
import org.springframework.core.io.Resource;

public class LatexBuilder {
	
	private FindDao findDao;
	
	private String dir;
	
	private UrlFinder urlFinder;
	
	private OLAPJDBC olapJdbc;
	
	private ResourceViewDao resourceViewDao;
	
	private CodecDao codecDao;
	
	private DatasetDao datasetDao;
	
	private OLAPLaTeX olapLatex;
	
	private WMSMapProviderDao wmsMapProviderDao;
	
	private LayerGaulUtils layerGaulUtils;
	
	private DecimalFormat twoDigit = new DecimalFormat("#,##0.00");;
	
	private final static Logger LOGGER =  Logger.getLogger(LatexBuilder.class);
	
	private Map<String, String> colorMap = new HashMap<String, String>();
	
	public LatexBuilder(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public StringBuilder build(List<Box> boxes, String language) throws FenixException {
		StringBuilder sb = new StringBuilder();
		for (Box b : boxes) {
			int width = Math.abs(b.getEndX() - b.getStartX());
			int height = Math.abs(b.getEndY() - b.getStartY());
			int startX = b.getStartX();
			int startY = b.getStartY();
			LatexStyleSettings s = createLatexStyleSettings(b);
			switch (b.getResourceType()) {
				case IMAGE:
					sb.append(imageblock(width, height, startX, startY, b.getResourceID(), s));
				break;
				case TEXT:
					sb.append(textblock(width, height, startX, startY, b.getResourceID(), s));
				break;
				case CHART:
					sb.append(chartblock(width, height, startX, startY, b.getResourceID(), s));
				break;
				case TABLE:
					sb.append(tableblock(width, height, startX, startY, b.getResourceID(), language, s));
				break;
				case OLAP:
					sb.append(olapblock(width, height, startX, startY, b.getResourceID(), language, s));
				break;
				case LAYER:
					sb.append(layerBlock(width, height, startX, startY, b.getResourceID(), s));
				break;
				case MAP:
					sb.append(mapBlock(width, height, startX, startY, b.getResourceID(), s));
				break;
			}
			
			
		}
		return sb;
	}
	
	private LatexStyleSettings createLatexStyleSettings(Box b) {
		LatexStyleSettings s = new LatexStyleSettings();
		s.setBoxBackgroundColor(b.getBoxBackgroundColor());
		s.setBoxLineColor(b.getBoxLineColor());
		s.setCaption(b.getCaption());
		s.setCaptionColor(b.getCaptionColor());
		s.setCaptionFontFamily(b.getCaptionFont());
		s.setCaptionFontSize(b.getCaptionSize());
		s.setCaptionPosition(b.getCaptionPosition());
		return s;
	}
	
	public StringBuilder documentclass() throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\documentclass[a4paper]{article}\n");
		return sb;
	}

	public StringBuilder usepackage(DesignerConstants orientation) throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\usepackage[margin=1cm, a4paper, ").append(orientation.name().toLowerCase()).append("]{geometry}\n");
		sb.append("\\usepackage{color}\n");
		sb.append("\\usepackage[pdftex]{graphicx}\n");
		sb.append("\\usepackage[absolute]{textpos}\n");
		sb.append("\\usepackage{lscape}\n");
		sb.append("\\usepackage{lingmacros}\n");
		sb.append("\\usepackage{tree-dvips}\n");
		sb.append("\\usepackage{soul}\n");
		sb.append("\\usepackage{fixltx2e}\n");
		sb.append("\\usepackage{ulem}\n");
		sb.append("\\usepackage{multirow}\n");
		return sb;
	}
	
	public StringBuilder defineColors(List<PageVO> pvos) throws FenixException {
		StringBuilder sb = new StringBuilder();
		for (PageVO pvo : pvos) {
			for (BoxVO bvo : pvo.getBoxes()) {
				String bc = null;
				if (bvo.getBoxBackgroundColor() != null && !bvo.getBoxBackgroundColor().equals("")) {
					bc = bvo.getBoxBackgroundColor().substring(1);
					colorMap.put(bc, UUID.randomUUID().toString());
					sb.append("\\definecolor{");
					sb.append(colorMap.get(bvo.getBoxBackgroundColor().substring(1)));
					sb.append("}{RGB}{");
					sb.append(Integer.parseInt(bc.substring(0, 2), 16));
					sb.append(",");
					sb.append(Integer.parseInt(bc.substring(2, 4), 16));
					sb.append(",");
					sb.append(Integer.parseInt(bc.substring(4), 16));
					sb.append("}\n");
				}
				if (bvo.getBoxLineColor() != null && !bvo.getBoxLineColor().equals("")) {
					bc = bvo.getBoxLineColor().substring(1);
					colorMap.put(bc, UUID.randomUUID().toString());
					sb.append("\\definecolor{");
					sb.append(colorMap.get(bvo.getBoxLineColor().substring(1)));
					sb.append("}{RGB}{");
					sb.append(Integer.parseInt(bc.substring(0, 2), 16));
					sb.append(",");
					sb.append(Integer.parseInt(bc.substring(2, 4), 16));
					sb.append(",");
					sb.append(Integer.parseInt(bc.substring(4), 16));
					sb.append("}\n");
				}
				if (bvo.getCaptionColor() != null && !bvo.getCaptionColor().equals("")) {
					bc = bvo.getCaptionColor().substring(1);
					colorMap.put(bc, UUID.randomUUID().toString());
					sb.append("\\definecolor{");
					sb.append(colorMap.get(bvo.getCaptionColor().substring(1)));
					sb.append("}{RGB}{");
					sb.append(Integer.parseInt(bc.substring(0, 2), 16));
					sb.append(",");
					sb.append(Integer.parseInt(bc.substring(2, 4), 16));
					sb.append(",");
					sb.append(Integer.parseInt(bc.substring(4), 16));
					sb.append("}\n");
				}
			}
		}
		return sb;
	}

	public StringBuilder textblockSettings() throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\setlength{\\TPHorizModule}{1cm}\n");
		sb.append("\\setlength{\\TPVertModule}{\\TPHorizModule}\n");
		sb.append("\\textblockorigin{10mm}{10mm}\n");
		sb.append("\\setlength{\\parindent}{0pt}\n");
		return sb;
	}
	
	public StringBuilder beginDocument() throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{document}\n");
		return sb;
	}
	
	private StringBuilder textblock(int width, int height, int startX, int startY, Long textID, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		sb.append(createText(textID)).append("\n");
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private StringBuilder tableblock(int width, int height, int startX, int startY, Long tableViewID, String language, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		if (s.getCaptionPosition().equals(DesignerConstants.ABOVE.name()))
			sb.append(createCaption(s));
		sb.append(createTable(tableViewID, language, width, height)).append("\n");
		if (s.getCaptionPosition().equals(DesignerConstants.BELOW.name()))
			sb.append(createCaption(s));
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private StringBuilder olapblock(int width, int height, int startX, int startY, Long olapID, String language, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		if (s.getCaptionPosition().equals(DesignerConstants.ABOVE.name()))
			sb.append(createCaption(s));
		sb.append(olapLatex.createOLAPLaTeX(olapID, width)).append("\n");
		if (s.getCaptionPosition().equals(DesignerConstants.BELOW.name()))
			sb.append(createCaption(s));
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private StringBuilder imageblock(int width, int height, int startX, int startY, Long imageID, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		String imgPath = createImage(imageID);
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		if (s.getCaptionPosition().equals(DesignerConstants.ABOVE.name()))
			sb.append(createCaption(s));
		sb.append("\\includegraphics[width=").append(width).append("cm,height=").append(height).append("cm]{").append(imgPath).append("}\n");
		if (s.getCaptionPosition().equals(DesignerConstants.BELOW.name()))
			sb.append(createCaption(s));
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private StringBuilder chartblock(int width, int height, int startX, int startY, Long chartID, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		String imgPath = createChart(chartID);
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		if (s.getCaptionPosition().equals(DesignerConstants.ABOVE.name()))
			sb.append(createCaption(s));
		sb.append("\\includegraphics[width=").append(width).append("cm,height=").append(height).append("cm]{").append(imgPath).append("}\n");
		if (s.getCaptionPosition().equals(DesignerConstants.BELOW.name()))
			sb.append(createCaption(s));
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private StringBuilder createCaption(LatexStyleSettings s) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		if (!s.getCaptionFontFamily().equals("")) {
			DesignerConstants dc = DesignerConstants.valueOf(s.getCaptionFontFamily());
			sb.append("\\");
			switch (dc) {
				case TIMES: sb.append("textrm{"); break;
				case ARIAL: sb.append("textnormal{"); break;
				case COURIER: sb.append("texttt{"); break;
				case SERIF: sb.append("textsf{"); break;
			}
		}
		if (!s.getCaptionFontSize().equals("")) {
			DesignerConstants dc = DesignerConstants.valueOf(s.getCaptionFontSize());
			switch (dc) {
				case TINY: sb.append("\\").append("tiny\n"); break;
				case SMALL: sb.append("\\small\n"); break;
				case NORMALSIZE: sb.append("\\").append("normalsize\n"); break;
				case MEDIUM: sb.append("\\large\n"); break;
				case LARGE: sb.append("\\Large\n"); break;
				case XLARGE: sb.append("\\LARGE\n"); break;
				case XXLARGE: sb.append("\\huge\n"); break;
			}
		}
		if (!s.getCaptionColor().equals(""))
			sb.append("{\\").append("color{").append(colorMap.get(s.getCaptionColor().substring(1))).append("}");
		sb.append(s.getCaption());
		if (!s.getCaptionColor().equals(""))
			sb.append("}");
		if (!s.getCaptionFontFamily().equals(""))
			sb.append("}");
//		sb.append("}\n");
		sb.append("\n");
		return sb;
	}
	
	private StringBuilder layerBlock(int width, int height, int startX, int startY, Long chartID, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		String imgPath = createLayerImage("40764");
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		sb.append("\\includegraphics[width=").append(width).append("cm,height=").append(height).append("cm]{").append(imgPath).append("}\n");
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private StringBuilder mapBlock(int width, int height, int startX, int startY, Long mapID, LatexStyleSettings s) throws FenixException {
		StringBuilder sb = new StringBuilder();
		String imgPath = createMapImage(mapID, width, height);
		sb.append("\\begin{textblock}{").append(width).append("}(").append(startX).append(",").append(startY).append(")\n");
		if (!s.getBoxLineColor().equals("")) {
			sb.append("\\fcolorbox{");
			sb.append(colorMap.get(s.getBoxLineColor().substring(1)));
			sb.append("}{");
			sb.append(colorMap.get(s.getBoxBackgroundColor().substring(1)));
			sb.append("}{\n");
		}
		sb.append("\\begin{minipage}[t][").append(height).append("cm]{").append(width).append("cm}\n");
		sb.append("\\includegraphics[width=").append(width).append("cm,height=").append(height).append("cm]{").append(imgPath).append("}\n");
		sb.append("\\end{minipage}\n");
		if (!s.getBoxLineColor().equals(""))
			sb.append("}\n");
		sb.append("\\end{textblock}\n");
		return sb;
	}
	
	private String createText(Long textID) throws FenixException {
		TextView text = findDao.findTextView(textID);
		if (text != null) {
			String html = text.getText().getContent();
			String latex = HTML2LaTeX.cleanLatexAreaContent(html);
			return latex;
		} else {
			throw new FenixException("There is no text with ID " + textID);
		}
	}
	
	private String createTable(Long tableViewID, String language, int width, int height) throws FenixException {
		StringBuilder sb = new StringBuilder();
		ResourceView rv = resourceViewDao.find(tableViewID);
		Map<String, List<String>> filterCriteria = loadFilterCriteria(rv);
		for (Dataset d : rv.getDatasets()) {
			sb.append("\\resizebox{").append(width).append("cm}{!} {\n");
			List<String> headers = getHeadersList(d);
			List<List<String>> dbTable = getFilteredRecords(d.getResourceId(), headers, filterCriteria, language);
			sb.append(tableHeaders(headers, width));
			sb.append(tableContents(dbTable));
			sb.append("\\end{tabular}\n");
			sb.append("}\n");
			sb.append("}\n");
//			sb.append("}\n");
		}
		return sb.toString();
	}
	
	private StringBuilder tableContents(List<List<String>> dbTable) {
		StringBuilder sb = new StringBuilder();
		for (List<String> row : dbTable) {
			for (int i = 0 ; i < row.size() ; i++) {
				if ((row.get(i) != null) && (!row.get(i).isEmpty()))
					sb.append(HTML2LaTeX.removeLatexIllegalCharacters(row.get(i)));
				else
					sb.append("n.a.");
				if (i < row.size() - 1)
					sb.append(" & ");
			}
			sb.append("\\\\\n");
			sb.append("\\hline\n");
		}
		return sb;
	}
	
	private StringBuilder tableHeaders(List<String> headers, int tableSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\resizebox{").append(tableSize).append("cm}{!} {\n");
		sb.append("\\begin{tabular}{| ");
		for (String h : headers) 
			sb.append("c |");
		sb.append("} \n");
		sb.append("\\hline\n");
		for (int i = 0 ; i < headers.size() ; i++) {
			sb.append("\\textbf{").append(HTML2LaTeX.removeLatexIllegalCharacters(headers.get(i))).append("}");
			if (i < headers.size() - 1)
				sb.append(" & ");
		}
		sb.append("\\\\\n");
		sb.append("\\hline\n");
		return sb;
	}
	
	private List<String> getHeadersList(Dataset dataset) {
		List<String> headers = new ArrayList<String>();
		for (Descriptor d : dataset.getDatasetType().getDescriptors())
			headers.add(d.getHeader());
		return headers;
	}
	
	private List<List<String>> getFilteredRecords(Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String language) {
		Dataset dataset = datasetDao.findById(datasetId);
		List<Object[]> originalRowList = datasetDao.getFilteredRowValues(dataset, headerList, filterCriteria);
		List<List<String>> rowList = new ArrayList<List<String>>();
		for (int i = 0; i < originalRowList.size(); i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < originalRowList.get(i).length; j++)
				if (originalRowList.get(i)[j] != null) {
					if (!originalRowList.get(i)[j].equals("null"))
						row.add(originalRowList.get(i)[j].toString());
					else
						row.add(EmptyDatumLabel.getLabel());
				} else
					row.add(EmptyDatumLabel.getLabel());
			rowList.add(row);
		}
		List<List<String>> rowListResult = new ArrayList<List<String>>();
		int columns = headerList.size();
		long t0 = System.currentTimeMillis();
		DataType colTypes[] = new DataType[columns];
		Option codingOptions[] = new Option[columns];
		HashMap cachedCodes[] = new HashMap[columns];
		for (int j = 0; j < columns; j++) {
			String header = headerList.get(j);
			if (!header.equals("ID")) {
				Descriptor descriptor = GwtConnector.getDescriptorFromColumnName(dataset, header);
				if (descriptor == null)
					throw new IllegalArgumentException("Could not find column '" + header + "' in dataset '" + dataset.getTitle() + "' (id:" + datasetId + ")");
				colTypes[j] = descriptor.getDataType();
				List<Option> optionList = descriptor.getOptions();
				codingOptions[j] = null; // init
				for (Option op : optionList) {
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null) {
						codingOptions[j] = op;
						break;
					}
				}
			}
			cachedCodes[j] = new HashMap(60);
		}
		for (int rowidx = 0; rowidx < rowList.size(); rowidx++) {
			List<String> rowResult = new ArrayList<String>(columns);
			for (int colidx = 0; colidx <= columns; colidx++) {
				String cellvalue = rowList.get(rowidx).get(colidx);
				if (colidx == 0) {
//					rowResult.add(cellvalue);
//					System.out.println("Skip ID: " + cellvalue);
				} else {
//					try {
//						System.out.println("DaTa TyPe: " + colTypes[colidx - 1].name());
						DataType dt = colTypes[colidx - 1];
						if (dt == DataType.date) {
							cellvalue = GwtConnector.formatDate(dataset, cellvalue);
						} else if (dt == DataType.measurementUnit) {
//							System.out.println("\tDaTa TyPe: " + colTypes[colidx].name() + " ---> " + cellvalue);
						} else if (dt == DataType.quantity) {
//							System.out.println("\tDaTa TyPe: " + colTypes[colidx].name() + " ---> " + cellvalue);
							cellvalue = twoDigit.format(Double.valueOf(cellvalue.toString()));
						} else {
							Option codingOption = null;
							codingOption = codingOptions[colidx - 1];
							if (codingOption != null) {
								String cached = (String) cachedCodes[colidx - 1].get(cellvalue);
								if (cached == null) {
									cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), language);
									if (cached.equals(cellvalue) && !language.equals("EN")) {
										cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), "EN");
									}
									cachedCodes[colidx - 1].put(cellvalue, cached);
								}
								cellvalue = cached;
							}
						}
						rowResult.add(cellvalue);
//					} catch (IndexOutOfBoundsException e) {
//						throw new FenixException(e.getMessage());
//					}
				}
			}
			rowListResult.add(rowResult);
		}
		return rowListResult;
	}
	
	private Map<String, List<String>> loadFilterCriteria(ResourceView rv) {
		Map<String, List<String>> filterCriteria = new HashMap<String, List<String>>();
		for (DescriptorView dv : rv.getDescriptorViews()) {
			String dataType = dv.getContentDescriptor();
			List<String> values = new ArrayList<String>();
			if (dataType.contains("date")) {
				List<UniqueDateValues> udvs = resourceViewDao.findDateValues(dv);
				for (UniqueDateValues udv : udvs)
					values.add(FieldParser.parseDate(udv.getValue()));
			} else {
				List<UniqueTextValues> utvs = resourceViewDao.findTextValues(dv);
				for (UniqueTextValues utv : utvs)
					values.add(utv.getValue());
			}
			filterCriteria.put(dataType, values);
		}
		return filterCriteria;
	}
	
	@SuppressWarnings("deprecation")
	private List<UniqueDateValues> findDescriptorViewUniqueDateValues(DescriptorView d) {
		List<UniqueDateValues> udvs = new ArrayList<UniqueDateValues>();
		olapJdbc.openConnection();
		List<Long> ids = olapJdbc.findUniqueDateValue(d.getHeader(), d.getContentDescriptor());
		olapJdbc.closeConnection();
		udvs = resourceViewDao.findUniqueDateValues(ids, "EN");
		return udvs;
	}
	
	@SuppressWarnings("deprecation")
	private List<UniqueTextValues> findDescriptorViewUniqueTextValues(DescriptorView d) {
		List<UniqueTextValues> udvs = new ArrayList<UniqueTextValues>();
		olapJdbc.openConnection();
		List<Long> ids = olapJdbc.findUniqueTextValue(d.getHeader(), d.getContentDescriptor());
		olapJdbc.closeConnection();
		udvs = resourceViewDao.findUniqueTextValues(ids, "EN");
		return udvs;
	}
	
	private String createImage(Long imageID) throws FenixException {
		FenixImage img = findDao.findImage(imageID);
		if (img != null) {
			try {
				String imgName = UUID.randomUUID() + ".png";
				String imgPath = dir + File.separator + imgName;
				byte[] bytes = img.getImage();
				InputStream in = new ByteArrayInputStream(bytes);
				BufferedImage image = ImageIO.read(in);
				File file = new File(imgPath);
				ImageIO.write(image, "png", file);
				return imgPath;
			} catch (IOException e) {
				throw new FenixException(e.getMessage());
			}
		} else {
			throw new FenixException("There is no image with ID " + imageID);
		}
	}
	
	private String createChart(Long chartID) throws FenixException {
		String path = null;
		String frame = urlFinder.fenixBirtUrl + "/FenixBirtServlet?dataViewId=" + chartID + "&servletType=preview";
		try {
			URL url = new URL(frame);
			Object o = url.getContent();
			BirtImageHound hound = new BirtImageHound();
			String pathToChart = hound.findBirtImageDirectory(dir);
			File srcFile = new File(pathToChart);
			String filename = UUID.randomUUID() + ".pdf";
			File destFile = new File(dir + File.separator + filename);
			FileUtils.moveFile(srcFile, destFile);
			path =  dir + File.separator + filename;
		} catch (MalformedURLException e) {
			LOGGER.info(e.getMessage());
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		return path;
	}
	
	private String createLayerImage(String gaulCode) throws FenixException {
		try {
			String imageFileName = dir + File.separator + UUID.randomUUID().toString() + ".png";
			BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(gaulCode));
			List<GeoView> geoViews = new ArrayList<GeoView>();
			GeoView gv = new GeoView(wmsMapProviderDao.findById(3233));
			geoViews.add(gv);
			BufferedImage bi = getLayerImage(bbox, geoViews, 300, 200);
			File imageFile = new File(imageFileName);
			ImageIO.write(bi, "png", imageFile);
			return imageFileName;
		} catch (NumberFormatException e) {
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	private String createMapImage(Long mapID, int width, int height) throws FenixException {
		try {
			MapView map = wmsMapProviderDao.find(mapID);
			List<GeoView> geoViews = wmsMapProviderDao.findMapGeoViews(mapID);
//			LOGGER.info(geoViews.size() + " geoViews for map " + mapID);
			String imageFileName = dir + File.separator + UUID.randomUUID().toString() + ".png";
//			LOGGER.info("BBox is null? " + (map.getBoundingBox() == null));
			BoundingBox bbox = map.getBoundingBox();
			BufferedImage bi = getLayerImage(bbox, geoViews, (100 * width), (100 * height));
			File imageFile = new File(imageFileName);
			ImageIO.write(bi, "png", imageFile);
			return imageFileName;
		} catch (NumberFormatException e) {
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	private BufferedImage getLayerImage(BoundingBox bbox, List<GeoView> gvlist, int width, int height) {
		WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
		mm.setHeight(height);
		mm.setWidth(width);
		mm.setBoundingBox(bbox);
		for (GeoView geoView : gvlist) 
			mm.addLayer(geoView);		
		BufferedImage image = mm.getMapImage();
		return image;
	}
	
	public StringBuilder endDocument() {
		StringBuilder sb = new StringBuilder();
		sb.append("\\end{document}\n");
		return sb;
	}

	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setOlapJdbc(OLAPJDBC olapJdbc) {
		this.olapJdbc = olapJdbc;
	}

	public void setResourceViewDao(ResourceViewDao resourceViewDao) {
		this.resourceViewDao = resourceViewDao;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setOlapLatex(OLAPLaTeX olapLatex) {
		this.olapLatex = olapLatex;
	}
	
}