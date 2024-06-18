package com.j0k3r_dev.cic_eva_peron.report.member;

import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "members")
public class Member {

    @Id
    @UuidGenerator //  :/ enserio¡=? XD
    private String id;

    private String names;

    private String lastNames;

    @ManyToOne
    private TypeOfIdentification typeOfIdentification;

    @Column(unique = true)
    private String identificationNumber;

    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    private String relationship;

    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

}
