package com.quan.shoppingcart.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
@Entity(name = "Item")
public class Item {
    @Id
    @SequenceGenerator(
        name = "item_sequence",
        sequenceName = "item_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "item_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 0, message = "price must be higher than 0")
    @Column(name = "price", nullable = false)
    private double price;

    @Min(value = 0, message = "quantity must be higher or equals to 0" )
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotBlank(message = "product code cannot be null")
    @Column(name = "productcode", nullable = false, unique = true)
    private String productcode;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<Cart> carts = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany(mappedBy = "OrderItems")
    private List<Order2> orders = new ArrayList<>();

    public Item( String name, double price, int quantity, String productcode) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productcode = productcode;
    }


    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", productcode="
                + productcode + "]";
    }

    public void subtractQuantity(int quantity) {
        this.quantity = this.quantity - quantity;
    }
    public void plusQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }
    

}
