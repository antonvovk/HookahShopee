package com.wolf.hookahshopee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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

    @Column(name = "UUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "QUANTITY", columnDefinition = "BIGINT", nullable = false)
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    public void addReservation(Long quantity) {
        this.quantity += quantity;
    }

    public void removeReservation(Long quantity) {
        this.quantity -= quantity;
    }

    public void clearReservation() {
        this.quantity = 0L;
    }
}
