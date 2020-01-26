package com.wolf.hookahshopee.productreservation.model;

import com.wolf.hookahshopee.city.model.City;
import com.wolf.hookahshopee.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "PRODUCT_RESERVATION")
public class ProductReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "QUANTITY", columnDefinition = "BIGINT", nullable = false)
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    public void addReservation(Long quantity) {
        this.quantity += quantity;
    }

    public void removeReservation(Long quantity) {
        if (this.quantity - quantity >= 0) {
            this.quantity -= quantity;
        }
    }
}
