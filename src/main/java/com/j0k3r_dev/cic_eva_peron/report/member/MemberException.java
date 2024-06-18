package com.j0k3r_dev.cic_eva_peron.report.member;

import lombok.Getter;

@Getter
public class MemberException extends Exception{

    private final Integer code;

    public MemberException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

}
