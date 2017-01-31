package org.fao.fenix.web.modules.amis.client.view.map;

import java.util.HashMap;
import java.util.Map;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;

public class AMISOLPanel {
	
	private final long mapWindowId;	
	private ContentPanel olPanel;
	//private AmisOLToolbar amisOLToolbar;
	private ClientMapView mapView;
	private MapPanel mapPanel;
	

	private static final MapWindowManager mapWindowManager = new MapWindowManager();
	public static AMISOLPanel getMapWindow(long mapWindowId) {
		return mapWindowManager.getMapWindow(mapWindowId);
	}
	
	
	public AMISOLPanel(long id) {
		mapWindowManager.addWindow(this);
//		mapWindowId = mwCounter.getNext();
		mapWindowId = id;
		olPanel = new ContentPanel();
		olPanel.setHeaderVisible(false);
		//amisOLToolbar = new AmisOLToolbar();
		mapView = new ClientMapView();
	}
	
	/*public ContentPanel build(AMISMapPanel amisMapPanel, String language) {
		
		mapPanel = new MapPanel(amisMapPanel, language);	
		olPanel.setHeaderVisible(false);
		olPanel.setTopComponent(mapPanel.getToolbar());
		olPanel.add(mapPanel.getGUI());
		mapPanel.getGUI().setHeaderVisible(false);
		return olPanel;
	}*/

	public long getMapWindowId() {
		return mapWindowId;
	}
	
	public ClientMapView getMapView() {
		return mapView;
	}
		
	public MapPanel getMapPanel() {
		return mapPanel;
	}

	
	//==========================================================================

	/**
	 * Needed for retrieving a MapWindow through its code.
	 */
	protected static class MapWindowManager {
		private final Map<Long, AMISOLPanel> allMapWindows = new HashMap<Long, AMISOLPanel>();
		protected void addWindow(AMISOLPanel mw) {
			allMapWindows.put(mw.getMapWindowId(), mw);
			//mw.getWindow().addWindowListener(new WListener(mw.getMapWindowId()));
		}
		protected void removeWindow(MapWindow mw) {
			allMapWindows.remove(mw.getMapWindowId());
		}
		public AMISOLPanel getMapWindow(long mapWindowId) {
			return allMapWindows.get(mapWindowId);
		}

		class WListener extends WindowListener {
			private final long mwid;

			public WListener(long id) {
				mwid = id;
			}

			public void windowClose(WindowEvent we) {
				allMapWindows.remove(mwid);
	//			we.getWindow().removeWindowListener(this);
			}
		}
	}
	
}
