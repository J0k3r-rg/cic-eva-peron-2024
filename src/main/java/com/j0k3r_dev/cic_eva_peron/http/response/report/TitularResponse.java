package com.j0k3r_dev.cic_eva_peron.http.response.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitularResponse {

    private String id;

    private String names;

    private String lastNames;

    private String typeOfIdentification;

    private String identificationNumber;

    private String birthDate;

    private String birthplace;

    private String nationality;

    private String phone;

    private String address;

    private String neighborhood;

    private String city;

    private String postalCode;
}
