package com.quan.shoppingcart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Cart;
import com.quan.shoppingcart.Entity.Role;
import com.quan.shoppingcart.Entity.Request.AccountRequest;
import com.quan.shoppingcart.Exception.EntityNotFoundException;
import com.quan.shoppingcart.Repository.AccountRepos;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    AccountRepos accountRepos;

    @Override
    public void createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        if(accountRepos.findAccountByUsername(accountRequest.getUsername()).isPresent()) {
            throw new EntityNotFoundException("the account with username  " + accountRequest.getUsername() + " alredy exits");
        }
        account.setUsername(accountRequest.getUsername());
        if(accountRequest.getPassword().length() <4 || !accountRequest.getPassword().equals(accountRequest.getConfirmedpassword()) ) {
            throw new EntityNotFoundException("the password dont match");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(accountRequest.getPassword()));
        Cart cart = new Cart();
        account.setCart(cart);
        account.setRole(Role.USER);
        accountRepos.save(account);
        
        
    }

    @Override
    public void deleteAccount(Long id) {
           Optional<Account>  entity = accountRepos.findById(id);
        Account account = checkAccount(entity, id);
        accountRepos.delete(account);
        
    }

    @Override
    public Account getAccount(String username) {
       Optional<Account>  entity = accountRepos.findAccountByUsername(username);
       Account account = checkAccount(entity, 404L);
       return account;

    }

    @Override
    public List<Account> getAccounts() {
      return accountRepos.findAll();
    }

    @Override
    public Account updateAccount(Long id, AccountRequest accountRequest) {
        Optional<Account>  entity = accountRepos.findById(id);
        Account account = checkAccount(entity, id);

        account.setUsername(null);

        if(accountRequest.getPassword().length() <4 || !accountRequest.getPassword().equals(accountRequest.getConfirmedpassword()) ) {
            throw new EntityNotFoundException("the password dont match");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(accountRequest.getPassword()));
      return  accountRepos.save(account);
    }

    private Account checkAccount(Optional<Account> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the account with id " + id + " not found");
    }

    
}
