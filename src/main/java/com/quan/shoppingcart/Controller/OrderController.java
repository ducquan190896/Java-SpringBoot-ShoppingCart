package com.quan.shoppingcart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.quan.shoppingcart.Entity.Order2;
import com.quan.shoppingcart.Service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Order2>> getOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<List<Order2>> getOrdersByAccount(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrdersByAccount(id), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Order2> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @PostMapping("/placeOrder/{id}")
    public ResponseEntity<Object> PlaceOrder(@PathVariable Long id) {
        
        return new ResponseEntity<>(orderService.placeOrderFromCart(id), HttpStatus.CREATED);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Order2> updateOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.updateOrder(id), HttpStatus.OK);
    }
}
