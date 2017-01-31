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
package org.fao.fenix.web.modules.x.server.utils;

import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;

public class OptionsFactory {

	private final static long TIMEOUT = 60000;
	
	public static Options createRequestUpdateOptions(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestUpdate");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options createRequestChunkOptions(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestChunk");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options createRequestUpdateSizeOptions(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestUpdateSize");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options createDeleteChunksOptions(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:deleteChunks");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options createRequestDatasetOptions(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestDataset");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options createRequestTextOptions(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestText");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options requestDBFeatureLayerChunkList(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestDBFeatureLayerChunkList");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options requestShpFeatureLayerChunkList(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestShpFeatureLayerChunkList");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
	public static Options requestRasterLayerChunkList(String url) {
		Options options = new Options();
		options.setTo(new EndpointReference(url));
		options.setAction("urn:requestRasterLayerChunkList");
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
		options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
		options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
		options.setTimeOutInMilliSeconds(TIMEOUT);
		return options;
	}
	
}
