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

@Entity(name = "SHOP")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "PHONE_NUMBER", columnDefinition = "VARCHAR(20)", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL", columnDefinition = "VARCHAR(30)", nullable = false)
    private String email;

    @Column(name = "SCHEDULE", columnDefinition = "VARCHAR(100)", nullable = false)
    private String schedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shop")
    private List<Seller> sellers;
}
