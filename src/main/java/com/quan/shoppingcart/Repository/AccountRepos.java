package com.quan.shoppingcart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.shoppingcart.Entity.Account;

@Repository
public interface AccountRepos extends JpaRepository<Account, Long> {
    @Query(
        value = "select * from account where username = ?1",
        nativeQuery = true
    )
    Optional<Account> findAccountByUsername(String username);
    
}
