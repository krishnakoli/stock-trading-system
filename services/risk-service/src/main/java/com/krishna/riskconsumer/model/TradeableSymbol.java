package com.krishna.riskconsumer.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="tradable_symbols")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeableSymbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String exchange;

    private Boolean active;

}
