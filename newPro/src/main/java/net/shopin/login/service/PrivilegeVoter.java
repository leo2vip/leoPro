package net.shopin.login.service;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author liufl / 2014�?�?3�? */
public class PrivilegeVoter implements AccessDecisionVoter<Object> {

	public boolean supports(ConfigAttribute attribute) {
		if (attribute.getAttribute() != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);
		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;
				// Attempt to find a matching granted authority
				for (GrantedAuthority authority : authorities) {
					if (authority.getAuthority().startsWith(attribute.getAttribute())) {
						return ACCESS_GRANTED;
					} else {
						if (authority.getAuthority().endsWith("_ALL")) {
							String mask = authority.getAuthority().substring(0, authority.getAuthority().length() - 4);
							if (attribute.getAttribute().startsWith(mask)) {
								return ACCESS_GRANTED;
							}
						}
					}
				}
			}
		}
		return result;
	}

	Collection<? extends GrantedAuthority> extractAuthorities(
			Authentication authentication) {
		return authentication.getAuthorities();
	}

}
