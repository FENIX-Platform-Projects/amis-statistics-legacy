/*
 */

package org.fao.fenix.web.modules.map.client.view.form;

import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.vo.PDDModel;
import org.fao.fenix.web.modules.map.common.vo.Position;
import org.fao.fenix.web.modules.map.common.vo.StyleDefinition;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author ETj
 */
public class StringSelectorForm {

	private Window window;

	private List<String> itemNames;
	private AsyncCallback<StyleDefinition> sdReceptor;

	public StringSelectorForm(List<String> codeVoList, AsyncCallback<StyleDefinition> stringReceptor) {
		this.itemNames = codeVoList;
		this.sdReceptor = stringReceptor;
	}


	ComboBox<PDDModel> combo;
	
	ComboBox<ListItem> fontSize;
	
	ComboBox<ListItem> position;
	
	ComboBox<ListItem> color;
	
	ComboBox<ListItem> fontName;

	
	
	

	public void run() {
		window = new Window();
		window.setSize(400, 200);
		window.setPlain(true);
		window.setHeading("Adding label to layer");
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
		

		combo = new ComboBox<PDDModel>();
		combo.setTriggerAction(TriggerAction.ALL);
		form.add(combo);
		form.add(buildFontSizeComboBox());
		form.add(buildPositionComboBox());
		form.add(buildColorComboBox());
		
	

		ListStore<PDDModel> store = new ListStore<PDDModel>();
		for (String name : itemNames) {
			store.add(new PDDModel(name, name));
		}

		combo.setStore(store);
		combo.setAllowBlank(false);
		combo.setForceSelection(true);
		combo.setDisplayField("label");
		combo.setAutoHeight(true);
		combo.setAutoWidth(true);
		combo.setEditable(false);
		combo.setEmptyText("Please select an item");
		combo.setFieldLabel("Field");

		form.setButtonAlign(HorizontalAlignment.CENTER);
		

		Button okButton = new Button("Create label layer");
		okButton.addSelectionListener(
			new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent e) {		
					StyleDefinition styleDefinition = new StyleDefinition();				
					String id = combo.getSelection().get(0).getName();
					for (String name : itemNames) {
						if(name.equals(id)) {
							window.close();
							styleDefinition.setFieldName(name);
						}
					}
					styleDefinition.setFontSize(Integer.valueOf(fontSize.getValue().getValue()));	
					styleDefinition.setPosition(Position.valueOfIgnoreCase(position.getValue().getValue()));
					styleDefinition.setColor(color.getValue().getValue());	
					styleDefinition.setFontName("Arial");	
					sdReceptor.onSuccess(styleDefinition);
				}
			});
		form.addButton(okButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addSelectionListener(
			new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					Info.display("Action canceled", "Field name selection canceled");
					window.close();
				}
			});

		form.addButton(cancelButton);

		return form;
	}

	
	
	private ComboBox<ListItem> buildFontSizeComboBox() {
		fontSize = new ComboBox<ListItem>();
		fontSize.setTriggerAction(TriggerAction.ALL);
		ListStore<ListItem> fontsList = new ListStore<ListItem>();
		ListItem def = new ListItem("10pt", "10");
		fontsList.add(new ListItem("6pt", "6"));
		fontsList.add(new ListItem("8pt", "8"));
		fontsList.add(new ListItem("9pt", "9"));
		fontsList.add(def);
		fontsList.add(new ListItem("11pt", "11"));
		fontsList.add(new ListItem("12pt", "12"));
		fontsList.add(new ListItem("13pt", "13"));
		fontsList.add(new ListItem("14pt", "14"));
//		fontsList.add(new ListItem("20pt", "20"));
		fontSize.setValue(def);
		fontSize.setStore(fontsList);
		fontSize.setFieldLabel(BabelFish.print().fontSize());
		fontSize.setAllowBlank(true);
		fontSize.setEditable(false);
		fontSize.setDisplayField("name");
		return fontSize;
	}
	
	private ComboBox<ListItem> buildPositionComboBox() {
		position = new ComboBox<ListItem>();
		position.setTriggerAction(TriggerAction.ALL);
		ListStore<ListItem> positionList = new ListStore<ListItem>();
		ListItem def = new ListItem("Center", Position.Center.toString());
		positionList.add(new ListItem("Top left", Position.TopLeft.toString()));
		positionList.add(new ListItem("Top" , Position.Top.toString()));
		positionList.add(new ListItem("Top Right", Position.TopRight.toString()));
		positionList.add(new ListItem("Left", Position.Left.toString()));
		positionList.add(def);
//		positionList.add(new ListItem("Center", Position.Center.toString()));
		positionList.add(new ListItem("Right", Position.Right.toString()));
		positionList.add(new ListItem("Bottom Left", Position.BottomLeft.toString()));
		positionList.add(new ListItem("Bottom", Position.Bottom.toString()));
		positionList.add(new ListItem("Bottom Right", Position.BottomRight.toString()));
		position.setValue(def);
		position.setStore(positionList);
		position.setFieldLabel(BabelFish.print().position());
		position.setAllowBlank(true);
		position.setEditable(false);
		position.setDisplayField("name");
		return position;
	}
	
	private ComboBox<ListItem> buildColorComboBox() {
		color = new ComboBox<ListItem>();
		color.setTriggerAction(TriggerAction.ALL);
		ListStore<ListItem> colorList = new ListStore<ListItem>();
		ListItem def = new ListItem("Light Green", "0cffaa");
		colorList.add(def);
		colorList.add(new ListItem("White", "ffffff"));
		colorList.add(new ListItem("Green", "1dab20"));
		colorList.add(new ListItem("Black", "000000"));	
		color.setStore(colorList);
		color.setValue(def);
		color.setFieldLabel(BabelFish.print().color());
		color.setAllowBlank(true);
		color.setEditable(false);
		color.setDisplayField("name");
		return color;
	}
	
	private ComboBox<ListItem> buildFontNameComboBox() {
		fontName = new ComboBox<ListItem>();
		fontName.setTriggerAction(TriggerAction.ALL);
		ListStore<ListItem> fontNameList = new ListStore<ListItem>();
		fontName.setStore(fontNameList);
		fontName.setFieldLabel("");
		fontName.setAllowBlank(true);
		fontName.setEditable(false);
		fontName.setDisplayField("name");
		return fontName;
	}
	
}


