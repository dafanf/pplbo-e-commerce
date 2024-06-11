package com.pplbo.ecommerce.cart.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pplbo.ecommerce.cart.service.dto.CartProductRequest;
import com.pplbo.ecommerce.cart.service.dto.CartRequest;
import com.pplbo.ecommerce.cart.service.dto.ProductRequest;
import com.pplbo.ecommerce.cart.service.dto.ProductResponse;
import com.pplbo.ecommerce.cart.service.error.ResourceNotFoundException;
import com.pplbo.ecommerce.cart.service.model.Cart;
import com.pplbo.ecommerce.cart.service.model.Product;
import com.pplbo.ecommerce.cart.service.service.CartService;
import com.pplbo.ecommerce.cart.service.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCart(@RequestBody CartRequest cart) {
        return cartService.createCart(cart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

    @PostMapping("/{cartId}/products/{productId}")
    public Cart addProductToCart(@PathVariable Long cartId, @PathVariable Integer productId,
            @RequestParam Integer quantity) {
        return cartService.addProductToCart(cartId, productId, quantity);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public Cart removeProductFromCart(@PathVariable Long cartId, @PathVariable Integer productId,
            @RequestParam Integer quantity) {
        Cart cart = cartService.removeProductFromCart(cartId, productId, quantity);
        return cart;
    }
}
