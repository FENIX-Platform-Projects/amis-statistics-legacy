/*
 *   Copyright (C) 2007-2008 Food and Agriculture Organization of the
 *   United Nations (FAO-UN)
 *  
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or (at
 *  your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */

package org.fao.fenix.web.modules.map.common.vo;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author etj <e_tajariol AT users.sourceforge.net>
 */
public class ClientProjectedDataDimension implements IsSerializable {
	/** this value will be used as combobox item key for the ANY selection */
	public final static String UNCONSTRAINED_CLIENT_VALUE = "#:ANY:#";

	public String name;
	public String label;
	public String currentId;
	public boolean isUnconstrained = false;
	public ArrayList<String[]> allowableValues; // id, label

	public ClientProjectedDataDimension() {}
	
	public ClientProjectedDataDimension(String name) {
		this.name = name;
	}	
}
