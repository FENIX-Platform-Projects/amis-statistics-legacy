package org.fao.fenix.web.modules.ipc.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleFour;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleOne;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleThree;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleTwo;
import org.fao.fenix.web.modules.ipc.common.services.IPCXMLServiceEntry;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RichTextArea;

public class IPCXMLController {
	

	public static void buildModules(final ModuleOne moduleOne, final ModuleTwo moduleTwo,final ModuleThree moduleThree, final ModuleFour moduleFour, final String source, final boolean isFromFile) {
		String langCode = "EN";
		IPCXMLServiceEntry.getInstance().getIPCCodeList(langCode, new AsyncCallback<HashMap<String, List<CodeVo>>>() {
			public void onSuccess(HashMap<String, List<CodeVo>> result) {
				System.out.println("resultsize: " + result.size());
				System.out.println("level: " + result.get("level").size());
				buildModuleOne(moduleOne, source, isFromFile, result);
				buildModuleTwo(moduleTwo, source, isFromFile, result);
				buildModuleThree(moduleThree, source, isFromFile, result);
				buildModuleFour(moduleFour, source, isFromFile, result);
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("Error", e.getMessage());
			}
		});
	
	}
	

	public static void buildModuleOne(final ModuleOne moduleOne, final String source, final boolean isFromFile, final HashMap<String, List<CodeVo>> codes) {
		IPCXMLServiceEntry.getInstance().getModule(source, 1, isFromFile, new AsyncCallback<ModuleVO>() {
				public void onSuccess(ModuleVO result) {
					for (FreeTextVO ftvo : result.getFreeTexts())
						moduleOne.getPanel().add(moduleOne.buildReferenceOutcomePanel(ftvo, codes));
					try {
						moduleOne.getPanel().getLayout().layout();
					} catch (NullPointerException e) {
						
					}
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("Error", e.getMessage());
				}
			});
			
	}
	
	public static void buildModuleTwo(final ModuleTwo moduleTwo, final String source, final boolean isFromFile, final HashMap<String, List<CodeVo>> codes) {
		
		IPCXMLServiceEntry.getInstance().getModule(source, 2, isFromFile, new AsyncCallback<ModuleVO>() {
			
			public void onSuccess(ModuleVO result) {
				HorizontalPanel timePeriodPanel = moduleTwo.buildTimePeriodPanel(result, codes);
				moduleTwo.getPanel().add(timePeriodPanel);
				try {
					moduleTwo.getPanel().getLayout().layout();
				} catch (NullPointerException e) {
					
				}
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("Error", e.getMessage());
			}
			
		});
	}
	
	public static void buildModuleThree(final ModuleThree moduleThree, final String source, final boolean isFromFile, final HashMap<String, List<CodeVo>> codes ) {
		
		IPCXMLServiceEntry.getInstance().getModule(source, 3, isFromFile, new AsyncCallback<ModuleVO>() {
			
			public void onSuccess(ModuleVO result) {
				VerticalPanel moduleThreePanel = moduleThree.buildModuleThreePanel(result, codes);
				moduleThree.getPanel().add(moduleThreePanel);
				try {
					moduleThree.getPanel().getLayout().layout();
				} catch (NullPointerException e) {
					
				}
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("Error", e.getMessage());
			}
			
		});
	}
	
	public static void buildModuleFour(final ModuleFour moduleFour, final String source,
										final boolean isFromFile, final Map<String, List<CodeVo>> codes) {
		
		IPCXMLServiceEntry.getInstance().getModule(source, 4, isFromFile, new AsyncCallback<ModuleVO>() {
			
			public void onSuccess(ModuleVO result) {
				VerticalPanel moduleFourPanel = moduleFour.buildModuleFourPanel(result, codes);
				moduleFour.getPanel().add(moduleFourPanel);
				try {
					moduleFour.getPanel().getLayout().layout();
				} catch (NullPointerException e) {
					
				}
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("Error", e.getMessage());
			}
			
		});
	}
	
	public static SelectionListener<ButtonEvent> editText(final HTML html) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				RichTextArea textArea = new RichTextArea();
				textArea.setHTML(html.toString());
				TextEditor editor = new TextEditor(textArea);
				editor.build();
				editor.getSaveResourceAs().removeAllListeners();
				editor.getSaveResourceAs().addSelectionListener(closeEditor(editor, html));
			};
		};
	}
	
	public static SelectionListener<ButtonEvent> editText(final RichTextArea textAre) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				RichTextArea textArea = new RichTextArea();
				TextEditor editor = new TextEditor(textArea);
				editor.build();
				editor.getSaveResourceAs().removeAllListeners();
				editor.getSaveResourceAs().addSelectionListener(closeEditor(editor, textAre));
			};
		};
	}
	
	public static SelectionListener<ButtonEvent> closeEditor(final TextEditor editor, final HTML html) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent e) {
				System.out.println(editor.getTextArea().getText());
				System.out.println(editor.getTextArea().getHTML());
			
				html.setHTML(editor.getTextArea().getHTML());
				editor.getWindow().close();
			};
		};
	}
	
	public static SelectionListener<ButtonEvent> closeEditor(final TextEditor editor, final RichTextArea textArea) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent e) {
				System.out.println(editor.getTextArea().getText());
				System.out.println(editor.getTextArea().getHTML());
			
				textArea.setHTML(editor.getTextArea().getHTML());
				editor.getWindow().close();
			};
		};
	}
	
}
