package org.fao.fenix.web.modules.core.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gwtwidgets.server.spring.ServletUtils;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.HttpSessionContextIntegrationFilter;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.logout.LogoutHandler;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;

public class FenixLogin {

	private LogoutHandler logoutHandler;
	private AuthenticationManager authenticationManager;
	private UserDetailsManager userDetailsManager;

	
	
	
	public boolean login(String username, String password) {
		UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
		Authentication authRequest = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password);
		Authentication auth = authenticationManager.authenticate(authRequest);
		SecurityContextHolder.getContext().setAuthentication(auth);
		System.out.println(ServletUtils.getRequest());
		ServletUtils.getRequest().getSession().setAttribute(HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		return auth.isAuthenticated();
	}

	public void logout(HttpServletRequest HttpServletResponse, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logoutHandler.logout(HttpServletResponse, resp, auth);
	}

	public boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.isAuthenticated();
	}

	public boolean hasRole(String roleName) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		GrantedAuthority[] ga = auth.getAuthorities();
		for (int i = 0; i < ga.length; i++) {
			if (ga.equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	public void setLogoutHandler(LogoutHandler logoutHandler) {
		this.logoutHandler = logoutHandler;
	}

	public void setUserDetailsManager(UserDetailsManager userDetailsManager) {
		this.userDetailsManager = userDetailsManager;
	}




}