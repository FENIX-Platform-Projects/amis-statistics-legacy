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
package org.fao.fenix.web.modules.x.client.control;

import org.fao.fenix.web.modules.x.client.view.XExplorerMetadataPanel;
import org.fao.fenix.web.modules.x.common.vo.XExplorerModelData;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class XExplorerMetadataController extends XExplorerController {

	
	public static void updateMetadata(XExplorerMetadataPanel xExplorerMetadataPanel, XExplorerModelData selected) {
		cleanMetadata(xExplorerMetadataPanel);
		fillMetadata(xExplorerMetadataPanel, selected);
	}
	
	private static void cleanMetadata(XExplorerMetadataPanel xExplorerMetadataPanel) {
		VerticalPanel wrapper = xExplorerMetadataPanel.getWrapper();
		for (int i = wrapper.getItemCount() - 1 ; i >= 0 ; i--)
			wrapper.remove(wrapper.getWidget(i));
	}
	
	private static void fillMetadata(XExplorerMetadataPanel xExplorerMetadataPanel, XExplorerModelData selected) {
		xExplorerMetadataPanel.updateMetadata(selected);
		xExplorerMetadataPanel.getMetadataPanel().getLayout().layout();
		xExplorerMetadataPanel.getWrapper().getLayout().layout();
	}
	
}
