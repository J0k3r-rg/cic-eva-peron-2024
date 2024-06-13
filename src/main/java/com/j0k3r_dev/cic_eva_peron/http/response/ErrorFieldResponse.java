package com.j0k3r_dev.cic_eva_peron.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFieldResponse {
    private String field;

    private String msg;
}
