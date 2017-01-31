package org.fao.fenix.web.modules.tinymcereport.client.control;


public class TinyMCENativeController {

	/**
	 * there is a bug when you set 'height: "100%"', 
	 * so it has been set to a big value and that's it
	 */
	public static native void initializeTinyMCE(String tinyMCEPanelID) /*-{
		$wnd.tinyMCE.init({
			mode : "none",
			theme: "advanced",
			plugins : "table, template, preview, print, paste, visualchars, insertdatetime, fullscreen, searchreplace, advhr, xhtmlxtras, style",
			skin : "o2k7",
			setup : function(ed) {
      			ed.onDblClick.add(function(ed, e) {
      				ed.focus();
      				var html = ed.selection.getContent({format : 'raw'});
      				@org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController::doubleClick(Ljava/lang/String;Ljava/lang/String;)(html,tinyMCEPanelID);
         		})
      			ed.onContextMenu.add(function(ed, e) {
      				ed.focus();
      				var html = ed.selection.getContent({format : 'raw'});
      				@org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController::rightClick(Ljava/lang/String;Ljava/lang/String;)(html,tinyMCEPanelID);
         		});
   			},
   			visual : false,
   			relative_urls : false,
   			remove_script_host: false, 
   			verify_html : false,  
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_buttons1: "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,formatselect,fontselect,fontsizeselect,|,sub,sup,charmap,|,styleprops,visualchars", 
			theme_advanced_buttons2: "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,hr,removeformat,visualaid,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,code,|,insertdate,inserttime,|,forecolor,backcolor",
    		theme_advanced_buttons3: "tablecontrols,|,fullscreen",
			width  : "100%",
			height : "600",
			force_br_newlines : true,
        	force_p_newlines : false,
        	forced_root_block : '',
			template_templates : [
				{
                	title : "A4 Paper Format",
                    src : "../TinyMCETemplates/A4.html",
                    description : "A4 Paper Format"
                },
                {
                	title : "Test Template",
                    src : "../TinyMCETemplates/Test.html",
                    description : "An Example"
                },
                {
                	title : "Fortnightly Foodgrain Outlook Template",
                    src : "../TinyMCETemplates/Fortnightly.html",
                    description : "Bangladesh Fortnightly Foodgrain Outlook"
                },
                {
                	title : "Bangladesh Food Situation Report",
                    src : "../TinyMCETemplates/Quarterly.html",
                    description : "Bangladesh Food Situation Report"
                }
        	]
		});
	}-*/;
	
	public static native void attachTinyMCE(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceAddControl', true, panelID);
	}-*/;
	
	public static native void refresh(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceRepaint');
	}-*/;
	
	public static native void preview(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mcePreview', true, panelID);
	}-*/;
	
	public static native void cleanup(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceCleanup', true, panelID);
	}-*/;
	
	public static native void repaint(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceNewDocument', true, panelID);
		$wnd.alert("Repainted");
	}-*/;
	
	// $wnd.tinyMCE.execCommand('mceSetContent', false, '');
	
	public static native void fullScreen(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceFullScreen', true, panelID);
	}-*/;
	
	public static native void selectAll(String panelID) /*-{
		$wnd.tinyMCE.execCommand('selectall', true, panelID);
	}-*/;
	
	public static native void replaceAll(String panelID) /*-{
		$wnd.tinyMCE.execCommand('selectall', true, panelID);
		$wnd.tinyMCE.execCommand('mceReplaceContent', true, "");
	}-*/;
	
	public static native void print(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mcePrint', true, panelID);
	}-*/;
	
	public static native void loadTemplate(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceTemplate', true, panelID);
	}-*/;
	
	public static native void removeTinyMCE(String panelID) /*-{
		$wnd.tinyMCE.execCommand('mceRemoveControl', true, panelID);
	}-*/;
	
	public static native String getContent(String panelID) /*-{
		return $wnd.tinyMCE.get(panelID).getContent();
	}-*/;
	
//	var html = null;
//	if ($wnd.tinyMCE.activeEditor) {
//		html = $wnd.tinyMCE.activeEditor.getContent();
//	}
//	return html;
	
	public static native void setContent(String panelID, String content) /*-{
	    $wnd.tinyMCE.execCommand("mceInsertRawHTML", false, content);
	}-*/;
	
}