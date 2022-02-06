package in.geekster.springsecuritydemo.dtos;

import in.geekster.springsecuritydemo.enums.AccountStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserPrincipal implements UserDetails {

    private final UserDTO userDTO;

    public UserPrincipal(final UserDTO userDTO) {
        this.userDTO = userDTO;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userDTO.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return !AccountStatus.INACTIVE.equals(userDTO.getStatus());
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return !AccountStatus.SUSPENDED.equals(userDTO.getStatus());
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return AccountStatus.ACTIVE.equals(userDTO.getStatus());
        return true;
    }
}
