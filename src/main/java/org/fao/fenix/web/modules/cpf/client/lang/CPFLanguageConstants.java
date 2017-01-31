package org.fao.fenix.web.modules.cpf.client.lang;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

public interface CPFLanguageConstants  extends ConstantsWithLookup {

	@DefaultStringValue("Country Programming Framework (CPF)")
	public String countryProgrammingFramework();
	
	@DefaultStringValue("Country Programming Framework (CPF) Management Tool")
	public String countryProgrammingFrameworkManagementTool();
	
	@DefaultStringValue("HOME")
	public String HOME();

	@DefaultStringValue("CREATE CPF RESULTS MATRIX")
	public String CREATE_CPF_RESULTS_MATRIX();
	
	@DefaultStringValue("FIND CPF")
	public String FIND_CPF();
	
	@DefaultStringValue("Username")
	public String username();
	
	@DefaultStringValue("Password")
	public String password();
	
	@DefaultStringValue("Log In")
	public String logIn();
	
	@DefaultStringValue("Log Out")
	public String logOut();
	
	@DefaultStringValue("Welcome")
	public String welcome();
	
	@DefaultStringValue("click to clear message")
	public String clickToClearMessage();
	
	@DefaultStringValue("Your user account is inactive. Please contact the CPF Administrator to include you into a user group.")
	public String userGroupDisabledException();
	
	@DefaultStringValue("This username does not exist in the system.")
	public String usernameNotFoundException();

	@DefaultStringValue("The password is not correct.")
	public String userBadCredentialsException();
	
	@DefaultStringValue("The username and/or password is not correct.")
	public String userAuthenticationException();

}