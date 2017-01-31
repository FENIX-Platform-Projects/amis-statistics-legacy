package org.fao.fenix.web.modules.x.server.utils;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

public class XWebServicePayload {

	private final static String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";
	
	public static OMElement requestUpdate(String payload) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = fac.createOMNamespace(NAMESPACE, "ns");
		OMElement method = fac.createOMElement("requestUpdate", namespace);
		OMElement value = fac.createOMElement("payload", namespace);
		value.addChild(fac.createOMText(value, payload));
		method.addChild(value);
		return method;
	}
	
}