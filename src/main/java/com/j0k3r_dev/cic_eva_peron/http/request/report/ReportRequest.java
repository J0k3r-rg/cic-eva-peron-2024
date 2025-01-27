package com.j0k3r_dev.cic_eva_peron.http.request.report;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequest {

    @NotBlank
    private String idTitular;

    @NotBlank
    private String idEmployee;

    private List<String> members;

    @NotEmpty
    private List<String> items;

}
