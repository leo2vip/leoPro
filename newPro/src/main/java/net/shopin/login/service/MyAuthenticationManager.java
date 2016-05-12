package net.shopin.login.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/***
 * 
 * @author leo2v
 *
 */
public class MyAuthenticationManager extends ProviderManager {

	private Logger log = LoggerFactory.getLogger(getClass());

	public MyAuthenticationManager(List<AuthenticationProvider> providers) {
		super(providers);
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Authentication _temp = null;
		for (AuthenticationProvider provider : this.getProviders()) {
			_temp = provider.authenticate(authentication);
			if (_temp != null)
				break;
		}
		if (_temp == null) {
			return authentication;
		}
		String username = _temp.getName();
//		String password = _temp.getCredentials() == null ? "" : _temp.getCredentials().toString();
		if (_temp.isAuthenticated()) {
			log.info("login success:user:" + username + ", with authorities:" + _temp.getAuthorities());
		} else {
			return null;
		}
		return _temp;
	}

}
