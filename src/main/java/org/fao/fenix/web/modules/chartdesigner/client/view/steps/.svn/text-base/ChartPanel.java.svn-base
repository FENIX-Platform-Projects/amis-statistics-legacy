package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import org.fao.fenix.web.modules.core.client.utils.SocialBar;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.Image;

public class ChartPanel extends ChartDesignerStepPanel {

	private SocialBar socialBar;
	
	private ContentPanel contentPanel;
	
	private VerticalPanel wrapper;
	
	private String chartID;
	
	private Image chartImage = new Image("images/CHART.png");
	
	private String imagePath = "";
	
	private final static int SPACING = 5;
	
	private final static String CHART_WIDTH = "640px";
	
	private final static String CHART_HEIGHT = "360px";
	
	private final static String WRAPPER_WIDTH = "640px";
	
	private final static String WRAPPER_HEIGHT = "360px";
	
	public ChartPanel(String suggestion, String width) {
		super(suggestion, width);
		contentPanel = new ContentPanel(new FillLayout());
		contentPanel.setHeaderVisible(false);
		contentPanel.setBodyBorder(false);
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		wrapper.setSize(WRAPPER_WIDTH, WRAPPER_HEIGHT);
		wrapper.setScrollMode(Scroll.AUTO);
		socialBar = new SocialBar();
		chartID = "";
		this.getLayoutContainer().add(buildContentPanel());
	}
	
	private ContentPanel buildContentPanel() {
		contentPanel.setBottomComponent(socialBar.getSocialBar(ResourceType.CHART, chartID));
		contentPanel.add(buildWrapperPanel());
		return contentPanel;
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.add(chartImage);
		return wrapper;
	}
	
	public void setImage(String filename, String width, String height) {
		wrapper.remove(chartImage);
		this.setImagePath(filename);
		chartImage = new Image("../exportObject/" + filename);
		chartImage.setSize(width, height);
		wrapper.add(chartImage);
//		wrapper.setSize(String.valueOf(chartImage.getWidth()-25) + "px", String.valueOf(chartImage.getHeight()) + "px");
		wrapper.getLayout().layout();
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public Image getChartImage() {
		return chartImage;
	}

	public void setChartImage(Image chartImage) {
		this.chartImage = chartImage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
