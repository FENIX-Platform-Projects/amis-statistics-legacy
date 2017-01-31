package org.fao.fenix.web.modules.pm.client.control;

import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.re.client.view.project.ResourceExplorerProject;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class ProjectManagerToolbarController {

	public static SelectionListener<IconButtonEvent> createNewProject(){
		return new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent ce){
				// new MetadataEditorProject(null).build();
				MetadataEditorWindow meEditor = new MetadataEditorWindow();
				meEditor.build(false, true, false, MESaver.getSaveProjectListener(meEditor, null));
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> openProjectResourceExplorer(){
		return new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent ce){
				new ResourceExplorerProject();				
			}
		};
	}
	
}
