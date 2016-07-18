package org.fao.fenix.web.modules.coding.client.view.creator;


import java.util.List;

import org.fao.fenix.web.modules.coding.client.control.creator.DcmtCodingCreatorController;
import org.fao.fenix.web.modules.coding.client.view.vo.DcmtCodingCreatorMD;
import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.DataListSelectionModel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;


public class DcmtCodingCreatorResult {
	
	private VerticalPanel vPanel;
	private HorizontalPanel hPanel;
	private HorizontalPanel hPanel1;
	private HorizontalPanel hPanel2;
	private HorizontalPanel hPanel3;
	private DataList elements;
	private DataList attributes;
	private DataList excluding;
	private Button add;
	private Button remove;
	private Button addCode;
	private Button elementsTree;
	private Button attributesTree;
	private Tree tree;  
	private ContentPanel cpE;
	private ContentPanel cpA;
	private ContentPanel cpTree;
	private VerticalPanel vpE;
	private HTML label;
	private HTML dcmtCode;
	private String fromCode;
	private String index;
	private DcmtCodingCreatorGridMD md;
	private TextBox textBox;
	private DcmtCodingCreatorElementsTree eTree;
	private String codingSystem;
	private String langCode;
		
	
	public HorizontalPanel build(DcmtCodingCreatorWindow window) {		
		hPanel = new HorizontalPanel();
		hPanel1 = new HorizontalPanel();
		hPanel1.setSpacing(10);
		hPanel2 = new HorizontalPanel();
		hPanel2.setSpacing(10);
		hPanel3 = new HorizontalPanel();
		hPanel3.setSpacing(10);
		hPanel1.add(buildFirstPart());
		hPanel2.add(buildSecondPart());
		hPanel3.add(buildThirdPart());
		hPanel1.setSize(350, 450);
		hPanel2.setSize(50, 480);
		hPanel3.setSize(350, 480);
		hPanel.add(hPanel1);
		hPanel.add(hPanel2);
		hPanel.add(hPanel3);
	    enhance(window);
		return hPanel;

	}
	
	
	private void enhance(DcmtCodingCreatorWindow window) {
		add.addSelectionListener(DcmtCodingCreatorController.addItem(this, window));
		remove.addSelectionListener(DcmtCodingCreatorController.removeItem(this, window));	
		addCode.addSelectionListener(DcmtCodingCreatorController.addCode(this, window));
		elementsTree.addSelectionListener(DcmtCodingCreatorController.domainElements(this));
		attributesTree.addSelectionListener(DcmtCodingCreatorController.domainAttributes(this));
	}
	
	private VerticalPanel buildFirstPart() {
		vPanel = new VerticalPanel();
		vPanel.add(buildLabelPanel());
		vPanel.add(buildElementsPanel());
		vPanel.add(buildFirstSpace());
		vPanel.add(buildAttributesPanel());
		vPanel.add(buildOperatorPanel());
		vPanel.add(buildTreeButtons());
		return vPanel;
	}

	
	private VerticalPanel buildSecondPart() {
		vPanel = new VerticalPanel();
		vPanel.add(buildSpace());
		vPanel.add(buildAddButton());
		vPanel.add(buildRemoveButton());
		return vPanel;
	}
	
	private VerticalPanel buildThirdPart() {
		vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new HTML("<b>" + BabelFish.print().resultedTree() +": </b>"));
		vPanel.add(buildTreeResults());
		vPanel.add(new HTML("<b>"+  BabelFish.print().resultedDcmtCode()  +": </b>"));
		vPanel.add(buildCodeResult());
		vPanel.add(buildAddingButtons());
		return vPanel;
	}
	
	
	
	private HorizontalPanel buildLabelPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		label = new HTML("<b>" + BabelFish.print().label()  + ":  </b>");
		label.setWidth("250");
		panel.add(label);
		return panel;
	}
		
	

	/***
	 * First part of the HorizontalPanel
	 * 
	 * @return
	 */
	
	 private ContentPanel buildElementsPanel() {  
	 	cpE= new ContentPanel();
//	 	cpE.setStyleName("my-datalist");
	    cpE.setFrame(true);  
	    cpE.setCollapsible(true);  
	    cpE.setAnimCollapse(true);  
	    cpE.setHeading(BabelFish.print().elements());  
	    cpE.setLayout(new FitLayout());  
//	    cpE.setStyleName("my-datalist");
//	    cpE.setStylePrimaryName("my-datalist");
	 
	 	cpE.setSize(340, 150);
	 	cpE.setScrollMode(Scroll.AUTO);
	    return cpE;
	}  
	 
	 private HorizontalPanel buildFirstSpace(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHeight(10);
		return panel;
	 }
	 
	 private ContentPanel buildAttributesPanel() {	
		cpA = new ContentPanel();
//		cpA.setStyleName("my-datalist");
		cpA.setFrame(true);  
		cpA.setCollapsible(true);  
		cpA.setAnimCollapse(true);  
		cpA.setHeading(BabelFish.print().attributes());  
		cpA.setLayout(new FitLayout());    
		cpA.setSize(340, 150);
		cpA.setScrollMode(Scroll.AUTO);
		return cpA;
	}
	 
	 public VerticalPanel buildDatalistElements(List<DcmtCodingCreatorMD> code){	 
		 VerticalPanel vPanel = new VerticalPanel();
		 elements = new DataList();
		 elements.setStyleName("my-bg");
		 DataListItem elementsItem = new DataListItem();
		 Listener<ComponentEvent> l = new Listener<ComponentEvent>() {  
			        public void handleEvent(ComponentEvent ce) {  
			            DataList l = (DataList) ce.getComponent();
				          if (l.getSelectedItem().isChecked() == true) {
				        	  l.getSelectedItem().setChecked(false);
				          }
				          else {
				        	  l.getSelectedItem().setChecked(true);
				          }
				        }  
				      };  
		 elements.setSelectionMode(SelectionMode.MULTI);  
		 final DataListSelectionModel sm = elements.getSelectionModel();
		 sm.setSelectOnCheck(true);
		 elements.setSelectionModel(sm);  
		 elements.setBorders(false);  
		 elements.setCheckable(true);
		 elements.disableTextSelection(true);
		 elements.addListener(Events.OnClick, l);  
		 for (int i=0; i< code.size(); i++ ) {
			 elementsItem = new DataListItem();
			 elementsItem.setData(code.get(i).getCode(), code.get(i));
			 elementsItem.setText("[" + code.get(i).getCode() + "] " + code.get(i).getLabel());
			 elementsItem.disableTextSelection(true);
			 elementsItem.disableEvents(true);
			 elements.add(elementsItem);
			 elements.setAutoWidth(true);
			 vPanel.add(elements);
		 }
		return vPanel;
	 }
	 
	 public VerticalPanel buildDatalistAttributes(List<DcmtCodingCreatorMD> code){
			 VerticalPanel vPanel = new VerticalPanel();
//			 vPanel.setStyleName("my-datalist");
			 attributes = new DataList();
			 attributes.setStyleName("my-bg");
			 DataListItem attributesItem= new DataListItem();
			 Listener<ComponentEvent> l = new Listener<ComponentEvent>() {  
				        public void handleEvent(ComponentEvent ce) {  
				          DataList l = (DataList) ce.getComponent();
				          if (l.getSelectedItem().isChecked() == true) {
				        	  l.getSelectedItem().setChecked(false);
				          }
				          else {
				        	  l.getSelectedItem().setChecked(true);
				          }
				        }  
				      };  
			 attributes.setSelectionMode(SelectionMode.MULTI);  
			 final DataListSelectionModel sm = attributes.getSelectionModel();  
			 attributes.setSelectionModel(sm);  
			 attributes.setBorders(false);  
			 attributes.setCheckable(true);
			 attributes.addListener(Events.OnClick, l);  
	
			 for (int i=0; i< code.size(); i++ ) {
				 attributesItem = new DataListItem();
				 attributesItem.setData(code.get(i).getCode(), code.get(i));
				 attributesItem.setText("[" + code.get(i).getCode() + "] " + code.get(i).getLabel());
				 attributesItem.disableTextSelection(true);
				 attributes.add(attributesItem);
				 attributes.setAutoWidth(true);
				 vPanel.add(attributes);
			 }
			return vPanel;
		 }
		   
		
	 
	private HorizontalPanel buildOperatorPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		excluding = new DataList();
		panel.setSpacing(10);	
		 Listener<ComponentEvent> l = new Listener<ComponentEvent>() {  
		        public void handleEvent(ComponentEvent ce) {  
		            DataList l = (DataList) ce.getComponent();
			          if (l.getSelectedItem().isChecked() == true) {
			        	  l.getSelectedItem().setChecked(false);
			          }
			          else {
			        	  l.getSelectedItem().setChecked(true);
			          }
			        }  
			      };  
		
		excluding.setSelectionMode(SelectionMode.MULTI);
		final DataListSelectionModel sm = excluding.getSelectionModel();
		sm.setSelectOnCheck(true);
		excluding.setSelectionModel(sm);
		excluding.add("excluding");
		excluding.setCheckable(true);
		excluding.addListener(Events.OnClick, l);
		excluding.setToolTip(BabelFish.print().excludingToolTip());
		panel.add(excluding);
		panel.setWidth("250px");
		return panel;
	}
	
	private HorizontalPanel buildTreeButtons() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		elementsTree = new Button(BabelFish.print().search() + " " + BabelFish.print().element());
		attributesTree = new Button(BabelFish.print().search() + " " + BabelFish.print().attribute());
		elementsTree.setSize("50px", "20px");
		attributesTree.setSize("50px", "20px");
		panel.add(elementsTree);
		panel.add(attributesTree);
		return panel;
	}
	
	/***
	 * Second part of the HorizontalPanel
	 * 
	 * @return
	 */
	private HorizontalPanel buildSpace() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHeight(75);
		return panel;
	}
	
	

	
	private HorizontalPanel buildAddButton() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		add = new Button("->");
		panel.add(add);
		return panel;
	}
	
	private HorizontalPanel buildRemoveButton() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		remove = new Button("<-");
		panel.add(remove);
		return panel;
	}
	
	private ContentPanel buildTreeResults() {
		cpTree = new ContentPanel();
		tree = new Tree();
		tree.setSelectionMode(SelectionMode.SINGLE);
		cpTree.add(tree);
		cpTree.setBorders(true);
		cpTree.setLayout(new FitLayout());
		cpTree.setHeaderVisible(false);
		cpTree.setScrollMode(Scroll.AUTO);
		cpTree.setSize(340, 320);
		return cpTree;
	}
	
	
	
	public Tree setTree(String element, List<String> attributes, Boolean excluding){
		System.out.println(excluding);
		try {
			TreeItem rootElement = tree.getItemById(element);
			if ( rootElement == null ){
				System.out.println("e is null");
				rootElement = new TreeItem();
				rootElement.setId(element);
				if ( excluding == true &&  attributes.isEmpty()) { 
					rootElement.setText(element + " (" + BabelFish.print().excluding()  + ")");
				}
				else {
					rootElement.setText(element);
				}
			
				tree.getRootItem().add(rootElement);
				/*** if there are attributes selected **/
				for(int j=0; j< attributes.size(); j++) {
					TreeItem attribute = new TreeItem();
					attribute.setId(attributes.get(j) + " " + element);
					if ( excluding == false ) {
						attribute.setText(attributes.get(j));
					}
					else {
						attribute.setText(attributes.get(j) + " (" + BabelFish.print().excluding()  + ")");	
					}
					rootElement.add(attribute);
				}
				/** if there are not attributes selected ***/
				
				
			}
			
			/*** if the element already exists ***/
			else {
				
				if ( excluding == false ) { 
					rootElement.setText(element);	
				}
				/*** if the element selected have excluding and no attributes, then remove it all and put it on excluding **/
				if ( excluding == true &&  attributes.isEmpty()){
					rootElement.removeAll();
					rootElement.setText(element + " (" + BabelFish.print().excluding()  + ")");
				}
				/*** the element exist so add the attributes with excluding policy **/
				else {
					for(int j=0; j< attributes.size(); j++) {
						TreeItem attribute = tree.getItemById(attributes.get(j) + " " + element);
						/*** the attribute exist then delete it and add the new one ***/
						if ( attribute != null ) {
							/** delete the attribute **/
							rootElement.remove(attribute);
						}
						attribute = new TreeItem();
						attribute.setId(attributes.get(j)+ " " + element);
						if ( excluding == false ) {
								attribute.setText(attributes.get(j));
						}
						else {
								attribute.setText(attributes.get(j) + " (" + BabelFish.print().excluding()  + ")");	
						}
						rootElement.add(attribute);
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("EXCEPTION");
		}
		
		return tree;
	}
	
	
	
	public TreeItem setTreeItem(String label){
		TreeItem item = new TreeItem(label);
		return item;
	}
	
	private HorizontalPanel buildCodeResult() {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(5);
//			panel.setStyleName("my-datalist");
			dcmtCode = new HTML("");
//			dcmtCode.setStyleName("my-datalist");
			panel.add(dcmtCode);
			panel.setSize(340, 40);
			panel.setScrollMode(Scroll.AUTO);
			panel.setBorders(true);
			return panel;
	}
	
	private HorizontalPanel buildAddingButtons() {
		HorizontalPanel panel = new HorizontalPanel();
		addCode = new Button(BabelFish.print().add() + " " + BabelFish.print().code());
		addCode.setToolTip(BabelFish.print().add() + " " + BabelFish.print().code() + " " + BabelFish.print().to() + " " + BabelFish.print().mapping());
		panel.add(addCode);
		return panel;
	}
	

	public VerticalPanel getVPanel() {
		return vPanel;
	}

	public HorizontalPanel getHPanel() {
		return hPanel;
	}

	public DataList getElements() {
		return elements;
	}

	public DataList getAttributes() {
		return attributes;
	}

	public DataList getExcluding() {
		return excluding;
	}

	public Button getAdd() {
		return add;
	}

	public Button getRemove() {
		return remove;
	}

	public Tree getTree() {
		return tree;
	}

	public ContentPanel getCpE() {
		return cpE;
	}

	public void setCpE(ContentPanel cpE) {
		this.cpE = cpE;
	}

	public ContentPanel getCpA() {
		return cpA;
	}

	public void setCpA(ContentPanel cpA) {
		this.cpA = cpA;
	}

	public VerticalPanel getVpE() {
		return vpE;
	}

	public void setVpE(VerticalPanel vpE) {
		this.vpE = vpE;
	}

	public HTML getLabel() {
		return label;
	}

	public void setLabel(HTML label) {
		this.label = label;
	}


	public ContentPanel getCpTree() {
		return cpTree;
	}


	public void setCpTree(ContentPanel cpTree) {
		this.cpTree = cpTree;
	}


	public HTML getDcmtCode() {
		return dcmtCode;
	}


	public void setDcmtCode(HTML dcmtCode) {
		this.dcmtCode = dcmtCode;
	}


	public TextBox getTextBox() {
		return textBox;
	}


	public void setTextBox(TextBox textBox) {
		this.textBox = textBox;
	}


	public String getFromCode() {
		return fromCode;
	}


	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}


	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public DcmtCodingCreatorGridMD getMd() {
		return md;
	}


	public void setMd(DcmtCodingCreatorGridMD md) {
		this.md = md;
	}


	public String getCodingSystem() {
		return codingSystem;
	}


	public void setCodingSystem(String codingSystem) {
		this.codingSystem = codingSystem;
	}


	public String getLangCode() {
		return langCode;
	}


	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	

}