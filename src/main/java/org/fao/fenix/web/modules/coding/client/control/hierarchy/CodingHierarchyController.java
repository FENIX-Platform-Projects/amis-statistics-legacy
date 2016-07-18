package org.fao.fenix.web.modules.coding.client.control.hierarchy;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.view.hierachy.CodingHierarchySelection;
import org.fao.fenix.web.modules.coding.client.view.hierachy.CodingHierarchyWindow;
import org.fao.fenix.web.modules.coding.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.coding.client.view.utils.TableItemBuilder;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingHiearachyMD;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class CodingHierarchyController {
	
	static TableHeadersBuilder tableHeadersBuilder;
	static TableItemBuilder tableItemBuilder;
	
	
	/***
	 * Method that retrieve all Coding Systems that have hierarchies
	 *  
	 * @return All coding systems
	 */
	public static void findAllCodingSystems(final ListBox listBox) {
		CodingServiceEntry.getInstance().findCsWithHierarchy(new AsyncCallback<List<CodingSystemVo>>() {
			public void onSuccess(List<CodingSystemVo> codingSystems) {
				for(CodingSystemVo codingSystem : codingSystems) {
					if ( codingSystem.getRegion().equals(""))
						listBox.addItem(codingSystem.getTitle());
					else 
						listBox.addItem(codingSystem.getTitle() + ", " + codingSystem.getRegion());
				}	
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: failed to initialize the Coding Systems");					
			}
		});
	}
	
	public static void getCsDescriptions(final CodingHierarchyWindow window, final Long codingSystemId, final ListBox listBox) {
		/***
		 * change name at the level of the selected code (???)
		 * ADD new possibilities, like down up of some levels
		 */
		CodingServiceEntry.getInstance().getCsDescriptions(codingSystemId, window.getCode().getCode(), new AsyncCallback<List<List<String>>>() {
			public void onSuccess(List<List<String>> descriptions) {
				
				System.out.println(descriptions.get(0).size() + " | " + descriptions.get(1).size());
				
				for(int i=0; i < descriptions.get(0).size(); i++){
					System.out.println(descriptions.get(1).get(i));
	
					window.getLevel().put(descriptions.get(1).get(i), Integer.valueOf(descriptions.get(0).get(i)));
					listBox.addItem(descriptions.get(1).get(i));
				}			
				window.setCodeLevel(Integer.valueOf(descriptions.get(2).get(0)));
				
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: failed to initialize the Coding Systems");					
			}
		});
	}
	
	public static void initializeGrid(final CodingHierarchyWindow window, final Long codingSystemId) {
		/***
		 * change name at the level of the selected code (???)
		 * ADD new possibilities, like down up of some levels
		 */
		CodingServiceEntry.getInstance().initializeHierarchyGrid(window.getCodingSystemId(), window.getCode().getCode(), new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codesVo) {
				ListStore<CodingHiearachyMD> md = window.getMd();			
				md.removeAll();
				
				List<CodingHiearachyMD> newMd = new ArrayList<CodingHiearachyMD>();
							
				CodingHiearachyMD code = new CodingHiearachyMD();
				
				for (CodeVo c : codesVo) {
					if ( !c.getCode().equals(window.getCode().getCode()) && !c.getLabel().equals(window.getCode().getLabel())) 
						newMd.add(new CodingHiearachyMD(c.getCode(), c.getLabel(), c.getLevel(), c.getParent()));
					else {
						code = new CodingHiearachyMD(window.getCode().getCode(), window.getCode().getLabel(), c.getLevel(), c.getParent());
						newMd.add(0, code);
					}
				}
				md.add(newMd);
				

				window.getGrid().getSelectionModel().select(code, true);
			
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: failed to initialize the Coding Systems");					
			}
		});
	}
	
	
	
	

	public static SelectionListener<ButtonEvent> getCodingHierarchyToolBox(final CodingHierarchySelection codingHierarchySelection) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				CodeVo codeVo = new CodeVo();
//				codeVo.setCode("398");
//				codeVo.setLabel("Bengo");
//				codingHierarchySelection.getCodingSystemListBox(listBox)
				
				codeVo.setCode("8");
				codeVo.setLabel("Angola");
				
//				codeVo.setCode("Africa");
//				codeVo.setLabel("Africa");
				codingHierarchySelection.getCodingHierarchyWindow().build(new Long(14), codeVo, codingHierarchySelection.getResultedCodes());
			}
		};
	}
	
	
	public static SelectionListener<ComponentEvent> getCodes(final CodingHierarchyWindow window, final Long codingSystemId, final ListBox listBox) {
		return new SelectionListener<ComponentEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ComponentEvent event) {
			String request = listBox.getItemText(listBox.getSelectedIndex());
			List<CodingHiearachyMD> code = new ArrayList<CodingHiearachyMD>();
			code.add(new CodingHiearachyMD(window.getCode().getCode(), window.getCode().getLabel()));

				CodingServiceEntry.getInstance().getCodes(window.getCodingSystemId(), code, window.getCodeLevel(), window.getLevel().get(request), new AsyncCallback<List<List<CodeVo>>>() {
					public void onSuccess(List<List<CodeVo>> codesVo) {
						ListStore<CodingHiearachyMD> md = window.getMd();
						md.removeAll();
	
						List<CodingHiearachyMD> newMd = new ArrayList<CodingHiearachyMD>();
	
						for (CodeVo c : codesVo.get(0)) {
							newMd.add(new CodingHiearachyMD(c.getCode(), c.getLabel(), c.getLevel(), c.getParent()));
						}
						for (CodeVo c : codesVo.get(1)) {
							newMd.add(new CodingHiearachyMD(c.getCode(), c.getLabel(), c.getLevel(), c.getParent()));
						}
						md.add(newMd);
					}
	
					public void onFailure(Throwable caught) {
						FenixAlert.error("ERROR", "RPC: Failed retriving codes");
					}
				});
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> getNewCodes(final CodingHierarchyWindow window, final Long codingSystemId, final ListBox listBox) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
			String request = listBox.getItemText(listBox.getSelectedIndex());
		
			
			
			List<CodingHiearachyMD> checkedData = window.getGrid().getSelectionModel().getSelectedItems();
				
			ListStore<CodingHiearachyMD> md = window.getMd();
			md.removeAll();
			
			System.out.println("Get codes " + checkedData.size());
			List<String> codes = new ArrayList<String>();
			for(CodingHiearachyMD cd : checkedData) {
				codes.add(cd.getCode());
			}
				
				CodingServiceEntry.getInstance().getCodes(window.getCodingSystemId(), checkedData, checkedData.get(0).getlevel(), window.getLevel().get(request), new AsyncCallback<List<List<CodeVo>>>() {
					public void onSuccess(List<List<CodeVo>> codesVo) {
						ListStore<CodingHiearachyMD> md = window.getMd();
	
						List<CodingHiearachyMD> newMd = new ArrayList<CodingHiearachyMD>();
						
						List<CodingHiearachyMD> models = md.getModels();
						
						
						for (CodeVo c : codesVo.get(0)) {
							Boolean check = false;
							for(CodingHiearachyMD model : models){
								if(model.getCode().equals(c.getCode())) {
									check = true;
									break;
								}
							}
							if ( !check) {
//								System.out.println("1)" +c.getCode() + " ," +  c.getLabel());
								newMd.add(new CodingHiearachyMD(c.getCode(), c.getLabel(), c.getLevel(),c.getParent()));
							}
							
						}
						md.add(newMd);
						window.getGrid().getSelectionModel().selectAll();
						
						newMd = new ArrayList<CodingHiearachyMD>();
						for (CodeVo c : codesVo.get(1)) {
							Boolean check = false;
							for(CodingHiearachyMD model : models){
								if(model.getCode().equals(c.getCode())) {
									check = true;
									break;
								}
							}
							if ( !check) {
//								System.out.println("2)" + c.getCode() + " ," +  c.getLabel());
								newMd.add(new CodingHiearachyMD(c.getCode(), c.getLabel(), c.getLevel(), c.getParent()));
							}
						}
						
						md.add(newMd);
//						window.getGrid().getSelectionModel().selectAll();
					}
					
					public void onFailure(Throwable caught) {
						FenixAlert.error("ERROR", "RPC: Failed retriving codes222");
						}
					});
				};
			
			
		};
	}
	
	
	public static SelectionListener<ButtonEvent> addUserSelection(final Grid<CodingHiearachyMD> grid, final Tree tree){
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				List<CodingHiearachyMD> checkedData = grid.getSelectionModel().getSelectedItems();
				for(CodingHiearachyMD cd : checkedData)
					setTree(tree, cd.getCode(), cd.getLabel());
			}
		};
	}
	
	
	public static void setTree(Tree tree, String code, String label){
		System.out.println(code + " | " + label);
		try {
			TreeItem rootElement = tree.getItemById(code);
			if ( rootElement == null ){
				System.out.println("code is null " + code);
				rootElement = new TreeItem();
				rootElement.setId(code);	
				rootElement.setChecked(true);
				rootElement.setText("[" + code + "] " + label);
				tree.getRootItem().add(rootElement);
			}
		}
		catch(Exception e) {
			System.out.println("EXCEPTION");
		}
	}
	
	public static SelectionListener<ButtonEvent> removeSelection(final Tree tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (TreeItem item : tree.getRootItem().getItems())
					if (item.isChecked())
						tree.getRootItem().remove(item);
			}
		};
	}

	public static SelectionListener<ButtonEvent> removeAll(final Tree tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				tree.getRootItem().removeAll();
			}
		};
	}
	
	
	public static SelectionListener<ComponentEvent> getSelectedCodes(final Tree tree, final List<CodeVo> codesVo) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				for (TreeItem item : tree.getRootItem().getItems())
					if (item.isChecked())
						System.out.println();
				/*** bisogna riempire i codici ***/
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> printCodes(final List<CodeVo> codesVo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for(CodeVo codeVo :  codesVo)
					System.out.println(codeVo.getCode() + " | " + codeVo.getLabel());
			}
		};
	}
	
	
	
}
