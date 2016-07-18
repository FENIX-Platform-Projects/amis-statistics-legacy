package org.fao.fenix.web.modules.coding.client.control.search;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchWindow;
import org.fao.fenix.web.modules.coding.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.coding.client.view.utils.TableItemBuilder;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class CodingFindConvertController {
	
	static TableHeadersBuilder tableHeadersBuilder;
	static TableItemBuilder tableItemBuilder;
	
	
	/***
	 * Method that retrieve all Code Systems from database
	 * 
	 * @return All coding systems
	 */
	/** TODO: QUICK FIX about the regions
	 * 		  -> dynamically retrieve regions 
	 */
	public static void findAllCodingSystems(final ListBox codingSystem, final ListBox category) {
		CodingServiceEntry.getInstance().findAllCodingSystems(category.getItemText(category.getSelectedIndex()), new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> cssvo ){
				System.out.println("adding coding systems");
				for(String cs : cssvo) {
					codingSystem.addItem(cs, "0");
				} 		
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: failed to initialize the Code Systems");					
			}
		});
	}
	
	/***
	 * Method that retrieve all the official Fao LangCodes 
	 * 
	 * @return All coding systems
	 */
	public static void findOfficialLangCodesFao(final ListBox listBox) {
		CodingServiceEntry.getInstance().findOfficialLangCodesFao(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> langCodes) {
				for(int i= 0; i< langCodes.size(); i++) {
					listBox.addItem(langCodes.get(i));					
				}
				for(int i= 0; i< listBox.getItemCount(); i++) {
					if(listBox.getValue(i).equals("EN")) {
						listBox.setSelectedIndex(i);
						break;
					}				
				}
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: failed to initialize the Lang Codes");					
			}
		});
	}
	
	/***
	 * Method that close the table result window
	 * 
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> getCancelTableListener(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
			}
		};
	}
	
	/***
	 * Method that clear the table result window
	 * 
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> getClearTableListener(final CodingSearchResults window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getTable().removeAll();
				window.getCodingPager().getPageBox().setText("");
				HTML info = window.getCodingPager().getPageInfo();
				info.setHTML(BabelFish.print().page() + " x / X ");
			}
		};
	}
	
	
	/***
	 * Method that close the table result window
	 * 
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> getCancelMenuListener(final CodingSearchWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
//				System.out.println("trying to close");
			}
		};
	}
	
	/***
	 * Method that clear the table result window
	 * 
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> getClearMenuListener(final CodingSearchWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				System.out.println("trying to clean");
				window.getWindow().close();
				CodingSearchWindow window = new CodingSearchWindow();
				window.build();
			}
		};
	}
	

	public static void showCodingSystem(final CodingSearchResults window, final Long resourceId){
		CodingServiceEntry.getInstance().showCodingSystem(resourceId, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> codesVo) {
					setCodingPager(window, codesVo, BabelFish.print().codeSystem());

					if ( window.getCodingPager().getTotalRows() < TableConstants.ITEMS_PER_PAGE) {
						buildTableCodes(window, 0, window.getCodingPager().getTotalRows());
					}	
					else {
						buildTableCodes(window, 0, TableConstants.ITEMS_PER_PAGE);
						window.getCodingPager().setCodesVo(codesVo);
					}
//				System.out.println("TOTAL ROWS SIZE = " + window.getCodingPager().getTotalRows());
				}
				public void onFailure(Throwable caught) {		
				}
			});
	}
	
	
	
	
	/**************************************************
	 * 
	 * SEARCH/CONVERT
	 * 
	 * @return
	 ********************************************/
	/** TODO: ADD A LOADING WINDOW **/
	public static SelectionListener<ButtonEvent> findConvertButton(final ListBox searchBox, final TextBox searchTextBox,
			final ListBox codingSystemBox, final ListBox searchCodingSystemBox, final ListBox langCodes, final Table table, final CodingSearchResults window) {
		
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
//				LoadingWindow loadingWindow = new LoadingWindow("Retriving codes", "Querying the database Codes", "loading");
//				loadingWindow.showLoadingBox();
				/*** TODO: get langCode from listBox ***/
				final String langCode = langCodes.getItemText(langCodes.getSelectedIndex());
				String criteria = searchBox.getItemText(searchBox.getSelectedIndex()); 
				
				
				if ( criteria.equals(BabelFish.print().code())) {
					criteria = "Code";
				}
				else if ( criteria.equals(BabelFish.print().label())) {
					criteria = "Label";
				}
				else if ( criteria.equals(BabelFish.print().description())) {
					criteria = "Description";
				}
				else if ( criteria.equals(BabelFish.print().codeSystem())) {
					criteria = "Coding System";
				}
				

				/***
				 * Start Checks on the required field
				 */
				if ( searchBox.getSelectedIndex() == 0 ){
					FenixAlert.error("ERROR", "RPC: Select a search criteria");	
				}
				 
				// se la selezione e' diversa da code system(4) e il testo e' vuoto
				else if ( !criteria.equals("Coding System") && searchTextBox.getText().isEmpty()) {
					FenixAlert.error("ERROR", "RPC: Text Field Search is Empty");	
				}
				
				// se la selezione e' diversa da code system(4), il testo non e' vuoto
				// ma non e' stato selezionato nessun coding system
				else if ( !criteria.equals("Coding System") && !searchTextBox.getText().isEmpty() && codingSystemBox.getSelectedIndex() == 0 ) {
					FenixAlert.error("ERROR", "RPC: Select a Coding System");	
				}
				
				// è stato inserita la ricerca di un code system senza averne specificato alcuno nel text box o nella list box
				else if (criteria.equals("Coding System") && searchTextBox.getText().isEmpty() && codingSystemBox.getSelectedIndex() == 0) {
					FenixAlert.error("ERROR", "RPC: Insert a Code System in the Text Box, or using the List Box");	
				}
				
				// è stato inserita la ricerca di un code system senza averne specificato alcuno nel text box o nella list box
				else if ( codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("ALL") && 
						searchCodingSystemBox.getItemText(searchCodingSystemBox.getSelectedIndex()).equals("ALL")) {
					FenixAlert.error("ERROR", "RPC: Cannot make a conversion between ALL Coding Systems");	
				}
				
				/*****
				 * 
				 * 	SEARCHING (not convert)
				 * 
				 */
				else if ( searchCodingSystemBox.getSelectedIndex() == 0) {//  && !searchTextBox.getText().isEmpty() && codingSystemBox.getSelectedIndex() != 0 ) {
				 // Retrieve the CODE From ALL Code System
					final String search = searchTextBox.getText();				
					
//					System.out.println(criteria);
					/**
					 * SearchALLBy 
					 */
					if ( codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("ALL")) {					
						List<String> codeSystems = new ArrayList<String>();
						for (int i = 2; i < codingSystemBox.getItemCount(); i++) {
							codeSystems.add(codingSystemBox.getItemText(i));
						}
						
						CodingServiceEntry.getInstance().searchAllBy(criteria, search, codeSystems, langCode, new AsyncCallback<List<List<CodeVo>>>() {
							public void onSuccess(List<List<CodeVo>> allCodesVo) {	
								List<CodeVo> codesVo = new ArrayList<CodeVo>();
								
								if ( allCodesVo.isEmpty()){
									FenixAlert.info("", "No codes retrieved");
								}else {
									
									for (int i=0; i < allCodesVo.size(); i++ ) {
										for (int j = 0; j < allCodesVo.get(i).size(); j++) {
											codesVo.add(allCodesVo.get(i).get(j));
//											System.out.println("-> "+ allCodesVo.get(i).get(j).getTitle());
										}
									}
									if ( codesVo.isEmpty()){
										FenixAlert.info("", "No codes retrieved");
									}
									else {
										
										setCodingPager(window, codesVo, "ALL");
										
										if ( window.getCodingPager().getTotalRows() < TableConstants.ITEMS_PER_PAGE) {
											buildTableCodes(window, 0, window.getCodingPager().getTotalRows());
										}	
										else {
											buildTableCodes(window, 0, TableConstants.ITEMS_PER_PAGE);
											window.getCodingPager().setCodesVo(codesVo);
										}
	//								System.out.println("TOTAL ROWS SIZE = " + window.getCodingPager().getTotalRows());
									}
								}
							}
							public void onFailure(Throwable caught) {
								FenixAlert.error("ERROR", "RPC: Failed, the " + searchBox.getItemText(searchBox.getSelectedIndex()) 
										+ " " + searchTextBox.getText() + " doesn't exist");					
							}
						});
					}
					/**
					 * SearchBy
					 */
					else {						
						String codingSystem = codingSystemBox.getItemText(codingSystemBox.getSelectedIndex());
						CodingServiceEntry.getInstance().searchBy(criteria, search, codingSystem, langCode, new AsyncCallback<List<CodeVo>>() {
							public void onSuccess(List<CodeVo> codesVo) {
								
								if ( codesVo.isEmpty()){
									FenixAlert.info("", "No codes retrieved");
								}else {
	
									setCodingPager(window, codesVo, "SearchBy");
									
									if ( window.getCodingPager().getTotalRows() < TableConstants.ITEMS_PER_PAGE) {
										buildTableCodes(window, 0, window.getCodingPager().getTotalRows());
									}	
									else {
										buildTableCodes(window, 0, TableConstants.ITEMS_PER_PAGE);
										window.getCodingPager().setCodesVo(codesVo);
									}
//									System.out.println("TOTAL ROWS SIZE = " + window.getCodingPager().getTotalRows());
									}
							}
							public void onFailure(Throwable caught) {
								FenixAlert.error("ERROR", "RPC: Failed, the " + searchBox.getItemText(searchBox.getSelectedIndex()) 
										+ " " + searchTextBox.getText() + " doesn't exist");					
							}
						});
					}		
				}	

				
				
				/***
				 * HERE THE USER SELECTED A CONVERSION BETWEEN DIFFERENT CODE SYSTEMS
				 */
				  else if ( searchCodingSystemBox.getSelectedIndex() != 0 && codingSystemBox.getSelectedIndex() != 0 ) {
//					System.out.println("CONVERT-" + codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()) + "-");
					/**
					 * Checks 	
					 */
					// test if codingsystem selected is the same as the one selected				
					if ( codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals(searchCodingSystemBox.getItemText(searchCodingSystemBox.getSelectedIndex()))) {
							// call the function that retrive all the code system that have that code
							FenixAlert.error("ERROR", "RPC: The Code Systems selected are equals");
					}
				
					/***
					 * CONVERT FROM DCMT
					 */
					else if ( codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("DCMT")) {
//						System.out.println("CONVERT FROM DCMT");
						//final String criteria = searchBox.getItemText(searchBox.getSelectedIndex());
						final String search = searchTextBox.getText();
						
						String destinationCodingSystem = searchCodingSystemBox.getItemText(searchCodingSystemBox.getSelectedIndex());
						CodingServiceEntry.getInstance().convertFromDcmt(criteria, search, destinationCodingSystem, langCode, new AsyncCallback<List<CodeVo>>() {
							public void onSuccess(List<CodeVo> codesVo) {
								if ( codesVo.isEmpty()){ 
									FenixAlert.info("", "No codes retrieved");
								}
								else {
									setCodingPager(window, codesVo, "Convert into");		
									if ( window.getCodingPager().getTotalRows() < TableConstants.ITEMS_PER_PAGE) {
										buildTableCodes(window, 0, window.getCodingPager().getTotalRows());
									}	
									else {
										buildTableCodes(window, 0, TableConstants.ITEMS_PER_PAGE);
										window.getCodingPager().setCodesVo(codesVo);
									}
								}
							}
							public void onFailure(Throwable caught) {
								FenixAlert.error("ERROR", "RPC: Failed, the " + searchBox.getItemText(searchBox.getSelectedIndex()) 
										+ " " + searchTextBox.getText() + " doesn't exist");					
							}
						});			
					}
					else if ( !codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("DCMT")) {
//						System.out.println("CONVERT");
						//final String criteria = searchBox.getItemText(searchBox.getSelectedIndex());
						final String search = searchTextBox.getText();
						
						
						if ( criteria.equals("Code") && !codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("ALL") && !searchCodingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("ALL")) {
							String destinationCodingSystem = searchCodingSystemBox.getItemText(searchCodingSystemBox.getSelectedIndex());
							String sourceCodingSystem = codingSystemBox.getItemText(codingSystemBox.getSelectedIndex());
//							System.out.println(criteria +" "+search +" "+sourceCodingSystem + " "+destinationCodingSystem);
							CodingServiceEntry.getInstance().convertInto(criteria, search, sourceCodingSystem, destinationCodingSystem, langCode, new AsyncCallback<List<CodeVo>>() {
								public void onSuccess(List<CodeVo> codesVo) {
									if ( codesVo.isEmpty()){
										FenixAlert.info("", "No codes retrieved");
									}
									else {
										setCodingPager(window, codesVo, "Convert into");		
										if ( window.getCodingPager().getTotalRows() < TableConstants.ITEMS_PER_PAGE) {
											buildTableCodes(window, 0, window.getCodingPager().getTotalRows());
										}	
										else {
											buildTableCodes(window, 0, TableConstants.ITEMS_PER_PAGE);
											window.getCodingPager().setCodesVo(codesVo);
										}
									}
								}
								public void onFailure(Throwable caught) {
									FenixAlert.error("ERROR", "RPC: Failed, the " + searchBox.getItemText(searchBox.getSelectedIndex()) 
											+ " " + searchTextBox.getText() + " doesn't exist");					
								}
							});		
						}
						else {
							if ( !criteria.equals("Code"))
//								FenixAlert.alert("", I18N.print().notCode);
								FenixAlert.alert("ERROR", "Select a code to convert");
							else if (codingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("ALL"))
//								FenixAlert.alert("", I18N.print().notCode);
								FenixAlert.alert("ERROR", "Select a codingSystem");								
							else if	(searchCodingSystemBox.getItemText(codingSystemBox.getSelectedIndex()).equals("ALL"))
//							FenixAlert.alert("", I18N.print().notCode);
							FenixAlert.alert("ERROR", "Select a codingSystem");
						}
					}
					
				}
//				loadingWindow.destroy();
			}
			
		};
	}
	
	
	public static SelectionListener<ButtonEvent> findConvertButton(final CodingSystemVo codingSystemVo, final ListBox langCodes, final TextBox searchTextBox, final Table table, final CodingSearchResults window) {
		
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			public void componentSelected(ButtonEvent event) {
				final String langCode = langCodes.getItemText(langCodes.getSelectedIndex());
				String criteria = "Label";
				final String search = searchTextBox.getText();				
	
				CodingServiceEntry.getInstance().searchBy(criteria, search, codingSystemVo.getTitle(), langCode, new AsyncCallback<List<CodeVo>>() {
					public void onSuccess(List<CodeVo> codesVo) {								
						if ( codesVo.isEmpty())
							FenixAlert.info("", "No codes retrieved");
									
						else {
							setCodingPager(window, codesVo, "SearchBy");
							
							if ( window.getCodingPager().getTotalRows() < TableConstants.ITEMS_PER_PAGE) 
								buildTableCodes(window, 0, window.getCodingPager().getTotalRows());
											
							else {
								buildTableCodes(window, 0, TableConstants.ITEMS_PER_PAGE);
								window.getCodingPager().setCodesVo(codesVo);
							}
	
						}
					}
					public void onFailure(Throwable caught) {
							FenixAlert.error("ERROR", "RPC: Failed, because " + searchTextBox.getText() + " doesn't exist");					
						}
				});
							
			}	

		};
	}
	
	
	public static void buildTableCodes(CodingSearchResults window, Integer fromIndex, Integer toIndex) {
//		System.out.println(fromIndex + " " + toIndex);
		tableItemBuilder = new TableItemBuilder();
		window.getTable().removeAll();		
		
		if ( window.getCodingPager().getType().equals("ALL") ) {
			TableHeadersBuilder.renameTableHeadersForSearchAll(window.getTable());
			for (int i=fromIndex; i < toIndex; i++) {
				window.getTable().add(TableItemBuilder.buildTableItemWithCs(window.getCodingPager().getCodesVo().get(i)));
			}	
		}
		else {
			TableHeadersBuilder.renameTableHeadersForSearch(window.getTable());
			for (int i=fromIndex; i < toIndex; i++) {
				window.getTable().add(TableItemBuilder.buildTableItem(window.getCodingPager().getCodesVo().get(i)));
			}	
			/** TODO: add region **/
			window.setTitle("Searched into " + window.getCodingPager().getCodesVo().get(0).getTitle());
		}
	
		window.getTable().setVisible(true);		
		window.show();
	}
	
	
	public static void setCodingPager(CodingSearchResults window, List<CodeVo> codesVo, String type) {
		window.getCodingPager().setTotalRows(codesVo.size());
		window.getCodingPager().setActualPage(1);
		window.getCodingPager().setType(type);
		window.getCodingPager().setCodesVo(codesVo);
		HTML info = window.getCodingPager().getPageInfo();
		info.setHTML(BabelFish.print().page() + " 1 / " + window.getCodingPager().getTotalPages());
		window.getCodingPager().getPageBox().setText("1");
	}
	
	/***
	 * Method that add selected values to the combobox of the table
	 * 
	 * @param window
	 * @return
	 */
	public static SelectionListener<ButtonEvent> fillTableComboBox(final CodingSearchWindow codingSearchWindow, final CodingSearchResults window, final ListStore<DimensionItemModel> listStore, final ComboBox<DimensionItemModel> combo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Table t = window.getTable();
				List<TableItem> tableItems = t.getSelectedItems();
				 for(TableItem item : tableItems) {
					 System.out.println("Label: " + item.getValue(4));
					 System.out.println("Code1: " + item.getValue(0));
					 DimensionItemModel model = new DimensionItemModel(item.getValue(4).toString(), item.getValue(0).toString());	  
					 listStore.add(model);
					 combo.select(combo.getStore().getCount() - 1);
				 }

				window.getWindow().close();
				codingSearchWindow.getWindow().close();
				
				
				FenixAlert.info(BabelFish.print().newItemsAdded(), BabelFish.print().selectedItemsAddedToList(), "");								
			}
		};
	}

}
