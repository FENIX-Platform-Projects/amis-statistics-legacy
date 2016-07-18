package org.fao.fenix.web.modules.communication.server;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;

public class CommunicationServicePayload {

	public static OMElement getPingPayload() {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("sayHello", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		value.addChild(fac.createOMText(value, "GIEWS"));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getSaveCommunicationResourcePayload(CommunicationResourceVo rsrc) {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("saveCommunicationResource", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		String payload = rsrc.getType() + "#" + rsrc.getLocalId();
		value.addChild(fac.createOMText(value, payload));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getShaDigestPayload(CommunicationResourceVo rsrc) {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("shaDigest", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		String payload = rsrc.getType() + "#" + rsrc.getLocalId();
		value.addChild(fac.createOMText(value, payload));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getImportDatasetPayload(String localResourceId, String username, String type) {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("importDataset", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		String payload = localResourceId + ":" + username + ":" + type;
		value.addChild(fac.createOMText(value, payload));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getImportIPCDatasetPayload(String localResourceId, String username) {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("importIPCDataset", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		String payload = localResourceId + ":" + username;
		value.addChild(fac.createOMText(value, payload));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getCreateIPCMapPayload(String gaul0code, String gaul0label, String layerLabel, String layerCode, String username, String phaseDatasetId, String  riskDatasetId, String trendDatasetId, String mapIdentifier) {
		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("createIPCMap", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		String payload = gaul0code + ":" + gaul0label + ":" + layerLabel + ":" + layerCode + ":" + username + ":" + phaseDatasetId + ":" + riskDatasetId + ":" + trendDatasetId + ":" + mapIdentifier;
		value.addChild(fac.createOMText(value, payload));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getDownloadResourcePayload(String id) {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

		OMElement method = fac.createOMElement("downloadResourceServer", omNs);
		OMElement value = fac.createOMElement("symbol", omNs);

		value.addChild(fac.createOMText(value, id));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getCommunicationResourcesPayload() {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = fac.createOMNamespace(NAMESPACE, "ns");

		OMElement method = fac.createOMElement("getCommunicationResources", namespace);
		OMElement value = fac.createOMElement("symbol", namespace);

		value.addChild(fac.createOMText(value, "COMMUNICATION MODULE GET COMMUNICATION RESOURCES"));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getUserListPayload() {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = fac.createOMNamespace(NAMESPACE, "ns");

		OMElement method = fac.createOMElement("getUserList", namespace);
		OMElement value = fac.createOMElement("symbol", namespace);

		value.addChild(fac.createOMText(value, "COMMUNICATION MODULE GET USER LIST"));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getPeerListPayload() {

		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = fac.createOMNamespace(NAMESPACE, "ns");

		OMElement method = fac.createOMElement("getPeerList", namespace);
		OMElement value = fac.createOMElement("symbol", namespace);

		value.addChild(fac.createOMText(value, "GIEWS"));
		method.addChild(value);

		return method;

	}
	
	public static OMElement getPushInfoPayload(CommunicationResourceVo rsrc) {
		String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace namespace = fac.createOMNamespace(NAMESPACE, "ns");

		OMElement method = fac.createOMElement("pushInfo", namespace);
		OMElement value = fac.createOMElement("symbol", namespace);

		value.addChild(fac.createOMText(value, buildCommunicationResourceAsString(rsrc)));
		method.addChild(value);

		return method;
	}
	
	public static String buildCommunicationResourceAsString(CommunicationResourceVo rsrc) {
		String string = "";
		string += "id," + rsrc.getId() + ",";
		string += "localId," + rsrc.getLocalId() + ",";
		string += "title," + rsrc.getTitle() + ",";
		string += "type," + rsrc.getType() + ",";
		string += "digest," + rsrc.getDigest() + ",";
		string += "host," + rsrc.getHost() + ",";
		string += "hostLabel," + rsrc.getHostLabel() + ",";
		string += "ogroup," + rsrc.getOGroup() + ",";
		string += "privilege," + rsrc.getPrivilege();
		return string;
	}
	
}
