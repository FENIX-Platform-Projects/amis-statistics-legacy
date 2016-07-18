package org.fao.fenix.web.modules.coding.client.control.creator;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorAttributesTree;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorElementsTree;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorMenu;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorResult;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCodingCreatorWindow;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCreateNewItem;
import org.fao.fenix.web.modules.coding.client.view.creator.DcmtCreatorConfirm;
import org.fao.fenix.web.modules.coding.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.coding.client.view.utils.TableItemBuilder;
import org.fao.fenix.web.modules.coding.client.view.vo.DcmtCodingCreatorMD;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.DcmtCodingCreatorGridMD;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.DataListSelectionModel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class DcmtCodingCreatorController {

	static TableHeadersBuilder tableHeadersBuilder;
	static TableItemBuilder tableItemBuilder;

	/***
	 * Method that retrieve all Code Systems from database
	 * 
	 * @return All coding systems
	 */
	public static void findAllCodingSystems(final ListBox listBox) {
		CodingServiceEntry.getInstance().findAllCodingSystems("Commodity", new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> codingSystems) {
				for (int i = 0; i < codingSystems.size(); i++) {
					if (!codingSystems.get(i).equals("DCMT"))
						listBox.addItem(codingSystems.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");
			}
		});
	}

	/***
	 * Method that retrive the rows of a query
	 * 
	 * @return All coding systems
	 */
	public static SelectionListener<ButtonEvent> getQuerySizeAndReInitialize(final DcmtCodingCreatorMenu window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				String codingSystem = window.getCodingSystemBox().getItemText(window.getCodingSystemBox().getSelectedIndex());
				String criteria = window.getSearchByBox().getItemText(window.getSearchByBox().getSelectedIndex());
				final String search = window.getSearchTextBox().getText();
				final String langCode =  window.getLangCode().getItemText(window.getLangCode().getSelectedIndex());
				window.setLang(langCode);
				
				if (criteria.equals(BabelFish.print().code())) {
					criteria = "Code";
				} else if (criteria.equals(BabelFish.print().label())) {
					criteria = "Label";
				} else if (criteria.equals(BabelFish.print().description())) {
					criteria = "Description";
				} else if (criteria.equals(BabelFish.print().codeSystem())) {
					criteria = "Coding System";
				}
				
				/** used for the saving if the search is a search on a different coding system ***/
				String title = window.getCSystem().getTitle();
				String region = window.getCSystem().getRegion();
				final CodingSystemVo cs = new CodingSystemVo();
				cs.setTitle(title);
				cs.setRegion(region);
				
//				System.out.println("--->" + criteria + " " + I18N.print().codeSystem() );
				CodingServiceEntry.getInstance().tokenizeCodingSystem(codingSystem, new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> codingSystem) {
						cs.setTitle("HS");
						cs.setRegion("0");
						if ((!codingSystem.get(0).equals(window.getCSystem().getTitle()) || (!codingSystem.get(1).equals(window.getCSystem().getRegion())))){
							List<DcmtCodingCreatorGridMD> modifiedCodes = window.getModifiedCodes();
							/** confirm window **/						
							
							System.out.println("search= "  + cs.getTitle() + " "+ cs.getRegion());
							if (modifiedCodes.size() != 0) {
								DcmtCreatorConfirm dcmtConfirm = new DcmtCreatorConfirm(BabelFish.print().confirm(), modifiedCodes, upload(window, modifiedCodes, cs));
							}
							window.initializeModifiedCodes();
//							window.initializeCodingSystem();
							window.getCSystem().setTitle(codingSystem.get(0));
							window.getCSystem().setRegion(codingSystem.get(1));
							
						}
						
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("ERROR", "RPC: Error initialization");
					}
				});

				CodingServiceEntry.getInstance().getQuerySize(criteria, search, codingSystem, langCode, new AsyncCallback<Integer>() {
					public void onSuccess(Integer resultSize) {
						window.getPager().setTotalRows(resultSize);
//						System.out.println(resultSize);
						updatePagerInfo(window, 1);
						searchButton(window, 0, TableConstants.ITEMS_PER_PAGE);
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("ERROR", "RPC: Failed retriving codes");
					}
				});
			}
		};
	}

	/***
	 * check if a code is changed, and if it is changed put it in the Grid
	 * 
	 * @param window
	 * @return
	 */
	public static void checkChangedCodes(final DcmtCodingCreatorMenu window) {
		/*** takes the modified codes ***/
		List<DcmtCodingCreatorGridMD> newMd = window.getModifiedCodes();

		ListStore<DcmtCodingCreatorGridMD> md = window.getMd();

//		System.out.println("size " + newMd.size());
		for (int i = 0; i < newMd.size(); i++) {
//			System.out.println("->" + newMd.get(i).getCode() + " " + newMd.get(i).getDcmtCode() );
			for (int j = 0; j < md.getCount(); j++) {
				if (md.getAt(j).getCode().equals(newMd.get(i).getCode())) {
					md.remove(md.getAt(j));
					md.insert(newMd.get(i), j);
					md.commitChanges();
//					System.out.println("->" + md.getAt(j).getCode() + " " + md.getAt(j).getDcmtCode() + " | " + newMd.get(i).getCode() + " " + newMd.get(i).getDcmtCode());
				}
			}
		}
	}

	/***
	 * select (highlight) the addedCode
	 * 
	 * @param window
	 * @return
	 */
	public static void highlightAddedCode(final DcmtCodingCreatorMenu window, final DcmtCodingCreatorGridMD md) {
		/*** takes the modified codes ***/
		window.getGrid().getSelectionModel().select(md, false);
	}

	/***
	 * insert a code that is changed after has been created
	 * 
	 * @param window
	 * @return
	 */
	public static void insertChangedCode(final DcmtCodingCreatorMenu window, final DcmtCodingCreatorGridMD md) {
		List<DcmtCodingCreatorGridMD> newMd = window.getModifiedCodes();
		Boolean check = false;
//		System.out.println("INSERTING: " + md.getCode() + " " + md.getDcmtCode());
		for (int i = 0; i < newMd.size(); i++) {
			// System.out.println("insertcode: " + newMd.get(i).getCode() + " "
			// + md.getCode());
			if (newMd.get(i).getCode().equals(md.getCode())) {
				newMd.remove(i);
				newMd.add(md);
				check = true;
			}
		}
		if (check == false) {
//			System.out.println("insertcode: " + md.getCode());
			newMd.add(md);
		}
		checkChangedCodes(window);
	}

	/***
	 * get the label from a Dcmt code
	 * 
	 * @param window
	 * @return
	 */
	public static void getDcmtLabel(final DcmtCodingCreatorMenu window, final String dcmtCode) {
		final String langCode = window.getLang();
		if (!dcmtCode.isEmpty())
			CodingServiceEntry.getInstance().testCode(dcmtCode, new AsyncCallback<String>() {
				public void onSuccess(String label) {
					
					
				if (label.equals("false"))
					window.getDcmtLabel().setHTML("<b>" + BabelFish.print().label() + ":</b> " + "<font color='red'>" + BabelFish.print().badFormat() + "</font>");
				else if(label.equals(dcmtCode)) {
					CodingServiceEntry.getInstance().testHierarchicStructure(new AsyncCallback<String>() {
						public void onSuccess(String test) {
							if ( test.isEmpty())
								CodingServiceEntry.getInstance().getDcmtLabel(dcmtCode, langCode, new AsyncCallback<String>() {
									public void onSuccess(String label) {
										 window.getDcmtLabel().setHTML("<b>" + BabelFish.print().label() + ":</b> " + label);	
									}
									public void onFailure(Throwable caught) {
									}
								});
							else 
								window.getDcmtLabel().setHTML("<b>" + BabelFish.print().label() + ":</b> " + "<font color='red'> " + BabelFish.print().badHierarchy() + ": " + test);
						}
						public void onFailure(Throwable caught) {
							}
					});
					}
					else {
						window.getDcmtLabel().setHTML("<b>" + BabelFish.print().label() + ":</b> " + "<font color='red'>" + BabelFish.print().badCode() + ": " + label + "</font>");
					}
				}
	
				public void onFailure(Throwable caught) {
				}
			});
	}

	/***
	 * upload the modified Codes to the database
	 * 
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> uploadModifiedCodes(final DcmtCodingCreatorMenu window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				List<DcmtCodingCreatorGridMD> modifiedCodes = window.getModifiedCodes();
				CodingSystemVo codingSystem = window.getCSystem();
				/** confirm window **/
				if (modifiedCodes.size() == 0) {
					FenixAlert.info("Upload", "There are not modified codes", "");
				} else {
					DcmtCreatorConfirm dcmtConfirm = new DcmtCreatorConfirm(BabelFish.print().confirm(), modifiedCodes, upload(window, modifiedCodes, codingSystem));
				}
			}
		};
	}
	
	/*** 
	 * uploadModified codes when the window is closed 
	 * ***/
	public static void uploadModifiedCodesClosing(final DcmtCodingCreatorMenu window) {
		/*** TODO: call the database for each changed code, and check if the dcmtCode is different or it 
		 * 		   is it to be changed
		 */
		List<DcmtCodingCreatorGridMD> modifiedCodes = window.getModifiedCodes();
		CodingSystemVo codingSystem = window.getCSystem();
		/** confirm window **/
		if (modifiedCodes.size() != 0) {
			DcmtCreatorConfirm dcmtConfirm = new DcmtCreatorConfirm(BabelFish.print().confirm(), modifiedCodes, upload(window, modifiedCodes, codingSystem));
		}
	}
	

	private static SelectionListener<ButtonEvent> upload(final DcmtCodingCreatorMenu window, final List<DcmtCodingCreatorGridMD> modifiedCodes, final CodingSystemVo codingSystem) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Button b = ce.getButton();
//				System.out.println(b.getText());
//				System.out.println("-> " + modifiedCodes.size() + " " + codingSystem.getTitle());
				for (int i = 0; i < modifiedCodes.size(); i++) {
//					System.out.println(modifiedCodes.get(i).getCode() + " | " + modifiedCodes.get(i).getDcmtCode() + " | " + codingSystem.getTitle() + "," + codingSystem.getRegion());
				}
				try {
					CodingServiceEntry.getInstance().uploadModifiedCodes(modifiedCodes, codingSystem, new AsyncCallback<String>() {
						public void onSuccess(String addedRecords) {
//							System.out.println("added records= " + addedRecords);
						}

						public void onFailure(Throwable caught) {
							FenixAlert.error("ERROR", caught.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("RPC Failed", e.getMessage());
				}
				window.initializeModifiedCodes();
			}
		};
	}

	/***
	 * search the codes from a coding system
	 * 
	 * @param window
	 * @return
	 */
	public static void searchButton(final DcmtCodingCreatorMenu window, final int fromIndex, final int toIndex) {
//		final LoadingWindow loading = new LoadingWindow();
//		loading.create(I18N.print().retrivingCodes());
		String codingSystem = window.getCodingSystemBox().getItemText(window.getCodingSystemBox().getSelectedIndex());
		String criteria = window.getSearchByBox().getItemText(window.getSearchByBox().getSelectedIndex());
		final String search = window.getSearchTextBox().getText();
		final String sortingType = window.getPager().getSortingType();
		final String columnName = window.getGrid().getColumnModel().getColumnId(window.getPager().getColumnIndex());
		final String langCode = window.getDcmtCodingCreatorMenu().getLangCode().getItemText(window.getDcmtCodingCreatorMenu().getLangCode().getSelectedIndex());

		if (criteria.equals(BabelFish.print().code())) {
			criteria = "Code";
		} else if (criteria.equals(BabelFish.print().label())) {
			criteria = "Label";
		} else if (criteria.equals(BabelFish.print().description())) {
			criteria = "Description";
		} else if (criteria.equals(BabelFish.print().codeSystem())) {
			criteria = "Coding System";
		}

//		System.out.println(criteria + "," + search + "," + codingSystem + "," + fromIndex + "," + toIndex);

		CodingServiceEntry.getInstance().searchByCreatorOrder(criteria, search, codingSystem, langCode, fromIndex, toIndex, sortingType, columnName, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codesVo) {
				ListStore<DcmtCodingCreatorGridMD> md = window.getMd();
				md.removeAll();

				List<DcmtCodingCreatorGridMD> newMd = new ArrayList<DcmtCodingCreatorGridMD>();

				for (int i = 0; i < codesVo.size(); i++) {
					newMd.add(new DcmtCodingCreatorGridMD(codesVo.get(i).getCode(), codesVo.get(i).getDcmtCode(), codesVo.get(i).getLabel()));
				}

				md.add(newMd);
//				System.out.println("check changes");
				checkChangedCodes(window);
//				loading.destroy();
			}

			public void onFailure(Throwable caught) {
//				loading.destroy();
				FenixAlert.error("ERROR", "RPC: Failed retriving codes");
			}
		});
	}

	/***
	 * Search dcmt elements and attributes from the label selected from the grid
	 * 
	 * @param table
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> searchCodeButton(final EditorGrid<DcmtCodingCreatorGridMD> grid, final DcmtCodingCreatorWindow window) {

		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				DcmtCodingCreatorGridMD m = (DcmtCodingCreatorGridMD) grid.getSelectionModel().getSelectedItem();
				final String label = m.getLabel();
				final String codeLabel = (String) m.getCode();
				final String dcmtCode = m.getDcmtCode();
				final String langCode = window.getDcmtCodingCreatorMenu().getLang();
				final CodingSystemVo cs = window.getDcmtCodingCreatorMenu().getCSystem();
//				System.out.println("LABEL SEARCHED " + label + " | " + langCode);
				List<DcmtCodingCreatorMD> code = new ArrayList<DcmtCodingCreatorMD>();
				List<List<DcmtCodingCreatorMD>> listCreatorMd = new ArrayList<List<DcmtCodingCreatorMD>>();

				window.getDcmtCodingCreatorResult().setMd(m);
				window.getDcmtCodingCreatorResult().setLangCode(langCode);
//				System.out.println("LABEL SEARCHED2 " + label + " | " + window.getDcmtCodingCreatorResult().getLangCode());

				/*** for the Tree **/
				CodingServiceEntry.getInstance().searchDcmtCodes(codeLabel, label, langCode, dcmtCode, cs, new AsyncCallback<List<CodeVo>>() {
					public void onSuccess(List<CodeVo> codesVo) {
						List<DcmtCodingCreatorMD> codeElements = new ArrayList<DcmtCodingCreatorMD>();
						List<DcmtCodingCreatorMD> codeAttributes = new ArrayList<DcmtCodingCreatorMD>();
						List<List<DcmtCodingCreatorMD>> listCreatorMd = new ArrayList<List<DcmtCodingCreatorMD>>();
						ContentPanel cp = window.getDcmtCodingCreatorResult().getCpTree();
						window.getDcmtCodingCreatorResult().getDcmtCode().setHTML("");
						window.getDcmtCodingCreatorResult().setCodingSystem(window.getDcmtCodingCreatorMenu().getCs());
						cp.removeAll();
						Tree tree = window.getDcmtCodingCreatorResult().getTree();
						tree.removeAll();

						window.getDcmtCodingCreatorResult().getLabel().setHTML("<b>" + BabelFish.print().label() + ": " + label + "</b>");
						window.getDcmtCodingCreatorResult().setFromCode(codeLabel);
						VerticalPanel allElements = new VerticalPanel();
						VerticalPanel allAttributes = new VerticalPanel();
						VerticalPanel tmpE = new VerticalPanel();
						VerticalPanel tmpA = new VerticalPanel();
						VerticalPanel again = (VerticalPanel) window.getDcmtCodingCreatorResult().getCpE().getData("allElements");
						window.getDcmtCodingCreatorResult().getCpE().remove(again);

						again = (VerticalPanel) window.getDcmtCodingCreatorResult().getCpA().getData("allAttributes");
						window.getDcmtCodingCreatorResult().getCpA().remove(again);

						for (int i = 0; i < codesVo.size(); i++) {
//							System.out.println("returned CODES->" + i + ")" + codesVo.get(i).getCode() + " " + codesVo.get(i).getLabel());
							if (codesVo.get(i).getCode().charAt(0) >= '0' && codesVo.get(i).getCode().charAt(0) <= '9') {
								codeElements.add(new DcmtCodingCreatorMD("", "", codesVo.get(i).getCode(), codesVo.get(i).getLabel()));
							} else {
								codeAttributes.add(new DcmtCodingCreatorMD("", "", codesVo.get(i).getCode(), codesVo.get(i).getLabel()));
							}
						}
						tmpE = window.getDcmtCodingCreatorResult().buildDatalistElements(codeElements);
						tmpA = window.getDcmtCodingCreatorResult().buildDatalistAttributes(codeAttributes);
						allElements.add(tmpE);
						allAttributes.add(tmpA);
						window.getDcmtCodingCreatorResult().getCpE().add(allElements);
						window.getDcmtCodingCreatorResult().getCpE().setData("allElements", allElements);
						window.getDcmtCodingCreatorResult().getCpA().add(allAttributes);
						window.getDcmtCodingCreatorResult().getCpA().setData("allAttributes", allAttributes);
						if (/*!dcmtCode.isEmpty() &&*/ dcmtCode != null) {
							if (!dcmtCode.isEmpty()) {
								window.getDcmtCodingCreatorResult().getDcmtCode().setHTML(dcmtCode);
								fillTreeFromDcmtCode(window.getDcmtCodingCreatorResult(), window);
							}
						}

						window.getTabs().getItem(1).setEnabled(true);
						window.getTabs().setSelection(window.getTabs().getItem(1));
					}

					public void onFailure(Throwable caught) {

					}
				});
			}
		};
	}

	/***
	 * add the item to the tree and makes the dcmt code adding it to the textBox
	 * 
	 * @param dcmtCR
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> addItem(final DcmtCodingCreatorResult dcmtCR, final DcmtCodingCreatorWindow window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {

				Tree tree = dcmtCR.getTree();

				dcmtCR.getCpTree().removeAll();
				DataList elements = dcmtCR.getElements();
				List<DataListItem> checkedElements = elements.getChecked();

				DataList attributes = dcmtCR.getAttributes();
				List<DataListItem> checkedAttributes = attributes.getChecked();
				List<String> allAttributes = new ArrayList<String>();
				
				if ( dcmtCR.getExcluding().getItem(0).isChecked() && !checkedAttributes.isEmpty())
					FenixAlert.info("excluding", "it's possible to exclude only elements", "");
				if ( dcmtCR.getExcluding().getItem(0).isChecked() ) {
					
				}
				else {
					for (DataListItem checkedAttribute : checkedAttributes) {
						allAttributes.add(checkedAttribute.getText());
					}
				}

			
				final DataListSelectionModel sm = elements.getSelectionModel();
				sm.deselectAll();
				sm.refresh();
				elements.setSelectionModel(sm);

				/*** makes tree **/
				for (int i = 0; i < checkedElements.size(); i++) {
					tree = dcmtCR.setTree(checkedElements.get(i).getText(), allAttributes, dcmtCR.getExcluding().getItem(0).isChecked());
				}

				tree.expandAll();
				dcmtCR.getCpTree().add(tree);
				dcmtCR.getTree().expandAll();
				/** create dcmt code **/
				List<String> allElements = new ArrayList<String>();
				for (int i = 0; i < checkedElements.size(); i++) {
					allElements.add(checkedElements.get(i).getText());
				}
				CodingServiceEntry.getInstance().updateInsertDcmtCode(allElements, allAttributes, dcmtCR.getExcluding().getItem(0).isChecked(), new AsyncCallback<String>() {
					public void onSuccess(String code) {
//						System.out.println("CODE: " + code);
						dcmtCR.getDcmtCode().setHTML(code);
					}

					public void onFailure(Throwable caught) {
//						System.out.println("FAILED: CodingServiceEntry.getInstance().updateInsertDcmtCode");
					}
				});

				/** reset checkboxs **/
				for (int i = 0; i < checkedAttributes.size(); i++) {
					checkedAttributes.get(i).setChecked(false);
				}
				for (int i = 0; i < checkedElements.size(); i++) {
					checkedElements.get(i).setChecked(false);
				}
				dcmtCR.getExcluding().getItem(0).setChecked(false);

				/** refresh **/
				window.getTabs().setSelection(window.getTabs().getItem(0));
				window.getTabs().setSelection(window.getTabs().getItem(1));
			}
		};

	}

	/***
	 * remove the item selected, from the tree and retrives the new dcmt Code
	 * 
	 * @param dcmtCR
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> removeItem(final DcmtCodingCreatorResult dcmtCR, final DcmtCodingCreatorWindow window) {

		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				Tree tree = dcmtCR.getTree();
				TreeItem item = (TreeItem) tree.getSelectionModel().getSelectedItem();
				/***
				 * if the item is an attribute, remove the attribute from the
				 * tree and the code
				 **/
				if (item.getText().charAt(1) >= 'a' && item.getText().charAt(1) <= 'z') {
//					System.out.println(item.getParentItem().getText() + " | " + item.getText());
					CodingServiceEntry.getInstance().updateRemovedAttrDcmtCode(item.getParentItem().getText(), item.getText(), new AsyncCallback<String>() {
						public void onSuccess(String code) {
//							System.out.println("CODE: " + code);
							dcmtCR.getDcmtCode().setHTML(code);
						}

						public void onFailure(Throwable caught) {
//							System.out.println("FAILED: CodingServiceEntry.getInstance().updateRemovedAttrDcmtCode");
						}
					});
				}
				/***
				 * if the item is an element, remove the element from the tree
				 * and code
				 **/
				else {
					CodingServiceEntry.getInstance().updateRemovedElemDcmtCode(item.getText(), new AsyncCallback<String>() {
						public void onSuccess(String code) {
//							System.out.println("CODE: " + code);
							dcmtCR.getDcmtCode().setHTML(code);
						}

						public void onFailure(Throwable caught) {
//							System.out.println("FAILED: CodingServiceEntry.getInstance().updateRemovedElemDcmtCode");
						}
					});
				}
				item.getParentItem().remove(item);
			}
		};
	}

	/***
	 * Map the code and dcmtCode
	 * 
	 * @param dcmtCR
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> addCode(final DcmtCodingCreatorResult dcmtCR, final DcmtCodingCreatorWindow window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				/*** retrive dcmt code **/
				String dcmtCode = dcmtCR.getDcmtCode().getHTML();
//				System.out.println("dcmtCode retrived " + dcmtCode);
				/*** retrive system code **/
				String fromCode = dcmtCR.getFromCode();

				DcmtCodingCreatorGridMD md = dcmtCR.getMd();
				md.setDcmtCode(dcmtCode);

				/*** insert the new in the list of the changed codes ***/
				insertChangedCode(window.getDcmtCodingCreatorMenu(), md);

				window.getDcmtCodingCreatorMenu().getGrid().getSelectionModel().select(md, false);
				window.getTabs().setSelection(window.getTabs().getItem(0));
				
				/*** TODO: checks if the code is already mapped in the same codingSystem ***/
			}
		};
	}

	/***
	 * Calls the window containing the DOMAIN elements
	 * 
	 * @param dcmtCR
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> domainElements(final DcmtCodingCreatorResult dcmtCR) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				final String langCode = dcmtCR.getLangCode();
				/*** get all base elements ***/
				CodingServiceEntry.getInstance().findFathers("elements", langCode, new AsyncCallback<List<CodeVo>>() {
					public void onSuccess(List<CodeVo> codeVo) {
						DcmtCodingCreatorElementsTree eWindow = new DcmtCodingCreatorElementsTree();
						eWindow.build(eWindow, dcmtCR);
						/***
						 * put all the LEVEL1 on the tree
						 */
						Tree tree = eWindow.getTree();
						for (int i = 0; i < codeVo.size(); i++) {
							tree = eWindow.setRoots(codeVo.get(i).getCode(), codeVo.get(i).getLabel());
						}
						/*** shows the tree and the window **/
						eWindow.getCpTree().add(tree);
						eWindow.show();
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("Error making tree", "", "");
					}
				});
			}
		};
	}

	/***
	 * Calls the window containing the DOMAIN attributes
	 * 
	 * @param dcmtCR
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> domainAttributes(final DcmtCodingCreatorResult dcmtCR) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				/*** get all base elements ***/
				final String langCode = dcmtCR.getLangCode();
				CodingServiceEntry.getInstance().findFathers("attributes", langCode, new AsyncCallback<List<CodeVo>>() {
					public void onSuccess(List<CodeVo> codeVo) {
						DcmtCodingCreatorAttributesTree aWindow = new DcmtCodingCreatorAttributesTree();
						aWindow.build(aWindow, dcmtCR);
						/***
						 * put all the LEVEL1 on the tree
						 */
						Tree tree = aWindow.getTree();
						for (int i = 0; i < codeVo.size(); i++) {
							tree = aWindow.setRoots(codeVo.get(i).getCode(), codeVo.get(i).getLabel());
						}
						/*** shows the tree and the window **/
						aWindow.getCpTree().add(tree);
						aWindow.show();
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("Error making tree", "", "");
					}
				});
			}
		};
	}

	/***
	 * Retrive the children of the element selected
	 * 
	 * @param ewindow
	 * @param father
	 * @return
	 */
	public static void childrenElements(final DcmtCodingCreatorElementsTree eWindow, final String father, final String langCode) {
		/*** get all base elements ***/
//		System.out.println("taking children " + langCode);
		CodingServiceEntry.getInstance().findChildren(father, langCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codeVo) {
				/***
				 * retrive children
				 */
//				System.out.println("taking children");
				Tree tree = eWindow.getTree();
				for (int i = 0; i < codeVo.size(); i++) {
					tree = eWindow.setTree(father, codeVo.get(i).getCode(), codeVo.get(i).getLabel());
				}
				/*** shows the tree and the window **/
				tree.expandAll();
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error making tree", "", "");
			}
		});
	}

	/***
	 * Retrive the children of the element selected
	 * 
	 * @param aWindow
	 * @param father
	 * @return
	 */
	public static void childrenAttributes(final DcmtCodingCreatorAttributesTree aWindow, final String father, final String langCode) {
		/*** get all base elements ***/
//		System.out.println("taking children");
		CodingServiceEntry.getInstance().findChildren(father, langCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> codeVo) {
				/***
				 * retrive children
				 */
//				System.out.println("taking children");
				Tree tree = aWindow.getTree();
				for (int i = 0; i < codeVo.size(); i++) {
					tree = aWindow.setTree(father, codeVo.get(i).getCode(), codeVo.get(i).getLabel());
				}
				/*** shows the tree and the window **/
				tree.expandAll();
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error making tree", "", "");
			}
		});
	}

	/***
	 * Close the window with The elements-
	 * 
	 * @param eWindow
	 * @return
	 */
	public static SelectionListener<ButtonEvent> closeWindow(final FenixWindow eWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				eWindow.getWindow().close();
			}
		};
	}

	/***
	 * Insert the element into the datalist of the elements
	 * 
	 * @param eWindow
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> getElement(final DcmtCodingCreatorResult window, final DcmtCodingCreatorElementsTree eWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				DataList elements = window.getElements();
				DataListItem elementsItem = new DataListItem();
				String element = eWindow.getTree().getSelectedItem().getText();
//				System.out.println("element to add " + element);
				elementsItem.setText(element);
				elementsItem.disableTextSelection(true);
				elements.add(elementsItem);
				elements.setAutoWidth(true);
				eWindow.getWindow().close();
			}
		};
	}

	/***
	 * Close the window with The Attributes-
	 * 
	 * @param aWindow
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> getAttribute(final DcmtCodingCreatorResult window, final DcmtCodingCreatorAttributesTree aWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				DataList attributes = window.getAttributes();
				DataListItem attributesItem = new DataListItem();
				String attribute = aWindow.getTree().getSelectedItem().getText();
//				System.out.println("attribute to add " + attribute);
				attributesItem.setText(attribute);
				attributesItem.disableTextSelection(true);
				attributes.add(attribute);
				attributes.setAutoWidth(true);
				aWindow.getWindow().close();
			}
		};
	}

	/***
	 * Calls the window to create a new element
	 * 
	 * @return
	 */
	public static SelectionListener<ButtonEvent> createNewItem(final String item) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				DcmtCreateNewItem dcmtCreateNewItem = new DcmtCreateNewItem();
				dcmtCreateNewItem.build(item);
			}
		};
	}

	/************ PAGER CONTROLLER ***/

	public static void updatePagerInfo(final DcmtCodingCreatorMenu dcmtCodingCreatorMenu, final int page) {
		/*** get total rows about my query **/
		HTML info = dcmtCodingCreatorMenu.getPager().getPageInfo();
		int pages = dcmtCodingCreatorMenu.getPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
		if ((dcmtCodingCreatorMenu.getPager().getTotalRows() % TableConstants.ITEMS_PER_PAGE) != 0)
			pages++;
		info.setHTML(BabelFish.print().page() + " " + page + " / " + pages);
		dcmtCodingCreatorMenu.getPager().setActualPage(page);
	}

	/*** next works **/
	public static SelectionListener<ButtonEvent> next(final DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * dcmtCodingCreatorMenu.getPager().getActualPage();
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > dcmtCodingCreatorMenu.getPager().getTotalRows()) {
					toIndex = dcmtCodingCreatorMenu.getPager().getTotalRows() - fromIndex;
				}

				String codingSystem = dcmtCodingCreatorMenu.getCodingSystemBox().getItemText(dcmtCodingCreatorMenu.getCodingSystemBox().getSelectedIndex());
				final String criteria = dcmtCodingCreatorMenu.getSearchByBox().getItemText(dcmtCodingCreatorMenu.getSearchByBox().getSelectedIndex());
				final String search = dcmtCodingCreatorMenu.getSearchTextBox().getText();

				/** calls the query **/
				searchButton(dcmtCodingCreatorMenu, fromIndex, toIndex);

				int newPage = 1 + dcmtCodingCreatorMenu.getPager().getActualPage();
				if ((fromIndex + toIndex) > dcmtCodingCreatorMenu.getPager().getTotalRows())
					newPage = 1 + dcmtCodingCreatorMenu.getPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(dcmtCodingCreatorMenu, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> previous(final DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				Integer fromIndex = TableConstants.ITEMS_PER_PAGE * (dcmtCodingCreatorMenu.getPager().getActualPage() - 2);
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				String codingSystem = dcmtCodingCreatorMenu.getCodingSystemBox().getItemText(dcmtCodingCreatorMenu.getCodingSystemBox().getSelectedIndex());
				final String criteria = dcmtCodingCreatorMenu.getSearchByBox().getItemText(dcmtCodingCreatorMenu.getSearchByBox().getSelectedIndex());
				final String search = dcmtCodingCreatorMenu.getSearchTextBox().getText();
				if (fromIndex <= 0)
					fromIndex = 0;

				/** calls the query **/
				searchButton(dcmtCodingCreatorMenu, fromIndex, toIndex);

				int newPage = dcmtCodingCreatorMenu.getPager().getActualPage() - 1;
				if ((TableConstants.ITEMS_PER_PAGE * (dcmtCodingCreatorMenu.getPager().getActualPage() - 1)) <= 0)
					newPage = 1;
				updatePagerInfo(dcmtCodingCreatorMenu, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> start(final DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String codingSystem = dcmtCodingCreatorMenu.getCodingSystemBox().getItemText(dcmtCodingCreatorMenu.getCodingSystemBox().getSelectedIndex());
				final String criteria = dcmtCodingCreatorMenu.getSearchByBox().getItemText(dcmtCodingCreatorMenu.getSearchByBox().getSelectedIndex());
				final String search = dcmtCodingCreatorMenu.getSearchTextBox().getText();
				Integer fromIndex = 0;
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > dcmtCodingCreatorMenu.getPager().getTotalRows())
					fromIndex = dcmtCodingCreatorMenu.getPager().getTotalRows() - 1;

				/** calls the query **/
				searchButton(dcmtCodingCreatorMenu, fromIndex, toIndex);

				updatePagerInfo(dcmtCodingCreatorMenu, 1);
			};
		};
	}

	public static SelectionListener<ButtonEvent> end(final DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String codingSystem = dcmtCodingCreatorMenu.getCodingSystemBox().getItemText(dcmtCodingCreatorMenu.getCodingSystemBox().getSelectedIndex());
				final String criteria = dcmtCodingCreatorMenu.getSearchByBox().getItemText(dcmtCodingCreatorMenu.getSearchByBox().getSelectedIndex());
				final String search = dcmtCodingCreatorMenu.getSearchTextBox().getText();
				Integer fromIndex = (TableConstants.ITEMS_PER_PAGE * (dcmtCodingCreatorMenu.getPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE));
				Integer toIndex = dcmtCodingCreatorMenu.getPager().getTotalRows() - 1;

				System.out.println(fromIndex + " " + toIndex);

				/** calls the query **/
				searchButton(dcmtCodingCreatorMenu, fromIndex, toIndex);

				int newPage = 1 + dcmtCodingCreatorMenu.getPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE;
				updatePagerInfo(dcmtCodingCreatorMenu, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> goToPage(final DcmtCodingCreatorMenu dcmtCodingCreatorMenu) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String codingSystem = dcmtCodingCreatorMenu.getCodingSystemBox().getItemText(dcmtCodingCreatorMenu.getCodingSystemBox().getSelectedIndex());
				final String criteria = dcmtCodingCreatorMenu.getSearchByBox().getItemText(dcmtCodingCreatorMenu.getSearchByBox().getSelectedIndex());
				final String search = dcmtCodingCreatorMenu.getSearchTextBox().getText();

				Integer page = Integer.parseInt(dcmtCodingCreatorMenu.getPager().getPageBox().getText());
				int newPage = page;
				if (page <= 1) {
					page = 0;
					newPage = 1;
				} else {
					page--;
				}
				Integer fromIndex = (page * TableConstants.ITEMS_PER_PAGE);
				Integer toIndex = TableConstants.ITEMS_PER_PAGE;
				if ((fromIndex + toIndex) > dcmtCodingCreatorMenu.getPager().getTotalRows()) {
					fromIndex = (TableConstants.ITEMS_PER_PAGE * (dcmtCodingCreatorMenu.getPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE));
					toIndex = dcmtCodingCreatorMenu.getPager().getTotalRows() - 1;
				}
				/** calls the query **/
				searchButton(dcmtCodingCreatorMenu, fromIndex, toIndex);

				if ((fromIndex + toIndex) > dcmtCodingCreatorMenu.getPager().getTotalRows())
					newPage = 1 + (dcmtCodingCreatorMenu.getPager().getTotalRows() / TableConstants.ITEMS_PER_PAGE);
				updatePagerInfo(dcmtCodingCreatorMenu, newPage);
			};
		};
	}

	public static SelectionListener<ButtonEvent> saveNewItem(final DcmtCreateNewItem dcmtCreateNewItem) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String label = (String) dcmtCreateNewItem.getLabel().getValue();
				if (!label.isEmpty()) {
					/** takes the other fields to prepare and add to the db **/
					String description = (String) dcmtCreateNewItem.getDescription().getValue();
					String langCode = (String) dcmtCreateNewItem.getLangCode().getItemText(dcmtCreateNewItem.getLangCode().getSelectedIndex());
					/***
					 * TODO: change with a more user-friendly choice of the "father"
					 ***/
					String father = (String) dcmtCreateNewItem.getStructure().getValue();
					String item = dcmtCreateNewItem.getItem();
					System.out.println(label + " " + langCode + " " + description + " " + father + " " + item);
					CodingServiceEntry.getInstance().saveNewItem(label, langCode, description, father, item, new AsyncCallback() {
						public void onSuccess(Object result) {
							FenixAlert.info("", BabelFish.print().code() + " " + BabelFish.print().added(), " ");
							dcmtCreateNewItem.getWindow().close();
						}
						public void onFailure(Throwable caught) {
							FenixAlert.error("RPC Failed", "RPC: Failed saving code");
						}
					});
				}
			};
		};
	}

	/***
	 * if already exist a dcmtCode put the elements and the attributes to the
	 * tree
	 * 
	 * @param dcmtCR
	 * @param window
	 * @return
	 */
	public static void fillTreeFromDcmtCode(final DcmtCodingCreatorResult dcmtCR, final DcmtCodingCreatorWindow window) {
		final String langCode = window.getDcmtCodingCreatorMenu().getLangCode().getItemText(window.getDcmtCodingCreatorMenu().getLangCode().getSelectedIndex());
		
		/*** retrive the elements with their LABEL **/
		CodingServiceEntry.getInstance().getElementsAttributes(langCode, new AsyncCallback<List<List<String>>>() {
			public void onSuccess(List<List<String>> elementsAttributes) {
				Tree tree = dcmtCR.getTree();
				/***
				 * per ogni elemento cerco gli attributi e li inserisco nel tree
				 ***/
				for (int i = 0; i < elementsAttributes.size(); i++) {
					System.out.println("aiuhsdaiusdhaougysdaousdgyauiosdyag");
					String element = elementsAttributes.get(i).get(0);
					elementsAttributes.get(i).remove(0);
					System.out.println(element + " " + elementsAttributes.get(i));
					tree = dcmtCR.setTree(element, elementsAttributes.get(i), false);
				}
			}

			public void onFailure(Throwable caught) {

			}
		});

//		/*** per ogni elemento cerco gli nonAttributi e li inserisco nel tree ***/
//		CodingServiceEntry.getInstance().getElementsNotAttributes(langCode, new AsyncCallback<List<List<String>>>() {
//			public void onSuccess(List<List<String>> elementsNotAttributes) {
//				Tree tree = dcmtCR.getTree();
//				/***
//				 * per ogni elemento cerco gli attributi e li inserisco nel tree
//				 ***/
//				for (int i = 0; i < elementsNotAttributes.size(); i++) {
//					String element = elementsNotAttributes.get(i).get(0);
//					elementsNotAttributes.get(i).remove(0);
//					if (elementsNotAttributes.get(i).size() != 0)
//						System.out.println(element + " " + elementsNotAttributes.get(i));
//					tree = dcmtCR.setTree(element, elementsNotAttributes.get(i), true);
//				}
//
//			}
//
//			public void onFailure(Throwable caught) {
//			}
//		});

		/*** torno i nonElementi **/
		CodingServiceEntry.getInstance().getNotElements(langCode, new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> notElements) {
				Tree tree = dcmtCR.getTree();
				/***
				 * per ogni elemento cerco gli attributi e li inserisco nel tree
				 ***/
				for (int i = 0; i < notElements.size(); i++) {
					tree = dcmtCR.setTree(notElements.get(i), new ArrayList<String>(), true);
				}
			}

			public void onFailure(Throwable caught) {
			}
		});

		/** refresh **/
		Tree tree = dcmtCR.getTree();
		System.out.println("BELLA ");
		tree.expandAll();
		dcmtCR.getCpTree().add(tree);
		dcmtCR.getTree().expandAll();
		window.getTabs().setSelection(window.getTabs().getItem(0));
		window.getTabs().setSelection(window.getTabs().getItem(1));
	}

	public static SelectionListener<ComponentEvent> exportAsCSV(final DcmtCodingCreatorMenu window) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				String system = window.getCSystem().getTitle();
				String region = window.getCSystem().getRegion();
				
				List<DcmtCodingCreatorGridMD> modifiedCodes = window.getModifiedCodes();
//				CodingSystemVo codingSystem = window.getCSystem();
//				/** confirm window **/
//				if (modifiedCodes.size() == 0) {
//					FenixAlert.info("Upload", "There are not modified codes", "");
//				} else {
//					DcmtCreatorConfirm dcmtConfirm = new DcmtCreatorConfirm(I18N.print().confirm(), modifiedCodes, upload(window, modifiedCodes, codingSystem));
//				}
				
				
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				CodingServiceEntry.getInstance().exportAsCSV(system, region, modifiedCodes, new AsyncCallback<String>(){
					public void onSuccess(String fileName){
						if (fileName.equals("empty")) 
							FenixAlert.info("Error", "There are not saved mapping");
						else
							Window.open("../exportObject/" + fileName, "_blank", "status=no");
						loading.destroy();
					}
					
					public void onFailure(Throwable caught){
						Info.display("exportAsCSV", caught.getLocalizedMessage());
						loading.destroy();
					}
				});
				
			}
		};
	}
	
}
