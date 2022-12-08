package com.quan.shoppingcart.Security.Filter;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.shoppingcart.Entity.Account;
import com.quan.shoppingcart.Security.CustomAuthenticationManager;
import com.quan.shoppingcart.Security.SecurityConstant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterAuthentication extends UsernamePasswordAuthenticationFilter {

    @Autowired 
    CustomAuthenticationManager customAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
            System.out.println(account);
        Authentication authentication = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
        return customAuthenticationManager.authenticate(authentication);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                System.out.println(authResult.getName() + " successfully authenticate");
       String token = JWT
       .create()
       .withSubject(authResult.getName())
       .withClaim("authorities", authResult.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList()))
       .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.expire_token) )
       .sign(Algorithm.HMAC512(SecurityConstant.private_key));
        
       System.out.println("token : " + token);
       response.addHeader("Authorization", SecurityConstant.authorization + token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}
