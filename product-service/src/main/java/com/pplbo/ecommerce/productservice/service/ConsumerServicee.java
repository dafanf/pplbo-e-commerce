package com.pplbo.ecommerce.productservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplbo.ecommerce.productservice.event.OrderCreatedEvent;
import com.pplbo.ecommerce.productservice.dto.dtoorder.OrderLineItemResponse;
import com.pplbo.ecommerce.productservice.service.InventoryService;
import com.pplbo.ecommerce.productservice.service.KafkaProducerService;
import com.pplbo.ecommerce.productservice.dto.dtoorder.OrderResponse;
import com.pplbo.ecommerce.productservice.model.Inventory;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServicee {
    private final InventoryService inventoryService;
    private final KafkaProducerService kafkaProducerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "OrderCreateEvent", groupId = "group_id")
    public void consumeOrderEvent(String message) {
        try {
            OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);
            handleOrderCreatedEventOrder(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOrderCreatedEventOrder(OrderCreatedEvent event) {
        boolean instock = true;

        List<OrderLineItemResponse> orderLineItems = event.getOrder().orderLineItems();
        String orderStatus = event.getOrder().orderStatus();
        OrderResponse updatedOrder;

        //Default order status is Success
        updatedOrder = event.getOrder().withOrderStatus("Success");

        // Check if order is in processing state
        if (orderStatus.equals("Processing")) {
            for (OrderLineItemResponse orderLineItem : orderLineItems) {
                if (!inventoryService.checkStok(orderLineItem.productId(), orderLineItem.quantity())) {
                    instock = false;
                    break;
                }
            }
            //Cek stok, jika stok cukup maka order berhasil dan stok berkurang
            if (instock) {
                for (OrderLineItemResponse orderLineItem : orderLineItems) {
                    inventoryService.decreaseInventory(orderLineItem.productId(), orderLineItem.quantity());
                }
                updatedOrder = event.getOrder().withOrderStatus("Success");
            } else {
                updatedOrder = event.getOrder().withOrderStatus("Failed");
            }
        }else if (orderStatus.equals("Failed")) { //Jika order gagal, maka stok dikembalikan
            for (OrderLineItemResponse orderLineItem : orderLineItems) {
                Inventory existInventory = inventoryService.getInventoryByProductId(orderLineItem.productId());
                inventoryService.recoverInventory(existInventory, orderLineItem.quantity());
            }
            updatedOrder = event.getOrder().withOrderStatus("Failed");
        }

        //Publish event 
        kafkaProducerService.sendUserCreatedEvent(new OrderCreatedEvent(updatedOrder));
    }
}
