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

public class SLDEditorBackground extends SLDEditorTabItem {

	private List<SLDEditorRuleForBackgrounds> rules;
	private SLDEditorWindow myWindow;
	
	public SLDEditorBackground(String header, SLDEditorWindow myWindow) {
		super(header);
		this.myWindow = myWindow;
		this.getTabItem().setSize("385px", "450px");
		//this.getTabItem().setSize("585px", "650px");
		this.getTabItem().setScrollMode(Scroll.AUTO);
		rules = new ArrayList<SLDEditorRuleForBackgrounds>();
	}
	
	public TabItem build() {
		
		List<SLDEditorRuleVO> list = myWindow.getVoList();
		if(list == null)
		{
			SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(this);
			this.getTabItem().add(rule.build());
			rules.add(rule);
		}
		else
		{
			if(list.size() != 0)
			{
				SLDEditorRuleVO temp= list.get(0);
				if(temp.getType() == SLDEditorConstants.BACKGROUND)
				{
					SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(this, true);
					this.getTabItem().add(rule.build());
					rules.add(rule);
					this.populateUi(temp, rule);
				}
				else
				{
					SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(this);
					this.getTabItem().add(rule.build());
					rules.add(rule);
				}
			}
			else 
			{
				SLDEditorRuleForBackgrounds rule = new SLDEditorRuleForBackgrounds(this);
				this.getTabItem().add(rule.build());
				rules.add(rule);
			}
				
		}	
		return this.getTabItem();
	}

	private void populateUi(SLDEditorRuleVO temp, SLDEditorRuleForBackgrounds rule) {
		rule.getFillPolygons().setValue(true);
		rule.buildPolygonAlgorithmPanel(rule, this, temp);
	}

	public List<SLDEditorRuleForBackgrounds> getRules() {
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
						if(temp.getType() == SLDEditorConstants.BACKGROUND)
						{
							SLDEditorController.addRule(this,true, false);
						}
						else
						{
							//System.out.println("SLDEditorConstants. not BACKGROUND ");
						}
					}
					
				int iRules =0;
				for(int iLine=1; iLine < list.size(); iLine++)
					{
						temp = list.get(iLine);
						if(temp.getType() == SLDEditorConstants.BACKGROUND)
						{
							if(temp.getLabelIntervalPropertyName().equalsIgnoreCase("AREA"))
							{
								//Calling Equal area method
								SLDEditorController.fillEqualInterval(rules.get(iRules), this, temp);
							}
							else
							{
								//Calling Equal interval method
								SLDEditorController.fillEqualArea(rules.get(iRules), this, temp);
							}
							System.out.println("SLDEditorConstants.BACKGROUND 2");
							iRules++;
						}
						else
						{
							System.out.println("SLDEditorConstants. not BACKGROUND 2 ");
						}
					}
				}
			}
		}
	}	
}