/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.latex.client.control;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.latex.client.view.LatexWindow;
import org.fao.fenix.web.modules.latex.common.services.LatexServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LatexController {

	public static SelectionListener<IconButtonEvent> addPackage(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String addOn = "<div id='PACKAGE'>" +
				   			   "" + colorString("\\usepackage", "purple") + "{" +
				   			   "" + highlightString("PACKAGE NAME", "yellow") + "" +
				   			   "}</div>";
				alterLatexContent(w, "DOCUMENTCLASS", addOn);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> addAuthor(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String addOn = "<div id='AUTHOR_PREAMBLE'>" +
							   "" + colorString("\\author", "green") + "{" +
							   "" + highlightString("AUTHOR'S NAME", "yellow") + "" +
							   "}<br></div>";
				alterLatexContent(w, "DOCUMENTCLASS", addOn);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> addDate(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String addOn = "<div id='DATE_PREAMBLE'>" +
							   "" + colorString("\\date", "green") + "{" +
							   "" + highlightString("DATE", "yellow") + "" +
							   "}<br></div>";
				alterLatexContent(w, "DOCUMENTCLASS", addOn);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> addTitle(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String addOn = "<div id='TITLE_PREAMBLE'>" +
							   "" + colorString("\\title", "green") + "{" +
							   "" + highlightString("YOUR TEXT HERE", "yellow") + "" +
							   "}<br></div>";
				alterLatexContent(w, "DOCUMENTCLASS", addOn);
				alterLatexContent(w, "BEGIN", "<div id='TITLE_BODY'>" + colorString("\\maketitle", "green") + "</div>");
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> addMultiCols(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String addOn = "<div id='MULTICOLS_PREAMBLE'>" +
							   "" + colorString("\\usepackage", "purple") + "{multicol}<br></div>";
				alterLatexContent(w, "DOCUMENTCLASS", addOn);
				alterLatexContent(w, "BEGIN", "<div id='MULTICOLS_BODY_END'>" + colorString("\\end{multicols}", "red") + "<br></div>");
				alterLatexContent(w, "BEGIN", colorString("YOUR MULTI-COLUMNS TEXT HERE", "black"));
				alterLatexContent(w, "BEGIN", "<div id='MULTICOLS_BODY_START'>" + colorString("\\begin{multicols}{4}", "red") + "<br></div>");
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> addSweave(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String addOn = "<div id='SWEAVE_PREAMBLE'>" + colorString("\\SweaveOpts", "green") + "{echo=false}<br></div>";
				alterLatexContent(w, "DOCUMENTCLASS", addOn);
			}
		};
	}
	
	private static String colorString(String code, String color) {
		return "<font style='color: " + color + ";'>" + code + "</font>";
	}
	
	private static String highlightString(String code, String color) {
		return "<font style='background-color: " + color + ";'>" + code + "</font>";
	}
	
	private static void alterLatexContent(LatexWindow w, String id, String addOn) {
		String code = w.getLatexArea().getHTML();
		code = insert(id, code, addOn);
		w.getLatexArea().setHTML(code);
	}
	
	private static String insert(String id, String code, String addOn) {
		int divStartIDX = code.indexOf("<div id=\"" + id + "\">");
		int divEndIDX = code.indexOf("</div>", divStartIDX);
		int splitIDX = divEndIDX + "</div>".length();
		String firstHalf = code.substring(0, splitIDX);
		String secondHalf = code.substring(splitIDX);
		return firstHalf + addOn + secondHalf;
	}
	
	public static SelectionListener<IconButtonEvent> exportPDF(final LatexWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				String latexAreaContent = w.getLatexArea().getHTML();
				System.out.println("\n" + latexAreaContent + "\n");
				final LoadingWindow loading = new LoadingWindow("LaTeX Report", "TeX Server is processing your report.", "Please Wait...");
				try {
					LatexServiceEntry.getInstance().exportPDF(latexAreaContent, new AsyncCallback<String>() {
						public void onSuccess(String report) {
							loading.destroyLoadingBox();
							System.out.println("REPORT @ " + report);
							int separatorIDX = 1 + report.lastIndexOf("/");
							report = report.substring(separatorIDX);
							Window.open("../exportObject/" + report, "_blank", "status=no");
							loading.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("ERROR", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					loading.destroyLoadingBox();
					FenixAlert.error("ERROR", e.getMessage());
					loading.destroyLoadingBox();
				}
			}
		};
	}
	
}