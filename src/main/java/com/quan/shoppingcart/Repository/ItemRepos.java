package com.quan.shoppingcart.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Item;

@Repository
public interface ItemRepos extends JpaRepository<Item, Long> {
    @Query(
        value = "select * from item where productcode = ?1",
        nativeQuery = true
    )
    Optional<Item> findItemByProductcode(String productcode);
   

    List<Item> findItemByCarts(Cart cart);
}
