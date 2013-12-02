package net.onlineconsultations.security;

import java.util.Collections;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HibernateUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	User user = this.userDAO.getByUsername(username);

	if (user == null) {
	    throw new UsernameNotFoundException("No such user with username = "
		    + username);
	}

	return new org.springframework.security.core.userdetails.User(
		user.getUsername(), user.getPassword(),
		Collections.singletonList(new SimpleGrantedAuthority(user
			.getRole().getRole())));
    }

}
