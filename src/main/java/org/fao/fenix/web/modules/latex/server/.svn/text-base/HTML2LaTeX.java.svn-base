package org.fao.fenix.web.modules.latex.server;

public class HTML2LaTeX {

	/**
	 * Replace all <br>
	 * with \n, remove all HTML tags.
	 */
	// <span style=""font-weight: bold;"">
	public static String cleanLatexAreaContent(String latexAreaContent) {
		System.out.println("####################################################################################################");
		System.out.println(latexAreaContent);
		System.out.println("####################################################################################################");
		latexAreaContent = replaceHTML(latexAreaContent, "<br>", "\\\\newline ");
		latexAreaContent = replaceHTML(latexAreaContent, "&lt;", "<");
		latexAreaContent = replaceHTML(latexAreaContent, "&gt;", ">");
		latexAreaContent = replaceHTML(latexAreaContent, "&nbsp;", " ");
		latexAreaContent = replaceHTML(latexAreaContent, "&amp;", "and");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-weight: bold;\">", "\\\\textbf{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-style: italic;\">", "\\\\emph{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"text-decoration: underline;\">", "\\\\underline{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"background-color: yellow;\">", "\\\\hl{");
		
//		latexAreaContent = replaceHTML(latexAreaContent, "<div style=\"text-align: left;\">", "\\\\begin{center}\n");
//		latexAreaContent = replaceHTML(latexAreaContent, "<div style=\"text-align: center;\">", "\\\\begin{center}\n");
//		latexAreaContent = replaceHTML(latexAreaContent, "<div style=\"text-align: right;\">", "\\\\begin{center}\n");
		
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"text-decoration: line-through;\">", "\\\\sout{");
		latexAreaContent = replaceHTML(latexAreaContent, "<sub>", "\\\\textsubscript{");
		latexAreaContent = replaceHTML(latexAreaContent, "<sup>", "\\\\textsuperscript{");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"1\">", "\\\\tiny\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"2\">", "\\\\small\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"3\">", "\\\\normalsize\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"4\">", "\\\\large\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"5\">", "\\\\Large\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"6\">", "\\\\LARGE\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<font size=\"7\">", "\\\\huge\n");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-family: Times New Roman;\">", "\\\\textrm{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-family: Arial;\">", "\\\\textnormal{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-family: Courier New;\">", "\\\\texttt{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-family: Georgia;\">", "\\\\textsf{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-family: Trebuchet;\">", "\\\\textsf{");
		latexAreaContent = replaceHTML(latexAreaContent, "<span style=\"font-family: Verdana;\">", "\\\\textsf{");
		latexAreaContent = replaceHTML(latexAreaContent, "</span>", "}");
		latexAreaContent = replaceHTML(latexAreaContent, "</sub>", "}");
		latexAreaContent = replaceHTML(latexAreaContent, "</sup>", "}");
//		latexAreaContent = replaceHTML(latexAreaContent, "</font>", "}");
//		latexAreaContent = replaceHTML(latexAreaContent, "</div>", "\\\\end{center}");
		for (HtmlTag tag : HtmlTag.values())
			latexAreaContent = removeHTMLTag(latexAreaContent, tag.name());
		latexAreaContent = removeLatexIllegalCharacters(latexAreaContent);
		System.out.println("####################################################################################################");
		System.out.println(latexAreaContent);
		System.out.println("####################################################################################################");
		return latexAreaContent;
	}

	private static String removeHTMLTag(String html, String tag) {
		html = removeHTMLTag(html, tag, false);
		html = removeHTMLTag(html, tag, true);
		return html;
	}

	private static String removeHTMLTag(String html, String tag, boolean isClose) {
		for (int i = 0; i < html.length(); i++) {
			int openIDX = html.indexOf("<" + tag, i);
			if (isClose)
				openIDX = html.indexOf("</" + tag, i);
			if (openIDX > -1) {
				int closeIDX = 1 + html.indexOf(">", openIDX);
				String openTag = html.substring(openIDX, closeIDX);
				html = html.replaceAll(openTag, "");
			}
		}
		return html;
	}
	
	public static String removeLatexIllegalCharacters(String text) {
		String clean = "";
		for (int i = 0 ; i < text.length() ; i++) {
			switch (text.charAt(i)) {
				case 37: clean += "\\"; break;
//				case 92: clean += "\\"; break;
				case 35: clean += "\\"; break;
				case 36: clean += "\\"; break;
				case 38: clean += "\\"; break;
				case 94: clean += "\\"; break;
				case 95: clean += "\\"; break;
//				case 123: clean += "\\"; break;
//				case 125: clean += "\\"; break;
				case 126: clean += "\\"; break;
			}
			clean += text.charAt(i);
		}
		return clean;
	}

	private static String replaceHTML(String html, String tag, String escapeSequence) {
		html = html.replaceAll(tag, escapeSequence);
		return html;
	}

	enum HtmlTag {
		div, font, li, a, abbr, acronym, address, applet, area, b, base, basefont, bdo, big, blockquote, body, br, button, caption, center, cite, code, col, colgroup, dd, del, dfn, dir, dl, dt, em, fieldset, form, frame, frameset, h1, h2, h3, h4, h5, h6, head, hr, html, i, iframe, img, input, ins, kbd, label, legend, link, map, menu, meta, noframes, noscript, object, ol, optgroup, option, p, param, pre, q, s, samp, script, select, small, span, strike, strong, style, sub, sup, table, tbody, td, textarea, tfoot, th, thead, title, tr, tt, u, ul, var;
	}

}