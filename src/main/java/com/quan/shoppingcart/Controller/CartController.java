package com.quan.shoppingcart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Request.CartRequest;
import com.quan.shoppingcart.Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    CartService cartService;

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getCarts() {
        return new ResponseEntity<List<Cart>>(cartService.getCarts(), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<Cart> getCartByAccount(@PathVariable Long accountId) {
        return new ResponseEntity<Cart>(cartService.getCartByAccount(accountId), HttpStatus.OK);
    }

    @PutMapping("/addItem")
    public ResponseEntity<Cart> addItem(@RequestBody CartRequest cartRequest) {
        return new ResponseEntity<Cart>(cartService.addItemToCart(cartRequest), HttpStatus.OK);
    }
    @PutMapping("/removeItem")
    public ResponseEntity<Cart> removeItem(@RequestBody CartRequest cartRequest) {
        return new ResponseEntity<Cart>(cartService.removeItemFromCart(cartRequest), HttpStatus.OK);
    }

    @PutMapping("/clearAllItems/account/{accountId}")
    public ResponseEntity<HttpStatus> clearAllItems(@PathVariable Long accountId) {
        cartService.clearAllItems(accountId);;
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
