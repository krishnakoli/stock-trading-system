package com.krishna.notificationservice.consumer;

import com.krishna.notificationservice.event.PortfolioEvent;
import com.krishna.notificationservice.event.RiskRejectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "portfolio-update-info")
    public void handleApproved(PortfolioEvent event) {
      if(event.getStatus().equals("UPDATED")){
          log.info("Portfolio has been {} for the orderId:{} ",
                  event.getStatus(),event.getOrderId());
      }
      else{
          log.info("Portfolio {} for the orderId:{} due to:{} ",
                  event.getStatus(),event.getOrderId(),event.getMsg());
      }
    }

    @KafkaListener(topics = "risk-rejected")
    public void handleRejected(RiskRejectedEvent event) {
        log.error(
                "Order with orderId:{} get rejected due to:{}",
                event.getOrderId(),event.getMsg());
    }

}
