package com.krishna.stockordersystem.model;

import com.krishna.stockordersystem.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;   // business id (UUID style):

    @NotBlank
    private String clientId;

    private String symbol;

    private Integer quantity;

    private Double price;

    private String orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}