package com.j0k3r_dev.cic_eva_peron.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Employee extends UserEntity{

    @Column(unique = true)
    private String fileNumber;

    private String sector;

    private String position;

}
