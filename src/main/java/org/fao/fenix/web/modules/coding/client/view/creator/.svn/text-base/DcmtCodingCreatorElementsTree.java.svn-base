package org.fao.fenix.web.modules.coding.client.view.creator;

import org.fao.fenix.web.modules.coding.client.control.creator.DcmtCodingCreatorController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;



public class DcmtCodingCreatorElementsTree extends FenixWindow {

	private Tree tree;  
	private ContentPanel cpTree;
	private Button addElement;
	private Button createNewElement;
	private Button close;
	private DcmtCodingCreatorElementsTree eWindow;	
	private String langCode;
	
	public void build(DcmtCodingCreatorElementsTree eWindow, DcmtCodingCreatorResult window) {
		setLangCode(window.getLangCode());
		eWindow.setEWindow(eWindow);
		setCenterProperties();
		getCenter().add(buildPanel());
		addCenterPartToWindow();
		format();
		enhance(window);
	}
	
	
	private void enhance(DcmtCodingCreatorResult window) {
		close.addSelectionListener(DcmtCodingCreatorController.closeWindow(eWindow));
		addElement.addSelectionListener(DcmtCodingCreatorController.getElement(window, eWindow));
		createNewElement.addSelectionListener(DcmtCodingCreatorController.createNewItem("element"));
	}
	
	
	private VerticalPanel buildPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(buildTree());
		vPanel.add(buildButtons());
		return vPanel;
	}

	private ContentPanel buildTree() {
		cpTree = new ContentPanel();
		tree = new Tree();
		tree.setSelectionMode(SelectionMode.SINGLE);
		 Listener<ComponentEvent> l = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) {  
		        	Tree t = (Tree) ce.getComponent();
		          if (t.getSelectedItem().isLeaf() == true) {
		        	  DcmtCodingCreatorController.childrenElements(getEWindow(), t.getSelectedItem().getItemId().toString(), getLangCode());
		          }
		        }  
		    };  
	 	tree.addListener(Events.OnClick, l);  
		cpTree.add(tree);
		cpTree.setBorders(true);
		cpTree.setLayout(new FitLayout());
		cpTree.setHeaderVisible(false);
		cpTree.setScrollMode(Scroll.AUTO);
		cpTree.setSize(630, 400);
		cpTree.setBorders(true);
		return cpTree;
	}
	
	public HorizontalPanel buildButtons(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		addElement = new Button(BabelFish.print().add() + " " + BabelFish.print().element());
		createNewElement = new Button(BabelFish.print().createNewElement());
		close = new Button(BabelFish.print().close());
		addElement.setSize("50px", "20px");
		createNewElement.setSize("50px", "20px");
		close.setSize("50px", "20px");
		panel.add(addElement);
		panel.add(createNewElement);
		panel.add(close);
		panel.setSize(640, 30);
		return panel;
	}
	
	
	
	public Tree setRoots(String code, String label){
		TreeItem rootElement = new TreeItem();
		rootElement.setId(code);
		rootElement.setText("[" + code + "] " + label);
		tree.getRootItem().add(rootElement);
		return tree;
	}
	
	public Tree setTree(String father, String code, String label){
		TreeItem item = new TreeItem();
		item.setId(code);
		item.setText("[" + code + "] " + label);	
		TreeItem fatherElement = tree.getItemById(father);
		fatherElement.add(item);
		return tree;
	}
	
	
	
	public TreeItem setTreeItem(String label){
		TreeItem item = new TreeItem(label);
		return item;
	}

	
	

	
	protected void format() {
		getWindow().setModal(true);
		setSize(650, 500);
		window.setHeading("<b> " + BabelFish.print().elements() + " </b>");
		getCenter().setHeaderVisible(false);
	}


	public Tree getTree() {
		return tree;
	}


	public void setTree(Tree tree) {
		this.tree = tree;
	}


	public ContentPanel getCpTree() {
		return cpTree;
	}


	public void setCpTree(ContentPanel cpTree) {
		this.cpTree = cpTree;
	}


	public Button getAddElement() {
		return addElement;
	}


	public void setAddElement(Button addElement) {
		this.addElement = addElement;
	}


	public Button getClose() {
		return close;
	}


	public void setClose(Button close) {
		this.close = close;
	}


	public DcmtCodingCreatorElementsTree getEWindow() {
		return eWindow;
	}


	public void setEWindow(DcmtCodingCreatorElementsTree window) {
		eWindow = window;
	}


	public String getLangCode() {
		return langCode;
	}


	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	

}