package org.fao.fenix.web.modules.statistics.client.view;

import java.io.File;
import java.util.UUID;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Image;

public class StatisticsChartPanel {

	private VerticalPanel panel;

	private Image image;
	private String imagePath;

	private String WIDTH = "800px";
	private int SPACING = 2;
	private String FIELD_WIDTH = "120px";

	private String HEIGHT = "450px";

	public StatisticsChartPanel() {}

	public StatisticsChartPanel(String imagePath) {
		this.imagePath = imagePath;
	}

	public VerticalPanel build() {

		panel = new VerticalPanel();
		panel.setLayout(new FitLayout());
		panel.setSize(WIDTH, HEIGHT);

		try {
			panel.remove(image);
		} catch (Exception ex) {
		}

		image = new Image(imagePath);
		
		/*
		String filename = UUID.randomUUID().toString() + ".png";
		String filepath = dir + File.separator + filename;
		*/
		
		//FenixAlert.error(BabelFish.print().error(), "sss it mac:"+ image.getUrl());
	
		//image.setSize("100px", "100px");
		panel.add(image);

		// #!mactest:file:///E:/fenix/fenix-web/war/fenix/images/barChartBig.png!#
		// FenixAlert.error(BabelFish.print().error(),imagePath);
		// IconButton jj=new IconButton(imagePath);
		// panel.add(jj);

		return panel;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}
}