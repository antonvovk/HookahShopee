package com.wolf.hookahshopee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "SELLER")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "PHONE_NUMBER", columnDefinition = "VARCHAR(50)", nullable = false)
    private String phoneNumber;

    @Column(name = "LASTNAME", columnDefinition = "VARCHAR(50)", nullable = false)
    private String firstName;

    @Column(name = "FIRSTNAME", columnDefinition = "VARCHAR(50)", nullable = false)
    private String lastName;

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(200)", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", columnDefinition = "VARCHAR(200)", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "seller")
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "seller")
    private List<ProductItem> productItems;
}
