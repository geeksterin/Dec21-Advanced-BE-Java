package in.geekster.springsecuritydemo.services.impls;

import in.geekster.springsecuritydemo.dtos.UserDTO;
import in.geekster.springsecuritydemo.dtos.UserPrincipal;
import in.geekster.springsecuritydemo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.debug("Fetching User by username: {}", username);
        final UserDTO userDTO = userService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
        log.debug("Retrieved User: {}", userDTO);
        return new UserPrincipal(userDTO);
    }
}
