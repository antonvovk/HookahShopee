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

@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(name = "PRICE", columnDefinition = "INT", nullable = false)
    private Integer price;

    @Column(name = "DISCOUNT", columnDefinition = "INT", nullable = false)
    private Integer discount;

    @Column(name = "FINAL_PRICE", columnDefinition = "INT", nullable = false)
    private Integer finalPrice;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(250)", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANUFACTURER_ID", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<ProductItem> productItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderItem> orderItems;

    public Boolean isPromotional() {
        return discount != 0;
    }
}
