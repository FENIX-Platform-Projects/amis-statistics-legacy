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
package org.fao.fenix.web.modules.metadataeditor.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DatasetTypeVO implements IsSerializable {

	private long id;
	
	private String title;
	
	private List<DescriptorVO> descriptors;
	
	public DatasetTypeVO() {
		this.setDescriptors(new ArrayList<DescriptorVO>());
		this.setId(0);
		this.setTitle("");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DescriptorVO> getDescriptors() {
		return descriptors;
	}

	public void setDescriptors(List<DescriptorVO> descriptors) {
		this.descriptors = descriptors;
	}
	
	public void addDescriptorVO(DescriptorVO vo) {
		if (this.descriptors == null)
			this.descriptors = new ArrayList<DescriptorVO>();
		this.descriptors.add(vo);
	}
	
}