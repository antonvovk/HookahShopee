package com.wolf.hookahshopee.security;

import com.wolf.hookahshopee.user.model.Role;
import com.wolf.hookahshopee.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                true,
                null
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userRole.name()));
        return grantedAuthorities;
    }
}
