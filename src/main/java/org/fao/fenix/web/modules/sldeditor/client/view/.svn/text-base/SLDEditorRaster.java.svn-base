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
package org.fao.fenix.web.modules.sldeditor.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;

public class SLDEditorRaster extends SLDEditorTabItem {

	private List<SLDEditorRuleForRaster> rules;
	private SLDEditorWindow myWindow;
	
	public SLDEditorRaster(String header, SLDEditorWindow myWindow) {
		super(header);
		this.myWindow = myWindow;
		this.getTabItem().setSize("385px", "450px");
		this.getTabItem().setScrollMode(Scroll.AUTO);
		rules = new ArrayList<SLDEditorRuleForRaster>();
	}
	
	public TabItem build() {
		if(myWindow.getVoList() == null)
		{
			SLDEditorRuleForRaster rule = new SLDEditorRuleForRaster(this);
			this.getTabItem().add(rule.build());
			this.getRules().add(rule);
		}
		else
		{
			this.populateUi();
		}
	
		return this.getTabItem();
	}

	public List<SLDEditorRuleForRaster> getRules() {
		return rules;
	}
	
	private void populateUi() {
		
		if(myWindow != null)
		{
			List<SLDEditorRuleVO> list = myWindow.getVoList();
			if(list != null)
			{
				if(list.size() != 0)
				{
					SLDEditorRuleVO temp= null;
					for(int iRaster=1; iRaster < list.size(); iRaster++)
					{
						temp = list.get(iRaster);
						SLDEditorController.addRule(this,true);
					}
					
				int iRules =0;
					for(int iRaster=1; iRaster < list.size(); iRaster++)
					{
						temp = list.get(iRaster);
						SLDEditorController.rasterRuleFormat(rules.get(iRules), this, temp);
						iRules++;
					}
				}
			}
		}
	}	
}