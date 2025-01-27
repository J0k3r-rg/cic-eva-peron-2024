package com.j0k3r_dev.cic_eva_peron.security.permissions;

import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    @Builder.Default
    private Boolean enable = true;
}
