package com.quan.shoppingcart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Entity.Request.AccountRequest;
import com.quan.shoppingcart.Service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Account> getAccount(@PathVariable String username) {
        return new ResponseEntity<Account>(accountService.getAccount(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> Register(@RequestBody AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<Account>(accountService.updateAccount(id, accountRequest), HttpStatus.OK);
    }

}
