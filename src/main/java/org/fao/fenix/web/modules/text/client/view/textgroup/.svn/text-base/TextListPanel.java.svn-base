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

package org.fao.fenix.web.modules.text.client.view.textgroup;


import java.util.List;

import org.fao.fenix.web.modules.text.client.control.textgroup.TextListController;
import org.fao.fenix.web.modules.text.client.control.vo.TextItem;
import org.fao.fenix.web.modules.text.common.model.Text;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.binder.DataListBinder;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;



/**
 * This panel contains the list of the texts associated to the text group
 * and the related controls to handle them.
 *
 */
public class TextListPanel {

	private final ContentPanel mainPanel = new ContentPanel();
	private final VerticalPanel listPanel = new VerticalPanel();
	
	
	private final ToolBar toolbar;

	final private DataList list = new DataList();
	
	final private ListStore<Text> store = new ListStore<Text>();
	
	private final TextGroupWindow parent;
	
	public TextListPanel(TextGroupWindow parentWindow, TextViewerPanel viewer, boolean isEditable)
	{
		this.parent = parentWindow;

		//MAIN PANEL
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setHeaderVisible(false);
		mainPanel.setScrollMode(Scroll.AUTO);
		
		toolbar = TextListToolbar.build(parent, isEditable);
		mainPanel.setTopComponent(toolbar);
		
		// LIST PANEL
		listPanel.setSpacing(10);
		//listPanel.add(list);
		
		
		//
		    list.setSelectionMode(Style.SelectionMode.SINGLE);
	        list.setFlatStyle(true);
	        list.setBorders(false);
	       
	        listPanel.add(list);
	        
	        DataListBinder<Text> binder = new DataListBinder<Text>(list, store);
	        binder.setDisplayProperty("name");
	       /* binder.setStringProvider(new ModelStringProvider<Text>() {

	            public String getStringValue(Text text, String property) {
	            	GWT.log("store.getRecord(text)  = "+store.getRecord(text), null);
	                if (store.getRecord(text).isDirty()) {
	                    return "<span style='color: red'>" + text.getName() + "</span>";
	                }
	                else {
	                    return "" +text.getName() ;
	                }
	            };
	        });*/
	        binder.init();

	        if(parent.getTextGroupID()!=null){
				TextListController.buildTextList(parent, this, viewer, store);
			}
	        
	         //store.add(getData());

	      

		
		///
	//	list.setSelectionMode(SelectionMode.SINGLE);
	//	final DataListSelectionModel sm = list.getSelectionModel();
	//	list.setSelectionModel(sm);
	//	list.setBorders(false);
		
			
		list.setContextMenu(new TextListContextMenu(parent).build(null));
		list.addListener(Events.ContextMenu, TextListController.setContextMenu(list, parent));
		
		list.addListener(Events.SelectionChange, TextListController.selection(this, viewer));
		list.setWidth(190);
       
		//if(parent.getTextGroupID()!=null){
		//	TextListController.buildTextList(parent, this, viewer, store);
		//}
		
		mainPanel.add(listPanel);
		
	}	

	public ContentPanel build()
	{
		return mainPanel;
	}

    public TextItem getSelectedText()
    {
        return (TextItem)list.getSelectedItem();
    }

    public DataListItem getSelectedItem()
    {
        return list.getSelectedItem();
    }
    
	public void addTexts(List<TextVO> texts)
	{
		for(TextVO text : texts)	{
			addText(text);
		}
	}
	
	public void populateStore(List<TextVO> texts)
	{
		for(TextVO text : texts)	{
			store.add(new Text(text));
		}
		store.setSortField("name");
		
		DataListItem item = list.getItem(0);
		list.getSelectionModel().select(item, true);
		
		GWT.log("store count = "+store.getCount(), null);
		GWT.log("list item count  = "+list.getItemCount(), null);
		
		
	}


	public int getTextCount() {
		return list.getItemCount();
	}

	public TextItem getTextByIndex(int index) {
		return (TextItem)list.getItem(index);
	}


	public Text getTextItem(long textId)
	{
		for(DataListItem dli: list.getItems()) {
			Text li = dli.getModel();
			if(li.getId() == textId) {
				return li;
			}
		}

   		Info.display("Alert", "Text not found '"+textId+"'");
		return null;
	}

	private int getTextIndex(long textId)
	{
        for(int i=0; i<list.getItemCount(); i++)
            if( ((TextItem)list.getItem(i)).getTextId() == textId)
                return i;

   		Info.display("Alert", "Text not found '"+textId+"'");
        return -1;
	}



	public void renameText(long textId, String newTitle) {
		Text li = getTextItem(textId);
		if(li != null) {
			li.setName(newTitle);
			li.getTextVO().setTitle(newTitle);
		}
	}

	public void addText(TextVO text)
    {
		Text model = new Text(text);
        store.add(model);
        list.setSelectedItem(list.getItem(store.indexOf(model)));
    }


    private static TextItem buildText(TextVO text)
	{
		TextItem item = new TextItem();

        String title = text.getTitle();
        if(title.length() > 32) { // crop title if too long...
            item.setTitle(title);
            title = title.substring(0, 29) + "...";
        }
		item.setText(title);
		item.setTextId(text.getResourceId());
		item.setToolTip(text.getTitle());
		item.setTextVO(text);

		item.setId(Long.toString(text.getResourceId()));
		item.setIconStyle("textResourceIcon");
		

        return item;
	}


    public void removeText(long textId) {
    	 Text li = getTextItem(textId);
    	 store.remove(li);
    }
    
    public List<DataListItem> getAllTextItems(){
    	List<DataListItem> items = list.getItems();
    	return items;
    }

}

