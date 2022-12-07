package com.quan.shoppingcart.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Table(name = "order2")
@Entity(name = "Order2")
@Getter
@Setter
@NoArgsConstructor
 @AllArgsConstructor
public class Order2 {
    @Id
    @SequenceGenerator(
        name = "order2_sequence",
        sequenceName = "order2_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "order2_sequence"
    )
     @Column(name = "id")
    private Long id;
    @Min(value = 0, message = "total price of all items must be higher than equals to 0")
    @Column(name = "totalprice")
    private double totalprice;

    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToMany()
    @JoinTable(
        name = "order_item",
        joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> OrderItems;

    

    public Order2(
            double totalprice,
            Account account, List<Item> items) {
        this.totalprice = totalprice;
        this.account = account;
        this.OrderItems = items;
    }



    // public void placeOrderFromCart(Account account) {
    //     Cart cart = account.getCart();
    //     double totalprice = cart.getTotalprice();
    //     List<Item> items = cart.getItems();

        
    //     this.setOrderItems(items);
    //     this.setAccount(account);
    //     this.setTotalprice(totalprice);
       
    // }



    @Override
    public String toString() {
        return "Order2 [id=" + id + ", totalprice=" + totalprice + ", account=" + account + ", OrderItems=" + OrderItems
                + "]";
    }



  
  

}
