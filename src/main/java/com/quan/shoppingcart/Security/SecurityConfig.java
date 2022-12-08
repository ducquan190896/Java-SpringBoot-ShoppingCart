package com.quan.shoppingcart.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.quan.shoppingcart.Security.Filter.FilterAuthentication;
import com.quan.shoppingcart.Security.Filter.FilterException;
import com.quan.shoppingcart.Security.Filter.FilterJwtAuthorization;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

        @Autowired 
        CustomAuthenticationManager customAuthenticationManager;
        @Autowired
        FilterException filterException;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        FilterAuthentication filterAuthentication = new FilterAuthentication(customAuthenticationManager);
        filterAuthentication.setFilterProcessesUrl("/login");

        http.csrf().disable()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.POST, "/api/account/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(filterException, filterAuthentication.getClass())
            .addFilter(filterAuthentication)
            .addFilterAfter(new FilterJwtAuthorization(), filterAuthentication.getClass())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
