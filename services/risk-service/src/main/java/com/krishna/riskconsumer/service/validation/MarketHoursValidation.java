package com.krishna.riskconsumer.service.validation;

import com.krishna.riskconsumer.event.OrderPlacedEvent;
import org.springframework.stereotype.Component;

import java.time.*;


@Component
class MarketHoursValidation implements RiskValidationStrategy {


    private static final LocalTime MARKET_OPEN =
            LocalTime.of(9, 15);

    private static final LocalTime MARKET_CLOSE =
            LocalTime.of(15, 30);

    @Override
    public void validate(OrderPlacedEvent order) {

        if (!isMarketOpen()) {
            throw new RuntimeException(
                    "Market Closed");
        }
    }

    public boolean isMarketOpen() {

        LocalDate today =
                LocalDate.now(ZoneId.of("Asia/Kolkata"));

        DayOfWeek day = today.getDayOfWeek();

        if (day == DayOfWeek.SATURDAY ||
                day == DayOfWeek.SUNDAY) {
            return false;
        }

        LocalTime now =
                LocalTime.now(ZoneId.of("Asia/Kolkata"));

        return !now.isBefore(MARKET_OPEN)
                && !now.isAfter(MARKET_CLOSE);
    }
}