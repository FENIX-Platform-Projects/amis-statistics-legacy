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

public class DescriptorVO implements IsSerializable {

	private long id;

	private String contentDescriptor;

	private String header;
	
	private List<OptionVO> options;
	
	private boolean isKey;
	
	public DescriptorVO() {
		this.setContentDescriptor("");
		this.setHeader("");
		this.setId(0);
		this.setKey(false);
		this.setOptions(new ArrayList<OptionVO>());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContentDescriptor() {
		return contentDescriptor;
	}

	public void setContentDescriptor(String contentDescriptor) {
		this.contentDescriptor = contentDescriptor;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	public List<OptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionVO> options) {
		this.options = options;
	}
	
	public void addOption(OptionVO option) {
		if (this.options == null)
			this.options = new ArrayList<OptionVO>();
		this.options.add(option);
	}
	
	public String toString() {
		String s = this.getHeader() + " | " + this.getContentDescriptor() + " | " + this.getOptions().size() + " | options\n";
		for (OptionVO vo : this.getOptions())
			s += "\t" + vo.getOptionName() + " | " + vo.getOptionValue() + "\n";
		return s;
	}
	
}