package com.quan.shoppingcart.Entity;

import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;



// @Table(name = "order")
// @Entity(name = "Order")
// @Getter
// @Setter
// @NoArgsConstructor
//  @AllArgsConstructor
public class Order {
    // @Id
    // @SequenceGenerator(
    //     name = "order_sequence",
    //     sequenceName = "order_sequence",
    //     allocationSize = 1
    // )
    // @GeneratedValue(
    //     strategy = GenerationType.SEQUENCE,
    //     generator = "order_sequence"
    // )
    // // @Column(name = "id", updatable = false)
    // private Long id;

    // @Min(value = 0, message = "total price of all items must be higher than equals to 0")
    // @Column(name = "totalprice", nullable = false)
    // private double totalprice;

    // @ManyToOne()
    // @JoinColumn(name = "account_id", referencedColumnName = "id")
    // private Account account;

    // @ManyToMany
    // @JoinTable(
    //     name = "order_item",
    //     joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
    //     inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    // )
    // private List<Item> items = new ArrayList<>();

    

    // public Order(
    //         double totalprice,
    //         Account account, List<Item> items) {
    //     this.totalprice = totalprice;
    //     this.account = account;
    //     this.items = items;
    // }



    // public Order placeOrderFromCart(Account account) {
    //     Cart cart = account.getCart();
    //     double totalprice = cart.getTotalprice();
    //     Order order = new Order(totalprice, account, items);
    //     return order;
    // }

}
