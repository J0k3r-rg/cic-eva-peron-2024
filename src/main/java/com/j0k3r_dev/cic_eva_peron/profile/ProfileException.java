package com.j0k3r_dev.cic_eva_peron.profile;

import lombok.Getter;

@Getter
public class ProfileException extends Exception{

    private final Integer code;

    public ProfileException(String message, Integer code){
        super(message);
        this.code = code;
    }

}
