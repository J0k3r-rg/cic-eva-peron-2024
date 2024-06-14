package com.j0k3r_dev.cic_eva_peron.registry;

import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registries")
public class RegistryEntity {

    @Id
    @UuidGenerator
    private String id;

    private String description;

    @ManyToOne
    private UserEntity userEntity;

}
