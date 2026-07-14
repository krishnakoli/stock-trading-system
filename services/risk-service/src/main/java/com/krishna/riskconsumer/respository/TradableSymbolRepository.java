package com.krishna.riskconsumer.respository;

import com.krishna.riskconsumer.event.OrderPlacedEvent;
import com.krishna.riskconsumer.model.TradeableSymbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradableSymbolRepository extends JpaRepository<TradeableSymbol,Long> {

    boolean existsBySymbolAndActiveTrue(String symbol);

}
