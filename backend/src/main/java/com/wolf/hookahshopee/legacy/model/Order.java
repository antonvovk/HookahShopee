package com.wolf.hookahshopee.legacy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "ORDR")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "UUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SELLER_ID")
    private User seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private User client;

    @Column(name = "START_DATE", columnDefinition = "DATETIME2", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", columnDefinition = "DATETIME2")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "PRICE", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "LASTNAME", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String firstName;

    @Column(name = "FIRSTNAME", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String lastName;

    @Column(name = "PHONE_NUMBER", columnDefinition = "VARCHAR(50)", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL", columnDefinition = "NVARCHAR(50)")
    private String email;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(name = "DELIVERY_CITY", columnDefinition = "NVARCHAR(50)")
    private String deliveryCity;

    @Column(name = "DELIVERY_DEPARTMENT", columnDefinition = "NVARCHAR(50)")
    private String deliveryDepartment;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
