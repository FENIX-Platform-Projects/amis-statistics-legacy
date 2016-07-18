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

package org.fao.fenix.web.modules.text.client.control.textgroup;

import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.ipc.client.view.ConfirmWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceParentModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.text.client.view.textgroup.RenameTextWindow;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.text.common.model.Text;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class TextMenuController {

	
	public static SelectionListener<MenuEvent> removeText(final Text textItem, final TextGroupWindow tgw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				tgw.removeText(textItem.getId());
				
        		GWT.log("Removing text ... ", null);
        		TextServiceEntry.getInstance().removeTextFromTextGroup(tgw.getTextGroupID(), textItem.getId(), new AsyncCallback<Long>() {

        			public void onSuccess(final Long result) {
        				GWT.log("Saving complete ", null);
        				//FenixAlert.info(I18N.print().updateCompleted(), MetadataLangEntry
        					//	.getInstance().updateSuccessful());
        			}

        			public void onFailure(Throwable caught) {
        			//	FenixAlert.error(I18N.print().error(),
        			//	"The text group update failed because you do not have update permission or there is a technical problem");
        			}
				});
				}
		};
	}

	
	public static SelectionListener<MenuEvent> renameText(final Text textItem, final TextGroupWindow tgw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				new RenameTextWindow(textItem.getId(), textItem.getName(), tgw).build();
			}
		};
	}

		
	public static SelectionListener<MenuEvent> showMetadata(final Text textItem, final boolean isTextEditable) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				ResourceOpener.openMetadata(textItem.getId(), isTextEditable);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> deleteText(final Text textItem, final TextGroupWindow tgw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				Window window = new Window();
				REServiceEntry.getInstance().checkConnectedResources(textItem.getId(), ResourceType.TEXTVIEW, new AsyncCallback<List<ResourceParentModel>>() {
					public void onSuccess(List<ResourceParentModel> result) {
						String text = new String();
						text += "<font color='red'> <b>" + BabelFish.print().areYouSureDeleteResource() + "</b></font>";

						if ( !result.isEmpty() ) {	
							text += "<br><br> <b>"+ BabelFish.print().resourceConnected() +":</b><br>";
							/** TODO: the resourcetype should be translated dynamically **/
							for(ResourceParentModel resource : result) {
								System.out.println("-> " + resource.getName() + " | " + resource.getType());
								text += resource.getType() + " | " + resource.getName() + "<br>";
							}
						}

						new ConfirmWindow(BabelFish.print().confirmResourceDelete(), text, CatalogueContextMenuController.deleteResourceConfirmed(textItem.getId(),ResourceType.TEXTVIEW));
					}

					public void onFailure(Throwable caught) {

					}
				});
				}
		};
	}

}
