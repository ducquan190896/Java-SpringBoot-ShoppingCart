package com.quan.shoppingcart.Service;

import java.util.List;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Order;
import com.quan.shoppingcart.Entity.Order2;

public interface OrderService {
    List<Order2> getOrders();
    List<Order2> getOrdersByAccount(Long accountId);
    Order2 getOrder(Long id);
    Order2 placeOrderFromCart(Long accountId);
    Order2 updateOrder(Long orderId);
    void deleteOrder(Long orderId);
}
