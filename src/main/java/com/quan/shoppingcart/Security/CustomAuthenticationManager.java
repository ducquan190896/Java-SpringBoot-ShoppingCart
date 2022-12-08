package com.quan.shoppingcart.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Repository.AccountRepos;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    AccountRepos accountRepos;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        System.out.println(username);
        Optional<Account> entity = accountRepos.findAccountByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the account username " + username + " not found");
        }
        Account account = entity.get();
        
        String encodedPassword = new BCryptPasswordEncoder().encode(authentication.getCredentials().toString());
        System.out.println(authentication.getCredentials());
        boolean isCorrect = new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(), account.getPassword());
        if(!isCorrect) {
            throw new BadCredentialsException("the password provided is wrong");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getRole()));
        Authentication authentication2 = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), authorities);
        
        System.out.println(account);
        return authentication2;
    }
    
}
