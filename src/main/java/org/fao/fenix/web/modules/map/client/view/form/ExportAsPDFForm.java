/*

 */
package org.fao.fenix.web.modules.map.client.view.form;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.control.action.ExportMapAsPdf;
import org.fao.fenix.web.modules.map.client.control.vo.CGVModelData;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.dnd.ListViewDragSource;
import com.extjs.gxt.ui.client.dnd.ListViewDropTarget;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

/**
 *
 * @author etj
 */
public class ExportAsPDFForm {

	private final ClientMapView cmv;
	private Window window;

	ListStore<CGVModelData> storeDst = new ListStore<CGVModelData>();
	TextField<String> title = new TextField<String>();

	public ExportAsPDFForm(ClientMapView cmv) {
		this.cmv = cmv;
	}
	
	public void run() {
		window = new Window();
		window.setSize(500, 300);
		window.setPlain(true);
		window.setHeading("Export map to PDF - Setting options");
		window.setLayout(new FitLayout());
//		window.setCloseAction(Window.CloseAction.CLOSE);

		FormPanel panel = createForm();
		window.add(panel, new FitData(4));

		window.setModal(true);
		window.show();
	}

	private FormPanel createForm() {
		FormPanel form = new FormPanel();
		form.setFrame(false);
		form.setHeaderVisible(false);

		title.setFieldLabel("Title");
		title.setAllowBlank(false);
		title.setValue(cmv.getTitle());

		form.add(title);


		ListViewDNDExample list = new ListViewDNDExample();
		form.add(list);

		form.setButtonAlign(HorizontalAlignment.CENTER);

		Button exportButton = new Button("Export");
		exportButton.addSelectionListener(
			new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent e) {
					List<ClientGeoView> legendList = new ArrayList<ClientGeoView>();
					for (CGVModelData cmd : storeDst.getModels()) {
						legendList.add((ClientGeoView)cmd.get("cgv"));
					}

					cmv.setTitle(title.getValue());
					new ExportMapAsPdf(cmv, legendList).run();
					window.close();
				}
			});
		form.addButton(exportButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addSelectionListener(
			new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					Info.display("Action canceled", "Export map action canceled");
					window.close();
				}
			});

		form.addButton(cancelButton);

		return form;
//		vp.add(form2);
	}

	public class ListViewDNDExample extends LayoutContainer {

		@Override
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
//			setStyleAttribute("margin", "10px");
			ContentPanel cp = new ContentPanel();
			cp.setHeading("Layer legends");
			cp.setStyleAttribute("marginTop", "10px");
			cp.setSize(450, 160);
			cp.setFrame(false);
			cp.setLayout(new RowLayout(Orientation.HORIZONTAL));

			ListView<CGVModelData> listSrc = new ListView<CGVModelData>();
			listSrc.setDisplayProperty("title");
			ListStore<CGVModelData> storeSrc = new ListStore<CGVModelData>();
			for (ClientGeoView clientGeoView : cmv.getLayerList()) {
				CGVModelData cgvmd = new CGVModelData(clientGeoView);
				storeSrc.add(cgvmd);
			}						
			listSrc.setStore(storeSrc);

			ListView<CGVModelData> listDst = new ListView<CGVModelData>();
			listDst.setDisplayProperty("title");
//			ListStore<CGVModelData> storeDst = new ListStore<CGVModelData>();
			listDst.setStore(storeDst);

			RowData data = new RowData(.5, 1);
//			data.setMargins(new Margins(5));

			cp.add(listSrc, data);
			cp.add(listDst, data);

			ListViewDragSource source3 = new ListViewDragSource(listSrc);
			source3.setGroup("demo2");
			ListViewDragSource source4 = new ListViewDragSource(listDst);
			source4.setGroup("demo2");

			ListViewDropTarget target3 = new ListViewDropTarget(listSrc);
			target3.setGroup("demo2");
			target3.setFeedback(Feedback.INSERT);
			ListViewDropTarget target4 = new ListViewDropTarget(listDst);
			target4.setGroup("demo2");
			target4.setFeedback(Feedback.INSERT);
			add(cp);

		}
	}
}
