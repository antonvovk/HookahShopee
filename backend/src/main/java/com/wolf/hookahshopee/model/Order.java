package com.wolf.hookahshopee.model;

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

    @Column(name = "START_DATE", columnDefinition = "DATETIME2", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", columnDefinition = "DATETIME2")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "PRICE", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private User client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems;
}
