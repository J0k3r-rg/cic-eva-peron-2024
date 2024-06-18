package com.j0k3r_dev.cic_eva_peron.http.request.report;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitularRequest {

    @NotBlank
    private String names;

    @NotBlank
    private String lastNames;

    @NotBlank
    private String typeOfIdentification;

    @NotBlank
    private String identificationNumber;

    @NotBlank
    private String birthDateString;

    @NotBlank
    private String birthplace;

    @NotBlank
    private String nationality;

    private String phone;

    @NotBlank
    private String address;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

}
