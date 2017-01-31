package org.fao.fenix.web.modules.core.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FenixUserVo implements IsSerializable {

	private String loginName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String language;
	
	private boolean isEnabled;

	/**
	 * Possible values: {ROLE_USER, ROLE_ADMINISTRATOR, ROLE_FPI , ROLE_CCBS,
	 * ROLE_IPC}
	 */
	private String[] roles;
	
	public FenixUserVo() {
		this.setEmail("john.doe@fao.org");
		this.setEnabled(true);
		this.setFirstName("John");
		this.setLastName("Doe");
		this.setLoginName("john.doe");
		this.setPassword("seven");
		this.setLanguage("EN");
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] grantedAuthorities) {
		this.roles = grantedAuthorities;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
