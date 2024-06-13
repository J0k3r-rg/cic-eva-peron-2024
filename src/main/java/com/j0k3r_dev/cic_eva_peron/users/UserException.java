package com.j0k3r_dev.cic_eva_peron.users;

import lombok.Getter;

@Getter
public class UserException extends Exception{
    private Integer code;

    public UserException(String msg, Integer code){
        super(msg);
        this.code=code;
    }

}
