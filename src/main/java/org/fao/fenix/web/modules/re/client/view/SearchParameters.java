package org.fao.fenix.web.modules.re.client.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.CategoryList;
import org.fao.fenix.web.modules.core.common.vo.GAULCountryList;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.re.client.control.SearchParametersController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;


public abstract class SearchParameters {

	final int SPACING=10;
	final String WIDTH="60px";
	HorizontalPanel nodemainCont;
	protected VerticalPanel mainCont;
	TextBox keyword;
	TextBox titleResource;
	ListBox orderBy;
	RadioButton ascOrder;
	RadioButton descOrder;
	ContentPanel region;
	HTML regionText;
	ContentPanel category;
	HTML categoryText;
	List<String> regionList;
	List<String> categoryList;
	GAULCountryList countries = new GAULCountryList(false);
	
	ResourceExplorer resourceExplorer;
	
	public VerticalPanel getMainCont() {
		return mainCont;
	}

	public void clean(){
		keyword.setText("");
		titleResource.setText("");
		regionList=null;
		categoryList=null;
		category.setToolTip(new ToolTipConfig(BabelFish.print().category(), BabelFish.print().noneSelected()));
		region.setToolTip(new ToolTipConfig(BabelFish.print().region(), BabelFish.print().noneSelected()));
		regionText.setHTML(BabelFish.print().noneSelected());
		categoryText.setHTML(BabelFish.print().noneSelected());
	}
	
	
	public RadioButton getAscOrder() {
		return ascOrder;
	}

	public RadioButton getDescOrder() {
		return descOrder;
	}

	public ListBox getOrderBy() {
		return orderBy;
	}

	public HorizontalPanel getNodemainCont() {
		return nodemainCont;
	}

	public void setRegionText(HTML regionText) {
		this.regionText = regionText;
	}

	public ContentPanel getRegion() {
		return region;
	}

	public ContentPanel getCategory() {
		return category;
	}

	public void setCategoryText(HTML categoryText) {
		this.categoryText = categoryText;
	}

	public void setRegionList(List<String> regionList) {
		this.regionList = regionList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public String getKeyword() {
		return keyword.getText();
	}

	public String getTitleResource() {
		return titleResource.getText();
	}
	
	
	
	public List<String> getRegionList() {
		return regionList;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public SearchParameters(final ResourceExplorer resourceExplorer){
		
		this.resourceExplorer=resourceExplorer;
		mainCont=new VerticalPanel();
		mainCont.setScrollMode(Scroll.AUTO);
		keyword=new TextBox();
		
		keyword.addKeyboardListener(SearchParametersController.onEnter(resourceExplorer));
		
		titleResource=new TextBox();
		
		titleResource.addKeyboardListener(SearchParametersController.onEnter(resourceExplorer));
		
		region=new ContentPanel();
		region.setHeaderVisible(false);
		region.setBodyBorder(false);
		region.setWidth(90);
		regionText=new HTML(BabelFish.print().noneSelected());
		region.add(regionText);
		
		category=new ContentPanel();
		category.setHeaderVisible(false);
		category.setBodyBorder(false);
		category.setWidth(90);
		categoryText=new HTML(BabelFish.print().noneSelected());
		category.add(categoryText);
		
		buildInterface();
	}
	
	private void buildCategoryList(){
		final Window win=new Window();
		win.setHeading(BabelFish.print().category());
		win.setResizable(false);
		win.setSize(380, 400);
		win.setLayout(new FitLayout());
		com.google.gwt.user.client.ui.VerticalPanel cont= new com.google.gwt.user.client.ui.VerticalPanel();
		cont.setSpacing(5);
		final ListBox l = new ListBox(); 
		CategoryList c=new CategoryList(l);

		c.getCategoriesListBox(l);
		l.setSize("360px", "330px");
		l.setMultipleSelect(true);
		l.removeItem(0);
		cont.add(l);
		
		com.google.gwt.user.client.ui.HorizontalPanel contButton = new com.google.gwt.user.client.ui.HorizontalPanel();
		Button ok = new Button(BabelFish.print().ok());
		Button reset = new Button(BabelFish.print().reset());
		contButton.add(ok);
		contButton.add(reset);
		contButton.setSpacing(5);
		cont.add(contButton);
		cont.setCellHorizontalAlignment(contButton, com.google.gwt.user.client.ui.HorizontalPanel.ALIGN_CENTER);
		cont.setCellWidth(contButton, "100%");
		win.add(cont);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent event) {
				categoryText.setHTML(BabelFish.print().noneSelected());
				category.setToolTip("");
				categoryList = null;
							
				win.hide();
			}
		});
		ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent event) {
					List<String> resultCode = new ArrayList<String>();
					List<String> resultLabel = new ArrayList<String>();
					for(int i=0; i< l.getItemCount(); i++){
						if (l.isItemSelected(i)){
							resultLabel.add(l.getItemText(i));
							resultCode.add(l.getValue(i));
						}
					}
					
					categoryList=resultCode;
					categoryText.setHTML(resultLabel.get(0)+" ...");
					String categorySelected="";
					for (int i=0; i<resultLabel.size(); i++){
						if (i==0){
							categorySelected+=resultLabel.get(i);
						} else{
							categorySelected+=", "+resultLabel.get(i);
						}
						
					}
					category.setToolTip(new ToolTipConfig("Catagories", categorySelected));
				
					win.hide();
				}
		});
		win.show();
	}
	
	
	private void buildRegionList(){
		final Window win=new Window();
		win.setHeading(BabelFish.print().region());
		win.setResizable(false);
		win.setSize(275, 400);
		win.setLayout(new FitLayout());
		com.google.gwt.user.client.ui.VerticalPanel cont= new com.google.gwt.user.client.ui.VerticalPanel();
		cont.setSpacing(5);
		//final ListBox l= new ListBox();
		//DataUtils c=new DataUtils();
		//c.fillRegionList(l);
		
		final ListBox l = new ListBox();
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos)
					l.addItem(vo.getLabel(), vo.getCode());
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
		
		l.setMultipleSelect(true);
		l.setSize("250px", "330px");
		cont.add(l);
		com.google.gwt.user.client.ui.HorizontalPanel contButton = new com.google.gwt.user.client.ui.HorizontalPanel();
		Button ok = new Button(BabelFish.print().ok());
		Button reset = new Button(BabelFish.print().reset());
		contButton.add(ok);
		contButton.add(reset);
		contButton.setSpacing(5);
		cont.add(contButton);
		cont.setCellHorizontalAlignment(contButton, com.google.gwt.user.client.ui.HorizontalPanel.ALIGN_CENTER);
		cont.setCellWidth(contButton, "100%");
		win.add(cont);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent event) {
				regionText.setHTML(BabelFish.print().noneSelected());
				region.setToolTip("");
				regionList=null;
				win.hide();
			}
		});
		ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent event) {
				
				List<String> resultCode = new ArrayList<String>();
				List<String> resultLabel = new ArrayList<String>();
				for(int i=0; i< l.getItemCount(); i++){
					if (l.isItemSelected(i)){
						resultLabel.add(l.getItemText(i));
						resultCode.add(l.getValue(i));
					}
				}
				
				regionList=resultCode;
				
				regionText.setHTML(resultLabel.get(0)+" ...");
				String regionSelected="";
				for (int i=0; i<resultLabel.size(); i++){
					if (i==0){
						regionSelected+=resultLabel.get(i);
					} else{
						regionSelected+=", "+resultLabel.get(i);
					}
					
				}
				region.setToolTip(new ToolTipConfig("Regions", regionSelected));
			
				
				/*	List<String> result=new ArrayList<String>();
					for(int i=0; i< l.getItemCount(); i++){
						if (l.isItemSelected(i)){
							result.add(l.getItemText(i));
						}
					}
						
					regionList=result;
					regionText.setHTML(regionList.get(0)+" ...");
					String regionSelected="";
					for (int i=0; i<regionList.size(); i++){
						if (i==0){
							regionSelected+=regionList.get(i);
						} else{
							regionSelected+=", "+regionList.get(i);
						}
						
					}
					region.setToolTip(new ToolTipConfig("Regions", regionSelected));*/
				
				
					win.hide();
			}
		});
		win.show();
	}
		
	
	 
	private void buildInterface(){
			
		mainCont.add(new HTML("<br><br>"));
		
		HorizontalPanel keywordmainCont=new HorizontalPanel();
		HTML labelKeyword=new HTML(("<b>"+BabelFish.print().keywords()+":<b>"));
		labelKeyword.setWidth(WIDTH);
		keyword.setWidth("150px");
		keywordmainCont.add(labelKeyword);
		keywordmainCont.add(keyword);
		keywordmainCont.setSpacing(SPACING);
		mainCont.add(keywordmainCont);
		
		mainCont.add(new HTML("<br>"));
		
		HorizontalPanel titlemainCont=new HorizontalPanel();
		HTML labelTitle=new HTML(("<b>"+BabelFish.print().title()+":<b>"));
		labelTitle.setWidth(WIDTH);
		titleResource.setWidth("150px");
		titlemainCont.add(labelTitle);
		titlemainCont.add(titleResource);
		titlemainCont.setSpacing(SPACING);
		mainCont.add(titlemainCont);
		
		mainCont.add(new HTML("<br>"));
		
		HorizontalPanel regionmainCont=new HorizontalPanel();
		HTML labelRegion=new HTML(("<b>"+BabelFish.print().region()+":<b>"));
		labelRegion.setWidth(WIDTH);
		regionmainCont.add(labelRegion);
		regionmainCont.add(region);
		IconButton listRegion=new IconButton("adv_options_button");
		listRegion.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent ce){
				buildRegionList();
			}
		});
		regionmainCont.add(listRegion);
		regionmainCont.setSpacing(SPACING);
		mainCont.add(regionmainCont);
		
		mainCont.add(new HTML("<br>"));
		
		HorizontalPanel catagorymainCont=new HorizontalPanel();
		HTML labelCategory=new HTML(("<b>"+BabelFish.print().category()+":<b>"));
		labelCategory.setWidth(WIDTH);
		catagorymainCont.add(labelCategory);
		catagorymainCont.add(category);
		IconButton listCategory=new IconButton("adv_options_button");
		listCategory.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent ce){
				buildCategoryList();
			}
		});
		catagorymainCont.add(listCategory);
		catagorymainCont.setSpacing(SPACING);
		mainCont.add(catagorymainCont);
		
		mainCont.add(new HTML("<br>"));
		
		
		VerticalPanel contOrder = new VerticalPanel();
		
		HorizontalPanel hrOder = new HorizontalPanel();
		hrOder.setSpacing(SPACING);
		hrOder.add(new HTML("<b>" + BabelFish.print().orderBy() + "</b>"));
		orderBy = new ListBox();
		orderBy.setWidth("80px");
		orderBy.addItem("Name", "title");
		orderBy.addItem("Date", "dateLastUpdate");
		hrOder.add(orderBy);
		contOrder.add(hrOder);
		
		HorizontalPanel hrAscDesc = new HorizontalPanel();
		hrAscDesc.setSpacing(SPACING);
		ascOrder = new RadioButton("orderType");
		ascOrder.setChecked(true);
		descOrder = new RadioButton("orderType");
		hrAscDesc.add(new HTML("<span style='font-size:10px; font-weight:bold;'>ASC</span>"));
		hrAscDesc.add(ascOrder);
		hrAscDesc.add(new HTML("<span style='font-size:10px; font-weight:bold;'>DESC</span>"));
		hrAscDesc.add(descOrder);
		contOrder.add(hrAscDesc);
		
		mainCont.add(contOrder);
		
//		nodemainCont=new HorizontalPanel();
//		nodemainCont.setSpacing(SPACING);
//		mainCont.add(nodemainCont);
		
	}
	
	public ListBox getCountriesListBox() {
		ListBox list = new ListBox();
		list.setName("countries");
		
		LinkedHashMap<String, String> map = countries.getGAULCountryList();
		
		//sort the map
		List<String> mapKeys = new ArrayList<String>(map.keySet());
		List<String> mapValues = new ArrayList<String>(map.values());
		map.clear();

		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();

		for (int i=0; i<sortedArray.length; i++) {
    		map.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), (String) sortedArray[i]);
		}
	
		//fill the ListBox, using the now sorted map
		Iterator<Map.Entry<String, String>> keyValuePairs = map.entrySet().iterator();
       
		for (int i = 0; i < map.size(); i++) {
			Map.Entry<String, String> entry = keyValuePairs.next();
			list.addItem(entry.getValue(), entry.getKey());
		}
		return list;

	}
	
}
