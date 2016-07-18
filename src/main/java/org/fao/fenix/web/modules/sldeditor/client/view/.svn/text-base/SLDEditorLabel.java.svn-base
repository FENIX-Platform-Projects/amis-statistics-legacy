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
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorConstants;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;

public class SLDEditorLabel extends SLDEditorTabItem {	
	private List<SLDEditorRuleForLabels> rules;
	private SLDEditorWindow myWindow;
	
	public SLDEditorLabel(String header, SLDEditorWindow myWindow) {
		super(header);
		this.myWindow = myWindow;
		this.getTabItem().setSize("385px", "450px");
		//this.getTabItem().setSize("585px", "650px");
		this.getTabItem().setScrollMode(Scroll.AUTO);
		rules = new ArrayList<SLDEditorRuleForLabels>();
	}
	
	public TabItem build() {
		if(myWindow.getVoList() == null)
		{
			SLDEditorRuleForLabels rule = new SLDEditorRuleForLabels(this);
			this.getTabItem().add(rule.build());
			rules.add(rule);
		}
		else
		{
			boolean flag = false;
			SLDEditorRuleVO temp= null;
			List<SLDEditorRuleVO> list = myWindow.getVoList();
			for(int iLine=1; iLine < list.size(); iLine++)
			{
				temp = list.get(iLine);
				if(temp.getType() == SLDEditorConstants.LABEL)
				{
					flag=true;
					break;
				}
			}
			if(flag)
			{
				this.populateUi();
			}
			else
			{
				SLDEditorRuleForLabels rule = new SLDEditorRuleForLabels(this);
				this.getTabItem().add(rule.build());
				rules.add(rule);
			}
		}		
		return this.getTabItem();
	}

	public List<SLDEditorRuleForLabels> getRules() {
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
					for(int iLine=1; iLine < list.size(); iLine++)
					{
						temp = list.get(iLine);
						if(temp.getType() == SLDEditorConstants.LABEL)
						{
							SLDEditorController.addRule(this,true);
						}
						else
						{
							//System.out.println("SLDEditorConstants. not LABEL ");
						}
					}
					
				int iRules =0;
					for(int iLine=1; iLine < list.size(); iLine++)
					{
						temp = list.get(iLine);
						if(temp.getType() == SLDEditorConstants.LABEL)
						{
							SLDEditorController.styleLabels(rules.get(iRules), this, temp);
							iRules++;
						}
						else
						{
							//System.out.println("SLDEditorConstants. not LABEL 2 ");
						}
					}
				}
			}
		}
	}
}