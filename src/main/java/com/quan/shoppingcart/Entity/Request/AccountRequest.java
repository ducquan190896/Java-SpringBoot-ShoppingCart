package com.quan.shoppingcart.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
    private String username;
    private String password;
    private String confirmedpassword;
    @Override
    public String toString() {
        return "UserRequest [username=" + username + ", password=" + password + ", confirmedpassword="
                + confirmedpassword + "]";
    }
}
