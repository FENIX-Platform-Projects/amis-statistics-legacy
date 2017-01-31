package org.fao.fenix.web.modules.edi.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FEWSNETServiceAsync {

	/**
	 * 
	 * @param zipURLs URL's used at http://earlywarning.usgs.gov/fews/haiti/web/imgbrowsc2.php?extent=crem
	 * @return A collection of ZIP Workstation-paths to allow users to get the ZIP files.
	 * @throws FenixGWTException
	 */
	public void getZipFiles(List<String> zipURLs, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	/**
	 * 
	 * @param zipURLs URL's used at http://earlywarning.usgs.gov/fews/haiti/web/imgbrowsc2.php?extent=crem
	 * @return A collection of ID's to open the layers from the Workstation interface
	 * @throws FenixGWTException
	 */
	public void importZipFiles(String zipURL, ResourceVO rvo, String requestedStyle, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	/** Run the <code>geotiffHarvester</code> and delete zip(s) and unzipped file(s). */
	public void harvest(List<String> bin, AsyncCallback<Boolean> callback) throws FenixGWTException;
	
}