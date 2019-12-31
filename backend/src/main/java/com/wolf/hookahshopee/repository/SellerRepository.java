package com.wolf.hookahshopee.repository;

import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.model.Seller;
import com.wolf.hookahshopee.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByPhoneNumber(String phoneNumber);

    List<Seller> findAllByRole(Role role);

    List<Seller> findAllByShop(Shop shop);
}
