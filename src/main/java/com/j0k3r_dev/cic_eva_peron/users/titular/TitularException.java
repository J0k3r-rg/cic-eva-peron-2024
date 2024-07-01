package com.j0k3r_dev.cic_eva_peron.users.titular;

import lombok.Getter;

@Getter
public class TitularException extends Exception{

    private final Integer code;

    public TitularException(String message, Integer code){
        super(message);
        this.code = code;
    }

}
