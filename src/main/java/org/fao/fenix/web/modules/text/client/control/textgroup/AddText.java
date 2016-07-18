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
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.RECallback;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddText {
    private TextGroupWindow tgw;

    public AddText(TextGroupWindow tgw) {
        this.tgw = tgw;
    }

	public void load()
	{
		ResourceExplorerText re = new ResourceExplorerText(tgw);
        re.setCallback(new TextsSelected());
	}

    class TextsSelected implements RECallback {

        public void callback(ResourceExplorer re, List<ResourceChildModel> selectedResourceId) {
            re.getWindow().close();

            if(selectedResourceId.isEmpty()) {
               FenixAlert.error(BabelFish.print().error(), BabelFish.print().noTextsSelected());
                return;
            }

            List<Long> textIds = new ArrayList<Long>();
            HashMap<Long, PermissionVo> textmap = new HashMap<Long, PermissionVo>();
           
            for(ResourceChildModel text: selectedResourceId) {
            	PermissionVo vo = new PermissionVo();
            	vo.setCanWrite(text.getFenixResourceVo().isHasWritePermission());
            	vo.setCanDelete(text.getFenixResourceVo().isHasDeletePermission());
            	vo.setCanDownload(text.getFenixResourceVo().isHasDownloadPermission());
            	
            	textmap.put(Long.valueOf(text.getId()), vo);
            }

            TextServiceEntry.getInstance().getTextsList(textmap, new TextsLoaded());
        }
    }

    class TextsLoaded implements AsyncCallback<List<TextVO>> {

        public void onSuccess(List<TextVO> textList) {
        	List<Long> textIds = new ArrayList<Long>();
			
        	for (TextVO textVO : textList) {
        		tgw.addText(textVO);
        		textIds.add(textVO.getResourceId());
        	}
         	
        	if(textIds!=null || textIds.size() > 0){
        		GWT.log("Saving ... ", null);
        		TextServiceEntry.getInstance().addTextToTextGroup(tgw.getTextGroupID(), textIds, new AsyncCallback<Long>() {

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
            //save text group
           // TextListToolbarController.save(tgw); 
        }

        public void onFailure(Throwable arg0) {
           FenixAlert.error(BabelFish.print().error(),
                                arg0.getMessage());
        }
    }
}
