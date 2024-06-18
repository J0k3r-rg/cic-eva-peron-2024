package com.j0k3r_dev.cic_eva_peron.normalization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "type_of_identifications")
public class TypeOfIdentification {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String name;
}
