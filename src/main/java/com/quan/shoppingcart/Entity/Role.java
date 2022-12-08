package com.quan.shoppingcart.Entity;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SELLER("ROLE_SELLER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
//the authorities for accounts