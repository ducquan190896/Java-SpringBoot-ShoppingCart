package com.quan.shoppingcart.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartRequest {
    private Long accountid;
    private Long  itemid;
    private int amount;
    @Override
    public String toString() {
        return "CartRequest [accountid=" + accountid + ", itemid=" + itemid + ", amount=" + amount + "]";
    }

    
}
