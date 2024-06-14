package com.j0k3r_dev.cic_eva_peron.security.roles;

import lombok.Getter;

@Getter
public class RoleException extends Exception{
    private final int code;

    public RoleException(String message, int code) {
        super(message);
        this.code = code;
    }
}
