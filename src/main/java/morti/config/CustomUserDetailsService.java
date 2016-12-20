package morti.config;


import morti.data.Role;
import morti.dto.CustomUserDetails;
import morti.dto.User;
import morti.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {

    private Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class);

    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByEmail(email);
            if (user == null) {
                LOGGER.debug("user not found with the provided username");
                return null;
            }
            LOGGER.debug(" user from username " + user.toString());
            return convertToUser(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private CustomUserDetails convertToUser(User user) {

        Set<GrantedAuthority> authorities = getAuthorities(user);

        CustomUserDetails customUser =
                new CustomUserDetails(user.getEmail(), user.getUserId(), user.getPassword(), authorities);
        return customUser;
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role: user.getRole()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.toString());
            authorities.add(grantedAuthority);
        }
        LOGGER.debug("user authorities are " + authorities.toString());
        return authorities;
    }
}
