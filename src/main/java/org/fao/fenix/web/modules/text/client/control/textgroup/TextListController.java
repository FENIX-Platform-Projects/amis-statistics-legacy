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

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.client.view.textgroup.CloneTextGroupWindow;
import org.fao.fenix.web.modules.text.client.view.textgroup.RenameTextWindow;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextListContextMenu;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextListPanel;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextViewerPanel;
import org.fao.fenix.web.modules.text.common.model.Text;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;
import org.fao.fenix.web.modules.text.common.vo.TextGroupVO;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TextListController {

	public static void buildTextList(final TextGroupWindow tgw, final TextListPanel tlp, final TextViewerPanel viewer, final ListStore<Text> store)
	{
		if(tgw.getTextGroupID()!=null) {
		 TextServiceEntry.getInstance().getTextGroup(tgw.getTextGroupID(), new AsyncCallback<TextGroupVO>() {

				public void onSuccess(final TextGroupVO result) {
					tgw.setTextGroup(result);
					if(result.getTextList()!=null && result.getTextList().size() > 0) {
						tlp.populateStore(result.getTextList());
						Text model = tlp.getSelectedItem().getModel();

						//tlp.addTexts(result.getTextList());
						//System.out.println("tlp.getSelectedText(): "+ tlp.getSelectedText().getTitle());
						System.out.println("tlp.getSelectedText(): "+ model.getName());
						//viewer.populateViewer(tlp.getSelectedText());

						viewer.populateViewer(model);
					}
					tgw.getWest().getLayout().layout();
					tgw.getCenter().getLayout().layout();
				}

				public void onFailure(Throwable caught) {
					System.out.println("getTextGroup failed: "+ caught.getMessage());
				}

			});
		}
	}
	
    public static Listener<ComponentEvent> selection(final TextListPanel tlp, final TextViewerPanel tvp) {

		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				DataList l = (DataList) ce.getComponent();
				if(l.getSelectedItems().size() == 1) {
					Text li = l.getSelectedItem().getModel();
					//TextItem li = (TextItem)l.getSelectedItem();
					tvp.populateViewer(li);					
				}
			}
		};
	}
    
 
    public static SelectionListener<ButtonEvent> clone(final DateField referenceDate, final TextGroupWindow tgw, final CloneTextGroupWindow window) {
    	return new SelectionListener<ButtonEvent>() {
    		public void componentSelected(ButtonEvent ce) {

    			List<TextVO> textVOList = new ArrayList<TextVO>();

    			for(DataListItem item: tgw.getTextListPanel().getAllTextItems()) {
    				Text text = item.getModel();
    				textVOList.add(text.getTextVO());
    			}

    			TextServiceEntry.getInstance().cloneTextGroup(tgw.getTextGroup(), textVOList, referenceDate.getRawValue(), new AsyncCallback<FenixResourceVo>() {

    				public void onSuccess(final FenixResourceVo rsrc) {

    					// add to Project Manager
    					if(rsrc!=null){
    						System.out.println("New Fenix Resource is NOT null");
    						final List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
    						resourceList.add(PMModel.createResourceModel(rsrc));

    						PMModel.addNewResourceToProjectManager(resourceList, PMModel.createResourceModel(rsrc), ResourceType.TEXTGROUP);
    					}
    					else System.out.println("New Fenix Resource is null");

    					FenixAlert.info(BabelFish.print().copyComplete(), "'"+rsrc.getTitle()+"' "+BabelFish.print().textGroupInProjectManager()); 

    					window.getWindow().close();
    				}

    				public void onFailure(Throwable caught) {
    					FenixAlert.error(BabelFish.print().copyFailed(), BabelFish.print().copyFailed()); 
    					System.out.println("cloneTextGroup failed: "+ caught.getMessage());
    				}

    			});
    		}
    	};
    }
    
    public static SelectionListener<ButtonEvent> cancel(final FenixWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
			}
		};
	}
    
    public static SelectionListener<ButtonEvent> rename(final TextField<String> textField, final Long textId, final TextGroupWindow tgw, final RenameTextWindow window) {
    	return new SelectionListener<ButtonEvent>() {
    		public void componentSelected(ButtonEvent ce) {
    			final String newTitle = textField.getValue();

    			MetadataServiceEntry.getInstance().rename(textId, newTitle, new AsyncCallback() {

    				public void onSuccess(final Object result) {

    					//set new title in the interface
    					Text textItem = tgw.getTextListPanel().getTextItem(textId);
    					textItem.setName(newTitle);
    					textItem.getTextVO().setTitle(newTitle);
    					tgw.getWest().getLayout().layout();

    					FenixAlert.info(BabelFish.print().renameComplete(),BabelFish.print().renameSuccessful()); 

    					window.getWindow().close();

    				}

    				public void onFailure(Throwable caught) {
    					FenixAlert.error(BabelFish.print().renameFailed(), BabelFish.print().renameFailed()); 
    					System.out.println("Rename failed: "+ caught.getMessage());
    				}

    			});

    		}
    	};
    }
    
    public static Listener setContextMenu(final DataList list, final TextGroupWindow window) {
		return new Listener() {
			public void handleEvent(BaseEvent event) {
				list.setContextMenu(new TextListContextMenu(window).build(list.getSelectedItem()));
			}
		};
	}
}
