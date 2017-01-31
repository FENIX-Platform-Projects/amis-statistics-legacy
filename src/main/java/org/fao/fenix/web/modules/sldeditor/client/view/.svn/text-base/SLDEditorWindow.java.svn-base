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

import java.util.List;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class SLDEditorWindow extends FenixWindow {

	private SLDEditorPanel sldEditorPanel;
	
	private Button closeButton;
	
	private Button createSLDButton;
	
	private Button uploadSLDButton;
	
	private Button saveSLDButton;

	private static final String WINDOW_WIDTH = "415px";

	private static final String WINDOW_HEIGHT = "550px";
	
	private static final String WINDOW_WIDTH_RASTER = "650px";

	private static final String WINDOW_HEIGHT_RASTER = "600px";
	
	private static int algorithm;
	
	private String idPanel;
	
	private List<SLDEditorRuleVO> voList;

	public SLDEditorWindow() {
		sldEditorPanel = new SLDEditorPanel(this);
		SLDEditorRuleForBackgrounds.setWindow(this);
		this.idPanel = "shape";
	}
	
	public SLDEditorWindow(String raster) {
		sldEditorPanel = new SLDEditorPanel(raster, this);
		this.idPanel = "raster";
	}

	
	public void build(List<SLDEditorRuleVO> voList) {
		this.voList = voList;
		if(this.idPanel == "shape")
		{
			buildCenterPanel();
			format(0);
		}
		else if(this.idPanel == "raster")
		{
			buildCenterPanelRaster();
			format(1);
		}	
		show();
	}
	
	public void importSld() {
		SLDEditorController.createMsgBox();
	}

	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(sldEditorPanel.build());
		getCenter().setBottomComponent(buildButtonsPanel());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.NONE);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void buildCenterPanelRaster() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(sldEditorPanel.buildRaster());
		getCenter().setBottomComponent(buildButtonsPanelRaster());
		getCenter().setSize(600, 300);
		getCenter().setScrollMode(Scroll.NONE);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(5);
		createSLDButton = new Button("Create SLD", SLDEditorController.createSLD(this, algorithm));
		buttonsPanel.add(createSLDButton);
		uploadSLDButton = new Button("Upload SLD", SLDEditorController.uploadSLD(this));
		buttonsPanel.add(uploadSLDButton);
		saveSLDButton = new Button("Save SLD", SLDEditorController.saveSLD(this));
		buttonsPanel.add(saveSLDButton);
		closeButton = new Button("Close Window", SLDEditorController.close(this));
		buttonsPanel.add(closeButton);
		return buttonsPanel;
	}

	private HorizontalPanel buildButtonsPanelRaster() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(5);
		createSLDButton = new Button("Create SLD", SLDEditorController.createSLDRaster(this));
		buttonsPanel.add(createSLDButton);
		uploadSLDButton = new Button("Upload SLD", SLDEditorController.uploadSLD(this));
		buttonsPanel.add(uploadSLDButton);
		saveSLDButton = new Button("Save SLD", SLDEditorController.saveSLD(this));
		buttonsPanel.add(saveSLDButton);
		closeButton = new Button("Close Window", SLDEditorController.close(this));
		buttonsPanel.add(closeButton);
		return buttonsPanel;
	}
	
	private void format(int size) {
		if(size == 0)
		{
			setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		}
		else if(size == 1)
		{
			setSize(WINDOW_WIDTH_RASTER, WINDOW_HEIGHT_RASTER);
		}
		getWindow().setHeading("SLD Editor");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("sld");
		getWindow().setCollapsible(false);
	}

	public SLDEditorPanel getSldEditorPanel() {
		return sldEditorPanel;
	}

	public static int getAlgorithm() {
		return algorithm;
	}

	public static void setAlgorithm(int algorithm) {
		SLDEditorWindow.algorithm = algorithm;
	}

	public List<SLDEditorRuleVO> getVoList() {
		return voList;
	}
	
}