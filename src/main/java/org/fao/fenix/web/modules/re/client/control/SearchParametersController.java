package org.fao.fenix.web.modules.re.client.control;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchParametersController {
	
	public static ChangeListener buildLookInChangeListener() {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				ListBox listbox = (ListBox)widget;
			}
		};
	}
	
	public static KeyboardListener onEnter(final ResourceExplorer resourceExplorer){
		return new KeyboardListener(){
			public void onKeyDown(Widget sender, char keyCode, int modifiers) {
			}

			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			}

			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if (keyCode == '\r'){
					SearchButtonsController.getResourcesAction(resourceExplorer);
				}
				
			}
		};
	}
	
	public static void fillCategories(final LinkedHashMap<String,String> categoryMap, final ListBox categoryListBox){
		REServiceEntry.getInstance().getCategories( new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codes) {
				//System.out.println("codes " +codes.size());
				for (CodeVo code : codes) {
//					System.out.println("codes " + code.getCode() );
//					categoryMap.put(code.getCode(), code.getLabel());
					categoryListBox.addItem(code.getLabel(), code.getCode());
				}
				System.out.println("size " + categoryMap.size() );
			}

			public void onFailure(Throwable caught) {
//				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");
			}
		});
		
	}
	
	public static void fillCategories(final ListStore<CategoryModelData> listStore){
		REServiceEntry.getInstance().getCategories( new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codes) {
				//System.out.println("codes " +codes.size());
				for (CodeVo code : codes) {
//					System.out.println("codes " + code.getCode() );
//					categoryMap.put(code.getCode(), code.getLabel());
					
					
					listStore.add(new CategoryModelData(code.getCode(), code.getLabel()));
				}
		
			}

			public void onFailure(Throwable caught) {
//				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");
			}
		});
		
	}
	
	public static void fillCategories(final String categoryCode, final ResourceChildModel rcm){
		REServiceEntry.getInstance().getCategories( new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codes) {
			//	System.out.println("codes " +codes.size());
				for (CodeVo code : codes) {
//					System.out.println("codes " + code.getCode() );
//					categoryMap.put(code.getCode(), code.getLabel());
					if ( categoryCode.equals(code.getCode()));
						rcm.set("category", code.getLabel());				
				}
		
			}

			public void onFailure(Throwable caught) {
//				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");
			}
		});
		
//		REServiceEntry.getInstance().getCategoryLabel(categoryCode, new AsyncCallback<CodeVo>() {
//			public void onSuccess(CodeVo code) {
//				rcm.set("category", code.getLabel());			
//			}
//			public void onFailure(Throwable caught) {
//				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");
//			}
//		});
//		
	}
	
	
	
	public static void fillCategories(final String categoryCode, final CategoryModelData cmd){
		System.out.println("categoryCode " + categoryCode );
		REServiceEntry.getInstance().getCategoryLabel(categoryCode, new AsyncCallback<CodeVo>() {
			public void onSuccess(CodeVo code) {
				cmd.setCategoryName(code.getLabel());	
			}
			public void onFailure(Throwable caught) {
//				FenixAlert.error("ERROR", "RPC: failed to get the Category");
			}
		});
		
	}
	
//	public static void fillResourceChildModel(final String categoryCode, final CategoryModelData cmd){
//		System.out.println("categoryCode " + categoryCode );
//		REServiceEntry.getInstance().getCategoryLabel(categoryCode, new AsyncCallback<CodeVo>() {
//			public void onSuccess(CodeVo code) {
//				cmd.setCategoryName(code.getLabel());	
//			}
//			public void onFailure(Throwable caught) {
//				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");
//			}
//		});
//		
//	}
	
	

}
