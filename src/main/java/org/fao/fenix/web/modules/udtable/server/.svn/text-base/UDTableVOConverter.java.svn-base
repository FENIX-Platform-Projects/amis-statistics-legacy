package org.fao.fenix.web.modules.udtable.server;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.udtable.UDTable;
import org.fao.fenix.core.domain.udtable.UDTableCell;
import org.fao.fenix.core.domain.udtable.UDTableFilter;
import org.fao.fenix.core.domain.udtable.UDTableHeader;
import org.fao.fenix.core.domain.udtable.UDTableRow;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableCellVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableHeaderVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableRowVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

public class UDTableVOConverter {
	
	private static Logger LOGGER = Logger.getLogger(UDTableVOConverter.class);

	/* ********************************** */
	/* ********** VO TO OBJECT ********** */
	/* ********************************** */
	
	public static UDTableFilterVO filter2vo(UDTableFilter f) {
		UDTableFilterVO vo = new UDTableFilterVO();
		vo.setHeader(f.getHeader());
		vo.setDataType(f.getDataType());
		vo.setAllowedValues(f.getAllowedValues());
		vo.setResourceId(f.getResourceId());
		return vo;
	}
	
	public static UDTableCellVO cell2vo(UDTableCell c) {
		UDTableCellVO vo = new UDTableCellVO();
		vo.setCellId(c.getCellId());
		vo.setValue(c.getValue());
		vo.setLanguage(c.getLanguage());
		vo.setCode(c.getCode());
		return vo;
	}
	
	public static UDTableRowVO row2vo(UDTableRow r) {
		UDTableRowVO vo = new UDTableRowVO();
		vo.setRowId(r.getRowId());
		for (UDTableCell c : r.getCells())
			vo.addCell(cell2vo(c));
		for (UDTableCellVO t : vo.getCells())
			System.out.println("-> " + t.getCode() + "\t|\t" + t.getValue());
		System.out.println();
		return vo;
	}
	
	public static UDTableVO table2vo(UDTable t) {
		UDTableVO vo = new UDTableVO();
		vo.setDatasetRecordCount(t.getDatasetRecordCount());
		vo.setIsCore(t.getIsCore());
		for (UDTableRow r : t.getRows())
			vo.addRow(row2vo(r));
		if (t.getHeaders() != null)
			for (UDTableHeader h : t.getHeaders())
				vo.addHeader(header2vo(h));
		if (t.getFilters() != null)
			for (UDTableFilter f : t.getFilters())
				vo.addFilter(filter2vo(f));
		return vo;
	}
	
	public static UDTableHeaderVO header2vo(UDTableHeader h) {
		UDTableHeaderVO vo = new UDTableHeaderVO();
		vo.setRawType(h.getRawType());
		vo.setDataType(h.getDataType());
		vo.setIsCode(h.getIsCode());
		vo.setHasCodingSystem(h.getHasCodingSystem());
		vo.setHeader(h.getHeader());
		vo.setCodingSystemName(h.getCodingSystemName());
		vo.setCodingSystemRegion(h.getCodingSystemRegion());
		vo.setCodingSystemLang(h.getCodingSystemLang());
		return vo;
	}
	
	/* ********************************** */
	/* ********** OBJECT TO VO ********** */
	/* ********************************** */
	
	public static UDTableFilter vo2filter(UDTableFilterVO vo) {
		UDTableFilter f = new UDTableFilter();
		f.setHeader(vo.getHeader());
		f.setDataType(vo.getDataType());
		f.setAllowedValues(vo.getAllowedValues());
		f.setResourceId(vo.getResourceId());
		if ( !vo.getCodingSystemLang().isEmpty() && !vo.getCodingSystemName().isEmpty() && !vo.getCodingSystemRegion().isEmpty()) {
			f.setCodingSystemName(vo.getCodingSystemName());
			f.setCodingSystemRegion(vo.getCodingSystemRegion());
			f.setCodingSystemLang(vo.getCodingSystemLang());
			f.setHasCodingSystem(true);
		}
		else 
			f.setHasCodingSystem(false);
		return f;
	}
	
	public static UDTableCell vo2cell(UDTableCellVO vo) {
		UDTableCell c = new UDTableCell();
		c.setCellId(vo.getCellId());
		c.setValue(vo.getValue());
		c.setLabel(vo.getLabel());
		c.setLanguage(vo.getLanguage());
		return c;
	}
	
	public static UDTableRow vo2row(UDTableRowVO vo) {
		UDTableRow r = new UDTableRow();
		r.setRowId(vo.getRowId());
		for (UDTableCellVO cvo : vo.getCells())
			r.addCell(vo2cell(cvo));
		return r;
	}
	
	public static UDTable vo2table(UDTableVO vo) {
		UDTable t = new UDTable();
		t.setIsCore(vo.getIsCore());
		for (UDTableRowVO rvo : vo.getRows())
			t.addRow(vo2row(rvo));
		for (UDTableHeaderVO hvo : vo.getHeaders())
			t.addHeader(vo2header(hvo));
		for (UDTableFilterVO fvo : vo.getFilters())
			t.addFilter(vo2filter(fvo));
		return t;
	}
	
	public static UDTableHeader vo2header(UDTableHeaderVO vo) {
		UDTableHeader h = new UDTableHeader();
		h.setRawType(vo.getRawType());
		h.setDataType(vo.getDataType());
		h.setIsCode(vo.getIsCode());
		h.setHasCodingSystem(vo.getHasCodingSystem());
		h.setHeader(vo.getHeader());
		h.setCodingSystemLang(vo.getCodingSystemLang());
		h.setCodingSystemName(vo.getCodingSystemName());
		h.setCodingSystemRegion(vo.getCodingSystemRegion());
		return h;
	}
	
}