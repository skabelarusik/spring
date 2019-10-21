package by.epam.crackertracker.config;

import by.epam.crackertracker.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user){
        super();
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(takeRole(user.getRole().name().toUpperCase())));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    public String getRole(){
        return user.getRole().name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private String takeRole(String roleParam){
        String role;
        switch (roleParam){
            case "ADMIN":
                role = "ROLE_ADMIN";
                break;
            case "USER":
                role = "ROLE_USER";
                break;
            case "SUPERUSER":
                role = "ROLE_SUPERUSER";
                break;
            case "CURATOR":
                role = "ROLE_CURATOR";
                break;
                default: role = "ROLE_GUEST";
        }
        return role;
    }
}
