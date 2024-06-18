package com.j0k3r_dev.cic_eva_peron.registry;

import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "registries")
public class RegistryEntity {

    @Id
    @UuidGenerator
    private String id;

    private String description;

    @ManyToOne
    private UserEntity userEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

}
