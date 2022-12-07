package com.quan.shoppingcart.Service;

import java.util.List;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Request.AccountRequest;

public interface AccountService {
    Account getAccount(String username);
    List<Account> getAccounts();
    void createAccount(AccountRequest accountRequest);
    Account updateAccount(Long id, AccountRequest accountRequest);
    void deleteAccount(Long id);
}
