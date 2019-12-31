package com.wolf.hookahshopee.security;

import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.model.Client;
import com.wolf.hookahshopee.model.Seller;
import com.wolf.hookahshopee.repository.ClientRepository;
import com.wolf.hookahshopee.repository.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final SellerRepository sellerRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public JwtUserDetailsService(SellerRepository sellerRepository, ClientRepository clientRepository) {
        this.sellerRepository = sellerRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepository.findByPhoneNumber(username).orElse(null);

        if (seller == null) {
            Client client = clientRepository.findByPhoneNumber(username).orElse(null);

            if (client == null) {
                throw new EntityNotFoundException(JwtUser.class, "username", username);
            }

            return JwtUserFactory.create(client);
        }

        return JwtUserFactory.create(seller);
    }
}
