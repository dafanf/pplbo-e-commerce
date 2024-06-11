package com.pplbo.ecommerce.cart.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplbo.ecommerce.cart.service.dto.CartProductRequest;
import com.pplbo.ecommerce.cart.service.dto.CartProductResponse;
import com.pplbo.ecommerce.cart.service.dto.CartRequest;
import com.pplbo.ecommerce.cart.service.model.Cart;
import com.pplbo.ecommerce.cart.service.model.Product;
import com.pplbo.ecommerce.cart.service.model.ProductToBuy;
import com.pplbo.ecommerce.cart.service.repository.CartRepository;
import com.pplbo.ecommerce.cart.service.repository.ProductRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductToBuyService productToBuyService;

    public Cart addProductToCart(Long cartId, Integer productId, Integer quantityToBuy) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found with ID: " + cartId));
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));

        boolean productExistsInCart = false;
        for (ProductToBuy productToBuy : cart.getProductsToBuy()) {
            if (productToBuy.getProductId().equals(productId)) {
                int newQuantityToBuy = productToBuy.getQuantityToBuy() + quantityToBuy;
                if (newQuantityToBuy > product.getQuantity()) {
                    throw new IllegalArgumentException(
                            "Not enough quantity available for product: " + product.getName());
                }
                productToBuy.setQuantityToBuy(newQuantityToBuy);
                productToBuy.setTotalProductPrice(newQuantityToBuy * product.getPrice());
                productExistsInCart = true;
                break;
            }
        }

        if (!productExistsInCart) {
            if (quantityToBuy > product.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity available for product: " + product.getName());
            }

            ProductToBuy productToBuy = ProductToBuy.builder()
                    .productId(productId)
                    .cart(cart)
                    .quantityToBuy(quantityToBuy)
                    .totalProductPrice(quantityToBuy * product.getPrice())
                    .build();
            cart.addProductToBuy(productToBuy);
        }

        // Update the total price of the cart
        long totalPrice = 0;
        for (ProductToBuy productToBuy : cart.getProductsToBuy()) {
            totalPrice += productToBuy.getTotalProductPrice();
        }
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart createCart(CartRequest cartRequest) {
        Cart cart = Cart.builder()
                .userID(cartRequest.userID())
                .totalPrice(0L) // assuming new carts start with a total price of 0
                .build();
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Cart not found with ID: " + id);
        }
    }

    public Cart removeProductFromCart(Long cartId, Integer productId, Integer quantityToRemove) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found with ID: " + cartId));
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));

        boolean productExistsInCart = false;
        ProductToBuy productToRemove = null;

        for (ProductToBuy productToBuy : cart.getProductsToBuy()) {
            if (productToBuy.getProductId().equals(productId)) {
                int newQuantityToBuy = productToBuy.getQuantityToBuy() - quantityToRemove;
                if (newQuantityToBuy <= 0) {
                    productToRemove = productToBuy; // Mark for removal
                } else {
                    productToBuy.setQuantityToBuy(newQuantityToBuy);
                    productToBuy.setTotalProductPrice(newQuantityToBuy * product.getPrice());
                }
                productExistsInCart = true;
                break;
            }
        }

        if (!productExistsInCart) {
            throw new NoSuchElementException("Product not found in the cart with ID: " + productId);
        }

        if (productToRemove != null) {
            cart.getProductsToBuy().remove(productToRemove);
        }

        // Update the total price of the cart
        long totalPrice = 0;
        for (ProductToBuy productToBuy : cart.getProductsToBuy()) {
            totalPrice += productToBuy.getTotalProductPrice();
        }
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }
}