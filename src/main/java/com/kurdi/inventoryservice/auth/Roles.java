package com.kurdi.inventoryservice.auth;

public enum Roles {
    ADMIN("ROLE_SUPPLIER"),
    Editor("ROLE_CUSTOMER");



    private final String role;
    Roles(String role) {
        this.role = role;
    }

    public String getRole()
    {
        return this.role;
    }
}
