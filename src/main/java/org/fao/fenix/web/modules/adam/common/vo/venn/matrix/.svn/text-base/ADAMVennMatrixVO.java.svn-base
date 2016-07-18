package org.fao.fenix.web.modules.adam.common.vo.venn.matrix;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ADAMVennMatrixVO implements IsSerializable {
	
	// list of the ORs
	LinkedHashMap<String, String> ors;
	
	// list of the recipients 
	LinkedHashMap<String, String> recipients = new LinkedHashMap<String, String>();
	
	// for every recipient
	// code OR, coderecipient, values for the recipients
	Map<String, Map<String, ADAMVennRecipientsVO>> recipientsVO;
//	HashMap<String, ADAMVennRecipientsVO> recipientsVO;
	
	HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();

	// list of the recipients 
	LinkedHashMap<String, String> donors = new LinkedHashMap<String, String>();
	
	public LinkedHashMap<String, String> getOrs() {
		return ors;
	}

	public void setOrs(LinkedHashMap<String, String> ors) {
		this.ors = ors;
	}

	public LinkedHashMap<String, String> getRecipients() {
		return recipients;
	}

	public void setRecipients(LinkedHashMap<String, String> recipients) {
		this.recipients = recipients;
	}

	public Map<String, Map<String, ADAMVennRecipientsVO>> getRecipientsVO() {
		return recipientsVO;
	}

	public void setRecipientsVO(
			Map<String, Map<String, ADAMVennRecipientsVO>> recipientsVO) {
		this.recipientsVO = recipientsVO;
	}

	public HashMap<String, ADAMVennRecipientsVO> getRecipientsVOHM() {
		return recipientsVOHM;
	}

	public void setRecipientsVOHM(
			HashMap<String, ADAMVennRecipientsVO> recipientsVOHM) {
		this.recipientsVOHM = recipientsVOHM;
	}

	public LinkedHashMap<String, String> getDonors() {
		return donors;
	}

	public void setDonors(LinkedHashMap<String, String> donors) {
		this.donors = donors;
	}

	
	
	
	

}
