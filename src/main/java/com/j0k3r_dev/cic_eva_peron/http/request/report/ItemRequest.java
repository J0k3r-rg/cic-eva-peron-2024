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
public class ItemRequest {

    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

}
