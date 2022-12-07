package com.quan.shoppingcart.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Item;
import com.quan.shoppingcart.Entity.Request.CartRequest;
import com.quan.shoppingcart.Exception.EntityNotFoundException;
import com.quan.shoppingcart.Repository.AccountRepos;
import com.quan.shoppingcart.Repository.CartRepos;
import com.quan.shoppingcart.Repository.ItemRepos;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CartRepos cartRepos;
    @Autowired
    AccountRepos accountRepos;
    @Autowired
    ItemRepos itemRepos;

    @Override
    public Cart addItemToCart(CartRequest cartRequest) {
        Optional<Account> entityAccount = accountRepos.findById(cartRequest.getAccountid());
        if(!entityAccount.isPresent()) {
            throw new EntityNotFoundException("the account with id " + cartRequest.getAccountid() + " not found");
        }
        Account account = entityAccount.get();
        Cart cart = account.getCart();

        Optional<Item> entityItem = itemRepos.findById(cartRequest.getItemid());
        if(!entityItem.isPresent()) {
            throw new EntityNotFoundException("the account with id " + cartRequest.getItemid() + " not found");
        }
        Item item = entityItem.get();
        if(item.getQuantity() < cartRequest.getAmount()) {
            throw new EntityNotFoundException("the available number of items in storage is less than the number from cart request");
        } 
        for(int i = 1; i <= cartRequest.getAmount(); i++) {
           
            cart.addItem(item);
            item.setQuantity(item.getQuantity() - 1);
        }
        itemRepos.save(item);
       return cartRepos.save(cart);

    }

    @Override
    public Cart getCart(Long id) {
        Optional<Cart> entity = cartRepos.findById(id);
        Cart cart = checkCart(entity, id);
        return cart;
    }

    @Override
    public Cart getCartByAccount(Long accountid) {
        Optional<Account> entityAccount = accountRepos.findById(accountid);
        if(!entityAccount.isPresent()) {
            throw new EntityNotFoundException("the account with id " + accountid + " not found");
        }
        Optional<Cart> entity = cartRepos.findCartByAccount(entityAccount.get());
        Cart cart = checkCart(entity, 404L);
        return cart;
    }
    

    @Override
    public List<Cart> getCarts() {
      return cartRepos.findAll();
    }

    @Override
    public Cart removeItemFromCart(CartRequest cartRequest) {
        Optional<Account> entityAccount = accountRepos.findById(cartRequest.getAccountid());
        if(!entityAccount.isPresent()) {
            throw new EntityNotFoundException("the account with id " + cartRequest.getAccountid() + " not found");
        }
        Account account = entityAccount.get();
        Cart cart = account.getCart();

        Optional<Item> entityItem = itemRepos.findById(cartRequest.getItemid());
        if(!entityItem.isPresent()) {
            throw new EntityNotFoundException("the account with id " + cartRequest.getItemid() + " not found");
        }
        Item item = entityItem.get();
  
        for(int i = 1; i <= cartRequest.getAmount(); i++) {
            cart.removeItem(item);;
            item.setQuantity(item.getQuantity() + 1);
        }
        itemRepos.save(item);
       return cartRepos.save(cart);
    }

    private Cart checkCart(Optional<Cart> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the cart with id " + id + " not found");
    }

    @Override
    public void clearAllItems(Long AccountId) {
        Optional<Account> entityAccount = accountRepos.findById(AccountId);
        if(!entityAccount.isPresent()) {
            throw new EntityNotFoundException("the account with id " + AccountId + " not found");
        }
        Account account = entityAccount.get();
        Cart cart = account.getCart();
        cart.setItems(new ArrayList<>());
        cartRepos.save(cart);
        accountRepos.save(account);

        
    }
    
    
}
