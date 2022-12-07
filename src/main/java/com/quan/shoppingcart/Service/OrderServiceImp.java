package com.quan.shoppingcart.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Item;
import com.quan.shoppingcart.Entity.Order;
import com.quan.shoppingcart.Entity.Order2;
import com.quan.shoppingcart.Exception.EntityNotFoundException;
import com.quan.shoppingcart.Repository.AccountRepos;
import com.quan.shoppingcart.Repository.CartRepos;
import com.quan.shoppingcart.Repository.OrderRepos;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepos orderRepos;
    @Autowired
    AccountRepos accountRepos;
    @Autowired
    CartRepos cartRepos;

    @Override
    public void deleteOrder(Long orderId) {
      Optional<Order2> entity = orderRepos.findById(orderId);
      if(!entity.isPresent()) {
        
        throw new EntityNotFoundException("the order with id " + orderId + " not found");
      }
      Order2 order = entity.get();
        orderRepos.delete(order);
    }

    @Override
    public List<Order2> getOrders() {
      return orderRepos.findAll();
    }

    @Override
    public List<Order2> getOrdersByAccount(Long accountId) {
      Optional<Account> entity = accountRepos.findById(accountId);
      if(!entity.isPresent()) {
        throw new EntityNotFoundException("the account with id " + accountId + " not found" );

      }
      Account account = entity.get();
      List<Order2> orders = orderRepos.findOrderByAccount(account);
      return orders;
    }

    @Override
    public Order2 placeOrderFromCart(Long accountId) {
        Optional<Account> entity = accountRepos.findById(accountId);
        if(!entity.isPresent()) {
          throw new EntityNotFoundException("the account with id " + accountId + " not found" );
  
        }
        
        Order2 order = new Order2();
        Account account = entity.get();
        Cart cart = account.getCart();
        order.setAccount(account);
        order.setTotalprice(cart.getTotalprice());
       List<Item> items = cart.getItems();
        List<Item> itemsOrder = new ArrayList<>();
        if(items.size() > 0) {
            items.stream().forEach(ite -> itemsOrder.add(ite));
        }
        order.setOrderItems(itemsOrder);
       return orderRepos.save(order);
   
    }

    @Override
    public Order2 getOrder(Long id) {
       Optional<Order2> entity = orderRepos.findById(id);
       if(!entity.isPresent()) {
        throw new EntityNotFoundException("the order with id " + id + " not found");
       }
       return entity.get();
    }

    @Override
    public Order2 updateOrder(Long orderId) {
        Optional<Order2> entity = orderRepos.findById(orderId);
        if(!entity.isPresent()) {
          
          throw new EntityNotFoundException("the order with id " + orderId + " not found");
        }
        Order2 order = entity.get();

        Account account = order.getAccount();

        Cart cart = account.getCart();
        order.setAccount(account);
        order.setTotalprice(cart.getTotalprice());
       List<Item> items = cart.getItems();
        List<Item> itemsOrder = new ArrayList<>();
        if(items.size() > 0) {
            items.stream().forEach(ite -> itemsOrder.add(ite));
        }
        order.setOrderItems(itemsOrder);
       return orderRepos.save(order);
    }
    
}
