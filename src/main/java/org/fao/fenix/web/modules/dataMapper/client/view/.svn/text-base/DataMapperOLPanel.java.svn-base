package org.fao.fenix.web.modules.dataMapper.client.view;



import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.haiti.client.view.HaitiOLPanel;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.Counter;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;

public class DataMapperOLPanel {

	private ContentPanel olPanel;
	
	private MapPanel mapPanel;
	
	private final long mapWindowId;
	
	private ClientMapView mapView;
	
	private static Counter mwCounter = new Counter(100);
	
	private static final MapWindowManager mapWindowManager = new MapWindowManager();
	
	public static DataMapperOLPanel getMapWindow(long mapWindowId) {
		return mapWindowManager.getMapWindow(mapWindowId);
	}
	
	
	public DataMapperOLPanel() {
		mapWindowId = mwCounter.getNext();
		olPanel = new ContentPanel();
		olPanel.setHeaderVisible(false);
		mapView = new ClientMapView();
	}
	
	public ContentPanel build(DataMapperMapPanel panel, String language) {
		mapPanel = new MapPanel(mapWindowId, language);
		olPanel.setTopComponent(mapPanel.getToolbar());
		olPanel.add(mapPanel.getGUI());
		format();
		return olPanel;
	}
	
	public ContentPanel build(DataMapperOLPanel olpanel, String language) {
		mapPanel = new MapPanel(mapWindowId, language);
		olPanel.setTopComponent(mapPanel.getToolbar());
		olPanel.add(mapPanel.getGUI());
		format();
		return olPanel;
	}
	
	private void format() {
		olPanel.setBodyBorder(false);
		mapPanel.getGUI().setBodyBorder(false);
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


	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}
	
	/**
	 * Needed for retrieving a MapWindow through its code.
	 */
	protected static class MapWindowManager {
		private final Map<Long, DataMapperOLPanel> allMapWindows = new HashMap<Long, DataMapperOLPanel>();
		protected void addWindow(DataMapperOLPanel mw) {
			allMapWindows.put(mw.getMapWindowId(), mw);
//			mw.getWindow().addWindowListener(new WListener(mw.getMapWindowId()));
		}
		protected void removeWindow(MapWindow mw) {
			allMapWindows.remove(mw.getMapWindowId());
		}
		public DataMapperOLPanel getMapWindow(long mapWindowId) {
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