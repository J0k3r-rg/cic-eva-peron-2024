package com.j0k3r_dev.cic_eva_peron.users;

import com.j0k3r_dev.cic_eva_peron.security.roles.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserEntity implements UserDetails {

    @Id
    @UuidGenerator
    @Column(length = 40)
    private String id;

    private Integer codeNumberActualization;

    private Integer codeNumberLogin;

    @Column(unique = true, length = 45)
    private String username;

    @Column(length = 200)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String dni;

    private String names;

    private String lastNames;

    @Column(unique = true)
    private String urlImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Builder.Default
    private Boolean enable = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role.getName()));
        this.role.getPermissions().forEach(
                permission ->
                        authorities.add(new SimpleGrantedAuthority("Permission_"+permission.getName()))
        );
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
