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



@Table(name = "cart")
@Entity(name = "Cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @SequenceGenerator(
        name = "cart_sequence",
        sequenceName = "cart_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "cart_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Min(value = 0, message = "the total price must be higher than 0")
    @Column(name = "totalprice", nullable = false)
    private double totalprice;

    // @JsonIgnore
    @ManyToMany()
    @JoinTable(
        name = "cart_item",
        joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id") 
    )
    private List<Item> items = new ArrayList<>();

    @OneToOne(mappedBy = "cart", fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public Cart( double totalprice, List<Item> items,
            Account account) {
        this.totalprice = totalprice;
        this.items = items;
        this.account = account;
    }
    public Cart(Account account) {
        this.totalprice = 0;
        this.items = new ArrayList<>();
        this.account = account;
}
    @Override
    public String toString() {
        return "Cart [id=" + id + ", totalprice=" + totalprice + ", account=" + account + "]";
    }

    public void addItem(Item item) {
        this.items.add(item);
        this.totalprice = this.getTotalprice() + item.getPrice();
        
    }
    public void removeItem(Item item) {
        this.totalprice = this.getTotalprice() - item.getPrice();
        this.items.remove(item);
    }

}
