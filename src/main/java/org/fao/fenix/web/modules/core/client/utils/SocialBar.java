package org.fao.fenix.web.modules.core.client.utils;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.x.common.services.XServiceEntry;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;

public class SocialBar {

	/**public HorizontalPanel getSocialBar(final ResourceType resourceType, final String resourceID) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		Html label = new Html("<div style='font-weight: bold; font-family: sans-serif; color: #1D4589; vertical-align:middle; font-size: 8pt;'>Share it on: </div>");
		p.add(label);
		
		final Anchor facebook = new Anchor("Facebook");
		facebook.setTarget("_blank");
		p.add(facebook);
		
		final Anchor twitter = new Anchor("Twitter");
		twitter.setHTML("<a><img src='images/twitter.png' height='20px' width='20px'></a>");
		twitter.setTarget("_blank");
		p.add(twitter);
		
		final Anchor digg = new Anchor("Digg");
		digg.setHTML("<a><img src='images/digg.png' height='20px' width='20px'></a>");
		digg.setTarget("_blank");
		p.add(digg);
		
		final Anchor mail = new Anchor("Mail");
		mail.setHTML("<a'><img src='images/sharemail.png' height='20px' width='20px'></a>");
		mail.setTarget("_blank");
		p.add(mail);
		
		final Html like = new Html();
		p.add(like);
		
		try {
			XServiceEntry.getInstance().getFenixURL(new AsyncCallback<String>() {
				public void onSuccess(String url) {
					switch (resourceType) {
						case TEXT:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openText=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openText=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openText=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openText=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openText=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openText=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
						case CORE_DATASET:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openProjectDataset=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openProjectDataset=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openProjectDataset=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openProjectDataset=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openProjectDataset=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openProjectDataset=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
						case CUSTOM_DATASET:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openProjectDataset=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openProjectDataset=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openProjectDataset=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openProjectDataset=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openProjectDataset=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openProjectDataset=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
						case OLAP:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openOLAP=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openOLAP=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openOLAP=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openOLAP=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openOLAP=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openOLAP=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
						case MAP:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openMap=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openMap=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openMap=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openMap=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openMap=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openMap=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
						case IMAGE:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openImage=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openImage=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openImage=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openImage=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openImage=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openImage=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
						case CHART:
							facebook.setHTML("<a name='fb_share' type='icon' share_url='" + url + "?openChart=" + resourceID + "'><img src='images/facebook.png' height='20px' width='20px'></a>");
							facebook.setHref("http://www.facebook.com/sharer.php?u=" + url + "?openChart=" + resourceID + "&src=sp");
							twitter.setHref("http://twitter.com/home?status=Available on the Workstation " + url + "?openChart=" + resourceID);
							digg.setHref("http://digg.com/submit?url=" + url + "?openChart=" + resourceID);
							mail.setHref("mailto:?subject=Available on FENIX Workstation&body=Hi, this is the link to the resource: " + url + "?openChart=" + resourceID);
							like.setHtml("<iframe src='http://www.facebook.com/plugins/like.php?href=" + url + "?openChart=" + resourceID + "&amp;layout=button_count&amp;show_faces=true&amp;width=160&amp;action=like&amp;colorscheme=light&amp;height=25' scrolling='no' frameborder='0' style='border:none; overflow:hidden; width:160px; height:25px;' allowTransparency='true'></iframe>");
						break;
					}
				}
				public void onFailure(Throwable arg0) {
					FenixAlert.error(BabelFish.print().error(), arg0.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
		
		return p;
	}**/
	
public HorizontalPanel getSocialBar(final ResourceType resourceType, final String resourceID) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		return p;
}	
	
}