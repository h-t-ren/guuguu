package cn.ecust.bs.guuguu.service;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-26
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.ecust.bs.guuguu.domain.User;

public class GuuGuuUserDetailsService implements UserDetailsService {

	@Autowired
	private Neo4jOperations template;

	@Override
	public GuuGuuUserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException {

		final User user = findByLogin(login);
		if (user == null)
			throw new UsernameNotFoundException("Username not found: " + login);
		return new GuuGuuUserDetails(user);
	}

	private User findByLogin(String login) {
		return template.lookup(User.class, "login", login).to(User.class)
				.single();
	}

	public User getUserFromSession() {

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof GuuGuuUserDetails) {
			GuuGuuUserDetails userDetails = (GuuGuuUserDetails) principal;
			return userDetails.getUser();
		}
		return null;

	}

	public void setUserInSession(User user) {
		SecurityContext context = SecurityContextHolder.getContext();
		GuuGuuUserDetails userDetails = new GuuGuuUserDetails(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, user.getPassword(), userDetails.getAuthorities());
		context.setAuthentication(authentication);

	}

}