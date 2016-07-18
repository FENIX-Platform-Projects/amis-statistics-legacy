package org.fao.fenix.web.modules.cpf.client.view.home;


import org.fao.fenix.web.modules.cpf.client.view.menu.CPFMainMenu;

public class CPFHome {

	CPFMainMenu mainMenu;
	 
	public CPFHome(CPFMainMenu menu) {
		mainMenu = menu;
	}

	public void build() {
        
		restoreHomeVisibility();
			
	}
		
	public static void restoreHomeVisibility() {
		styleVisibilityDisplay("HOME_CONTENT");
	}

	public static void hideHomeVisibility() {
		styleVisibilityNoDisplay("HOME_CONTENT");
	}
	
	
	 
		


	 public static native String styleVisibilityDisplay(String id)/*-{
     var elem;
     elem = $doc.getElementById(id);
     if (elem != null) {
	//  alert(id + " | " + elem.style.display);

//	    elem.style.visibility = "visible";
	    elem.style.display = "";
	    return "in-line";
	 }
     else
     	return null;


}-*/;

	 public static native String styleVisibilityNoDisplay(String id)/*-{
		var elem;
		elem = $doc.getElementById(id);
		if (elem != null) {
//		  	alert(id + " | " + elem.style.display);

//		    elem.style.visibility = "hidden";
		    elem.style.display = "none";
		    return "none";

		}
		else
		   return null;


	}-*/;



}
