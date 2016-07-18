package org.fao.fenix.web.modules.qb.client.util;





import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Operator extends BaseModel implements IsSerializable{

	private static final long serialVersionUID = 1L;
	
	/*public static String IS_EQUAL_TO = "=";
	public static String IS_NOT_EQUAL_TO = "<>";
	public static String IS_LESS_THAN = "<";
	public static String IS_LESS_THAN_OR_EQUAL_TO = "<=";
	public static String IS_GREATER_THAN = ">";
	public static String IS_GREATER_THAN_OR_EQUAL_TO = ">=";
	public static String BETWEEN = "between";
	public static String LIKE = "like";
	public static String IN = "in";*/
	
	public Operator() {
	}


	
	public Operator(String name, String code) {
		setName(name);
		setCode(code);
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getName() {
		return get("name");
	}
	
	public void setCode(String source) {
		set("code", source);
	}

	public String getCode() {
		return get("code");
	}

}