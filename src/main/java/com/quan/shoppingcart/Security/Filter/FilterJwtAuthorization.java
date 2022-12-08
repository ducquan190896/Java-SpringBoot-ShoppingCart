package com.quan.shoppingcart.Security.Filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quan.shoppingcart.Security.SecurityConstant;

public class FilterJwtAuthorization extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith(SecurityConstant.authorization)) {
            filterChain.doFilter(request, response);
            return;
        } 
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);

        String token = authorization.replace(SecurityConstant.authorization, "");
        System.out.println("token got from header authorization: " + token);

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstant.private_key)).build().verify(token);

        String username = decodedJWT.getSubject();
        System.out.println(username);
        
       List<String> claims =  decodedJWT.getClaim("authorities").asList(String.class);
       claims.stream().forEach(clai -> System.out.println(clai));

       List<SimpleGrantedAuthority> authorities = claims.stream().map(clai -> new SimpleGrantedAuthority(clai)).collect(Collectors.toList());

       Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

       SecurityContextHolder.getContext().setAuthentication(authentication);

       filterChain.doFilter(request, response);
        } catch (JWTVerificationException ex) {
            throw new JWTCreationException(ex.getMessage(), ex);
        }
    }
}
