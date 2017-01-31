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
package org.fao.fenix.web.modules.qb.client.control;

import org.fao.fenix.web.modules.qb.client.view.QueryBuilder;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;


public class PanelOneController {

	public static SelectionListener<ButtonEvent> openResourceExplorerDataset(final QueryBuilder queryBuilder) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new ResourceExplorerDataset(queryBuilder);
			}
		};
	}

	public static SelectionListener<ButtonEvent> openTable(final ResourceChildModel model) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {					
					new TableWindow().showAllData(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
					//new TableWindow().build(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
				}
			};
		}

}