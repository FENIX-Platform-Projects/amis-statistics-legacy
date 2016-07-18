package org.fao.fenix.web.modules.r.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class RException extends Exception implements IsSerializable {

	public RException() {
		super();
	}
	
	public RException(String message) {
		super(message);
	}
	
}