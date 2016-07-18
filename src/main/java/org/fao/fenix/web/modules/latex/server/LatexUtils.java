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
package org.fao.fenix.web.modules.latex.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.r.RConnector;

public class LatexUtils {
	
	private final static Logger LOGGER =  Logger.getLogger(LatexUtils.class);
	
	private final static String TMP_DIR = System.getProperty("java.io.tmpdir");
	
	private RConnector rConnector = new RConnector();

	public String exportSweavePDF(String cleanLatexAreaContent) throws FenixException {
		
		try {
			
			LOGGER.info("[GWT] - THIS IS ANOTHER ANOTHER TEST");
			
			String snwFilePath = createTemporaryFile(cleanLatexAreaContent, ".snw");
			String snwFilename = snwFilePath.substring(1 + snwFilePath.lastIndexOf(File.separator), snwFilePath.indexOf(".snw"));
			String texFilePath = rConnector.exec(snwFilename);
			String texFilename = texFilePath.substring(1 + texFilePath.lastIndexOf(File.separator), texFilePath.indexOf(".tex"));
			
			String command = "pdflatex -output-directory=" + TMP_DIR + " " + texFilePath;
			
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = buf.readLine()) != null)
				LOGGER.info("[LaTeX] - " + line);
			buf.close();
			
			int returnCode = process.waitFor();
			LOGGER.info("[LaTeX] - " + returnCode);
			
			LOGGER.info("[LaTeX] - RETURNING " + TMP_DIR + File.separator + texFilename + ".pdf");
			return TMP_DIR + File.separator + texFilename + ".pdf";
			
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		} catch (InterruptedException e) {
			throw new FenixException(e.getMessage());
		}
	}	
	
	public String exportLatexPDF(String cleanLatexAreaContent) throws FenixException {
		
		try {
			
			String texFilePath = createTemporaryFile(cleanLatexAreaContent, ".tex");
			String filename = texFilePath.substring(0, texFilePath.indexOf(".tex"));
			String command = "pdflatex -output-directory=" + TMP_DIR + " " + texFilePath;
			
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = buf.readLine()) != null) {
				LOGGER.info("[LaTeX] - " + line);
				if (line.contains("...") || line.contains("Type  H <return>  for immediate help.")) {
					LOGGER.error("LaTeX Thrown an Error. Please Check Your Report or its Contents. Quitting...");
					throw new FenixException("LaTeX Thrown an Error. Please Check Your Report or its Contents.");
				}
			}
			buf.close();
			
			int returnCode = process.waitFor();
			LOGGER.info("[LaTeX] - " + returnCode);
			
			return filename + ".pdf";
			
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		} catch (InterruptedException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	private String createTemporaryFile(String latexAreaContent, String extension) {
		try {
			File file = File.createTempFile("TEX_FILE_", extension);
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(latexAreaContent.getBytes());
			stream.close();
			return file.getAbsolutePath();
		} catch (FileNotFoundException e) {
			LOGGER.info(e.getMessage());
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
	
}
