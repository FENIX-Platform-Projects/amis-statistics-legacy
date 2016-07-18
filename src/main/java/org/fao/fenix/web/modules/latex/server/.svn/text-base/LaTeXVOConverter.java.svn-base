/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.latex.server;

import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.CustomDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.resourceview.BooleanResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.DescriptorView;
import org.fao.fenix.core.domain.resourceview.NumericResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.resourceview.ResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.StringResourceViewSetting;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;

public class LaTeXVOConverter {

	public static ResourceViewVO resourceView2vo(ResourceView r) {
		ResourceViewVO vo = new ResourceViewVO();
		if (r.getTitle() != null)
			vo.setTitle(r.getTitle());
		if (r.getOlapHTML() != null)
		vo.setOlapHTML(r.getOlapHTML());
		if (r.getOlapFunction() != null)
		vo.setOlapFunction(r.getOlapFunction());
		if (r.getResourceId() != null)
		vo.setResourceId(r.getResourceId());
		if (r.getQuery() != null)
		vo.setQuery(r.getQuery());
		if (r.getType() != null)
		vo.setType(r.getType());
		if (r.getDatasets() != null)
			for (Dataset d : r.getDatasets()) 
				vo.addDataset(dataset2vo(d, false));
		if (r.getDescriptorViews() != null)
			for (DescriptorView dv : r.getDescriptorViews())
				vo.addDescriptorView(descriptor2vo(dv));
		if (r.getSettings() != null)
			for (ResourceViewSetting s : r.getSettings())
				vo.addResourceViewSetting(setting2vo(s));
		return vo;
	}
	
	public static DatasetVO dataset2vo(Dataset d, boolean addDescriptors) {
		DatasetVO vo = new DatasetVO();
		vo.setDatasetName(d.getTitle());
		vo.setDsId(String.valueOf(d.getResourceId()));
		if (addDescriptors)
			for (Descriptor des : d.getDatasetType().getDescriptors())
				vo.addDescriptorViewVO(descriptor2vo(des));
		return vo;
	}
	
	@SuppressWarnings("deprecation")
	public static DescriptorViewVO descriptor2vo(Descriptor d) {
		DescriptorViewVO vo = new DescriptorViewVO();
		vo.setContentDescriptor(d.getContentDescriptor());
		vo.setHeader(d.getHeader());
		return vo;
	}
	
	public static ResourceViewSettingVO setting2vo(ResourceViewSetting r) {
		ResourceViewSettingVO vo = new ResourceViewSettingVO();
		vo.setId(r.getId());
		vo.setSettingName(r.getSettingName());
		if (r instanceof BooleanResourceViewSetting) {
			vo.setValue(String.valueOf(((BooleanResourceViewSetting)r).isSelected()));
		} else if (r instanceof NumericResourceViewSetting) {
			vo.setValue(String.valueOf(((NumericResourceViewSetting)r).getQuantity().intValue()));
		} else if (r instanceof StringResourceViewSetting) {
			vo.setValue(String.valueOf(((StringResourceViewSetting)r).getValue()));
		}
		return vo;
	}
	
	public static ResourceView vo2ResourceView(ResourceViewVO vo) {
		ResourceView r = new ResourceView();
		r.setTitle(vo.getTitle());
		r.setOlapHTML(vo.getOlapHTML());
		r.setOlapFunction(vo.getOlapFunction());
		r.setResourceId(vo.getResourceId());
		r.setType(vo.getType());
		r.setQuery(vo.getQuery());
		r.setOlapFunction(vo.getOlapFunction());
		r.setResourceId(vo.getResourceId());
		if ((vo.getDatasets() != null) && (!vo.getDatasets().isEmpty()))
			for (DatasetVO dvo : vo.getDatasets()) 
				r.addDataset(vo2coreDataset(dvo));
		if ((vo.getDescriptorViews() != null) && (!vo.getDescriptorViews().isEmpty()))
			for (DescriptorViewVO dvvo : vo.getDescriptorViews())
				r.addDescriptorView(vo2descriptor(dvvo));
		if ((vo.getSettings() != null) && (!vo.getSettings().isEmpty())) {
			for (ResourceViewSettingVO svo : vo.getSettings()) {
				DesignerConstants resourceViewSettingType = DesignerConstants.valueOf(svo.getResourceViewSettingType());
				switch (resourceViewSettingType) {
					case NUMERIC: r.addResourceViewSetting(vo2numericSetting(svo)); break;
					case STRING: r.addResourceViewSetting(vo2stringSetting(svo)); break;
					case BOOLEAN: r.addResourceViewSetting(vo2booleanSetting(svo)); break;
				}
			}
		}
		return r;
	}
	
	public static CoreDataset vo2coreDataset(DatasetVO vo) {
		CoreDataset d = new CoreDataset();
		d.setTitle(vo.getDatasetName());
		d.setResourceId(Long.valueOf(vo.getDsId()));
		return d;
	}
	
	public static CustomDataset vo2customDataset(DatasetVO vo) {
		CustomDataset d = new CustomDataset();
		d.setTitle(vo.getDatasetName());
		d.setResourceId(Long.valueOf(vo.getDsId()));
		return d;
	}
	
	public static DescriptorView vo2descriptor(DescriptorViewVO vo) {
		DescriptorView d = new DescriptorView();
		d.setContentDescriptor(vo.getContentDescriptor());
		d.setHeader(vo.getHeader());
		return d;
	}
	
	public static StringResourceViewSetting vo2stringSetting(ResourceViewSettingVO vo) {
		StringResourceViewSetting r = new StringResourceViewSetting();
		r.setId(vo.getId());
		r.setSettingName(vo.getSettingName());
		r.setValue(vo.getValue());
		return r;
	}
	
	public static NumericResourceViewSetting vo2numericSetting(ResourceViewSettingVO vo) {
		NumericResourceViewSetting r = new NumericResourceViewSetting();
		r.setId(vo.getId());
		r.setSettingName(vo.getSettingName());
		r.setQuantity(Double.valueOf(vo.getValue()));
		return r;
	}
	
	public static BooleanResourceViewSetting vo2booleanSetting(ResourceViewSettingVO vo) {
		BooleanResourceViewSetting r = new BooleanResourceViewSetting();
		r.setId(vo.getId());
		r.setSettingName(vo.getSettingName());
		r.setSelected(Boolean.valueOf(vo.getValue()));
		return r;
	}
	
}