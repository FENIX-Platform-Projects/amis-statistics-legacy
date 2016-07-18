package org.fao.fenix.web.modules.shared.window.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.toolbar.ImageToolbar;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Image;

public class ImageWindow extends FenixWindow {

	private String imagePath;
	
	private Long imageID;
	
	private ImageToolbar imageToolbar;
	
	public ImageWindow(Long imageID, String imagePath) {
		this.setImageID(imageID);
		this.setImagePath(imagePath);
		imageToolbar = new ImageToolbar();
	}
	
	public void buildPreview() {
		Image img = createPreview();
		format(img);
		buildCenterPanel(img);
		getWindow().setCollapsible(false);
		setCollapsible(false);
		show();
	}
	
	public void build() {
		Image img = createImage();
		buildCenterPanel(img);
		format(img);
		show();
	}
	
	private void buildCenterPanel(Image img) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
//		getCenter().setTopComponent(imageToolbar.build());
		getCenter().add(img);		
//		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
//		getCenterData().setSize(300);
		addCenterPartToWindow();
//		SocialBar sb = new SocialBar();
//		getCenter().setBottomComponent(sb.getSocialBar(ResourceType.IMAGE, String.valueOf(imageID)));
	}
	
	private Image createImage() {
		return new Image("../exportObject/" + this.getImagePath());
	}
	
	private Image createPreview() {
		return new Image(this.getImagePath());
	}
	
	private void format(Image img) {
//		System.out.println(String.valueOf(img.getWidth() + 10) + "px");
//		System.out.println(String.valueOf(img.getHeight() + 30) + "px");
		setSize(String.valueOf(img.getWidth() + 10) + "px", String.valueOf(img.getHeight() + 30) + "px");
//		System.out.println();
//		System.out.println(img.getWidth() + ", " + img.getHeight());
//		setSize("400px", "300px");
		getWindow().setHeading("Image");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("smallCamera");
		getWindow().setCollapsible(false);
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getImageID() {
		return imageID;
	}

	public void setImageID(Long imageID) {
		this.imageID = imageID;
	}
	
}
