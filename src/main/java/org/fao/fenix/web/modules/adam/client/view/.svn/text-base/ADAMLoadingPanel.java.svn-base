package org.fao.fenix.web.modules.adam.client.view;

import java.util.Map;

import org.apache.axis2.wsdl.codegen.extension.PackageFinder;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class ADAMLoadingPanel {


	public HorizontalPanel buildWaitingPanel() {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSize(ADAMConstants.SMALL_BOX_WIDTH + "px", ADAMConstants.SMALL_BOX_HEIGHT + "px");
		p.setSpacing(5);
//		p.setHorizontalAlign(HorizontalAlignment.CENTER);
//		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 12px; color: #17376D;'><b>Please wait, loading.</b></div>");
		q.setHeight("120px");
		
		left.add(q);
//		left.add(buildQuestionsToolbar());
		
		Image i = new Image("adam-images/loading.gif");
//		i.setSize("35px", "17px");
		
		right.add(i);
		
		p.add(left);
		p.add(right);
		
		return p;
	}
	
	
   public HorizontalPanel buildWaitingPanelWhite() {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSize(ADAMConstants.SMALL_BOX_WIDTH + "px", ADAMConstants.SMALL_BOX_HEIGHT + "px");
		p.setSpacing(5);
//		p.setHorizontalAlign(HorizontalAlignment.CENTER);
//		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 12px; color: #17376D;'><b>Please wait, loading.</b></div>");
		q.setHeight("120px");
		
		left.add(q);
//		left.add(buildQuestionsToolbar());
		
		Image i = new Image("adam-images/loading_white.gif");
//		i.setSize("35px", "17px");
		
		right.add(i);
		
		p.add(left);
		p.add(right);
		
		return p;
	}

	
	
}