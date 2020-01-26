package com.wolf.hookahshopee.user.repository;

import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.user.model.Role;
import com.wolf.hookahshopee.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByUuid(UUID uuid);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleIn(List<Role> roles);

    List<User> findAllByCity(City city);

    List<User> findAllByCityAndRoleIn(City city, List<Role> roles);
}
