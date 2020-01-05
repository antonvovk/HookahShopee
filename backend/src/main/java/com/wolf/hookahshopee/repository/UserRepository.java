package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleIn(List<Role> roles);

    List<User> findAllByCity(City city);

    List<User> findAllByCityAndRoleIn(City city, List<Role> roles);
}
