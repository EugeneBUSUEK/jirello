package com.tinkoffworkshop.jirello.model.enums;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;

import java.util.stream.Stream;

public enum RoleType {
    ADMIN("admin"),
    USER("user"),
    SPECTATOR("spectator");

    private String role;

    private RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static String getRoleTypeValue(String roleTypeName) {
        String finalRoleTypeName = roleTypeName.toUpperCase();

        return Stream.of(RoleType.values())
                .filter(roleType -> roleType.name().equals(finalRoleTypeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("role " + finalRoleTypeName + " not exist"))
                .getRole();
    }

    public static String getRoleTypeName(String role) {
        return Stream.of(RoleType.values())
                .filter(roleType -> roleType.getRole().equals(role))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("role " + role + " not exist"))
                .name();
    }
}
