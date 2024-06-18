package com.j0k3r_dev.cic_eva_peron.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizationResponse {

    private String token;

    private String token_actualization;

}
