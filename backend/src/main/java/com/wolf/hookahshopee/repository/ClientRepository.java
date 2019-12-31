package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.City;
import com.wolf.hookahshopee.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByPhoneNumber(String phoneNumber);

    List<Client> findAllByCity(City city);
}
