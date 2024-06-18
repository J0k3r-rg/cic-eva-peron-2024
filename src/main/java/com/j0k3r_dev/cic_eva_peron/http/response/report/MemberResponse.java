package com.j0k3r_dev.cic_eva_peron.http.response.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {

    private String id;

    private String names;

    private String lastNames;

    private String typeOfIdentificationString;

    private String identificationNumber;

    private String age;

    private String relationship;
}
