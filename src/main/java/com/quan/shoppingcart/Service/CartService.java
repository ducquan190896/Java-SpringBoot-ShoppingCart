package com.quan.shoppingcart.Service;

import java.util.List;

import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Request.CartRequest;

public interface CartService {
    Cart getCartByAccount(Long accountid);
    Cart getCart(Long id);
    List<Cart> getCarts();
    Cart addItemToCart(CartRequest cartRequest);
    Cart removeItemFromCart(CartRequest cartRequest);
    void clearAllItems(Long AccountId);
}
