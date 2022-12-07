package com.quan.shoppingcart.Entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;



@Table(name = "account")
@Entity(name = "Account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @SequenceGenerator(
        name = "account_sequence",
        sequenceName = "account_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "account_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    
    @NotBlank(message = "username cannot be blank")
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Order2> orders;

    public Account( String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Account( String username, String password, Cart cart) {
        this.username = username;
        this.password = password;
        this.cart = cart;
    }


    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    
}
