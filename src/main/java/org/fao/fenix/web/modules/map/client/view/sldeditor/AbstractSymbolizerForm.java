/*
 */

package org.fao.fenix.web.modules.map.client.view.sldeditor;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author ETj
 */
public interface AbstractSymbolizerForm {
	
	public FormPanel getSymbolizerForm();
	public JavaScriptObject updateSymbolizer();

}
