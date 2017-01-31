package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fao.fenix.map.util.IOUtil;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SingleSLDSaveServlet extends	
	javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 19L;
	
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println(getClass().getSimpleName() + ":: doPost ::");
		
		// save file to filesystem
		String fileName = "temp.xml";
		
		// this is a workaround for the way IE passes params
		int bspos = fileName.lastIndexOf('\\');				
		if(bspos!=-1) fileName = fileName.substring(bspos+1); 
		
		File tempFile = IOUtil.stream2tempfile(request.getInputStream(), fileName);
		response.getWriter().println("PATH="+tempFile.getPath());
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println(getClass().getSimpleName() + ":: doGet ::");
		
		Map params = request.getParameterMap();
		String path = ((String[]) params.get("PATH"))[0];
		
		File tmpFile = new File(path);
		FileInputStream inputSteram = new FileInputStream(tmpFile);
		
		response.setHeader("Content-Disposition", "inline" );
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Expires","0");
		response.setContentType("text/xml");
		IOUtil.copy(inputSteram, response.getOutputStream());
	}
}

