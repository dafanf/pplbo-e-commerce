package com.pplbo.ecommerce.productservice.sagas.createorder.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.ecommerce.productservice.sagas.createorder.event.OrderEvent;
import com.pplbo.ecommerce.productservice.dto.dtoorder.OrderLineItemResponse;
import com.pplbo.ecommerce.productservice.service.InventoryService;
import com.pplbo.ecommerce.productservice.sagas.createorder.service.ProducerService;
import com.pplbo.ecommerce.productservice.dto.dtoorder.OrderResponse;
import com.pplbo.ecommerce.productservice.model.Inventory;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final InventoryService inventoryService;
    private final ProducerService producerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "productRequestEvent", groupId = "group_id")
    public void consumeOrderEvent(String message) {
        try {
            OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
            handleOrderEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = "productCompensateEvent", groupId = "group_id")
    public void consumeOrderComposateEvent(String message) {
        try {
            OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
            handleCompesation(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOrderEvent(OrderEvent event) {
        boolean instock = true;

        List<OrderLineItemResponse> orderLineItems = event.getOrder().orderLineItems();
        String orderStatus = event.getOrder().orderStatus();

        // Check if order is in processing state
        if (orderStatus.equals("PESANAN_TERTUNDA")) {
            OrderResponse updatedOrder;
            for (OrderLineItemResponse orderLineItem : orderLineItems) {
                if (!inventoryService.checkStok(orderLineItem.productId(), orderLineItem.quantity())) {
                    instock = false;
                    break;
                }
            }
            // Cek stok, jika stok cukup maka order berhasil dan stok berkurang
            if (instock) {
                for (OrderLineItemResponse orderLineItem : orderLineItems) {
                    inventoryService.decreaseInventory(orderLineItem.productId(), orderLineItem.quantity());
                }
                updatedOrder = event.getOrder().withOrderStatus("PESANAN_DIBUAT");
            } else {
                updatedOrder = event.getOrder().withOrderStatus("PESANAN_GAGAL");
            }

            producerService.sendOrderReplyEvent(new OrderEvent(updatedOrder));
        } 
    }

    private void handleCompesation(OrderEvent event) {
        boolean instock = true;

        List<OrderLineItemResponse> orderLineItems = event.getOrder().orderLineItems();
        String orderStatus = event.getOrder().orderStatus();

        //Cek jika order gagal, maka stok dikembalikan
        if (orderStatus.equals("PESANAN_DIBATALKAN")) { 
            for (OrderLineItemResponse orderLineItem : orderLineItems) {
                Inventory existInventory = inventoryService.getInventoryByProductId(orderLineItem.productId());
                inventoryService.recoverInventory(existInventory, orderLineItem.quantity());
            }
            // producerService.sendOrderReplyEvent(new OrderEvent(event.getOrder()));
        }
    }
}
