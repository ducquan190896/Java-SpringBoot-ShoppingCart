package com.quan.shoppingcart.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Item;

@Repository
public interface CartRepos extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByAccount(Account account);
    List<Cart> findCartByItems(Item item);
}
