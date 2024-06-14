package com.j0k3r_dev.cic_eva_peron.security.permissions;

import lombok.Getter;

@Getter
public class PermissionException extends Exception{

    private final Integer code;

    public PermissionException(String msg, Integer code){
        super(msg);
        this.code = code;
    }

}
