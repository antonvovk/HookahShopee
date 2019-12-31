package com.wolf.hookahshopee.security;

import com.wolf.hookahshopee.model.Client;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.model.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    public static JwtUser create(Seller seller) {
        return new JwtUser(
                seller.getPhoneNumber(),
                seller.getFirstName(),
                seller.getLastName(),
                seller.getPassword(),
                mapToGrantedAuthorities(seller.getRole()),
                true,
                null
        );
    }

    public static JwtUser create(Client client) {
        return new JwtUser(
                client.getPhoneNumber(),
                client.getFirstName(),
                client.getLastName(),
                client.getPassword(),
                mapToGrantedAuthorities(client.getRole()),
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
