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
public class MemberRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String names;

    @NotBlank
    private String lastNames;

    @NotBlank
    private String typeOfIdentificationString;

    @NotBlank
    private String identificationNumber;

    @NotBlank
    private String  birthdateString;

    @NotBlank
    private String relationship;

}
