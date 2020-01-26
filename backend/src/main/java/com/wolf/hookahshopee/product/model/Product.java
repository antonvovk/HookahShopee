package com.wolf.hookahshopee.product.model;

import com.wolf.hookahshopee.manufacturer.model.Manufacturer;
import com.wolf.hookahshopee.order.model.OrderItem;
import com.wolf.hookahshopee.productreservation.model.ProductReservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "UUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "NAME", columnDefinition = "NVARCHAR(50)", unique = true, nullable = false)
    private String name;

    @Column(name = "PRICE", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    @Column(name = "DISCOUNT", columnDefinition = "BIGINT", nullable = false)
    private Long discount;

    @Column(name = "FINAL_PRICE", columnDefinition = "BIGINT", nullable = false)
    private Long finalPrice;

    @Column(name = "NUMBER_OF_SALES", columnDefinition = "BIGINT", nullable = false)
    private Long numberOfSales;

    @Column(name = "HTML_CONTENT", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String htmlContent;

    @Column(name = "IMAGE_NAME", columnDefinition = "NVARCHAR(50)")
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANUFACTURER_ID", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<ProductItem> productItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderItem> orderItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductReservation> productReservations;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
