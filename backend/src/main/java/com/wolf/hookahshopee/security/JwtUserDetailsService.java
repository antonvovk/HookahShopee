package com.wolf.hookahshopee.security;

import com.wolf.hookahshopee.exception.EntityNotFoundException;
import com.wolf.hookahshopee.model.User;
import com.wolf.hookahshopee.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "username", username);
        }

        return JwtUserFactory.create(user);
    }
}
