package org.fao.fenix.web.modules.haiti.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAMapPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.Counter;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;

public class HaitiOLPanel {

	private ContentPanel olPanel;
	
	private HaitiOLToolbar haitiOLToolbar;
	
	private MapPanel mapPanel;
	
	private final long mapWindowId;
	
	private ClientMapView mapView;
	
	private static Counter mwCounter = new Counter(100);
	
	private static final MapWindowManager mapWindowManager = new MapWindowManager();
	public static HaitiOLPanel getMapWindow(long mapWindowId) {
		return mapWindowManager.getMapWindow(mapWindowId);
	}
	
	public HaitiOLPanel(long id) {
		mapWindowManager.addWindow(this);
//		mapWindowId = mwCounter.getNext();
		mapWindowId = id;
		olPanel = new ContentPanel();
		olPanel.setHeaderVisible(false);
		haitiOLToolbar = new HaitiOLToolbar();
		mapView = new ClientMapView();
	}
	
	
	
	public ContentPanel build(HaitiMapTabItem haitiMapTabItem, String language) {
		mapPanel = new MapPanel(haitiMapTabItem, language);
		olPanel.setTopComponent(mapPanel.getToolbar());
		olPanel.add(mapPanel.getGUI());
		return olPanel;
	}
	
	public ContentPanel build(HaitiCNSAPanel haitiCNSAPanel, HaitiCNSAMapPanel haitiCNSAMapPanel, String language) {
		
		mapPanel = new MapPanel(haitiCNSAPanel, haitiCNSAMapPanel, language);	
		olPanel.setHeaderVisible(false);
		olPanel.setTopComponent(mapPanel.getToolbar());
		olPanel.add(mapPanel.getGUI());
		mapPanel.getGUI().setHeaderVisible(false);
		return olPanel;
	}

	public long getMapWindowId() {
		return mapWindowId;
	}

	public ClientMapView getMapView() {
		return mapView;
	}

	public ContentPanel getOlPanel() {
		return olPanel;
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public HaitiOLToolbar getHaitiOLToolbar() {
		return haitiOLToolbar;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}
	
	/**
	 * Needed for retrieving a MapWindow through its code.
	 */
	protected static class MapWindowManager {
		private final Map<Long, HaitiOLPanel> allMapWindows = new HashMap<Long, HaitiOLPanel>();
		protected void addWindow(HaitiOLPanel mw) {
			allMapWindows.put(mw.getMapWindowId(), mw);
//			mw.getWindow().addWindowListener(new WListener(mw.getMapWindowId()));
		}
		protected void removeWindow(MapWindow mw) {
			allMapWindows.remove(mw.getMapWindowId());
		}
		public HaitiOLPanel getMapWindow(long mapWindowId) {
			return allMapWindows.get(mapWindowId);
		}

		class WListener extends WindowListener {
			private final long mwid;

			public WListener(long id) {
				mwid = id;
			}

			public void windowClose(WindowEvent we) {
				allMapWindows.remove(mwid);
//				we.getWindow().removeWindowListener(this);
			}
		}
	}

	
	
}