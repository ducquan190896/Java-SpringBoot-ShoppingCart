package com.quan.shoppingcart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Order2;

@Repository
public interface OrderRepos extends JpaRepository<Order2, Long> {
    List<Order2> findOrderByAccount(Account account);
}
