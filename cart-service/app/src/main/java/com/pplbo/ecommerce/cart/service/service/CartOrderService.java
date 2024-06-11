package com.pplbo.ecommerce.cart.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pplbo.ecommerce.cart.service.dto.ProductPromoted;
import com.pplbo.ecommerce.cart.service.dto.ShippingRequest;
import com.pplbo.ecommerce.cart.service.model.Cart;
import com.pplbo.ecommerce.cart.service.model.CartOrder;
import com.pplbo.ecommerce.cart.service.model.LineItem;
import com.pplbo.ecommerce.cart.service.model.Product;
import com.pplbo.ecommerce.cart.service.model.ProductToBuy;
import com.pplbo.ecommerce.cart.service.model.Shipping;

@Service
public class CartOrderService {
    @Autowired
    CartService cartService;

    ShippingService shippingService = new ShippingService();

    private RestTemplate restTemplate = new RestTemplate();

    private final String PROMOTION_API_URL = "http://localhost:8085/api/promotions/";

    public CartOrder createOrder(Long customerId, ShippingRequest shipping) {
        Cart cart = cartService.getCartById(customerId);
        Long totalPrice = (long) 0;
        List<LineItem> lineItems = new ArrayList<>();
        for (ProductToBuy product : cart.getProductsToBuy()) {
            // fetch product promotion
            ResponseEntity<ProductPromoted> productPromoted = restTemplate.getForEntity(
                    PROMOTION_API_URL + "products/" + product.getId(),
                    ProductPromoted.class);
            Long productId = productPromoted.getBody().productId();
            Long productIdBonus = productPromoted.getBody().bonusProductId();
            if (productId == productIdBonus) {
                lineItems.add(new LineItem(productId, 2));
                totalPrice += product.getTotalProductPrice() * 2;
            } else if (productIdBonus != null) {
                lineItems.add(new LineItem(productId, 1));
                totalPrice += product.getTotalProductPrice();
                lineItems.add(new LineItem(productIdBonus, 1));
            }
        }
        // TODO fetch shipping promotion
        double shippingDiscount = 0.10;
        Double shippingPrice = 10000 * shippingDiscount;
        return CartOrder.builder().customerId(customerId).orderStatus("PENDING")
                .shipping(shippingService.saveShipping(shipping, shippingPrice))
                .lineItems(lineItems).build();
    }
}
