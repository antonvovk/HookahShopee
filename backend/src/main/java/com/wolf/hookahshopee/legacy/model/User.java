package com.wolf.hookahshopee.legacy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "USR")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "UUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "PHONE_NUMBER", columnDefinition = "VARCHAR(50)", nullable = false)
    private String phoneNumber;

    @Column(name = "LASTNAME", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String firstName;

    @Column(name = "FIRSTNAME", columnDefinition = "NVARCHAR(50)", nullable = false)
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
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Order> sellerOrders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "client")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Order> clientOrders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "seller")
    private List<ProductItem> productItems;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
