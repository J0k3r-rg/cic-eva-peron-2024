package com.j0k3r_dev.cic_eva_peron.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String id;

    private String token;

    private List<String> authorities;

    private String image;

    private String email;

}
