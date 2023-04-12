package br.com.deliverymental.deliverymental.enums;

public enum Roles {
    USER(0),
    PSYCHOLOGIST(1),
    ADMIN(2);

    public final int roleId;

    Roles(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
