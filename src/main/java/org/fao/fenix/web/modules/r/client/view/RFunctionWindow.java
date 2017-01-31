package org.fao.fenix.web.modules.r.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.control.RController;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class RFunctionWindow extends FenixWindow {

	private RFunctionPanel rFunctionPanel;
	
	private RFunctionVO rFunctionVO;
	
	private static final String WINDOW_WIDTH = "725px";
	
	private static final String WINDOW_HEIGHT = "550px";
	
	public RFunctionWindow() {
		rFunctionPanel = new RFunctionPanel();
	}
	
	public void build(TableWindow tw, RFunctionVO vo) {
		buildCenterPanel(tw, vo);
		TableVO tvo = (TableVO)tw.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
		rFunctionPanel.getCalculateButton().addSelectionListener(RController.calculate(this, tvo));
		rFunctionPanel.getCloseButton().addSelectionListener(RController.close(this));
		this.setrFunctionVO(vo);
		format(vo);
		show();
	}
	
	public void build(TableViewWindow tw, RFunctionVO vo) {
		buildCenterPanel(tw, vo);
		TableVO tvo = (TableVO)tw.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
		rFunctionPanel.getCalculateButton().addSelectionListener(RController.calculate(this, tvo));
		rFunctionPanel.getCloseButton().addSelectionListener(RController.close(this));
		this.setrFunctionVO(vo);
		format(vo);
		show();
	}
	
	private void buildCenterPanel(TableWindow tw, RFunctionVO vo) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(rFunctionPanel.build(tw, vo));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void buildCenterPanel(TableViewWindow tw, RFunctionVO vo) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(rFunctionPanel.build(tw, vo));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format(RFunctionVO vo) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading(BabelFish.print().getString(vo.getCommand()));
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("R");
		getWindow().setCollapsible(false);
	}

	public RFunctionPanel getrFunctionPanel() {
		return rFunctionPanel;
	}

	public RFunctionVO getrFunctionVO() {
		return rFunctionVO;
	}

	public void setrFunctionVO(RFunctionVO rFunctionVO) {
		this.rFunctionVO = rFunctionVO;
	}

}