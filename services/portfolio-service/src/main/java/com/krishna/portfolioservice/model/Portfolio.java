package com.krishna.portfolioservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "portfolio")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private String symbol;

    private Integer quantity;

    private Double averagePrice;
}